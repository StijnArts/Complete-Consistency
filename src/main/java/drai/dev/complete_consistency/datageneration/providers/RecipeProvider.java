package drai.dev.complete_consistency.datageneration.providers;

import drai.dev.complete_consistency.helpers.*;
import net.fabricmc.fabric.api.datagen.v1.*;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.minecraft.core.*;
import net.minecraft.data.recipes.*;

import java.util.concurrent.*;
import java.util.function.*;

public class RecipeProvider extends FabricRecipeProvider {

	public RecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	public void buildRecipes(RecipeOutput exporter) {
		RecipeHelper.getRecipes().forEach((itemLike, consumer) -> consumer.accept(exporter,itemLike));
	}
}
