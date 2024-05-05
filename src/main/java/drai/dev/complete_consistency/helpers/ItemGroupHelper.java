package drai.dev.complete_consistency.helpers;

import com.google.common.collect.*;
import net.fabricmc.fabric.api.itemgroup.v1.*;
import net.minecraft.resources.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;

import java.util.function.*;

public class ItemGroupHelper {
    private static LinkedHashMultimap<ItemLike, Consumer<ItemLike>> recipes = LinkedHashMultimap.create();

    public static void addToGroup(ItemLike tab, Consumer<ItemLike> consumer){
        recipes.put(tab, consumer);
    }

    public static void registerSelf() {
        recipes.forEach((itemLike, fabricItemGroupEntriesResourceKeyBiConsumer) -> fabricItemGroupEntriesResourceKeyBiConsumer.accept(itemLike));
    }

    public static Consumer<ItemLike> createAddConsumer(ResourceKey<CreativeModeTab> buildingBlocks) {
        return (ItemLike item)->ItemGroupEvents.modifyEntriesEvent(buildingBlocks).register(consumer -> consumer.accept(item));
    }

    public static Consumer<ItemLike> createAfterConsumer(ResourceKey<CreativeModeTab> buildingBlocks, Supplier<ItemLike> block) {
        return (ItemLike item)->ItemGroupEvents.modifyEntriesEvent(buildingBlocks).register(consumer -> consumer.addAfter(block.get(), item));
    }
}
