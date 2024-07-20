package com.jacky.data_collector_service.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum ECategory {
    WARFRAMES("Warframes"),
    SENTINELS("Sentinels"),
    SECONDARY("Secondary"),
    PRIMARY("Primary"),
    MELEE("Melee"),
    ARCHWING("Archwing"),
    ARCH_MELEE("Arch-Melee"),
    ARCH_GUN("Arch-Gun");

    private final String displayName;

    ECategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static List<String> getListWeaponCategory(){
        List<String> weapons = new ArrayList<>();

        weapons.add(PRIMARY.getDisplayName());
        weapons.add(SECONDARY.getDisplayName());
        weapons.add(MELEE.getDisplayName());

        return weapons;
    }
}
