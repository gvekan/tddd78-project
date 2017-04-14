package se.liu.ida.gusan092.tddd78.project.game.objects.still;

import se.liu.ida.gusan092.tddd78.project.game.Game;
import se.liu.ida.gusan092.tddd78.project.game.Handler;
import se.liu.ida.gusan092.tddd78.project.game.objects.GameObject;
import se.liu.ida.gusan092.tddd78.project.game.objects.Type;

import java.awt.Color;

public abstract class StillObject extends GameObject
{
    private boolean halfTick = true;

    protected StillObject(final int x, final int y, final int width, final int height, final Color color, final Type type,
			  final Handler handler)
    {
	super(x, y, width, height, color, type, handler);
	setVelY(1);
    }

    @Override public void tick() {
        if (y == Game.HEIGHT) {
            handler.removeAfterTick(this);
            return;
	}
        if (halfTick) {
	    x += velX;
	    y += velY;
	}
	halfTick = !halfTick;
    }
}
