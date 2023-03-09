package charcoalPit.core;

import charcoalPit.CharcoalPit;
import charcoalPit.block.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

//@EventBusSubscriber(modid=CharcoalPit.MODID, bus=Bus.MOD)
public class ModBlockRegistry {

	public static BlockThatch Thatch=new BlockThatch();
	public static RotatedPillarBlock LogPile=new BlockLogPile();
	public static Block WoodAsh=new BlockAsh(),CoalAsh=new BlockAsh(),SandyBrick=new Block(Properties.of(Material.STONE, MaterialColor.COLOR_ORANGE).strength(2, 6).requiresCorrectToolForDrops()),
			CokeBlock=new Block(Properties.of(Material.WOOD, MaterialColor.COLOR_BLACK).strength(5F, 6F).requiresCorrectToolForDrops()) {
		public int getFireSpreadSpeed(net.minecraft.world.level.block.state.BlockState state, net.minecraft.world.level.BlockGetter world, net.minecraft.core.BlockPos pos, net.minecraft.core.Direction face) {
			return 5;
		};
		public int getFlammability(net.minecraft.world.level.block.state.BlockState state, net.minecraft.world.level.BlockGetter world, net.minecraft.core.BlockPos pos, net.minecraft.core.Direction face) {
			return 5;
		};
	};
	public static FallingBlock Ash=new FallingBlock(Properties.of(Material.SAND, MaterialColor.COLOR_LIGHT_GRAY).strength(0.5F).sound(SoundType.SAND));
	public static SlabBlock SandySlab=new SlabBlock(Properties.of(Material.STONE, MaterialColor.COLOR_ORANGE).strength(2, 6).requiresCorrectToolForDrops());
	public static StairBlock SandyStair=new StairBlock(()->SandyBrick.defaultBlockState(), Properties.of(Material.STONE, MaterialColor.COLOR_ORANGE).strength(2, 6).requiresCorrectToolForDrops());
	public static WallBlock SandyWall=new WallBlock(Properties.of(Material.STONE, MaterialColor.COLOR_ORANGE).strength(2, 6).requiresCorrectToolForDrops());
	
