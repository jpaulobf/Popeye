package engine;

import interfaces.GameInterface;
import game.GameController;

/**
 * Game factory
 */
public class GameFactory {
    public static GameInterface getGameInstance() {
        return (new GameController());
    }
}
