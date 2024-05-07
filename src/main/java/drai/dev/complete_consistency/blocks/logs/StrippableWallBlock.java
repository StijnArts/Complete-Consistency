package drai.dev.complete_consistency.blocks.logs;

import net.minecraft.core.*;
import net.minecraft.server.level.*;
import net.minecraft.sounds.*;
import net.minecraft.util.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.phys.*;
import org.jetbrains.annotations.*;

public class StrippableWallBlock extends WallBlock {
	private final Block strippedBlock;

	public StrippableWallBlock(Block strippedBlock, Properties settings) {
		super(settings);
		this.strippedBlock = strippedBlock;
	}

	@Override
	protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
		return getStrippedBlockInteractionResult(state, level, pos, player, hand, this.strippedBlock);
	}

	@NotNull
	static ItemInteractionResult getStrippedBlockInteractionResult(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, Block strippedBlock) {
		if (!(player.getMainHandItem().getItem() instanceof AxeItem)) {
			return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
		} else {
			if (!world.isClientSide) {
				world.setBlockAndUpdate(pos, strippedBlock.withPropertiesOf(state));
				player.getMainHandItem().hurtAndBreak(1, RandomSource.create(), (ServerPlayer) player,
						() -> player.broadcastBreakEvent(LivingEntity.getSlotForHand(hand)));
			} else {
				world.playSound(player, pos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0F, 1.0F);
			}
			return ItemInteractionResult.SUCCESS;
		}
	}
}
