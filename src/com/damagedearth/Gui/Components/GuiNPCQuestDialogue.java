/*******************************************************************************
 * This is a free program developed and managed by The Predators Development Team. This
 * source code should have a copy of the GNU General Public License. If it does not,
 * please see <http://www.gnu.org/licenses/>. The Predators Development Team forums is
 * located at <http://thepredators.endofinternet.net/index.php>.
 ******************************************************************************/

package com.damagedearth.Gui.Components;

import com.damagedearth.DamagedEarth;
import com.damagedearth.Gameplay.Quests.BasicQuest;
import com.damagedearth.Utilities.FontRenderer;
import com.damagedearth.Utilities.H2FontRenderer;
import com.damagedearth.Utilities.Tesselator;
import com.damagedearth.Worlds.BasicWorld;

public class GuiNPCQuestDialogue
{
    protected GuiButton done;
    protected GuiButton cancel;
    protected BasicQuest quest;
    protected boolean isQuestComplete;
    //Disables button if quest is turned in
    protected boolean isQuestTurnedIn;
    GuiScreen parentScreen;
    private String title;
    private String definition;
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean isSelected;
    private boolean isEnabled;

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

        done = new GuiButton("Accept", BasicWorld.VIEW_CORDS_X + this.parentScreen.damagedEarth.width / 2 + this.parentScreen.damagedEarth.width / 4 + 2,
                BasicWorld.VIEW_CORDS_Y + 224,
                245, 114, new Runnable()
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
        cancel = new GuiButton("Back", BasicWorld.VIEW_CORDS_X + this.parentScreen.damagedEarth.width / 2 + 4,
                BasicWorld.VIEW_CORDS_Y + 224,
                245, 114, new Runnable()
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
        Tesselator tesselator = new Tesselator();

        tesselator.set(this.x, this.y, this.x + this.width, this.y - this.height / 4);
        tesselator.setColor(0, 0, 0, 1);
        tesselator.startDrawingCQuad();
        tesselator.endDrawingQuad();
        tesselator.set(this.x, this.y - height, this.x + this.width, this.y - this.height + this.height / 4);
        tesselator.startDrawingCQuad();
        tesselator.endDrawingQuad();
        tesselator.setColor(0.5F, 0.5F, 0.5F, 0.5F);
        tesselator.set(BasicWorld.VIEW_CORDS_X + this.parentScreen.damagedEarth.width / 2 + 2, BasicWorld.VIEW_CORDS_Y + parentScreen.damagedEarth.height - 92, BasicWorld.VIEW_CORDS_X + this.parentScreen.damagedEarth.width - 2, BasicWorld.VIEW_CORDS_Y + 2);
        tesselator.startDrawingCQuad();
        tesselator.endDrawingQuad();

        DamagedEarth.fontRenderer.drawString(this.quest.getSlayingQuestInstance() != null ? "Kill " + this.quest.getSlayingQuestInstance().getInitialAmount() + " " + this.quest.getSlayingQuestInstance().getEnemyToKill() + (this.quest.getSlayingQuestInstance().getInitialAmount() > 1 ? "'s" : "") : "", BasicWorld.VIEW_CORDS_X + this.parentScreen.damagedEarth.width / 2 + 6, 440);
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
        Tesselator tesselator = new Tesselator();
        tesselator.set(x, y, x + width, y - height);
        tesselator.setColor(0.5f, 0.5f, 0.5f, 0.5f);
        tesselator.startDrawingCQuad();
        tesselator.endDrawingQuad();
        this.renderLore();
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
            done.setY(BasicWorld.VIEW_CORDS_Y + 120);
            cancel.setX(BasicWorld.VIEW_CORDS_X + this.parentScreen.damagedEarth.width / 2 + 4);
            cancel.setY(BasicWorld.VIEW_CORDS_Y + 120);

            this.render();
            if (this.isSelected) this.whileSelected();
            //Draw quest name on selectable
            DamagedEarth.fontRenderer.drawString(this.quest.getQuestName(), x, y - 27);

            if (this.quest.isComplete())
            {
                this.isQuestComplete = true;
                this.done.setName("Complete");
            }

            //Checks if the parent NPC has the quest in currentQuests. If it doesn't, the quest is turned in and this selectable will be disabled.
            if (!((GuiNPC) this.parentScreen).getNpc().getCurrentQuests().contains(quest)) this.isQuestTurnedIn = true;

            //This disables the "okay" button if the player is currently working on the quest
            if (parentScreen.damagedEarth.currentWorld.thePlayer.getOwnedQuests().contains(quest) && !quest.isComplete())
            {
                this.done.setEnabled(false);
            }
        }
    }

    private void renderLore()
    {
        Tesselator tesselator = new Tesselator();
        //Check to see if the player doesn't have the quest.
        //TODO: Render the quest lore
        if (!this.parentScreen.damagedEarth.currentWorld.thePlayer.getOwnedQuests().contains(this.quest))
        {

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
}
