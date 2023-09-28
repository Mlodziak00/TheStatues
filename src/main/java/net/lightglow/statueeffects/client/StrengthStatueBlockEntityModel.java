package net.lightglow.statueeffects.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;

public class StrengthStatueBlockEntityModel extends Model {
    private final ModelPart root;
    private final ModelPart body;

    private final ModelPart right_leg;
    private final ModelPart left_leg;
    private final ModelPart head;
    private final ModelPart right_arm;
    private final ModelPart left_arm;
    private final ModelPart sword;

    public StrengthStatueBlockEntityModel(ModelPart root) {
        super(RenderLayer::getEntitySolid);
        this.root = root;
        this.body = root.getChild("body");
        this.right_leg = root.getChild("right_leg");
        this.left_leg = root.getChild("left_leg");
        this.head = root.getChild("head");
        this.right_arm = root.getChild("right_arm");
        this.left_arm = root.getChild("left_arm");
        this.sword = root.getChild("sword");
    }

    public static TexturedModelData getStrengthTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        modelPartData.addChild("body", ModelPartBuilder.create().uv(16, 80).cuboid(-4.0F, -12.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(16, 96).cuboid(-4.0F, -12.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(0.0F, 5.75F, 0.0F));

        modelPartData.addChild("right_leg", ModelPartBuilder.create().uv(0, 80).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(0, 96).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(-2.0F, 5.75F, 0.0F));

        modelPartData.addChild("left_leg", ModelPartBuilder.create().uv(16, 112).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(0, 112).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.pivot(2.0F, 5.75F, 0.0F));

        modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 64).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F))
                .uv(32, 64).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.5F)), ModelTransform.pivot(0.0F, -6.25F, 0.0F));

        modelPartData.addChild("right_arm", ModelPartBuilder.create().uv(40, 80).cuboid(-4.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(40, 96).cuboid(-4.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.of(-4.0F, -4.25F, 0.0F, -0.4418F, -0.2909F, -0.5451F));

        modelPartData.addChild("left_arm", ModelPartBuilder.create().uv(32, 112).cuboid(0.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(48, 112).cuboid(0.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.of(4.0F, -4.25F, 0.0F, -0.4592F, 0.2909F, 0.5451F));

        modelPartData.addChild("sword", ModelPartBuilder.create().uv(96, 71).cuboid(-8.0F, -8.0F, 1.0F, 16.0F, 16.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 9.0F, -6.75F, 0.0F, 0.0F, -2.3562F));
        return TexturedModelData.of(modelData, 128, 128);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        this.renderStrengthStatue(matrices, vertices, light, overlay, red, green, blue, alpha);

    }

    public void renderStrengthStatue(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        this.root.render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }
}
