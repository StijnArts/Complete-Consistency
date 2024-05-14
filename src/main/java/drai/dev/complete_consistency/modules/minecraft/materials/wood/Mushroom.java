package drai.dev.complete_consistency.modules.minecraft.materials.wood;

import drai.dev.complete_consistency.materials.impl.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.material.*;

public class Mushroom extends WoodMaterial {
    public Mushroom(String namespace, int index) {
        super(namespace, "mushroom", MapColor.SAND, ()-> Blocks.BAMBOO_BUTTON, true, index);
    }
}
