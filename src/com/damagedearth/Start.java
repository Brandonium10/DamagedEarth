package com.damagedearth;

public class Start
{

    public Start()
    {
        DamagedEarth damagedEarth = new DamagedEarth(1000, 700);
        damagedEarth.startGame();
    }

    public static void main(String[] args)
    {
        new Start();
    }
}
