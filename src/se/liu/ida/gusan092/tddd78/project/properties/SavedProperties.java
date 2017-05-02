package se.liu.ida.gusan092.tddd78.project.properties;

import se.liu.ida.gusan092.tddd78.project.game.Game;
import se.liu.ida.gusan092.tddd78.project.game.Handler;
import se.liu.ida.gusan092.tddd78.project.game.Spawner;
import se.liu.ida.gusan092.tddd78.project.game.objects.Bullet;
import se.liu.ida.gusan092.tddd78.project.game.objects.GameObject;
import se.liu.ida.gusan092.tddd78.project.game.objects.Type;
import se.liu.ida.gusan092.tddd78.project.game.objects.Player;
import se.liu.ida.gusan092.tddd78.project.game.objects.still.Animal;
import se.liu.ida.gusan092.tddd78.project.game.objects.still.Container;
import se.liu.ida.gusan092.tddd78.project.game.objects.still.Road;
import se.liu.ida.gusan092.tddd78.project.game.objects.still.Roadblock;
import se.liu.ida.gusan092.tddd78.project.game.powerup.Ammo;
import se.liu.ida.gusan092.tddd78.project.game.powerup.Ghost;
import se.liu.ida.gusan092.tddd78.project.game.powerup.PowerUp;
import se.liu.ida.gusan092.tddd78.project.game.powerup.PowerUpId;
import se.liu.ida.gusan092.tddd78.project.game.powerup.Unstoppable;
import se.liu.ida.gusan092.tddd78.project.gui.App;

import java.util.List;
import java.util.Properties;

/**
 * Used as a instance and handling the saved properties for a game
 */
public final class SavedProperties extends AppProperties
{
    private static final SavedProperties INSTANCE = new SavedProperties();
    private final static String GAME_KEY = "game";
    private final static String HANDLER_KEY = "handler";
    private final static String H_OBJECT_KEY = "h";
    private final static String ENVIRONMENT_KEY = "environment";
    private final static String E_OBJECT_KEY = "e";
    private final static String SPAWNER_KEY = "spawner";
    private final static String POWER_UPS_KEY = "power_ups";
    private final static String POWER_UP_KEY = "p";
    /**
     * The string used to distinguish values
     */
    public final static String VALUE_SPLIT = ",";
    /**
     * The string used to distinguish enum and value
     */
    public final static String ENUM_SPLIT = ":";


    private SavedProperties() {
	super("saved.properties");
    }

    public static SavedProperties getInstance() {
        return INSTANCE;
    }

    public boolean hasGame() {
        return !prop.isEmpty();
    }

    public void saveGame(final Game game) {
	prop.setProperty(GAME_KEY, game.getSaveValues());
	Handler handler = game.getHandler();
	List<GameObject> gameObjects = handler.getGameObjects();
	int size = gameObjects.size();
	prop.setProperty(HANDLER_KEY, Integer.toString(size));
	for (int i = 0; i < size; i++) {
	    final GameObject gameObject = gameObjects.get(i);
	    prop.setProperty(H_OBJECT_KEY + Integer.toString(i), gameObject.getSaveValues());
	}
	handler = game.getEnvironment();
	gameObjects = handler.getGameObjects();
	size = gameObjects.size();
	prop.setProperty(ENVIRONMENT_KEY, Integer.toString(size));
	for (int i = 0; i < size; i++) {
	    final GameObject gameObject = gameObjects.get(i);
	    prop.setProperty(E_OBJECT_KEY + Integer.toString(i), gameObject.getSaveValues());
	}
	Spawner spawner = game.getSpawner();
	prop.setProperty(SPAWNER_KEY, spawner.getSaveValues());
	write();
    }

    public void savePowerUps(final List<PowerUp> powerUps) {
	savePowerUpRecursive(powerUps, "");
    }

