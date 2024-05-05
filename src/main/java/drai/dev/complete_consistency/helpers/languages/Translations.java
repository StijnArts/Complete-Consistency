package drai.dev.complete_consistency.helpers.languages;

import com.mojang.datafixers.util.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;

import java.util.*;

public class Translations {
	ArrayList<Pair<Block,String>> blockTranslations = new ArrayList<>();
	ArrayList<Pair<Item,String>> itemTranslations = new ArrayList<>();
	public ArrayList<Pair<Block, String>> getBlockTranslations() {
		return blockTranslations;
	}

	public ArrayList<Pair<Item, String>> getItemTranslations() {
		return itemTranslations;
	}

	public void addTranslation(Block block, String translation){
		blockTranslations.add(Pair.of(block,translation));
	}

	public void addTranslation(Item item, String translation){
		itemTranslations.add(Pair.of(item,translation));
	}
}
