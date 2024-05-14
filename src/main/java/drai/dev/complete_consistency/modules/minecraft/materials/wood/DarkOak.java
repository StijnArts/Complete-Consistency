package drai.dev.complete_consistency.modules.minecraft.materials.wood;

import drai.dev.complete_consistency.materials.impl.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.material.*;

public class DarkOak extends WoodMaterial {
    public DarkOak(String namespace, int index) {
        super(namespace, "dark_oak", MapColor.COLOR_BROWN, ()->Blocks.ACACIA_BUTTON, false, 5);
        this.addBlock(MinecraftWoodBlocks.LOG.getName(), Blocks.DARK_OAK_LOG);
        this.addItem(MinecraftWoodBlocks.LOG.getName(), Items.DARK_OAK_LOG);
        this.addBlock(MinecraftWoodBlocks.STRIPPED_LOG.getName(), Blocks.STRIPPED_DARK_OAK_LOG);
        this.addItem(MinecraftWoodBlocks.STRIPPED_LOG.getName(), Items.STRIPPED_DARK_OAK_LOG);
        this.addBlock(MinecraftWoodBlocks.WOOD.getName(), Blocks.DARK_OAK_WOOD);
        this.addItem(MinecraftWoodBlocks.WOOD.getName(), Items.DARK_OAK_WOOD);
        this.addBlock(MinecraftWoodBlocks.STRIPPED_WOOD.getName(), Blocks.STRIPPED_DARK_OAK_WOOD);
        this.addItem(MinecraftWoodBlocks.STRIPPED_WOOD.getName(), Items.STRIPPED_DARK_OAK_WOOD);
        this.addBlock(MinecraftWoodBlocks.PLANKS.getName(), Blocks.DARK_OAK_PLANKS);
        this.addItem(MinecraftWoodBlocks.PLANKS.getName(), Items.DARK_OAK_PLANKS);
        this.addBlock(MinecraftWoodBlocks.STAIRS.getName(), Blocks.DARK_OAK_STAIRS);
        this.addItem(MinecraftWoodBlocks.STAIRS.getName(), Items.DARK_OAK_STAIRS);
        this.addBlock(MinecraftWoodBlocks.SLAB.getName(), Blocks.DARK_OAK_SLAB);
        this.addItem(MinecraftWoodBlocks.SLAB.getName(), Items.DARK_OAK_SLAB);
        this.addBlock(MinecraftWoodBlocks.PRESSURE_PLATE.getName(), Blocks.DARK_OAK_PRESSURE_PLATE);
        this.addItem(MinecraftWoodBlocks.PRESSURE_PLATE.getName(), Items.DARK_OAK_PRESSURE_PLATE);
        this.addBlock(MinecraftWoodBlocks.DOOR.getName(), Blocks.DARK_OAK_DOOR);
        this.addItem(MinecraftWoodBlocks.DOOR.getName(), Items.DARK_OAK_DOOR);
        this.addBlock(MinecraftWoodBlocks.TRAPDOOR.getName(), Blocks.DARK_OAK_TRAPDOOR);
        this.addItem(MinecraftWoodBlocks.TRAPDOOR.getName(), Items.DARK_OAK_TRAPDOOR);
        this.addBlock(MinecraftWoodBlocks.FENCE.getName(), Blocks.DARK_OAK_FENCE);
        this.addItem(MinecraftWoodBlocks.FENCE.getName(), Items.DARK_OAK_FENCE);
        this.addBlock(MinecraftWoodBlocks.FENCE_GATE.getName(), Blocks.DARK_OAK_FENCE_GATE);
        this.addItem(MinecraftWoodBlocks.FENCE_GATE.getName(), Items.DARK_OAK_FENCE_GATE);
        this.addBlock(MinecraftWoodBlocks.BUTTON.getName(), Blocks.DARK_OAK_BUTTON);
        this.addItem(MinecraftWoodBlocks.BUTTON.getName(), Items.DARK_OAK_BUTTON);
        this.addItem(MinecraftWoodBlocks.SIGN_ITEM.getName(), Items.DARK_OAK_SIGN);
        this.addBlock(MinecraftWoodBlocks.STANDING_SIGN.getName(), Blocks.DARK_OAK_SIGN);
        this.addBlock(MinecraftWoodBlocks.WALL_SIGN.getName(), Blocks.DARK_OAK_WALL_SIGN);
        this.addItem(MinecraftWoodBlocks.HANGING_SIGN_ITEM.getName(), Items.DARK_OAK_HANGING_SIGN);
        this.addBlock(MinecraftWoodBlocks.HANGING_SIGN.getName(), Blocks.DARK_OAK_HANGING_SIGN);
        this.addBlock(MinecraftWoodBlocks.HANGING_WALL_SIGN.getName(), Blocks.DARK_OAK_WALL_HANGING_SIGN);
        this.addItem(MinecraftWoodBlocks.BOAT.getName(), Items.DARK_OAK_BOAT);
        this.addItem(MinecraftWoodBlocks.CHEST_BOAT.getName(), Items.DARK_OAK_CHEST_BOAT);

        this.addBlock(MinecraftWoodBlocks.CARTOGRAPHY_TABLE.getName(), Blocks.CARTOGRAPHY_TABLE);
        this.addItem(MinecraftWoodBlocks.CARTOGRAPHY_TABLE.getName(), Items.CARTOGRAPHY_TABLE);
    }
}
