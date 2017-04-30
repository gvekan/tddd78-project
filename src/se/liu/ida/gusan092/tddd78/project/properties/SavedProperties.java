package se.liu.ida.gusan092.tddd78.project.properties;

import se.liu.ida.gusan092.tddd78.project.game.Game;
import se.liu.ida.gusan092.tddd78.project.game.Handler;
import se.liu.ida.gusan092.tddd78.project.game.Spawner;
import se.liu.ida.gusan092.tddd78.project.game.objects.GameObject;
import se.liu.ida.gusan092.tddd78.project.game.objects.Type;
import se.liu.ida.gusan092.tddd78.project.game.objects.Player;
import se.liu.ida.gusan092.tddd78.project.game.powerup.PowerUp;

import java.util.List;

public class SavedProperties extends AppProperties
{
    private static final SavedProperties INSTANCE = new SavedProperties("");
    private final static String GAME_KEY = "game";
    private final static String HANDLER_KEY = "handler";
    private final static String H_OBJECT_KEY = "h";
    private final static String ENVIRONMENT_KEY = "environment";
    private final static String E_OBJECT_KEY = "e";
    private final static String SPAWNER_KEY = "spawner";
    private final static String POWER_UPS_KEY = "power_ups";
    private final static String POWER_UP_KEY = "p";
    public final static String VALUE_SPLIT = ",";
    public final static String ENUM_SPLIT = ":";


    private SavedProperties(final String start) {
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
	recursivePowerUp(powerUps, "");
    }

    private void recursivePowerUp(final List<PowerUp> powerUps, final String key) {
	int size = powerUps.size();
	prop.setProperty(POWER_UPS_KEY + key, Integer.toString(size));
	for (int i = 0; i < powerUps.size(); i++) {
	    String newKey = key + Integer.toString(i);
	    final PowerUp powerUp = powerUps.get(i);
	    prop.setProperty(POWER_UP_KEY + newKey, powerUp.getSaveValues());
	    List<PowerUp> interrupted = powerUp.getInterrupted();
	    if (!interrupted.isEmpty()) {
		recursivePowerUp(interrupted, newKey);
	    }
	}
    }

    public Game getGame() {
       if (hasGame()) {
            Player player;
            Handler handler = new Handler();
            String size = prop.getProperty(HANDLER_KEY);
	    for (int i = 0; i < Integer.parseInt(size); i++) {
		String gameObject = prop.getProperty(H_OBJECT_KEY + Integer.toString(i));
		String[] values = gameObject.split(ENUM_SPLIT);
		switch (Type.values()[Integer.parseInt(values[0])]) {
		    case PLAYER:

		}
	    }
	    Handler environment = new Handler();

	    Spawner spawner = new Spawner(handler, environment);
	}
	return null;
    }
}
