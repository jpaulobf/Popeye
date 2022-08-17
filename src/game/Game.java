package game;

import util.Audio;
import util.LoadingStuffs;
import java.awt.Color;
import java.awt.Graphics2D;
import interfaces.GameInterface;
import java.awt.image.VolatileImage;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.util.List;
import java.util.ArrayList;

/**
 * Class responsable for put the game elements together
 */
public class Game implements GameInterface {

    //the game statemachine goes here
    private StateMachine gameState          = null;

    //some support and the graphical device itself
    private Graphics2D g2d                  = null;
    private List<Audio> audioList           = new ArrayList<Audio>();

    private volatile Audio theme            = null;
    private volatile long framecounter      = 0;
    private volatile boolean mute           = false;
    private volatile boolean stopped        = false;
    private volatile boolean changingStage  = false;
    private volatile boolean skipDraw       = false;

    //width and height of window for base metrics of the game
    private final int wwm                   = 1920;
    private final int whm                   = 1080;

    //graphic device elements
    private VolatileImage bufferImage       = null;
    private GraphicsEnvironment ge          = null;
    private GraphicsDevice dsd              = null;
    private Graphics2D g2dFS                = null;

    //game components
    private MenuScreen menuScreen           = null;
    // private OptionsScreen options        = null;
    private GameLevel gameLevel             = null;
    private GameStage gameStage             = null;
    // private Score score                  = null;
    private ScreenTransition screenT        = null;
    private ExitScreen exitScreen           = null;
    private GameOver gameOver               = null;

