package net.lightglow.statueeffects.util;

import net.lightglow.statueeffects.init.EffectInit;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;

import java.util.Iterator;
import java.util.List;

public class StatueBlockEffects {
    public static void giveFly(World world, BlockPos pos, int horizontal, int vertical)
    {
        Box playerInZone = (new Box(pos)).expand(horizontal, vertical, horizontal);
        List<ServerPlayerEntity> list = world.getNonSpectatingEntities(ServerPlayerEntity.class, playerInZone);
        Iterator<ServerPlayerEntity> iterator = list.iterator();

        ServerPlayerEntity targetPlayer;

        while(iterator.hasNext())
        {
            targetPlayer = iterator.next();

            StatusEffectInstance flightEffect = new StatusEffectInstance(EffectInit.WINGED, 200, 0, false, false);

            targetPlayer.addStatusEffect(flightEffect);

        }
    }
    public static void giveRegen(World world, BlockPos pos, int horizontal, int vertical)
    {
        Box playerInZone = (new Box(pos)).expand(horizontal, vertical, horizontal);
        List<ServerPlayerEntity> list = world.getNonSpectatingEntities(ServerPlayerEntity.class, playerInZone);
        Iterator<ServerPlayerEntity> iterator = list.iterator();

        ServerPlayerEntity targetPlayer;

        while(iterator.hasNext())
        {
            targetPlayer = iterator.next();

            StatusEffectInstance flightEffect = new StatusEffectInstance(StatusEffects.REGENERATION, 200, 0, false, false);

            targetPlayer.addStatusEffect(flightEffect);

        }
    }
    public static void giveDefinces(World world, BlockPos pos, int horizontal, int vertical)
    {
        Box playerInZone = (new Box(pos)).expand(horizontal, vertical, horizontal);
        List<ServerPlayerEntity> list = world.getNonSpectatingEntities(ServerPlayerEntity.class, playerInZone);
        Iterator<ServerPlayerEntity> iterator = list.iterator();

        ServerPlayerEntity targetPlayer;

        while(iterator.hasNext())
        {
            targetPlayer = iterator.next();

            StatusEffectInstance flightEffect = new StatusEffectInstance(StatusEffects.RESISTANCE, 200, 0, false, false);

            targetPlayer.addStatusEffect(flightEffect);

        }
    }

    public static void giveStrength(World world, BlockPos pos, int horizontal, int vertical)
    {
        Box playerInZone = (new Box(pos)).expand(horizontal, vertical, horizontal);
        List<ServerPlayerEntity> list = world.getNonSpectatingEntities(ServerPlayerEntity.class, playerInZone);
        Iterator<ServerPlayerEntity> iterator = list.iterator();

        ServerPlayerEntity targetPlayer;

        while(iterator.hasNext())
        {
            targetPlayer = iterator.next();

            StatusEffectInstance flightEffect = new StatusEffectInstance(StatusEffects.STRENGTH, 200, 0, false, false);

            targetPlayer.addStatusEffect(flightEffect);

        }
    }

    public static void giveShortSight(World world, BlockPos pos, int horizontal, int vertical)
    {
        Box playerInZone = (new Box(pos)).expand(horizontal, vertical, horizontal);
        List<ServerPlayerEntity> list = world.getNonSpectatingEntities(ServerPlayerEntity.class, playerInZone);
        Iterator<ServerPlayerEntity> iterator = list.iterator();

        ServerPlayerEntity targetPlayer;

        while(iterator.hasNext())
        {
            targetPlayer = iterator.next();

            StatusEffectInstance flightEffect = new StatusEffectInstance(StatusEffects.BLINDNESS, 100, 0, false, false);

            targetPlayer.addStatusEffect(flightEffect);

        }
    }
}
