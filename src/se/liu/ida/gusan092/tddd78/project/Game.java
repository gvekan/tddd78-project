package se.liu.ida.gusan092.tddd78.project;


import java.awt.Canvas;

import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;

public class Game extends Canvas implements Runnable
{
    public static final int WIDTH = 500, HEIGHT = 750;

    public static final double ACCELERATION = 5;
    public static final double MIN_AMOUNT_OF_TICKS = 60.0;
    private double amountOfTicks = MIN_AMOUNT_OF_TICKS;
    private double ns = 1000000000 / amountOfTicks;
    private double speedIncreaser = MIN_AMOUNT_OF_TICKS;

    private Thread thread;
    private boolean threadRunning = false;

    private Random random = new Random();

    private Handler handler = new Handler();
    private Handler environment = new Handler();
    //private Hud hud = new Hud();

    public Game() {
        Player player = new Player((WIDTH-20)/2, (HEIGHT-45), Identity.PLAYER, handler, this);
	handler.addGameObject(player);
        this.addKeyListener(new Controller(player));

        new Window(WIDTH,HEIGHT,"Test", this);

    }

    public synchronized void start() {
        thread = new Thread(this);
	thread.start();
	threadRunning = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
	    threadRunning = false;
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
        while (threadRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
	    }
	    if (threadRunning) {
                render();
	    }
	    frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;

		handler.addGameObject(new Roadblock(random.nextInt(WIDTH - 25), Identity.ROADBLOCK, handler));

                speedIncreaser += ACCELERATION;
		if (speedIncreaser%1 == 0) {
		    amountOfTicks = speedIncreaser;
		    ns = 1000000000 / amountOfTicks;
		    System.out.println("Amount of ticks: " + amountOfTicks);
		}
	    }
	}
    }

    private void tick() {
	handler.tick();
	environment.tick();
	//hud.tick();
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

        handler.render(g);
        environment.render(g);
	//hud.render(g);

	g.dispose();
	bs.show();
    }

    public double getAmountOfTicks() {
        return amountOfTicks;
    }

    public void setAmountOfTicks(double amountOfTicks) {
        this.amountOfTicks = clamp((int)amountOfTicks, (int)MIN_AMOUNT_OF_TICKS, (int)amountOfTicks + 1);
	speedIncreaser = this.amountOfTicks;
	ns = 1000000000 / this.amountOfTicks;
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

    public static void main(String[] args) {
	new Game();
    }
}
