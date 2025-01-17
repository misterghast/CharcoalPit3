package charcoalPit.entity;

import charcoalPit.CharcoalPit;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

public class ModEntities {
	
	public static ResourceLocation airplane=new ResourceLocation(CharcoalPit.MODID,"airplane");
	//1.375F, 0.5625F
	//38F/16F, 42F/16F
	
	public static EntityType<Airplane> AIRPLANE=EntityType.Builder.<Airplane>of(Airplane::new, MobCategory.MISC).sized(38F/16F, 42F/16F).clientTrackingRange(10)
			.build(airplane.toString());
	
	public static void registerEntities(RegisterEvent event){
		event.register(ForgeRegistries.Keys.ENTITY_TYPES, helper -> {
			helper.register(new ResourceLocation(CharcoalPit.MODID, "airplane"), AIRPLANE);
		});
		//event.getRegistry().registerAll(AIRPLANE.setRegistryName("airplane"));
	}
	
}
