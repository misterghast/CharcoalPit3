package charcoalPit.core;

import charcoalPit.CharcoalPit;
import charcoalPit.block.BlockCeramicPot;
import charcoalPit.block.BlockPotteryKiln;
import charcoalPit.entity.Airplane;
import charcoalPit.item.tool.ModTiers;
import charcoalPit.recipe.PotteryKilnRecipe;
import charcoalPit.recipe.SquisherRecipe;
import charcoalPit.tile.TilePotteryKiln;
import charcoalPit.tile.TileWrathLantern;
import com.mojang.math.Matrix4f;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.HopperBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.AnvilRepairEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.ItemCraftedEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.items.IItemHandler;

import java.util.ArrayList;

@EventBusSubscriber(modid=CharcoalPit.MODID, bus=Bus.FORGE)
public class PileIgnitr {
	
	
	@SubscribeEvent
	public static void checkIgnition(BlockEvent.NeighborNotifyEvent event){
		if(!event.isCanceled()&&
				event.getLevel().getBlockState(event.getPos()).getBlock()==Blocks.FIRE){
			for(Direction facing:event.getNotifiedSides()){
				BlockPos pos=event.getPos().relative(facing);
				if(event.getLevel().getBlockState(pos).getBlock()==ModBlockRegistry.LogPile){
					//found log pile to ignite
					igniteLogs(event.getLevel(),pos);
					
				}else if(event.getLevel().getBlockState(pos).getBlock()==Blocks.COAL_BLOCK){
					//found coal pile to ignite
					igniteCoal(event.getLevel(),pos);
				} else if(event.getLevel().getBlockState(pos).getBlock()==ModBlockRegistry.Kiln){
				//found coal pile to ignite
					ignitePottery(event.getLevel(),pos);
			}
		}
		}
	}
	public static void ignitePottery(LevelAccessor world, BlockPos pos) {
		if(world.getBlockState(pos).getBlock()==ModBlockRegistry.Kiln&&
				world.getBlockState(pos).getValue(BlockPotteryKiln.TYPE)== BlockPotteryKiln.EnumKilnTypes.WOOD) {
			world.setBlock(pos, ModBlockRegistry.Kiln.defaultBlockState().setValue(BlockPotteryKiln.TYPE, BlockPotteryKiln.EnumKilnTypes.ACTIVE), 3);
			((TilePotteryKiln)world.getBlockEntity(pos)).setActive(true);
			for(int x=-1;x<=1;x++) {
				for(int z=-1;z<=1;z++) {
					ignitePottery(world, new BlockPos(pos.getX()+x, pos.getY(), pos.getZ()+z));
				}
			}
		}
	}
	public static void igniteLogs(LevelAccessor world, BlockPos pos){
		if(world.getBlockState(pos).getBlock()==ModBlockRegistry.LogPile){
			world.setBlock(pos, ModBlockRegistry.ActiveLogPile.defaultBlockState().setValue(BlockStateProperties.AXIS, world.getBlockState(pos).getValue(BlockStateProperties.AXIS)),2);
			Direction[] neighbors=Direction.values();
			for(int i=0;i<neighbors.length;i++){
				igniteLogs(world, pos.relative(neighbors[i]));
			}
		}
	}
	public static void igniteCoal(LevelAccessor world, BlockPos pos){
		if(world.getBlockState(pos).getBlock()==Blocks.COAL_BLOCK){
			world.setBlock(pos, ModBlockRegistry.ActiveCoalPile.defaultBlockState(),2);
			Direction[] neighbors=Direction.values();
			for(int i=0;i<neighbors.length;i++){
				igniteCoal(world, pos.relative(neighbors[i]));
			}
		}
	}
	@SubscribeEvent(priority=EventPriority.HIGH)
	public static void placeKiln(PlayerInteractEvent.RightClickBlock event) {
		//undye pots
		if(!event.isCanceled()&&event.getLevel().getBlockState(event.getPos()).getBlock()==Blocks.WATER_CAULDRON&&
				event.getLevel().getBlockState(event.getPos()).getValue(LayeredCauldronBlock.LEVEL)>0) {
			Block block=Block.byItem(event.getItemStack().getItem());
			if(block instanceof BlockCeramicPot&&block!=ModBlockRegistry.CeramicPot) {
				ItemStack stack=new ItemStack(ModBlockRegistry.CeramicPot, 1);
				stack.setTag(event.getItemStack().getTag());
				event.getEntity().setItemInHand(event.getHand(), stack);
				event.getLevel().setBlockAndUpdate(event.getPos(), Blocks.CAULDRON.defaultBlockState().setValue(LayeredCauldronBlock.LEVEL, event.getLevel().getBlockState(event.getPos()).getValue(LayeredCauldronBlock.LEVEL)-1));
				event.setUseBlock(Result.DENY);
				event.setUseItem(Result.DENY);
			}
		}

		if(!event.isCanceled()&&event.getEntity().isCrouching()&& PotteryKilnRecipe.isValidInput(event.getItemStack(), event.getLevel())&&
				event.getFace()==Direction.UP&&event.getLevel().getBlockState(event.getPos()).isFaceSturdy(event.getLevel(), event.getPos(), Direction.UP)&&
				event.getLevel().getBlockState(event.getPos().relative(Direction.UP)).getMaterial().isReplaceable()) {
			if(!event.getLevel().isClientSide()) {
				event.getLevel().setBlockAndUpdate(event.getPos().relative(Direction.UP), ModBlockRegistry.Kiln.defaultBlockState());
				TilePotteryKiln tile=((TilePotteryKiln)event.getLevel().getBlockEntity(event.getPos().relative((Direction.UP))));
				event.getEntity().setItemInHand(event.getHand(), tile.pottery.insertItem(0, event.getItemStack(), false));
				tile.setChanged();
				event.getLevel().playSound(null, event.getPos(), SoundType.GRAVEL.getPlaceSound(), SoundSource.BLOCKS, 1F, 1F);
			}
			event.setUseBlock(Result.DENY);
			event.setUseItem(Result.DENY);
		}
	}

