package charcoalPit.core;

import charcoalPit.CharcoalPit;
import charcoalPit.recipe.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

@EventBusSubscriber(modid=CharcoalPit.MODID, bus=Bus.MOD)
public class ModRecipeRegistry {
	
	@SubscribeEvent
	public static void registerRecipeType(RegisterEvent event) {
		event.register(ForgeRegistries.Keys.RECIPE_SERIALIZERS, helper -> {
			helper.register(new ResourceLocation(CharcoalPit.MODID, "orekiln"), OreKilnRecipe.SERIALIZER);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "barrel"),  BarrelRecipe.SERIALIZER);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "squish"),
					SquisherRecipe.SERIALIZER);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "alloy"), AlloySmeltRecipe.SERIALIZER);
			//helper.register(new ResourceLocation(CharcoalPit.MODID, "campfire_alloy"), CampfireSmeltRecipe.SERIALIZER);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "distill"), DistilleryRecipe.SERIALIZER);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "pottery"), PotteryKilnRecipe.SERIALIZER);
		});

		event.register(ForgeRegistries.Keys.RECIPE_TYPES, helper -> {
			helper.register(new ResourceLocation(CharcoalPit.MODID, "orekiln"), OreKilnRecipe.ORE_KILN_RECIPE);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "barrel"),  BarrelRecipe.BARREL_RECIPE);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "squish"),
					SquisherRecipe.SQUISH_RECIPE);
			//helper.register(new ResourceLocation(CharcoalPit.MODID, "alloy"), AlloySmeltRecipe.);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "distill"), DistilleryRecipe.DISTILLERY_RECIPE);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "pottery"), PotteryKilnRecipe.POTTERY_RECIPE);
		});

		/*event.getRegistry().registerAll(OreKilnRecipe.SERIALIZER.setRegistryName("orekiln"), BarrelRecipe.SERIALIZER.setRegistryName("barrel"),
				SquisherRecipe.SERIALIZER.setRegistryName("squish"),AlloySmeltRecipe.SERIALIZER.setRegistryName("alloy"),DistilleryRecipe.SERIALIZER.setRegistryName("distill"));*/
	}
	
}
