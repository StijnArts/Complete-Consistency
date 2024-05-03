package drai.dev.complete_consistency.client.renderer;

import com.mojang.blaze3d.vertex.*;
import com.mojang.math.*;
import drai.dev.complete_consistency.interfaces.*;
import drai.dev.complete_consistency.items.boat.*;
import net.fabricmc.api.*;
import net.minecraft.client.model.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.resources.*;
import net.minecraft.util.*;
import net.minecraft.world.entity.vehicle.*;
import org.joml.*;

import java.lang.*;
import java.lang.Math;

@Environment(value = EnvType.CLIENT)
public class BoatRenderer {

    public static boolean render(
            Boat boat,
            float f,
            float g,
            PoseStack poseStack,
            MultiBufferSource multiBufferSource,
            int i
    ) {
        if (boat instanceof CustomBoatTypeOverride cbto) {
            BoatTypeOverride type = cbto.bcl_getCustomType();
            if (type != null) {
                boolean hasChest = boat instanceof ChestBoat;
                float k;
                poseStack.pushPose();
                poseStack.translate(0.0, 0.375, 0.0);
                poseStack.mulPose(Axis.YP.rotationDegrees(180.0f - f));
                float h = (float) boat.getHurtTime() - g;
                float j = boat.getDamage() - g;
                if (j < 0.0f) {
                    j = 0.0f;
                }
                if (h > 0.0f) {
                    poseStack.mulPose(Axis.XP.rotationDegrees(Mth.sin(h) * h * j / 10.0f * (float) boat.getHurtDir()));
                }
                if (!Mth.equal(k = boat.getBubbleAngle(g), 0.0f)) {
                    poseStack.mulPose(new Quaternionf().setAngleAxis(
                            boat.getBubbleAngle(g) * ((float) Math.PI / 180),
                            1.0f, 0.0f, 1.0f
                    ));
                }
                ResourceLocation resourceLocation = hasChest ? type.chestBoatTexture : type.boatTexture;
                ListModel<Boat> boatModel = type.getBoatModel(hasChest);
                poseStack.scale(-1.0f, -1.0f, 1.0f);
                poseStack.mulPose(Axis.YP.rotationDegrees(90.0f));
                boatModel.setupAnim(boat, g, 0.0f, -0.1f, 0.0f, 0.0f);
                VertexConsumer vertexConsumer = multiBufferSource.getBuffer(boatModel.renderType(resourceLocation));
                boatModel.renderToBuffer(
                        poseStack, vertexConsumer, i,
                        OverlayTexture.NO_OVERLAY,
                        1.0f, 1.0f, 1.0f, 1.0f
                );
                if (!boat.isUnderWater()) {
                    VertexConsumer vertexConsumer2 = multiBufferSource.getBuffer(RenderType.waterMask());
                    if (boatModel instanceof WaterPatchModel waterPatchModel) {
                        waterPatchModel.waterPatch().render(poseStack, vertexConsumer2, i, OverlayTexture.NO_OVERLAY);
                    }
                }
                poseStack.popPose();

                return true;
            }

        }
        return false;
    }
}
