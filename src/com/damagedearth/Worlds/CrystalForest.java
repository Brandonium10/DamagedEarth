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

package com.damagedearth.Worlds;

import com.damagedearth.DamagedEarth;
import com.damagedearth.Entities.Components.Entity;
import com.damagedearth.Entities.Components.EntityEnemy;
import com.damagedearth.Entities.Components.EntityNPC;
import com.damagedearth.Gameplay.Quests.SlayingQuest;
import com.damagedearth.Gui.Components.GuiNPC;

import java.awt.*;
import java.util.Iterator;

public class CrystalForest extends BasicWorld
{
    /*
    All entity's in this world
     */
    EntityNPC npcDumbbottom;
    EntityEnemy enemyTreegiant1;
    EntityEnemy enemyTreegiant2;
    EntityEnemy enemyTreegiant3;

    /*
    All Gui's that correspond to the NPC's in this world
     */
    GuiNPC guiDumbottom;

    /*
    All quests that correspond to the NPC's in this world: @npcName@questName@chainNumber
     */
    SlayingQuest dumbottomTraining1;

    public CrystalForest(int spawnX, int spawnY, DamagedEarth damagedEarth)
    {
        super(spawnX, spawnY, damagedEarth);
    }

    @Override
    public void gameLoop()
    {
    }

    /*
    Initiate all entities and creates the GUI's for the NPC's
     */
    @Override
    public void loadWorld()
    {
        //Set up NPC's GUIs and quests
        this.npcDumbbottom = new EntityNPC("Dumbottom", thePlayer, 155, 900, 50, 50, damagedEarth, null, null, null, null);
        this.dumbottomTraining1 = new SlayingQuest(this.thePlayer, "Training1", this.npcDumbbottom, "Treegiant", 2);
        this.guiDumbottom = new GuiNPC(damagedEarth, "Dumbbottom", null, this.npcDumbbottom);

        this.enemyTreegiant1 = new EntityEnemy("Treegiant", thePlayer, 50, 500, 900, 60, 50, damagedEarth, new Point(700, 100), new Point(500, 100), null, null, 1.5, 3);
        this.enemyTreegiant2 = new EntityEnemy("Treegiant", thePlayer, 50, 200, 700, 60, 50, damagedEarth, new Point(500, 900), new Point(200, 700), null, null, 1.5, 3);

        this.entityList.add(npcDumbbottom);
        this.entityList.add(enemyTreegiant1);
        this.entityList.add(enemyTreegiant2);

        //Loads every NPC's data from the file. This MUST be called here.
        Iterator<Entity> eItr = this.entityList.iterator();
        while (eItr.hasNext())
        {
            Entity entity = eItr.next();

            if (entity instanceof EntityNPC)
            {
                entity.getNPCInstance().loadData();
            }
        }
        //This is for the file FW
        this.allWorldQuests.add(this.dumbottomTraining1);

        //This loads the player into the world with all proper data. This method MUST be called here
        if (!damagedEarth.plyrManager.load(thePlayer))
        {
            thePlayer.teleport(this.getSpawnX(), this.getSpawnY());
            System.out.println("[Player] Error loading the player data file: loaded default values instead");
        }
    }
}
