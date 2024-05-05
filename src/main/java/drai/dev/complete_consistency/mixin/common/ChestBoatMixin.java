package drai.dev.complete_consistency.mixin.common;

import drai.dev.complete_consistency.interfaces.*;
import drai.dev.complete_consistency.items.boat.*;
import net.minecraft.world.entity.vehicle.*;
import net.minecraft.world.item.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(ChestBoat.class)
public abstract class ChestBoatMixin {

    @Inject(method = "getDropItem", at = @At("HEAD"), cancellable = true)
    void bcl_getDropItem(CallbackInfoReturnable<Item> cir) {
        if (this instanceof CustomBoatTypeOverride cbto) {
            BoatTypeOverride type = cbto.bcl_getCustomType();
            if (type != null) {
                BoatItem boat = type.getChestBoatItem();
                if (boat != null) {
                    cir.setReturnValue(boat);
                }
            }
        }
    }
}
