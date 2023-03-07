package charcoalPit.core;

import charcoalPit.CharcoalPit;
import charcoalPit.loot.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

@EventBusSubscriber(modid=CharcoalPit.MODID, bus=Bus.MOD)
public class ModLootModifiers {
	
	public static void registerLootModifiers(RegisterEvent event) {
		event.register(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, helper -> {
			helper.register(new ResourceLocation(CharcoalPit.MODID, "straw_from_grass"), StrawFromGrass.DIRECT_CODEC);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "kernels_from_grass"), KernalsFromGrass.DIRECT_CODEC);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "cherry_from_birch"), CherryFromBirch.DIRECT_CODEC);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "dragon_from_acacia"), DragonFromAcacia.DIRECT_CODEC);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "chestnut_from_dark_oak"), ChestnutFromDarkOak.DIRECT_CODEC);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "fruit_from_jungle"), FruitFromJungle.DIRECT_CODEC);

		});
	}

}
