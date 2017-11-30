/*
 *  Alien: This is the Alien class that specifies how the Alien
 *  works in the Space Invaders game.
 *
 */

class Alien {
    private double x;
    private double y;
    private double alienWidth;
    private double alienHeight;
    
    /* Constructs an Alien at the specified x and y positions
     * @param x the x coordinate of the Alien
     * @param y the y coordinate of the Alien
     */
    public Alien(double x, double y) {
        // set x and y so that it can been accessed throught the class
        this.x = x;
        this.y = y;
    }
    
    /* Draws the Alien using PennDraw and calculates the height and width
     * @return void
     */
    public void draw() {
        double mainBodyHalfWidth = 0.021;
        double sidesHalfWidth = 0.007;
        double mainBodyHalfHeight = 0.015;
        double sidesHalfHeight = 0.015;
        double antennaeHalfHeight = 0.0035;
        double bottomHalfHeight = 0.0049;
        PennDraw.setPenColor(PennDraw.WHITE);
        PennDraw.filledRectangle(x, y, mainBodyHalfWidth, mainBodyHalfHeight);
        PennDraw.filledRectangle(x + 0.0255, y, sidesHalfWidth, sidesHalfHeight);
        PennDraw.filledRectangle(x-0.0255, y, sidesHalfWidth, sidesHalfHeight);
        PennDraw.filledSquare(x + 0.011, y + 0.018, antennaeHalfHeight);
        PennDraw.filledSquare(x - 0.011, y + 0.018, antennaeHalfHeight);
        PennDraw.filledSquare(x + 0.018, y + 0.025, antennaeHalfHeight);
        PennDraw.filledSquare(x - 0.018, y + 0.025, antennaeHalfHeight);
        PennDraw.filledRectangle(x - 0.0095, y - 0.015, 0.005, bottomHalfHeight);
        PennDraw.filledRectangle(x + 0.0095, y - 0.015, 0.005, bottomHalfHeight);
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.filledSquare(x - 0.011, y + 0.006, 0.0035);
        PennDraw.filledSquare(x + 0.011, y + 0.006, 0.0035);
        PennDraw.filledRectangle(x, y - 0.0125, 0.0145, 0.003);
        PennDraw.filledRectangle(x + 0.024, y - 0.0085, 0.003, 0.0065);
        PennDraw.filledRectangle(x - 0.024, y - 0.0085, 0.003, 0.0065);
        PennDraw.filledRectangle(x + 0.031, y + 0.01, 0.003, 0.0055);
        PennDraw.filledRectangle(x - 0.031, y + 0.01, 0.003, 0.0055);
        PennDraw.filledSquare(x + 0.026, y + 0.014, 0.0035);
        PennDraw.filledSquare(x-0.026, y+0.014, 0.0035);
        alienWidth = mainBodyHalfWidth * 2 + sidesHalfWidth * 4;
        alienHeight = mainBodyHalfHeight * 2 + antennaeHalfHeight * 4 
            + bottomHalfHeight * 2;
    }
    
    // getters that return x, y, and alienWidth
    public double getX() { return x; }
    public double getY() { return y; }
    public double getAlienWidth() { return alienWidth; }
    
    /* Detects if the Alien has hit the side of the canvase
     * @return true if a collision has occured
     *         false if no collision has occured
     */
    public boolean collisionWall() {
        double LX = x - alienWidth/2;
        double RX = x + alienWidth/2;
        return (LX < 0 || RX > 1);
    }
    
    /* Detects if the Alien has been hit by the spaceship's Laser
     * @param Laser with which to check if the Alien has been hit
     * @return true if the Alien has been hit
     *         false if no hit has occured
     */
    public boolean hit(Laser laser) {
        return(laser.getX() <= getX() + alienWidth/2 &&
               laser.getX() >= getX() - alienWidth/2 && 
               laser.getY() >= getY() - alienHeight/2 && 
               laser.getY() <= getY() + alienHeight/2);
    }
    
    /* Changes the x to be newX
     * @param newX that represents the updated x
     * @return void
     */
    public void changeX(double newX) {
        x = newX;
    }
    
    /* Creates a Laser at the Alien's x and y positions with specified velocity
     * @param velocity for the new Laser
     * @return the new Laser
     */
    public Laser shoot(double velocity) {
        Laser alienLaser = new Laser(getX(), getY(), velocity);
        return alienLaser;
    }  
}
