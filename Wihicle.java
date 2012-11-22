import java.awt.Toolkit;
import javax.swing.*;
import java.awt.*; 
import java.awt.Graphics;
import java.awt.Canvas;

public class Wihicle implements Drawable, Collisionable
{
    private Box box;
    public int frameHeight, frameWidth;
    private int x, y, width, height;
    private int wihicleX = 0, wihicleY, wihicleWidth = 30, wihicleHeight = 10, step = 3;
    private int muzzleAngle = 270, muzzleStep = 5, muzzleLength = 15;
    private int muzzleX, muzzleY;
    private double speed = 0, muzzleSpeed = 0;
    private double[] sin, cos;
    private long lastBulletTime = 0;
    private boolean dead = false;
        
    public Wihicle()
    {
    }
    
    public void step()
    {
        if(Math.abs(this.speed) > 0.1 || Math.abs(this.muzzleSpeed) > 0.1)
        {
            computeMove();
        }
    }
    
    private void computeMove()
    {
    }
    
    public void goRight()
    {        
        if(this.wihicleX < this.frameWidth - this.wihicleWidth)
            this.speed = this.step;
    }
    
    public void goLeft()
    {
        if(this.wihicleX > 0)
            this.speed = - this.step;
    }
    
    public void goUp()
    {
        
    }
    
    public void goDown()
    {
        
    }
    
    public void muzzleLeft()
    {
        if(this.muzzleAngle > 200)
        {
            this.muzzleSpeed -= this.muzzleStep;
            this.muzzleAngle -= this.muzzleStep;
        }
    }
    
    public void muzzleRight()
    {
        if(this.muzzleAngle < 340)
        {
            this.muzzleSpeed += this.muzzleStep;
            this.muzzleAngle += this.muzzleStep;
        }
    }
    
    public void fire()
    {
        long currentTime = System.currentTimeMillis();
        
        if(currentTime - this.lastBulletTime > 500)
        {
            Bullet bullet = new Bullet(
                this.muzzleAngle,
                this.wihicleX + (int)(width*0.5) + this.muzzleX,
                this.wihicleY + this.muzzleY,
                this.speed,
                0
            );

            this.box.addObject(bullet);
            
            this.lastBulletTime = currentTime;
        } 
    }
    
    // Drawable methods

    public void draw(Graphics g)
    {        
        g.drawRect(
            this.wihicleX,
            this.wihicleY,
            this.wihicleWidth,
            this.wihicleHeight
        );
        
        int muzzleX = this.wihicleX + (int)(width*0.5);
        g.drawLine(
            muzzleX,
            this.wihicleY,
            muzzleX + this.muzzleX,
            this.wihicleY + this.muzzleY
        );        
    }
    
    public boolean isDead(Box box)
    {
        if(this.dead)
            Toolkit.getDefaultToolkit().beep();
        
        return this.dead;
    }
    
    // Collisionable methods
    
    public int getX()
    {
        return this.x;
    }
    
    public int getY()
    {
        return this.y;
    }
    
    public int getWidth()
    {
        return this.width;
    }
    
    public int getHeight()
    {
        return this.height;
    }
    
    public int collisionCheckSum()
    {
        return 1;
    }
    
    public void collisionedWith(int checkSum)
    {
        if((checkSum&0xFF & collisionCheckSum()&0xFF) == 0)
        {            
            this.dead = true;
        }
    }
}