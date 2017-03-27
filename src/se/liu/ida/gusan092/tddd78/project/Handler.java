package se.liu.ida.gusan092.tddd78.project;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;
import java.awt.Graphics;
import java.util.List;

public class Handler
{
    private List<GameObject> gameObjects = new ArrayList<>();
    //en fori loop bör användas då objekt kan ändras undertiden

    public void tick() {
	for (int i = 0; i < gameObjects.size(); i++) {
	    final GameObject gameObject = gameObjects.get(i);
	    gameObject.tick();
	}
    }

    public void render(Graphics g) {
	for (int i = 0; i < gameObjects.size(); i++) {
	    final GameObject gameObject = gameObjects.get(i);
	    gameObject.render(g);
	}
    }

    public void addGameObject(GameObject gameObject) {
        gameObjects.add(gameObject);
    }

    public void removeGameObjects(GameObject gameObject) {
        gameObjects.remove(gameObject);
    }


    /**
     * @param identity
     *
     * @return first GameObject with the Identity
     */
    public GameObject getGameObjectById(Identity identity) {
	for (int i = 0; i < gameObjects.size(); i++) {
	    final GameObject gameObject = gameObjects.get(i);
	    if (gameObject.getIdentity() == identity) {
		return gameObject;
	    }
	}
	return null;
    }

    public boolean willHaveCollision(GameObject gameObject) {
	Rectangle rectangle = gameObject.getBounds();
	for (int i = 0; i < gameObjects.size(); i++) {
	    final GameObject test = gameObjects.get(i);
	    if (test.hasCollision(rectangle)) {
		return true;
	    }
	}
	return false;
    }

    public boolean hasCollision(Rectangle rectangle, Identity identity) {
	for (int i = 0; i < gameObjects.size(); i++) {
	    final GameObject test = gameObjects.get(i);
	    if (test.getIdentity() != identity && test.hasCollision(rectangle)) {
		return true;
	    }
	}
	return false;
    }

    public boolean hasCollision(GameObject gameObject) {
        Rectangle rectangle = gameObject.getBounds();
        Identity identity = gameObject.getIdentity();
	for (int i = 0; i < gameObjects.size(); i++) {
	    final GameObject test = gameObjects.get(i);
	    if (test.getIdentity() != identity && test.hasCollision(rectangle)) {
		return true;
	    }
	}
    	return false;
    }

    public List<GameObject> getCollisions(Rectangle rectangle, Identity identity) {
        List<GameObject> collisions = new ArrayList<>();
	for (int i = 0; i < gameObjects.size(); i++) {
	    final GameObject test = gameObjects.get(i);
	    if (test.getIdentity() != identity && test.hasCollision(rectangle)) {
		collisions.add(test);
	    }
	}
    	return collisions;
    }

    public List<GameObject> getCollisions(Rectangle rectangle, List<Identity> identities) {
        List<GameObject> collisions = new ArrayList<>();
	for (int i = 0; i < gameObjects.size(); i++) {
	    final GameObject test = gameObjects.get(i);
	    if (!identities.contains(test.getIdentity()) && test.hasCollision(rectangle)) {
		collisions.add(test);
	    }
	}
    	return collisions;
    }

    public List<GameObject> getCollisions(GameObject gameObject) {
        List<GameObject> collisions = new ArrayList<>();
        Rectangle rectangle = gameObject.getBounds();
        Identity identity = gameObject.getIdentity();
	for (int i = 0; i < gameObjects.size(); i++) {
	    final GameObject test = gameObjects.get(i);
	    if (test.getIdentity() != identity && test.hasCollision(rectangle)) {
		collisions.add(test);
	    }
	}
    	return collisions;
    }

}