	public static BlockActivePile ActiveLogPile=new BlockActivePile(false, Properties.of(Material.WOOD, MaterialColor.WOOD).strength(2F).sound(SoundType.WOOD));
	public static BlockActivePile ActiveCoalPile=new BlockActivePile(true, Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).strength(5F, 6F).requiresCorrectToolForDrops());
	public static BlockCreosoteCollector BrickCollector=new BlockCreosoteCollector(Properties.copy(Blocks.BRICKS)), SandyCollector=new BlockCreosoteCollector(Properties.copy(SandyBrick)),
			NetherCollector=new BlockCreosoteCollector(Properties.copy(Blocks.NETHER_BRICKS)),EndCollector=new BlockCreosoteCollector(Properties.copy(Blocks.END_STONE_BRICKS));
	public static BlockCeramicPot CeramicPot=new BlockCeramicPot(MaterialColor.COLOR_ORANGE);/*,WhitePot=new BlockCeramicPot(MaterialColor.TERRACOTTA_WHITE),
			OrangePot=new BlockCeramicPot(MaterialColor.TERRACOTTA_ORANGE),MagentaPot=new BlockCeramicPot(MaterialColor.TERRACOTTA_MAGENTA),
			LightBluePot=new BlockCeramicPot(MaterialColor.TERRACOTTA_LIGHT_BLUE),YellowPot=new BlockCeramicPot(MaterialColor.TERRACOTTA_YELLOW),
			LimePot=new BlockCeramicPot(MaterialColor.TERRACOTTA_LIGHT_GREEN),PinkPot=new BlockCeramicPot(MaterialColor.TERRACOTTA_PINK),
			GrayPot=new BlockCeramicPot(MaterialColor.TERRACOTTA_GRAY),LightGrayPot=new BlockCeramicPot(MaterialColor.TERRACOTTA_LIGHT_GRAY),
			CyanPot=new BlockCeramicPot(MaterialColor.TERRACOTTA_CYAN),PurplePot=new BlockCeramicPot(MaterialColor.TERRACOTTA_PURPLE),
			BluePot=new BlockCeramicPot(MaterialColor.TERRACOTTA_BLUE),BrownPot=new BlockCeramicPot(MaterialColor.TERRACOTTA_BROWN),
			GreenPot=new BlockCeramicPot(MaterialColor.TERRACOTTA_GREEN),RedPot=new BlockCeramicPot(MaterialColor.TERRACOTTA_RED),
			BlackPot=new BlockCeramicPot(MaterialColor.TERRACOTTA_BLACK);*/
	public static BlockBellows Bellows=new BlockBellows();
	
	public static BlockBarrel Barrel=new BlockBarrel();
	public static BlockMechanicalBellows MechanicalBellows=new BlockMechanicalBellows();
	
	public static BlockLeeks Leeks=new BlockLeeks(Properties.copy(Blocks.WHEAT));
	public static BlockCorn Corn=new BlockCorn(Properties.copy(Blocks.WHEAT));
	public static BlockSunflowerCrop Sunflower=new BlockSunflowerCrop(Properties.copy(Blocks.WHEAT));
	
	/*public static SaplingBlock AppleSapling=new SaplingBlock(new ModFeatures.AppleTree(),BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS));
	public static SaplingBlock CherrySapling=new SaplingBlock(new ModFeatures.CherryTree(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS));
	public static SaplingBlock DragonSapling=new SaplingBlock(new ModFeatures.DragonTree(),BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING));
	public static SaplingBlock ChestnutSapling=new SaplingBlock(new ModFeatures.ChestnutTree(),BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING));
	public static SaplingBlock OliveSapling=new SaplingBlock(new ModFeatures.OliveTree(),BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING));
	public static SaplingBlock OrangeSapling=new SaplingBlock(new ModFeatures.OrangeTree(),BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING));*/
	
	public static BlockFruitLeaves AppleLeaves=new BlockFruitLeaves(Properties.copy(Blocks.OAK_LEAVES), null, 0.166F);
	public static BlockFruitLeaves CherryLeaves=new BlockFruitLeaves(Properties.copy(Blocks.BIRCH_LEAVES), null, 0.333F);
	public static BlockFruitLeaves DragonLeaves=new BlockFruitLeaves(Properties.copy(Blocks.BIRCH_LEAVES),null, 0.1F);
	public static BlockFruitLeaves ChestnutLeaves=new BlockFruitLeaves(Properties.copy(Blocks.DARK_OAK_LEAVES),null,0.166F);
	public static BlockFruitLeaves OliveLeaves=new BlockFruitLeaves(Properties.copy(Blocks.ACACIA_LEAVES),null,0.133F);
	public static BlockFruitLeaves OrangeLeaves=new BlockFruitLeaves(Properties.copy(Blocks.ACACIA_LEAVES),null,0.166F);
	
	public static BlockBananaPod BanananaPod=new BlockBananaPod(Properties.copy(Blocks.COCOA));
	public static BlockCocoPod CoconutPod=new BlockCocoPod(Properties.copy(Blocks.COCOA));
	
	public static BlockGenieLight GenieLight=new BlockGenieLight();
	
	public static BlockFeedingThrough FeedingThrough =new BlockFeedingThrough(Properties.copy(Blocks.OAK_PLANKS));
	public static BlockFeedingThrough FeedingThroughBirch =new BlockFeedingThrough(Properties.copy(Blocks.OAK_PLANKS));
	//public static BlockFeedingThrough FeedingThroughJungle =new BlockFeedingThrough(Properties.copy(Blocks.OAK_PLANKS));
	public static BlockFeedingThrough FeedingThroughSpruce =new BlockFeedingThrough(Properties.copy(Blocks.OAK_PLANKS));
	//public static BlockFeedingThrough FeedingThroughDark =new BlockFeedingThrough(Properties.copy(Blocks.OAK_PLANKS));
	public static BlockFeedingThrough FeedingThroughAcacia =new BlockFeedingThrough(Properties.copy(Blocks.OAK_PLANKS));
	//public static BlockFeedingThrough FeedingThroughCrimson =new BlockFeedingThrough(Properties.copy(Blocks.OAK_PLANKS));
	public static BlockFeedingThrough FeedingThroughWarped =new BlockFeedingThrough(Properties.copy(Blocks.OAK_PLANKS));
	
	public static BlockNestBox NestBox=new BlockNestBox(Properties.copy(Blocks.OAK_PLANKS));
	public static BlockBloomeryy Bloomeryy=new BlockBloomeryy(Properties.copy(Blocks.FURNACE));
	public static Block CharcoalBlock=new Block(Properties.copy(Blocks.COAL_BLOCK)){
		@Override
		public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
			return 5;
		}
		
		@Override
		public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
			return 5;
		}
	};
	public static BlockBloom Bloom=new BlockBloom(Properties.copy(Blocks.RAW_IRON_BLOCK));
	public static BlockBlastFurnace BlastFurnace=new BlockBlastFurnace(Properties.copy(Blocks.BLAST_FURNACE));
	public static BlockDistillery Distillery=new BlockDistillery(Properties.copy(Blocks.COPPER_BLOCK));
	public static BlockSteamPress SteamPress=new BlockSteamPress(Properties.copy(Blocks.BLAST_FURNACE));
	
	public static BlockWrathLantern WrathLantern=new BlockWrathLantern(Properties.copy(Blocks.LANTERN));

	public static BlockPotteryKiln Kiln = new BlockPotteryKiln();
	
	
	/*public static DoorBlock BrickDoor=new DoorBlock(AbstractBlock.Properties.from(Blocks.IRON_DOOR)),
			SandyDoor=new DoorBlock(Properties.from(Blocks.IRON_DOOR)),
			NetherDoor=new DoorBlock(Properties.from(Blocks.IRON_DOOR)),
			EndDoor=new DoorBlock(Properties.from(Blocks.IRON_DOOR));*/
	
	//public static BlockCreosote Creosote=new BlockCreosote();
	
	//@SubscribeEvent
	public static void registerBlocks(RegisterEvent event) {
		event.register(ForgeRegistries.Keys.BLOCKS, helper -> {
			helper.register(new ResourceLocation(CharcoalPit.MODID, "thatch"), Thatch);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "log_pile"), LogPile);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "wood_ash"), WoodAsh);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "coal_ash"), CoalAsh);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "coke"), CokeBlock);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "ash"), Ash);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "active_log_pile"), ActiveLogPile);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "active_coal_pile"), ActiveCoalPile);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "sandy_brick"), SandyBrick);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "sandy_slab"), SandySlab);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "sandy_stair"), SandyStair);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "sandy_wall"), SandyWall);
			//helper.register(new ResourceLocation(CharcoalPit.MODID, "creosote_oil"), Creosote);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "brick_collector"), BrickCollector);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "sandy_collector"), SandyCollector);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "nether_collector"), NetherCollector);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "end_collector"), EndCollector);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "bellows"), Bellows);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "barrel"), Barrel);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "mechanical_bellows"), MechanicalBellows);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "leeks"), Leeks);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "corn"), Corn);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "apple_leaves"), AppleLeaves);
			//helper.register(new ResourceLocation(CharcoalPit.MODID, "apple_sapling"), AppleSapling);
			//helper.register(new ResourceLocation(CharcoalPit.MODID, "cherry_sapling"), CherrySapling);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "cherry_leaves"), CherryLeaves);
			//helper.register(new ResourceLocation(CharcoalPit.MODID, "dragon_sapling"), DragonSapling);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "dragon_leaves"), DragonLeaves);
			//helper.register(new ResourceLocation(CharcoalPit.MODID, "chestnut_sapling"), ChestnutSapling);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "chestnut_leaves"), ChestnutLeaves);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "banana_pod"), BanananaPod);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "sunflower_crop"), Sunflower);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "coconut_pod"), CoconutPod);
			//helper.register(new ResourceLocation(CharcoalPit.MODID, "olive_sapling"), OliveSapling);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "olive_leaves"), OliveLeaves);
			//helper.register(new ResourceLocation(CharcoalPit.MODID, "orange_sapling"), OrangeSapling);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "orange_leaves"), OrangeLeaves);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "genie_light"), GenieLight);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "nest_box"), NestBox);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "bloomeryy"), Bloomeryy);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "charcoal_block"), CharcoalBlock);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "bloom"), Bloom);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "blast_furnace"), BlastFurnace);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "distillery"), Distillery);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "steam_press"), SteamPress);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "wrath_lantern"), WrathLantern);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "ceramic_pot"), CeramicPot);
			/*helper.register(new ResourceLocation(CharcoalPit.MODID, "yellow_pot"), YellowPot);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "white_pot"), WhitePot);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "purple_pot"), PurplePot);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "pink_pot"), PinkPot);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "orange_pot"), OrangePot);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "lime_pot"), LimePot);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "light_gray_pot"), LightGrayPot);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "green_pot"), GreenPot);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "gray_pot"), GrayPot);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "cyan_pot"), CyanPot);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "blue_pot"), BluePot);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "black_pot"), BlackPot);*/
			helper.register(new ResourceLocation(CharcoalPit.MODID, "feeding_through"), FeedingThrough);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "feeding_through_birch"), FeedingThroughBirch);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "feeding_through_spruce"), FeedingThroughSpruce);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "feeding_through_acacia"), FeedingThroughAcacia);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "feeding_through_warped"), FeedingThroughWarped);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "pottery_kiln"), Kiln);
		});
		/*event.getRegistry().registerAll(CeramicPot.setRegistryName("ceramic_pot"), YellowPot.setRegistryName("yellow_pot"), WhitePot.setRegistryName("white_pot"),
				RedPot.setRegistryName("red_pot"), PurplePot.setRegistryName("purple_pot"), PinkPot.setRegistryName("pink_pot"), OrangePot.setRegistryName("orange_pot"),
				MagentaPot.setRegistryName("magenta_pot"), LimePot.setRegistryName("lime_pot"), LightGrayPot.setRegistryName("light_gray_pot"),
				LightBluePot.setRegistryName("light_blue_pot"), GreenPot.setRegistryName("green_pot"), GrayPot.setRegistryName("gray_pot"), CyanPot.setRegistryName("cyan_pot"),
				BrownPot.setRegistryName("brown_pot"), BluePot.setRegistryName("blue_pot"), BlackPot.setRegistryName("black_pot"));
		event.getRegistry().registerAll(FeedingThrough.setRegistryName("feeding_through"), FeedingThroughBirch.setRegistryName("feeding_through_birch"),
				FeedingThroughJungle.setRegistryName("feeding_through_jungle"), FeedingThroughSpruce.setRegistryName("feeding_through_spruce"),
				FeedingThroughDark.setRegistryName("feeding_through_dark"), FeedingThroughAcacia.setRegistryName("feeding_through_acacia"),
				FeedingThroughCrimson.setRegistryName("feeding_through_crimson"), FeedingThroughWarped.setRegistryName("feeding_through_warped"));*/
	}

	private static class CustomBlockTagsProvider extends BlockTagsProvider {
		public CustomBlockTagsProvider(DataGenerator generator, String modid, ExistingFileHelper existingFileHelper) {
			super(generator, modid, existingFileHelper);
		}

		@Override
		protected void addTags() {
			this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(CokeBlock, ActiveCoalPile, SandyBrick, SandySlab, SandyStair, SandyWall, BrickCollector, SandyCollector,
					NetherCollector, EndCollector, MechanicalBellows, /*CeramicPot, YellowPot, WhitePot, RedPot, PurplePot, PinkPot, OrangePot, MagentaPot,
					LimePot, LightGrayPot, LightBluePot, GreenPot, GrayPot, CyanPot, BrownPot, BluePot, BlackPot, */Bloomeryy, CharcoalBlock, Bloom, BlastFurnace);
			this.tag(BlockTags.MINEABLE_WITH_AXE).add(LogPile, ActiveLogPile, Bellows, Barrel, BanananaPod, CoconutPod, NestBox,
					FeedingThrough,FeedingThroughAcacia,FeedingThroughBirch,/*FeedingThroughDark,*///FeedingThroughCrimson,FeedingThroughJungle,
					FeedingThroughWarped,FeedingThroughSpruce);
			this.tag(BlockTags.MINEABLE_WITH_HOE).add(Thatch, AppleLeaves, CherryLeaves, DragonLeaves, ChestnutLeaves,OrangeLeaves,OliveLeaves);
			this.tag(BlockTags.MINEABLE_WITH_SHOVEL).add(WoodAsh, CoalAsh, Ash);
		}
	}

	//@SubscribeEvent
	public static void datagen(GatherDataEvent event){
			event.getGenerator().addProvider(
					event.includeServer(), new CustomBlockTagsProvider
					(
						event.getGenerator(),
						CharcoalPit.MODID,
						event.getExistingFileHelper()
					)
			);
	}
	
}


