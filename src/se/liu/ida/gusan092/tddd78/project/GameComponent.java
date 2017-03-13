package se.liu.ida.gusan092.tddd78.project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.AbstractMap;
import java.util.EnumMap;
import java.util.Map.Entry;

import javax.swing.*;

public class GameComponent extends JComponent
{
    private static final String PRESSED = "pressed";
    private static final String RELEASED = "released";
    private static final int TIMER_DELAY = 10;
    private AbstractMap<Key, Boolean> keyMap = new EnumMap<>(Key.class);

    private Game game;
    private Player player;

    public GameComponent(final Game game) {
	this.game = game;
	this.player = game.getPlayer();

 	keyMap.put(Key.UP, false);
 	keyMap.put(Key.DOWN, false);
	keyMap.put(Key.LEFT, false);
 	keyMap.put(Key.RIGHT, false);

	ActionMap actionMap = getActionMap();
	InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);

 	for (Key key : Key.values()) {
	    KeyStroke pressedKeyStroke = KeyStroke.getKeyStroke(key.getKeyCode(), 0, false);
	    KeyStroke releasedKeyStroke = KeyStroke.getKeyStroke(key.getKeyCode(), 0, true);

	    inputMap.put(pressedKeyStroke, key.getText() + PRESSED);
	    inputMap.put(releasedKeyStroke, key.getText() + RELEASED);
	    actionMap.put(key.getText() + PRESSED, new ArrowBinding(key, false));
	    actionMap.put(key.getText() + RELEASED, new ArrowBinding(key, true));
 	}
	new Timer(TIMER_DELAY, new TimerListener()).start();
    }

    private static void createAndShowGui() {
          GameComponent mainComponent = new GameComponent(new Game(new Player(30,50)));

          JFrame frame = new JFrame("Test");
          frame.setLayout(new BorderLayout());
          frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
          frame.add(mainComponent, BorderLayout.CENTER);

          frame.pack();
          frame.setVisible(true);
       }

       public static void main(String[] args) {
          SwingUtilities.invokeLater(GameComponent::createAndShowGui);
       }

       private class TimerListener implements ActionListener {
          public void actionPerformed(ActionEvent e) {
              int x = 0;
              int y = 0;
              for (final Entry<Key, Boolean> keyBooleanEntry : keyMap.entrySet()) {
                 Key key = keyBooleanEntry.getKey();
                 Boolean value = keyBooleanEntry.getValue();
                 System.out.printf("%6s %b%n", key, value);
                 if (value) {
                     switch (key) {
			 case UP:
			     y--;
			     break;
			 case DOWN:
			     y++;
			     break;
			 case LEFT:
			     x--;
			     break;
			 case RIGHT:
			     x++;
			     break;
		     }
		 }
              }
              System.out.println();
	      player.move(x,y);
	      repaint();
          }
       }

       private class ArrowBinding extends AbstractAction {
          private Key key;
          private boolean released;

          public ArrowBinding(Key key, boolean released) {
             this.key = key;
             this.released = released;
          }

          @Override
          public void actionPerformed(ActionEvent aEvt) {
             keyMap.put(key, !released);
          }
       }

    @Override public Dimension getPreferredSize() {
	return new Dimension(Game.WIDTH, Game.HEIGHT);
    }

    @Override protected void paintComponent(final Graphics g) {
	super.paintComponent(g);
	final Graphics2D g2d = (Graphics2D) g;

	player.draw(g2d);
    }
}
