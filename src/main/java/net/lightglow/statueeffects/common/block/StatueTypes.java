package net.lightglow.statueeffects.common.block;

import net.minecraft.util.StringIdentifiable;

public enum StatueTypes implements StringIdentifiable {
    BASE("base"),
    FLIGHT("flight"),
    REGEN("regen"),
    DEFINCES("definces"),
    STRENGTH("strength"),
    SHORTSIGHT("shortsight");

    private final String name;

    private StatueTypes(String name)
    {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

    @Override
    public String asString() {
        return this.name;
    }

}
