/*******************************************************************************
 * This is a free program developed and managed by The Predators Development Team. This
 * source code should have a copy of the GNU General Public License. If it does not,
 * please see <http://www.gnu.org/licenses/>. The Predators Development Team forums is
 * located at <http://thepredators.endofinternet.net/index.php>.
 ******************************************************************************/

package com.damagedearth.Utilities;

import com.damagedearth.Worlds.BasicWorld;
import org.lwjgl.input.Mouse;

public class MouseHelper
{
    static boolean mouseStates[] = new boolean[2];

    /*
    Sees if mouse coordinates are within an area
     */
    public static boolean insideArea(double x, double y, double width, double height)
    {

        double leftSide = x;
        double rightSide = x + width;
        double topSide = y;
        double bottomSide = y - height;

        double mouseX = BasicWorld.VIEW_CORDS_X + Mouse.getX();
        double mouseY = (BasicWorld.VIEW_CORDS_Y + (Mouse.getY()));
        /*
        System.out.printf("Left Side: %s\n", leftSide);
        System.out.printf("Right Side: %s\n", rightSide);
        System.out.printf("Top Side: %s\n", topSide);
        System.out.printf("Bottom Side: %s\n", bottomSide);
        System.out.printf("Mouse X: %s\n", mouseX);
        System.out.printf("Mouse Y: %s\n\n", mouseY);
        */
        return mouseX >= leftSide && mouseX <= rightSide && mouseY <= topSide && mouseY >= bottomSide;
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
