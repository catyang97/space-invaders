/*
 *  Execution:  
 *  This program is the game Space Invaders. It requires LinkedList.java, 
 *  Space_Invaders_Spaceship.png, and PennDraw.java in order to execute properly. 
 *  It includes 4 classes for Alien, Laser, Spaceship, and Button. To play the
 *  game, run java SpaceInvaders and click on either PLAY or INSTRUCTIONS. The 
 *  instructions for how to play the game are detailed in the INSTRUCTIONS page.
 */ 

public class SpaceInvaders {
    // create a LinkedList of Alien objects
    private static LinkedList<Alien> aliens = new LinkedList<Alien>();
    // create Spaceship and Alien objects
    private static Spaceship spaceship;
    private static Alien alien;
    // specify the number of rows and columns of Aliens
    private static int col = 8;
    private static int row = 4;
    private static int size = row * col;
    // specify how much the Aliens should move horizontally
    private static double update = 0.002;
    // initialize the spaceship's Laser to be null until the right key is pressed
    private static Laser laser = null;
    // create a LinkedList of Lasers for the Aliens to shoot
    private static LinkedList<Laser> laserList = new LinkedList<Laser>();
    // start with a score of 0
    private static int score = 0; 
    // specify how many lives the user should receive
    private static int lives = 5;
    // specify how frequent the Aliens should shoot (lower number = fewer lasers)
    private static double alienLaserFreq = 0.0007;
    // create a LinkedList to hold the dead Aliens
    private static LinkedList<Integer> removed = new LinkedList<Integer>();
    // String containing the keys that could be pressed to shoot Lasers
    private static String controls = "space invdr";
    // start with the char at this index
    private static int stringIndex = 5;
    // booleans for different screens
    private static boolean startScreen = true;
    private static boolean playing = false;
    private static boolean instructions = false;
    private static boolean game = false;
    private static boolean pause = false;
    private static boolean firstTime = true;
    
