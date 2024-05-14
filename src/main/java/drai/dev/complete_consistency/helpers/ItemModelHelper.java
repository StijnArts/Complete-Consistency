package drai.dev.complete_consistency.helpers;

import com.google.common.collect.*;
import net.minecraft.data.models.*;
import net.minecraft.world.item.*;

import java.util.function.*;

public class ItemModelHelper {
	private static final LinkedHashMultimap<Item, BiConsumer<ItemModelGenerators, Item>> itemModels = LinkedHashMultimap.create();

	public static void addItemModel(Item item, BiConsumer<ItemModelGenerators, Item> consumer){
		itemModels.put(item, consumer);
	}

	public static LinkedHashMultimap<Item, BiConsumer<ItemModelGenerators, Item>> getItemModels() {
		return itemModels;
	}
}
