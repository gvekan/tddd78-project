package se.liu.ida.gusan092.tddd78.project;


import java.awt.Graphics2D;

import java.awt.*;

public class Roadblock extends StillObject
{
    protected Roadblock(final int x, final ObjectType objectType, Handler handler) {
	super(x, -5, 25, 5, objectType, handler);
    }

    @Override public void collision(final GameObject collision, final Side side) {
        handler.remove(this);
    }

    @Override public void maxTick() {

    }

    @Override public void render(final Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.WHITE);
        g2d.fill(getBounds());
    }
}
