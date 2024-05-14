package drai.dev.complete_consistency.blockentities;

import com.google.common.collect.*;
import net.minecraft.core.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;
import org.jetbrains.annotations.*;

import java.util.*;

public class DynamicBlockEntityType<T extends BlockEntity> extends BlockEntityType<T> {

    private final Set<Block> validBlocks = Sets.newHashSet();
    private final BlockEntitySupplier<? extends T> factory;

    public DynamicBlockEntityType(BlockEntitySupplier<? extends T> supplier) {
        super(null, Collections.emptySet(), null);
        this.factory = supplier;
    }

    @Override
    @Nullable
    public T create(BlockPos blockPos, BlockState blockState) {
        return factory.create(blockPos, blockState);
    }

    @Override
    public boolean isValid(BlockState blockState) {
        return validBlocks.contains(blockState.getBlock());
    }

    public void registerBlock(Block block) {
        validBlocks.add(block);
    }

    @FunctionalInterface
    public interface BlockEntitySupplier<T extends BlockEntity> {
        T create(BlockPos blockPos, BlockState blockState);
    }
}
