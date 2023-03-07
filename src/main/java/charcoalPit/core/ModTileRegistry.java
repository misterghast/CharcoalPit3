package charcoalPit.core;

import charcoalPit.CharcoalPit;
import charcoalPit.tile.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

//@EventBusSubscriber(modid=CharcoalPit.MODID, bus=Bus.MOD)
public class ModTileRegistry {
	
	
	public static BlockEntityType<TileActivePile> ActivePile=BlockEntityType.Builder.of(TileActivePile::new, ModBlockRegistry.ActiveLogPile,ModBlockRegistry.ActiveCoalPile).build(null);
	public static BlockEntityType<TileCreosoteCollector> CreosoteCollector=BlockEntityType.Builder.of(TileCreosoteCollector::new, ModBlockRegistry.BrickCollector,ModBlockRegistry.SandyCollector,
			ModBlockRegistry.NetherCollector,ModBlockRegistry.EndCollector).build(null);
	public static BlockEntityType<TileCeramicPot> CeramicPot=BlockEntityType.Builder.of(TileCeramicPot::new, ModBlockRegistry.CeramicPot,ModBlockRegistry.BlackPot,
			ModBlockRegistry.BluePot,ModBlockRegistry.BrownPot,ModBlockRegistry.CyanPot,ModBlockRegistry.GrayPot,ModBlockRegistry.GreenPot,
			ModBlockRegistry.LightBluePot,ModBlockRegistry.LightGrayPot,ModBlockRegistry.LimePot,ModBlockRegistry.MagentaPot,ModBlockRegistry.OrangePot,
			ModBlockRegistry.PinkPot,ModBlockRegistry.PurplePot,ModBlockRegistry.RedPot,ModBlockRegistry.WhitePot,ModBlockRegistry.YellowPot).build(null);
	public static BlockEntityType<TileBarrel> Barrel=BlockEntityType.Builder.of(TileBarrel::new, ModBlockRegistry.Barrel).build(null);
	public static BlockEntityType<TileFeedingThrough> FeedingThrough=BlockEntityType.Builder.of(TileFeedingThrough::new,
			ModBlockRegistry.FeedingThrough,ModBlockRegistry.FeedingThroughBirch,ModBlockRegistry.FeedingThroughJungle,ModBlockRegistry.FeedingThroughSpruce,
			ModBlockRegistry.FeedingThroughDark,ModBlockRegistry.FeedingThroughAcacia,ModBlockRegistry.FeedingThroughCrimson,ModBlockRegistry.FeedingThroughWarped).build(null);
	public static BlockEntityType<TileNestBox> NestBox=BlockEntityType.Builder.of(TileNestBox::new, ModBlockRegistry.NestBox).build(null);
	public static BlockEntityType<TileBloomeryy> Bloomeryy=BlockEntityType.Builder.of(TileBloomeryy::new,ModBlockRegistry.Bloomeryy).build(null);
	public static BlockEntityType<TileBloom> Bloom=BlockEntityType.Builder.of(TileBloom::new,ModBlockRegistry.Bloom).build(null);
	public static BlockEntityType<TileBlastFurnace> BlastFurnace=BlockEntityType.Builder.of(TileBlastFurnace::new,ModBlockRegistry.BlastFurnace).build(null);
	public static BlockEntityType<TileDistillery> Distillery=BlockEntityType.Builder.of(TileDistillery::new,ModBlockRegistry.Distillery).build(null);
	public static BlockEntityType<TileSteamPress> SteamPress=BlockEntityType.Builder.of(TileSteamPress::new,ModBlockRegistry.SteamPress).build(null);
	public static BlockEntityType<TileWrathLantern> WrathLantern=BlockEntityType.Builder.of(TileWrathLantern::new,ModBlockRegistry.WrathLantern).build(null);
	
	
	//@SubscribeEvent
	public static void registerTileEntity(RegisterEvent event) {
		event.register(ForgeRegistries.Keys.BLOCK_ENTITY_TYPES, helper -> {
			helper.register(new ResourceLocation(CharcoalPit.MODID, "active_pile"), ActivePile);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "creosote_collector"), CreosoteCollector);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "ceramic_pot"),
					CeramicPot);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "barrel"),  Barrel);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "feeding_through"), FeedingThrough);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "nest_box"),
					NestBox);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "bloomeryy"), Bloomeryy);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "bloom"), Bloom);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "blast_furnace"),
					BlastFurnace);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "distillery"), Distillery);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "steam_press"), SteamPress);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "wrath_lantern"),
					WrathLantern);

		});
		/*event.getRegistry().registerAll(ActivePile.setRegistryName("active_pile"),CreosoteCollector.setRegistryName("creosote_collector"),
				CeramicPot.setRegistryName("ceramic_pot"), Barrel.setRegistryName("barrel"),FeedingThrough.setRegistryName("feeding_through"),
				NestBox.setRegistryName("nest_box"),Bloomeryy.setRegistryName("bloomeryy"),Bloom.setRegistryName("bloom"),
				BlastFurnace.setRegistryName("blast_furnace"),Distillery.setRegistryName("distillery"),SteamPress.setRegistryName("steam_press"),
				WrathLantern.setRegistryName("wrath_lantern"));*/
	}
	
}
