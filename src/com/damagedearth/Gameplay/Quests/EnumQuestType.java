/*******************************************************************************
 * This is a free program developed and managed by The Predators Development Team. This
 * source code should have a copy of the GNU General Public License. If it does not,
 * please see <http://www.gnu.org/licenses/>. The Predators Development Team forums is
 * located at <http://thepredators.endofinternet.net/index.php>.
 ******************************************************************************/

/*******************************************************************************
 * This is a free program developed and managed by The Predators Development Team. This
 * source code should have a copy of the GNU General Public License. If it does not,
 * please see <http://www.gnu.org/licenses/>. The Predators Development Team forums is
 * located at <http://thepredators.endofinternet.net/index.php>.
 ******************************************************************************/

package com.damagedearth.Gameplay.Quests;

public enum EnumQuestType
{
    SLAYING(1), COLLECTING(2), ESCORTING(3), TRAVEL(4), EXPLORE(5);

    private int questType;

    private EnumQuestType(int questType)
    {
        this.questType = questType;
    }

    public String getStringType()
    {
        return (questType == 1 ? "Slaying" : (questType == 2 ? "Collecting" : (questType == 3 ? "Escort" : (questType == 4 ? "Travel" : (questType == 5 ? "Explore" : "")))));
    }
}
