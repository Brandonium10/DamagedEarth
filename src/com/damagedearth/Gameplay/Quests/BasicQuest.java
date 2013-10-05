/*******************************************************************************
 * This is a free program developed and managed by The Predators Development Team. This
 * source code should have a copy of the GNU General Public License. If it does not,
 * please see <http://www.gnu.org/licenses/>. The Predators Development Team forums is
 * located at <http://thepredators.endofinternet.net/index.php>.
 ******************************************************************************/

/*******************************************************************************
 * This is a free program developed and managed by The Predators Development Team. This
 * source code should have a copy of the GNU General Public License. If it does not,
 * please see <http://www.gnu.org/licenses/>. The Predators Development Team forums is
 * located at <http://thepredators.endofinternet.net/index.php>.
 ******************************************************************************/

package com.damagedearth.Gameplay.Quests;

import com.damagedearth.Entities.Components.EntityNPC;
import com.damagedearth.Entities.EntityPlayer;

public class BasicQuest
{
    /*
    TODO: Add other quest types to quest framework
     */
    EntityPlayer thePlayer;
    EntityNPC questGiver;

    String questName;

    EnumQuestType questType;
    boolean isComplete;

    public BasicQuest(EntityPlayer thePlayer, String questName)
    {
        this.thePlayer = thePlayer;
        this.questName = questName;
    }

    public BasicQuest(EntityPlayer thePlayer, String questName, EntityNPC questGiver)
    {
        this.thePlayer = thePlayer;
        this.questName = questName;
        this.questGiver = questGiver;
        this.questGiver.getCurrentQuests().add(this);
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