	@SubscribeEvent
	public static void addLoot(LootTableLoadEvent event) {
		if(event.getName().toString().equals("minecraft:chests/end_city_treasure")){
			event.getTable().addPool(LootPool.lootPool().add(LootTableReference.lootTableReference(new ResourceLocation(CharcoalPit.MODID, "end_aeternalis"))).build());
		}
		if(event.getName().toString().equals("minecraft:entities/chicken")){
			event.getTable().addPool(LootPool.lootPool().add(LootTableReference.lootTableReference(new ResourceLocation(CharcoalPit.MODID,"leeks"))).build());
		}
		if(event.getName().toString().equals("minecraft:entities/squid")){
			event.getTable().addPool(LootPool.lootPool().add(LootTableReference.lootTableReference(new ResourceLocation(CharcoalPit.MODID,"calamari"))).build());
		}
		if(event.getName().toString().equals("minecraft:chests/simple_dungeon")){
			event.getTable().addPool(LootPool.lootPool().add(LootTableReference.lootTableReference(new ResourceLocation(CharcoalPit.MODID,"saplings"))).build());
		}
		if(event.getName().toString().equals("minecraft:chests/abandoned_mineshaft")){
			event.getTable().addPool(LootPool.lootPool().add(LootTableReference.lootTableReference(new ResourceLocation(CharcoalPit.MODID,"saplings"))).build());
		}
		if(event.getName().toString().equals("minecraft:chests/stronghold_corridor")){
			event.getTable().addPool(LootPool.lootPool().add(LootTableReference.lootTableReference(new ResourceLocation(CharcoalPit.MODID,"saplings"))).build());
		}
		if(event.getName().toString().equals("minecraft:chests/stronghold_crossing")){
			event.getTable().addPool(LootPool.lootPool().add(LootTableReference.lootTableReference(new ResourceLocation(CharcoalPit.MODID,"saplings"))).build());
		}
	}
	@SubscribeEvent
	public static void savePotInventory(ItemCraftedEvent event) {
		if(Block.byItem(event.getCrafting().getItem()) instanceof BlockCeramicPot) {
			for(int i=0;i<event.getInventory().getContainerSize();i++) {
				if(Block.byItem(event.getInventory().getItem(i).getItem()) instanceof BlockCeramicPot) {
					event.getCrafting().setTag(event.getInventory().getItem(i).getTag());
					return;
				}
			}
		}
	}
	@SubscribeEvent
	public static void furnaceFuel(FurnaceFuelBurnTimeEvent event) {
		if(event.getItemStack().getItem()==ModItemRegistry.CreosoteBucket)
			event.setBurnTime(4800);
		//if(event.getItemStack().getItem()==ModItemRegistry.BioDieselBucket)
			//event.setBurnTime(25600);
		if(event.getItemStack().getItem()==ModItemRegistry.CharcoalBlock)
			event.setBurnTime(16000);
		//if(event.getItemStack().getItem()==ModItemRegistry.EthanolBucket)
			//event.setBurnTime(15200);
	}
	
