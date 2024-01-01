package game.menu;

import interfaces.GameInterface;
import java.awt.Graphics2D;
import java.awt.Color;
import util.LoadingStuffs;
import java.awt.image.BufferedImage;

import game.GameController;
import util.Audio;

/**
 * Class representing Option Screen Graphics & Logic
 */
public class OptionsScreen {
    
    //game parameters
    private GameController gameRef                        = null;
    private Graphics2D g2d                      = null;
    
    private BufferedImage popeye                = null;
    private BufferedImage selector              = null;
    private BufferedImage optionsLogo           = null;
    private BufferedImage labelPlayMusic        = null;
    private BufferedImage toogleOn              = null;
    private BufferedImage toogleOff             = null;
    private BufferedImage toogleMusic           = null;
    private BufferedImage toogleSfx             = null;
    private BufferedImage labelPlaySfx          = null;
    private BufferedImage labelMusicVolume      = null;
    private BufferedImage labelSfxVolume        = null;
    private BufferedImage labelExit             = null;
    private BufferedImage [] volumeOn           = null;
    private BufferedImage [] volumeOff          = null;
    private BufferedImage [] musicVolIcon       = null;
    private BufferedImage [] sfxVolIcon         = null;
    private final byte TOTAL_OPTIONS            = 5;

    //sounds
    private Audio item                          = null;

    //menu control
    private final short SELECTOR_START          = 314;
    private final byte SELECTOR_DIFF            = 93;
    private byte selectorPosition               = 0;
    private volatile boolean goMenu             = false;
    private int resolutionW                     = 0;
    private int resolutionH                     = 0;
    private short selectorO                     = SELECTOR_START;
    private boolean isMusicOn                   = true;
    private boolean isSfxOn                     = true;
    private short selectorP                     = (short)(selectorO + (selectorPosition * SELECTOR_DIFF));
    private byte musicVolume                    = 6;
    private byte sfxVolume                      = 6;
    private final short ADDITIONAL_PIXELS_EXIT  = 96;

    /**
     * Constructor
     * @param game
     */
    public OptionsScreen(GameInterface game) {

        //get the game pointer
        this.gameRef                = (GameController)game;
        this.g2d                    = this.getG2D();
        this.resolutionW            = this.gameRef.getInternalResolutionWidth();
        this.resolutionH            = this.gameRef.getInternalResolutionHeight();

        //define images arrays
        this.volumeOn               = new BufferedImage[6];
        this.volumeOff              = new BufferedImage[6];
        this.musicVolIcon           = new BufferedImage[6];
        this.sfxVolIcon             = new BufferedImage[6];

        //load images
        this.selector               = LoadingStuffs.getInstance().getImage("selector-options");
        this.popeye                 = LoadingStuffs.getInstance().getImage("popeye-options");
        this.optionsLogo            = LoadingStuffs.getInstance().getImage("options-logo");
        this.labelPlayMusic         = LoadingStuffs.getInstance().getImage("label-play-music");
        this.toogleOn               = LoadingStuffs.getInstance().getImage("toogle-on");
        this.toogleOff              = LoadingStuffs.getInstance().getImage("toogle-off");
        this.labelPlaySfx           = LoadingStuffs.getInstance().getImage("label-play-sfx");
        this.labelMusicVolume       = LoadingStuffs.getInstance().getImage("label-music-vol");
        this.labelSfxVolume         = LoadingStuffs.getInstance().getImage("label-sfx-vol");
        this.labelExit              = LoadingStuffs.getInstance().getImage("label-exit-option");

        //get the volume icons
        this.volumeOn[0]            = LoadingStuffs.getInstance().getImage("v1-on");
        this.volumeOn[1]            = LoadingStuffs.getInstance().getImage("v2-on");
        this.volumeOn[2]            = LoadingStuffs.getInstance().getImage("v3-on");
        this.volumeOn[3]            = LoadingStuffs.getInstance().getImage("v4-on");
        this.volumeOn[4]            = LoadingStuffs.getInstance().getImage("v5-on");
        this.volumeOn[5]            = LoadingStuffs.getInstance().getImage("v6-on");
        this.volumeOff[0]           = LoadingStuffs.getInstance().getImage("v1-off");
        this.volumeOff[1]           = LoadingStuffs.getInstance().getImage("v2-off");
        this.volumeOff[2]           = LoadingStuffs.getInstance().getImage("v3-off");
        this.volumeOff[3]           = LoadingStuffs.getInstance().getImage("v4-off");
        this.volumeOff[4]           = LoadingStuffs.getInstance().getImage("v5-off");
        this.volumeOff[5]           = LoadingStuffs.getInstance().getImage("v6-off");

        //define the music & sfx toogle image
        this.toogleMusic            = this.toogleOn;
        this.toogleSfx              = this.toogleOn;

        //define the music volume images
        for (byte i = 0; i < this.volumeOn.length; i++) {
            this.musicVolIcon[i]    = this.volumeOn[i];
            this.sfxVolIcon[i]      = this.volumeOn[i];
        }

        //load the sounds
        this.item = LoadingStuffs.getInstance().getAudio("star");
    }

