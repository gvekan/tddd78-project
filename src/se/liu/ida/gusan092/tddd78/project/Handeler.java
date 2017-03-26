package se.liu.ida.gusan092.tddd78.project;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;
import java.awt.Graphics;
import java.util.List;

public class Handeler
{
    private LinkedList<GameObject> gameObjects = new LinkedList<>();
    //en fori loop bör användas då objekt kan ändras undertiden

    public void tick() {
	for (int i = 0; i < gameObjects.size(); i++) {
	    final GameObject go = gameObjects.get(i);
	    go.tick();
	}
    }

    public void render(Graphics g) {
	for (int i = 0; i < gameObjects.size(); i++) {
	    final GameObject go = gameObjects.get(i);
	    go.render(g);
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
	    final GameObject go = gameObjects.get(i);
	    if (go.getIdentity() == identity) {
		return go;
	    }
	}
	return null;
    }

    public boolean willHaveCollision(GameObject go) {
	Rectangle r = go.getBounds();
	for (int i = 0; i < gameObjects.size(); i++) {
	    final GameObject test = gameObjects.get(i);
	    if (r.intersects(test.getBounds())) {
		return true;
	    }
	}
	return false;
    }

    public boolean hasCollision(Rectangle r, Identity identity) {
	for (int i = 0; i < gameObjects.size(); i++) {
	    final GameObject test = gameObjects.get(i);
	    if (test.getIdentity() != identity && r.intersects(test.getBounds())) {
		return true;
	    }
	}
	return false;
    }

    public boolean hasCollision(GameObject go) {
        Rectangle r = go.getBounds();
        Identity identity = go.getIdentity();
	for (int i = 0; i < gameObjects.size(); i++) {
	    final GameObject test = gameObjects.get(i);
	    if (test.getIdentity() != identity && r.intersects(test.getBounds())) {
		return true;
	    }
	}
    	return false;
    }

    public List<GameObject> getCollisions(Rectangle r, Identity identity) {
        List<GameObject> collisions = new ArrayList<>();
	for (int i = 0; i < gameObjects.size(); i++) {
	    final GameObject test = gameObjects.get(i);
	    if (test.getIdentity() != identity && r.intersects(test.getBounds())) {
		collisions.add(test);
	    }
	}
    	return collisions;
    }

    public List<GameObject> getCollisions(GameObject go) {
        List<GameObject> collisions = new ArrayList<>();
        Rectangle r = go.getBounds();
        Identity identity = go.getIdentity();
	for (int i = 0; i < gameObjects.size(); i++) {
	    final GameObject test = gameObjects.get(i);
	    if (test.getIdentity() != identity && r.intersects(test.getBounds())) {
		collisions.add(test);
	    }
	}
    	return collisions;
    }

}
