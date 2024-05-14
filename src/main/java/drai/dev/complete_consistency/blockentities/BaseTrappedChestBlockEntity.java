package drai.dev.complete_consistency.blockentities;

import drai.dev.complete_consistency.registry.*;
import net.minecraft.core.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;

public class BaseTrappedChestBlockEntity extends ChestBlockEntity {
    public BaseTrappedChestBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(BaseBlockEntities.TRAPPED_CHEST, blockPos, blockState);
    }

    @Override
    protected void signalOpenCount(Level level, BlockPos pos, BlockState state, int eventId, int eventParam) {
        super.signalOpenCount(level, pos, state, eventId, eventParam);
        if (eventId != eventParam) {
            Block block = state.getBlock();
            level.updateNeighborsAt(pos, block);
            level.updateNeighborsAt(pos.below(), block);
        }
    }
}
