package com.damagedearth.Utilities;

import com.damagedearth.DamagedEarth;
import com.damagedearth.Entities.EntityPlayer;
import com.damagedearth.GameElements.Quests.Components.BasicQuest;
import com.damagedearth.GameElements.Quests.Components.SlayingQuest;
import com.damagedearth.Utilities.Components.FileConfiguration;

public class PlayerFileManager
{
    /**
     * This file is for managing all file configuration that has to do with the player
     */
    private String locationsName;
    private String questsName;

    private FileConfiguration locationConfiguration;
    private FileConfiguration questsConfiguration;

    private DamagedEarth damagedEarth;

    public PlayerFileManager(String locationsName, String questsName, DamagedEarth damagedEarth)
    {
        this.locationsName = locationsName;
        this.questsName = questsName;
        this.locationConfiguration = new FileConfiguration(this.locationsName + ".txt");
        this.questsConfiguration = new FileConfiguration(this.questsName + ".txt");
        this.damagedEarth = damagedEarth;
    }

    public void update(EntityPlayer thePlayer)
    {
        this.updateQuests(thePlayer);
        this.updateLocation(thePlayer);
    }

    public boolean load(EntityPlayer thePlayer)
    {
        return this.loadLocation(thePlayer) && this.loadQuests(thePlayer);
    }

    /**
     * Updates the players location to a file and encodes it
     * TODO: Save the current world the player is in along with his/her location
     *
     * @param thePlayer An instance of a player to use for saving location
     */
    public void updateLocation(EntityPlayer thePlayer)
    {
        try
        {
            if (locationConfiguration.getFile().exists())
            {
                locationConfiguration.decode();
            }
            locationConfiguration.writeln(String.format("loc:%s:%s", thePlayer.getX(), thePlayer.getY()), false);

            locationConfiguration.encode();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Decodes the file and loads the players location into xCord and yCord. Teleports the player to the proper location.
     *
     * @param thePlayer An instance of the player
     * @return True if the file successfully loads without any errors (aka. If the file exists)
     */
    public boolean loadLocation(EntityPlayer thePlayer)
    {
        try
        {
            locationConfiguration.decode();
            double xCord = Double.parseDouble(locationConfiguration.getLineValue("loc:").split(":")[1]);
            double yCord = Double.parseDouble(locationConfiguration.getLineValue("loc:").split(":")[2]);
            thePlayer.teleport(xCord, yCord);
            locationConfiguration.encode();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public void updateQuests(EntityPlayer thePlayer)
    {
        try
        {
            //If the file exists then we will decode it and then clear it
            if (questsConfiguration.getFile().exists())
            {
                //questsConfiguration.decode();
                //Reset the file to we can rewrite
                questsConfiguration.clear();
            }
            //Loop through each quests and append the name and value to the end of the line
            for (BasicQuest quest : thePlayer.getOwnedQuests())
            {
                if (quest instanceof SlayingQuest)
                {
                    questsConfiguration.writeln(String.format("%s:%s", quest.getQuestName(), quest.getSlayingQuestInstance().getAmount()), true);
                }
            }
            //questsConfiguration.encode();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //TODO: If the quest is complete, it won't register with the game if you load it from a file. Fix it. You must check if you killed all enemies, then set it to completed = true
    public boolean loadQuests(EntityPlayer thePlayer)
    {
        try
        {
            //questsConfiguration.decode();

            //Loops through every line and gets the corresponding quest for the data that line contains. Eventually adds the quest to the player's current quests.
            for (int i = 1; i <= questsConfiguration.getLastLine(); i++)
            {
                if (!questsConfiguration.getLineValue(i).isEmpty())
                {
                    String currentQuestName = questsConfiguration.getLineValue(i).split(":")[0];
                    int currentAmount = Integer.parseInt(questsConfiguration.getLineValue(i).split(":")[1]);

                    BasicQuest currentQuest = damagedEarth.currentWorld.getCorrespondingQuest(currentQuestName);
                    currentQuest.getSlayingQuestInstance().setAmount(currentAmount);
                    thePlayer.acceptQuest(currentQuest);
                }
            }

            //questsConfiguration.encode();
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
