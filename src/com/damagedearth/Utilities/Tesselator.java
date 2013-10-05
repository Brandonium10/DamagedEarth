package com.damagedearth.Utilities;

import com.damagedearth.Utilities.Components.TrueTypeFont;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.lwjgl.opengl.GL11.*;

public class Tesselator
{
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private float r;
    private float g;
    private float b;
    private float a;

    private int id;

    private static String defaultFont = "Times New Roman";
    private static int defaultFontSize = 25;
    private static Font f = new Font(defaultFont, Font.PLAIN, defaultFontSize);
    private static TrueTypeFont trueTypeFont = new TrueTypeFont(f, true);

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

    public void drawString(String s, double x, double y)
    {
        trueTypeFont.drawString((float) x, (float) y, s, 1.0F, 1.0F);
    }

    public void drawString(String s, double x, double y, String fontName)
    {
        trueTypeFont.setFont(new Font(fontName, Font.PLAIN, defaultFontSize));
        trueTypeFont.drawString((float) x, (float) y, s, 1.0F, 1.0F);
        trueTypeFont.setFont(new Font("defaultFont", Font.PLAIN, defaultFontSize));
    }

    public void drawString(String s, double x, double y, int fontSize)
    {
        trueTypeFont.setFontSize(fontSize);
        trueTypeFont.drawString((float) x, (float) y, s, 1.0F, 1.0F);
        trueTypeFont.setFontSize(defaultFontSize);
    }

    public void drawString(String s, double x, double y, String fontName, int fontSize)
    {
        trueTypeFont.setFont(new Font(fontName, Font.PLAIN, fontSize));
        trueTypeFont.drawString((float) x, (float) y, s, 1.0F, 1.0F);
        trueTypeFont.setFont(new Font("defaultFont", Font.PLAIN, defaultFontSize));
    }

    public void drawCenterString(String s, double x, double y)
    {
        trueTypeFont.drawString((float) x, (float) y, s, 1.0F, 1.0F, TrueTypeFont.ALIGN_CENTER);
    }

    public void drawCenterString(String s, double x, double y, String fontName)
    {
        trueTypeFont.setFont(new Font(fontName, Font.PLAIN, defaultFontSize));
        trueTypeFont.drawString((float) x, (float) y, s, 1.0F, 1.0F, TrueTypeFont.ALIGN_CENTER);
        trueTypeFont.setFont(new Font("defaultFont", Font.PLAIN, defaultFontSize));
    }

    public void drawCenterString(String s, double x, double y, int fontSize)
    {
        trueTypeFont.setFontSize(fontSize);
        trueTypeFont.drawString((float) x, (float) y, s, 1.0F, 1.0F, TrueTypeFont.ALIGN_CENTER);
        trueTypeFont.setFontSize(defaultFontSize);
    }

    public void drawCenterString(String s, double x, double y, String fontName, int fontSize)
    {
        trueTypeFont.setFont(new Font(fontName, Font.PLAIN, fontSize));
        trueTypeFont.drawString((float) x, (float) y, s, 1.0F, 1.0F, TrueTypeFont.ALIGN_CENTER);
        trueTypeFont.setFont(new Font("defaultFont", Font.PLAIN, defaultFontSize));
    }

    public static TrueTypeFont getTrueTypeFont()
    {
        return trueTypeFont;
    }

    public static void setTrueTypeFont(TrueTypeFont trueTypeFont)
    {
        Tesselator.trueTypeFont = trueTypeFont;
    }
}
