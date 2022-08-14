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
    private static LoadingStuffs instance   = null;
    private int chargeStatus                = 0;

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

            image = ImageIO.read(new File("images\\splash.png"));
            images.put("splashImage", image);

            image = ImageIO.read(new File("images\\bean.png"));
            images.put("bean", image);

            image = ImageIO.read(new File("images\\gameover.png"));
            images.put("gameover", image);

            Logger.INFO("read all images...", this);

            Audio audio = new Audio("audio\\opening.wav", 0, Audio.SFX);
            if (audio != null && audio.isReady()) {
                audios.put("opening", audio);
            }

            audio = new Audio("audio\\closing.wav", 0, Audio.SFX);
            if (audio != null && audio.isReady()) {
                audios.put("closing", audio);
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