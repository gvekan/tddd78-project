package se.liu.ida.gusan092.tddd78.project.game;

import se.liu.ida.gusan092.tddd78.project.game.objects.GameObject;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.awt.Graphics;
import java.util.List;
import java.util.Objects;

public class Handler
{
    private List<GameObject> gameObjects = new ArrayList<>(1000);
    //en fori loop bör användas då objekt kan ändras undertiden

    public void tick() {
	for (int i = 0; i < gameObjects.size(); i++) {
	    final GameObject gameObject = gameObjects.get(i);
	    if (gameObject != null) {
		gameObject.tick();
	    }
	}
    }

/*    public void maxTick() {
    	for (int i = 0; i < gameObjects.size(); i++) {
    	    final GameObject gameObject = gameObjects.get(i);
    	    gameObject.maxTick();
    	}
        }*/

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

    public void remove(GameObject gameObject) {
        gameObjects.remove(gameObject);
    }

    public boolean hasCollision(final Rectangle rectangle, final GameObject gameObject) {
	for (int i = 0; i < gameObjects.size(); i++) {
	    final GameObject test = gameObjects.get(i);
	    if (!Objects.equals(test, gameObject) && test.hasCollision(rectangle)) {
		return true;
	    }
	}
	return false;
    }

    public boolean hasCollision(final GameObject gameObject) {
        Rectangle rectangle = gameObject.getBounds();
	return hasCollision(rectangle,gameObject);
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

    public List<GameObject> getCollisions(final GameObject gameObject) {
        Rectangle rectangle = gameObject.getBounds();
	return getCollisions(rectangle, gameObject);
    }

}
