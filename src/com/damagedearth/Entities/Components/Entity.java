package com.damagedearth.Entities.Components;

import com.damagedearth.DamagedEarth;
import com.damagedearth.Entities.ControlledEntityPlayer;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

public class Entity
{

    double x, y, defaultX, defaultY, width, height;
    ControlledEntityPlayer thePlayer;
    DamagedEarth damagedEarth;
    String entityName;

    protected Point des1 = null;
    protected Point des2 = null;
    protected Point des3 = null;
    protected Point des4 = null;
    protected int loopStep = 1;

    /**
     *
     * @param name The name of this entity
     * @param thePlayer An instance of the player
     * @param defaultX The x spawn location of this entity
     * @param defaultY The y spawn location of this entity
     * @param width The width of this entity
     * @param height The height of this entity
     * @param damagedEarth An instance of DamagedEarth
     * @param des1 First path cords
     * @param des2 Second path cords
     * @param des3 Third path cords
     * @param des4 Fourth path cords
     */
    public Entity(String name, ControlledEntityPlayer thePlayer, double defaultX, double defaultY, double width, double height, DamagedEarth damagedEarth, Point des1, Point des2, Point des3, Point des4)
    {
        this.entityName = name;
        this.thePlayer = thePlayer;
        this.x = defaultX;
        this.y = defaultY;
        this.defaultX = defaultX;
        this.defaultY = defaultY;
        this.width = width;
        this.height = height;
        this.damagedEarth = damagedEarth;
        this.des1 = des1;
        this.des2 = des2;
        this.des3 = des3;
        this.des4 = des4;
    }

    public void onLivingUpdate()
    {
        this.renderEntity();
        this.strollEntity();
    }

    protected void strollEntity()
    {
        if (this instanceof EntityEnemy && ((EntityEnemy) this).seesPlayer)
        {
            return;
        }
        if (this.loopStep == 1)
        {
            if (des1 != null)
            {
                if (this.x != des1.x)
                {
                    if (this.x < des1.x)
                    {
                        this.x++;
                    }
                    else
                    {
                        this.x--;
                    }
                }
                if (this.y != des1.y)
                {
                    if (this.y < des1.y)
                    {
                        this.y++;
                    }
                    else
                    {
                        this.y--;
                    }
                }
                if (this.x == des1.x && this.y == des1.y)
                {
                    this.loopStep = 2;
                }
            }
            else
            {
                this.loopStep = 2;
            }
        }
        else if (this.loopStep == 2)
        {
            if (des2 != null)
            {
                if (this.x != des2.x)
                {
                    if (this.x < des2.x)
                    {
                        this.x++;
                    }
                    else
                    {
                        this.x--;
                    }
                }
                if (this.y != des2.y)
                {
                    if (this.y < des2.y)
                    {
                        this.y++;
                    }
                    else
                    {
                        this.y--;
                    }
                }
                if (this.x == des2.x && this.y == des2.y)
                {
                    this.loopStep = 3;
                }
            }
            else
            {
                this.loopStep = 3;
            }
        }
        else if (this.loopStep == 3)
        {
            if (des3 != null)
            {
                if (this.x != des3.x)
                {
                    if (this.x < des3.x)
                    {
                        this.x++;
                    }
                    else
                    {
                        this.x--;
                    }
                }
                if (this.y != des3.y)
                {
                    if (this.y < des3.y)
                    {
                        this.y++;
                    }
                    else
                    {
                        this.y--;
                    }
                }
                if (this.x == des3.x && this.y == des3.y)
                {
                    this.loopStep = 4;
                }
            }
            else
            {
                this.loopStep = 4;
            }
        }
        else if (this.loopStep == 4)
        {
            if (des4 != null)
            {
                if (this.x != des4.x)
                {
                    if (this.x < des4.x)
                    {
                        this.x++;
                    }
                    else
                    {
                        this.x--;
                    }
                }
                if (this.y != des4.y)
                {
                    if (this.y < des4.y)
                    {
                        this.y++;
                    }
                    else
                    {
                        this.y--;
                    }
                }
                if (this.x == des4.x && this.y == des4.y)
                {
                    this.loopStep = 1;
                }
            }
            else
            {
                this.loopStep = 1;
            }
        }
    }