    /**
     * Game logic update
     * @param frametime
     */
    public synchronized void update(long frametime) {
        
        //calc the selector position
        this.selectorP = (short)(this.selectorO + (this.selectorPosition * SELECTOR_DIFF));
       
        //if the selector is in the exit option (go xx pixel dows)
        if (this.selectorPosition == TOTAL_OPTIONS - 1) {
            this.selectorP += ADDITIONAL_PIXELS_EXIT;
        }

        //define the music toogle button
        if (this.isMusicOn) {
            this.toogleMusic = this.toogleOn;
        } else {
            this.toogleMusic = this.toogleOff;
        }

        //define the sfx toogle button
        if (this.isSfxOn) {
            this.toogleSfx = this.toogleOn;
        } else {
            this.toogleSfx = this.toogleOff;
        }

        //define the volume image
        for (byte i = 0; i < this.volumeOn.length; i++) {
            this.musicVolIcon[i]    = this.volumeOn[i];
            this.sfxVolIcon[i]      = this.volumeOn[i];
        }

        switch(this.musicVolume) { //no break, execute in chain
            case 0: this.musicVolIcon[0] = this.volumeOff[0];
            case 1: this.musicVolIcon[1] = this.volumeOff[1];
            case 2: this.musicVolIcon[2] = this.volumeOff[2];
            case 3: this.musicVolIcon[3] = this.volumeOff[3];
            case 4: this.musicVolIcon[4] = this.volumeOff[4];    
            case 5: this.musicVolIcon[5] = this.volumeOff[5];    
        }

        switch(this.sfxVolume) { //no break, execute in chain
            case 0: this.sfxVolIcon[0] = this.volumeOff[0];
            case 1: this.sfxVolIcon[1] = this.volumeOff[1];
            case 2: this.sfxVolIcon[2] = this.volumeOff[2];
            case 3: this.sfxVolIcon[3] = this.volumeOff[3];
            case 4: this.sfxVolIcon[4] = this.volumeOff[4];
            case 5: this.sfxVolIcon[5] = this.volumeOff[5];
        }
    }

    /**
     * Draw method
     * @param frametime
     */    
    public synchronized void draw(long frametime) {

        this.g2d.setBackground(new Color(253, 187, 41));
        this.g2d.clearRect(0, 0, resolutionW, resolutionH);
        
        //popeye
        this.g2d.drawImage(this.popeye,             19, 476, null);

        //selector
        this.g2d.drawImage(this.selector,           514, this.selectorP, null);
        
        //logo
        this.g2d.drawImage(this.optionsLogo,        1228, 47, null);
        
        //labels
        this.g2d.drawImage(this.labelPlayMusic,     609, 326, null);
        this.g2d.drawImage(this.labelMusicVolume,   609, 419, null);
        
        //music volume & toogle
        this.g2d.drawImage(this.toogleMusic,        1482, 326, null);
        this.g2d.drawImage(this.musicVolIcon[5],    1482, 423, null);
        this.g2d.drawImage(this.musicVolIcon[4],    1513, 427, null);
        this.g2d.drawImage(this.musicVolIcon[3],    1544, 431, null);
        this.g2d.drawImage(this.musicVolIcon[2],    1575, 435, null);
        this.g2d.drawImage(this.musicVolIcon[1],    1606, 439, null);
        this.g2d.drawImage(this.musicVolIcon[0],    1637, 443, null);

        //labels
        this.g2d.drawImage(this.labelPlaySfx,       609, 512, null);
        this.g2d.drawImage(this.labelSfxVolume,     609, 606, null);

        //music volume & toogle
        this.g2d.drawImage(this.toogleSfx,          1482, 513, null);
        this.g2d.drawImage(this.sfxVolIcon[5],      1482, 610, null);
        this.g2d.drawImage(this.sfxVolIcon[4],      1513, 614, null);
        this.g2d.drawImage(this.sfxVolIcon[3],      1544, 618, null);
        this.g2d.drawImage(this.sfxVolIcon[2],      1575, 622, null);
        this.g2d.drawImage(this.sfxVolIcon[1],      1606, 626, null);
        this.g2d.drawImage(this.sfxVolIcon[0],      1637, 630, null);

        //label exit
        this.g2d.drawImage(this.labelExit,          550, 789, null);
    }

