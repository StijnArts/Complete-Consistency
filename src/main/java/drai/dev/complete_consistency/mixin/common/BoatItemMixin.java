package drai.dev.complete_consistency.mixin.common;

import drai.dev.complete_consistency.interfaces.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(BoatItem.class)
public class BoatItemMixin {
    @ModifyArg(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;noCollision(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/phys/AABB;)Z"))
    Entity bcl_suse(Entity boat) {
        if (this instanceof CustomBoatTypeOverride self) {
            if (boat instanceof CustomBoatTypeOverride newBoat) {
                newBoat.bcl_setCustomType(self.bcl_getCustomType());
            }
        }
        return boat;
    }
}
