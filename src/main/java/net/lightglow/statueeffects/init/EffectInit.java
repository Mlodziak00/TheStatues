package net.lightglow.statueeffects.init;

import net.lightglow.statueeffects.EffectStatues;
import net.lightglow.statueeffects.common.effect.WingedStatusEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EffectInit {
    public static final StatusEffect WINGED = new WingedStatusEffect(StatusEffectCategory.BENEFICIAL, 0x000000);
    public static void registerStatusEffects()
    {
        Registry.register(Registry.STATUS_EFFECT, new Identifier(EffectStatues.MOD_ID, "winged"), WINGED);
    }
}
