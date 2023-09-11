package net.lightglow.statueeffects.init;

import net.lightglow.statueeffects.EffectStatues;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class SoundInit {
    public static final SoundEvent INSERT_ITEM = new SoundEvent(new Identifier(EffectStatues.MOD_ID,  "block.statue.insert"));
    public static void registerStatueSounds(){

    }
}
