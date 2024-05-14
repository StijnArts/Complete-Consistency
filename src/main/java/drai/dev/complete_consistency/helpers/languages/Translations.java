package drai.dev.complete_consistency.helpers.languages;

import com.mojang.datafixers.util.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;

import java.util.*;

public class Translations {
	ArrayList<Pair<Block,String>> blockTranslations = new ArrayList<>();
	ArrayList<Pair<Item,String>> itemTranslations = new ArrayList<>();
	Map<String,String>  tagTranslations = new HashMap<>();
	Map<ItemLike,String> replacementTranslations = new HashMap<>();
	public ArrayList<Pair<Block, String>> getBlockTranslations() {
		return blockTranslations;
	}

	public ArrayList<Pair<Item, String>> getItemTranslations() {
		return itemTranslations;
	}

	public void addTranslation(Block block, String translation){
		blockTranslations.add(Pair.of(block,translation.trim()));
	}

	public void addTranslation(Item item, String translation){
		itemTranslations.add(Pair.of(item,translation.trim()));
	}

	public void addTagTranslation(String translationKey, String translation) {
		tagTranslations.put(translationKey, translation.trim());
	}

	public void addReplacementTranslations(Item item, String newTranslation) {
		replacementTranslations.put(item, newTranslation.trim());
	}

	public Map<ItemLike,String> getReplacementTranslations() {
		return replacementTranslations;
	}

	public Map<String,String> getTagTranslations() {
		return tagTranslations;
	}
}
