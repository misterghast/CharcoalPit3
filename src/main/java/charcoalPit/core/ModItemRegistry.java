package charcoalPit.core;

import charcoalPit.CharcoalPit;
import charcoalPit.block.BlockFruitLeaves;
import charcoalPit.fluid.ModFluidRegistry;
import charcoalPit.item.*;
import charcoalPit.item.tool.ModArmorTiers;
import charcoalPit.item.tool.ModTiers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.common.data.ForgeItemTagsProvider;

import net.minecraft.world.food.FoodProperties;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.RegisterEvent;

import static charcoalPit.core.ModBlockRegistry.*;

//@EventBusSubscriber(modid=CharcoalPit.MODID, bus=Bus.MOD)
public class ModItemRegistry {
	
	public static CreativeModeTab CHARCOAL_PIT=new CreativeModeTab("charcoal_pit") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(Items.CHARCOAL);
		}
	};
	public static CreativeModeTab CHARCOAL_PIT_FOODS=new CreativeModeTab("charcoal_pit_foods") {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ModItemRegistry.Kebabs);
		}
	};

	public static BlockItemFuel Thatch=buildBlockItem(ModBlockRegistry.Thatch,200),LogPile=buildBlockItem(ModBlockRegistry.LogPile,3000),CokeBlock=buildBlockItem(ModBlockRegistry.CokeBlock,32000);
	public static BlockItem WoodAsh=buildBlockItem(ModBlockRegistry.WoodAsh),CoalAsh=buildBlockItem(ModBlockRegistry.CoalAsh),AshBlock=buildBlockItem(ModBlockRegistry.Ash),
			SandyBrick=buildBlockItem(ModBlockRegistry.SandyBrick),SandySlab=buildBlockItem(ModBlockRegistry.SandySlab),SandyStair=buildBlockItem(ModBlockRegistry.SandyStair),SandyWall=buildBlockItem(ModBlockRegistry.SandyWall);
	
	public static ItemFuel Straw=buildItem(CHARCOAL_PIT,50),Coke=buildItem(CHARCOAL_PIT,3200);
	public static Item Ash=buildItem(CHARCOAL_PIT),Aeternalis=new ItemAeternalis();
	public static BoneMealItem Fertilizer=new BoneMealItem(new Item.Properties().tab(CHARCOAL_PIT));
	public static ItemFireStarter FireStarter=new ItemFireStarter();
	public static Item SandyBrickItem=buildItem(CHARCOAL_PIT),NetherBrickItem=buildItem(CHARCOAL_PIT),UnfireSandyBrick=buildItem(CHARCOAL_PIT),UnfiredBrick=buildItem(CHARCOAL_PIT);
	
	public static BlockItem BrickCollector=buildBlockItem(ModBlockRegistry.BrickCollector,CHARCOAL_PIT),
			SandyCollector=buildBlockItem(ModBlockRegistry.SandyCollector,CHARCOAL_PIT),
			NetherCollector=buildBlockItem(ModBlockRegistry.NetherCollector,CHARCOAL_PIT),
			EndCollector=buildBlockItem(ModBlockRegistry.EndCollector,CHARCOAL_PIT);
	public static BlockItem CeramicPot=buildBlockItemP(ModBlockRegistry.CeramicPot),WhitePot=buildBlockItemP(ModBlockRegistry.WhitePot),
			OrangePot=buildBlockItemP(ModBlockRegistry.OrangePot),MagentaPot=buildBlockItemP(ModBlockRegistry.MagentaPot),
			LightBluePot=buildBlockItemP(ModBlockRegistry.LightBluePot),YellowPot=buildBlockItemP(ModBlockRegistry.YellowPot),
			LimePot=buildBlockItemP(ModBlockRegistry.LimePot),PinkPot=buildBlockItemP(ModBlockRegistry.PinkPot),
			GrayPot=buildBlockItemP(ModBlockRegistry.GrayPot),LightGrayPot=buildBlockItemP(ModBlockRegistry.LightGrayPot),
			CyanPot=buildBlockItemP(ModBlockRegistry.CyanPot),PurplePot=buildBlockItemP(ModBlockRegistry.PurplePot),
			BluePot=buildBlockItemP(ModBlockRegistry.BluePot),BrownPot=buildBlockItemP(ModBlockRegistry.BrownPot),
			GreenPot=buildBlockItemP(ModBlockRegistry.GreenPot),RedPot=buildBlockItemP(ModBlockRegistry.RedPot),
			BlackPot=buildBlockItemP(ModBlockRegistry.BlackPot);
	public static ItemClayPot ClayPot=new ItemClayPot();
	//public static Item ClayPot=buildItem(CHARCOAL_PIT);//no alloy
	public static ItemCrackedPot CrackedPot=new ItemCrackedPot();
	
	public static ItemBarrel Barrel=new ItemBarrel(ModBlockRegistry.Barrel,new Item.Properties().tab(CHARCOAL_PIT));
	
	public static BucketItem CreosoteBucket=new BucketItem(()->ModFluidRegistry.CreosoteStill, new Item.Properties().tab(CHARCOAL_PIT).stacksTo(1).craftRemainder(Items.BUCKET));
	public static ItemAlcoholBottle AlcoholBottle=new ItemAlcoholBottle();
	public static BucketItem VinegarBucket=new BucketItem(()->ModFluidRegistry.VinegarStill, new Item.Properties().tab(CHARCOAL_PIT).stacksTo(1).craftRemainder(Items.BUCKET));
	public static Item VinegarBottle=new Item(new Item.Properties().tab(CHARCOAL_PIT).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE));
	public static Item Cheese=new Item(new Item.Properties().tab(CHARCOAL_PIT_FOODS).food(new FoodProperties.Builder().nutrition(5).saturationMod(1.2F).build()));
	public static BlockItem Bellows=buildBlockItem(ModBlockRegistry.Bellows);
	public static BlockItem MechanicalBeellows=buildBlockItem(ModBlockRegistry.MechanicalBellows);
	public static ItemKebabs Kebabs=new ItemKebabs(new Item.Properties().tab(CHARCOAL_PIT_FOODS).food(new FoodProperties.Builder().nutrition(5).saturationMod(1.6F).meat().build()).craftRemainder(Items.STICK));
	public static ItemNameBlockItem Leek=new ItemNameBlockItem(ModBlockRegistry.Leeks,new Item.Properties().tab(CHARCOAL_PIT_FOODS).food(new FoodProperties.Builder().nutrition(1).saturationMod(0.6F).fast().build()));
	public static ItemKebabs FarfetchStew=new ItemKebabs(new Item.Properties().tab(CHARCOAL_PIT_FOODS).food(new FoodProperties.Builder().nutrition(8).saturationMod(1.6F).meat().build()).craftRemainder(Items.BOWL));
	public static Item Calamari=new Item(new Item.Properties().tab(CHARCOAL_PIT_FOODS).food(new FoodProperties.Builder().nutrition(1).saturationMod(0.2F).meat().build()));
	public static Item CookedCalamri=new Item(new Item.Properties().tab(CHARCOAL_PIT_FOODS).food(new FoodProperties.Builder().nutrition(4).saturationMod(1.2F).meat().build()));
	public static Item CookedEgg=new Item(new Item.Properties().tab(CHARCOAL_PIT_FOODS).food(new FoodProperties.Builder().nutrition(3).saturationMod(1.2F).meat().build()));
	public static ItemNameBlockItem Corn=new ItemNameBlockItem(ModBlockRegistry.Corn,new Item.Properties().tab(CHARCOAL_PIT_FOODS).food(new FoodProperties.Builder().nutrition(3).saturationMod(1.2F).build()));
	public static Item PopCorn=new Item(new Item.Properties().tab(CHARCOAL_PIT_FOODS).food(new FoodProperties.Builder().nutrition(2).saturationMod(0.6F).fast().build()));
	public static ItemKebabs CornStew=new ItemKebabs(new Item.Properties().tab(CHARCOAL_PIT_FOODS).food(new FoodProperties.Builder().nutrition(7).saturationMod(1.6F).build()).craftRemainder(Items.BOWL));
	public static Item Sushi=new Item(new Item.Properties().tab(CHARCOAL_PIT_FOODS).food(new FoodProperties.Builder().nutrition(6).saturationMod(1.2F).meat().build()));
	public static Item Fugu=new Item(new Item.Properties().tab(CHARCOAL_PIT_FOODS).food(new FoodProperties.Builder().nutrition(5).saturationMod(2.4F).meat().effect(()->new MobEffectInstance(MobEffects.POISON,1200,3),0.02F).build()));
	public static Item Cherry=new Item(new Item.Properties().tab(CHARCOAL_PIT_FOODS).food(new FoodProperties.Builder().nutrition(2).saturationMod(0.6F).fast().build()));
	public static Item DragonFruit=new Item(new Item.Properties().tab(CHARCOAL_PIT_FOODS).food(new FoodProperties.Builder().nutrition(6).saturationMod(1.2F).alwaysEat().effect(()->new MobEffectInstance(MobEffects.FIRE_RESISTANCE,20*10),1F).build()));
	public static Item ChestNut=new Item(new Item.Properties().tab(CHARCOAL_PIT_FOODS).food(new FoodProperties.Builder().nutrition(3).saturationMod(1.2F).build()));
	//public static Item CookedChestNut=new Item(new Item.Properties().tab(CHARCOAL_PIT_FOODS).food(new FoodProperties.Builder().nutrition(4).saturationMod(1.2F).build()));
	public static ItemNameBlockItem Bananana=new ItemNameBlockItem(ModBlockRegistry.BanananaPod,new Item.Properties().tab(CHARCOAL_PIT_FOODS).food(new FoodProperties.Builder().nutrition(5).saturationMod(0.6F).build()));
	//public static Item Bananana=new Item(new Item.Properties().tab(CHARCOAL_PIT_FOODS).food(new FoodProperties.Builder().nutrition(5).saturationMod(0.6F).build()));
	public static ItemNameBlockItem Cococonut=new ItemNameBlockItem(ModBlockRegistry.CoconutPod,new Item.Properties().tab(CHARCOAL_PIT_FOODS).food(new FoodProperties.Builder().nutrition(4).saturationMod(1.2F).build()).craftRemainder(Items.BOWL));
	public static Item ChocoPoweder=new Item(new Item.Properties().tab(CHARCOAL_PIT_FOODS));
	public static Item Chocolate=new Item(new Item.Properties().tab(CHARCOAL_PIT_FOODS).food(new FoodProperties.Builder().nutrition(4).saturationMod(0.2F).fast().build()));
	public static ItemKebabs Dango=new ItemKebabs(new Item.Properties().tab(CHARCOAL_PIT_FOODS).food(new FoodProperties.Builder().nutrition(6).saturationMod(1.2F).build()).craftRemainder(Items.STICK));
	public static ItemNameBlockItem SunflowerSeeds=new ItemNameBlockItem(ModBlockRegistry.Sunflower,new Item.Properties().tab(CHARCOAL_PIT_FOODS).food(new FoodProperties.Builder().nutrition(2).saturationMod(0.6F).fast().build()));
	public static ItemMortarPestle mortarPestle=new ItemMortarPestle();
	public static Item Flour=buildItem(CHARCOAL_PIT_FOODS);
	public static Item CornFlour=buildItem(CHARCOAL_PIT_FOODS);
	public static Item Croissant=new Item(new Item.Properties().tab(CHARCOAL_PIT_FOODS).food(new FoodProperties.Builder().nutrition(6).saturationMod(1.2F).build()));
	public static Item Olives=buildItem(CHARCOAL_PIT_FOODS);
	public static Item Pickled_Olives=new Item(new Item.Properties().tab(CHARCOAL_PIT_FOODS).food(new FoodProperties.Builder().nutrition(4).saturationMod(1.2F).build()));
	public static Item Orange=new Item(new Item.Properties().tab(CHARCOAL_PIT_FOODS).food(new FoodProperties.Builder().nutrition(4).saturationMod(0.6F).build()));
	public static ItemKebabs SerinanStew=new ItemKebabs(new Item.Properties().tab(CHARCOAL_PIT_FOODS).food(new FoodProperties.Builder().nutrition(10).saturationMod(1.6F).meat().build()).craftRemainder(Items.BOWL));
	public static ItemKebabs BunnyStew=new ItemKebabs(new Item.Properties().tab(CHARCOAL_PIT_FOODS).food(new FoodProperties.Builder().nutrition(8).saturationMod(1.6F).meat().build()).craftRemainder(Items.BOWL));

	@ObjectHolder(registryName = "minecraft:item", value="charcoal_pit:apple_sapling")
	public static BlockItem AppleSapling=null;
	@ObjectHolder(registryName = "minecraft:item", value="charcoal_pit:cherry_sapling")
	public static BlockItem CherrySapling=null;
	@ObjectHolder(registryName = "minecraft:item", value="charcoal_pit:dragon_sapling")
	public static BlockItem DragonSapling=null;
	@ObjectHolder(registryName = "minecraft:item", value="charcoal_pit:chestnut_sapling")
	public static BlockItem ChestnutSapling=null;
	@ObjectHolder(registryName = "minecraft:item", value="charcoal_pit:olive_sapling")
	public static BlockItem OliveSapling=null;
	@ObjectHolder(registryName = "minecraft:item", value="charcoal_pit:orange_sapling")
	public static BlockItem OrangeSapling=null;

	@ObjectHolder(registryName = "minecraft:item", value ="charcoal_pit:apple_leaves")
	public static ItemBlockLeaves AppleLeaves= null;
	@ObjectHolder(registryName = "minecraft:item", value ="charcoal_pit:cherry_leaves")
	public static ItemBlockLeaves CherryLeaves= null;
	@ObjectHolder(registryName = "minecraft:item", value ="charcoal_pit:dragon_leaves")
	public static ItemBlockLeaves DragonLeaves= null;
	@ObjectHolder(registryName = "minecraft:item", value ="charcoal_pit:chestnut_leaves")
	public static ItemBlockLeaves ChestnutLeaves=null;
	@ObjectHolder(registryName = "minecraft:item", value ="charcoal_pit:olive_leaves")
	public static ItemBlockLeaves OliveLeaves=null;
	@ObjectHolder(registryName = "minecraft:item", value ="charcoal_pit:orange_leaves")
	public static ItemBlockLeaves OrangeLeaves=null;

	public static ItemOilLamp OilLamp=new ItemOilLamp(ModBlockRegistry.GenieLight,new Item.Properties().tab(CHARCOAL_PIT).stacksTo(1));
	
	public static BucketItem OliveOilBucket=new BucketItem(()->ModFluidRegistry.OliveOilStill, new Item.Properties().tab(CHARCOAL_PIT).stacksTo(1).craftRemainder(Items.BUCKET));
	public static BucketItem WalnutOilBucket=new BucketItem(()->ModFluidRegistry.WalnutOilStill, new Item.Properties().tab(CHARCOAL_PIT).stacksTo(1).craftRemainder(Items.BUCKET));
	public static BucketItem SunflowerOilBucket=new BucketItem(()->ModFluidRegistry.SunflowerOilStill, new Item.Properties().tab(CHARCOAL_PIT).stacksTo(1).craftRemainder(Items.BUCKET));
	public static BucketItem BioDieselBucket=new BucketItem(()-> ModFluidRegistry.BiodieselStill,new Item.Properties().tab(CHARCOAL_PIT).stacksTo(1).craftRemainder(Items.BUCKET));
	public static BucketItem EthanolBucket=new BucketItem(()->ModFluidRegistry.EthanolStill,new Item.Properties().tab(CHARCOAL_PIT).stacksTo(1).craftRemainder(Items.BUCKET));
	public static BucketItem EthoxideBucket=new BucketItem(()->ModFluidRegistry.EthoxideStill,new Item.Properties().tab(CHARCOAL_PIT).stacksTo(1).craftRemainder(Items.BUCKET));
	public static BucketItem SeedOilBucket=new BucketItem(()->ModFluidRegistry.SeedOilStill,new Item.Properties().tab(CHARCOAL_PIT).stacksTo(1).craftRemainder(Items.BUCKET));
	
	public static BlockItem FeedingThrough =buildBlockItem(ModBlockRegistry.FeedingThrough);
	public static BlockItem FeedingThroughBirch=buildBlockItem(ModBlockRegistry.FeedingThroughBirch);
	//public static BlockItem FeedingThroughJungle =buildBlockItem(ModBlockRegistry.FeedingThroughJungle);
	public static BlockItem FeedingThroughSpruce =buildBlockItem(ModBlockRegistry.FeedingThroughSpruce);
	//public static BlockItem FeedingThroughDark =buildBlockItem(ModBlockRegistry.FeedingThroughDark);
	public static BlockItem FeedingThroughAcacia =buildBlockItem(ModBlockRegistry.FeedingThroughAcacia);
	//public static BlockItem FeedingThroughCrimson =buildBlockItem(ModBlockRegistry.FeedingThroughCrimson);
	public static BlockItem FeedingThroughWarped =buildBlockItem(ModBlockRegistry.FeedingThroughWarped);
	
	public static BlockItem NestBox=buildBlockItem(ModBlockRegistry.NestBox);
	public static BlockItem Bloomeryy=buildBlockItem(ModBlockRegistry.Bloomeryy);
	public static BlockItem CharcoalBlock=buildBlockItem(ModBlockRegistry.CharcoalBlock);
	public static BlockItem Bloom=new BlockItem(ModBlockRegistry.Bloom,new Item.Properties().tab(CHARCOAL_PIT).stacksTo(16));
	public static BlockItem BlastFurnace=buildBlockItem(ModBlockRegistry.BlastFurnace);
	public static BlockItem Distillery=buildBlockItem(ModBlockRegistry.Distillery);
	public static BlockItem SteamPress=buildBlockItem(ModBlockRegistry.SteamPress);
	public static BlockItem WrathLantern=buildBlockItem(ModBlockRegistry.WrathLantern);
	
	public static ItemAnimalCage AnimalCage=new ItemAnimalCage(new Item.Properties().tab(CHARCOAL_PIT));
	public static Item LyeBottle=new Item(new Item.Properties().tab(CHARCOAL_PIT).stacksTo(16));
	public static Item SourAlcoholBottle=new Item(new Item.Properties().tab(CHARCOAL_PIT).stacksTo(16).craftRemainder(LyeBottle));
	public static ItemFuel Glycerine=buildItem(CHARCOAL_PIT,1200);
	public static Item AlloyPigIron=buildItem(CHARCOAL_PIT),AlloySteel=buildItem(CHARCOAL_PIT);
	public static ItemTuyere Tuyere=new ItemTuyere(new Item.Properties().tab(CHARCOAL_PIT));
	public static Item Flux=buildItem(CHARCOAL_PIT),PrismarineDust=buildItem(CHARCOAL_PIT);
	public static Item AlloyOrichalcum=buildItem(CHARCOAL_PIT);
	public static ItemEthanolBottle EthanolBottle=new ItemEthanolBottle();
	public static Item EthoxideBottle=new Item(new Item.Properties().tab(CHARCOAL_PIT).stacksTo(16).craftRemainder(Items.GLASS_BOTTLE));
	public static Item winglet=buildItem(CHARCOAL_PIT),wing=buildItem(CHARCOAL_PIT),engine=buildItem(CHARCOAL_PIT);
	public static Item NetherShard=new SimpleFoiledItem(new Item.Properties().tab(CHARCOAL_PIT).rarity(Rarity.UNCOMMON));
	
	
	public static PickaxeItem FlintPick=new PickaxeItem(ModTiers.FLINT,1,-2.8F,new Item.Properties().tab(CHARCOAL_PIT));
	public static ShovelItem FlintShovel=new ShovelItem(ModTiers.FLINT,1.5F,-3,new Item.Properties().tab(CHARCOAL_PIT));
	public static AxeItem FlintAxe=new AxeItem(ModTiers.FLINT,7,-3.2F,new Item.Properties().tab(CHARCOAL_PIT));
	public static HoeItem FlintHoe=new HoeItem(ModTiers.FLINT,-1,-2,new Item.Properties().tab(CHARCOAL_PIT));
	public static SwordItem FlintSword=new SwordItem(ModTiers.FLINT,3,-2.4F,new Item.Properties().tab(CHARCOAL_PIT));
	//public static ItemDagger FlintDagger=new ItemDagger(ModTiers.FLINT,2,-2,new Item.Properties().tab(CHARCOAL_PIT));
	
	public static PickaxeItem CopperPick=new PickaxeItem(ModTiers.COPPER, 1,-2.8F,new Item.Properties().tab(CHARCOAL_PIT));
	public static ShovelItem CopperShovle=new ShovelItem(ModTiers.COPPER,1.5F,-3,new Item.Properties().tab(CHARCOAL_PIT));
	public static AxeItem CopperAxe=new AxeItem(ModTiers.COPPER,6.75F,-3.1F,new Item.Properties().tab(CHARCOAL_PIT));
	public static HoeItem CopperHoe=new HoeItem(ModTiers.COPPER,-1,-2,new Item.Properties().tab(CHARCOAL_PIT));
	public static SwordItem CopperSword=new SwordItem(ModTiers.COPPER,3,-2.4F,new Item.Properties().tab(CHARCOAL_PIT));
	
	public static PickaxeItem SteelPick=new PickaxeItem(ModTiers.STEEL, 1,-2.8F,new Item.Properties().tab(CHARCOAL_PIT));
	public static ShovelItem SteelShovle=new ShovelItem(ModTiers.STEEL,1.5F,-3,new Item.Properties().tab(CHARCOAL_PIT));
	public static AxeItem SteelAxe=new AxeItem(ModTiers.STEEL,5.5F,-3F,new Item.Properties().tab(CHARCOAL_PIT));
	public static HoeItem SteelHoe=new HoeItem(ModTiers.STEEL,-2,-2,new Item.Properties().tab(CHARCOAL_PIT));
	public static SwordItem SteelSword=new SwordItem(ModTiers.STEEL,3,-2.4F,new Item.Properties().tab(CHARCOAL_PIT));
	
	public static ArmorItem SteelHelmet=new ArmorItem(ModArmorTiers.STEEL, EquipmentSlot.HEAD,new Item.Properties().tab(CHARCOAL_PIT));
	public static ArmorItem SteelChestplate=new ArmorItem(ModArmorTiers.STEEL,EquipmentSlot.CHEST,new Item.Properties().tab(CHARCOAL_PIT));
	public static ArmorItem SteelLeggings=new ArmorItem(ModArmorTiers.STEEL,EquipmentSlot.LEGS,new Item.Properties().tab(CHARCOAL_PIT));
	public static ArmorItem SteelBoots=new ArmorItem(ModArmorTiers.STEEL,EquipmentSlot.FEET,new Item.Properties().tab(CHARCOAL_PIT));
	
	public static ArmorItem CopperHelmet=new ArmorItem(ModArmorTiers.COPPER, EquipmentSlot.HEAD,new Item.Properties().tab(CHARCOAL_PIT));
	public static ArmorItem CopperChestplate=new ArmorItem(ModArmorTiers.COPPER,EquipmentSlot.CHEST,new Item.Properties().tab(CHARCOAL_PIT));
	public static ArmorItem CopperLeggings=new ArmorItem(ModArmorTiers.COPPER,EquipmentSlot.LEGS,new Item.Properties().tab(CHARCOAL_PIT));
	public static ArmorItem CopperBoots=new ArmorItem(ModArmorTiers.COPPER,EquipmentSlot.FEET,new Item.Properties().tab(CHARCOAL_PIT));
	
	public static PickaxeItem OrichalcumPick=new PickaxeItem(ModTiers.ORICHALCUM,1,-2.8F,new Item.Properties().tab(CHARCOAL_PIT));
	public static ShovelItem OrichalcumShovel=new ShovelItem(ModTiers.ORICHALCUM,1.5F,-3,new Item.Properties().tab(CHARCOAL_PIT));
	public static AxeItem OrichalcumAxe=new AxeItem(ModTiers.ORICHALCUM,6F,-3F,new Item.Properties().tab(CHARCOAL_PIT));
	public static HoeItem OrichalcumHoe=new HoeItem(ModTiers.ORICHALCUM,-2,-1,new Item.Properties().tab(CHARCOAL_PIT));
	
	public static ArmorItem OrichalcumHelmet=new ArmorItem(ModArmorTiers.ORICHALCUM,EquipmentSlot.HEAD,new Item.Properties().tab(CHARCOAL_PIT));
	public static ArmorItem OrichalcumChestplate=new ArmorItem(ModArmorTiers.ORICHALCUM,EquipmentSlot.CHEST,new Item.Properties().tab(CHARCOAL_PIT));
	public static ArmorItem OrichalcumLeggings=new ArmorItem(ModArmorTiers.ORICHALCUM,EquipmentSlot.LEGS,new Item.Properties().tab(CHARCOAL_PIT));
	public static ArmorItem OrichalcumBoots=new ArmorItem(ModArmorTiers.ORICHALCUM,EquipmentSlot.FEET,new Item.Properties().tab(CHARCOAL_PIT));
	
	public static ItemAirplane plane=new ItemAirplane(new Item.Properties().tab(CHARCOAL_PIT).stacksTo(1));
	
	/*public static TallBlockItem BrickDoor=new TallBlockItem(ModBlockRegistry.BrickDoor,new Item.Properties().group(CHARCOAL_PIT)),
			SandyDoor=new TallBlockItem(ModBlockRegistry.SandyDoor,new Item.Properties().group(CHARCOAL_PIT)),
			NetherDoor=new TallBlockItem(ModBlockRegistry.NetherDoor,new Item.Properties().group(CHARCOAL_PIT)),
			EndDoor=new TallBlockItem(ModBlockRegistry.EndDoor,new Item.Properties().group(CHARCOAL_PIT));*/
	
	
	
	//@SubscribeEvent
	public static void registerItems(RegisterEvent event) {
		event.register(ForgeRegistries.Keys.ITEMS, helper -> {
			helper.register(new ResourceLocation(CharcoalPit.MODID, "thatch"), Thatch);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "log_pile"), LogPile);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "wood_ash"), WoodAsh);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "coal_ash"),
					CoalAsh);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "coke_block"), CokeBlock);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "ash_block"), AshBlock);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "sandy_brick"),
					SandyBrick);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "sandy_slab"), SandySlab);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "sandy_stair"), SandyStair);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "sandy_wall"), SandyWall);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "brick_collector"),
					BrickCollector);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "sandy_collector"), SandyCollector);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "nether_collector"), NetherCollector);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "end_collector"), EndCollector);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "barrel"),
					Barrel);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "mechanical_bellows"), MechanicalBeellows);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "bellows"), Bellows);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "apple_sapling"), buildBlockItem(ModBlockRegistry.AppleSapling));

			helper.register(new ResourceLocation(CharcoalPit.MODID, "cherry_sapling"), buildBlockItem(ModBlockRegistry.CherrySapling));
			helper.register(new ResourceLocation(CharcoalPit.MODID, "apple_leaves"),
					buildLeavesItem(ModBlockRegistry.AppleLeaves));
			helper.register(new ResourceLocation(CharcoalPit.MODID, "cherry_leaves"),
					buildLeavesItem(ModBlockRegistry.CherryLeaves));
			helper.register(new ResourceLocation(CharcoalPit.MODID, "dragon_sapling"), buildBlockItem(ModBlockRegistry.DragonSapling));
			helper.register(new ResourceLocation(CharcoalPit.MODID, "dragon_leaves"),
					buildLeavesItem(ModBlockRegistry.DragonLeaves));
			helper.register(new ResourceLocation(CharcoalPit.MODID, "chestnut_sapling"), buildBlockItem(ModBlockRegistry.ChestnutSapling));
			helper.register(new ResourceLocation(CharcoalPit.MODID, "chestnut_leaves"),
					buildLeavesItem(ModBlockRegistry.ChestnutLeaves));
			helper.register(new ResourceLocation(CharcoalPit.MODID, "olive_sapling"), buildBlockItem(ModBlockRegistry.OliveSapling));
			helper.register(new ResourceLocation(CharcoalPit.MODID, "olive_leaves"),
					buildLeavesItem(ModBlockRegistry.OliveLeaves));
			helper.register(new ResourceLocation(CharcoalPit.MODID, "orange_sapling"), buildBlockItem(ModBlockRegistry.OrangeSapling));
			helper.register(new ResourceLocation(CharcoalPit.MODID, "orange_leaves"),
					buildLeavesItem(ModBlockRegistry.OrangeLeaves));
			helper.register(new ResourceLocation(CharcoalPit.MODID, "oil_lamp"), OilLamp);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "nest_box"), NestBox);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "bloomeryy"),
					Bloomeryy);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "charcoal_block"), CharcoalBlock);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "bloom"), Bloom);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "blast_furnace"),
					BlastFurnace);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "distillery"), Distillery);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "steam_press"), SteamPress);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "wrath_lantern"),
					WrathLantern);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "straw"), Straw);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "ash"),  Ash);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "coke"),  Coke);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "aeternalis_fuel"),
					Aeternalis);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "fertilizer"),  Fertilizer);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "fire_starter"),  FireStarter);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "creosote_bucket"),
					CreosoteBucket);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "vinegar_bucket"), VinegarBucket);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "clay_pot"), ClayPot);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "cracked_pot"), CrackedPot);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "sandy_brick_item"),
					SandyBrickItem);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "unfired_sandy_brick"), UnfireSandyBrick);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "nether_brick_item"), NetherBrickItem);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "unfired_brick"),
					UnfiredBrick);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "alcohol_bottle"), AlcoholBottle);
			//helper.register(new ResourceLocation(CharcoalPit.MODID, "vinegar_bucket"), VinegarBucket);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "vinegar_bottle"),
					VinegarBottle);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "cheese"), Cheese);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "kebabs"), Kebabs);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "leek"),
					Leek);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "farfetch_stew"), FarfetchStew);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "calamari"),
					Calamari);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "cooked_calamari"), CookedCalamri);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "cooked_egg"), CookedEgg);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "corn"),
					Corn);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "popcorn"), PopCorn);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "corn_stew"), CornStew);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "sushi"),
					Sushi);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "fugu"), Fugu);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "cherry"), Cherry);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "dragon_fruit"), DragonFruit);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "chestnut"),
					ChestNut);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "banana"), Bananana);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "coconut"), Cococonut);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "choco_powder"),
					ChocoPoweder);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "chocolate"), Chocolate);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "tricolor_dango"), Dango);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "sunflower_seeds"), SunflowerSeeds);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "mortar_pestle"),
					mortarPestle);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "flour"), Flour);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "corn_flour"), CornFlour);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "croissant"), Croissant);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "olives"),
					Olives);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "pickled_olives"), Pickled_Olives);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "orange"), Orange);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "olive_oil_bucket"), OliveOilBucket);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "walnut_oil_bucket"),  WalnutOilBucket);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "sunflower_oil_bucket"), SunflowerOilBucket);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "animal_cage"),
					AnimalCage);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "bio_diesel_bucket"), BioDieselBucket);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "lye_bottle"),
					LyeBottle);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "sour_alcohol_bottle"), SourAlcoholBottle);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "glycerine"), Glycerine);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "alloy_pig_iron"),
					AlloyPigIron);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "alloy_steel"), AlloySteel);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "tuyere"), Tuyere);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "flux"),
					Flux);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "serinan_stew"), SerinanStew);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "bunny_stew"), BunnyStew);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "alloy_orichalcum"),
					AlloyOrichalcum);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "prismarine_dust"), PrismarineDust);
			//helper.register(new ResourceLocation(CharcoalPit.MODID, "ethanol_bucket"), EthanolBucket);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "ethanol_bottle"), EthanolBottle);
			//helper.register(new ResourceLocation(CharcoalPit.MODID, "ethoxide_bucket"), EthoxideBucket);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "ethoxide_bottle"),
					EthoxideBottle);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "seed_oil_bucket"), SeedOilBucket);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "ethanol_bucket"), EthanolBucket);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "ethoxide_bucket"), EthoxideBucket);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "airplane"),
					plane);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "winglet"), winglet);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "wing"), wing);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "engine"), engine);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "nether_shard"),
					NetherShard);

			helper.register(new ResourceLocation(CharcoalPit.MODID, "ceramic_pot"), CeramicPot);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "yellow_pot"), YellowPot);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "white_pot"), WhitePot);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "red_pot"), RedPot);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "purple_pot"), PurplePot);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "pink_pot"), PinkPot);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "orange_pot"), OrangePot);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "magenta_pot"), MagentaPot);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "lime_pot"), LimePot);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "light_gray_pot"), LightGrayPot);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "light_blue_pot"), LightBluePot);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "green_pot"), GreenPot);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "gray_pot"), GrayPot);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "cyan_pot"), CyanPot);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "brown_pot"), BrownPot);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "blue_pot"), BluePot);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "black_pot"), BlackPot);

			helper.register(new ResourceLocation(CharcoalPit.MODID, "feeding_through"), FeedingThrough);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "feeding_through_birch"), FeedingThroughBirch);
			//helper.register(new ResourceLocation(CharcoalPit.MODID, "feeding_through_jungle"), FeedingThroughJungle);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "feeding_through_spruce"),  FeedingThroughSpruce);
			//helper.register(new ResourceLocation(CharcoalPit.MODID, "feeding_through_dark"), FeedingThroughDark);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "feeding_through_acacia"),  FeedingThroughAcacia);
			//helper.register(new ResourceLocation(CharcoalPit.MODID, "feeding_through_crimson"), FeedingThroughCrimson);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "feeding_through_warped"),  FeedingThroughWarped);

			helper.register(new ResourceLocation(CharcoalPit.MODID, "flint_pick"), FlintPick);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "flint_shovel"), FlintShovel);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "flint_axe"), FlintAxe);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "flint_hoe"), FlintHoe);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "flint_sword"), FlintSword);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "copper_pick"), CopperPick);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "copper_shovel"), CopperShovle);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "copper_axe"), CopperAxe);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "copper_hoe"), CopperHoe);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "copper_sword"), CopperSword);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "steel_pick"), SteelPick);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "steel_shovel"), SteelShovle);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "steel_axe"), SteelAxe);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "steel_hoe"), SteelHoe);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "steel_sword"), SteelSword);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "steel_helmet"), SteelHelmet);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "steel_chestplate"), SteelChestplate);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "steel_leggings"), SteelLeggings);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "steel_boots"), SteelBoots);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "copper_helmet"), CopperHelmet);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "copper_chestplate"), CopperChestplate);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "copper_leggings"), CopperLeggings);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "copper_boots"), CopperBoots);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "orichalcum_pick"), OrichalcumPick);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "orichalcum_shovel"), OrichalcumShovel);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "orichalcum_axe"), OrichalcumAxe);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "orichalcum_helmet"), OrichalcumHelmet);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "orichalcum_chestplate"), OrichalcumChestplate);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "orichalcum_leggings"), OrichalcumLeggings);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "orichalcum_boots"), OrichalcumBoots);
			helper.register(new ResourceLocation(CharcoalPit.MODID, "orichalcum_hoe"), OrichalcumHoe);

		});

		DispenserBlock.registerBehavior(ModItemRegistry.Bloom, new DispenserPlaceBloom());
		/*event.getRegistry().registerAll(CeramicPot.setRegistryName("ceramic_pot"),YellowPot.setRegistryName("yellow_pot"),WhitePot.setRegistryName("white_pot"),
				RedPot.setRegistryName("red_pot"),PurplePot.setRegistryName("purple_pot"),PinkPot.setRegistryName("pink_pot"),OrangePot.setRegistryName("orange_pot"),
				MagentaPot.setRegistryName("magenta_pot"),LimePot.setRegistryName("lime_pot"),LightGrayPot.setRegistryName("light_gray_pot"),
				LightBluePot.setRegistryName("light_blue_pot"),GreenPot.setRegistryName("green_pot"),GrayPot.setRegistryName("gray_pot"),CyanPot.setRegistryName("cyan_pot"),
				BrownPot.setRegistryName("brown_pot"),BluePot.setRegistryName("blue_pot"),BlackPot.setRegistryName("black_pot"));
		event.getRegistry().registerAll(FeedingThrough.setRegistryName("feeding_through"),FeedingThroughBirch.setRegistryName("feeding_through_birch"),
				FeedingThroughJungle.setRegistryName("feeding_through_jungle"), FeedingThroughSpruce.setRegistryName("feeding_through_spruce"),
				FeedingThroughDark.setRegistryName("feeding_through_dark"), FeedingThroughAcacia.setRegistryName("feeding_through_acacia"),
				FeedingThroughCrimson.setRegistryName("feeding_through_crimson"), FeedingThroughWarped.setRegistryName("feeding_through_warped"));
		event.getRegistry().registerAll(FlintPick.setRegistryName("flint_pick"),FlintShovel.setRegistryName("flint_shovel"),FlintAxe.setRegistryName("flint_axe"),
				FlintHoe.setRegistryName("flint_hoe"),FlintSword.setRegistryName("flint_sword"),CopperPick.setRegistryName("copper_pick"),
				CopperShovle.setRegistryName("copper_shovel"),CopperAxe.setRegistryName("copper_axe"),CopperHoe.setRegistryName("copper_hoe"),
				CopperSword.setRegistryName("copper_sword"),SteelPick.setRegistryName("steel_pick"),SteelShovle.setRegistryName("steel_shovel"),
				SteelAxe.setRegistryName("steel_axe"),SteelHoe.setRegistryName("steel_hoe"),SteelSword.setRegistryName("steel_sword"),
				SteelHelmet.setRegistryName("steel_helmet"),SteelChestplate.setRegistryName("steel_chestplate"),SteelLeggings.setRegistryName("steel_leggings"),
				SteelBoots.setRegistryName("steel_boots"),CopperHelmet.setRegistryName("copper_helmet"),CopperChestplate.setRegistryName("copper_chestplate"),
				CopperLeggings.setRegistryName("copper_leggings"),CopperBoots.setRegistryName("copper_boots"),
				OrichalcumPick.setRegistryName("orichalcum_pick"),OrichalcumShovel.setRegistryName("orichalcum_shovel"),OrichalcumAxe.setRegistryName("orichalcum_axe"),
				OrichalcumHelmet.setRegistryName("orichalcum_helmet"),OrichalcumChestplate.setRegistryName("orichalcum_chestplate"),
				OrichalcumLeggings.setRegistryName("orichalcum_leggings"),OrichalcumBoots.setRegistryName("orichalcum_boots"),OrichalcumHoe.setRegistryName("orichalcum_hoe"));*/
		
		
		/*DispenserBlock.registerBehavior(ModItemRegistry.CeramicPot, new DispenserPlacePot());
		DispenserBlock.registerBehavior(ModItemRegistry.BlackPot, new DispenserPlacePot());
		DispenserBlock.registerBehavior(ModItemRegistry.BluePot, new DispenserPlacePot());
		DispenserBlock.registerBehavior(ModItemRegistry.BrownPot, new DispenserPlacePot());
		DispenserBlock.registerBehavior(ModItemRegistry.CyanPot, new DispenserPlacePot());
		DispenserBlock.registerBehavior(ModItemRegistry.GrayPot, new DispenserPlacePot());
		DispenserBlock.registerBehavior(ModItemRegistry.GreenPot, new DispenserPlacePot());
		DispenserBlock.registerBehavior(ModItemRegistry.LightBluePot, new DispenserPlacePot());
		DispenserBlock.registerBehavior(ModItemRegistry.LightGrayPot, new DispenserPlacePot());
		DispenserBlock.registerBehavior(ModItemRegistry.LimePot, new DispenserPlacePot());
		DispenserBlock.registerBehavior(ModItemRegistry.MagentaPot, new DispenserPlacePot());
		DispenserBlock.registerBehavior(ModItemRegistry.OrangePot, new DispenserPlacePot());
		DispenserBlock.registerBehavior(ModItemRegistry.PinkPot, new DispenserPlacePot());
		DispenserBlock.registerBehavior(ModItemRegistry.PurplePot, new DispenserPlacePot());
		DispenserBlock.registerBehavior(ModItemRegistry.RedPot, new DispenserPlacePot());
		DispenserBlock.registerBehavior(ModItemRegistry.WhitePot, new DispenserPlacePot());
		DispenserBlock.registerBehavior(ModItemRegistry.YellowPot, new DispenserPlacePot());*/
	}

	public static BlockItemFuel buildBlockItem(Block block, int time) {
		return buildBlockItem(block, CHARCOAL_PIT, time);
	}
	
	public static BlockItemFuel buildBlockItem(Block block, CreativeModeTab group, int time) {
		return new BlockItemFuel(block, new Item.Properties().tab(group)).setBurnTime(time);
	}
	
	public static BlockItem buildBlockItem(Block block) {
		return buildBlockItem(block, CHARCOAL_PIT);
	}
	
	public static BlockItem buildBlockItemP(Block block) {
		return new BlockItem(block, new Item.Properties().tab(CHARCOAL_PIT).stacksTo(1));
	}

	public static ItemBlockLeaves buildLeavesItem(BlockFruitLeaves block) {
		return new ItemBlockLeaves(block, new Item.Properties().tab(CHARCOAL_PIT));
	}
	
	public static BlockItem buildBlockItem(Block block, CreativeModeTab group) {
		return new BlockItem(block, new Item.Properties().tab(group));
	}
	
	public static ItemFuel buildItem(CreativeModeTab group,int time) {
		return new ItemFuel(new Item.Properties().tab(group)).setBurnTime(time);
	}
	
	public static Item buildItem(CreativeModeTab group) {
		return new Item(new Item.Properties().tab(group));
	}
	
}
