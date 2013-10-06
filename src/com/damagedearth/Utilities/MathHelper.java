/*******************************************************************************
 * This is a free program developed and managed by The Predators Development Team. This
 * source code should have a copy of the GNU General Public License. If it does not,
 * please see <http://www.gnu.org/licenses/>. The Predators Development Team forums is
 * located at <http://thepredators.endofinternet.net/index.php>.
 ******************************************************************************/

package com.damagedearth.Utilities;

public class MathHelper
{
    public static boolean isNegative(double x)
    {
        if (x < 0)
        {
            return true;
        }
        return false;
    }

    public static double getDistanceSq(double x1, double y1, double x2, double y2)
    {
        double xDifference = x1 - x2;
        double yDifference = y1 - y2;
        return xDifference * xDifference + yDifference * yDifference;
    }

    public static double max(double doub1, double doub2, double doub3)
    {
        double highest = Math.max(doub1, doub2);
        return Math.max(highest, doub3);
    }

    public static double max(double doub1, double doub2, double doub3, double doub4)
    {
        double highest = Math.max(doub1, doub2);
        return max(highest, doub3, doub4);
    }

    public static double max(double doub1, double doub2, double doub3, double doub4, double doub5)
    {
        double highest = Math.max(doub1, doub2);
        return max(highest, doub3, doub4, doub5);
    }
}
