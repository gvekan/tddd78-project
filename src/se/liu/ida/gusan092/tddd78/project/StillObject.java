package se.liu.ida.gusan092.tddd78.project;

public abstract class StillObject extends GameObject
{
    private boolean halfTick = true;

    protected StillObject(final int x, final int y, final int width, final int height, final ObjectType objectType,
			  final Handler handler)
    {
	super(x, y, width, height, objectType, handler);
	setVelY(1);
    }

    @Override public void tick() {
        if (y > Game.HEIGHT) {
            handler.remove(this);
	}
        if (halfTick) {
	    x += velX;
	    y += velY;
	}
	halfTick = !halfTick;
    }
}
