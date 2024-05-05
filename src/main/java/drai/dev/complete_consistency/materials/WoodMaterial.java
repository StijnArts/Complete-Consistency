package drai.dev.complete_consistency.materials;

import drai.dev.complete_consistency.*;
import drai.dev.complete_consistency.helpers.*;
import drai.dev.complete_consistency.items.boat.*;
import drai.dev.complete_consistency.mixin.accessors.*;
import drai.dev.complete_consistency.modules.*;
import drai.dev.complete_consistency.modules.minecraft.materials.wood.*;
import drai.dev.complete_consistency.registry.*;
import net.minecraft.core.registries.*;
import net.minecraft.resources.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.*;

import java.io.*;
import java.util.*;
import java.util.function.*;

public class WoodMaterial extends BlockMaterial {
    private final Supplier<ItemLike> previousWoodType;
    private static Map<MaterialModule, List<BlockFactory<WoodMaterial>>> factories = new HashMap<>();
    public boolean isFungal = false;
    private final WoodType woodType;
    private BoatTypeOverride boatType;
    private final BlockSetType blockSetType;
    private final List<Block> logBlocks = new ArrayList<>();
    private final List<Block> woodBlocks = new ArrayList<>();
    public File palette;

    public WoodMaterial(String namespace, String name, MapColor mapColor, Supplier<ItemLike> previousWoodType) {
        super(namespace, name, mapColor);
        palette = RelativeFileHelper.getAssetLocation("\\"+namespace+"\\palettes\\wood\\"+name+"_palette.png");
        this.previousWoodType = previousWoodType;
        blockSetType = BlockSetTypeAccessor.invokeRegister(BlockSetTypeAccessor.invokeConstructor(name));
        this.woodType = WoodTypeAccessor.invokeRegister(WoodTypeAccessor.invokeConstructor(name, blockSetType));
    }

    public WoodMaterial(String namespace, String name, MapColor mapColor, Supplier<ItemLike> previousWoodType, boolean isFungal) {
        this(namespace, name, mapColor, previousWoodType);
        this.isFungal = isFungal;
    }

    public static void addBlockType(MaterialModule module, String blockName, BlockConsumer<WoodMaterial> consumer) {
        factories.compute(module, ((materialModule, blockFactories) -> Objects.requireNonNullElseGet(blockFactories, ArrayList::new))).add(new BlockFactory<>(blockName, consumer));
    }

    @Override
    public void register(String namespace) {
        MaterialRegistry.registerMaterial(getResourceLocation(), this);
    }

    @Override
    public void createBlocks() {
        runFactories(factories, this);
    }

    public List<Block> getLogBlocks() {
        var logBlocks = new ArrayList<Block>();
        logBlocks.add(getBlock(MinecraftWoodBlocks.LOG.getName()));
        return logBlocks;
    }

    public List<Block> getWoodBlocks() {
        var logBlocks = new ArrayList<Block>();
        logBlocks.add(getBlock(MinecraftWoodBlocks.WOOD.getName()));
        return logBlocks;
    }


    public Supplier<ItemLike> getPreviousWoodBlock() {
        return previousWoodType;
    }

    public String getLogAffix() {
        return (isFungal ? " Stem" : " Log");
    }

    public String getWoodAffix() {
        return (isFungal ? " Hyphae" : " Wood");
    }
}
