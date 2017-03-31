package se.liu.ida.gusan092.tddd78.project.game.objects.controlled;

import se.liu.ida.gusan092.tddd78.project.game.Game;
import se.liu.ida.gusan092.tddd78.project.game.Handler;
import se.liu.ida.gusan092.tddd78.project.game.objects.GameObject;
import se.liu.ida.gusan092.tddd78.project.game.objects.Side;

public interface CollisionHandlerControlled
{
    public void collision(final Game game, final Handler handler, final ControlledObject controlledObject, final GameObject collision, final Side side);
    public void collisionWithControlled(final Game game, final Handler handler, final ControlledObject controlledObject, final ControlledObject collision, final Side side);
}
