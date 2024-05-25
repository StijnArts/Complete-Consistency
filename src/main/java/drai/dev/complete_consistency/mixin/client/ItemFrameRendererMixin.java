package drai.dev.complete_consistency.mixin.client;

import drai.dev.complete_consistency.entities.*;
import drai.dev.complete_consistency.entities.interfaces.*;
import drai.dev.complete_consistency.items.boat.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.resources.model.*;
import net.minecraft.core.registries.*;
import net.minecraft.world.entity.decoration.*;
import net.minecraft.world.entity.vehicle.*;
import net.minecraft.world.item.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(ItemFrameRenderer.class)
public abstract class ItemFrameRendererMixin extends EntityRenderer<ItemFrame> {

    protected ItemFrameRendererMixin(EntityRendererProvider.Context context) {
        super(context);
    }

    @Inject(method = "getFrameModelResourceLoc", at = @At("HEAD"), cancellable = true)
    private void bcl_init(ItemFrame entity, ItemStack item, CallbackInfoReturnable<ModelResourceLocation> cir) {
        System.out.println(BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType()));
        if(entity instanceof CustomItemFrameEntity customItemFrameEntity) {
            System.out.println("made it into the instance of check");
            var modelLocation = getModelResourceLocation(customItemFrameEntity, item);
            if(modelLocation!=null){
                cir.setReturnValue(modelLocation);
                cir.cancel();
                return;
            }
        }
    }

    @Unique
    private ModelResourceLocation getModelResourceLocation(CustomItemFrameEntity customItemFrameEntity, ItemStack item) {
        if (item.is(Items.FILLED_MAP)) {
            return customItemFrameEntity.getMapFrameLocation();
        }
        return customItemFrameEntity.getFrameLocation();
    }
}
