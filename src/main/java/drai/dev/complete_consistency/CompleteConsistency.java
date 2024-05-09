package drai.dev.complete_consistency;

import drai.dev.complete_consistency.helpers.*;
import drai.dev.complete_consistency.modules.minecraft.*;
import drai.dev.complete_consistency.registry.*;
import drai.dev.complete_consistency.tag.*;
import net.fabricmc.api.*;
import net.fabricmc.fabric.api.itemgroup.v1.*;
import net.fabricmc.loader.api.*;
import net.minecraft.world.item.*;

import javax.xml.transform.*;
import java.util.logging.*;

public class CompleteConsistency implements ModInitializer {
    public static boolean STARTED_GENERATION = false;
    /**
     * Runs the mod initializer.
     */
    public static String MOD_ID = "complete_consistency";
    public static final Logger LOGGER = Logger.getLogger(MOD_ID);

    public static boolean isClient() {
        return FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT;
    }

    public static boolean isServer() {
        return FabricLoader.getInstance().getEnvironmentType() == EnvType.SERVER;
    }


    public static boolean isDatagen() {
        return System.getProperty("fabric-api.datagen") != null;
    }


    @Override
    public void onInitialize() {
        BlockReplacements.replaceBlocks();
        UpgradedVanillaTags.register();
        TagManager.ensureStaticallyLoaded();
        MinecraftModule.register();
    }

    public static void onFinishLoading(){
        ItemGroupHelper.registerSelf();
    }
}