    private void savePowerUpRecursive(final List<PowerUp> powerUps, final String key) {
	int size = powerUps.size();
	prop.setProperty(POWER_UPS_KEY + key, Integer.toString(size));
	for (int i = 0; i < powerUps.size(); i++) {
	    String newKey = key + Integer.toString(i);
	    final PowerUp powerUp = powerUps.get(i);
	    prop.setProperty(POWER_UP_KEY + newKey, powerUp.getSaveValues());
	    List<PowerUp> interrupted = powerUp.getInterrupted();
	    if (!interrupted.isEmpty()) {
		savePowerUpRecursive(interrupted, newKey);
	    }
	}
    }

    public Game getGame(final App app) {
	if (hasGame()) {
	    Game game = new Game();
	    Player player = null;

	    Handler handler = new Handler();
	    int size = Integer.parseInt(prop.getProperty(HANDLER_KEY));
	    for (int i = 0; i < size; i++) {
		String gameObject = prop.getProperty(H_OBJECT_KEY + Integer.toString(i));
		String[] values = gameObject.split(ENUM_SPLIT);
		switch (Type.values()[Integer.parseInt(values[0])]) {
		    case PLAYER:
			player = new Player(handler, game, values[1]);
			handler.add(player);
			break;
		    case ANIMAL:
		        handler.add(new Animal(handler,values[1]));
		        break;
		    case ROADBLOCK:
		        handler.add(new Roadblock(handler, values[1]));
		        break;
		    case BULLET:
		        handler.add(new Bullet(handler,player,values[1]));
		        break;
		    case CONTAINER:
		        handler.add(new Container(handler, values[1]));
		        break;
		}
	    }

	    Handler environment = new Handler();
	    size = Integer.parseInt(prop.getProperty(ENVIRONMENT_KEY));
	    for (int i = 0; i < size; i++) {
		String gameObject = prop.getProperty(E_OBJECT_KEY + Integer.toString(i));
		String[] values = gameObject.split(ENUM_SPLIT);
		if (Type.values()[Integer.parseInt(values[0])] == Type.ROAD) environment.add(new Road(environment,values[1]));
	    }

	    size = Integer.parseInt(prop.getProperty(POWER_UPS_KEY));
	    for (int i = 0; i < size; i++) {
		String newKey = Integer.toString(i);
		if (prop.getProperty(POWER_UPS_KEY + newKey) != null) {
		    getPowerUpRecursive(newKey, player, handler);
		}
		String powerUpData = prop.getProperty(POWER_UP_KEY + newKey);
		String[] values = powerUpData.split(ENUM_SPLIT);
		PowerUp powerUp = null;
		switch (PowerUpId.values()[Integer.parseInt(values[0])]) {
		    case AMMO:
			powerUp = new Ammo(player, handler,values[1]);
			break;
		    case GHOST:
			powerUp = new Ghost(player, values[1]);
			break;
		    case UNSTOPPABLE:
			powerUp = new Unstoppable(player, values[1]);
			break;
		}
		powerUp.setRunning(false);
		powerUp.resume();
	    }

	    Spawner spawner = new Spawner(handler, environment, prop.getProperty(SPAWNER_KEY));

	    game.setSaveValues(app, handler, environment, spawner, player, prop.getProperty(GAME_KEY));
	    game.pause();
	    game.stopRender();
	    game.start();
	    prop = new Properties();
	    write();
	    return game;
	}
	return null;
    }

    private void getPowerUpRecursive(final String key, final Player player, final Handler handler) {
	int size = Integer.parseInt(prop.getProperty(POWER_UPS_KEY + key));
	for (int i = 0; i < size; i++) {
	    String newKey = key + Integer.toString(i);
	    if (prop.getProperty(POWER_UPS_KEY + newKey) != null) {
		getPowerUpRecursive(newKey, player, handler);
	    }
	    String powerUp = prop.getProperty(POWER_UP_KEY + newKey);
	    String[] values = powerUp.split(ENUM_SPLIT);
	    switch (PowerUpId.values()[Integer.parseInt(values[0])]) {
		case AMMO:
		    new Ammo(player, handler,values[1]);
		    break;
		case GHOST:
		    new Ghost(player, values[1]);
		    break;
		case UNSTOPPABLE:
		    new Unstoppable(player, values[1]);
		    break;
	    }
	}
    }
}
