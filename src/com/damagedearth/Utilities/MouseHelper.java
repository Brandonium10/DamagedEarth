package com.damagedearth.Utilities;

import com.damagedearth.Worlds.BasicWorld;
import org.lwjgl.input.Mouse;

public class MouseHelper
{
    static boolean mouseStates[] = new boolean[2];

    /*
    Sees if the mouse coordinates are within an area on the display
     */
    public static boolean insideAreaD(int x, int y, int width, int height)
    {

        int leftSide = x;
        int rightSide = x + width;
        int topSide = y;
        int bottomSide = y - height;

        int mouseX = Mouse.getX();
        int mouseY = 700 - Mouse.getY();

        /*
        System.out.printf("Left Side: %s\n", leftSide);
        System.out.printf("Right Side: %s\n", rightSide);
        System.out.printf("Top Side: %s\n", topSide);
        System.out.printf("Bottom Side: %s\n", bottomSide);
        System.out.printf("Mouse X: %s\n", mouseX);
        System.out.printf("Mouse Y: %s\n\n", mouseY);
        */

        return mouseX >= leftSide && mouseX <= rightSide && mouseY >= topSide && mouseY <= bottomSide;
    }

    /*
    Sees if mouse coordinates are within an area on the world
     */
    public static boolean insideAreaW(double x, double y, double width, double height)
    {

        double leftSide = x;
        double rightSide = x + width;
        double bottomSide = y;
        double topSide = y - height;

        double mouseX = BasicWorld.VIEW_CORDS_X + Mouse.getX();
        double mouseY = (BasicWorld.VIEW_CORDS_Y + (700 - Mouse.getY()));
        /*
        System.out.printf("Left Side: %s\n", leftSide);
        System.out.printf("Right Side: %s\n", rightSide);
        System.out.printf("Top Side: %s\n", topSide);
        System.out.printf("Bottom Side: %s\n", bottomSide);
        System.out.printf("Mouse X: %s\n", mouseX);
        System.out.printf("Mouse Y: %s\n\n", mouseY);
        */

        return mouseX >= leftSide && mouseX <= rightSide && mouseY >= topSide && mouseY <= bottomSide;
    }

    /*
    Setting the mouse coordinates to a specific location. I have no idea what this could be used for.
     */
    public static void setMouseLoc(int x, int y)
    {
        Mouse.setCursorPosition(x, y);
    }

    public static boolean getMouseClick(int key)
    {
        if (Mouse.isButtonDown(key) != mouseStates[key])
        {
            return mouseStates[key] = !mouseStates[key];
        }
        else
        {
            return false;
        }
    }
}
