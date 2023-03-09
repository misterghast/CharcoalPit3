package charcoalPit.tile;

import charcoalPit.block.BlockPotteryKiln;
import charcoalPit.block.BlockPotteryKiln.EnumKilnTypes;
import charcoalPit.core.ModBlockRegistry;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public class TESRPotteryKiln implements BlockEntityRenderer<TilePotteryKiln> {

    public TESRPotteryKiln(BlockEntityRendererProvider.Context context) {

    }

    @Override
    public void render(TilePotteryKiln tile, float partialTicks, PoseStack matrixStack,
                       MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        BlockState state=tile.getLevel().getBlockState(tile.getBlockPos());
        if(state.getBlock()==ModBlockRegistry.Kiln&&(state.getValue(BlockPotteryKiln.TYPE)==EnumKilnTypes.EMPTY||state.getValue(BlockPotteryKiln.TYPE)==EnumKilnTypes.COMPLETE)) {
            matrixStack.pushPose();

            matrixStack.translate(0.5, 0.1, 0.5);
            ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
            ItemStack stack=tile.pottery.getStackInSlot(0);
            BakedModel ibakedmodel = itemRenderer.getModel(stack, tile.getLevel(), null,2);
            itemRenderer.render(stack, ItemTransforms.TransformType.GROUND, true, matrixStack, buffer, combinedLight, combinedOverlay, ibakedmodel);

            matrixStack.popPose();
        }
    }

}