package com.damagedearth.Entities;

import com.damagedearth.Utilities.TextureLoader;

import java.awt.image.BufferedImage;

public enum EnumPlayerClass
{
    SCIENTIST(1), SOLDIER(2);

    private int classType;

    private EnumPlayerClass(int classType)
    {
        this.classType = classType;
    }

    public BufferedImage getClassImage()
    {
        if (classType == 1)
        {
            return TextureLoader.loadImage("res/Player/Scientist/scientist.png");
        }
        else if (classType == 2)
        {
            return TextureLoader.loadImage("res/Player/Soldier/soldier.png");
        }
        return null;
    }

    public static EnumPlayerClass getClassByString(String className)
    {
        if (SCIENTIST.toString().equalsIgnoreCase(className))
        {
            return SCIENTIST;
        }
        else if (SOLDIER.toString().equalsIgnoreCase(className))
        {
            return SOLDIER;
        }
        return null;
    }
}
