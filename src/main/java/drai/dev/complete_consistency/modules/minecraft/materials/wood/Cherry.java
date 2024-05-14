package drai.dev.complete_consistency.modules.minecraft.materials.wood;

import drai.dev.complete_consistency.materials.impl.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.material.*;

public class Cherry extends WoodMaterial {
    public Cherry(String namespace, int index) {
        super(namespace, "cherry", MapColor.TERRACOTTA_WHITE, ()->Blocks.MANGROVE_BUTTON, false, 7);
        this.addBlock(MinecraftWoodBlocks.LOG.getName(), Blocks.CHERRY_LOG);
        this.addItem(MinecraftWoodBlocks.LOG.getName(), Items.CHERRY_LOG);
        this.addBlock(MinecraftWoodBlocks.STRIPPED_LOG.getName(), Blocks.STRIPPED_CHERRY_LOG);
        this.addItem(MinecraftWoodBlocks.STRIPPED_LOG.getName(), Items.STRIPPED_CHERRY_LOG);
        this.addBlock(MinecraftWoodBlocks.WOOD.getName(), Blocks.CHERRY_WOOD);
        this.addItem(MinecraftWoodBlocks.WOOD.getName(), Items.CHERRY_WOOD);
        this.addBlock(MinecraftWoodBlocks.STRIPPED_WOOD.getName(), Blocks.STRIPPED_CHERRY_WOOD);
        this.addItem(MinecraftWoodBlocks.STRIPPED_WOOD.getName(), Items.STRIPPED_CHERRY_WOOD);
        this.addBlock(MinecraftWoodBlocks.PLANKS.getName(), Blocks.CHERRY_PLANKS);
        this.addItem(MinecraftWoodBlocks.PLANKS.getName(), Items.CHERRY_PLANKS);
        this.addBlock(MinecraftWoodBlocks.STAIRS.getName(), Blocks.CHERRY_STAIRS);
        this.addItem(MinecraftWoodBlocks.STAIRS.getName(), Items.CHERRY_STAIRS);
        this.addBlock(MinecraftWoodBlocks.SLAB.getName(), Blocks.CHERRY_SLAB);
        this.addItem(MinecraftWoodBlocks.SLAB.getName(), Items.CHERRY_SLAB);
        this.addBlock(MinecraftWoodBlocks.PRESSURE_PLATE.getName(), Blocks.CHERRY_PRESSURE_PLATE);
        this.addItem(MinecraftWoodBlocks.PRESSURE_PLATE.getName(), Items.CHERRY_PRESSURE_PLATE);
        this.addBlock(MinecraftWoodBlocks.DOOR.getName(), Blocks.CHERRY_DOOR);
        this.addItem(MinecraftWoodBlocks.DOOR.getName(), Items.CHERRY_DOOR);
        this.addBlock(MinecraftWoodBlocks.TRAPDOOR.getName(), Blocks.CHERRY_TRAPDOOR);
        this.addItem(MinecraftWoodBlocks.TRAPDOOR.getName(), Items.CHERRY_TRAPDOOR);
        this.addBlock(MinecraftWoodBlocks.FENCE.getName(), Blocks.CHERRY_FENCE);
        this.addItem(MinecraftWoodBlocks.FENCE.getName(), Items.CHERRY_FENCE);
        this.addBlock(MinecraftWoodBlocks.FENCE_GATE.getName(), Blocks.CHERRY_FENCE_GATE);
        this.addItem(MinecraftWoodBlocks.FENCE_GATE.getName(), Items.CHERRY_FENCE_GATE);
        this.addBlock(MinecraftWoodBlocks.BUTTON.getName(), Blocks.CHERRY_BUTTON);
        this.addItem(MinecraftWoodBlocks.BUTTON.getName(), Items.CHERRY_BUTTON);
        this.addItem(MinecraftWoodBlocks.SIGN_ITEM.getName(), Items.CHERRY_SIGN);
        this.addBlock(MinecraftWoodBlocks.STANDING_SIGN.getName(), Blocks.CHERRY_SIGN);
        this.addBlock(MinecraftWoodBlocks.WALL_SIGN.getName(), Blocks.CHERRY_WALL_SIGN);
        this.addItem(MinecraftWoodBlocks.HANGING_SIGN_ITEM.getName(), Items.CHERRY_HANGING_SIGN);
        this.addBlock(MinecraftWoodBlocks.HANGING_SIGN.getName(), Blocks.CHERRY_HANGING_SIGN);
        this.addBlock(MinecraftWoodBlocks.HANGING_WALL_SIGN.getName(), Blocks.CHERRY_WALL_HANGING_SIGN);
        this.addItem(MinecraftWoodBlocks.BOAT.getName(), Items.CHERRY_BOAT);
        this.addItem(MinecraftWoodBlocks.CHEST_BOAT.getName(), Items.CHERRY_CHEST_BOAT);
    }
}
