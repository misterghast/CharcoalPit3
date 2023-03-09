package charcoalPit.tile;

import charcoalPit.block.BlockPotteryKiln;
import charcoalPit.block.BlockPotteryKiln.EnumKilnTypes;
import charcoalPit.core.Config;
import charcoalPit.core.ModBlockRegistry;
import charcoalPit.core.ModTileRegistry;
import charcoalPit.recipe.PotteryKilnRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class TilePotteryKiln extends BlockEntity {

    public int invalidTicks;
    public int burnTime;
    public float xp;
    public boolean isValid;
    public PotteryStackHandler pottery;

    public TilePotteryKiln(BlockPos pos, BlockState state) {
        super(ModTileRegistry.PotteryKiln, pos, state);
        invalidTicks=0;
        burnTime=-1;
        xp=0;
        isValid=false;
        pottery=new PotteryStackHandler();
    }

    //ticker
    public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState state, T blockEntityGeneric) {
        TilePotteryKiln blockEntity = (TilePotteryKiln) blockEntityGeneric;
        if(!level.isClientSide&&blockEntity.burnTime>-1){
            blockEntity.checkValid();
            if(blockEntity.burnTime>0) {
                blockEntity.burnTime--;
                if(blockEntity.burnTime%500==0);
                    blockEntity.setChanged();
            }else{
                if(blockEntity.burnTime==0){
                    PotteryKilnRecipe result=PotteryKilnRecipe.getResult(blockEntity.pottery.getStackInSlot(0), level);
                    if(result!=null) {
                        ItemStack out= PotteryKilnRecipe.processClayPot(blockEntity.pottery.getStackInSlot(0), level);
                        if(out.isEmpty())
                            out=new ItemStack(result.output,blockEntity.pottery.getStackInSlot(0).getCount());
                        blockEntity.xp=result.xp*blockEntity.pottery.getStackInSlot(0).getCount();
                        blockEntity.pottery.setStackInSlot(0, out);
                    }
                    level.setBlockAndUpdate(pos, ModBlockRegistry.Kiln.defaultBlockState().setValue(BlockPotteryKiln.TYPE, EnumKilnTypes.COMPLETE));
                    level.removeBlock(pos.above(), false);
                    blockEntity.burnTime--;
                }
            }
        }
    }
    public void setActive(boolean active){
        if(active){
            burnTime=Config.PotteryTime.get();
        }else{
            burnTime=-1;
        }
    }


    public void dropInventory(){
        //level.addFreshEntity
        //new ItemEntity
        ItemEntity item_stack_entity = new ItemEntity(level, worldPosition.getX(), worldPosition.getY(), worldPosition.getZ(), pottery.getStackInSlot(0));
        level.addFreshEntity(item_stack_entity);

        int x=(int)xp+Math.random()<(xp-(int)xp)?1:0;
        while(x>0){
            int i=ExperienceOrb.getExperienceValue(x);
            x-=i;
            level.addFreshEntity(new ExperienceOrb(level, (double)worldPosition.getX() + 0.5D, (double)worldPosition.getY() + 0.5D, (double)worldPosition.getZ() + 0.5D, i));
        }
    }
    public void checkValid(){
        boolean valid=true;
        if(Config.RainyPottery.get()&&this.level.isRainingAt(this.worldPosition.above())){
            valid=false;
        }else{
            if(isValid)
                return;
        }
        //check structure
        for(Direction facing:Direction.Plane.HORIZONTAL){
            BlockState block=this.level.getBlockState(this.worldPosition.relative(facing));
            if(!block.isFaceSturdy(this.level, worldPosition.relative(facing), facing.getOpposite())){
                valid=false;
                break;
            }
        }
        BlockState block=this.level.getBlockState(this.worldPosition.above());
        if(block.getBlock()!= Blocks.FIRE){
            if(level.getBlockState(this.worldPosition.above()).isAir()||
                    BaseFireBlock.canBePlacedAt(this.level, this.worldPosition.above(), Direction.UP)){
                BlockState blockstate1 = BaseFireBlock.getState(this.level, this.worldPosition.above());
                this.level.setBlock(this.worldPosition.above(), blockstate1, 11);
            }else{
                valid=false;
            }
        }
        if(valid){
            isValid=true;
            invalidTicks=0;
        }else{
            if(invalidTicks<100){
                invalidTicks++;
            }else{
                setActive(false);
                this.level.setBlock(worldPosition, ModBlockRegistry.Kiln.defaultBlockState().setValue(BlockPotteryKiln.TYPE, EnumKilnTypes.WOOD), 2);
                this.level.setBlock(worldPosition.above(), Blocks.AIR.defaultBlockState(), 2);
                invalidTicks=0;
            }
        }
    }

    @Override
    public void saveAdditional(CompoundTag compound) {
        compound.putInt("invalid", invalidTicks);
        compound.putInt("time", burnTime);
        compound.putFloat("xp", xp);
        compound.putBoolean("valid", isValid);
        compound.put("pottery", pottery.serializeNBT());
        super.saveAdditional(compound);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        invalidTicks=nbt.getInt("invalid");
        burnTime=nbt.getInt("time");
        xp=nbt.getFloat("xp");
        isValid=nbt.getBoolean("valid");
        pottery.deserializeNBT(nbt.getCompound("pottery"));
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag nbt=super.getUpdateTag();
        nbt.put("pottery", pottery.serializeNBT());
        return nbt;
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

    /*@Override
    public void handleUpdateTag(BlockState state, CompoundTag tag) {
        super.handleUpdateTag(tag);
        pottery.deserializeNBT(tag.getCompound("pottery"));
    }*/

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        pottery.deserializeNBT(pkt.getTag().getCompound("pottery"));
    }


    public class PotteryStackHandler extends ItemStackHandler{
        @Override
        public int getSlotLimit(int slot) {
            return 8;
        }
    }

}