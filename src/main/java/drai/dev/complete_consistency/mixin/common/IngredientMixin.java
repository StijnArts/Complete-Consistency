package drai.dev.complete_consistency.mixin.common;

import drai.dev.complete_consistency.helpers.*;
import net.minecraft.core.registries.*;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(Ingredient.class)
public class IngredientMixin {

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z"), method =
            "test(Lnet/minecraft/world/item/ItemStack;)Z")
    private boolean overrideItemCheckIfReplaced(ItemStack instance, Item item){
        return RecipeReplacementsHelper.replaceItem(instance, item);
    }
}
