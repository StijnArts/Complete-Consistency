package drai.dev.complete_consistency.modules.minecraft.generic;

import net.minecraft.core.*;
import net.minecraft.data.models.blockstates.*;
import net.minecraft.resources.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.*;

public class StairBlockStateGenerator {
    public static BlockStateGenerator createStairsBlockState(Block stairsBlock, ResourceLocation innerModelId, ResourceLocation regularModelId, ResourceLocation outerModelId, ResourceLocation innerHorizontal, ResourceLocation straightHorizontal, ResourceLocation outerHorizontal, boolean uvLock) {
//        return PropertyDispatch.property(BlockStateProperties.AXIS)
//                .select(Direction.Axis.Y, Variant.variant())
//                .select(Direction.Axis.Z, Variant.variant()
//                      .with(VariantProperties.X_ROT, VariantProperties.Rotation.R90))
//                .select(Direction.Axis.X, Variant.variant()
//                      .with(VariantProperties.X_ROT, VariantProperties.Rotation.R90)
//                      .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90));
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
//                    ResourceLocation model;
//                    int shapeYRotationMod = 0;
//                    int facingYRotationMod = 0;
//                    if(facing == Direction.NORTH){
//                        facingYRotationMod = 270;
//                    } else if (facing == Direction.SOUTH){
//                        facingYRotationMod = 90;
//                    } else if (facing == Direction.WEST){
//                        facingYRotationMod = 180;
//                    }
                    for(var axis : BlockStateProperties.AXIS.getPossibleValues()){
                        //TODO properly read y rot values per shape
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
                                    // For EAST direction, no rotation and uvLock for STRAIGHT shape
//                .select(Direction.EAST, Half.BOTTOM, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, innerModelId))
//                .select(Direction.EAST, Half.BOTTOM, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outerModelId))
//                .select(Direction.EAST, Half.BOTTOM, StairsShape.STRAIGHT, Variant.variant()
//                        .with(VariantProperties.MODEL, regularModelId))
//                .select(Direction.EAST, Half.BOTTOM, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outerModelId)
//                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
//                        .with(VariantProperties.UV_LOCK, uvLock))
//                .select(Direction.EAST, Half.BOTTOM, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerModelId)
//                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
//                        .with(VariantProperties.UV_LOCK, uvLock))
                                    if (shape == StairsShape.INNER_LEFT || shape == StairsShape.OUTER_LEFT){
                                        yRotation = 270;
                                    }
                                    break;
                                case WEST:
//                .select(Direction.WEST, Half.BOTTOM, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regularModelId)
//                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)
//                        .with(VariantProperties.UV_LOCK, uvLock))
//                .select(Direction.WEST, Half.BOTTOM, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outerModelId)
//                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)
//                        .with(VariantProperties.UV_LOCK, uvLock))
//                .select(Direction.WEST, Half.BOTTOM, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outerModelId)
//                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
//                        .with(VariantProperties.UV_LOCK, uvLock))
//                .select(Direction.WEST, Half.BOTTOM, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, innerModelId)
//                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)
//                        .with(VariantProperties.UV_LOCK, uvLock))
//                .select(Direction.WEST, Half.BOTTOM, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerModelId)
//                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
//                        .with(VariantProperties.UV_LOCK, uvLock))
                                    if (shape == StairsShape.INNER_LEFT || shape == StairsShape.OUTER_LEFT){
                                        yRotation = 90;
                                    }  else {
                                        yRotation = 180;
                                    }
                                    break;
                                case SOUTH:
//                .select(Direction.SOUTH, Half.BOTTOM, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regularModelId)
//                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
//                        .with(VariantProperties.UV_LOCK, uvLock))
//                .select(Direction.SOUTH, Half.BOTTOM, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outerModelId)
//                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
//                        .with(VariantProperties.UV_LOCK, uvLock))
//                .select(Direction.SOUTH, Half.BOTTOM, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, innerModelId)
//                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
//                        .with(VariantProperties.UV_LOCK, uvLock))
//                .select(Direction.SOUTH, Half.BOTTOM, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerModelId))
//                .select(Direction.SOUTH, Half.BOTTOM, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outerModelId))
                                    if (shape != StairsShape.INNER_LEFT && shape != StairsShape.OUTER_LEFT){
                                        yRotation = 90;
                                    }
                                    break;
                                case NORTH:
//                .select(Direction.NORTH, Half.BOTTOM, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regularModelId)
//                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
//                        .with(VariantProperties.UV_LOCK, uvLock))
//                .select(Direction.NORTH, Half.BOTTOM, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outerModelId)
//                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
//                        .with(VariantProperties.UV_LOCK, uvLock))
//                .select(Direction.NORTH, Half.BOTTOM, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outerModelId)
//                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)
//                        .with(VariantProperties.UV_LOCK, uvLock))
//                .select(Direction.NORTH, Half.BOTTOM, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, innerModelId)
//                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
//                        .with(VariantProperties.UV_LOCK, uvLock))
//                .select(Direction.NORTH, Half.BOTTOM, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerModelId)
//                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)
//                        .with(VariantProperties.UV_LOCK, uvLock))
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
//                                    .select(Direction.EAST, Half.TOP, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outerModelId)
//                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
//                        .with(VariantProperties.UV_LOCK, uvLock))
//                .select(Direction.EAST, Half.TOP, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outerModelId)
//                        .with(VariantProperties.UV_LOCK, uvLock))
//                .select(Direction.EAST, Half.TOP, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, innerModelId)
//                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
//                        .with(VariantProperties.UV_LOCK, uvLock))
//                .select(Direction.EAST, Half.TOP, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerModelId)
//                        .with(VariantProperties.UV_LOCK, uvLock))
                                    if (shape == StairsShape.INNER_RIGHT || shape == StairsShape.OUTER_RIGHT){
                                        yRotation += 90;
                                    }
                                    break;
                                case WEST:
//                .select(Direction.WEST, Half.TOP, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outerModelId)
//                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
//                        .with(VariantProperties.UV_LOCK, uvLock))
//                .select(Direction.WEST, Half.TOP, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outerModelId)
//                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)
//                        .with(VariantProperties.UV_LOCK, uvLock))
//                .select(Direction.WEST, Half.TOP, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, innerModelId)
//                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
//                        .with(VariantProperties.UV_LOCK, uvLock))
//                .select(Direction.WEST, Half.TOP, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerModelId)
//                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)
//                        .with(VariantProperties.UV_LOCK, uvLock))
                                    if (shape == StairsShape.INNER_RIGHT || shape == StairsShape.OUTER_RIGHT){
                                        yRotation += 270;
                                    } else yRotation += 180;
                                    break;
                                case SOUTH:
//                .select(Direction.SOUTH, Half.TOP, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outerModelId)
//                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)
//                        .with(VariantProperties.UV_LOCK, uvLock))
//                .select(Direction.SOUTH, Half.TOP, StairsShape.OUTER_LEFT, Variant.variant()
//                        .with(VariantProperties.MODEL, outerModelId)
//                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, uvLock))
//                .select(Direction.SOUTH, Half.TOP, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, innerModelId)
//                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)
//                        .with(VariantProperties.UV_LOCK, uvLock))
//                .select(Direction.SOUTH, Half.TOP, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerModelId)
//                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
//                        .with(VariantProperties.UV_LOCK, uvLock))
                                    if (shape == StairsShape.INNER_RIGHT || shape == StairsShape.OUTER_RIGHT){
                                        yRotation += 180;
                                    } else yRotation += 90;
                                    break;
                                case NORTH:
//                .select(Direction.NORTH, Half.TOP, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regularModelId)
//                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
//                        .with(VariantProperties.UV_LOCK, uvLock))
//                .select(Direction.NORTH, Half.TOP, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outerModelId)
//                        .with(VariantProperties.UV_LOCK, uvLock))
//                .select(Direction.NORTH, Half.TOP, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outerModelId)
//                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
//                        .with(VariantProperties.UV_LOCK, uvLock))
//                .select(Direction.NORTH, Half.TOP, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, innerModelId)
//                        .with(VariantProperties.UV_LOCK, uvLock))
//                .select(Direction.NORTH, Half.TOP, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerModelId)
//                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
//                        .with(VariantProperties.UV_LOCK, uvLock));
                                    if (shape != StairsShape.INNER_RIGHT && shape != StairsShape.OUTER_RIGHT){
                                        yRotation += 270;
                                    }
                                    break;
                                default:
                                    break;
                            }
                        }
                        
//                        if(shape == StairsShape.INNER_LEFT || shape == StairsShape.INNER_RIGHT){
//                            if(axis == Direction.Axis.X || axis == Direction.Axis.Z) {
//                                model = innerHorizontalZ;
////                                if(half == Half.BOTTOM && shape == StairsShape.INNER_RIGHT) {
////                                    facingXRotationModifier += 90;
////                                }
////                                if(half == Half.TOP){
////                                    if(facing == Direction.NORTH) {
////                                        facingXRotationModifier += 90 + 180;
////                                    } else if(facing == Direction.SOUTH) {
////                                        facingXRotationModifier -= 90;
////                                    }
////                                }
////                            } else if (axis == Direction.Axis.Z) {
////                                model = innerHorizontalZ;
////                                if(half == Half.TOP && shape == StairsShape.INNER_RIGHT) {
////                                    facingXRotationModifier += 90;
////                                }
////                                if(half == Half.TOP){
////                                    if(facing == Direction.EAST) {
////                                        facingXRotationModifier += 90 + 180;
////                                    } else if(facing == Direction.WEST) {
////                                        facingXRotationModifier -= 90;
////                                    }
////                                }
//                            } else {
//                                model = innerModelId;
//                            }
////                            shapeYRotationMod = 270;
////
////                            if(shape == StairsShape.INNER_LEFT){
////                                shapeYRotationMod = 90;
////                                facingXRotationModifier += 90;
////                            }
////                            if(half == Half.TOP) {
////                                if (facing == Direction.WEST || facing == Direction.EAST) {
////                                    facingXRotationModifier += 90;
////                                } else if (facing == Direction.NORTH || facing == Direction.SOUTH) {
////                                    facingXRotationModifier -= 90;
////                                }
////                            }
//                        } else if (shape == StairsShape.OUTER_LEFT || shape == StairsShape.OUTER_RIGHT){
//                            if(axis == Direction.Axis.X || axis == Direction.Axis.Z) {
//                                model = outerHorizontalZ;
//                            } else {
//                                model = outerModelId;
//                            }
//                        }  else {
//                            if(axis == Direction.Axis.X) {
//                                model = straightHorizontal;
//                            } else if (axis == Direction.Axis.Z) {
//                                model = straightHorizontal;
//                            } else {
//                                model = regularModelId;
//                            }
//                        }
                        variant.with(VariantProperties.MODEL, model).with(VariantProperties.UV_LOCK, uvLock);

//                        int axisXRotationMod = (axis == Direction.Axis.X || axis == Direction.Axis.Z ? 270 : 0) - (half == Half.BOTTOM ? 0 : 90);
//                        int axisYRotationMod = (axis == Direction.Axis.Z ? 90 : 0);
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
//        propertyDispatch


                return propertyDispatch;
    }

    public static BlockStateGenerator createRotatedPillarBlockStairs(Block block, ResourceLocation inner, ResourceLocation straight, ResourceLocation outer, ResourceLocation innerHorizontal, ResourceLocation straightHorizontal, ResourceLocation outerHorizontal) {
        return createStairsBlockState(block, inner, straight, outer, innerHorizontal, straightHorizontal, outerHorizontal, false);
    }
}
