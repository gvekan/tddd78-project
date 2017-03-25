package se.liu.ida.gusan092.tddd78.project;

import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Deque;
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
     * @param id
     *
     * @return first GameObject with the Id
     */
    public GameObject getGameObjectById(Id id) {
	for (int i = 0; i < gameObjects.size(); i++) {
	    final GameObject go = gameObjects.get(i);
	    if (go.getId() == id) {
		return go;
	    }
	}
	return null;
    }

    public boolean hasCollision(GameObject go) {
        Rectangle r = go.getBounds();
        Id id = go.getId();
	for (int i = 0; i < gameObjects.size(); i++) {
	    final GameObject test = gameObjects.get(i);
	    if (test.getId() != id && r.intersects(test.getBounds())) {
		return true;
	    }
	}
    	return false;
    }

    public List<GameObject> getCollisions(GameObject go) {
        List<GameObject> collisions = new ArrayList<>();
        Rectangle r = go.getBounds();
        Id id = go.getId();
	for (int i = 0; i < gameObjects.size(); i++) {
	    final GameObject test = gameObjects.get(i);
	    if (test.getId() != id && r.intersects(test.getBounds())) {
		collisions.add(test);
	    }
	}
    	return collisions;
    }

}
