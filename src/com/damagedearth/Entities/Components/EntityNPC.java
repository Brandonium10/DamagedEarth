package com.damagedearth.Entities.Components;

import com.damagedearth.DamagedEarth;
import com.damagedearth.Entities.EntityPlayer;
import com.damagedearth.GameElements.Quests.Components.BasicQuest;
import com.damagedearth.Gui.Components.GuiNPC;
import com.damagedearth.Utilities.MouseHelper;
import com.damagedearth.Utilities.NPCFileManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;

public class EntityNPC extends Entity
{
    //TODO: The file FW supports continuing quests that are in-complete, but if you complete the quest and save your info it won't load up next time. Fix it.

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
            if (MouseHelper.insideAreaW((int) this.x, (int) this.y, (int) this.width, (int) this.height) && this.thePlayer.nearEntity(this))
            {
                //Draws the speech bubble over the entity
                glRectd(this.x, this.y - this.height - 40, this.x + width, this.y - this.height - 2);
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
        glPushAttrib(GL_CURRENT_BIT);
        //Follow this format for OpenGL texture drawing
        glColor3f(0, 1, 0);
        glBegin(GL_QUADS);
        {
            glVertex2d(x, y);
            glVertex2d(x + width, y);
            glVertex2d(x + width, y - height);
            glVertex2d(x, y - height);
        }
        glEnd();
        glPopAttrib();
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
