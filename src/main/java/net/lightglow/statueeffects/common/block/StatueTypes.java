package net.lightglow.statueeffects.common.block;

import net.minecraft.util.StringIdentifiable;

public enum StatueTypes implements StringIdentifiable {
    BASE("base"),
    FLIGHT("flight"),
    REGEN("regen"),
    DEFINCES("definces"),
    STRENGTH("strength"),
    SHORTSIGHT("shortsight");

    private final String statueType;

    StatueTypes(String statueType) {this.statueType = statueType;}

    public String toString() {
        return this.statueType;
    }

    @Override
    public String asString() {
        return this.statueType;
    }

}
