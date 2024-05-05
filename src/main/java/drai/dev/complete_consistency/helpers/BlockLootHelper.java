package drai.dev.complete_consistency.helpers;

import com.google.common.collect.*;
import drai.dev.complete_consistency.datageneration.providers.loottables.*;
import net.minecraft.world.level.block.*;

import java.util.function.*;

public class BlockLootHelper {
	private static LinkedHashMultimap<Block, BiConsumer<BlockLootProvider, Block>> blockLoot = LinkedHashMultimap.create();

	public static LinkedHashMultimap<Block, BiConsumer<BlockLootProvider, Block>> getBlockLoot() {
		return blockLoot;
	}

	public static void addBlockLoot(Block block, BiConsumer<BlockLootProvider, Block> consumer){
		blockLoot.put(block, consumer);
	}
}
