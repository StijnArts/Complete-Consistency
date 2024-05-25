//package drai.dev.complete_consistency.mixin.common;
//
//import drai.dev.complete_consistency.interfaces.*;
//import drai.dev.complete_consistency.items.boat.*;
//import net.minecraft.core.*;
//import net.minecraft.nbt.*;
//import net.minecraft.network.syncher.*;
//import net.minecraft.world.entity.*;
//import net.minecraft.world.entity.decoration.*;
//import net.minecraft.world.entity.vehicle.*;
//import net.minecraft.world.item.*;
//import net.minecraft.world.level.*;
//import net.minecraft.world.level.block.state.*;
//import org.spongepowered.asm.mixin.*;
//import org.spongepowered.asm.mixin.injection.*;
//import org.spongepowered.asm.mixin.injection.callback.*;
//
//@Mixin(ItemFrame.class)
//public abstract class ItemFrameMixin  {
//
//    @Inject(method = "setVariant(Lnet/minecraft/world/entity/vehicle/Boat$Type;)V", at = @At("HEAD"), cancellable = true)
//    void bcl_setType(Boat.Type type, CallbackInfo ci) {
//        if (bcl_type != null) {
//            this.entityData.set(DATA_ID_TYPE, bcl_type.ordinal());
//            ci.cancel();
//        }
//    }
//
//    @Inject(method = "getVariant()Lnet/minecraft/world/entity/vehicle/Boat$Type;", at = @At("HEAD"), cancellable = true)
//    void bcl_getBoatType(CallbackInfoReturnable<Boat.Type> cir) {
//        BoatTypeOverride type = BoatTypeOverride.byId(this.entityData.get(DATA_ID_TYPE));
//        if (type != null) {
//            bcl_type = type;
//            cir.setReturnValue(Boat.Type.OAK);
//        }
//    }
//
//
//    @Inject(method = "addAdditionalSaveData", at = @At("HEAD"))
//    void bcl_addAdditionalSaveData(CompoundTag compoundTag, CallbackInfo ci) {
//        BoatTypeOverride type = this.bcl_getCustomType();
//        if (type != null) {
//            compoundTag.putString("cType", type.name());
//        }
//    }
//
//    @Inject(method = "readAdditionalSaveData", at = @At("HEAD"))
//    void bcl_readAdditionalSaveData(CompoundTag compoundTag, CallbackInfo ci) {
//        if (compoundTag.contains("cType")) {
//            this.bcl_setCustomType(BoatTypeOverride.byName(compoundTag.getString("cType")));
//        } else {
//            this.bcl_setCustomType(null);
//        }
//    }
//
//    @Inject(method = "getDropItem", at = @At("HEAD"), cancellable = true)
//    void bcl_getDropItem(CallbackInfoReturnable<Item> cir) {
//        BoatTypeOverride type = this.bcl_getCustomType();
//        if (type != null) {
//            BoatItem boat = type.getBoatItem();
//            if (boat != null) {
//                cir.setReturnValue(boat);
//            }
//        }
//    }
//
//    @Inject(method = "checkFallDamage", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/vehicle/Boat;kill()V", shift = At.Shift.AFTER), cancellable = true)
//    void bcl_checkFallDamage(double d, boolean bl, BlockState blockState, BlockPos blockPos, CallbackInfo ci) {
//        BoatTypeOverride type = this.bcl_getCustomType();
//        if (type != null) {
//            if (this.level().getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
//                for (int i = 0; i < 3; ++i) {
//                    this.spawnAtLocation(type.getPlanks());
//                }
//                for (int i = 0; i < 2; ++i) {
//                    this.spawnAtLocation(Items.STICK);
//                }
//
//                this.resetFallDistance();
//                ci.cancel();
//            }
//        }
//    }
//}
