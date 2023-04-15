package charcoalPit.tree;

import charcoalPit.core.Config;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.common.world.ModifiableBiomeInfo;

public record FruitTreeModifier(HolderSet<Biome> biomes, HolderSet<PlacedFeature> features, GenerationStep.Decoration step) implements BiomeModifier
{

    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder)
    {
        if (phase == Phase.ADD && this.biomes.contains(biome) && Config.EnableNaturalFruitTreeGrowth.get())
        {
            BiomeGenerationSettingsBuilder generationSettings = builder.getGenerationSettings();
            this.features.forEach(holder -> generationSettings.addFeature(this.step, holder));
        }
    }

    @Override
    public Codec<? extends BiomeModifier> codec()
    {
        return ForgeMod.ADD_FEATURES_BIOME_MODIFIER_TYPE.get();
    }

    public static final Codec<FruitTreeModifier> RECORD_CODEC = RecordCodecBuilder.create(instance -> // Given an instance
            instance.group( // Define the fields within the instance
                    Biome.LIST_CODEC.fieldOf("biomes").forGetter(FruitTreeModifier::biomes),
                    PlacedFeature.LIST_CODEC.fieldOf("features").forGetter(FruitTreeModifier::features),
                    GenerationStep.Decoration.CODEC.fieldOf("step").forGetter(FruitTreeModifier::step)
            ).apply(instance, FruitTreeModifier::new) // Define how to create the object
    );

}
