package charcoalPit.block;

import charcoalPit.CharcoalPit;
import charcoalPit.core.Config;
import charcoalPit.core.ModTileRegistry;
import charcoalPit.recipe.PotteryKilnRecipe;
import charcoalPit.tile.TilePotteryKiln;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class BlockPotteryKiln extends Block implements EntityBlock {

    public static final EnumProperty<EnumKilnTypes> TYPE=EnumProperty.create("kiln_type", EnumKilnTypes.class);
    public static final VoxelShape EMPTY= Block.box(0.0D, 0.0D, 0.0D, 16.0D, 1.0D, 16.0D);
    public static final VoxelShape EMPTY_BOX = Block.box(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
    public static final VoxelShape THATCH= Block.box(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D);
    public static final VoxelShape COMPLETE= Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D);
    public BlockPotteryKiln() {
        super(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.TERRACOTTA_RED));
        this.registerDefaultState(this.getStateDefinition().any().setValue(TYPE, EnumKilnTypes.EMPTY));
    }


    @Override
    public VoxelShape getOcclusionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        switch(pState.getValue(TYPE)) {
            case COMPLETE:return COMPLETE;
            case EMPTY:return EMPTY_BOX;
            case THATCH:return THATCH;
            default:return Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
        }
    }

    @Override
    public boolean skipRendering(BlockState pState, BlockState pAdjacentBlockState, Direction pDirection) {
        return super.skipRendering(pState, pAdjacentBlockState, pDirection);
    }

    /*@Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return super.getStateForPlacement(context).setValue(TYPE, EnumKilnTypes.EMPTY);
    }*/

    @Override
    public SoundType getSoundType(BlockState state) {
        switch(state.getValue(TYPE)) {
            case ACTIVE:return SoundType.WOOD;
            case COMPLETE:return SoundType.SAND;
            case EMPTY:return SoundType.ROOTED_DIRT;
            case THATCH:return SoundType.GRASS;
            case WOOD:return SoundType.WOOD;
            default:return SoundType.ROOTED_DIRT;
        }
    }

    public float getHardness(BlockState state) {
        switch(state.getValue(TYPE)) {
            case ACTIVE:return 2F;
            case COMPLETE:return 0.5F;
            case EMPTY:return 0F;
            case THATCH:return 0.5F;
            case WOOD:return 2F;
            default:return 0F;
        }
    }

    /*@Override
    public float getPlayerRelativeBlockHardness(BlockState state, Player player, Level worldIn,
                                                BlockPos pos) {
        float f = getHardness(state);
        if (f == -1.0F) {
            return 0.0F;
        } else {
            int i = net.minecraftforge.common.ForgeHooks.canHarvestBlock(state, player, worldIn, pos) ? 30 : 100;
            return player.getDigSpeed(state, pos) / f / (float)i;
        }
    }*/

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        switch(pState.getValue(TYPE)) {
            case COMPLETE:return COMPLETE;
            case EMPTY:return EMPTY;
            case THATCH:return THATCH;
            default:return Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
        }
    }

    /*@Override
    public boolean isTransparent(BlockState state) {
        return !(state.getValue(TYPE)==EnumKilnTypes.ACTIVE||state.getValue(TYPE)==EnumKilnTypes.WOOD);

    }*/



    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(TYPE);
    }


    @Override
    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
        return worldIn.getBlockState(pos.below()).isFaceSturdy(worldIn, pos.below(), Direction.UP);
    }


    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new TilePotteryKiln(pPos, pState);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return type == ModTileRegistry.PotteryKiln ? TilePotteryKiln::tick : null;
    }


    @Override
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn,
                                          BlockPos currentPos, BlockPos facingPos) {
        if(facing==Direction.DOWN&&!canSurvive(stateIn, worldIn, currentPos))return Blocks.AIR.defaultBlockState();
        return stateIn;
    }


    @Override
    public void neighborChanged(BlockState state, Level worldIn, BlockPos pos, Block blockIn, BlockPos fromPos,
                                boolean isMoving) {
        if(state.getValue(TYPE)==EnumKilnTypes.ACTIVE) {
            if(pos.above().equals(fromPos)) {
                if(!(worldIn.getBlockState(fromPos).getBlock()==Blocks.FIRE)) {
                    ((TilePotteryKiln)worldIn.getBlockEntity(pos)).isValid=false;
                }
            }else {
                ((TilePotteryKiln)worldIn.getBlockEntity(pos)).isValid=false;
            }
        }
    }

    @Override
    public boolean isFireSource(BlockState state, LevelReader world, BlockPos pos, Direction side) {
        return state.getValue(TYPE)==EnumKilnTypes.ACTIVE;
    }


    @SuppressWarnings("deprecation")
    @Override
    public RenderShape getRenderShape(BlockState state) {
        return state.getValue(TYPE)==EnumKilnTypes.EMPTY?RenderShape.INVISIBLE:super.getRenderShape(state);
    }


   //How
    /*@Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.hasBlockEntity() && (state.getBlock() != newState.getBlock() || !newState.hasBlockEntity())) {
            ((TilePotteryKiln)worldIn.getBlockEntity(pos)).dropInventory();
            worldIn.removeTileEntity(pos);
        }
    }*/


    /*@Override
    public boolean hasBlockEntity(BlockState state) {
        return true;
    }*/


    /*@Override
    public BlockEntity createBlockEntity(BlockState state, Level world) {
        return new TilePotteryKiln();
    }*/


    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if(!pState.is(pNewState.getBlock())) {
            TilePotteryKiln tile = ((TilePotteryKiln) pLevel.getBlockEntity(pPos));
            if (tile instanceof TilePotteryKiln) {
                tile.dropInventory();
            }
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
    }

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player,
                                 InteractionHand handIn, BlockHitResult hit) {
        switch(state.getValue(TYPE)){
            case EMPTY: {
                        if(player.getItemInHand(handIn).is(ItemTags.create(new ResourceLocation(CharcoalPit.MODID, "kiln_straw")))&&player.getItemInHand(handIn).getCount()>=Config.StrawAmount.get()){
                            if(!worldIn.isClientSide()) {
                                player.getItemInHand(handIn).setCount(player.getItemInHand(handIn).getCount()-Config.StrawAmount.get());
                                //BlockState def = this.defaultBlockState();
                                //def.setValue(TYPE, TYPE.value(EnumKilnTypes.THATCH));
                                worldIn.setBlockAndUpdate(pos, state.setValue(TYPE, EnumKilnTypes.THATCH));
                                worldIn.playSound(null, pos, SoundEvents.GRASS_PLACE, SoundSource.BLOCKS, 1F, 1F);
                                return InteractionResult.SUCCESS;
                            }
                        }else {
                            if (player.getItemInHand(handIn).isEmpty() && handIn.equals(InteractionHand.MAIN_HAND)) {
                                if (!worldIn.isClientSide()) {
                                    TilePotteryKiln tile = ((TilePotteryKiln) worldIn.getBlockEntity(pos));
                                    player.setItemInHand(handIn, tile.pottery.extractItem(0, 8, false));
                                    worldIn.destroyBlock(pos, true);
                                    return InteractionResult.SUCCESS;
                                }
                            } else {
                                return InteractionResult.FAIL;
                            }
                        }
            }
            case THATCH:{
                if(player.getItemInHand(handIn).is(ItemTags.LOGS_THAT_BURN)&&player.getItemInHand(handIn).getCount()>=Config.WoodAmount.get()) {
                    if(!worldIn.isClientSide()){
                        BlockState def = this.defaultBlockState();
                        player.getItemInHand(handIn).setCount(player.getItemInHand(handIn).getCount()-Config.WoodAmount.get());
                        worldIn.setBlockAndUpdate(pos, state.setValue(TYPE, EnumKilnTypes.WOOD));
                        worldIn.playSound(null, pos, SoundEvents.WOOD_PLACE, SoundSource.BLOCKS, 1F, 1F);
                        return InteractionResult.SUCCESS;
                    } else {
                        return InteractionResult.SUCCESS;
                    }
                }else
                    return InteractionResult.FAIL;
            }
            default:{
                return InteractionResult.FAIL;
            }
        }
    }

    public enum EnumKilnTypes implements StringRepresentable {
        //stage 1
        EMPTY("empty"),
        //stage 2
        THATCH("thatch"),
        //stage 3
        WOOD("wood"),
        //stage 4
        ACTIVE("active"),
        //complete
        COMPLETE("complete");

        private String name;
        private EnumKilnTypes(String id) {
            name=id;
        }
        @Override
        public String getSerializedName() {
            return name;
        }
    }


}