    protected void renderEntity()
    {
        glPushMatrix();
        glPushAttrib(GL_CURRENT_BIT);
        //Follow this format for OpenGL texture drawing
        glColor3f(1, 0, 0);
        glBegin(GL_QUADS);
        {
            glVertex2d(x, y);
            glVertex2d(x + width, y);
            glVertex2d(x + width, y - height);
            glVertex2d(x, y - height);
        }
        glEnd();
        glPopAttrib();
        glPopMatrix();
    }

    public void teleport(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public void move(double x, double y)
    {
        this.x += x;
        this.y += y;
    }

    /**
     *
     * @return Returns true if the entity is collided left with the player
     */
    protected boolean getEntityLeftCollided()
    {
        double leftSide = this.x;
        double top = this.y;
        double rightSide = leftSide + this.width;
        double bottom = top - this.height;

        double leftSidePlayer = thePlayer.getX();
        double topPlayer = thePlayer.getY();
        double rightSidePlayer = leftSidePlayer + thePlayer.getWidth();
        double bottomPlayer = topPlayer - thePlayer.getHeight();

        return ((rightSide >= leftSidePlayer && rightSide <= rightSidePlayer) && ((top <= topPlayer && top >= bottomPlayer) || (bottom >= bottomPlayer && bottom <= topPlayer)));
    }

    /**
     *
     * @return Returns true if the entity is collided right with the player
     */
    protected boolean getEntityRightCollided()
    {
        double leftSide = this.x;
        double top = this.y;
        double rightSide = leftSide + this.width;
        double bottom = top - this.height;

        double leftSidePlayer = thePlayer.getX();
        double topPlayer = thePlayer.getY();
        double rightSidePlayer = leftSidePlayer + thePlayer.getWidth();
        double bottomPlayer = topPlayer - thePlayer.getHeight();

        return ((leftSide <= rightSidePlayer && leftSide >= leftSidePlayer) && ((top <= topPlayer && top >= bottomPlayer) || (bottom >= bottomPlayer && bottom <= topPlayer)));
    }

    /**
     *
     * @return Returns true if the entity is collided at the bottom with the player
     */
    protected boolean getEntityBottomCollided()
    {
        double leftSide = this.x;
        double top = this.y;
        double rightSide = leftSide + this.width;
        double bottom = top - this.height;

        double leftSidePlayer = thePlayer.getX();
        double topPlayer = thePlayer.getY();
        double rightSidePlayer = leftSidePlayer + thePlayer.getWidth();
        double bottomPlayer = topPlayer - thePlayer.getHeight();

        return ((bottom <= topPlayer && bottom >= bottomPlayer) && ((rightSide >= leftSidePlayer && rightSide <= rightSidePlayer) || (leftSide <= rightSidePlayer && leftSide >= leftSidePlayer)));
    }

    /**
     *
     * @return Returns true if the entity is collided at the top with the player
     */
    protected boolean getEntityTopCollided()
    {
        double leftSide = this.x;
        double top = this.y;
        double rightSide = leftSide + this.width;
        double bottom = top - this.height;

        double leftSidePlayer = thePlayer.getX();
        double topPlayer = thePlayer.getY();
        double rightSidePlayer = leftSidePlayer + thePlayer.getWidth();
        double bottomPlayer = topPlayer - thePlayer.getHeight();

        return ((top >= bottomPlayer && top <= topPlayer) && ((rightSide >= leftSidePlayer && rightSide <= rightSidePlayer) || (leftSide <= rightSidePlayer && leftSide >= leftSidePlayer)));
    }

    public String getEntityName()
    {
        return entityName;
    }

    public void setEntityName(String entityName)
    {
        this.entityName = entityName;
    }

    public ControlledEntityPlayer getThePlayer()
    {
        return thePlayer;
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

    public double getDefaultY()
    {
        return defaultY;
    }

    public void setDefaultY(double defaultY)
    {
        this.defaultY = defaultY;
    }

    public double getDefaultX()
    {
        return defaultX;
    }

    public void setDefaultX(double defaultX)
    {
        this.defaultX = defaultX;
    }

    public double getY()
    {
        return y;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    public double getX()
    {
        return x;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public EntityEnemy getEnemyInstance()
    {
        return this instanceof EntityEnemy ? (EntityEnemy) this : null;
    }

    public EntityNPC getNPCInstance()
    {
        return this instanceof EntityNPC ? (EntityNPC) this : null;
    }
}