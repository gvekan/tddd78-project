package se.liu.ida.gusan092.tddd78.project;

import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.awt.Graphics;

public class Handeler
{
    private LinkedList<GameObject> gameObjects = new LinkedList<>();

    public void tick() {
	for (GameObject go:
	     gameObjects) {
	    go.tick();
	}
    }

    public void render(Graphics g) {
	for (GameObject go:
	     gameObjects) {
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
	for (GameObject go:
	     gameObjects) {
	    if (go.getId() == id) {
	        return go;
	    }
	}
	return null;
    }


    public ArrayList<GameObject> getCollisions(GameObject go) {
        ArrayList<GameObject> collisions = new ArrayList<>();
        Rectangle r = go.getBounds();
        Id id = go.getId();
    	for (GameObject test:
    	     gameObjects) {
    	    if (test.getId() != id && r.intersects(test.getBounds())) {
    	        collisions.add(test);
    	    }
    	}
    	return collisions;
        }

}
