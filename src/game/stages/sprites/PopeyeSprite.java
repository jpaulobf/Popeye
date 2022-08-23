package game.stages.sprites;

import game.SpriteImpl;
import game.stages.StageInterface;
import util.LoadingStuffs;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Class representing Popeye Sprite
 */
public class PopeyeSprite extends SpriteImpl {

    //---------------------------------------------------------//
    //--- Properties & member variables                     ---//
    //---------------------------------------------------------//
    private BufferedImage popeyeSprite  = null;
    private StageInterface stageRef     = null;
    private Graphics2D g2d              = null;
    private boolean enableLeft          = false;
    private boolean enableRight         = false;
    private boolean enableTop           = false;
    private boolean enableBottom        = false;
    private int positionXInt            = 0;
    private int positionYInt            = 0;
    private byte currentLevel           = 0;

    public void enableLeftFlag()    {if (!this.enableLeft) this.enableLeft = true;}
    public void disableLeftFlag()   {if (this.enableLeft) this.enableLeft = false;}
    public void enableRightFlag()   {if (!this.enableRight) this.enableRight = true;}
    public void disableRightFlag()  {if (this.enableRight) this.enableRight = false;}
    public void enableBottomFlag()   {if (!this.enableBottom) this.enableBottom = true;}
    public void disableBottomFlag()  {if (this.enableBottom) this.enableBottom = false;}
    public void enableTopFlag()   {if (!this.enableTop) this.enableTop = true;}
    public void disableTopFlag()  {if (this.enableTop) this.enableTop = false;}

    /**
     * Constructor
     * @param stage
     */
    public PopeyeSprite(StageInterface stage, int initialX, int initialY) {
        this.stageRef       = stage;
        this.g2d            = this.stageRef.getG2D();
        this.popeyeSprite   = LoadingStuffs.getInstance().getImage("popeye-sprite");
        this.positionX      = initialX;
        this.positionY      = initialY;
        this.positionXInt   = (int)this.positionX;
        this.positionYInt   = (int)this.positionY;
        this.width          = (short)this.popeyeSprite.getWidth();
        this.height         = (short)this.popeyeSprite.getHeight();
    }

    @Override
    public void draw(long frametime) {
        this.g2d.drawImage(this.popeyeSprite, this.positionXInt, this.positionYInt, null);
    }

    @Override
    public void update(long frametime) {
        if (this.enableLeft) {
            this.decPositionX();
            this.positionXInt = (int)Math.floor(this.positionX);
        } else if (this.enableRight) {
            this.addPositionX();
            this.positionXInt = (int)Math.floor(this.positionX);
        }

        if (this.enableTop) {
            this.positionYInt = (int)Math.floor(this.positionY);
        } else if (this.enableBottom) {
            this.positionYInt = (int)Math.floor(this.positionY);
        }

        if (currentLevel == 0) {
            if (this.positionX > 1560) {
                this.positionX      = 302;
            } else if (this.positionX < 298) {
                this.positionX      = 1560;
            }

            if (this.positionX > 649 && this.positionX < 1218) {
                this.currentLevel   = 1;
                this.positionY += 192;
                this.positionYInt = (int)this.positionY;
            }
        } else {
            //lock the sides
            if (this.positionX < 367) {
                this.positionX = 367;
            } else if (this.positionX > 1492) {
                this.positionX = 1492;
            }

            //if want to go down
            if (this.enableBottom) {
                //central ladder
                if (currentLevel == 1 && this.positionX > 910 && this.positionX < 934) {
                    this.currentLevel++;
                    this.positionY += 192;
                    this.positionYInt = (int)this.positionY;
                    this.enableBottom = false;
                }

                //left ladder
                if (this.positionX > 411 && this.positionX < 457) {
                    if (this.currentLevel < 3) this.currentLevel++;
                    this.positionY += 192;
                    this.positionYInt = (int)this.positionY;
                    this.enableBottom = false;
                }
            }

            if (this.enableTop) {
                //left ladder
                if (this.positionX > 491 && this.positionX < 533) {
                    if (this.currentLevel > 0) this.currentLevel--;
                    this.positionY -= 192;
                    this.positionYInt = (int)this.positionY;
                    this.enableTop = false;
                }
            }

            /*
            if (currentLevel == 1 && this.enableBottom) {
                if (this.positionX > 910 && this.positionX < 934) {
                    this.currentLevel = 2;
                    this.positionY = 696;
                    this.positionYInt = (int)this.positionY;
                } else if (this.positionX > 411 && this.positionX < 457) {
                    this.currentLevel = 2;
                    this.positionY = 696;
                    this.positionYInt = (int)this.positionY;
                }
            }

            if (currentLevel == 2 && this.enableBottom) {
                if (this.positionX > 411 && this.positionX < 457) {
                    this.currentLevel = 3;
                    this.positionY = 888;
                    this.positionYInt = (int)this.positionY;
                }
            }*/
        }
    }
}