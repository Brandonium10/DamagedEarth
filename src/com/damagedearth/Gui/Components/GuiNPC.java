package com.damagedearth.Gui.Components;

import com.damagedearth.DamagedEarth;
import com.damagedearth.Utilities.MouseHelper;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.glRectd;

public class GuiNPC extends GuiScreen
{
    protected List<GuiNPCQuestDialogue> selectableList = new ArrayList<GuiNPCQuestDialogue>();

    public GuiNPC(DamagedEarth damagedEarth, String displayName, GuiScreen parentScreen)
    {
        super(damagedEarth, displayName, parentScreen);
    }

    @Override
    public void setUpGUI()
    {
        super.setUpGUI();
    }

    @Override
    public void handleInput()
    {
        if (MouseHelper.getMouseClick(0))
        {
            for (GuiNPCQuestDialogue selectableDialogue : this.selectableList)
            {
                if (selectableDialogue.isEnabled())
                {
                    if (MouseHelper.insideAreaW(selectableDialogue.getX(), selectableDialogue.getY(), selectableDialogue.getWidth(), selectableDialogue.getHeight()))
                    {
                        System.out.println("Button has been clicked: " + mouseAction(0, selectableDialogue));
                    }
                }
            }
            for (GuiButton button : this.buttonList)
            {
                if (button.isEnabled)
                {
                    if (MouseHelper.insideAreaW(button.x, button.y, button.width, button.height))
                    {
                        System.out.println("Button has been clicked: " + mouseAction(0, button));
                    }
                }
            }
        }
        else if (MouseHelper.getMouseClick(1))
        {
            for (GuiNPCQuestDialogue selectableDialogue : this.selectableList)
            {
                if (selectableDialogue.isEnabled())
                {
                    if (MouseHelper.insideAreaW(selectableDialogue.getX(), selectableDialogue.getY(), selectableDialogue.getWidth(), selectableDialogue.getHeight()))
                    {
                        System.out.println("Button has been clicked: " + mouseAction(1, selectableDialogue));
                    }
                }
            }
            for (GuiButton button : this.buttonList)
            {
                if (button.isEnabled)
                {
                    if (MouseHelper.insideAreaW(button.x, button.y, button.width, button.height))
                    {
                        System.out.println("Button has been clicked: " + mouseAction(0, button));
                    }
                }
            }
        }

        if (this.checkKey(Keyboard.KEY_ESCAPE))
        {
            this.damagedEarth.switchScreen(parentScreen);
        }
    }

    public int mouseAction(int key, GuiNPCQuestDialogue selectableDialogue)
    {
        if (key == 0)
        {
            if (selectableDialogue.isSelected())
            {
                selectableDialogue.unSelect();
            }
            else
            {
                selectableDialogue.select();
            }
        }
        return 0;
    }

    @Override
    public void updateScreen()
    {
        int yOffset = DamagedEarth.VIEW_CORDS_Y + 194;
        this.drawGLScreen();

        for (GuiNPCQuestDialogue selectable : this.selectableList)
        {
            selectable.setX(DamagedEarth.VIEW_CORDS_X + 4);
            selectable.setWidth(this.damagedEarth.width / 2 - 6);
            selectable.setY(yOffset);
            selectable.setHeight(100);

            yOffset += 104;

            selectable.update();
        }

        for (GuiButton button : this.buttonList)
        {
            button.update();
        }
        this.handleInput();
    }

    @Override
    public void drawGLScreen()
    {
        //Make sure the rectangle is drawn in perspective with the scrolling cords
        glRectd(DamagedEarth.VIEW_CORDS_X + 2, DamagedEarth.VIEW_CORDS_Y + 92, DamagedEarth.VIEW_CORDS_X + this.damagedEarth.width / 2, DamagedEarth.VIEW_CORDS_Y + this.damagedEarth.height - 2);
    }

    public List<GuiNPCQuestDialogue> getSelectableList()
    {
        return selectableList;
    }
}
