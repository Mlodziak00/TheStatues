package net.lightglow.statueeffects.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;

public class BlahajStatueBlockEntityModel extends Model {
    private final ModelPart root;
    private final ModelPart blahaj;
    private final ModelPart blahaj_r1;
    private final ModelPart blahaj_r2;
    private final ModelPart blahaj_r3;
    private final ModelPart blahaj_r4;
    private final ModelPart blahaj_r5;
    private final ModelPart blahaj_r6;
    private final ModelPart blahaj_r7;
    private final ModelPart blahaj_r8;
    public BlahajStatueBlockEntityModel(ModelPart root) {
        super(RenderLayer::getEntitySolid);
        this.root = root;
        this.blahaj = root.getChild("blahaj");
        this.blahaj_r1 = root.getChild("blahaj_r1");
        this.blahaj_r2 = root.getChild("blahaj_r2");
        this.blahaj_r3 = root.getChild("blahaj_r3");
        this.blahaj_r4 = root.getChild("blahaj_r4");
        this.blahaj_r5 = root.getChild("blahaj_r5");
        this.blahaj_r6 = root.getChild("blahaj_r6");
        this.blahaj_r7 = root.getChild("blahaj_r7");
        this.blahaj_r8 = root.getChild("blahaj_r8");
    }

    public static TexturedModelData getBlahajTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        modelPartData.addChild("blahaj", ModelPartBuilder.create().uv(86, 4).cuboid(-2.0F, -4.0F, -1.0F, 4.0F, 4.0F, 12.0F, new Dilation(0.0F))
                .uv(118, 16).cuboid(-2.0F, -4.0F, -2.0F, 4.0F, 3.0F, 1.0F, new Dilation(0.0F))
                .uv(90, 42).cuboid(-3.0F, 0.0F, 0.5F, 6.0F, 2.0F, 13.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 16.0F, -7.5F));

        modelPartData.addChild("blahaj_r1", ModelPartBuilder.create().uv(100, 20).mirrored().cuboid(0.0F, 0.0F, -1.0F, 3.0F, 1.0F, 2.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(2.0F, 14.0F, -1.0F, 0.0F, 0.0F, 0.5236F));

        modelPartData.addChild("blahaj_r2", ModelPartBuilder.create().uv(100, 20).cuboid(-3.0F, 0.0F, -1.0F, 3.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, 14.0F, -1.0F, 0.0F, 0.0F, -0.5236F));

        modelPartData.addChild("blahaj_r3", ModelPartBuilder.create().uv(122, 21).cuboid(-0.5F, -3.0F, -1.0F, 1.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 14.0F, 1.75F, -0.1745F, 0.0F, 0.0F));

        modelPartData.addChild("blahaj_r4", ModelPartBuilder.create().uv(110, 20).cuboid(-0.5F, -4.0F, -1.0F, 1.0F, 4.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 14.0F, -3.0F, -0.1745F, 0.0F, 0.0F));

        modelPartData.addChild("blahaj_r5", ModelPartBuilder.create().uv(120, 38).cuboid(-1.0F, -0.0978F, -0.1113F, 2.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 15.25F, 8.225F, 0.2182F, 0.0F, 0.0F));

        modelPartData.addChild("blahaj_r6", ModelPartBuilder.create().uv(120, 33).cuboid(-1.0F, -3.0978F, -0.1113F, 2.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 13.25F, 8.1F, -0.1309F, 0.0F, 0.0F));

        modelPartData.addChild("blahaj_r7", ModelPartBuilder.create().uv(116, 26).cuboid(-1.0F, -3.5072F, 0.0395F, 2.0F, 3.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 16.25F, 6.1F, 0.0436F, 0.0F, 0.0F));

        modelPartData.addChild("blahaj_r8", ModelPartBuilder.create().uv(118, 20).cuboid(-1.0F, 0.5F, 0.0F, 2.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 12.0F, 3.5F, -0.0873F, 0.0F, 0.0F));
        return TexturedModelData.of(modelData, 128, 128);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        this.renderBlahajStatue(matrices, vertices, light, overlay, red, green, blue, alpha);

    }

    public void renderBlahajStatue(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        this.root.render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }
}
