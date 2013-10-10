package com.damagedearth.Gui;

import com.damagedearth.DamagedEarth;
import com.damagedearth.Gui.Components.GuiButton;
import com.damagedearth.Gui.Components.GuiScreen;
import com.damagedearth.Utilities.FontRenderer;

public class GuiIntro extends GuiScreen
{
    public GuiIntro(DamagedEarth damagedEarth, String displayName, GuiScreen parentScreen)
    {
        super(damagedEarth, displayName, parentScreen);
    }

    public GuiIntro(DamagedEarth damagedEarth, String displayName)
    {
        super(damagedEarth, displayName);
    }

    @Override
    public void setUpGUI()
    {
        super.setUpGUI();
    }

    @Override
    protected void drawGLScreen()
    {
        super.drawGLScreen();

        FontRenderer fontRenderer = DamagedEarth.fontRenderer;
    }

    @Override
    protected void handleInput()
    {
        super.handleInput();
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
    }

    @Override
    protected boolean checkKey(int i)
    {
        return super.checkKey(i);
    }
}
