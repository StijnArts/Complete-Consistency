package drai.dev.complete_consistency.materials.impl;

import drai.dev.complete_consistency.*;
import drai.dev.complete_consistency.helpers.*;
import drai.dev.complete_consistency.materials.*;
import drai.dev.complete_consistency.modules.*;
import drai.dev.complete_consistency.registry.*;
import net.minecraft.resources.*;
import net.minecraft.world.level.material.*;

import java.util.*;

public class ResourceBlockMaterial extends BlockMaterial {


    public ResourceBlockMaterial(String namespace, String name, MapColor mapColor, int index) {
        super(namespace, name, mapColor, index);
    }

    @Override
    public void register(String namespace) {
        MaterialRegistry.registerMaterial(new ResourceLocation(namespace, name), this);
    }

    private static Map<MaterialModule, List<BlockFactory<ResourceBlockMaterial>>> factories = new HashMap<>();
    public static void addBlockType(MaterialModule module, String blockName, BlockConsumer<ResourceBlockMaterial> consumer) {
        factories.get(module).add(new BlockFactory<>(blockName, consumer));
    }

    @Override
    public void createBlocks() {
        runFactories(factories, this);
    }

    @Override
    public int getIndex(String type) {
        return 0;
    }
}
