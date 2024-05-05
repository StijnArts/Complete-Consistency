package drai.dev.complete_consistency.helpers.item;

import drai.dev.complete_consistency.*;
import drai.dev.complete_consistency.helpers.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.*;
import net.minecraft.data.models.*;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;

import java.util.*;
import java.util.function.*;

public class ItemHandler {

	public static Item registerItem(String name, String translation, Item item,
									BiConsumer<ItemModelGenerators, Item> itemModelGeneratorsConsumer,
									List<TagKey<Item>> itemTags){
		Item returnItem = Registry.register(BuiltInRegistries.ITEM, CompleteConsistency.MOD_ID +":"+name,item);
		LanguageHelper.getEnglishTranslations().addTranslation(returnItem,translation);
		ItemModelHelper.addItemModel(returnItem,itemModelGeneratorsConsumer);
		TagHelper.addItemTags(returnItem, itemTags);
		return returnItem;
	}

	public static Item registerItemWithRecipe(String name, String translation, Item item,
											  BiConsumer<ItemModelGenerators, Item> itemModelGeneratorsConsumer,
											  BiConsumer<RecipeOutput, ItemLike> recipeConsumer,
											  List<TagKey<Item>> itemTags){
		Item returnItem = Registry.register(BuiltInRegistries.ITEM, CompleteConsistency.MOD_ID +":"+name,item);
		LanguageHelper.getEnglishTranslations().addTranslation(returnItem,translation);
		ItemModelHelper.addItemModel(returnItem,itemModelGeneratorsConsumer);
		RecipeHelper.addRecipe(returnItem,recipeConsumer);
		TagHelper.addItemTags(returnItem, itemTags);
		return returnItem;
	}

	public static Item registerItemWithRecipeWithBlockState(String name, String translation, Item item,
															BiConsumer<ItemModelGenerators, Item> itemModelGeneratorsConsumer,
															BiConsumer<BlockModelGenerators, ItemLike> blockModelGeneratorsConsumer,
															BiConsumer<RecipeOutput, ItemLike> recipeConsumer,
															List<TagKey<Item>> itemTags){
		Item returnItem = Registry.register(BuiltInRegistries.ITEM, CompleteConsistency.MOD_ID +":"+name,item);
		LanguageHelper.getEnglishTranslations().addTranslation(returnItem,translation);
		ItemModelHelper.addItemModel(returnItem,itemModelGeneratorsConsumer);
		BlockStateHelper.addBlockModel(returnItem, blockModelGeneratorsConsumer);
		RecipeHelper.addRecipe(returnItem,recipeConsumer);
		TagHelper.addItemTags(returnItem, itemTags);
		return returnItem;
	}
}
