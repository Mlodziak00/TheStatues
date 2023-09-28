package net.lightglow.statueeffects.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;

public class DefincesStatueBlockEntityModel extends Model {
    private final ModelPart root;
    private final ModelPart body;

    private final ModelPart right_leg;
    private final ModelPart left_leg;
    private final ModelPart head;
    private final ModelPart right_arm;
    private final ModelPart left_arm;
    private final ModelPart shield;

    public DefincesStatueBlockEntityModel(ModelPart root) {
        super(RenderLayer::getEntitySolid);
        this.root = root;
        this.body = root.getChild("body");
        this.right_leg = root.getChild("right_leg");
        this.left_leg = root.getChild("left_leg");
        this.head = root.getChild("head");
        this.right_arm = root.getChild("right_arm");
        this.left_arm = root.getChild("left_arm");
        this.shield = root.getChild("shield");
    }

    public static TexturedModelData getDefincesTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        ModelPartData body = modelPartData.addChild("body", ModelPartBuilder.create(), ModelTransform.of(0.0F, 6.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

        body.addChild("jacket_r1", ModelPartBuilder.create().uv(16, 96).cuboid(-4.0F, -19.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.25F))
                .uv(16, 80).cuboid(-4.0F, -19.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 12.0F, 0.0F, 0.0F, -0.2618F, 0.0F));

        modelPartData.addChild("right_leg", ModelPartBuilder.create().uv(0, 80).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(0, 96).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.of(-2.0F, 10.75F, -5.0F, 0.7854F, 0.0F, 0.0F));

        modelPartData.addChild("left_leg", ModelPartBuilder.create().uv(16, 112).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(0, 112).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.of(2.0F, 7.75F, -4.0F, 0.48F, 0.0F, 0.0F));

        modelPartData.addChild("head", ModelPartBuilder.create().uv(0, 64).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F))
                .uv(32, 64).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.5F)), ModelTransform.of(0.0F, -1.25F, -1.0F, 0.0152F, -0.0859F, -0.1752F));

        modelPartData.addChild("right_arm", ModelPartBuilder.create().uv(40, 80).cuboid(-4.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(40, 96).cuboid(-4.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.of(-3.75F, 0.75F, -1.0F, -1.4399F, -0.2618F, 0.0F));

        modelPartData.addChild("left_arm", ModelPartBuilder.create().uv(32, 112).cuboid(0.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F))
                .uv(48, 112).cuboid(0.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.25F)), ModelTransform.of(4.0F, 1.25F, -1.0F, -1.2087F, 0.9823F, 0.2109F));

        modelPartData.addChild("shield", ModelPartBuilder.create().uv(86, 48).cuboid(-6.0F, -9.0F, -4.0F, 12.0F, 22.0F, 1.0F, new Dilation(0.0F))
                .uv(112, 48).cuboid(-1.0F, -3.0F, -3.0F, 2.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.15F, -6.0F, 0.3162F, -0.5293F, -0.2101F));
        return TexturedModelData.of(modelData, 128, 128);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        this.renderDefincesStatue(matrices, vertices, light, overlay, red, green, blue, alpha);

    }

    public void renderDefincesStatue(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        this.root.render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }
}
