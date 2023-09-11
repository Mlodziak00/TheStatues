package net.lightglow.statueeffects;

import net.fabricmc.api.ModInitializer;

import net.lightglow.statueeffects.init.BlockInit;
import net.lightglow.statueeffects.init.EffectInit;
import net.lightglow.statueeffects.init.SoundInit;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EffectStatues implements ModInitializer {
	public static final String MOD_ID = "effect-statues";
    public static final Logger LOGGER = LoggerFactory.getLogger("effect-statues");

	@Override
	public void onInitialize() {
		SoundInit.registerStatueSounds();
		BlockInit.registerBlocks();
		BlockInit.registerBlockItems();
		BlockInit.registerBlockEntity();

		EffectInit.registerStatusEffects();
	}

	public static Identifier createEntityId(String path) {
		return new Identifier(MOD_ID, path);
	}
}