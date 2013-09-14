package com.damagedearth;

import com.damagedearth.Gui.Components.GuiScreen;
import com.damagedearth.Gui.GuiMainMenu;
import com.damagedearth.Worlds.BasicWorld;
import com.damagedearth.Worlds.CrystalForest;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

public class DamagedEarth
{

    public int width;
    public int height;

    public static int VIEW_CORDS_X = 0;
    public static int VIEW_CORDS_Y = 0;
    public static double TRANSLATE_MODIFIER_X = 0;
    public static double TRANSLATE_MODIFIER_Y = 0;

    public GuiScreen currentScreen;
    public BasicWorld currentWorld;

    public DamagedEarth(int width, int height)
    {
        this.width = width;
        this.height = height;
    }

    public void startGame()
    {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("~~~GAME START INITIATED~~~");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~");
        //Loads the CrystalForest world on start up
        this.loadWorld(new CrystalForest(100, 100, this));
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
            glViewport(0, 0, (int) this.width, (int) this.height);
            glMatrixMode(GL_PROJECTION);
            glLoadIdentity();
            glOrtho(0, width, height, 0, -1, 1);
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
                glTranslated(-TRANSLATE_MODIFIER_X, -TRANSLATE_MODIFIER_Y, 0);
            }
            glPushMatrix();

            TRANSLATE_MODIFIER_X = 0;
            TRANSLATE_MODIFIER_Y = 0;

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
