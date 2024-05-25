package drai.dev.complete_consistency.modules.minecraft.materials.wood;

import drai.dev.complete_consistency.materials.impl.*;
import drai.dev.complete_consistency.modules.minecraft.materials.wood.enums.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.material.*;

public class Spruce extends WoodMaterial {
    public Spruce(String namespace, int index) {
        super(namespace, "spruce", MapColor.PODZOL, ()->Blocks.OAK_BUTTON, false, 1);
        this.addBlock(MinecraftWoodBlocks.LOG.getName(), Blocks.SPRUCE_LOG);
        this.addItem(MinecraftWoodBlocks.LOG.getName(), Items.SPRUCE_LOG);
        this.addBlock(MinecraftWoodBlocks.STRIPPED_LOG.getName(), Blocks.STRIPPED_SPRUCE_LOG);
        this.addItem(MinecraftWoodBlocks.STRIPPED_LOG.getName(), Items.STRIPPED_SPRUCE_LOG);
        this.addBlock(MinecraftWoodBlocks.WOOD.getName(), Blocks.SPRUCE_WOOD);
        this.addItem(MinecraftWoodBlocks.WOOD.getName(), Items.SPRUCE_WOOD);
        this.addBlock(MinecraftWoodBlocks.STRIPPED_WOOD.getName(), Blocks.STRIPPED_SPRUCE_WOOD);
        this.addItem(MinecraftWoodBlocks.STRIPPED_WOOD.getName(), Items.STRIPPED_SPRUCE_WOOD);
        this.addBlock(MinecraftWoodBlocks.PLANKS.getName(), Blocks.SPRUCE_PLANKS);
        this.addItem(MinecraftWoodBlocks.PLANKS.getName(), Items.SPRUCE_PLANKS);
        this.addBlock(MinecraftWoodBlocks.STAIRS.getName(), Blocks.SPRUCE_STAIRS);
        this.addItem(MinecraftWoodBlocks.STAIRS.getName(), Items.SPRUCE_STAIRS);
        this.addBlock(MinecraftWoodBlocks.SLAB.getName(), Blocks.SPRUCE_SLAB);
        this.addItem(MinecraftWoodBlocks.SLAB.getName(), Items.SPRUCE_SLAB);
        this.addBlock(MinecraftWoodBlocks.PRESSURE_PLATE.getName(), Blocks.SPRUCE_PRESSURE_PLATE);
        this.addItem(MinecraftWoodBlocks.PRESSURE_PLATE.getName(), Items.SPRUCE_PRESSURE_PLATE);
        this.addBlock(MinecraftWoodBlocks.DOOR.getName(), Blocks.SPRUCE_DOOR);
        this.addItem(MinecraftWoodBlocks.DOOR.getName(), Items.SPRUCE_DOOR);
        this.addBlock(MinecraftWoodBlocks.TRAPDOOR.getName(), Blocks.SPRUCE_TRAPDOOR);
        this.addItem(MinecraftWoodBlocks.TRAPDOOR.getName(), Items.SPRUCE_TRAPDOOR);
        this.addBlock(MinecraftWoodBlocks.FENCE.getName(), Blocks.SPRUCE_FENCE);
        this.addItem(MinecraftWoodBlocks.FENCE.getName(), Items.SPRUCE_FENCE);
        this.addBlock(MinecraftWoodBlocks.FENCE_GATE.getName(), Blocks.SPRUCE_FENCE_GATE);
        this.addItem(MinecraftWoodBlocks.FENCE_GATE.getName(), Items.SPRUCE_FENCE_GATE);
        this.addBlock(MinecraftWoodBlocks.BUTTON.getName(), Blocks.SPRUCE_BUTTON);
        this.addItem(MinecraftWoodBlocks.BUTTON.getName(), Items.SPRUCE_BUTTON);
        this.addItem(MinecraftWoodBlocks.SIGN_ITEM.getName(), Items.SPRUCE_SIGN);
        this.addBlock(MinecraftWoodBlocks.STANDING_SIGN.getName(), Blocks.SPRUCE_SIGN);
        this.addBlock(MinecraftWoodBlocks.WALL_SIGN.getName(), Blocks.SPRUCE_WALL_SIGN);
        this.addItem(MinecraftWoodBlocks.HANGING_SIGN_ITEM.getName(), Items.SPRUCE_HANGING_SIGN);
        this.addBlock(MinecraftWoodBlocks.HANGING_SIGN.getName(), Blocks.SPRUCE_HANGING_SIGN);
        this.addBlock(MinecraftWoodBlocks.HANGING_WALL_SIGN.getName(), Blocks.SPRUCE_WALL_HANGING_SIGN);
        this.addItem(MinecraftWoodBlocks.BOAT.getName(), Items.SPRUCE_BOAT);
        this.addItem(MinecraftWoodBlocks.CHEST_BOAT.getName(), Items.SPRUCE_CHEST_BOAT);

        this.addItem(MinecraftWoodBlocks.STICK.getName(), Items.STICK);
        this.addBlock(MinecraftWoodBlocks.BARREL.getName(), Blocks.BARREL);
        this.addItem(MinecraftWoodBlocks.BARREL.getName(), Items.BARREL);
        this.addBlock(MinecraftWoodBlocks.COMPOSTER.getName(), Blocks.COMPOSTER);
        this.addItem(MinecraftWoodBlocks.COMPOSTER.getName(), Items.COMPOSTER);
    }
}
