package game;

import java.awt.Graphics2D;
import interfaces.GameInterface;
import game.player.Player;
import game.stages.Stage1;
import game.stages.StageInterface;

/**
 * Class representing the game board
 */
public class Game {

	//Game variables
	private volatile boolean stopped		= false;

	//Gameplay variables
	private long framecounter				= 0;
	private GameController gameRef 			= null;
	private GameLevel gameLevelRef			= null;
	private StageInterface currentStage		= null;
	private Player player					= null;
	private int windowWidth             	= 0;
    private int windowHeight            	= 0;

	/**
	 * Construtor
	 */
	public Game(GameInterface game, int windowWidth, int windowHeight) {
		//parent object
		this.gameRef			= (GameController)game;
		this.gameLevelRef		= this.gameRef.getGameLevel();
		this.windowHeight   	= windowHeight;
        this.windowWidth    	= windowWidth;
		this.player				= new Player(this, 579, 304);
		this.currentStage		= new Stage1(this, this.windowWidth, this.windowHeight);
	}

	/**
	 * Update method
	 */
	public void update(long frametime) {
		if (!this.stopped) {
			//add framecounter
			this.framecounter += frametime;

			if (this.framecounter == frametime) {
				this.player.firstUpdate(frametime);
				this.currentStage.firstUpdate(frametime);
			} else {
				this.player.update(frametime);
				this.currentStage.update(frametime);
			}
		}
	}

	/**
	 * Draw method
	 * @param frametime
	 */
	public void draw(long frametime) {
		this.currentStage.draw(frametime);
		this.player.draw(frametime);
	}

	//----------------------------------------------------//
    //------------------- Movements ----------------------//
    //----------------------------------------------------//
	/**
	 * Move the game
	 * @param keyCode
	 */
	public synchronized void move(int keyCode) {
		if (!this.stopped) {
			this.player.move(keyCode);
		}
	}

	/**
	 * Release Key
	 * @param keyCode
	 */
	public synchronized void release(int keyCode) {
		this.player.release(keyCode);
	}

	//----------------------------------------------------//
    //--------------- Pause & Reset ----------------------//
    //----------------------------------------------------//
	/**
	 * Reset game method
	 */
	public synchronized void resetGame() {
		if (this.gameLevelRef.getCurrentLevel() == GameLevel.FIRST_LEVEL) {
			this.currentStage.reset();
		}
	}

	/**
	 * Toogle game pause
	 */
	public void tooglePause() {
		this.stopped = !this.stopped;
	}

    //----------------------------------------------------//
    //------------------- Accessors ----------------------//
    //----------------------------------------------------//
	public Graphics2D getG2D()					{ 	return (this.gameRef.getG2D());			}
	public GameController getGameRef() 					{	return (this.gameRef);					}
}