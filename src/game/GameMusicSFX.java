package game;

import java.util.List;
import interfaces.GameInterface;
import java.util.ArrayList;
import util.Audio;
import util.LoadingStuffs;

/**
 * Game Music & SFX Class
 */
public class GameMusicSFX {


    private List<Audio> audioList   = new ArrayList<Audio>();
    private volatile Audio theme            = null;
    private volatile boolean mute           = false;
    private GameInterface gameRef           = null;

    /**
     * Constructor
     * @param game
     */
    public GameMusicSFX(GameInterface game) {

        this.gameRef = game;

        //get the audio list
        this.audioList = LoadingStuffs.getInstance().getAudioList();
    }
    
    /**
     * Mute / unmute the game theme
     */
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
    public void stopTheme() {
        this.theme.stop();
    }

    /**
     * Aux reset method
     */
    public void resetTheme() {
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

    /**
     * Play the current theme
     */
    public void playTheme() {
        this.theme.playContinuously();
    }
}