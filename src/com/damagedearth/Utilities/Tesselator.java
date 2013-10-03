package com.damagedearth.Utilities;

import java.awt.image.BufferedImage;

import static org.lwjgl.opengl.GL11.*;

public class Tesselator
{
    double x1;
    double y1;
    double x2;
    double y2;
    float r;
    float g;
    float b;
    float a;

    int id;

    public Tesselator()
    {

    }

    public void set(double x1, double y1, double x2, double y2)
    {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public void setColor(float r, float g, float b)
    {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public void setColor(float r, float g, float b, float a)
    {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    /*
    This method is for drawing quads without color
     */
    public void startDrawingQuad()
    {
        glPushAttrib(GL_CURRENT_BIT);

        //Follow this format for OpenGL texture drawing
        glBegin(GL_QUADS);
        {
            glVertex2d(x1, y1);
            glVertex2d(x1, y2);
            glVertex2d(x2, y2);
            glVertex2d(x2, y1);
        }
    }

    /*
    This method is for drawing quads with color
     */
    public void startDrawingCQuad()
    {
        glPushAttrib(GL_CURRENT_BIT);
        glColor4f(r, g, b, a);

        //Follow this format for OpenGL texture drawing
        glBegin(GL_QUADS);
        {
            glVertex2d(x1, y1);
            glVertex2d(x1, y2);
            glVertex2d(x2, y2);
            glVertex2d(x2, y1);
        }
    }

    public void endDrawingQuad()
    {
        glEnd();
        glPopAttrib();
    }

    /**
     * This method is for rendering images onto the screen.
     *
     * @param image The image to render
     */
    public void startDrawingTextQuad(BufferedImage image)
    {
        glPushMatrix();

        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);

        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA); //ACT AS THOUGH YOUR LIFE DEPENDS ON THIS LINE!

        id = TextureLoader.loadTexture(image);

        //Follow this format for OpenGL texture drawing
        glBegin(GL_QUADS);
        {
            glTexCoord2d(0, 0);
            glVertex2d(x1, y1);
            glTexCoord2d(1, 0);
            glVertex2d(x2, y1);
            glTexCoord2d(1, 1);
            glVertex2d(x2, y2);
            glTexCoord2d(0, 1);
            glVertex2d(x1, y2);
        }
    }

    public void endDrawingTextQuad()
    {
        glEnd();

        glDisable(GL_TEXTURE_2D);
        glDisable(GL_BLEND);
        glDeleteTextures(id);

        glPopMatrix();
    }
}
