package drai.dev.complete_consistency.tag;

import drai.dev.complete_consistency.helpers.*;
import net.minecraft.tags.*;
import net.minecraft.world.level.block.*;

public class UVCommonBlockTags {
	public static final TagKey<Block> BARREL = TagManager.BLOCKS.makeCommonTag("barrel");
	public static final TagKey<Block> BOOKSHELVES = TagManager.BLOCKS.makeCommonTag("bookshelves");
	public static final TagKey<Block> CHEST = TagManager.BLOCKS.makeCommonTag("chest");
	public static final TagKey<Block> COMPOSTER = TagManager.BLOCKS.makeCommonTag("composter");
	public static final TagKey<Block> END_STONES = TagManager.BLOCKS.makeCommonTag("end_stones");
	public static final TagKey<Block> GEN_END_STONES = END_STONES;
	public static final TagKey<Block> IMMOBILE = TagManager.BLOCKS.makeCommonTag("immobile");
	public static final TagKey<Block> LEAVES = TagManager.BLOCKS.makeCommonTag("leaves");
	public static final TagKey<Block> NETHERRACK = TagManager.BLOCKS.makeCommonTag("netherrack");
	public static final TagKey<Block> MYCELIUM = TagManager.BLOCKS.makeCommonTag("mycelium");
	public static final TagKey<Block> NETHER_MYCELIUM = TagManager.BLOCKS.makeCommonTag("nether_mycelium");
	public static final TagKey<Block> NETHER_PORTAL_FRAME = TagManager.BLOCKS.makeCommonTag("nether_pframe");
	public static final TagKey<Block> NETHER_STONES = TagManager.BLOCKS.makeCommonTag("nether_stones");
	public static final TagKey<Block> NETHER_ORES = TagManager.BLOCKS.makeCommonTag("nether_ores");
	public static final TagKey<Block> ORES = TagManager.BLOCKS.makeCommonTag("ores");
	public static final TagKey<Block> END_ORES = TagManager.BLOCKS.makeCommonTag("end_ores");
	public static final TagKey<Block> SAPLINGS = TagManager.BLOCKS.makeCommonTag("saplings");
	public static final TagKey<Block> SEEDS = TagManager.BLOCKS.makeCommonTag("seeds");
	public static final TagKey<Block> SOUL_GROUND = TagManager.BLOCKS.makeCommonTag("soul_ground");
	public static final TagKey<Block> SCULK_LIKE = TagManager.BLOCKS.makeCommonTag("sculk_like");
	public static final TagKey<Block> WOODEN_BARREL = TagManager.BLOCKS.makeCommonTag("wooden_barrels");
	public static final TagKey<Block> WOODEN_CHEST = TagManager.BLOCKS.makeCommonTag("wooden_chests");
	public static final TagKey<Block> WOODEN_COMPOSTER = TagManager.BLOCKS.makeCommonTag("wooden_composter");
	public static final TagKey<Block> WORKBENCHES = TagManager.BLOCKS.makeCommonTag("workbench");

	public static final TagKey<Block> DRAGON_IMMUNE = TagManager.BLOCKS.makeCommonTag("dragon_immune");

	public static final TagKey<Block> MINABLE_WITH_HAMMER = TagManager.BLOCKS.makeCommonTag("mineable/hammer");

	public static final TagKey<Block> IS_OBSIDIAN = TagManager.BLOCKS.makeCommonTag("is_obsidian");
	public static final TagKey<Block> TERRAIN = TagManager.BLOCKS.makeCommonTag("terrain");
	public static final TagKey<Block> GRASS_SOIL = TagManager.BLOCKS.makeCommonTag("grass_soil");
	public static final TagKey<Block> NETHER_TERRAIN = TagManager.BLOCKS.makeCommonTag("nether_terrain");
	public static final TagKey<Block> BUDDING_BLOCKS = TagManager.BLOCKS.makeCommonTag("budding_blocks");
	public static final TagKey<Block> WATER_PLANT = TagManager.BLOCKS.makeCommonTag("water_plant");
	;
	public static final TagKey<Block> PLANT = TagManager.BLOCKS.makeCommonTag("plant");
	;

