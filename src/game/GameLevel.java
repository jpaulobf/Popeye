package game;

public class GameLevel {

    //---------------------------------------------------------//
    //--- Properties                                        ---//
    //---------------------------------------------------------//
    public static final byte FIRST_LEVEL    = 0;
    public static final byte SECOND_LEVEL   = 1;
    public static final byte THIRD_LEVEL    = 2;
    private volatile byte currentLevel      = FIRST_LEVEL;

    /**
     * Constructor
     */
    public GameLevel() {}

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