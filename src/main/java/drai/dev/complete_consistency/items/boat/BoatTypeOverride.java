package drai.dev.complete_consistency.items.boat;

import drai.dev.complete_consistency.*;
import drai.dev.complete_consistency.model.*;
import net.minecraft.client.model.*;
import net.minecraft.client.model.geom.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.resources.*;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;

import java.util.*;
import java.util.stream.*;

public class BoatTypeOverride {
    private static final String DEFAULT_LAYER = "main";
    private static final List<BoatTypeOverride> values = new ArrayList<>(8);
    private final String name;
    private final Block planks;
    private final int ordinal;
    public final ResourceLocation id;
    public final ResourceLocation boatTexture;
    public final ResourceLocation chestBoatTexture;
    public final ModelLayerLocation boatModelName;
    public final ModelLayerLocation chestBoatModelName;
    public boolean isRaft = false;
    private BoatModelHolder boatModelHolder;
    private BoatItem boat;
    private BoatItem chestBoat;

    BoatTypeOverride(String modID, String name, Block planks) {
        this.id = new ResourceLocation(modID, name);
        this.name = name;
        this.planks = planks;
        int nr = Objects.hash(new Object[]{name});
        if (nr >= 0 && nr <= 1000) {
            nr += 1000;
        }

        while(byId(nr) != null) {
            CompleteConsistency.LOGGER.warning("Boat Type Ordinal " + nr + " is already used, searching for another one");
            ++nr;
            if (nr >= 0 && nr <= 1000) {
                nr += 1000;
            }
        }

        this.ordinal = nr;
        if (CompleteConsistency.isClient()) {
            this.boatModelName = createBoatModelName(this.id.getNamespace(), this.id.getPath());
            this.chestBoatModelName = createChestBoatModelName(this.id.getNamespace(), this.id.getPath());
            this.boatTexture = getTextureLocation(modID, name, false);
            this.chestBoatTexture = getTextureLocation(modID, name, true);
        } else {
            this.boatModelName = null;
            this.chestBoatModelName = null;
            this.boatTexture = null;
            this.chestBoatTexture = null;
        }

        values.add(this);
    }

    public BoatModel getBoatModel(boolean chest) {
        return boatModelHolder.getBoatModel(chest);
    }

    public void createBoatModels(EntityRendererProvider.Context context) {
        if(this.boatModelHolder == null) {
            this.boatModelHolder = new BoatModelHolder(context, this.boatModelName, this.chestBoatModelName);
        }

    }

    public Block getPlanks() {
        return this.planks;
    }

    public void setBoatItem(BoatItem item) {
        this.boat = item;
    }

    public BoatItem getBoatItem() {
        return this.boat;
    }

    public void setChestBoatItem(BoatItem item) {
        this.chestBoat = item;
    }

    public BoatItem getChestBoatItem() {
        return this.chestBoat;
    }

    public static Stream<BoatTypeOverride> values() {
        return values.stream();
    }

    private static ModelLayerLocation createBoatModelName(String modID, String name) {
        return new ModelLayerLocation(new ResourceLocation(modID, "boat/" + name), "main");
    }

    private static ModelLayerLocation createChestBoatModelName(String modID, String name) {
        return new ModelLayerLocation(new ResourceLocation(modID, "chest_boat/" + name), "main");
    }

    private static ResourceLocation getTextureLocation(String modID, String name, boolean chest) {
        return chest ? new ResourceLocation(modID, "textures/entity/chest_boat/" + name + ".png") : new ResourceLocation(modID, "textures/entity/boat/" + name + ".png");
    }

    public static BoatTypeOverride create(String modID, String name, Block planks) {
        BoatTypeOverride t = new BoatTypeOverride(modID, name, planks);
        return t;
    }

    public BoatItem createItem(boolean hasChest) {
        return this.createItem(hasChest, (new Item.Properties()).stacksTo(1));
    }

    public BoatItem createItem(boolean hasChest, Item.Properties itemSettings) {
        BoatItem item = new BaseBoatItem(hasChest, this, itemSettings);
        if (hasChest) {
            this.setChestBoatItem(item);
        } else {
            this.setBoatItem(item);
        }

        return item;
    }

    public static BoatTypeOverride byId(int i) {
        Iterator<BoatTypeOverride> var1 = values.iterator();

        BoatTypeOverride t;
        do {
            if (!var1.hasNext()) {
                return null;
            }

            t = var1.next();
        } while(t.ordinal != i);

        return t;
    }

    public static BoatTypeOverride byName(String string) {
        Iterator var1 = values.iterator();

        BoatTypeOverride t;
        do {
            if (!var1.hasNext()) {
                return null;
            }

            t = (BoatTypeOverride)var1.next();
        } while(!t.name().equals(string));

        return t;
    }

    public String name() {
        return this.name;
    }

    public int ordinal() {
        return this.ordinal;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj != null && obj.getClass() == this.getClass()) {
            BoatTypeOverride that = (BoatTypeOverride)obj;
            return Objects.equals(this.name, that.name) && this.ordinal == that.ordinal;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.name, this.ordinal});
    }

    public String toString() {
        return "BoatTypeOverride[name=" + this.name + ", ordinal=" + this.ordinal + "]";
    }
}
