package com.damagedearth.Gui.Components;

import com.damagedearth.GameElements.Quests.Components.BasicQuest;
import com.damagedearth.Worlds.BasicWorld;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;

public class GuiNPCQuestDialogue
{
    protected GuiButton done;
    protected GuiButton cancel;
    protected BasicQuest quest;
    protected boolean isQuestComplete;
    //Disables button if quest is turned in
    protected boolean isQuestTurnedIn;
    GuiScreen parentScreen;
    /**
     * TODO: Add support for entering in "before accept", "in complete", and "complete" dialogue for the dialogue box.
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

    /**
     * @param title        The visible title of the selectable
     * @param definition   The visible description of the selectable
     * @param isSelected   Is the button selected?
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
        this.isQuestTurnedIn = false;

        done = new GuiButton("Okay", BasicWorld.VIEW_CORDS_X + this.parentScreen.damagedEarth.width / 2 + this.parentScreen.damagedEarth.width / 4 + 2,
                BasicWorld.VIEW_CORDS_Y + this.parentScreen.damagedEarth.height - 104,
                BasicWorld.VIEW_CORDS_X + this.parentScreen.damagedEarth.width - 4, BasicWorld.VIEW_CORDS_Y + this.parentScreen.damagedEarth.height - 4, new Runnable()
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
                    //Disables this selectable because the quest has been turned in
                }
                else
                {
                    parentScreen.damagedEarth.currentWorld.thePlayer.acceptQuest(quest);
                }
                unSelect();
                parentScreen.damagedEarth.switchScreen(null);
            }
        }, false, 0, 255, 0);
        cancel = new GuiButton("Cancel", BasicWorld.VIEW_CORDS_X + this.parentScreen.damagedEarth.width / 2 + 4,
                BasicWorld.VIEW_CORDS_Y + this.parentScreen.damagedEarth.height - 104,
                BasicWorld.VIEW_CORDS_X + this.parentScreen.damagedEarth.width / 2 + this.parentScreen.damagedEarth.width / 4 - 2, BasicWorld.VIEW_CORDS_Y + this.parentScreen.damagedEarth.height - 4, new Runnable()
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
        glRectf(BasicWorld.VIEW_CORDS_X + this.parentScreen.damagedEarth.width / 2 + 2, BasicWorld.VIEW_CORDS_Y + 92, BasicWorld.VIEW_CORDS_X + this.parentScreen.damagedEarth.width - 2, BasicWorld.VIEW_CORDS_Y + this.parentScreen.damagedEarth.height - 2);
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
            //If the quest is turned in this selectable along w/ all its buttons will be disabled.
            if (this.isQuestTurnedIn)
            {
                this.setEnabled(false);
                this.done.setEnabled(false);
                this.cancel.setEnabled(false);
                return;
            }

            //Updates the buttons positions with the scrolling cords
            done.setX(BasicWorld.VIEW_CORDS_X + this.parentScreen.damagedEarth.width / 2 + this.parentScreen.damagedEarth.width / 4 + 2);
            done.setY(BasicWorld.VIEW_CORDS_Y + this.parentScreen.damagedEarth.height - 104);
            done.setWidth(BasicWorld.VIEW_CORDS_X + this.parentScreen.damagedEarth.width - 4 - done.getX());
            done.setHeight(BasicWorld.VIEW_CORDS_Y + this.parentScreen.damagedEarth.height - 4 - done.getY());
            cancel.setX(BasicWorld.VIEW_CORDS_X + this.parentScreen.damagedEarth.width / 2 + 4);
            cancel.setY(BasicWorld.VIEW_CORDS_Y + this.parentScreen.damagedEarth.height - 104);
            cancel.setWidth(BasicWorld.VIEW_CORDS_X + this.parentScreen.damagedEarth.width / 2 + this.parentScreen.damagedEarth.width / 4 - 2 - cancel.getX());
            cancel.setHeight(BasicWorld.VIEW_CORDS_Y + this.parentScreen.damagedEarth.height - 4 - cancel.getY());

            this.render();
            if (this.isSelected) this.whileSelected();

            if (this.quest.isComplete()) this.isQuestComplete = true;

            //Checks if the parent NPC has the quest in currentQuests. If it doesn't, the quest is turned in and this selectable will be disabled.
            if (!((GuiNPC) this.parentScreen).getNpc().getCurrentQuests().contains(quest)) this.isQuestTurnedIn = true;

            //This disables the "okay" button if the player is currently working on the quest
            if (parentScreen.damagedEarth.currentWorld.thePlayer.getOwnedQuests().contains(quest) && !quest.isComplete())
            {
                this.done.setEnabled(false);
            }
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
