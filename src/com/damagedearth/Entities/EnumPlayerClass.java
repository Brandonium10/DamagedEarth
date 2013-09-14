package com.damagedearth.Entities;

public enum EnumPlayerClass
{
    SCIENTIST(1), SOLDIER(2);

    private int classType;

    private EnumPlayerClass(int classType)
    {
        this.classType = classType;
    }
}