    public static void main(String[] args) {
        PennDraw.setCanvasSize(700, 700);
        PennDraw.clear(PennDraw.BLACK);
        
        // create the Start Screen with the game name and buttons for 
        // START and INSTRUCTIONS
        PennDraw.setPenColor(PennDraw.WHITE);
        PennDraw.setFontBold();
        PennDraw.setFontSize(48);
        PennDraw.text(0.5, 0.7, "SPACE INVADERS");
        PennDraw.setFontPlain();
        PennDraw.setFontSize(30);
        Button startButton = new Button(0.5, 0.5, "START", 0.08, 0.04);
        Button instructionsButton = new Button(0.5, 0.35, "INSTRUCTIONS", 
                                               0.17, 0.04);
        Alien leftAlien = new Alien(0.15, 0.705);
        leftAlien.draw();
        Alien rightAlien = new Alien(0.85, 0.705);
        rightAlien.draw();
        PennDraw.enableAnimation(30);
        
        // continously check if either button has been clicked
        while (startScreen) {
            if (instructionsButton.clicked()) {
                // leave the startScreen and go to the instructions page
                instructions = true;
                break;
            } 
            if (startButton.clicked()) {
                // leave the startScreen and go to the game
                playing = true;
                game = true;
                break;
            }
        }
        
        while (instructions) {
            // display the instructions
            PennDraw.clear(PennDraw.BLACK);
            PennDraw.setPenColor(PennDraw.WHITE);
            PennDraw.setFontSize(20);
            PennDraw.text(0.5, 0.85, "Objective: Destroy all of the pesky " +
                          "space invaders");
            PennDraw.text(0.5, 0.8, "and avoid their lethal lasers.");
            PennDraw.text(0.5, 0.7, "Controls: Use the mouse to move the " +
                          "spaceship.");
            PennDraw.text(0.5, 0.65, "The key to shoot lasers starts with " + 
                          "the space bar.");
            PennDraw.text(0.5, 0.6, "However, every time you lose a life, this " +
                          "control changes");
            PennDraw.text(0.5, 0.55, "to a random letter in 'space invaders' " +
                          "(including the space bar)");
            PennDraw.text(0.5, 0.5, "You need to test each letter to find right " +
                          "key in order to"); 
            PennDraw.text(0.5, 0.45, "continue shooting.");
            PennDraw.text(0.5, 0.35, "Note that you can only shoot one laser at " +
                          "a time");
            PennDraw.text(0.5, 0.3, "and you only have 5 lives.");
            // create a button that if clicked, starts the game
            PennDraw.setFontSize(30);
            Button instructionButton = new Button(0.5, 0.1, "PLAY",
                                                  0.065, 0.04);
            if (instructionButton.clicked()) {
                playing = true;
                game = true;
                break;
            }
            PennDraw.advance();
        }
        
        while (game) { // this loop allows the pause feature to work
            // create the user's spaceship
            spaceship = new Spaceship(0.5, 0.02); 
            // only adds new Aliens if this is the first time starting the game
            // so that resuming from the pause screen doesn't create new Aliens
            if (firstTime) {
                for (int c = 0; c < col; c++) {
                    for (int r = 0; r < row; r++) {
                        // add a new Alien at each position to the LinkedList aliens
                        alien = new Alien(c*0.1 + 0.1, 0.8 - (r * 0.1));
                        aliens.add(alien);
                    }
                }
            }
            while (playing) {
                // clear screen each iteration
                PennDraw.clear(PennDraw.BLACK);
                // display the current score and number of lives
                PennDraw.setPenColor(PennDraw.WHITE);
                PennDraw.setFontSize(20);
                PennDraw.text(0.1, 0.9, "Score: " + Integer.toString(score));
                PennDraw.text(0.9, 0.9, "Lives: " + Integer.toString(lives));
                
                // clicking this button pauses the game
                Button pauseButton = new Button(0.9, 0.95, "PAUSE", 0.09, 0.03);
                if (pauseButton.clicked()) {
                    playing = false;
                    pause = true;
                    break;
                }
                
                // updates the position of the Aliens and draws them if the Alien at
                // the index has not already been killed
                for (int i = 0; i < aliens.size(); i++) {
                    if (!removed.contains(i)) {
                        Alien alienHere = aliens.get(i);
                        double xHere = alienHere.getX();
                        alienHere.changeX(xHere + update);
                        alienHere.draw();
                        
                        // randomly shoot Lasers from the current Alien
                        if (Math.random() < alienLaserFreq) {
                            Laser alienLaser = alienHere.shoot(-0.01);
                            // add this Laser to laserList so that it will continue
                            // to be drawn and updated
                            laserList.add(alienLaser);
                        } 
                    }
                }
                
                // draw and move each laser in laserList
                for (int i = 0; i < laserList.size(); i++) {
                    Laser laserHere = laserList.get(i);
                    laserHere.draw();
                    laserHere.move();
                    // check if the spaceship has been hit and if so, subtract a life,
                    // remove the laser from the list, and change the controls for 
                    // shooting a laser to a different key
                    if (spaceship.hit(laserHere)) {
                        lives --;
                        laserList.remove(i);
                        stringIndex = (int) Math.floor(Math.random() * 11);
                    }
                }

                // create new for loop so that all positions are updated before 
                // checking for collision
                for (int i = 0; i < aliens.size(); i++) {
                    Alien alienHere = aliens.get(i);
                    // if the Aliens hit a wall, send them in the opposite direction
                    if (alienHere.collisionWall()) {
                        update = -update;
                        break;
                    }    
                }
                
                // iterate through the aliens and check the spaceship's laser has hit
                // an Alien
                for (int i = 0; i < aliens.size(); i++) {
                    Alien alienHere = aliens.get(i);
                    if (laser != null && !removed.contains(i)) {
                        if (alienHere.hit(laser)) {
                            // add the Alien to the removed list 
                            removed.add(i);
                            // update the score by 100
                            score += 100;
                            // decrease the size counter for the Aliens
                            size --;
                            // remove the Laser
                            laser = null;
                            break;
                        }
                    }
                }
                
                // draw and move the Spaceship
                // draw and move the Laser if it has been shot
                spaceship.draw();
                if (laser != null) {
                    laser.draw();
                }
                spaceship.move();
                if (laser != null) {
                    laser.move();
                    
                }
                
                // check if a key has been typed
                if (PennDraw.hasNextKeyTyped()) {
                    // get the right control that will shoot the Laser
                    char right = controls.charAt(stringIndex);
                    char key = PennDraw.nextKeyTyped(); 
                    // shoot the Laser if the right key has been typed
                    if (key == right) {
                        laser = spaceship.shootLaser(0.01);
                    }
                }
                
                // exit the game if all the Aliens have been killed or
                // all the lives have been depleted
                if (size <= 0 || lives <= 0) {
                    playing = false;
                    break;
                }
                PennDraw.advance();
            }       
            
            PennDraw.clear(PennDraw.BLACK);
            PennDraw.setPenColor(PennDraw.WHITE);
            PennDraw.setFontSize(40);
            // display if the user has won or lost
            if (size <= 0) {
                PennDraw.text(0.5, 0.5, "YOU WIN!");
            }
            if (lives <= 0) {
                PennDraw.text(0.5, 0.5, "YOU LOSE!");
            }
            
            // if the pause button has been clicked, display this screen
            while (pause) {
                // create a button that if clicked will return to the game
                Button resumeButton = new Button(0.5, 0.5,"RESUME", 0.17, 0.04);
                if (resumeButton.clicked()) {
                    playing = true;
                    firstTime = false;
                    pause = false;
                }
                PennDraw.advance();
            }
            PennDraw.advance();
        }
    }
}
