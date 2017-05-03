package se.liu.ida.gusan092.tddd78.project.game;


import se.liu.ida.gusan092.tddd78.project.game.objects.still.Animal;
import se.liu.ida.gusan092.tddd78.project.game.objects.still.Container;
import se.liu.ida.gusan092.tddd78.project.game.objects.still.Road;
import se.liu.ida.gusan092.tddd78.project.game.objects.still.Roadblock;
import se.liu.ida.gusan092.tddd78.project.game.powerup.PowerUpId;
import se.liu.ida.gusan092.tddd78.project.properties.SavedProperties;

import java.util.Random;

/**
 * Creates gameobjects for the handlers
 */
public class Spawner
{
    /**
     * Creates a overlap between roads
     */
    public static final int OVERLAP = 18;
    /**
     * The default value of the roadCounter
     */
    public static final int ROAD_START = Game.HEIGHT * 2 - OVERLAP;
    /**
     * Representing the counters minimum amount for level 3
     */
    public static final int LEVEL_3 = 4000;
    /**
     * Representing the counters minimum amount for level 2
     */
    public static final int LEVEL_2 = 2000;
    /**
     * Representing the odds for power ups in level 1
     */
    public static final int L1_PU_ODDS = 20;
    /**
     * The default value of the spawnCounter
     */
    public static final int SPAWN = 60;
    private Handler handler;
    private Handler environment;
    private int counter = 0;
    private int spawnCounter = 0;
    private int roadCounter = ROAD_START;
    private Random random = new Random();

    public Spawner(final Handler handler, final Handler environment) {
	this.handler = handler;
	this.environment = environment;
	environment.add(new Road(0, environment));
	environment.add(new Road(-Game.HEIGHT, environment));
    }

    public Spawner(final Handler handler, final Handler environment, final String saveValues) {
	this.handler = handler;
	this.environment = environment;
	String[] values = saveValues.split(SavedProperties.VALUE_SPLIT);
	this.counter = Integer.parseInt(values[0]);
	this.spawnCounter = Integer.parseInt(values[1]);
	this.roadCounter = Integer.parseInt(values[2]);
    }

    public void tick() {
	if (roadCounter == 0) {
	    roadCounter = ROAD_START;
	    environment.add(new Road(-Game.HEIGHT, environment));
	} else roadCounter--;
	counter++;
	if (spawnCounter == 0) {
	    if (counter > LEVEL_3) {
		level3();
	    } else if (counter > LEVEL_2) {
		level2();
	    } else {
	        level1();
	    }
	    spawnCounter = SPAWN;
	} else spawnCounter--;
    }


    public String getSaveValues() {
        return Integer.toString(counter) + SavedProperties.VALUE_SPLIT + Integer.toString(spawnCounter) + SavedProperties.VALUE_SPLIT + Integer.toString(roadCounter);
    }

    private boolean oneTo(int n) {
        return random.nextInt(n) == 0;
    }

    private void level1() {
	handler.add(new Container(random.nextInt(Game.WIDTH - Container.SIZE), handler, PowerUpId.GHOST));
	if (oneTo(2)) {
	    handler.add(new Roadblock(random.nextInt(Game.WIDTH - Roadblock.WIDTH), handler));
	}
	if (oneTo(L1_PU_ODDS)) handler.add(new Container(random.nextInt(Game.WIDTH - Container.SIZE), handler, PowerUpId.AMMO));
    }

    private void level2() {
	if (oneTo(2)) {
	    handler.add(new Roadblock(random.nextInt(Game.WIDTH - Roadblock.WIDTH + 1), handler));
	}
	if (oneTo(4)) handler.add(new Animal(random.nextInt(Game.WIDTH), handler));
	if (oneTo(L1_PU_ODDS)) {
	    switch (random.nextInt(2)) {
		case 0:
		    handler.add(new Container(random.nextInt(Game.WIDTH - Container.SIZE), handler, PowerUpId.AMMO));
		    break;
		case 1:
		    if (oneTo(2)) handler.add(
			    new Container(random.nextInt(Game.WIDTH - Container.SIZE), handler, PowerUpId.UNSTOPPABLE));
		    else handler.add(new Container(random.nextInt(Game.WIDTH - Container.SIZE), handler, PowerUpId.AMMO));
		    break;
	    }
	}
    }

    private void level3() {
	if (oneTo(3)) {
	    for (int i = 0; i < random.nextInt(5) + 1; i++) {
		handler.add(new Roadblock(random.nextInt(Game.WIDTH - Roadblock.WIDTH + 1), handler));
	    }
	} else if(oneTo(4)) {
	    switch (random.nextInt(3)) {
		case 0:
		    handler.add(new Roadblock(random.nextInt(Game.WIDTH - Roadblock.WIDTH + 1), handler));
		    break;
		case 1:
		    if (oneTo(2)) handler.add(new Container(random.nextInt(Game.WIDTH - Container.SIZE), handler, PowerUpId.AMMO));
		    else handler.add(new Roadblock(random.nextInt(Game.WIDTH - Roadblock.WIDTH + 1), handler));
		    break;
		case 2:
		    if (oneTo(4)) handler.add(new Container(random.nextInt(Game.WIDTH - Container.SIZE), handler, PowerUpId.UNSTOPPABLE));
		    else handler.add(new Animal(random.nextInt(Game.WIDTH), handler));
		    break;
	    }
	}
    }
}
