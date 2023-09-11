package net.lightglow.statueeffects.common.effect;

import net.lightglow.statueeffects.init.EffectInit;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;

public class WingedStatusEffect extends StatusEffect {


    public WingedStatusEffect(StatusEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity instanceof PlayerEntity) {
            if (!((PlayerEntity) entity).isCreative() && !((PlayerEntity) entity).isSpectator()) {
                ((PlayerEntity) entity).getAbilities().allowFlying = true;
                ((PlayerEntity) entity).getAbilities().setFlySpeed(0.025f);
            }
        }
    }

    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        if (!((PlayerEntity) entity).isCreative() && !((PlayerEntity) entity).isSpectator()) {
            ((PlayerEntity) entity).getAbilities().flying = false;
            ((PlayerEntity) entity).getAbilities().allowFlying = false;
            ((PlayerEntity) entity).getAbilities().setFlySpeed(0.1f);
            if (!entity.hasStatusEffect(EffectInit.WINGED)) {
                ((PlayerEntity) entity).sendAbilitiesUpdate();
            }
        }
    }
}