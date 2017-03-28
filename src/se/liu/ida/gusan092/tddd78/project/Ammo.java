package se.liu.ida.gusan092.tddd78.project;

import java.awt.*;

public class Ammo extends PowerUp
{
    private int ammo = 10;

    protected Ammo(final int x, final Handler handler) {
	super(x, handler, Color.RED);
    }

    @Override public void use() {

    }

    @Override public void collision(final GameObject collision, final Side side) {

    }

    @Override public void maxTick() {

    }
}
