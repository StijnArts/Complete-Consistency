package drai.dev.complete_consistency.items;

import drai.dev.complete_consistency.entities.*;
import drai.dev.complete_consistency.materials.impl.*;
import drai.dev.complete_consistency.registry.*;
import net.minecraft.core.*;
import net.minecraft.core.component.*;
import net.minecraft.world.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.decoration.*;
import net.minecraft.world.entity.player.*;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.*;
import net.minecraft.world.item.context.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.gameevent.*;

import java.util.*;

public class CCItemFrameItem extends ItemFrameItem {
    private final EntityType<? extends HangingEntity> type;
    private final WoodMaterial material;

    public CCItemFrameItem(EntityType<? extends HangingEntity> type, Properties properties, WoodMaterial material) {
        super(type, properties);
        this.type = type;
        this.material = material;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if(type == BaseBlockEntities.ITEM_FRAME || type == BaseBlockEntities.GLOW_ITEM_FRAME){
            BlockPos blockPos = context.getClickedPos();
            Direction direction = context.getClickedFace();
            BlockPos blockPos2 = blockPos.relative(direction);
            Player player = context.getPlayer();
            ItemStack itemStack = context.getItemInHand();
            if (player != null && !this.mayPlace(player, direction, itemStack, blockPos2)) {
                return InteractionResult.FAIL;
            } else {
                Level level = context.getLevel();
                HangingEntity hangingEntity;
                if (this.type == BaseBlockEntities.ITEM_FRAME) {
                    hangingEntity = CCItemFrameEntity.create(level, blockPos2, direction, material);
                } else {
                    hangingEntity = CCGlowItemFrameEntity.create(level, blockPos2, direction, material);
                }

                CustomData customData = (CustomData)itemStack.getOrDefault(DataComponents.ENTITY_DATA, CustomData.EMPTY);
                if (!customData.isEmpty()) {
                    EntityType.updateCustomEntityTag(level, player, hangingEntity, customData);
                }

                if (hangingEntity.survives()) {
                    if (!level.isClientSide) {
                        hangingEntity.playPlacementSound();
                        level.gameEvent(player, GameEvent.ENTITY_PLACE, hangingEntity.position());
                        level.addFreshEntity(hangingEntity);
                    }

                    itemStack.shrink(1);
                    return InteractionResult.sidedSuccess(level.isClientSide);
                } else {
                    return InteractionResult.CONSUME;
                }
            }
        } else{
            return super.useOn(context);
        }
    }
}
