package drai.dev.complete_consistency.helpers;

import drai.dev.complete_consistency.helpers.languages.*;
import net.minecraft.core.registries.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;

import java.util.*;

public class TagKeyHelper {

	public static TagKey<Block> createBlockTagKey(ResourceLocation id){
		var returnTag = TagKey.create(Registries.BLOCK, id);
		createBlockTagLanguageEntry(id);
		return returnTag;
	}

	public static TagKey<Block> createCommonBlockTagKey(String id){
		var resourceLocation = new ResourceLocation("c:"+id);
        return createBlockTagKey(resourceLocation);
	}

	public static TagKey<Item> createCommonItemTagKey(String id){
		var resourceLocation = new ResourceLocation("c:"+id);
        return createItemTagKey(resourceLocation);
	}

	public static TagKey<Block> createBlockTagKeyWithCompositeTags(ResourceLocation id, List<TagKey<Block>> blockTags){
		TagKey<Block> returnTag = TagKey.create(Registries.BLOCK, id);
		createBlockTagLanguageEntry(id);
		blockTags.forEach(tag->TagHelper.addBlockTags(tag,List.of(returnTag)));
		return returnTag;
	}

	public static TagKey<Block> createBlockTagKeyWithBlocks(ResourceLocation id, List<ResourceLocation> blocks){
		TagKey<Block> returnTag = TagKey.create(Registries.BLOCK, id);
		createBlockTagLanguageEntry(id);
		blocks.forEach(block->TagHelper.addBlockTags(block,List.of(returnTag)));
		return returnTag;
	}

	public static TagKey<Block> createBlockTagKeyWithCompositeTagsAndBlocks(ResourceLocation id, List<TagKey<Block>> blockTags, List<ResourceLocation> blocks){
		TagKey<Block> returnTag = TagKey.create(Registries.BLOCK, id);
		createBlockTagLanguageEntry(id);
		blockTags.forEach(tag->TagHelper.addBlockTags(tag,List.of(returnTag)));
		blocks.forEach(block->TagHelper.addBlockTags(block,List.of(returnTag)));
		return returnTag;
	}

	public static TagKey<Item> createItemTagKey(ResourceLocation id){
		var returnTag = TagKey.create(Registries.ITEM, id);
		createItemTagLanguageEntry(id);
		return returnTag;
	}

	public static TagKey<Item> createItemTagKeyWithCompositeTags(ResourceLocation id, List<TagKey<Item>> itemTags){
		TagKey<Item> returnTag = TagKey.create(Registries.ITEM, id);
		createItemTagLanguageEntry(id);
		itemTags.forEach(tag->TagHelper.addItemTags(tag,List.of(returnTag)));
		return returnTag;
	}

	public static TagKey<Item> createItemTagKeyWithCompositeTagsResource(ResourceLocation id, List<ResourceLocation> itemTags){
        return createItemTagKeyWithItems(id, itemTags);
	}

	public static TagKey<Item> createItemTagKeyWithItems(ResourceLocation id, List<ResourceLocation> items){
		TagKey<Item> returnTag = TagKey.create(Registries.ITEM, id);
		createItemTagLanguageEntry(id);
		items.forEach(item->TagHelper.addItemTags(item,List.of(returnTag)));
		return returnTag;
	}

	public static TagKey<Item> createItemTagKeyWithCompositeTagsAndItems(ResourceLocation id, List<TagKey<Item>> itemTags, List<ResourceLocation> items){
		TagKey<Item> returnTag = TagKey.create(Registries.ITEM, id);
		createItemTagLanguageEntry(id);
		itemTags.forEach(tag->TagHelper.addItemTags(tag,List.of(returnTag)));
		items.forEach(item->TagHelper.addItemTags(item,List.of(returnTag)));
		return returnTag;
	}

	private static void createItemTagLanguageEntry(ResourceLocation id) {
		String tagLangFileName = "";
		for (String segment : id.getPath().split("_")) {
			tagLangFileName = tagLangFileName +" "+ StringUtil.capitalizeWord(segment);
		}
		LanguageHelper.getEnglishTranslations().addTagTranslation("tag.item."+id.toLanguageKey(), tagLangFileName);
	}

	private static void createBlockTagLanguageEntry(ResourceLocation id) {
		String tagLangFileName = "";
		for (String segment : id.getPath().split("_")) {
			tagLangFileName = tagLangFileName +" "+ StringUtil.capitalizeWord(segment);
		}
		LanguageHelper.getEnglishTranslations().addTagTranslation("tag.block."+id.toLanguageKey(), tagLangFileName);
	}
}
