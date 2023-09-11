package net.lightglow.statueeffects.init;


import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.lightglow.statueeffects.EffectStatues;
import net.lightglow.statueeffects.common.block.StatueBlock;
import net.lightglow.statueeffects.common.block.StatueBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockInit {
    public static BlockEntityType<StatueBlockEntity> STATUE_BLOCK_ENTITY;

    public static final Block STATUE_BLOCK = new StatueBlock(FabricBlockSettings.of(Material.STONE).strength(3.0F, 3.0F));

    public static void registerBlocks()
    {
        Registry.register(Registry.BLOCK, new Identifier(EffectStatues.MOD_ID, "statue_block"), STATUE_BLOCK);
    }

    public static void registerBlockItems()
    {
        Registry.register(Registry.ITEM, new Identifier(EffectStatues.MOD_ID, "statue_block"), new BlockItem(STATUE_BLOCK, new Item.Settings().maxCount(1).group(ItemGroup.DECORATIONS)));

    }
    public static void registerBlockEntity()
    {
        STATUE_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "statue_block", FabricBlockEntityTypeBuilder.create(StatueBlockEntity::new, STATUE_BLOCK).build(null));
    }
}
