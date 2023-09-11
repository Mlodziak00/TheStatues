package net.lightglow.statueeffects.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;

public class WingedStatueBlockEntityModel extends Model {
    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart right_leg;
    private final ModelPart left_leg;
    private final ModelPart head;
    private final ModelPart right_arm;
    private final ModelPart left_arm;
    private final ModelPart wingr;
    private final ModelPart wingl;
    public WingedStatueBlockEntityModel(ModelPart root) {
        super(RenderLayer::getEntitySolid);
        this.root = root;
        this.body = root.getChild("body");
        this.right_leg = root.getChild("right_leg");
        this.left_leg = root.getChild("left_leg");
        this.head = root.getChild("head");
        this.right_arm = root.getChild("right_arm");
        this.left_arm = root.getChild("left_arm");
        this.wingr = root.getChild("wingr");
        this.wingl = root.getChild("wingl");
    }

    public static TexturedModelData getWingedTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        modelPartData.addChild("body", ModelPartBuilder.create().uv(16, 80).cuboid(-4.0F, -12.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(16, 96).cuboid(-4.0F, -12.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.of(0.0F, 7.75F, 0.0F, 0.2182F, 0.0F, 0.0F));

        modelPartData.addChild("right_leg", ModelPartBuilder.create().uv(0, 80).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(0, 96).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.of(-2.0F, 7.75F, -4.0F, 0.3491F, 0.0F, 0.0F));

        modelPartData.addChild("left_leg", ModelPartBuilder.create().uv(16, 112).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(0, 112).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.of(2.0F, 7.75F, 0.0F, 0.3491F, 0.0F, 0.0F));

        modelPartData.addChild("right_arm", ModelPartBuilder.create().uv(40, 80).cuboid(-12.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(40, 96).cuboid(-12.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.of(4.0F, -2.25F, -2.0F, 0.5236F, 0.0F, 0.0F));

        modelPartData.addChild("left_arm", ModelPartBuilder.create().uv(32, 112).cuboid(8.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(48, 112).cuboid(8.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.of(-4.0F, -2.125F, -2.0F, 0.5236F, 0.0F, 0.0F));

        modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 64).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F))
                .uv(32, 64).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.5F)), ModelTransform.of(0.0F, -4.25F, -3.0F, -0.3491F, 0.0F, 0.0F));
        modelPartData.addChild("wingr", ModelPartBuilder.create().uv(100, -14).cuboid(0.0F, -5.0F, 0.0F, 0.0F, 30.0F, 14.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, -0.25F, 0.25F, 0.2921F, -0.7203F, -0.1958F));

        modelPartData.addChild("wingl", ModelPartBuilder.create().uv(100, -14).cuboid(0.0F, -5.0F, 0.0F, 0.0F, 30.0F, 14.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -0.25F, 0.25F, 0.2921F, 0.7203F, 0.1958F));
        return TexturedModelData.of(modelData, 128, 128);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        this.renderWingedStatue(matrices, vertices, light, overlay, red, green, blue, alpha);

    }

    public void renderWingedStatue(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        this.root.render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }
}
