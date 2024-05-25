package drai.dev.complete_consistency.modules.minecraft.materials.wood.generators;

import com.mojang.datafixers.util.*;
import drai.dev.complete_consistency.model.*;
import net.minecraft.core.*;
import net.minecraft.data.models.*;
import net.minecraft.data.models.blockstates.*;
import net.minecraft.data.models.model.*;
import net.minecraft.resources.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.*;

import java.util.*;

public class ChiseledBookshelfGenerator {

    public static void createChiseledBookshelf(Block block, BlockModelGenerators blockModelGenerators) {
        ResourceLocation resourceLocation = ModelLocationUtils.getModelLocation(block);
        MultiPartGenerator multiPartGenerator = MultiPartGenerator.multiPart(block);
        TextureMapping textureMapping = (new TextureMapping()).put(TextureSlot.TOP, TextureMapping.getBlockTexture(block, "_top"))
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(block, "_side")).put(TextureSlot.FRONT, TextureMapping.getBlockTexture(block, "_empty"));
        ResourceLocation bookshelf = UpgradedVanillaModelTemplates.CHISELED_BOOKSHELF_TEMPLATE.create(block, textureMapping, blockModelGenerators.modelOutput);
        ResourceLocation inventory = UpgradedVanillaModelTemplates.CHISELED_BOOKSHELF_INVENTORY.create(block, textureMapping, blockModelGenerators.modelOutput);
        List.of(Pair.of(Direction.NORTH, VariantProperties.Rotation.R0),
                Pair.of(Direction.EAST, VariantProperties.Rotation.R90),
                Pair.of(Direction.SOUTH, VariantProperties.Rotation.R180),
                Pair.of(Direction.WEST, VariantProperties.Rotation.R270)).forEach(pair -> {
            Direction direction = (Direction)pair.getFirst();
            VariantProperties.Rotation rotation = (VariantProperties.Rotation)((Object)((Object)pair.getSecond()));
            Condition.TerminalCondition terminalCondition = Condition.condition().term(BlockStateProperties.HORIZONTAL_FACING, direction);
            multiPartGenerator.with((Condition)terminalCondition, Variant.variant().with(VariantProperties.MODEL, resourceLocation).with(VariantProperties.Y_ROT, rotation).with(VariantProperties.UV_LOCK, true));
            addSlotStateAndRotationVariants(block, multiPartGenerator, terminalCondition, rotation, blockModelGenerators);
        });
        blockModelGenerators.blockStateOutput.accept(multiPartGenerator);
        blockModelGenerators.delegateItemModel(block, inventory);
        CHISELED_BOOKSHELF_SLOT_MODEL_CACHE.clear();
    }

    private static final Map<BookSlotModelCacheKey, ResourceLocation> CHISELED_BOOKSHELF_SLOT_MODEL_CACHE = new HashMap<>();
    record BookSlotModelCacheKey(ModelTemplate template, String modelSuffix) {
    }

    private static void addSlotStateAndRotationVariants(Block block, MultiPartGenerator generator, Condition.TerminalCondition condition, VariantProperties.Rotation rotation, BlockModelGenerators blockModelGenerators) {
        List.of(Pair.of(BlockStateProperties.CHISELED_BOOKSHELF_SLOT_0_OCCUPIED, ModelTemplates.CHISELED_BOOKSHELF_SLOT_TOP_LEFT), Pair.of(BlockStateProperties.CHISELED_BOOKSHELF_SLOT_1_OCCUPIED, ModelTemplates.CHISELED_BOOKSHELF_SLOT_TOP_MID), Pair.of(BlockStateProperties.CHISELED_BOOKSHELF_SLOT_2_OCCUPIED, ModelTemplates.CHISELED_BOOKSHELF_SLOT_TOP_RIGHT), Pair.of(BlockStateProperties.CHISELED_BOOKSHELF_SLOT_3_OCCUPIED, ModelTemplates.CHISELED_BOOKSHELF_SLOT_BOTTOM_LEFT), Pair.of(BlockStateProperties.CHISELED_BOOKSHELF_SLOT_4_OCCUPIED, ModelTemplates.CHISELED_BOOKSHELF_SLOT_BOTTOM_MID), Pair.of(BlockStateProperties.CHISELED_BOOKSHELF_SLOT_5_OCCUPIED, ModelTemplates.CHISELED_BOOKSHELF_SLOT_BOTTOM_RIGHT)).forEach(pair -> {
            BooleanProperty booleanProperty = (BooleanProperty)pair.getFirst();
            ModelTemplate modelTemplate = (ModelTemplate)pair.getSecond();
            addBookSlotModel(block, generator, condition, rotation, booleanProperty, modelTemplate, true, blockModelGenerators);
            addBookSlotModel(block, generator, condition, rotation, booleanProperty, modelTemplate, false, blockModelGenerators);
        });
    }

    private static void addBookSlotModel(Block block, MultiPartGenerator generator, Condition.TerminalCondition condition, VariantProperties.Rotation rotation, BooleanProperty hasBookProperty, ModelTemplate template, boolean hasBook, BlockModelGenerators blockModelGenerators) {
        String string = hasBook ? "_occupied" : "_empty";
        TextureMapping textureMapping = new TextureMapping().put(TextureSlot.TEXTURE, TextureMapping.getBlockTexture(block, string));
        BookSlotModelCacheKey bookSlotModelCacheKey2 = new BookSlotModelCacheKey(template, string);
        ResourceLocation resourceLocation = CHISELED_BOOKSHELF_SLOT_MODEL_CACHE.computeIfAbsent(bookSlotModelCacheKey2, bookSlotModelCacheKey -> template.createWithSuffix(block, string, textureMapping, blockModelGenerators.modelOutput));
        generator.with(Condition.and(condition, Condition.condition().term(hasBookProperty, hasBook)), Variant.variant().with(VariantProperties.MODEL, resourceLocation).with(VariantProperties.Y_ROT, rotation));
    }
}
