package se.liu.ida.gusan092.tddd78.project;

public interface CollisionHandler
{
    public void collisionFront(Game game, Handler handler, GameObject gameObject, GameObject collision);
    public void collisionBack(Game game, Handler handler, GameObject gameObject, GameObject collision);
    public void collisionLeft(Game game, Handler handler, GameObject gameObject, GameObject collision);
    public void collisionRight(Game game, Handler handler, GameObject gameObject, GameObject collision);
}
