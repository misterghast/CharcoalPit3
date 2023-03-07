package charcoalPit.core;

import charcoalPit.CharcoalPit;
import charcoalPit.entity.Airplane;
import charcoalPit.entity.ModEntities;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegisterEvent;

@Mod.EventBusSubscriber(modid= CharcoalPit.MODID, bus= Mod.EventBusSubscriber.Bus.MOD)
public class RegistryClass {
	
	@SubscribeEvent
	public static void registerBlocks(RegisterEvent event) {
		ModBlockRegistry.registerBlocks(event);
	}
	
	@SubscribeEvent
	public static void registerItems(RegisterEvent event) {
		ModItemRegistry.registerItems(event);
	}
	
	@SubscribeEvent
	public static void registerTileEntity(RegisterEvent event) {
		ModTileRegistry.registerTileEntity(event);
	}
	
	@SubscribeEvent
	public static void dataGen(GatherDataEvent event){
		ModBlockRegistry.datagen(event);
	}
	
	@SubscribeEvent
	public static void registerEntities(RegisterEvent event){
		ModEntities.registerEntities(event);
	}
	
	@SubscribeEvent
	public static void registerEntityAttributes(EntityAttributeCreationEvent event){
		event.put(ModEntities.AIRPLANE, Airplane.createAttributes().build());
	}
	
}
