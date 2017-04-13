package se.liu.ida.gusan092.tddd78.project.game.objects.still;

import se.liu.ida.gusan092.tddd78.project.game.Handler;
import se.liu.ida.gusan092.tddd78.project.game.objects.GameObject;
import se.liu.ida.gusan092.tddd78.project.game.objects.Side;
import se.liu.ida.gusan092.tddd78.project.game.objects.Type;

import java.awt.*;

public class Trail extends StillObject
{
    public static final float START_ALPHA = 0.5f;
    public static final float REDUCTION = 0.03f;
    public static final float MIN_ALPHA = 0.02f;
    private long timer = System.currentTimeMillis();

    private float alpha = START_ALPHA;

    public Trail(final int x, final int y, final int width, final int height, final Color color, final Handler handler)
    {
	super(x, y, width, height, color, Type.TRAIL, handler);
	setVelY(5);
    }

    @Override public void collisionWithGameObject(final GameObject collision, final Side side) {

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

    private AlphaComposite makeTransparent(final float alpha) {
        int type = AlphaComposite.SRC_OVER;
        return (AlphaComposite.getInstance(type,alpha));
    }
}
