/*******************************************************************************
 * This is a free program developed and managed by The Predators Development Team. This
 * source code should have a copy of the GNU General Public License. If it does not,
 * please see <http://www.gnu.org/licenses/>. The Predators Development Team forums is
 * located at <http://thepredators.endofinternet.net/index.php>.
 ******************************************************************************/

package com.damagedearth.Gameplay.Items;

import com.damagedearth.DamagedEarth;
import com.damagedearth.Worlds.BasicWorld;

import java.util.ArrayList;
import java.util.List;

public class Inventory
{
    private DamagedEarth damagedEarth;
    private List<ItemSlot> slots = new ArrayList<ItemSlot>();
    private List<Item> items = new ArrayList<Item>();

    public Inventory(DamagedEarth damagedEarth)
    {
        //TODO: Inventory needs multi-world support
        this.damagedEarth = damagedEarth;

        //Draws item slots evenly across the screen.
        for (int x = BasicWorld.VIEW_CORDS_X + 2; x < BasicWorld.VIEW_CORDS_X + this.damagedEarth.width; x += 62)
        {
            for (int y = BasicWorld.VIEW_CORDS_Y + this.damagedEarth.height / 2 - 200; y < BasicWorld.VIEW_CORDS_Y + this.damagedEarth.height - 12; y += 68)
            {
                slots.add(new ItemSlot(x, y));
            }
        }
    }

    public void addItem(Item item)
    {
        //Check if the inventory already contains this item
        if (!this.contains(item))
        {
            //Assigns the item to the next open slot
            for (ItemSlot slot : this.slots)
            {
                if (slot.getItem() == null)
                {
                    slot.setItem(item);
                    break;
                }
            }

            //Lets add the item to the item list so we can use it later.
            this.items.add(item);
        }
    }

    public boolean contains(Item item)
    {
        return this.items.contains(item);
    }

    /**
     * This method is called from GuiInventory.
     */
    public void update()
    {
        for (ItemSlot slot : this.slots)
        {
            slot.update();
        }
    }
}
