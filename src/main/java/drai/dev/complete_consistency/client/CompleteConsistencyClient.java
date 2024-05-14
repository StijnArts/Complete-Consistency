package drai.dev.complete_consistency.client;

import drai.dev.complete_consistency.registry.*;
import net.fabricmc.api.*;

public class CompleteConsistencyClient implements ClientModInitializer {
    /**
     * Runs the mod initializer on the client environment.
     */

    @Override
    public void onInitializeClient() {
        BaseBlockEntityRenders.register();
    }
}
