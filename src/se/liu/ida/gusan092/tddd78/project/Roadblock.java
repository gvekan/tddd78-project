package se.liu.ida.gusan092.tddd78.project;


import java.awt.Graphics2D;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.*;
import java.util.List;

public class Roadblock extends GameObject
{

    public static final int WIDTH = 25;
    public static final int HEIGHT = 5;
    private boolean halfTick = true;

    protected Roadblock(final int x, final Id id, Handeler handeler) {
	super(x, -HEIGHT, id, handeler);
	setVelY(1);
    }

    @Override public void tick() {
        if (y > Game.HEIGHT) {
            handeler.removeGameObjects(this);
	}
        if (halfTick) {
	    x += velX;
	    y += velY;
	}
	halfTick = !halfTick;
    }

    @Override public void render(final Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.WHITE);
        g2d.fill(getBounds());
    }

    @Override public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }
}
