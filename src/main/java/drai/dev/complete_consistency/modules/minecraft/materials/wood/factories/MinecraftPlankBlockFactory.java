package drai.dev.complete_consistency.modules.minecraft.materials.wood.factories;

import drai.dev.complete_consistency.*;
import drai.dev.complete_consistency.blocks.blockentities.*;
import drai.dev.complete_consistency.client.renderer.*;
import drai.dev.complete_consistency.helpers.*;
import drai.dev.complete_consistency.helpers.block.*;
import drai.dev.complete_consistency.helpers.itemgroup.*;
import drai.dev.complete_consistency.helpers.languages.*;
import drai.dev.complete_consistency.materials.impl.*;
import drai.dev.complete_consistency.model.*;
import drai.dev.complete_consistency.modules.minecraft.generic.*;
import drai.dev.complete_consistency.modules.minecraft.materials.wood.*;
import drai.dev.complete_consistency.tag.*;
import net.fabricmc.fabric.api.blockrenderlayer.v1.*;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.fabricmc.fabric.api.object.builder.v1.block.*;
import net.minecraft.client.renderer.*;
import net.minecraft.core.registries.*;
import net.minecraft.data.loot.*;
import net.minecraft.data.models.*;
import net.minecraft.data.models.model.*;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;

import javax.imageio.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import static drai.dev.complete_consistency.modules.minecraft.materials.wood.MinecraftWoodBlocks.*;

