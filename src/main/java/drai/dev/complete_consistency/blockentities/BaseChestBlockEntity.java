package drai.dev.complete_consistency.blockentities;

import drai.dev.complete_consistency.registry.*;
import net.minecraft.core.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;

public class BaseChestBlockEntity extends ChestBlockEntity {
    public BaseChestBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(BaseBlockEntities.CHEST, blockPos, blockState);
    }
}
