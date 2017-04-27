package se.liu.ida.gusan092.tddd78.project.game;


import se.liu.ida.gusan092.tddd78.project.game.objects.Type;
import se.liu.ida.gusan092.tddd78.project.game.objects.still.Container;
import se.liu.ida.gusan092.tddd78.project.game.objects.still.Roadblock;
import se.liu.ida.gusan092.tddd78.project.game.powerup.PowerUpId;

import java.util.Random;

public class Spawner
{
    private Handler handler;
    private Handler environment;
    private int counter = 0;
    private int spawnCounter = 0;
    private Random random = new Random();

    public Spawner(final Handler handler, final Handler environment) {
	this.handler = handler;
	this.environment = environment;
    }

    public void tick() {
	counter++;
	if (spawnCounter == 0) {
	    if (counter > 3000) {
	        if (oneTo(3)) {
		    for (int i = 0; i < random.nextInt(5) + 1; i++) {
			handler.add(new Roadblock(random.nextInt(Game.WIDTH - Roadblock.WIDTH + 1), Type.ROADBLOCK, handler));
		    }
		} else if(oneTo(4)) {
		    switch (random.nextInt(3)) {
			case 0:
			    handler.add(new Roadblock(random.nextInt(Game.WIDTH - Roadblock.WIDTH + 1), Type.ROADBLOCK, handler));
			    break;
			case 1:
			    if (oneTo(2)) handler.add(new Container(random.nextInt(Game.WIDTH - Container.DIAMETER), handler, PowerUpId.AMMO));
			    else handler.add(new Roadblock(random.nextInt(Game.WIDTH - Roadblock.WIDTH + 1), Type.ROADBLOCK, handler));
			    break;
			case 2:
			    if (oneTo(4)) handler.add(new Container(random.nextInt(Game.WIDTH - Container.DIAMETER), handler, PowerUpId.UNSTOPPABLE));
			    else handler.add(new Container(random.nextInt(Game.WIDTH - Container.DIAMETER), handler, PowerUpId.AMMO));
			    break;
		    }
		}
	    } else if (counter > 2000) {
		if (oneTo(2)) {
		    handler.add(new Roadblock(random.nextInt(Game.WIDTH - Roadblock.WIDTH + 1), Type.ROADBLOCK, handler));
		}
		if (oneTo(20)) {
		    switch (random.nextInt(2)) {
			case 0:
			    handler.add(new Container(random.nextInt(Game.WIDTH - Container.DIAMETER), handler, PowerUpId.AMMO));
			    break;
			case 1:
			    if (oneTo(2)) handler.add(new Container(random.nextInt(Game.WIDTH - Container.DIAMETER), handler, PowerUpId.UNSTOPPABLE));
			    else handler.add(new Container(random.nextInt(Game.WIDTH - Container.DIAMETER), handler, PowerUpId.AMMO));
			    break;
		    }
		}
	    } else {
		if (oneTo(2)) {
		    handler.add(new Roadblock(random.nextInt(Game.WIDTH - Roadblock.WIDTH), Type.ROADBLOCK, handler));
		}
		if (oneTo(20)) handler.add(new Container(random.nextInt(Game.WIDTH - Container.DIAMETER), handler, PowerUpId.AMMO));
	    }
	    spawnCounter = 60;
	}
	spawnCounter--;
    }

    private boolean oneTo(int n) {
        return random.nextInt(n) == 0;
    }
}
