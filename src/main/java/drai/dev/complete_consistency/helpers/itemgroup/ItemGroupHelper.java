package drai.dev.complete_consistency.helpers.itemgroup;

import drai.dev.complete_consistency.*;
import drai.dev.complete_consistency.helpers.*;
import drai.dev.complete_consistency.materials.*;
import net.fabricmc.fabric.api.itemgroup.v1.*;
import net.minecraft.client.resources.model.*;
import net.minecraft.core.registries.*;
import net.minecraft.resources.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;

import java.util.*;
import java.util.function.*;
import java.util.logging.Level;

public class ItemGroupHelper {
    private static final HashMap<ResourceKey<CreativeModeTab>, List<ItemHolder>> ITEMS_FOR_GROUPS = new HashMap<>();

    public static void addToGroup(ResourceKey<CreativeModeTab> tab, BlockMaterial material, String type, boolean isItem){
        ITEMS_FOR_GROUPS
                .compute(tab, (creativeModeTab, hashMap) -> Objects.requireNonNullElse(hashMap, new ArrayList<>()))
                .add(new ItemHolder(material, type, isItem));
    }

    public static void addToGroup(ResourceKey<CreativeModeTab> tab, BlockMaterial material, String type, boolean isItem, Supplier<ItemLike> previousItem){
        ITEMS_FOR_GROUPS
                .compute(tab, (creativeModeTab, hashMap) -> Objects.requireNonNullElse(hashMap, new ArrayList<>()))
                .add(new ItemHolder(material, type, isItem, previousItem));
    }

    public static void registerSelf() {
        ItemGroupEvents.MODIFY_ENTRIES_ALL.register((group, entries) -> {
            var groupLocation = BuiltInRegistries.CREATIVE_MODE_TAB.getResourceKey(group);
            if(groupLocation.isPresent()){
                if(ITEMS_FOR_GROUPS.containsKey(groupLocation.get())){
                    var sortedItemHolderList = ITEMS_FOR_GROUPS.get(groupLocation.get()).stream().sorted(ItemHolder.comparator()).toList();
                    for(var itemHolder : sortedItemHolderList){
                        var value = itemHolder.getHeldItem();
                        var previousItem = itemHolder.getPreviousItem();
                        if(previousItem == null){
                            entries.accept(value);
                        } else {
                            try{
                                entries.addAfter(previousItem, value);
                            } catch (NullPointerException e){
                                CompleteConsistency.LOGGER.log(Level.SEVERE,
                                        "cant find itemlike " + previousItem + "for itemlike " + value.toString());
                                entries.accept(value);
                            }
                        }
                    }
                }
            }

        });
    }
}
