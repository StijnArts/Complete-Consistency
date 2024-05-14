package drai.dev.complete_consistency.model;

import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.*;
import net.minecraft.client.renderer.entity.*;

public class BoatModelHolder {
    private BoatModel boatModel;
    private BoatModel chestBoatModel;

    public BoatModelHolder(EntityRendererProvider.Context context,
                           ModelLayerLocation boatModelName,
                           ModelLayerLocation chestBoatModelName) {
            this.boatModel = new BoatModel(context.bakeLayer(boatModelName));
            this.chestBoatModel = new ChestBoatModel(context.bakeLayer(chestBoatModelName));
    }

    public BoatModel getBoatModel(boolean chest) {
        return chest ? this.chestBoatModel : this.boatModel;
    }
}
