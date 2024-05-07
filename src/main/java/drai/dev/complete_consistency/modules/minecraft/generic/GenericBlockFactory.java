package drai.dev.complete_consistency.modules.minecraft.generic;

import drai.dev.complete_consistency.helpers.*;
import drai.dev.complete_consistency.helpers.block.*;
import drai.dev.complete_consistency.helpers.languages.*;
import drai.dev.complete_consistency.materials.*;
import drai.dev.complete_consistency.model.*;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.minecraft.core.registries.*;
import net.minecraft.data.loot.*;
import net.minecraft.data.models.*;
import net.minecraft.data.models.model.*;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;

import java.util.*;
import java.util.function.*;

public class GenericBlockFactory {
    public static <T extends Block> Block createBlock(BlockMaterial material, String blockType, String blockId,
                                                     BiFunction<String, String, T> supplier,
                                                     List<TagKey<Block>> blockTags,
                                                     List<TagKey<Item>> itemTags){
        String blockLangFileName = "";
        for (String segment : blockId.split("_")) {
            blockLangFileName = blockLangFileName +" "+ StringUtil.capitalizeWord(segment);
        }
        if(!material.hasBlock(blockType)) {
            Block returnBlock = supplier.apply(blockId, blockLangFileName.trim());
            material.addBlock(blockType, returnBlock);
            return returnBlock;
        } else {
            var block = material.getBlock(blockType);
            TagHelper.addBlockTags(block, blockTags);
            TagHelper.addItemTags(block.asItem(), itemTags);
            return null;
        }
    }

    public static Block createStairs(int number, BlockMaterial material, String blockType, Block sourceBlock, String sourceBlockType, Supplier<Block> stairsSupplier) {

        return createStairs(number, material, blockType, sourceBlock, sourceBlockType, stairsSupplier.get());
    }

    public static Block createStairs(int number, BlockMaterial material, String blockType, Block sourceBlock, BiFunction<String, String, Block> blockSupplier,
                                     List<TagKey<Block>> blockTags,
                                     List<TagKey<Item>> itemTags) {
        var sourceBlockId = BuiltInRegistries.BLOCK.getKey(sourceBlock).getPath();
        String blockId = sourceBlockId + "_stairs";
        return createBlock(material, blockType, blockId, blockSupplier, blockTags, itemTags);
    }

