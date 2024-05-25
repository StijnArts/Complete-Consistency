package drai.dev.complete_consistency.modules.minecraft.materials.wood;

import drai.dev.complete_consistency.materials.impl.*;
import drai.dev.complete_consistency.modules.minecraft.materials.wood.enums.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.material.*;

public class Acacia extends WoodMaterial {
    public Acacia(String namespace, int index) {
        super(namespace, "acacia", MapColor.COLOR_ORANGE, ()->Blocks.JUNGLE_BUTTON, false, 4);
        this.addBlock(MinecraftWoodBlocks.LOG.getName(), Blocks.ACACIA_LOG);
        this.addItem(MinecraftWoodBlocks.LOG.getName(), Items.ACACIA_LOG);
        this.addBlock(MinecraftWoodBlocks.STRIPPED_LOG.getName(), Blocks.STRIPPED_ACACIA_LOG);
        this.addItem(MinecraftWoodBlocks.STRIPPED_LOG.getName(), Items.STRIPPED_ACACIA_LOG);
        this.addBlock(MinecraftWoodBlocks.WOOD.getName(), Blocks.ACACIA_WOOD);
        this.addItem(MinecraftWoodBlocks.WOOD.getName(), Items.ACACIA_WOOD);
        this.addBlock(MinecraftWoodBlocks.STRIPPED_WOOD.getName(), Blocks.STRIPPED_ACACIA_WOOD);
        this.addItem(MinecraftWoodBlocks.STRIPPED_WOOD.getName(), Items.STRIPPED_ACACIA_WOOD);
        this.addBlock(MinecraftWoodBlocks.PLANKS.getName(), Blocks.ACACIA_PLANKS);
        this.addItem(MinecraftWoodBlocks.PLANKS.getName(), Items.ACACIA_PLANKS);
        this.addBlock(MinecraftWoodBlocks.STAIRS.getName(), Blocks.ACACIA_STAIRS);
        this.addItem(MinecraftWoodBlocks.STAIRS.getName(), Items.ACACIA_STAIRS);
        this.addBlock(MinecraftWoodBlocks.SLAB.getName(), Blocks.ACACIA_SLAB);
        this.addItem(MinecraftWoodBlocks.SLAB.getName(), Items.ACACIA_SLAB);
        this.addBlock(MinecraftWoodBlocks.PRESSURE_PLATE.getName(), Blocks.ACACIA_PRESSURE_PLATE);
        this.addItem(MinecraftWoodBlocks.PRESSURE_PLATE.getName(), Items.ACACIA_PRESSURE_PLATE);
        this.addBlock(MinecraftWoodBlocks.DOOR.getName(), Blocks.ACACIA_DOOR);
        this.addItem(MinecraftWoodBlocks.DOOR.getName(), Items.ACACIA_DOOR);
        this.addBlock(MinecraftWoodBlocks.TRAPDOOR.getName(), Blocks.ACACIA_TRAPDOOR);
        this.addItem(MinecraftWoodBlocks.TRAPDOOR.getName(), Items.ACACIA_TRAPDOOR);
        this.addBlock(MinecraftWoodBlocks.FENCE.getName(), Blocks.ACACIA_FENCE);
        this.addItem(MinecraftWoodBlocks.FENCE.getName(), Items.ACACIA_FENCE);
        this.addBlock(MinecraftWoodBlocks.FENCE_GATE.getName(), Blocks.ACACIA_FENCE_GATE);
        this.addItem(MinecraftWoodBlocks.FENCE_GATE.getName(), Items.ACACIA_FENCE_GATE);
        this.addBlock(MinecraftWoodBlocks.BUTTON.getName(), Blocks.ACACIA_BUTTON);
        this.addItem(MinecraftWoodBlocks.BUTTON.getName(), Items.ACACIA_BUTTON);
        this.addItem(MinecraftWoodBlocks.SIGN_ITEM.getName(), Items.ACACIA_SIGN);
        this.addBlock(MinecraftWoodBlocks.STANDING_SIGN.getName(), Blocks.ACACIA_SIGN);
        this.addBlock(MinecraftWoodBlocks.WALL_SIGN.getName(), Blocks.ACACIA_WALL_SIGN);
        this.addItem(MinecraftWoodBlocks.HANGING_SIGN_ITEM.getName(), Items.ACACIA_HANGING_SIGN);
        this.addBlock(MinecraftWoodBlocks.HANGING_SIGN.getName(), Blocks.ACACIA_HANGING_SIGN);
        this.addBlock(MinecraftWoodBlocks.HANGING_WALL_SIGN.getName(), Blocks.ACACIA_WALL_HANGING_SIGN);
        this.addItem(MinecraftWoodBlocks.BOAT.getName(), Items.ACACIA_BOAT);
        this.addItem(MinecraftWoodBlocks.CHEST_BOAT.getName(), Items.ACACIA_CHEST_BOAT);
    }
}
