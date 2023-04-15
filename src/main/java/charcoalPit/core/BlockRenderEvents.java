package charcoalPit.core;

import charcoalPit.CharcoalPit;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.math.Vector3d;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.BlockModelShaper;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid= CharcoalPit.MODID, bus= Mod.EventBusSubscriber.Bus.MOD)
public class BlockRenderEvents
{
    /* For fuck's sake Forge get your rendering documentation together
    @SubscribeEvent
    public static void renderBlock(RenderLevelStageEvent event ) {
        if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_CUTOUT_BLOCKS) {

        }
    }

    public static void  renderBlockOverlay(PoseStack pose, Vector3d blockPos, TextureAtlasSprite toRender,) {
        BlockRenderDispatcher dispatcher = Minecraft.getInstance().getBlockRenderer();
        Minecraft.getInstance().getTextureManager().
    }*/
}
