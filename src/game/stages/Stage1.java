package game.stages;

import game.GameStage;
import java.awt.image.VolatileImage;
import java.awt.image.BufferedImage;
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

    /**
     * Constructor
     * @param gameStage
     */
    public Stage1(GameStage gameStage, int windowWidth, int windowHeight) {
        this.gameStageRef   = gameStage;
        this.windowHeight   = windowHeight;
        this.windowWidth    = windowWidth;
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
    }

    public void draw(long frametime) {
        //clear the stage
        this.gameStageRef.getG2D().setBackground(Color.BLACK);
        this.gameStageRef.getG2D().clearRect(0, 0, this.windowWidth, this.windowHeight);

        //Draw the static bg
        this.gameStageRef.getG2D().drawImage(this.bgBufferImage, 0, 0, null);

        //draw the spinash
        this.gameStageRef.getG2D().drawImage(this.bgSpinashImage, this.spinashX, this.spinashY, null);
    }

    /**
     * Draw the spinash can
     */
    private void drawSpinashCan() {
        //create a backbuffer image for doublebuffer
        this.bgSpinashImage  = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().createCompatibleImage(38, 39);
        this.g2dSp          = (Graphics2D)bgSpinashImage.getGraphics();

        this.g2dSp.setColor(new Color(3, 12, 255));
        this.g2dSp.fillRect(9, 12, 26, 27);

        this.g2dSp.setColor(new Color(0, 51, 150));
        this.g2dSp.fillRect(33, 13, 1, 26);
        this.g2dSp.fillRect(8, 12, 27, 1);
        this.g2dSp.fillRect(10, 13, 1, 26);

        this.g2dSp.setColor(new Color(0, 26, 187));
        this.g2dSp.fillRect(10, 13, 25, 1);

        this.g2dSp.setColor(new Color(181, 184, 249));
        this.g2dSp.fillRect(9, 36, 26, 3);

        this.g2dSp.setColor(new Color(0, 161, 55));
        this.g2dSp.fillRect(9, 31, 26, 3);
        
        this.g2dSp.setColor(new Color(0, 0, 59));
        this.g2dSp.fillRect(34, 13, 1, 26);
        this.g2dSp.fillRect(9, 13, 1, 26);

        this.g2dSp.setColor(new Color(0, 166, 16));
        this.g2dSp.fillRect(13, 20, 4, 2);
        this.g2dSp.fillRect(13, 21, 2, 3);
        this.g2dSp.fillRect(15, 24, 2, 4);
        this.g2dSp.fillRect(13, 26, 3, 2);
        this.g2dSp.fillRect(20, 20, 2, 8);
        this.g2dSp.fillRect(22, 24, 2, 2);
        this.g2dSp.fillRect(24, 20, 2, 4);
        this.g2dSp.fillRect(22, 20, 2, 2);
        this.g2dSp.fillRect(29, 20, 3, 8);

        this.g2dSp.setColor(new Color(0, 51, 150));
        this.g2dSp.fillRect(13, 19, 3, 1);
        this.g2dSp.fillRect(12, 19, 1, 4);
        this.g2dSp.fillRect(20, 19, 5, 1);
        this.g2dSp.fillRect(19, 19, 1, 9);
        this.g2dSp.fillRect(28, 19, 3, 1);
        this.g2dSp.fillRect(28, 20, 1, 8);

        this.g2dSp.setColor(new Color(0, 93, 63));
        this.g2dSp.fillRect(33, 31, 1, 3);

        this.g2dSp.setColor(new Color(181, 184, 249));
        this.g2dSp.fillRect(12, 31, 3, 3);
        this.g2dSp.fillRect(12, 12, 3, 5);

        this.g2dSp.setColor(new Color(0, 93, 63));
        this.g2dSp.fillRect(8, 4, 25, 8);
        this.g2dSp.fillRect(5, 13, 4, 3);
        this.g2dSp.fillRect(8, 16, 1, 3);
        this.g2dSp.fillRect(3, 10, 2, 2);
        this.g2dSp.fillRect(12, 2, 2, 2);
        this.g2dSp.setColor(new Color(0, 185, 0));
        this.g2dSp.fillRect(3, 16, 5, 4);
        this.g2dSp.fillRect(2, 16, 1, 3);
        this.g2dSp.fillRect(0, 12, 5, 5);
        this.g2dSp.fillRect(5, 10, 3, 3);
        this.g2dSp.fillRect(2, 7, 6, 3);
        this.g2dSp.fillRect(2, 5, 1, 2);
        this.g2dSp.fillRect(3, 4, 6, 3);
        this.g2dSp.fillRect(5, 2, 3, 2);
        this.g2dSp.fillRect(8, 0, 4, 4);
        this.g2dSp.fillRect(14, 0, 6, 4);
        this.g2dSp.fillRect(20, 2, 2, 2);
        this.g2dSp.fillRect(17, 4, 3, 3);
        this.g2dSp.fillRect(11, 7, 9, 4);
        this.g2dSp.fillRect(23, 7, 3, 3);
        this.g2dSp.fillRect(29, 7, 3, 3);

        this.g2dSp.setColor(new Color(181, 184, 249));
        this.g2dSp.fillRect(22, 0, 12, 4);
        this.g2dSp.fillRect(30, 3, 6, 3);
        this.g2dSp.fillRect(33, 6, 5, 3);
        this.g2dSp.fillRect(33, 9, 3, 4);
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
            this.drawLeftHouse(404, 44);

            //draw right house
            this.drawRightHouse(1271, 53);

            //upper path of the level
            this.g2dBg.setColor(new Color(57, 112, 0));
            this.g2dBg.fillRect(386, 303, 1155, 11);
            this.g2dBg.fillRect(1308, 314, 214, 10);
            this.g2dBg.fillRect(655, 294, 20, 9);
            this.g2dBg.fillRect(674, 285, 38, 18);
            this.g2dBg.fillRect(712, 294, 19, 9);
            this.g2dBg.fillRect(386, 314, 269, 10);
            this.g2dBg.fillRect(500, 324, 78, 9);
            this.g2dBg.fillRect(1233, 285, 38, 19);
            this.g2dBg.fillRect(1214, 294, 19, 9);

            //central paths of the level
            this.g2dBg.setColor(new Color(99, 100, 122));
            this.g2dBg.fillRect(344, 448, 311, 20);
            this.g2dBg.fillRect(1271, 448, 306, 20);
            this.g2dBg.fillRect(366, 641, 1195, 20);
            this.g2dBg.fillRect(366, 833, 1195, 20);
            this.g2dBg.fillRect(366, 833, 1195, 20);

            //draw the grass
            this.drawGrass(343, 274);
            this.drawGrass(1229, 274);
            this.drawGrass(1460, 274);
            this.g2dBg.setColor(Color.BLACK);
            this.g2dBg.fillRect(343, 284, 41, 25);

            //bottom path
            this.g2dBg.setColor(new Color(99, 100, 122));
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
           this.drawRightHouseBase(1213, 314);

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
     * Draw the right house
     * @param startX
     * @param startY
     */
    private void drawRightHouse(int startX, int startY) {
        this.g2dBg.setColor(new Color(191, 153, 118));
        this.g2dBg.fillRect(startX + 39, startY + 97, 192, 153);
        
        this.g2dBg.setColor(new Color(0, 4, 87));
        this.g2dBg.fillRect(startX + 57, startY + 125, 155, 107);

        this.g2dBg.setColor(new Color(97, 99, 85));
        this.g2dBg.fillRect(startX + 39, startY + 97, 192, 9);

        this.g2dBg.setColor(new Color(195, 94, 0));
        this.g2dBg.fillRect(startX + 0, startY + 29, 270, 68);
        this.g2dBg.fillRect(startX + 19, startY + 19, 232, 10);
        this.g2dBg.fillRect(startX + 39, startY + 10, 192, 9);
        this.g2dBg.fillRect(startX + 58, startY + 0, 154, 10);

        this.g2dBg.setColor(new Color(129, 11, 0));
        this.g2dBg.fillRect(startX + 96, startY + 10, 39, 9);
        this.g2dBg.fillRect(startX + 231, startY + 29, 20, 10);
        this.g2dBg.fillRect(startX + 250, startY + 77, 20, 10);
        this.g2dBg.fillRect(startX + 154, startY + 86, 39, 11);
        this.g2dBg.fillRect(startX + 57, startY + 86, 59, 11);
        this.g2dBg.fillRect(startX + 0, startY + 86, 19, 11);
        this.g2dBg.fillRect(startX + 19, startY + 58, 19, 9);
        this.g2dBg.fillRect(startX + 0, startY + 38, 19, 10);

        this.g2dBg.setColor(new Color(0, 4, 87));
        this.g2dBg.fillRect(startX + 58, startY + 28, 154, 40);

        this.g2dBg.setColor(new Color(229, 211, 0));
        this.g2dBg.fillRect(startX + 84, startY + 28, 15, 6);
        this.g2dBg.fillRect(startX + 79, startY + 34, 11, 5);
        this.g2dBg.fillRect(startX + 94, startY + 33, 10, 6);
        this.g2dBg.fillRect(startX + 79, startY + 39, 6, 19);
        this.g2dBg.fillRect(startX + 89, startY + 38, 11, 6);
        this.g2dBg.fillRect(startX + 94, startY + 41, 6, 17);
        this.g2dBg.fillRect(startX + 88, startY + 53, 6, 10);
        this.g2dBg.fillRect(startX + 84, startY + 53, 6, 10);

        this.g2dBg.fillRect(startX + 113, startY + 28, 6, 6);
        this.g2dBg.fillRect(startX + 108, startY + 34, 6, 15);
        this.g2dBg.fillRect(startX + 118, startY + 34, 6, 15);
        this.g2dBg.fillRect(startX + 113, startY + 48, 6, 6);
        this.g2dBg.fillRect(startX + 108, startY + 53, 6, 6);
        this.g2dBg.fillRect(startX + 118, startY + 53, 6, 6);
        this.g2dBg.fillRect(startX + 123, startY + 57, 10, 6);

        this.g2dBg.fillRect(startX + 132, startY + 38, 6, 6);
        this.g2dBg.fillRect(startX + 132, startY + 48, 6, 10);
        this.g2dBg.fillRect(startX + 136, startY + 52, 6, 6);
        this.g2dBg.fillRect(startX + 142, startY + 47, 6, 6);
        this.g2dBg.fillRect(startX + 147, startY + 43, 6, 20);
        this.g2dBg.fillRect(startX + 151, startY + 53, 6, 6);
        this.g2dBg.fillRect(startX + 157, startY + 43, 6, 11);
        this.g2dBg.fillRect(startX + 162, startY + 48, 6, 6);
        this.g2dBg.fillRect(startX + 166, startY + 43, 6, 15);
        this.g2dBg.fillRect(startX + 170, startY + 38, 11, 6);
        this.g2dBg.fillRect(startX + 172, startY + 48, 9, 6);
        this.g2dBg.fillRect(startX + 171, startY + 57, 15, 6);
        this.g2dBg.fillRect(startX + 180, startY + 43, 6, 6);
    }

    /**
     * Draw left house
     * @param startX
     * @param startY
     */
    private void drawLeftHouse(int startX, int startY) {
        this.g2dBg.setColor(new Color(191, 153, 118));
        this.g2dBg.fillRect(startX + 17, startY + 104, 234, 156);

        this.g2dBg.setColor(Color.BLACK);
        this.g2dBg.fillRect(startX + 96, startY + 146, 73, 73);

        this.g2dBg.setColor(new Color(97, 99, 85));
        this.g2dBg.fillRect(startX + 19, startY + 106, 232, 9);

        this.g2dBg.setColor(new Color(70, 37, 0));
        this.g2dBg.fillRect(startX + 16, startY + 106, 232, 1);
        this.g2dBg.fillRect(startX + 246, startY + 106, 2, 153);
        this.g2dBg.fillRect(startX + 16, startY + 257, 232, 2);
        this.g2dBg.fillRect(startX + 16, startY + 106, 3, 152);
        this.g2dBg.fillRect(startX + 19, startY + 142, 227, 4);
        this.g2dBg.fillRect(startX + 19, startY + 181, 227, 4);
        this.g2dBg.fillRect(startX + 19, startY + 219, 227, 4);
        this.g2dBg.fillRect(startX + 53, startY + 107, 4, 150);
        this.g2dBg.fillRect(startX + 92, startY + 107, 4, 150);
        this.g2dBg.fillRect(startX + 131, startY + 107, 4, 150);
        this.g2dBg.fillRect(startX + 169, startY + 107, 4, 150);
        this.g2dBg.fillRect(startX + 208, startY + 107, 4, 150);

        this.g2dBg.setColor(new Color(135, 63, 0));
        this.g2dBg.fillRect(startX + 0, startY + 0, 96, 18);

        this.g2dBg.setColor(new Color(105, 9, 0));
        this.g2dBg.fillRect(startX + 19, startY + 18, 59, 10);

        this.g2dBg.setColor(new Color(195, 94, 0));
        this.g2dBg.fillRect(startX + 19, startY + 28, 59, 10);
        this.g2dBg.fillRect(startX + 0, startY + 38, 270, 68);

        this.g2dBg.setColor(new Color(129, 11, 0));
        this.g2dBg.fillRect(startX + 0, startY + 38, 19, 20);
        this.g2dBg.fillRect(startX + 19, startY + 57, 59, 10);
        this.g2dBg.fillRect(startX + 80, startY + 43, 35, 5);
        this.g2dBg.fillRect(startX + 78, startY + 38, 2, 5);
        this.g2dBg.fillRect(startX + 250, startY + 47, 20, 10);
        this.g2dBg.fillRect(startX + 231, startY + 67, 20, 10);
        this.g2dBg.fillRect(startX + 19, startY + 86, 40, 10);
        this.g2dBg.fillRect(startX + 251, startY + 86, 19, 10);
        this.g2dBg.fillRect(startX + 77, startY + 96, 20, 10);
        this.g2dBg.fillRect(startX + 135, startY + 96, 38, 10);

        this.g2dBg.setColor(new Color(0, 4, 98));
        this.g2dBg.fillRect(startX + 77, startY + 48, 136, 39);

        this.g2dBg.setColor(new Color(203, 191, 38));
        this.g2dBg.fillRect(startX + 80, startY + 38, 126, 5);
        this.g2dBg.fillRect(startX + 75, startY + 43, 5, 5);
        this.g2dBg.fillRect(startX + 206, startY + 43, 5, 5);


        this.g2dBg.fillRect(startX + 80, startY + 52, 9, 30);
        this.g2dBg.fillRect(startX + 89, startY + 52, 6, 6);
        this.g2dBg.fillRect(startX + 89, startY + 66, 6, 6);
        this.g2dBg.fillRect(startX + 94, startY + 57, 5, 10);

        this.g2dBg.fillRect(startX + 103, startY + 52, 16, 5);
        this.g2dBg.fillRect(startX + 103, startY + 57, 5, 25);
        this.g2dBg.fillRect(startX + 108, startY + 71, 11, 11);
        this.g2dBg.fillRect(startX + 114, startY + 57, 5, 14);

        this.g2dBg.fillRect(startX + 123, startY + 52, 9, 30);
        this.g2dBg.fillRect(startX + 132, startY + 52, 6, 6);
        this.g2dBg.fillRect(startX + 132, startY + 66, 6, 6);
        this.g2dBg.fillRect(startX + 137, startY + 57, 5, 10);

        this.g2dBg.fillRect(startX + 146, startY + 52, 9, 30);
        this.g2dBg.fillRect(startX + 155, startY + 71, 6, 11);
        this.g2dBg.fillRect(startX + 155, startY + 62, 6, 5);
        this.g2dBg.fillRect(startX + 155, startY + 52, 6, 6);
        
        this.g2dBg.fillRect(startX + 165, startY + 52, 10, 15);
        this.g2dBg.fillRect(startX + 170, startY + 67, 11, 15);
        this.g2dBg.fillRect(startX + 179, startY + 52, 6, 15);

        this.g2dBg.fillRect(startX + 189, startY + 52, 9, 30);
        this.g2dBg.fillRect(startX + 198, startY + 71, 6, 11);
        this.g2dBg.fillRect(startX + 198, startY + 62, 6, 5);
        this.g2dBg.fillRect(startX + 198, startY + 52, 6, 6);
    }

    /**
     * Draw the grass
     * @param startX
     * @param startY
     */
    private void drawGrass(int startX, int startY) {
        this.g2dBg.setColor(new Color(0, 196, 85));
        this.g2dBg.fillRect(startX + 58, startY + 0, 6, 6);
        this.g2dBg.fillRect(startX + 54, startY + 5, 10, 6);
        this.g2dBg.fillRect(startX + 20, startY + 10, 6, 6);
        this.g2dBg.fillRect(startX + 35, startY + 10, 6, 6);
        this.g2dBg.fillRect(startX + 49, startY + 10, 10, 6);
        this.g2dBg.fillRect(startX + 68, startY + 10, 6, 6);
        this.g2dBg.fillRect(startX + 15, startY + 15, 6, 6);
        this.g2dBg.fillRect(startX + 35, startY + 15, 6, 6);
        this.g2dBg.fillRect(startX + 45, startY + 15, 14, 6);
        this.g2dBg.fillRect(startX + 64, startY + 16, 14, 4);
        this.g2dBg.fillRect(startX + 10, startY + 20, 16, 6);
        this.g2dBg.fillRect(startX + 30, startY + 20, 48, 6);
        this.g2dBg.fillRect(startX + 5, startY + 24, 73, 6);
        this.g2dBg.fillRect(startX + 0, startY + 29, 6, 6);
        this.g2dBg.fillRect(startX + 10, startY + 29, 6, 6);
        this.g2dBg.fillRect(startX + 24, startY + 29, 6, 6);
        this.g2dBg.fillRect(startX + 39, startY + 29, 6, 6);
        this.g2dBg.fillRect(startX + 54, startY + 29, 6, 6);
        this.g2dBg.fillRect(startX + 69, startY + 29, 9, 6);
    }

    /**
     * Draw the right house bases
     * @param startX
     * @param startY
     */
    private void drawRightHouseBase(int startX, int startY) {
        this.g2dBg.setColor(new Color(65, 34, 1));
        this.g2dBg.fillRect(startX + 0, startY + 0, 95, 10);
        this.g2dBg.fillRect(startX + 309, startY + 0, 19, 10);
        this.g2dBg.fillRect(startX + 95, startY + 10, 214, 9);
        this.g2dBg.fillRect(startX + 95, startY + 10, 214, 9);

        this.g2dBg.setColor(new Color(132, 65, 0));
        this.g2dBg.fillRect(startX + 20, startY + 10, 75, 9);
        this.g2dBg.fillRect(startX + 309, startY + 10, 19, 9);
        this.g2dBg.fillRect(startX + 40, startY + 19, 288, 9);
        this.g2dBg.fillRect(startX + 58, startY + 28, 251, 10);
        this.g2dBg.fillRect(startX + 77, startY + 47, 77, 10);
        this.g2dBg.fillRect(startX + 77, startY + 47, 77, 10);

        this.g2dBg.setColor(new Color(65, 34, 1));
        this.g2dBg.fillRect(startX + 77, startY + 57, 77, 10);
        this.g2dBg.fillRect(startX + 309, startY + 28, 19, 10);
        this.g2dBg.fillRect(startX + 270, startY + 38, 58, 19);

        this.g2dBg.setColor(new Color(132, 65, 0));
        this.g2dBg.fillRect(startX + 77, startY + 38, 212, 9);

        this.g2dBg.setColor(new Color(37, 3, 6));
        this.g2dBg.fillRect(startX + 95, startY + 67, 19, 9);

        this.g2dBg.setColor(new Color(65, 34, 1));
        this.g2dBg.fillRect(startX + 95, startY + 76, 19, 58);
        this.g2dBg.fillRect(startX + 289, startY + 57, 19, 77);

    }

    /**
     * Draw the left house bases
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

    /**
     * Toogle spinash position
     */
    private void toogleSpinash() {
        this.spinashTop = !this.spinashTop;
    }
}