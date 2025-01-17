package charcoalPit.item;

import java.util.List;

import charcoalPit.core.ModItemRegistry;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.InteractionResult;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

public class ItemCrackedPot extends Item{

	public ItemCrackedPot() {
		super(new Item.Properties().stacksTo(1).tab(ModItemRegistry.CHARCOAL_PIT));
	}
	
	@OnlyIn(Dist.CLIENT)
	@Override
	public void appendHoverText(ItemStack stack, Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
		if(stack.hasTag()&&stack.getTag().contains("inventory")) {
			ItemStackHandler inv=new ItemStackHandler();
			inv.deserializeNBT(stack.getTag().getCompound("inventory"));
			tooltip.add(Component.literal("").append(inv.getStackInSlot(0).getHoverName()).append(Component.literal(" x"+inv.getStackInSlot(0).getCount())));
		}
	}
	
	@Override
	public InteractionResult useOn(UseOnContext context) {
		if(context.getLevel().isClientSide) {
			context.getLevel().playSound(context.getPlayer(), context.getClickedPos(), SoundEvents.GLASS_BREAK, SoundSource.PLAYERS, 1F, 1F);
			return InteractionResult.CONSUME;
		}else {
			if(context.getItemInHand().hasTag()&&context.getItemInHand().getTag().contains("inventory")) {
				ItemStackHandler inv=new ItemStackHandler();
				System.out.println(context.getItemInHand().getTag());
				inv.deserializeNBT(context.getItemInHand().getTag().getCompound("inventory"));
				ItemHandlerHelper.giveItemToPlayer(context.getPlayer(),inv.getStackInSlot(0));
			}
			if(context.getItemInHand().hasTag()&&context.getItemInHand().getTag().contains("xp")) {
				int x=context.getItemInHand().getTag().getInt("xp");
				while(x>0){
					int i=ExperienceOrb.getExperienceValue(x);
					x-=i;
					context.getLevel().addFreshEntity(new ExperienceOrb(context.getLevel(), (double)context.getPlayer().getX() + 0.5D, (double)context.getPlayer().getY() + 0.5D, (double)context.getPlayer().getZ() + 0.5D, i));
				}
			}
			context.getLevel().playSound(context.getPlayer(), context.getClickedPos(), SoundEvents.GLASS_BREAK, SoundSource.PLAYERS, 1F, 1F);
			context.getItemInHand().shrink(1);
			return InteractionResult.CONSUME;
		}
	}

}
