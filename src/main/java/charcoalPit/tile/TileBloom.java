package charcoalPit.tile;

import charcoalPit.core.ModTileRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class TileBloom extends BlockEntity {
	
	public ItemStack items=ItemStack.EMPTY;
	public int workCount;
	
	public TileBloom(BlockPos pWorldPosition, BlockState pBlockState) {
		super(ModTileRegistry.Bloom, pWorldPosition, pBlockState);
		workCount=0;
	}
	
	public void dropInventory(){
		Containers.dropItemStack(level, worldPosition.getX(), worldPosition.getY(), worldPosition.getZ(), items);
	}
	
	@Override
	protected void saveAdditional(CompoundTag pTag) {
		super.saveAdditional(pTag);
		pTag.put("items", items.serializeNBT());
		pTag.putInt("work",workCount);
	}

	@Override
	public CompoundTag getUpdateTag() {
		CompoundTag tag = super.getUpdateTag();
		tag.put("items", items.serializeNBT());
		tag.putInt("work", workCount);
		return tag;
	}

	@Override
	public Packet<ClientGamePacketListener> getUpdatePacket() {
        /*CompoundTag tag = new CompoundTag();
        tag.putInt("invalid", invalidTicks);
        tag.putInt("time", burnTime);
        tag.putFloat("xp", xp);
        tag.putBoolean("valid", isValid);
        tag.put("pottery", pottery.serializeNBT());*/
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	public void handleUpdateTag(CompoundTag tag) {
		items = ItemStack.of(tag.getCompound("items"));
		workCount = tag.getInt("work");
	}

	@Override
	public void load(CompoundTag pTag) {
		super.load(pTag);
		items = ItemStack.of(pTag.getCompound("items"));
		System.out.println(pTag.getCompound("items"));
		System.out.println(items.getItem());
		workCount=pTag.getInt("work");
		System.out.println(workCount);
	}
}
