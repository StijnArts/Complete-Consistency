package drai.dev.complete_consistency.interfaces;

import drai.dev.complete_consistency.items.boat.*;
import net.fabricmc.api.*;
import net.minecraft.client.renderer.block.model.*;
import net.minecraft.resources.*;

public interface CustomBoatTypeOverride {
    void bcl_setCustomType(BoatTypeOverride var1);

    BoatTypeOverride bcl_getCustomType();
}
