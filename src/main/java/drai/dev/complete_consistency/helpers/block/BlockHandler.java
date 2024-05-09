package drai.dev.complete_consistency.helpers.block;

import drai.dev.complete_consistency.*;
import drai.dev.complete_consistency.datageneration.providers.loottables.*;
import drai.dev.complete_consistency.helpers.*;
import net.fabricmc.fabric.api.itemgroup.v1.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.*;
import net.minecraft.data.models.*;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.*;
import net.minecraft.tags.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;

import java.util.*;
import java.util.function.*;
import java.util.logging.Level;

public class BlockHandler {


	public static Block registerBlockWithoutItem(String name, String modId, Block block,
									  BiConsumer<BlockModelGenerators, ItemLike> blockModelGeneratorsConsumer,
									  BiConsumer<BlockLootProvider, Block> lootTableConsumer,
									  List<TagKey<Block>> blockTags){
		Block returnBlock = Registry.register(BuiltInRegistries.BLOCK, IDHelper.createID(name,modId), block);
		System.out.println("Registered block "+modId+":"+name);
		BlockStateHelper.addBlockModel(returnBlock, blockModelGeneratorsConsumer);
		BlockLootHelper.addBlockLoot(returnBlock,lootTableConsumer);
		TagHelper.addBlockTags(returnBlock,blockTags);
		return returnBlock;
	}
	public static Block registerBlockWithRecipe(String name, String translation, String modId, Block block,
												Consumer<ItemLike> itemGroupEntriesConsumer,
												BiConsumer<BlockModelGenerators, ItemLike> blockModelGeneratorsConsumer,
												BiConsumer<RecipeOutput, ItemLike> recipeConsumer,
												BiConsumer<BlockLootProvider, Block> lootTableConsumer,
												List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags){
		Item returnedItem = registerBlockItem(name, modId, block, itemGroupEntriesConsumer, itemTags);
		Block returnBlock = Registry.register(BuiltInRegistries.BLOCK, IDHelper.createID(name,modId),block);
		System.out.println("Registered block "+modId+":"+name);
		LanguageHelper.getEnglishTranslations().addTranslation(returnBlock,translation);
		BlockStateHelper.addBlockModel(returnBlock, blockModelGeneratorsConsumer);
		RecipeHelper.addRecipe(returnedItem,recipeConsumer);
		BlockLootHelper.addBlockLoot(returnBlock,lootTableConsumer);
		TagHelper.addBlockTags(returnBlock,blockTags);

		return returnBlock;
	}

	public static Block registerNonStackableBlockWithRecipe(String name, String translation, String modId, Block block,
															Consumer<ItemLike> itemGroupEntriesConsumer,
												BiConsumer<BlockModelGenerators, ItemLike> blockModelGeneratorsConsumer,
												BiConsumer<RecipeOutput, ItemLike> recipeConsumer,
												BiConsumer<BlockLootProvider, Block> lootTableConsumer,
												List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags){
		Item returnedItem = registerNonStackableBlockItem(name, modId, block, itemGroupEntriesConsumer, itemTags);
		Block returnBlock = Registry.register(BuiltInRegistries.BLOCK, IDHelper.createID(name,modId),block);
		System.out.println("Registered block "+modId+":"+name);
		LanguageHelper.getEnglishTranslations().addTranslation(returnBlock,translation);
		BlockStateHelper.addBlockModel(returnBlock, blockModelGeneratorsConsumer);
		RecipeHelper.addRecipe(returnedItem,recipeConsumer);
		BlockLootHelper.addBlockLoot(returnBlock,lootTableConsumer);
		TagHelper.addBlockTags(returnBlock,blockTags);

		return returnBlock;
	}
	public static Block registerBlockWithRecipe(String name, String translation, String modId, Block block,
												Consumer<ItemLike> itemGroupEntriesConsumer,
									  	BiConsumer<BlockModelGenerators, ItemLike> blockModelGeneratorsConsumer,
									  	BiConsumer<ItemModelGenerators, Item> itemModelGeneratorsConsumer,
										BiConsumer<RecipeOutput, ItemLike> recipeConsumer,
												BiConsumer<BlockLootProvider, Block> lootTableConsumer,
									  	List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags){
		Item returnedItem = registerBlockItem(name, modId, block, itemGroupEntriesConsumer, itemTags);
		Block returnBlock = Registry.register(BuiltInRegistries.BLOCK, IDHelper.createID(name,modId),block);
		System.out.println("Registered block "+modId+":"+name);
		LanguageHelper.getEnglishTranslations().addTranslation(returnBlock,translation);
		BlockStateHelper.addBlockModel(returnBlock, blockModelGeneratorsConsumer);
		RecipeHelper.addRecipe(returnedItem,recipeConsumer);
		ItemModelHelper.addItemModel(returnedItem,itemModelGeneratorsConsumer);
		BlockLootHelper.addBlockLoot(returnBlock,lootTableConsumer);
		TagHelper.addBlockTags(returnBlock,blockTags);
		return returnBlock;
	}

