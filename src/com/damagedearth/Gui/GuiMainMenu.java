package com.damagedearth.Gui;

import com.damagedearth.DamagedEarth;
import com.damagedearth.Gui.Components.GuiButton;
import com.damagedearth.Gui.Components.GuiScreen;
import com.damagedearth.Utilities.MouseHelper;
import org.lwjgl.input.Keyboard;

public class GuiMainMenu extends GuiScreen
{
    public GuiMainMenu(DamagedEarth damagedEarth, String displayName, GuiScreen parentScreen)
    {
        super(damagedEarth, displayName, parentScreen);
    }

    public GuiMainMenu(DamagedEarth damagedEarth, String displayName)
    {
        super(damagedEarth, displayName);
    }

    /**
     * GuiMainMenu reacts a different way when dealing with button coordinates. That's why a custom handleInput() is being used.
     */
    @Override
    public void handleInput()
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
    public void setUpGUI()
    {
        super.setUpGUI();
        final GuiCreateCharacter guiCreateCharacter = new GuiCreateCharacter(damagedEarth, "Choose class", this);
        this.buttonList.add(new GuiButton("Start Game", damagedEarth.width / 4, damagedEarth.height / 4, damagedEarth.width - damagedEarth.width / 4, damagedEarth.height / 4 - 35, new Runnable()
        {
            @Override
            public void run()
            {
                if (damagedEarth.plyrManager.loadClass(damagedEarth.currentWorld.thePlayer))
                {
                    damagedEarth.switchScreen(null);
                }
                else
                {
                    damagedEarth.switchScreen(guiCreateCharacter);
                }
            }
        }, true));

        this.buttonList.add(new GuiButton("Quit Game", damagedEarth.width / 4, damagedEarth.height - damagedEarth.height / 4, damagedEarth.width - damagedEarth.width / 4, damagedEarth.height - damagedEarth.height / 4 - 35, new Runnable()
        {
            @Override
            public void run()
            {
                System.exit(0);
            }
        }, true));
    }

    @Override
    public void drawGLScreen()
    {
        super.drawGLScreen();
    }
}
