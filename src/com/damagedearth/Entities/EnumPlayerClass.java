/*******************************************************************************
 * This is a free program developed and managed by The Predators Development Team. This
 * source code should have a copy of the GNU General Public License. If it does not,
 * please see <http://www.gnu.org/licenses/>. The Predators Development Team forums is
 * located at <http://thepredators.endofinternet.net/index.php>.
 ******************************************************************************/

package com.damagedearth.Entities;

import com.damagedearth.Utilities.TextureLoader;

import java.awt.image.BufferedImage;

public enum EnumPlayerClass
{
    SCIENTIST(1), SOLDIER(2);

    private int classType;

    private EnumPlayerClass(int classType)
    {
        this.classType = classType;
    }

    public BufferedImage getClassImage()
    {
        if (classType == 1)
        {
            return TextureLoader.loadImage("res/Player/Scientist/scientist.png");
        }
        else if (classType == 2)
        {
            return TextureLoader.loadImage("res/Player/Soldier/soldier.png");
        }
        return null;
    }

    public static EnumPlayerClass getClassByString(String className)
    {
        if (SCIENTIST.toString().equalsIgnoreCase(className))
        {
            return SCIENTIST;
        }
        else if (SOLDIER.toString().equalsIgnoreCase(className))
        {
            return SOLDIER;
        }
        return null;
    }
}
