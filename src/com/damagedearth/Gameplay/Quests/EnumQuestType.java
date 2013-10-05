package com.damagedearth.Gameplay.Quests;

public enum EnumQuestType
{
    SLAYING(1), COLLECTING(2), ESCORTING(3), TRAVEL(4), EXPLORE(5);

    private int questType;

    private EnumQuestType(int questType)
    {
        this.questType = questType;
    }

    public String getStringType()
    {
        return (questType == 1 ? "Slaying" : (questType == 2 ? "Collecting" : (questType == 3 ? "Escort" : (questType == 4 ? "Travel" : (questType == 5 ? "Explore" : "")))));
    }
}
