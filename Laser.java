/*
 *  Laser: The Laser class specifies the construction, look, and behavior of
 *  the lasers that the player and the Aliens shoot in Space Invaders.
 */
class Laser {
    private double x;
    private double y;
    private double vy;
    private double width = 0.004;
    
    /* Constructs Laser at the specified x and y positions and with the vy
     * @param x the x coordinate of the Laser
     * @param y the y coordinate of the Laser
     * @param the velocity of the Laser
     */
    public Laser(double x, double y, double vy) {
        this.x = x;
        this.y = y;
        this.vy = vy;
    }
    
    /* Draws the Laser as a green rectangle
     * @return void
     */
    public void draw() {
        PennDraw.setPenColor(PennDraw.GREEN);
        PennDraw.filledRectangle(x, y, width, 0.015);
    }
    
    /* Moves the y position of the Laser by vy
     * @return void
     */
    public void move() {
        y += vy;
    }
    
    // getters for x and y
    public double getX() { return x; }
    public double getY() { return y; }
}
