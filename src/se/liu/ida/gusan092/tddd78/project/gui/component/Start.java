package se.liu.ida.gusan092.tddd78.project.gui.component;

import se.liu.ida.gusan092.tddd78.project.gui.App;

import javax.swing.*;
import java.awt.*;

/**
 * The start component for window
 */
public class Start extends AComponent
{

    /**
     * The padding used between buttons
     */
    public static final int PADDING = 30;

    public Start(final App app) {
	super(State.START);
	setLayout(new GridBagLayout());
       	JButton button;
       	GridBagConstraints c = new GridBagConstraints();

	JLabel label = new JLabel("A CAR GAME");
	c.fill = GridBagConstraints.HORIZONTAL;
	c.ipady = PADDING;       //reset to default
	c.ipadx = PADDING;
	c.weighty = 0;   //request any extra vertical space
	c.anchor = GridBagConstraints.CENTER; //bottom of space
	c.insets = new Insets(10,0,0,0);  //top padding
	c.gridy = 1;       //third row
	add(label,c);

	button = new JButton("CONTINUE");
	button.addActionListener(app::continueGame);
	c.fill = GridBagConstraints.HORIZONTAL;
	c.ipady = PADDING;       //reset to default
	c.ipadx = PADDING;
	c.weighty = 0;   //request any extra vertical space
	c.anchor = GridBagConstraints.CENTER; //bottom of space
	c.insets = new Insets(10,0,0,0);  //top padding
//	c.gridx = 1;       //aligned with button 2
//	c.gridwidth = 2;   //2 columns wide
	c.gridy = 2;       //third row
	add(button, c);

       	button = new JButton("NEW GAME");
       	button.addActionListener(app::newGame);
       	c.fill = GridBagConstraints.HORIZONTAL;
       	c.ipady = PADDING;       //reset to default
       	c.ipadx = PADDING;
       	c.weighty = 0;   //request any extra vertical space
       	c.anchor = GridBagConstraints.CENTER; //bottom of space
       	c.insets = new Insets(10,0,0,0);  //top padding
       //	c.gridx = 1;       //aligned with button 2
       //	c.gridwidth = 2;   //2 columns wide
       	c.gridy = 3;       //third row
       	add(button, c);

	button = new JButton("HIGH SCORES");
	button.addActionListener(app::toHighScore);
	c.fill = GridBagConstraints.HORIZONTAL;
	c.ipady = PADDING;       //reset to default
	c.ipadx = PADDING;
	c.weighty = 0;   //request any extra vertical space
	c.anchor = GridBagConstraints.CENTER; //bottom of space
	c.insets = new Insets(10,0,0,0);  //top padding
//	c.gridx = 1;       //aligned with button 2
//	c.gridwidth = 2;   //2 columns wide
	c.gridy = 4;       //third row
	add(button, c);
    }
}
