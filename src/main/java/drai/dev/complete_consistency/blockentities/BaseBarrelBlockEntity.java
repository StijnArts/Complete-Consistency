package drai.dev.complete_consistency.blockentities;

import drai.dev.complete_consistency.registry.*;
import net.minecraft.core.*;
import net.minecraft.nbt.*;
import net.minecraft.network.chat.*;
import net.minecraft.sounds.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;

public class BaseBarrelBlockEntity extends RandomizableContainerBlockEntity {
    private NonNullList<ItemStack> inventory;
    private ContainerOpenersCounter openersCounter = new ContainerOpenersCounter() {

        @Override
        protected void onOpen(Level level, BlockPos blockPos, BlockState blockState) {
            BaseBarrelBlockEntity.this.playSound(blockState, SoundEvents.BARREL_OPEN);
            BaseBarrelBlockEntity.this.updateBlockState(blockState, true);
        }

        @Override
        protected void onClose(Level level, BlockPos blockPos, BlockState blockState) {
            BaseBarrelBlockEntity.this.playSound(blockState, SoundEvents.BARREL_CLOSE);
            BaseBarrelBlockEntity.this.updateBlockState(blockState, false);
        }

        @Override
        protected void openerCountChanged(Level level, BlockPos blockPos, BlockState blockState, int i, int j) {
        }

        @Override
        protected boolean isOwnContainer(Player player) {
            if (player.containerMenu instanceof ChestMenu) {
                Container container = ((ChestMenu) player.containerMenu).getContainer();
                return container == BaseBarrelBlockEntity.this;
            }
            return false;
        }
    };

    private BaseBarrelBlockEntity(BlockEntityType<?> type, BlockPos blockPos, BlockState blockState) {
        super(type, blockPos, blockState);
        this.inventory = NonNullList.withSize(27, ItemStack.EMPTY);
    }

    public BaseBarrelBlockEntity(BlockPos blockPos, BlockState blockState) {
        this(BaseBlockEntities.BARREL, blockPos, blockState);
    }

    @Override
    public int getContainerSize() {
        return 27;
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.inventory;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> list) {
        this.inventory = list;
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.barrel");
    }

    @Override
    protected AbstractContainerMenu createMenu(int syncId, Inventory playerInventory) {
        return ChestMenu.threeRows(syncId, playerInventory, this);
    }

    public void startOpen(Player player) {
        if (!this.remove && !player.isSpectator()) {
            this.openersCounter.incrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
//        if (!player.isSpectator()) {
//            if (viewerCount < 0) {
//                viewerCount = 0;
//            }
//
//            ++viewerCount;
//            BlockState blockState = this.getBlockState();
//            if (!blockState.getValue(BarrelBlock.OPEN)) {
//                playSound(blockState, SoundEvents.BARREL_OPEN);
//                updateBlockState(blockState, true);
//            }
//
//            if (level != null) {
//                scheduleUpdate();
//            }
//        }
    }

    private void scheduleUpdate() {
        level.scheduleTick(getBlockPos(), getBlockState().getBlock(), 5);
    }

    public void tick() {
//        if (level != null) {
//            viewerCount = ChestBlockEntity.getOpenCount(level, worldPosition);
//            if (viewerCount > 0) {
//                scheduleUpdate();
//            } else {
//                BlockState blockState = getBlockState();
//                if (!(blockState.getBlock() instanceof BaseBarrelBlock)) {
//                    setRemoved();
//                    return;
//                }
//                if (blockState.getValue(BarrelBlock.OPEN)) {
//                    playSound(blockState, SoundEvents.BARREL_CLOSE);
//                    updateBlockState(blockState, false);
//                }
//            }
//        }
    }

    @Override
    public void stopOpen(Player player) {
        if (!this.remove && !player.isSpectator()) {
            this.openersCounter.decrementOpeners(player, this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    public void recheckOpen() {
        if (!this.remove) {
            this.openersCounter.recheckOpeners(this.getLevel(), this.getBlockPos(), this.getBlockState());
        }
    }

    private void updateBlockState(BlockState state, boolean open) {
        if (level != null) {
            level.setBlock(this.getBlockPos(), state.setValue(BarrelBlock.OPEN, open), 3);
        }
    }

    private void playSound(BlockState blockState, SoundEvent soundEvent) {
        if (level != null) {
            Vec3i facingDir = blockState.getValue(BarrelBlock.FACING).getNormal();
            double x = this.worldPosition.getX() + 0.5D + facingDir.getX() / 2.0D;
            double y = this.worldPosition.getY() + 0.5D + facingDir.getY() / 2.0D;
            double z = this.worldPosition.getZ() + 0.5D + facingDir.getZ() / 2.0D;
            level.playSound(
                    null, x, y, z,
                    soundEvent, SoundSource.BLOCKS, 0.5F, this.level.random.nextFloat() * 0.1F + 0.9F
            );
        }
    }
}