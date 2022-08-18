package game;

import interfaces.GameInterface;

public class GameLevel {

    //---------------------------------------------------------//
    //--- Properties                                        ---//
    //---------------------------------------------------------//
    private GameInterface gameRef           = null;
    public static final byte FIRST_LEVEL    = 0;
    public static final byte SECOND_LEVEL   = 1;
    public static final byte THIRD_LEVEL    = 2;
    private volatile byte currentLevel      = FIRST_LEVEL;

    /**
     * Constructor
     * @param game
     */
    public GameLevel(GameInterface game) {
        this.gameRef = game;
    }

    /**
     * Set the game to the next level
     */
    public void nextLevel() {
        this.currentLevel = (byte)(++this.currentLevel%3);
    }

    /**
     * Get the current game level
     * @return
     */
    public byte getCurrentLevel() {
        return (this.currentLevel);
    }
}
