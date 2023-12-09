package game.player;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import util.Audio;
import util.LoadingStuffs;
import java.awt.image.BufferedImage;

import game.Game;

/**
 * Class representing the game board
 */
public class Player extends SpriteImpl
{
    private final int RIGHT_DIRECTION                   = 0;
    private final int LEFT_DIRECTION                    = 6;
    //---------------------------------------------------------//
    //--- Properties & member variables                     ---//
    //---------------------------------------------------------//
    private BufferedImage currentPlayerSprite           = null;
    private BufferedImage sprites[]                     = new BufferedImage[12];
    private Game mainGameFrameRef                       = null;
    private Graphics2D g2d                              = null;
    private boolean enableLeft                          = false;
    private boolean enableRight                         = false;
    private boolean enableTop                           = false;
    private boolean enableBottom                        = false;
    private byte currentLevel                           = 0;
    private byte ladderStep                             = 0;
    private Audio leftFoot                              = LoadingStuffs.getInstance().getAudio("left-foot");
    private Audio rightFoot                             = LoadingStuffs.getInstance().getAudio("right-foot");
    private Audio currentFoot                           = null;
    private AffineTransform affineTransform             = null;
    private volatile boolean jumpingAnimating           = false;
    private volatile boolean leftLadderUpAnimating      = false;
    private volatile boolean rightLadderUpAnimating     = false;
    private volatile boolean inTheLeftLadder            = false;
    private volatile boolean inTheRightLadder           = false;
    
    //walking animation
    private final byte walkingStates                    = 6;
    private byte currentWalkState                       = 0;
    private long walkingAnimationCounter                = 0;
    private long walkingSoundCounter                    = 0;
    private volatile boolean playerStoped               = true;
    private int playerDirection                         = RIGHT_DIRECTION;
    private int jumpDirection                           = RIGHT_DIRECTION;
    private boolean playFoot                            = false;
    private boolean currentFootLeft                     = true;

    //jump animation
    private long fallCounter                            = 0;
    private long ladderJumpCounter                      = 0;
    
    public void enableLeftFlag()    	        {if (!this.enableLeft) this.enableLeft = true;}
    public void disableLeftFlag()   	        {if (this.enableLeft) this.enableLeft = false;}
    public void enableRightFlag()   	        {if (!this.enableRight) this.enableRight = true;}
    public void disableRightFlag()  	        {if (this.enableRight) this.enableRight = false;}
    public void enableBottomFlag()  	        {if (!this.enableBottom) this.enableBottom = true;}
    public void disableBottomFlag() 	        {if (this.enableBottom) this.enableBottom = false;}
    public void enableTopFlag()   		        {if (!this.enableTop) this.enableTop = true;}
    public void disableTopFlag()  		        {if (this.enableTop) this.enableTop = false;}
    public byte getCurrentLevel() 		        {return this.currentLevel;}

    /**
     * Constructor
     * @param stage
     */
    public Player(Game mainGameFrame, int initialX, int initialY) {
        this.mainGameFrameRef   = mainGameFrame;
        this.g2d                = this.mainGameFrameRef.getG2D();

        for (int cnt = 0; cnt < 6; cnt++) {
            this.sprites[cnt] = LoadingStuffs.getInstance().getImage("popeye-sprite-" + cnt);
        }

        for (int cnt = 0; cnt < 6; cnt++) {
            this.sprites[6 + cnt] = LoadingStuffs.getInstance().getImage("i-popeye-sprite-" + cnt);
        }
        
        this.currentPlayerSprite    = this.sprites[0];
        this.positionX              = initialX;
        this.positionY              = initialY;
        this.width                  = (short)this.currentPlayerSprite.getWidth();
        this.height                 = (short)this.currentPlayerSprite.getHeight();
        this.affineTransform        = new AffineTransform();
        this.affineTransform.scale(1, 1); 
    }

    @Override
    public void draw(long frametime) {
        this.affineTransform.translate(this.positionX, this.positionY);
        this.g2d.drawImage(this.currentPlayerSprite, affineTransform,  null);
        this.affineTransform.translate(-this.positionX, -this.positionY);
    }

