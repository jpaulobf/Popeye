package game;

import java.awt.Color;
import java.awt.Graphics2D;
import interfaces.GameInterface;
import java.awt.image.VolatileImage;
import game.effects.ScreenTransition;
import game.hud.ExitScreen;
import game.menu.MenuScreen;
import game.menu.OptionsScreen;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;

/**
 * Class responsable for put the game elements together
 */
public class GameController implements GameInterface {

    //the game statemachine goes here
    private StateMachine gameState          = null;

    //some support and the graphical device itself
    private Graphics2D g2d                  = null;
    
    //game logic
    private volatile long framecounter      = 0;
    private volatile boolean stopped        = false;
    private volatile boolean changingStage  = false;
    private volatile boolean skipDraw       = false;

    //width and height of window for base metrics of the game
    //don't change this values unless you are changing the 
    //image elements resolution 
    private final int wwm                   = 1920;
    private final int whm                   = 1080;

    //graphic device elements
    private VolatileImage bufferImage       = null;
    private GraphicsEnvironment ge          = null;
    private GraphicsDevice dsd              = null;
    private Graphics2D g2dFS                = null;

    //game components
    private GameMusicSFX gameMusicSFX       = null;
    private MenuScreen menuScreen           = null;
    private OptionsScreen options           = null;
    private GameLevel gameLevel             = null;
    private Game mainGameFrame              = null;
    //no score for now
    //private Score score                  = null;
    private ScreenTransition screenT        = null;
    private ExitScreen exitScreen           = null;
    private GameOver gameOver               = null;

    /**
     * Game constructor
     */
    public GameController() {
        //create the double-buffering image
        this.ge                 = GraphicsEnvironment.getLocalGraphicsEnvironment();
        this.dsd                = ge.getDefaultScreenDevice();
        this.bufferImage        = dsd.getDefaultConfiguration().createCompatibleVolatileImage(this.wwm, this.whm);
        this.g2d                = (Graphics2D)bufferImage.getGraphics();
        this.g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

        //////////////////////////////////////////////////////////////////////
        // ->>>  create the game elements objects
        //////////////////////////////////////////////////////////////////////
        this.gameState          = new StateMachine(this);
        this.menuScreen         = new MenuScreen(this);
        this.gameLevel          = new GameLevel();
        this.mainGameFrame      = new Game(this, this.wwm, this.whm);
        this.options            = new OptionsScreen(this);
        this.gameMusicSFX       = new GameMusicSFX(this);
        // this.score           = new Score(this, new Point(9, 45), new Point(1173, 45), new Point(75, 412), new Point(58, 618));
        this.screenT            = new ScreenTransition(this);
        this.exitScreen         = new ExitScreen(this, this.wwm, this.whm);
        this.gameOver           = new GameOver(this, this.wwm, this.whm);
    }
    
