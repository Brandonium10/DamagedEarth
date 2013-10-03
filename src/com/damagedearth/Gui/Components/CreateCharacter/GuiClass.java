package com.damagedearth.Gui.Components.CreateCharacter;

import com.damagedearth.Entities.EntityPlayer;
import com.damagedearth.Entities.EnumPlayerClass;
import com.damagedearth.Utilities.Tesselator;
import com.damagedearth.Utilities.TextureLoader;

import java.awt.image.BufferedImage;

import static org.lwjgl.opengl.GL11.*;

public class GuiClass
{
    private EnumPlayerClass enumPlayerClass;
    private int x;
    private int y;
    private int width;
    private int height;
    private BufferedImage renderedImage;
    private EntityPlayer player;

    public GuiClass(EnumPlayerClass enumPlayerClass, int x, int y, EntityPlayer player)
    {
        this.enumPlayerClass = enumPlayerClass;
        this.x = x;
        this.y = y;
        this.player = player;
        this.width = 60;
        this.height = 65;
        this.renderedImage = enumPlayerClass.getClassImage();
    }

    public void update()
    {
        render();
    }

    private void render()
    {
        Tesselator tesselator = new Tesselator();
        tesselator.set(x, y, x + width, y - height);
        tesselator.startDrawingTextQuad(renderedImage);
        tesselator.endDrawingTextQuad();
    }

    public void onClick()
    {
        player.setPlayerClass(enumPlayerClass);
        player.getPlyManager().rewriteClass(enumPlayerClass);
        System.out.println("[Player] Player has chosen the class " + enumPlayerClass.toString());
    }

    public BufferedImage getRenderedImage()
    {
        return renderedImage;
    }

    public void setRenderedImage(BufferedImage renderedImage)
    {
        this.renderedImage = renderedImage;
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

    public EnumPlayerClass getEnumPlayerClass()
    {
        return enumPlayerClass;
    }
}
