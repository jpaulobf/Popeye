package util;

import javax.imageio.ImageIO;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;

/**
 * This class is responsible for load the game stuffs
 */
public class LoadingStuffs {
    
    //private instance of loader
    private static LoadingStuffs instance       = null;
    private int chargeStatus                    = 0;

    //Stuffs Map
    private Map<String, BufferedImage> images   = new HashMap<String, BufferedImage>();
    private Map<String, Audio> audios           = new HashMap<String, Audio>();

    /**
     * Get each audio added.
     * @return
     */
    public List<Audio> getAudioList() {
        return (new ArrayList<Audio>(this.audios.values()));
    }

    /**
     * Constructor... load the game stuffs...
     */
    private LoadingStuffs() {
        //load the tiles and sprites
        try {
            
            BufferedImage image = null;
            image = ImageIO.read(new File("images\\really.png"));
            images.put("really", image);

            image = ImageIO.read(new File("images\\yes.png"));
            images.put("lb-yes", image);

            image = ImageIO.read(new File("images\\no.png"));
            images.put("lb-no", image);

            image = ImageIO.read(new File("images\\splash2.png"));
            images.put("splashImage", image);

            image = ImageIO.read(new File("images\\bean.png"));
            images.put("bean", image);

            //------------------------------------------//
            //----------------- Sprites ----------------//
            //------------------------------------------//
            image = ImageIO.read(new File("images\\popeye.png"));
            images.put("popeye-sprite", image);

            image = ImageIO.read(new File("images\\p1.png"));
            images.put("popeye-sprite-0", image);

            image = ImageIO.read(new File("images\\p2.png"));
            images.put("popeye-sprite-1", image);

            image = ImageIO.read(new File("images\\p3.png"));
            images.put("popeye-sprite-2", image);

            image = ImageIO.read(new File("images\\p4.png"));
            images.put("popeye-sprite-3", image);

            image = ImageIO.read(new File("images\\p5.png"));
            images.put("popeye-sprite-4", image);

            image = ImageIO.read(new File("images\\p6.png"));
            images.put("popeye-sprite-5", image);

            image = ImageIO.read(new File("images\\p1i.png"));
            images.put("i-popeye-sprite-0", image);

            image = ImageIO.read(new File("images\\p2i.png"));
            images.put("i-popeye-sprite-1", image);

            image = ImageIO.read(new File("images\\p3i.png"));
            images.put("i-popeye-sprite-2", image);

            image = ImageIO.read(new File("images\\p4i.png"));
            images.put("i-popeye-sprite-3", image);

            image = ImageIO.read(new File("images\\p5i.png"));
            images.put("i-popeye-sprite-4", image);

            image = ImageIO.read(new File("images\\p6i.png"));
            images.put("i-popeye-sprite-5", image);


            //------------------------------------------//
            //----------------- Menu -------------------//
            //------------------------------------------//
            image = ImageIO.read(new File("images\\popeyelogo.png"));
            images.put("popeyelogo", image);

            image = ImageIO.read(new File("images\\selector.png"));
            images.put("selector", image);

            image = ImageIO.read(new File("images\\menu_frame.png"));
            images.put("menu-frame", image);

            image = ImageIO.read(new File("images\\lbPlayGame.png"));
            images.put("lbPlayGame", image);

            image = ImageIO.read(new File("images\\lbOptions.png"));
            images.put("lbOptions", image);

            image = ImageIO.read(new File("images\\lbExitGame.png"));
            images.put("lbExitGame", image);

            //------------------------------------------//
            //----------------- Options ----------------//
            //------------------------------------------//
            image = ImageIO.read(new File("images\\popeye_options.png"));
            images.put("popeye-options", image);

            image = ImageIO.read(new File("images\\options_logo.png"));
            images.put("options-logo", image);

            image = ImageIO.read(new File("images\\selector_options.png"));
            images.put("selector-options", image);

            image = ImageIO.read(new File("images\\toogle_off.png"));
            images.put("toogle-off", image);

            image = ImageIO.read(new File("images\\toogle_on.png"));
            images.put("toogle-on", image);

            image = ImageIO.read(new File("images\\toogle_on.png"));
            images.put("toogle-on", image);
            
            image = ImageIO.read(new File("images\\lb_play_music.png"));
            images.put("label-play-music", image);

            image = ImageIO.read(new File("images\\lb_play_sfx.png"));
            images.put("label-play-sfx", image);

            image = ImageIO.read(new File("images\\lb_music_volume.png"));
            images.put("label-music-vol", image);

            image = ImageIO.read(new File("images\\lb_sfx_volume.png"));
            images.put("label-sfx-vol", image);

            image = ImageIO.read(new File("images\\lb_exit_option.png"));
            images.put("label-exit-option", image);

            image = ImageIO.read(new File("images\\v1_on.png"));
            images.put("v1-on", image);

            image = ImageIO.read(new File("images\\v2_on.png"));
            images.put("v2-on", image);

            image = ImageIO.read(new File("images\\v3_on.png"));
            images.put("v3-on", image);

            image = ImageIO.read(new File("images\\v4_on.png"));
            images.put("v4-on", image);

            image = ImageIO.read(new File("images\\v5_on.png"));
            images.put("v5-on", image);

            image = ImageIO.read(new File("images\\v6_on.png"));
            images.put("v6-on", image);

            image = ImageIO.read(new File("images\\v1_off.png"));
            images.put("v1-off", image);

            image = ImageIO.read(new File("images\\v2_off.png"));
            images.put("v2-off", image);

            image = ImageIO.read(new File("images\\v3_off.png"));
            images.put("v3-off", image);

            image = ImageIO.read(new File("images\\v4_off.png"));
            images.put("v4-off", image);

            image = ImageIO.read(new File("images\\v5_off.png"));
            images.put("v5-off", image);

            image = ImageIO.read(new File("images\\v6_off.png"));
            images.put("v6-off", image);

            //------------------------------------------//
            //-------------- GameOver ------------------//
            //------------------------------------------//

            image = ImageIO.read(new File("images\\gameover.png"));
            images.put("gameover", image);

            Logger.INFO("read all images...", this);


            //--------------------------------------------------------------------------------------------------//
            Audio audio = new Audio("audio\\opening.wav", 0, Audio.SFX);
            if (audio != null && audio.isReady()) {
                audios.put("opening", audio);
            }

            audio = new Audio("audio\\closing.wav", 0, Audio.SFX);
            if (audio != null && audio.isReady()) {
                audios.put("closing", audio);
            }

            audio = new Audio("audio\\theme1.wav", 0, Audio.MUSIC);
            if (audio != null && audio.isReady()) {
                audios.put("theme1", audio);
            }

            //------------------------------------------//
            //----------------- Menu -------------------//
            //------------------------------------------//

            audio = new Audio("audio\\star.wav", 0, Audio.SFX);
            if (audio != null && audio.isReady()) {
                audios.put("star", audio);
            }

            audio = new Audio("audio\\start.wav", 0, Audio.SFX);
            if (audio != null && audio.isReady()) {
                audios.put("start", audio);
            }

            audio = new Audio("audio\\intro.wav", 0, Audio.MUSIC);
            if (audio != null && audio.isReady()) {
                audios.put("intro", audio);
            }

            audio = new Audio("audio\\s1.wav", 0, Audio.SFX);
            if (audio != null && audio.isReady()) {
                audios.put("right-foot", audio);
            }

            audio = new Audio("audio\\s2.wav", 0, Audio.SFX);
            if (audio != null && audio.isReady()) {
                audios.put("left-foot", audio);
            }
            Logger.INFO("read all audio...", this);
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create a transluced volatile image
     * @param image
     * @return
     */
    protected VolatileImage createVImage(BufferedImage image) { 
        VolatileImage vImage = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().createCompatibleVolatileImage(image.getWidth(), image.getHeight(), Transparency.BITMASK);
        Graphics2D bgd2 = (Graphics2D)vImage.getGraphics();
        bgd2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OUT));
        bgd2.setColor(new java.awt.Color(255,255,255,0));
        bgd2.fillRect(0, 0, image.getWidth(), image.getHeight());
        bgd2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
        bgd2.drawImage(image, 0, 0, vImage.getWidth(), vImage.getHeight(), //dest w1, h1, w2, h2
                              0, 0, image.getWidth(), image.getHeight(), //source w1, h1, w2, h2
                              null);
        return (vImage);
    }

    /**
     * Return the stored image
     * @param imageName
     * @return
     */
    public BufferedImage getImage(String imageName) {
        return (this.images.get(imageName));
    }

    /**
     * Return the stored audio
     * @param audioName
     * @return
     */
    public Audio getAudio(String audioName) {
        return (this.audios.get(audioName));
    }

    /**
     * Recover the singleton instance  
     * @return
     */
    public static LoadingStuffs getInstance() {
        if (instance == null) {
            instance = new LoadingStuffs();
        }
        return instance;
    }

    /**
     * Returns the charge counter status (0 ... 100%)
     * @return
     */
    public int getChargeStatus() {
        return (this.chargeStatus);
    }
}