package charcoalPit;

import charcoalPit.core.Config;
import charcoalPit.core.ModBlockRegistry;
import charcoalPit.core.ModFeatures;
import charcoalPit.core.RegistryClass;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;

@Mod(CharcoalPit.MODID)
public class CharcoalPit {

	//TODO: make fruit leaf textures change opaqueness based on fast/fancy settings, just for aesthetic's sake (it's weird having them be the only transparent leaves)

	public static final String MODID="charcoal_pit";

	{
		ForgeMod.enableMilkFluid();
	}
	
	public CharcoalPit() {
		//ModBlockRegistry.BLOCK_REG_PROVIDER.register(FMLJavaModLoadingContext.get().getModEventBus());
		ModLoadingContext.get().registerConfig(Type.COMMON, Config.CONFIG);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		ModFeatures.CONFIGURED_FEATURES.register(FMLJavaModLoadingContext.get().getModEventBus());
		ModFeatures.PLACED_FEATURES.register(FMLJavaModLoadingContext.get().getModEventBus());
		ModFeatures.FOLIAGE_PLACERS.register(FMLJavaModLoadingContext.get().getModEventBus());
		ModFeatures.BIOME_MODIFIERS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}

	protected void setup(FMLCommonSetupEvent event) {
		ModFeatures.load();
		if (ModFeatures.OLIVE == null) {
			System.out.println("PINGAS");
		} else {
			System.out.println("PINGAS_SUC");
			System.out.println(RegistryClass.charcoalPit("olive_tree"));
			System.out.println(ModFeatures.OLIVE.getHolder().get());
		}

		if (ModFeatures.ORANGE == null) {
			System.out.println("PINGAS");
		} else {
			System.out.println("PINGAS_SUC");
			System.out.println(BuiltinRegistries.CONFIGURED_FEATURE.get(RegistryClass.charcoalPit("orange_tree")));
		}
		if(!Config.EnableNaturalFruitTreeGrowth.get()) {

		}
	}
}
