package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.LinkedList;
import interfaces.GameInterface;
import java.awt.image.BufferedImage;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import util.LoadingStuffs;
import java.awt.Rectangle;
import util.Audio;

/**
 * Class representing the game board
 */
public class GameStage {

	//constants

	//images
	
	//theme
    private Graphics2D bg2d             			= null;
	private boolean fillColor						= false;
	private byte defaultTheme						= 0;

	//Game variables
	private volatile boolean stopped 				= false;

	//Gameplay variables
	private long framecounter						= 0;
	protected Game gameRef 							= null;
	protected short renderPositionX 				= 0;
	protected short renderPositionY 				= 0;
	
	/**
	 * Construtor
	 */
	public GameStage(GameInterface game) {

		//parent object
		this.gameRef				= (Game)game;

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

		}
	}

	/**
	 * Draw method
	 * @param frametime
	 */
	public void draw(long frametime) {

		
	}

	

	//----------------------------------------------------//
    //------------------- Movements ----------------------//
    //----------------------------------------------------//
	/**
	 * Move the game
	 * @param keyCode
	 */
	public synchronized void move(int keyCode, boolean releaseAfter) {
		if (!this.stopped) {
			if (keyCode == 39) { //right
			
			} else if (keyCode == 37) { //left
			
			} else if (keyCode == 38) { //up
			
			} else if (keyCode == 40) { //down
			
			} else if (keyCode == 32) { //space

			}
		}
	}

	/**
	 * Move the game
	 * @param keyCode
	 */
	public synchronized void move(int keyCode) {
		this.move(keyCode, false);
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
	public short getRenderPositionX() 			{	return (this.renderPositionX);			}
	public short getRenderPositionY() 			{ 	return (this.renderPositionY);			}
	public Graphics2D getG2D()					{ 	return (this.gameRef.getG2D());			}
	public Game getGameRef() 					{	return (this.gameRef);					}

}