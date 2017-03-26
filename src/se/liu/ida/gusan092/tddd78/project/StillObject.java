package se.liu.ida.gusan092.tddd78.project;

public abstract class StillObject extends GameObject
{
    private boolean halfTick = true;

    protected StillObject(final int x, final int y, final int width, final int height, final Identity identity,
			  final Handeler handeler)
    {
	super(x, y, width, height, identity, handeler);
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
}
