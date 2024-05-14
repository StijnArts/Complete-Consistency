package drai.dev.complete_consistency.blocks.blockentities;

import drai.dev.complete_consistency.*;
import drai.dev.complete_consistency.client.renderer.*;
import drai.dev.complete_consistency.interfaces.*;
import drai.dev.complete_consistency.registry.*;
import net.fabricmc.api.*;
import net.minecraft.client.renderer.block.model.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.*;
import net.minecraft.resources.*;
import net.minecraft.stats.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;
import org.jetbrains.annotations.*;

import java.util.*;

public class BaseChestBlock extends ChestBlock {

    public BaseChestBlock(Block source) {
        //TODO fix slow walking speed on chests
        super(Properties.ofFullCopy(source).noOcclusion(), () -> BaseBlockEntities.CHEST);
        BaseBlockEntities.CHEST.registerBlock(this);
    }

    @Override
    protected @NotNull Stat<ResourceLocation> getOpenChestStat() {
        return Stats.CUSTOM.get(Stats.TRIGGER_TRAPPED_CHEST);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return BaseBlockEntities.CHEST.create(blockPos, blockState);
    }
}