package drai.dev.complete_consistency.registry;

import drai.dev.complete_consistency.helpers.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.*;
import net.minecraft.data.worldgen.features.*;
import net.minecraft.resources.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.levelgen.feature.*;

import java.util.*;

public class BlockReplacements {
//    public static final ResourceKey<ConfiguredFeature<?, ?>> REPLACEMENT_HUGE_BROWN_MUSHROOM =
//            ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation("huge_brown_mushroom_replacement"));
//    public static final ResourceKey<ConfiguredFeature<?, ?>> REPLACEMENT_HUGE_RED_MUSHROOM =
//            ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation("huge_red_mushroom_replacement"));
    public static List<Block> REPLACED_BLOCKS = new ArrayList<>();
    public static final Block MUSHROOM_STEM = registerReplacement(new ResourceLocation("mushroom_stem"),
            new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MUSHROOM_STEM)));
    public static void replaceBlocks() {

    }

    public static Block registerReplacement(ResourceLocation location, Block block) {
        var registeredBlock =  Registry.register(BuiltInRegistries.BLOCK, location, block);
//        Registry.register(BuiltInRegistries.ITEM, location,new BlockItem(registeredBlock, new Item.Properties()));
        REPLACED_BLOCKS.add(registeredBlock);
        return registeredBlock;
    }
}
