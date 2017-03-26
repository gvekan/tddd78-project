package se.liu.ida.gusan092.tddd78.project;

public interface CollisionHandeler
{
    public void collisionFront(Game game, Handeler handeler ,GameObject go, GameObject collision);
    public void collisionBack(Game game, Handeler handeler ,GameObject go, GameObject collision);
    public void collisionLeft(Game game, Handeler handeler ,GameObject go, GameObject collision);
    public void collisionRight(Game game, Handeler handeler ,GameObject go, GameObject collision);
}
