package com.damagedearth.Gui;

import com.damagedearth.DamagedEarth;
import com.damagedearth.Gui.Components.GuiButton;
import com.damagedearth.Gui.Components.GuiScreen;

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
        super.handleInput();
    }

    @Override
    public void setUpGUI()
    {
        super.setUpGUI();
        final GuiCreateCharacter guiCreateCharacter = new GuiCreateCharacter(damagedEarth, "Choose class", this);
        this.buttonList.add(new GuiButton("Start Game", damagedEarth.width / 4, damagedEarth.height - damagedEarth.height / 4, 500, 50, new Runnable()
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

        this.buttonList.add(new GuiButton("Quit Game", damagedEarth.width / 4, damagedEarth.height / 4, 500, 50, new Runnable()
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
