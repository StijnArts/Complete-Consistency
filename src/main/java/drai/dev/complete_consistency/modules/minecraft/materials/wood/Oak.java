package drai.dev.complete_consistency.modules.minecraft.materials.wood;

import drai.dev.complete_consistency.materials.impl.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.material.*;

public class Oak extends WoodMaterial {
    public Oak(String namespace, int index) {
        super(namespace, "oak", MapColor.WOOD, null, false, 0);
        this.addBlock(MinecraftWoodBlocks.LOG.getName(), Blocks.OAK_LOG);
        this.addItem(MinecraftWoodBlocks.LOG.getName(), Items.OAK_LOG);
        this.addBlock(MinecraftWoodBlocks.STRIPPED_LOG.getName(), Blocks.STRIPPED_OAK_LOG);
        this.addItem(MinecraftWoodBlocks.STRIPPED_LOG.getName(), Items.STRIPPED_OAK_LOG);
        this.addBlock(MinecraftWoodBlocks.WOOD.getName(), Blocks.OAK_WOOD);
        this.addItem(MinecraftWoodBlocks.WOOD.getName(), Items.OAK_WOOD);
        this.addBlock(MinecraftWoodBlocks.STRIPPED_WOOD.getName(), Blocks.STRIPPED_OAK_WOOD);
        this.addItem(MinecraftWoodBlocks.STRIPPED_WOOD.getName(), Items.STRIPPED_OAK_WOOD);
        this.addBlock(MinecraftWoodBlocks.PLANKS.getName(), Blocks.OAK_PLANKS);
        this.addItem(MinecraftWoodBlocks.PLANKS.getName(), Items.OAK_PLANKS);
        this.addBlock(MinecraftWoodBlocks.STAIRS.getName(), Blocks.OAK_STAIRS);
        this.addItem(MinecraftWoodBlocks.STAIRS.getName(), Items.OAK_STAIRS);
        this.addBlock(MinecraftWoodBlocks.SLAB.getName(), Blocks.OAK_SLAB);
        this.addItem(MinecraftWoodBlocks.SLAB.getName(), Items.OAK_SLAB);
        this.addBlock(MinecraftWoodBlocks.PRESSURE_PLATE.getName(), Blocks.OAK_PRESSURE_PLATE);
        this.addItem(MinecraftWoodBlocks.PRESSURE_PLATE.getName(), Items.OAK_PRESSURE_PLATE);
        this.addBlock(MinecraftWoodBlocks.DOOR.getName(), Blocks.OAK_DOOR);
        this.addItem(MinecraftWoodBlocks.DOOR.getName(), Items.OAK_DOOR);
        this.addBlock(MinecraftWoodBlocks.TRAPDOOR.getName(), Blocks.OAK_TRAPDOOR);
        this.addItem(MinecraftWoodBlocks.TRAPDOOR.getName(), Items.OAK_TRAPDOOR);
        this.addBlock(MinecraftWoodBlocks.FENCE.getName(), Blocks.OAK_FENCE);
        this.addItem(MinecraftWoodBlocks.FENCE.getName(), Items.OAK_FENCE);
        this.addBlock(MinecraftWoodBlocks.FENCE_GATE.getName(), Blocks.OAK_FENCE_GATE);
        this.addItem(MinecraftWoodBlocks.FENCE_GATE.getName(), Items.OAK_FENCE_GATE);
        this.addBlock(MinecraftWoodBlocks.BUTTON.getName(), Blocks.OAK_BUTTON);
        this.addItem(MinecraftWoodBlocks.BUTTON.getName(), Items.OAK_BUTTON);
        this.addItem(MinecraftWoodBlocks.SIGN_ITEM.getName(), Items.OAK_SIGN);
        this.addBlock(MinecraftWoodBlocks.STANDING_SIGN.getName(), Blocks.OAK_SIGN);
        this.addBlock(MinecraftWoodBlocks.WALL_SIGN.getName(), Blocks.OAK_WALL_SIGN);
        this.addItem(MinecraftWoodBlocks.HANGING_SIGN_ITEM.getName(), Items.OAK_HANGING_SIGN);
        this.addBlock(MinecraftWoodBlocks.HANGING_SIGN.getName(), Blocks.OAK_HANGING_SIGN);
        this.addBlock(MinecraftWoodBlocks.HANGING_WALL_SIGN.getName(), Blocks.OAK_WALL_HANGING_SIGN);
        this.addItem(MinecraftWoodBlocks.BOAT.getName(), Items.OAK_BOAT);
        this.addItem(MinecraftWoodBlocks.CHEST_BOAT.getName(), Items.OAK_CHEST_BOAT);

        this.addBlock(MinecraftWoodBlocks.TORCH.getName(), Blocks.TORCH);
        this.addBlock(MinecraftWoodBlocks.WALL_TORCH.getName(), Blocks.WALL_TORCH);
        this.addBlock(MinecraftWoodBlocks.REDSTONE_TORCH.getName(), Blocks.REDSTONE_TORCH);
        this.addBlock(MinecraftWoodBlocks.REDSTONE_WALL_TORCH.getName(), Blocks.REDSTONE_WALL_TORCH);
        this.addBlock(MinecraftWoodBlocks.SOUL_TORCH.getName(), Blocks.SOUL_TORCH);
        this.addBlock(MinecraftWoodBlocks.SOUL_WALL_TORCH.getName(), Blocks.SOUL_WALL_TORCH);
        this.addBlock(MinecraftWoodBlocks.CAMPFIRE.getName(), Blocks.CAMPFIRE);
        this.addItem(MinecraftWoodBlocks.CAMPFIRE.getName(), Items.CAMPFIRE);
        this.addBlock(MinecraftWoodBlocks.SOUL_CAMPFIRE.getName(), Blocks.SOUL_CAMPFIRE);
        this.addItem(MinecraftWoodBlocks.SOUL_CAMPFIRE.getName(), Items.SOUL_CAMPFIRE);
        this.addBlock(MinecraftWoodBlocks.CRAFTING_TABLE.getName(), Blocks.CRAFTING_TABLE);
        this.addItem(MinecraftWoodBlocks.CRAFTING_TABLE.getName(), Items.CRAFTING_TABLE);
        this.addBlock(MinecraftWoodBlocks.LADDER.getName(), Blocks.LADDER);
        this.addItem(MinecraftWoodBlocks.LADDER.getName(), Items.LADDER);
        this.addBlock(MinecraftWoodBlocks.BEEHIVE.getName(), Blocks.BEEHIVE);
        this.addItem(MinecraftWoodBlocks.BEEHIVE.getName(), Items.BEEHIVE);
        this.addBlock(MinecraftWoodBlocks.BOOKSHELF.getName(), Blocks.BOOKSHELF);
        this.addItem(MinecraftWoodBlocks.BOOKSHELF.getName(), Items.BOOKSHELF);
        this.addBlock(MinecraftWoodBlocks.LOOM.getName(), Blocks.LOOM);
        this.addItem(MinecraftWoodBlocks.LOOM.getName(), Items.LOOM);
        this.addBlock(MinecraftWoodBlocks.CHISELED_BOOKSHELF.getName(), Blocks.CHISELED_BOOKSHELF);
        this.addItem(MinecraftWoodBlocks.CHISELED_BOOKSHELF.getName(), Items.CHISELED_BOOKSHELF);
        this.addBlock(MinecraftWoodBlocks.LECTERN.getName(), Blocks.LECTERN);
        this.addItem(MinecraftWoodBlocks.LECTERN.getName(), Items.LECTERN);

        this.addItem(MinecraftWoodBlocks.TORCH_ITEM.getName(), Items.TORCH);
        this.addItem(MinecraftWoodBlocks.SOUL_TORCH_ITEM.getName(), Items.SOUL_TORCH);
        this.addItem(MinecraftWoodBlocks.ITEM_FRAME.getName(), Items.ITEM_FRAME);
        this.addItem(MinecraftWoodBlocks.GLOW_ITEM_FRAME.getName(), Items.GLOW_ITEM_FRAME);
    }
}
