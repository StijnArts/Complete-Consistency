package drai.dev.complete_consistency.registry;

import com.google.common.collect.*;
import drai.dev.complete_consistency.materials.impl.*;
import net.minecraft.resources.*;

public class MaterialRegistry {
    public static LinkedHashMultimap<ResourceLocation, BrickMaterial> BRICK_MATERIALS = LinkedHashMultimap.create();
    public static LinkedHashMultimap<ResourceLocation, ConcreteMaterial> CONCRETE_MATERIALS = LinkedHashMultimap.create();
    public static LinkedHashMultimap<ResourceLocation, GlassMaterial> GLASS_MATERIALS = LinkedHashMultimap.create();
    public static LinkedHashMultimap<ResourceLocation, HayMaterial> HAY_MATERIALS = LinkedHashMultimap.create();
    public static LinkedHashMultimap<ResourceLocation, MetalMaterial> METAL_MATERIALS = LinkedHashMultimap.create();
    public static LinkedHashMultimap<ResourceLocation, QuartzMaterial> QUARTZ_MATERIALS = LinkedHashMultimap.create();
    public static LinkedHashMultimap<ResourceLocation, ResourceBlockMaterial> RESOURCE_BLOCK_MATERIALS = LinkedHashMultimap.create();
    public static LinkedHashMultimap<ResourceLocation, SandMaterial> SAND_MATERIALS = LinkedHashMultimap.create();
    public static LinkedHashMultimap<ResourceLocation, SoilMaterial> SOIL_MATERIALS = LinkedHashMultimap.create();
    public static LinkedHashMultimap<ResourceLocation, StoneMaterial> STONE_MATERIALS = LinkedHashMultimap.create();
    public static LinkedHashMultimap<ResourceLocation, TerracottaMaterial> TERRACOTTA_MATERIALS = LinkedHashMultimap.create();
    public static LinkedHashMultimap<ResourceLocation, WoodMaterial> WOOD_MATERIALS = LinkedHashMultimap.create();
    public static LinkedHashMultimap<ResourceLocation, WoolMaterial> WOOL_MATERIALS = LinkedHashMultimap.create();

    public static void registerMaterial(ResourceLocation resourceLocation, BrickMaterial material){
        BRICK_MATERIALS.put(resourceLocation, material);
    }

    public static void registerMaterial(ResourceLocation resourceLocation, ConcreteMaterial material){
        CONCRETE_MATERIALS.put(resourceLocation, material);
    }

    public static void registerMaterial(ResourceLocation resourceLocation, GlassMaterial material){
        GLASS_MATERIALS.put(resourceLocation, material);
    }

    public static void registerMaterial(ResourceLocation resourceLocation, HayMaterial material){
        HAY_MATERIALS.put(resourceLocation, material);
    }

    public static void registerMaterial(ResourceLocation resourceLocation, MetalMaterial material){
        METAL_MATERIALS.put(resourceLocation, material);
    }

    public static void registerMaterial(ResourceLocation resourceLocation, QuartzMaterial material){
        QUARTZ_MATERIALS.put(resourceLocation, material);
    }

    public static void registerMaterial(ResourceLocation resourceLocation, ResourceBlockMaterial material){
        RESOURCE_BLOCK_MATERIALS.put(resourceLocation, material);
    }

    public static void registerMaterial(ResourceLocation resourceLocation, SandMaterial material){
        SAND_MATERIALS.put(resourceLocation, material);
    }

    public static void registerMaterial(ResourceLocation resourceLocation, SoilMaterial material){
        SOIL_MATERIALS.put(resourceLocation, material);
    }

    public static void registerMaterial(ResourceLocation resourceLocation, StoneMaterial material){
        STONE_MATERIALS.put(resourceLocation, material);
    }

    public static void registerMaterial(ResourceLocation resourceLocation, TerracottaMaterial material){
        TERRACOTTA_MATERIALS.put(resourceLocation, material);
    }

    public static void registerMaterial(ResourceLocation resourceLocation, WoodMaterial material){
        WOOD_MATERIALS.put(resourceLocation, material);
    }

    public static void registerMaterial(ResourceLocation resourceLocation, WoolMaterial material){
        WOOL_MATERIALS.put(resourceLocation, material);
    }

    public static void runFactories() {
        for(var material : BRICK_MATERIALS.values()){
            material.createBlocks();
        }
        for(var material : CONCRETE_MATERIALS.values()){
            material.createBlocks();
        }
        for(var material : GLASS_MATERIALS.values()){
            material.createBlocks();
        }
        for(var material : HAY_MATERIALS.values()){
            material.createBlocks();
        }
        for(var material : METAL_MATERIALS.values()){
            material.createBlocks();
        }
        for(var material : QUARTZ_MATERIALS.values()){
            material.createBlocks();
        }
        for(var material : RESOURCE_BLOCK_MATERIALS.values()){
            material.createBlocks();
        }
        for(var material : SAND_MATERIALS.values()){
            material.createBlocks();
        }
        for(var material : SOIL_MATERIALS.values()){
            material.createBlocks();
        }
        for(var material : STONE_MATERIALS.values()){
            material.createBlocks();
        }
        for(var material : BRICK_MATERIALS.values()){
            material.createBlocks();
        }
        for(var material : TERRACOTTA_MATERIALS.values()){
            material.createBlocks();
        }
        for(var material : WOOD_MATERIALS.values()){
            material.createBlocks();
        }
        for(var material : WOOL_MATERIALS.values()){
            material.createBlocks();
        }
    }
}
