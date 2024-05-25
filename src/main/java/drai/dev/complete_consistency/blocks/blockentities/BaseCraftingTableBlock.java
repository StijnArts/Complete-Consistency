package drai.dev.complete_consistency.blocks.blockentities;

import net.fabricmc.api.*;
import net.minecraft.client.renderer.block.model.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import org.jetbrains.annotations.*;

import java.util.*;

public class BaseCraftingTableBlock extends CraftingTableBlock {
    protected BaseCraftingTableBlock(Block source) {
        this(Properties.ofFullCopy(source));
    }

    protected BaseCraftingTableBlock(Properties properties) {
        super(properties);
    }
}
