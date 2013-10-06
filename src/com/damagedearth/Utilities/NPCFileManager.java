/*******************************************************************************
 * This is a free program developed and managed by The Predators Development Team. This
 * source code should have a copy of the GNU General Public License. If it does not,
 * please see <http://www.gnu.org/licenses/>. The Predators Development Team forums is
 * located at <http://thepredators.endofinternet.net/index.php>.
 ******************************************************************************/

package com.damagedearth.Utilities;

import com.damagedearth.Entities.Components.EntityNPC;
import com.damagedearth.Gameplay.Quests.BasicQuest;
import com.damagedearth.Gameplay.Quests.SlayingQuest;
import com.damagedearth.Utilities.Components.FileConfiguration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NPCFileManager
{
    private FileConfiguration questConfiguration;
    private EntityNPC entityNPC;

    public NPCFileManager(EntityNPC entityNPC)
    {
        this.questConfiguration = new FileConfiguration(entityNPC.getEntityName().toLowerCase() + "-quests.txt");
        this.entityNPC = entityNPC;
    }

    public void updateNPC()
    {
        this.updateQuests();
    }

    public void loadNPC()
    {
        this.loadQuests();
    }

    private void updateQuests()
    {
        try
        {
            if (questConfiguration.getFile().exists())
            {
                //questConfiguration.decode();
                questConfiguration.clear();
            }
            //We will create the file after it's been cleared so loadQuests() knows that it is not an unsaved user.
            questConfiguration.getFile().createNewFile();

            for (BasicQuest quest : entityNPC.getCurrentQuests())
            {
                if (quest instanceof SlayingQuest)
                {
                    questConfiguration.writeln(quest.getQuestName(), true);
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Checks to see if any NPC quest is not in the file. If it's not, the quest has been completed and will be removed from the NPC.
     */
    private void loadQuests()
    {
        //It will load up the NPC quests normally if the file doesn't exist. This is for unsaved startups.
        if (!questConfiguration.getFile().exists()) return;

        List<BasicQuest> allowedQuests = new ArrayList<BasicQuest>();
        for (BasicQuest quest : this.entityNPC.getCurrentQuests())
        {
            if (questConfiguration.doesLineExists(quest.getQuestName()))
            {
                allowedQuests.add(quest);
            }
        }
        this.entityNPC.setCurrentQuests(allowedQuests);

    }
}
