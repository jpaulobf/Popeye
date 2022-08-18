package game.stages;

import game.GameStage;
import java.awt.image.VolatileImage;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.GraphicsEnvironment;

/**
 * Class representing Stage 1 graphics & logic
 */
public class Stage1 {

    //---------------------------------------------------------//
    //---                   Properties                      ---//
    //---------------------------------------------------------//
    private GameStage gameStageRef      = null;
    private VolatileImage bgBufferImage = null;
    private Graphics2D g2dBg            = null;
    private int windowWidth             = 0;
    private int windowHeight            = 0;

    /**
     * Constructor
     * @param gameStage
     */
    public Stage1(GameStage gameStage, int windowWidth, int windowHeight) {
        this.gameStageRef   = gameStage;
        this.windowHeight   = windowHeight;
        this.windowWidth    = windowWidth;
        this.drawBG();
    }

    /**
     * Stage update
     * @param frametime
     */
    public void update(long frametime) {

    }

    public void draw(long frametime) {
        //clear the stage
        this.gameStageRef.getG2D().setBackground(Color.BLACK);
        this.gameStageRef.getG2D().clearRect(0, 0, this.windowWidth, this.windowHeight);

        //Draw the static bg
        this.gameStageRef.getG2D().drawImage(this.bgBufferImage, 0, 0, null);

        //...
    }

    /**
     * Draw the static BG in the bgBuffer
     */
    private void drawBG() {
        if (this.g2dBg == null) {

            //create a backbuffer image for doublebuffer
            this.bgBufferImage  = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().createCompatibleVolatileImage(this.windowWidth, this.windowHeight);
            this.g2dBg          = (Graphics2D)bgBufferImage.getGraphics();

            //paint all bg in black
            this.g2dBg.setBackground(Color.BLACK);
            this.g2dBg.clearRect(0, 0, this.windowWidth, this.windowHeight);
            
            //todo....
            //...

           
        }
    }
}