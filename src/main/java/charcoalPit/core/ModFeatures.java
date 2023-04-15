package charcoalPit.core;

import charcoalPit.CharcoalPit;
import charcoalPit.tree.*;
import charcoalPit.tree.FruitTreeModifier;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.grower.AbstractMegaTreeGrower;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import charcoalPit.core.ModBlockRegistry;

import javax.annotation.Nullable;
import java.util.List;
import java.util.OptionalInt;
import java.util.function.Supplier;

import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.ThreeLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.DarkOakFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.registries.*;

//@Mod.EventBusSubscriber(modid = CharcoalPit.MODID)
public class ModFeatures {
	
	public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, CharcoalPit.MODID);
	public static final DeferredRegister<PlacedFeature> PLACED_FEATURES = DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, CharcoalPit.MODID);

	public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS = DeferredRegister.create(ForgeRegistries.FOLIAGE_PLACER_TYPES, CharcoalPit.MODID);

	public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, CharcoalPit.MODID);

	public static final RegistryObject<Codec<? extends BiomeModifier>> CONFIGURABLE_MODIFIER = BIOME_MODIFIERS.register("configurable_modifier", () -> FruitTreeModifier.RECORD_CODEC);

	//public static RegistryObject<ConfiguredFeature<?, ?>> APPLE = CONFIGURED_FEATURES.register("apple_tree", () -> fruitTreeFeature(Blocks.OAK_LOG, ModBlockRegistry.AppleLeaves,
		//	blobFoliage(2, 0, 3), 4, 2, 0, 1, 0, 1));

	public static final RegistryObject<FoliagePlacerType<?>> DRAGON_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("dragon_foliage_placer", () -> new FoliagePlacerType<>(DragonFoliagePlacer.CODEC));
	public static final RegistryObject<FoliagePlacerType<?>> OLIVE_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("olive_foliage_placer", () -> new FoliagePlacerType<>(OliveFoliagePlacer.CODEC));

	/*@ObjectHolder(registryName = "worldgen/foliage_placer_type", value = "charcoal_pit:olive_foliage_placer")
	public static FoliagePlacerType<?> OLIVE_FOL_HOLDER;

	@ObjectHolder(registryName = "worldgen/foliage_placer_type", value = "charcoal_pit:dragon_foliage_placer")
	public static FoliagePlacerType<?> DRAGON_FOL_HOLDER;

	@ObjectHolder(registryName = "worldgen/foliage_placer_type", value = "charcoal_pit:orange_foliage_placer")
	public static FoliagePlacerType<?> ORANGE_FOL_HOLDER;*/

	public static final RegistryObject<FoliagePlacerType<?>> ORANGE_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("orange_foliage_placer", () -> new FoliagePlacerType<>(OrangeFoliagePlacer.CODEC));
	public static final RegistryObject<FoliagePlacerType<?>> PALM_FOLIAGE_PLACER = FOLIAGE_PLACERS.register("palm_foliage_placer", () -> new FoliagePlacerType<>(PalmFoliagePlacer.CODEC));

	public static final RegistryObject<ConfiguredFeature<?, ?>> APPLE = CONFIGURED_FEATURES.register("apple_tree", () -> fruitTreeFeatureObject(Blocks.OAK_LOG, ModBlockRegistry.AppleLeaves,
			blobFoliage(2, 0, 3), 4, 2, 0, 1, 0, 1));
	public static final RegistryObject<PlacedFeature> APPLE_PLACER = getTreePlacerDeferred("apple_tree", APPLE);

	public static final RegistryObject<ConfiguredFeature<?, ?>> CHERRY = CONFIGURED_FEATURES.register("cherry_tree", () -> fruitTreeFeatureObject(Blocks.BIRCH_LOG, ModBlockRegistry.CherryLeaves, blobFoliage(2, 0, 3), 5, 2, 0, 1, 0, 1));
	public static final RegistryObject<PlacedFeature> CHERRY_PLACER = getTreePlacerDeferred("cherry_tree", CHERRY);

	public static final RegistryObject<ConfiguredFeature<?, ?>> DRAGON = CONFIGURED_FEATURES.register("dragon_tree", () -> fruitTreeFeatureObject(Blocks.ACACIA_LOG, ModBlockRegistry.DragonLeaves,
			new DragonFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)), 3, 0, 0, 1, 0, 1));
	public static final RegistryObject<PlacedFeature> DRAGON_TREE_PLACER = getTreePlacerDeferred("dragon_tree", DRAGON);

	public static final RegistryObject<ConfiguredFeature<?, ?>> CHESTNUT = CONFIGURED_FEATURES.register("chestnut_tree", () -> fruitTreeFeatureThreeLayerObject(Blocks.DARK_OAK_LOG, ModBlockRegistry.ChestnutLeaves,
			new DarkOakFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)), 5, 2, 0, 1, 1, 0, 1, 2));
	public static final RegistryObject<PlacedFeature> CHESTNUT_TREE_PLACER = getTreePlacerDeferred("chestnut_tree", CHESTNUT);

	public static final RegistryObject<ConfiguredFeature<?, ?>> OLIVE = CONFIGURED_FEATURES.register( "olive_tree", () -> fruitTreeFeatureObject(Blocks.ACACIA_LOG, ModBlockRegistry.OliveLeaves,
			new OliveFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)), 4, 2, 0, 1, 0, 1));
	public static final RegistryObject<PlacedFeature> OLIVE_TREE_PLACER = getTreePlacerDeferred("olive_tree", OLIVE);

	public static final RegistryObject<ConfiguredFeature<?, ?>> ORANGE = CONFIGURED_FEATURES.register( "orange_tree", () -> fruitTreeFeatureObject(Blocks.JUNGLE_LOG, ModBlockRegistry.OrangeLeaves,
			new OrangeFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)), 4, 2, 0, 1, 0, 1));
	public static final RegistryObject<PlacedFeature> ORANGE_TREE_PLACER = getTreePlacerDeferred("orange_tree", DRAGON);


	//public static FoliagePlacerType<DragonFoliagePlacer> DRAGON_PLACER=new FoliagePlacerType<>(DragonFoliagePlacer.CODEC);
	//public static FoliagePlacerType<OliveFoliagePlacer> OLIVE_PLACER=new FoliagePlacerType<>(OliveFoliagePlacer.CODEC);
	//public static FoliagePlacerType<OrangeFoliagePlacer> ORANGE_PLACER=new FoliagePlacerType<>(OrangeFoliagePlacer.CODEC);
	//public static FoliagePlacerType<PalmFoliagePlacer> PALM_PLACER=new FoliagePlacerType<>(PalmFoliagePlacer.CODEC);
	
	//public static TrunkPlacerType<BentTrunkPlacer> BENT_PLACER=new TrunkPlacerType<BentTrunkPlacer>(BentTrunkPlacer.CODEC);
	public static TrunkPlacerType<DoubleTrunkPlacer> DOUBLE_PLACER=new TrunkPlacerType<>(DoubleTrunkPlacer.CODEC);

	//this is sooooo fucked but i can't think of a better way to do it yet
	/*public static final Holder<ConfiguredFeature<?, ?>> APPLE =  fruitTreeFeature("apple_tree", Blocks.OAK_LOG, ModBlockRegistry.AppleLeaves,
			blobFoliage(2, 0, 3), 4, 2, 0, 1, 0, 1);
	public static Holder<PlacedFeature> APPLE_TREE_PLACER = getTreePlacer("apple_tree",  APPLE);
	/*@ObjectHolder(registryName = "minecraft:worldgen/placed_feature", value = "charcoal_pit:apple_tree_checker")
	public static Holder<PlacedFeature> APPLE_CHECKER;
	@ObjectHolder(registryName = "minecraft:worldgen/feature", value = "charcoal_pit:apple_tree_randomizer")
	public static Holder<ConfiguredFeature<?,?>> APPLE_RANDOMIZER;
	@ObjectHolder(registryName = "minecraft:worldgen/placed_feature", value = "charcoal_pit:apple_tree_placer")
	public static Holder<PlacedFeature> APPLE_TREE_PLACER;*/
	/*public static final Holder<ConfiguredFeature<?, ?>> CHERRY = fruitTreeFeature("cherry_tree", Blocks.BIRCH_LOG, ModBlockRegistry.CherryLeaves,
			blobFoliage(2, 0, 3), 5, 2, 0, 1, 0, 1);

	public static Holder<PlacedFeature> CHERRY__TREE_PLACER = getTreePlacer("cherry_tree", CHERRY);

	public static final Holder<ConfiguredFeature<?, ?>> DRAGON = fruitTreeFeature("dragon_tree", Blocks.ACACIA_LOG, ModBlockRegistry.DragonLeaves,
			new DragonFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)), 3, 0, 0, 1, 0, 1);
	public static Holder<PlacedFeature> DRAGON_TREE_PLACER = getTreePlacer("dragon_tree", DRAGON);

	public static final Holder<ConfiguredFeature<?, ?>> CHESTNUT = fruitTreeFeatureThreeLayer("chestnut_tree", Blocks.DARK_OAK_LOG, ModBlockRegistry.ChestnutLeaves,
			new DarkOakFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)), 5, 2, 0, 1, 1, 0, 1, 2);
	public static Holder<PlacedFeature> CHESTNUT_TREE_PLACER = getTreePlacer("chestnut_tree", CHESTNUT);

	public static final Holder<ConfiguredFeature<?, ?>> OLIVE = fruitTreeFeature("olive_tree", Blocks.ACACIA_LOG, ModBlockRegistry.OliveLeaves,
			new OliveFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)), 4, 2, 0, 1, 0, 1);
	public static Holder<PlacedFeature> OLIVE_TREE_PLACER = getTreePlacer("olive_tree", OLIVE);

	public static final Holder<ConfiguredFeature<?, ?>> ORANGE = fruitTreeFeature("orange_tree", Blocks.JUNGLE_LOG, ModBlockRegistry.OrangeLeaves,
			new OrangeFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)), 4, 2, 0, 1, 0, 1);
	public static Holder<PlacedFeature> ORANGE_TREE_PLACER = getTreePlacer("orange_tree", ORANGE);

	//@ObjectHolder(registryName = "minecraft:worldgen/placed_feature", value = "charcoal_pit:cherry_tree_checker")
	//public static Holder<PlacedFeature> CHERRY_CHECKER;
	//@ObjectHolder(registryName = "minecraft:worldgen/_feature", value = "cherry_tree_randomizer")
	//public static Holder<ConfiguredFeature<?,?>> CHERRY_RANDOMIZER;

	//@ObjectHolder(registryName = "minecraft:worldgen/placed_feature", value = "cherry_tree_placer")
	//public static Holder<PlacedFeature> CHERRY_TREE_PLACER;
	//@ObjectHolder(registryName = "minecraft:worldgen/feature", value = "charcoal_pit:dragon_tree")
	/*public static Holder<ConfiguredFeature<?, ?>> DRAGON;
	@ObjectHolder(registryName = "minecraft:worldgen/feature", value = "charcoal_pit:chestnut_tree")
	public static Holder<ConfiguredFeature<?, ?>> CHESTNUT;
	@ObjectHolder(registryName = "minecraft:worldgen/feature", value = "charcoal_pit:olive_tree")
	public static Holder<ConfiguredFeature<?, ?>> OLIVE;
	@ObjectHolder(registryName = "minecraft:worldgen/feature", value = "charcoal_pit:orange_tree")
	public static Holder<ConfiguredFeature<?, ?>> ORANGE;*/
	
	/*@SubscribeEvent
	public static void registerPlacers(RegisterEvent event) {
		if (ModBlockRegistry.AppleLeaves == null) {
			System.out.println("bals");
		}
		event.register(ForgeRegistries.Keys.FOLIAGE_PLACER_TYPES, helper -> {
			helper.register(new ResourceLocation(CharcoalPit.MODID, "dragon_placer"), DRAGON_PLACER);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "olive_placer"), OLIVE_PLACER);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "orange_placer"), ORANGE_PLACER);
		});

		//todo: finish

		event.register(Registry.CONFIGURED_FEATURE_REGISTRY, helper -> {
			helper.register(RegistryClass.charcoalPit("apple_tree_config"), fruitTreeFeature(Blocks.OAK_LOG, ModBlockRegistry.AppleLeaves,
					blobFoliage(2, 0, 3), 4, 2, 0, 1, 0, 1));
			//helper.register(RegistryClass.charcoalPit("apple_tree_randomizer"), fruitTreeRandomizer(APPLE_CHECKER, 0.1F));
			helper.register(RegistryClass.charcoalPit("cherry_tree_config"), fruitTreeFeature(Blocks.BIRCH_LOG, ModBlockRegistry.CherryLeaves,
					blobFoliage(2, 0, 3), 5, 2, 0, 1, 0, 1));
			//helper.register(RegistryClass.charcoalPit("cherry_tree_randomizer"), fruitTreeRandomizer(CHERRY_CHECKER, 0.1F));
			helper.register(RegistryClass.charcoalPit("dragon_tree"), fruitTreeFeature(Blocks.ACACIA_LOG, ModBlockRegistry.DragonLeaves,
					new DragonFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)), 3, 0, 0, 1, 0, 1));
			helper.register(RegistryClass.charcoalPit("chestnut_tree"), fruitTreeFeatureThreeLayer(Blocks.DARK_OAK_LOG, ModBlockRegistry.ChestnutLeaves,
					new DarkOakFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)), 5, 2, 0, 1, 1, 0, 1, 2));
			helper.register(RegistryClass.charcoalPit("olive_tree"), fruitTreeFeature(Blocks.ACACIA_LOG, ModBlockRegistry.OliveLeaves,
					new OliveFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)), 4, 2, 0, 1, 0, 1));
			helper.register(RegistryClass.charcoalPit("orange_tree"), fruitTreeFeature(Blocks.JUNGLE_LOG, ModBlockRegistry.OrangeLeaves,
					new OrangeFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)), 4, 2, 0, 1, 0, 1));
		});
		event.register(Registry.PLACED_FEATURE_REGISTRY, helper -> {
			//helper.register(RegistryClass.charcoalPit("apple_tree"), fruitTreePlacer(Registry.CONFIGURED_FEATURE_REGISTRY::, 1, 0.1F, 2));
		helper.register(RegistryClass.charcoalPit("apple_tree_checker"),
				fruitTreeValidator(APPLE));
		helper.register(RegistryClass.charcoalPit("apple_tree_placer"),
				fruitTreePlacer(APPLE_RANDOMIZER, 1, 0.1F, 2));
		helper.register(RegistryClass.charcoalPit("cherry_tree_checker"),
				fruitTreeValidator(CHERRY));
		helper.register(RegistryClass.charcoalPit("apple_tree"),
				new PlacedFeature(DRAGON, List.of(PlacementUtils.filteredByBlockSurvival(ModBlockRegistry.DragonSapling))));
		helper.register(RegistryClass.charcoalPit("apple_tree"),
				new PlacedFeature(CHESTNUT, List.of(PlacementUtils.filteredByBlockSurvival(ModBlockRegistry.ChestnutSapling))));
		helper.register(RegistryClass.charcoalPit("apple_tree"),
				new PlacedFeature(OLIVE, List.of(PlacementUtils.filteredByBlockSurvival(ModBlockRegistry.OliveSapling))));
		});

		RecordCodecBuilder.create(builder-> builder.group(
				Biome.LIST_CODEC.fieldOf("biomes").forGetter(FruitTreeModifier::biomes),
				Biome.LIST_CODEC.fieldOf("apple_biomes").forGetter(FruitTreeModifier::apple_biomes),
				Biome.LIST_CODEC.fieldOf("cherry_biomes").forGetter(FruitTreeModifier::cherry_biomes)
		).apply(builder, FruitTreeModifier::new)));

		event.register(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, helper -> {
			helper.register(RegistryClass.charcoalPit("fruit_tree_modifier"),  () ->
					RecordCodecBuilder.create(builder -> builder.group(
							Biome.LIST_CODEC.fieldOf("biomes").forGetter( obj: FruitTreeModifier -> obj),
							Biome.LIST_CODEC.fieldOf("apple_biomes").forGetter(FruitTreeModifier::apple_biomes),
							Biome.LIST_CODEC.fieldOf("cherry_biomes").forGetter(FruitTreeModifier::cherry_biomes)
					).apply(builder, FruitTreeModifier::new)));
		});
	}*/

	/*public static <FC extends FeatureConfiguration, F extends Feature<FC>> RegistryObject<ConfiguredFeature<FC, ?>> register(String key, F feature, Supplier<FC> configurationSupplier)
	{
		return BiomesOPlenty.CONFIGURED_FEATURE_REGISTER.register(key, () -> new ConfiguredFeature<>(feature, configurationSupplier.get()));
	}*/
	//@SubscribeEvent
	public static void register(FMLCommonSetupEvent event){
		//Registry.register(Registry.TRUNK_PLACER_TYPES, new ResourceLocation(CharcoalPit.MODID, "double_placer"), DOUBLE_PLACER);
		//Registry.register(Registry.FOLIAGE_PLACER_TYPES, new ResourceLocation(CharcoalPit.MODID, "dragon_placer"), DRAGON_PLACER);
		//Registry.register(Registry.FOLIAGE_PLACER_TYPES, new ResourceLocation(CharcoalPit.MODID, "olive_placer"), OLIVE_PLACER);
		//Registry.register(Registry.FOLIAGE_PLACER_TYPES, new ResourceLocation(CharcoalPit.MODID, "orange"), ORANGE_PLACER);

		/*APPLE= new ConfiguredFeature<>(new TreeConfiguration.TreeConfigurationBuilder(new SimpleStateProvider2(Blocks.OAK_LOG.defaultBlockState()),new StraightTrunkPlacer(4, 2, 0), new SimpleStateProvider2(ModBlockRegistry.AppleLeaves.defaultBlockState()), new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3), new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build());
		CHERRY= FeatureUtils.register("charcoal_pit:cherry",Feature.TREE,(new TreeConfiguration.TreeConfigurationBuilder(new SimpleStateProvider2(Blocks.BIRCH_LOG.defaultBlockState()), new StraightTrunkPlacer(5, 2, 0), new SimpleStateProvider2(ModBlockRegistry.CherryLeaves.defaultBlockState()), new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3), new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build());
		DRAGON= FeatureUtils.register("charcoal_pit:dragon",Feature.TREE,(new TreeConfiguration.TreeConfigurationBuilder(new SimpleStateProvider2(Blocks.ACACIA_LOG.defaultBlockState()), new StraightTrunkPlacer(3, 0, 0), new SimpleStateProvider2(ModBlockRegistry.DragonLeaves.defaultBlockState()), new DragonFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)), new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build());
		CHESTNUT= FeatureUtils.register("charcoal_pit:walnut",Feature.TREE,(new TreeConfiguration.TreeConfigurationBuilder(new SimpleStateProvider2(Blocks.DARK_OAK_LOG.defaultBlockState()), new DoubleTrunkPlacer(5,2,0), new SimpleStateProvider2(ModBlockRegistry.ChestnutLeaves.defaultBlockState()), new DarkOakFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)), new ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty()))).ignoreVines().build());
		OLIVE= FeatureUtils.register("charcoal_pit:olive",Feature.TREE,(new TreeConfiguration.TreeConfigurationBuilder(new SimpleStateProvider2(Blocks.ACACIA_LOG.defaultBlockState()), new StraightTrunkPlacer(3, 0, 0), new SimpleStateProvider2(ModBlockRegistry.OliveLeaves.defaultBlockState()), new OliveFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)), new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build());
		ORANGE= FeatureUtils.register("charcoal_pit:orange",Feature.TREE,(new TreeConfiguration.TreeConfigurationBuilder(new SimpleStateProvider2(Blocks.JUNGLE_LOG.defaultBlockState()),new StraightTrunkPlacer(4, 2, 0), new SimpleStateProvider2(ModBlockRegistry.OrangeLeaves.defaultBlockState()), new OrangeFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)), new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build());
		//APPLE = BuiltinRegistries.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(CharcoalPit.MODID,"apple"), new ConfiguredFeature<>(Feature.TREE,((new TreeConfiguration.TreeConfigurationBuilder(new SimpleStateProvider2(Blocks.OAK_LOG.defaultBlockState()),new StraightTrunkPlacer(4, 2, 0), new SimpleStateProvider2(ModBlockRegistry.AppleLeaves.defaultBlockState()), new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3), new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build())));
		/*CHERRY = BuiltinRegistries.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(CharcoalPit.MODID,"cherry"), new ConfiguredFeature<>(Feature.TREE,((new TreeConfiguration.TreeConfigurationBuilder(new SimpleStateProvider2(Blocks.BIRCH_LOG.defaultBlockState()), new StraightTrunkPlacer(5, 2, 0), new SimpleStateProvider2(ModBlockRegistry.CherryLeaves.defaultBlockState()), new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3), new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build())));
		DRAGON = BuiltinRegistries.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(CharcoalPit.MODID,"dragon"), new ConfiguredFeature<>(Feature.TREE,((new TreeConfiguration.TreeConfigurationBuilder(new SimpleStateProvider2(Blocks.ACACIA_LOG.defaultBlockState()), new StraightTrunkPlacer(3, 0, 0), new SimpleStateProvider2(ModBlockRegistry.DragonLeaves.defaultBlockState()), new DragonFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)), new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build())));
		CHESTNUT=BuiltinRegistries.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(CharcoalPit.MODID, "chestnut"),new ConfiguredFeature<>(Feature.TREE,((new TreeConfiguration.TreeConfigurationBuilder(new SimpleStateProvider2(Blocks.DARK_OAK_LOG.defaultBlockState()), new DoubleTrunkPlacer(5,2,0), new SimpleStateProvider2(ModBlockRegistry.ChestnutLeaves.defaultBlockState()), new DarkOakFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)), new ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty()))).ignoreVines().build())));
		OLIVE=BuiltinRegistries.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(CharcoalPit.MODID,"olive"), new ConfiguredFeature<>(Feature.TREE,((new TreeConfiguration.TreeConfigurationBuilder(new SimpleStateProvider2(Blocks.ACACIA_LOG.defaultBlockState()), new StraightTrunkPlacer(3, 0, 0), new SimpleStateProvider2(ModBlockRegistry.OliveLeaves.defaultBlockState()), new OliveFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)), new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build())));
		ORANGE = BuiltinRegistries.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(CharcoalPit.MODID,"orange"), new ConfiguredFeature<>(Feature.TREE,((new TreeConfiguration.TreeConfigurationBuilder(new SimpleStateProvider2(Blocks.JUNGLE_LOG.defaultBlockState()),new StraightTrunkPlacer(4, 2, 0), new SimpleStateProvider2(ModBlockRegistry.OrangeLeaves.defaultBlockState()), new OrangeFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)), new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build())));
		*/
		/*ModBlockRegistry.AppleLeaves.fruit= Items.APPLE;
		ModBlockRegistry.CherryLeaves.fruit=ModItemRegistry.Cherry;
		ModBlockRegistry.DragonLeaves.fruit=ModItemRegistry.DragonFruit;
		ModBlockRegistry.ChestnutLeaves.fruit=ModItemRegistry.ChestNut;
		ModBlockRegistry.OliveLeaves.fruit=ModItemRegistry.Olives;
		ModBlockRegistry.OrangeLeaves.fruit=ModItemRegistry.Orange;*/
	}
	
	public static class AppleTree extends AbstractTreeGrower{
		
		@Nullable
		@Override
		protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource randomIn, boolean largeHive) {
			return APPLE.getHolder().get();
		}
	}

	public static class CherryTree extends AbstractTreeGrower{
		
		@Nullable
		@Override
		protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource randomIn, boolean largeHive) {
			return CHERRY.getHolder().get();
		}
	}
	public static class DragonTree extends AbstractTreeGrower{
		
		@Nullable
		@Override
		protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource randomIn, boolean largeHive) {
			return DRAGON.getHolder().get();
		}
	}
	public static class OliveTree extends AbstractTreeGrower{
		
		@Nullable
		@Override
		protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource randomIn, boolean largeHive) {
			return OLIVE.getHolder().get();
		}
	}
	public static class OrangeTree extends AbstractTreeGrower{
		
		@Nullable
		@Override
		protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource randomIn, boolean largeHive) {
			return ORANGE.getHolder().get();
		}
	}
	public static class ChestnutTree extends AbstractMegaTreeGrower {
		
		@Nullable
		@Override
		protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource randomIn, boolean largeHive) {
			return null;
		}
		
		@Nullable
		@Override
		protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredMegaFeature(RandomSource rand) {
			return CHESTNUT.getHolder().get();
		}
	}
	
	public static class SimpleStateProvider2 extends SimpleStateProvider{
		
		public SimpleStateProvider2(BlockState p_68801_) {
			super(p_68801_);
		}
	}
	//todo: finish these mthods

	public static BlobFoliagePlacer blobFoliage(int radius, int offset, int height) {
		return new BlobFoliagePlacer(ConstantInt.of(radius), ConstantInt.of(offset), height);
	}
	public static Holder<ConfiguredFeature<?, ?>> fruitTreeFeature(String id, Block logBlock, Block leafBlock, FoliagePlacer foliagePlacer, int baseHeight, int randHeightA, int randHeightB, int sizeLimit, int lowerSize, int upperSize) {
		return BuiltinRegistries.register((Registry)BuiltinRegistries.CONFIGURED_FEATURE, RegistryClass.charcoalPit(id), new ConfiguredFeature<>(Feature.TREE,
				new TreeConfiguration.TreeConfigurationBuilder(
						new SimpleStateProvider2(logBlock.defaultBlockState()),
						new StraightTrunkPlacer(baseHeight, randHeightA, randHeightB),
						new SimpleStateProvider2(leafBlock.defaultBlockState()),
						foliagePlacer,
						new TwoLayersFeatureSize(sizeLimit, lowerSize, upperSize)).ignoreVines().build()));
	}

	public static ConfiguredFeature<?, ?> fruitTreeFeatureObject(Block logBlock, Block leafBlock, FoliagePlacer foliagePlacer, int baseHeight, int randHeightA, int randHeightB, int sizeLimit, int lowerSize, int upperSize) {
		return new ConfiguredFeature<>(Feature.TREE,
				new TreeConfiguration.TreeConfigurationBuilder(
						new SimpleStateProvider2(logBlock.defaultBlockState()),
						new StraightTrunkPlacer(baseHeight, randHeightA, randHeightB),
						new SimpleStateProvider2(leafBlock.defaultBlockState()),
						foliagePlacer,
						new TwoLayersFeatureSize(sizeLimit, lowerSize, upperSize)).ignoreVines().build());
	}

	public static Holder<ConfiguredFeature<?,?>> fruitTreeFeatureThreeLayer(String id, Block logBlock, Block leafBlock, FoliagePlacer foliagePlacer, int baseHeight, int randHeightA, int randHeightB, int sizeLimit, int lowerSize, int upperSize, int ex1, int ex2) {
		return BuiltinRegistries.register((Registry)BuiltinRegistries.CONFIGURED_FEATURE, RegistryClass.charcoalPit(id), new ConfiguredFeature<>(Feature.TREE,
				new TreeConfiguration.TreeConfigurationBuilder(
						new SimpleStateProvider2(logBlock.defaultBlockState()),
						new StraightTrunkPlacer(baseHeight, randHeightA, randHeightB),
						new SimpleStateProvider2(leafBlock.defaultBlockState()),
						foliagePlacer,
						new ThreeLayersFeatureSize(sizeLimit, lowerSize, upperSize, ex1, ex2, OptionalInt.empty())).ignoreVines().build()));
	}

	public static ConfiguredFeature<?,?> fruitTreeFeatureThreeLayerObject(Block logBlock, Block leafBlock, FoliagePlacer foliagePlacer, int baseHeight, int randHeightA, int randHeightB, int sizeLimit, int lowerSize, int upperSize, int ex1, int ex2) {
		return new ConfiguredFeature<>(Feature.TREE,
				new TreeConfiguration.TreeConfigurationBuilder(
						new SimpleStateProvider2(logBlock.defaultBlockState()),
						new StraightTrunkPlacer(baseHeight, randHeightA, randHeightB),
						new SimpleStateProvider2(leafBlock.defaultBlockState()),
						foliagePlacer,
						new ThreeLayersFeatureSize(sizeLimit, lowerSize, upperSize, ex1, ex2, OptionalInt.empty())).ignoreVines().build());
	}

	public static PlacedFeature fruitTreeValidator(Holder<ConfiguredFeature<?, ?>> feature) {
		return new PlacedFeature(feature, List.of(PlacementUtils.filteredByBlockSurvival(ModBlockRegistry.AppleSapling)));
	}

	public static Holder<PlacedFeature> fruitTreeValidator(String id, Holder<ConfiguredFeature<?, ?>> feature) {
		return BuiltinRegistries.register(BuiltinRegistries.PLACED_FEATURE, RegistryClass.charcoalPit(id), new PlacedFeature(feature, List.of(PlacementUtils.filteredByBlockSurvival(ModBlockRegistry.AppleSapling))));
	}

	public static ConfiguredFeature fruitTreeRandomizer(Holder<PlacedFeature> validator, float chance) {
		return new ConfiguredFeature<>(Feature.RANDOM_SELECTOR,
				new RandomFeatureConfiguration(List.of(
						new WeightedPlacedFeature(validator, chance)),
						validator
				));
	}

	public static void load() {
		//aaaa
	}

	public static PlacedFeature fruitTreePlacer(Holder<ConfiguredFeature<?, ?>> randomizer, int min, float chanceExtra, int max) {
		return new PlacedFeature(randomizer, VegetationPlacements.treePlacement(PlacementUtils.countExtra(min, chanceExtra, max)));
	}

	public static Holder<PlacedFeature> fruitTreePlacer(String id, Holder<ConfiguredFeature<?, ?>> randomizer, int min, float chanceExtra, int max) {
		return BuiltinRegistries.register((Registry)BuiltinRegistries.PLACED_FEATURE, RegistryClass.charcoalPit(id), new PlacedFeature(randomizer, VegetationPlacements.treePlacement(PlacementUtils.countExtra(min, chanceExtra, max))));
	}

	public static PlacedFeature fruitTreePlacer(ConfiguredFeature<?, ?> randomizer, int min, float chanceExtra, int max) {
		return new PlacedFeature(Holder.direct(randomizer), VegetationPlacements.treePlacement(PlacementUtils.countExtra(min, chanceExtra, max)));
	}

	public static Holder<PlacedFeature> getTreePlacer(String id, Holder<ConfiguredFeature<?, ?>> base_tree) {
		Holder<PlacedFeature> checker = BuiltinRegistries.register(BuiltinRegistries.PLACED_FEATURE, RegistryClass.charcoalPit(id + "checker"), new PlacedFeature(base_tree, List.of(PlacementUtils.filteredByBlockSurvival(ModBlockRegistry.AppleSapling))));
		Holder<ConfiguredFeature<?, ?>> randomizer = BuiltinRegistries.register(BuiltinRegistries.CONFIGURED_FEATURE, RegistryClass.charcoalPit(id + "randomizer"), new ConfiguredFeature<>(Feature.RANDOM_SELECTOR,
				new RandomFeatureConfiguration(List.of(
						new WeightedPlacedFeature(checker, 0.1F)),
						checker
				)));
		return BuiltinRegistries.register((Registry)BuiltinRegistries.PLACED_FEATURE, RegistryClass.charcoalPit(id), new PlacedFeature(randomizer, VegetationPlacements.treePlacement(PlacementUtils.countExtra(1, 0.2F, 2))));
	}

	public static RegistryObject<PlacedFeature> getTreePlacerDeferred(String id, RegistryObject<ConfiguredFeature<?, ?>> base_tree) {
		RegistryObject<PlacedFeature> checker = PLACED_FEATURES.register( id + "_checker", () -> new PlacedFeature(base_tree.getHolder().get(), List.of(PlacementUtils.filteredByBlockSurvival(ModBlockRegistry.AppleSapling))));
		RegistryObject<ConfiguredFeature<?, ?>> randomizer = CONFIGURED_FEATURES.register(id + "_randomizer", () -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR,
				new RandomFeatureConfiguration(List.of(
						new WeightedPlacedFeature(checker.getHolder().get(), 0.01F)),
						checker.getHolder().get()
				)));
		System.out.println(APPLE.getId());
		return PLACED_FEATURES.register(id, () -> new PlacedFeature(randomizer.getHolder().get(), VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.2F, 2))));
	}
}