	static void prepareTags() {
		TagManager.BLOCKS.addOtherTags(MINABLE_WITH_HAMMER, BlockTags.MINEABLE_WITH_PICKAXE);
		TagManager.BLOCKS.add(SCULK_LIKE, Blocks.SCULK);
		TagManager.BLOCKS.addOtherTags(DRAGON_IMMUNE, BlockTags.DRAGON_IMMUNE);

		TagManager.BLOCKS.add(END_STONES, Blocks.END_STONE);
		TagManager.BLOCKS.addOtherTags(NETHER_STONES, BlockTags.BASE_STONE_NETHER);

		TagManager.BLOCKS.add(
				NETHERRACK,
				Blocks.NETHERRACK,
				Blocks.NETHER_QUARTZ_ORE,
				Blocks.NETHER_GOLD_ORE,
				Blocks.CRIMSON_NYLIUM,
				Blocks.WARPED_NYLIUM
		);

		TagManager.BLOCKS.add(NETHER_ORES, Blocks.NETHER_QUARTZ_ORE, Blocks.NETHER_GOLD_ORE);
		TagManager.BLOCKS.add(SOUL_GROUND, Blocks.SOUL_SAND, Blocks.SOUL_SOIL);

		TagManager.BLOCKS.add(IS_OBSIDIAN, Blocks.OBSIDIAN, Blocks.CRYING_OBSIDIAN);

		TagManager.BLOCKS.add(MYCELIUM, Blocks.MYCELIUM);
		TagManager.BLOCKS.addOtherTags(MYCELIUM, NETHER_MYCELIUM);

		TagManager.BLOCKS.addOtherTags(GRASS_SOIL, BlockTags.DIRT, TERRAIN, BlockTags.LOGS, BlockTags.PLANKS);


		TagManager.BLOCKS.add(
				TERRAIN,
				Blocks.MAGMA_BLOCK,
				Blocks.GRAVEL,
				Blocks.SAND,
				Blocks.RED_SAND,
				Blocks.GLOWSTONE,
				Blocks.BONE_BLOCK,
				Blocks.SCULK
		);
		TagManager.BLOCKS.addOtherTags(
				TERRAIN,
				NETHER_TERRAIN,
				BlockTags.DRIPSTONE_REPLACEABLE,
				BlockTags.BASE_STONE_OVERWORLD,
				BlockTags.NYLIUM,
				MYCELIUM,
				END_STONES
		);

		TagManager.BLOCKS.add(
				NETHER_TERRAIN,
				Blocks.MAGMA_BLOCK,
				Blocks.GRAVEL,
				Blocks.RED_SAND,
				Blocks.GLOWSTONE,
				Blocks.BONE_BLOCK,
				Blocks.BLACKSTONE
		);
		TagManager.BLOCKS.addOtherTags(
				NETHER_TERRAIN,
				NETHERRACK,
				BlockTags.NYLIUM,
				NETHER_ORES,
				SOUL_GROUND,
				NETHER_MYCELIUM
		);

		TagManager.BLOCKS.add(UVCommonBlockTags.BOOKSHELVES, Blocks.BOOKSHELF);
		TagManager.BLOCKS.add(UVCommonBlockTags.CHEST, Blocks.CHEST);

		TagManager.BLOCKS.add(
				BlockTags.NETHER_CARVER_REPLACEABLES,
				Blocks.BASALT,
				Blocks.RED_SAND,
				Blocks.MAGMA_BLOCK,
				Blocks.SCULK
		);
		TagManager.BLOCKS.addOtherTags(
				BlockTags.NETHER_CARVER_REPLACEABLES,
				UVCommonBlockTags.NETHER_STONES,
				UVCommonBlockTags.NETHERRACK
		);

		TagManager.BLOCKS.addOtherTags(
				BlockTags.MINEABLE_WITH_AXE,
				WOODEN_BARREL,
				WOODEN_COMPOSTER,
				WOODEN_CHEST,
				WORKBENCHES
		);

		TagManager.BLOCKS.add(WATER_PLANT, Blocks.KELP, Blocks.KELP_PLANT, Blocks.SEAGRASS, Blocks.TALL_SEAGRASS);
		TagManager.BLOCKS.add(
				SAPLINGS,
				Blocks.OAK_SAPLING,
				Blocks.SPRUCE_SAPLING,
				Blocks.BIRCH_SAPLING,
				Blocks.JUNGLE_SAPLING,
				Blocks.ACACIA_SAPLING,
				Blocks.DARK_OAK_SAPLING,
				Blocks.CHERRY_SAPLING,
				Blocks.BAMBOO_SAPLING,
				Blocks.MANGROVE_PROPAGULE
		);
		TagManager.BLOCKS.addOtherTags(PLANT, SAPLINGS);
		TagManager.BLOCKS.add(
				PLANT,
				Blocks.MANGROVE_LEAVES,
				Blocks.SHORT_GRASS,
				Blocks.FERN,
				Blocks.DANDELION,
				Blocks.TORCHFLOWER,
				Blocks.POPPY,
				Blocks.BLUE_ORCHID,
				Blocks.ALLIUM,
				Blocks.AZURE_BLUET,
				Blocks.RED_TULIP,
				Blocks.ORANGE_TULIP,
				Blocks.WHITE_TULIP,
				Blocks.PINK_TULIP,
				Blocks.OXEYE_DAISY,
				Blocks.CORNFLOWER,
				Blocks.WITHER_ROSE,
				Blocks.LILY_OF_THE_VALLEY,
				Blocks.WHEAT,
				Blocks.CACTUS,
				Blocks.SUGAR_CANE,
				Blocks.ATTACHED_PUMPKIN_STEM,
				Blocks.ATTACHED_MELON_STEM,
				Blocks.PUMPKIN_STEM,
				Blocks.MELON_STEM,
				Blocks.VINE,
				Blocks.LILY_PAD,
				Blocks.COCOA,
				Blocks.CARROTS,
				Blocks.POTATOES,
				Blocks.SUNFLOWER,
				Blocks.LILAC,
				Blocks.ROSE_BUSH,
				Blocks.PEONY,
				Blocks.TALL_GRASS,
				Blocks.LARGE_FERN,
				Blocks.TORCHFLOWER_CROP,
				Blocks.PITCHER_CROP,
				Blocks.PITCHER_PLANT,
				Blocks.BEETROOTS,
				Blocks.BAMBOO,
				Blocks.SWEET_BERRY_BUSH,
				Blocks.CAVE_VINES,
				Blocks.CAVE_VINES_PLANT,
				Blocks.SPORE_BLOSSOM,
				Blocks.AZALEA,
				Blocks.FLOWERING_AZALEA,
				Blocks.PINK_PETALS,
				Blocks.BIG_DRIPLEAF,
				Blocks.BIG_DRIPLEAF_STEM,
				Blocks.SMALL_DRIPLEAF
		);
	}
	public static boolean initialized = false;
	public static void register(){
		if(!initialized){
			ADAMANTITE_BLOCKS = TagKeyHelper.createCommonBlockTagKey("adamantite_blocks");
			ADAMANTITE_ORES = TagKeyHelper.createCommonBlockTagKey("adamantite_ores");
			AETHERIUM_BLOCKS = TagKeyHelper.createCommonBlockTagKey("aetherium_blocks");
			AETHERIUM_ORES = TagKeyHelper.createCommonBlockTagKey("aetherium_ores");
			ALUMINUM_BLOCKS = TagKeyHelper.createCommonBlockTagKey("aluminum_blocks");
			ALUMINUM_ORES = TagKeyHelper.createCommonBlockTagKey("aluminum_ores");
			AMETHYST_BLOCKS = TagKeyHelper.createCommonBlockTagKey("amethyst_blocks");
			AMETHYST_ORES = TagKeyHelper.createCommonBlockTagKey("amethyst_ores");
			ANCIENT_DEBRIS = TagKeyHelper.createCommonBlockTagKey("ancient_debris");
			AQUARIUM_BLOCKS = TagKeyHelper.createCommonBlockTagKey("aquarium_blocks");
			AQUARIUM_ORES = TagKeyHelper.createCommonBlockTagKey("aquarium_ores");
			ARGONIUM_BLOCKS = TagKeyHelper.createCommonBlockTagKey("argonium_blocks");
			ASTERITE_BLOCKS = TagKeyHelper.createCommonBlockTagKey("asterite_blocks");
			ASTERITE_ORES = TagKeyHelper.createCommonBlockTagKey("asterite_ores");
			ASTEROID_ASTERITE_ORES = TagKeyHelper.createCommonBlockTagKey("asteroid_asterite_ores");
			ASTEROID_COAL_ORES = TagKeyHelper.createCommonBlockTagKey("asteroid_coal_ores");
			ASTEROID_COPPER_ORES = TagKeyHelper.createCommonBlockTagKey("asteroid_copper_ores");
			ASTEROID_DIAMOND_ORES = TagKeyHelper.createCommonBlockTagKey("asteroid_diamond_ores");
			ASTEROID_EMERALD_ORES = TagKeyHelper.createCommonBlockTagKey("asteroid_emerald_ores");
			ASTEROID_GALAXIUM_ORES = TagKeyHelper.createCommonBlockTagKey("asteroid_galaxium_ores");
			ASTEROID_GOLD_ORES = TagKeyHelper.createCommonBlockTagKey("asteroid_gold_ores");
			ASTEROID_IRON_ORES = TagKeyHelper.createCommonBlockTagKey("asteroid_iron_ores");
			ASTEROID_LAPIS_ORES = TagKeyHelper.createCommonBlockTagKey("asteroid_lapis_ores");
			ASTEROID_LEAD_ORES = TagKeyHelper.createCommonBlockTagKey("asteroid_lead_ores");
			ASTEROID_METITE_ORES = TagKeyHelper.createCommonBlockTagKey("asteroid_metite_ores");
			ASTEROID_REDSTONE_ORES = TagKeyHelper.createCommonBlockTagKey("asteroid_redstone_ores");
			ASTEROID_SILVER_ORES = TagKeyHelper.createCommonBlockTagKey("asteroid_silver_ores");
			ASTEROID_STELLUM_ORES = TagKeyHelper.createCommonBlockTagKey("asteroid_stellum_ores");
			ASTEROID_TIN_ORES = TagKeyHelper.createCommonBlockTagKey("asteroid_tin_ores");
			BANGLUM_BLOCKS = TagKeyHelper.createCommonBlockTagKey("banglum_blocks");
			BANGLUM_ORES = TagKeyHelper.createCommonBlockTagKey("banglum_ores");
			BASALT = TagKeyHelper.createCommonBlockTagKey("basalt");
			BAUXITE_ORES = TagKeyHelper.createCommonBlockTagKey("bauxite_ores");
			BLACK_SAND = TagKeyHelper.createCommonBlockTagKey("black_sand");
			BLUE_SAND = TagKeyHelper.createCommonBlockTagKey("blue_sand");
			BONE_BLOCKS = TagKeyHelper.createCommonBlockTagKey("bone_blocks");
			BRASS_BLOCKS = TagKeyHelper.createCommonBlockTagKey("brass_blocks");
			BRONZE_BLOCKS = TagKeyHelper.createCommonBlockTagKey("bronze_blocks");
			BUSHES = TagKeyHelper.createCommonBlockTagKey("bushes");
			CARMOT_BLOCKS = TagKeyHelper.createCommonBlockTagKey("carmot_blocks");
			CARMOT_ORES = TagKeyHelper.createCommonBlockTagKey("carmot_ores");
			CELESTIUM_BLOCKS = TagKeyHelper.createCommonBlockTagKey("celestium_blocks");
			CERTUS_QUARTZ_BLOCKS = TagKeyHelper.createCommonBlockTagKey("certus_quartz_blocks");
			CERTUS_QUARTZ_ORES = TagKeyHelper.createCommonBlockTagKey("certus_quartz_ores");
			CHROME_BLOCKS = TagKeyHelper.createCommonBlockTagKey("chrome_blocks");
			CINNABAR_ORES = TagKeyHelper.createCommonBlockTagKey("cinnabar_ores");
			COAL_BLOCKS = TagKeyHelper.createCommonBlockTagKey("coal_blocks");
			COAL_COKE_BLOCKS = TagKeyHelper.createCommonBlockTagKey("coal_coke_blocks");
			COAL_GRAVELS = TagKeyHelper.createCommonBlockTagKey("coal_gravels");
			COAL_ORES = TagKeyHelper.createCommonBlockTagKey("coal_ores");
			COBALT_BLOCKS = TagKeyHelper.createCommonBlockTagKey("cobalt_blocks");
			COBALT_DUSTS = TagKeyHelper.createCommonBlockTagKey("cobalt_dusts");
			COBALT_ORES = TagKeyHelper.createCommonBlockTagKey("cobalt_ores");
			COBBLESTONE = TagKeyHelper.createCommonBlockTagKey("cobblestone");
			COMPRESSED_DIRT = TagKeyHelper.createCommonBlockTagKey("compressed_dirt");
			COMPRESSED_GLOW_STONE = TagKeyHelper.createCommonBlockTagKey("compressed_glow_stone");
			COMPRESSED_OBSIDIAN = TagKeyHelper.createCommonBlockTagKey("compressed_obsidian");
			COMPRESSED_STONE = TagKeyHelper.createCommonBlockTagKey("compressed_stone");
			CONCRETE = TagKeyHelper.createCommonBlockTagKey("concrete");
			CONCRETE_POWDER = TagKeyHelper.createCommonBlockTagKey("concrete_powder");
			COPPER_BLOCKS = TagKeyHelper.createCommonBlockTagKey("copper_blocks");
			COPPER_ORE = TagKeyHelper.createCommonBlockTagKey("copper_ore");
			COPPER_ORES = TagKeyHelper.createCommonBlockTagKey("copper_ores");
			CROP_LIKE = TagKeyHelper.createCommonBlockTagKey("crop_like");
			DIAMOND_BLOCKS = TagKeyHelper.createCommonBlockTagKey("diamond_blocks");
			DIAMOND_GRAVELS = TagKeyHelper.createCommonBlockTagKey("diamond_gravels");
			DIAMOND_ORES = TagKeyHelper.createCommonBlockTagKey("diamond_ores");
			DIRT = TagKeyHelper.createCommonBlockTagKey("dirt");
			DISCORDIUM_BLOCKS = TagKeyHelper.createCommonBlockTagKey("discordium_blocks");
			DOUBLE_COMPRESSED_DIRT = TagKeyHelper.createCommonBlockTagKey("double_compressed_dirt");
			DOUBLE_COMPRESSED_GLOW_STONE = TagKeyHelper.createCommonBlockTagKey("double_compressed_glow_stone");
			DOUBLE_COMPRESSED_OBSIDIAN = TagKeyHelper.createCommonBlockTagKey("double_compressed_obsidian");
			DOUBLE_COMPRESSED_STONE = TagKeyHelper.createCommonBlockTagKey("double_compressed_stone");
			DURASTEEL_BLOCKS = TagKeyHelper.createCommonBlockTagKey("durasteel_blocks");
			ELECTRUM_BLOCKS = TagKeyHelper.createCommonBlockTagKey("electrum_blocks");
			EMERALD_BLOCKS = TagKeyHelper.createCommonBlockTagKey("emerald_blocks");
			EMERALD_GRAVELS = TagKeyHelper.createCommonBlockTagKey("emerald_gravels");
			EMERALD_ORES = TagKeyHelper.createCommonBlockTagKey("emerald_ores");
			ETHERITE_BLOCKS = TagKeyHelper.createCommonBlockTagKey("etherite_blocks");
			FARMLANDS = TagKeyHelper.createCommonBlockTagKey("farmlands");
			FENCE_GATES = TagKeyHelper.createCommonBlockTagKey("fence_gates");
			FERRITE_BLOCKS = TagKeyHelper.createCommonBlockTagKey("ferrite_blocks");
			FLOWERS = TagKeyHelper.createCommonBlockTagKey("flowers");
			FOOLS_GOLD_BLOCKS = TagKeyHelper.createCommonBlockTagKey("fools_gold_blocks");
			GALAXIUM_BLOCKS = TagKeyHelper.createCommonBlockTagKey("galaxium_blocks");
			GALAXIUM_ORES = TagKeyHelper.createCommonBlockTagKey("galaxium_ores");
			GALENA_ORES = TagKeyHelper.createCommonBlockTagKey("galena_ores");
			GLASS = TagKeyHelper.createCommonBlockTagKey("glass");
			GLASS_BLOCKS = TagKeyHelper.createCommonBlockTagKey("glass_blocks");
			GLOWSTONE_BLOCKS = TagKeyHelper.createCommonBlockTagKey("glowstone_blocks");
			GOLD_BLOCKS = TagKeyHelper.createCommonBlockTagKey("gold_blocks");
			GOLD_GRAVELS = TagKeyHelper.createCommonBlockTagKey("gold_gravels");
			GOLD_ORES = TagKeyHelper.createCommonBlockTagKey("gold_ores");
			GRASS = TagKeyHelper.createCommonBlockTagKey("grass");
			GRASS_LIKE = TagKeyHelper.createCommonBlockTagKey("grass_like");
			GRAVEL = TagKeyHelper.createCommonBlockTagKey("gravel");
			INVAR_BLOCKS = TagKeyHelper.createCommonBlockTagKey("invar_blocks");
			IRIDIUM_BLOCKS = TagKeyHelper.createCommonBlockTagKey("iridium_blocks");
			IRIDIUM_ORES = TagKeyHelper.createCommonBlockTagKey("iridium_ores");
			IRIDIUM_REINFORCED_STONE_BLOCKS = TagKeyHelper.createCommonBlockTagKey("iridium_reinforced_stone_blocks");
			IRIDIUM_REINFORCED_TUNGSTENSTEEL_BLOCKS = TagKeyHelper.createCommonBlockTagKey("iridium_reinforced_tungstensteel_blocks");
			IRON_BLOCKS = TagKeyHelper.createCommonBlockTagKey("iron_blocks");
			IRON_GRAVELS = TagKeyHelper.createCommonBlockTagKey("iron_gravels");
			IRON_ORES = TagKeyHelper.createCommonBlockTagKey("iron_ores");
			KYBER_BLOCKS = TagKeyHelper.createCommonBlockTagKey("kyber_blocks");
			KYBER_ORES = TagKeyHelper.createCommonBlockTagKey("kyber_ores");
			LAPIS_BLOCKS = TagKeyHelper.createCommonBlockTagKey("lapis_blocks");
			LAPIS_GRAVELS = TagKeyHelper.createCommonBlockTagKey("lapis_gravels");
			LAPIS_ORES = TagKeyHelper.createCommonBlockTagKey("lapis_ores");
			LEAD_BLOCKS = TagKeyHelper.createCommonBlockTagKey("lead_blocks");
			LEAD_ORES = TagKeyHelper.createCommonBlockTagKey("lead_ores");
			LIMESTONE = TagKeyHelper.createCommonBlockTagKey("limestone");
			LUNUM_BLOCKS = TagKeyHelper.createCommonBlockTagKey("lunum_blocks");
			LUNUM_ORES = TagKeyHelper.createCommonBlockTagKey("lunum_ores");
			LUTETIUM_BLOCKS = TagKeyHelper.createCommonBlockTagKey("lutetium_blocks");
			LUTETIUM_ORES = TagKeyHelper.createCommonBlockTagKey("lutetium_ores");
			MANGANESE_BLOCKS = TagKeyHelper.createCommonBlockTagKey("manganese_blocks");
			MANGANESE_ORES = TagKeyHelper.createCommonBlockTagKey("manganese_ores");
			MARBLE = TagKeyHelper.createCommonBlockTagKey("marble");
			METALLURGIUM_BLOCKS = TagKeyHelper.createCommonBlockTagKey("metallurgium_blocks");
			METEORIC_STEEL_BLOCKS = TagKeyHelper.createCommonBlockTagKey("meteoric_steel_blocks");
			METEOR_METITE_ORES = TagKeyHelper.createCommonBlockTagKey("meteor_metite_ores");
			METITE_BLOCKS = TagKeyHelper.createCommonBlockTagKey("metite_blocks");
			METITE_ORES = TagKeyHelper.createCommonBlockTagKey("metite_ores");
			MIDAS_GOLD_BLOCKS = TagKeyHelper.createCommonBlockTagKey("midas_gold_blocks");
			MIDAS_GOLD_ORES = TagKeyHelper.createCommonBlockTagKey("midas_gold_ores");
			MOON_LUNUM_ORES = TagKeyHelper.createCommonBlockTagKey("moon_lunum_ores");
			MUSHROOMS = TagKeyHelper.createCommonBlockTagKey("mushrooms");
			MYTHRIL_BLOCKS = TagKeyHelper.createCommonBlockTagKey("mythril_blocks");
			MYTHRIL_ORES = TagKeyHelper.createCommonBlockTagKey("mythril_ores");
			NATURAL_STONES = TagKeyHelper.createCommonBlockTagKey("natural_stones");
			NETHERITE_BLOCKS = TagKeyHelper.createCommonBlockTagKey("netherite_blocks");
			NETHER_PFRAME = TagKeyHelper.createCommonBlockTagKey("nether_pframe");
			NETHERRACKS = TagKeyHelper.createCommonBlockTagKey("netherracks");
			NICKEL_BLOCKS = TagKeyHelper.createCommonBlockTagKey("nickel_blocks");
			NICKEL_ORES = TagKeyHelper.createCommonBlockTagKey("nickel_ores");
			ORES_COAL = TagKeyHelper.createCommonBlockTagKey("ores/coal");
			ORES_DIAMOND = TagKeyHelper.createCommonBlockTagKey("ores/diamond");
			ORES_EMERALD = TagKeyHelper.createCommonBlockTagKey("ores/emerald");
			ORES_GOLD = TagKeyHelper.createCommonBlockTagKey("ores/gold");
			ORES_IRON = TagKeyHelper.createCommonBlockTagKey("ores/iron");
			ORES_LAPIS = TagKeyHelper.createCommonBlockTagKey("ores/lapis");
			ORES_REDSTONE = TagKeyHelper.createCommonBlockTagKey("ores/redstone");
			ORICHALCUM_BLOCKS = TagKeyHelper.createCommonBlockTagKey("orichalcum_blocks");
			ORICHALCUM_ORES = TagKeyHelper.createCommonBlockTagKey("orichalcum_ores");
			OSMIUM_BLOCKS = TagKeyHelper.createCommonBlockTagKey("osmium_blocks");
			OSMIUM_ORES = TagKeyHelper.createCommonBlockTagKey("osmium_ores");
			PALLADIUM_BLOCKS = TagKeyHelper.createCommonBlockTagKey("palladium_blocks");
			PALLADIUM_ORES = TagKeyHelper.createCommonBlockTagKey("palladium_ores");
			PERIDOT_BLOCKS = TagKeyHelper.createCommonBlockTagKey("peridot_blocks");
			PERIDOT_ORES = TagKeyHelper.createCommonBlockTagKey("peridot_ores");
			PINK_SAND = TagKeyHelper.createCommonBlockTagKey("pink_sand");
			PLANKS_THAT_BURN = TagKeyHelper.createCommonBlockTagKey("planks_that_burn");
			PLATINUM_BLOCKS = TagKeyHelper.createCommonBlockTagKey("platinum_blocks");
			PLATINUM_ORES = TagKeyHelper.createCommonBlockTagKey("platinum_ores");
			PLUTONIUM_BLOCKS = TagKeyHelper.createCommonBlockTagKey("plutonium_blocks");
			POLLEN = TagKeyHelper.createCommonBlockTagKey("pollen");
			STRIPPED_LOGS = TagKeyHelper.createCommonBlockTagKey("stripped_logs");
			STRIPPED_WOOD = TagKeyHelper.createCommonBlockTagKey("stripped_wood");
			PREHISTORIC_CALAMITES_LOGS = TagKeyHelper.createCommonBlockTagKey("prehistoric_calamites_logs");
			PREHISTORIC_DARKWOOD_LOGS = TagKeyHelper.createCommonBlockTagKey("prehistoric_darkwood_logs");
			PREHISTORIC_LEPIDODENDRALES_LOGS = TagKeyHelper.createCommonBlockTagKey("prehistoric_lepidodendrales_logs");
			PREHISTORIC_MANGROVE_LOGS = TagKeyHelper.createCommonBlockTagKey("prehistoric_mangrove_logs");
			PROMETHEUM_BLOCKS = TagKeyHelper.createCommonBlockTagKey("prometheum_blocks");
			PROMETHEUM_ORES = TagKeyHelper.createCommonBlockTagKey("prometheum_ores");
			PURPLE_SAND = TagKeyHelper.createCommonBlockTagKey("purple_sand");
			PURPUR_BLOCKS = TagKeyHelper.createCommonBlockTagKey("purpur_blocks");
			PYRITE_BLOCKS = TagKeyHelper.createCommonBlockTagKey("pyrite_blocks");
			PYRITE_ORES = TagKeyHelper.createCommonBlockTagKey("pyrite_ores");
			QUADRILLUM_BLOCKS = TagKeyHelper.createCommonBlockTagKey("quadrillum_blocks");
			QUADRILLUM_ORES = TagKeyHelper.createCommonBlockTagKey("quadrillum_ores");
			QUARTZ_BLOCKS = TagKeyHelper.createCommonBlockTagKey("quartz_blocks");
			QUARTZ_ORES = TagKeyHelper.createCommonBlockTagKey("quartz_ores");
			QUICKSILVER_BLOCKS = TagKeyHelper.createCommonBlockTagKey("quicksilver_blocks");
			RAW_SILVER_BLOCKS = TagKeyHelper.createCommonBlockTagKey("raw_silver_blocks");
			RED_GARNET_BLOCKS = TagKeyHelper.createCommonBlockTagKey("red_garnet_blocks");
			RED_SANDSTONES = TagKeyHelper.createCommonBlockTagKey("red_sandstones");
			REDSTONE_BLOCKS = TagKeyHelper.createCommonBlockTagKey("redstone_blocks");
			REDSTONE_GRAVELS = TagKeyHelper.createCommonBlockTagKey("redstone_gravels");
			REDSTONE_ORES = TagKeyHelper.createCommonBlockTagKey("redstone_ores");
			REFINED_IRON_BLOCKS = TagKeyHelper.createCommonBlockTagKey("refined_iron_blocks");
			ROSE_GOLD_BLOCKS = TagKeyHelper.createCommonBlockTagKey("rose_gold_blocks");
			RUBY_BLOCKS = TagKeyHelper.createCommonBlockTagKey("ruby_blocks");
			RUBY_ORES = TagKeyHelper.createCommonBlockTagKey("ruby_ores");
			RUNITE_BLOCKS = TagKeyHelper.createCommonBlockTagKey("runite_blocks");
			RUNITE_ORES = TagKeyHelper.createCommonBlockTagKey("runite_ores");
			SALT_BLOCKS = TagKeyHelper.createCommonBlockTagKey("salt_blocks");
			SALT_ORES = TagKeyHelper.createCommonBlockTagKey("salt_ores");
			SAND = TagKeyHelper.createCommonBlockTagKey("sand");
			SANDSTONE = TagKeyHelper.createCommonBlockTagKey("sandstone");
			SAPPHIRE_BLOCKS = TagKeyHelper.createCommonBlockTagKey("sapphire_blocks");
			SAPPHIRE_ORES = TagKeyHelper.createCommonBlockTagKey("sapphire_ores");
			SHELDONITE_ORES = TagKeyHelper.createCommonBlockTagKey("sheldonite_ores");
			SILVER_BLOCKS = TagKeyHelper.createCommonBlockTagKey("silver_blocks");
			SILVER_ORES = TagKeyHelper.createCommonBlockTagKey("silver_ores");
			SLIME_BLOCKS = TagKeyHelper.createCommonBlockTagKey("slime_blocks");
			SLOWSILVER_BLOCKS = TagKeyHelper.createCommonBlockTagKey("slowsilver_blocks");
			SODALITE_ORES = TagKeyHelper.createCommonBlockTagKey("sodalite_ores");
			SPHALERITE_ORES = TagKeyHelper.createCommonBlockTagKey("sphalerite_ores");
			STAINED_GLASS = TagKeyHelper.createCommonBlockTagKey("stained_glass");
			STARRITE_BLOCKS = TagKeyHelper.createCommonBlockTagKey("starrite_blocks");
			STARRITE_ORES = TagKeyHelper.createCommonBlockTagKey("starrite_ores");
			STEEL_BLOCKS = TagKeyHelper.createCommonBlockTagKey("steel_blocks");
			STELLUM_BLOCKS = TagKeyHelper.createCommonBlockTagKey("stellum_blocks");
			STELLUM_ORES = TagKeyHelper.createCommonBlockTagKey("stellum_ores");
			STERLING_SILVER_BLOCKS = TagKeyHelper.createCommonBlockTagKey("sterling_silver_blocks");
			STONE = TagKeyHelper.createCommonBlockTagKey("stone");
			STORAGE_BLOCKS = TagKeyHelper.createCommonBlockTagKey("storage_blocks");
			STORMYX_BLOCKS = TagKeyHelper.createCommonBlockTagKey("stormyx_blocks");
			STORMYX_ORES = TagKeyHelper.createCommonBlockTagKey("stormyx_ores");
			STRIP_COMMAND = TagKeyHelper.createCommonBlockTagKey("strip_command");
			TANTALITE_BLOCKS = TagKeyHelper.createCommonBlockTagKey("tantalite_blocks");
			TANTALITE_ORES = TagKeyHelper.createCommonBlockTagKey("tantalite_ores");
			TERRACOTTA = TagKeyHelper.createCommonBlockTagKey("terracotta");
			THORIUM_BLOCKS = TagKeyHelper.createCommonBlockTagKey("thorium_blocks");
			TIN_BLOCKS = TagKeyHelper.createCommonBlockTagKey("tin_blocks");
			TIN_ORES = TagKeyHelper.createCommonBlockTagKey("tin_ores");
			TITANIUM_BLOCKS = TagKeyHelper.createCommonBlockTagKey("titanium_blocks");
			TITANIUM_ORES = TagKeyHelper.createCommonBlockTagKey("titanium_ores");
			TOPAZ_BLOCKS = TagKeyHelper.createCommonBlockTagKey("topaz_blocks");
			TOPAZ_ORES = TagKeyHelper.createCommonBlockTagKey("topaz_ores");
			TRIPLE_COMPRESSED_STONE = TagKeyHelper.createCommonBlockTagKey("triple_compressed_stone");
			TRUESILVER_BLOCKS = TagKeyHelper.createCommonBlockTagKey("truesilver_blocks");
			TRUESILVER_ORES = TagKeyHelper.createCommonBlockTagKey("truesilver_ores");
			TUNGSTEN_BLOCKS = TagKeyHelper.createCommonBlockTagKey("tungsten_blocks");
			TUNGSTEN_ORES = TagKeyHelper.createCommonBlockTagKey("tungsten_ores");
			TUNGSTENSTEEL_BLOCKS = TagKeyHelper.createCommonBlockTagKey("tungstensteel_blocks");
			UNIVITE_BLOCKS = TagKeyHelper.createCommonBlockTagKey("univite_blocks");
			UNOBTAINIUM_BLOCKS = TagKeyHelper.createCommonBlockTagKey("unobtainium_blocks");
			UNOBTAINIUM_ORES = TagKeyHelper.createCommonBlockTagKey("unobtainium_ores");
			URANIUM_BLOCKS = TagKeyHelper.createCommonBlockTagKey("uranium_blocks");
			URANIUM_ORES = TagKeyHelper.createCommonBlockTagKey("uranium_ores");
			UR_BLOCKS = TagKeyHelper.createCommonBlockTagKey("ur_blocks");
			UR_ORES = TagKeyHelper.createCommonBlockTagKey("ur_ores");
			VERMICULITE_ORES = TagKeyHelper.createCommonBlockTagKey("vermiculite_ores");
			WHITE_SAND = TagKeyHelper.createCommonBlockTagKey("white_sand");
			WOODEN_BARRELS = TagKeyHelper.createCommonBlockTagKey("wooden_barrels");
			WOODEN_CHESTS = TagKeyHelper.createCommonBlockTagKey("wooden_chests");
			WORKBENCH = TagKeyHelper.createCommonBlockTagKey("workbench");
			YELLOW_GARNET_BLOCKS = TagKeyHelper.createCommonBlockTagKey("yellow_garnet_blocks");
			YELLOW_SANDSTONES = TagKeyHelper.createCommonBlockTagKey("yellow_sandstones");
			ZINC_BLOCKS = TagKeyHelper.createCommonBlockTagKey("zinc_blocks");
			ZINC_ORE = TagKeyHelper.createCommonBlockTagKey("zinc_ore");
			ZINC_ORES = TagKeyHelper.createCommonBlockTagKey("zinc_ores");

		}
	}

