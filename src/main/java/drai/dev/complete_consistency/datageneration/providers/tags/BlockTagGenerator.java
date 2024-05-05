package drai.dev.complete_consistency.datageneration.providers.tags;

import drai.dev.complete_consistency.helpers.*;
import net.fabricmc.fabric.api.datagen.v1.*;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.level.block.*;

import java.util.concurrent.*;

public class BlockTagGenerator extends FabricTagProvider {

	/**
	 * Constructs a new {@link FabricTagProvider} with the default computed path.
	 *
	 * <p>Common implementations of this class are provided.
	 *
	 * @param output           the {@link FabricDataOutput} instance
	 * @param registriesFuture the backing registry for the tag type
	 */
	public BlockTagGenerator(FabricDataOutput output, CompletableFuture registriesFuture) {
		super(output, Registries.BLOCK, registriesFuture);
	}

	@Override
	protected void addTags(HolderLookup.Provider wrapperLookup) {
		for (TagKey<Block> blockTag : TagHelper.getResourceBlockTags().keySet()) {
			for (ResourceLocation block : TagHelper.getResourceBlockTags().get(blockTag)) {
				getOrCreateTagBuilder(blockTag).add(block);
			}
		}

		for (TagKey<Block> blockTag : TagHelper.getBlockTags().keySet()) {
			for (Block block : TagHelper.getBlockTags().get(blockTag)) {
				getOrCreateTagBuilder(blockTag).add(block);
			}
		}

		for (TagKey<Block> BlockTag : TagHelper.getCompositeBlockTags().keySet()) {
			for (TagKey<Block> subTag : TagHelper.getCompositeBlockTags().get(BlockTag)) {
				getOrCreateTagBuilder(BlockTag).addOptionalTag(subTag);
			}
		}
	}
}
