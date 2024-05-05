package drai.dev.complete_consistency.datageneration.providers.tags;

import drai.dev.complete_consistency.helpers.*;
import net.fabricmc.fabric.api.datagen.v1.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.*;
import net.minecraft.data.*;
import net.minecraft.data.tags.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.entity.ai.village.poi.*;

import java.util.concurrent.*;

public class PoiTypeTagsProvider extends TagsProvider<PoiType> {
	public PoiTypeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(output, Registries.POINT_OF_INTEREST_TYPE, lookupProvider);
	}


//	@Override
//	protected void generateTags() {
//		for (TagKey<PoiType> poiTypeTag : TagHelper.getResourcePoiTypeTags().keySet()) {
//			for (ResourceLocation block : TagHelper.getResourcePoiTypeTags().get(poiTypeTag)) {
//				getOrCreateTagBuilder(poiTypeTag).add(block);
//			}
//		}
//
//		for (TagKey<PoiType> poiTypeTag : TagHelper.getPoiTypeTags().keySet()) {
//			for (Block block : TagHelper.getPoiTypeTags().get(poiTypeTag)) {
//				getOrCreateTagBuilder(poiTypeTag).add(block);
//			}
//		}
//
//		for (TagKey<PoiType> poiTypeTag : TagHelper.getCompositePoiTypeTags().keySet()) {
//			for (TagKey<PoiType> subTag : TagHelper.getCompositePoiTypeTags().get(poiTypeTag)) {
//				getOrCreateTagBuilder(poiTypeTag).addOptionalTag(subTag);
//			}
//		}
//	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		for (TagKey<PoiType> poiTypeTag : TagHelper.getPoiTypeTags().keySet()) {
			for (var poiType : TagHelper.getPoiTypeTags().get(poiTypeTag)) {
				this.tag(poiTypeTag).add(poiType);
			}
		}
	}
}
