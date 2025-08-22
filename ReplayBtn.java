import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class ReplayBtn extends Actor
{
    public ReplayBtn() {
        setImage(new GreenfootImage("Restart", 18, Color.BLACK, Color.GREEN));
    }
    
    public void act()
    {
        if (Greenfoot.mouseClicked(this)) {
                
                Greenfoot.setWorld(new MyWorld());
            }
    }
}
