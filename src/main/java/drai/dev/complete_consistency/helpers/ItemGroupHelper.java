package drai.dev.complete_consistency.helpers;

import com.google.common.collect.*;
import net.fabricmc.fabric.api.itemgroup.v1.*;
import net.minecraft.resources.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import oshi.util.tuples.*;

import java.util.*;
import java.util.function.*;

public class ItemGroupHelper {
    private static final HashMap<CreativeModeTab, HashMap<ItemLike, Supplier<ItemLike>>> recipes = LinkedHashMultimap.create();

    public static void addToGroup(int number, ItemLike item, Consumer<ItemLike> consumer){
        recipes.put(number, new Pair<>(item, consumer));
    }

    public static void registerSelf() {
        for (int i = 0; i < recipes.size(); i++) {
            var contents = recipes.get(i);
            for(var content : contents){
                content.getB().accept(content.getA());
            }
        }

    }
    //TODO make this a hashmap that iterates through its contents per creative mode tab
    public static Consumer<ItemLike> createAddConsumer(ResourceKey<CreativeModeTab> buildingBlocks) {
        return (ItemLike item)->ItemGroupEvents.modifyEntriesEvent(buildingBlocks).register(consumer -> consumer.accept(item));
    }

    public static Consumer<ItemLike> createAfterConsumer(ResourceKey<CreativeModeTab> buildingBlocks, Supplier<ItemLike> block) {
        return (ItemLike item)->ItemGroupEvents.modifyEntriesEvent(buildingBlocks).register(consumer -> consumer.addAfter(block.get(), item));
    }
}