public class MinecraftPlankBlockFactory {
    public static void plankBlock(WoodMaterial material) {
        var logTag = material.getItemTag(MinecraftWoodBlocks.LOG.getName());
        List<TagKey<Block>> blockTags = material.getBlockTags(Stream.of(PLANKS).map(MinecraftWoodBlocks::getName).toList());
        List<TagKey<Item>> itemTags = material.getItemTags(Stream.of(MinecraftWoodBlocks.PLANKS).map(MinecraftWoodBlocks::getName).toList());
        BiFunction<String, String, Block> blockSupplier = (String id, String langFileName) -> BlockHandler.registerBlockWithRecipe(
                id, langFileName,
                material.getNamespace(),
                new Block(BlockBehaviour.Properties.of().mapColor(material.getColor()).instrument(NoteBlockInstrument.BASS)
                        .strength(2.0f, 3.0f).sound(SoundType.WOOD).ignitedByLava()),
                item-> ItemGroupHelper.addToGroup(CreativeModeTabs.BUILDING_BLOCKS, material, PLANKS.getName(), false),
                (blockModelGenerators,block)->{
                    var output = TexturedModel.CUBE.create((Block) block, blockModelGenerators.modelOutput);
                    blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock((Block) block, output));
                    blockModelGenerators.delegateItemModel((Block) block, output);
            },
                ((finishedRecipeConsumer, item) -> {
                    //TODO Farmers delight cutting board recipe, Create sawing recipe
                    ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, item,4).requires(logTag)
                            .unlockedBy("has_"+material+"_logs", FabricRecipeProvider.has(logTag)).save(finishedRecipeConsumer);
                }),
                BlockLootSubProvider::dropSelf,
                blockTags, itemTags);
        var returnBlock = GenericBlockFactory.createBlock(material, MinecraftWoodBlocks.PLANKS.getName(), material + "_planks", blockSupplier,
                blockTags, itemTags);
        if(returnBlock != null){
            TextureHelper.addTexture(()->{
                try {
                    File plankTextureLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\block\\MATERIAL_planks.png");
                    TextureHelper.swapColors("block\\"+material+"_planks", "block", material.getNamespace(),
                            ImageIO.read(plankTextureLocation), TextureHelper.woodPresetPalette, material.palette);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        var blockTag = material.getBlockTag(MinecraftWoodBlocks.PLANKS.getName());
        var itemTag = material.getItemTag(MinecraftWoodBlocks.PLANKS.getName());
        TagHelper.addBlockTags(blockTag, List.of(BlockTags.PLANKS, BlockTags.MINEABLE_WITH_AXE));
        TagHelper.addItemTags(itemTag, List.of(ItemTags.PLANKS));
//        ProcessingRecipeHelper.addCuttingRecipe(returnBlock, (gen, item)->{
//            gen.stripAndMakePlanks(log, strippedLog, (Block) item);
//            gen.stripAndMakePlanks(wood, strippedWood, (Block) item);
//        });
    }

    public static void plankStairsBlock(WoodMaterial material) {
        Block plankBlock = material.getBlock(MinecraftWoodBlocks.PLANKS.getName());
        var defaultState = plankBlock.defaultBlockState();
        GenericBlockFactory.createStairs(material, MinecraftWoodBlocks.STAIRS.getName(), plankBlock, PLANKS.getName(),
                ()->new StairBlock(defaultState, BlockBehaviour.Properties.ofFullCopy(plankBlock)), material+"_"+STAIRS.getName());

        TagKey<Block> blockTag = material.getBlockTag((MinecraftWoodBlocks.STAIRS.getName()));
        TagKey<Item> itemTag = material.getItemTag((MinecraftWoodBlocks.STAIRS.getName()));
        TagHelper.addBlockTags(blockTag, List.of(BlockTags.WOODEN_STAIRS, BlockTags.MINEABLE_WITH_AXE));
        TagHelper.addItemTags(itemTag, List.of(ItemTags.WOODEN_STAIRS));
    }

    public static void plankSlabBlock(WoodMaterial material) {
        Block plankBlock = material.getBlock(PLANKS.getName());
        GenericBlockFactory.createSlab(material, SLAB.getName(), plankBlock, PLANKS.getName(),
                ()->new SlabBlock(BlockBehaviour.Properties.ofFullCopy(plankBlock)),  material+"_"+SLAB.getName());

        TagKey<Block> blockTag = material.getBlockTag((SLAB.getName()));
        TagKey<Item> itemTag = material.getItemTag((SLAB.getName()));
        TagHelper.addBlockTags(blockTag, List.of(BlockTags.WOODEN_SLABS, BlockTags.MINEABLE_WITH_AXE));
        TagHelper.addItemTags(itemTag, List.of(ItemTags.WOODEN_SLABS));
    }

    public static void plankWallBlock(WoodMaterial material) {
        Block plankBlock = material.getBlock(PLANKS.getName());
        var returnBlock = GenericBlockFactory.createWall(material, WALL.getName(), plankBlock, PLANKS.getName(),
                ()->new WallBlock(BlockBehaviour.Properties.ofFullCopy(plankBlock)),material+"_"+WALL.getName());
        if(returnBlock!=null && CompleteConsistency.isClient()){
            BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
        }
        TagKey<Block> blockTag = material.getBlockTag((WALL.getName()));
        TagKey<Item> itemTag = material.getItemTag((WALL.getName()));
        TagHelper.addBlockTags(blockTag, List.of(UpgradedVanillaTags.WOODEN_WALLS_BLOCK_TAG, BlockTags.WALLS, BlockTags.MINEABLE_WITH_AXE));
        TagHelper.addItemTags(itemTag, List.of(UpgradedVanillaTags.WOODEN_WALLS_ITEM_TAG, ItemTags.WALLS));
    }

    public static void plankButtonBlock(WoodMaterial material) {
        Block plankBlock = material.getBlock(PLANKS.getName());
        GenericBlockFactory.createButton(material, BUTTON.getName(), plankBlock, PLANKS.getName(),
                ()->Blocks.woodenButton(material.getBlockSetType()),material+"_"+BUTTON.getName());
        TagKey<Block> blockTag = material.getBlockTag((BUTTON.getName()));
        TagKey<Item> itemTag = material.getItemTag((BUTTON.getName()));
        TagHelper.addBlockTags(blockTag, List.of(BlockTags.WOODEN_BUTTONS, BlockTags.MINEABLE_WITH_AXE));
        TagHelper.addItemTags(itemTag, List.of(ItemTags.WOODEN_BUTTONS));
    }

    public static void plankPressurePlateBlock(WoodMaterial material) {
        Block plankBlock = material.getBlock(PLANKS.getName());
        GenericBlockFactory.createPressurePlate(material, PRESSURE_PLATE.getName(), plankBlock, PLANKS.getName(),
                ()->new PressurePlateBlock(material.getBlockSetType(), BlockBehaviour.Properties.ofFullCopy(plankBlock)), material+"_"+PRESSURE_PLATE.getName());
        TagKey<Block> blockTag = material.getBlockTag((PRESSURE_PLATE.getName()));
        TagKey<Item> itemTag = material.getItemTag((PRESSURE_PLATE.getName()));
        TagHelper.addBlockTags(blockTag, List.of(BlockTags.WOODEN_PRESSURE_PLATES, BlockTags.MINEABLE_WITH_AXE));
        TagHelper.addItemTags(itemTag, List.of(ItemTags.WOODEN_PRESSURE_PLATES));
    }

    public static void plankTrapdoorBlock(WoodMaterial material) {
        Block plankBlock = material.getBlock(PLANKS.getName());
        GenericBlockFactory.createTrapdoor(material, TRAPDOOR.getName(), plankBlock, PLANKS.getName(),
                () -> new TrapDoorBlock(material.getBlockSetType(), BlockBehaviour.Properties.ofFullCopy(plankBlock).noOcclusion()), material+"_"+TRAPDOOR.getName());
        TagKey<Block> blockTag = material.getBlockTag((TRAPDOOR.getName()));
        TagKey<Item> itemTag = material.getItemTag((TRAPDOOR.getName()));
        TagHelper.addBlockTags(blockTag, List.of(BlockTags.WOODEN_TRAPDOORS, BlockTags.MINEABLE_WITH_AXE));
        TagHelper.addItemTags(itemTag, List.of(ItemTags.WOODEN_TRAPDOORS));
    }

    public static void plankDoorBlock(WoodMaterial material) {
        Block plankBlock = material.getBlock(PLANKS.getName());
        GenericBlockFactory.createDoorBlock(material, DOOR.getName(), plankBlock, PLANKS.getName(),
                () -> new DoorBlock(material.getBlockSetType(), BlockBehaviour.Properties.ofFullCopy(plankBlock).noOcclusion()), material+"_"+DOOR.getName());

        TagKey<Block> blockTag = material.getBlockTag((DOOR.getName()));
        TagKey<Item> itemTag = material.getItemTag((DOOR.getName()));
        TagHelper.addBlockTags(blockTag, List.of(BlockTags.WOODEN_DOORS, BlockTags.MINEABLE_WITH_AXE));
        TagHelper.addItemTags(itemTag, List.of(ItemTags.WOODEN_DOORS));
    }

    public static void plankFenceBlock(WoodMaterial material) {
        Block plankBlock = material.getBlock(PLANKS.getName());
        Item stickItem = material.getItem(STICK.getName());
        GenericBlockFactory.createFenceBlock(material, FENCE.getName(), plankBlock, stickItem, PLANKS.getName(),
                ()-> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(plankBlock)), material+"_"+FENCE.getName());
        TagKey<Block> blockTag = material.getBlockTag((FENCE.getName()));
        TagKey<Item> itemTag = material.getItemTag((FENCE.getName()));
        TagHelper.addBlockTags(blockTag, List.of(BlockTags.WOODEN_FENCES, BlockTags.MINEABLE_WITH_AXE));
        TagHelper.addItemTags(itemTag, List.of(ItemTags.WOODEN_FENCES));
    }

    public static void plankFenceGateBlock(WoodMaterial material) {
        Block plankBlock = material.getBlock(PLANKS.getName());
        Item stickItem = material.getItem(STICK.getName());
        GenericBlockFactory.createFenceGateBlock(material, FENCE_GATE.getName(), plankBlock, stickItem, PLANKS.getName(),
                ()-> new FenceGateBlock(material.getWoodType(), BlockBehaviour.Properties.ofFullCopy(plankBlock)), material+"_"+FENCE_GATE.getName());
        TagKey<Block> blockTag = material.getBlockTag((FENCE_GATE.getName()));
        TagHelper.addBlockTags(blockTag, List.of(BlockTags.FENCE_GATES, BlockTags.MINEABLE_WITH_AXE));
    }

    public static void plankLadderBlock(WoodMaterial material) {
        Item stickItem = material.getItem(STICK.getName());
        List<TagKey<Block>> blockTags = material.getBlockTags(Stream.of(LADDER).map(MinecraftWoodBlocks::getName).toList());
        List<TagKey<Item>> itemTags = material.getItemTags(Stream.of(MinecraftWoodBlocks.LADDER).map(MinecraftWoodBlocks::getName).toList());
        BiFunction<String, String, Block> blockSupplier = (String id, String langFileName) -> BlockHandler.registerBlockWithRecipe(
                id, langFileName,
                material.getNamespace(),
                new LadderBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LADDER).sound(SoundType.LADDER).noOcclusion()),
                item-> ItemGroupHelper.addToGroup(CreativeModeTabs.FUNCTIONAL_BLOCKS, material, LADDER.getName(), false, ()->Blocks.LADDER),
                (blockModelGenerators,block)->{
                    TextureMapping textureMapping = TextureMapping.cube((Block) block);
                    UpgradedVanillaModelTemplates.LADDER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
                    blockModelGenerators.createNonTemplateHorizontalBlock((Block) block);
                    blockModelGenerators.createSimpleFlatItemModel((Block) block);
                },
                ((finishedRecipeConsumer, item) -> {
                    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, item,3).define('S',stickItem)
                            .pattern("S S")
                            .pattern("SSS")
                            .pattern("S S")
                            .unlockedBy("has_"+material+"_sticks", FabricRecipeProvider.has(stickItem))
                            .save(finishedRecipeConsumer);
                }),
                BlockLootSubProvider::dropSelf,
                blockTags, itemTags);
        var returnBlock = GenericBlockFactory.createBlock(material, LADDER.getName(), material + "_ladder", blockSupplier,
                blockTags, itemTags);
        if(returnBlock != null){
            TextureHelper.addTexture(()->{
                try {
                    File ladderTextureLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\block\\MATERIAL_ladder.png");
                    TextureHelper.swapColors("block\\"+material+"_ladder", "block", material.getNamespace(),
                            ImageIO.read(ladderTextureLocation), TextureHelper.woodPresetPalette, material.palette);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            if(CompleteConsistency.isClient()){
                BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
            }
        }

        TagKey<Block> blockTag = material.getBlockTag((LADDER.getName()));
        TagKey<Item> itemTag = material.getItemTag((LADDER.getName()));
        TagHelper.addItemTags(itemTag, List.of(UpgradedVanillaTags.WOODEN_LADDER_ITEM_TAG));
        TagHelper.addBlockTags(blockTag, List.of(UpgradedVanillaTags.WOODEN_LADDER_BLOCK_TAG, BlockTags.CLIMBABLE, BlockTags.MINEABLE_WITH_AXE));
    }

    public static void standingSignBlock(WoodMaterial material) {
        var plankBlock = material.getBlock(PLANKS.getName());
        List<TagKey<Block>> blockTags = material.getBlockTags(Stream.of(STANDING_SIGN).map(MinecraftWoodBlocks::getName).toList());
        blockTags.add(BlockTags.STANDING_SIGNS);
        BiFunction<String, String, Block> blockSupplier = (String id, String langFileName) -> BlockHandler.registerBlockWithoutItem(id,
                material.getNamespace(), new StandingSignBlock(material.getWoodType(), BlockBehaviour.Properties.ofFullCopy(plankBlock).noCollission()),
                (blockModelGenerators,block)->{},
                BlockLootSubProvider::dropSelf,
                blockTags);
        var returnBlock = GenericBlockFactory.createBlock(material, STANDING_SIGN.getName(), material + "_standing_sign", blockSupplier,
                blockTags, List.of());
        if(returnBlock != null){
            TextureHelper.addTexture(()->{
                try {
                    File ladderTextureLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\entity\\sign\\MATERIAL.png");
                    BufferedImage signTexture = TextureHelper.swapColors("entity\\signs\\"+material, "entity\\signs", "minecraft",
                            ImageIO.read(ladderTextureLocation), TextureHelper.woodPresetPalette,material. palette);
                    File logTextureLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\overlay\\entity\\signs\\"+material+".png");
                    TextureHelper.overlayTexture(signTexture,ImageIO.read(logTextureLocation), 0,16,
                            "entity\\signs\\"+material, "entity\\signs","minecraft");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            if(CompleteConsistency.isClient()){
                BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
            }
        }
    }

    public static void wallSignBlock(WoodMaterial material) {
        var plankBlock = material.getBlock(PLANKS.getName());
        List<TagKey<Block>> blockTags = material.getBlockTags(Stream.of(WALL_SIGN).map(MinecraftWoodBlocks::getName).toList());
        blockTags.add(BlockTags.WALL_SIGNS);
        BiFunction<String, String, Block> blockSupplier = (String id, String langFileName) -> BlockHandler.registerBlockWithoutItem(id,
                material.getNamespace(), new WallSignBlock(material.getWoodType(), BlockBehaviour.Properties.ofFullCopy(plankBlock).noCollission()),
                (blockModelGenerators,block)->{},
                BlockLootSubProvider::dropSelf,
                blockTags);
        var returnBlock = GenericBlockFactory.createBlock(material, WALL_SIGN.getName(), material + "_wall_sign", blockSupplier,
                blockTags, List.of());
        if(returnBlock != null) {
            if(CompleteConsistency.isClient()){
                BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
            }
        }
    }

    public static void chestBlock(WoodMaterial material) {
        var plankBlock = material.getBlock(PLANKS.getName());
        List<TagKey<Block>> blockTags = material.getBlockTags(Stream.of(CHEST).map(MinecraftWoodBlocks::getName).toList());
        blockTags.add(UVCommonBlockTags.WOODEN_CHESTS);
        List<TagKey<Item>> itemTags = material.getItemTags(Stream.of(CHEST).map(MinecraftWoodBlocks::getName).toList());
        itemTags.add(UVCommonItemTags.WOODEN_CHESTS);
        var plankTag = material.getItemTag(PLANKS.getName());
        BiFunction<String, String, Block> blockSupplier = (String id, String langFileName) -> BlockHandler.registerBlockWithRecipe(id, langFileName,
                material.getNamespace(), new BaseChestBlock(plankBlock),
                item-> ItemGroupHelper.addToGroup(CreativeModeTabs.FUNCTIONAL_BLOCKS, material, CHEST.getName(), false, ()->Blocks.CHEST),
                (blockModelGenerators,block)->{
                    TextureMapping textureMapping = new TextureMapping().put(TextureSlot.TEXTURE, BuiltInRegistries.BLOCK.getKey((Block) block).withPrefix("block/chest/"));
                    var chestModel = UpgradedVanillaModelTemplates.CHEST.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
                    blockModelGenerators.blockEntityModels(ModelLocationUtils.decorateBlockModelLocation(material+"_chest"), plankBlock).create((Block) block);
                    blockModelGenerators.delegateItemModel((Block) block, chestModel);
                },
                ((finishedRecipeConsumer, item) -> {
                    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, item,1).define('P',plankTag)
                            .pattern("PPP")
                            .pattern("P P")
                            .pattern("PPP")
                            .unlockedBy("has_"+material+"_planks", FabricRecipeProvider.has(plankTag))
                            .save(finishedRecipeConsumer);
                }),
                BlockLootSubProvider::dropSelf,
                blockTags, itemTags);
        var returnBlock = GenericBlockFactory.createBlock(material, CHEST.getName(), material + "_chest", blockSupplier,
                blockTags, itemTags);
        if(returnBlock != null) {
            TextureHelper.addTexture(() -> {
                try {
                    File chestEntityTextureLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\entity\\chest\\MATERIAL_chest.png");
                    File chestLeftEntityTextureLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\entity\\chest\\MATERIAL_chest_left.png");
                    File chestRightEntityTextureLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\entity\\chest\\MATERIAL_chest_right.png");
                    BufferedImage chestTexture = TextureHelper.swapColors("block\\chest\\" + material + "_chest", "block\\chest", material.getNamespace(),
                            ImageIO.read(chestEntityTextureLocation), TextureHelper.woodPresetPalette, material.palette);
                    BufferedImage chestLeftTexture = TextureHelper.swapColors("block\\chest\\" + material + "_chest_left", "block\\chest", material.getNamespace(),
                            ImageIO.read(chestLeftEntityTextureLocation), TextureHelper.woodPresetPalette, material.palette);
                    BufferedImage chestRightTexture = TextureHelper.swapColors("block\\chest\\" + material + "_chest_right", "block\\chest", material.getNamespace(),
                            ImageIO.read(chestRightEntityTextureLocation), TextureHelper.woodPresetPalette, material.palette);
                    File chestOverlayLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\overlay\\entity\\chest\\MATERIAL_chest.png");
                    File chestLeftOverlayLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\overlay\\entity\\chest\\MATERIAL_chest_left.png");
                    File chestRightOverlayLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\overlay\\entity\\chest\\MATERIAL_chest_right.png");
                    TextureHelper.overlayTexture(chestTexture, ImageIO.read(chestOverlayLocation), 0, 0, "block\\chest\\" + material + "_chest", "block\\chest", material.getNamespace());
                    TextureHelper.overlayTexture(chestLeftTexture, ImageIO.read(chestLeftOverlayLocation), 0, 0, "block\\chest\\" + material + "_chest_left", "block\\chest", material.getNamespace());
                    TextureHelper.overlayTexture(chestRightTexture, ImageIO.read(chestRightOverlayLocation), 0, 0, "block\\chest\\" + material + "_chest_right", "block\\chest", material.getNamespace());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            if(CompleteConsistency.isClient()){
                BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
                BaseChestBlockEntityRenderer.registerRenderLayer(returnBlock);
            }
        }

        TagKey<Block> blockTag = material.getBlockTag((CHEST.getName()));
        TagKey<Item> itemTag = material.getItemTag((CHEST.getName()));
        TagHelper.addItemTags(itemTag, List.of(UpgradedVanillaTags.WOODEN_LADDER_ITEM_TAG));
        TagHelper.addBlockTags(blockTag, List.of(UpgradedVanillaTags.WOODEN_LADDER_BLOCK_TAG, BlockTags.CLIMBABLE, BlockTags.MINEABLE_WITH_AXE));
    }


    public static void trappedChestBlock(WoodMaterial material) {
        var plankBlock = material.getBlock(PLANKS.getName());
        List<TagKey<Block>> blockTags = material.getBlockTags(Stream.of(TRAPPED_CHEST).map(MinecraftWoodBlocks::getName).toList());
        blockTags.add(UVCommonBlockTags.WOODEN_CHESTS);
        List<TagKey<Item>> itemTags = material.getItemTags(Stream.of(MinecraftWoodBlocks.TRAPPED_CHEST).map(MinecraftWoodBlocks::getName).toList());
        itemTags.add(UVCommonItemTags.WOODEN_CHESTS);
        var plankTag = material.getItemTag(PLANKS.getName());
        BiFunction<String, String, Block> blockSupplier = (String id, String langFileName) -> BlockHandler.registerBlockWithRecipe(id, langFileName,
                material.getNamespace(), new BaseTrappedChestBlock(plankBlock),
                item-> ItemGroupHelper.addToGroup(CreativeModeTabs.REDSTONE_BLOCKS, material, TRAPPED_CHEST.getName(), false, ()->Blocks.TRAPPED_CHEST),
                (blockModelGenerators,block)->{
                    TextureMapping textureMapping = new TextureMapping().put(TextureSlot.TEXTURE, BuiltInRegistries.BLOCK.getKey((Block) block).withPrefix("block/trapped_chest/"));
                    var chestModel = UpgradedVanillaModelTemplates.CHEST.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
                    blockModelGenerators.blockEntityModels(ModelLocationUtils.decorateBlockModelLocation("trapped_"+material+"_chest"), plankBlock).create((Block) block);
                    blockModelGenerators.delegateItemModel((Block) block, chestModel);
                },
                ((finishedRecipeConsumer, item) -> {
                    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, item,1).define('P',plankTag)
                            .pattern("PPP")
                            .pattern("P P")
                            .pattern("PPP")
                            .unlockedBy("has_"+material+"_planks", FabricRecipeProvider.has(plankTag))
                            .save(finishedRecipeConsumer);
                }),
                BlockLootSubProvider::dropSelf,
                blockTags, itemTags);
        var returnBlock = GenericBlockFactory.createBlock(material, TRAPPED_CHEST.getName(), "trapped_"+material + "_chest", blockSupplier,
                blockTags, itemTags);
        if(returnBlock != null) {
            TextureHelper.addTexture(() -> {
                try {
                    File chestEntityTextureLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\entity\\chest\\MATERIAL_chest.png");
                    File chestLeftEntityTextureLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\entity\\chest\\MATERIAL_chest_left.png");
                    File chestRightEntityTextureLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\entity\\chest\\MATERIAL_chest_right.png");
                    BufferedImage chestTexture = TextureHelper.swapColors("block\\chest\\trapped_" + material + "_chest", "block\\chest", material.getNamespace(),
                            ImageIO.read(chestEntityTextureLocation), TextureHelper.woodPresetPalette, material.palette);
                    BufferedImage chestLeftTexture = TextureHelper.swapColors("block\\chest\\trapped_" + material + "_chest_left", "block\\chest", material.getNamespace(),
                            ImageIO.read(chestLeftEntityTextureLocation), TextureHelper.woodPresetPalette, material.palette);
                    BufferedImage chestRightTexture = TextureHelper.swapColors("block\\chest\\trapped_" + material + "_chest_right", "block\\chest", material.getNamespace(),
                            ImageIO.read(chestRightEntityTextureLocation), TextureHelper.woodPresetPalette, material.palette);
                    File chestOverlayLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\overlay\\entity\\trapped_chest\\MATERIAL_chest.png");
                    File chestLeftOverlayLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\overlay\\entity\\trapped_chest\\MATERIAL_chest_left.png");
                    File chestRightOverlayLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\overlay\\entity\\trapped_chest\\MATERIAL_chest_right.png");
                    TextureHelper.overlayTexture(chestTexture, ImageIO.read(chestOverlayLocation), 0, 0, "block\\trapped_chest\\trapped_" + material + "_chest", "block\\trapped_chest", material.getNamespace());
                    TextureHelper.overlayTexture(chestLeftTexture, ImageIO.read(chestLeftOverlayLocation), 0, 0, "block\\trapped_chest\\trapped_" + material + "_chest_left", "block\\trapped_chest", material.getNamespace());
                    TextureHelper.overlayTexture(chestRightTexture, ImageIO.read(chestRightOverlayLocation), 0, 0, "block\\trapped_chest\\trapped_" + material + "_chest_right", "block\\trapped_chest", material.getNamespace());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            if(CompleteConsistency.isClient()){
                BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
                BaseTrappedChestBlockEntityRenderer.registerRenderLayer(returnBlock);
            }
        }

        TagKey<Block> blockTag = material.getBlockTag((CHEST.getName()));
        TagKey<Item> itemTag = material.getItemTag((CHEST.getName()));
        TagHelper.addItemTags(itemTag, List.of(UpgradedVanillaTags.WOODEN_LADDER_ITEM_TAG));
        TagHelper.addBlockTags(blockTag, List.of(UpgradedVanillaTags.WOODEN_LADDER_BLOCK_TAG, BlockTags.CLIMBABLE, BlockTags.MINEABLE_WITH_AXE));
    }
}
