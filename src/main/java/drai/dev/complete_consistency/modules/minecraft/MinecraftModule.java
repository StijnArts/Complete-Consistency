package drai.dev.complete_consistency.modules.minecraft;

import drai.dev.complete_consistency.helpers.*;
import drai.dev.complete_consistency.materials.impl.*;
import drai.dev.complete_consistency.modules.*;
import drai.dev.complete_consistency.modules.minecraft.materials.wood.*;
import drai.dev.complete_consistency.modules.minecraft.materials.wood.enums.*;
import drai.dev.complete_consistency.modules.minecraft.materials.wood.factories.*;
import drai.dev.complete_consistency.registry.*;
import drai.dev.complete_consistency.tag.*;
import net.minecraft.world.item.*;

public class MinecraftModule extends MaterialModule {
    public MinecraftModule() {
        super("minecraft", 0);
        int materialCounter = 0;
        materials.add(new Oak(namespace, ++materialCounter));
        materials.add(new Spruce(namespace, ++materialCounter));
        materials.add(new Birch(namespace, ++materialCounter));
        materials.add(new Jungle(namespace, ++materialCounter));
        materials.add(new Acacia(namespace, ++materialCounter));
        materials.add(new DarkOak(namespace, ++materialCounter));
        materials.add(new Mangrove(namespace, ++materialCounter));
        materials.add(new Cherry(namespace, ++materialCounter));
        materials.add(new Bamboo(namespace, ++materialCounter));
        materials.add(new Crimson(namespace, ++materialCounter));
        materials.add(new Warped(namespace, ++materialCounter));
        materials.add(new Mushroom(namespace, ++materialCounter));
        registerMaterials();
    }

    public static void register(){
        UpgradedVanillaTags.register();
        ModuleRegistry.registerModule(new MinecraftModule());
    }

    @Override
    public void registerBlockAndItemTypes() {
        registerWoodTypes();
        registerRecipeReplacements();
    }

    private void registerRecipeReplacements() {
        RecipeReplacementsHelper.addReplacement(()->Items.STICK, UpgradedVanillaTags.STICKS);
        RecipeReplacementsHelper.addReplacement(()->Items.TORCH, UpgradedVanillaTags.TORCH_ITEM_TAG);
        RecipeReplacementsHelper.addReplacement(()->Items.SOUL_TORCH, UpgradedVanillaTags.SOUL_TORCH_ITEM_TAG);
    }

