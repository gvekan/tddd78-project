package se.liu.ida.gusan092.tddd78.project.gui.Component;

import se.liu.ida.gusan092.tddd78.project.game.Game;
import se.liu.ida.gusan092.tddd78.project.gui.*;
import se.liu.ida.gusan092.tddd78.project.gui.Window;

import javax.swing.*;
import java.awt.*;

public class Start extends WComponent
{

    public Start(final Window window) {
	super(State.START);
	this.setLayout(new GridBagLayout());
       	JButton button;
       	GridBagConstraints c = new GridBagConstraints();

	JLabel label = new JLabel("A CAR GAME");
	c.fill = GridBagConstraints.HORIZONTAL;
	c.ipady = 30;       //reset to default
	c.ipadx = 30;
	c.weighty = 0;   //request any extra vertical space
	c.anchor = GridBagConstraints.CENTER; //bottom of space
	c.insets = new Insets(10,0,0,0);  //top padding
	c.gridy = 1;       //third row
	this.add(label,c);

	button = new JButton("CONTINUE");
	button.addActionListener(window::toGame);
	c.fill = GridBagConstraints.HORIZONTAL;
	c.ipady = 30;       //reset to default
	c.ipadx = 30;
	c.weighty = 0;   //request any extra vertical space
	c.anchor = GridBagConstraints.CENTER; //bottom of space
	c.insets = new Insets(10,0,0,0);  //top padding
//	c.gridx = 1;       //aligned with button 2
//	c.gridwidth = 2;   //2 columns wide
	c.gridy = 2;       //third row
	this.add(button, c);

       	button = new JButton("NEW GAME");
       	button.addActionListener(window::newGame);
       	c.fill = GridBagConstraints.HORIZONTAL;
       	c.ipady = 30;       //reset to default
       	c.ipadx = 30;
       	c.weighty = 0;   //request any extra vertical space
       	c.anchor = GridBagConstraints.CENTER; //bottom of space
       	c.insets = new Insets(10,0,0,0);  //top padding
       //	c.gridx = 1;       //aligned with button 2
       //	c.gridwidth = 2;   //2 columns wide
       	c.gridy = 3;       //third row
       	this.add(button, c);

	button = new JButton("SETTINGS");
	c.fill = GridBagConstraints.HORIZONTAL;
	c.ipady = 30;       //reset to default
	c.ipadx = 30;
	c.weighty = 0;   //request any extra vertical space
	c.anchor = GridBagConstraints.CENTER; //bottom of space
	c.insets = new Insets(10,0,0,0);  //top padding
//	c.gridx = 1;       //aligned with button 2
//	c.gridwidth = 2;   //2 columns wide
	c.gridy = 4;       //third row
	this.add(button, c);
    }

    @Override public Dimension getPreferredSize() {
	return new Dimension(Game.WIDTH, Game.HEIGHT);
    }
}
