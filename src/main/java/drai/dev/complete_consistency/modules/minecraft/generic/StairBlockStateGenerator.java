package drai.dev.complete_consistency.modules.minecraft.generic;

import net.minecraft.core.*;
import net.minecraft.data.models.blockstates.*;
import net.minecraft.resources.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.*;

public class StairBlockStateGenerator {
    public static BlockStateGenerator createStairsBlockState(Block stairsBlock, ResourceLocation innerModelId, ResourceLocation regularModelId, ResourceLocation outerModelId, ResourceLocation innerHorizontal, ResourceLocation straightHorizontal, ResourceLocation outerHorizontal, ResourceLocation innerHorizontalZ, ResourceLocation straightHorizontalZ, ResourceLocation outerHorizontalZ, boolean uvLock) {
//        return PropertyDispatch.property(BlockStateProperties.AXIS)
//                .select(Direction.Axis.Y, Variant.variant())
//                .select(Direction.Axis.Z, Variant.variant()
//                      .with(VariantProperties.X_ROT, VariantProperties.Rotation.R90))
//                .select(Direction.Axis.X, Variant.variant()
//                      .with(VariantProperties.X_ROT, VariantProperties.Rotation.R90)
//                      .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90));
        return MultiVariantGenerator.multiVariant(stairsBlock).with(createStairsProperties(innerModelId, regularModelId, outerModelId, innerHorizontal, straightHorizontal, outerHorizontal, innerHorizontalZ, straightHorizontalZ, outerHorizontalZ, uvLock));

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
                                                           ResourceLocation innerHorizontalZ,
                                                           ResourceLocation straightHorizontalZ,
                                                           ResourceLocation outerHorizontalZ,
                                                           boolean uvLock) {
        var propertyDispatch = PropertyDispatch.properties(BlockStateProperties.HORIZONTAL_FACING, BlockStateProperties.HALF, BlockStateProperties.STAIRS_SHAPE, BlockStateProperties.AXIS);
        for(var half : BlockStateProperties.HALF.getPossibleValues()){
            int halfXRotationModifier = 0;
            if(half == Half.TOP){
                halfXRotationModifier = 180;
            }
            for(var shape : BlockStateProperties.STAIRS_SHAPE.getPossibleValues()){
                for(var facing : BlockStateProperties.HORIZONTAL_FACING.getPossibleValues()){
                    ResourceLocation model;
                    int shapeYRotationMod = 0;
                    int facingYRotationMod = 0;
                    if(facing == Direction.NORTH){
                        facingYRotationMod = 270;
                    } else if (facing == Direction.SOUTH){
                        facingYRotationMod = 90;
                    } else if (facing == Direction.WEST){
                        facingYRotationMod = 180;
                    }
                    int facingXRotationModifier = 0;
                    for(var axis : BlockStateProperties.AXIS.getPossibleValues()){
                        var variant = Variant.variant();
                        if(shape == StairsShape.INNER_LEFT || shape == StairsShape.INNER_RIGHT){
                            if(axis == Direction.Axis.X) {
                                model = innerHorizontalZ;
//                                if(half == Half.TOP){
//                                    if(facing == Direction.NORTH) {
//                                        facingXRotationModifier += 90 + 180;
//                                    } else if(facing == Direction.SOUTH) {
//                                        facingXRotationModifier -= 90;
//                                    }
//                                }
                            } else if (axis == Direction.Axis.Z) {
                                model = innerHorizontalZ;
//                                if(half == Half.TOP){
//                                    if(facing == Direction.EAST) {
//                                        facingXRotationModifier += 90 + 180;
//                                    } else if(facing == Direction.WEST) {
//                                        facingXRotationModifier -= 90;
//                                    }
//                                }
                            } else {
                                model = innerModelId;
                            }
                            shapeYRotationMod = 270;

                            if(shape == StairsShape.INNER_RIGHT){
                                shapeYRotationMod = 0;
                            }
                            if(half == Half.TOP) {
                                if (facing == Direction.WEST || facing == Direction.EAST) {
                                    facingXRotationModifier += 90;
                                } else if (facing == Direction.NORTH || facing == Direction.SOUTH) {
                                    facingXRotationModifier -= 90;
                                }
                            }
                        } else if (shape == StairsShape.OUTER_LEFT){
                            shapeYRotationMod = 270;
                            if(axis == Direction.Axis.X) {
                                model = outerHorizontalZ;
//                                if(half == Half.TOP){
//                                    if(facing == Direction.NORTH) {
//                                        halfXRotationModifier += 180;
//                                    } else if(facing == Direction.SOUTH) {
//                                        halfXRotationModifier -= 180;
//                                    }
//                                }
                            } else if (axis == Direction.Axis.Z) {
                                model = outerHorizontalZ;
//                                if(half == Half.TOP){
//                                    if(facing == Direction.EAST) {
//                                        halfXRotationModifier += 90;
//                                    } else if(facing == Direction.WEST) {
//                                        halfXRotationModifier -= 90;
//                                    }
//                                }
                            } else {
                                model = outerModelId;
                            }
                        } else if (shape == StairsShape.OUTER_RIGHT){
                            if(axis == Direction.Axis.X) {
                                model = outerHorizontalZ;
                            } else if (axis == Direction.Axis.Z) {
                                model = outerHorizontalZ;
                            } else {
                                model = outerModelId;
                            }
                        } else {
                            if(axis == Direction.Axis.X) {
                                model = straightHorizontalZ;
                            } else if (axis == Direction.Axis.Z) {
                                model = straightHorizontalZ;
                            } else {
                                model = regularModelId;
                            }
                        }
                        variant.with(VariantProperties.MODEL, model).with(VariantProperties.UV_LOCK, uvLock);

                        //                        int axisXRotationMod = (axis == Direction.Axis.X || axis == Direction.Axis.Z ? 270 : 0) - (half == Half.BOTTOM ? 0 : 90);
//                        int axisYRotationMod = (axis == Direction.Axis.X ? 90 : 0);
                        var xRotation = getRotation(halfXRotationModifier+facingXRotationModifier);
                        if(xRotation != VariantProperties.Rotation.R0){
                            variant.with(VariantProperties.X_ROT, xRotation);
                        }
                        var yRotation = getRotation(shapeYRotationMod + facingYRotationMod);
                        if(yRotation != VariantProperties.Rotation.R0){
                            variant.with(VariantProperties.Y_ROT, yRotation);
                        }
                        propertyDispatch.select(facing, half, shape, axis, variant);
                    }
                }
            }
        }
//                .select(Direction.EAST, Half.BOTTOM, StairsShape.STRAIGHT, Variant.variant()
//                        .with(VariantProperties.MODEL, regularModelId))
//                .select(Direction.WEST, Half.BOTTOM, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regularModelId)
//                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)
//                        .with(VariantProperties.UV_LOCK, uvLock))
//                .select(Direction.SOUTH, Half.BOTTOM, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regularModelId)
//                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
//                        .with(VariantProperties.UV_LOCK, uvLock))
//                .select(Direction.NORTH, Half.BOTTOM, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regularModelId)
//                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
//                        .with(VariantProperties.UV_LOCK, uvLock))
//                .select(Direction.EAST, Half.BOTTOM, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outerModelId))
//                .select(Direction.WEST, Half.BOTTOM, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outerModelId)
//                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)
//                        .with(VariantProperties.UV_LOCK, uvLock))
//                .select(Direction.SOUTH, Half.BOTTOM, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outerModelId)
//                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
//                        .with(VariantProperties.UV_LOCK, uvLock))
//                .select(Direction.NORTH, Half.BOTTOM, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outerModelId)
//                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
//                        .with(VariantProperties.UV_LOCK, uvLock))
//                .select(Direction.EAST, Half.BOTTOM, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outerModelId)
//                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
//                        .with(VariantProperties.UV_LOCK, uvLock))
//                .select(Direction.WEST, Half.BOTTOM, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outerModelId)
//                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
//                        .with(VariantProperties.UV_LOCK, uvLock))
//                .select(Direction.SOUTH, Half.BOTTOM, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outerModelId))
//                .select(Direction.NORTH, Half.BOTTOM, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outerModelId)
//                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)
//                        .with(VariantProperties.UV_LOCK, uvLock))
//                .select(Direction.EAST, Half.BOTTOM, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, innerModelId))
//                .select(Direction.WEST, Half.BOTTOM, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, innerModelId)
//                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)
//                        .with(VariantProperties.UV_LOCK, uvLock))
//                .select(Direction.SOUTH, Half.BOTTOM, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, innerModelId)
//                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
//                        .with(VariantProperties.UV_LOCK, uvLock))
//                .select(Direction.NORTH, Half.BOTTOM, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, innerModelId)
//                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
//                        .with(VariantProperties.UV_LOCK, uvLock))
//                .select(Direction.EAST, Half.BOTTOM, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerModelId)
//                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
//                        .with(VariantProperties.UV_LOCK, uvLock))
//                .select(Direction.WEST, Half.BOTTOM, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerModelId)
//                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
//                        .with(VariantProperties.UV_LOCK, uvLock))
//                .select(Direction.SOUTH, Half.BOTTOM, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerModelId))
//                .select(Direction.NORTH, Half.BOTTOM, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerModelId)
//                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)
//                        .with(VariantProperties.UV_LOCK, uvLock))
//                .select(Direction.NORTH, Half.TOP, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regularModelId)
//                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
//                        .with(VariantProperties.UV_LOCK, uvLock))
//                .select(Direction.EAST, Half.TOP, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outerModelId)
//                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
//                        .with(VariantProperties.UV_LOCK, uvLock))
//                .select(Direction.WEST, Half.TOP, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outerModelId)
//                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
//                        .with(VariantProperties.UV_LOCK, uvLock))
//                .select(Direction.SOUTH, Half.TOP, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outerModelId)
//                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)
//                        .with(VariantProperties.UV_LOCK, uvLock))
//                .select(Direction.NORTH, Half.TOP, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outerModelId)
//                        .with(VariantProperties.UV_LOCK, uvLock))
//                .select(Direction.EAST, Half.TOP, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outerModelId)
//                        .with(VariantProperties.UV_LOCK, uvLock))
//                .select(Direction.WEST, Half.TOP, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outerModelId)
//                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)
//                        .with(VariantProperties.UV_LOCK, uvLock))
//                .select(Direction.SOUTH, Half.TOP, StairsShape.OUTER_LEFT, Variant.variant()
//                        .with(VariantProperties.MODEL, outerModelId)
//                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, uvLock))
//                .select(Direction.NORTH, Half.TOP, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outerModelId)
//                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
//                        .with(VariantProperties.UV_LOCK, uvLock))
//                .select(Direction.EAST, Half.TOP, StairsShape.INNER_RIGHT, Variant.variant()
//                        .with(VariantProperties.MODEL, innerModelId)
//                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, uvLock))
//                .select(Direction.WEST, Half.TOP, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, innerModelId)
//                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
//                        .with(VariantProperties.UV_LOCK, uvLock))
//                .select(Direction.SOUTH, Half.TOP, StairsShape.INNER_RIGHT, Variant.variant()
//                        .with(VariantProperties.MODEL, innerModelId)
//                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, uvLock))
//                .select(Direction.NORTH, Half.TOP, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, innerModelId)
//                        .with(VariantProperties.UV_LOCK, uvLock))
//                .select(Direction.EAST, Half.TOP, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerModelId)
//                        .with(VariantProperties.UV_LOCK, uvLock))
//                .select(Direction.WEST, Half.TOP, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerModelId)
//                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)
//                        .with(VariantProperties.UV_LOCK, uvLock))
//                .select(Direction.SOUTH, Half.TOP, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerModelId)
//                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
//                        .with(VariantProperties.UV_LOCK, uvLock))
//                .select(Direction.NORTH, Half.TOP, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerModelId)
//                        .with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
//                        .with(VariantProperties.UV_LOCK, uvLock));
                return propertyDispatch;
    }

    public static BlockStateGenerator createRotatedPillarBlockStairs(Block block, ResourceLocation inner, ResourceLocation straight, ResourceLocation outer, ResourceLocation innerHorizontal, ResourceLocation straightHorizontal, ResourceLocation outerHorizontal, ResourceLocation innerHorizontalZ, ResourceLocation straightHorizontalZ, ResourceLocation outerHorizontalZ) {
        return createStairsBlockState(block, inner, straight, outer, innerHorizontal, straightHorizontal, outerHorizontal, innerHorizontalZ, straightHorizontalZ, outerHorizontalZ, false);
    }
}