    @Override
    public void update(long frametime) {

        //jumping animation
        if (this.jumpingAnimating) {
            this.currentWalkState = 5;
            this.fallCounter += frametime;
            //small jump
            if (this.fallCounter == frametime) {
                //this.jumpDirection = this.playerDirection;
            } else if (this.fallCounter < 200_000_000) {
                if (this.jumpDirection == RIGHT_DIRECTION) {
                    this.positionX += 1;
                } else {
                    this.positionX -= 1;
                }
                this.positionY -= 3;
            } else {
                if (this.jumpDirection == RIGHT_DIRECTION) {
                    this.positionX += 1;
                } else {
                    this.positionX -= 1;
                }
                this.positionY += 8;
                if (this.positionY >= 494) {
                    this.currentLevel = 1;
                    this.jumpingAnimating = false;
                    this.fallCounter = 0;
                    this.currentWalkState = 0;
                }
            }
            this.currentPlayerSprite = this.sprites[this.currentWalkState + jumpDirection];
        } else if (this.leftLadderUpAnimating) {
            //start the counter and set the direction
            this.ladderJumpCounter += frametime;
            this.playerDirection    = LEFT_DIRECTION;

            //set the sprite start position
            if (this.ladderStep == 0) {
                this.positionX = 510;
            }

            //small jump
            if (this.ladderJumpCounter < 200_000_000) {
                this.positionX -= 2;
                this.positionY -= 4;
            } else {
                this.positionY += 4;
                if (this.positionY >= 494 - (38 + (38 * ladderStep))) {
                    this.ladderStep += 1;
                    this.ladderJumpCounter = 0;
                    if (this.ladderStep % 2 == 0) {
                        this.currentWalkState = 1;
                    } else {
                        this.currentWalkState = 3;
                    }
                    
                    this.inTheLeftLadder = (this.ladderStep != 0) && (this.ladderStep < 5);
                    if (ladderStep == 5) {
                        this.currentLevel--;
                        this.ladderStep = 0;
                        this.currentWalkState = 0;
                    }
                    this.leftLadderUpAnimating = false;
                }
            }
            this.currentPlayerSprite = this.sprites[this.currentWalkState + playerDirection];

        } else if (this.rightLadderUpAnimating) {

            //start the counter and set the direction
            this.ladderJumpCounter += frametime;
            this.playerDirection    = RIGHT_DIRECTION;

            //set the sprite start position
            if (this.ladderStep == 0) {
                this.positionX = 1345;
            }

            //small jump
            if (this.ladderJumpCounter < 200_000_000) {
                this.positionX += 2;
                this.positionY -= 4;
            } else {
                this.positionY += 4;
                if (this.positionY >= 494 - (38 + (38 * ladderStep))) {
                    this.ladderStep += 1;
                    this.ladderJumpCounter = 0;
                    if (this.ladderStep % 2 == 0) {
                        this.currentWalkState = 1;
                    } else {
                        this.currentWalkState = 3;
                    }
                    
                    this.inTheRightLadder = (this.ladderStep != 0) && (this.ladderStep < 5);
                    if (ladderStep == 5) {
                        this.currentLevel--;
                        this.ladderStep = 0;
                        this.currentWalkState = 0;
                    }
                    this.rightLadderUpAnimating = false;
                }
            }
            this.currentPlayerSprite = this.sprites[this.currentWalkState + playerDirection];

        } else {          

            if (!this.inTheLeftLadder && !this.inTheRightLadder) {

                this.walkingAnimationCounter    += frametime;
                this.walkingSoundCounter        += frametime;
                
                //animate sprite
                if (this.walkingAnimationCounter >= 100_000_000 && !this.playerStoped) {
                    this.currentWalkState = (byte)(++this.currentWalkState%this.walkingStates);
                    this.currentPlayerSprite = this.sprites[this.currentWalkState + playerDirection];
                    this.walkingAnimationCounter = 0;          
                }

                //control step sound
                if (this.walkingSoundCounter >= 146_667_000 && !this.playerStoped) {
                    this.currentFootLeft = !this.currentFootLeft;
                    this.playFoot = true;
                    this.walkingSoundCounter = 0;
                    if (this.currentFootLeft) {
                        this.currentFoot = this.leftFoot;
                    } else {
                        this.currentFoot = this.rightFoot;
                    }
                }

                //play step sound
                if (playFoot) {
                    this.currentFoot.play();
                    playFoot = false;
                }

                //walk left / right
                if (this.enableLeft) {
                    this.decPositionX();
                } else if (this.enableRight) {
                    this.addPositionX();
                }
            }

            //control level 1 / plataform 0
            if (currentLevel == 0) {
                //thru
                if (this.positionX > 1560) {
                    this.positionX = 302;
                } else if (this.positionX < 298) {
                    this.positionX = 1560;
                }

                //center (no path)
                if (this.positionX > 630 && this.positionX < 1218) {
                    this.jumpingAnimating = true;
                    this.jumpDirection = this.playerDirection;
                }
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
                    this.enableBottom = false;
                }

                //botton left ladder
                if (this.currentLevel == 2  && this.positionX > 817 && this.positionX < 893) {
                    this.currentLevel++;
                    this.positionY += 192;
                }

                //botton right ladder
                if (this.currentLevel == 2  && this.positionX > 995 && this.positionX < 1035) {
                    this.currentLevel++;
                    this.positionY += 192;
                }

                //left ladder
                if (this.positionX > 425 && this.positionX < 459) {
                    if (this.currentLevel < 3) {
                        this.currentLevel++;
                        this.positionY += 192;
                    }
                    this.enableBottom = false;
                }

                //right ladder
                if (this.positionX > 1419 && this.positionX < 1450) {
                    if (this.currentLevel < 3) {
                        this.currentLevel++;
                        this.positionY += 192;
                    }
                    this.enableBottom = false;
                }
            }

            if (this.enableTop) {
                //left ladder
                if ((this.positionX > 490 && this.positionX < 555) || this.inTheLeftLadder) {
                    if (this.currentLevel > 0) {
                        this.leftLadderUpAnimating = true;
                    }
                }

                //right ladder
                if ((this.positionX > 1300 && this.positionX < 1365) || this.inTheRightLadder) {
                    if (this.currentLevel > 0) {
                        this.rightLadderUpAnimating = true;
                    }
                }

                //botton left ladder
                if (this.currentLevel == 3 && this.positionX > 725 && this.positionX < 761) {
                    this.currentLevel--;
                    this.positionY -= 192;
                }

                //botton right ladder
                if (this.currentLevel == 3  && this.positionX > 1075 && this.positionX < 1113) {
                    this.currentLevel--;
                    this.positionY -= 192;
                }
            }
        }
    }

    public void move(int keyCode) {
        if (keyCode == 39) { //right
            this.disableLeftFlag();
            this.enableRightFlag();
            this.playerStoped = false;
            this.playerDirection = RIGHT_DIRECTION;
        } else if (keyCode == 37) { //left
            this.disableRightFlag();
            this.enableLeftFlag();
            this.playerStoped = false;
            this.playerDirection = LEFT_DIRECTION;
        } else if (keyCode == 38) { //up
            this.enableTopFlag();
        } else if (keyCode == 40) { //down
            this.enableBottomFlag();
        } else if (keyCode == 32) { //space
            System.out.println(this.getPositionX());
            System.out.println(this.getCurrentLevel()); 
        }
    }

    public void release(int keyCode) {
        if (keyCode == 39) { //right
            this.disableRightFlag();
            //this.currentWalkState = 0;
        } else if (keyCode == 37) { //left
            this.disableLeftFlag();
            //this.currentWalkState = 0;
        } else if (keyCode == 38) { //up
            this.disableTopFlag();
        } else if (keyCode == 40) { //down
            this.disableBottomFlag();
        }

        if (!this.enableLeft && !this.enableRight) {
            this.playerStoped = true;
        }
    }

    public void firstUpdate(long frametime) {
    }
}