package drai.dev.complete_consistency.entities;

import drai.dev.complete_consistency.entities.interfaces.*;
import drai.dev.complete_consistency.items.boat.*;
import drai.dev.complete_consistency.materials.impl.*;
import drai.dev.complete_consistency.registry.*;
import net.minecraft.client.resources.model.*;
import net.minecraft.commands.synchronization.brigadier.*;
import net.minecraft.core.*;
import net.minecraft.nbt.*;
import net.minecraft.network.syncher.*;
import net.minecraft.resources.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.decoration.*;
import net.minecraft.world.level.*;
import org.jetbrains.annotations.*;

public class CCGlowItemFrameEntity extends GlowItemFrame implements CustomItemFrameEntity {

    public CCGlowItemFrameEntity(EntityType<? extends ItemFrame> entityType, Level level) {
        super(entityType, level);
    }
    @Nullable
    private WoodMaterial material;
    private CCGlowItemFrameEntity(Level level, BlockPos pos, Direction facingDirection, WoodMaterial material) {
        super(level, pos, facingDirection);
        this.material = material;
    }

    public static CCGlowItemFrameEntity create(Level level, BlockPos blockPos2, Direction direction, WoodMaterial material) {
        return new CCGlowItemFrameEntity(level, blockPos2, direction, material);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putString("Material", material.getNamespace()+":"+material);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("Material")) {
            this.material = MaterialRegistry.WOOD_MATERIALS.get(new ResourceLocation(compound.getString("Material"))).stream().findFirst().get();
        }
    }

    @Override
    public ModelResourceLocation getMapFrameLocation() {
        if (material == null) return null;
        return new ModelResourceLocation(material.getNamespace(), material+"_item_frame", "map=true");
    }

    @Override
    public ModelResourceLocation getFrameLocation() {
        if (material == null) return null;
        return new ModelResourceLocation(material.getNamespace(), material+"_item_frame", "map=false");
    }
}