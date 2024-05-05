package drai.dev.complete_consistency.materials;

import drai.dev.complete_consistency.*;
import drai.dev.complete_consistency.helpers.*;
import drai.dev.complete_consistency.modules.*;
import drai.dev.complete_consistency.registry.*;
import net.minecraft.resources.*;
import net.minecraft.world.level.material.*;

import java.util.*;

public class WoolMaterial extends BlockMaterial{


    public WoolMaterial(String namespace, String name, MapColor mapColor) {
        super(namespace, name, mapColor);
    }

    @Override
    public void register(String namespace) {
        MaterialRegistry.registerMaterial(new ResourceLocation(namespace, name), this);
    }

    private static Map<MaterialModule, List<BlockFactory<WoolMaterial>>> factories = new HashMap<>();
    public static void addBlockType(MaterialModule module, String blockName, BlockConsumer<WoolMaterial> consumer) {
        factories.get(module).add(new BlockFactory<>(blockName, consumer));
    }

    @Override
    public void createBlocks() {
        runFactories(factories, this);
    }
}
