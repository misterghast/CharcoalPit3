package charcoalPit.core;

import charcoalPit.tile.TileBloom;
import charcoalPit.tile.TileCeramicPot;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DispenserBlock;

public class DispenserPlaceBloom extends DefaultDispenseItemBehavior{
	
	@Override
	protected ItemStack execute(BlockSource source, ItemStack stack) {
		if (!source.getLevel().isClientSide()) {
			Direction facing = (Direction) source.getBlockState().getValue(DispenserBlock.FACING);
			BlockPos pos = source.getPos().relative(facing);
			if (source.getLevel().getBlockState(pos).getMaterial().isReplaceable()) {
				source.getLevel().setBlockAndUpdate(pos, Block.byItem(stack.getItem()).defaultBlockState());
				if (stack.hasTag() && stack.getTag().contains("inventory")) {
					((TileCeramicPot) source.getLevel().getBlockEntity(pos)).inventory.deserializeNBT(stack.getTag().getCompound("inventory"));
				} else if (stack.hasTag() && stack.getTag().contains("items")) {
					TileBloom bloom = ((TileBloom) source.getLevel().getBlockEntity(pos));
					bloom.items = ItemStack.of(stack.getTag().getCompound("items"));
					bloom.setChanged();
				}
				source.getLevel().playSound(null, pos, SoundEvents.STONE_PLACE, SoundSource.BLOCKS, 1F, 1F);
				stack.shrink(1);
			}
		}
		return stack;
}
}


