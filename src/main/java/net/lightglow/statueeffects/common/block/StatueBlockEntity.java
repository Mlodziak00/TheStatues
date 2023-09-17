package net.lightglow.statueeffects.common.block;

import net.lightglow.statueeffects.init.BlockInit;
import net.lightglow.statueeffects.util.StatueBlockEffects;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.stat.Stat;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.Nameable;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class StatueBlockEntity extends BlockEntity implements Nameable {
    StatueTypes statueTypes;
    public Text customTextureName;
    public StatueBlockEntity(BlockPos pos, BlockState state) {
        super(BlockInit.STATUE_BLOCK_ENTITY, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, StatueBlockEntity be)
    {
        if(!world.isClient)
        {
            if (be.statueTypes == StatueTypes.FLIGHT) {
                StatueBlockEffects.giveFly(world, pos, 10, 10);
                world.updateListeners(pos,state,state,Block.NOTIFY_ALL);
                world.markDirty(pos);
            } else if (be.statueTypes == StatueTypes.REGEN) {
                StatueBlockEffects.giveRegen(world, pos, 10, 10);
                world.updateListeners(pos,state,state,Block.NOTIFY_ALL);
                world.markDirty(pos);
            } else if (be.statueTypes == StatueTypes.DEFINCES) {
                StatueBlockEffects.giveDefinces(world, pos, 10, 10);
                world.updateListeners(pos,state,state,Block.NOTIFY_ALL);
                world.markDirty(pos);
            } else if (be.statueTypes == StatueTypes.STRENGTH) {
                StatueBlockEffects.giveStrength(world, pos, 10, 10);
                world.updateListeners(pos,state,state,Block.NOTIFY_ALL);
                world.markDirty(pos);
            } else if (be.statueTypes == StatueTypes.SHORTSIGHT) {
                StatueBlockEffects.giveShortSight(world, pos, 10, 10);
                world.updateListeners(pos,state,state,Block.NOTIFY_ALL);
                world.markDirty(pos);
            }
        }
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        if (this.statueTypes != null) {
            nbt.putString("StatueType", this.statueTypes.asString());
        }
        if (this.customTextureName != null) {
            nbt.putString("CustomTextureName", Text.Serializer.toJson(this.customTextureName));
        }
        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        if (nbt.contains("StatueTypes", NbtElement.STRING_TYPE)) {
            nbt.getString("StatueType");
        }
        if (nbt.contains("CustomTextureName", NbtElement.STRING_TYPE)) {
            this.customTextureName = Text.Serializer.fromJson(nbt.getString("CustomTextureName"));
        }
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        NbtCompound nbt = new NbtCompound();
        if (this.statueTypes != null) {
            nbt.putString("StatueType", this.statueTypes.asString());
        }
        if (this.customTextureName != null) {
            nbt.putString("CustomTextureName", Text.Serializer.toJson(this.customTextureName));
        }
        return nbt;
    }

    @Override
    public Text getName() {
        if (this.customTextureName != null){
            return this.customTextureName;
        }
        return this.getContainerName();
    }

    @Override
    public Text getDisplayName() {
        return this.getName();
    }

    @Nullable
    @Override
    public Text getCustomName() {
        return this.customTextureName;
    }

    public Text getContainerName() {
        return null;
    }

    public void setCustomName(Text customTextureName) {
        this.customTextureName = customTextureName;
    }


}
