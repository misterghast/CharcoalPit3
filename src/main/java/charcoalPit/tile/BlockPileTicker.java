package charcoalPit.tile;

import charcoalPit.block.BlockActivePile;
import charcoalPit.core.Config;
import charcoalPit.core.ModBlockRegistry;
import charcoalPit.fluid.ModFluidRegistry;
import charcoalPit.tile.TileActivePile;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import java.util.Random;

public class BlockPileTicker implements BlockEntityTicker<TileActivePile> {

    public static Random rand;

    @Override
    public void tick(Level worldIn, BlockPos pos, BlockState state, TileActivePile pBlockEntity) {
        int burnTime = pBlockEntity.burnTime;
        int itemsLeft = pBlockEntity.itemsLeft;
        boolean isCoke = pBlockEntity.isCoke;
        FluidTank creosote = pBlockEntity.creosote;
        if(!worldIn.isClientSide){
            pBlockEntity.checkValid();
            if(burnTime>0){
                burnTime--;
                if(burnTime%1000==0)
                    pBlockEntity.setChanged();
            }else{
                if(itemsLeft>0){
                    itemsLeft--;
                    creosote.fill(new FluidStack(ModFluidRegistry.CreosoteStill, isCoke? Config.CokeCreosote.get():Config.CharcoalCreosote.get()), IFluidHandler.FluidAction.EXECUTE);
                    burnTime=isCoke?Config.CokeTime.get()/10:Config.CharcoalTime.get()/10;
                }else{
                    worldIn.setBlockAndUpdate(pos, isCoke? ModBlockRegistry.CoalAsh.defaultBlockState():ModBlockRegistry.WoodAsh.defaultBlockState());
                }
            }
            if(creosote.getFluidAmount()>0){
                if(worldIn.getBlockEntity(pos.relative(Direction.DOWN))instanceof TileActivePile){
                    TileActivePile down=(TileActivePile)worldIn.getBlockEntity(pos.relative(Direction.DOWN));
                    creosote.drain(down.creosote.fill(creosote.getFluid(), IFluidHandler.FluidAction.EXECUTE), IFluidHandler.FluidAction.EXECUTE);
                }
            }
        } else if (pBlockEntity.isCoke && pBlockEntity.isValid){
            double centerX = pos.getX() + 0.5F;
            double centerY = pos.getY() + 2F;
            double centerZ = pos.getZ() + 0.5F;
            //double d3 = 0.2199999988079071D;
            //double d4 = 0.27000001072883606D;
            worldIn.addParticle(ParticleTypes.SMOKE, centerX+(rand.nextDouble()-0.5), centerY, centerZ+(rand.nextDouble()-0.5), 0.0D, 0.1D, 0.0D);
            worldIn.addParticle(ParticleTypes.SMOKE, centerX+(rand.nextDouble()-0.5), centerY, centerZ+(rand.nextDouble()-0.5), 0.0D, 0.15D, 0.0D);
            worldIn.addParticle(ParticleTypes.SMOKE, centerX+(rand.nextDouble()-0.5), centerY-1, centerZ+(rand.nextDouble()-0.5), 0.0D, 0.1D, 0.0D);
            worldIn.addParticle(ParticleTypes.SMOKE, centerX+(rand.nextDouble()-0.5), centerY-1, centerZ+(rand.nextDouble()-0.5), 0.0D, 0.15D, 0.0D);
        }
    }
}
