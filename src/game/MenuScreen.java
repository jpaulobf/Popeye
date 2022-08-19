package game;

import interfaces.GameInterface;
import java.awt.Graphics2D;
import java.awt.Color;
import util.LoadingStuffs;
import java.awt.image.BufferedImage;
import util.Audio;

/**
 * Class representing the menu
 */
public class MenuScreen {
    
    private GameInterface gameRef           = null;
    private Graphics2D g2d                  = null;

    //images
    private BufferedImage plogo             = null;
    private BufferedImage selector          = null;
    private BufferedImage labelPlay         = null;
    private BufferedImage labelOptions      = null;
    private BufferedImage labelExit         = null;
    private BufferedImage frame             = null;

    //sounds
    private Audio intro                     = null;
    private Audio item                      = null;
    private Audio start                     = null;

    //menu control
    private byte selectorPosition           = 0;
    private byte selectors[]                = {0, 1, 2};
    private short optionsSpace              = 90;
    private volatile boolean goOptions      = false;
    private volatile boolean goGame         = false;
    private volatile boolean goExit         = false;
    private final Color menuColor           = new Color(253, 187, 41);
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
        this.plogo              = LoadingStuffs.getInstance().getImage("popeyelogo");
        this.selector           = LoadingStuffs.getInstance().getImage("selector");
        this.frame              = LoadingStuffs.getInstance().getImage("menu-frame");
        this.labelPlay          = LoadingStuffs.getInstance().getImage("lbPlayGame");
        this.labelOptions       = LoadingStuffs.getInstance().getImage("lbOptions");
        this.labelExit          = LoadingStuffs.getInstance().getImage("lbExitGame");

        //load the sounds
        this.intro              = LoadingStuffs.getInstance().getAudio("intro");
        this.item               = LoadingStuffs.getInstance().getAudio("star");
        this.start              = LoadingStuffs.getInstance().getAudio("start");
    }

    /**
     * Game logic update
     * @param frametime
     */
    public synchronized void update(long frametime) {
    
    }

    /**
     * Stop the menu music
     */
    public void stopMusic() {
        this.intro.stop();
    }

    /**
     * Draw method
     * @param frametime
     */    
    public synchronized void draw(long frametime) {
        this.g2d.setBackground(this.menuColor);
        this.g2d.clearRect(0, 0, resolutionW, resolutionH);
        this.g2d.drawImage(this.plogo, 253, 238, null);
        this.g2d.drawImage(this.frame, 1019, 293, null);
        this.g2d.drawImage(this.selector, 1069, 412 + (selectors[selectorPosition] * optionsSpace), null);
        this.g2d.drawImage(this.labelPlay, 1209, 438, null);
        this.g2d.drawImage(this.labelOptions, 1275, 526, null);
        this.g2d.drawImage(this.labelExit, 1256, 613, null);
    }

    /**
     * 
     * @param frametime
     */
    public synchronized void firstUpdate(long frametime) {
        this.intro.playContinuously();
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
            this.goExit = true;
        } else if (key == 38) { //UP
            if (this.selectorPosition > 0) {
                this.selectorPosition--;
            } else {
                this.selectorPosition = 2;
            }
            this.item.play();
        } else if (key == 40) { //DOWN
            if (this.selectorPosition < 2) {
                this.selectorPosition++;
            } else {
                this.selectorPosition = 0;
            }
            this.item.play();
        } else if (key == 10) { //ENTER
            this.goExit     = false;
            this.goOptions  = false;
            this.goGame     = false;
            if (this.selectorPosition == 0) {
                this.goGame = true;
                this.start.play();
            } else if (this.selectorPosition == 1) {
                this.goOptions = true;
            } else if (this.selectorPosition == 2) {
                this.goExit = true;
            }
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
}