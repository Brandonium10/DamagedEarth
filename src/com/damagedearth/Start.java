/*******************************************************************************
 * This is a free program developed and managed by The Predators Development Team. This
 * source code should have a copy of the GNU General Public License. If it does not,
 * please see <http://www.gnu.org/licenses/>. The Predators Development Team forums is
 * located at <http://thepredators.endofinternet.net/index.php>.
 ******************************************************************************/

package com.damagedearth;

public class Start
{

    public Start()
    {
        DamagedEarth damagedEarth = new DamagedEarth(1000, 700);
        damagedEarth.startGame();
    }

    public static void main(String[] args)
    {
        new Start();
    }
}
