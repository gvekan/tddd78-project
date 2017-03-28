package se.liu.ida.gusan092.tddd78.project.game;

import se.liu.ida.gusan092.tddd78.project.game.objects.GameObject;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.awt.Graphics;
import java.util.Objects;

public class Handler
{
    private ArrayList<GameObject> controlledObjects = new ArrayList<>();
    //en fori loop bör användas då objekt kan ändras undertiden

    public void tick() {
	for (int i = 0; i < controlledObjects.size(); i++) {
	    final GameObject gameObject = controlledObjects.get(i);
	    gameObject.tick();
	}
    }

    public void maxTick() {
	for (int i = 0; i < controlledObjects.size(); i++) {
	    final GameObject gameObject = controlledObjects.get(i);
	    gameObject.maxTick();
	}
    }

    public void render(Graphics g) {
	for (int i = 0; i < controlledObjects.size(); i++) {
	    final GameObject gameObject = controlledObjects.get(i);
	    gameObject.render(g);
	}
    }

    public void add(GameObject gameObject) {
        controlledObjects.add(gameObject);
    }

    public void remove(GameObject gameObject) {
        controlledObjects.remove(gameObject);
    }

    public boolean hasCollision(Rectangle rectangle, GameObject gameObject) {
	for (int i = 0; i < controlledObjects.size(); i++) {
	    final GameObject test = controlledObjects.get(i);
	    if (!Objects.equals(test, gameObject) && test.hasCollision(rectangle)) {
		return true;
	    }
	}
	return false;
    }

    public boolean hasCollision(GameObject gameObject) {
        Rectangle rectangle = gameObject.getBounds();
	return hasCollision(rectangle,gameObject);
    }

    public ArrayList<GameObject> getCollisions(Rectangle rectangle, GameObject gameObject) {
        ArrayList<GameObject> collisions = new ArrayList<>();
	for (int i = 0; i < controlledObjects.size(); i++) {
	    final GameObject test = controlledObjects.get(i);
	    if (!Objects.equals(test, gameObject) && test.hasCollision(rectangle)) {
		collisions.add(test);
	    }
	}
    	return collisions;
    }

    public ArrayList<GameObject> getCollisions(GameObject gameObject) {
        Rectangle rectangle = gameObject.getBounds();
	return getCollisions(rectangle, gameObject);
    }

}
