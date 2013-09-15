package com.damagedearth.Gui.Components;

import com.damagedearth.DamagedEarth;
import com.damagedearth.GameElements.Quests.Components.BasicQuest;

import static org.lwjgl.opengl.GL11.*;

public class GuiNPCDialogue
{
    /*
    TODO: Add support for accepting quests
     */
    private String title;
    private String definition;
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean isSelected;
    GuiScreen parentScreen;

    protected GuiButton done;
    protected GuiButton cancel;

    protected BasicQuest quest;

    public GuiNPCDialogue(String title, String definition, boolean isSelected, final GuiScreen parentScreen)
    {
        this.title = title;
        this.definition = definition;
        this.isSelected = isSelected;
        this.parentScreen = parentScreen;

        done = new GuiButton("Okay", DamagedEarth.VIEW_CORDS_X + this.parentScreen.damagedEarth.width / 2 + this.parentScreen.damagedEarth.width / 4 + 2,
                DamagedEarth.VIEW_CORDS_Y + this.parentScreen.damagedEarth.height - 104,
                DamagedEarth.VIEW_CORDS_X + this.parentScreen.damagedEarth.width - 4, DamagedEarth.VIEW_CORDS_Y + this.parentScreen.damagedEarth.height - 4, new Runnable()
        {
            @Override
            public void run()
            {
                parentScreen.damagedEarth.switchScreen(null);
                parentScreen.damagedEarth.currentWorld.thePlayer.acceptQuest(quest);
            }
        }, false, 0, 255, 0);
        cancel = new GuiButton("Cancel", DamagedEarth.VIEW_CORDS_X + this.parentScreen.damagedEarth.width / 2 + 4,
                DamagedEarth.VIEW_CORDS_Y + this.parentScreen.damagedEarth.height - 104,
                DamagedEarth.VIEW_CORDS_X + this.parentScreen.damagedEarth.width / 2 + this.parentScreen.damagedEarth.width / 4 - 2, DamagedEarth.VIEW_CORDS_Y + this.parentScreen.damagedEarth.height - 4, new Runnable()
        {
            @Override
            public void run()
            {
                unSelect();
            }
        }, false, 255, 0, 0);

        this.parentScreen.buttonList.add(done);
        this.parentScreen.buttonList.add(cancel);
    }

    public void select()
    {
        this.isSelected = true;
        this.onSelect();
    }

    public void unSelect()
    {
        this.isSelected = false;
        this.onUnSelect();
    }

    /*
    This method draws the button selected art and the dialogue box on the right
     */
    private void whileSelected()
    {
        glPushMatrix();
        glPushAttrib(GL_CURRENT_BIT);
        glColor4f(0, 0, 0, 1);
        glRectf(this.x, this.y, this.x + this.width, this.y - this.height / 4);
        glRectf(this.x, this.y - height, this.x + this.width, this.y - this.height + this.height / 4);
        glColor4f(1, 1, 1, 0);
        glRectf(DamagedEarth.VIEW_CORDS_X + this.parentScreen.damagedEarth.width / 2 + 2, DamagedEarth.VIEW_CORDS_Y + 92, DamagedEarth.VIEW_CORDS_X + this.parentScreen.damagedEarth.width - 2, DamagedEarth.VIEW_CORDS_Y + this.parentScreen.damagedEarth.height - 2);
        glPopAttrib();
        glPopMatrix();
    }

    private void onSelect()
    {
        /*
        This will deselect all other selectables when this one is selected.
         */
        if (this.parentScreen instanceof GuiNPC)
        {
            for (GuiNPCDialogue selectable : ((GuiNPC) this.parentScreen).selectableList)
            {
                if (selectable != this)
                {
                    selectable.unSelect();
                }
            }
            this.done.setEnabled(true);
            this.cancel.setEnabled(true);
        }
    }

    private void onUnSelect()
    {
        this.done.setEnabled(false);
        this.cancel.setEnabled(false);
    }

    private void render()
    {
        try
        {
            glPushMatrix();
            glPushAttrib(GL_CURRENT_BIT);
            glColor4f(0.5f, 0.5f, 0.5f, 0.5f);
            glRecti(this.x, this.y, this.x + this.width, this.y - this.height);
            glPopAttrib();
            glPopMatrix();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /*
    Updates the button, calls render() and whileSelected() if the button is selected
     */
    public void update()
    {
        //Updates the buttons positions with the scrolling cords
        done.setX(DamagedEarth.VIEW_CORDS_X + this.parentScreen.damagedEarth.width / 2 + this.parentScreen.damagedEarth.width / 4 + 2);
        done.setY(DamagedEarth.VIEW_CORDS_Y + this.parentScreen.damagedEarth.height - 104);
        done.setWidth(DamagedEarth.VIEW_CORDS_X + this.parentScreen.damagedEarth.width - 4 - done.getX());
        done.setHeight(DamagedEarth.VIEW_CORDS_Y + this.parentScreen.damagedEarth.height - 4 - done.getY());
        cancel.setX(DamagedEarth.VIEW_CORDS_X + this.parentScreen.damagedEarth.width / 2 + 4);
        cancel.setY(DamagedEarth.VIEW_CORDS_Y + this.parentScreen.damagedEarth.height - 104);
        cancel.setWidth(DamagedEarth.VIEW_CORDS_X + this.parentScreen.damagedEarth.width / 2 + this.parentScreen.damagedEarth.width / 4 - 2 - cancel.getX());
        cancel.setHeight(DamagedEarth.VIEW_CORDS_Y + this.parentScreen.damagedEarth.height - 4 - cancel.getY());

        this.render();
        if (this.isSelected) this.whileSelected();
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getDefinition()
    {
        return definition;
    }

    public void setDefinition(String definition)
    {
        this.definition = definition;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public BasicQuest getQuest()
    {
        return quest;
    }

    public void setQuest(BasicQuest quest)
    {
        this.quest = quest;
    }

    public boolean isSelected()
    {
        return isSelected;
    }
}
