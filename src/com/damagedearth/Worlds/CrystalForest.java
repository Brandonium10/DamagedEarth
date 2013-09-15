package com.damagedearth.Worlds;

import com.damagedearth.DamagedEarth;
import com.damagedearth.Entities.Components.EntityEnemy;
import com.damagedearth.Entities.Components.EntityNPC;
import com.damagedearth.GameElements.Quests.Components.SlayingQuest;
import com.damagedearth.Gui.Components.GuiNPC;
import com.damagedearth.Gui.Components.GuiNPCDialogue;

import java.awt.*;

public class CrystalForest extends BasicWorld
{
    /*
    All entity's in this world
     */
    EntityNPC npcDumbbottom;
    EntityEnemy enemyTreegiant1;
    EntityEnemy enemyTreegiant2;
    EntityEnemy enemyTreegiant3;

    /*
    All Gui's that correspond to the NPC's in this world
     */
    GuiNPC guiDumbottom;

    /*
    All quests that correspond to the NPC's in this world: @npcName@questName@chainNumber
     */
    SlayingQuest dumbottomTraining1;

    public CrystalForest(int spawnX, int spawnY, DamagedEarth damagedEarth)
    {
        super(spawnX, spawnY, damagedEarth);
    }

    @Override
    public void gameLoop()
    {
    }

    /*
    Initiate all entities and creates the GUI's for the NPC's
     */
    @Override
    public void loadWorld()
    {
        /*
        Declare the NPC Gui's
         */
        this.guiDumbottom = new GuiNPC(damagedEarth, "Dumbbottom", null);
        this.guiDumbottom.getSelectableList().add(new GuiNPCDialogue("Test1 Name", "Test1 Desc", false, guiDumbottom));

        /*
        Declare the entities
         */
        this.npcDumbbottom = new EntityNPC("Dumbottom", thePlayer, guiDumbottom, 155, 100, 50, 50, damagedEarth, null, null, null, null);
        this.enemyTreegiant1 = new EntityEnemy("Treegiant", thePlayer, 50, 500, 100, 60, 50, damagedEarth, new Point(700, 100), new Point(500, 100), null, null, 1.5, 3);

        /*
        Declare the NPC's quests and sets the quest declared to the correct selectable
         */
        this.dumbottomTraining1 = new SlayingQuest(this.thePlayer, "Training1", this.npcDumbbottom);
        this.guiDumbottom.getSelectableList().get(0).setQuest(this.dumbottomTraining1);

        /*
        Add the entities to the entityList so they can be updated properly
         */
        this.entityList.add(npcDumbbottom);
        this.entityList.add(enemyTreegiant1);
    }
}
