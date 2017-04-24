package se.liu.ida.gusan092.tddd78.project.game;


import se.liu.ida.gusan092.tddd78.project.game.hud.Hud;
import se.liu.ida.gusan092.tddd78.project.game.objects.Type;
import se.liu.ida.gusan092.tddd78.project.game.objects.controlled.Player;
import se.liu.ida.gusan092.tddd78.project.game.objects.still.Roadblock;
import se.liu.ida.gusan092.tddd78.project.game.powerup.*;
import se.liu.ida.gusan092.tddd78.project.game.objects.still.Container;
import se.liu.ida.gusan092.tddd78.project.gui.Window;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Graphics;

import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable
{

    public static final int WIDTH = 500, HEIGHT = 850;

    public static final double INCREASE = 5;
    public static final double MIN_AMOUNT_OF_TICKS = 60.0;
    public static final int S_IN_NS = 1000000000;
    private double amountOfTicks = MIN_AMOUNT_OF_TICKS;
    private double ns = S_IN_NS / amountOfTicks;
    private double speedIncreaser = MIN_AMOUNT_OF_TICKS;

    private int spawnTimer = 0;

    private Thread thread = new Thread(this);
    private boolean threadRunning = false;

    private Random random = new Random();

    private Boolean running = true;
    private Handler handler = new Handler();
    private Handler environment = new Handler();
    private Hud hud;

    public Game() {
        Player player = new Player(handler, this);
	handler.add(player);
        this.addKeyListener(new Controller(player));
        hud = new Hud(player);
    }

    public synchronized void start() {
	threadRunning = true;
	thread.start();
    }

    public synchronized void stop() {
        try {
	    threadRunning = false;
            thread.join();
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
    }


    @Override public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        boolean powerup = true;
        while (threadRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                if (running) {
		    tick();
		}
                delta--;
	    }
	    if (threadRunning) {
                if (running) {
		    render();
		}
	    }
	    frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
		if (running) {
		    speedIncreaser += INCREASE;
		    if (speedIncreaser % 1 == 0) {
			if (powerup) {
			    handler.add(new Container(random.nextInt(WIDTH - Container.DIAMETER), handler, PowerUpId.AMMO));
			} else {
			    handler.add(new Container(random.nextInt(WIDTH - Container.DIAMETER), handler, PowerUpId.UNSTOPPABLE));
			}
			powerup = !powerup;
			amountOfTicks = speedIncreaser;
			ns = S_IN_NS / amountOfTicks;
			System.out.println("Amount of ticks: " + amountOfTicks);
		    }
		}
	    }
	}
    }

    private void tick() {
        if (spawnTimer == 0) {
	    handler.add(new Roadblock(random.nextInt(WIDTH - Roadblock.WIDTH), Type.ROADBLOCK, handler));
	    spawnTimer = 60;
	}
	environment.tick();
	handler.tick();
	spawnTimer--;
    }


    private void render() {
	BufferStrategy bs = this.getBufferStrategy(); //Funkar inte om jag försöker använda jcomponent
        if (bs == null) {
	    this.createBufferStrategy(3);
	    return;
	}

	Graphics g = bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0,0,WIDTH,HEIGHT);

	environment.render(g);
        handler.render(g);
	hud.render(g);

	g.dispose();
	Toolkit.getDefaultToolkit().sync();
	bs.show();
    }

    public double getAmountOfTicks() {
        return amountOfTicks;
    }

    public void setAmountOfTicks(double amountOfTicks) {
        this.amountOfTicks = clamp((int)amountOfTicks, (int)MIN_AMOUNT_OF_TICKS, (int)amountOfTicks + 1);
	speedIncreaser = this.amountOfTicks;
	ns = S_IN_NS / this.amountOfTicks;
    }

    public Handler getEnvironment() {
        return environment;
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
    }

    public void resume() {
        running = true;
    }

    public static void main(String[] args) {
	Window window = new Window(new Game());
    }
}