	public static TagKey<Block> STRIPPED_LOGS;
	public static TagKey<Block> STRIPPED_WOOD;
	public static TagKey<Block> ADAMANTITE_BLOCKS;
	public static TagKey<Block> ADAMANTITE_ORES;
	public static TagKey<Block> AETHERIUM_BLOCKS;
	public static TagKey<Block> AETHERIUM_ORES;
	public static TagKey<Block> ALUMINUM_BLOCKS;
	public static TagKey<Block> ALUMINUM_ORES;
	public static TagKey<Block> AMETHYST_BLOCKS;
	public static TagKey<Block> AMETHYST_ORES;
	public static TagKey<Block> ANCIENT_DEBRIS;
	public static TagKey<Block> AQUARIUM_BLOCKS;
	public static TagKey<Block> AQUARIUM_ORES;
	public static TagKey<Block> ARGONIUM_BLOCKS;
	public static TagKey<Block> ASTERITE_BLOCKS;
	public static TagKey<Block> ASTERITE_ORES;
	public static TagKey<Block> ASTEROID_ASTERITE_ORES;
	public static TagKey<Block> ASTEROID_COAL_ORES;
	public static TagKey<Block> ASTEROID_COPPER_ORES;
	public static TagKey<Block> ASTEROID_DIAMOND_ORES;
	public static TagKey<Block> ASTEROID_EMERALD_ORES;
	public static TagKey<Block> ASTEROID_GALAXIUM_ORES;
	public static TagKey<Block> ASTEROID_GOLD_ORES;
	public static TagKey<Block> ASTEROID_IRON_ORES;
	public static TagKey<Block> ASTEROID_LAPIS_ORES;
	public static TagKey<Block> ASTEROID_LEAD_ORES;
	public static TagKey<Block> ASTEROID_METITE_ORES;
	public static TagKey<Block> ASTEROID_REDSTONE_ORES;
	public static TagKey<Block> ASTEROID_SILVER_ORES;
	public static TagKey<Block> ASTEROID_STELLUM_ORES;
	public static TagKey<Block> ASTEROID_TIN_ORES;
	public static TagKey<Block> BANGLUM_BLOCKS;
	public static TagKey<Block> BANGLUM_ORES;
	public static TagKey<Block> BASALT;
	public static TagKey<Block> BAUXITE_ORES;
	public static TagKey<Block> BLACK_SAND;
	public static TagKey<Block> BLUE_SAND;
	public static TagKey<Block> BONE_BLOCKS;
	public static TagKey<Block> BRASS_BLOCKS;
	public static TagKey<Block> BRONZE_BLOCKS;
	public static TagKey<Block> BUSHES;
	public static TagKey<Block> CARMOT_BLOCKS;
	public static TagKey<Block> CARMOT_ORES;
	public static TagKey<Block> CELESTIUM_BLOCKS;
	public static TagKey<Block> CERTUS_QUARTZ_BLOCKS;
	public static TagKey<Block> CERTUS_QUARTZ_ORES;
	public static TagKey<Block> CHROME_BLOCKS;
	public static TagKey<Block> CINNABAR_ORES;
	public static TagKey<Block> COAL_BLOCKS;
	public static TagKey<Block> COAL_COKE_BLOCKS;
	public static TagKey<Block> COAL_GRAVELS;
	public static TagKey<Block> COAL_ORES;
	public static TagKey<Block> COBALT_BLOCKS;
	public static TagKey<Block> COBALT_DUSTS;
	public static TagKey<Block> COBALT_ORES;
	public static TagKey<Block> COBBLESTONE;
	public static TagKey<Block> COMPRESSED_DIRT;
	public static TagKey<Block> COMPRESSED_GLOW_STONE;
	public static TagKey<Block> COMPRESSED_OBSIDIAN;
	public static TagKey<Block> COMPRESSED_STONE;
	public static TagKey<Block> CONCRETE;
	public static TagKey<Block> CONCRETE_POWDER;
	public static TagKey<Block> COPPER_BLOCKS;
	public static TagKey<Block> COPPER_ORE;
	public static TagKey<Block> COPPER_ORES;
	public static TagKey<Block> CROP_LIKE;
	public static TagKey<Block> DIAMOND_BLOCKS;
	public static TagKey<Block> DIAMOND_GRAVELS;
	public static TagKey<Block> DIAMOND_ORES;
	public static TagKey<Block> DIRT;
	public static TagKey<Block> DISCORDIUM_BLOCKS;
	public static TagKey<Block> DOUBLE_COMPRESSED_DIRT;
	public static TagKey<Block> DOUBLE_COMPRESSED_GLOW_STONE;
	public static TagKey<Block> DOUBLE_COMPRESSED_OBSIDIAN;
	public static TagKey<Block> DOUBLE_COMPRESSED_STONE;
	public static TagKey<Block> DURASTEEL_BLOCKS;
	public static TagKey<Block> ELECTRUM_BLOCKS;
	public static TagKey<Block> EMERALD_BLOCKS;
	public static TagKey<Block> EMERALD_GRAVELS;
	public static TagKey<Block> EMERALD_ORES;
	public static TagKey<Block> ETHERITE_BLOCKS;
	public static TagKey<Block> FARMLANDS;
	public static TagKey<Block> FENCE_GATES;
	public static TagKey<Block> FERRITE_BLOCKS;
	public static TagKey<Block> FLOWERS;
	public static TagKey<Block> FOOLS_GOLD_BLOCKS;
	public static TagKey<Block> GALAXIUM_BLOCKS;
	public static TagKey<Block> GALAXIUM_ORES;
	public static TagKey<Block> GALENA_ORES;
	public static TagKey<Block> GLASS;
	public static TagKey<Block> GLASS_BLOCKS;
	public static TagKey<Block> GLOWSTONE_BLOCKS;
	public static TagKey<Block> GOLD_BLOCKS;
	public static TagKey<Block> GOLD_GRAVELS;
	public static TagKey<Block> GOLD_ORES;
	public static TagKey<Block> GRASS;
	public static TagKey<Block> GRASS_LIKE;
	public static TagKey<Block> GRAVEL;
	public static TagKey<Block> INVAR_BLOCKS;
	public static TagKey<Block> IRIDIUM_BLOCKS;
	public static TagKey<Block> IRIDIUM_ORES;
	public static TagKey<Block> IRIDIUM_REINFORCED_STONE_BLOCKS;
	public static TagKey<Block> IRIDIUM_REINFORCED_TUNGSTENSTEEL_BLOCKS;
	public static TagKey<Block> IRON_BLOCKS;
	public static TagKey<Block> IRON_GRAVELS;
	public static TagKey<Block> IRON_ORES;
	public static TagKey<Block> KYBER_BLOCKS;
	public static TagKey<Block> KYBER_ORES;
	public static TagKey<Block> LAPIS_BLOCKS;
	public static TagKey<Block> LAPIS_GRAVELS;
	public static TagKey<Block> LAPIS_ORES;
	public static TagKey<Block> LEAD_BLOCKS;
	public static TagKey<Block> LEAD_ORES;
	public static TagKey<Block> LIMESTONE;
	public static TagKey<Block> LUNUM_BLOCKS;
	public static TagKey<Block> LUNUM_ORES;
	public static TagKey<Block> LUTETIUM_BLOCKS;
	public static TagKey<Block> LUTETIUM_ORES;
	public static TagKey<Block> MANGANESE_BLOCKS;
	public static TagKey<Block> MANGANESE_ORES;
	public static TagKey<Block> MARBLE;
	public static TagKey<Block> METALLURGIUM_BLOCKS;
	public static TagKey<Block> METEORIC_STEEL_BLOCKS;
	public static TagKey<Block> METEOR_METITE_ORES;
	public static TagKey<Block> METITE_BLOCKS;
	public static TagKey<Block> METITE_ORES;
	public static TagKey<Block> MIDAS_GOLD_BLOCKS;
	public static TagKey<Block> MIDAS_GOLD_ORES;
	public static TagKey<Block> MOON_LUNUM_ORES;
	public static TagKey<Block> MUSHROOMS;
	public static TagKey<Block> MYTHRIL_BLOCKS;
	public static TagKey<Block> MYTHRIL_ORES;
	public static TagKey<Block> NATURAL_STONES;
	public static TagKey<Block> NETHERITE_BLOCKS;
	public static TagKey<Block> NETHER_PFRAME;
	public static TagKey<Block> NETHERRACKS;
	public static TagKey<Block> NICKEL_BLOCKS;
	public static TagKey<Block> NICKEL_ORES;
	public static TagKey<Block> ORES_COAL;
	public static TagKey<Block> ORES_DIAMOND;
	public static TagKey<Block> ORES_EMERALD;
	public static TagKey<Block> ORES_GOLD;
	public static TagKey<Block> ORES_IRON;
	public static TagKey<Block> ORES_LAPIS;
	public static TagKey<Block> ORES_REDSTONE;
	public static TagKey<Block> ORICHALCUM_BLOCKS;
	public static TagKey<Block> ORICHALCUM_ORES;
	public static TagKey<Block> OSMIUM_BLOCKS;
	public static TagKey<Block> OSMIUM_ORES;
	public static TagKey<Block> PALLADIUM_BLOCKS;
	public static TagKey<Block> PALLADIUM_ORES;
	public static TagKey<Block> PERIDOT_BLOCKS;
	public static TagKey<Block> PERIDOT_ORES;
	public static TagKey<Block> PINK_SAND;
	public static TagKey<Block> PLANKS_THAT_BURN;
	public static TagKey<Block> PLATINUM_BLOCKS;
	public static TagKey<Block> PLATINUM_ORES;
	public static TagKey<Block> PLUTONIUM_BLOCKS;
	public static TagKey<Block> POLLEN;
	public static TagKey<Block> PREHISTORIC_CALAMITES_LOGS;
	public static TagKey<Block> PREHISTORIC_DARKWOOD_LOGS;
	public static TagKey<Block> PREHISTORIC_LEPIDODENDRALES_LOGS;
	public static TagKey<Block> PREHISTORIC_MANGROVE_LOGS;
	public static TagKey<Block> PROMETHEUM_BLOCKS;
	public static TagKey<Block> PROMETHEUM_ORES;
	public static TagKey<Block> PURPLE_SAND;
	public static TagKey<Block> PURPUR_BLOCKS;
	public static TagKey<Block> PYRITE_BLOCKS;
	public static TagKey<Block> PYRITE_ORES;
	public static TagKey<Block> QUADRILLUM_BLOCKS;
	public static TagKey<Block> QUADRILLUM_ORES;
	public static TagKey<Block> QUARTZ_BLOCKS;
	public static TagKey<Block> QUARTZ_ORES;
	public static TagKey<Block> QUICKSILVER_BLOCKS;
	public static TagKey<Block> RAW_SILVER_BLOCKS;
	public static TagKey<Block> RED_GARNET_BLOCKS;
	public static TagKey<Block> RED_SANDSTONES;
	public static TagKey<Block> REDSTONE_BLOCKS;
	public static TagKey<Block> REDSTONE_GRAVELS;
	public static TagKey<Block> REDSTONE_ORES;
	public static TagKey<Block> REFINED_IRON_BLOCKS;
	public static TagKey<Block> ROSE_GOLD_BLOCKS;
	public static TagKey<Block> RUBY_BLOCKS;
	public static TagKey<Block> RUBY_ORES;
	public static TagKey<Block> RUNITE_BLOCKS;
	public static TagKey<Block> RUNITE_ORES;
	public static TagKey<Block> SALT_BLOCKS;
	public static TagKey<Block> SALT_ORES;
	public static TagKey<Block> SAND;
	public static TagKey<Block> SANDSTONE;
	public static TagKey<Block> SAPPHIRE_BLOCKS;
	public static TagKey<Block> SAPPHIRE_ORES;
	public static TagKey<Block> SHELDONITE_ORES;
	public static TagKey<Block> SILVER_BLOCKS;
	public static TagKey<Block> SILVER_ORES;
	public static TagKey<Block> SLIME_BLOCKS;
	public static TagKey<Block> SLOWSILVER_BLOCKS;
	public static TagKey<Block> SODALITE_ORES;
	public static TagKey<Block> SPHALERITE_ORES;
	public static TagKey<Block> STAINED_GLASS;
	public static TagKey<Block> STARRITE_BLOCKS;
	public static TagKey<Block> STARRITE_ORES;
	public static TagKey<Block> STEEL_BLOCKS;
	public static TagKey<Block> STELLUM_BLOCKS;
	public static TagKey<Block> STELLUM_ORES;
	public static TagKey<Block> STERLING_SILVER_BLOCKS;
	public static TagKey<Block> STONE;
	public static TagKey<Block> STORAGE_BLOCKS;
	public static TagKey<Block> STORMYX_BLOCKS;
	public static TagKey<Block> STORMYX_ORES;
	public static TagKey<Block> STRIP_COMMAND;
	public static TagKey<Block> TANTALITE_BLOCKS;
	public static TagKey<Block> TANTALITE_ORES;
	public static TagKey<Block> TERRACOTTA;
	public static TagKey<Block> THORIUM_BLOCKS;
	public static TagKey<Block> TIN_BLOCKS;
	public static TagKey<Block> TIN_ORES;
	public static TagKey<Block> TITANIUM_BLOCKS;
	public static TagKey<Block> TITANIUM_ORES;
	public static TagKey<Block> TOPAZ_BLOCKS;
	public static TagKey<Block> TOPAZ_ORES;
	public static TagKey<Block> TRIPLE_COMPRESSED_STONE;
	public static TagKey<Block> TRUESILVER_BLOCKS;
	public static TagKey<Block> TRUESILVER_ORES;
	public static TagKey<Block> TUNGSTEN_BLOCKS;
	public static TagKey<Block> TUNGSTEN_ORES;
	public static TagKey<Block> TUNGSTENSTEEL_BLOCKS;
	public static TagKey<Block> UNIVITE_BLOCKS;
	public static TagKey<Block> UNOBTAINIUM_BLOCKS;
	public static TagKey<Block> UNOBTAINIUM_ORES;
	public static TagKey<Block> URANIUM_BLOCKS;
	public static TagKey<Block> URANIUM_ORES;
	public static TagKey<Block> UR_BLOCKS;
	public static TagKey<Block> UR_ORES;
	public static TagKey<Block> VERMICULITE_ORES;
	public static TagKey<Block> WHITE_SAND;
	public static TagKey<Block> WOODEN_BARRELS;
	public static TagKey<Block> WOODEN_CHESTS;
	public static TagKey<Block> WORKBENCH;
	public static TagKey<Block> YELLOW_GARNET_BLOCKS;
	public static TagKey<Block> YELLOW_SANDSTONES;
	public static TagKey<Block> ZINC_BLOCKS;
	public static TagKey<Block> ZINC_ORE;
	public static TagKey<Block> ZINC_ORES;

}
