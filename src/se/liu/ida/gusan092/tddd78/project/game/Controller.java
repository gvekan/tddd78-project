package se.liu.ida.gusan092.tddd78.project.game;

import se.liu.ida.gusan092.tddd78.project.game.objects.Player;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.EnumMap;

/**
 * The keyadapter for a player
 */
public class Controller extends KeyAdapter
{
    private Player player;
    private EnumMap<KeyAction,Integer> keys = new EnumMap<>(KeyAction.class); //kan ersätrtas med hashmap med <Integer,KeyAction> samt switchar i keyPressed/Released
    private EnumMap<KeyAction,Boolean> pressed = new EnumMap<>(KeyAction.class);

    public Controller(final Player player) {
	this.player = player;


	keys.put(KeyAction.MOVE_UP, KeyEvent.VK_UP);
	keys.put(KeyAction.MOVE_DOWN, KeyEvent.VK_DOWN);
	keys.put(KeyAction.MOVE_LEFT, KeyEvent.VK_LEFT);
	keys.put(KeyAction.MOVE_RIGHT, KeyEvent.VK_RIGHT);
	keys.put(KeyAction.USE_POWERUPS, KeyEvent.VK_SPACE);
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
	} else if (key == keys.get(KeyAction.USE_POWERUPS) && !pressed.get(KeyAction.USE_POWERUPS)) {
	    pressed.put(KeyAction.USE_POWERUPS, true);
	    player.usePowerUps();
	}
    }

    @Override public void keyReleased(final KeyEvent e) {
	int key = e.getKeyCode();
	if (key == keys.get(KeyAction.MOVE_UP)) {
	    pressed.put(KeyAction.MOVE_UP, false);
	    int velY = player.getVelY();
	    if (velY < 0 || (pressed.get(KeyAction.MOVE_DOWN) && velY == 0)) {
		moveDown();
	    }
	} else if (key == keys.get(KeyAction.MOVE_DOWN)) {
	    pressed.put(KeyAction.MOVE_DOWN, false);
	    int velY = player.getVelY();
	    if (velY > 0 || (pressed.get(KeyAction.MOVE_UP) && velY == 0)) {
		moveUp();
	    }
	} else if (key == keys.get(KeyAction.MOVE_LEFT)) {
	    pressed.put(KeyAction.MOVE_LEFT, false);
	    int velX = player.getVelX();
	    if (velX < 0 || (pressed.get(KeyAction.MOVE_RIGHT) && velX == 0)) {
		moveRight();
	    }
	} else if (key == keys.get(KeyAction.MOVE_RIGHT)) {
	    pressed.put(KeyAction.MOVE_RIGHT, false);
	    int velX = player.getVelX();
	    if (velX > 0 || (pressed.get(KeyAction.MOVE_LEFT) && velX == 0)) {
		moveLeft();
	    }
	} else if (key == keys.get(KeyAction.USE_POWERUPS)) {
	    pressed.put(KeyAction.USE_POWERUPS, false);
   	    player.stopPowerUps();
   	}
    }

    private void moveUp() {
	player.setVelY(player.getVelY() - 1);
    }

    private void moveDown() {
	player.setVelY(player.getVelY() + 1);
    }

    private void moveLeft() {
	player.setVelX(player.getVelX() - 1);
    }

    private void moveRight() {
	player.setVelX(player.getVelX() + 1);
    }
}
