package drai.dev.complete_consistency.modules.minecraft.generic;

import drai.dev.complete_consistency.helpers.*;
import drai.dev.complete_consistency.helpers.languages.*;
import drai.dev.complete_consistency.materials.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;

import java.util.*;
import java.util.function.*;

public class GenericItemFactory {
    public static <T extends Item> Item createItem(BlockMaterial material, String blockType, String blockId,
                                                   BiFunction<String, String, T> supplier,
                                                   List<TagKey<Item>> itemTags){
        String blockLangFileName = "";
        for (String segment : blockId.split("_")) {
            blockLangFileName = blockLangFileName +" "+ StringUtil.capitalizeWord(segment);
        }
        if(!material.hasBlock(blockType)) {
            Item returnItem = supplier.apply(blockId, blockLangFileName.trim());
            System.out.println("Created an item through the generic item factory");
            material.addItem(blockType, returnItem);
            return returnItem;
        } else {
            System.out.println("item existed already");
            var block = material.getItem(blockType);
            TagHelper.addItemTags(block, itemTags);
            return null;
        }
    }
}
