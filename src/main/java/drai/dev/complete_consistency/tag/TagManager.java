package drai.dev.complete_consistency.tag;

import com.google.common.collect.*;
import drai.dev.complete_consistency.mixin.accessors.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.*;
import net.minecraft.resources.*;
import net.minecraft.server.packs.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import org.jetbrains.annotations.*;

import java.util.*;
import java.util.function.*;

public class TagManager {
    private static final Map<String, TagRegistry<?>> TYPES = Maps.newHashMap();

    public static TagRegistry.RegistryBacked<Block> BLOCKS = registerType(BuiltInRegistries.BLOCK);
    public static TagRegistry.Items ITEMS = registerItem();

    public static <T> TagRegistry.RegistryBacked<T> registerType(DefaultedRegistry<T> registry) {
        TagRegistry<T> type = new TagRegistry.RegistryBacked<>(registry);
        return (TagRegistry.RegistryBacked<T>) TYPES.computeIfAbsent(type.directory, (dir) -> type);
    }

    public static TagRegistry.Items registerItem() {
        TagRegistry.Items type = new TagRegistry.Items();
        return (TagRegistry.Items) TYPES.computeIfAbsent(type.directory, (dir) -> type);
    }

    public static <T> TagRegistry.Simple<T> registerType(Registry<T> registry, String directory) {
        return registerType(registry.key(), directory, (o) -> registry.getKey(o));
    }

    public static <T> TagRegistry.Simple<T> registerType(
            ResourceKey<? extends Registry<T>> registry,
            String directory,
            Function<T, ResourceLocation> locationProvider
    ) {
        return (TagRegistry.Simple<T>) TYPES.computeIfAbsent(
                directory,
                (dir) -> new TagRegistry.Simple<>(
                        registry,
                        dir,
                        locationProvider
                )
        );
    }

    public static <T> TagRegistry.UnTyped<T> registerType(
            ResourceKey<? extends Registry<T>> registry,
            String directory
    ) {
        return (TagRegistry.UnTyped<T>) TYPES.computeIfAbsent(
                directory,
                (dir) -> new TagRegistry.UnTyped<>(registry, dir)
        );
    }

    /**
     * Initializes basic tags. Should be called only in BCLib main class.
     */
    @ApiStatus.Internal
    public static void ensureStaticallyLoaded() {
        UVCommonItemTags.prepareTags();
        UVCommonBlockTags.prepareTags();
    }


    /**
     * Automatically called in {@link net.minecraft.tags.TagLoader#loadAndBuild(ResourceManager)}.
     * <p>
     * In most cases there is no need to call this Method manually.
     *
     * @param directory The name of the Tag-directory. Should be either <i>"tags/blocks"</i> or
     *                  <i>"tags/items"</i>.
     * @param tagsMap   The map that will hold the registered Tags
     * @return The {@code tagsMap} Parameter.
     */
    @ApiStatus.Internal
    public static <T> Map<ResourceLocation, List<TagLoader.EntryWithSource>> apply(
            String directory,
            Map<ResourceLocation, List<TagLoader.EntryWithSource>> tagsMap
    ) {
        TagRegistry<?> type = TYPES.get(directory);
        if (type != null) {
            type.apply(tagsMap);
        }

        return tagsMap;
    }


    /*public static boolean isToolWithMineableTag(ItemStack stack, TagKey<Block> tag) {
        if (stack.getItem() instanceof DiggerItemAccessor dig) {
            return dig.bclib_getBlockTag() == tag;
        }
        return false;
    }*/
}