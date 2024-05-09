package drai.dev.complete_consistency.modules.minecraft.materials.wood.factories;

import drai.dev.complete_consistency.helpers.*;
import drai.dev.complete_consistency.helpers.item.*;
import drai.dev.complete_consistency.materials.*;
import drai.dev.complete_consistency.modules.minecraft.generic.*;
import drai.dev.complete_consistency.modules.minecraft.materials.wood.*;
import drai.dev.complete_consistency.tag.*;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.minecraft.data.models.model.*;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;

import javax.imageio.*;
import java.io.*;
import java.util.*;
import java.util.function.*;

public class MinecraftWoodItemFactory {
    public static void stickItem(WoodMaterial material){
        TagKey<Item> plankTag = material.getItemTag(MinecraftWoodBlocks.PLANKS.getName());
        BiFunction<String, String, Item> itemSupplier = (String id, String langFileName) -> ItemHandler.registerItemWithRecipe(id, langFileName, material.getNamespace(),
                new Item(new Item.Properties().stacksTo(64)),
                (itemModelGenerator,item)->{itemModelGenerator.generateFlatItem(item, ModelTemplates.FLAT_ITEM);},
                (finishedRecipeConsumer, item) -> {
                    ShapedRecipeBuilder.shaped(RecipeCategory.MISC, item,4).define('P',plankTag)
                            .pattern("P")
                            .pattern("P")
                            .unlockedBy("has_"+material+"_planks", FabricRecipeProvider.has(plankTag)).save(finishedRecipeConsumer);
                },
                List.of(UpgradedVanillaTags.STICKS)
        );
        Item returnItem = GenericItemFactory.createItem(material, MinecraftWoodBlocks.STICK.getName(), material + "_stick", itemSupplier, List.of());
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
}
