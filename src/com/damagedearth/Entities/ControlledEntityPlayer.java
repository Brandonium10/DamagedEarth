package com.damagedearth.Entities;

import com.damagedearth.Entities.Components.Entity;
import com.damagedearth.GameElements.Quests.Components.BasicQuest;
import com.damagedearth.GameElements.Quests.Components.SlayingQuest;
import com.damagedearth.Utilities.MouseHelper;
import com.damagedearth.Worlds.BasicWorld;
import com.damagedearth.Utilities.MathHelper;
import com.damagedearth.Utilities.TextureLoader;
import org.lwjgl.input.Keyboard;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.lwjgl.opengl.GL11.*;

public class ControlledEntityPlayer
{
    /*
    x and y coordinates of the player are based on the BOTTOM left side
     */
    private double x, y, width, height;
    private int health;
    private int level;
    private int xp;
    private boolean isDead;
    private Entity targetedEntity;
    private EnumPlayerClass playerClass;
    private BasicWorld currentWorld;
    private List<BasicQuest> ownedQuests = new ArrayList<BasicQuest>();
    private int averageDamage;
    private int damageModifier;
    private boolean keyStates[];
    private float speed;
    private BufferedImage currentPlayerImage = TextureLoader.loadImage("res/Player/Soldier/soldier.png");

    /*
    0 = starting position
    1 = right
    2 = left
    3 = up
    4 = down
     */
    private int lastDirection;

    public ControlledEntityPlayer(double defaultX, double defaultY, double width, double height, BasicWorld currentWorld, EnumPlayerClass playerClass)
    {
        this.x = defaultX;
        this.y = defaultY;
        this.width = width;
        this.height = height;
        this.speed = 1.5F;
        this.currentWorld = currentWorld;
        this.playerClass = playerClass;
        this.keyStates = new boolean[256];
        this.lastDirection = 0;
        this.averageDamage = 3;
        this.health = 55;
        this.damageModifier = 6;
        this.isDead = false;
        targetedEntity = null;
    }

    public void onLivingUpdate()
    {
        if (!isDead)
        {
            this.handleInput();
            this.renderPlayer();
            this.checkDeath();
        }
    }

    private void checkDeath()
    {
        if (health <= 0)
        {
            isDead = true;
        }
    }

    private boolean checkKey(int i)
    {
        return Keyboard.isKeyDown(i) != keyStates[i] && (keyStates[i] = !keyStates[i]);
    }

