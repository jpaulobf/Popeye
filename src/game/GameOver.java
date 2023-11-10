package game;

import java.awt.Graphics2D;
import util.LoadingStuffs;
import util.Audio;
import java.awt.image.BufferedImage;

/**
 * Class represents the gameover scene
 */
public class GameOver {

    //pointers
    private Graphics2D bgd2                 = null;
    private Game gameRef                    = null;

    //images positions & sizes
    private int windowWidth                 = 0;
    private int windowHeight                = 0;

    //counter & animation parameters
    private volatile long framecounter      = 0;

    //images & sounds
    private BufferedImage gameover          = null;
    private Audio gameoverMusic             = null;
    
    /**
     * Constructor
     * @param g2d
     * @param windowWidth
     * @param windowHeight
     */
    public GameOver(Game game, int windowWidth, int windowHeight) {
        
        //store the screen resolution
        this.windowHeight   = windowHeight;
        this.windowWidth    = windowWidth;
        
        //store the game object & the Graphics2D
        this.gameRef        = game;
        this.bgd2           = this.getG2D();
        this.gameRef        = game;

        //load the gameover image
        this.gameover       = LoadingStuffs.getInstance().getImage("gameover");
        //this.gameoverMusic  = LoadingStuffs.getInstance().getAudio("gameover-m");
    }

    /**
     * Update the gameover scene and its elements
     * @param frametime
     */
    public void update(long frametime) {
        this.framecounter += frametime;
        if(this.framecounter == frametime) {
            this.gameoverMusic.play();
        }
    }

    /**
     * 
     * @param frametime
     */
    public void draw(long frametime) {
        //After construct the bg once, copy it to the graphic device
        this.gameRef.getG2D().drawImage(this.gameover, 0, 0, null);
    }

    /**
     * Reset the GameOver
     */
    public void reset() {
        this.framecounter = 0;
    }

    //getter
    public Graphics2D getG2D()  { 	return (this.gameRef.getG2D());    }
}