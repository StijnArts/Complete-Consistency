package drai.dev.complete_consistency.modules.minecraft.materials.wood;

import java.util.*;

public enum MinecraftWoodBlocks {
    STICK,
    LOG,
    WOOD,
    WOOD_STAIRS,
    WOOD_SLAB,
    WOOD_WALL,
    WOOD_FENCE,
    WOOD_FENCE_GATE,
    WOOD_TRAPDOOR,
    WOOD_PRESSURE_PLATE,
    WOOD_BUTTON,
    STRIPPED_LOG,
    STRIPPED_WOOD,
    STRIPPED_WOOD_STAIRS,
    STRIPPED_WOOD_SLAB,
    STRIPPED_WOOD_WALL,
    STRIPPED_WOOD_FENCE,
    STRIPPED_WOOD_FENCE_GATE,
    STRIPPED_WOOD_TRAPDOOR,
    STRIPPED_WOOD_PRESSURE_PLATE,
    STRIPPED_WOOD_BUTTON,
    PLANKS,
    STAIRS,
    SLAB,
    WALL,
    FENCE,
    FENCE_GATE,
    DOOR,
    TRAPDOOR,
    PRESSURE_PLATE,
    BUTTON,
    LADDER,
    STANDING_SIGN,
    WALL_SIGN,
    SIGN_ITEM,
    HANGING_SIGN, //TODO move to combination blocks
    HANGING_WALL_SIGN,
    HANGING_SIGN_ITEM,
    CHEST,
    TRAPPED_CHEST,
    BOAT,
    CHEST_BOAT,//Finished till here
    BARREL,
    TORCH,
    WALL_TORCH,
    TORCH_ITEM,
    SOUL_TORCH,
    SOUL_WALL_TORCH,
    SOUL_TORCH_ITEM,
    REDSTONE_TORCH,
    REDSTONE_WALL_TORCH,
    REDSTONE_TORCH_ITEM,
    CRAFTING_TABLE,
    CAMPFIRE,
    SOUL_CAMPFIRE,
    BOOKSHELF,
    CHISELED_BOOKSHELF,
    LECTERN,
    BEEHIVE,
    COMPOSTER,
    LOOM,
    CARTOGRAPHY_TABLE,
    FLETCHING_TABLE,
    SCAFFOLDING,
    ITEM_FRAME,
    GLOW_ITEM_FRAME;

    public static int indexOf(MinecraftWoodBlocks minecraftWoodBlocks) {
        return Arrays.stream(values()).toList().indexOf(minecraftWoodBlocks);
    }

    public String getName(){
        return this.name().toLowerCase();
    }

    @Override
    public String toString() {
        return getName();
    }
}
