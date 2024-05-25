package drai.dev.complete_consistency.modules.minecraft.materials.wood.factories;

import com.mojang.datafixers.util.*;
import drai.dev.complete_consistency.*;
import drai.dev.complete_consistency.blockentities.*;
import drai.dev.complete_consistency.blocks.blockentities.*;
import drai.dev.complete_consistency.client.renderer.*;
import drai.dev.complete_consistency.helpers.*;
import drai.dev.complete_consistency.helpers.block.*;
import drai.dev.complete_consistency.helpers.itemgroup.*;
import drai.dev.complete_consistency.helpers.languages.*;
import drai.dev.complete_consistency.materials.impl.*;
import drai.dev.complete_consistency.model.*;
import drai.dev.complete_consistency.modules.minecraft.generic.*;
import drai.dev.complete_consistency.modules.minecraft.materials.wood.enums.*;
import drai.dev.complete_consistency.tag.*;
import net.fabricmc.fabric.api.blockrenderlayer.v1.*;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.fabricmc.fabric.api.object.builder.v1.block.*;
import net.minecraft.client.renderer.*;
import net.minecraft.core.*;
import net.minecraft.core.particles.*;
import net.minecraft.core.registries.*;
import net.minecraft.data.loot.*;
import net.minecraft.data.models.*;
import net.minecraft.data.models.blockstates.*;
import net.minecraft.data.models.model.*;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.entity.ai.village.poi.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;

