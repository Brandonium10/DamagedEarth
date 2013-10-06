/*******************************************************************************
 * This is a free program developed and managed by The Predators Development Team. This
 * source code should have a copy of the GNU General Public License. If it does not,
 * please see <http://www.gnu.org/licenses/>. The Predators Development Team forums is
 * located at <http://thepredators.endofinternet.net/index.php>.
 ******************************************************************************/

package com.damagedearth.Gameplay.Items;

import com.damagedearth.Gameplay.Items.Item;
import com.damagedearth.Utilities.Tesselator;
import com.damagedearth.Worlds.BasicWorld;

public class ItemSlot
{
    private Item item;
    private int x;
    private int y;
    private int width;
    private int height;

    public ItemSlot(Item item, int x, int y)
    {
        this.item = item;
        this.x = x;
        this.y = y;
        this.width = 60;
        this.height = 65;
    }

    public ItemSlot(int x, int y)
    {
        this.item = null;
        this.x = x;
        this.y = y;
        this.width = 50;
        this.height = 55;
    }

    public void update()
    {
        this.render();
    }

    private void render()
    {
        Tesselator tesselator = new Tesselator();
        //Render the item slot box that goes behind the item
        tesselator.set(x, y, x + width, y - height);
        tesselator.setColor(192, 192, 192);
        tesselator.startDrawingCQuad();
        tesselator.endDrawingQuad();

        if (item != null)
        {
            //Render the item image inside the item slot
            tesselator.set(x + 4, y - 4, x + this.item.getItemImage().getWidth(), y - this.item.getItemImage().getHeight());
            tesselator.startDrawingTextQuad(this.item.getItemImage());
            tesselator.endDrawingTextQuad();
        }
        else
        {
            tesselator.set(x + 4, y - 4, x + this.getWidth() - 4, y - this.getHeight() + 4);
            //Draw a white box if there is no item for this slot
            tesselator.startDrawingQuad();
            tesselator.endDrawingQuad();
        }
    }

    public Item getItem()
    {
        return item;
    }

    public void setItem(Item item)
    {
        this.item = item;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }
}
