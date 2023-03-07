package charcoalPit.item;

import java.awt.*;
import java.util.List;

import charcoalPit.core.ModItemRegistry;
import charcoalPit.gui.ClayPotContainer2;
import charcoalPit.recipe.OreKilnRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.network.NetworkHooks;

public class ItemClayPot extends Item{
	
	public ItemClayPot() {
		super(new Item.Properties().stacksTo(1).tab(ModItemRegistry.CHARCOAL_PIT).stacksTo(1));
	}
	
	@OnlyIn(Dist.CLIENT)
	@Override
	public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
		/*if(stack.hasTag()&&stack.getTag().contains("inventory")) {
			ItemStackHandler inv=new ItemStackHandler();
			inv.deserializeNBT(stack.getTag().getCompound("inventory"));
			if(OreKilnRecipe.oreKilnIsEmpty(inv)) {
				tooltip.add(Component.literal("Empty"));
			}else {
				ItemStack out=OreKilnRecipe.OreKilnGetOutput(stack.getTag().getCompound("inventory"), Minecraft.getInstance().level);
				if(out.isEmpty()) {
					tooltip.add(Component.literal(ChatFormatting.DARK_RED+"Invalid"+" ("+OreKilnRecipe.oreKilnGetOreAmount(inv)+"/8)"));
				}else {
					Component tx=out.getHoverName().plainCopy().append(Component.literal(" x"+out.getCount()));
					tx.getStyle().applyFormat(ChatFormatting.GREEN);
					tooltip.add(tx);
				}
			}
		}*/
		if(stack.hasTag()&&stack.getTag().contains("result")){
			if(stack.getTag().getBoolean("empty")){
				tooltip.add(Component.literal("Empty"));
			}else{
				ItemStack result=ItemStack.of(stack.getTag().getCompound("result"));
				ItemStackHandler inv=new ItemStackHandler();
				inv.deserializeNBT(stack.getTag().getCompound("inventory"));
				if(result.isEmpty()){
					tooltip.add(Component.literal(ChatFormatting.DARK_RED+"Invalid"+" ("+OreKilnRecipe.oreKilnGetOreAmount(inv)+"/8)"));
				}else{
					tooltip.add(Component.literal(ChatFormatting.GREEN+"").append(result.getHoverName()).append(Component.literal(" x"+result.getCount())).withStyle(ChatFormatting.GREEN));
				}
			}
		}
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
		if(!worldIn.isClientSide)
			NetworkHooks.openScreen((ServerPlayer)playerIn, new MenuProvider() {
				
				@Override
				public AbstractContainerMenu createMenu(int arg0, Inventory arg1, Player arg2) {
					return new ClayPotContainer2(arg0, arg1, arg2.getInventory().selected, worldIn);
				}
				
				@Override
				public Component getDisplayName() {
					return Component.translatable("screen.charcoal_pit.clay_pot");
				}

			},buf->buf.writeByte((byte)playerIn.getInventory().selected));
		return super.use(worldIn, playerIn, handIn);
	}
	
}
