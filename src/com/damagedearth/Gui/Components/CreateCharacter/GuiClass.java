package com.damagedearth.Gui.Components.CreateCharacter;

import com.damagedearth.Entities.EntityPlayer;
import com.damagedearth.Entities.EnumPlayerClass;
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
        glPushMatrix();

        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);

        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA); //ACT AS THOUGH YOUR LIFE DEPENDS ON THIS LINE!

        int id = TextureLoader.loadTexture(renderedImage);

        //Follow this format for OpenGL texture drawing
        glBegin(GL_QUADS);
        {
            glTexCoord2d(0, 0);
            glVertex2d(x, y);
            glTexCoord2d(1, 0);
            glVertex2d(x + width, y);
            glTexCoord2d(1, 1);
            glVertex2d(x + width, y - height);
            glTexCoord2d(0, 1);
            glVertex2d(x, y - height);
        }
        glEnd();

        glDisable(GL_TEXTURE_2D);
        glDisable(GL_BLEND);
        glDeleteTextures(id);

        glPopMatrix();
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
