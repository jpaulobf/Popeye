package game.stages;

import game.GameStage;
import game.SpriteImpl;
import game.stages.sprites.PopeyeSprite;
import java.awt.image.VolatileImage;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.GraphicsEnvironment;

/**
 * Class representing Stage 1 graphics & logic
 */
public class Stage1 implements StageInterface {

    //---------------------------------------------------------//
    //---                   Properties                      ---//
    //---------------------------------------------------------//
    private GameStage gameStageRef          = null;
    private VolatileImage bgBufferImage     = null;
    private BufferedImage bgSpinashImage    = null;
    private Graphics2D g2dBg                = null;
    private Graphics2D g2dSp                = null;
    private int windowWidth                 = 0;
    private int windowHeight                = 0;
    private boolean spinashTop              = true;
    private short spinashX                  = 366;
    private short spinashY                  = 543;
    private long framecounter               = 0;
    private SpriteImpl popeyeSprite         = null;

    /**
     * Constructor
     * @param gameStage
     */
    public Stage1(GameStage gameStage, int windowWidth, int windowHeight) {
        this.gameStageRef   = gameStage;
        this.windowHeight   = windowHeight;
        this.windowWidth    = windowWidth;
        this.popeyeSprite   = new PopeyeSprite(this, 579, 312);

        //create a backbuffer image for doublebuffer
        this.bgSpinashImage = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().createCompatibleImage(38, 39);
        this.g2dSp          = (Graphics2D)bgSpinashImage.getGraphics();

        this.drawBG();
        this.drawSpinashCan();
    }

    /**
     * Stage update
     * @param frametime
     */
    public void update(long frametime) {
        this.framecounter += frametime;
        if (this.framecounter >= 4_000_000_000L) {
            this.framecounter = 0;
            this.toogleSpinash();

            if (this.spinashTop) {
                this.spinashY = 543;
            } else {
                this.spinashY = 735;
            }
        }

        this.popeyeSprite.update(frametime);
    }

    public void draw(long frametime) {
        //clear the stage
        this.gameStageRef.getG2D().setBackground(Color.BLACK);
        this.gameStageRef.getG2D().clearRect(0, 0, this.windowWidth, this.windowHeight);

        //Draw the static bg
        this.gameStageRef.getG2D().drawImage(this.bgBufferImage, 0, 0, null);

        //draw the spinash
        this.gameStageRef.getG2D().drawImage(this.bgSpinashImage, this.spinashX, this.spinashY, null);

        //draw popeye sprite
        this.popeyeSprite.draw(frametime);
    }

    /**
     * Create the Spinash Can
     */
    private void drawSpinashCan() {
        Helper.drawSpinashCan(this.g2dSp);
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
            
            //draw left house
            Helper.drawLeftHouse(this.g2dBg, 404, 44);

            //draw right house
            Helper.drawRightHouse(this.g2dBg, 1271, 53);

            //draw the upper path
            Helper.drawUpperPath(this.g2dBg);

            //draw the center path
            Helper.drawCenterPath(this.g2dBg);

            //draw the grass
            Helper.drawGrass(this.g2dBg, 343, 274);
            Helper.drawGrass(this.g2dBg, 1229, 274);
            Helper.drawGrass(this.g2dBg, 1460, 274);
            this.g2dBg.setColor(Color.BLACK);
            this.g2dBg.fillRect(343, 284, 41, 25);

            //draw bottom path
            Helper.drawBottomPath(this.g2dBg);
            
            //draw the waves
            Helper.drawWaves(this.g2dBg);

            //center ladder
            Helper.drawCentralLadder(this.g2dBg, 925, 564);
            
            //left ladder
            Helper.drawLeftLadder(this.g2dBg, 404, 459);
            Helper.drawLeftLadder(this.g2dBg, 404, 651);
            Helper.drawLeftLadder(this.g2dBg, 404, 844);
            Helper.drawLeftLadder(this.g2dBg, 982, 844);

            //right ladder
            Helper.drawRightLadder(this.g2dBg, 1386, 459);
            Helper.drawRightLadder(this.g2dBg, 1386, 651);
            Helper.drawRightLadder(this.g2dBg, 1386, 844);
            Helper.drawRightLadder(this.g2dBg, 789, 844);

            //barrels
            Helper.drawBarrel(this.g2dBg, 384, 582);
            Helper.drawBarrel(this.g2dBg, 384, 774);

            //draw the left house base
            Helper.drawLeftHouseBase(this.g2dBg, 386, 314);

            //draw the right house base
            Helper.drawRightHouseBase(this.g2dBg, 1213, 314);

            //draw left signal
            Helper.drawLeftSignal(this.g2dBg, 383, 366);

            //draw left signal
            Helper.drawRightSignal(this.g2dBg, 1461, 366);

            //thru
            Helper.drawThru(this.g2dBg, 384, 396);
            Helper.drawThru(this.g2dBg, 1463,396);

            //flower
            Helper.drawFlower(this.g2dBg, 684, 239);
        }
    }

    /**
     * Toogle spinash position
     */
    private void toogleSpinash() {
        this.spinashTop = !this.spinashTop;
    }

    @Override
    public Graphics2D getG2D() {
        return (this.gameStageRef.getG2D());
    }

    @Override
    public void move(int keyCode) {
        if (keyCode == 39) { //right
			this.popeyeSprite.enableRightFlag();
        } else if (keyCode == 37) { //left
            this.popeyeSprite.enableLeftFlag();
        } else if (keyCode == 38) { //up
            this.popeyeSprite.enableTopFlag();
        } else if (keyCode == 40) { //down
            this.popeyeSprite.enableBottomFlag();
        } else if (keyCode == 32) { //space
            System.out.println(this.popeyeSprite.getPositionX());
        }
    }

    public void releaseKey(int keyCode) {
        if (keyCode == 39) { //right
			this.popeyeSprite.disableRightFlag();
        } else if (keyCode == 37) { //left
            this.popeyeSprite.disableLeftFlag();
        }else if (keyCode == 40) { //down
            this.popeyeSprite.disableBottomFlag();
        }
    }

    /**
     * Reset stage
     */
    public void reset() {

    }
}