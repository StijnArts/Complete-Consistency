package drai.dev.complete_consistency.modules.minecraft.materials.wood;

import drai.dev.complete_consistency.materials.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.material.*;

public class Oak extends WoodMaterial {
    public Oak(String namespace) {
        super(namespace, "oak", MapColor.WOOD, null, true);
        this.addBlock(MinecraftWoodBlocks.LOG.getName(), Blocks.OAK_LOG);
        this.addBlock(MinecraftWoodBlocks.STRIPPED_LOG.getName(), Blocks.STRIPPED_OAK_LOG);
        this.addBlock(MinecraftWoodBlocks.WOOD.getName(), Blocks.OAK_WOOD);
        this.addBlock(MinecraftWoodBlocks.STRIPPED_WOOD.getName(), Blocks.STRIPPED_OAK_WOOD);
        this.addBlock(MinecraftWoodBlocks.PLANKS.getName(), Blocks.OAK_PLANKS);
        this.addBlock(MinecraftWoodBlocks.STAIRS.getName(), Blocks.OAK_STAIRS);
        this.addBlock(MinecraftWoodBlocks.SLAB.getName(), Blocks.OAK_SLAB);
        this.addBlock(MinecraftWoodBlocks.PRESSURE_PLATE.getName(), Blocks.OAK_PRESSURE_PLATE);
        this.addBlock(MinecraftWoodBlocks.DOOR.getName(), Blocks.OAK_DOOR);
        this.addBlock(MinecraftWoodBlocks.TRAPDOOR.getName(), Blocks.OAK_TRAPDOOR);
        this.addBlock(MinecraftWoodBlocks.FENCE.getName(), Blocks.OAK_FENCE);
        this.addBlock(MinecraftWoodBlocks.FENCE_GATE.getName(), Blocks.OAK_FENCE_GATE);
        this.addBlock(MinecraftWoodBlocks.BUTTON.getName(), Blocks.OAK_BUTTON);
        this.addBlock(MinecraftWoodBlocks.TORCH.getName(), Blocks.TORCH);
        this.addBlock(MinecraftWoodBlocks.WALL_TORCH.getName(), Blocks.WALL_TORCH);
        this.addBlock(MinecraftWoodBlocks.REDSTONE_TORCH.getName(), Blocks.REDSTONE_TORCH);
        this.addBlock(MinecraftWoodBlocks.REDSTONE_WALL_TORCH.getName(), Blocks.REDSTONE_WALL_TORCH);
        this.addBlock(MinecraftWoodBlocks.SOUL_TORCH.getName(), Blocks.SOUL_TORCH);
        this.addBlock(MinecraftWoodBlocks.SOUL_WALL_TORCH.getName(), Blocks.SOUL_WALL_TORCH);
        this.addBlock(MinecraftWoodBlocks.CAMPFIRE.getName(), Blocks.CAMPFIRE);
        this.addBlock(MinecraftWoodBlocks.SOUL_CAMPFIRE.getName(), Blocks.SOUL_CAMPFIRE);
        this.addBlock(MinecraftWoodBlocks.WORKBENCH.getName(), Blocks.SOUL_CAMPFIRE);
        this.addBlock(MinecraftWoodBlocks.LADDER.getName(), Blocks.LADDER);
        this.addBlock(MinecraftWoodBlocks.BEEHIVE.getName(), Blocks.BEEHIVE);
        this.addBlock(MinecraftWoodBlocks.BOOKSHELF.getName(), Blocks.BEEHIVE);
        this.addBlock(MinecraftWoodBlocks.CHISELED_BOOKSHELF.getName(), Blocks.CHISELED_BOOKSHELF);
        this.addBlock(MinecraftWoodBlocks.LECTERN.getName(), Blocks.LECTERN);
        this.addBlock(MinecraftWoodBlocks.STANDING_SIGN.getName(), Blocks.LECTERN);
        this.addBlock(MinecraftWoodBlocks.WALL_SIGN.getName(), Blocks.LECTERN);
        this.addBlock(MinecraftWoodBlocks.HANGING_SIGN.getName(), Blocks.LECTERN);
        this.addBlock(MinecraftWoodBlocks.HANGING_WALL_SIGN.getName(), Blocks.LECTERN);

        this.addItem(MinecraftWoodBlocks.TORCH_ITEM.getName(), Items.TORCH);
        this.addItem(MinecraftWoodBlocks.SOUL_TORCH_ITEM.getName(), Items.SOUL_TORCH);
        this.addItem(MinecraftWoodBlocks.ITEM_FRAME.getName(), Items.ITEM_FRAME);
        this.addItem(MinecraftWoodBlocks.GLOW_ITEM_FRAME.getName(), Items.GLOW_ITEM_FRAME);
        this.addItem(MinecraftWoodBlocks.SIGN_ITEM.getName(), Items.OAK_SIGN);
        this.addItem(MinecraftWoodBlocks.HANGING_SIGN_ITEM.getName(), Items.OAK_HANGING_SIGN);
        this.addItem(MinecraftWoodBlocks.BOAT.getName(), Items.OAK_BOAT);
        this.addItem(MinecraftWoodBlocks.CHEST_BOAT.getName(), Items.OAK_CHEST_BOAT);
    }
}
