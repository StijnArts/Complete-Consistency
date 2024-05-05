package drai.dev.complete_consistency.items.boat;

import drai.dev.complete_consistency.interfaces.*;
import net.minecraft.world.entity.vehicle.*;
import net.minecraft.world.item.*;

public class BaseBoatItem extends BoatItem implements CustomBoatTypeOverride {
    BoatTypeOverride customType;

    public BaseBoatItem(boolean bl, BoatTypeOverride type, Item.Properties properties) {
        super(bl, Boat.Type.OAK, properties);
        this.bcl_setCustomType(type);
    }

    public void bcl_setCustomType(BoatTypeOverride type) {
        this.customType = type;
    }

    public BoatTypeOverride bcl_getCustomType() {
        return this.customType;
    }
}
