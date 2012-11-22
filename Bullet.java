import javax.swing.*;
import java.awt.*; 
import java.awt.Graphics;
import java.awt.Canvas;

public class Bullet implements Drawable, Collisionable
{
    private int x, y, width = 1, height = 1, muzzleAngle, startX, startY;
    private double speedX, speedY, startSpeedX, startSpeedY, v0;
    private JFrame frame;
    private boolean dead = false;
    private long start;
    
    public Bullet(
        int muzzleAngle,
        int x,
        int y,
        double parentSpeedX,
        double parentSpeedY
    )
    {
        this.start = System.currentTimeMillis();
        
        this.startX = x;
        this.startY = y;
        
        this.x = x;
        this.y = y;
        this.frame = frame;
        
        int startSpeed = 10;
        
        this.muzzleAngle = muzzleAngle;
        
        this.speedX = 
            SinCosLookup.getCos(muzzleAngle) * startSpeed +
            SinCosLookup.getCos(muzzleAngle) * parentSpeedX
        ;
        this.startSpeedX = this.speedX;
        
        this.speedY =
            SinCosLookup.getSin(muzzleAngle) * startSpeed +
            SinCosLookup.getSin(muzzleAngle) * parentSpeedY
        ;
        this.startSpeedY = this.speedY;
        
        this.v0 = Math.sqrt(Math.pow(this.startSpeedX, 2) + Math.pow(this.startSpeedY, 2));
    }
    
    public void step()
    {
        double t = (System.currentTimeMillis() - this.start);
        this.x = (int)(this.v0 * t * SinCosLookup.getCos(this.muzzleAngle)) + this.startX;
        
        double g = -0.001;
        this.y = (int)(this.startY + this.v0 * t * SinCosLookup.getSin(this.muzzleAngle) - (g / 2) * Math.pow(t, 2));
        System.out.println("y = " + this.y + "\tx = " + this.x + "\tt = " + t);
        
        //        this.x += (int)this.speedX;      
        //        this.speedY += 0.4;         
        //        this.y += (int)this.speedY;
        
    }
    
    // Drawable methods
    
    public void draw(Graphics g)
    {        
        g.drawRect(
            this.x,
            this.y,
            1,
            1
        );
    }
    
    public boolean isDead(Box box)
    {
        if(this.y > box.getHeight())
        {
            return true;
        }
        
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
        return 6;
    }
    
    public void collisionedWith(int checkSum)
    {
        if((checkSum&0xFF & collisionCheckSum()&0xFF) == 0)
        {            
            this.dead = true;
        }

    }
}