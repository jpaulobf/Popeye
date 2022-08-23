package game.stages;

import java.awt.Graphics2D;

public interface StageInterface {

    public Graphics2D getG2D();

    public void move(int keyCode);

    public void releaseKey(int keyCode);
    
}
