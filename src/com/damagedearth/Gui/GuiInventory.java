/*******************************************************************************
 * This is a free program developed and managed by The Predators Development Team. This
 * source code should have a copy of the GNU General Public License. If it does not,
 * please see <http://www.gnu.org/licenses/>. The Predators Development Team forums is
 * located at <http://thepredators.endofinternet.net/index.php>.
 ******************************************************************************/

package com.damagedearth.Gui;

import com.damagedearth.DamagedEarth;
import com.damagedearth.Gui.Components.GuiButton;
import com.damagedearth.Gui.Components.GuiScreen;

public class GuiInventory extends GuiScreen
{
    public GuiInventory(DamagedEarth damagedEarth, String displayName, GuiScreen parentScreen)
    {
        super(damagedEarth, displayName, parentScreen);
    }

    public GuiInventory(DamagedEarth damagedEarth, String displayName)
    {
        super(damagedEarth, displayName);
    }

    @Override
    public void setUpGUI()
    {
        super.setUpGUI();
    }

    @Override
    protected void drawGLScreen()
    {
        super.drawGLScreen();
    }

    @Override
    protected void handleInput()
    {
        super.handleInput();
    }

    @Override
    protected int mouseAction(int key, GuiButton buttonClicked)
    {
        return super.mouseAction(key, buttonClicked);
    }

    @Override
    public void updateScreen()
    {
        super.updateScreen();
        damagedEarth.currentWorld.thePlayer.getInventory().update();
    }

    @Override
    protected boolean checkKey(int i)
    {
        return super.checkKey(i);
    }
}
