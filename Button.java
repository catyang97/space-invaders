/*
 *  Button: The button class specifies how a button looks and behaves in Space Invaders.
 */

class Button {
    private double x;
    private double y;
    private double rectHalfWidth;
    private double rectHalfHeight;
    
    /* Constructs a rectangle with text imput and the height and width for a Rectangle
     * @param x the x coordinate of the text
     * @param y the y coordinate of the text
     * @param text to be displayed in the rectangle
     * @param rectHalfWidth- half the width of the rectangle
     * @param rectHalfHeight- half the height of the rectable
     */
    public Button(double x, double y, String text, 
                  double rectHalfWidth, double rectHalfHeight) {
        this.x = x;
        this.y = y;
        this.rectHalfWidth = rectHalfWidth;
        this.rectHalfHeight = rectHalfHeight;
        PennDraw.text(x, y, text);
        PennDraw.rectangle(x, y + 0.005, rectHalfWidth, rectHalfHeight);
        
    }
    
    /* Detects if the mouse has been pressed inside the rectangle
     * @return true if all the conditions have been met
     *         false if the rectangle has not been clicked
     */
    public boolean clicked() {
        return (PennDraw.mousePressed() && 
                PennDraw.mouseX() <= x + rectHalfWidth && 
                PennDraw.mouseX() >= x - rectHalfWidth && 
                PennDraw.mouseY() <= y + rectHalfHeight && 
                PennDraw.mouseY() >= y - rectHalfHeight);
    }
}

