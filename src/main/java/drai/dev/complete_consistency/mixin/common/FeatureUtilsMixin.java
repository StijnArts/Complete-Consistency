package drai.dev.complete_consistency.mixin.common;

import drai.dev.complete_consistency.registry.*;
import net.minecraft.data.worldgen.*;
import net.minecraft.data.worldgen.features.*;
import net.minecraft.resources.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.levelgen.feature.*;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;


@Mixin(FeatureUtils.class)
public class FeatureUtilsMixin {

    /*@Inject(method = "register(Lnet/minecraft/data/worldgen/BootstrapContext;Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/world/level/levelgen/feature/Feature;Lnet/minecraft/world/level/levelgen/feature/configurations/FeatureConfiguration;)V", at = @At("HEAD"), cancellable = true)
    private static  <FC extends FeatureConfiguration, F extends Feature<FC>>  void reset(
            BootstrapContext<ConfiguredFeature<?, ?>> context,
            ResourceKey<ConfiguredFeature<?, ?>> key,
            F feature,
            FC config,
            CallbackInfo ci) {
//        if(key == TreeFeatures.HUGE_BROWN_MUSHROOM){
//            FeatureUtils.register(context, REPLACEMENT_HUGE_BROWN_MUSHROOM, Feature.HUGE_BROWN_MUSHROOM,
//                    new HugeMushroomFeatureConfiguration(
//                            BlockStateProvider.simple((BlockState)((BlockState) Blocks.BROWN_MUSHROOM_BLOCK.defaultBlockState().setValue(HugeMushroomBlock.UP, true)).setValue(HugeMushroomBlock.DOWN, false)),
//                            BlockStateProvider.simple((BlockState)((BlockState) BlockReplacements.MUSHROOM_STEM.defaultBlockState())), 3));
//            ci.cancel();
//        } else if(key == TreeFeatures.HUGE_RED_MUSHROOM){
//            FeatureUtils.register(context, REPLACEMENT_HUGE_RED_MUSHROOM, Feature.HUGE_RED_MUSHROOM,
//                    new HugeMushroomFeatureConfiguration(
//                            BlockStateProvider.simple((BlockState)Blocks.RED_MUSHROOM_BLOCK.defaultBlockState().setValue(HugeMushroomBlock.DOWN, false)),
//                            BlockStateProvider.simple((BlockState)((BlockState) BlockReplacements.MUSHROOM_STEM.defaultBlockState())), 2));
//            ci.cancel();
//        }
    }*/
}
