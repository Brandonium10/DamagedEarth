package com.damagedearth.Utilities;

import com.damagedearth.Entities.Components.Entity;
import com.damagedearth.Entities.ControlledEntityPlayer;
import com.damagedearth.Utilities.Components.EnumConfigurationType;
import com.damagedearth.Utilities.Components.FileConfiguration;

public class PlayerFileManager
{
    /**
     * This file is for managing all file configuration that has to do with the player
     */

    String configName;

    FileConfiguration fileConfiguration;

    public PlayerFileManager(String configName) {
        this.configName = configName;
        this.fileConfiguration = new FileConfiguration("/Users/ayates/IdeaProjects/DEProject/" + this.configName + ".txt");
    }

    /**
     * Updates the players location to a file and encodes it
     * TODO: Save the current world the player is in along with his/her location
     *
     * @param thePlayer An instance of a player to use for saving location
     */
    public void updateLocation(ControlledEntityPlayer thePlayer) {
        try
        {
            if (fileConfiguration.getFile().exists())
            {
                fileConfiguration.decode();
                fileConfiguration.editLine("loc:", String.format("loc:%s:%s", thePlayer.getX(), thePlayer.getY()));
                fileConfiguration.encode();
            }
            else
            {
                fileConfiguration.writeln(String.format("loc:%s:%s", thePlayer.getX(), thePlayer.getY()), true);
                fileConfiguration.encode();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Decodes the file and loads the players location into xCord and yCord. Teleports the player to the proper location.
     *
     * @param thePlayer
     * @return True if the file successfully loads without any errors (aka. If the file exists)
     */
    public boolean loadLocation(ControlledEntityPlayer thePlayer) {
        try
        {
            fileConfiguration.decode();
            double xCord = Double.parseDouble(fileConfiguration.getLineValue("loc:").split(":")[1]);
            double yCord = Double.parseDouble(fileConfiguration.getLineValue("loc:").split(":")[2]);
            thePlayer.teleport(xCord, yCord);
            fileConfiguration.encode();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
}