	@SubscribeEvent
	public static void mineCopper(PlayerEvent.HarvestCheck event){
		Block block=event.getTargetBlock().getBlock();
		if(block==Blocks.COPPER_ORE||block==Blocks.DEEPSLATE_COPPER_ORE||block==Blocks.RAW_COPPER_BLOCK){
			if(event.getEntity().getInventory().getSelected().isCorrectToolForDrops(Blocks.COAL_ORE.defaultBlockState()))
				event.setCanHarvest(true);
		}
	}
	
	@SubscribeEvent
	public static void rapairTrident(AnvilUpdateEvent event){
		if(event.getLeft().isDamageableItem()&&event.getLeft().getItem()== Items.TRIDENT&&MethodHelper.isItemInTag(event.getRight(),MethodHelper.ORICHALCUM)){
			int l2 = Math.min(event.getLeft().getDamageValue(), event.getLeft().getMaxDamage() / 4);
			ItemStack stack1=event.getLeft().copy();
			int i3;
			for(i3=0;l2>0&&i3<event.getRight().getCount();i3++){
				int j3=stack1.getDamageValue()-l2;
				stack1.setDamageValue(j3);
				l2=Math.min(stack1.getDamageValue(),stack1.getMaxDamage()/4);
			}
			event.setMaterialCost(i3);
			event.setCost(event.getCost()+i3);
			stack1.setRepairCost(AnvilMenu.calculateIncreasedRepairCost(stack1.getBaseRepairCost()));
			event.setOutput(stack1);
		}else if(event.getLeft().isDamageableItem()&&event.getRight().getItem()==ModItemRegistry.NetherShard){
			ItemStack stack1=event.getLeft().copy();
			stack1.setDamageValue(0);
			event.setMaterialCost(1);
			event.setCost(event.getCost()+1);
			stack1.setRepairCost(AnvilMenu.calculateIncreasedRepairCost(stack1.getBaseRepairCost()));
			event.setOutput(stack1);
		}
	}
	
	@SubscribeEvent
	public static void aquaAffinity(PlayerEvent.BreakSpeed event){
		if(event.getEntity().isEyeInFluid(FluidTags.WATER)&&isOrichalcumTool(event.getEntity().getMainHandItem().getItem())&&!EnchantmentHelper.hasAquaAffinity(event.getEntity())){
			event.setNewSpeed(event.getNewSpeed()*5);
		}
	}
	
	public static boolean isOrichalcumTool(Item item){
		if(item instanceof DiggerItem tool){
			return tool.getTier()== ModTiers.ORICHALCUM;
		}
		return false;
	}
	
	@SubscribeEvent
	public static void WrathLanternKill(LivingDeathEvent event){
		if(!event.getEntity().level.isClientSide()&&event.getSource().getEntity() instanceof LivingEntity &&!(event.getSource().getEntity() instanceof Player)){
			//entity exists but not player
			Level level=event.getEntity().level;
			BlockPos entityPos=event.getEntity().blockPosition();
			for(int i=-1;i<2;i+=1){
				for(int j=-1;j<2;j+=1){
					LevelChunk chunk=level.getChunkAt(entityPos.offset(i*16,0,j*16));
					for(var entry:chunk.getBlockEntities().entrySet()){
						if(entry.getValue() instanceof TileWrathLantern){
							BlockPos pos=entry.getValue().getBlockPos();
							if(Math.abs(pos.getX()-entityPos.getX())<=4&&Math.abs(pos.getY()-entityPos.getY())<=4&&Math.abs(pos.getZ()-entityPos.getZ())<=4){
								event.getEntity().setLastHurtByPlayer(FakePlayerFactory.getMinecraft((ServerLevel) level));
								return;
							}
						}
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void CropTrades(VillagerTradesEvent event){
		Trades.CropTrades(event);
	}
	
	@SubscribeEvent
	public static void SaplingTrades(WandererTradesEvent event){
		Trades.SaplingTrades(event);
	}
}