    /**
     * Keyboard control the options screen
     * @param key
     */
    public void keyMovement(int key) {
        
        if (key == 38) { //UP
            if ((this.selectorPosition == 2 && !this.isMusicOn) || (this.selectorPosition == 4 && !this.isSfxOn)) {
                this.selectorPosition -= 2;
            } else {
                this.selectorPosition = (byte)(this.selectorPosition-1);
            }
            if (this.selectorPosition < 0) {
                this.selectorPosition = TOTAL_OPTIONS - 1;
            }
            this.item.play();

        } else if (key == 40) { //DOWN
            if ((this.selectorPosition == 0 && !this.isMusicOn) || (this.selectorPosition == 2 && !this.isSfxOn)) {
                this.selectorPosition += 2;
            } else {
                this.selectorPosition = (byte)((this.selectorPosition+1)%TOTAL_OPTIONS);
            }
            this.item.play();

        } else if (key == 37 || key == 39) { //LEFT

            if (this.selectorPosition == 0) {
                this.isMusicOn = !this.isMusicOn;
                //Mute
                if (!this.isMusicOn) {
                    this.gameRef.getGameMusicSFX().audioMuteControl(Audio.MUSIC, true);
                    this.musicVolume = 0;
                //Unmute
                } else {
                    this.gameRef.getGameMusicSFX().audioMuteControl(Audio.MUSIC, false);
                    this.musicVolume = 6;
                }

            } else if (this.selectorPosition == 1 && key == 37) { //music - left
                this.musicVolume = (byte)((this.musicVolume + 1)%7);
                if (this.musicVolume == 0) {
                    this.musicVolume = 1;
                    this.gameRef.getGameMusicSFX().decVolumeMusic(25f);
                } else {
                    this.gameRef.getGameMusicSFX().incVolumeMusic(5f);
                }
                
            } else if (this.selectorPosition == 1 && key == 39) { //music - right
                this.musicVolume--;
                if (this.musicVolume < 1) {
                    this.musicVolume = 6;
                    this.gameRef.getGameMusicSFX().incVolumeMusic(25f);
                } else {
                    this.gameRef.getGameMusicSFX().decVolumeMusic(5f);
                }

            } else if (this.selectorPosition == 2) {
                this.isSfxOn = !this.isSfxOn;
                //mute
                if (!this.isSfxOn) {
                    this.gameRef.getGameMusicSFX().audioMuteControl(Audio.SFX, true);
                    this.sfxVolume = 0;
                //unmute
                } else {
                    this.gameRef.getGameMusicSFX().audioMuteControl(Audio.SFX, false);
                    this.sfxVolume = 6;
                }

            } else if (this.selectorPosition == 3 && key == 37) { //sfx - left
                this.sfxVolume = (byte)((this.sfxVolume + 1)%7);
                if (this.sfxVolume == 0) {
                    this.sfxVolume = 1;
                    this.gameRef.getGameMusicSFX().decVolumeSFX(25f);
                } else {
                    this.gameRef.getGameMusicSFX().incVolumeSFX(5f);
                }

            } else if (this.selectorPosition == 3 && key == 39) { //sfx - right
                this.sfxVolume--;
                if (this.sfxVolume < 1) {
                    this.sfxVolume = 6;
                    this.gameRef.getGameMusicSFX().incVolumeSFX(25f);
                } else {
                    this.gameRef.getGameMusicSFX().decVolumeSFX(5f);
                }

            }
        } else if (key == 10) { //ENTER
            if (this.selectorPosition == (TOTAL_OPTIONS - 1)) {
                this.goMenu = true;
            }

        } else if (key == 27) { //Esc - Back to Menu
            this.goMenu = true;
        }
    }

    /**
     * Reset game options
     */
    public void reset() {
        this.selectorPosition = 0;
        this.goMenu = false;
    }

    //getters
    public boolean goMenu()                     {   return (this.goMenu);                   }
    public Graphics2D getG2D()					{ 	return (this.gameRef.getG2D());		    }

    public void firstUpdate(long frametime) {
    }
}