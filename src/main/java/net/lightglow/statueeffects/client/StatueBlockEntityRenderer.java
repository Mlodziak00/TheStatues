package net.lightglow.statueeffects.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.lightglow.statueeffects.EffectStatues;
import net.lightglow.statueeffects.common.block.StatueBlock;
import net.lightglow.statueeffects.common.block.StatueBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3f;

import java.util.Objects;

@Environment(value = EnvType.CLIENT)
public class StatueBlockEntityRenderer<T extends BlockEntity> implements BlockEntityRenderer<StatueBlockEntity> {


    public static final Identifier STATUE_TEXTURE = new Identifier(EffectStatues.MOD_ID, "textures/entity/statue_block/statue_block_normal.png");
    public static final Identifier APPLES_TEXTURE = new Identifier(EffectStatues.MOD_ID, "textures/entity/statue_block/statue_block_apples.png");
    public static final Identifier M_TEXTURE = new Identifier(EffectStatues.MOD_ID, "textures/entity/statue_block/statue_block_m.png");
    public static final Identifier BLAHAJ_TEXTURE = new Identifier(EffectStatues.MOD_ID, "textures/entity/statue_block/statue_block_blahaj.png");

    private final StatueBlockEntityModel statue;
    private final WingedStatueBlockEntityModel wingedStatue;
    private final RegenStatueBlockEntityModel regenStatue;
    private final DefincesStatueBlockEntityModel defincesStatue;
    private final StrengthStatueBlockEntityModel strengthStatue;
    private final ShortsightStatueBlockEntityModel shortSightStatue;
    private final ThinkerStatueBlockEntityModel thinkerStatue;
    private final BlahajStatueBlockEntityModel blahajStatue;

    public StatueBlockEntityRenderer(BlockEntityRendererFactory.Context context){
        this.statue = new StatueBlockEntityModel(context.getLayerModelPart(EffectStatuesClient.STATUE_LAYER));
        this.wingedStatue = new WingedStatueBlockEntityModel(context.getLayerModelPart(EffectStatuesClient.WINGED_STATUE_LAYER));
        this.regenStatue = new RegenStatueBlockEntityModel(context.getLayerModelPart(EffectStatuesClient.REGEN_STATUE_LAYER));
        this.defincesStatue = new DefincesStatueBlockEntityModel(context.getLayerModelPart(EffectStatuesClient.DEFINCES_STATUE_LAYER));
        this.strengthStatue = new StrengthStatueBlockEntityModel(context.getLayerModelPart(EffectStatuesClient.STRENGTH_STATUE_LAYER));
        this.shortSightStatue = new ShortsightStatueBlockEntityModel(context.getLayerModelPart(EffectStatuesClient.SHORTSIGHT_STATUE_LAYER));
        this.thinkerStatue = new ThinkerStatueBlockEntityModel(context.getLayerModelPart(EffectStatuesClient.THINKER_STATUE_LAYER));
        this.blahajStatue = new BlahajStatueBlockEntityModel(context.getLayerModelPart(EffectStatuesClient.BLAHAJ_STATUE_LAYER));
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
            if (Objects.equals(be.customTextureName, Text.literal("Apples"))) {
                vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutoutNoCull(APPLES_TEXTURE));
            } else if (Objects.equals(be.customTextureName, Text.literal("Mlodziak00"))) {
                vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutoutNoCull(M_TEXTURE));
            } else {
                vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutoutNoCull(STATUE_TEXTURE));
            }
            if (be.statueTypes == 1) {
                this.wingedStatue.renderWingedStatue(matrices, vertexConsumer, light, overlay, 1.0f, 1.0f, 1.0f, 1.0f);
            } else if (be.statueTypes == 2) {
                this.regenStatue.renderRegenStatue(matrices, vertexConsumer, light, overlay, 1.0f, 1.0f, 1.0f, 1.0f);
            } else if (be.statueTypes == 3) {
                this.defincesStatue.renderDefincesStatue(matrices, vertexConsumer, light, overlay, 1.0f, 1.0f, 1.0f, 1.0f);
            } else if (be.statueTypes == 4) {
                this.strengthStatue.renderStrengthStatue(matrices, vertexConsumer, light, overlay, 1.0f, 1.0f, 1.0f, 1.0f);
            } else if (be.statueTypes == 5) {
                this.shortSightStatue.renderShortSightStatue(matrices, vertexConsumer, light, overlay, 1.0f, 1.0f, 1.0f, 1.0f);
            } else {
                if (Objects.equals(be.customTextureName, Text.literal("The Thinker"))) {
                    vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutoutNoCull(STATUE_TEXTURE));
                    this.thinkerStatue.renderThinkerStatue(matrices, vertexConsumer, light, overlay, 1.0f, 1.0f, 1.0f, 1.0f);
                } else if (Objects.equals(be.customTextureName, Text.literal("Blahaj"))) {
                    vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityCutoutNoCull(BLAHAJ_TEXTURE));
                    this.blahajStatue.renderBlahajStatue(matrices, vertexConsumer, light, overlay, 1.0f, 1.0f, 1.0f, 1.0f);
                } else {
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
