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
import net.minecraft.nbt.NbtCompound;
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
import java.util.stream.Stream;

public class StatueBlock extends BlockWithEntity implements BlockEntityProvider {

    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final EnumProperty<DoubleBlockHalf> HALF = Properties.DOUBLE_BLOCK_HALF;
    DoubleBlockHalf _lower = DoubleBlockHalf.LOWER;
    DoubleBlockHalf _upper = DoubleBlockHalf.UPPER;



    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, HALF);
    }

    public StatueBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(FACING, Direction.NORTH).with(HALF, _lower));
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        destroy(world, pos, true);
        super.onBreak(world, pos, state, player);
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, BlockEntity tileEntity, ItemStack stack) {
        destroy(world, pos, true);
        super.afterBreak(world, player, pos, state, tileEntity, stack);
    }

    //@Override
    //public void onDestroyedByExplosion(World world, BlockPos pos, Explosion explosion) {
    //    destroy(world, pos, false);
    //    super.onDestroyedByExplosion(world, pos, explosion);
    //}
    private void destroy(World world, BlockPos pos, boolean shouldDrop) {
        BlockState state = world.getBlockState(pos);
        if (world.getBlockState(state.get(HALF) == _lower ? pos.up() : pos.down()).getBlock() == this)
            world.removeBlock(state.get(HALF) == _lower ? pos.up() : pos.down(), false);
        if (shouldDrop) ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(this));
    }

    private void setStatueType(BlockEntity be, int number) {
        ((StatueBlockEntity) be).statueTypes = number;
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
        world.setBlockState(pos.up(), BlockInit.STATUE_BLOCK.getDefaultState().with(FACING, world.getBlockState(pos).get(FACING)).with(HALF, _upper), 3);
    }

    @Override
    public boolean onSyncedBlockEvent(BlockState state, World world, BlockPos pos, int type, int data) {
        if (world.getBlockEntity(pos) instanceof StatueBlockEntity entity) {
            return entity.onSyncedBlockEvent(type, data);
        } else {
            return false;
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient()) {
            ItemStack itemStack = player.getStackInHand(hand);
            BlockEntity be = world.getBlockEntity(pos);
            if (be instanceof StatueBlockEntity) {
                if (state.get(HALF) == _lower) {
                    if (((StatueBlockEntity) be).statueTypes == 0) {
                        if (hand == Hand.MAIN_HAND) {
                            if (itemStack.isOf(Items.AMETHYST_SHARD)) {
                                if (!player.isCreative()) {
                                    player.getInventory().getMainHandStack().decrement(1);
                                }
                                this.setStatueType(be, 1);
                                this.playInsertItemSound(player);
                                return ActionResult.SUCCESS;
                            } else if (itemStack.isIn(ItemTags.FLOWERS)) {
                                if (!player.isCreative()) {
                                    player.getInventory().getMainHandStack().decrement(1);
                                }
                                this.setStatueType(be, 2);
                                this.playInsertItemSound(player);
                                return ActionResult.SUCCESS;
                            } else if (itemStack.isIn(ItemTags.ANVIL)) {
                                if (!player.isCreative()) {
                                    player.getInventory().getMainHandStack().decrement(1);
                                }
                                this.setStatueType(be, 3);
                                this.playInsertItemSound(player);
                                return ActionResult.SUCCESS;
                            } else if (itemStack.isIn(ItemTags.WOOL)) {
                                if (!player.isCreative()) {
                                    player.getInventory().getMainHandStack().decrement(1);
                                }
                                this.setStatueType(be, 4);
                                this.playInsertItemSound(player);
                                return ActionResult.SUCCESS;
                            } else if (itemStack.isOf(Items.SPIDER_EYE)) {
                                if (!player.isCreative()) {
                                    player.getInventory().getMainHandStack().decrement(1);
                                }
                                this.setStatueType(be, 5);
                                this.playInsertItemSound(player);
                                return ActionResult.SUCCESS;
                            }
                        }
                    }
                }
            }
        }
        return ActionResult.SUCCESS;
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
        if (state.get(HALF) == _upper) {
            return SHAPE_TOP;
        } else if (state.get(HALF) == _lower) {
            return SHAPE_BOTTOM;
        }
        return null;
    }
}