    public static Block createStairs(int number, BlockMaterial material, String blockType, Block sourceBlock, String sourceBlockType, Block stairs) {
        List<TagKey<Block>> blockTags = material.getBlockTags(List.of(blockType));
        List<TagKey<Item>> itemTags = material.getItemTags(List.of(blockType));
        TagKey<Item> sourceBlockTag = material.getItemTag(sourceBlockType);
        BiFunction<String, String, Block> blockSupplier = (String id, String langFileName) -> BlockHandler.registerBlockWithRecipe(number, id, langFileName,
                material.getNamespace(), stairs,
                ItemGroupHelper.createAfterConsumer(CreativeModeTabs.BUILDING_BLOCKS, () -> material.getPreviousBlock(blockType)),
                (blockModelGenerators, block) -> {
                    TextureMapping textureMapping = new TextureMapping()
                            .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(sourceBlock, "_top"))
                            .put(TextureSlot.TOP, TextureMapping.getBlockTexture(sourceBlock, "_top"))
                            .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(sourceBlock, (sourceBlock instanceof RotatedPillarBlock ? "" : "_side")))
                            .put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(sourceBlock, (sourceBlock instanceof RotatedPillarBlock ? "" : "_side")));
                    ResourceLocation inner = ModelTemplates.STAIRS_INNER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
                    ResourceLocation straight = ModelTemplates.STAIRS_STRAIGHT.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
                    ResourceLocation outer = ModelTemplates.STAIRS_OUTER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
                    blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createStairs((Block) block, inner, straight, outer));
                    blockModelGenerators.delegateItemModel((Block) block, straight);
                },
                ((finishedRecipeConsumer, item) -> {
                    FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer, RecipeCategory.BUILDING_BLOCKS, item, sourceBlock);
                    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, item, 4).define('P', sourceBlockTag)
                            .pattern("P  ")
                            .pattern("PP ")
                            .pattern("PPP")
                            .unlockedBy("has_" + material + "_logs", FabricRecipeProvider.has(sourceBlockTag)).save(finishedRecipeConsumer);
                }),
                BlockLootSubProvider::dropSelf,
                blockTags, itemTags);
        return createStairs(number, material, blockType, sourceBlock, blockSupplier, blockTags, itemTags);
    }

    public static Block createStairs(int number, BlockMaterial material, String blockType, Block sourceBlock, String sourceBlockType, Block stairs, BiConsumer<BlockModelGenerators, ItemLike> modelGenerator) {
        List<TagKey<Block>> blockTags = material.getBlockTags(List.of(blockType));
        List<TagKey<Item>> itemTags = material.getItemTags(List.of(blockType));
        TagKey<Item> sourceBlockTag = material.getItemTag(sourceBlockType);
        BiFunction<String, String, Block> blockSupplier = (String id, String langFileName) -> BlockHandler.registerBlockWithRecipe(number, id, langFileName,
                material.getNamespace(), stairs,
                ItemGroupHelper.createAfterConsumer(CreativeModeTabs.BUILDING_BLOCKS, () -> material.getPreviousBlock(blockType)),
                modelGenerator,
                ((finishedRecipeConsumer, item) -> {
                    FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer, RecipeCategory.BUILDING_BLOCKS, item, sourceBlock);
                    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, item, 4).define('P', sourceBlockTag)
                            .pattern("P  ")
                            .pattern("PP ")
                            .pattern("PPP")
                            .unlockedBy("has_" + material + "_logs", FabricRecipeProvider.has(sourceBlockTag)).save(finishedRecipeConsumer);
                }),
                BlockLootSubProvider::dropSelf,
                blockTags, itemTags);
        return createStairs(number, material, blockType, sourceBlock, blockSupplier, blockTags, itemTags);
    }

    public static Block createSlab(int number, BlockMaterial material, String blockType, Block sourceBlock, String sourceBlockType, Supplier<Block> slabSupplier) {
        var sourceBlockId = BuiltInRegistries.BLOCK.getKey(sourceBlock).getPath();
        String blockId = sourceBlockId + "_slab";
        
        List<TagKey<Block>> blockTags = material.getBlockTags(List.of(blockType));
        List<TagKey<Item>> itemTags = material.getItemTags(List.of(blockType));
        TagKey<Item> sourceBlockTag = material.getItemTag(sourceBlockType);
        BiFunction<String, String, Block> blockSupplier = (String id, String langFileName) ->  BlockHandler.registerBlockWithRecipe(number, id, langFileName,
                material.getNamespace(), slabSupplier.get(),
                ItemGroupHelper.createAfterConsumer(CreativeModeTabs.BUILDING_BLOCKS, () -> material.getPreviousBlock(blockType)),
                (blockModelGenerators, block) -> {
                    TextureMapping textureMapping = new TextureMapping()
                            .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(sourceBlock, "_top"))
                            .put(TextureSlot.TOP, TextureMapping.getBlockTexture(sourceBlock, "_top"))
                            .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(sourceBlock, (sourceBlock instanceof RotatedPillarBlock ? "" : "_side") ))
                            .put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(sourceBlock, (sourceBlock instanceof RotatedPillarBlock ? "" : "_side")));
                    ResourceLocation bottom = ModelTemplates.SLAB_BOTTOM.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
                    ResourceLocation top = ModelTemplates.SLAB_TOP.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
                    blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSlab((Block) block, bottom, top,
                            ModelLocationUtils.getModelLocation(sourceBlock)));
                    blockModelGenerators.delegateItemModel((Block) block, bottom);
                },
                ((finishedRecipeConsumer, item) -> {
                    FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer, RecipeCategory.BUILDING_BLOCKS, item, sourceBlock, 2);
                    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, item, 6).define('P', sourceBlockTag)
                            .pattern("PPP")
                            .unlockedBy("has_" + material + "_logs", FabricRecipeProvider.has(sourceBlockTag)).save(finishedRecipeConsumer);
                }),
                BlockLootSubProvider::dropSelf,
                blockTags, itemTags);
        return createBlock(material, blockType, blockId, blockSupplier, blockTags, itemTags);
    }

    public static Block createWall(int number, BlockMaterial material, String blockType, Block sourceBlock, String sourceBlockType, Supplier<Block> wallSupplier) {
        var sourceBlockId = BuiltInRegistries.BLOCK.getKey(sourceBlock).getPath();
        String blockId = sourceBlockId + "_wall";
        List<TagKey<Block>> blockTags = material.getBlockTags(List.of(blockType));
        List<TagKey<Item>> itemTags = material.getItemTags(List.of(blockType));
        TagKey<Item> sourceBlockTag = material.getItemTag(sourceBlockType);
        BiFunction<String, String, Block> blockSupplier = (String id, String langFileName) ->  BlockHandler.registerBlockWithRecipe(number, id, langFileName,
                material.getNamespace(), wallSupplier.get(),
                ItemGroupHelper.createAfterConsumer(CreativeModeTabs.BUILDING_BLOCKS, () -> material.getPreviousBlock(blockType)),
                (blockModelGenerators, block) -> {
                    TextureMapping textureMapping = new TextureMapping()
                            .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(sourceBlock, "_top"))
                            .put(TextureSlot.TOP, TextureMapping.getBlockTexture(sourceBlock, "_top"))
                            .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(sourceBlock, (sourceBlock instanceof RotatedPillarBlock ? "" : "_side")))
                            .put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(sourceBlock, (sourceBlock instanceof RotatedPillarBlock ? "" : "_side")));
                    ResourceLocation inventory = UpgradedVanillaModelTemplates.LOG_WALL_INVENTORY.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
                    ResourceLocation post = UpgradedVanillaModelTemplates.LOG_WALL_POST.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
                    ResourceLocation tallSide = UpgradedVanillaModelTemplates.LOG_WALL_SIDE_TALL.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
                    ResourceLocation lowSide = UpgradedVanillaModelTemplates.LOG_WALL_SIDE.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
                    blockModelGenerators.delegateItemModel((Block) block, inventory);
                    blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createWall((Block) block, post, lowSide, tallSide));
                },
                ((finishedRecipeConsumer, item) -> {
                    FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer, RecipeCategory.BUILDING_BLOCKS, item, sourceBlock);
                    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, item, 6).define('P', sourceBlockTag)
                            .pattern("PPP")
                            .pattern("PPP")
                            .unlockedBy("has_" + material + "_stripped_logs", FabricRecipeProvider.has(sourceBlockTag))
                            .save(finishedRecipeConsumer);
                }),
                BlockLootSubProvider::dropSelf,
                blockTags, itemTags);
        return createBlock(material, blockType, blockId, blockSupplier, blockTags, itemTags);
    }
}
