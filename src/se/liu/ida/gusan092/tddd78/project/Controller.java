package se.liu.ida.gusan092.tddd78.project;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.EnumMap;
import java.util.HashMap;

public class Controller extends KeyAdapter
{
    private GameObject gameObject;
    private EnumMap<KeyAction,Integer> keys = new EnumMap<>(KeyAction.class);
    private EnumMap<KeyAction,Boolean> pressed = new EnumMap<>(KeyAction.class);

    public Controller(final GameObject gameObject) {
	this.gameObject = gameObject;
	//this.keys = keys;


	keys.put(KeyAction.MOVE_UP, KeyEvent.VK_W);
	keys.put(KeyAction.MOVE_DOWN, KeyEvent.VK_S);
	keys.put(KeyAction.MOVE_LEFT, KeyEvent.VK_A);
	keys.put(KeyAction.MOVE_RIGHT, KeyEvent.VK_D);
	for (KeyAction keyAction:
	     keys.keySet()) {
	    pressed.put(keyAction,false);
	}

    }

    @Override public void keyPressed(final KeyEvent e) {
	int key = e.getKeyCode();

	if (key == keys.get(KeyAction.MOVE_UP) && !pressed.get(KeyAction.MOVE_UP)) {
	    pressed.put(KeyAction.MOVE_UP, true);
	    moveUp();
	} else if (key == keys.get(KeyAction.MOVE_DOWN) && !pressed.get(KeyAction.MOVE_DOWN)) {
	    pressed.put(KeyAction.MOVE_DOWN, true);
	    moveDown();
	} else if (key == keys.get(KeyAction.MOVE_LEFT) && !pressed.get(KeyAction.MOVE_LEFT)) {
	    pressed.put(KeyAction.MOVE_LEFT, true);
	    moveLeft();
	} else if (key == keys.get(KeyAction.MOVE_RIGHT) && !pressed.get(KeyAction.MOVE_RIGHT)) {
	    pressed.put(KeyAction.MOVE_RIGHT, true);
	    moveRight();
	}
    }

    @Override public void keyReleased(final KeyEvent e) {
	int key = e.getKeyCode();
	if (key == keys.get(KeyAction.MOVE_UP)) {
	    pressed.put(KeyAction.MOVE_UP, false);
	    moveDown();
	} else if (key == keys.get(KeyAction.MOVE_DOWN)) {
	    pressed.put(KeyAction.MOVE_DOWN, false);
	    moveUp();
	} else if (key == keys.get(KeyAction.MOVE_LEFT)) {
	    pressed.put(KeyAction.MOVE_LEFT, false);
	    moveRight();
	} else if (key == keys.get(KeyAction.MOVE_RIGHT)) {
	    pressed.put(KeyAction.MOVE_RIGHT, false);
	    moveLeft();
	}
    }

    private void moveUp() {
	gameObject.setVelY(gameObject.getVelY()-1);
    }

    private void moveDown() {
	gameObject.setVelY(gameObject.getVelY()+1);
    }

    private void moveLeft() {
	gameObject.setVelX(gameObject.getVelX()-1);
    }

    private void moveRight() {
	gameObject.setVelX(gameObject.getVelX()+1);
    }
}
