package drai.dev.complete_consistency.mixin.registration;

import drai.dev.complete_consistency.interfaces.*;
import drai.dev.complete_consistency.items.boat.*;
import drai.dev.complete_consistency.modules.minecraft.*;
import net.minecraft.client.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(Blocks.class)
public class BlockRegistrationMixin {
    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void bcl_getDropItem(CallbackInfo ci) {

//        CombinationRegistry.getSourceMod("betterend").setInitialized();
//        CombinationRegistry.registerCombinationBlocks();
    }
}
