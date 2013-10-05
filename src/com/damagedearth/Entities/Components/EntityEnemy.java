/*******************************************************************************
 * This is a free program developed and managed by The Predators Development Team. This
 * source code should have a copy of the GNU General Public License. If it does not,
 * please see <http://www.gnu.org/licenses/>. The Predators Development Team forums is
 * located at <http://thepredators.endofinternet.net/index.php>.
 ******************************************************************************/

/*******************************************************************************
 * This is a free program developed and managed by The Predators Development Team. This
 * source code should have a copy of the GNU General Public License. If it does not,
 * please see <http://www.gnu.org/licenses/>. The Predators Development Team forums is
 * located at <http://thepredators.endofinternet.net/index.php>.
 ******************************************************************************/

package com.damagedearth.Entities.Components;

import com.damagedearth.DamagedEarth;
import com.damagedearth.Entities.EntityPlayer;
import com.damagedearth.Gameplay.Quests.BasicQuest;
import com.damagedearth.Gameplay.Quests.SlayingQuest;
import com.damagedearth.Utilities.MathHelper;

import java.awt.*;
import java.util.Random;

public class EntityEnemy extends Entity
{
    protected boolean seesPlayer;
    protected int maxDistance;
    protected double speed;

    public int health;
    protected int averageDamage;
    protected int damageModifier;

    public boolean isDead;

    protected int attackDelay;
    private int attackCounter;

    public EntityEnemy(String name, EntityPlayer thePlayer, int health, int defaultX, int defaultY, int width, int height, DamagedEarth damagedEarth, Point des1, Point des2, Point des3, Point des4, double speed, int averageDamage)
    {
        super(name, thePlayer, defaultX, defaultY, width, height, damagedEarth, des1, des2, des3, des4);
        this.speed = speed;
        this.health = health;
        this.averageDamage = averageDamage;
        this.damageModifier = 6;
        this.maxDistance = 110;
        this.attackDelay = 40;
        this.attackCounter = this.attackDelay;
        this.isDead = false;
    }

    @Override
    public void onLivingUpdate()
    {
        if (!isDead)
        {
            this.strollEntity();
            this.renderEntity();
            this.sightHandler();
            this.followPlayer();
            this.attackPlayer();
            if (attackCounter <= 0) attackCounter = this.attackDelay;
            else attackCounter--;
        }
    }

    private void followPlayer()
    {
        if (seesPlayer)
        {
            if (this.x != thePlayer.getX())
            {
                if (this.x < thePlayer.getX())
                {
                    if (!this.getEntityLeftCollided())
                    {
                        this.move(1, 0);
                    }
                }
                else
                {
                    if (!this.getEntityRightCollided())
                    {
                        this.move(-1, 0);
                    }
                }
            }
            if (this.y != thePlayer.getY())
            {
                if (this.y < thePlayer.getY())
                {
                    if (!this.getEntityTopCollided())
                    {
                        this.move(0, 1);
                    }
                }
                else
                {
                    if (!this.getEntityBottomCollided())
                    {
                        this.move(0, -1);
                    }
                }
            }
        }
    }

    private void sightHandler()
    {
        double distance = Math.sqrt((double) MathHelper.getDistanceSq(thePlayer.getX(), thePlayer.getY(), this.x, this.y));
        if (!seesPlayer)
        {
            if (distance < maxDistance)
            {
                seesPlayer = true;
            }
        }
        else
        {
            if (distance > maxDistance)
            {
                seesPlayer = false;
            }
        }
    }

    public void damage(int health)
    {
        if (!isDead)
        {
            this.health -= health;
            if (this.health <= 0)
            {
                this.onDeath();
                this.isDead = true;
            }
        }
    }

    private void onDeath()
    {
        /*
        Updates every slaying quest's killstreak with this entity. updateKillstreak() checks if it's the correct entity.
         */
        for (BasicQuest basicQuest : this.thePlayer.getOwnedQuests())
        {
            SlayingQuest quest = basicQuest.getSlayingQuestInstance();
            if (quest != null)
            {
                quest.updateKillstreak(this);
            }
        }

    }

    public boolean nearPlayer()
    {
        return Math.sqrt(MathHelper.getDistanceSq(this.x, this.y, this.thePlayer.getX(), this.thePlayer.getY())) <= 50;
    }

    private void attackPlayer()
    {
        if (this.nearPlayer())
        {
            if (this.attackCounter <= 0)
            {
                Random r = new Random();
                int damageModifier = r.nextInt(this.damageModifier);
                int damageDealt = this.averageDamage + damageModifier;
                this.thePlayer.damage(damageDealt);
            }
        }
    }

    public int getMaxDistance()
    {
        return maxDistance;
    }

    public void setMaxDistance(int maxDistance)
    {
        this.maxDistance = maxDistance;
    }

    public double getSpeed()
    {
        return speed;
    }

    public void setSpeed(double speed)
    {
        this.speed = speed;
    }

    public int getHealth()
    {
        return health;
    }

    public void setHealth(int health)
    {
        this.health = health;
    }

    public int getAverageDamage()
    {
        return averageDamage;
    }

    public void setAverageDamage(int averageDamage)
    {
        this.averageDamage = averageDamage;
    }

    public int getDamageModifier()
    {
        return damageModifier;
    }

    public void setDamageModifier(int damageModifier)
    {
        this.damageModifier = damageModifier;
    }

    public boolean isDead()
    {
        return isDead;
    }

    public void setDead(boolean dead)
    {
        isDead = dead;
    }

    public int getAttackDelay()
    {
        return attackDelay;
    }

    public void setAttackDelay(int attackDelay)
    {
        this.attackDelay = attackDelay;
    }
}
