package drai.dev.complete_consistency.registry;

import drai.dev.complete_consistency.*;
import drai.dev.complete_consistency.blockentities.*;
import drai.dev.complete_consistency.blocks.blockentities.*;
import net.fabricmc.fabric.api.object.builder.v1.entity.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.*;
import net.minecraft.resources.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.*;

public class BaseBlockEntities {
    public static final DynamicBlockEntityType<BaseChestBlockEntity> CHEST = registerBlockEntityType(new ResourceLocation(CompleteConsistency.MOD_ID,
            "chest"), BaseChestBlockEntity::new);
    public static final DynamicBlockEntityType<BaseTrappedChestBlockEntity> TRAPPED_CHEST = registerBlockEntityType(new ResourceLocation(CompleteConsistency.MOD_ID,
            "trapped_chest"), BaseTrappedChestBlockEntity::new);
    public static final DynamicBlockEntityType<BaseBarrelBlockEntity> BARREL = registerBlockEntityType(new ResourceLocation(CompleteConsistency.MOD_ID,
            "barrel"), BaseBarrelBlockEntity::new);

    public static final DynamicBlockEntityType<BaseFurnaceBlockEntity> FURNACE = registerBlockEntityType(new ResourceLocation(CompleteConsistency.MOD_ID,
            "furnace"), BaseFurnaceBlockEntity::new);

    public static <T extends Entity> EntityType<T> registerEntity(
            ResourceLocation id,
            EntityType<T> entity
    ) {
        Registry.register(BuiltInRegistries.ENTITY_TYPE, id, entity);
        return entity;
    }

    public static <T extends BlockEntity> DynamicBlockEntityType<T> registerBlockEntityType(
            ResourceLocation typeId,
            DynamicBlockEntityType.BlockEntitySupplier<? extends T> supplier
    ) {
        return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, typeId, new DynamicBlockEntityType<>(supplier));
    }

    public static void register() {
    }

    public static Block[] getChests() {
        return BuiltInRegistries.BLOCK
                .stream()
                .filter(block -> block instanceof BaseChestBlock)
                .toArray(Block[]::new);
    }

    public static Block[] getBarrels() {
        return BuiltInRegistries.BLOCK
                .stream()
                .filter(block -> block instanceof BaseBarrelBlock)
                .toArray(Block[]::new);
    }

    public static Block[] getFurnaces() {
        return BuiltInRegistries.BLOCK
                .stream()
                .filter(block -> block instanceof BaseFurnaceBlock)
                .toArray(Block[]::new);
    }
}
