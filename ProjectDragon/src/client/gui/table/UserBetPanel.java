package client.gui.table;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import client.event.*;

public class UserBetPanel extends JPanel implements ActionListener {
	
	private JLabel userAvailableCreditsLabel;
	private JLabel userCreditsLabel;
	private JTextField userBetField;
	private JButton userFoldButton;
	private JButton userCheckButton;
	private JButton userRaiseButton;
	
	public UserBetPanel() {
		init();
	}

	private void init() {
		this.setBounds(666, 571, 332, 148);
		this.setLayout(null);

		userAvailableCreditsLabel = new JLabel("xxx");
		userAvailableCreditsLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		userAvailableCreditsLabel.setBounds(168, 33, 60, 19);
		this.add(userAvailableCreditsLabel);

		userFoldButton = new JButton("Fold");
		userFoldButton.setBounds(232, 108, 100, 40);
		userFoldButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		userFoldButton.addActionListener(this);
		this.add(userFoldButton);

		userCheckButton = new JButton("Check");
		userCheckButton.setBounds(0, 108, 100, 40);
		userCheckButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		userCheckButton.addActionListener(this);
		this.add(userCheckButton);

		userRaiseButton = new JButton("Raise");
		userRaiseButton.setBounds(116, 108, 100, 40);
		userRaiseButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		userRaiseButton.addActionListener(this);
		this.add(userRaiseButton);

		userBetField = new JTextField();
		userBetField.setBounds(116, 63, 100, 40);
		userBetField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		userBetField.setColumns(10);
		this.add(userBetField);

		userCreditsLabel = new JLabel("Credits:");
		userCreditsLabel.setBounds(103, 32, 55, 20);
		userCreditsLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(userCreditsLabel);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(userCheckButton)) {
			
			EventBus.publish(new Event(Event.Tag.REQUEST_CHECK, 1));
			
		} else if (e.getSource().equals(userFoldButton)) {
			
			EventBus.publish(new Event(Event.Tag.REQUEST_FOLD, 1));
			
		} else if (e.getSource().equals(userRaiseButton)) {
			
			EventBus.publish(new Event(Event.Tag.REQUEST_RAISE, Integer
					.parseInt(userBetField.getText())));
			
		}
	}

}
