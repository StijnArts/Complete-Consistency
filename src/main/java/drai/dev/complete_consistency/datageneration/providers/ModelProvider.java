package drai.dev.complete_consistency.datageneration.providers;

import drai.dev.complete_consistency.helpers.*;
import net.fabricmc.fabric.api.datagen.v1.*;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.minecraft.data.models.*;

public class ModelProvider extends FabricModelProvider{
	public ModelProvider(FabricDataOutput dataGenerator) {
		super(dataGenerator);
	}

	@Override
	public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
		BlockStateHelper.getBlockModels().forEach((itemLike, consumer) -> consumer.accept(blockStateModelGenerator, itemLike));

	}

	@Override
	public void generateItemModels(ItemModelGenerators itemModelGenerator) {
		ItemModelHelper.getItemModels().forEach(((item, consumer) -> consumer.accept(itemModelGenerator,item)));
	}
}
