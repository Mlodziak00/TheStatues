package net.lightglow.statueeffects.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.lightglow.statueeffects.EffectStatues;
import net.lightglow.statueeffects.init.BlockInit;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.client.render.entity.model.EntityModelLayer;

public class EffectStatuesClient implements ClientModInitializer {
    public static final EntityModelLayer STATUE_LAYER = new EntityModelLayer(EffectStatues.createEntityId("statue_block"), "main");
    public static final EntityModelLayer WINGED_STATUE_LAYER = new EntityModelLayer(EffectStatues.createEntityId("winged_statue_block"), "main");
    public static final EntityModelLayer THINKER_STATUE_LAYER = new EntityModelLayer(EffectStatues.createEntityId("thinker_statue_block"), "main");

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.STATUE_BLOCK, RenderLayer.getCutout());
        EntityModelLayerRegistry.registerModelLayer(STATUE_LAYER, StatueBlockEntityModel::getBaseTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(WINGED_STATUE_LAYER, WingedStatueBlockEntityModel::getWingedTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(THINKER_STATUE_LAYER, ThinkerStatueBlockEntityModel::getThinkerTexturedModelData);
        BlockEntityRendererFactories.register(BlockInit.STATUE_BLOCK_ENTITY, StatueBlockEntityRenderer::new);
    }
}
