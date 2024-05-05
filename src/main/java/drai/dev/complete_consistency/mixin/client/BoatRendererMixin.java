package drai.dev.complete_consistency.mixin.client;

import com.mojang.blaze3d.vertex.*;
import drai.dev.complete_consistency.client.renderer.*;
import drai.dev.complete_consistency.items.boat.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.world.entity.vehicle.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;


@Mixin(BoatRenderer.class)
public abstract class BoatRendererMixin extends EntityRenderer<Boat> {
    protected BoatRendererMixin(EntityRendererProvider.Context context) {
        super(context);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void bcl_init(EntityRendererProvider.Context context, boolean bl, CallbackInfo ci) {
        BoatTypeOverride.values().forEach(type -> type.createBoatModels(context));
    }

    @Inject(method = "render(Lnet/minecraft/world/entity/vehicle/Boat;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At("HEAD"), cancellable = true)
    void bcl_render(
            Boat boat,
            float f, float g,
            PoseStack poseStack, MultiBufferSource multiBufferSource,
            int i,
            CallbackInfo ci
    ) {
        if (CustomBoatRenderer.render(boat, f, g, poseStack, multiBufferSource, i)) {
            super.render(boat, f, g, poseStack, multiBufferSource, i);
            ci.cancel();
        }
    }
}

