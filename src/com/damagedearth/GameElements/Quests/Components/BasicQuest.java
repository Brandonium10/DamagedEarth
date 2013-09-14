package com.damagedearth.GameElements.Quests.Components;

import com.damagedearth.Entities.ControlledEntityPlayer;
import com.damagedearth.Entities.Components.EntityNPC;

public class BasicQuest
{
    /*
    TODO: Add other quest types to quest framework
     */
    ControlledEntityPlayer thePlayer;
    EntityNPC questGiver;

    String questName;

    EnumQuestType questType;
    boolean isComplete;

    public BasicQuest(ControlledEntityPlayer thePlayer, String questName)
    {
        this.thePlayer = thePlayer;
        this.questName = questName;
    }

    public BasicQuest(ControlledEntityPlayer thePlayer, String questName, EntityNPC questGiver)
    {
        this.thePlayer = thePlayer;
        this.questName = questName;
        this.questGiver = questGiver;
    }

    public String getQuestName()
    {
        return questName;
    }

    public void setQuestName(String questName)
    {
        this.questName = questName;
    }

    public EnumQuestType getQuestType()
    {
        return questType;
    }

    public void setQuestType(EnumQuestType questType)
    {
        this.questType = questType;
    }

    public boolean isComplete()
    {
        return isComplete;
    }

    public void setComplete(boolean complete)
    {
        isComplete = complete;
    }

    public EntityNPC getQuestGiver()
    {
        return questGiver;
    }

    public SlayingQuest getSlayingQuestInstance()
    {
        return this instanceof SlayingQuest ? (SlayingQuest) this : null;
    }
}
