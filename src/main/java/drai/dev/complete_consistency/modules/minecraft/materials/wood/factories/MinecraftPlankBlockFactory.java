package drai.dev.complete_consistency.modules.minecraft.materials.wood.factories;

import drai.dev.complete_consistency.helpers.*;
import drai.dev.complete_consistency.helpers.block.*;
import drai.dev.complete_consistency.materials.*;
import drai.dev.complete_consistency.modules.minecraft.generic.*;
import drai.dev.complete_consistency.modules.minecraft.materials.wood.*;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.minecraft.data.loot.*;
import net.minecraft.data.models.*;
import net.minecraft.data.models.model.*;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;

import javax.imageio.*;
import java.io.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class MinecraftPlankBlockFactory {
    public static void plankBlock(WoodMaterial material) {
        var logTag = material.getItemTag(MinecraftWoodBlocks.LOG.getName());
        List<TagKey<Block>> blockTags = material.getBlockTags(Stream.of(MinecraftWoodBlocks.STRIPPED_WOOD).map(MinecraftWoodBlocks::getName).toList());
        List<TagKey<Item>> itemTags = material.getItemTags(Stream.of(MinecraftWoodBlocks.STRIPPED_WOOD).map(MinecraftWoodBlocks::getName).toList());
        BiFunction<String, String, Block> blockSupplier = (String id, String langFileName) -> BlockHandler.registerBlockWithRecipe(MinecraftWoodBlocks.indexOf(MinecraftWoodBlocks.PLANKS),
                id, langFileName,
                material.getNamespace(),
                new Block(BlockBehaviour.Properties.of().mapColor(material.getColor()).instrument(NoteBlockInstrument.BASS)
                        .strength(2.0f, 3.0f).sound(SoundType.WOOD).ignitedByLava()),
                ItemGroupHelper.createAfterConsumer(CreativeModeTabs.BUILDING_BLOCKS, () -> material.getNextBlock(MinecraftWoodBlocks.PLANKS.getName())),
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
}
