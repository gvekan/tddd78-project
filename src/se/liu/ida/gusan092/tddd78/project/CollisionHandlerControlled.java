package se.liu.ida.gusan092.tddd78.project;

public interface CollisionHandlerControlled
{
    public void collision(Game game, Handler handler, ControlledObject controlledObject, GameObject collision, Side side);
    public void collisionControlled(Game game, Handler handler, ControlledObject controlledObject, GameObject collision, Side side);
}
