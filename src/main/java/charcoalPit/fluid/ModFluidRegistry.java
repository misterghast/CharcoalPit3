package charcoalPit.fluid;

import charcoalPit.CharcoalPit;
import charcoalPit.core.ModBlockRegistry;
import charcoalPit.core.ModItemRegistry;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.levelgen.Aquifer;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fluids.ForgeFlowingFluid.Properties;

import java.util.function.BiFunction;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

public class ModFluidRegistry {
	
	/*public static final ResourceLocation creosote_still=new ResourceLocation(CharcoalPit.MODID, "block/creosote_still"),
			creosote_flowing=new ResourceLocation(CharcoalPit.MODID, "block/creosote_flow");*/
	public static ForgeFlowingFluid CreosoteFlowing;
    public static ForgeFlowingFluid CreosoteStill;
    public static ForgeFlowingFluid.Properties Creosote = new Properties(() -> new FluidType(FluidType.Properties.create()), () -> CreosoteStill, () -> CreosoteFlowing);


	
	public static final ResourceLocation alcohol_still=new ResourceLocation("minecraft:block/water_still"),
			alcohol_flowing=new ResourceLocation("minecraft:block/water_flow");
	public static ForgeFlowingFluid AlcoholStill;
	public static ForgeFlowingFluid AlcoholFlowing;
    public static ForgeFlowingFluid.Properties Alcohol = new Properties(() -> new FluidType(FluidType.Properties.create()), () -> AlcoholStill, () -> AlcoholFlowing);
	public static final ResourceLocation vinegar_still=new ResourceLocation("minecraft:block/water_still"),
			vinegar_flowing=new ResourceLocation("minecraft:block/water_flow");
	public static ForgeFlowingFluid VinegarStill, VinegarFlowing;
	public static ForgeFlowingFluid.Properties Vinegar = new Properties(() -> new FluidType(FluidType.Properties.create()), () -> VinegarStill, () -> VinegarFlowing);
	
	public static ForgeFlowingFluid CausticLyeStill;
	public static ForgeFlowingFluid CausticLyeFlowing;
	public static ForgeFlowingFluid.Properties CausticLye = new Properties(() -> new FluidType(FluidType.Properties.create()), () -> CausticLyeStill, () -> CausticLyeFlowing);

	public static ForgeFlowingFluid OliveOilStill;
	public static ForgeFlowingFluid OliveOilFlowing;
	public static ForgeFlowingFluid.Properties OliveOil = new Properties(() -> new FluidType(FluidType.Properties.create()), () -> OliveOilStill, () -> OliveOilFlowing);
	
	public static ForgeFlowingFluid WalnutOilStill;
	public static ForgeFlowingFluid WalnutOilFlowing;
	public static Properties WalnutOil = new Properties(() -> new FluidType(FluidType.Properties.create()), () -> WalnutOilStill, () -> WalnutOilFlowing);

	public static ForgeFlowingFluid SunflowerOilStill;
	public static ForgeFlowingFluid SunflowerOilFlowing;
	public static Properties SunflowerOil = new Properties(() -> new FluidType(FluidType.Properties.create()), () -> SunflowerOilStill, () -> SunflowerOilFlowing);
	
	public static ForgeFlowingFluid BiodieselStill;
	public static ForgeFlowingFluid BiodieselFlowing;
	public static Properties Biodiesel = new Properties(() -> new FluidType(FluidType.Properties.create()), () -> BiodieselStill, () -> BiodieselFlowing);
	
	public static ForgeFlowingFluid EthanolStill;
	public static ForgeFlowingFluid EthanolFlowing;
	public static Properties Ethanol = new Properties(() -> new FluidType(FluidType.Properties.create()), () -> EthanolStill, () -> EthanolFlowing);

	public static ForgeFlowingFluid EthoxideStill;
	public static ForgeFlowingFluid EthoxideFlowing;
	public static Properties Ethoxide = new Properties(() -> new FluidType(FluidType.Properties.create()), () -> EthoxideStill, () -> EthoxideFlowing);
	
	public static ForgeFlowingFluid SeedOilStill;
	public static ForgeFlowingFluid SeedOilFlowing;
	public static Properties SeedOil = new Properties(() -> new FluidType(FluidType.Properties.create()), () -> SeedOilStill, () -> SeedOilFlowing);

	@SubscribeEvent
	public static void registerFluids(RegisterEvent event) {
		event.register(ForgeRegistries.Keys.FLUIDS, helper -> {
            helper.register(new ResourceLocation(CharcoalPit.MODID, "creosote_still"), CreosoteStill);
            helper.register(new ResourceLocation(CharcoalPit.MODID, "creosote_flowing"),
                    CreosoteFlowing);
            helper.register(new ResourceLocation(CharcoalPit.MODID, "alcohol_still"),
                    AlcoholStill);
            helper.register(new ResourceLocation(CharcoalPit.MODID, "alcohol_flowing"),
                    AlcoholFlowing);
			helper.register(new ResourceLocation("minecraft:block/water_still"), VinegarStill);
			helper.register(new ResourceLocation("minecraft:block/water_flowing"), VinegarFlowing);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "caustic_lye_still"), CausticLyeStill);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "caustic_lye_flowing"), CausticLyeFlowing);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "olive_oil_still"), OliveOilStill);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "olive_oil_flowing"), OliveOilFlowing);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "walnut_oil_still"), WalnutOilStill);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "walnut_oil_flowing"), WalnutOilFlowing);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "sunflower_oil_still"), SunflowerOilStill);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "sunflower_oil_flowing"), SunflowerOilFlowing);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "biodiesel_still"), BiodieselStill);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "biodiesel_flowing"), BiodieselFlowing);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "ethanol_still"), EthanolStill);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "ethanol_flowing"), EthanolFlowing);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "ethoxide_still"), EthoxideStill);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "ethoxide_flowing"), EthoxideFlowing);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "seed_oil_still"), SeedOilStill);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "seed_oil_flowing"), SeedOilFlowing);
        });
    }
			/*helper.register(new ResourceLocation(CharcoalPit.MODID, "alcohol_still"),
					AlcoholStill);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "alcohol_flowing"),
					AlcoholFlowing);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "vinegar_still"),
					VinegarStill);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "vinegar_flowing"),
					VinegarFlowing);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "caustic_lye"),
					CausticLyeStill);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "olive_oil"),
					OliveOilStill);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "walnut_oil"),
					WalnutOilStill);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "sunflower_oil"),
					SunflowerOilStill);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "bio_diesel"),
					BioDieselStill);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "ethanol"),
					EthanolStill);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "ethoxide"),
					EthoxideStill);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "seed_oil"),
					SeedOilStill);

		});


	}*/

	/*public static class AlcoholProperties extends FluidAtt{
		AlcoholProperties(Builder builder, Fluid fluid){super(builder, fluid);}

		@Override
		public int getColor(FluidStack stack) {
			if(stack.hasTag()&&stack.getTag().contains("CustomPotionColor")){
				return stack.getTag().getInt("CustomPotionColor")+0xFF000000;
			}
			return 0xFFFFFFFF;
		}
		public static Builder builder(ResourceLocation stillTexture, ResourceLocation flowingTexture) {
			return new Builder2(stillTexture, flowingTexture, AlcoholProperties::new);
		}
		public  static class Builder2 extends Builder{
			public Builder2(ResourceLocation stillTexture, ResourceLocation flowingTexture, BiFunction<Builder,Fluid,FluidAttributes> factory) {
				super(stillTexture,flowingTexture,factory);
			}
		}
	}*/
	
}
