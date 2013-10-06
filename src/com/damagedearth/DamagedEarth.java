/*******************************************************************************
 * This is a free program developed and managed by The Predators Development Team. This
 * source code should have a copy of the GNU General Public License. If it does not,
 * please see <http://www.gnu.org/licenses/>. The Predators Development Team forums is
 * located at <http://thepredators.endofinternet.net/index.php>.
 ******************************************************************************/

package com.damagedearth;

import com.damagedearth.Gui.Components.GuiScreen;
import com.damagedearth.Gui.GuiMainMenu;
import com.damagedearth.Utilities.PlayerFileManager;
import com.damagedearth.Worlds.BasicWorld;
import com.damagedearth.Worlds.CrystalForest;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;

public class DamagedEarth
{
    public int width;
    public int height;

    public GuiScreen currentScreen;
    public BasicWorld currentWorld;

    //An instance of the player manager. Records all information related to the player
    public PlayerFileManager plyrManager;

    public DamagedEarth(int width, int height)
    {
        this.width = width;
        this.height = height;
        this.plyrManager = new PlayerFileManager("player-location", "player-quests", "player-class", this);
    }

    public void startGame()
    {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("~~~GAME START INITIATED~~~");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~");
        //Loads the CrystalForest world on start up
        this.loadWorld(new CrystalForest(100, 800, this));
        this.loadDisplay();

        //This is the game update. Rendering is done here along with other tasks that need to be called repeatedly
        while (!Display.isCloseRequested())
        {
            this.update();
            Display.update();
            //Cuts off un-necessary FPS. Reduces CPU Power needed to run this game
            Display.sync(60);
        }

        //Quits the game
        Display.destroy();
        System.out.println("~~~~~~~~~~~~~~~~~");
        System.out.println("~~~GAME ENDING~~~");
        System.out.println("~~~~~~~~~~~~~~~~~");
        System.exit(0);
    }

    //Loads the display
    public void loadDisplay()
    {
        try
        {
            Display.setDisplayMode(new DisplayMode(width, height));
            Display.setResizable(false);
            Display.create();
            GL11.glEnable(GL11.GL_COLOR_MATERIAL);
            GL11.glDisable(GL11.GL_DITHER);
            GL11.glEnable(GL11.GL_NORMALIZE); // calculated normals when scaling
            GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST); // High quality visuals
            GL11.glHint(GL11.GL_POLYGON_SMOOTH_HINT, GL11.GL_NICEST); //  Really Nice Perspective Calculations
            glViewport(0, 0, (int) this.width, (int) this.height);
            glMatrixMode(GL_PROJECTION);
            glLoadIdentity();
            glOrtho(0, width, 0, height, 0, 25);
            glMatrixMode(GL_MODELVIEW);
            //This switches to the main menu on startGame up
            this.switchScreen(new GuiMainMenu(this, "DamagedEarth"));
        }
        catch (LWJGLException e)
        {
            e.printStackTrace();
        }
    }

    /*
    Main game update
     */
    public void update()
    {
        glClear(GL_COLOR_BUFFER_BIT);

        if (this.currentScreen == null)
        {
            glPopMatrix();
            {
                glTranslated(-BasicWorld.TRANSLATE_MODIFIER_X, -BasicWorld.TRANSLATE_MODIFIER_Y, 0);
            }
            glPushMatrix();

            BasicWorld.TRANSLATE_MODIFIER_X = 0;
            BasicWorld.TRANSLATE_MODIFIER_Y = 0;

            //This will update and (re)render the current world
            this.currentWorld.updateAndRender();
        }
        else
        {
            //This will update the current screen
            this.currentScreen.updateScreen();
        }
    }

    public void switchScreen(GuiScreen screen)
    {
        /*
        Switches the current gui screen.
         */
        try
        {
            if (screen == null)
            {
                this.currentScreen = null;
                return;
            }
            this.currentScreen = screen;
            this.currentScreen.setUpGUI();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void loadWorld(BasicWorld world)
    {
        /*
        This loads the world and all entities within it. This will not switch to the world until you call switchScreen(null)
         */
        System.out.println("Loading world: " + world.getClass().getName());
        this.currentWorld = world;
        this.currentWorld.loadWorld();
        System.out.println("Loaded world: " + world.getClass().getName());
    }
}
