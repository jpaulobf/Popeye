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
            
            //upper path of the level
            this.g2dBg.setColor(new Color(57, 112, 0));
            this.g2dBg.fillRect(386, 303, 1155, 11);

            //central paths of the level
            this.g2dBg.setColor(new Color(99, 100, 122));
            this.g2dBg.fillRect(344, 448, 311, 20);
            this.g2dBg.fillRect(1271, 448, 306, 20);
            this.g2dBg.fillRect(366, 641, 1195, 20);
            this.g2dBg.fillRect(366, 833, 1195, 20);
            this.g2dBg.fillRect(366, 833, 1195, 20);

            //bottom path
            this.g2dBg.fillRect(344, 1026, 1232, 55);
            this.g2dBg.setColor(new Color(39, 80, 0));
            this.g2dBg.fillRect(924, 1017, 58, 9);
            this.g2dBg.fillRect(344, 1026, 156, 11);
            this.g2dBg.fillRect(344, 1037, 138, 10);
            this.g2dBg.fillRect(905, 1026, 96, 11);
            this.g2dBg.fillRect(1425, 1026, 151, 11);
            this.g2dBg.fillRect(1445, 1037, 131, 10);

            //waves
            this.g2dBg.setColor(new Color(83, 68, 55));
            this.g2dBg.fillRect(372, 1065, 1204, 5);
            this.g2dBg.fillRect(354, 1070, 1204, 5);
            for (short i = 0; i < 33; i++) this.drawWaterWave(344 + (i * 38), 1055);
            this.g2dBg.setColor(Color.BLACK);
            this.g2dBg.fillRect(1576, 1055, 25, 25);
            
            //center ladder
            this.drawCentralLadder(925, 564);
            
            //left ladder
            this.drawLeftLadder(404, 459);
            this.drawLeftLadder(404, 651);
            this.drawLeftLadder(404, 844);
            this.drawLeftLadder(982, 844);

            //right ladder
            this.drawRightLadder(1386, 459);
            this.drawRightLadder(1386, 651);
            this.drawRightLadder(1386, 844);
            this.drawRightLadder(789, 844);

           //barrels
           this.drawBarrel(384, 582);
           this.drawBarrel(384, 774);

           //draw the left house base
           this.drawLeftHouseBase(386, 314);

           //draw the right house base
           //this.drawRightHouseBase(1213, 314);

           //draw left signal
           this.drawLeftSignal(383, 366);

           //draw left signal
           this.drawRightSignal(1461, 366);

            //thru
            this.drawThru(384, 396);
            this.drawThru(1463,396);

           //flower
           this.drawFlower(684, 239);
        }
    }

    /**
     * 
     * @param startX
     * @param startY
     */
    private void drawLeftHouseBase(int startX, int startY) {
        this.g2dBg.setColor(new Color(132, 65, 0));
        this.g2dBg.fillRect(startX + 0, startY + 19, 308, 9);

        this.g2dBg.setColor(new Color(65, 34, 1));
        this.g2dBg.fillRect(startX + 269, startY + 0, 76, 10);
        this.g2dBg.fillRect(startX + 192, startY + 10, 77, 9);
        this.g2dBg.fillRect(startX + 0, startY + 10, 114, 9);
        this.g2dBg.fillRect(startX + 114, startY + 19, 78, 9);
        this.g2dBg.fillRect(startX + 0, startY + 28, 19, 10);
        this.g2dBg.fillRect(startX + 0, startY + 38, 57, 19);
        this.g2dBg.fillRect(startX + 192, startY + 57, 77, 10);

        this.g2dBg.setColor(new Color(132, 65, 0));
        this.g2dBg.fillRect(startX + 269, startY + 10, 58, 9);
        this.g2dBg.fillRect(startX + 18, startY + 28, 270, 10);
        this.g2dBg.fillRect(startX + 37, startY + 38, 232, 9);
        this.g2dBg.fillRect(startX + 192, startY + 47, 77, 10);
        this.g2dBg.fillRect(startX + 192, startY + 47, 77, 10);

        this.g2dBg.setColor(new Color(65, 34, 1));
        this.g2dBg.fillRect(startX + 230, startY + 76, 19, 58);
        this.g2dBg.fillRect(startX + 18, startY + 57, 19, 77);

        this.g2dBg.setColor(new Color(37, 3, 6));
        this.g2dBg.fillRect(startX + 230, startY + 67, 19, 9);
    }

    /**
     * Draw the left signal
     * @param startX
     * @param startY
     */
    private void drawLeftSignal(int startX, int startY) {
        this.g2dBg.setColor(new Color(252, 20, 0));
        this.g2dBg.fillRect(startX + 38, startY + 9, 10, 78);

        this.g2dBg.setColor(new Color(233, 163, 0));
        this.g2dBg.fillRect(startX + 0, startY + 24, 5, 30);
        this.g2dBg.fillRect(startX + 5, startY + 20, 5, 39);
        this.g2dBg.fillRect(startX + 10, startY + 16, 5, 47);
        this.g2dBg.fillRect(startX + 15, startY + 11, 5, 56);
        this.g2dBg.fillRect(startX + 20, startY + 6, 4, 66);
        this.g2dBg.fillRect(startX + 24, startY + 0, 4, 78);
        this.g2dBg.fillRect(startX + 28, startY + 20, 44, 38);
        this.g2dBg.fillRect(startX + 72, startY + 25, 5, 28);
    }

    /**
     * Draw the right signal
     * @param startX
     * @param startY
     */
    private void drawRightSignal(int startX, int startY) {
        this.g2dBg.setColor(new Color(252, 20, 0));
        this.g2dBg.fillRect(startX + 29, startY + 9, 10, 78);

        this.g2dBg.setColor(new Color(233, 163, 0));
        this.g2dBg.fillRect(startX + 72, startY + 24, 5, 30);
        this.g2dBg.fillRect(startX + 67, startY + 20, 5, 39);
        this.g2dBg.fillRect(startX + 62, startY + 16, 5, 47);
        this.g2dBg.fillRect(startX + 57, startY + 11, 5, 56);
        this.g2dBg.fillRect(startX + 53, startY + 6, 4, 66);
        this.g2dBg.fillRect(startX + 49, startY + 0, 4, 78);
        this.g2dBg.fillRect(startX + 5, startY + 20, 44, 38);
        this.g2dBg.fillRect(startX + 0, startY + 25, 5, 28);
    }

    /**
     * Draw the thru word
     * @param startX
     * @param startY
     */
    private void drawThru(int startX, int startY) {
        this.g2dBg.setColor(new Color(0, 4, 87));

        this.g2dBg.fillRect(startX + 0, startY + 0, 12, 4);
        this.g2dBg.fillRect(startX + 4, startY + 4, 4, 14);
        this.g2dBg.fillRect(startX + 18, startY + 0, 4, 18);
        this.g2dBg.fillRect(startX + 22, startY + 9, 6, 5);
        this.g2dBg.fillRect(startX + 28, startY + 0, 4, 18);
        this.g2dBg.fillRect(startX + 38, startY + 0, 3, 18);
        this.g2dBg.fillRect(startX + 41, startY + 9, 6, 5);
        this.g2dBg.fillRect(startX + 41, startY + 0, 10, 4);
        this.g2dBg.fillRect(startX + 47, startY + 4, 4, 5);
        this.g2dBg.fillRect(startX + 47, startY + 13, 4, 5);
        this.g2dBg.fillRect(startX + 57, startY + 0, 4,18);
        this.g2dBg.fillRect(startX + 61, startY + 14, 5,4);
        this.g2dBg.fillRect(startX + 66, startY + 0, 4,18);
    }

    /**
     * Helper to draw the water wave
     * @param startX
     * @param startY
     */
    private void drawWaterWave(int startX, int startY) {
        this.g2dBg.setColor(new Color(0, 35, 118));
        this.g2dBg.fillRect(startX + 14, startY + 0, 5, 5);
        this.g2dBg.fillRect(startX + 9, startY + 5, 15, 5);
        this.g2dBg.fillRect(startX + 0, startY + 10, 28, 5);
        this.g2dBg.fillRect(startX + 0, startY + 20, 28, 5);
        this.g2dBg.fillRect(startX + 0, startY + 15, 10, 5);
        this.g2dBg.fillRect(startX + 15, startY + 15, 13, 5);
        this.g2dBg.fillRect(startX + 28, startY + 15, 11, 10);
    }

    /**
     * Helper to draw the left ladder
     * @param startX
     * @param startY
     */
    private void drawLeftLadder(int startX, int startY) {
        this.g2dBg.setColor(new Color(65, 35, 0));
        this.g2dBg.fillRect(startX + 0, startY + 0, 19, 182);
        this.g2dBg.fillRect(startX + 18, startY + 29, 20, 153);
        this.g2dBg.fillRect(startX + 38, startY + 68, 19, 114);
        this.g2dBg.fillRect(startX + 57, startY + 106, 19, 76);
        this.g2dBg.fillRect(startX + 76, startY + 145, 20, 37);

        this.g2dBg.setColor(new Color(27, 3, 0));
        this.g2dBg.fillRect(startX + 96, startY + 173, 39, 9);
        this.g2dBg.fillRect(startX + 76, startY + 135, 40, 10);
        this.g2dBg.fillRect(startX + 57, startY + 96, 39, 10);
        this.g2dBg.fillRect(startX + 38, startY + 58, 39, 10);
        this.g2dBg.fillRect(startX + 19, startY + 19, 39, 10);
        this.g2dBg.fillRect(startX, startY + 58, 19, 9);
        this.g2dBg.fillRect(startX + 19, startY + 105, 20, 10);
        this.g2dBg.fillRect(startX, startY + 134, 19, 10);
        this.g2dBg.fillRect(startX + 39, startY + 135, 19, 9);
        this.g2dBg.fillRect(startX, startY + 173, 57, 9);

        this.g2dBg.setColor(new Color(153, 128, 121));
        this.g2dBg.fillRect(startX + 19, startY, 39, 19);
        this.g2dBg.fillRect(startX + 38, startY + 29, 39, 29);
        this.g2dBg.fillRect(startX + 57, startY + 68, 39, 28);
        this.g2dBg.fillRect(startX + 76, startY + 106, 40, 29);
        this.g2dBg.fillRect(startX + 96, startY + 145, 39, 28);
    }

    /**
     * Helper to draw the flower
     * @param startX
     * @param startY
     */
    private void drawFlower(int startX, int startY) {
        //caule
        this.g2dBg.setColor(new Color(0, 190, 0));
        this.g2dBg.fillRect(startX + 12, startY + 30, 1, 3);
        this.g2dBg.fillRect(startX + 13, startY + 31, 1, 2);
        this.g2dBg.fillRect(startX + 14, startY + 31, 1, 1);
        this.g2dBg.fillRect(startX + 9, startY + 34, 3, 4);
        this.g2dBg.fillRect(startX + 11, startY + 38, 3, 5);
        this.g2dBg.fillRect(startX + 3, startY + 36, 2, 1);
        this.g2dBg.fillRect(startX + 2, startY + 37, 3, 6);
        this.g2dBg.fillRect(startX + 5, startY + 38, 1, 7);
        this.g2dBg.fillRect(startX + 6, startY + 39, 1, 6);
        this.g2dBg.fillRect(startX + 7, startY + 41, 2, 4);
        this.g2dBg.fillRect(startX + 9, startY + 43, 7, 2);
        this.g2dBg.fillRect(startX + 24, startY + 38, 3, 3);
        this.g2dBg.fillRect(startX + 21, startY + 40, 3, 3);
        this.g2dBg.fillRect(startX + 19, startY + 43, 4, 3);
        this.g2dBg.fillRect(startX + 15, startY + 45, 4, 1);
        this.g2dBg.fillRect(startX + 16, startY + 46, 5, 2);
        this.g2dBg.fillRect(startX + 18, startY + 48, 4, 2);
        this.g2dBg.fillRect(startX + 19, startY + 50, 4, 4);
        this.g2dBg.fillRect(startX + 18, startY + 54, 4, 4);
        this.g2dBg.fillRect(startX + 17, startY + 56, 1, 6);
        this.g2dBg.fillRect(startX + 18, startY + 58, 1, 6);
        this.g2dBg.fillRect(startX + 7, startY + 45, 7, 1);
        this.g2dBg.fillRect(startX + 11, startY + 32, 2, 3);

        //miolo
        this.g2dBg.setColor(new Color(240, 20, 1));
        this.g2dBg.fillRect(startX + 14, startY + 11, 3, 10);
        this.g2dBg.fillRect(startX + 11, startY + 13, 3, 6);
        this.g2dBg.fillRect(startX + 17, startY + 14, 2, 7);
        this.g2dBg.fillRect(startX + 19, startY + 16, 2, 3);

        //petalas
        this.g2dBg.setColor(new Color(226, 226, 236));
        this.g2dBg.fillRect(startX + 19, startY + 0, 9, 9);
        this.g2dBg.fillRect(startX + 17, startY + 3, 6, 8);
        this.g2dBg.fillRect(startX + 14, startY + 7, 3, 4);
        this.g2dBg.fillRect(startX + 3, startY + 3, 8, 5);
        this.g2dBg.fillRect(startX + 5, startY + 8, 6, 3);
        this.g2dBg.fillRect(startX + 7, startY + 11, 7, 3);
        this.g2dBg.fillRect(startX + 11, startY + 10, 3, 1);
        this.g2dBg.fillRect(startX + 0, startY + 15, 4, 8);
        this.g2dBg.fillRect(startX + 2, startY + 23, 2, 3);
        this.g2dBg.fillRect(startX + 4, startY + 18, 4, 5);
        this.g2dBg.fillRect(startX + 8, startY + 18, 2, 2);
        this.g2dBg.fillRect(startX + 29, startY + 12, 3, 9);
        this.g2dBg.fillRect(startX + 31, startY + 21, 1, 2);
        this.g2dBg.fillRect(startX + 26, startY + 15, 3, 5);
        this.g2dBg.fillRect(startX + 22, startY + 15, 4, 4);
        this.g2dBg.fillRect(startX + 22, startY + 24, 6, 7);
        this.g2dBg.fillRect(startX + 22, startY + 22, 4, 2);
        this.g2dBg.fillRect(startX + 21, startY + 20, 2, 2);
        this.g2dBg.fillRect(startX + 13, startY + 22, 9, 4);
        this.g2dBg.fillRect(startX + 7, startY + 29, 8, 2);
        this.g2dBg.fillRect(startX + 15, startY + 29, 3, 3);
        this.g2dBg.fillRect(startX + 10, startY + 27, 5, 2);
        this.g2dBg.fillRect(startX + 11, startY + 26, 4, 1);
        this.g2dBg.fillRect(startX + 10, startY + 19, 4, 1);
        this.g2dBg.fillRect(startX + 13, startY + 20, 1, 2);
        this.g2dBg.fillRect(startX + 14, startY + 21, 7, 1);
        this.g2dBg.fillRect(startX + 19, startY + 19, 2, 2);
        this.g2dBg.fillRect(startX + 21, startY + 11, 1, 9);
        this.g2dBg.fillRect(startX + 19, startY + 11, 2, 5);
        this.g2dBg.fillRect(startX + 17, startY + 11, 2, 3);
        this.g2dBg.fillRect(startX + 4, startY + 17, 7, 1);
        this.g2dBg.fillRect(startX + 10, startY + 18, 1, 1);
        this.g2dBg.fillRect(startX + 9, startY + 14, 2, 1);
        this.g2dBg.fillRect(startX + 7, startY + 16, 4, 1);
        this.g2dBg.fillRect(startX + 10, startY + 15, 1, 1);
        this.g2dBg.fillRect(startX + 8, startY + 20, 2, 1);
        this.g2dBg.fillRect(startX + 12, startY + 20, 1, 3);
        this.g2dBg.fillRect(startX + 22, startY + 13, 1, 2);
        this.g2dBg.fillRect(startX + 12, startY + 25, 1, 1);
        this.g2dBg.fillRect(startX + 15, startY + 26, 1, 2);
        this.g2dBg.fillRect(startX + 16, startY + 26, 1, 1);
        this.g2dBg.fillRect(startX + 28, startY + 13, 1, 2);
        this.g2dBg.fillRect(startX + 27, startY + 14, 1, 1);
        this.g2dBg.fillRect(startX + 31, startY + 11, 1, 1);
        this.g2dBg.fillRect(startX + 2, startY + 2, 1, 5);
        this.g2dBg.fillRect(startX + 3, startY + 2, 8, 1);
        this.g2dBg.fillRect(startX + 11, startY + 6, 1, 4);
        this.g2dBg.fillRect(startX + 12, startY + 9, 1, 1);
    }

    /**
     * Helper to draw the right ladder
     * @param startX
     * @param startY
     */
    private void drawRightLadder(int startX, int startY) {
        this.g2dBg.setColor(new Color(65, 35, 0));
        this.g2dBg.fillRect(startX + 116, startY + 0, 19, 182);
        this.g2dBg.fillRect(startX + 97, startY + 29, 20, 153);
        this.g2dBg.fillRect(startX + 78, startY + 68, 19, 114);
        this.g2dBg.fillRect(startX + 59, startY + 106, 19, 76);
        this.g2dBg.fillRect(startX + 39, startY + 145, 20, 37);

        this.g2dBg.setColor(new Color(27, 3, 0));
        this.g2dBg.fillRect(startX, startY + 173, 39, 9);
        this.g2dBg.fillRect(startX + 19, startY + 135, 40, 10);
        this.g2dBg.fillRect(startX + 39, startY + 96, 39, 10);
        this.g2dBg.fillRect(startX + 58, startY + 58, 39, 10);
        this.g2dBg.fillRect(startX + 77, startY + 19, 39, 10);
        this.g2dBg.fillRect(startX + 116, startY + 58, 19, 9);
        this.g2dBg.fillRect(startX + 96, startY + 105, 20, 10);
        this.g2dBg.fillRect(startX + 116, startY + 134, 19, 10);
        this.g2dBg.fillRect(startX + 77, startY + 135, 19, 9);
        this.g2dBg.fillRect(startX + 78, startY + 173, 57, 9);
        
        this.g2dBg.setColor(new Color(153, 128, 121));
        this.g2dBg.fillRect(startX + 77, startY, 39, 19);
        this.g2dBg.fillRect(startX + 58, startY + 29, 39, 29);
        this.g2dBg.fillRect(startX + 39, startY + 68, 39, 28);
        this.g2dBg.fillRect(startX + 19, startY + 106, 40, 29);
        this.g2dBg.fillRect(startX, startY + 145, 39, 28);
    }

    /**
     * Helper to draw the central ladder
     * @param startX
     * @param startY
     */
    private void drawCentralLadder(int startX, int startY) {

        this.g2dBg.setColor(new Color(153,128,121));
        this.g2dBg.fillRect(startX + 0, startY + 0, 18, 19);
        this.g2dBg.fillRect(startX + 38, startY + 0, 18, 19);
        this.g2dBg.fillRect(startX + 0, startY + 29, 18, 19);
        this.g2dBg.fillRect(startX + 38, startY + 29, 18, 19);
        this.g2dBg.fillRect(startX + 0, startY + 58, 18, 19);
        this.g2dBg.fillRect(startX + 38, startY + 58, 18, 19);
        this.g2dBg.fillRect(startX + 0, startY + 87, 18, 19);
        this.g2dBg.fillRect(startX + 38, startY + 87, 18, 19);
        this.g2dBg.fillRect(startX + 0, startY + 87, 18, 19);
        this.g2dBg.fillRect(startX + 38, startY + 87, 18, 19);
        this.g2dBg.fillRect(startX + 0, startY + 116, 18, 19);
        this.g2dBg.fillRect(startX + 38, startY + 116, 18, 19);

        this.g2dBg.setColor(new Color(65, 35, 0));
        this.g2dBg.fillRect(startX + 0, startY + 19, 56, 10);
        this.g2dBg.fillRect(startX + 0, startY + 48, 56, 10);
        this.g2dBg.fillRect(startX + 0, startY + 77, 56, 10);
        this.g2dBg.fillRect(startX + 0, startY + 106, 56, 10);

        this.g2dBg.setColor(Color.BLACK);
        this.g2dBg.fillRect(startX + 18, startY + 87, 20, 19);
    }

    /**
     * Helper to draw the barrel
     * @param startX
     * @param startY
     */
    private void drawBarrel(int startX, int startY) {
        //red
        this.g2dBg.setColor(new Color(134, 12, 0));
        this.g2dBg.fillRect(startX, startY, 40, 10);
        this.g2dBg.fillRect(startX, startY + 21, 40, 19);
        this.g2dBg.fillRect(startX, startY + 50, 40, 9);
        //brown
        this.g2dBg.setColor(new Color(186, 154, 118));
        this.g2dBg.fillRect(startX, startY + 10, 40, 11);
        this.g2dBg.fillRect(startX, startY + 40, 40, 10);
    }
}