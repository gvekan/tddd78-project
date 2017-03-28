package se.liu.ida.gusan092.tddd78.project;

public abstract class PowerUp extends StillObject
{
    protected boolean activated = false;

    protected PowerUp(final int x, final Handler handler)
    {
	super(x, -20, 20, 20, ObjectType.POWERUP, handler);
    }
    public abstract void activate(ControlledObject controlledObject);
}
