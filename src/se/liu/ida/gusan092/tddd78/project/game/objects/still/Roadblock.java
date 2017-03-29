package se.liu.ida.gusan092.tddd78.project.game.objects.still;


import se.liu.ida.gusan092.tddd78.project.game.Handler;
import se.liu.ida.gusan092.tddd78.project.game.objects.GameObject;
import se.liu.ida.gusan092.tddd78.project.game.objects.Type;
import se.liu.ida.gusan092.tddd78.project.game.objects.Side;
import se.liu.ida.gusan092.tddd78.project.game.objects.controlled.ControlledObject;

import java.awt.Graphics2D;

import java.awt.*;

public class Roadblock extends StillObject
{
    public Roadblock(final int x, final Type type, Handler handler) {
	super(x, -5, 25, 5, type, handler);
    }

    @Override public void collisionAsGameObject(final GameObject collision, final Side side) {
        handler.remove(this);
    }

    @Override public void render(final Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.WHITE);
        g2d.fill(getBounds());
    }
}
