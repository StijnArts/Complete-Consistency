package drai.dev.complete_consistency.modules;

import drai.dev.complete_consistency.materials.*;
import drai.dev.complete_consistency.registry.*;

import java.util.*;

public abstract class MaterialModule {
    public String namespace;
    public int index = 100;
    protected List<BlockMaterial> materials = new ArrayList<>();

    public MaterialModule(String namespace) {
        this.namespace = namespace;
    }
    public MaterialModule(String namespace, int index) {
        this.namespace = namespace;
        this.index = index;
    }

    public void registerMaterials(){
        for (var material : materials) {
            material.register(namespace);
        }
    }

    public abstract void registerBlockAndItemTypes();
}
