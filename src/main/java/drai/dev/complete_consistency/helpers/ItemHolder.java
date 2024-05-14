package drai.dev.complete_consistency.helpers;

import drai.dev.complete_consistency.materials.*;
import net.minecraft.world.level.*;
import org.jetbrains.annotations.*;

import java.util.*;
import java.util.function.*;

public class ItemHolder {
    private final BlockMaterial material;
    @Nullable
    private Supplier<ItemLike> previousItem;
    private final String type;
    private boolean isItem = false;

    public ItemHolder(BlockMaterial material, String type, boolean isItem) {
        this(material, type);
        this.isItem = isItem;
    }

    public ItemHolder(BlockMaterial material, String type, boolean isItem, @Nullable Supplier<ItemLike> previousItem) {
        this(material, type);
        this.isItem = isItem;
        this.previousItem = previousItem;
    }

    public ItemHolder(BlockMaterial material, String type) {
        this.material = material;
        this.type = type;
    }

    public ItemLike getHeldItem(){
        if(isItem) return material.getItem(type);
        return material.getBlock(type);
    }

    public int getIndex(){
        return material.getIndex(type);
    }

    public ItemLike getPreviousItem(){
        if(previousItem!=null) return previousItem.get();
        return material.getPreviousBlock(type);
    }

    public int getMaterialIndex() {
        return material.getMaterialIndex();
    }

    public static Comparator<ItemHolder> comparator(){
        return ((o1, o2) -> {
            var comparison = Integer.compare(o1.getIndex(), o2.getIndex());
            if(comparison == 0){
                return Integer.compare(o2.getMaterialIndex(), o1.getMaterialIndex());
            } else return comparison;
        });
    }
}
