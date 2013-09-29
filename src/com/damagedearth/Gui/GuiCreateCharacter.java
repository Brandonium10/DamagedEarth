package com.damagedearth.Gui;

import com.damagedearth.DamagedEarth;
import com.damagedearth.Entities.EnumPlayerClass;
import com.damagedearth.Gui.Components.CreateCharacter.GuiClass;
import com.damagedearth.Gui.Components.GuiButton;
import com.damagedearth.Gui.Components.GuiScreen;
import com.damagedearth.Utilities.MouseHelper;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.List;

public class GuiCreateCharacter extends GuiScreen
{
    List<GuiClass> classes = new ArrayList<GuiClass>();

    public GuiCreateCharacter(DamagedEarth damagedEarth, String displayName, GuiScreen parentScreen)
    {
        super(damagedEarth, displayName, parentScreen);
    }

    @Override
    protected boolean checkKey(int i)
    {
        return super.checkKey(i);
    }

    @Override
    public void setUpGUI()
    {
        super.setUpGUI();

        this.classes.add(new GuiClass(EnumPlayerClass.SCIENTIST, parentScreen.damagedEarth.width / 4, parentScreen.damagedEarth.height / 2, parentScreen.damagedEarth.currentWorld.thePlayer));
        this.classes.add(new GuiClass(EnumPlayerClass.SOLDIER, parentScreen.damagedEarth.width - parentScreen.damagedEarth.width / 4, parentScreen.damagedEarth.height / 2, parentScreen.damagedEarth.currentWorld.thePlayer));
        this.buttonList.add(new GuiButton("Enter world", parentScreen.damagedEarth.width / 4, parentScreen.damagedEarth.height - parentScreen.damagedEarth.height / 4, parentScreen.damagedEarth.width - parentScreen.damagedEarth.width / 4, parentScreen.damagedEarth.height - parentScreen.damagedEarth.height / 4 - 70, new Runnable()
        {
            @Override
            public void run()
            {
                if (damagedEarth.currentWorld.thePlayer.getPlayerClass() != null)
                {
                    damagedEarth.switchScreen(null);
                }
            }
        }, true, 0, 0, 255));
    }

    @Override
    protected void drawGLScreen()
    {
        super.drawGLScreen();
    }

    /**
     * GuiChooseClass reacts a different way when dealing with button coordinates. That's why a custom handleInput() is being used.
     */
    @Override
    protected void handleInput()
    {
        if (MouseHelper.getMouseClick(0))
        {
            for (GuiButton button : this.buttonList)
            {
                if (MouseHelper.insideAreaD(button.getX(), button.getY(), button.getWidth(), button.getHeight()))
                {
                    System.out.println("Button has been clicked: " + mouseAction(0, button));
                }
            }

            for (GuiClass currentClass : this.classes)
            {
                if (MouseHelper.insideAreaW(currentClass.getX(), currentClass.getY(), currentClass.getWidth(), currentClass.getHeight()))
                {
                    currentClass.onClick();
                }
            }
        }
        else if (MouseHelper.getMouseClick(1))
        {
            for (GuiButton button : this.buttonList)
            {
                if (MouseHelper.insideAreaD(button.getX(), button.getY(), button.getWidth(), button.getHeight()))
                {
                    System.out.println("Button has been clicked: " + mouseAction(1, button));
                }
            }
        }

        if (this.checkKey(Keyboard.KEY_ESCAPE))
        {
            this.damagedEarth.switchScreen(this.parentScreen);
        }
    }

    @Override
    protected int mouseAction(int key, GuiButton buttonClicked)
    {
        return super.mouseAction(key, buttonClicked);
    }

    @Override
    public void updateScreen()
    {
        super.updateScreen();

        for (GuiClass currentClass : this.classes)
        {
            currentClass.update();
        }
    }
}
