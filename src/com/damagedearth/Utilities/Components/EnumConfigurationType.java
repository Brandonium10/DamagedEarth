package com.damagedearth.Utilities.Components;

public enum EnumConfigurationType
{
    /**
     * PLAYER: Players EXP, Level, Class, Current Quests, Inventory, etc.
     * WORLD: All entities locations, enemy statistics, boss statistics, etc.
     * SETTINGS: All things related to settings (ex. alpha value, keybindings, difficulty)
     */
    PLAYER("Player"), WORLD("World"), SETTINGS("Settings");

    String configType;

    EnumConfigurationType(String configType)
    {
        this.configType = configType;
    }

    public String getFriendlyName() {
        return configType;
    }
}
