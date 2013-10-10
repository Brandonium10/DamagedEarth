/*******************************************************************************
 * This is a free program developed and managed by The Predators Development Team. This
 * source code should have a copy of the GNU General Public License. If it does not,
 * please see <http://www.gnu.org/licenses/>. The Predators Development Team forums is
 * located at <http://thepredators.endofinternet.net/index.php>.
 ******************************************************************************/

package com.damagedearth.Gui.Components;

import com.damagedearth.DamagedEarth;
import com.damagedearth.Utilities.FontRenderer;
import com.damagedearth.Utilities.Tesselator;

public class GuiButton
{
    private int x = 0, y = 0, width = 0, height = 0;
    private float r = 0, g = 0, b = 0;
    private String name;
    private boolean isEnabled;
    private Runnable run;

    /*
    This constructor is for white buttons
     */
    public GuiButton(String name, int x, int y, int width, int height, Runnable run, boolean isEnabled)
    {
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.run = run;
        this.isEnabled = isEnabled;
        this.r = 1;
        this.g = 0;
        this.b = 0;
    }

    /**
     * @param name      The visible name of the button
     * @param x         The start x coordinates of the button
     * @param y         The start y coordinates of the button
     * @param run       The runnable for which will be called when you click the button
     * @param isEnabled If the button is enabled or not
     * @param r         The red value of the button
     * @param g         The green value of the button
     * @param b         The blue value of the button
     */
    public GuiButton(String name, int x, int y, int width, int height, Runnable run, boolean isEnabled, int r, int g, int b)
    {
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.run = run;
        this.isEnabled = isEnabled;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    private void drawButton()
    {
        Tesselator tesselator = new Tesselator();
        tesselator.set(x, y, x + width, y - height);
        tesselator.setColor(r, g, b);
        tesselator.startDrawingCQuad();
        tesselator.endDrawingQuad();

        DamagedEarth.fontRenderer.drawCenterString(name, x + width / 2, y - height / 2 - 12);
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