	public static Block registerBlock(String name, String translation, String modId, Block block,
									  Consumer<ItemLike> itemGroupEntriesConsumer,
									  BiConsumer<BlockModelGenerators, ItemLike> blockModelGeneratorsConsumer,
									  BiConsumer<BlockLootProvider, Block> lootTableConsumer,
									  List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags){
		Item returnedItem = registerBlockItem(name, modId, block, itemGroupEntriesConsumer, itemTags);
		Block returnBlock = Registry.register(BuiltInRegistries.BLOCK, IDHelper.createID(name,modId),block);
		System.out.println("Registered block "+modId+":"+name);
		LanguageHelper.getEnglishTranslations().addTranslation(returnBlock,translation);
		BlockStateHelper.addBlockModel(returnBlock, blockModelGeneratorsConsumer);
		BlockLootHelper.addBlockLoot(returnBlock,lootTableConsumer);
		TagHelper.addBlockTags(returnBlock,blockTags);
		return returnBlock;
	}

	public static Block registerBlock(String name, String translation, String modId, Block block,
									  Consumer<ItemLike> itemGroupEntriesConsumer,
									  BiConsumer<BlockModelGenerators, ItemLike> blockModelGeneratorsConsumer,
									  BiConsumer<ItemModelGenerators, Item> itemModelGeneratorsConsumer,
									  BiConsumer<BlockLootProvider, Block> lootTableConsumer,
									  List<TagKey<Block>> blockTags, List<TagKey<Item>> itemTags){
		Item returnedItem = registerBlockItem(name, modId, block, itemGroupEntriesConsumer, itemTags);
		Block returnBlock = Registry.register(BuiltInRegistries.BLOCK, IDHelper.createID(name,modId),block);
		System.out.println("Registered block "+modId+":"+name);
		LanguageHelper.getEnglishTranslations().addTranslation(returnBlock,translation);
		BlockStateHelper.addBlockModel(returnBlock, blockModelGeneratorsConsumer);
		ItemModelHelper.addItemModel(returnedItem,itemModelGeneratorsConsumer);
		BlockLootHelper.addBlockLoot(returnBlock,lootTableConsumer);
		TagHelper.addBlockTags(returnBlock,blockTags);
		return returnBlock;
	}

	public static Item registerBlockItem(String name, String modId, Block block,
									  Consumer<ItemLike> itemGroupEntriesConsumer, List<TagKey<Item>> itemTags){
		BlockItem returnItem = Registry.register(BuiltInRegistries.ITEM,IDHelper.createID(name,modId),new BlockItem(block, new Item.Properties()));
		System.out.println("Registered item "+modId+":"+name);
		returnItem.registerBlocks(Item.BY_BLOCK, returnItem);
		TagHelper.addItemTags(returnItem, itemTags);
		itemGroupEntriesConsumer.accept(returnItem);
		return returnItem;
	}

	public static Item registerNonStackableBlockItem(String name, String modId, Block block,
									  Consumer<ItemLike> itemGroupEntriesConsumer, List<TagKey<Item>> itemTags){
		BlockItem returnItem = Registry.register(BuiltInRegistries.ITEM,IDHelper.createID(name,modId),new BlockItem(block, new Item.Properties().stacksTo(1)));
		System.out.println("Registered item "+modId+":"+name);
		returnItem.registerBlocks(Item.BY_BLOCK, returnItem);
		TagHelper.addItemTags(returnItem, itemTags);
		itemGroupEntriesConsumer.accept(returnItem);
		return returnItem;
	}
}
