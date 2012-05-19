package client.gui.table;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import client.event.*;

/**
 * The panel that has all the options that the player can do such as make a call or a bet
 * @author forssenm
 *
 */
public class UserBetPanel extends JPanel implements ActionListener {
		
	private JLabel availableCreditsLabel;
	private JLabel creditsLabel;
	private JTextField betField;
	private JButton foldButton;
	private JButton checkButton;
	private JButton raiseButton;
	private JButton leaveTableButton;
	
	private int buttonHeight = P.INSTANCE.getButtonHeight();
	private int buttonWidth = P.INSTANCE.getButtonWidth();
	
	/**
	 * Creates the panel
	 */
	public UserBetPanel() {
		init();
	}

	private void init() {
		this.setPreferredSize(new Dimension(buttonHeight + 10, 
				P.INSTANCE.getFrameWidth()));
		this.setLayout(new FlowLayout());
		
		leaveTableButton = new JButton("Leave table");
		leaveTableButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		leaveTableButton.setSize(buttonWidth, buttonHeight);
		leaveTableButton.addActionListener(this);
		
		availableCreditsLabel = new JLabel("xxx");
		availableCreditsLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		availableCreditsLabel.setPreferredSize(new Dimension(60,19));

		foldButton = new JButton("Fold");
		foldButton.setSize(buttonWidth, buttonHeight);
		foldButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		foldButton.setBackground(Color.red);
		foldButton.setOpaque(true);
		foldButton.addActionListener(this);

		checkButton = new JButton("Check");
		checkButton.setSize(buttonWidth, buttonHeight);
		checkButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		checkButton.setBackground(Color.green);
		checkButton.setOpaque(true);
		checkButton.addActionListener(this);

		raiseButton = new JButton("Raise");
		raiseButton.setSize(buttonWidth, buttonHeight);
		raiseButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		raiseButton.addActionListener(this);

		betField = new JTextField();
		betField.setSize(buttonWidth, buttonHeight);
		betField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		betField.setColumns(10);

		creditsLabel = new JLabel("Credits:");
		creditsLabel.setSize(buttonWidth, buttonHeight);
		creditsLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		this.add(leaveTableButton);
		this.add(foldButton);
		this.add(checkButton);
		this.add(raiseButton);
		this.add(betField);
		this.add(creditsLabel);
		this.add(availableCreditsLabel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(checkButton)) {
			
			EventBus.publish(new Event(Event.Tag.REQUEST_CHECK, 1));
			
		} else if (e.getSource().equals(foldButton)) {
			
			EventBus.publish(new Event(Event.Tag.REQUEST_FOLD, 1));
			
		} else if (e.getSource().equals(raiseButton)) {
			
			EventBus.publish(new Event(Event.Tag.REQUEST_RAISE, Integer
					.parseInt(betField.getText())));
		} else if (e.getSource().equals(leaveTableButton)) {
//			EventBus.publish(new Event(Event.Tag.REQUEST_FOLD, 1));
			EventBus.publish(new Event(Event.Tag.LEAVE_TABLE, 1));
		}
	}

}
