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
    protected String name;
    protected String namespace;
    protected HashMap<String, Block> blocks = new HashMap<>();
    protected HashMap<String, Supplier<Block>> suppliedBlocks = new HashMap<>();
    protected HashMap<String, TagKey<Block>> blockTags = new HashMap<>();
    protected HashMap<String, Item> items = new HashMap<>();
    protected HashMap<String, TagKey<Item>> itemTags = new HashMap<>();
    private final MapColor mapColor;
    private final int materialIndex;


    public BlockMaterial(String namespace, String name, MapColor mapColor, int materialIndex) {
        this.name = name.toLowerCase();
        this.namespace = namespace;
        this.mapColor = mapColor;
        this.materialIndex = materialIndex;
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
            blockTags.add(this.getBlockTag(tag));
        }
        return blockTags;
    }

    public List<TagKey<Item>> getItemTags(List<String> tags) {
        List<TagKey<Item>> itemTags = new ArrayList<>();
        for(var tag : tags){
            itemTags.add(this.getItemTag(tag));
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
        if(blockTags.containsKey(name)) return blockTags.get(name);
        var blockTagKey = TagKeyHelper.createBlockTagKey(new ResourceLocation(namespace,this+"_"+name));
        addBlockTag(name, blockTagKey);
        return blockTagKey;
    }

    public TagKey<Item> getItemTag(String name) {
        if(itemTags.containsKey(name)) return itemTags.get(name);
        var blockTagKey = TagKeyHelper.createItemTagKey(new ResourceLocation(namespace,this+"_"+name));
        addItemTag(name, blockTagKey);
        return blockTagKey;
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
                var blockTagKey = TagKeyHelper.createBlockTagKey(new ResourceLocation(material.namespace,material+"_"+factory.blockName));
                var itemTagKey = TagKeyHelper.createItemTagKey(new ResourceLocation(material.namespace,material+"_"+factory.blockName));
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

    public Item getItem(String name) {
        return items.get(name);
    }

    public abstract int getIndex(String type);

    public int getMaterialIndex() {
        return this.materialIndex;
    }
}
