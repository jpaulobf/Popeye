package engine;
/**
 * Game interface (update & draw methods)
 */
public interface CanvasEngine {
    /**
     * Update the game logic / receives the frametime
     * @param frametime
     */
    public void update(long frametime);

    /**
     * Draw the game / receives the frametime
     * @param frametime
     */
    public void draw(long frametime);

    /**
     * Set the game resolution
     * @param width
     * @param height
     */    
    public void toogleFullscreen();

    /**
     * Toggle the fullscreen mode
     */
    public void toogleFullScreenProportion();

}