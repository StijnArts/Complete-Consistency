package drai.dev.complete_consistency.modules.minecraft.materials.wood;

import drai.dev.complete_consistency.materials.*;
import net.minecraft.world.level.block.*;

public class BrownMushroom extends WoodMaterial {
    public BrownMushroom(String namespace) {
        super(namespace, "brown_mushroom", ()-> Blocks.CHERRY_BUTTON, true);
        this.addBlock();
    }
}
