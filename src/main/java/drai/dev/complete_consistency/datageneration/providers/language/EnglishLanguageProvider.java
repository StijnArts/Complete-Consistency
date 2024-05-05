package drai.dev.complete_consistency.datageneration.providers.language;

import com.mojang.datafixers.util.*;
import drai.dev.complete_consistency.helpers.*;
import net.fabricmc.fabric.api.datagen.v1.*;
import net.fabricmc.fabric.api.datagen.v1.provider.*;
import net.minecraft.core.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;

import java.util.concurrent.*;

public class EnglishLanguageProvider extends FabricLanguageProvider {

	public EnglishLanguageProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
		super(dataOutput, registryLookup);
	}

	@Override
	public void generateTranslations(HolderLookup.Provider registryLookup, TranslationBuilder translationBuilder) {
		LanguageHelper.getEnglishTranslations().getBlockTranslations().forEach((Pair<Block,String> pair)->translationBuilder.add(pair.getFirst(),pair.getSecond()));
		LanguageHelper.getEnglishTranslations().getItemTranslations().forEach((Pair<Item,String> pair)->translationBuilder.add(pair.getFirst(),pair.getSecond()));
	}
}
