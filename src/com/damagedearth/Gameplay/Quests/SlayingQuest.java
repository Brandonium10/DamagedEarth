/*******************************************************************************
 * This is a free program developed and managed by The Predators Development Team. This
 * source code should have a copy of the GNU General Public License. If it does not,
 * please see <http://www.gnu.org/licenses/>. The Predators Development Team forums is
 * located at <http://thepredators.endofinternet.net/index.php>.
 ******************************************************************************/

package com.damagedearth.Gameplay.Quests;

import com.damagedearth.Entities.Components.EntityEnemy;
import com.damagedearth.Entities.Components.EntityNPC;
import com.damagedearth.Entities.EntityPlayer;

public class SlayingQuest extends BasicQuest
{
    private String enemyToKill;
    private int initialAmount;
    private int amount;

    public SlayingQuest(EntityPlayer thePlayer, String questName, EntityNPC npcGiver, String enemyToKill, int amount)
    {
        super(thePlayer, questName, npcGiver);
        this.enemyToKill = enemyToKill;
        this.amount = amount;
        //Save the initial amount for quest dialogues
        this.initialAmount = amount;
    }

    /*
    This method is called whenever a player kills an enemy. It will update the players killstreak
    with the current quest by checking to see if he killed the enemy(s) that the quest requires
     */
    public void updateKillstreak(EntityEnemy enemyKilled)
    {
        if (!this.isComplete() && this.enemyToKill != null && this.amount != 0)
        {
            if (enemyKilled.getEntityName().equals(this.enemyToKill))
            {
                amount--;
                System.out.println(amount);
            }
            if (amount <= 0)
            {
                this.setComplete(true);
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

    public int getInitialAmount()
    {
        return initialAmount;
    }
}
