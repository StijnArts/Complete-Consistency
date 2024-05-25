package drai.dev.complete_consistency.modules.minecraft.materials.wood;

import drai.dev.complete_consistency.materials.impl.*;
import drai.dev.complete_consistency.modules.minecraft.materials.wood.enums.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.material.*;

public class Birch extends WoodMaterial {
    public Birch(String namespace, int index) {
        super(namespace, "birch", MapColor.SAND, ()->Blocks.SPRUCE_BUTTON, false, 2);
        this.addBlock(MinecraftWoodBlocks.LOG.getName(), Blocks.BIRCH_LOG);
        this.addItem(MinecraftWoodBlocks.LOG.getName(), Items.BIRCH_LOG);
        this.addBlock(MinecraftWoodBlocks.STRIPPED_LOG.getName(), Blocks.STRIPPED_BIRCH_LOG);
        this.addItem(MinecraftWoodBlocks.STRIPPED_LOG.getName(), Items.STRIPPED_BIRCH_LOG);
        this.addBlock(MinecraftWoodBlocks.WOOD.getName(), Blocks.BIRCH_WOOD);
        this.addItem(MinecraftWoodBlocks.WOOD.getName(), Items.BIRCH_WOOD);
        this.addBlock(MinecraftWoodBlocks.STRIPPED_WOOD.getName(), Blocks.STRIPPED_BIRCH_WOOD);
        this.addItem(MinecraftWoodBlocks.STRIPPED_WOOD.getName(), Items.STRIPPED_BIRCH_WOOD);
        this.addBlock(MinecraftWoodBlocks.PLANKS.getName(), Blocks.BIRCH_PLANKS);
        this.addItem(MinecraftWoodBlocks.PLANKS.getName(), Items.BIRCH_PLANKS);
        this.addBlock(MinecraftWoodBlocks.STAIRS.getName(), Blocks.BIRCH_STAIRS);
        this.addItem(MinecraftWoodBlocks.STAIRS.getName(), Items.BIRCH_STAIRS);
        this.addBlock(MinecraftWoodBlocks.SLAB.getName(), Blocks.BIRCH_SLAB);
        this.addItem(MinecraftWoodBlocks.SLAB.getName(), Items.BIRCH_SLAB);
        this.addBlock(MinecraftWoodBlocks.PRESSURE_PLATE.getName(), Blocks.BIRCH_PRESSURE_PLATE);
        this.addItem(MinecraftWoodBlocks.PRESSURE_PLATE.getName(), Items.BIRCH_PRESSURE_PLATE);
        this.addBlock(MinecraftWoodBlocks.DOOR.getName(), Blocks.BIRCH_DOOR);
        this.addItem(MinecraftWoodBlocks.DOOR.getName(), Items.BIRCH_DOOR);
        this.addBlock(MinecraftWoodBlocks.TRAPDOOR.getName(), Blocks.BIRCH_TRAPDOOR);
        this.addItem(MinecraftWoodBlocks.TRAPDOOR.getName(), Items.BIRCH_TRAPDOOR);
        this.addBlock(MinecraftWoodBlocks.FENCE.getName(), Blocks.BIRCH_FENCE);
        this.addItem(MinecraftWoodBlocks.FENCE.getName(), Items.BIRCH_FENCE);
        this.addBlock(MinecraftWoodBlocks.FENCE_GATE.getName(), Blocks.BIRCH_FENCE_GATE);
        this.addItem(MinecraftWoodBlocks.FENCE_GATE.getName(), Items.BIRCH_FENCE_GATE);
        this.addBlock(MinecraftWoodBlocks.BUTTON.getName(), Blocks.BIRCH_BUTTON);
        this.addItem(MinecraftWoodBlocks.BUTTON.getName(), Items.BIRCH_BUTTON);
        this.addItem(MinecraftWoodBlocks.SIGN_ITEM.getName(), Items.BIRCH_SIGN);
        this.addBlock(MinecraftWoodBlocks.STANDING_SIGN.getName(), Blocks.BIRCH_SIGN);
        this.addBlock(MinecraftWoodBlocks.WALL_SIGN.getName(), Blocks.BIRCH_WALL_SIGN);
        this.addItem(MinecraftWoodBlocks.HANGING_SIGN_ITEM.getName(), Items.BIRCH_HANGING_SIGN);
        this.addBlock(MinecraftWoodBlocks.HANGING_SIGN.getName(), Blocks.BIRCH_HANGING_SIGN);
        this.addBlock(MinecraftWoodBlocks.HANGING_WALL_SIGN.getName(), Blocks.BIRCH_WALL_HANGING_SIGN);
        this.addItem(MinecraftWoodBlocks.BOAT.getName(), Items.BIRCH_BOAT);
        this.addItem(MinecraftWoodBlocks.CHEST_BOAT.getName(), Items.BIRCH_CHEST_BOAT);

        this.addBlock(MinecraftWoodBlocks.FLETCHING_TABLE.getName(), Blocks.FLETCHING_TABLE);
        this.addItem(MinecraftWoodBlocks.FLETCHING_TABLE.getName(), Items.FLETCHING_TABLE);
        this.addItem(MinecraftWoodBlocks.ITEM_FRAME.getName(), Items.ITEM_FRAME);
        this.addItem(MinecraftWoodBlocks.GLOW_ITEM_FRAME.getName(), Items.GLOW_ITEM_FRAME);
    }
}
