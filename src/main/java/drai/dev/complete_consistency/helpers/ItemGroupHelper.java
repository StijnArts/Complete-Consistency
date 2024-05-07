package drai.dev.complete_consistency.helpers;

import com.google.common.collect.*;
import net.fabricmc.fabric.api.itemgroup.v1.*;
import net.minecraft.core.registries.*;
import net.minecraft.resources.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import oshi.util.tuples.*;

import java.util.*;
import java.util.function.*;

public class ItemGroupHelper {
    private static final HashMap<ResourceKey<CreativeModeTab>, HashMap<ItemLike, Supplier<ItemLike>>> ITEMS_FOR_GROUPS = new HashMap<>();

    public static void addToGroup(ResourceKey<CreativeModeTab> tab, ItemLike item, Supplier<ItemLike> itemToBePutAfter){
        ITEMS_FOR_GROUPS
                .compute(tab, (creativeModeTab, hashMap) -> Objects.requireNonNullElse(hashMap, new HashMap<>()))
                .put(item, itemToBePutAfter);
    }

    public static void registerSelf() {
        ItemGroupEvents.MODIFY_ENTRIES_ALL.register((group, entries) -> {
            var groupLocation = BuiltInRegistries.CREATIVE_MODE_TAB.getResourceKey(group);
            if(groupLocation.isPresent()){
                if(ITEMS_FOR_GROUPS.containsKey(groupLocation.get())){
                    for(var itemPair : ITEMS_FOR_GROUPS.get(groupLocation.get()).entrySet()){
                        if(itemPair.getValue() == null){
                            entries.accept(itemPair.getKey());
                        } else {
                            entries.addAfter(itemPair.getValue().get(), itemPair.getKey());
                        }
                    }
                }
            }

        });
    }
    //TODO make this a hashmap that iterates through its contents per creative mode tab
}
