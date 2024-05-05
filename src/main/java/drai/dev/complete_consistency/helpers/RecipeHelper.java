package drai.dev.complete_consistency.helpers;

import com.google.common.collect.*;
import net.minecraft.data.recipes.*;
import net.minecraft.world.level.*;

import java.util.function.*;

public class RecipeHelper {
	private static LinkedHashMultimap<ItemLike, BiConsumer<RecipeOutput, ItemLike>> recipes = LinkedHashMultimap.create();

	public static void addRecipe(ItemLike itemLike, BiConsumer<RecipeOutput, ItemLike> recipeConsumer){
		recipes.put(itemLike, recipeConsumer);
	}

	public static LinkedHashMultimap<ItemLike, BiConsumer<RecipeOutput, ItemLike>> getRecipes() {
		return recipes;
	}
}
