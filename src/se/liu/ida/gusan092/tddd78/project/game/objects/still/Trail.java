package se.liu.ida.gusan092.tddd78.project.game.objects.still;

import se.liu.ida.gusan092.tddd78.project.game.Handler;
import se.liu.ida.gusan092.tddd78.project.game.objects.GameObject;
import se.liu.ida.gusan092.tddd78.project.game.objects.Side;
import se.liu.ida.gusan092.tddd78.project.game.objects.Type;
import se.liu.ida.gusan092.tddd78.project.game.objects.controlled.ControlledObject;

import java.awt.*;

public class Trail extends StillObject
{
    private ControlledObject controlledObject;
    private long timer = System.currentTimeMillis();

    //private Color color;

    private float alpha = 1;

    public Trail(final int x, final int y, final int width, final int height, final Handler handler,
		 final ControlledObject controlledObject)
    {
	super(x, y, width, height, Type.TRAIL, handler);
	this.controlledObject = controlledObject;
	//setX(controlledObject.getX());
	//setY(controlledObject.getY());
	setWidth(controlledObject.getWidth());
	//setHeight(controlledObject.getHeight());
	setVelY(5);
	alpha -= 0.5;
    }

    @Override public void collisionAsGameObject(final GameObject collision, final Side side) {

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

	g2d.setColor(controlledObject.getColor());
	//g2d.setColor(color);
	//g2d.fillRect(x+width/5,y,width/5,height);
	//g2d.fillRect(x+(width/5)*3,y,width/5,height);
	g2d.fillRect(x,y,width,height);

	g2d.setComposite(makeTransparent(1));
    }

    private AlphaComposite makeTransparent(float alpha) {
        int type = AlphaComposite.SRC_OVER;
        return (AlphaComposite.getInstance(type,alpha));
    }
}
