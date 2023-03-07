package charcoalPit.block;

import java.util.Random;

import charcoalPit.tile.BlockPileTicker;
import charcoalPit.tile.TileActivePile;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import org.jetbrains.annotations.Nullable;

public class BlockActivePile extends Block implements EntityBlock {

	public final boolean isCoal;
	public BlockActivePile(boolean coal,Properties properties) {
		super(properties);
		isCoal=coal;
	}
	
	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		if(!isCoal) {
			builder.add(BlockStateProperties.AXIS);
		}
	}
	
	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
		return new TileActivePile(p_153215_,p_153216_,isCoal);
	}
	
	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level plevel, BlockState pstate, BlockEntityType<T> ptype) {
		/*return plevel.isClientSide()?null:(level,blockpos,blockstate,t)-> {
			if (t instanceof TileActivePile tile) {
				tile.tick();
			}
		};*/
		return (BlockEntityTicker<T>) new BlockPileTicker();
	}
	
	@Override
	public boolean isFireSource(BlockState state, LevelReader world, BlockPos pos, Direction side) {
		return true;
	}
	
	@Override
	public void neighborChanged(BlockState state, Level worldIn, BlockPos pos, Block blockIn, BlockPos fromPos,
			boolean isMoving) {
		((TileActivePile)worldIn.getBlockEntity(pos)).isValid=false;
	}

	//@OnlyIn(Dist.CLIENT)
	//public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, Random rand) {

	//}

}
