package interfaces;

import java.awt.Graphics2D;
import java.awt.image.VolatileImage;

/**
 * All games need to implement the GameInterface interface
 */
public interface GameInterface {

    public static final int R = 82;
    public static final int P = 80;
    public static final int M = 77;
    public static final int N1 = 49;
    public static final int N2 = 50;

    /**
     * Game update
     * @param frametime
     */
    public void update(long frametime);

    /**
     * Game draw
     * @param frametime
     */
    public void draw(long frametime);

    /**
     * Draw the game in fullscreen
     * @param frametime
     * @param fullScreenXPos
     * @param fullScreenYPos
     * @param fullScreenWidth
     * @param fullScreenHeight
     */
    public void drawFullscreen(long frametime, int fullScreenXPos, int fullScreenYPos, int fullScreenWidth, int fullScreenHeight);

    /**
     * Recover the G2D from the buffer
     * @return
     */
    public Graphics2D getG2D();

    /**
     * Recover the bufferedImage
     * @return
     */
    public VolatileImage getBufferedImage();

    /**
     * Update Graphics for FullScreen
     * @param g2d
     */
    public void updateGraphics2D(Graphics2D g2d);

    /**
     * Get internal resolution - W
     * @return internal width resolution
     */
    public int getInternalResolutionWidth();

    /**
     * Get internal resolution - H
     * @return internal height resolution
     */
    public int getInternalResolutionHeight();

    /**
     * Pause the game
     */
    public void tooglePause();

    /**
     * Soft reset
     */
    public void softReset();

    /**
     * Exit the game
     */
    public void exitGame();

    /**
     * Key pressed
     * @param keyCode
     */
    public void keyPressed(int keyCode);

    /**
     * Key released
     * @param keyCode
     */
    public void keyReleased(int keyCode);

    /**
     * Update the 
     * @param state
     */
    public void changeGameState(int state);

    /**
     * Return to main menu
     */
    public void toMainMenu();

    /**
     * Terminate the current game
     */
    public void gameTerminate();

    /**
     * Set the game to gameover
     */
    public void gameOver();

    public void changeGameStateToIngame();
}