    private void registerWoodTypes() {
        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.LOG.getName(), MinecraftLogBlockFactory::logBlock);
        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.WOOD.getName(), MinecraftLogBlockFactory::woodBlock);
        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.STRIPPED_LOG.getName(), MinecraftLogBlockFactory::strippedLogBlock);
        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.STRIPPED_WOOD.getName(), MinecraftLogBlockFactory::strippedWoodBlock);
        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.PLANKS.getName(), MinecraftPlankBlockFactory::plankBlock);
        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.STICK.getName(), MinecraftWoodItemFactory::stickItem);
        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.STRIPPED_WOOD_STAIRS.getName(), MinecraftLogBlockFactory::strippedWoodStairsBlock);
        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.STRIPPED_WOOD_SLAB.getName(), MinecraftLogBlockFactory::strippedWoodSlabBlock);
        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.STRIPPED_WOOD_WALL.getName(), MinecraftLogBlockFactory::strippedWoodWallBlock);
        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.STRIPPED_WOOD_BUTTON.getName(), MinecraftLogBlockFactory::strippedWoodButtonBlock);
        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.STRIPPED_WOOD_TRAPDOOR.getName(), MinecraftLogBlockFactory::strippedWoodTrapdoorBlock);
        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.STRIPPED_WOOD_FENCE.getName(), MinecraftLogBlockFactory::strippedWoodFenceBlock);
        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.STRIPPED_WOOD_FENCE_GATE.getName(), MinecraftLogBlockFactory::strippedWoodFenceGateBlock);
        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.STRIPPED_WOOD_PRESSURE_PLATE.getName(), MinecraftLogBlockFactory::strippedWoodPressurePlateBlock);
        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.WOOD_STAIRS.getName(), MinecraftLogBlockFactory::woodStairsBlock);
        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.WOOD_SLAB.getName(), MinecraftLogBlockFactory::woodSlabBlock);
        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.WOOD_WALL.getName(), MinecraftLogBlockFactory::woodWallBlock);
        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.WOOD_BUTTON.getName(), MinecraftLogBlockFactory::woodButtonBlock);
        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.WOOD_TRAPDOOR.getName(), MinecraftLogBlockFactory::woodTrapdoorBlock);
        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.WOOD_FENCE.getName(), MinecraftLogBlockFactory::woodFenceBlock);
        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.WOOD_FENCE_GATE.getName(), MinecraftLogBlockFactory::woodFenceGateBlock);
        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.WOOD_PRESSURE_PLATE.getName(), MinecraftLogBlockFactory::woodPressurePlateBlock);
        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.STAIRS.getName(), MinecraftPlankBlockFactory::plankStairsBlock);
        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.SLAB.getName(), MinecraftPlankBlockFactory::plankSlabBlock);
        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.WALL.getName(), MinecraftPlankBlockFactory::plankWallBlock);
        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.BUTTON.getName(), MinecraftPlankBlockFactory::plankButtonBlock);
        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.DOOR.getName(), MinecraftPlankBlockFactory::plankDoorBlock);
        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.TRAPDOOR.getName(), MinecraftPlankBlockFactory::plankTrapdoorBlock);
        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.FENCE.getName(), MinecraftPlankBlockFactory::plankFenceBlock);
        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.FENCE_GATE.getName(), MinecraftPlankBlockFactory::plankFenceGateBlock);
        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.PRESSURE_PLATE.getName(), MinecraftPlankBlockFactory::plankPressurePlateBlock);
        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.LADDER.getName(), MinecraftPlankBlockFactory::plankLadderBlock);
        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.SIGN_ITEM.getName(), MinecraftWoodItemFactory::signItem);
        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.CHEST.getName(), MinecraftPlankBlockFactory::chestBlock);
        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.TRAPPED_CHEST.getName(), MinecraftPlankBlockFactory::trappedChestBlock);
        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.BOAT.getName(), MinecraftWoodItemFactory::boatItem);
        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.CHEST_BOAT.getName(), MinecraftWoodItemFactory::chestBoatItem);
        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.BARREL.getName(), MinecraftPlankBlockFactory::barrelBlock);
        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.TORCH_ITEM.getName(), MinecraftWoodItemFactory::torchItem);
        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.SOUL_TORCH_ITEM.getName(), MinecraftWoodItemFactory::soulTorchItem);
        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.REDSTONE_TORCH_ITEM.getName(), MinecraftWoodItemFactory::redstoneTorchItem);
        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.CRAFTING_TABLE.getName(), MinecraftPlankBlockFactory::craftingTableBlock);
        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.CAMPFIRE.getName(), MinecraftLogBlockFactory::campfireBlock);
        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.SOUL_CAMPFIRE.getName(), MinecraftLogBlockFactory::soulCampfireBlock);
        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.BOOKSHELF.getName(), MinecraftPlankBlockFactory::bookshelfBlock);
        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.CHISELED_BOOKSHELF.getName(), MinecraftPlankBlockFactory::chiseledBookshelfBlock);
        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.LECTERN.getName(), MinecraftPlankBlockFactory::lecternBlock);
        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.BEEHIVE.getName(), MinecraftPlankBlockFactory::beehiveBlock);
        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.COMPOSTER.getName(), MinecraftPlankBlockFactory::composterBlock);
        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.CARTOGRAPHY_TABLE.getName(), MinecraftPlankBlockFactory::cartographyTableBlock);
        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.FLETCHING_TABLE.getName(), MinecraftPlankBlockFactory::fletchingTableBlock);
        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.SCAFFOLDING_ITEM.getName(), MinecraftWoodItemFactory::scaffoldingBlockItem);
        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.ITEM_FRAME.getName(), MinecraftWoodItemFactory::itemFrame);
//        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.GLOW_ITEM_FRAME.getName(), MinecraftWoodItemFactory::glowItemFrame);
    }
}
