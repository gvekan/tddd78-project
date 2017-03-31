package se.liu.ida.gusan092.tddd78.project.game.objects.still;

import se.liu.ida.gusan092.tddd78.project.game.Handler;
import se.liu.ida.gusan092.tddd78.project.game.objects.GameObject;
import se.liu.ida.gusan092.tddd78.project.game.objects.Side;
import se.liu.ida.gusan092.tddd78.project.game.objects.Type;
import se.liu.ida.gusan092.tddd78.project.game.objects.controlled.ControlledObject;

import java.awt.*;

public class Trail extends StillObject
{
    private long timer = System.currentTimeMillis();

    private float alpha = 1;

    public Trail(final int x, final int y, final int width, final int height, final Color color, final Handler handler)
    {
	super(x, y, width, height, color, Type.TRAIL, handler);
	setVelY(5);
	alpha -= 0.5;
    }

    @Override public void collisionWithGameObject(final GameObject collision, final Side side) {

    }

    @Override public void tick() {
	super.tick();
	if (alpha < 0.02) {
	    handler.remove(this);
	}
	alpha-=0.03;
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
