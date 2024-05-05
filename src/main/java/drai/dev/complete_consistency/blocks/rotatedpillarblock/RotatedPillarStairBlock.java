package drai.dev.complete_consistency.blocks.rotatedpillarblock;

import com.mojang.datafixers.kinds.*;
import com.mojang.serialization.*;
import com.mojang.serialization.codecs.*;
import net.minecraft.core.*;
import net.minecraft.world.item.context.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.*;

public class RotatedPillarStairBlock extends StairBlock {
    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;
    public RotatedPillarStairBlock(BlockState baseState, Properties properties) {
        super(baseState, properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue((Property)FACING, (Comparable)Direction.NORTH)
                .setValue(HALF, Half.BOTTOM)
                .setValue(SHAPE, StairsShape.STRAIGHT)
                .setValue((Property)WATERLOGGED, (Comparable)Boolean.valueOf(false))
                .setValue(AXIS, Direction.Axis.Y));
    }

    @Override
    protected BlockState rotate(BlockState state, Rotation rotation) {
        return RotatedPillarBlock.rotatePillar(state, rotation).setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, HALF, SHAPE, WATERLOGGED, AXIS);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction direction = context.getClickedFace();
        BlockPos blockPos = context.getClickedPos();
        FluidState fluidState = context.getLevel().getFluidState(blockPos);
        BlockState blockState = this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection()).setValue(HALF,
                direction == Direction.DOWN || direction != Direction.UP && context.getClickLocation().y - (double)blockPos.getY() > 0.5 ? Half.TOP : Half.BOTTOM)
                .setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
        return (BlockState)blockState.setValue(SHAPE, StairBlock.getStairsShape(blockState, context.getLevel(), blockPos)).setValue(AXIS, direction.getAxis());
    }
}
