package drai.dev.complete_consistency.blocks.logs;

import drai.dev.complete_consistency.blocks.rotatedpillarblock.*;
import net.minecraft.core.*;
import net.minecraft.server.level.*;
import net.minecraft.sounds.*;
import net.minecraft.util.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.phys.*;

import static drai.dev.complete_consistency.blocks.logs.StrippableWallBlock.getStrippedBlockInteractionResult;

public class StrippableStairsBlock extends StairBlock {
	private Block strippedBlock;

	public StrippableStairsBlock(Block strippedBlock, Properties settings) {
		super(Blocks.OAK_LOG.defaultBlockState(), settings);
		this.strippedBlock = strippedBlock;
	}

	@Override
	protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
		return getStrippedBlockInteractionResult(state, level, pos, player, hand, this.strippedBlock);
	}
}
