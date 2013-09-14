package com.damagedearth.GameElements.Quests.Components;

import com.damagedearth.Entities.Components.EntityNPC;
import com.damagedearth.Entities.ControlledEntityPlayer;
import com.damagedearth.Entities.Components.EntityEnemy;

public class SlayingQuest extends BasicQuest
{
    protected String enemyToKill;
    protected int amount;

    public SlayingQuest(ControlledEntityPlayer thePlayer, String questName)
    {
        super(thePlayer, questName);
        this.questType = EnumQuestType.SLAYING;
    }

    public SlayingQuest(ControlledEntityPlayer thePlayer, String questName, EntityNPC npcGiver)
    {
        super(thePlayer, questName, npcGiver);
        this.questType = EnumQuestType.SLAYING;
    }

    public void setGoal(String enemyName, int amount)
    {
        this.enemyToKill = enemyName;
        this.amount = amount;
    }

    /*
    This method is called whenever a player kills an enemy. It will update the players killstreak
    with the current quest by checking to see if he killed the enemy(s) that the quest requires
     */
    public void updateKillstreak(EntityEnemy enemyKilled)
    {
        if (!isComplete && this.enemyToKill != null && this.amount != 0)
        {
            if (enemyKilled.getEntityName().equals(this.enemyToKill))
            {
                amount--;
                System.out.println(amount);
            }
            if (amount <= 0)
            {
                this.isComplete = true;
                System.out.println("Completed test quest");
            }
        }
    }

    public String getEnemyToKill()
    {
        return enemyToKill;
    }

    public void setEnemyToKill(String enemyToKill)
    {
        this.enemyToKill = enemyToKill;
    }

    public int getAmount()
    {
        return amount;
    }

    public void setAmount(int amount)
    {
        this.amount = amount;
    }
}
