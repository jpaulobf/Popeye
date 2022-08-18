package game;

import java.awt.Graphics2D;
import interfaces.GameInterface;
import game.stages.Stage1;

/**
 * Class representing the game board
 */
public class GameStage {

	//constants

	//images
	
	//Game variables
	private volatile boolean stopped 				= false;

	//Gameplay variables
	private long framecounter						= 0;
	private Game gameRef 							= null;
	private GameLevel gameLevelRef					= null;
	private Stage1 stage1							= null;
	private int windowWidth             			= 0;
    private int windowHeight            			= 0;

	/**
	 * Construtor
	 */
	public GameStage(GameInterface game, int windowWidth, int windowHeight) {

		//parent object
		this.gameRef			= (Game)game;
		this.gameLevelRef		= this.gameRef.getGameLevel();
		this.windowHeight   	= windowHeight;
        this.windowWidth    	= windowWidth;
		this.stage1				= new Stage1(this, this.windowWidth, this.windowHeight);

		//create the main game image structure
		// this.gameBoardBG			= GraphicsEnvironment.getLocalGraphicsEnvironment()
		// 												 .getDefaultScreenDevice().getDefaultConfiguration()
		// 												 .createCompatibleImage(bgwidth, bgheight, Transparency.TRANSLUCENT);
		
		//get the G2D (from backbuffered image)
		// this.bg2d					= (Graphics2D)this.gameBoardBG.getGraphics();


		//just one, draw game background
		// this.drawGameBoardBG();


		//calc the render position
		// this.renderPositionX 		= (short)((this.gameRef.getInternalResolutionWidth() / 2) - (this.boardSquareWidth / 2) - (BOARD_LEFT + 60));
		// this.renderPositionY 		= (short)((this.gameRef.getInternalResolutionHeight() / 2) - (this.boardSquareHeight / 2) - BOARD_TOP);
	}

	/**
	 * Update method
	 */
	public void update(long frametime) {
		if (!this.stopped) {
			
			//add framecounter
			this.framecounter += frametime;

			if (this.framecounter == frametime) {

			} else {
				if (this.gameLevelRef.getCurrentLevel() == GameLevel.FIRST_LEVEL) {
					this.stage1.update(frametime);
				}
			}

		}
	}

	/**
	 * Draw method
	 * @param frametime
	 */
	public void draw(long frametime) {

		if (this.gameLevelRef.getCurrentLevel() == GameLevel.FIRST_LEVEL) {
			this.stage1.draw(frametime);
		}
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
			if (keyCode == 39) { //right
			
			} else if (keyCode == 37) { //left
			
			} else if (keyCode == 38) { //up
			
			} else if (keyCode == 40) { //down
			
			} else if (keyCode == 32) { //space

			}
		}
	}

	//----------------------------------------------------//
    //--------------- Pause & Reset ----------------------//
    //----------------------------------------------------//
	/**
	 * Reset game method
	 */
	public synchronized void resetGame() {
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
	public Game getGameRef() 					{	return (this.gameRef);					}
}