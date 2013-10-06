/*******************************************************************************
 * This is a free program developed and managed by The Predators Development Team. This
 * source code should have a copy of the GNU General Public License. If it does not,
 * please see <http://www.gnu.org/licenses/>. The Predators Development Team forums is
 * located at <http://thepredators.endofinternet.net/index.php>.
 ******************************************************************************/

package com.damagedearth.Utilities.Components;

public enum EnumConfigurationType
{
    /**
     * PLAYER: Players EXP, Level, Class, Current Quests, Inventory, etc.
     * WORLD: All entities locations, enemy statistics, boss statistics, etc.
     * SETTINGS: All things related to settings (ex. alpha value, keybindings, difficulty)
     */
    PLAYER("Player"), WORLD("World"), SETTINGS("Settings");

    String configType;

    EnumConfigurationType(String configType)
    {
        this.configType = configType;
    }

    public String getFriendlyName()
    {
        return configType;
    }
}
