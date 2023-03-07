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
			helper.register(new ResourceLocation(CharcoalPit.MODID, "distill"), DistilleryRecipe.SERIALIZER);
		});

		/*event.getRegistry().registerAll(OreKilnRecipe.SERIALIZER.setRegistryName("orekiln"), BarrelRecipe.SERIALIZER.setRegistryName("barrel"),
				SquisherRecipe.SERIALIZER.setRegistryName("squish"),AlloySmeltRecipe.SERIALIZER.setRegistryName("alloy"),DistilleryRecipe.SERIALIZER.setRegistryName("distill"));*/
	}
	
}
