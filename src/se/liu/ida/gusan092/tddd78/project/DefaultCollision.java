package se.liu.ida.gusan092.tddd78.project;

public class DefaultCollision implements CollisionHandeler
{
    @Override public void collision(GameObject go) {
	switch (go.getId()) {
	    case PLAYER:
	    case ROADBLOCK:
	}
    }
}
