package drai.dev.complete_consistency.modules.minecraft.materials.wood;

import drai.dev.complete_consistency.materials.impl.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.material.*;

public class Jungle extends WoodMaterial {
    public Jungle(String namespace, int index) {
        super(namespace, "jungle", MapColor.DIRT, ()->Blocks.BIRCH_BUTTON, false,3);
        this.addBlock(MinecraftWoodBlocks.LOG.getName(), Blocks.JUNGLE_LOG);
        this.addItem(MinecraftWoodBlocks.LOG.getName(), Items.JUNGLE_LOG);
        this.addBlock(MinecraftWoodBlocks.STRIPPED_LOG.getName(), Blocks.STRIPPED_JUNGLE_LOG);
        this.addItem(MinecraftWoodBlocks.STRIPPED_LOG.getName(), Items.STRIPPED_JUNGLE_LOG);
        this.addBlock(MinecraftWoodBlocks.WOOD.getName(), Blocks.JUNGLE_WOOD);
        this.addItem(MinecraftWoodBlocks.WOOD.getName(), Items.JUNGLE_WOOD);
        this.addBlock(MinecraftWoodBlocks.STRIPPED_WOOD.getName(), Blocks.STRIPPED_JUNGLE_WOOD);
        this.addItem(MinecraftWoodBlocks.STRIPPED_WOOD.getName(), Items.STRIPPED_JUNGLE_WOOD);
        this.addBlock(MinecraftWoodBlocks.PLANKS.getName(), Blocks.JUNGLE_PLANKS);
        this.addItem(MinecraftWoodBlocks.PLANKS.getName(), Items.JUNGLE_PLANKS);
        this.addBlock(MinecraftWoodBlocks.STAIRS.getName(), Blocks.JUNGLE_STAIRS);
        this.addItem(MinecraftWoodBlocks.STAIRS.getName(), Items.JUNGLE_STAIRS);
        this.addBlock(MinecraftWoodBlocks.SLAB.getName(), Blocks.JUNGLE_SLAB);
        this.addItem(MinecraftWoodBlocks.SLAB.getName(), Items.JUNGLE_SLAB);
        this.addBlock(MinecraftWoodBlocks.PRESSURE_PLATE.getName(), Blocks.JUNGLE_PRESSURE_PLATE);
        this.addItem(MinecraftWoodBlocks.PRESSURE_PLATE.getName(), Items.JUNGLE_PRESSURE_PLATE);
        this.addBlock(MinecraftWoodBlocks.DOOR.getName(), Blocks.JUNGLE_DOOR);
        this.addItem(MinecraftWoodBlocks.DOOR.getName(), Items.JUNGLE_DOOR);
        this.addBlock(MinecraftWoodBlocks.TRAPDOOR.getName(), Blocks.JUNGLE_TRAPDOOR);
        this.addItem(MinecraftWoodBlocks.TRAPDOOR.getName(), Items.JUNGLE_TRAPDOOR);
        this.addBlock(MinecraftWoodBlocks.FENCE.getName(), Blocks.JUNGLE_FENCE);
        this.addItem(MinecraftWoodBlocks.FENCE.getName(), Items.JUNGLE_FENCE);
        this.addBlock(MinecraftWoodBlocks.FENCE_GATE.getName(), Blocks.JUNGLE_FENCE_GATE);
        this.addItem(MinecraftWoodBlocks.FENCE_GATE.getName(), Items.JUNGLE_FENCE_GATE);
        this.addBlock(MinecraftWoodBlocks.BUTTON.getName(), Blocks.JUNGLE_BUTTON);
        this.addItem(MinecraftWoodBlocks.BUTTON.getName(), Items.JUNGLE_BUTTON);
        this.addItem(MinecraftWoodBlocks.SIGN_ITEM.getName(), Items.JUNGLE_SIGN);
        this.addBlock(MinecraftWoodBlocks.STANDING_SIGN.getName(), Blocks.JUNGLE_SIGN);
        this.addBlock(MinecraftWoodBlocks.WALL_SIGN.getName(), Blocks.JUNGLE_WALL_SIGN);
        this.addItem(MinecraftWoodBlocks.HANGING_SIGN_ITEM.getName(), Items.JUNGLE_HANGING_SIGN);
        this.addBlock(MinecraftWoodBlocks.HANGING_SIGN.getName(), Blocks.JUNGLE_HANGING_SIGN);
        this.addBlock(MinecraftWoodBlocks.HANGING_WALL_SIGN.getName(), Blocks.JUNGLE_WALL_HANGING_SIGN);
        this.addItem(MinecraftWoodBlocks.BOAT.getName(), Items.JUNGLE_BOAT);
        this.addItem(MinecraftWoodBlocks.CHEST_BOAT.getName(), Items.JUNGLE_CHEST_BOAT);
    }
}
