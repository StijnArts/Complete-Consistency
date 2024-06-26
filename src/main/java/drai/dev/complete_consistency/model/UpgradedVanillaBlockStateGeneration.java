package drai.dev.complete_consistency.model;

import net.minecraft.core.*;
import net.minecraft.data.models.blockstates.*;
import net.minecraft.resources.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.*;

public class UpgradedVanillaBlockStateGeneration {
	public static BlockStateGenerator createStairsBlockState(Block stairsBlock, ResourceLocation innerModelId, ResourceLocation regularModelId, ResourceLocation outerModelId, boolean uvLock) {
		return MultiVariantGenerator.multiVariant(stairsBlock).with(
				PropertyDispatch.properties(BlockStateProperties.HORIZONTAL_FACING, BlockStateProperties.HALF, BlockStateProperties.STAIRS_SHAPE)
						.select(Direction.EAST, Half.BOTTOM, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regularModelId))
						.select(Direction.WEST, Half.BOTTOM, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regularModelId)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, uvLock))
						.select(Direction.SOUTH, Half.BOTTOM, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regularModelId)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, uvLock))
						.select(Direction.NORTH, Half.BOTTOM, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regularModelId)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, uvLock))
						.select(Direction.EAST, Half.BOTTOM, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outerModelId))
						.select(Direction.WEST, Half.BOTTOM, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outerModelId)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, uvLock))
						.select(Direction.SOUTH, Half.BOTTOM, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outerModelId)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, uvLock))
						.select(Direction.NORTH, Half.BOTTOM, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outerModelId)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, uvLock))
						.select(Direction.EAST, Half.BOTTOM, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outerModelId)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, uvLock))
						.select(Direction.WEST, Half.BOTTOM, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outerModelId)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, uvLock))
						.select(Direction.SOUTH, Half.BOTTOM, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outerModelId))
						.select(Direction.NORTH, Half.BOTTOM, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outerModelId)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, uvLock))
						.select(Direction.EAST, Half.BOTTOM, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, innerModelId))
						.select(Direction.WEST, Half.BOTTOM, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, innerModelId)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, uvLock))
						.select(Direction.SOUTH, Half.BOTTOM, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, innerModelId)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, uvLock))
						.select(Direction.NORTH, Half.BOTTOM, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, innerModelId)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, uvLock))
						.select(Direction.EAST, Half.BOTTOM, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerModelId)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, uvLock))
						.select(Direction.WEST, Half.BOTTOM, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerModelId)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, uvLock))
						.select(Direction.SOUTH, Half.BOTTOM, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerModelId))
						.select(Direction.NORTH, Half.BOTTOM, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerModelId)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, uvLock))
						.select(Direction.EAST, Half.TOP, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regularModelId)
								.with(VariantProperties.UV_LOCK, uvLock))
						.select(Direction.WEST, Half.TOP, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regularModelId)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)
								.with(VariantProperties.UV_LOCK, uvLock)).select(Direction.SOUTH, Half.TOP, StairsShape.STRAIGHT, Variant.variant()
								.with(VariantProperties.MODEL, regularModelId)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, uvLock))
						.select(Direction.NORTH, Half.TOP, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regularModelId)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
								.with(VariantProperties.UV_LOCK, uvLock)).select(Direction.EAST, Half.TOP, StairsShape.OUTER_RIGHT, Variant.variant()
								.with(VariantProperties.MODEL, outerModelId)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, uvLock))
						.select(Direction.WEST, Half.TOP, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outerModelId)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
								.with(VariantProperties.UV_LOCK, uvLock)).select(Direction.SOUTH, Half.TOP, StairsShape.OUTER_RIGHT, Variant.variant()
								.with(VariantProperties.MODEL, outerModelId)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, uvLock))
						.select(Direction.NORTH, Half.TOP, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outerModelId)
								.with(VariantProperties.UV_LOCK, uvLock))
						.select(Direction.EAST, Half.TOP, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outerModelId)
								.with(VariantProperties.UV_LOCK, uvLock))
						.select(Direction.WEST, Half.TOP, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outerModelId)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)
								.with(VariantProperties.UV_LOCK, uvLock)).select(Direction.SOUTH, Half.TOP, StairsShape.OUTER_LEFT, Variant.variant()
								.with(VariantProperties.MODEL, outerModelId)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, uvLock))
						.select(Direction.NORTH, Half.TOP, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outerModelId)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
								.with(VariantProperties.UV_LOCK, uvLock)).select(Direction.EAST, Half.TOP, StairsShape.INNER_RIGHT, Variant.variant()
								.with(VariantProperties.MODEL, innerModelId)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, uvLock))
						.select(Direction.WEST, Half.TOP, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, innerModelId)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
								.with(VariantProperties.UV_LOCK, uvLock)).select(Direction.SOUTH, Half.TOP, StairsShape.INNER_RIGHT, Variant.variant()
								.with(VariantProperties.MODEL, innerModelId)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, uvLock))
						.select(Direction.NORTH, Half.TOP, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, innerModelId)
								.with(VariantProperties.UV_LOCK, uvLock))
						.select(Direction.EAST, Half.TOP, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerModelId)
								.with(VariantProperties.UV_LOCK, uvLock))
						.select(Direction.WEST, Half.TOP, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerModelId)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)
								.with(VariantProperties.UV_LOCK, uvLock))
						.select(Direction.SOUTH, Half.TOP, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerModelId)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, uvLock))
						.select(Direction.NORTH, Half.TOP, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerModelId)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
								.with(VariantProperties.UV_LOCK, uvLock)));
	}

	public static BlockStateGenerator createGrassStairsBlockState(Block stairsBlock, ResourceLocation innerModelId, ResourceLocation innerModelIdUp,
																  ResourceLocation regularModelId, ResourceLocation regularModelIdUp, ResourceLocation outerModelId, ResourceLocation outerModelIdUp,
																  boolean uvLock) {
		return MultiVariantGenerator.multiVariant(stairsBlock).with(
				PropertyDispatch.properties(BlockStateProperties.HORIZONTAL_FACING, BlockStateProperties.HALF, BlockStateProperties.STAIRS_SHAPE)
						.select(Direction.EAST, Half.BOTTOM, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regularModelId))
						.select(Direction.WEST, Half.BOTTOM, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regularModelId)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, uvLock))
						.select(Direction.SOUTH, Half.BOTTOM, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regularModelId)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, uvLock))
						.select(Direction.NORTH, Half.BOTTOM, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regularModelId)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, uvLock))
						.select(Direction.EAST, Half.BOTTOM, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outerModelId))
						.select(Direction.WEST, Half.BOTTOM, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outerModelId)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, uvLock))
						.select(Direction.SOUTH, Half.BOTTOM, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outerModelId)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, uvLock))
						.select(Direction.NORTH, Half.BOTTOM, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outerModelId)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, uvLock))
						.select(Direction.EAST, Half.BOTTOM, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outerModelId)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, uvLock))
						.select(Direction.WEST, Half.BOTTOM, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outerModelId)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, uvLock))
						.select(Direction.SOUTH, Half.BOTTOM, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outerModelId))
						.select(Direction.NORTH, Half.BOTTOM, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outerModelId)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, uvLock))
						.select(Direction.EAST, Half.BOTTOM, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, innerModelId))
						.select(Direction.WEST, Half.BOTTOM, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, innerModelId)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, uvLock))
						.select(Direction.SOUTH, Half.BOTTOM, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, innerModelId)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, uvLock))
						.select(Direction.NORTH, Half.BOTTOM, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, innerModelId)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, uvLock))
						.select(Direction.EAST, Half.BOTTOM, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerModelId)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, uvLock))
						.select(Direction.WEST, Half.BOTTOM, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerModelId)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, uvLock))
						.select(Direction.SOUTH, Half.BOTTOM, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerModelId))
						.select(Direction.NORTH, Half.BOTTOM, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerModelId)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, uvLock))
						.select(Direction.EAST, Half.TOP, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regularModelIdUp)
								.with(VariantProperties.UV_LOCK, uvLock))
						.select(Direction.WEST, Half.TOP, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regularModelIdUp)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)
								.with(VariantProperties.UV_LOCK, uvLock)).select(Direction.SOUTH, Half.TOP, StairsShape.STRAIGHT, Variant.variant()
								.with(VariantProperties.MODEL, regularModelIdUp)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, uvLock))
						.select(Direction.NORTH, Half.TOP, StairsShape.STRAIGHT, Variant.variant().with(VariantProperties.MODEL, regularModelIdUp)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
								.with(VariantProperties.UV_LOCK, uvLock)).select(Direction.EAST, Half.TOP, StairsShape.OUTER_RIGHT, Variant.variant()
								.with(VariantProperties.MODEL, outerModelIdUp)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, uvLock))
						.select(Direction.WEST, Half.TOP, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outerModelIdUp)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
								.with(VariantProperties.UV_LOCK, uvLock)).select(Direction.SOUTH, Half.TOP, StairsShape.OUTER_RIGHT, Variant.variant()
								.with(VariantProperties.MODEL, outerModelIdUp)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, uvLock))
						.select(Direction.NORTH, Half.TOP, StairsShape.OUTER_RIGHT, Variant.variant().with(VariantProperties.MODEL, outerModelIdUp)
								.with(VariantProperties.UV_LOCK, uvLock))
						.select(Direction.EAST, Half.TOP, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outerModelIdUp)
								.with(VariantProperties.UV_LOCK, uvLock))
						.select(Direction.WEST, Half.TOP, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outerModelIdUp)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)
								.with(VariantProperties.UV_LOCK, uvLock)).select(Direction.SOUTH, Half.TOP, StairsShape.OUTER_LEFT, Variant.variant()
								.with(VariantProperties.MODEL, outerModelIdUp)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, uvLock))
						.select(Direction.NORTH, Half.TOP, StairsShape.OUTER_LEFT, Variant.variant().with(VariantProperties.MODEL, outerModelIdUp)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
								.with(VariantProperties.UV_LOCK, uvLock)).select(Direction.EAST, Half.TOP, StairsShape.INNER_RIGHT, Variant.variant()
								.with(VariantProperties.MODEL, innerModelIdUp)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, uvLock))
						.select(Direction.WEST, Half.TOP, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, innerModelIdUp)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
								.with(VariantProperties.UV_LOCK, uvLock)).select(Direction.SOUTH, Half.TOP, StairsShape.INNER_RIGHT, Variant.variant()
								.with(VariantProperties.MODEL, innerModelIdUp)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, uvLock))
						.select(Direction.NORTH, Half.TOP, StairsShape.INNER_RIGHT, Variant.variant().with(VariantProperties.MODEL, innerModelIdUp)
								.with(VariantProperties.UV_LOCK, uvLock))
						.select(Direction.EAST, Half.TOP, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerModelIdUp)
								.with(VariantProperties.UV_LOCK, uvLock))
						.select(Direction.WEST, Half.TOP, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerModelIdUp)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)
								.with(VariantProperties.UV_LOCK, uvLock)).select(Direction.SOUTH, Half.TOP, StairsShape.INNER_LEFT, Variant.variant()
								.with(VariantProperties.MODEL, innerModelIdUp)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90).with(VariantProperties.UV_LOCK, uvLock))
						.select(Direction.NORTH, Half.TOP, StairsShape.INNER_LEFT, Variant.variant().with(VariantProperties.MODEL, innerModelIdUp)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270)
								.with(VariantProperties.UV_LOCK, uvLock)));
	}

	public static BlockStateGenerator createRotatableSlabBlockState(Block slab, ResourceLocation bottomModelId, ResourceLocation topModelId, ResourceLocation fullBlockModelId, boolean uvLock) {
		return MultiVariantGenerator.multiVariant(slab).with(
				PropertyDispatch.properties(BlockStateProperties.SLAB_TYPE, BlockStateProperties.HORIZONTAL_FACING)
						.select(SlabType.BOTTOM, Direction.EAST, Variant.variant()
								.with(VariantProperties.MODEL, bottomModelId)
								.with(VariantProperties.UV_LOCK,uvLock)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R0))
						.select(SlabType.TOP, Direction.EAST, Variant.variant()
								.with(VariantProperties.MODEL, topModelId)
								.with(VariantProperties.UV_LOCK,uvLock)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R0))
						.select(SlabType.DOUBLE, Direction.EAST, Variant.variant()
								.with(VariantProperties.MODEL, fullBlockModelId)
								.with(VariantProperties.UV_LOCK,uvLock)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R0))
						.select(SlabType.BOTTOM, Direction.NORTH, Variant.variant()
								.with(VariantProperties.MODEL, bottomModelId)
								.with(VariantProperties.UV_LOCK,uvLock)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270))
						.select(SlabType.TOP, Direction.NORTH, Variant.variant()
								.with(VariantProperties.MODEL, topModelId)
								.with(VariantProperties.UV_LOCK,uvLock)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270))
						.select(SlabType.DOUBLE, Direction.NORTH, Variant.variant()
								.with(VariantProperties.MODEL, fullBlockModelId)
								.with(VariantProperties.UV_LOCK,uvLock)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270))
						.select(SlabType.BOTTOM, Direction.SOUTH, Variant.variant()
								.with(VariantProperties.MODEL, bottomModelId)
								.with(VariantProperties.UV_LOCK,uvLock)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
						.select(SlabType.TOP, Direction.SOUTH, Variant.variant()
								.with(VariantProperties.MODEL, topModelId)
								.with(VariantProperties.UV_LOCK,uvLock)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
						.select(SlabType.DOUBLE, Direction.SOUTH, Variant.variant()
								.with(VariantProperties.MODEL, fullBlockModelId)
								.with(VariantProperties.UV_LOCK,uvLock)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
						.select(SlabType.BOTTOM, Direction.WEST, Variant.variant()
								.with(VariantProperties.MODEL, bottomModelId)
								.with(VariantProperties.UV_LOCK,uvLock)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
						.select(SlabType.TOP, Direction.WEST, Variant.variant()
								.with(VariantProperties.MODEL, topModelId)
								.with(VariantProperties.UV_LOCK,uvLock)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
						.select(SlabType.DOUBLE, Direction.WEST, Variant.variant()
								.with(VariantProperties.MODEL, fullBlockModelId)
								.with(VariantProperties.UV_LOCK,uvLock)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
		);
	}
	public static BlockStateGenerator createRotatableWall(Block wallBlock, ResourceLocation postModelLocation, ResourceLocation lowSideModelLocation, ResourceLocation tallSideModelLocation, boolean uvLock) {
		return MultiPartGenerator.multiPart(wallBlock)
				.with((Condition)Condition.condition().term(BlockStateProperties.UP, true), Variant.variant().with(VariantProperties.MODEL, postModelLocation))
				.with((Condition)Condition.condition().term(BlockStateProperties.NORTH_WALL, WallSide.LOW), Variant.variant()
						.with(VariantProperties.MODEL, lowSideModelLocation).with(VariantProperties.UV_LOCK, uvLock))
				.with((Condition)Condition.condition().term(BlockStateProperties.EAST_WALL, WallSide.LOW), Variant.variant()
						.with(VariantProperties.MODEL, lowSideModelLocation).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
						.with(VariantProperties.UV_LOCK, uvLock))
				.with((Condition)Condition.condition().term(BlockStateProperties.SOUTH_WALL, WallSide.LOW), Variant.variant()
						.with(VariantProperties.MODEL, lowSideModelLocation).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180).with(VariantProperties.UV_LOCK, uvLock))
				.with((Condition)Condition.condition().term(BlockStateProperties.WEST_WALL, WallSide.LOW), Variant.variant().with(VariantProperties.MODEL, lowSideModelLocation)
						.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, uvLock))
				.with((Condition)Condition.condition().term(BlockStateProperties.NORTH_WALL, WallSide.TALL), Variant.variant()
						.with(VariantProperties.MODEL, tallSideModelLocation).with(VariantProperties.UV_LOCK, uvLock))
				.with((Condition)Condition.condition().term(BlockStateProperties.EAST_WALL, WallSide.TALL), Variant.variant()
						.with(VariantProperties.MODEL, tallSideModelLocation).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90)
						.with(VariantProperties.UV_LOCK, uvLock))
				.with((Condition)Condition.condition().term(BlockStateProperties.SOUTH_WALL, WallSide.TALL), Variant.variant()
						.with(VariantProperties.MODEL, tallSideModelLocation).with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180)
						.with(VariantProperties.UV_LOCK, uvLock))
				.with((Condition)Condition.condition().term(BlockStateProperties.WEST_WALL, WallSide.TALL), Variant.variant().with(VariantProperties.MODEL, tallSideModelLocation)
						.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270).with(VariantProperties.UV_LOCK, uvLock));
	}
}
