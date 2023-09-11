package net.lightglow.statueeffects.common.block;

import net.lightglow.statueeffects.init.BlockInit;
import net.lightglow.statueeffects.init.SoundInit;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.ItemTags;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.*;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class StatueBlock extends BlockWithEntity implements BlockEntityProvider {

    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final EnumProperty<DoubleBlockHalf> half = Properties.DOUBLE_BLOCK_HALF;
    DoubleBlockHalf _lower = DoubleBlockHalf.LOWER;
    DoubleBlockHalf _upper = DoubleBlockHalf.UPPER;



    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, half);
    }

    public StatueBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(FACING, Direction.NORTH).with(half, _lower));
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        destroy(world, pos, false);
        super.onBreak(world, pos, state, player);
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, BlockEntity tileEntity, ItemStack stack) {
        destroy(world, pos, true);
        super.afterBreak(world, player, pos, state, tileEntity, stack);
    }

    @Override
    public void onDestroyedByExplosion(World world, BlockPos pos, Explosion explosion) {
        destroy(world, pos, true);
        super.onDestroyedByExplosion(world, pos, explosion);
    }
    private void destroy(World world, BlockPos pos, boolean shouldDrop) {
        BlockState state = world.getBlockState(pos);
        if (world.getBlockState(state.get(half) == _lower ? pos.up() : pos.down()).getBlock() == this)
            world.removeBlock(state.get(half) == _lower ? pos.up() : pos.down(), false);
        if (shouldDrop) ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(this));
    }


    private static final VoxelShape SHAPE_BOTTOM = Stream.of(
            Block.createCuboidShape(0, 5, 0, 16, 6, 16),
            Block.createCuboidShape(1, 3, 1, 15, 5, 15),
            Block.createCuboidShape(0, 0, 0, 16, 3, 16),
            Block.createCuboidShape(4, 6, 4, 12, 16, 12)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    public static VoxelShape SHAPE_TOP = Block.createCuboidShape(4, 0, 4, 12, 16, 12);

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return world.isAir(pos.up());
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new StatueBlockEntity(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }



    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        BlockEntity be;
        if (itemStack.hasCustomName() && (be = world.getBlockEntity(pos)) instanceof StatueBlockEntity){
            ((StatueBlockEntity) be).setCustomName(itemStack.getName());
        }
        world.setBlockState(pos.up(), BlockInit.STATUE_BLOCK.getDefaultState().with(FACING, world.getBlockState(pos).get(FACING)).with(half, _upper), 3);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient()){
            ItemStack itemStack = player.getStackInHand(hand);
            BlockEntity be = world.getBlockEntity(pos);
            if (be instanceof StatueBlockEntity) {
                if (itemStack.isOf(Items.AMETHYST_SHARD) && ((StatueBlockEntity) be).isBaseStatue) {
                    if (!player.isCreative()){
                        player.getInventory().getMainHandStack().decrement(1);
                    }
                    this.setFlightStatue(be, world, pos);
                    this.playInsertItemSound(player);
                    return ActionResult.SUCCESS;
                } else if (itemStack.isIn(ItemTags.FLOWERS) && ((StatueBlockEntity) be).isBaseStatue) {
                    if (!player.isCreative()){
                        player.getInventory().getMainHandStack().decrement(1);
                    }
                    this.setRegenStatue(be, world, pos);
                    this.playInsertItemSound(player);
                    return ActionResult.SUCCESS;
                } else if (itemStack.isIn(ItemTags.ANVIL) && ((StatueBlockEntity) be).isBaseStatue) {
                    if (!player.isCreative()){
                        player.getInventory().getMainHandStack().decrement(1);
                    }
                    this.setDefincesStatue(be, world, pos);
                    this.playInsertItemSound(player);
                    return ActionResult.SUCCESS;
                } else if (itemStack.isIn(ItemTags.WOOL) && ((StatueBlockEntity) be).isBaseStatue) {
                    if (!player.isCreative()){
                        player.getInventory().getMainHandStack().decrement(1);
                    }
                    this.setStrengthStatue(be, world, pos);
                    this.playInsertItemSound(player);
                    return ActionResult.SUCCESS;
                } else if (itemStack.isIn(ItemTags.FISHES) && ((StatueBlockEntity) be).isBaseStatue) {
                    if (!player.isCreative()){
                        player.getInventory().getMainHandStack().decrement(1);
                    }
                    this.setShortSightStatue(be, world, pos);
                    this.playInsertItemSound(player);
                    return ActionResult.SUCCESS;
                    }
                }
        }
        return ActionResult.PASS;
    }

    public void setFlightStatue(BlockEntity blockEntity, World world, BlockPos pos) {
        ((StatueBlockEntity) blockEntity).isBaseStatue = false;
        ((StatueBlockEntity) blockEntity).isFlightStatue = true;
        world.addParticle(new BlockStateParticleEffect(ParticleTypes.BLOCK, Blocks.STONE.getDefaultState()), pos.getX(), pos.getY(), pos.getZ(), Math.cos(20), 0.15d, Math.sin(20) + 5);
    }
    public void setRegenStatue(BlockEntity blockEntity, World world, BlockPos pos) {
        ((StatueBlockEntity) blockEntity).isBaseStatue = false;
        ((StatueBlockEntity) blockEntity).isRegenStatue = true;
        world.addParticle(new BlockStateParticleEffect(ParticleTypes.BLOCK, Blocks.STONE.getDefaultState()), pos.getX(), pos.getY(), pos.getZ(), Math.cos(20), 0.15d, Math.sin(20) + 5);
    }
    public void setDefincesStatue(BlockEntity blockEntity, World world, BlockPos pos) {
        ((StatueBlockEntity) blockEntity).isBaseStatue = false;
        ((StatueBlockEntity) blockEntity).isDefincesStatue = true;
        world.addParticle(new BlockStateParticleEffect(ParticleTypes.BLOCK, Blocks.STONE.getDefaultState()), pos.getX(), pos.getY(), pos.getZ(), Math.cos(20), 0.15d, Math.sin(20) + 5);
    }
    public void setStrengthStatue(BlockEntity blockEntity, World world, BlockPos pos) {
        ((StatueBlockEntity) blockEntity).isBaseStatue = false;
        ((StatueBlockEntity) blockEntity).isStrengthStatue = true;
        world.addParticle(new BlockStateParticleEffect(ParticleTypes.BLOCK, Blocks.STONE.getDefaultState()), pos.getX(), pos.getY(), pos.getZ(), Math.cos(20), 0.15d, Math.sin(20) + 5);
    }
    public void setShortSightStatue(BlockEntity blockEntity, World world, BlockPos pos) {
        ((StatueBlockEntity) blockEntity).isBaseStatue = false;
        ((StatueBlockEntity) blockEntity).isShortSightStatue = true;
        world.addParticle(new BlockStateParticleEffect(ParticleTypes.BLOCK, Blocks.STONE.getDefaultState()), pos.getX(), pos.getY(), pos.getZ(), Math.cos(20), 0.15d, Math.sin(20) + 5);
    }


    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        tooltip.add(Text.translatable("item.effect-statues.statueitem.tooltip").formatted(Formatting.WHITE));
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, BlockInit.STATUE_BLOCK_ENTITY, StatueBlockEntity::tick);
    }

    protected SoundEvent getInsertItemSound() {
        return SoundInit.INSERT_ITEM;
    }

    public void playInsertItemSound(Entity entity) {

        World var2 = entity.getWorld();
        SoundEvent soundEvent = this.getInsertItemSound();
        if (soundEvent != null) {
            var2.playSound(null, entity.getX(),entity.getY(),entity.getZ(), SoundInit.INSERT_ITEM, SoundCategory.PLAYERS, 1, 1);
        }

    }
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (state.get(half) == _upper) {
            return SHAPE_TOP;
        } else if (state.get(half) == _lower) {
            return SHAPE_BOTTOM;
        }
        return null;
    }
}