    /**
     * Update the game logic / receives the frametime
     * @param frametime
     */
    @Override
    public synchronized void update(long frametime) {
        
        //if changing stage or stopped do nothing.
        if (!this.changingStage && !this.stopped) {
            
            //update based on game state
            if (this.gameState.getCurrentState() == StateMachine.MENU) {

                //sum framecounter
                this.framecounter += frametime;

                //update just one time
                if (this.framecounter == frametime) { 
                    //if necessary
                    this.menuScreen.firstUpdate(frametime);
                } else {
                    //update menu
                    this.menuScreen.update(frametime);

                    //then, check the selections
                    if (this.menuScreen.goOptions()) {
                        
                        //reset the menu selection parameters
                        this.menuScreen.reset();

                        //define the current state as option
                        this.gameState.setCurrentState(StateMachine.OPTIONS);

                    } else if (this.menuScreen.goGame()) {

                        //stop the game music
                        this.menuScreen.stopMusic();
                        
                        this.mainGameFrame = new Game(this, this.wwm, this.whm);

                        //reset the menu selection parameters
                        this.menuScreen.reset();

                        //go to staging status
                        this.gameState.setCurrentState(StateMachine.STAGING);
                    } else if (this.menuScreen.goExit()) {
                        //Exit the game
                        System.exit(0);
                    }
                }
            } else if (this.gameState.getCurrentState() == StateMachine.OPTIONS) {
                //sum framecounter
                this.framecounter += frametime;

                //update just one time
                if (this.framecounter == frametime) { 
                    //if necessary
                    this.options.firstUpdate(frametime);
                
                } else {
                    //update the options screen
                    this.options.update(frametime);

                    if (this.options.goMenu()) {
                        //reset the options options
                        this.options.reset();
                        
                        //revert the state to menu
                        this.gameState.setCurrentState(StateMachine.MENU);

                        //skip next draw
                        this.skipDraw = true;
                    }
                }
            } else if (this.gameState.getCurrentState() == StateMachine.STAGING) {
                //sum framecounter
                this.framecounter += frametime;
                
                //update just one time
                if (this.framecounter == frametime) { 
                    //if necessary
                } else {
                    //do whatever is necessary to start the game...
                    this.framecounter = 0;
                    this.skipDraw = true;
                    this.gameState.setCurrentState(StateMachine.IN_GAME);
                }
            } else if (this.gameState.getCurrentState() == StateMachine.IN_GAME) {
                
                //sum framecounter
                this.framecounter += frametime;
                
                this.screenT.update(frametime);

                //update just once
                if (this.framecounter == frametime) {
                    this.gameMusicSFX.playTheme();
                    this.skipDraw = true;
                } else {
                    this.mainGameFrame.update(frametime);
                }
               
            } else if (this.gameState.getCurrentState() == StateMachine.EXITING) {
                
                this.framecounter += frametime;
                
                if (this.framecounter == frametime) {
                    this.exitScreen.firstUpdate(frametime);
                }
                
                this.exitScreen.update(frametime);
                
            } else if (this.gameState.getCurrentState() == StateMachine.GAME_OVER) {
                //sum framecounter
                this.framecounter += frametime;

                /*
                //update just once
                if (this.framecounter == frametime) { //run just once
                    this.gameOver.firstUpdate(frametime);
                    this.theme.stop();
                } else if (this.framecounter >= 4_000_000_000L) {
                    this.framecounter = 0;
                    this.softReset();
                    this.gameState.setCurrentState(StateMachine.IN_GAME);
                } else {
                    this.gameOver.update(frametime);
                }
                */
            }
        }
    }

    /**
     * Draw the game elements
     * @param frametime
     */
    @Override
    public synchronized void draw(long frametime) {

        if (!this.skipDraw) {
            //this graphical device (g2d) points to backbuffer, so, we are making things behide the scenes
            //clear the stage
            this.g2d.setBackground(Color.BLACK);
            this.g2d.clearRect(0, 0, this.wwm, this.whm);

            if (!this.changingStage) {
                //////////////////////////////////////////////////////////////////////
                // ->>>  draw the game elements
                //////////////////////////////////////////////////////////////////////
                if (this.gameState.getCurrentState() == StateMachine.MENU) { 
                    this.menuScreen.draw(frametime);
                } else if (this.gameState.getCurrentState() == StateMachine.OPTIONS) {
                    this.options.draw(frametime);
                } else if (this.gameState.getCurrentState() == StateMachine.IN_GAME ||
                           this.gameState.getCurrentState() == StateMachine.EXITING ||
                           this.gameState.getCurrentState() == StateMachine.GAME_OVER) {
                    
                    //render game elements
                    this.mainGameFrame.draw(frametime);
                    // this.score.draw(frametime);
                    this.screenT.draw(frametime);

                    if (this.gameState.getCurrentState() == StateMachine.EXITING) {
                        this.exitScreen.draw(frametime);
                    }

                    if (this.gameState.getCurrentState() == StateMachine.GAME_OVER) {
                        this.gameOver.draw(frametime);
                    }
                }
            }
        }
        this.skipDraw = false;
    }

    /**
     * Draw the game in full screen
     */
    @Override
    public void drawFullscreen(long frametime, int fullScreenXPos, int fullScreenYPos, int fullScreenWidth, int fullScreenHeight) {
        this.g2dFS.drawImage(this.bufferImage, fullScreenXPos, fullScreenYPos, fullScreenWidth, fullScreenHeight, 
                                               0, 0, this.wwm, this.whm, null);
    }

    //----------------------------------------------------//
    //--------------- Game Control        ----------------//
    //----------------------------------------------------//

    /**
     * Toogle the pause button
     */
    @Override
    public synchronized void tooglePause() {
        this.gameMusicSFX.toogleMuteTheme();
        this.mainGameFrame.tooglePause();
    }

    /**
     * Game reset
     */
    @Override
    public void softReset() {
        this.gameMusicSFX.resetTheme();
        this.mainGameFrame.resetGame();
        this.screenT.reset();
        // this.score.reset();
        this.gameOver.reset();
    }

    /**
     * Toogle changing stage controller
     */
    public synchronized void toogleChangingStage() {
        this.changingStage = !this.changingStage;
    }