import javax.imageio.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import static drai.dev.complete_consistency.modules.minecraft.materials.wood.enums.MinecraftWoodBlocks.*;
import static drai.dev.complete_consistency.modules.minecraft.materials.wood.generators.ChiseledBookshelfGenerator.createChiseledBookshelf;
import static net.minecraft.data.recipes.RecipeCategory.MISC;

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
                    ShapedRecipeBuilder.shaped(MISC, item,3).define('S',stickItem)
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
                    ShapedRecipeBuilder.shaped(MISC, item,1).define('P',plankTag)
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
                    ShapedRecipeBuilder.shaped(MISC, item,1).define('P',plankTag)
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

    public static void barrelBlock(WoodMaterial material) {
        var plankBlock = material.getBlock(PLANKS.getName());
        List<TagKey<Block>> blockTags = material.getBlockTags(Stream.of(BARREL).map(MinecraftWoodBlocks::getName).toList());
//        blockTags.add(PoiTags.FISHERMAN_WORKSTATION);
        List<TagKey<Item>> itemTags = material.getItemTags(Stream.of(MinecraftWoodBlocks.BARREL).map(MinecraftWoodBlocks::getName).toList());
        itemTags.add(UVCommonItemTags.WOODEN_CHESTS);
        var plankTag = material.getItemTag(PLANKS.getName());
        var slabTag = material.getItemTag(SLAB.getName());
        BiFunction<String, String, Block> blockSupplier = (String id, String langFileName) -> BlockHandler.registerBlockWithRecipe(id, langFileName,
                material.getNamespace(), new BaseBarrelBlock(plankBlock),
                item-> ItemGroupHelper.addToGroup(CreativeModeTabs.FUNCTIONAL_BLOCKS, material, BARREL.getName(), false, ()->Blocks.BARREL),
                (blockModelGenerators,block)->{
                    ResourceLocation texture = TextureMapping.getBlockTexture((Block) block, "_top_open");
                    blockModelGenerators.blockStateOutput.accept(
                            MultiVariantGenerator.multiVariant((Block) block)
                                    .with(blockModelGenerators.createColumnWithFacing())
                                    .with(PropertyDispatch.property(BlockStateProperties.OPEN)
                                            .select(false, Variant.variant()
                                                    .with(VariantProperties.MODEL, TexturedModel.CUBE_TOP_BOTTOM.create((Block) block, blockModelGenerators.modelOutput)))
                                            .select(true, Variant.variant().with(VariantProperties.MODEL, TexturedModel.CUBE_TOP_BOTTOM.get((Block) block)
                                                    .updateTextures(textureMapping -> textureMapping.put(TextureSlot.TOP, texture))
                                                    .createWithSuffix((Block) block, "_open", blockModelGenerators.modelOutput)))));
                    blockModelGenerators.delegateItemModel((Block) block, ModelLocationUtils.getModelLocation((Block) block));
                },
                ((finishedRecipeConsumer, item) -> {
                    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, item,1).define('P',plankTag).define('S',slabTag)
                            .pattern("PSP")
                            .pattern("P P")
                            .pattern("PSP")
                            .unlockedBy("has_"+material+"_planks", FabricRecipeProvider.has(slabTag))
                            .save(finishedRecipeConsumer);
                }),
                BlockLootSubProvider::dropSelf,
                blockTags, itemTags);
        var returnBlock = GenericBlockFactory.createBlock(material, BARREL.getName(), material + "_barrel", blockSupplier,
                blockTags, itemTags);
        if(returnBlock != null) {
            for(var state : returnBlock.getStateDefinition().getPossibleStates()){
                var holderOptional = BuiltInRegistries.POINT_OF_INTEREST_TYPE.getHolder(PoiTypes.FISHERMAN);
                holderOptional.ifPresent(poiTypeReference -> PoiTypes.TYPE_BY_STATE.put(state, poiTypeReference));
            }

            if(CompleteConsistency.isClient()){
                BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
            }
            TextureHelper.addTexture(() -> {
                try {
                    File barrelTopTextureLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\block\\MATERIAL_barrel_top.png");
                    File barrelTopOpenTextureLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\block\\MATERIAL_barrel_top_open.png");
                    File barrelSideTextureLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\block\\MATERIAL_barrel_side.png");
                    File barrelBottomTextureLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\block\\MATERIAL_barrel_bottom.png");
                    BufferedImage barrelTopTexture = TextureHelper.swapColors("block\\" + material + "_barrel_top", "block", material.getNamespace(),
                            ImageIO.read(barrelTopTextureLocation), TextureHelper.woodPresetPalette, material.palette);
                    BufferedImage barrelTopOpenTexture = TextureHelper.swapColors("block\\" + material + "_barrel_top_open", "block", material.getNamespace(),
                            ImageIO.read(barrelTopOpenTextureLocation), TextureHelper.woodPresetPalette, material.palette);
                    BufferedImage barrelSideTexture = TextureHelper.swapColors("block\\" + material + "_barrel_side", "block", material.getNamespace(),
                            ImageIO.read(barrelSideTextureLocation), TextureHelper.woodPresetPalette, material.palette);
                    BufferedImage barrelBottomTexture = TextureHelper.swapColors("block\\" + material + "_barrel_bottom", "block", material.getNamespace(),
                            ImageIO.read(barrelBottomTextureLocation), TextureHelper.woodPresetPalette, material.palette);
                    File barrelTopOverlayLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\overlay\\block\\MATERIAL_barrel_top.png");
                    File barrelSideOverlayLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\overlay\\block\\MATERIAL_barrel_side.png");
                    TextureHelper.overlayTexture(barrelTopTexture, ImageIO.read(barrelTopOverlayLocation), 0, 0, "block\\" + material + "_barrel_top", "block", material.getNamespace());
                    TextureHelper.overlayTexture(barrelSideTexture, ImageIO.read(barrelSideOverlayLocation), 0, 0, "block\\" + material + "_barrel_side", "block", material.getNamespace());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    public static void wallTorchBlock(WoodMaterial material) {
        List<TagKey<Block>> blockTags = material.getBlockTags(Stream.of(WALL_TORCH).map(MinecraftWoodBlocks::getName).toList());
        BiFunction<String, String, Block> blockSupplier = (String id, String langFileName) -> BlockHandler.registerBlockWithoutItem(id,
                material.getNamespace(), new WallTorchBlock(ParticleTypes.FLAME, BlockBehaviour.Properties.ofFullCopy(Blocks.TORCH)),
                (blockModelGenerators,block)->{},
                BlockLootSubProvider::dropSelf,
                blockTags);
        var returnBlock = GenericBlockFactory.createBlock(material, WALL_TORCH.getName(), material + "_wall_torch", blockSupplier,
                blockTags, List.of());
        if(returnBlock != null) {
            if (CompleteConsistency.isClient()) {
                BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
            }
        }
    }

    public static void torchBlock(WoodMaterial material) {
        List<TagKey<Block>> blockTags = material.getBlockTags(Stream.of(TORCH).map(MinecraftWoodBlocks::getName).toList());
        BiFunction<String, String, Block> blockSupplier = (String id, String langFileName) -> BlockHandler.registerBlockWithoutItem(id,
                material.getNamespace(), new TorchBlock(ParticleTypes.FLAME, BlockBehaviour.Properties.ofFullCopy(Blocks.TORCH)),
                (blockModelGenerators,block)->{},
                BlockLootSubProvider::dropSelf,
                blockTags);
        var returnBlock = GenericBlockFactory.createBlock(material, TORCH.getName(), material + "_torch_block", blockSupplier,
                blockTags, List.of());
        if(returnBlock != null) {
            if (CompleteConsistency.isClient()) {
                BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
            }
        }
    }

    public static void wallSoulTorchBlock(WoodMaterial material) {
        List<TagKey<Block>> blockTags = material.getBlockTags(Stream.of(SOUL_WALL_TORCH).map(MinecraftWoodBlocks::getName).toList());
        BiFunction<String, String, Block> blockSupplier = (String id, String langFileName) -> BlockHandler.registerBlockWithoutItem(id,
                material.getNamespace(), new WallTorchBlock(ParticleTypes.SOUL_FIRE_FLAME, BlockBehaviour.Properties.ofFullCopy(Blocks.TORCH)),
                (blockModelGenerators,block)->{},
                BlockLootSubProvider::dropSelf,
                blockTags);
        var returnBlock = GenericBlockFactory.createBlock(material, SOUL_WALL_TORCH.getName(), material + "_soul_wall_torch", blockSupplier,
                blockTags, List.of());
        if(returnBlock != null) {
            if (CompleteConsistency.isClient()) {
                BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
            }
        }
    }

    public static void soulTorchBlock(WoodMaterial material) {
        List<TagKey<Block>> blockTags = material.getBlockTags(Stream.of(SOUL_TORCH).map(MinecraftWoodBlocks::getName).toList());
        BiFunction<String, String, Block> blockSupplier = (String id, String langFileName) -> BlockHandler.registerBlockWithoutItem(id,
                material.getNamespace(), new TorchBlock(ParticleTypes.SOUL_FIRE_FLAME, BlockBehaviour.Properties.ofFullCopy(Blocks.TORCH)),
                (blockModelGenerators,block)->{},
                BlockLootSubProvider::dropSelf,
                blockTags);
        var returnBlock = GenericBlockFactory.createBlock(material, SOUL_TORCH.getName(), material + "_soul_torch_block", blockSupplier,
                blockTags, List.of());
        if(returnBlock != null) {
            if (CompleteConsistency.isClient()) {
                BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
            }
        }
    }

    public static void wallRedstoneTorchBlock(WoodMaterial material) {
        List<TagKey<Block>> blockTags = material.getBlockTags(Stream.of(REDSTONE_WALL_TORCH).map(MinecraftWoodBlocks::getName).toList());
        BiFunction<String, String, Block> blockSupplier = (String id, String langFileName) -> BlockHandler.registerBlockWithoutItem(id,
                material.getNamespace(), new RedstoneWallTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TORCH)),
                (blockModelGenerators,block)->{},
                BlockLootSubProvider::dropSelf,
                blockTags);
        var returnBlock = GenericBlockFactory.createBlock(material, REDSTONE_WALL_TORCH.getName(), material + "_redstone_wall_torch", blockSupplier,
                blockTags, List.of());
        if(returnBlock != null) {
            if (CompleteConsistency.isClient()) {
                BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
            }
        }
    }

    public static void redstoneTorchBlock(WoodMaterial material) {
        List<TagKey<Block>> blockTags = material.getBlockTags(Stream.of(REDSTONE_TORCH).map(MinecraftWoodBlocks::getName).toList());
        BiFunction<String, String, Block> blockSupplier = (String id, String langFileName) -> BlockHandler.registerBlockWithoutItem(id,
                material.getNamespace(), new RedstoneTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TORCH)),
                (blockModelGenerators,block)->{},
                BlockLootSubProvider::dropSelf,
                blockTags);
        var returnBlock = GenericBlockFactory.createBlock(material, REDSTONE_TORCH.getName(), material + "_redstone_torch_block", blockSupplier,
                blockTags, List.of());
        if(returnBlock != null) {
            if (CompleteConsistency.isClient()) {
                BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
            }
        }
    }

    public static void craftingTableBlock(WoodMaterial material) {
        var plankBlock = material.getBlock(PLANKS.getName());
        List<TagKey<Block>> blockTags = material.getBlockTags(Stream.of(CRAFTING_TABLE).map(MinecraftWoodBlocks::getName).toList());
        List<TagKey<Item>> itemTags = material.getItemTags(Stream.of(MinecraftWoodBlocks.CRAFTING_TABLE).map(MinecraftWoodBlocks::getName).toList());
        var plankTag = material.getItemTag(PLANKS.getName());
        BiFunction<String, String, Block> blockSupplier = (String id, String langFileName) -> BlockHandler.registerBlockWithRecipe(id, langFileName,
                material.getNamespace(), new CraftingTableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CRAFTING_TABLE)),
                item-> ItemGroupHelper.addToGroup(CreativeModeTabs.FUNCTIONAL_BLOCKS, material, CRAFTING_TABLE.getName(), false, ()->Blocks.CRAFTING_TABLE),
                (blockModelGenerators,block)->{
                    TextureMapping textureMapping = TextureMapping.craftingTable((Block) block, plankBlock);
                    ResourceLocation model = ModelTemplates.CUBE.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
                    blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock((Block) block,model ));
                    blockModelGenerators.delegateItemModel((Block) block,model);
                },
                ((finishedRecipeConsumer, item) -> {
                    ShapedRecipeBuilder.shaped(MISC, item,1).define('S',plankTag)
                            .pattern("SS")
                            .pattern("SS")
                            .unlockedBy("has_"+material+"_planks", FabricRecipeProvider.has(plankTag))
                            .save(finishedRecipeConsumer);
                }),
                BlockLootSubProvider::dropSelf,
                blockTags, itemTags);
        var returnBlock = GenericBlockFactory.createBlock(material, CRAFTING_TABLE.getName(), material + "_crafting_table", blockSupplier,
                blockTags, itemTags);
        if(returnBlock != null) {
            TextureHelper.addTexture(() -> {
                try {
                    File craftingTableFrontTextureLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\block\\MATERIAL_crafting_table_front.png");
                    File craftingTableSideTextureLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\block\\MATERIAL_crafting_table_side.png");
                    File craftingTableTopTextureLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\block\\MATERIAL_crafting_table_top.png");
                    BufferedImage craftingTableFrontTexture = TextureHelper.swapColors("block\\" + material + "_crafting_table_front", "block", material.getNamespace(), ImageIO.read(craftingTableFrontTextureLocation), TextureHelper.woodPresetPalette, material.palette);
                    File craftingTableFrontOverlayLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\overlay\\block\\MATERIAL_crafting_table_front.png");
                    TextureHelper.overlayTexture(craftingTableFrontTexture, ImageIO.read(craftingTableFrontOverlayLocation), 0, 0, "block\\" + material + "_crafting_table_front", "block", material.getNamespace());
                    BufferedImage craftingTableSideTexture = TextureHelper.swapColors("block\\" + material + "_crafting_table_side", "block", material.getNamespace(), ImageIO.read(craftingTableSideTextureLocation), TextureHelper.woodPresetPalette, material.palette);
                    File craftingTableSideOverlayLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\overlay\\block\\MATERIAL_crafting_table_side.png");
                    TextureHelper.overlayTexture(craftingTableSideTexture, ImageIO.read(craftingTableSideOverlayLocation), 0, 0, "block\\" + material + "_crafting_table_side", "block", material.getNamespace());
                    BufferedImage craftingTableTopTexture = TextureHelper.swapColors("block\\" + material + "_crafting_table_top", "block", material.getNamespace(), ImageIO.read(craftingTableTopTextureLocation), TextureHelper.woodPresetPalette, material.palette);
                    File craftingTableTopOverlayLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\overlay\\block\\MATERIAL_crafting_table_top.png");
                    TextureHelper.overlayTexture(craftingTableTopTexture, ImageIO.read(craftingTableTopOverlayLocation), 0, 0, "block\\" + material + "_crafting_table_top", "block", material.getNamespace());

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    public static void bookshelfBlock(WoodMaterial material) {
        var plankBlock = material.getBlock(PLANKS.getName());
        List<TagKey<Block>> blockTags = material.getBlockTags(Stream.of(BOOKSHELF).map(MinecraftWoodBlocks::getName).toList());
        blockTags.add(BlockTags.ENCHANTMENT_POWER_PROVIDER);
        List<TagKey<Item>> itemTags = material.getItemTags(Stream.of(MinecraftWoodBlocks.BOOKSHELF).map(MinecraftWoodBlocks::getName).toList());
        var plankTag = material.getItemTag(PLANKS.getName());
        BiFunction<String, String, Block> blockSupplier = (String id, String langFileName) -> BlockHandler.registerBlockWithRecipe(id, langFileName,
                material.getNamespace(), new BaseBookshelfBlock(BlockBehaviour.Properties.ofFullCopy(plankBlock)),
                item-> ItemGroupHelper.addToGroup(CreativeModeTabs.FUNCTIONAL_BLOCKS, material, BOOKSHELF.getName(), false, ()->Blocks.BOOKSHELF),
                (blockModelGenerators,block)->{
                    TextureMapping textureMapping = (new TextureMapping()).put(TextureSlot.END, TextureMapping.getBlockTexture(plankBlock))
                            .put(TextureSlot.SIDE, TextureMapping.getBlockTexture((Block) block));
                    ResourceLocation bookshelf = ModelTemplates.CUBE_COLUMN.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
                    blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock((Block) block, bookshelf));
                    blockModelGenerators.delegateItemModel((Block) block,bookshelf);
                },
                ((finishedRecipeConsumer, item) -> {
                    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, item,1).define('B',Items.BOOK).define('P',plankTag)
                            .pattern("PPP")
                            .pattern("BBB")
                            .pattern("PPP")
                            .unlockedBy("has_"+material+"_planks", FabricRecipeProvider.has(plankTag))
                            .save(finishedRecipeConsumer);
                }),
                BlockLootSubProvider::dropSelf,
                blockTags, itemTags);
        var returnBlock = GenericBlockFactory.createBlock(material, BOOKSHELF.getName(), material + "_bookshelf", blockSupplier,
                blockTags, itemTags);
        if(returnBlock != null) {
            TextureHelper.addTexture(() -> {
                try {
                    File bookshelfTextureLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\block\\MATERIAL_bookshelf.png");
                    BufferedImage bookshelfTexture = TextureHelper.swapColors("block\\" + material + "_bookshelf", "block", material.getNamespace(), ImageIO.read(bookshelfTextureLocation), TextureHelper.woodPresetPalette, material.palette);
                    File bookshelfOverlayLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\overlay\\block\\MATERIAL_bookshelf.png");
                    TextureHelper.overlayTexture(bookshelfTexture, ImageIO.read(bookshelfOverlayLocation), 0, 0, "block\\" + material + "_bookshelf", "block", material.getNamespace());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    public static void chiseledBookshelfBlock(WoodMaterial material) {
        var plankBlock = material.getBlock(PLANKS.getName());
        List<TagKey<Block>> blockTags = material.getBlockTags(Stream.of(CHISELED_BOOKSHELF).map(MinecraftWoodBlocks::getName).toList());
        blockTags.add(BlockTags.ENCHANTMENT_POWER_PROVIDER);
        List<TagKey<Item>> itemTags = material.getItemTags(Stream.of(MinecraftWoodBlocks.CHISELED_BOOKSHELF).map(MinecraftWoodBlocks::getName).toList());
        var plankTag = material.getItemTag(PLANKS.getName());
        var slabTag = material.getItemTag(SLAB.getName());
        BiFunction<String, String, Block> blockSupplier = (String id, String langFileName) -> BlockHandler.registerBlockWithRecipe(id, langFileName,
                material.getNamespace(), new ChiseledBookShelfBlock(BlockBehaviour.Properties.ofFullCopy(plankBlock)),
                item-> ItemGroupHelper.addToGroup(CreativeModeTabs.FUNCTIONAL_BLOCKS, material, CHISELED_BOOKSHELF.getName(), false, ()->Blocks.CHISELED_BOOKSHELF),
                (blockModelGenerators,block)->{
                    createChiseledBookshelf((Block) block, blockModelGenerators);
                },
                ((finishedRecipeConsumer, item) -> {
                    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, item,1).define('B',slabTag).define('P',plankTag)
                            .pattern("PPP")
                            .pattern("BBB")
                            .pattern("PPP")
                            .unlockedBy("has_"+material+"_planks", FabricRecipeProvider.has(plankTag))
                            .save(finishedRecipeConsumer);
                }),
                BlockLootSubProvider::dropSelf,
                blockTags, itemTags);
        var returnBlock = GenericBlockFactory.createBlock(material, CHISELED_BOOKSHELF.getName(), material + "_chiseled_bookshelf", blockSupplier,
                blockTags, itemTags);
        if(returnBlock != null) {
            TextureHelper.addTexture(() -> {
                try {
                    File chiseledBookshelfTextureLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\block\\chiseled_bookshelf_empty.png");
                    BufferedImage emptyChiseledBookshelfTexture = TextureHelper.swapColors("block\\" + material + "_chiseled_bookshelf_empty", "block",
                            material.getNamespace(), ImageIO.read(chiseledBookshelfTextureLocation), TextureHelper.woodPresetPalette, material.palette);
                    File chiseledBookshelfSideTextureLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\block\\chiseled_bookshelf_side.png");
                    BufferedImage chiseledBookshelfSideTexture = TextureHelper.swapColors("block\\" + material + "_chiseled_bookshelf_side", "block",
                            material.getNamespace(), ImageIO.read(chiseledBookshelfSideTextureLocation), TextureHelper.woodPresetPalette, material.palette);
                    File chiseledBookshelfTopTextureLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\block\\chiseled_bookshelf_top.png");
                    BufferedImage chiseledBookshelfTopTexture = TextureHelper.swapColors("block\\" + material + "_chiseled_bookshelf_top", "block",
                            material.getNamespace(), ImageIO.read(chiseledBookshelfTopTextureLocation), TextureHelper.woodPresetPalette, material.palette);
                    File bookshelfOverlayLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\overlay\\block\\chiseled_bookshelf_occupied.png");
                    TextureHelper.overlayTexture(emptyChiseledBookshelfTexture, ImageIO.read(bookshelfOverlayLocation), 0, 0,
                            "block\\" + material + "_chiseled_bookshelf_occupied", "block", material.getNamespace());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    public static void lecternBlock(WoodMaterial material) {
        var plankBlock = material.getBlock(PLANKS.getName());
        List<TagKey<Block>> blockTags = material.getBlockTags(Stream.of(LECTERN).map(MinecraftWoodBlocks::getName).toList());
        List<TagKey<Item>> itemTags = material.getItemTags(Stream.of(MinecraftWoodBlocks.LECTERN).map(MinecraftWoodBlocks::getName).toList());
        var bookshelves = material.getItemTag(BOOKSHELF.getName());
        var slabTag = material.getItemTag(SLAB.getName());
        BiFunction<String, String, Block> blockSupplier = (String id, String langFileName) -> BlockHandler.registerBlockWithRecipe(id, langFileName,
                material.getNamespace(), new LecternBlock(BlockBehaviour.Properties.ofFullCopy(plankBlock)),
                item-> ItemGroupHelper.addToGroup(CreativeModeTabs.FUNCTIONAL_BLOCKS, material, LECTERN.getName(), false, ()->Blocks.LECTERN),
                (blockModelGenerators,block)->{
                    ResourceLocation particle = new ResourceLocation(material.getNamespace(),"block/"+material+"_lectern_sides");
                    ResourceLocation bottom = TextureMapping.getBlockTexture(plankBlock);
                    ResourceLocation base = new ResourceLocation(material.getNamespace(),"block/"+material+"_lectern_base");
                    ResourceLocation front = new ResourceLocation(material.getNamespace(),"block/"+material+"_lectern_front");
                    ResourceLocation sides = new ResourceLocation(material.getNamespace(),"block/"+material+"_lectern_sides");
                    ResourceLocation top = new ResourceLocation(material.getNamespace(),"block/"+material+"_lectern_top");
                    TextureMapping textureMapping = (new TextureMapping())
                            .put(TextureSlot.PARTICLE, particle)
                            .put(TextureSlot.BOTTOM, bottom)
                            .put(UpgradedVanillaModelTemplates.BASE, base)
                            .put(TextureSlot.FRONT, front)
                            .put(UpgradedVanillaModelTemplates.SIDES, sides)
                            .put(TextureSlot.TOP, top);
                    ResourceLocation lecternModel = UpgradedVanillaModelTemplates.LECTERN.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
                    blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant((Block) block, Variant.variant().with(VariantProperties.MODEL, lecternModel)).with(BlockModelGenerators.createHorizontalFacingDispatch()));
                    blockModelGenerators.delegateItemModel((Block) block,lecternModel);
                },
                ((finishedRecipeConsumer, item) -> {
                    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, item,1).define('B',bookshelves).define('S',slabTag)
                            .pattern("SSS")
                            .pattern(" B ")
                            .pattern(" S ")
                            .unlockedBy("has_"+material+"_bookshelf", FabricRecipeProvider.has(bookshelves))
                            .save(finishedRecipeConsumer);
                }),
                BlockLootSubProvider::dropSelf,
                blockTags, itemTags);
        var returnBlock = GenericBlockFactory.createBlock(material, LECTERN.getName(), material + "_lectern", blockSupplier,
                blockTags, itemTags);
        if(returnBlock != null) {
            for(var state : returnBlock.getStateDefinition().getPossibleStates()){
                var holderOptional = BuiltInRegistries.POINT_OF_INTEREST_TYPE.getHolder(PoiTypes.LIBRARIAN);
                holderOptional.ifPresent(poiTypeReference -> PoiTypes.TYPE_BY_STATE.put(state, poiTypeReference));
            }
            TextureHelper.addTexture(() -> {
                try {
                    File lecternSidesTextureLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\block\\MATERIAL_lectern_sides.png");
                    File lecternBaseTextureLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\block\\MATERIAL_lectern_base.png");
                    File lecternFrontTextureLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\block\\MATERIAL_lectern_front.png");
                    File lecternTopTextureLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\block\\MATERIAL_lectern_top.png");
                    BufferedImage lecternSidesTexture = TextureHelper.swapColors("block\\" + material + "_lectern_sides", "block", material.getNamespace(), ImageIO.read(lecternSidesTextureLocation), TextureHelper.woodPresetPalette, material.palette);
                    BufferedImage lecternTopTexture = TextureHelper.swapColors("block\\" + material + "_lectern_top", "block", material.getNamespace(), ImageIO.read(lecternTopTextureLocation), TextureHelper.woodPresetPalette, material.palette);
                    BufferedImage lecternBaseTexture = TextureHelper.swapColors("block\\" + material + "_lectern_base", "block", material.getNamespace(), ImageIO.read(lecternBaseTextureLocation), TextureHelper.woodPresetPalette, material.palette);
                    File soul_campfireLogLitOverlayLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\overlay\\block\\MATERIAL_lectern_base.png");
                    TextureHelper.overlayTexture(lecternBaseTexture, ImageIO.read(soul_campfireLogLitOverlayLocation), 0, 0, "block\\" + material + "_lectern_base", "block", material.getNamespace());
                    BufferedImage lecternFrontTexture = TextureHelper.swapColors("block\\" + material + "_lectern_front", "block", material.getNamespace(), ImageIO.read(lecternFrontTextureLocation), TextureHelper.woodPresetPalette, material.palette);
                    File lecternFrontOverlayLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\overlay\\block\\MATERIAL_lectern_front.png");
                    TextureHelper.overlayTexture(lecternFrontTexture, ImageIO.read(lecternFrontOverlayLocation), 0, 0, "block\\" + material + "_lectern_front", "block", material.getNamespace());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            if(CompleteConsistency.isClient()) BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
        }
    }

    public static void beehiveBlock(WoodMaterial material) {
        var plankBlock = material.getBlock(PLANKS.getName());
        List<TagKey<Block>> blockTags = material.getBlockTags(Stream.of(LECTERN).map(MinecraftWoodBlocks::getName).toList());
        blockTags.add(BlockTags.BEEHIVES);
        List<TagKey<Item>> itemTags = material.getItemTags(Stream.of(MinecraftWoodBlocks.LECTERN).map(MinecraftWoodBlocks::getName).toList());
        var plankBlocks = material.getItemTag(PLANKS.getName());
        var slabTag = material.getItemTag(SLAB.getName());
        BiFunction<String, String, Block> blockSupplier = (String id, String langFileName) -> BlockHandler.registerBlockWithRecipe(id, langFileName,
                material.getNamespace(), new BeehiveBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEEHIVE)),
                item-> ItemGroupHelper.addToGroup(CreativeModeTabs.FUNCTIONAL_BLOCKS, material, BEEHIVE.getName(), false, ()->Blocks.BEEHIVE),
                (blockModelGenerators, block) -> {
                    TextureMapping textureMapping = TextureMapping.orientableCubeSameEnds((Block) block).copyForced(TextureSlot.SIDE, TextureSlot.PARTICLE);
                    TextureMapping textureMapping2 = textureMapping.copyAndUpdate(TextureSlot.FRONT, TextureMapping.getBlockTexture((Block) block, "_front_honey"));
                    ResourceLocation resourceLocation = ModelTemplates.CUBE_ORIENTABLE_TOP_BOTTOM.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
                    ResourceLocation resourceLocation2 = ModelTemplates.CUBE_ORIENTABLE_TOP_BOTTOM.createWithSuffix((Block) block, "_honey", textureMapping2, blockModelGenerators.modelOutput);
                    blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant((Block) block).with(BlockModelGenerators.createHorizontalFacingDispatch()).with(BlockModelGenerators.createEmptyOrFullDispatch(BlockStateProperties.LEVEL_HONEY, 5, resourceLocation2, resourceLocation)));
                    blockModelGenerators.delegateItemModel((Block) block, resourceLocation);
                },
                ((finishedRecipeConsumer, item) -> {
                    ShapedRecipeBuilder.shaped(MISC, item, 1).define('P', plankBlocks).define('H', Items.HONEYCOMB)
                            .pattern("PPP")
                            .pattern("HHH")
                            .pattern("PPP")
                            .unlockedBy("has_" + material + "_planks", FabricRecipeProvider.has(plankBlocks))
                            .save(finishedRecipeConsumer);
                }),
                (blockLootProvider, block) -> blockLootProvider.add(block, BlockLootSubProvider.createBeeHiveDrop(block)),
                blockTags, itemTags);
        var returnBlock = GenericBlockFactory.createBlock(material, BEEHIVE.getName(), material + "_beehive", blockSupplier,
                blockTags, itemTags);
        if(returnBlock != null) {
            for (var state : returnBlock.getStateDefinition().getPossibleStates()) {
                var holderOptional = BuiltInRegistries.POINT_OF_INTEREST_TYPE.getHolder(PoiTypes.BEEHIVE);
                holderOptional.ifPresent(poiTypeReference -> PoiTypes.TYPE_BY_STATE.put(state, poiTypeReference));
            }
            TextureHelper.addTexture(() -> {
                try {
                    File beehiveFrontTextureLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\block\\MATERIAL_beehive_front.png");
                    File beehiveEndTextureLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\block\\MATERIAL_beehive_end.png");
                    File beehiveSideTextureLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\block\\MATERIAL_beehive_side.png");
                    File beehiveFrontHoneyTextureLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\block\\MATERIAL_beehive_front_honey.png");
                    BufferedImage beehiveFrontTexture = TextureHelper.swapColors("block\\" + material + "_beehive_front", "block", material.getNamespace(), ImageIO.read(beehiveFrontTextureLocation), TextureHelper.woodPresetPalette, material.palette);
                    File beehiveFrontOverlayLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\overlay\\block\\MATERIAL_beehive_front.png");
                    TextureHelper.overlayTexture(beehiveFrontTexture, ImageIO.read(beehiveFrontOverlayLocation), 0, 0, "block\\" + material + "_beehive_front", "block", material.getNamespace());
                    BufferedImage beehiveEndTexture = TextureHelper.swapColors("block\\" + material + "_beehive_end", "block", material.getNamespace(), ImageIO.read(beehiveEndTextureLocation), TextureHelper.woodPresetPalette, material.palette);
                    BufferedImage beehiveSideTexture = TextureHelper.swapColors("block\\" + material + "_beehive_side", "block", material.getNamespace(), ImageIO.read(beehiveSideTextureLocation), TextureHelper.woodPresetPalette, material.palette);
                    File beehiveSideOverlayLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\overlay\\block\\MATERIAL_beehive_side.png");
                    TextureHelper.overlayTexture(beehiveSideTexture, ImageIO.read(beehiveSideOverlayLocation), 0, 0, "block\\" + material + "_beehive_side", "block", material.getNamespace());
                    BufferedImage beehiveFrontHoneyTexture = TextureHelper.swapColors("block\\" + material + "_beehive_front_honey", "block", material.getNamespace(), ImageIO.read(beehiveFrontHoneyTextureLocation), TextureHelper.woodPresetPalette, material.palette);
                    File beehiveFrontHoneyOverlayLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\overlay\\block\\MATERIAL_beehive_front_honey.png");
                    TextureHelper.overlayTexture(beehiveFrontHoneyTexture, ImageIO.read(beehiveFrontHoneyOverlayLocation), 0, 0, "block\\" + material + "_beehive_front_honey", "block", material.getNamespace());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    public static void composterBlock(WoodMaterial material) {
        var plankBlock = material.getBlock(PLANKS.getName());
        List<TagKey<Block>> blockTags = material.getBlockTags(Stream.of(COMPOSTER).map(MinecraftWoodBlocks::getName).toList());
        List<TagKey<Item>> itemTags = material.getItemTags(Stream.of(MinecraftWoodBlocks.COMPOSTER).map(MinecraftWoodBlocks::getName).toList());
        var plankBlocks = material.getItemTag(PLANKS.getName());
        var slabTag = material.getItemTag(SLAB.getName());
        BiFunction<String, String, Block> blockSupplier = (String id, String langFileName) -> BlockHandler.registerBlockWithRecipe(id, langFileName,
                material.getNamespace(), new ComposterBlock(BlockBehaviour.Properties.ofFullCopy(plankBlock)),
                item -> ItemGroupHelper.addToGroup(CreativeModeTabs.FUNCTIONAL_BLOCKS, material, COMPOSTER.getName(), false, () -> Blocks.COMPOSTER),
                (blockModelGenerators, block) -> {
                    TextureMapping textureMapping = new TextureMapping()
                            .put(TextureSlot.TOP, TextureMapping.getBlockTexture((Block) block, "_top"))
                            .put(TextureSlot.SIDE, TextureMapping.getBlockTexture((Block) block, "_side"))
                            .put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture((Block) block, "_side"))
                            .put(TextureSlot.INSIDE, TextureMapping.getBlockTexture((Block) block, "_bottom"))
                            .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture((Block) block, "_bottom"));
                    ResourceLocation resourceLocation = UpgradedVanillaModelTemplates.COMPOSTER.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
                    blockModelGenerators.blockStateOutput.accept(MultiPartGenerator.multiPart((Block) block)
                            .with(Variant.variant().with(VariantProperties.MODEL, TextureMapping.getBlockTexture((Block) block)))
                            .with((Condition) Condition.condition().term(BlockStateProperties.LEVEL_COMPOSTER, 1), Variant.variant().with(VariantProperties.MODEL, TextureMapping.getBlockTexture(Blocks.COMPOSTER, "_contents1")))
                            .with((Condition) Condition.condition().term(BlockStateProperties.LEVEL_COMPOSTER, 2), Variant.variant().with(VariantProperties.MODEL, TextureMapping.getBlockTexture(Blocks.COMPOSTER, "_contents2")))
                            .with((Condition) Condition.condition().term(BlockStateProperties.LEVEL_COMPOSTER, 3), Variant.variant().with(VariantProperties.MODEL, TextureMapping.getBlockTexture(Blocks.COMPOSTER, "_contents3")))
                            .with((Condition) Condition.condition().term(BlockStateProperties.LEVEL_COMPOSTER, 4), Variant.variant().with(VariantProperties.MODEL, TextureMapping.getBlockTexture(Blocks.COMPOSTER, "_contents4")))
                            .with((Condition) Condition.condition().term(BlockStateProperties.LEVEL_COMPOSTER, 5), Variant.variant().with(VariantProperties.MODEL, TextureMapping.getBlockTexture(Blocks.COMPOSTER, "_contents5")))
                            .with((Condition) Condition.condition().term(BlockStateProperties.LEVEL_COMPOSTER, 6), Variant.variant().with(VariantProperties.MODEL, TextureMapping.getBlockTexture(Blocks.COMPOSTER, "_contents6")))
                            .with((Condition) Condition.condition().term(BlockStateProperties.LEVEL_COMPOSTER, 7), Variant.variant().with(VariantProperties.MODEL, TextureMapping.getBlockTexture(Blocks.COMPOSTER, "_contents7")))
                            .with((Condition) Condition.condition().term(BlockStateProperties.LEVEL_COMPOSTER, 8), Variant.variant().with(VariantProperties.MODEL, TextureMapping.getBlockTexture(Blocks.COMPOSTER, "_contents_ready"))));
                    blockModelGenerators.delegateItemModel((Block) block, resourceLocation);
                },
                ((finishedRecipeConsumer, item) -> {
                    ShapedRecipeBuilder.shaped(MISC, item, 1).define('P', slabTag)
                            .pattern("P P")
                            .pattern("P P")
                            .pattern("PPP")
                            .unlockedBy("has_" + material + "_slabs", FabricRecipeProvider.has(slabTag))
                            .save(finishedRecipeConsumer);
                }),
                BlockLootSubProvider::dropSelf,
                blockTags, itemTags);
        var returnBlock = GenericBlockFactory.createBlock(material, COMPOSTER.getName(), material + "_composter", blockSupplier,
                blockTags, itemTags);
        if (returnBlock != null) {
            for (var state : returnBlock.getStateDefinition().getPossibleStates()) {
                var holderOptional = BuiltInRegistries.POINT_OF_INTEREST_TYPE.getHolder(PoiTypes.FARMER);
                holderOptional.ifPresent(poiTypeReference -> PoiTypes.TYPE_BY_STATE.put(state, poiTypeReference));
            }
            TextureHelper.addTexture(() -> {
                try {
                    File composterFrontTextureLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\block\\MATERIAL_composter_side.png");
                    File composterEndTextureLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\block\\MATERIAL_composter_top.png");
                    File composterSideTextureLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\block\\MATERIAL_composter_bottom.png");
                    BufferedImage composterFrontTexture = TextureHelper.swapColors("block\\" + material + "_composter_side", "block", material.getNamespace(), ImageIO.read(composterFrontTextureLocation), TextureHelper.woodPresetPalette, material.palette);
                    File composterFrontOverlayLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\overlay\\block\\MATERIAL_composter_side.png");
                    TextureHelper.overlayTexture(composterFrontTexture, ImageIO.read(composterFrontOverlayLocation), 0, 0, "block\\" + material + "_composter_side", "block", material.getNamespace());
                    TextureHelper.swapColors("block\\" + material + "_composter_top", "block", material.getNamespace(), ImageIO.read(composterEndTextureLocation), TextureHelper.woodPresetPalette, material.palette);
                    TextureHelper.swapColors("block\\" + material + "_composter_bottom", "block", material.getNamespace(), ImageIO.read(composterSideTextureLocation), TextureHelper.woodPresetPalette, material.palette);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    public static void cartographyTableBlock(WoodMaterial material) {
        var plankBlock = material.getBlock(PLANKS.getName());
        List<TagKey<Block>> blockTags = material.getBlockTags(Stream.of(CARTOGRAPHY_TABLE).map(MinecraftWoodBlocks::getName).toList());
        List<TagKey<Item>> itemTags = material.getItemTags(Stream.of(MinecraftWoodBlocks.CARTOGRAPHY_TABLE).map(MinecraftWoodBlocks::getName).toList());
        var plankBlocks = material.getItemTag(PLANKS.getName());
        BiFunction<String, String, Block> blockSupplier = (String id, String langFileName) -> BlockHandler.registerBlockWithRecipe(id, langFileName,
                material.getNamespace(), new CartographyTableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CARTOGRAPHY_TABLE)),
                item-> ItemGroupHelper.addToGroup(CreativeModeTabs.FUNCTIONAL_BLOCKS, material, CARTOGRAPHY_TABLE.getName(), false, ()->Blocks.CARTOGRAPHY_TABLE),
                (blockModelGenerators, block) -> {
                    TextureMapping textureMapping = new TextureMapping()
                            .put(TextureSlot.DOWN, TextureMapping.getBlockTexture(plankBlock))
                            .put(TextureSlot.EAST, new ResourceLocation(material.getNamespace(), "block/"+material + "_cartography_table_side3"))
                            .put(TextureSlot.NORTH,new ResourceLocation(material.getNamespace(), "block/"+material + "_cartography_table_side3"))
                            .put(TextureSlot.PARTICLE,new ResourceLocation(material.getNamespace(), "block/"+material + "_cartography_table_side3"))
                            .put(TextureSlot.SOUTH, new ResourceLocation(material.getNamespace(), "block/"+material + "_cartography_table_side1"))
                            .put(TextureSlot.UP,new ResourceLocation(material.getNamespace(), "block/"+material + "_cartography_table_top"))
                            .put(TextureSlot.WEST,new ResourceLocation(material.getNamespace(), "block/"+material + "_cartography_table_side2"));
                    ResourceLocation resourceLocation = UpgradedVanillaModelTemplates.CRAFTING_TABLE_LIKE.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
                    blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock((Block) block, resourceLocation));
                    blockModelGenerators.delegateItemModel((Block) block, resourceLocation);
                },
                ((finishedRecipeConsumer, item) -> {
                    ShapedRecipeBuilder.shaped(MISC, item, 1).define('P', plankBlocks).define('S', Items.PAPER)
                            .pattern("SS")
                            .pattern("PP")
                            .pattern("PP")
                            .unlockedBy("has_" + material + "_planks", FabricRecipeProvider.has(plankBlocks))
                            .save(finishedRecipeConsumer);
                }),
                BlockLootSubProvider::dropSelf,
                blockTags, itemTags);
        var returnBlock = GenericBlockFactory.createBlock(material, CARTOGRAPHY_TABLE.getName(), material + "_cartography_table", blockSupplier,
                blockTags, itemTags);
        if(returnBlock != null) {
            for (var state : returnBlock.getStateDefinition().getPossibleStates()) {
                var holderOptional = BuiltInRegistries.POINT_OF_INTEREST_TYPE.getHolder(PoiTypes.CARTOGRAPHER);
                holderOptional.ifPresent(poiTypeReference -> PoiTypes.TYPE_BY_STATE.put(state, poiTypeReference));
            }
            TextureHelper.addTexture(() -> {
                try {
                    File darkOakPalette = RelativeFileHelper.getAssetLocation("\\minecraft\\palettes\\wood\\dark_oak_palette.png");
                    File cartographyTableSide1TextureLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\block\\MATERIAL_cartography_table_side1.png");
                    File cartographyTableSide2TextureLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\block\\MATERIAL_cartography_table_side2.png");
                    File cartographyTableSide3TextureLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\block\\MATERIAL_cartography_table_side3.png");
                    File cartographyTableTopTextureLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\block\\MATERIAL_cartography_table_top.png");
                    BufferedImage cartographyTableSide1Texture = TextureHelper.swapColors("block\\" + material + "_cartography_table_side1", "block", material.getNamespace(), ImageIO.read(cartographyTableSide1TextureLocation), darkOakPalette, material.palette);
                    File cartographyTableSide1OverlayLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\overlay\\block\\MATERIAL_cartography_table_side1.png");
                    TextureHelper.overlayTexture(cartographyTableSide1Texture, ImageIO.read(cartographyTableSide1OverlayLocation), 0, 0, "block\\" + material + "_cartography_table_side1", "block", material.getNamespace());
                    BufferedImage cartographyTableSide2Texture = TextureHelper.swapColors("block\\" + material + "_cartography_table_side2", "block", material.getNamespace(), ImageIO.read(cartographyTableSide2TextureLocation), darkOakPalette, material.palette);
                    File cartographyTableSide2OverlayLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\overlay\\block\\MATERIAL_cartography_table_side2.png");
                    TextureHelper.overlayTexture(cartographyTableSide2Texture, ImageIO.read(cartographyTableSide2OverlayLocation), 0, 0, "block\\" + material + "_cartography_table_side2", "block", material.getNamespace());
                    TextureHelper.swapColors("block\\" + material + "_cartography_table_side3", "block", material.getNamespace(), ImageIO.read(cartographyTableSide3TextureLocation), darkOakPalette, material.palette);
                    BufferedImage cartographyTableTopTexture = TextureHelper.swapColors("block\\" + material + "_cartography_table_top", "block", material.getNamespace(), ImageIO.read(cartographyTableTopTextureLocation), darkOakPalette, material.palette);
                    File cartographyTableTopOverlayLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\overlay\\block\\MATERIAL_cartography_table_top.png");
                    TextureHelper.overlayTexture(cartographyTableTopTexture, ImageIO.read(cartographyTableTopOverlayLocation), 0, 0, "block\\" + material + "_cartography_table_top", "block", material.getNamespace());
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                    throw new RuntimeException(e);
                }
            });
        }
    }

    public static void fletchingTableBlock(WoodMaterial material) {
        var plankBlock = material.getBlock(PLANKS.getName());
        List<TagKey<Block>> blockTags = material.getBlockTags(Stream.of(FLETCHING_TABLE).map(MinecraftWoodBlocks::getName).toList());
        List<TagKey<Item>> itemTags = material.getItemTags(Stream.of(MinecraftWoodBlocks.FLETCHING_TABLE).map(MinecraftWoodBlocks::getName).toList());
        var plankBlocks = material.getItemTag(PLANKS.getName());
        var slabTag = material.getItemTag(SLAB.getName());
        BiFunction<String, String, Block> blockSupplier = (String id, String langFileName) -> BlockHandler.registerBlockWithRecipe(id, langFileName,
                material.getNamespace(), new FletchingTableBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.FLETCHING_TABLE)),
                item-> ItemGroupHelper.addToGroup(CreativeModeTabs.FUNCTIONAL_BLOCKS, material, FLETCHING_TABLE.getName(), false, ()->Blocks.FLETCHING_TABLE),
                (blockModelGenerators, block) -> {
                    TextureMapping textureMapping = new TextureMapping()
                            .put(TextureSlot.DOWN, TextureMapping.getBlockTexture(plankBlock))
                            .put(TextureSlot.EAST, new ResourceLocation(material.getNamespace(), "block/"+material + "_fletching_table_side"))
                            .put(TextureSlot.NORTH,new ResourceLocation(material.getNamespace(), "block/"+material + "_fletching_table_front"))
                            .put(TextureSlot.PARTICLE,new ResourceLocation(material.getNamespace(), "block/"+material + "_fletching_table_front"))
                            .put(TextureSlot.SOUTH, new ResourceLocation(material.getNamespace(), "block/"+material + "_fletching_table_front"))
                            .put(TextureSlot.UP,new ResourceLocation(material.getNamespace(), "block/"+material + "_fletching_table_top"))
                            .put(TextureSlot.WEST,new ResourceLocation(material.getNamespace(), "block/"+material + "_fletching_table_side"));
                    ResourceLocation resourceLocation = UpgradedVanillaModelTemplates.CRAFTING_TABLE_LIKE.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
                    blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock((Block) block, resourceLocation));
                    blockModelGenerators.delegateItemModel((Block) block, resourceLocation);
                },
                ((finishedRecipeConsumer, item) -> {
                    ShapedRecipeBuilder.shaped(MISC, item, 1).define('P', plankBlocks).define('S', Items.FLINT)
                            .pattern("SS")
                            .pattern("PP")
                            .pattern("PP")
                            .unlockedBy("has_" + material + "_planks", FabricRecipeProvider.has(plankBlocks))
                            .save(finishedRecipeConsumer);
                }),
                BlockLootSubProvider::dropSelf,
                blockTags, itemTags);
        var returnBlock = GenericBlockFactory.createBlock(material, FLETCHING_TABLE.getName(), material + "_fletching_table", blockSupplier,
                blockTags, itemTags);
        if(returnBlock != null) {
            for (var state : returnBlock.getStateDefinition().getPossibleStates()) {
                var holderOptional = BuiltInRegistries.POINT_OF_INTEREST_TYPE.getHolder(PoiTypes.FLETCHER);
                holderOptional.ifPresent(poiTypeReference -> PoiTypes.TYPE_BY_STATE.put(state, poiTypeReference));
            }
            TextureHelper.addTexture(() -> {
                try {
                    File birchPalette = RelativeFileHelper.getAssetLocation("\\minecraft\\palettes\\wood\\birch_palette.png");
                    File fletchingTableSideTextureLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\block\\MATERIAL_fletching_table_side.png");
                    File fletchingTableFrontTextureLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\block\\MATERIAL_fletching_table_front.png");
                    File fletchingTableTopTextureLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\block\\MATERIAL_fletching_table_top.png");
                    BufferedImage fletchingTableSideTexture = TextureHelper.swapColors("block\\" + material + "_fletching_table_side", "block", material.getNamespace(), ImageIO.read(fletchingTableSideTextureLocation), birchPalette, material.palette);
                    File fletchingTableSideOverlayLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\overlay\\block\\MATERIAL_fletching_table_side.png");
                    TextureHelper.overlayTexture(fletchingTableSideTexture, ImageIO.read(fletchingTableSideOverlayLocation), 0, 0, "block\\" + material + "_fletching_table_side", "block", material.getNamespace());
                    BufferedImage fletchingTableFrontTexture = TextureHelper.swapColors("block\\" + material + "_fletching_table_front", "block", material.getNamespace(), ImageIO.read(fletchingTableFrontTextureLocation), birchPalette, material.palette);
                    File fletchingTableFrontOverlayLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\overlay\\block\\MATERIAL_fletching_table_front.png");
                    TextureHelper.overlayTexture(fletchingTableFrontTexture, ImageIO.read(fletchingTableFrontOverlayLocation), 0, 0, "block\\" + material + "_fletching_table_front", "block", material.getNamespace());
                    BufferedImage fletchingTableTopTexture = TextureHelper.swapColors("block\\" + material + "_fletching_table_top", "block", material.getNamespace(), ImageIO.read(fletchingTableTopTextureLocation), birchPalette, material.palette);
                    File fletchingTableTopOverlayLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\overlay\\block\\MATERIAL_fletching_table_top.png");
                    TextureHelper.overlayTexture(fletchingTableTopTexture, ImageIO.read(fletchingTableTopOverlayLocation), 0, 0, "block\\" + material + "_fletching_table_top", "block", material.getNamespace());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    public static void scaffoldingBlock(WoodMaterial material) {
        List<TagKey<Block>> blockTags = material.getBlockTags(Stream.of(SCAFFOLDING).map(MinecraftWoodBlocks::getName).toList());
        blockTags.add(BlockTags.CLIMBABLE);
        BiFunction<String, String, Block> blockSupplier = (String id, String langFileName) -> BlockHandler.registerBlockWithoutItem(id,
                material.getNamespace(), new ScaffoldingBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SCAFFOLDING)),
                (blockModelGenerators,block)->{
                    TextureMapping textureMapping = new TextureMapping()
                            .put(TextureSlot.TOP, TextureMapping.getBlockTexture((Block) block, "_top"))
                            .put(TextureSlot.SIDE, TextureMapping.getBlockTexture((Block) block, "_side"))
                            .put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture((Block) block, "_top"))
                            .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture((Block) block, "_bottom"));
                    ResourceLocation resourceLocation = UpgradedVanillaModelTemplates.SCAFFOLDING_STABLE.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
                    ResourceLocation resourceLocation2 = UpgradedVanillaModelTemplates.SCAFFOLDING_UNSTABLE.create((Block) block, textureMapping, blockModelGenerators.modelOutput);
                    blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant((Block) block)
                            .with(BlockModelGenerators.createBooleanModelDispatch(BlockStateProperties.BOTTOM, resourceLocation2, resourceLocation)));
                    blockModelGenerators.delegateItemModel((Block) block, resourceLocation);
                },
                BlockLootSubProvider::dropSelf,
                blockTags);
        var returnBlock = GenericBlockFactory.createBlock(material, SCAFFOLDING.getName(), material + "_scaffolding_block", blockSupplier,
                blockTags, List.of());
        if(returnBlock != null) {
            if (CompleteConsistency.isClient()) {
                BlockRenderLayerMap.INSTANCE.putBlock(returnBlock, RenderType.cutout());
            }
            TextureHelper.addTexture(() -> {
                try {
                    File bambooPalette = RelativeFileHelper.getAssetLocation("\\minecraft\\palettes\\wood\\bamboo_palette.png");
                    File scaffoldingSideTextureLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\block\\scaffolding_side.png");
                    File scaffoldingBottomTextureLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\block\\scaffolding_bottom.png");
                    File scaffoldingTopTextureLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\block\\scaffolding_top.png");
                    BufferedImage scaffoldingBottomTexture = TextureHelper.swapColors("block\\" + material + "_scaffolding_block_bottom", "block", material.getNamespace(), ImageIO.read(scaffoldingBottomTextureLocation), bambooPalette, material.palette);
                    BufferedImage scaffoldingSideTexture = TextureHelper.swapColors("block\\" + material + "_scaffolding_block_side", "block", material.getNamespace(), ImageIO.read(scaffoldingSideTextureLocation), bambooPalette, material.palette);
                    File scaffoldingSideOverlayLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\overlay\\block\\scaffolding_side.png");
                    TextureHelper.overlayTexture(scaffoldingSideTexture, ImageIO.read(scaffoldingSideOverlayLocation), 0, 0, "block\\" + material + "_scaffolding_block_side", "block", material.getNamespace());
                    BufferedImage scaffoldingTOPTexture = TextureHelper.swapColors("block\\" + material + "_scaffolding_block_top", "block", material.getNamespace(), ImageIO.read(scaffoldingTopTextureLocation), bambooPalette, material.palette);
                    File scaffoldingTopOverlayLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\overlay\\block\\scaffolding_top.png");
                    TextureHelper.overlayTexture(scaffoldingTOPTexture, ImageIO.read(scaffoldingTopOverlayLocation), 0, 0, "block\\" + material + "_scaffolding_block_top", "block", material.getNamespace());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
}
