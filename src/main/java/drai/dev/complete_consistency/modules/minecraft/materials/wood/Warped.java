package drai.dev.complete_consistency.modules.minecraft.materials.wood;

import drai.dev.complete_consistency.materials.impl.*;
import drai.dev.complete_consistency.modules.minecraft.materials.wood.enums.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.material.*;

public class Warped extends WoodMaterial {
    public Warped(String namespace, int index) {
        super(namespace, "warped", MapColor.WARPED_STEM, ()->Blocks.CRIMSON_BUTTON, true, index);
        this.addBlock(MinecraftWoodBlocks.LOG.getName(), Blocks.WARPED_STEM);
        this.addItem(MinecraftWoodBlocks.LOG.getName(), Items.WARPED_STEM);
        this.addBlock(MinecraftWoodBlocks.STRIPPED_LOG.getName(), Blocks.STRIPPED_WARPED_STEM);
        this.addItem(MinecraftWoodBlocks.STRIPPED_LOG.getName(), Items.STRIPPED_WARPED_STEM);
        this.addBlock(MinecraftWoodBlocks.WOOD.getName(), Blocks.WARPED_HYPHAE);
        this.addItem(MinecraftWoodBlocks.WOOD.getName(), Items.WARPED_HYPHAE);
        this.addBlock(MinecraftWoodBlocks.STRIPPED_WOOD.getName(), Blocks.STRIPPED_WARPED_HYPHAE);
        this.addItem(MinecraftWoodBlocks.STRIPPED_WOOD.getName(), Items.STRIPPED_WARPED_HYPHAE);
        this.addBlock(MinecraftWoodBlocks.PLANKS.getName(), Blocks.WARPED_PLANKS);
        this.addItem(MinecraftWoodBlocks.PLANKS.getName(), Items.WARPED_PLANKS);
        this.addBlock(MinecraftWoodBlocks.STAIRS.getName(), Blocks.WARPED_STAIRS);
        this.addItem(MinecraftWoodBlocks.STAIRS.getName(), Items.WARPED_STAIRS);
        this.addBlock(MinecraftWoodBlocks.SLAB.getName(), Blocks.WARPED_SLAB);
        this.addItem(MinecraftWoodBlocks.SLAB.getName(), Items.WARPED_SLAB);
        this.addBlock(MinecraftWoodBlocks.PRESSURE_PLATE.getName(), Blocks.WARPED_PRESSURE_PLATE);
        this.addItem(MinecraftWoodBlocks.PRESSURE_PLATE.getName(), Items.WARPED_PRESSURE_PLATE);
        this.addBlock(MinecraftWoodBlocks.DOOR.getName(), Blocks.WARPED_DOOR);
        this.addItem(MinecraftWoodBlocks.DOOR.getName(), Items.WARPED_DOOR);
        this.addBlock(MinecraftWoodBlocks.TRAPDOOR.getName(), Blocks.WARPED_TRAPDOOR);
        this.addItem(MinecraftWoodBlocks.TRAPDOOR.getName(), Items.WARPED_TRAPDOOR);
        this.addBlock(MinecraftWoodBlocks.FENCE.getName(), Blocks.WARPED_FENCE);
        this.addItem(MinecraftWoodBlocks.FENCE.getName(), Items.WARPED_FENCE);
        this.addBlock(MinecraftWoodBlocks.FENCE_GATE.getName(), Blocks.WARPED_FENCE_GATE);
        this.addItem(MinecraftWoodBlocks.FENCE_GATE.getName(), Items.WARPED_FENCE_GATE);
        this.addBlock(MinecraftWoodBlocks.BUTTON.getName(), Blocks.WARPED_BUTTON);
        this.addItem(MinecraftWoodBlocks.BUTTON.getName(), Items.WARPED_BUTTON);
        this.addItem(MinecraftWoodBlocks.SIGN_ITEM.getName(), Items.WARPED_SIGN);
        this.addBlock(MinecraftWoodBlocks.STANDING_SIGN.getName(), Blocks.WARPED_SIGN);
        this.addBlock(MinecraftWoodBlocks.WALL_SIGN.getName(), Blocks.WARPED_WALL_SIGN);
        this.addItem(MinecraftWoodBlocks.HANGING_SIGN_ITEM.getName(), Items.WARPED_HANGING_SIGN);
        this.addBlock(MinecraftWoodBlocks.HANGING_SIGN.getName(), Blocks.WARPED_HANGING_SIGN);
        this.addBlock(MinecraftWoodBlocks.HANGING_WALL_SIGN.getName(), Blocks.WARPED_WALL_HANGING_SIGN);
    }
}
