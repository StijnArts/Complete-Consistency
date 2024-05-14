package drai.dev.complete_consistency.modules.minecraft.materials.wood.factories;

import drai.dev.complete_consistency.helpers.*;
import drai.dev.complete_consistency.helpers.item.*;
import drai.dev.complete_consistency.helpers.itemgroup.*;
import drai.dev.complete_consistency.helpers.languages.*;
import drai.dev.complete_consistency.items.boat.*;
import drai.dev.complete_consistency.materials.impl.*;
import drai.dev.complete_consistency.modules.minecraft.generic.*;
import drai.dev.complete_consistency.tag.*;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.minecraft.data.models.*;
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

import static drai.dev.complete_consistency.modules.minecraft.materials.wood.MinecraftWoodBlocks.*;

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
}
