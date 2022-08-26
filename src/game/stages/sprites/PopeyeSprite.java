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
    public byte getCurrentLevel() {return this.currentLevel;}

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
            
            //thru
            if (this.positionX > 1560) {
                this.positionX = 302;
            } else if (this.positionX < 298) {
                this.positionX = 1560;
            }

            //center (no path)
            if (this.positionX > 649 && this.positionX < 1218) {
                this.currentLevel   = 1;
                this.positionY += 192;
                this.positionYInt = (int)this.positionY;
            }

            /*
            //left ladder
            if (this.enableBottom && this.positionX > 425 && this.positionX < 459) {
                currentLevel++;
                this.positionY += 192;
                this.positionYInt = (int)this.positionY;
                this.enableBottom = false;
            }

            //right ladder
            if (this.enableBottom && this.positionX > 1419 && this.positionX < 1450) {
                this.currentLevel++;
                this.positionY += 192;
                this.positionYInt = (int)this.positionY;
                this.enableBottom = false;
            } */
            
        } else {
            //lock the sides
            if (this.positionX < 367) {
                this.positionX = 367;
            } else if (this.positionX > 1492) {
                this.positionX = 1492;
            }
        }
        
        //if want to go down
        if (this.enableBottom) {
            //central ladder
            if (this.currentLevel == 1 && this.positionX > 892 && this.positionX < 954) {
                this.currentLevel++;
                this.positionY += 192;
                this.positionYInt = (int)this.positionY;
                this.enableBottom = false;
            }

            if (this.currentLevel == 2  && this.positionX > 817 && this.positionX < 893) {
                this.currentLevel++;
                this.positionY += 192;
                this.positionYInt = (int)this.positionY;
            }

            //left ladder
            if (this.positionX > 425 && this.positionX < 459) {
                if (this.currentLevel < 3) {
                    this.currentLevel++;
                    this.positionY += 192;
                    this.positionYInt = (int)this.positionY;
                }
                this.enableBottom = false;
            }

            //right ladder
            if (this.positionX > 1419 && this.positionX < 1450) {
                if (this.currentLevel < 3) {
                    this.currentLevel++;
                    this.positionY += 192;
                    this.positionYInt = (int)this.positionY;
                }
                this.enableBottom = false;
            }
        }

        if (this.enableTop) {
            //left ladder
            if (this.positionX > 501 && this.positionX < 535) {
                if (this.currentLevel > 0) {
                    this.currentLevel--;
                    this.positionY -= 192;
                    this.positionYInt = (int)this.positionY;
                    this.enableTop = false;
                }
            }

            //right ladder
            if (this.positionX > 1324 && this.positionX < 1356) {
                if (this.currentLevel > 0) {
                    this.currentLevel--;
                    this.positionY -= 192;
                    this.positionYInt = (int)this.positionY;
                    this.enableTop = false;
                }
            }

            if (this.currentLevel == 3 && this.positionX > 725 && this.positionX < 761) {
                this.currentLevel--;
                this.positionY -= 192;
                this.positionYInt = (int)this.positionY;
            }
        }
    }
}