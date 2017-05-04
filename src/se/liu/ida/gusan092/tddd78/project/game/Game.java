package se.liu.ida.gusan092.tddd78.project.game;


import se.liu.ida.gusan092.tddd78.project.game.objects.Player;
import se.liu.ida.gusan092.tddd78.project.gui.App;
import se.liu.ida.gusan092.tddd78.project.properties.SavedProperties;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Graphics;

import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

/**
 * The game working as a engine and contains all objects relating the game
 */
public class Game extends Canvas implements Runnable
{

    /**
     * The width of the gameboard
     */
    public static final int WIDTH = 500;

    /**
     *The height of the gameboard
     */
    public static final int HEIGHT = 848;

    /**
     * The increase of amountOfTicks
     */
    public static final double INCREASE = 5;

    /**
     * Minimum ticks per second
     */
    public static final double MIN_AMOUNT_OF_TICKS = 60.0;
    /**
     * 1 second in nanoseconds
     */
    public static final int S_IN_NS = 1000000000;
    /**
     * Frames per second for render
     */
    public static final double FPS = 60;
    private double amountOfTicks = MIN_AMOUNT_OF_TICKS;
    private double ns = S_IN_NS / amountOfTicks;
    private double speedIncreaser = MIN_AMOUNT_OF_TICKS;

    private App app = null;

    private Thread thread = new Thread(this);
    private boolean threadRunning = false;

    private Boolean running = true;
    private Boolean render = true;
    private Handler handler = null;
    private Handler environment = null;
    private Spawner spawner = null;
    private Hud hud = null;

    private List<Player> players = new ArrayList<>();

    public Game(final App app) {
        this.app = app;
	handler = new Handler();
 	environment = new Handler();
 	spawner = new Spawner(handler, environment);
        Player player = new Player(handler, this);
        players.add(player);
	handler.add(player);
        addKeyListener(new Controller(player));
        hud = new Hud(players);
    }

    public Game() {

    }

    public void setSaveValues(final App app, final Handler handler, final Handler environment, final Spawner spawner,
			      final Player player, final String saveValues)
    {
        String[] values = saveValues.split(SavedProperties.VALUE_SPLIT);
	amountOfTicks = Double.valueOf(values[0]);
	ns = S_IN_NS / amountOfTicks;
	speedIncreaser = Double.valueOf(values[1]);
	this.app = app;
	this.handler = handler;
	this.environment = environment;
	this.spawner = spawner;
	players.add(player);
	addKeyListener(new Controller(player));
	hud = new Hud(players);
    }

    public void start() {
	threadRunning = true;
	thread.start();
    }

    public void stop() {
        try {
	    threadRunning = false;
            thread.join();
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
    }


    @Override public void run() {
        requestFocus();
        long lastTime = System.nanoTime();
        double delta = 0;
        double deltaRender = 0;
        double nsRender = S_IN_NS / FPS;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (threadRunning) { //Stopped
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
		if (running) tick();
		delta--;
	    }
	    if (now >= deltaRender) {
		if (threadRunning) {
		    if (render) render();
		}
		deltaRender = now + nsRender;
		frames++;
	    }

	    if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
		if (running) {
		    speedIncreaser += INCREASE;
		    if (speedIncreaser % 1 == 0) {
			amountOfTicks = speedIncreaser;
			ns = S_IN_NS / amountOfTicks;
			System.out.println("Amount of ticks: " + amountOfTicks);
		    }
		}
	    }
	}
    }

    private void tick() {
        spawner.tick();
	environment.tick();
	handler.tick();
	if (isGameOver()) gameOver();
    }


    private void render() {
	BufferStrategy bs = getBufferStrategy(); //Funkar inte om jag försöker använda jcomponent
	if (bs == null) {
	    createBufferStrategy(3);
	    return;
	}

	Graphics g;
        try {
            g = bs.getDrawGraphics();
	} catch (IllegalStateException ignore) {
	    return;
	}

	g.setColor(Color.BLACK);
	g.fillRect(0, 0, WIDTH, HEIGHT);

	environment.render(g);
	handler.render(g);
	hud.render(g);

	g.dispose();
	Toolkit.getDefaultToolkit().sync();

	try {
	    bs.show();
	} catch (IllegalStateException ignore) {
	}
    }

    public double getAmountOfTicks() {
        return amountOfTicks;
    }

    public void setAmountOfTicks(double amountOfTicks) {
        this.amountOfTicks = clamp((int)amountOfTicks, (int)MIN_AMOUNT_OF_TICKS, (int)amountOfTicks + 1);
	speedIncreaser = this.amountOfTicks;
	ns = S_IN_NS / this.amountOfTicks;
    }

    public String getSaveValues() {
        return Double.toString(amountOfTicks) + SavedProperties.VALUE_SPLIT +  Double.toString(speedIncreaser);
    }

    public Handler getHandler() {
	return handler;
    }

    public Handler getEnvironment() {
	return environment;
    }

    public Spawner getSpawner() {
	return spawner;
    }

    public static int clamp(int variable, int min, int max) {
        if (variable >= max) {
            return max;
	} else if (variable <= min) {
            return min;
	} else {
	    return variable;
	}
    }

    @Override public Dimension getPreferredSize() {
	return new Dimension(WIDTH, HEIGHT);
    }

    public void pause() {
        running = false;
        handler.setRunning(false);
    }

    public void resume() {
        if (!isGameOver()) {
	    running = true;
	    handler.setRunning(true);
	}
    }

    public void stopRender() {render = false;}

    public void startRender() {
	createBufferStrategy(3);
        render = true;
	requestFocus();
    }

    public boolean isRunning() {
        return running;
    }

    public boolean isGameOver() {
        int count = 0;
	for (Player controlledObject:
	     players) {
	    if (controlledObject.isAlive()) ++count;
	}
	if (count == 0 || (players.size() > 1 && count == 1)) return true;
	else return false;
    }

    public void gameOver() {
        pause();
	if (players.size() > 1) app.gameOver(players);
	else app.gameOver(players.get(0));
    }
}
