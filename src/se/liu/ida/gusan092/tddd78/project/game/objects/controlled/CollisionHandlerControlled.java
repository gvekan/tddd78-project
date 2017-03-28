package se.liu.ida.gusan092.tddd78.project.game.objects.controlled;

import se.liu.ida.gusan092.tddd78.project.game.Game;
import se.liu.ida.gusan092.tddd78.project.game.Handler;
import se.liu.ida.gusan092.tddd78.project.game.objects.GameObject;
import se.liu.ida.gusan092.tddd78.project.game.objects.Side;

public interface CollisionHandlerControlled
{
    public void collision(Game game, Handler handler, ControlledObject controlledObject, GameObject collision, Side side);
    public void collisionActivated(Game game, Handler handler, ControlledObject controlledObject, GameObject collision, Side side);
}
