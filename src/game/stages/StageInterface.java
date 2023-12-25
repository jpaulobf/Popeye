package game.stages;

import java.awt.Graphics2D;

public interface StageInterface {

    public Graphics2D getG2D();
  
    public void update(long frametime);

    public void draw(long frametime);

    public void reset();

    public void firstUpdate(long frametime);
}
