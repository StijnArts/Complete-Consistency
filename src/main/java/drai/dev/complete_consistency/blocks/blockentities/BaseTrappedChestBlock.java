package drai.dev.complete_consistency.blocks.blockentities;

import drai.dev.complete_consistency.registry.*;
import net.minecraft.core.*;
import net.minecraft.resources.*;
import net.minecraft.stats.*;
import net.minecraft.util.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;

public class BaseTrappedChestBlock extends ChestBlock {

    public BaseTrappedChestBlock(Block source) {
        super(Properties.ofFullCopy(source).noOcclusion(), () -> BaseBlockEntities.TRAPPED_CHEST);
        BaseBlockEntities.TRAPPED_CHEST.registerBlock(this);
    }

    @Override
    protected Stat<ResourceLocation> getOpenChestStat() {
        return Stats.CUSTOM.get(Stats.TRIGGER_TRAPPED_CHEST);
    }

    @Override
    protected boolean isSignalSource(BlockState state) {
        return true;
    }

    @Override
    protected int getSignal(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return Mth.clamp(ChestBlockEntity.getOpenCount(level, pos), 0, 15);
    }

    @Override
    protected int getDirectSignal(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        if (direction == Direction.UP) {
            return state.getSignal(level, pos, direction);
        }
        return 0;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return BaseBlockEntities.TRAPPED_CHEST.create(blockPos, blockState);
    }
}
