package drai.dev.complete_consistency.datageneration.providers.tags;

import drai.dev.complete_consistency.helpers.*;
import net.fabricmc.fabric.api.datagen.v1.*;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;

import java.util.concurrent.*;

public class ItemTagGenerator extends FabricTagProvider{

	/**
	 * Constructs a new {@link FabricTagProvider} with the default computed path.
	 *
	 * <p>Common implementations of this class are provided.
	 *
	 * @param output           the {@link FabricDataOutput} instance
	 * @param registriesFuture the backing registry for the tag type
	 */
	public ItemTagGenerator(FabricDataOutput output, CompletableFuture registriesFuture) {
		super(output, Registries.ITEM, registriesFuture);
	}

	@Override
	protected void addTags(HolderLookup.Provider wrapperLookup) {
		for (TagKey<Item> itemTag : TagHelper.getResourceItemTags().keySet()) {
			for (ResourceLocation item : TagHelper.getResourceItemTags().get(itemTag)) {
				getOrCreateTagBuilder(itemTag).add(item);
			}
		}

		for (TagKey<Item> itemTag : TagHelper.getItemTags().keySet()) {
			for (Item item : TagHelper.getItemTags().get(itemTag)) {
				getOrCreateTagBuilder(itemTag).add(item);
			}
		}

		for (TagKey<Item> itemTag : TagHelper.getCompositeItemTags().keySet()) {
			for (TagKey<Item> subTag : TagHelper.getCompositeItemTags().get(itemTag)) {
				getOrCreateTagBuilder(itemTag).addOptionalTag(subTag);
			}
		}
	}
}
