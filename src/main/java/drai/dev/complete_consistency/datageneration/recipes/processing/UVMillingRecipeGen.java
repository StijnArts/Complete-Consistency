//package drai.dev.complete_consistency.datageneration.recipes.processing;
//
//import com.simibubi.create.*;
//import com.simibubi.create.foundation.data.recipe.*;
//import com.tterrag.registrate.util.entry.*;
//import drai.dev.complete_consistency.helpers.*;
//import net.fabricmc.fabric.api.datagen.v1.*;
//import net.minecraft.core.*;
//import net.minecraft.resources.*;
//import net.minecraft.tags.*;
//import net.minecraft.world.item.*;
//
//public class UVMillingRecipeGen extends UVProcessingRecipeGen {
//
//	@Override
//	protected void init() {
//		ProcessingRecipeHelper.getMillingRecipes().forEach(((itemLike, consumer) -> consumer.accept(this, itemLike)));
//	}
//
//	protected GeneratedRecipe metalOre(String name, ItemEntry<? extends Item> crushed, int duration) {
//		return create(name + "_ore", b -> b.duration(duration)
//				.withCondition(DefaultResourceConditions.not(DefaultResourceConditions.tagsPopulated(TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("c", name + "_ores")))))
//				.require(AllTags.forgeItemTag(name + "_ores"))
//				.output(crushed.get()));
//	}
//
//	public UVMillingRecipeGen(FabricDataGenerator p_i48262_1_) {
//		super(p_i48262_1_);
//	}
//
//	@Override
//	protected AllRecipeTypes getRecipeType() {
//		return AllRecipeTypes.MILLING;
//	}
//
//}
