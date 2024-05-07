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
        WoodMaterial.addBlockType(this, MinecraftWoodBlocks.LOG.getName(), MinecraftLogBlockFactory::logBlock);
        WoodMaterial.addBlockType(this, MinecraftWoodBlocks.WOOD.getName(), MinecraftLogBlockFactory::woodBlock);
        WoodMaterial.addBlockType(this, MinecraftWoodBlocks.STRIPPED_LOG.getName(), MinecraftLogBlockFactory::strippedLogBlock);
        WoodMaterial.addBlockType(this, MinecraftWoodBlocks.STRIPPED_WOOD.getName(), MinecraftLogBlockFactory::strippedWoodBlock);
        WoodMaterial.addBlockType(this, MinecraftWoodBlocks.PLANKS.getName(), MinecraftPlankBlockFactory::plankBlock);
        WoodMaterial.addBlockType(this, MinecraftWoodBlocks.STRIPPED_WOOD_STAIRS.getName(), (WoodMaterial material)-> MinecraftLogBlockFactory.strippedWoodStairsBlock(material, MinecraftWoodBlocks.WOOD.getName()));
        WoodMaterial.addBlockType(this, MinecraftWoodBlocks.STRIPPED_WOOD_SLAB.getName(), (WoodMaterial material)-> MinecraftLogBlockFactory.strippedWoodSlabBlock(material, MinecraftWoodBlocks.WOOD.getName()));
        WoodMaterial.addBlockType(this, MinecraftWoodBlocks.STRIPPED_WOOD_WALL.getName(), (WoodMaterial material)-> MinecraftLogBlockFactory.strippedLogWallBlock(material, MinecraftWoodBlocks.WOOD.getName()));
        WoodMaterial.addBlockType(this, MinecraftWoodBlocks.WOOD_STAIRS.getName(), (WoodMaterial material)-> MinecraftLogBlockFactory.woodStairsBlock(material, MinecraftWoodBlocks.WOOD.getName()));
        WoodMaterial.addBlockType(this, MinecraftWoodBlocks.WOOD_SLAB.getName(), (WoodMaterial material)-> MinecraftLogBlockFactory.woodSlabBlock(material, MinecraftWoodBlocks.WOOD.getName()));
        WoodMaterial.addBlockType(this, MinecraftWoodBlocks.WOOD_WALL.getName(), (WoodMaterial material)-> MinecraftLogBlockFactory.woodWallBlock(material, MinecraftWoodBlocks.WOOD.getName()));
    }
}
