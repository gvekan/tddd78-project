package se.liu.ida.gusan092.tddd78.project;

public class DefaultCollision implements CollisionHandeler
{
    @Override public void collision(Game game, Handeler handeler ,GameObject go, GameObject collision) {
	switch (collision.getId()) {
	    case ROADBLOCK:
	        handeler.removeGameObjects(collision);
	        game.collision(Id.ROADBLOCK);
	        break;
	}
    }
}
