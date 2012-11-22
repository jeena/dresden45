import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Iterator;
import javax.swing.*;

public class Box extends JFrame
{
    private LinkedList<Drawable> drawable = new LinkedList<Drawable>(); 
    private LinkedList<Collisionable> collisionable = new LinkedList<Collisionable>(); 
    // Bullet 1, Tank 2, Plane 3, 

    public Box(String title)
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public void step()
    {
        // Move
                
        for(Iterator<Drawable> it = this.drawable.iterator(); it.hasNext();)
        {
            it.next().step();
        }
        
        // Check collision
        
        for(int i = 0; i < this.collisionable.size(); i++)
        {
            Collisionable object = this.collisionable.get(i);
            
            for(Iterator<Collisionable> it = this.collisionable.iterator(); it.hasNext();)
            {
                Collisionable object2 = it.next();
                
                if(
                    object != object2 &&
                    object.getX() <= object2.getX() &&
                    object.getX() + object.getWidth() >= object2.getX() &&
                    object.getY() <= object2.getY() &&
                    object.getY() + object.getHeight() >= object2.getY()
                )
                {
                    object.collisionedWith(object2.collisionCheckSum());
                    object2.collisionedWith(object.collisionCheckSum());
                }
            }
            
            if(object.isDead(this))
            {
                this.drawable.remove(i);
                this.collisionable.remove(i);
            }
            
        }
    }
       
    public void paint(Graphics g)
    {
        super.paint(g);
        
        for(Iterator<Drawable> it = this.drawable.iterator(); it.hasNext();)
        {
            it.next().draw(g);
        }        
    }
    
    public void addObject(Drawable graphic)
    {
        this.drawable.add(graphic);
        this.collisionable.add((Collisionable)graphic);
    }
}