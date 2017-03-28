package se.liu.ida.gusan092.tddd78.project.game.objects;

import se.liu.ida.gusan092.tddd78.project.game.Game;
import se.liu.ida.gusan092.tddd78.project.game.Handler;

public interface CollisionHandler
{
    public void collision(Game game, Handler handler, GameObject gameObject, GameObject collision, Side side);
}
