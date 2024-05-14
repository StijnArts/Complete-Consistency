package drai.dev.complete_consistency.modules.minecraft.materials.wood;

import drai.dev.complete_consistency.materials.impl.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.material.*;

public class Bamboo extends WoodMaterial {
    public Bamboo(String namespace, int index) {
        super(namespace, "bamboo", MapColor.COLOR_YELLOW, ()->Blocks.CHERRY_BUTTON, false, 8);
        this.addBlock(MinecraftWoodBlocks.LOG.getName(), Blocks.BAMBOO_BLOCK);
        this.addItem(MinecraftWoodBlocks.LOG.getName(), Items.BAMBOO_BLOCK);
        this.addBlock(MinecraftWoodBlocks.STRIPPED_LOG.getName(), Blocks.STRIPPED_BAMBOO_BLOCK);
        this.addItem(MinecraftWoodBlocks.STRIPPED_LOG.getName(), Items.STRIPPED_BAMBOO_BLOCK);
        this.addBlock(MinecraftWoodBlocks.PLANKS.getName(), Blocks.BAMBOO_PLANKS);
        this.addItem(MinecraftWoodBlocks.PLANKS.getName(), Items.BAMBOO_PLANKS);
        this.addBlock(MinecraftWoodBlocks.STAIRS.getName(), Blocks.BAMBOO_STAIRS);
        this.addItem(MinecraftWoodBlocks.STAIRS.getName(), Items.BAMBOO_STAIRS);
        this.addBlock(MinecraftWoodBlocks.SLAB.getName(), Blocks.BAMBOO_SLAB);
        this.addItem(MinecraftWoodBlocks.SLAB.getName(), Items.BAMBOO_SLAB);
        this.addBlock(MinecraftWoodBlocks.PRESSURE_PLATE.getName(), Blocks.BAMBOO_PRESSURE_PLATE);
        this.addItem(MinecraftWoodBlocks.PRESSURE_PLATE.getName(), Items.BAMBOO_PRESSURE_PLATE);
        this.addBlock(MinecraftWoodBlocks.DOOR.getName(), Blocks.BAMBOO_DOOR);
        this.addItem(MinecraftWoodBlocks.DOOR.getName(), Items.BAMBOO_DOOR);
        this.addBlock(MinecraftWoodBlocks.TRAPDOOR.getName(), Blocks.BAMBOO_TRAPDOOR);
        this.addItem(MinecraftWoodBlocks.TRAPDOOR.getName(), Items.BAMBOO_TRAPDOOR);
        this.addBlock(MinecraftWoodBlocks.FENCE.getName(), Blocks.BAMBOO_FENCE);
        this.addItem(MinecraftWoodBlocks.FENCE.getName(), Items.BAMBOO_FENCE);
        this.addBlock(MinecraftWoodBlocks.FENCE_GATE.getName(), Blocks.BAMBOO_FENCE_GATE);
        this.addItem(MinecraftWoodBlocks.FENCE_GATE.getName(), Items.BAMBOO_FENCE_GATE);
        this.addBlock(MinecraftWoodBlocks.BUTTON.getName(), Blocks.BAMBOO_BUTTON);
        this.addItem(MinecraftWoodBlocks.BUTTON.getName(), Items.BAMBOO_BUTTON);
        this.addItem(MinecraftWoodBlocks.SIGN_ITEM.getName(), Items.BAMBOO_SIGN);
        this.addBlock(MinecraftWoodBlocks.STANDING_SIGN.getName(), Blocks.BAMBOO_SIGN);
        this.addBlock(MinecraftWoodBlocks.WALL_SIGN.getName(), Blocks.BAMBOO_WALL_SIGN);
        this.addItem(MinecraftWoodBlocks.HANGING_SIGN_ITEM.getName(), Items.BAMBOO_HANGING_SIGN);
        this.addBlock(MinecraftWoodBlocks.HANGING_SIGN.getName(), Blocks.BAMBOO_HANGING_SIGN);
        this.addBlock(MinecraftWoodBlocks.HANGING_WALL_SIGN.getName(), Blocks.BAMBOO_WALL_HANGING_SIGN);
        this.addItem(MinecraftWoodBlocks.BOAT.getName(), Items.BAMBOO_RAFT);
        this.addItem(MinecraftWoodBlocks.CHEST_BOAT.getName(), Items.BAMBOO_CHEST_RAFT);

        this.addBlock(MinecraftWoodBlocks.SCAFFOLDING.getName(), Blocks.SCAFFOLDING);
        this.addItem(MinecraftWoodBlocks.SCAFFOLDING.getName(), Items.SCAFFOLDING);
        this.addItem(MinecraftWoodBlocks.STICK.getName(), Items.BAMBOO);
    }
}
