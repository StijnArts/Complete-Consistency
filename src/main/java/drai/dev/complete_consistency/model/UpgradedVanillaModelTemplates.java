package drai.dev.complete_consistency.model;

import drai.dev.complete_consistency.blockentities.*;
import net.minecraft.data.models.model.*;
import net.minecraft.resources.*;
import net.minecraft.world.level.block.state.properties.*;

import java.util.*;

import static net.minecraft.data.models.model.TextureSlot.*;

public class UpgradedVanillaModelTemplates {
	public static TextureSlot BASE = TextureSlot.create("base");
	public static TextureSlot LEVER = TextureSlot.create("lever");
	public static TextureSlot LOG = TextureSlot.create("log");
	public static TextureSlot SIDES = TextureSlot.create("sides");
	public static TextureSlot WOOD = TextureSlot.create("wood");
	public static TextureSlot PIVOT = TextureSlot.create("pivot");
	public static TextureSlot ROUND = TextureSlot.create("round");
	public static TextureSlot LEG = TextureSlot.create("leg");
	public static TextureSlot HOOK = TextureSlot.create("hook");
	public static TextureSlot OVERLAY = TextureSlot.create("overlay");
	public static TextureSlot BODY = TextureSlot.create("body");
	public static TextureSlot X = TextureSlot.create("x");
	public static TextureSlot Y = TextureSlot.create("y");
	public static TextureSlot Z = TextureSlot.create("z");
	public static final BooleanProperty MAP = BooleanProperty.create("map");
	public static final ModelTemplate ITEM_FRAME = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/item_frame")),
			Optional.of(""),PARTICLE, WOOD, BACK);
	public static final ModelTemplate ITEM_FRAME_MAP = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/item_frame_map")),
			Optional.of(""),PARTICLE, WOOD, BACK);
	public static final ModelTemplate SCAFFOLDING_STABLE = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/scaffolding_stable")),
			Optional.of("stable"),PARTICLE, BOTTOM, SIDE, TOP);
	public static final ModelTemplate SCAFFOLDING_UNSTABLE = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/scaffolding_unstable")),
			Optional.of("unstable"),PARTICLE, BOTTOM, SIDE, TOP);
	public static final ModelTemplate COMPOSTER = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/composter")),
			Optional.of(""),PARTICLE, BOTTOM, SIDE, TOP, INSIDE);
	public static final ModelTemplate CHISELED_BOOKSHELF_TEMPLATE = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/chiseled_bookshelf_template")),
			Optional.of(""), SIDE, TOP);
	public static final ModelTemplate CHISELED_BOOKSHELF_INVENTORY = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/chiseled_bookshelf_inventory")),
			Optional.of("_inventory"), SIDE, TOP, TextureSlot.FRONT);
	public static final ModelTemplate WALL_POST_SIDE_BOTTOM_TOP = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/wall_post_side_bottom_top")),
			Optional.of("_post"), SIDE,TextureSlot.PARTICLE, TOP, BOTTOM);
	public static final ModelTemplate WALL_SIDE_SIDE_BOTTOM_TOP = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/wall_side_side_bottom_top")),
			Optional.of("_side"), SIDE,TextureSlot.PARTICLE, TOP, BOTTOM);
	public static final ModelTemplate WALL_SIDE_TALL_SIDE_BOTTOM_TOP = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/wall_side_tall_side_bottom_top")),
			Optional.of("_side_tall"), SIDE,TextureSlot.PARTICLE, TOP, BOTTOM);
	public static final ModelTemplate WALL_INVENTORY_SIDE_BOTTOM_TOP = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/wall_inventory_side_bottom_top")),
			Optional.of("_inventory"), SIDE,TextureSlot.PARTICLE, TOP, BOTTOM);
	public static final ModelTemplate PATH_WALL_POST = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/path_wall_post")),
			Optional.of("_post"), SIDE,TextureSlot.PARTICLE, TOP, BOTTOM);
	public static final ModelTemplate PATH_WALL_SIDE = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/path_wall_side")),
			Optional.of("_side"), SIDE,TextureSlot.PARTICLE, TOP, BOTTOM);
	public static final ModelTemplate PATH_WALL_SIDE_TALL = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/path_wall_side_tall")),
			Optional.of("_side_tall"), SIDE,TextureSlot.PARTICLE, TOP, BOTTOM);
	public static final ModelTemplate PATH_WALL_INVENTORY = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/path_wall_inventory")),
			Optional.of("_inventory"), SIDE,TextureSlot.PARTICLE, TOP, BOTTOM);
	public static final ModelTemplate GRASS_WALL_POST_SIDE_BOTTOM_TOP = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/grass_wall_post_side_bottom_top")),
			Optional.of("_post"), SIDE,TextureSlot.PARTICLE, TOP, BOTTOM);
	public static final ModelTemplate GRASS_WALL_SIDE_SIDE_BOTTOM_TOP = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/grass_wall_side_side_bottom_top")),
			Optional.of("_side"), SIDE,TextureSlot.PARTICLE, TOP, BOTTOM);
	public static final ModelTemplate GRASS_WALL_SIDE_TALL_SIDE_BOTTOM_TOP = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/grass_wall_side_tall_side_bottom_top")),
			Optional.of("_side_tall"), SIDE,TextureSlot.PARTICLE, TOP, BOTTOM);
	public static final ModelTemplate GRASS_WALL_INVENTORY_SIDE_BOTTOM_TOP = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/grass_wall_inventory_side_bottom_top")),
			Optional.of("_inventory"), SIDE,TextureSlot.PARTICLE, TOP, BOTTOM);
	public static final ModelTemplate LADDER = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/ladder")),
			Optional.of(""),TextureSlot.ALL);
	public static final ModelTemplate LEVER_ON = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/lever_on")),
			Optional.of("_on"),TextureSlot.PARTICLE,BASE,LEVER);
	public static final ModelTemplate LEVER_OFF = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/lever")),
			Optional.of(""),TextureSlot.PARTICLE,BASE,LEVER);
	public static final ModelTemplate CAMPFIRE_OFF = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/campfire_off")),
			Optional.of("_off"),TextureSlot.PARTICLE,LOG);
	public static final ModelTemplate CAMPFIRE_TEMPLATE = new ModelTemplate(Optional.of(new ResourceLocation("minecraft","block/template_campfire")),
			Optional.of("_template"),TextureSlot.PARTICLE,LOG);
	public static final ModelTemplate LECTERN = new ModelTemplate(Optional.of(new ResourceLocation("minecraft","block/lectern")),
			Optional.of("_template"),TextureSlot.PARTICLE, BOTTOM, BASE, TextureSlot.FRONT, SIDES, TOP);
	public static final ModelTemplate GRINDSTONE = new ModelTemplate(Optional.of(new ResourceLocation("minecraft","block/grindstone")),
			Optional.of(""), PIVOT, ROUND, SIDE, TextureSlot.PARTICLE,LEG);
	public static final ModelTemplate CRAFTING_TABLE_LIKE = new ModelTemplate(Optional.of(new ResourceLocation("minecraft","block/cartography_table")),
			Optional.of(""), TextureSlot.DOWN, TextureSlot.EAST, TextureSlot.NORTH, TextureSlot.PARTICLE, TextureSlot.SOUTH, TextureSlot.UP, TextureSlot.WEST);
	public static final ModelTemplate SMOKER = new ModelTemplate(Optional.of(new ResourceLocation("minecraft","block/smoker")),
			Optional.of(""), BOTTOM, TextureSlot.FRONT, SIDE, TOP);
	public static final ModelTemplate SMOKER_ON = new ModelTemplate(Optional.of(new ResourceLocation("minecraft","block/smoker_on")),
			Optional.of("_on"), BOTTOM, TextureSlot.FRONT, SIDE, TOP);
	public static final ModelTemplate FURNACE = new ModelTemplate(Optional.of(new ResourceLocation("minecraft","block/furnace")),
			Optional.of(""), TextureSlot.FRONT, SIDE, TOP);
	public static final ModelTemplate FURNACE_ON = new ModelTemplate(Optional.of(new ResourceLocation("minecraft","block/furnace_on")),
			Optional.of("_on"), TextureSlot.FRONT, SIDE, TOP);
	public static final ModelTemplate TRIPWIRE_HOOK = new ModelTemplate(Optional.of(new ResourceLocation("minecraft","block/tripwire_hook")),
			Optional.of(""), TextureSlot.PARTICLE, WOOD, HOOK);
	public static final ModelTemplate TRIPWIRE_HOOK_ON = new ModelTemplate(Optional.of(new ResourceLocation("minecraft","block/tripwire_hook_on")),
			Optional.of("_on"), TextureSlot.PARTICLE, WOOD, HOOK);
	public static final ModelTemplate TRIPWIRE_HOOK_ATTACHED = new ModelTemplate(Optional.of(new ResourceLocation("minecraft","block/tripwire_hook_attached")),
			Optional.of("_attached"), TextureSlot.PARTICLE, WOOD, HOOK);
	public static final ModelTemplate TRIPWIRE_HOOK_ATTACHED_ON = new ModelTemplate(Optional.of(new ResourceLocation("minecraft","block/tripwire_hook_attached_on")),
			Optional.of("_attached_on"), TextureSlot.PARTICLE, WOOD, HOOK);
	public static final ModelTemplate STONECUTTER = new ModelTemplate(Optional.of(new ResourceLocation("minecraft","block/stonecutter")),
			Optional.of(""), TextureSlot.PARTICLE, BOTTOM, SIDE, TOP);
	public static final ModelTemplate OBSERVER = new ModelTemplate(Optional.of(new ResourceLocation("minecraft","block/observer")),
			Optional.of(""), TextureSlot.FRONT, SIDE, TOP, BOTTOM, TextureSlot.PARTICLE);
	public static final ModelTemplate CHEST = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/chest_item")),
			Optional.of("_item"), TextureSlot.TEXTURE);
	public static final ModelTemplate OBSERVER_ON = new ModelTemplate(Optional.of(new ResourceLocation("minecraft","block/observer_on")),
			Optional.of("_on"), TextureSlot.FRONT, SIDE, TOP, BOTTOM, TextureSlot.PARTICLE);
	public static final ModelTemplate PATH_SLAB = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/path_slab")),
			Optional.of(""), BOTTOM, TOP, SIDE, TextureSlot.PARTICLE);
	public static final ModelTemplate PATH_SLAB_TOP = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/path_slab_top")),
			Optional.of("_top"), BOTTOM, TOP, SIDE, TextureSlot.PARTICLE);
	public static final ModelTemplate PATH_STAIRS = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/path_stairs")),
			Optional.of(""), BOTTOM, TOP, SIDE, TextureSlot.PARTICLE);
	public static final ModelTemplate PATH_STAIRS_INNER = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/path_stairs_inner")),
			Optional.of("_inner"), BOTTOM, TOP, SIDE, TextureSlot.PARTICLE);
	public static final ModelTemplate PATH_STAIRS_OUTER = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/path_stairs_outer")),
			Optional.of("_outer"), BOTTOM, TOP, SIDE, TextureSlot.PARTICLE);
	public static final ModelTemplate PATH_STAIRS_UP = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/path_stairs_up")),
			Optional.of("_up"), BOTTOM, TOP, SIDE, TextureSlot.PARTICLE);
	public static final ModelTemplate PATH_STAIRS_INNER_UP = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/path_stairs_inner_up")),
			Optional.of("_inner_up"), BOTTOM, TOP, SIDE, TextureSlot.PARTICLE);
	public static final ModelTemplate PATH_STAIRS_OUTER_UP = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/path_stairs_outer_up")),
			Optional.of("_outer_up"), BOTTOM, TOP, SIDE, TextureSlot.PARTICLE);
	public static final ModelTemplate GRASS_SLAB = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/grass_slab_template")),
			Optional.of(""), BOTTOM, TOP, SIDE, TextureSlot.PARTICLE, OVERLAY);
	public static final ModelTemplate GRASS_SLAB_TOP = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/grass_slab_top_template")),
			Optional.of("_top"), BOTTOM, TOP, SIDE, TextureSlot.PARTICLE, OVERLAY);
	public static final ModelTemplate GRASS_STAIRS = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/grass_stairs_template")),
			Optional.of(""), BOTTOM, TOP, SIDE, TextureSlot.PARTICLE, OVERLAY);
	public static final ModelTemplate GRASS_STAIRS_INNER = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/grass_stairs_inner_template")),
			Optional.of("_inner"), BOTTOM, TOP, SIDE, TextureSlot.PARTICLE, OVERLAY);
	public static final ModelTemplate GRASS_STAIRS_OUTER = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/grass_stairs_outer_template")),
			Optional.of("_outer"), BOTTOM, TOP, SIDE, TextureSlot.PARTICLE, OVERLAY);
	public static final ModelTemplate GRASS_STAIRS_UP = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/grass_stairs_up_template")),
			Optional.of("_up"), BOTTOM, TOP, SIDE, TextureSlot.PARTICLE, OVERLAY);
	public static final ModelTemplate GRASS_STAIRS_INNER_UP = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/grass_stairs_inner_up_template")),
			Optional.of("_inner_up"), BOTTOM, TOP, SIDE, TextureSlot.PARTICLE, OVERLAY);
	public static final ModelTemplate GRASS_STAIRS_OUTER_UP = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/grass_stairs_outer_up_template")),
			Optional.of("_outer_up"), BOTTOM, TOP, SIDE, TextureSlot.PARTICLE, OVERLAY);
	public static final ModelTemplate COLUMN_STAIRS_UP = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/column_stairs_up_template")),
			Optional.of("_up"), BOTTOM, TOP, SIDE, TextureSlot.PARTICLE);
	public static final ModelTemplate COLUMN_STAIRS_INNER_UP = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/column_stairs_inner_up_template")),
			Optional.of("_inner_up"), BOTTOM, TOP, SIDE, TextureSlot.PARTICLE);
	public static final ModelTemplate COLUMN_STAIRS_OUTER_UP = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/column_stairs_outer_up_template")),
			Optional.of("_outer_up"), BOTTOM, TOP, SIDE, TextureSlot.PARTICLE);
	public static final ModelTemplate LEAVES_STAIRS = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/leaves_stairs")),
			Optional.of("_up"), BOTTOM, TOP, SIDE);
	public static final ModelTemplate LEAVES_STAIRS_INNER = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/leaves_inner_stairs")),
			Optional.of("_inner_up"), BOTTOM, TOP, SIDE);
	public static final ModelTemplate LEAVES_STAIRS_OUTER = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/leaves_outer_stairs")),
			Optional.of("_outer_up"), BOTTOM, TOP, SIDE);
	public static final ModelTemplate LEAVES_SLAB = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/leaves_slab")),
			Optional.of(""), BOTTOM, TOP, SIDE);
	public static final ModelTemplate LEAVES_SLAB_TOP = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/leaves_slab_top")),
			Optional.of("_top"), BOTTOM, TOP, SIDE);
	public static final ModelTemplate LEAVES_WALL_POST = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/leaves_template_wall_post")),
			Optional.of("_post"),TextureSlot.WALL);
	public static final ModelTemplate LEAVES_WALL_SIDE = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/leaves_template_wall_side")),
			Optional.of("_side"),TextureSlot.WALL);
	public static final ModelTemplate LEAVES_WALL_SIDE_TALL = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/leaves_template_wall_side_tall")),
			Optional.of("_side_tall"),TextureSlot.WALL);
	public static final ModelTemplate LEAVES_WALL_INVENTORY = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/leaves_wall_inventory")),
			Optional.of("_inventory"),TextureSlot.WALL);
	public static final ModelTemplate LOG_WALL_POST = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/log_wall_post")),
			Optional.of("_post"), BOTTOM, TOP, SIDE, TextureSlot.PARTICLE);
	public static final ModelTemplate LOG_WALL_SIDE = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/log_wall_side")),
			Optional.of("_side"), BOTTOM, TOP, SIDE, TextureSlot.PARTICLE);
	public static final ModelTemplate LOG_WALL_SIDE_TALL = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/log_wall_side_tall")),
			Optional.of("_side_tall"), BOTTOM, TOP, SIDE, TextureSlot.PARTICLE);
	public static final ModelTemplate LOG_WALL_INVENTORY = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/log_wall_inventory")),
			Optional.of("_inventory"), BOTTOM, TOP, SIDE, TextureSlot.PARTICLE);
	public static final ModelTemplate GLAZED_STAIRS = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/glazed_terracotta_stairs")),
			Optional.of(""), BOTTOM, TOP, SIDE);
	public static final ModelTemplate GLAZED_STAIRS_INNER = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/glazed_terracotta_stairs_inner")),
			Optional.of("_inner"), BOTTOM, TOP, SIDE);
	public static final ModelTemplate GLAZED_STAIRS_OUTER = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/glazed_terracotta_stairs_outer")),
			Optional.of("_outer"), BOTTOM, TOP, SIDE);
	public static final ModelTemplate GLAZED_SLAB = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/glazed_terracotta_slab")),
			Optional.of(""), BOTTOM, TOP, SIDE);
	public static final ModelTemplate GLAZED_SLAB_TOP = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/glazed_terracotta_slab_top")),
			Optional.of("_top"), BOTTOM, TOP, SIDE);
	public static final ModelTemplate GLASS_STAIRS = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/glass/glass_stairs")),
			Optional.of(""), BOTTOM, TOP, SIDE, TextureSlot.PARTICLE, TextureSlot.FRONT);
	public static final ModelTemplate GLASS_STAIRS_INNER = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/glass/glass_stairs_inner")),
			Optional.of("_inner"), BOTTOM, TOP, SIDE, TextureSlot.PARTICLE, TextureSlot.FRONT);
	public static final ModelTemplate GLASS_STAIRS_OUTER = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/glass/glass_stairs_outer")),
			Optional.of("_outer"), BOTTOM, TOP, SIDE, TextureSlot.PARTICLE, TextureSlot.FRONT);
	public static final ModelTemplate GLASS_SLAB_BOTTOM = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/glass/glass_slab")),
			Optional.of(""), BOTTOM, TOP, SIDE);
	public static final ModelTemplate GLASS_SLAB_TOP = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/glass/glass_slab_top")),
			Optional.of("_top"), BOTTOM, TOP, SIDE);
	public static final ModelTemplate LIGHTNING_ROD = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/lightning_rod")),
			Optional.of(""), TextureSlot.TEXTURE, TextureSlot.PARTICLE);
	public static final ModelTemplate LIGHTNING_ROD_ON = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/lightning_rod_on")),
			Optional.of("_on"), TextureSlot.TEXTURE, TextureSlot.PARTICLE);
	public static final ModelTemplate CHAIN = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/chain")),
			Optional.of(""), TextureSlot.ALL, TextureSlot.PARTICLE);
	public static final ModelTemplate HOPPER = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/hopper")),
			Optional.of(""), TOP, SIDE, TextureSlot.INSIDE, TextureSlot.PARTICLE);
	public static final ModelTemplate HOPPER_SIDE = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/hopper_side")),
			Optional.of("_side"), TOP, SIDE, TextureSlot.INSIDE, TextureSlot.PARTICLE);
	public static final ModelTemplate ANVIL = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/template_anvil")),
			Optional.of(""), TOP, BODY, TextureSlot.PARTICLE);
	public static final ModelTemplate ROTATED_PILLAR_STAIRS = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/rotated_pillar_stairs")),
			Optional.of(""), X, Y, Z, TextureSlot.PARTICLE);
	public static final ModelTemplate ROTATED_PILLAR_STAIRS_INNER = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/rotated_pillar_stairs_inner")),
			Optional.of("_inner"), X, Y, Z, TextureSlot.PARTICLE);
	public static final ModelTemplate ROTATED_PILLAR_STAIRS_OUTER = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/rotated_pillar_stairs_outer")),
			Optional.of("_outer"), X, Y, Z, TextureSlot.PARTICLE);
	public static final ModelTemplate ROTATED_PILLAR_STAIRS_HORIZONTAL = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/rotated_pillar_stairs")),
			Optional.of("_horizontal"), X, Y, Z, TextureSlot.PARTICLE);
	public static final ModelTemplate ROTATED_PILLAR_STAIRS_INNER_HORIZONTAL = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/rotated_pillar_stairs_inner")),
			Optional.of("_inner_horizontal"), X, Y, Z, TextureSlot.PARTICLE);
	public static final ModelTemplate ROTATED_PILLAR_STAIRS_OUTER_HORIZONTAL = new ModelTemplate(Optional.of(new ResourceLocation("complete_consistency","block/rotated_pillar_stairs_outer")),
			Optional.of("_outer_horizontal"), X, Y, Z, TextureSlot.PARTICLE);

}
