package drai.dev.complete_consistency.mixin.common;

import net.minecraft.core.*;
import net.minecraft.core.registries.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

import static net.minecraft.world.level.block.HugeMushroomBlock.*;

@Mixin(HugeMushroomBlock.class)
public class BlocksMixin {
    /*@Unique
    private static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;
    @Inject(method = "createBlockStateDefinition", at = @At("HEAD"), cancellable = true)
    private void reset(StateDefinition.Builder<Block, BlockState> builder, CallbackInfo ci){
        builder.add(UP, DOWN, NORTH, EAST, SOUTH, WEST, AXIS);
        ci.cancel();
    }*/
}