    /**
     * Exit the game (after confirmation)
     */
    public void exitGame() {
        System.exit(0);
    }

    /**
     * Advance to the next game level.
     */
    public void nextLevel() {
        this.gameLevel.nextLevel();
        this.screenT.reset();
    }
    
    @Override
    public synchronized void gameTerminate() {
        // this.score.reset();
        this.gameMusicSFX.stopTheme();
        this.framecounter   = 0;
        this.skipDraw       = true;
    }

    @Override
    public synchronized void gameOver() {
        this.framecounter = 0;
        this.changeGameState(StateMachine.GAME_OVER);
        this.skipDraw = true;
    }

    @Override
    public synchronized void toMainMenu() {
        this.changeGameState(StateMachine.MENU);
    }

    @Override
    public void changeGameStateToIngame() {
        this.changeGameState(StateMachine.IN_GAME);
    }

    /**
     * Change the game state
     */
    @Override
    public synchronized void changeGameState(int state) {
        this.gameState.setCurrentState(state);
    }

    //----------------------------------------------------//
    //--------------- Keyboard & Joystick ----------------//
    //----------------------------------------------------//
    /**
     * Control the game main character movement
     * @param keyDirection
     */
    private synchronized void movement(int keyDirection) {
        if (this.gameState.getCurrentState() == StateMachine.MENU) {
            this.menuScreen.keyMovement(keyDirection);
        } else if (this.gameState.getCurrentState() == StateMachine.IN_GAME) {
            this.mainGameFrame.move(keyDirection);
        } else if (this.gameState.getCurrentState() == StateMachine.OPTIONS) {
            this.options.keyMovement(keyDirection);
        } else if (this.gameState.getCurrentState() == StateMachine.EXITING) {
            this.exitScreen.move(keyDirection);
        }
    }

    /**
     * Game keypress
     */
    @Override
    public void keyPressed(int keyCode) {
        if (!this.changingStage && !this.stopped) {
            this.movement(keyCode);
        }

        if (this.gameState.getCurrentState() == StateMachine.IN_GAME) {
            //when ESC is pressed
            if (keyCode == 27) {
                this.gameState.setCurrentState(StateMachine.EXITING);
                this.framecounter = 0;
            }
        }
    }

    /**
     * Game keyRelease
     */
    public void keyReleased(int keyCode) {
        if (!this.changingStage && !this.stopped) {
            if (this.gameState.getCurrentState() == StateMachine.IN_GAME) {
                if (keyCode == M)   {this.gameMusicSFX.toogleMuteTheme();   }
                if (keyCode == P)   {this.tooglePause();                    }
                if (keyCode == R)   {this.softReset();                      }
                this.mainGameFrame.release(keyCode);
            }
        }
    }

    //----------------------------------------------------//
    //------------------- Accessors ----------------------//
    //----------------------------------------------------//
    public GameOver getGameOver()                   {   return this.gameOver;       }
    public StateMachine getGameState()              {   return this.gameState;      }
    public int getInternalResolutionWidth()         {   return (this.wwm);          }
    public int getInternalResolutionHeight()        {   return (this.whm);          }
    public VolatileImage getBufferedImage()         {   return (this.bufferImage);  }
    public Graphics2D getG2D()                      {   return (this.g2d);          }
    // public Score getScore()                         {   return (this.score);        }
    public void updateGraphics2D(Graphics2D g2d)    {   this.g2dFS = g2d;           }
    public GameLevel getGameLevel()                 {   return this.gameLevel;      }
    public GameMusicSFX getGameMusicSFX()           {   return (this.gameMusicSFX); }


    /**
     * StateMachine class - control the state of the game
     */
    private class StateMachine {
        
        public final static int LOADING     = 0;
        public final static int SPLASH      = 1;
        public final static int DEMO        = 2;
        public final static int MENU        = 3;
        public final static int OPTIONS     = 4;
        public final static int STARTING    = 5;
        public final static int STAGING     = 6;
        public final static int IN_GAME     = 7;
        public final static int CONTINUE    = 8;
        public final static int GAME_OVER   = 9;
        public final static int EXITING     = 10;
        protected int currentState          = STARTING;
        protected GameController referencetToGame     = null;

        /**
         * Constructor
         * @param game
         */
        public StateMachine(GameController game) {
            this.currentState       = MENU;
            this.referencetToGame   = game;
        }

        /**
         * Recovery the current state
         * @return
         */
        public int getCurrentState() {
            return (this.currentState);
        }

        /**
         * Define new current state
         * @param state
         */
        public void setCurrentState(int state) {
            this.currentState = state;
        }
    }
}