    /*
    This renders the player according to his x and y coordinates
     */
    private void renderPlayer()
    {
        glPushMatrix();

        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);

        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA); //ACT AS THOUGH YOUR LIFE DEPENDS ON THIS LINE!

        int id = TextureLoader.loadTexture(currentPlayerImage);

        //Follow this format for OpenGL texture drawing
        glBegin(GL_QUADS);
        {
            glTexCoord2d(0, 0);
            glVertex2d(x, y);
            glTexCoord2d(1, 0);
            glVertex2d(x + width, y);
            glTexCoord2d(1, 1);
            glVertex2d(x + width, y - height);
            glTexCoord2d(0, 1);
            glVertex2d(x, y - height);
        }
        glEnd();

        glDisable(GL_TEXTURE_2D);
        glDisable(GL_BLEND);
        glDeleteTextures(id);

        glPopMatrix();
    }

    public void move(double x, double y)
    {
        this.x += x;
        this.y += y;
    }

    public void teleport(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public void acceptQuest(BasicQuest quest)
    {
        this.ownedQuests.add(quest);
        quest.getQuestGiver().getGivableQuests().remove(quest);
        System.out.println("[Player] Accepted quest: " + quest.getQuestName());
    }

    public void abandonQuest(BasicQuest quest)
    {
        this.ownedQuests.remove(quest);
    }

    public void finishQuest(BasicQuest quest)
    {
        this.ownedQuests.remove(quest);
    }

    /*
    This method will attack the targeted enemy
     */
    public void attackEnemy()
    {
        if (Math.sqrt(MathHelper.getDistanceSq(this.x, this.y, this.getTargetedEntity().getX(), this.getTargetedEntity().getY())) < 150)
        {
            try
            {
                int randomDamageModifier = new Random().nextInt(this.damageModifier);
                int finalDamage = this.averageDamage + randomDamageModifier;

                this.getTargetedEntity().getEnemyInstance().damage(finalDamage);
            }
            catch (NullPointerException e)
            {
                System.out.println("[Player] You cannot attack that target");
            }
        }
        else
        {
            System.out.println("[Player] Not close enough to the target");
        }
    }

    private void handleInput()
    {
        this.keyInput();
        this.mouseInput();
    }

    private void mouseInput()
    {
        if (MouseHelper.getMouseClick(0))
        {
            for (Entity e : this.currentWorld.getEntityList())
            {
                if (MouseHelper.insideAreaW(e.getX(), e.getY(), e.getWidth(), e.getHeight()))
                {
                    this.setTargetedEntity(e);
                    System.out.println("Entity targeted: " + e.getEntityName());
                }
            }
        }
    }

    private void keyInput()
    {
        currentPlayerImage = TextureLoader.loadImage("res/Player/Soldier/soldier.png");
        if (Keyboard.isKeyDown(Keyboard.KEY_LEFT))
        {
            this.move(-speed, 0);
            this.lastDirection = 2;
            currentPlayerImage = TextureLoader.loadImage("res/Player/Soldier/soldier-left.png");
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_DOWN))
        {
            this.move(0, speed);
            this.lastDirection = 4;
            currentPlayerImage = TextureLoader.loadImage("res/Player/Soldier/soldier.png");
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
        {
            this.move(speed, 0);
            this.lastDirection = 1;
            currentPlayerImage = TextureLoader.loadImage("res/Player/Soldier/soldier-right.png");
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_UP))
        {
            this.move(0, -speed);
            this.lastDirection = 3;
            currentPlayerImage = TextureLoader.loadImage("res/Player/Soldier/soldier-back.png");
        }
        if (this.checkKey(Keyboard.KEY_1))
        {
            this.attackEnemy();
        }
    }

    public void damage(int health)
    {
        if (!isDead)
        {
            this.health -= health;
        }
    }

    public boolean nearEntity(Entity entity)
    {
        return Math.sqrt(MathHelper.getDistanceSq(this.x, this.y, entity.getX(), entity.getY())) <= 70;
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public boolean isDead()
    {
        return isDead;
    }

    public void setDead(boolean dead)
    {
        isDead = dead;
    }

    public double getHeight()
    {
        return height;
    }

    public void setHeight(double height)
    {
        this.height = height;
    }

    public double getWidth()
    {
        return width;
    }

    public void setWidth(double width)
    {
        this.width = width;
    }

    public int getHealth()
    {
        return health;
    }

    public void setHealth(int health)
    {
        this.health = health;
    }

    public int getLevel()
    {
        return level;
    }

    public int getDamageModifier()
    {
        return damageModifier;
    }

    public void setDamageModifier(int damageModifier)
    {
        this.damageModifier = damageModifier;
    }

    public int getAverageDamage()
    {
        return averageDamage;
    }

    public void setAverageDamage(int averageDamage)
    {
        this.averageDamage = averageDamage;
    }

    public float getSpeed()
    {
        return speed;
    }

    public void setSpeed(float speed)
    {
        this.speed = speed;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public int getXp()
    {
        return xp;
    }

    public void setXp(int xp)
    {
        this.xp = xp;
    }

    public void addXp(int xp)
    {
        this.xp += xp;
    }

    public void removeXp(int xp)
    {
        this.xp -= xp;
    }

    public List<BasicQuest> getOwnedQuests()
    {
        return ownedQuests;
    }

    public EnumPlayerClass getPlayerClass()
    {
        return playerClass;
    }

    public void setPlayerClass(EnumPlayerClass playerClass)
    {
        this.playerClass = playerClass;
    }

    public Entity getTargetedEntity()
    {
        return targetedEntity;
    }

    public void setTargetedEntity(Entity targetedEntity)
    {
        this.targetedEntity = targetedEntity;
    }
}