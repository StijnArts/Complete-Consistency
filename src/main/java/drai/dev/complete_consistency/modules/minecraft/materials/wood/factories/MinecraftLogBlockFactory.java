package drai.dev.complete_consistency.modules.minecraft.materials.wood.factories;

import drai.dev.complete_consistency.*;
import drai.dev.complete_consistency.blocks.logs.*;
import drai.dev.complete_consistency.blocks.rotatedpillarblock.*;
import drai.dev.complete_consistency.helpers.*;
import drai.dev.complete_consistency.helpers.block.*;
import drai.dev.complete_consistency.materials.*;
import drai.dev.complete_consistency.model.*;
import drai.dev.complete_consistency.modules.minecraft.generic.*;
import drai.dev.complete_consistency.modules.minecraft.materials.wood.*;
import drai.dev.complete_consistency.tag.*;
import net.fabricmc.fabric.api.blockrenderlayer.v1.*;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.fabricmc.fabric.api.registry.*;
import net.minecraft.client.renderer.*;
import net.minecraft.data.loot.*;
import net.minecraft.data.models.*;
import net.minecraft.data.models.model.*;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class MinecraftLogBlockFactory {
    public static void logBlock(WoodMaterial material) {
        List<TagKey<Block>> blockTags = material.getBlockTags(Stream.of(MinecraftWoodBlocks.LOG).map(MinecraftWoodBlocks::getName).toList());
        List<TagKey<Item>> itemTags = material.getItemTags(Stream.of(MinecraftWoodBlocks.LOG).map(MinecraftWoodBlocks::getName).toList());
        BiFunction<String, String, Block> blockSupplier = (String id, String langFileName) -> BlockHandler.registerBlock(id, langFileName,
                    material.getNamespace(),
                    new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(material.isFungal ? Blocks.WARPED_STEM : Blocks.OAK_LOG).mapColor(material.getColor())),
                    ItemGroupHelper.createAfterConsumer(CreativeModeTabs.BUILDING_BLOCKS, material.getPreviousWoodBlock()),
                    (blockModelGenerators, block) -> {
                        blockModelGenerators.createRotatedPillarWithHorizontalVariant((Block) block,
                            TexturedModel.COLUMN, TexturedModel.COLUMN_HORIZONTAL);
                        ResourceLocation resourceLocation = ModelLocationUtils.getModelLocation((Block) block);
                        blockModelGenerators.delegateItemModel((Block) block, resourceLocation );
                    },
                    BlockLootSubProvider::dropSelf,
                    blockTags, itemTags);
        GenericBlockFactory.createBlock(material, MinecraftWoodBlocks.LOG.getName(), material + material.getLogAffix().toLowerCase().replaceAll(" ", "_"), blockSupplier, blockTags, itemTags);
//        if(returnBlock == null) {
//            var block  = material.getBlock(MinecraftWoodBlocks.LOG.getName());
//            BlockStateHelper.addBlockModel(block, blockModelGeneratorsConsumer);
//            BlockLootHelper.addBlockLoot(block, BlockLootSubProvider::dropSelf);
//            var location = BuiltInRegistries.BLOCK.getKey(block);
//            TagHelper.addBlockTags(block, blockTags);
//            BlockHandler.registerBlockItem(location.getPath(), location.getNamespace(), block, consumer, itemTags);
//            String blockLangFileName = "";
//            for (String segment : location.getPath().split("_")) {
//                blockLangFileName = blockLangFileName +" "+ StringUtil.capitalizeWord(segment);
//            }
//            LanguageHelper.getEnglishTranslations().addTranslation(block,blockLangFileName.trim());
//        }
        var blockTag = material.getBlockTag(MinecraftWoodBlocks.LOG.getName());
        var itemTag = material.getItemTag(MinecraftWoodBlocks.LOG.getName());
        TagHelper.addBlockTags(blockTag, List.of(BlockTags.LOGS, BlockTags.LOGS_THAT_BURN, BlockTags.MINEABLE_WITH_AXE));
        TagHelper.addItemTags(itemTag, List.of(ItemTags.LOGS, ItemTags.LOGS_THAT_BURN));
    }

    public static void strippedLogBlock(WoodMaterial material) {
        List<TagKey<Block>> blockTags = material.getBlockTags(Stream.of(MinecraftWoodBlocks.STRIPPED_LOG).map(MinecraftWoodBlocks::getName).toList());
        List<TagKey<Item>> itemTags = material.getItemTags(Stream.of(MinecraftWoodBlocks.STRIPPED_LOG).map(MinecraftWoodBlocks::getName).toList());
        blockTags.add(UVCommonBlockTags.STRIPPED_LOGS);
            BiFunction<String, String, Block> blockSupplier = (String id, String langFileName) -> BlockHandler.registerBlockWithRecipe(id, langFileName,
                    material.getNamespace(),
                    new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(material.isFungal ? Blocks.WARPED_STEM : Blocks.OAK_LOG)),
                    ItemGroupHelper.createAfterConsumer(CreativeModeTabs.BUILDING_BLOCKS, () -> material.getBlock(MinecraftWoodBlocks.WOOD.getName())),
                    (blockModelGenerators, block) -> {
                        blockModelGenerators.createRotatedPillarWithHorizontalVariant((Block) block,
                                TexturedModel.COLUMN, TexturedModel.COLUMN_HORIZONTAL);
                        ResourceLocation resourceLocation = ModelLocationUtils.getModelLocation((Block) block);
                        blockModelGenerators.delegateItemModel((Block) block, resourceLocation );
                    },
                    (finishedRecipeConsumer, item) -> {
                        //TODO Sawing recipe, Cutting board recipe
                    },
                    BlockLootSubProvider::dropSelf,
                    blockTags, itemTags);

        var returnBlock = GenericBlockFactory.createBlock(material, MinecraftWoodBlocks.STRIPPED_LOG.getName(), "stripped_"+material + material.getLogAffix().toLowerCase().replaceAll(" ", "_"), blockSupplier, blockTags, itemTags);
        if(returnBlock!=null){
            List<Block> logBlocks = material.getLogBlocks();
            logBlocks.forEach((log) -> StrippableBlockRegistry.register(log, returnBlock));
        }
        var blockTag = material.getBlockTag(MinecraftWoodBlocks.STRIPPED_LOG.getName());
        var itemTag = material.getItemTag(MinecraftWoodBlocks.STRIPPED_LOG.getName());
        TagHelper.addBlockTags(blockTag, List.of(BlockTags.LOGS, BlockTags.LOGS_THAT_BURN, BlockTags.MINEABLE_WITH_AXE));
        TagHelper.addItemTags(itemTag, List.of(ItemTags.LOGS, ItemTags.LOGS_THAT_BURN));
    }

    public static void woodBlock(WoodMaterial material) {
        TagKey<Item> logTag = material.getItemTag(MinecraftWoodBlocks.LOG.getName());
        List<TagKey<Block>> blockTags = material.getBlockTags(Stream.of(MinecraftWoodBlocks.WOOD).map(MinecraftWoodBlocks::getName).toList());
        List<TagKey<Item>> itemTags = material.getItemTags(Stream.of(MinecraftWoodBlocks.WOOD).map(MinecraftWoodBlocks::getName).toList());
            BiFunction<String, String, Block> blockSupplier = (String id, String langFileName) -> BlockHandler.registerBlockWithRecipe(id, langFileName,
                    material.getNamespace(),
                    new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(material.isFungal ? Blocks.WARPED_HYPHAE : Blocks.OAK_WOOD)),
                    ItemGroupHelper.createAfterConsumer(CreativeModeTabs.BUILDING_BLOCKS, () -> material.getBlock(MinecraftWoodBlocks.LOG.getName())),
                    (blockModelGenerators, block) -> {
                        blockModelGenerators.createRotatedPillarWithHorizontalVariant((Block) block,
                                TexturedModel.COLUMN, TexturedModel.COLUMN_HORIZONTAL);
                        ResourceLocation resourceLocation = ModelLocationUtils.getModelLocation((Block) block);
                        blockModelGenerators.delegateItemModel((Block) block, resourceLocation );
                    },
                    ((finishedRecipeConsumer, item) -> {
                        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, item, 3).define('L', logTag)
                                .pattern("LL")
                                .pattern("LL")
                                .unlockedBy("has_" + material + "_logs", FabricRecipeProvider.has(logTag)).save(finishedRecipeConsumer);
                    }),
                    BlockLootSubProvider::dropSelf,
                    blockTags, itemTags);
            String id = material.getName() + material.getWoodAffix().toLowerCase().replaceAll(" ", "_");
            GenericBlockFactory.createBlock(material, MinecraftWoodBlocks.WOOD.getName(), id, blockSupplier, blockTags, itemTags);
            var blockTag = material.getBlockTag(MinecraftWoodBlocks.WOOD.getName());
            var itemTag = material.getItemTag(MinecraftWoodBlocks.WOOD.getName());
            TagHelper.addBlockTags(blockTag, List.of(BlockTags.LOGS, BlockTags.LOGS_THAT_BURN, BlockTags.MINEABLE_WITH_AXE));
            TagHelper.addItemTags(itemTag, List.of(ItemTags.LOGS, ItemTags.LOGS_THAT_BURN));
    }

    public static void strippedWoodBlock(WoodMaterial material) {
        List<TagKey<Block>> blockTags = material.getBlockTags(Stream.of(MinecraftWoodBlocks.STRIPPED_WOOD).map(MinecraftWoodBlocks::getName).toList());
        List<TagKey<Item>> itemTags = material.getItemTags(Stream.of(MinecraftWoodBlocks.STRIPPED_WOOD).map(MinecraftWoodBlocks::getName).toList());
        blockTags.add(UVCommonBlockTags.STRIPPED_LOGS);

        TagKey<Item> stripped_log_tag = material.getItemTag(MinecraftWoodBlocks.STRIPPED_WOOD.getName());
            BiFunction<String, String, Block> blockSupplier = (String id, String langFileName) -> BlockHandler.registerBlockWithRecipe(id, langFileName,
                    material.getNamespace(),
                    new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(material.isFungal ? Blocks.WARPED_HYPHAE : Blocks.OAK_WOOD)),
                    ItemGroupHelper.createAfterConsumer(CreativeModeTabs.BUILDING_BLOCKS, () -> material.getBlock(MinecraftWoodBlocks.WOOD.getName())),
                    (blockModelGenerators, block) -> {
                        blockModelGenerators.createRotatedPillarWithHorizontalVariant((Block) block,
                                TexturedModel.COLUMN, TexturedModel.COLUMN_HORIZONTAL);
                        ResourceLocation resourceLocation = ModelLocationUtils.getModelLocation((Block) block);
                        blockModelGenerators.delegateItemModel((Block) block, resourceLocation );
                    },
                    ((finishedRecipeConsumer, item) -> {
                        //TODO Sawing recipe, Cutting board recipe
                        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, item, 3).define('L', stripped_log_tag)
                                .pattern("LL")
                                .pattern("LL")
                                .unlockedBy("has_stripped_" + material + "_logs", FabricRecipeProvider.has(stripped_log_tag)).save(finishedRecipeConsumer);
                    }),
                    BlockLootSubProvider::dropSelf,
                    blockTags, itemTags);
        var returnBlock = GenericBlockFactory.createBlock(material, MinecraftWoodBlocks.STRIPPED_WOOD.getName(), "stripped_"+material + material.getWoodAffix().toLowerCase().replaceAll(" ", "_"),
                blockSupplier, blockTags, itemTags);
        if(returnBlock!=null){
            List<Block> logBlocks = material.getWoodBlocks();
            logBlocks.forEach((log) -> StrippableBlockRegistry.register(log, returnBlock));
        }
        var blockTag = material.getBlockTag(MinecraftWoodBlocks.STRIPPED_WOOD.getName());
        var itemTag = material.getItemTag(MinecraftWoodBlocks.STRIPPED_WOOD.getName());
        TagHelper.addBlockTags(blockTag, List.of(BlockTags.LOGS, BlockTags.LOGS_THAT_BURN, BlockTags.MINEABLE_WITH_AXE));
        TagHelper.addItemTags(itemTag, List.of(ItemTags.LOGS, ItemTags.LOGS_THAT_BURN));
    }

    public static void logStairsBlock(WoodMaterial material, String name) {
        Block logBlock = material.getBlock(name);
        Block plankBlock = material.getBlock(MinecraftWoodBlocks.PLANKS.getName());
        Block strippedStairs = material.getBlock("stripped_"+name + "_" +MinecraftWoodBlocks.STAIRS.getName());
        BiConsumer<BlockModelGenerators, ItemLike> blockStateBiConsumer = (blockModelGenerators, block) -> {
            TextureMapping textureMapping = new TextureMapping()
                    .put(UpgradedVanillaModelTemplates.X, TextureMapping.getBlockTexture(logBlock, (logBlock instanceof RotatedPillarBlock ? "" : "_side")))
                    .put(UpgradedVanillaModelTemplates.Y, TextureMapping.getBlockTexture(logBlock, "_top"))
                    .put(UpgradedVanillaModelTemplates.Z, TextureMapping.getBlockTexture(logBlock, (logBlock instanceof RotatedPillarBlock ? "" : "_side")))
                    .put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(logBlock, (logBlock instanceof RotatedPillarBlock ? "" : "_side")));
            ResourceLocation inner = UpgradedVanillaModelTemplates.ROTATED_PILLAR_STAIRS_INNER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
            ResourceLocation straight = UpgradedVanillaModelTemplates.ROTATED_PILLAR_STAIRS.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
            ResourceLocation outer = UpgradedVanillaModelTemplates.ROTATED_PILLAR_STAIRS_OUTER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);

            TextureMapping textureMappingHorizontalX = new TextureMapping()
                    .put(UpgradedVanillaModelTemplates.X, TextureMapping.getBlockTexture(logBlock, "_top"))
                    .put(UpgradedVanillaModelTemplates.Y, TextureMapping.getBlockTexture(logBlock, (logBlock instanceof RotatedPillarBlock ? "" : "_side")))
                    .put(UpgradedVanillaModelTemplates.Z, TextureMapping.getBlockTexture(logBlock, (logBlock instanceof RotatedPillarBlock ? "" : "_side")))
                    .put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(logBlock, (logBlock instanceof RotatedPillarBlock ? "" : "_side")));
            ResourceLocation innerHorizontalX = UpgradedVanillaModelTemplates.ROTATED_PILLAR_STAIRS_INNER_HORIZONTAL_X.create((Block) block, textureMappingHorizontalX, blockModelGenerators.modelOutput);
            ResourceLocation straightHorizontalX = UpgradedVanillaModelTemplates.ROTATED_PILLAR_STAIRS_HORIZONTAL_X.create((Block) block, textureMappingHorizontalX, blockModelGenerators.modelOutput);
            ResourceLocation outerHorizontalX = UpgradedVanillaModelTemplates.ROTATED_PILLAR_STAIRS_OUTER_HORIZONTAL_X.create((Block) block, textureMappingHorizontalX, blockModelGenerators.modelOutput);

            TextureMapping textureMappingHorizontalZ = new TextureMapping()
                    .put(UpgradedVanillaModelTemplates.X, TextureMapping.getBlockTexture(logBlock, (logBlock instanceof RotatedPillarBlock ? "" : "_side")))
                    .put(UpgradedVanillaModelTemplates.Y, TextureMapping.getBlockTexture(logBlock, (logBlock instanceof RotatedPillarBlock ? "" : "_side")))
                    .put(UpgradedVanillaModelTemplates.Z, TextureMapping.getBlockTexture(logBlock, "_top"))
                    .put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(logBlock, (logBlock instanceof RotatedPillarBlock ? "" : "_side")));
            ResourceLocation innerHorizontalZ = UpgradedVanillaModelTemplates.ROTATED_PILLAR_STAIRS_INNER_HORIZONTAL_Z.create((Block) block, textureMappingHorizontalZ, blockModelGenerators.modelOutput);
            ResourceLocation straightHorizontalZ = UpgradedVanillaModelTemplates.ROTATED_PILLAR_STAIRS_HORIZONTAL_Z.create((Block) block, textureMappingHorizontalZ, blockModelGenerators.modelOutput);
            ResourceLocation outerHorizontalZ = UpgradedVanillaModelTemplates.ROTATED_PILLAR_STAIRS_OUTER_HORIZONTAL_Z.create((Block) block, textureMappingHorizontalZ, blockModelGenerators.modelOutput);

            blockModelGenerators.blockStateOutput.accept(StairBlockStateGenerator.createRotatedPillarBlockStairs((Block) block, inner, straight, outer, innerHorizontalX, straightHorizontalX, outerHorizontalX, innerHorizontalZ, straightHorizontalZ, outerHorizontalZ));
            blockModelGenerators.delegateItemModel((Block) block, straight);
        };
        GenericBlockFactory.createStairs(material, name + "_" +MinecraftWoodBlocks.STAIRS.getName(), logBlock, name,
                new StrippableStairsBlock(strippedStairs, BlockBehaviour.Properties.ofFullCopy(plankBlock)), blockStateBiConsumer);

        TagKey<Block> blockTag = material.getBlockTag((name + "_" +MinecraftWoodBlocks.STAIRS.getName()));
        TagKey<Item> itemTag = material.getItemTag((name + "_" +MinecraftWoodBlocks.STAIRS.getName()));
        TagHelper.addBlockTags(blockTag, List.of(BlockTags.WOODEN_STAIRS, BlockTags.MINEABLE_WITH_AXE));
        TagHelper.addItemTags(itemTag, List.of(ItemTags.WOODEN_STAIRS));
    }

    public static void strippedLogStairsBlock(WoodMaterial material, String name) {
        Block logBlock = material.getBlock("stripped_"+name);
        Block plankBlock = material.getBlock(MinecraftWoodBlocks.PLANKS.getName());
        var defaultState = plankBlock.defaultBlockState();
        GenericBlockFactory.createStairs(material, "stripped_" + name + "_" +MinecraftWoodBlocks.STAIRS.getName(), logBlock, name,
                new RotatedPillarStairBlock(defaultState, BlockBehaviour.Properties.ofFullCopy(plankBlock)));

        TagKey<Block> blockTag = material.getBlockTag(("stripped_"+name + "_" +MinecraftWoodBlocks.STAIRS.getName()));
        TagKey<Item> itemTag = material.getItemTag(("stripped_"+name + "_" +MinecraftWoodBlocks.STAIRS.getName()));
        TagHelper.addBlockTags(blockTag, List.of(BlockTags.WOODEN_STAIRS, BlockTags.MINEABLE_WITH_AXE));
        TagHelper.addItemTags(itemTag, List.of(ItemTags.WOODEN_STAIRS));
    }

    public static void logSlabBlock(WoodMaterial material, String name) {
        Block logBlock = material.getBlock(name);
        Block plankBlock = material.getBlock(MinecraftWoodBlocks.PLANKS.getName());
        Block strippedSlab = material.getBlock("stripped_"+name + "_" +MinecraftWoodBlocks.SLAB.getName());
        GenericBlockFactory.createSlab(material, name + "_" +MinecraftWoodBlocks.SLAB.getName(), logBlock, name,
                ()->new StrippableSlabBlock(strippedSlab, BlockBehaviour.Properties.ofFullCopy(plankBlock)));
        TagKey<Block> blockTag = material.getBlockTag((name + "_" +MinecraftWoodBlocks.SLAB.getName()));
        TagKey<Item> itemTag = material.getItemTag((name + "_" +MinecraftWoodBlocks.SLAB.getName()));
		TagHelper.addBlockTags(blockTag, List.of(BlockTags.WOODEN_SLABS, BlockTags.MINEABLE_WITH_AXE));
        TagHelper.addItemTags(itemTag, List.of(ItemTags.WOODEN_SLABS));
    }

    public static void strippedLogSlabBlock(WoodMaterial material, String name) {
        Block logBlock = material.getBlock("stripped_"+name);
        Block plankBlock = material.getBlock(MinecraftWoodBlocks.PLANKS.getName());
        GenericBlockFactory.createSlab(material, "stripped_"+name + "_" +MinecraftWoodBlocks.SLAB.getName(), logBlock, name,
                ()->new SlabBlock(BlockBehaviour.Properties.ofFullCopy(plankBlock)));

        TagKey<Block> blockTag = material.getBlockTag(("stripped_"+name + "_" +MinecraftWoodBlocks.SLAB.getName()));
        TagKey<Item> itemTag = material.getItemTag(("stripped_"+name + "_" +MinecraftWoodBlocks.SLAB.getName()));
        TagHelper.addBlockTags(blockTag, List.of(BlockTags.WOODEN_SLABS, BlockTags.MINEABLE_WITH_AXE));
        TagHelper.addItemTags(itemTag, List.of(ItemTags.WOODEN_SLABS));
    }

    public static void logWallBlock(WoodMaterial material, String name) {
        Block logBlock = material.getBlock(name);
        Block plankBlock = material.getBlock(MinecraftWoodBlocks.PLANKS.getName());
        Block strippedWall = material.getBlock("stripped_"+name + "_" +MinecraftWoodBlocks.WALL.getName());
        var returnBlock = GenericBlockFactory.createWall(material, name + "_" +MinecraftWoodBlocks.WALL.getName(), logBlock, name,
                ()->new StrippableWallBlock(strippedWall, BlockBehaviour.Properties.ofFullCopy(plankBlock)));
        if(returnBlock!=null && CompleteConsistency.isClient()){
            BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
        }
        TagKey<Block> blockTag = material.getBlockTag((name + "_" +MinecraftWoodBlocks.WALL.getName()));
        TagKey<Item> itemTag = material.getItemTag((name + "_" +MinecraftWoodBlocks.WALL.getName()));
        TagHelper.addBlockTags(blockTag, List.of(UpgradedVanillaTags.WOODEN_WALLS_BLOCK_TAG, BlockTags.WALLS, BlockTags.MINEABLE_WITH_AXE));
        TagHelper.addItemTags(itemTag, List.of(UpgradedVanillaTags.WOODEN_WALLS_ITEM_TAG, ItemTags.WALLS));
    }

    public static void strippedLogWallBlock(WoodMaterial material, String name) {
        Block logBlock = material.getBlock("stripped_"+name);
        Block plankBlock = material.getBlock(MinecraftWoodBlocks.PLANKS.getName());
        var returnBlock = GenericBlockFactory.createWall(material, "stripped_"+name + "_" +MinecraftWoodBlocks.WALL.getName(), logBlock, name,
                ()->new WallBlock(BlockBehaviour.Properties.ofFullCopy(plankBlock)));
        if(returnBlock!=null && CompleteConsistency.isClient()){
            BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
        }
        TagKey<Block> blockTag = material.getBlockTag(("stripped_"+name + "_" +MinecraftWoodBlocks.WALL.getName()));
        TagKey<Item> itemTag = material.getItemTag(("stripped_"+name + "_" +MinecraftWoodBlocks.WALL.getName()));
        TagHelper.addBlockTags(blockTag, List.of(UpgradedVanillaTags.WOODEN_WALLS_BLOCK_TAG, BlockTags.WALLS, BlockTags.MINEABLE_WITH_AXE));
        TagHelper.addItemTags(itemTag, List.of(UpgradedVanillaTags.WOODEN_WALLS_ITEM_TAG, ItemTags.WALLS));
    }
}