package se.liu.ida.gusan092.tddd78.project.game;

import se.liu.ida.gusan092.tddd78.project.game.objects.GameObject;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.awt.Graphics;
import java.util.List;
import java.util.Objects;

/**
 * A object containing all gameobjects in a game
 */
public class Handler
{
    private List<GameObject> gameObjects = new ArrayList<>(1000);
    private List<GameObject> objectsToRemove = new ArrayList<>();
    //en fori loop bör användas då objekt kan ändras undertiden

    public void tick() {
	for (int i = 0; i < gameObjects.size(); i++) {
	    final GameObject gameObject = gameObjects.get(i);
	    if (gameObject != null) {
		gameObject.tick();
	    }
	}
	int size = objectsToRemove.size();
	for (int i = size-1; i >= 0 ; i--) {
	    final GameObject gameObject = objectsToRemove.get(i);
	    gameObjects.remove(gameObject);
	}
	objectsToRemove.clear();
    }

    public void render(final Graphics g) {
	for (int i = 0; i < gameObjects.size(); i++) {
	    final GameObject gameObject = gameObjects.get(i);
	    if (gameObject != null) {
		gameObject.render(g);
	    }
	}
    }

    public void add(GameObject gameObject) {
        gameObjects.add(gameObject);
    }

    public void removeAfterTick(GameObject gameObject) {
        objectsToRemove.add(gameObject);
    }

    public List<GameObject> getCollisions(final Rectangle rectangle, final GameObject gameObject) {
        List<GameObject> collisions = new ArrayList<>();
	for (int i = 0; i < gameObjects.size(); i++) {
	    final GameObject test = gameObjects.get(i);
	    if (test != null) {
		if (!Objects.equals(test, gameObject) && test.hasCollision(rectangle)) {
		    collisions.add(test);
		}
	    }
	}
    	return collisions;
    }

    public void setRunning(boolean running) {
	for (int i = 0; i < gameObjects.size(); i++) {
	    final GameObject gameObject = gameObjects.get(i);
	    if (gameObject != null) {
		gameObject.setRunning(running);
	    }
	}
    }

    public List<GameObject> getGameObjects() {
	return gameObjects;
    }
}
