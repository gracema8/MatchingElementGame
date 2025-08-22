import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class GameOverText extends Actor
{
    
    public GameOverText(int value) {
        setImage(new GreenfootImage("Game Over, You made " + value + " attempts", 30, Color.WHITE, Color.RED));
    }
    
    
    public void act()
    {
        
    }
}
