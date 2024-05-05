package drai.dev.complete_consistency.datageneration;

import drai.dev.complete_consistency.datageneration.providers.*;
import drai.dev.complete_consistency.datageneration.providers.language.*;
import drai.dev.complete_consistency.datageneration.providers.loottables.*;
import drai.dev.complete_consistency.datageneration.providers.tags.*;
import drai.dev.complete_consistency.datageneration.recipes.processing.*;
import net.fabricmc.fabric.api.datagen.v1.*;
import net.minecraft.data.*;

public class DataGeneration implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		TextureProvider.run();
		var pack = fabricDataGenerator.createPack();
		pack.addProvider(EnglishLanguageProvider::new);
		pack.addProvider(ModelProvider::new);
		pack.addProvider(ItemTagGenerator::new);
		pack.addProvider(BlockTagGenerator::new);
		pack.addProvider(PoiTypeTagsProvider::new);
		pack.addProvider(BlockLootProvider::new);
		pack.addProvider(RecipeProvider::new);
//		UVProcessingRecipeGen.registerAll(fabricDataGenerator);
	}
}
