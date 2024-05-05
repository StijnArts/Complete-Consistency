package drai.dev.complete_consistency.datageneration.providers.loottables;

import drai.dev.complete_consistency.helpers.*;
import net.fabricmc.fabric.api.datagen.v1.*;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.minecraft.core.*;

import java.util.concurrent.*;

public class BlockLootProvider extends FabricBlockLootTableProvider {

	public BlockLootProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
		super(dataOutput, registryLookup);
	}

	@Override
	public void generate() {
		BlockLootHelper.getBlockLoot().forEach((block, consumer)-> consumer.accept(this,block));
	}
}
//resourceLocationBuilderBiConsumer.accept(IDHelper.createID("",""),
//				FabricBlockLootTableProvider.createSingleItemTable(item));
//drops(Tutorial.TEST_BLOCK, Tutorial.TEST_ITEM, ConstantLootNumberProvider.create(9.0F)));
