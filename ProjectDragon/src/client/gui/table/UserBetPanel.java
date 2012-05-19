package client.gui.table;

import java.awt.BorderLayout;
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
	
	private JPanel buttonPanel, infoPanel;
	
	private JLabel availableCreditsLabel;
	private JLabel creditsLabel;
	private JTextField betField;
	private JButton foldButton;
	private JButton checkButton;
	private JButton raiseButton;
	
	private int buttonHeight = P.INSTANCE.getButtonHeight();
	private int buttonWidth = P.INSTANCE.getButtonWidth();
	
	/**
	 * Creates the panel
	 */
	public UserBetPanel() {
		init();
	}

	private void init() {
		this.setPreferredSize(new Dimension(332,144));
		//this.setBounds(666, 571, 332, 148);
		this.setLayout(new BorderLayout());
		
		buttonPanel = new JPanel();
		infoPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(3, 1));
		infoPanel.setLayout(new BorderLayout());

		availableCreditsLabel = new JLabel("xxx");
		availableCreditsLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		//userAvailableCreditsLabel.setBounds(168, 33, 60, 19);
		availableCreditsLabel.setPreferredSize(new Dimension(60,19));

		foldButton = new JButton("Fold");
		//userFoldButton.setBounds(232, 108, 100, 40);
		foldButton.setSize(buttonWidth, buttonHeight);
		foldButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		foldButton.addActionListener(this);

		checkButton = new JButton("Check");
		//userCheckButton.setBounds(0, 108, 100, 40);
		checkButton.setSize(buttonWidth, buttonHeight);
		checkButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		checkButton.addActionListener(this);

		raiseButton = new JButton("Raise");
		//userRaiseButton.setBounds(116, 108, 100, 40);
		raiseButton.setSize(buttonWidth, buttonHeight);
		raiseButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		raiseButton.addActionListener(this);

		betField = new JTextField();
		//userBetField.setBounds(116, 63, 100, 40);
		betField.setSize(buttonWidth, buttonHeight);
		betField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		betField.setColumns(10);

		creditsLabel = new JLabel("Credits:");
		//userCreditsLabel.setBounds(103, 32, 55, 20);
		creditsLabel.setSize(buttonWidth, buttonHeight);
		creditsLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		buttonPanel.add(checkButton);
		buttonPanel.add(raiseButton);
		buttonPanel.add(foldButton);
		
		infoPanel.add(betField, BorderLayout.NORTH);
		infoPanel.add(creditsLabel, BorderLayout.CENTER);
		infoPanel.add(availableCreditsLabel, BorderLayout.SOUTH);
		
		this.add(buttonPanel, BorderLayout.EAST);
		this.add(infoPanel, BorderLayout.WEST);
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
			
		}
	}

}
