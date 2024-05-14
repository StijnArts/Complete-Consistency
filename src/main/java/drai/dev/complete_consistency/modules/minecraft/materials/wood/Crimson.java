package drai.dev.complete_consistency.modules.minecraft.materials.wood;

import drai.dev.complete_consistency.materials.impl.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.material.*;

public class Crimson extends WoodMaterial {
    public Crimson(String namespace, int index) {
        super(namespace, "crimson", MapColor.CRIMSON_STEM, ()->Blocks.BAMBOO_BUTTON, true, 9);
        this.addBlock(MinecraftWoodBlocks.LOG.getName(), Blocks.CRIMSON_STEM);
        this.addItem(MinecraftWoodBlocks.LOG.getName(), Items.CRIMSON_STEM);
        this.addBlock(MinecraftWoodBlocks.STRIPPED_LOG.getName(), Blocks.STRIPPED_CRIMSON_STEM);
        this.addItem(MinecraftWoodBlocks.STRIPPED_LOG.getName(), Items.STRIPPED_CRIMSON_STEM);
        this.addBlock(MinecraftWoodBlocks.WOOD.getName(), Blocks.CRIMSON_HYPHAE);
        this.addItem(MinecraftWoodBlocks.WOOD.getName(), Items.CRIMSON_HYPHAE);
        this.addBlock(MinecraftWoodBlocks.STRIPPED_WOOD.getName(), Blocks.STRIPPED_CRIMSON_HYPHAE);
        this.addItem(MinecraftWoodBlocks.STRIPPED_WOOD.getName(), Items.STRIPPED_CRIMSON_HYPHAE);
        this.addBlock(MinecraftWoodBlocks.PLANKS.getName(), Blocks.CRIMSON_PLANKS);
        this.addItem(MinecraftWoodBlocks.PLANKS.getName(), Items.CRIMSON_PLANKS);
        this.addBlock(MinecraftWoodBlocks.STAIRS.getName(), Blocks.CRIMSON_STAIRS);
        this.addItem(MinecraftWoodBlocks.STAIRS.getName(), Items.CRIMSON_STAIRS);
        this.addBlock(MinecraftWoodBlocks.SLAB.getName(), Blocks.CRIMSON_SLAB);
        this.addItem(MinecraftWoodBlocks.SLAB.getName(), Items.CRIMSON_SLAB);
        this.addBlock(MinecraftWoodBlocks.PRESSURE_PLATE.getName(), Blocks.CRIMSON_PRESSURE_PLATE);
        this.addItem(MinecraftWoodBlocks.PRESSURE_PLATE.getName(), Items.CRIMSON_PRESSURE_PLATE);
        this.addBlock(MinecraftWoodBlocks.DOOR.getName(), Blocks.CRIMSON_DOOR);
        this.addItem(MinecraftWoodBlocks.DOOR.getName(), Items.CRIMSON_DOOR);
        this.addBlock(MinecraftWoodBlocks.TRAPDOOR.getName(), Blocks.CRIMSON_TRAPDOOR);
        this.addItem(MinecraftWoodBlocks.TRAPDOOR.getName(), Items.CRIMSON_TRAPDOOR);
        this.addBlock(MinecraftWoodBlocks.FENCE.getName(), Blocks.CRIMSON_FENCE);
        this.addItem(MinecraftWoodBlocks.FENCE.getName(), Items.CRIMSON_FENCE);
        this.addBlock(MinecraftWoodBlocks.FENCE_GATE.getName(), Blocks.CRIMSON_FENCE_GATE);
        this.addItem(MinecraftWoodBlocks.FENCE_GATE.getName(), Items.CRIMSON_FENCE_GATE);
        this.addBlock(MinecraftWoodBlocks.BUTTON.getName(), Blocks.CRIMSON_BUTTON);
        this.addItem(MinecraftWoodBlocks.BUTTON.getName(), Items.CRIMSON_BUTTON);
        this.addItem(MinecraftWoodBlocks.SIGN_ITEM.getName(), Items.CRIMSON_SIGN);
        this.addBlock(MinecraftWoodBlocks.STANDING_SIGN.getName(), Blocks.CRIMSON_SIGN);
        this.addBlock(MinecraftWoodBlocks.WALL_SIGN.getName(), Blocks.CRIMSON_WALL_SIGN);
        this.addItem(MinecraftWoodBlocks.HANGING_SIGN_ITEM.getName(), Items.CRIMSON_HANGING_SIGN);
        this.addBlock(MinecraftWoodBlocks.HANGING_SIGN.getName(), Blocks.CRIMSON_HANGING_SIGN);
        this.addBlock(MinecraftWoodBlocks.HANGING_WALL_SIGN.getName(), Blocks.CRIMSON_WALL_HANGING_SIGN);
    }
}
