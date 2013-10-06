/*******************************************************************************
 * This is a free program developed and managed by The Predators Development Team. This
 * source code should have a copy of the GNU General Public License. If it does not,
 * please see <http://www.gnu.org/licenses/>. The Predators Development Team forums is
 * located at <http://thepredators.endofinternet.net/index.php>.
 ******************************************************************************/

package com.damagedearth.Gameplay.Items;

import com.damagedearth.DamagedEarth;

import java.awt.image.BufferedImage;

public class Item
{
    private DamagedEarth damagedEarth;
    private String name;
    private int id;
    private BufferedImage itemImage;

    public Item(DamagedEarth damagedEarth, String name, int id)
    {
        this.damagedEarth = damagedEarth;
        this.name = name;
        this.id = id;
    }

    public boolean ownedByPlayer()
    {
        return damagedEarth.currentWorld.thePlayer.getInventory().contains(this);
    }

    /**
     * You do not need to check if the item is already in the player's inventory because addItem() already checks that.
     */
    public void give()
    {
        damagedEarth.currentWorld.thePlayer.getInventory().addItem(this);
    }

    public BufferedImage getItemImage()
    {
        return itemImage;
    }

    public void setItemImage(BufferedImage itemImage)
    {
        this.itemImage = itemImage;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
}
