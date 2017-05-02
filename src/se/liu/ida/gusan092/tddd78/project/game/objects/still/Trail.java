package se.liu.ida.gusan092.tddd78.project.game.objects.still;

import se.liu.ida.gusan092.tddd78.project.game.Handler;
import se.liu.ida.gusan092.tddd78.project.game.objects.Type;

import java.awt.*;

/**
 * A still object for the environment handler used by player to display a trail
 */
public class Trail extends StillObject
{
    /**
     * The start alpha for the opacity
     */
    public static final float START_ALPHA = 0.5f;


    /**
     * The reduction value for the opacity
     */
    public static final float REDUCTION = 0.03f;


    /**
     * The min alpha for the opacity
     */
    public static final float MIN_ALPHA = 0.02f;

    /**
     * The current alpha
     */
    private float alpha = START_ALPHA;

    /**
     * @param x the start x value (of the player)
     * @param y the start y value (end of the player)
     * @param width the width (of the player)
     * @param height the height (of the player)
     * @param color the color (of the player)
     * @param handler the environment handler in the game
     */
    public Trail(final int x, final int y, final int width, final int height, final Color color, final Handler handler)
    {
	super(x, y, width, height, color, Type.TRAIL, handler);
	setVelY(5);
    }

    @Override public void tick() {
	super.tick();
	if (alpha < MIN_ALPHA) {
	    handler.removeAfterTick(this);
	}
	alpha-= REDUCTION;
    }

    @Override public void render(final Graphics g) {
	Graphics2D g2d = (Graphics2D) g;

	g2d.setComposite(makeTransparent(alpha));

	super.render(g);

	g2d.setComposite(makeTransparent(1));
    }

    /**
     * @param alpha the current alpha
     *
     * @return the AlphaComposite to set the graphics
     */
    private AlphaComposite makeTransparent(final float alpha) {
        int type = AlphaComposite.SRC_OVER;
        return (AlphaComposite.getInstance(type,alpha));
    }

    @Override public void setStillSaveValues(final String[] saveValues) {

    }
}
