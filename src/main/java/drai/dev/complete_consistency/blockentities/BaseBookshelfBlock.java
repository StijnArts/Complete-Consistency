package drai.dev.complete_consistency.blockentities;

import net.fabricmc.api.*;
import net.minecraft.client.renderer.block.model.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.storage.loot.*;
import net.minecraft.world.level.storage.loot.parameters.*;
import org.jetbrains.annotations.*;

import java.util.*;

public class BaseBookshelfBlock extends Block {
    protected BaseBookshelfBlock(Block source) {
        this(BlockBehaviour.Properties.ofFullCopy(source));
    }

    public BaseBookshelfBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        ItemStack tool = builder.getParameter(LootContextParams.TOOL);
        if (tool != null) {
            int silk = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, tool);
            if (silk > 0) {
                return Collections.singletonList(new ItemStack(this));
            }
        }
        return Collections.singletonList(new ItemStack(Items.BOOK, 3));
    }

    protected ResourceLocation replacePath(ResourceLocation blockId) {
        String newPath = blockId.getPath().replace("_bookshelf", "");
        return new ResourceLocation(blockId.getNamespace(), newPath);
    }
}
