/*******************************************************************************
 * This is a free program developed and managed by The Predators Development Team. This
 * source code should have a copy of the GNU General Public License. If it does not,
 * please see <http://www.gnu.org/licenses/>. The Predators Development Team forums is
 * located at <http://thepredators.endofinternet.net/index.php>.
 ******************************************************************************/

package com.damagedearth.Gui.Components;

import com.damagedearth.DamagedEarth;
import com.damagedearth.Utilities.Components.TrueTypeFont;
import com.damagedearth.Utilities.MouseHelper;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.List;

public class GuiScreen
{
    public DamagedEarth damagedEarth;
    private boolean keyStates[];

    protected List<GuiButton> buttonList = new ArrayList<GuiButton>();

    protected GuiScreen parentScreen = null;
    public String displayName;
    TrueTypeFont fontRenderer;

    /**
     * @param damagedEarth An instance of Damaged Earth
     * @param displayName  The visible name of the GUI
     * @param parentScreen The parent screen of the gui
     */
    public GuiScreen(DamagedEarth damagedEarth, String displayName, GuiScreen parentScreen)
    {
        this.damagedEarth = damagedEarth;
        this.displayName = displayName;
        this.parentScreen = parentScreen;
        this.keyStates = new boolean[256];
    }

    public GuiScreen(DamagedEarth damagedEarth, String displayName)
    {
        this.damagedEarth = damagedEarth;
        this.displayName = displayName;
        this.keyStates = new boolean[256];
    }

    /*
    Sets up the GUI
     */
    public void setUpGUI()
    {

    }

    /*
    Renders special art onto the screen, not required.
     */
    protected void drawGLScreen()
    {
    }

    /*
    Handles the click input of all the buttons on the screen by checking the location of where you clicked.
    If that location is inside a button's area then mouseAction() will be called for that button.
     */
    protected void handleInput()
    {
        if (MouseHelper.getMouseClick(0))
        {
            for (GuiButton button : this.buttonList)
            {
                if (button.getEnabled())
                {
                    if (MouseHelper.insideArea(button.getX(), button.getY(), button.getWidth(), button.getHeight()))
                    {
                        System.out.println("Button has been clicked: " + mouseAction(0, button));
                    }
                }
            }
        }
        else if (MouseHelper.getMouseClick(1))
        {
            for (GuiButton button : this.buttonList)
            {
                if (button.getEnabled())
                {
                    if (MouseHelper.insideArea(button.getX(), button.getY(), button.getWidth(), button.getHeight()))
                    {
                        System.out.println("Button has been clicked: " + mouseAction(1, button));
                    }
                }
            }
        }

        if (this.checkKey(Keyboard.KEY_ESCAPE))
        {
            this.damagedEarth.switchScreen(this.parentScreen);
        }
    }

    /*
    Runs the button's click() or rightClick() methos based on whether or not you clicked
    or right clicked.
     */
    protected int mouseAction(int key, GuiButton buttonClicked)
    {
        if (buttonClicked.getEnabled())
        {
            if (key == 0)
            {
                buttonClicked.click();
            }
            else if (key == 1)
            {
                buttonClicked.rightClick();
            }
            return 0;
        }
        else
        {
            return 1;
        }
    }

    /*
    Updates the screen: handles input, draws the screen, and updates the buttons
     */
    public void updateScreen()
    {
        try
        {
            this.drawGLScreen();
            for (GuiButton button : this.buttonList)
            {
                button.update();
            }
            this.handleInput();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /*
    Special key press method
     */
    protected boolean checkKey(int i)
    {
        return Keyboard.isKeyDown(i) != keyStates[i] && (keyStates[i] = !keyStates[i]);
    }
}
