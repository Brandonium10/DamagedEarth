package com.damagedearth.Utilities;

import com.damagedearth.Utilities.Components.TrueTypeFont;

import java.awt.*;

public class H2FontRenderer
{
    private Font f;
    private TrueTypeFont trueTypeFont;

    public H2FontRenderer()
    {
        f = new Font("Times New Roman", Font.PLAIN, 36);
        trueTypeFont = new TrueTypeFont(f, true);
    }

    public void drawString(String s, double x, double y)
    {
        trueTypeFont.drawString((float) x, (float) y, s, 1.0F, 1.0F);
    }

    public void drawCenterString(String s, double x, double y)
    {
        trueTypeFont.drawString((float) x, (float) y, s, 1.0F, 1.0F, TrueTypeFont.ALIGN_CENTER);
    }
}
