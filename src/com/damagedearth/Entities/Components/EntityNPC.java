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

package com.damagedearth.Entities.Components;

import com.damagedearth.DamagedEarth;
import com.damagedearth.Entities.EntityPlayer;
import com.damagedearth.Gameplay.Quests.BasicQuest;
import com.damagedearth.Gui.Components.GuiNPC;
import com.damagedearth.Utilities.MouseHelper;
import com.damagedearth.Utilities.NPCFileManager;
import com.damagedearth.Utilities.Tesselator;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.glRectd;

public class EntityNPC extends Entity
{

    List<BasicQuest> currentQuests = new ArrayList<BasicQuest>();
    GuiNPC guiNPC;
    NPCFileManager npcFileManager;

    public EntityNPC(String name, EntityPlayer thePlayer, double defaultX, double defaultY, double width, double height, DamagedEarth damagedEarth, Point des1, Point des2, Point des3, Point des4)
    {
        super(name, thePlayer, defaultX, defaultY, width, height, damagedEarth, des1, des2, des3, des4);
        this.npcFileManager = new NPCFileManager(this);
    }

    @Override
    public void onLivingUpdate()
    {
        this.renderEntity();
        this.strollEntity();
        this.gameLoop();
    }

    public void loadData()
    {
        this.npcFileManager.loadNPC();
    }

    public void saveData()
    {
        System.out.println("Saving NPC data... (" + this.getEntityName() + ")");
        this.npcFileManager.updateNPC();
        System.out.println("Saved NPC data." + "(" + this.getEntityName() + ")");
    }

    protected void gameLoop()
    {
        if (this.guiNPC != null)
        {
            if (MouseHelper.insideArea((int) this.x, (int) this.y, (int) this.width, (int) this.height) && this.thePlayer.nearEntity(this))
            {
                //Draws the speech bubble over the entity
                glRectd(this.x, this.y + 40, this.x + width, this.y + 2);
                if (MouseHelper.getMouseClick(1))
                {
                    //Switches to the NPC's screen when you click on him
                    this.thePlayer.setTargetedEntity(this);
                    this.damagedEarth.switchScreen(guiNPC);
                }
            }
        }
    }

    @Override
    protected void renderEntity()
    {
        Tesselator tesselator = new Tesselator();
        tesselator.set(x, y, x + width, y - height);
        tesselator.setColor(0, 1, 0);
        tesselator.startDrawingCQuad();
        tesselator.endDrawingQuad();
    }

    public List<BasicQuest> getCurrentQuests()
    {
        return currentQuests;
    }

    public void setCurrentQuests(List<BasicQuest> currentQuests)
    {
        this.currentQuests = currentQuests;
    }

    public GuiNPC getGuiNPC()
    {
        return guiNPC;
    }

    public void setGuiNPC(GuiNPC guiNPC)
    {
        this.guiNPC = guiNPC;
    }
}
