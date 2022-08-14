package game;

import interfaces.GameInterface;
import java.awt.Graphics2D;
import java.awt.Color;

/**
 * Class representing the menu
 */
public class MenuScreen {
    
    private GameInterface gameRef           = null;
    private Graphics2D g2d                  = null;

    //images
    // private BufferedImage starSelected      = null;
    
    //sounds
    // private Audio intro                     = null;
    
    //menu control
    private byte selectorPosition           = 0;
    // private byte selectors[]                = {0, 2, 3};
    // private short optionsSpace              = 65;
    private byte level                      = 1;
    private volatile boolean goOptions      = false;
    private volatile boolean goGame         = false;
    private volatile boolean goExit         = false;
    private final Color menuColor           = new Color(0, 66, 147);
    private int resolutionW                 = 0;
    private int resolutionH                 = 0;

    /**
     * Constructor
     * @param game
     */
    public MenuScreen(GameInterface game) {

        //get the game pointer
        this.gameRef            = game;
        this.g2d                = this.getG2D();
        this.resolutionW        = this.gameRef.getInternalResolutionWidth();
        this.resolutionH        = this.gameRef.getInternalResolutionHeight();

        //load the images
        // this.tlogo              = LoadingStuffs.getInstance().getImage("tlogo");

        //load the sounds
        // this.intro              = LoadingStuffs.getInstance().getAudio("intro");
        
    }

    /**
     * Game logic update
     * @param frametime
     */
    public synchronized void update(long frametime) {
        this.gameRef.changeGameState(StateMachine.IN_GAME);
    }

    /**
     * Stop the menu music
     */
    public void stopMusic() {
        // this.intro.stop();
    }

    /**
     * Draw method
     * @param frametime
     */    
    public synchronized void draw(long frametime) {
        this.g2d.setBackground(this.menuColor);
        this.g2d.clearRect(0, 0, resolutionW, resolutionH);
    }

    /**
     * 
     * @param frametime
     */
    public synchronized void firstUpdate(long frametime) {
        // this.intro.playContinuously();
    }

    //getters
    public boolean goOptions()                  {   return (this.goOptions);            }   
    public boolean goGame()                     {   return (this.goGame);               }
    public boolean goExit()                     {   return (this.goExit);               }
    public Graphics2D getG2D()					{ 	return (this.gameRef.getG2D());		}

    /**
     * 
     * @param key
     */
    public void keyMovement(int key) {
        
        if (key == 27) { //esc

        } else if (key == 38) { //UP
            
        } else if (key == 40) { //DOWN
            
        } else if (key == 10) { //ENTER
            
        }
    }

    /**
     * Menu screen reset
     */
    public void reset() {
        this.goOptions  = false;
        this.goGame     = false;
        this.goExit     = false;
    }

    public byte getLevel() {return (this.level);}
}