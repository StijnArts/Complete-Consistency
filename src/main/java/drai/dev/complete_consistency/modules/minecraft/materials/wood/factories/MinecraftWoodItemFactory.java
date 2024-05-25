package drai.dev.complete_consistency.modules.minecraft.materials.wood.factories;

import drai.dev.complete_consistency.helpers.*;
import drai.dev.complete_consistency.helpers.item.*;
import drai.dev.complete_consistency.helpers.itemgroup.*;
import drai.dev.complete_consistency.helpers.languages.*;
import drai.dev.complete_consistency.items.*;
import drai.dev.complete_consistency.materials.impl.*;
import drai.dev.complete_consistency.model.*;
import drai.dev.complete_consistency.modules.minecraft.generic.*;
import drai.dev.complete_consistency.registry.*;
import drai.dev.complete_consistency.tag.*;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.minecraft.client.model.*;
import net.minecraft.client.resources.model.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.*;
import net.minecraft.data.models.*;
import net.minecraft.data.models.blockstates.*;
import net.minecraft.data.models.model.*;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;

import javax.imageio.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import java.util.function.*;

import static drai.dev.complete_consistency.modules.minecraft.materials.wood.enums.MinecraftWoodBlocks.*;

public class MinecraftWoodItemFactory {
    public static void stickItem(WoodMaterial material){
        TagKey<Item> plankTag = material.getItemTag(PLANKS.getName());
        BiFunction<String, String, Item> itemSupplier = (String id, String langFileName) -> ItemHandler.registerItemWithRecipe(id, langFileName, material.getNamespace(),
                new Item(new Item.Properties().stacksTo(64)),
                item-> ItemGroupHelper.addToGroup(CreativeModeTabs.INGREDIENTS, material, STICK.getName(), true, ()->Items.STICK),
                (itemModelGenerator,item)->{itemModelGenerator.generateFlatItem(item, ModelTemplates.FLAT_ITEM);},
                (finishedRecipeConsumer, item) -> {
                    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, item,4).define('P',plankTag)
                            .pattern("P")
                            .pattern("P")
                            .unlockedBy("has_"+material+"_planks", FabricRecipeProvider.has(plankTag)).save(finishedRecipeConsumer);
                },
                List.of(UpgradedVanillaTags.STICKS)
        );
        Item returnItem = GenericItemFactory.createItem(material, STICK.getName(), material + "_stick", itemSupplier, List.of());
        if(returnItem != null) {
            TextureHelper.addTexture(() -> {
                File stickTextureLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\item\\MATERIAL_stick.png");
                try {
                    TextureHelper.swapColors("item\\" + material + "_stick", "item", material.getNamespace(), ImageIO.read(stickTextureLocation), TextureHelper.woodPresetPalette, material.palette);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    public static void signItem(WoodMaterial material){
        MinecraftPlankBlockFactory.standingSignBlock(material);
        MinecraftPlankBlockFactory.wallSignBlock(material);
        var standing = material.getBlock(STANDING_SIGN.getName());
        var wall = material.getBlock(WALL_SIGN.getName());
        var plankBlock = material.getBlock(PLANKS.getName());
        var stick = material.getItem(STICK.getName());
        var plankTag = material.getItemTag(PLANKS.getName());
        BiFunction<String, String, Item> itemSupplier = (String id, String langFileName) -> ItemHandler.registerItemWithRecipeWithBlockState(id, langFileName,
                material.getNamespace(), new SignItem(new Item.Properties().stacksTo(16),standing, wall),
                item-> ItemGroupHelper.addToGroup(CreativeModeTabs.FUNCTIONAL_BLOCKS, material, SIGN_ITEM.getName(), true, ()->Items.WARPED_HANGING_SIGN),
                (itemModelGenerator,item)->{itemModelGenerator.generateFlatItem(item, ModelTemplates.FLAT_ITEM);},
                (blockModelGenerators,block)->{
                    TextureMapping textureMapping = TextureMapping.defaultTexture(plankBlock);
                    ResourceLocation resourceLocation = ModelTemplates.PARTICLE_ONLY.create(standing, textureMapping, blockModelGenerators.modelOutput);
                    blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(standing, resourceLocation));
                    blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(wall, resourceLocation));
                    blockModelGenerators.skipAutoItemBlock(standing);
                },
                (finishedRecipeConsumer, item) -> {
                    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, item,3).define('P',plankTag).define('S',stick)
                            .pattern("PPP")
                            .pattern("PPP")
                            .pattern(" S ")
                            .unlockedBy("has_"+material+"_planks", FabricRecipeProvider.has(plankTag)).save(finishedRecipeConsumer);
                },
                List.of(ItemTags.SIGNS)
        );
        Item returnItem = GenericItemFactory.createItem(material, SIGN_ITEM.getName(), material + "_sign", itemSupplier, List.of());
        if(returnItem != null) {
            TextureHelper.addTexture(() -> {
                try {
                    File ladderTextureLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\item\\MATERIAL_sign.png");
                    BufferedImage signTexture = TextureHelper.swapColors("item\\" + material + "_sign", "item", material.getNamespace(),
                            ImageIO.read(ladderTextureLocation), TextureHelper.woodPresetPalette, material.palette);
                    File textTextureLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\overlay\\item\\MATERIAL_sign.png");
                    TextureHelper.overlayTexture(signTexture, ImageIO.read(textTextureLocation), 0, 0, "item\\" + material + "_sign", "item", material.getNamespace());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    public static void boatItem(WoodMaterial material){
        var plankTag = material.getItemTag(PLANKS.getName());
        BiFunction<String, String, Item> itemSupplier = (String id, String langFileName) -> ItemHandler.registerItemWithRecipe(id, langFileName, material.getNamespace(),
                material.createBoatType().createItem(false),
                item-> ItemGroupHelper.addToGroup(CreativeModeTabs.TOOLS_AND_UTILITIES, material, BOAT.getName(), true, ()->Items.BAMBOO_CHEST_RAFT),
                (itemModelGenerator,item)->{itemModelGenerator.generateFlatItem(item, ModelTemplates.FLAT_ITEM);},
                (finishedRecipeConsumer, item) -> {
                    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, item,1).define('P',plankTag)
                            .pattern("P P")
                            .pattern("PPP")
                            .unlockedBy("has_"+material+"_planks", FabricRecipeProvider.has(plankTag)).save(finishedRecipeConsumer);
                },
                List.of()
        );
        Item returnItem = GenericItemFactory.createItem(material, BOAT.getName(), material + "_boat", itemSupplier, List.of());
        if(returnItem != null) {
            TextureHelper.addTexture(() -> {
                try {
                    File boatEntityTexture = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\entity\\boat\\MATERIAL.png");
                    File boatTexture = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\item\\MATERIAL_boat.png");
                    TextureHelper.swapColors("entity\\boat\\" + material, "entity\\boat", material.getNamespace(),
                            ImageIO.read(boatEntityTexture), TextureHelper.woodPresetPalette, material.palette);
                    TextureHelper.swapColors("item\\" + material + "_boat", "item", material.getNamespace(),
                            ImageIO.read(boatTexture), TextureHelper.woodPresetPalette, material.palette);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    public static void chestBoatItem(WoodMaterial material){
        var boat = material.getItem(BOAT.getName());
        BiFunction<String, String, Item> itemSupplier = (String id, String langFileName) -> ItemHandler.registerItemWithRecipe(id, langFileName, material.getNamespace(),
                material.getBoatType().createItem(true),
                item-> ItemGroupHelper.addToGroup(CreativeModeTabs.TOOLS_AND_UTILITIES, material, CHEST_BOAT.getName(), true, ()->material.getItem(BOAT.getName())),
                (itemModelGenerator,item)->{itemModelGenerator.generateFlatItem(item, ModelTemplates.FLAT_ITEM);},
                (finishedRecipeConsumer, item) -> {
                    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, item,1).define('C',UVCommonItemTags.WOODEN_CHESTS).define('B',boat)
                            .pattern("C")
                            .pattern("B")
                            .unlockedBy("has_"+material+"_planks", FabricRecipeProvider.has(boat)).save(finishedRecipeConsumer);
                },
                List.of()
        );
        GenericItemFactory.createItem(material, CHEST_BOAT.getName(), material + "_chest_boat", itemSupplier, List.of());
        TextureHelper.addTexture(() -> {
            try {
                File boatEntityTexture = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\entity\\chest_boat\\MATERIAL.png");
                File boatItemTexture = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\item\\MATERIAL_chest_boat.png");
                BufferedImage chestBoatTexture = TextureHelper.swapColors("entity\\chest_boat\\"+material, "entity\\chest_boat", material.getNamespace(),
                        ImageIO.read(boatEntityTexture), TextureHelper.woodPresetPalette,material.palette);
                File chestBoatOverlayLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\overlay\\entity\\chest_boat\\MATERIAL.png");
                TextureHelper.overlayTexture(chestBoatTexture,ImageIO.read(chestBoatOverlayLocation), 0,0,"entity\\chest_boat\\"+material, "entity\\chest_boat", material.getNamespace());
                BufferedImage chestBoatItemTexture = TextureHelper.swapColors("item\\"+material+"_chest_boat", "item", material.getNamespace(),
                        ImageIO.read(boatItemTexture), TextureHelper.woodPresetPalette,material.palette);
                File chestBoatItemOverlayLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\overlay\\item\\MATERIAL_chest_boat.png");
                TextureHelper.overlayTexture(chestBoatItemTexture,ImageIO.read(chestBoatItemOverlayLocation), 0,0,"item\\"+material+"_chest_boat", "item", material.getNamespace());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static void torchItem(WoodMaterial material) {
        MinecraftPlankBlockFactory.torchBlock(material);
        MinecraftPlankBlockFactory.wallTorchBlock(material);
        var torch = material.getBlock(TORCH.getName());
        var wallTorch = material.getBlock(WALL_TORCH.getName());
        var stick = material.getItem(STICK.getName());
        var torchTag = material.getItemTag(TORCH_ITEM.getName());
        TagHelper.addItemTags(UpgradedVanillaTags.TORCH_ITEM_TAG, List.of(torchTag));
        BiFunction<String, String, Item> itemSupplier = (String id, String langFileName) -> ItemHandler.registerItemWithRecipeWithBlockState(id, langFileName,
                material.getNamespace(), new StandingAndWallBlockItem(torch, wallTorch, new Item.Properties(), Direction.DOWN),
                item-> ItemGroupHelper.addToGroup(CreativeModeTabs.FUNCTIONAL_BLOCKS, material, TORCH_ITEM.getName(), true, ()->Items.TORCH),
                (itemModelGenerator,item)->{},
                (blockModelGenerators,block)->{
                    TextureMapping textureMapping = TextureMapping.torch(torch);
                    blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(torch, ModelTemplates.TORCH.create(torch, textureMapping,blockModelGenerators.modelOutput)));
                    blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(wallTorch, Variant.variant().with(VariantProperties.MODEL, ModelTemplates.WALL_TORCH.create(wallTorch, textureMapping, blockModelGenerators.modelOutput))).with(BlockModelGenerators.createTorchHorizontalDispatch()));
                    blockModelGenerators.createSimpleFlatItemModel(torch);
                    blockModelGenerators.skipAutoItemBlock(wallTorch);
                },
                ((finishedRecipeConsumer, item) -> {
                    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, item,4).define('S',stick).define('C',ItemTags.COALS)
                            .pattern("C")
                            .pattern("S")
                            .unlockedBy("has_"+material+"_sticks", FabricRecipeProvider.has(stick))
                            .save(finishedRecipeConsumer);
                }), List.of(torchTag));
        Item returnItem = GenericItemFactory.createItem(material, TORCH_ITEM.getName(), material + "_torch", itemSupplier, List.of());
        if(returnItem != null) {
            TextureHelper.addTexture(() -> {
                try {
                    File torchTextureLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\block\\MATERIAL_torch.png");
                    BufferedImage torchTexture = TextureHelper.swapColors("block\\" + material + "_torch_block", "block", material.getNamespace(), ImageIO.read(torchTextureLocation), TextureHelper.woodPresetPalette, material.palette);
                    File torchOverlay = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\overlay\\block\\MATERIAL_torch.png");
                    TextureHelper.overlayTexture(torchTexture, ImageIO.read(torchOverlay), 0, 0, "block\\" + material + "_torch_block", "block", material.getNamespace());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    public static void soulTorchItem(WoodMaterial material) {
        MinecraftPlankBlockFactory.soulTorchBlock(material);
        MinecraftPlankBlockFactory.wallSoulTorchBlock(material);
        var torch = material.getBlock(SOUL_TORCH.getName());
        var wallTorch = material.getBlock(SOUL_WALL_TORCH.getName());
        var stick = material.getItem(STICK.getName());
        var torchTag = material.getItemTag(SOUL_TORCH_ITEM.getName());
        TagHelper.addItemTags(UpgradedVanillaTags.SOUL_TORCH_ITEM_TAG, List.of(torchTag));
        BiFunction<String, String, Item> itemSupplier = (String id, String langFileName) -> ItemHandler.registerItemWithRecipeWithBlockState(id, langFileName,
                material.getNamespace(), new StandingAndWallBlockItem(torch, wallTorch, new Item.Properties(), Direction.DOWN),
                item-> ItemGroupHelper.addToGroup(CreativeModeTabs.FUNCTIONAL_BLOCKS, material, SOUL_TORCH_ITEM.getName(), true, ()->Items.SOUL_TORCH),
                (itemModelGenerator,item)->{},
                (blockModelGenerators,block)->{
                    TextureMapping textureMapping = TextureMapping.torch(torch);
                    blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(torch, ModelTemplates.TORCH.create(torch, textureMapping,blockModelGenerators.modelOutput)));
                    blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(wallTorch, Variant.variant().with(VariantProperties.MODEL, ModelTemplates.WALL_TORCH.create(wallTorch, textureMapping, blockModelGenerators.modelOutput))).with(BlockModelGenerators.createTorchHorizontalDispatch()));
                    blockModelGenerators.createSimpleFlatItemModel(torch);
                    blockModelGenerators.skipAutoItemBlock(wallTorch);
                },
                ((finishedRecipeConsumer, item) -> {
                    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, item,4).define('S',stick).define('F',ItemTags.SOUL_FIRE_BASE_BLOCKS).define('C', UVCommonItemTags.COAL)
                            .pattern("C")
                            .pattern("S")
                            .pattern("F")
                            .unlockedBy("has_"+material+"_soul_fire_base_blocks", FabricRecipeProvider.has(ItemTags.SOUL_FIRE_BASE_BLOCKS))
                            .save(finishedRecipeConsumer);
                }), List.of(torchTag));
        Item returnItem = GenericItemFactory.createItem(material, SOUL_TORCH_ITEM.getName(), material + "_soul_torch", itemSupplier, List.of());
        if(returnItem != null) {
            TextureHelper.addTexture(() -> {
                try {
                    File leverTextureLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\block\\MATERIAL_soul_torch.png");
                    BufferedImage leverTexture = TextureHelper.swapColors("block\\"+material +"_soul_torch_block", "block", material.getNamespace(), ImageIO.read(leverTextureLocation), TextureHelper.woodPresetPalette,material.palette);
                    File leverOverlay = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\overlay\\block\\MATERIAL_soul_torch.png");
                    TextureHelper.overlayTexture(leverTexture,ImageIO.read(leverOverlay), 0,0,"block\\"+material + "_soul_torch_block", "block", material.getNamespace());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    public static void redstoneTorchItem(WoodMaterial material) {
        MinecraftPlankBlockFactory.redstoneTorchBlock(material);
        MinecraftPlankBlockFactory.wallRedstoneTorchBlock(material);
        var torch = material.getBlock(REDSTONE_TORCH.getName());
        var wallTorch = material.getBlock(REDSTONE_WALL_TORCH.getName());
        var stick = material.getItem(STICK.getName());
        var torchTag = material.getItemTag(REDSTONE_TORCH_ITEM.getName());
        TagHelper.addItemTags(UpgradedVanillaTags.REDSTONE_TORCH_ITEM_TAG, List.of(torchTag));
        BiFunction<String, String, Item> itemSupplier = (String id, String langFileName) -> ItemHandler.registerItemWithRecipeWithBlockState(id, langFileName,
                material.getNamespace(), new StandingAndWallBlockItem(torch, wallTorch, new Item.Properties(), Direction.DOWN),
                item-> ItemGroupHelper.addToGroup(CreativeModeTabs.FUNCTIONAL_BLOCKS, material, REDSTONE_TORCH_ITEM.getName(), true, ()->Items.REDSTONE_TORCH),
                (itemModelGenerator,item)->{},
                (blockModelGenerators,block)->{
                    TextureMapping textureMapping = TextureMapping.torch(torch);
                    blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(torch, ModelTemplates.TORCH.create(torch, textureMapping,blockModelGenerators.modelOutput)));
                    blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(wallTorch, Variant.variant().with(VariantProperties.MODEL, ModelTemplates.WALL_TORCH.create(wallTorch, textureMapping, blockModelGenerators.modelOutput))).with(BlockModelGenerators.createTorchHorizontalDispatch()));
                    blockModelGenerators.createSimpleFlatItemModel(torch);
                    blockModelGenerators.skipAutoItemBlock(wallTorch);
                },
                ((finishedRecipeConsumer, item) -> {
                    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, item,4).define('S',stick).define('C',Items.REDSTONE)
                            .pattern("C")
                            .pattern("S")
                            .unlockedBy("has_"+material+"_sticks", FabricRecipeProvider.has(stick))
                            .save(finishedRecipeConsumer);
                }), List.of(torchTag));
        Item returnItem = GenericItemFactory.createItem(material, REDSTONE_TORCH_ITEM.getName(), material + "_redstone_torch", itemSupplier, List.of());
        if(returnItem != null) {
            TextureHelper.addTexture(() -> {
                try {
                    File redstoneTorchOnLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\block\\MATERIAL_redstone_torch.png");
                    File redstoneTorchOffLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\block\\MATERIAL_redstone_torch_off.png");
                    BufferedImage redstoneTorchONTexture = TextureHelper.swapColors("block\\"+material +"_redstone_torch_block", "block", material.getNamespace(), ImageIO.read(redstoneTorchOnLocation), TextureHelper.woodPresetPalette,material.palette);
                    File redstoneTorchOnOverlay = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\overlay\\block\\MATERIAL_redstone_torch.png");
                    TextureHelper.overlayTexture(redstoneTorchONTexture,ImageIO.read(redstoneTorchOnOverlay), 0,0,"block\\"+material + "_redstone_torch_block", "block", material.getNamespace());
                    BufferedImage redstoneTorchOffTexture = TextureHelper.swapColors("block\\"+material +"_redstone_torch_off_block", "block", material.getNamespace(), ImageIO.read(redstoneTorchOffLocation), TextureHelper.woodPresetPalette,material.palette);
                    File redstoneTorchOffOverlay = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\overlay\\block\\MATERIAL_redstone_torch_off.png");
                    TextureHelper.overlayTexture(redstoneTorchOffTexture,ImageIO.read(redstoneTorchOffOverlay), 0,0,"block\\"+material + "_redstone_torch_off_block", "block", material.getNamespace());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    public static void scaffoldingBlockItem(WoodMaterial material) {
        MinecraftPlankBlockFactory.scaffoldingBlock(material);
        var scaffoldingBlock = material.getBlock(SCAFFOLDING.getName());
        var stick = material.getItem(STICK.getName());
        var torchTag = material.getItemTag(SCAFFOLDING_ITEM.getName());
        BiFunction<String, String, Item> itemSupplier = (String id, String langFileName) -> ItemHandler.registerItemWithRecipeWithBlockState(id, langFileName,
                material.getNamespace(), new ScaffoldingBlockItem(scaffoldingBlock, new Item.Properties()),
                item-> ItemGroupHelper.addToGroup(CreativeModeTabs.FUNCTIONAL_BLOCKS, material, SCAFFOLDING_ITEM.getName(), true, ()->Items.SCAFFOLDING),
                (itemModelGenerator,item)->{},
                (blockModelGenerators,block)->{
                },
                ((finishedRecipeConsumer, item) -> {
                    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, item,6).define('S',stick).define('C',Items.STRING)
                            .pattern("SCS")
                            .pattern("S S")
                            .pattern("S S")
                            .unlockedBy("has_"+material+"_sticks", FabricRecipeProvider.has(stick))
                            .save(finishedRecipeConsumer);
                }), List.of(torchTag));
        GenericItemFactory.createItem(material, SCAFFOLDING_ITEM.getName(), material + "_scaffolding", itemSupplier, List.of());
    }

    public static void itemFrame(WoodMaterial material) {
        MinecraftPlankBlockFactory.scaffoldingBlock(material);
        var plankBlock = material.getBlock(PLANKS.getName());
        var stick = material.getItem(STICK.getName());
        var torchTag = material.getItemTag(ITEM_FRAME.getName());
        BiFunction<String, String, Item> itemSupplier = (String id, String langFileName) -> ItemHandler.registerItemWithRecipeWithBlockState(id, langFileName,
                material.getNamespace(), new CCItemFrameItem(BaseBlockEntities.ITEM_FRAME, new Item.Properties(), material),
                item-> ItemGroupHelper.addToGroup(CreativeModeTabs.FUNCTIONAL_BLOCKS, material, ITEM_FRAME.getName(), true, ()->Items.ITEM_FRAME),
                (itemModelGenerator,item)->{
                    TextureMapping textureMapping = new TextureMapping()
                            .put(TextureSlot.BACK, new ResourceLocation("minecraft", "block/item_frame"))
                            .put(UpgradedVanillaModelTemplates.WOOD, TextureMapping.getBlockTexture(plankBlock))
                            .put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(plankBlock));
                    UpgradedVanillaModelTemplates.ITEM_FRAME.create(BuiltInRegistries.ITEM.getKey(item).withPrefix("block/"), textureMapping, itemModelGenerator.output);
                    UpgradedVanillaModelTemplates.ITEM_FRAME_MAP.create(BuiltInRegistries.ITEM.getKey(item).withPrefix("block/").withSuffix("_map"), textureMapping, itemModelGenerator.output);
                    itemModelGenerator.generateFlatItem(item, ModelTemplates.FLAT_ITEM);
                },
                (blockModelGenerators,block)->{
                },
                ((finishedRecipeConsumer, item) -> {
                    ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, item,6).define('S',stick).define('L',Items.LEATHER)
                            .pattern("SSS")
                            .pattern("SLS")
                            .pattern("SSS")
                            .unlockedBy("has_"+material+"_sticks", FabricRecipeProvider.has(stick))
                            .save(finishedRecipeConsumer);
                }), List.of(torchTag));
        var returnItem = GenericItemFactory.createItem(material, ITEM_FRAME.getName(), material + "_item_frame", itemSupplier, List.of());
        if(returnItem != null) {
            TextureHelper.addTexture(() -> {
                try {
                    File birchPalette = RelativeFileHelper.getAssetLocation("/minecraft/palettes/wood/birch_palette.png");
                    File redstoneTorchOnLocation = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\item\\item_frame.png");
                    BufferedImage redstoneTorchONTexture = TextureHelper.swapColors("item\\"+material +"_item_frame", "item", material.getNamespace(), ImageIO.read(redstoneTorchOnLocation), birchPalette,material.palette);
                    File redstoneTorchOnOverlay = RelativeFileHelper.getTemplateData("\\wood\\assets\\textures\\overlay\\item\\item_frame.png");
                    TextureHelper.overlayTexture(redstoneTorchONTexture,ImageIO.read(redstoneTorchOnOverlay), 0,0,"item\\"+material +"_item_frame", "item", material.getNamespace());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
}
