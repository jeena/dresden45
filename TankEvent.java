import java.awt.event.*;

public class TankEvent extends KeyAdapter
{
    Tank tank;
    protected static boolean keys[] = new boolean[256];
    
    public TankEvent(Tank tank)
    {
        this.tank = tank;
    }
    
    public void keyPressed(KeyEvent e)
    {
        keys[e.getKeyCode()&0xff] = true;
    }
    
    public void keyReleased(KeyEvent e)
    {
        keys[e.getKeyCode()&0xff] = false;
    }    
    
    public void keyMonitoring()
    {
        if(keys[KeyEvent.VK_LEFT&0xff])
            this.tank.goLeft();
            
        if(keys[KeyEvent.VK_RIGHT&0xff])
            this.tank.goRight();
            
        if(keys[KeyEvent.VK_UP&0xff])
            this.tank.muzzleLeft();
            
        if(keys[KeyEvent.VK_DOWN&0xff])
            this.tank.muzzleRight();
            
        if(keys[KeyEvent.VK_SPACE&0xff])
            this.tank.fire();
    }
}