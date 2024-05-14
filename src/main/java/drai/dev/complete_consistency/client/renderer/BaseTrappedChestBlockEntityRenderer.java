package drai.dev.complete_consistency.client.renderer;

import com.google.common.collect.*;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.*;
import drai.dev.complete_consistency.blockentities.*;
import drai.dev.complete_consistency.model.*;
import it.unimi.dsi.fastutil.ints.*;
import net.fabricmc.api.*;
import net.minecraft.client.model.geom.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.blockentity.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.*;
import net.minecraft.resources.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.DoubleBlockCombiner.*;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.*;
import net.minecraft.world.level.block.state.properties.*;

import java.util.*;

@Environment(EnvType.CLIENT)
public class BaseTrappedChestBlockEntityRenderer implements BlockEntityRenderer<BaseTrappedChestBlockEntity> {
    private static final HashMap<Block, RenderType[]> LAYERS = Maps.newHashMap();
    private static final RenderType[] RENDER_TYPES;

    private static final int ID_NORMAL = 0;
    private static final int ID_LEFT = 1;
    private static final int ID_RIGHT = 2;

    private final BaseChestBlockModel chestModel;

    public BaseTrappedChestBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {
        super();
        chestModel = new BaseChestBlockModel(BaseChestBlockModel.getTexturedModelData().bakeRoot());
    }

    public void render(
            BaseTrappedChestBlockEntity entity,
            float tickDelta,
            PoseStack matrices,
            MultiBufferSource vertexConsumers,
            int light,
            int overlay
    ) {
        Level world = entity.getLevel();
        boolean worldExists = world != null;
        BlockState blockState = worldExists ? entity.getBlockState() : Blocks.CHEST.defaultBlockState()
                                                                                   .setValue(
                                                                                           ChestBlock.FACING,
                                                                                           Direction.SOUTH
                                                                                   );
        ChestType chestType = blockState.hasProperty(ChestBlock.TYPE)
                ? blockState.getValue(ChestBlock.TYPE)
                : ChestType.SINGLE;
        Block block = blockState.getBlock();
        if (block instanceof AbstractChestBlock) {
            AbstractChestBlock<?> abstractChestBlock = (AbstractChestBlock<?>) block;
            boolean isDouble = chestType != ChestType.SINGLE;
            float f = blockState.getValue(ChestBlock.FACING).toYRot();
            NeighborCombineResult<? extends ChestBlockEntity> propertySource;

            matrices.pushPose();
            matrices.translate(0.5D, 0.5D, 0.5D);
            matrices.mulPose(Axis.YP.rotationDegrees(-f));
            matrices.translate(-0.5D, -0.5D, -0.5D);

            if (worldExists) {
                propertySource = abstractChestBlock.combine(blockState, world, entity.getBlockPos(), true);
            } else {
                propertySource = Combiner::acceptNone;
            }

            float pitch = propertySource.apply(ChestBlock.opennessCombiner(entity)).get(
                    tickDelta);
            pitch = 1.0F - pitch;
            pitch = 1.0F - pitch * pitch * pitch;
            @SuppressWarnings({
                    "unchecked",
                    "rawtypes"
            }) int blockLight = ((Int2IntFunction) propertySource.apply(new BrightnessCombiner())).applyAsInt(light);

            VertexConsumer vertexConsumer = getConsumer(vertexConsumers, block, chestType);

            if (isDouble) {
                if (chestType == ChestType.LEFT) {
                    renderParts(
                            matrices,
                            vertexConsumer,
                            chestModel.partLeftA,
                            chestModel.partLeftB,
                            chestModel.partLeftC,
                            pitch,
                            blockLight,
                            overlay
                    );
                } else {
                    renderParts(
                            matrices,
                            vertexConsumer,
                            chestModel.partRightA,
                            chestModel.partRightB,
                            chestModel.partRightC,
                            pitch,
                            blockLight,
                            overlay
                    );
                }
            } else {
                renderParts(
                        matrices,
                        vertexConsumer,
                        chestModel.partA,
                        chestModel.partB,
                        chestModel.partC,
                        pitch,
                        blockLight,
                        overlay
                );
            }

            matrices.popPose();
        }
    }

    private void renderParts(
            PoseStack matrices,
            VertexConsumer vertices,
            ModelPart modelPart,
            ModelPart modelPart2,
            ModelPart modelPart3,
            float pitch,
            int light,
            int overlay
    ) {
        modelPart.xRot = -(pitch * 1.5707964F);
        modelPart2.xRot = modelPart.xRot;
        modelPart.render(matrices, vertices, light, overlay);
        modelPart2.render(matrices, vertices, light, overlay);
        modelPart3.render(matrices, vertices, light, overlay);
    }

    private static RenderType getChestTexture(ChestType type, RenderType[] layers) {
        return switch (type) {
            case LEFT -> layers[ID_LEFT];
            case RIGHT -> layers[ID_RIGHT];
            default -> layers[ID_NORMAL];
        };
    }

    public static VertexConsumer getConsumer(MultiBufferSource provider, Block block, ChestType chestType) {
        RenderType[] layers = LAYERS.getOrDefault(block, RENDER_TYPES);
        return provider.getBuffer(getChestTexture(chestType, layers));
    }

    public static void registerRenderLayer(Block block) {
        ResourceLocation blockId = BuiltInRegistries.BLOCK.getKey(block);
        String modId = blockId.getNamespace();
        String path = blockId.getPath();
        LAYERS.put(
                block,
                new RenderType[]{
                        RenderType.entityCutout(new ResourceLocation(modId, "textures/block/trapped_chest/" + path + ".png")),
                        RenderType.entityCutout(new ResourceLocation(
                                modId,
                                "textures/block/trapped_chest/" + path + "_left.png"
                        )),
                        RenderType.entityCutout(new ResourceLocation(
                                modId,
                                "textures/block/trapped_chest/" + path + "_right.png"
                        ))
                }
        );
    }

    static {
        RENDER_TYPES = new RenderType[]{
                RenderType.entityCutout(new ResourceLocation("textures/entity/trapped_chest/normal.png")),
                RenderType.entityCutout(new ResourceLocation("textures/entity/trapped_chest/normal_left.png")),
                RenderType.entityCutout(new ResourceLocation("textures/entity/trapped_chest/normal_right.png"))
        };
    }
}
