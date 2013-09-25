package com.damagedearth.Worlds;

import com.damagedearth.DamagedEarth;
import com.damagedearth.Entities.Components.Entity;
import com.damagedearth.Entities.EntityPlayer;
import com.damagedearth.Entities.EnumPlayerClass;
import com.damagedearth.GameElements.Quests.Components.BasicQuest;

import java.util.ArrayList;
import java.util.List;

public abstract class BasicWorld
{
    public static int VIEW_CORDS_X = 0;
    public static int VIEW_CORDS_Y = 0;
    public static double TRANSLATE_MODIFIER_X = 0;
    public static double TRANSLATE_MODIFIER_Y = 0;
    /*
     The sole purpose of this list is for the getCorrespondingQuest() method.
     */
    List<BasicQuest> allWorldQuests = new ArrayList<BasicQuest>();

    protected int spawnX, spawnY;
    public EntityPlayer thePlayer;
    protected List<Entity> entityList = new ArrayList<Entity>();

    public DamagedEarth damagedEarth;

    public BasicWorld(int spawnX, int spawnY, DamagedEarth damagedEarth)
    {
        this.spawnX = spawnX;
        this.spawnY = spawnY;
        this.damagedEarth = damagedEarth;
        thePlayer = new EntityPlayer(40, 45, this, EnumPlayerClass.SCIENTIST);
    }

    public abstract void gameLoop();

    public abstract void loadWorld();

    /**
     * Updates all entities and objects in the world
     */
    public void updateAndRender()
    {
        this.gameLoop();
        try
        {
            for (Entity entity : this.entityList)
            {
                entity.onLivingUpdate();
            }
            this.thePlayer.onLivingUpdate();
        }
        catch (Exception e)
        {
        }
        this.scrollWorld(this.thePlayer.getX(), this.thePlayer.getY());
    }

    /**
     * The viewing area has specific coordinates(top left). If the player gets near the end of the viewing area,
     * it will scroll and increase the coordinates of the viewing area to the direction the player is going. Not
     * sure how this will react with the teleport() method, but it works perfectly with the move()
     */
    public void scrollWorld(double playerX, double playerY)
    {
        if (playerX >= (VIEW_CORDS_X + damagedEarth.width) - 300)
        {
            TRANSLATE_MODIFIER_X = 2;
            VIEW_CORDS_X += 2;
        }
        else if (playerX <= (VIEW_CORDS_X) + 300)
        {
            TRANSLATE_MODIFIER_X = -2;
            VIEW_CORDS_X -= 2;
        }

        if (playerY >= (VIEW_CORDS_Y + damagedEarth.height) - 100)
        {
            TRANSLATE_MODIFIER_Y = 2;
            VIEW_CORDS_Y += 2;
        }
        else if (playerY <= (VIEW_CORDS_Y) + 100)
        {
            TRANSLATE_MODIFIER_Y = -2;
            VIEW_CORDS_Y -= 2;
        }
    }

    public BasicQuest getCorrespondingQuest(String questName)
    {
        for (BasicQuest quest : this.allWorldQuests)
        {
            if (quest.getQuestName().equalsIgnoreCase(questName))
            {
                return quest;
            }
        }
        return null;
    }

    public int getSpawnX()
    {
        return spawnX;
    }

    public void setSpawnX(int spawnX)
    {
        this.spawnX = spawnX;
    }

    public int getSpawnY()
    {
        return spawnY;
    }

    public void setSpawnY(int spawnY)
    {
        this.spawnY = spawnY;
    }

    public List<Entity> getEntityList()
    {
        return entityList;
    }
}
