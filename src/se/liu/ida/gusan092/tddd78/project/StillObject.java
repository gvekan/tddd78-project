package se.liu.ida.gusan092.tddd78.project;

public abstract class StillObject extends GameObject
{
    private boolean halfTick = true;

    protected StillObject(final int x, final int y, final int width, final int height, final Identity identity,
			  final Handler handler)
    {
	super(x, y, width, height, identity, handler);
	setVelY(1);
    }

    @Override public void tick() {
        if (y > Game.HEIGHT) {
            handler.removeGameObjects(this);
	}
        if (halfTick) {
	    x += velX;
	    y += velY;
	}
	halfTick = !halfTick;
    }
}
