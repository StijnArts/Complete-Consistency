package drai.dev.complete_consistency.modules.minecraft.materials.wood;

import drai.dev.complete_consistency.materials.*;
import drai.dev.complete_consistency.registry.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.material.*;

public class Mushroom extends WoodMaterial {
    public Mushroom(String namespace) {
        super(namespace, "mushroom", MapColor.SAND, ()-> Blocks.WARPED_BUTTON, true);
//        this.addBlock(MinecraftWoodBlocks.LOG.getName(), BlockReplacements.MUSHROOM_STEM);
    }
}
