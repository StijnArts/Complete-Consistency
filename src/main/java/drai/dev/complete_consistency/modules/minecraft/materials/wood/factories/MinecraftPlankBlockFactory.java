package drai.dev.complete_consistency.modules.minecraft.materials.wood.factories;

import drai.dev.complete_consistency.*;
import drai.dev.complete_consistency.helpers.*;
import drai.dev.complete_consistency.helpers.block.*;
import drai.dev.complete_consistency.helpers.languages.*;
import drai.dev.complete_consistency.materials.*;
import drai.dev.complete_consistency.modules.minecraft.generic.*;
import drai.dev.complete_consistency.modules.minecraft.materials.wood.*;
import drai.dev.complete_consistency.tag.*;
import net.fabricmc.fabric.api.blockrenderlayer.v1.*;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.fabricmc.fabric.api.object.builder.v1.block.*;
import net.minecraft.client.renderer.*;
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
                item->ItemGroupHelper.addToGroup(CreativeModeTabs.BUILDING_BLOCKS, item, () -> material.getPreviousBlock(MinecraftWoodBlocks.PLANKS.getName())),
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
        Block plankBlock = material.getBlock(PLANKS.getName());
        var defaultState = plankBlock.defaultBlockState();
        GenericBlockFactory.createStairs(material, MinecraftWoodBlocks.STAIRS.getName(), plankBlock, PLANKS.getName(),
                new StairBlock(defaultState, BlockBehaviour.Properties.ofFullCopy(plankBlock)));

        TagKey<Block> blockTag = material.getBlockTag((MinecraftWoodBlocks.STAIRS.getName()));
        TagKey<Item> itemTag = material.getItemTag((MinecraftWoodBlocks.STAIRS.getName()));
        TagHelper.addBlockTags(blockTag, List.of(BlockTags.WOODEN_STAIRS, BlockTags.MINEABLE_WITH_AXE));
        TagHelper.addItemTags(itemTag, List.of(ItemTags.WOODEN_STAIRS));
    }

    public static void plankSlabBlock(WoodMaterial material) {
        Block plankBlock = material.getBlock(PLANKS.getName());
        GenericBlockFactory.createSlab(material, SLAB.getName(), plankBlock, PLANKS.getName(),
                ()->new SlabBlock(BlockBehaviour.Properties.ofFullCopy(plankBlock)));

        TagKey<Block> blockTag = material.getBlockTag((SLAB.getName()));
        TagKey<Item> itemTag = material.getItemTag((SLAB.getName()));
        TagHelper.addBlockTags(blockTag, List.of(BlockTags.WOODEN_SLABS, BlockTags.MINEABLE_WITH_AXE));
        TagHelper.addItemTags(itemTag, List.of(ItemTags.WOODEN_SLABS));
    }

    public static void plankWallBlock(WoodMaterial material) {
        Block plankBlock = material.getBlock(PLANKS.getName());
        var returnBlock = GenericBlockFactory.createWall(material, WALL.getName(), plankBlock, PLANKS.getName(),
                ()->new WallBlock(BlockBehaviour.Properties.ofFullCopy(plankBlock)));
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
                ()->Blocks.woodenButton(material.getBlockSetType()));
        TagKey<Block> blockTag = material.getBlockTag((BUTTON.getName()));
        TagKey<Item> itemTag = material.getItemTag((BUTTON.getName()));
        TagHelper.addBlockTags(blockTag, List.of(BlockTags.WOODEN_BUTTONS, BlockTags.MINEABLE_WITH_AXE));
        TagHelper.addItemTags(itemTag, List.of(ItemTags.WOODEN_BUTTONS));
    }

    public static void plankPressurePlateBlock(WoodMaterial material) {
        Block plankBlock = material.getBlock(PLANKS.getName());
        GenericBlockFactory.createPressurePlate(material, PRESSURE_PLATE.getName(), plankBlock, PLANKS.getName(),
                ()->new PressurePlateBlock(material.getBlockSetType(), BlockBehaviour.Properties.ofFullCopy(plankBlock)));
        TagKey<Block> blockTag = material.getBlockTag((PRESSURE_PLATE.getName()));
        TagKey<Item> itemTag = material.getItemTag((PRESSURE_PLATE.getName()));
        TagHelper.addBlockTags(blockTag, List.of(BlockTags.WOODEN_PRESSURE_PLATES, BlockTags.MINEABLE_WITH_AXE));
        TagHelper.addItemTags(itemTag, List.of(ItemTags.WOODEN_PRESSURE_PLATES));
    }

    public static void plankTrapdoorBlock(WoodMaterial material) {
        Block plankBlock = material.getBlock(PLANKS.getName());
        GenericBlockFactory.createTrapdoor(material, TRAPDOOR.getName(), plankBlock, PLANKS.getName(),
                () -> new TrapDoorBlock(material.getBlockSetType(), BlockBehaviour.Properties.ofFullCopy(plankBlock).noOcclusion()));
        TagKey<Block> blockTag = material.getBlockTag((TRAPDOOR.getName()));
        TagKey<Item> itemTag = material.getItemTag((TRAPDOOR.getName()));
        TagHelper.addBlockTags(blockTag, List.of(BlockTags.WOODEN_TRAPDOORS, BlockTags.MINEABLE_WITH_AXE));
        TagHelper.addItemTags(itemTag, List.of(ItemTags.WOODEN_TRAPDOORS));
    }

    public static void plankFenceBlock(WoodMaterial material) {
        Block plankBlock = material.getBlock(PLANKS.getName());
        Item stickItem = material.getItem(STICK.getName());
        GenericBlockFactory.createFenceBlock(material, FENCE.getName(), plankBlock, stickItem, PLANKS.getName(),
                ()-> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(plankBlock)));
        TagKey<Block> blockTag = material.getBlockTag((FENCE.getName()));
        TagKey<Item> itemTag = material.getItemTag((FENCE.getName()));
        TagHelper.addBlockTags(blockTag, List.of(BlockTags.WOODEN_FENCES, BlockTags.MINEABLE_WITH_AXE));
        TagHelper.addItemTags(itemTag, List.of(ItemTags.WOODEN_FENCES));
    }

    public static void plankFenceGateBlock(WoodMaterial material) {
        Block plankBlock = material.getBlock(PLANKS.getName());
        Item stickItem = material.getItem(STICK.getName());
        GenericBlockFactory.createFenceGateBlock(material, FENCE_GATE.getName(), plankBlock, stickItem, PLANKS.getName(),
                ()-> new FenceGateBlock(material.getWoodType(), BlockBehaviour.Properties.ofFullCopy(plankBlock)));
        TagKey<Block> blockTag = material.getBlockTag((FENCE_GATE.getName()));
        TagHelper.addBlockTags(blockTag, List.of(BlockTags.FENCE_GATES, BlockTags.MINEABLE_WITH_AXE));
    }
}
