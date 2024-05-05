package drai.dev.complete_consistency.materials;

import drai.dev.complete_consistency.*;
import drai.dev.complete_consistency.helpers.*;
import drai.dev.complete_consistency.modules.*;
import drai.dev.complete_consistency.modules.minecraft.materials.wood.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.material.*;

import java.util.*;
import java.util.function.*;

public abstract class BlockMaterial {
    String name;
    String namespace;
    HashMap<String, Block> blocks = new HashMap<>();
    HashMap<String, Supplier<Block>> suppliedBlocks = new HashMap<>();
    HashMap<String, TagKey<Block>> blockTags = new HashMap<>();
    HashMap<String, Item> items = new HashMap<>();
    HashMap<String, TagKey<Item>> itemTags = new HashMap<>();
    private MapColor mapColor;


    public BlockMaterial(String namespace, String name, MapColor mapColor) {
        this.name = name.toLowerCase();
        this.namespace = namespace;
        this.mapColor = mapColor;
    }

    public abstract void register(String namespace);
    public abstract void createBlocks();

    public ResourceLocation getResourceLocation(){
        return new ResourceLocation(namespace, name);
    }

    public String getName() {
        return name;
    }

    public List<TagKey<Block>> getBlockTags(List<String> tags) {
        List<TagKey<Block>> blockTags = new ArrayList<>();
        for(var tag : tags){
            blockTags.add(this.blockTags.get(tag));
        }
        return blockTags;
    }

    public List<TagKey<Item>> getItemTags(List<String> tags) {
        List<TagKey<Item>> itemTags = new ArrayList<>();
        for(var tag : tags){
            itemTags.add(this.itemTags.get(tag));
        }
        return itemTags;
    }

    public Block getBlock(String id) {
        var block = blocks.get(id);
        if(block == null){
            var supplier = suppliedBlocks.get(id);
            if(supplier!=null) block = supplier.get();
        }
        return block;
    }

    @Override
    public String toString() {
        return name;
    }

    public TagKey<Block> getBlockTag(String name) {
        return blockTags.get(name);
    }

    public TagKey<Item> getItemTag(String name) {
        return itemTags.get(name);
    }

    public String getNamespace() {
        return namespace;
    }

    public void addBlock(String name, Block block) {
        blocks.put(name, block);
    }

    public void addBlock(String name, Supplier<Block> block) {
        suppliedBlocks.put(name, block);
    }

    public void addItem(String name, Item item) {
        items.put(name, item);
    }

    public void addBlockTag(String name, TagKey<Block> blockTag) {
        blockTags.put(name, blockTag);
    }

    public void addItemTag(String name, TagKey<Item> blockTag) {
        itemTags.put(name, blockTag);
    }

    public boolean hasBlock(String name) {
        if(blocks.containsKey(name) || suppliedBlocks.containsKey(name)) return true;
        return items.containsKey(name);
    }

    public static <T extends BlockMaterial> void runFactories(Map<MaterialModule, List<BlockFactory<T>>> factories, T material){
        var sortedFactories = factories.entrySet().stream().sorted(Comparator.comparing(keys->keys.getKey().index)).map(Map.Entry::getValue).toList();
        for(List<BlockFactory<T>> factoriesList : sortedFactories){
            for(BlockFactory<T> factory : factoriesList){
                var blockTagKey = TagKeyHelper.createBlockTagKey(new ResourceLocation(CompleteConsistency.MOD_ID,material+"_"+factory.blockName));
                var itemTagKey = TagKeyHelper.createItemTagKey(new ResourceLocation(CompleteConsistency.MOD_ID,material+"_"+factory.blockName));
                material.addBlockTag(factory.blockName, blockTagKey);
                material.addItemTag(factory.blockName, itemTagKey);
            }
        }

        for(List<BlockFactory<T>> factoriesList : sortedFactories){
            for(BlockFactory<T> factory : factoriesList){
                factory.consumer.accept(material);
            }
        }

        for (Block block : material.blocks.values()) {
            for (BlockState blockState2 : block.getStateDefinition().getPossibleStates()) {
                Block.BLOCK_STATE_REGISTRY.add(blockState2);
                blockState2.initCache();
            }
            block.getLootTable();
        }
    }

    public ItemLike getPreviousBlock(String blockType) {
        var blockTypeEnum = MinecraftWoodBlocks.valueOf(blockType.toUpperCase());
        var enumValues = Arrays.stream(MinecraftWoodBlocks.values()).toList();
        var index = enumValues.indexOf(blockTypeEnum);
        return getBlock(enumValues.get(index-1).getName());
    }

    public ItemLike getNextBlock(String blockType) {
        var blockTypeEnum = MinecraftWoodBlocks.valueOf(blockType.toUpperCase());
        var enumValues = Arrays.stream(MinecraftWoodBlocks.values()).toList();
        var index = enumValues.indexOf(blockTypeEnum);
        return getBlock(enumValues.get(index+1).getName());
    }

    public MapColor getColor() {
        return this.mapColor;
    }
}
