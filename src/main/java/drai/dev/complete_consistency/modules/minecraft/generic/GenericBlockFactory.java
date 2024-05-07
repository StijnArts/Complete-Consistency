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

    public static Block createStairs(BlockMaterial material, String blockType, Block sourceBlock, String sourceBlockType, Supplier<Block> stairsSupplier) {

        return createStairs(material, blockType, sourceBlock, sourceBlockType, stairsSupplier.get());
    }

    public static Block createStairs(BlockMaterial material, String blockType, Block sourceBlock, BiFunction<String, String, Block> blockSupplier,
                                     List<TagKey<Block>> blockTags,
                                     List<TagKey<Item>> itemTags) {
        var sourceBlockId = BuiltInRegistries.BLOCK.getKey(sourceBlock).getPath();
        String blockId = sourceBlockId + "_stairs";
        return createBlock(material, blockType, blockId, blockSupplier, blockTags, itemTags);
    }

    public static Block createStairs(BlockMaterial material, String blockType, Block sourceBlock, String sourceBlockType, Block stairs) {
        List<TagKey<Block>> blockTags = material.getBlockTags(List.of(blockType));
        List<TagKey<Item>> itemTags = material.getItemTags(List.of(blockType));
        TagKey<Item> sourceBlockTag = material.getItemTag(sourceBlockType);
        BiFunction<String, String, Block> blockSupplier = (String id, String langFileName) -> BlockHandler.registerBlockWithRecipe(id, langFileName,
                material.getNamespace(), stairs,
                item->ItemGroupHelper.addToGroup(CreativeModeTabs.BUILDING_BLOCKS, item, () -> material.getPreviousBlock(blockType)),
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
        return createStairs(material, blockType, sourceBlock, blockSupplier, blockTags, itemTags);
    }

    public static Block createStairs(BlockMaterial material, String blockType, Block sourceBlock, String sourceBlockType, Block stairs, BiConsumer<BlockModelGenerators, ItemLike> modelGenerator) {
        List<TagKey<Block>> blockTags = material.getBlockTags(List.of(blockType));
        List<TagKey<Item>> itemTags = material.getItemTags(List.of(blockType));
        TagKey<Item> sourceBlockTag = material.getItemTag(sourceBlockType);
        BiFunction<String, String, Block> blockSupplier = (String id, String langFileName) -> BlockHandler.registerBlockWithRecipe(id, langFileName,
                material.getNamespace(), stairs,
                item->ItemGroupHelper.addToGroup(CreativeModeTabs.BUILDING_BLOCKS, item, () -> material.getPreviousBlock(blockType)),
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
        return createStairs(material, blockType, sourceBlock, blockSupplier, blockTags, itemTags);
    }

    public static Block createSlab(BlockMaterial material, String blockType, Block sourceBlock, String sourceBlockType, Supplier<Block> slabSupplier) {
        var sourceBlockId = BuiltInRegistries.BLOCK.getKey(sourceBlock).getPath();
        String blockId = sourceBlockId + "_slab";
        
        List<TagKey<Block>> blockTags = material.getBlockTags(List.of(blockType));
        List<TagKey<Item>> itemTags = material.getItemTags(List.of(blockType));
        TagKey<Item> sourceBlockTag = material.getItemTag(sourceBlockType);
        BiFunction<String, String, Block> blockSupplier = (String id, String langFileName) ->  BlockHandler.registerBlockWithRecipe(id, langFileName,
                material.getNamespace(), slabSupplier.get(),
                item->ItemGroupHelper.addToGroup(CreativeModeTabs.BUILDING_BLOCKS, item, () -> material.getPreviousBlock(blockType)),
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

    public static Block createWall(BlockMaterial material, String blockType, Block sourceBlock, String sourceBlockType, Supplier<Block> wallSupplier) {
        var sourceBlockId = BuiltInRegistries.BLOCK.getKey(sourceBlock).getPath();
        String blockId = sourceBlockId + "_wall";
        List<TagKey<Block>> blockTags = material.getBlockTags(List.of(blockType));
        List<TagKey<Item>> itemTags = material.getItemTags(List.of(blockType));
        TagKey<Item> sourceBlockTag = material.getItemTag(sourceBlockType);
        BiFunction<String, String, Block> blockSupplier = (String id, String langFileName) ->  BlockHandler.registerBlockWithRecipe(id, langFileName,
                material.getNamespace(), wallSupplier.get(),
                item->ItemGroupHelper.addToGroup(CreativeModeTabs.BUILDING_BLOCKS, item, () -> material.getPreviousBlock(blockType)),
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

    public static Block createButton(BlockMaterial material, String blockType, Block sourceBlock, String sourceBlockType, Supplier<Block> buttonSupplieer) {
        var sourceBlockId = BuiltInRegistries.BLOCK.getKey(sourceBlock).getPath();
        String blockId = sourceBlockId + "_button";
        List<TagKey<Block>> blockTags = material.getBlockTags(List.of(blockType));
        List<TagKey<Item>> itemTags = material.getItemTags(List.of(blockType));
        TagKey<Item> sourceBlockTag = material.getItemTag(sourceBlockType);
        BiFunction<String, String, Block> blockSupplier = (String id, String langFileName) ->  BlockHandler.registerBlockWithRecipe(id, langFileName,
                material.getNamespace(), buttonSupplieer.get(),
                item->ItemGroupHelper.addToGroup(CreativeModeTabs.BUILDING_BLOCKS, item, () -> material.getPreviousBlock(blockType)),
                (blockModelGenerators,block)->{
                    TextureMapping textureMapping = TextureMapping.cube(sourceBlock);
                    ResourceLocation normal = ModelTemplates.BUTTON.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
                    ResourceLocation pressed = ModelTemplates.BUTTON_PRESSED.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
                    ResourceLocation inventory = ModelTemplates.BUTTON_INVENTORY.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
                    blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createButton((Block) block,normal, pressed));
                    blockModelGenerators.delegateItemModel((Block) block,inventory);
                },
                ((finishedRecipeConsumer, item) -> {
                    FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer, RecipeCategory.REDSTONE, item, sourceBlock);
                    ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, item,1).requires(sourceBlockTag)
                            .unlockedBy("has_"+material+"_planks", FabricRecipeProvider.has(sourceBlockTag))
                            .save(finishedRecipeConsumer);
                }),
                BlockLootSubProvider::dropSelf,
                blockTags, itemTags);
        return createBlock(material, blockType, blockId, blockSupplier, blockTags, itemTags);
    }

    public static Block createPressurePlate(BlockMaterial material, String blockType, Block sourceBlock, String sourceBlockType, Supplier<Block> pressurePlateSupplier) {
        var sourceBlockId = BuiltInRegistries.BLOCK.getKey(sourceBlock).getPath();
        String blockId = sourceBlockId + "_pressure_plate";
        List<TagKey<Block>> blockTags = material.getBlockTags(List.of(blockType));
        List<TagKey<Item>> itemTags = material.getItemTags(List.of(blockType));
        TagKey<Item> sourceBlockTag = material.getItemTag(sourceBlockType);
        BiFunction<String, String, Block> blockSupplier = (String id, String langFileName) ->  BlockHandler.registerBlockWithRecipe(id, langFileName,
                material.getNamespace(), pressurePlateSupplier.get(),
                item->ItemGroupHelper.addToGroup(CreativeModeTabs.BUILDING_BLOCKS, item, () -> material.getPreviousBlock(blockType)),
                (blockModelGenerators,block)->{
                    TextureMapping textureMapping = TextureMapping.cube(sourceBlock);
                    ResourceLocation up = ModelTemplates.PRESSURE_PLATE_UP.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
                    ResourceLocation down = ModelTemplates.PRESSURE_PLATE_DOWN.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
                    blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createPressurePlate((Block) block,up, down));
                    blockModelGenerators.delegateItemModel((Block) block,up);
                },
                ((finishedRecipeConsumer, item) -> {
                    FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer, RecipeCategory.REDSTONE, item, sourceBlock);
                    ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, item,1).define('P',sourceBlockTag)
                            .pattern("PP")
                            .unlockedBy("has_"+material+"_planks", FabricRecipeProvider.has(sourceBlockTag))
                            .save(finishedRecipeConsumer);
                }),
                BlockLootSubProvider::dropSelf,
                blockTags, itemTags);
        return createBlock(material, blockType, blockId, blockSupplier, blockTags, itemTags);
    }

    public static Block createTrapdoor(BlockMaterial material, String blockType, Block sourceBlock, String sourceBlockType, Supplier<Block> trapdoorSupplier) {
        var sourceBlockId = BuiltInRegistries.BLOCK.getKey(sourceBlock).getPath();
        String blockId = sourceBlockId + "_trapdoor";
        List<TagKey<Block>> blockTags = material.getBlockTags(List.of(blockType));
        List<TagKey<Item>> itemTags = material.getItemTags(List.of(blockType));
        TagKey<Item> sourceBlockTag = material.getItemTag(sourceBlockType);
        BiFunction<String, String, Block> blockSupplier = (String id, String langFileName) ->  BlockHandler.registerBlockWithRecipe(id, langFileName,
                material.getNamespace(), trapdoorSupplier.get(),
                item->ItemGroupHelper.addToGroup(CreativeModeTabs.BUILDING_BLOCKS, item, () -> material.getPreviousBlock(blockType)),
                (blockModelGenerators,block)->{
                    TextureMapping textureMapping = TextureMapping.cube((Block) block);
                    ResourceLocation bottom = ModelTemplates.ORIENTABLE_TRAPDOOR_BOTTOM.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
                    ResourceLocation open = ModelTemplates.ORIENTABLE_TRAPDOOR_OPEN.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
                    ResourceLocation top = ModelTemplates.ORIENTABLE_TRAPDOOR_TOP.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
                    blockModelGenerators.delegateItemModel((Block) block, bottom);
                    blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createTrapdoor((Block) block, top, bottom,open));
                },
                ((finishedRecipeConsumer, item) -> {
                    FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer, RecipeCategory.REDSTONE, item, sourceBlock);
                    ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, item,2).define('P',sourceBlockTag)
                            .pattern("PPP")
                            .pattern("PPP")
                            .unlockedBy("has_"+material+"_planks", FabricRecipeProvider.has(sourceBlockTag))
                            .save(finishedRecipeConsumer);
                }),
                BlockLootSubProvider::dropSelf,
                blockTags, itemTags);
        return createBlock(material, blockType, blockId, blockSupplier, blockTags, itemTags);
    }

    public static Block createFenceBlock(BlockMaterial material, String blockType, Block sourceBlock, Item stick, String sourceBlockType, Supplier<Block> fenceSupplier) {
        var sourceBlockId = BuiltInRegistries.BLOCK.getKey(sourceBlock).getPath();
        String blockId = sourceBlockId + "_fence";
        List<TagKey<Block>> blockTags = material.getBlockTags(List.of(blockType));
        List<TagKey<Item>> itemTags = material.getItemTags(List.of(blockType));
        TagKey<Item> sourceBlockTag = material.getItemTag(sourceBlockType);
        BiFunction<String, String, Block> blockSupplier = (String id, String langFileName) ->  BlockHandler.registerBlockWithRecipe(id, langFileName,
                material.getNamespace(), fenceSupplier.get(),
                item->ItemGroupHelper.addToGroup(CreativeModeTabs.BUILDING_BLOCKS, item, () -> material.getPreviousBlock(blockType)),
                (blockModelGenerators,block)->{
                    TextureMapping textureMapping = TextureMapping.cube(sourceBlock);
                    ResourceLocation post = ModelTemplates.FENCE_POST.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
                    ResourceLocation side = ModelTemplates.FENCE_SIDE.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
                    ResourceLocation inventory = ModelTemplates.FENCE_INVENTORY.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
                    blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createFence((Block) block, post, side));
                    blockModelGenerators.delegateItemModel((Block) block,inventory);
                },
                ((finishedRecipeConsumer, item) -> {
                    FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer, RecipeCategory.REDSTONE, item, sourceBlock);
                    ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, item,2).define('P',sourceBlockTag).define('S',stick)
                            .pattern("PSP")
                            .pattern("PSP")
                            .unlockedBy("has_"+material+"_planks", FabricRecipeProvider.has(sourceBlockTag))
                            .save(finishedRecipeConsumer);
                }),
                BlockLootSubProvider::dropSelf,
                blockTags, itemTags);
        return createBlock(material, blockType, blockId, blockSupplier, blockTags, itemTags);
    }

    public static Block createFenceGateBlock(BlockMaterial material, String blockType, Block sourceBlock, Item stick, String sourceBlockType, Supplier<Block> fenceGateSupplier) {
        var sourceBlockId = BuiltInRegistries.BLOCK.getKey(sourceBlock).getPath();
        String blockId = sourceBlockId + "_fence_gate";
        List<TagKey<Block>> blockTags = material.getBlockTags(List.of(blockType));
        List<TagKey<Item>> itemTags = material.getItemTags(List.of(blockType));
        TagKey<Item> sourceBlockTag = material.getItemTag(sourceBlockType);
        BiFunction<String, String, Block> blockSupplier = (String id, String langFileName) ->  BlockHandler.registerBlockWithRecipe(id, langFileName,
                material.getNamespace(), fenceGateSupplier.get(),
                item->ItemGroupHelper.addToGroup(CreativeModeTabs.BUILDING_BLOCKS, item, () -> material.getPreviousBlock(blockType)),
                (blockModelGenerators,block)->{
                    TextureMapping textureMapping = TextureMapping.cube(sourceBlock);
                    ResourceLocation closed = ModelTemplates.FENCE_GATE_CLOSED.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
                    ResourceLocation open = ModelTemplates.FENCE_GATE_OPEN.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
                    ResourceLocation closedWall = ModelTemplates.FENCE_GATE_WALL_CLOSED.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
                    ResourceLocation openWall = ModelTemplates.FENCE_GATE_WALL_OPEN.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
                    blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createFenceGate((Block) block, open, closed,openWall,closedWall, true));
                },
                ((finishedRecipeConsumer, item) -> {
                    FabricRecipeProvider.stonecutterResultFromBase(finishedRecipeConsumer, RecipeCategory.REDSTONE, item, sourceBlock);
                    ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, item,2).define('P',sourceBlockTag).define('S',stick)
                            .pattern("SPS")
                            .pattern("SPS")
                            .unlockedBy("has_"+material+"_planks", FabricRecipeProvider.has(sourceBlockTag))
                            .save(finishedRecipeConsumer);
                }),
                BlockLootSubProvider::dropSelf,
                blockTags, itemTags);
        return createBlock(material, blockType, blockId, blockSupplier, blockTags, itemTags);
    }
}
