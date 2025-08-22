import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

public class MyWorld extends World
{
    int pairs = 4;
    Counter counter;
    boolean gameOver = false;
    
    public MyWorld()
    {
        super(800, 410, 1);
        // creates a dark gray background color
        getBackground().setColor(Color.DARK_GRAY);
        getBackground().fill();
        // creates the first level
        init();
    }
    
    private void init()
    {
        // initialize class field
    
        Card.paired = 0; // to count matches as they are made
        Card.attempts = 0;
         // clear the world of (possible) old objects
        removeObjects(getObjects(null));
        counter = new Counter();
        addObject(counter, 50, 100);
       
        gameOver = false;
     
        // shuffle the image numbers (so the same level does not always use the same images)
        Integer[] fileNums = { 1, 2, 3, 4, 5, 6, 7, 8 };
        Map<Integer, String> symbolMap = Map.of(1, "N", 2, "K", 3, "Fe", 4, "Cl", 5, "Na", 6, "Co", 7, "He", 8, "Zn");
        Map<Integer, String> nameMap = Map.of(1, "Nitrogen", 2, "Potassium", 3, "Iron", 4, "Chlorine", 5, "Sodium", 6, "Cobalt", 7, "Helium", 8, "Zinc");
        Collections.shuffle(Arrays.asList(fileNums));
        // build an array of cards for this level (cards created and assigned to the array)
        Card[] cards = new Card[pairs*2];
        for (int i=0; i<pairs*2; i++) cards[i] = new Card();
        // assign the cards the references to their face images and their matching cards
        // (1 <> 2, 3 <> 4, 5 <> 6, etc.) then shuffles the cards in the array
        for (int i=0; i<cards.length; i+=2)
        {
            // notify cards of their matching cards
            cards[i].setMatch(cards[i+1]);
            cards[i+1].setMatch(cards[i]);
            // set same face image to both cards
            cards[i].setImage(symbolMap.get(fileNums[i/2]));
            cards[i+1].setImage(nameMap.get(fileNums[i/2]));
        }
        Collections.shuffle(Arrays.asList(cards)); // shuffle cards
        // add the cards into the world (at calculated locations)
        int rows = cards.length < 10 ? 2 : 3; // determine number of rows
        int loWide = cards.length/rows; // determine minimum cards per row
        int xtra = cards.length-rows*loWide; // number of extra cards to add into rows
        int xtrasPlaced = 0; // to track number of extras placed in world
        int y0 = getHeight()/2-125*(rows-1)/2; // determine starting y-coordinate for rows
        for (int r=0; r<rows; r++)
        {
            // determine number of cards for this row
            int wide = loWide +(xtra > 0 ? 1-(xtra+r)%2 : 0);
            // determine starting x-coordinate for this row 
            int x0 = getWidth()/2-125*(wide-1)/2;
            // add cards for this row into world
            for (int c=0; c<wide; c++)
                addObject(cards[r*loWide+c+xtrasPlaced], x0+c*125, y0+r*125);
            if (wide > loWide) xtrasPlaced++; // update extras placed
        }
    }
    
    public void endGame() {
        
        gameOver = true;
        addObject(new ReplayBtn(), 400, 20);
        addObject(new GameOverText(Card.attempts), 400, 200);
    }
    
    public void act()
    {
        // proceeds to next level if all cards are matched
        counter.value = Card.attempts;
        if (Card.paired == pairs) endGame();
    }
    
}
    

