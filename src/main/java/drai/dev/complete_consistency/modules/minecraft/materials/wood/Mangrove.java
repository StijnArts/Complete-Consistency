package drai.dev.complete_consistency.modules.minecraft.materials.wood;

import drai.dev.complete_consistency.materials.impl.*;
import drai.dev.complete_consistency.modules.minecraft.materials.wood.enums.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.material.*;

public class Mangrove extends WoodMaterial {
    public Mangrove(String namespace, int index) {
        super(namespace, "mangrove", MapColor.COLOR_RED, ()->Blocks.DARK_OAK_BUTTON, false, 6);
        this.addBlock(MinecraftWoodBlocks.LOG.getName(), Blocks.MANGROVE_LOG);
        this.addItem(MinecraftWoodBlocks.LOG.getName(), Items.MANGROVE_LOG);
        this.addBlock(MinecraftWoodBlocks.STRIPPED_LOG.getName(), Blocks.STRIPPED_MANGROVE_LOG);
        this.addItem(MinecraftWoodBlocks.STRIPPED_LOG.getName(), Items.STRIPPED_MANGROVE_LOG);
        this.addBlock(MinecraftWoodBlocks.WOOD.getName(), Blocks.MANGROVE_WOOD);
        this.addItem(MinecraftWoodBlocks.WOOD.getName(), Items.MANGROVE_WOOD);
        this.addBlock(MinecraftWoodBlocks.STRIPPED_WOOD.getName(), Blocks.STRIPPED_MANGROVE_WOOD);
        this.addItem(MinecraftWoodBlocks.STRIPPED_WOOD.getName(), Items.STRIPPED_MANGROVE_WOOD);
        this.addBlock(MinecraftWoodBlocks.PLANKS.getName(), Blocks.MANGROVE_PLANKS);
        this.addItem(MinecraftWoodBlocks.PLANKS.getName(), Items.MANGROVE_PLANKS);
        this.addBlock(MinecraftWoodBlocks.STAIRS.getName(), Blocks.MANGROVE_STAIRS);
        this.addItem(MinecraftWoodBlocks.STAIRS.getName(), Items.MANGROVE_STAIRS);
        this.addBlock(MinecraftWoodBlocks.SLAB.getName(), Blocks.MANGROVE_SLAB);
        this.addItem(MinecraftWoodBlocks.SLAB.getName(), Items.MANGROVE_SLAB);
        this.addBlock(MinecraftWoodBlocks.PRESSURE_PLATE.getName(), Blocks.MANGROVE_PRESSURE_PLATE);
        this.addItem(MinecraftWoodBlocks.PRESSURE_PLATE.getName(), Items.MANGROVE_PRESSURE_PLATE);
        this.addBlock(MinecraftWoodBlocks.DOOR.getName(), Blocks.MANGROVE_DOOR);
        this.addItem(MinecraftWoodBlocks.DOOR.getName(), Items.MANGROVE_DOOR);
        this.addBlock(MinecraftWoodBlocks.TRAPDOOR.getName(), Blocks.MANGROVE_TRAPDOOR);
        this.addItem(MinecraftWoodBlocks.TRAPDOOR.getName(), Items.MANGROVE_TRAPDOOR);
        this.addBlock(MinecraftWoodBlocks.FENCE.getName(), Blocks.MANGROVE_FENCE);
        this.addItem(MinecraftWoodBlocks.FENCE.getName(), Items.MANGROVE_FENCE);
        this.addBlock(MinecraftWoodBlocks.FENCE_GATE.getName(), Blocks.MANGROVE_FENCE_GATE);
        this.addItem(MinecraftWoodBlocks.FENCE_GATE.getName(), Items.MANGROVE_FENCE_GATE);
        this.addBlock(MinecraftWoodBlocks.BUTTON.getName(), Blocks.MANGROVE_BUTTON);
        this.addItem(MinecraftWoodBlocks.BUTTON.getName(), Items.MANGROVE_BUTTON);
        this.addItem(MinecraftWoodBlocks.SIGN_ITEM.getName(), Items.MANGROVE_SIGN);
        this.addBlock(MinecraftWoodBlocks.STANDING_SIGN.getName(), Blocks.MANGROVE_SIGN);
        this.addBlock(MinecraftWoodBlocks.WALL_SIGN.getName(), Blocks.MANGROVE_WALL_SIGN);
        this.addItem(MinecraftWoodBlocks.HANGING_SIGN_ITEM.getName(), Items.MANGROVE_HANGING_SIGN);
        this.addBlock(MinecraftWoodBlocks.HANGING_SIGN.getName(), Blocks.MANGROVE_HANGING_SIGN);
        this.addBlock(MinecraftWoodBlocks.HANGING_WALL_SIGN.getName(), Blocks.MANGROVE_WALL_HANGING_SIGN);
        this.addItem(MinecraftWoodBlocks.BOAT.getName(), Items.MANGROVE_BOAT);
        this.addItem(MinecraftWoodBlocks.CHEST_BOAT.getName(), Items.MANGROVE_CHEST_BOAT);
    }
}
