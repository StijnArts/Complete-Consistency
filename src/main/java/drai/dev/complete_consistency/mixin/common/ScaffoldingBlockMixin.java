package drai.dev.complete_consistency.mixin.common;

import drai.dev.complete_consistency.interfaces.*;
import net.minecraft.core.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

import java.util.*;

import static net.minecraft.world.level.block.ScaffoldingBlock.DISTANCE;

@Mixin(ScaffoldingBlock.class)
public class ScaffoldingBlockMixin {
    @Inject(method = "getDistance", at = @At("TAIL"), cancellable = true)
    private static void bcl_suse(BlockGetter level, BlockPos pos, CallbackInfoReturnable<Integer> cir) {
        Direction direction;
        BlockState blockState2;
        BlockPos.MutableBlockPos mutableBlockPos = pos.mutable().move(Direction.DOWN);
        BlockState blockState = level.getBlockState(mutableBlockPos);
        int i = 7;
        if (blockState.getBlock() instanceof ScaffoldingBlock) {
            i = blockState.getValue(DISTANCE);
        } else if (blockState.isFaceSturdy(level, mutableBlockPos, Direction.UP)) {
            cir.setReturnValue(0);
        }
        Iterator<Direction> iterator = Direction.Plane.HORIZONTAL.iterator();
        while (iterator.hasNext() && (!((blockState2 = level.getBlockState(mutableBlockPos.setWithOffset((Vec3i)pos,
                direction = iterator.next()))).getBlock() instanceof ScaffoldingBlock) || (i = Math.min(i, blockState2.getValue(DISTANCE) + 1)) != 1)) {
        }
        cir.setReturnValue(i);
    }
}