    /**
     * Game constructor
     */
    public Game() {
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
        this.gameLevel          = new GameLevel(this);
        this.gameStage          = new GameStage(this);
        // this.options         = new OptionsScreen(this);
        // this.score           = new Score(this, new Point(9, 45), new Point(1173, 45), new Point(75, 412), new Point(58, 618));
        this.screenT            = new ScreenTransition(this);
        this.exitScreen         = new ExitScreen(this, this.wwm, this.whm);
        this.gameOver           = new GameOver(this, this.wwm, this.whm);

        //get the audio list
        this.audioList          = LoadingStuffs.getInstance().getAudioList();
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

                this.menuScreen.update(frametime);
                
                /*
                //update just one time
                if (this.framecounter == frametime) { 
                    //if necessary
                    this.menu.firstUpdate(frametime);
                } else {
                    //update menu
                    this.menu.update(frametime);

                    //then, check the selections
                    if (this.menu.goOptions()) {
                        
                        //reset the menu selection parameters
                        this.menu.reset();

                        //define the current state as option
                        this.gameState.setCurrentState(StateMachine.OPTIONS);

                    } else if (this.menu.goGame()) {

                        //stop the game music
                        this.menu.stopMusic();
                        
                        //get the level defined in the menu
                        this.gameLevel  = new GameLevel(this.menu.getLevel());
                        //this.board      = new Board(this);

                        //reset the menu selection parameters
                        this.menu.reset();

                        //go to staging status
                        this.gameState.setCurrentState(StateMachine.STAGING);
                    } else if (this.menu.goExit()) {
                        System.exit(0);
                    }
                }
                */
            } else if (this.gameState.getCurrentState() == StateMachine.OPTIONS) {

                //sum framecounter
                this.framecounter += frametime;

                /*
                //update just one time
                if (this.framecounter == frametime) { 
                    //if necessary
                    //this.options.firstUpdate(frametime);
                
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
                 */

            } else if (this.gameState.getCurrentState() == StateMachine.STAGING) {
                
                //sum framecounter
                this.framecounter += frametime;
                
                //update just one time
                if (this.framecounter == frametime) { 
                    //if necessary
                } else {
                    //
                    //do whatever is necessary to start the game...
                    //
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
                    // this.theme.playContinuously();
                    this.skipDraw = true;
                } else {
                    this.gameStage.update(frametime);
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
                    // this.options.draw(frametime);
                } else if (this.gameState.getCurrentState() == StateMachine.IN_GAME ||
                           this.gameState.getCurrentState() == StateMachine.EXITING ||
                           this.gameState.getCurrentState() == StateMachine.GAME_OVER) {
                    // this.board.draw(frametime);
                    // this.score.draw(frametime);
                    this.gameStage.draw(frametime);
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

    /**
     * Toogle the pause button
     */
    @Override
    public synchronized void tooglePause() {
        this.toogleMuteTheme();
        this.gameStage.tooglePause();
    }

    /**
     * Game reset
     */
    @Override
    public void softReset() {
        this.resetTheme();
        this.gameStage.resetGame();
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
            this.gameStage.move(keyDirection);
        } else if (this.gameState.getCurrentState() == StateMachine.OPTIONS) {
            // this.options.keyMovement(keyDirection);
        }
    }

    /**
     * Game keypress
     */
    @Override
    public void keyPressed(int keyCode) {
        if (!this.changingStage && !this.stopped) {

            if (this.gameState.getCurrentState() == StateMachine.IN_GAME) {
                
                if (keyCode == 27) { //esc
                    this.gameState.setCurrentState(StateMachine.EXITING);
                    this.framecounter = 0;
                } else {
                    this.movement(keyCode);
                }

            } else if (this.gameState.getCurrentState() == StateMachine.EXITING) {
                this.exitScreen.move(keyCode);
            }
        }
    }

    /**
     * Exit the game (after confirmation)
     */
    public void exitGame() {
        System.exit(0);
    }

    /**
     * Game keyRelease
     */
    public void keyReleased(int keyCode) {
        if (!this.changingStage && !this.stopped) {
            if (this.gameState.getCurrentState() == StateMachine.IN_GAME) {
                if (keyCode == M)   {this.toogleMuteTheme();    }
                if (keyCode == P)   {this.tooglePause();        }
                if (keyCode == R)   {this.softReset();          }
            }
        }
    }

    //----------------------------------------------------//
    //--------------- Music & SFX  -----------------------//
    //----------------------------------------------------//
    /**
     * Mute / unmute the game theme
     */
    @Override
    public void toogleMuteTheme() {
        if (!this.mute) {
            this.theme.pause();
        } else {
            this.theme.playContinuously();
        }
        this.mute = !this.mute;
    }

    /**
     * Stop the theme position
     */
    @Override
    public void stopTheme() {
        this.theme.stop();
    }

    /**
     * Aux reset method
     */
    private void resetTheme() {
        this.stopTheme();
        this.theme.playContinuously();
    }
    
    /**
     * Decrease the volume of the music
     * @param volume
     */
    public void decVolumeMusic(float volume) {
        this.controlVolume(volume, Audio.MUSIC, Audio.DECREASE);
    }

    /**
     * Increase the volume of the music
     * @param volume
     */
    public void incVolumeMusic(float volume) {
        this.controlVolume(volume, Audio.MUSIC, Audio.INCREASE);
    }

    /**
     * Decrease the volume of the SFX
     * @param volume
     */
    public void decVolumeSFX(float volume) {
        this.controlVolume(volume, Audio.SFX, Audio.DECREASE);
    }

    /**
     * Increase the volume of the SFX
     * @param volume
     */
    public void incVolumeSFX(float volume) {
        this.controlVolume(volume, Audio.SFX, Audio.INCREASE);
    }

    /**
     * Control the audio volume
     * @param volume - float with the increment/decrement
     * @param type - Music or SFX
     * @param action - Increase or Decrease
     */
    private void controlVolume(float volume, byte type, byte action) {
        for (Audio audio : audioList) {
            if (audio.getType() == type) {
                if (action == Audio.DECREASE) {
                    audio.decVolume(volume);
                } else {
                    audio.addVolume(volume);
                }
            }
        }
    }

    /**
     * Generic audio control
     */
    public void audioMuteControl(byte type, boolean mute) {
        for (Audio audio : audioList) {
            if (audio.getType() == type) {
                if (mute) {
                    audio.mute();
                } else {
                    audio.unmute();
                }
            }
        }
    }

    //----------------------------------------------------//
    //--------------- 	Game Level  ----------------------//
    //----------------------------------------------------//
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
        this.theme.stop();
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

    /**
     * Change the game state
     */
    @Override
    public synchronized void changeGameState(int state) {
        this.gameState.currentState = state;
    }

    //----------------------------------------------------//
    //------------------- Accessors ----------------------//
    //----------------------------------------------------//
    //public Score getScore()                     {   return (this.score);        }
    //public GameOver getGameOver()               {   return this.gameOver;       }
    public StateMachine getGameState()              {   return this.gameState;      }
    public int getInternalResolutionWidth()         {   return (this.wwm);          }
    public int getInternalResolutionHeight()        {   return (this.whm);          }
    public VolatileImage getBufferedImage()         {   return (this.bufferImage);  }
    public Graphics2D getG2D()                      {   return (this.g2d);          }
    // public Score getScore()                         {   return (this.score);        }
    // public Board getBoard()                         {   return (this.board);        }
    public void updateGraphics2D(Graphics2D g2d)    {   this.g2dFS = g2d;           }


    /**
     * Control the Game-Level
     */
    private class GameLevel {

        private GameInterface gameRef       = null;
        private static byte FIRST_LEVEL     = 0;
        private static byte SECOND_LEVEL    = 1;
        private static byte THIRD_LEVEL     = 2;
        private volatile byte currentLevel  = FIRST_LEVEL;

        /**
         * Constructor
         * @param game
         */
        public GameLevel(GameInterface game) {
            this.gameRef = game;
        }

        public void nextLevel() {
            this.currentLevel = (byte)(++this.currentLevel%3);
        }

        public byte getCurrentLevel() {
            return (this.currentLevel);
        }
    }
}