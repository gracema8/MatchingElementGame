import greenfoot.*;
 
public class Card extends Actor
{
    static int paired; // counts matches made during a level
    static Card first, second; // holds the clicked on cards
    public static int attempts;
     
    GreenfootImage[] images = new GreenfootImage[2]; // the images for this actor
    Card match; // the card that matches this one
     
    /** used to obtain the matching card to this one */
    public void setMatch(Card c)
    {
        match = c;
    }
     
    /** used to obtain the face up image of this card (and creates a random back image) */
    public void setImage(String front)
    {
        // gets and sizes its face image
        images[1] = new GreenfootImage(" "+front+" ", 18, Color.WHITE, Color.BLACK);
        images[1].scale(100, 100);
        // chooses random color part values for the fill color of the back image
        int red = 128+Greenfoot.getRandomNumber(128);
        int blue = 128+Greenfoot.getRandomNumber(128);
        int green = 128+Greenfoot.getRandomNumber(128);
        // creates and paints the back image with fill color
        images[0] = new GreenfootImage(100, 100);
        images[0].setColor(new Color(red, blue, green));
        images[0].fill();
        // draws a rectangle (or square) on the back image
        images[0].setColor(Color.BLACK);
        images[0].drawRect(5, 5, 90, 90);
        // sets the initial image of the card to the back image
        setImage(images[0]);
    }
     
    /** checks for and processes clicks on this card */
    public void act()
    {
        if (Greenfoot.mouseClicked(this) && getImage() == images[0])
        {
            attempts++;
            if (first == null) // first clicked card
            {
                first = this;
                flip();
                return;
            }
            if (second == null) // second clicked card
            {
                second = this;
                flip();
                Greenfoot.delay(30);
                if (first != match) // not a match
                {
                    first.flip();
                    flip(); // or 'this.flip();' or 'second.flip();'
                }
                else paired++; // matching
                 
                // clears picks to allow for new ones
                first = null;
                second = null;
            }
        }
    }
         
    /** switches the image of this card between its two assigned images */
    private void flip()
    {
        setImage(getImage() == images[0] ? images[1] : images[0]);
    }
}