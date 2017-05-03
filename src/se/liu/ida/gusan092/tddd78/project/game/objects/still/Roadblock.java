package se.liu.ida.gusan092.tddd78.project.game.objects.still;


import se.liu.ida.gusan092.tddd78.project.game.Handler;
import se.liu.ida.gusan092.tddd78.project.game.objects.Type;

import java.awt.*;


/**
 * A still obstacle that is removed after collision with player
 */
public class Roadblock extends StillObject
{
    /**
     * The width of a roadblock
     */
    public static final int WIDTH = 25;

    /**
     * The height of a roadblock
     */
    public static final int HEIGHT = 5;

    /**
     * The color of a roadblock
     */
    public static final Color COLOR = new Color(255,69,0);

    public Roadblock(final int x, final Handler handler) {
    	super(x, -HEIGHT, WIDTH, HEIGHT, COLOR, Type.ROADBLOCK, handler);
    }

    /**
     * Used to restore a saved game
     */
    public Roadblock(final Handler handler, final String saveValues) {
	super(WIDTH,HEIGHT,COLOR, Type.ROADBLOCK, handler);
	restoreSaveValues(saveValues);
    }


    /**
     * Not used because nothing has to be saved
     */
    @Override public void setStillSaveValues(final String[] saveValues) {

    }
}
