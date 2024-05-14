package drai.dev.complete_consistency.modules.minecraft.generic;

import net.minecraft.core.*;
import net.minecraft.data.models.blockstates.*;
import net.minecraft.resources.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.*;

public class StairBlockStateGenerator {
    public static BlockStateGenerator createStairsBlockState(Block stairsBlock,
                                                             ResourceLocation innerModelId,
                                                             ResourceLocation regularModelId,
                                                             ResourceLocation outerModelId,
                                                             ResourceLocation innerHorizontal,
                                                             ResourceLocation straightHorizontal,
                                                             ResourceLocation outerHorizontal,
                                                             boolean uvLock) {
        return MultiVariantGenerator.multiVariant(stairsBlock).with(createStairsProperties(innerModelId, regularModelId, outerModelId, innerHorizontal, straightHorizontal, outerHorizontal, uvLock));

    }

    public static VariantProperties.Rotation getRotation(int rotation){
        int actualRotation = rotation % 360;
        return switch (actualRotation) {
            default -> VariantProperties.Rotation.R0;
            case 90 -> VariantProperties.Rotation.R90;
            case 180 -> VariantProperties.Rotation.R180;
            case 270 -> VariantProperties.Rotation.R270;
        };
    }

    private static PropertyDispatch createStairsProperties(ResourceLocation innerModelId,
                                                           ResourceLocation regularModelId,
                                                           ResourceLocation outerModelId,
                                                           ResourceLocation innerHorizontal,
                                                           ResourceLocation straightHorizontal,
                                                           ResourceLocation outerHorizontal,
                                                           boolean uvLock) {
        var propertyDispatch = PropertyDispatch.properties(BlockStateProperties.HORIZONTAL_FACING, BlockStateProperties.HALF, BlockStateProperties.STAIRS_SHAPE, BlockStateProperties.AXIS);
        for(var half : BlockStateProperties.HALF.getPossibleValues()){
            int halfXRotationModifier = 0;
            if(half == Half.TOP){
                halfXRotationModifier = 180;
            }
            for(var shape : BlockStateProperties.STAIRS_SHAPE.getPossibleValues()){
                for(var facing : BlockStateProperties.HORIZONTAL_FACING.getPossibleValues()){
                    for(var axis : BlockStateProperties.AXIS.getPossibleValues()){
                        ResourceLocation model;
                        var variant = Variant.variant();
                        
                        switch (shape) {
                            case STRAIGHT:
                                if(axis == Direction.Axis.Z) model = straightHorizontal;
                                else if(axis == Direction.Axis.X) model = straightHorizontal;
                                else model = regularModelId;
                                break;
                            case OUTER_RIGHT:
                                if(axis == Direction.Axis.Z) model = outerHorizontal;
                                else if(axis == Direction.Axis.X) model = outerHorizontal;
                                else model = outerModelId;
                            case OUTER_LEFT:
                                if(axis == Direction.Axis.Z) model = outerHorizontal;
                                else if(axis == Direction.Axis.X) model = outerHorizontal;
                                else model = outerModelId;
                                break;
                            case INNER_RIGHT:
                                if(axis == Direction.Axis.Z) model = innerHorizontal;
                                else if(axis == Direction.Axis.X) model = innerHorizontal;
                                else model = innerModelId;
                            case INNER_LEFT:
                                if(axis == Direction.Axis.Z) model = innerHorizontal;
                                else if(axis == Direction.Axis.X) model = innerHorizontal;
                                else model = innerModelId;
                                break;
                            default:
                                model = innerModelId;
                                break;
                        }
                        int yRotation = 0;
                        if (half == Half.BOTTOM) {
                            if(axis != Direction.Axis.Y) {
                                if (shape == StairsShape.INNER_RIGHT) {
                                    model = innerHorizontal;
                                } else if (shape == StairsShape.OUTER_RIGHT) model = outerHorizontal;
                            }
                            switch (facing) {
                                case EAST:
                                    if (shape == StairsShape.INNER_LEFT || shape == StairsShape.OUTER_LEFT){
                                        yRotation = 270;
                                    }
                                    break;
                                case WEST:
                                    if (shape == StairsShape.INNER_LEFT || shape == StairsShape.OUTER_LEFT){
                                        yRotation = 90;
                                    }  else {
                                        yRotation = 180;
                                    }
                                    break;
                                case SOUTH:
                                    if (shape != StairsShape.INNER_LEFT && shape != StairsShape.OUTER_LEFT){
                                        yRotation = 90;
                                    }
                                    break;
                                case NORTH:
                                    if (shape == StairsShape.INNER_LEFT || shape == StairsShape.OUTER_LEFT){
                                        yRotation = 180;
                                    } else {
                                        yRotation = 270;
                                    }
                                    break;
                                default:
                                    break;
                            }
                        } else if (half == Half.TOP) {
                            if(axis != Direction.Axis.Y){
                                if (shape == StairsShape.INNER_LEFT){
                                    model = innerHorizontal;
                                } else if(shape == StairsShape.OUTER_LEFT) model = outerHorizontal;
                            }

//                            yRotation = 180; // Default y-rotation for top half
                            switch (facing) {
                                case EAST:
                                    if (shape == StairsShape.INNER_RIGHT || shape == StairsShape.OUTER_RIGHT){
                                        yRotation += 90;
                                    }
                                    break;
                                case WEST:
                                    if (shape == StairsShape.INNER_RIGHT || shape == StairsShape.OUTER_RIGHT){
                                        yRotation += 270;
                                    } else yRotation += 180;
                                    break;
                                case SOUTH:
                                    if (shape == StairsShape.INNER_RIGHT || shape == StairsShape.OUTER_RIGHT){
                                        yRotation += 180;
                                    } else yRotation += 90;
                                    break;
                                case NORTH:
                                    if (shape != StairsShape.INNER_RIGHT && shape != StairsShape.OUTER_RIGHT){
                                        yRotation += 270;
                                    }
                                    break;
                                default:
                                    break;
                            }
                        }
                        variant.with(VariantProperties.MODEL, model).with(VariantProperties.UV_LOCK, uvLock);
                        var xRotation = getRotation(halfXRotationModifier);
                        if(xRotation != VariantProperties.Rotation.R0){
                            variant.with(VariantProperties.X_ROT, xRotation);
                        }
                        var yRotationEnum = getRotation(yRotation);
                        if(yRotationEnum != VariantProperties.Rotation.R0){
                            variant.with(VariantProperties.Y_ROT, yRotationEnum);
                        }
                        propertyDispatch.select(facing, half, shape, axis, variant);
                    }
                }
            }
        }
        return propertyDispatch;
    }

    public static BlockStateGenerator createRotatedPillarBlockStairs(Block block, ResourceLocation inner, ResourceLocation straight, ResourceLocation outer, ResourceLocation innerHorizontal, ResourceLocation straightHorizontal, ResourceLocation outerHorizontal) {
        return createStairsBlockState(block, inner, straight, outer, innerHorizontal, straightHorizontal, outerHorizontal, false);
    }
}
