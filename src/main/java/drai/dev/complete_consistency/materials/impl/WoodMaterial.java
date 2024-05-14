package drai.dev.complete_consistency.materials.impl;

import drai.dev.complete_consistency.*;
import drai.dev.complete_consistency.helpers.*;
import drai.dev.complete_consistency.items.boat.*;
import drai.dev.complete_consistency.materials.*;
import drai.dev.complete_consistency.mixin.accessors.*;
import drai.dev.complete_consistency.modules.*;
import drai.dev.complete_consistency.modules.minecraft.materials.wood.*;
import drai.dev.complete_consistency.registry.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.*;
import org.jetbrains.annotations.*;

import java.io.*;
import java.util.*;
import java.util.function.*;

public class WoodMaterial extends BlockMaterial {
    private final Supplier<ItemLike> previousWoodType;
    private static Map<MaterialModule, List<BlockFactory<WoodMaterial>>> factories = new HashMap<>();
    public boolean isFungal = false;
    private WoodType woodType;
    private BoatTypeOverride boatType;
    private final BlockSetType blockSetType;
    private final List<Block> logBlocks = new ArrayList<>();
    private final List<Block> woodBlocks = new ArrayList<>();
    public File palette;

    public WoodMaterial(String namespace, String name, MapColor mapColor, Supplier<ItemLike> previousWoodType, int index) {
        super(namespace, name, mapColor, index);
        palette = RelativeFileHelper.getAssetLocation("\\"+namespace+"\\palettes\\wood\\"+name+"_palette.png");
        this.previousWoodType = previousWoodType;
        blockSetType = getBlockSetType(name);
        woodType = getWoodType(name);
    }

    private @NotNull WoodType getWoodType(String name) {
        this.woodType = WoodTypeAccessor.invokeRegister(WoodTypeAccessor.invokeConstructor(name, blockSetType));
        return this.woodType;
    }

    public BoatTypeOverride getBoatType() {
        return boatType;
    }

    private @NotNull BlockSetType getBlockSetType(String name) {
        final BlockSetType blockSetType;
        blockSetType = BlockSetTypeAccessor.invokeRegister(BlockSetTypeAccessor.invokeConstructor(name));
        return blockSetType;
    }

    public WoodMaterial(String namespace, String name, MapColor mapColor, Supplier<ItemLike> previousWoodType, boolean isFungal, int index) {
        this(namespace, name, mapColor, previousWoodType, index);
        this.isFungal = isFungal;
    }

    public static void addFactoryType(MaterialModule module, String blockName, BlockConsumer<WoodMaterial> consumer) {
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

    @Override
    public int getIndex(String type) {
        return MinecraftWoodBlocks.indexOf(MinecraftWoodBlocks.valueOf(type.toUpperCase()));
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

    public BlockSetType getBlockSetType() {
        return this.blockSetType;
    }

    public WoodType getWoodType() {
        return this.woodType;
    }

    public BoatTypeOverride createBoatType() {
        this.boatType = BoatTypeOverride.create(namespace, name, getBlock(MinecraftWoodBlocks.PLANKS.getName()));
        return this.boatType;
    }
}
