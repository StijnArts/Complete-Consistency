package drai.dev.complete_consistency.modules.minecraft;

import drai.dev.complete_consistency.materials.*;
import drai.dev.complete_consistency.modules.*;
import drai.dev.complete_consistency.modules.minecraft.materials.wood.*;
import drai.dev.complete_consistency.modules.minecraft.materials.wood.factories.*;
import drai.dev.complete_consistency.registry.*;
import drai.dev.complete_consistency.tag.*;

public class MinecraftModule extends MaterialModule {
    public MinecraftModule() {
        super("minecraft", 0);
        materials.add(new Oak(namespace));
        materials.add(new Mushroom(namespace));
        registerMaterials();
    }

    public static void register(){
        UpgradedVanillaTags.register();
        ModuleRegistry.registerModule(new MinecraftModule());
    }

    @Override
    public void registerBlockAndItemTypes() {
        registerWoodTypes();
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
        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.TRAPDOOR.getName(), MinecraftPlankBlockFactory::plankTrapdoorBlock);
        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.FENCE.getName(), MinecraftPlankBlockFactory::plankFenceBlock);
        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.FENCE_GATE.getName(), MinecraftPlankBlockFactory::plankFenceGateBlock);
        WoodMaterial.addFactoryType(this, MinecraftWoodBlocks.PRESSURE_PLATE.getName(), MinecraftPlankBlockFactory::plankPressurePlateBlock);
    }
}
