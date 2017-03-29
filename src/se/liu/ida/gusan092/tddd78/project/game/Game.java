package se.liu.ida.gusan092.tddd78.project.game;


import se.liu.ida.gusan092.tddd78.project.game.hud.Hud;
import se.liu.ida.gusan092.tddd78.project.game.hud.PlayerHud;
import se.liu.ida.gusan092.tddd78.project.game.objects.Type;
import se.liu.ida.gusan092.tddd78.project.game.objects.controlled.Player;
import se.liu.ida.gusan092.tddd78.project.game.objects.still.Roadblock;
import se.liu.ida.gusan092.tddd78.project.game.objects.still.powerup.Unstoppable;
import se.liu.ida.gusan092.tddd78.project.gui.Window;

import java.awt.*;

import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable
{

    public static final int WIDTH = 500, HEIGHT = 750;

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

    private Handler handler = new Handler();
    private Handler environment = new Handler();
    private Hud hud;

    public Game() {
        Player player = new Player((WIDTH - 20) / 2, (HEIGHT - 100), handler, this);
	handler.add(player);
        this.addKeyListener(new Controller(player));
        hud = new PlayerHud(player);

	handler.add(new Unstoppable(random.nextInt(WIDTH - 25), handler));

    }

    public synchronized void start() {
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

                speedIncreaser += INCREASE;
		if (speedIncreaser%1 == 0) {
		    amountOfTicks = speedIncreaser;
		    ns = 1000000000 / amountOfTicks;
		    System.out.println("Amount of ticks: " + amountOfTicks);
		}
	    }
	}
    }

    private void tick() {
        if (spawnTimer == 0) {
	    handler.add(new Roadblock(random.nextInt(WIDTH - 25), Type.ROADBLOCK, handler));
	    spawnTimer = 60;
	}
	environment.tick();
	handler.tick();
	spawnTimer--;
    }

/*    private void maxTick() {
        environment.maxTick();
        handler.maxTick();
        hud.maxTick();
    }*/

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
	ns = 1000000000 / this.amountOfTicks;
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
	return new Dimension(WIDTH, HEIGHT+hud.getHeight());
    }

    public static void main(String[] args) {
	Window window = new Window("Test", new Game());
    }
}
