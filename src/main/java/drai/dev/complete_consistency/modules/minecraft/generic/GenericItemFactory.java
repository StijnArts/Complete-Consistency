package drai.dev.complete_consistency.modules.minecraft.generic;

import drai.dev.complete_consistency.helpers.*;
import drai.dev.complete_consistency.helpers.languages.*;
import drai.dev.complete_consistency.materials.*;
import net.minecraft.core.registries.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;

import java.util.*;
import java.util.function.*;

public class GenericItemFactory {
    public static <T extends Item> Item createItem(BlockMaterial material, String itemType, String itemId,
                                                   BiFunction<String, String, T> supplier,
                                                   List<TagKey<Item>> itemTags){
        String blockLangFileName = "";
        for (String segment : itemId.split("_")) {
            blockLangFileName = blockLangFileName +" "+ StringUtil.capitalizeWord(segment);
        }
        if(!material.hasBlock(itemType)) {
            Item returnItem = supplier.apply(itemId, blockLangFileName.trim());
            System.out.println("Created an item through the generic item factory");
            material.addItem(itemType, returnItem);
            return returnItem;
        } else {
            System.out.println("item existed already");
            var item = material.getItem(itemType);
            var foundItemId = BuiltInRegistries.ITEM.getKey(item).getPath();
            if(!foundItemId.contains(material.getName())) {
                itemId = material.getName() + "_" + foundItemId;
                blockLangFileName = "";
                for (String segment : itemId.split("_")) {
                    blockLangFileName = blockLangFileName + " " + StringUtil.capitalizeWord(segment);
                }
                LanguageHelper.getEnglishTranslations().addReplacementTranslations(item, blockLangFileName.trim());
                System.out.println("Renamed "+foundItemId + " to " + blockLangFileName.trim());
            }
            TagHelper.addItemTags(item, itemTags);
            return null;
        }
    }
}
