package se.liu.ida.gusan092.tddd78.project;


import java.awt.Canvas;

import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Color;

public class Game extends Canvas implements Runnable
{
    public static final int WIDTH = 500, HEIGHT = 750;

    public static final double ACCELERATION = 0.1;
    private static final double MIN_AMOUNT_OF_TICKS = 60.0;
    private double amountOfTicks = MIN_AMOUNT_OF_TICKS;
    private double ns = 1000000000 / amountOfTicks;
    private double speedIncreaser = MIN_AMOUNT_OF_TICKS;
    public int speed = (int) Math.round(amountOfTicks/2);

    private Thread thread;
    private boolean threadRunning = false;


    private Handeler handeler = new Handeler();

    public Game() {
        Player player = new Player((WIDTH-20)/2,(HEIGHT-45),Id.PLAYER, handeler);
	handeler.addGameObject(player);
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

                speedIncreaser += ACCELERATION;
		if (speedIncreaser%1 == 0) {
		    amountOfTicks = speedIncreaser;
		    ns = 1000000000 / amountOfTicks;
		    speed = (int) Math.round(amountOfTicks/2);
		    System.out.println("Amount of ticks: " + amountOfTicks);
		}
	    }
	}
    }

    private void tick() {
	handeler.tick();
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

        handeler.render(g);

	g.dispose();
	bs.show();
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
