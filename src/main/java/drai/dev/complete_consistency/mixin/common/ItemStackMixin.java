package drai.dev.complete_consistency.mixin.common;

import drai.dev.complete_consistency.helpers.*;
import net.fabricmc.fabric.api.item.v1.*;
import net.minecraft.core.component.*;
import net.minecraft.network.chat.*;
import net.minecraft.world.item.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin implements DataComponentHolder,
        FabricItemStack {
    @Inject(method = "getHoverName", at = @At("TAIL"), cancellable = true)
    private void customCardName(CallbackInfoReturnable<Component> cir){
        var self = getSelf();
        var replacementTranslations = LanguageHelper.getEnglishTranslations().getReplacementTranslations();
        if(replacementTranslations.containsKey(self.getItem())){
            Component component = this.get(DataComponents.CUSTOM_NAME);
            if (component != null) {
                cir.setReturnValue(component);
                return;
            }
            Component component2 = this.get(DataComponents.ITEM_NAME);
            if (component2 != null) {
                cir.setReturnValue(component2);
                return;
            }
            cir.setReturnValue(Component.literal(replacementTranslations.get(self.getItem())));
            return;
        }

    }

    private ItemStack getSelf(){
        return (ItemStack) (Object) this;
    }
}
