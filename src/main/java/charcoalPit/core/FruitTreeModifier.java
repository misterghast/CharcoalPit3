package charcoalPit.core;

import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;

public record FruitTreeModifier(HolderSet<Biome> biomes, HolderSet<Biome> apple_biomes, HolderSet<Biome> cherry_biomes/*, HolderSet<Biome> dragon_biomes, HolderSet<Biome> chestnut_biomes, HolderSet<Biome> olive_biomes, HolderSet<Biome> orange_biomes*/) implements BiomeModifier {
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if(phase.equals(Phase.ADD) && biomes.contains(biome)) {
            if(apple_biomes.contains(biome)) {
                //builder.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModFeatures.APPLE_TREE_PLACER);
            }
            /*if(cherry_biomes.contains(biome)) {
                builder.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModFeatures.CHERRY_TREE_PLACER);
            }*/
        }
    }

    public Codec<? extends BiomeModifier> codec() {
        return null;
    }
}
