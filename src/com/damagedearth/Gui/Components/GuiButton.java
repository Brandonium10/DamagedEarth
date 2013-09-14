package com.damagedearth.Gui.Components;

import com.damagedearth.Utilities.MouseHelper;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glPopMatrix;

public class GuiButton
{
    int x = 0, y = 0, width = 0, height = 0;
    float r = 0, g = 0, b = 0;
    String name;
    boolean isEnabled;
    Runnable run;

    /*
    This constructor is for white buttons
     */
    public GuiButton(String name, int x, int y, int x2, int y2, Runnable run, boolean isEnabled)
    {
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = x2 - x;
        this.height = y2 - y;
        this.run = run;
        this.isEnabled = isEnabled;
        this.r = 255;
        this.g = 255;
        this.b = 255;
    }

    /*
    This constructor is for custom color buttons
     */
    public GuiButton(String name, int x, int y, int x2, int y2, Runnable run, boolean isEnabled, int r, int g, int b)
    {
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = x2 - x;
        this.height = y2 - y;
        this.run = run;
        this.isEnabled = isEnabled;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    private void drawButton()
    {
        glPushAttrib(GL_CURRENT_BIT);

        //This will be the normal image because he's holding a sword to his side.
        //BufferedImage buttonImage = TextureLoader.loadImage();

        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);

        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA); //ACT AS THOUGH YOUR LIFE DEPENDS ON THIS LINE!

        //int id = TextureLoader.loadTexture(buttonImage);

        //Follow this format for OpenGL texture drawing
        glColor3f(r, g, b);
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
        //glDeleteTextures(id);

        glPopAttrib();
    }

    public void click()
    {
        run.run();
    }

    public void rightClick()
    {

    }

    public void update()
    {
        if (this.getEnabled())
        {
            this.drawButton();
        }
    }

    public boolean getEnabled()
    {
        return this.isEnabled;
    }

    public void setEnabled(boolean enabled)
    {
        this.isEnabled = enabled;
    }

    public Runnable getRun()
    {
        return run;
    }

    public void setRun(Runnable run)
    {
        this.run = run;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public float getB()
    {
        return b;
    }

    public void setB(float b)
    {
        this.b = b;
    }

    public float getG()
    {
        return g;
    }

    public void setG(float g)
    {
        this.g = g;
    }

    public float getR()
    {
        return r;
    }

    public void setR(float r)
    {
        this.r = r;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }
}
