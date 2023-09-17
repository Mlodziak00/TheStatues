package net.lightglow.statueeffects.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.lightglow.statueeffects.EffectStatues;
import net.lightglow.statueeffects.common.block.StatueBlock;
import net.lightglow.statueeffects.common.block.StatueBlockEntity;
import net.lightglow.statueeffects.common.block.StatueTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3f;

import java.util.Objects;

@Environment(value = EnvType.CLIENT)
public class StatueBlockEntityRenderer<T extends BlockEntity> implements BlockEntityRenderer<StatueBlockEntity> {

    public static final Identifier STATUE_TEXTURE = new Identifier(EffectStatues.MOD_ID, "textures/entity/statue_block/statue_block_normal.png");
    public static final Identifier APPLES_TEXTURE = new Identifier(EffectStatues.MOD_ID, "textures/entity/statue_block/statue_block_apples.png");

    private final StatueBlockEntityModel statue;
    private final WingedStatueBlockEntityModel wingedStatue;
    private final ThinkerStatueBlockEntityModel thinkerStatue;

    public StatueBlockEntityRenderer(BlockEntityRendererFactory.Context context){
        this.statue = new StatueBlockEntityModel(context.getLayerModelPart(EffectStatuesClient.STATUE_LAYER));
        this.wingedStatue = new WingedStatueBlockEntityModel(context.getLayerModelPart(EffectStatuesClient.WINGED_STATUE_LAYER));
        this.thinkerStatue = new ThinkerStatueBlockEntityModel(context.getLayerModelPart(EffectStatuesClient.THINKER_STATUE_LAYER));
    }

    @Override
    public void render(StatueBlockEntity be, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumerProvider, int light, int overlay) {
        if (Objects.requireNonNull(be.getCachedState().get(StatueBlock.HALF)) == DoubleBlockHalf.LOWER) {
            matrices.push();
            matrices.translate(0.5F, 1.475F, 0.5F);
            matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(180));
            switch (be.getCachedState().get(StatueBlock.FACING)) {
                case NORTH -> matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(180));
                case EAST -> matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(270));
                case SOUTH -> matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(0));
                case WEST -> matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(90));
            }
            VertexConsumer vertexConsumer;
            if (be.statueTypes == StatueTypes.FLIGHT) {
                if (Objects.equals(be.customTextureName, Text.literal("Apples"))) {
                    vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutoutNoCull(APPLES_TEXTURE));
                    this.wingedStatue.renderWingedStatue(matrices, vertexConsumer, light, overlay, 1.0f, 1.0f, 1.0f, 1.0f);
                } else {
                    vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutoutNoCull(STATUE_TEXTURE));
                   this.wingedStatue.renderWingedStatue(matrices, vertexConsumer, light, overlay, 1.0f, 1.0f, 1.0f, 1.0f);
                }
            } else {
                if (Objects.equals(be.customTextureName, Text.literal("Apples"))) {
                    vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutoutNoCull(APPLES_TEXTURE));
                    this.statue.renderStatue(matrices, vertexConsumer, light, overlay, 1.0f, 1.0f, 1.0f, 1.0f);
                } else if (Objects.equals(be.customTextureName, Text.literal("The Thinker"))) {
                    vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutoutNoCull(STATUE_TEXTURE));
                    this.thinkerStatue.renderThinkerStatue(matrices, vertexConsumer, light, overlay, 1.0f, 1.0f, 1.0f, 1.0f);
                } else {
                    vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutoutNoCull(STATUE_TEXTURE));
                    this.statue.renderStatue(matrices, vertexConsumer, light, overlay, 1.0f, 1.0f, 1.0f, 1.0f);
                }
            }
            matrices.pop();
        }
    }

    @Override
    public boolean rendersOutsideBoundingBox(StatueBlockEntity blockEntity) {
        return BlockEntityRenderer.super.rendersOutsideBoundingBox(blockEntity);
    }
}
