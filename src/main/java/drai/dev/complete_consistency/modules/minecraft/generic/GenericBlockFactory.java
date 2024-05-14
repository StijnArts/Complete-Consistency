package drai.dev.complete_consistency.modules.minecraft.generic;

import drai.dev.complete_consistency.helpers.*;
import drai.dev.complete_consistency.helpers.block.*;
import drai.dev.complete_consistency.helpers.itemgroup.*;
import drai.dev.complete_consistency.helpers.languages.*;
import drai.dev.complete_consistency.materials.*;
import drai.dev.complete_consistency.model.*;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.minecraft.advancements.critereon.*;
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
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.storage.loot.*;
import net.minecraft.world.level.storage.loot.entries.*;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraft.world.level.storage.loot.providers.number.*;
import org.jetbrains.annotations.*;

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
            System.out.println("Created a block through the generic block factory");
            material.addBlock(blockType, returnBlock);
            return returnBlock;
        } else {
            //TODO add the material specific recipes to the pre-existing blocks
            System.out.println("Block existed already");
            var block = material.getBlock(blockType);
            var foundBlockId = BuiltInRegistries.BLOCK.getKey(block).getPath();
            if(!foundBlockId.contains(material.getName())) {
                blockId = material.getName() + "_" + foundBlockId;
                blockLangFileName = "";
                for (String segment : blockId.split("_")) {
                    blockLangFileName = blockLangFileName + " " + StringUtil.capitalizeWord(segment);
                }
                LanguageHelper.getEnglishTranslations().addReplacementTranslations(block.asItem(), blockLangFileName.trim());
                System.out.println("Renamed "+foundBlockId + " to " + blockLangFileName.trim());
            }
            TagHelper.addBlockTags(block, blockTags);
            TagHelper.addItemTags(block.asItem(), itemTags);
            return null;
        }
    }

    public static Block createStairs(BlockMaterial material, String blockType, Block sourceBlock, String sourceBlockType, Supplier<Block> stairsSupplier) {
        return createStairs(material, blockType, sourceBlock, sourceBlockType, stairsSupplier, sourceBlock, null);
    }

    public static Block createStairs(BlockMaterial material, String blockType, Block sourceBlock, String sourceBlockType, Supplier<Block> stairsSupplier, String name) {
        return createStairs(material, blockType, sourceBlock, sourceBlockType, stairsSupplier, sourceBlock, name);
    }

    public static Block createStairs(BlockMaterial material, String blockType, Block sourceBlock, String sourceBlockType, Supplier<Block> stairsSupplier, Block textureBlock) {
        return createStairs(material, blockType, sourceBlock, sourceBlockType, stairsSupplier, textureBlock, null);
    }

    public static Block createStairs(BlockMaterial material, String blockType, Block sourceBlock, String sourceBlockType, Supplier<Block> stairsSupplier, Block textureBlock, @Nullable String blockId) {
        if(blockId == null){
            var sourceBlockId = BuiltInRegistries.BLOCK.getKey(sourceBlock).getPath();
            blockId = sourceBlockId + "_stairs";
        }
        List<TagKey<Block>> blockTags = material.getBlockTags(List.of(blockType));
        List<TagKey<Item>> itemTags = material.getItemTags(List.of(blockType));
        TagKey<Item> sourceBlockTag = material.getItemTag(sourceBlockType);
        BiFunction<String, String, Block> blockSupplier = (String id, String langFileName) -> BlockHandler.registerBlockWithRecipe(id, langFileName,
                material.getNamespace(), stairsSupplier.get(),
                item->ItemGroupHelper.addToGroup(CreativeModeTabs.BUILDING_BLOCKS, material, blockType, false),
                (blockModelGenerators, block) -> {
                    TextureMapping textureMapping = new TextureMapping()
                            .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(textureBlock))
                            .put(TextureSlot.TOP, TextureMapping.getBlockTexture(textureBlock))
                            .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(textureBlock))
                            .put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(textureBlock));
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
        return createBlock(material, blockType, blockId, blockSupplier, blockTags, itemTags);
    }

    public static Block createSlab(BlockMaterial material, String blockType, Block sourceBlock, String sourceBlockType, Supplier<Block> stairsSupplier) {
        return createSlab(material, blockType, sourceBlock, sourceBlockType, stairsSupplier, sourceBlock, null);
    }

    public static Block createSlab(BlockMaterial material, String blockType, Block sourceBlock, String sourceBlockType, Supplier<Block> stairsSupplier, String name) {
        return createSlab(material, blockType, sourceBlock, sourceBlockType, stairsSupplier, sourceBlock, name);
    }

    public static Block createSlab(BlockMaterial material, String blockType, Block sourceBlock, String sourceBlockType, Supplier<Block> stairsSupplier, Block textureBlock) {
        return createSlab(material, blockType, sourceBlock, sourceBlockType, stairsSupplier, textureBlock, null);
    }

    public static Block createSlab(BlockMaterial material, String blockType, Block sourceBlock, String sourceBlockType, Supplier<Block> slabSupplier, Block textureBlock, @Nullable String blockId) {
        if(blockId == null) {
            var sourceBlockId = BuiltInRegistries.BLOCK.getKey(sourceBlock).getPath();
            blockId = sourceBlockId + "_slab";
        }
        List<TagKey<Block>> blockTags = material.getBlockTags(List.of(blockType));
        List<TagKey<Item>> itemTags = material.getItemTags(List.of(blockType));
        TagKey<Item> sourceBlockTag = material.getItemTag(sourceBlockType);
        BiFunction<String, String, Block> blockSupplier = (String id, String langFileName) ->  BlockHandler.registerBlockWithRecipe(id, langFileName,
                material.getNamespace(), slabSupplier.get(),
                item->ItemGroupHelper.addToGroup(CreativeModeTabs.BUILDING_BLOCKS, material, blockType, false),
                (blockModelGenerators, block) -> {
                    TextureMapping textureMapping = new TextureMapping()
                            .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(textureBlock))
                            .put(TextureSlot.TOP, TextureMapping.getBlockTexture(textureBlock))
                            .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(textureBlock))
                            .put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(textureBlock));
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

    public static Block createWall(BlockMaterial material, String blockType, Block sourceBlock, String sourceBlockType, Supplier<Block> stairsSupplier) {
        return createWall(material, blockType, sourceBlock, sourceBlockType, stairsSupplier, sourceBlock, null);
    }

    public static Block createWall(BlockMaterial material, String blockType, Block sourceBlock, String sourceBlockType, Supplier<Block> stairsSupplier, String name) {
        return createWall(material, blockType, sourceBlock, sourceBlockType, stairsSupplier, sourceBlock, name);
    }

    public static Block createWall(BlockMaterial material, String blockType, Block sourceBlock, String sourceBlockType, Supplier<Block> stairsSupplier, Block textureBlock) {
        return createWall(material, blockType, sourceBlock, sourceBlockType, stairsSupplier, textureBlock, null);
    }

    public static Block createWall(BlockMaterial material, String blockType, Block sourceBlock, String sourceBlockType, Supplier<Block> wallSupplier, Block textureBlock, @Nullable String blockId) {
        if(blockId == null) {
            var sourceBlockId = BuiltInRegistries.BLOCK.getKey(sourceBlock).getPath();
            blockId = sourceBlockId + "_wall";
        }
        List<TagKey<Block>> blockTags = material.getBlockTags(List.of(blockType));
        List<TagKey<Item>> itemTags = material.getItemTags(List.of(blockType));
        TagKey<Item> sourceBlockTag = material.getItemTag(sourceBlockType);
        BiFunction<String, String, Block> blockSupplier = (String id, String langFileName) ->  BlockHandler.registerBlockWithRecipe(id, langFileName,
                material.getNamespace(), wallSupplier.get(),
                item->ItemGroupHelper.addToGroup(CreativeModeTabs.BUILDING_BLOCKS, material, blockType, false),
                (blockModelGenerators, block) -> {
                    TextureMapping textureMapping = new TextureMapping()
                            .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(textureBlock))
                            .put(TextureSlot.TOP, TextureMapping.getBlockTexture(textureBlock))
                            .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(textureBlock))
                            .put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(textureBlock));
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

    public static Block createButton(BlockMaterial material, String blockType, Block sourceBlock, String sourceBlockType, Supplier<Block> stairsSupplier) {
        return createButton(material, blockType, sourceBlock, sourceBlockType, stairsSupplier, sourceBlock, null);
    }

    public static Block createButton(BlockMaterial material, String blockType, Block sourceBlock, String sourceBlockType, Supplier<Block> stairsSupplier, String name) {
        return createButton(material, blockType, sourceBlock, sourceBlockType, stairsSupplier, sourceBlock, name);
    }

    public static Block createButton(BlockMaterial material, String blockType, Block sourceBlock, String sourceBlockType, Supplier<Block> stairsSupplier, Block textureBlock) {
        return createButton(material, blockType, sourceBlock, sourceBlockType, stairsSupplier, textureBlock, null);
    }

    public static Block createButton(BlockMaterial material, String blockType, Block sourceBlock, String sourceBlockType, Supplier<Block> buttonSupplier, Block textureBlock, @Nullable String blockId) {
        if(blockId == null) {
            var sourceBlockId = BuiltInRegistries.BLOCK.getKey(sourceBlock).getPath();
            blockId = sourceBlockId + "_button";
        }
        List<TagKey<Block>> blockTags = material.getBlockTags(List.of(blockType));
        List<TagKey<Item>> itemTags = material.getItemTags(List.of(blockType));
        TagKey<Item> sourceBlockTag = material.getItemTag(sourceBlockType);
        BiFunction<String, String, Block> blockSupplier = (String id, String langFileName) ->  BlockHandler.registerBlockWithRecipe(id, langFileName,
                material.getNamespace(), buttonSupplier.get(),
                item->ItemGroupHelper.addToGroup(CreativeModeTabs.BUILDING_BLOCKS, material, blockType, false),
                (blockModelGenerators,block)->{
                    TextureMapping textureMapping = TextureMapping.cube(textureBlock);
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

    public static Block createPressurePlate(BlockMaterial material, String blockType, Block sourceBlock, String sourceBlockType, Supplier<Block> stairsSupplier) {
        return createPressurePlate(material, blockType, sourceBlock, sourceBlockType, stairsSupplier, sourceBlock, null);
    }

    public static Block createPressurePlate(BlockMaterial material, String blockType, Block sourceBlock, String sourceBlockType, Supplier<Block> stairsSupplier, String name) {
        return createPressurePlate(material, blockType, sourceBlock, sourceBlockType, stairsSupplier, sourceBlock, name);
    }

    public static Block createPressurePlate(BlockMaterial material, String blockType, Block sourceBlock, String sourceBlockType, Supplier<Block> stairsSupplier, Block textureBlock) {
        return createPressurePlate(material, blockType, sourceBlock, sourceBlockType, stairsSupplier, textureBlock, null);
    }

    public static Block createPressurePlate(BlockMaterial material, String blockType, Block sourceBlock, String sourceBlockType, Supplier<Block> pressurePlateSupplier, Block textureBlock, @Nullable String blockId) {
        if(blockId == null) {
            var sourceBlockId = BuiltInRegistries.BLOCK.getKey(sourceBlock).getPath();
            blockId = sourceBlockId + "_pressure_plate";
        }
        List<TagKey<Block>> blockTags = material.getBlockTags(List.of(blockType));
        List<TagKey<Item>> itemTags = material.getItemTags(List.of(blockType));
        TagKey<Item> sourceBlockTag = material.getItemTag(sourceBlockType);
        BiFunction<String, String, Block> blockSupplier = (String id, String langFileName) ->  BlockHandler.registerBlockWithRecipe(id, langFileName,
                material.getNamespace(), pressurePlateSupplier.get(),
                item-> ItemGroupHelper.addToGroup(CreativeModeTabs.BUILDING_BLOCKS, material, blockType, false),
                (blockModelGenerators,block)->{
                    TextureMapping textureMapping = TextureMapping.cube(textureBlock);
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

    public static Block createTrapdoor(BlockMaterial material, String blockType, Block sourceBlock, String sourceBlockType, Supplier<Block> stairsSupplier) {
        return createTrapdoor(material, blockType, sourceBlock, sourceBlockType, stairsSupplier, (block)->TextureMapping.cube((Block) block), null);
    }

    public static Block createTrapdoor(BlockMaterial material, String blockType, Block sourceBlock, String sourceBlockType, Supplier<Block> stairsSupplier, String name) {
        return createTrapdoor(material, blockType, sourceBlock, sourceBlockType, stairsSupplier, (block)->TextureMapping.cube((Block) block), name);
    }

    public static Block createGenericTrapdoor(BlockMaterial material, String blockType, Block sourceBlock, String sourceBlockType, Supplier<Block> stairsSupplier, Block textureBlock) {
        return createTrapdoor(material, blockType, sourceBlock, sourceBlockType, stairsSupplier, (block)->TextureMapping.cube(textureBlock), null);
    }

    public static Block createTrapdoor(BlockMaterial material, String blockType, Block sourceBlock, String sourceBlockType, Supplier<Block> trapdoorSupplier, Function<Block, TextureMapping> textureMappingFunction, @Nullable String blockId) {
        if(blockId == null) {
            var sourceBlockId = BuiltInRegistries.BLOCK.getKey(sourceBlock).getPath();
            blockId = sourceBlockId + "_trapdoor";
        }
        List<TagKey<Block>> blockTags = material.getBlockTags(List.of(blockType));
        List<TagKey<Item>> itemTags = material.getItemTags(List.of(blockType));
        TagKey<Item> sourceBlockTag = material.getItemTag(sourceBlockType);
        BiFunction<String, String, Block> blockSupplier = (String id, String langFileName) ->  BlockHandler.registerBlockWithRecipe(id, langFileName,
                material.getNamespace(), trapdoorSupplier.get(),
                item->ItemGroupHelper.addToGroup(CreativeModeTabs.BUILDING_BLOCKS, material, blockType, false),
                (blockModelGenerators,block)->{
                    var textureMapping = textureMappingFunction.apply((Block) block);
                    ResourceLocation bottom = ModelTemplates.ORIENTABLE_TRAPDOOR_BOTTOM.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
                    ResourceLocation open = ModelTemplates.ORIENTABLE_TRAPDOOR_OPEN.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
                    ResourceLocation top = ModelTemplates.ORIENTABLE_TRAPDOOR_TOP.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
                    blockModelGenerators.delegateItemModel((Block) block, bottom);
                    blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createOrientableTrapdoor((Block) block, top, bottom,open));
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

    public static Block createFenceBlock(BlockMaterial material, String blockType, Block sourceBlock, Item stick, String sourceBlockType, Supplier<Block> stairsSupplier) {
        return createFenceBlock(material, blockType, sourceBlock, stick, sourceBlockType, stairsSupplier, sourceBlock, null);
    }

    public static Block createFenceBlock(BlockMaterial material, String blockType, Block sourceBlock, Item stick, String sourceBlockType, Supplier<Block> stairsSupplier, String name) {
        return createFenceBlock(material, blockType, sourceBlock, stick, sourceBlockType, stairsSupplier, sourceBlock, name);
    }

    public static Block createFenceBlock(BlockMaterial material, String blockType, Block sourceBlock, Item stick, String sourceBlockType, Supplier<Block> stairsSupplier, Block textureBlock) {
        return createFenceBlock(material, blockType, sourceBlock, stick, sourceBlockType, stairsSupplier, textureBlock, null);
    }

    public static Block createFenceBlock(BlockMaterial material, String blockType, Block sourceBlock, Item stick, String sourceBlockType, Supplier<Block> fenceSupplier, Block textureBlock, @Nullable String blockId) {
        if(blockId == null) {
            var sourceBlockId = BuiltInRegistries.BLOCK.getKey(sourceBlock).getPath();
            blockId = sourceBlockId + "_fence";
        }
        List<TagKey<Block>> blockTags = material.getBlockTags(List.of(blockType));
        List<TagKey<Item>> itemTags = material.getItemTags(List.of(blockType));
        TagKey<Item> sourceBlockTag = material.getItemTag(sourceBlockType);
        BiFunction<String, String, Block> blockSupplier = (String id, String langFileName) ->  BlockHandler.registerBlockWithRecipe(id, langFileName,
                material.getNamespace(), fenceSupplier.get(),
                item->ItemGroupHelper.addToGroup(CreativeModeTabs.BUILDING_BLOCKS, material, blockType, false),
                (blockModelGenerators,block)->{
                    TextureMapping textureMapping = TextureMapping.cube(textureBlock);
                    ResourceLocation post = ModelTemplates.FENCE_POST.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
                    ResourceLocation side = ModelTemplates.FENCE_SIDE.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
                    ResourceLocation inventory = ModelTemplates.FENCE_INVENTORY.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
                    blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createFence((Block) block, post, side));
                    blockModelGenerators.delegateItemModel((Block) block, inventory);
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

    public static Block createFenceGateBlock(BlockMaterial material, String blockType, Block sourceBlock, Item stick, String sourceBlockType, Supplier<Block> stairsSupplier) {
        return createFenceGateBlock(material, blockType, sourceBlock, stick, sourceBlockType, stairsSupplier, sourceBlock, null);
    }

    public static Block createFenceGateBlock(BlockMaterial material, String blockType, Block sourceBlock, Item stick, String sourceBlockType, Supplier<Block> stairsSupplier, String name) {
        return createFenceGateBlock(material, blockType, sourceBlock, stick, sourceBlockType, stairsSupplier, sourceBlock, name);
    }

    public static Block createFenceGateBlock(BlockMaterial material, String blockType, Block sourceBlock, Item stick, String sourceBlockType, Supplier<Block> stairsSupplier, Block textureBlock) {
        return createFenceGateBlock(material, blockType, sourceBlock, stick, sourceBlockType, stairsSupplier, textureBlock, null);
    }

    public static Block createFenceGateBlock(BlockMaterial material, String blockType, Block sourceBlock, Item stick, String sourceBlockType, Supplier<Block> fenceGateSupplier, Block textureBlock, @Nullable String blockId) {
        if(blockId == null) {
            var sourceBlockId = BuiltInRegistries.BLOCK.getKey(sourceBlock).getPath();
            blockId = sourceBlockId + "_fence_gate";
        }
        List<TagKey<Block>> blockTags = material.getBlockTags(List.of(blockType));
        List<TagKey<Item>> itemTags = material.getItemTags(List.of(blockType));
        TagKey<Item> sourceBlockTag = material.getItemTag(sourceBlockType);
        BiFunction<String, String, Block> blockSupplier = (String id, String langFileName) ->  BlockHandler.registerBlockWithRecipe(id, langFileName,
                material.getNamespace(), fenceGateSupplier.get(),
                item->ItemGroupHelper.addToGroup(CreativeModeTabs.BUILDING_BLOCKS, material, blockType, false),
                (blockModelGenerators,block)->{
                    TextureMapping textureMapping = TextureMapping.cube(textureBlock);
                    ResourceLocation closed = ModelTemplates.FENCE_GATE_CLOSED.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
                    ResourceLocation open = ModelTemplates.FENCE_GATE_OPEN.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
                    ResourceLocation closedWall = ModelTemplates.FENCE_GATE_WALL_CLOSED.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
                    ResourceLocation openWall = ModelTemplates.FENCE_GATE_WALL_OPEN.create((Block) block,textureMapping,blockModelGenerators.modelOutput);
                    blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createFenceGate((Block) block, open, closed,openWall,closedWall, true));
                    blockModelGenerators.delegateItemModel((Block) block, closed);
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

    public static Block createDoorBlock(BlockMaterial material, String blockType, Block sourceBlock, String sourceBlockType, Supplier<Block> stairsSupplier) {
        return createDoorBlock(material, blockType, sourceBlock, sourceBlockType, stairsSupplier, null);
    }

    public static Block createDoorBlock(BlockMaterial material, String blockType, Block sourceBlock, String sourceBlockType, Supplier<Block> doorSupplier, @Nullable String blockId) {
        if(blockId == null) {
            var sourceBlockId = BuiltInRegistries.BLOCK.getKey(sourceBlock).getPath();
            blockId = sourceBlockId + "_door";
        }
        List<TagKey<Block>> blockTags = material.getBlockTags(List.of(blockType));
        List<TagKey<Item>> itemTags = material.getItemTags(List.of(blockType));
        TagKey<Item> sourceBlockTag = material.getItemTag(sourceBlockType);
        BiFunction<String, String, Block> blockSupplier = (String id, String langFileName) ->  BlockHandler.registerBlockWithRecipe(id, langFileName,
                material.getNamespace(), doorSupplier.get(),
                item->ItemGroupHelper.addToGroup(CreativeModeTabs.BUILDING_BLOCKS, material, blockType, false),
                (blockModelGenerators,block)->{
                    TextureMapping textureMapping = TextureMapping.door((Block) block);
                    ResourceLocation bottom_left = ModelTemplates.DOOR_BOTTOM_LEFT.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
                    ResourceLocation bottom_left_open = ModelTemplates.DOOR_BOTTOM_LEFT_OPEN.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
                    ResourceLocation bottom_right = ModelTemplates.DOOR_BOTTOM_RIGHT.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
                    ResourceLocation bottom_right_open = ModelTemplates.DOOR_BOTTOM_RIGHT_OPEN.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
                    ResourceLocation top_left = ModelTemplates.DOOR_TOP_LEFT.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
                    ResourceLocation top_left_open = ModelTemplates.DOOR_TOP_LEFT_OPEN.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
                    ResourceLocation top_right = ModelTemplates.DOOR_TOP_RIGHT.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
                    ResourceLocation top_right_open = ModelTemplates.DOOR_TOP_RIGHT_OPEN.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
                    blockModelGenerators.createSimpleFlatItemModel(block.asItem());
                    blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createDoor((Block) block, bottom_left,
                            bottom_left_open, bottom_right, bottom_right_open, top_left, top_left_open, top_right, top_right_open));
                },
                ((finishedRecipeConsumer, item) -> {
                    ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, item,3).define('P',sourceBlockTag)
                            .pattern("PP")
                            .pattern("PP")
                            .pattern("PP")
                            .unlockedBy("has_"+material+"_planks", FabricRecipeProvider.has(sourceBlockTag))
                            .save(finishedRecipeConsumer);
                }),
                ((blockLootProvider, block) -> blockLootProvider.add(block, LootTable.lootTable().withPool(blockLootProvider.applyExplosionCondition(block, LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(block).when(LootItemBlockStatePropertyCondition
                                .hasBlockStateProperties(block).setProperties(StatePropertiesPredicate.Builder.properties()
                                        .hasProperty(DoorBlock.HALF, DoubleBlockHalf.LOWER)))))))),
                blockTags, itemTags);
        return createBlock(material, blockType, blockId, blockSupplier, blockTags, itemTags);
    }
}
