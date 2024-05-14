package drai.dev.complete_consistency.blockentities;

import drai.dev.complete_consistency.registry.*;
import net.minecraft.core.*;
import net.minecraft.network.chat.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;

public class BaseFurnaceBlockEntity extends AbstractFurnaceBlockEntity {
    public BaseFurnaceBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(BaseBlockEntities.FURNACE, blockPos, blockState, RecipeType.SMELTING);
    }

    protected Component getDefaultName() {
        return Component.translatable("container.furnace");
    }

    protected AbstractContainerMenu createMenu(int syncId, Inventory playerInventory) {
        return new FurnaceMenu(syncId, playerInventory, this, this.dataAccess);
    }
}
