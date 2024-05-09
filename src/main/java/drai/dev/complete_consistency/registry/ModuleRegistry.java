package drai.dev.complete_consistency.registry;

import com.google.common.collect.*;
import drai.dev.complete_consistency.*;
import drai.dev.complete_consistency.materials.*;
import drai.dev.complete_consistency.modules.*;
import drai.dev.complete_consistency.modules.minecraft.*;
import net.minecraft.client.*;
import net.minecraft.resources.*;
import net.minecraft.world.level.block.*;

import java.util.*;

import static drai.dev.complete_consistency.CompleteConsistency.onFinishLoading;

public class ModuleRegistry {
    public static Map<String, MaterialModule> MODULES = new HashMap<>();
    public static List<String> EXPECTED_MODULES = new ArrayList<>();
    public static boolean initialized = false;
    public static void registerModule(MaterialModule materialModule){
        if(!initialized){
            EXPECTED_MODULES.add("minecraft");
            initialized = true;
        }
        MODULES.put(materialModule.namespace, materialModule);
        if(allModulesLoaded()){
            CompleteConsistency.STARTED_GENERATION = true;
            ModuleRegistry.registerModuleFactories();
            MaterialRegistry.runFactories();
            onFinishLoading();
        }
    }

    private static boolean allModulesLoaded() {
        return EXPECTED_MODULES.stream().allMatch(module-> MODULES.containsKey(module));
    }

    public static void registerModuleFactories(){
        MODULES.values().stream().sorted(Comparator.comparing(materialModule -> materialModule.index)).forEach(MaterialModule::registerBlockAndItemTypes);
    }
}
