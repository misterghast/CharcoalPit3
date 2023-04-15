package charcoalPit.block;

import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;

public class FruitBlockTexture extends TextureAtlasSprite {
    protected FruitBlockTexture(TextureAtlas pAtlas, Info pSpriteInfo, int pMipLevel, int pStorageX, int pStorageY, int pX, int pY, NativeImage pImage) {
        super(pAtlas, pSpriteInfo, pMipLevel, pStorageX, pStorageY, pX, pY, pImage);
    }
}
