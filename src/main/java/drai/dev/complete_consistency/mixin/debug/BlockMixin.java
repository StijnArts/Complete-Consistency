package drai.dev.complete_consistency.mixin.debug;

import drai.dev.complete_consistency.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

import java.util.logging.*;

@Mixin(Block.class)
public class BlockMixin {

    @Inject(method = "<init>", at = @At("TAIL"))
    private void reset(BlockBehaviour.Properties properties, CallbackInfo ci){
        if(CompleteConsistency.STARTED_GENERATION) System.out.println("Created a block");
        else System.out.println("Created a block but outside of the generation for complete consistency");
    }
}
