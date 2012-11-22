import javax.swing.*;

public class Main
{
    public static void main(String[] args)
    {
        Box box = new Box("Tank");
        box.setSize(600, 600);
        
        Tank tank = new Tank(box);
        box.addObject(tank);
        
        TankEvent controll = new TankEvent(tank);
        box.addKeyListener(controll);
                
        /* game loop */
		
        while(true)
        {
            controll.keyMonitoring();
            
            box.step();                        
            box.repaint();

            try
            {
                Thread.sleep(15);
            }
            catch(InterruptedException e)
            {
                break;
            }
        }
    }
}