package charcoalPit.loot;

import charcoalPit.CharcoalPit;
import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.GsonHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.List;

public class KernalsFromGrass extends LootModifier {
	public Item item;
	
	public KernalsFromGrass(LootItemCondition[] conditionsIn,Item item) {
		super(conditionsIn);
		this.item=item;
	}
	
	@Nonnull
	@Override
	protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
		/*if(context.hasParam(LootContextParams.BLOCK_STATE)) {
			if (context.getParamOrNull(LootContextParams.BLOCK_STATE).is(BlockTags.getAllTags().getTag(new ResourceLocation(CharcoalPit.MODID, "straw_grass"))) ||
					context.getParamOrNull(LootContextParams.BLOCK_STATE).is(BlockTags.getAllTags().getTag(new ResourceLocation(CharcoalPit.MODID, "straw_grass_tall"))))
				generatedLoot.add(new ItemStack(item));
		}*/
		return generatedLoot;
	}

	@Override
	public Codec<? extends IGlobalLootModifier> codec() {
		return null;
	}

	/*public static class Serializer extends GlobalLootModifierSerializer<KernalsFromGrass>{
		
		@Override
		public KernalsFromGrass read(ResourceLocation location, JsonObject object, LootItemCondition[] ailootcondition) {
			Item straw= ForgeRegistries.ITEMS.getValue(new ResourceLocation(GsonHelper.getAsString(object, "item")));
			return new KernalsFromGrass(ailootcondition, straw);
		}
		
		@Override
		public JsonObject write(KernalsFromGrass instance) {
			return null;
		}
	}*/
	
}
