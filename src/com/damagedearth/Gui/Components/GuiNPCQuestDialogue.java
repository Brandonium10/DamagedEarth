package com.damagedearth.Gui.Components;

import com.damagedearth.DamagedEarth;
import com.damagedearth.GameElements.Quests.Components.BasicQuest;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;

public class GuiNPCQuestDialogue
{
    /*
    TODO: Add support for entering in "before accept", "in complete", and "complete" dialogue for the dialogue box.
     */
    private String title;
    private String definition;
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean isSelected;
    private boolean isEnabled;
    private List<String> dialogueBeforeAccept = new ArrayList<String>();
    private List<String> dialogueIncomplete = new ArrayList<String>();
    private List<String> dialogueComplete = new ArrayList<String>();
    GuiScreen parentScreen;

    protected GuiButton done;
    protected GuiButton cancel;

    protected BasicQuest quest;
    protected boolean isQuestComplete;

    /**
     *
     * @param title The visible title of the selectable
     * @param definition The visible description of the selectable
     * @param isSelected Is the button selected?
     * @param parentScreen The parent NPC screen
     */
    public GuiNPCQuestDialogue(String title, String definition, boolean isSelected, final GuiScreen parentScreen)
    {
        this.title = title;
        this.definition = definition;
        this.isSelected = isSelected;
        this.parentScreen = parentScreen;
        this.isEnabled = true;
        this.isQuestComplete = false;

        done = new GuiButton("Okay", DamagedEarth.VIEW_CORDS_X + this.parentScreen.damagedEarth.width / 2 + this.parentScreen.damagedEarth.width / 4 + 2,
                DamagedEarth.VIEW_CORDS_Y + this.parentScreen.damagedEarth.height - 104,
                DamagedEarth.VIEW_CORDS_X + this.parentScreen.damagedEarth.width - 4, DamagedEarth.VIEW_CORDS_Y + this.parentScreen.damagedEarth.height - 4, new Runnable()
        {
            @Override
            public void run()
            {
                /*
                Changes the reaction of the "Okay" button based on whether or not the quest is complete
                 */
                if (isQuestComplete)
                {
                    parentScreen.damagedEarth.currentWorld.thePlayer.finishQuest(quest);
                    setEnabled(false);
                }
                else
                {
                    parentScreen.damagedEarth.currentWorld.thePlayer.acceptQuest(quest);
                }
                unSelect();
                parentScreen.damagedEarth.switchScreen(null);
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

        //Add these two buttons to the parent screen
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
        /*
        Renders button art and the dialogue box
         */
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
            for (GuiNPCQuestDialogue selectable : ((GuiNPC) this.parentScreen).selectableList)
            {
                if (selectable != this)
                {
                    selectable.unSelect();
                }
            }
            /*
            Enables the buttons when you select()
             */
            this.done.setEnabled(true);
            this.cancel.setEnabled(true);
        }
    }

    private void onUnSelect()
    {
        /*
        Disables the buttons when you unSelect()
         */
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
        if (this.isEnabled())
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

            if (this.quest.isComplete()) this.isQuestComplete = true;
        }
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

    public boolean isEnabled()
    {
        return isEnabled;
    }

    public void setEnabled(boolean enabled)
    {
        isEnabled = enabled;
    }

    public boolean isQuestComplete()
    {
        return isQuestComplete;
    }

    public void setQuestComplete(boolean questComplete)
    {
        isQuestComplete = questComplete;
    }

    public List<String> getDialogueBeforeAccept()
    {
        return dialogueBeforeAccept;
    }

    public void setDialogueBeforeAccept(List<String> dialogueBeforeAccept)
    {
        this.dialogueBeforeAccept = dialogueBeforeAccept;
    }

    public List<String> getDialogueIncomplete()
    {
        return dialogueIncomplete;
    }

    public void setDialogueIncomplete(List<String> dialogueIncomplete)
    {
        this.dialogueIncomplete = dialogueIncomplete;
    }

    public List<String> getDialogueComplete()
    {
        return dialogueComplete;
    }

    public void setDialogueComplete(List<String> dialogueComplete)
    {
        this.dialogueComplete = dialogueComplete;
    }
}
