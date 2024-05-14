package drai.dev.complete_consistency.helpers;

import net.minecraft.core.registries.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;

import java.util.*;
import java.util.function.*;

public class RecipeReplacementsHelper {
    private static final Map<Supplier<Item>, TagKey<Item>> unBakedRecipeReplacements = new HashMap<>();
    private static final Map<Item, TagKey<Item>> recipeReplacements = new HashMap<>();
    public static Map<Item, TagKey<Item>> getReplacedItems() {
        return recipeReplacements;
    }

    public static void addReplacement(Supplier<Item> replacedItem, TagKey<Item> replacement){
        unBakedRecipeReplacements.put(replacedItem, replacement);
    }


    public static void bakeReplacements() {
        for(var set : unBakedRecipeReplacements.entrySet()){
            recipeReplacements.put(set.getKey().get(), set.getValue());
        }
    }

    public static boolean replaceItem(ItemStack instance, Item item) {
        var itemName = BuiltInRegistries.ITEM.getKey(item).getPath();
//        System.out.println("checking item is " + itemName);
        if(!itemName.equalsIgnoreCase("air")){
            if(getReplacedItems().containsKey(instance.getItem())){
//                System.out.println("checking itemstack "+instance.getItem()+" is replaced");
                var result = new ItemStack(item).is(getReplacedItems().get(instance.getItem()));
//                System.out.println("replaced check is met: "+result);
                return result;
            }
//            System.out.println("checking itemstack "+instance.getItem()+" wasn't replaced");
        }
        return instance.is(item);
    }
}
