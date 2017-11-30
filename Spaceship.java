/*
 *  Spaceship: The Spaceship class specifies how the Spaceship (controlled by the player)
 *  looks, moves, and functions in Space Invaders.
 */

class Spaceship {
    private double x;
    private double y;
    private double dx = 0;
    private double shipHeight = 100; // in pixels
    private double shipWidth = 130; // in pixels
    
    /* Constructs Spaceship at the specified x and y positions
     * @param x the x coordinate of the Laser
     * @param y the y coordinate of the Laser
     */
    public Spaceship(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    /* Draws the Spaceship using the png file
     * @return void
     */
    public void draw() {
        PennDraw.picture(x, y, "Space_Invaders_Spaceship.png", shipWidth, shipHeight);
    }
    
    /* Moves the Spaceship by detecting changes in mouse position
     * @return void
     */
    public void move() {
        dx = PennDraw.mouseX() - x;
        x = PennDraw.mouseX();
    }
    
    /* Creates a new Laser at the tip of the Spaceship and with specified velocity
     * @param velocity of the Laser
     * @return the newly created Laser
     */
    public Laser shootLaser(double velocity) {
        Laser laser = new Laser(x, shipHeight / 1400 + y, velocity);
        return laser;
    }
    
    // getters for x and y positions
    public double getX() { return x; }
    public double getY() { return y; }
    
    /* Detects if the Spaceship has been hit by an Alien Laser
     * @param Laser with which to check if the Spaceship has been hit
     * @return true if the Spaceship has been hit
     *         false if no hit has occured
     */
    public boolean hit(Laser laser) {
        return(laser.getX() <= getX() + shipWidth / 1400 &&
               laser.getX() >= getX() - shipWidth / 1400 && 
               laser.getY() >= getY() - shipHeight / 1400 &&
               laser.getY() <= getY() + shipHeight / 1400);
    }
}
