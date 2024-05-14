package drai.dev.complete_consistency.registry;

import drai.dev.complete_consistency.client.renderer.*;
import drai.dev.complete_consistency.items.boat.*;
import net.fabricmc.api.*;
import net.fabricmc.fabric.api.client.rendering.v1.*;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.blockentity.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.world.entity.*;

@Environment(EnvType.CLIENT)
public class BaseBlockEntityRenders {
    public static void register() {
        BlockEntityRenderers.register(BaseBlockEntities.CHEST, BaseChestBlockEntityRenderer::new);
        BlockEntityRenderers.register(BaseBlockEntities.TRAPPED_CHEST, BaseTrappedChestBlockEntityRenderer::new);

        LayerDefinition boatModel = BoatModel.createBodyModel();
        LayerDefinition chestBoatModel = ChestBoatModel.createBodyModel();
        LayerDefinition raftModel = RaftModel.createBodyModel();
        LayerDefinition chestRaftModel = ChestRaftModel.createBodyModel();

        BoatTypeOverride.values().forEach(type -> {
            EntityModelLayerRegistry.registerModelLayer(type.boatModelName, () -> type.isRaft ? raftModel : boatModel);
            EntityModelLayerRegistry.registerModelLayer(
                    type.chestBoatModelName,
                    () -> type.isRaft ? chestRaftModel : chestBoatModel
            );
        });
    }
}
