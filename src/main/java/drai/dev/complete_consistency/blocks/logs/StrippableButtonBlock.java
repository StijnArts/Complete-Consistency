package drai.dev.complete_consistency.blocks.logs;

import net.minecraft.core.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.*;
import net.minecraft.world.phys.*;

public class StrippableButtonBlock extends ButtonBlock {
    private final Block strippedBlock;

    public StrippableButtonBlock(Block strippedBlock, BlockSetType type) {
        super(type, 30, BlockBehaviour.Properties.of().noCollission().strength(0.5f).pushReaction(PushReaction.DESTROY));
        this.strippedBlock = strippedBlock;
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        return StrippableWallBlock.getStrippedBlockInteractionResult(state, level, pos, player, hand, this.strippedBlock);
    }
}
