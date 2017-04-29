package se.liu.ida.gusan092.tddd78.project.game.objects.collision;

import se.liu.ida.gusan092.tddd78.project.game.Game;
import se.liu.ida.gusan092.tddd78.project.game.Handler;
import se.liu.ida.gusan092.tddd78.project.game.objects.GameObject;
import se.liu.ida.gusan092.tddd78.project.game.objects.Player;
import se.liu.ida.gusan092.tddd78.project.game.objects.Side;

public interface CollisionHandler
{
    public void collision(final Game game, final Handler handler, final Player player, final GameObject collision, final Side side);
    public void collisionWithPlayer(final Game game, final Handler handler, final Player player, final Player collision, final Side side);
}
