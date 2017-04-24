package se.liu.ida.gusan092.tddd78.project.gui;

import javax.swing.*;
import java.awt.*;

public class StartComponent extends JComponent
{
    private Window window;

    public StartComponent(final Window window) {
        this.window = window;
        this.setLayout(new GridBagLayout());
       	JButton button;
       	GridBagConstraints c = new GridBagConstraints();

       /*	button = new JButton("Button 1");
       	c.fill = GridBagConstraints.HORIZONTAL;
       	c.gridx = 0;
       	c.gridy = 0;
       	this.add(button, c);

       	button = new JButton("Button 2");
       	c.fill = GridBagConstraints.HORIZONTAL;
       	c.weightx = 0.5;
       	c.gridx = 1;
       	c.gridy = 0;
       	this.add(button, c);

       	button = new JButton("Button 3");
       	c.fill = GridBagConstraints.HORIZONTAL;
       	c.weightx = 0.5;
       	c.gridx = 2;
       	c.gridy = 0;
       	this.add(button, c);

       	button = new JButton("Long-Named Button 4");
       	c.fill = GridBagConstraints.HORIZONTAL;
       	c.ipady = 40;      //make this component tall
       	c.weightx = 0.0;
       	c.gridwidth = 3;
       	c.gridx = 0;
       	c.gridy = 1;
       	this.add(button, c);
       */
	JLabel label = new JLabel("A CAR GAME");
	c.fill = GridBagConstraints.HORIZONTAL;
	c.ipady = 30;       //reset to default
	c.ipadx = 30;
	c.weighty = 0;   //request any extra vertical space
	c.anchor = GridBagConstraints.CENTER; //bottom of space
	c.insets = new Insets(10,0,0,0);  //top padding
	c.gridy = 1;       //third row
	this.add(label,c);

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
       	c.gridy = 2;       //third row
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
	c.gridy = 3;       //third row
	this.add(button, c);
    }
}
