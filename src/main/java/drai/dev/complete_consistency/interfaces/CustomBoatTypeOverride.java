package drai.dev.complete_consistency.interfaces;

import net.fabricmc.api.*;
import net.minecraft.client.renderer.block.model.*;
import net.minecraft.resources.*;

public interface ItemModelProvider {
    @Environment(EnvType.CLIENT)
    default BlockModel getItemModel(ResourceLocation resourceLocation) {
        return ModelsHelper.createItemModel(resourceLocation);
    }
}
