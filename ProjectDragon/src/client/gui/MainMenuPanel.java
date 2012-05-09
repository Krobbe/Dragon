package client.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import event.Event;
import event.EventBus;
import event.EventHandler;

@SuppressWarnings("serial")
public class MainMenuPanel extends JPanel implements ActionListener,
		EventHandler {

	private JButton mainLogoutButton;
	private JButton mainJoinTableButton;
	private JButton mainCreateTableButton;
	private JButton mainStatisticsButton;

	public MainMenuPanel() {
		init();
		EventBus.register(this);
	}

	@Override
	public void onEvent(Event evt) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mainJoinTableButton) {
			EventBus.publish(new Event(Event.Tag.GO_TO_JOINTABLE, 1));
		} else if (e.getSource() == mainCreateTableButton) {
			EventBus.publish(new Event(Event.Tag.GO_TO_CREATETABLE, 1));
		} else if (e.getSource() == mainStatisticsButton) {
			EventBus.publish(new Event(Event.Tag.GO_TO_STATISTICS, 1));
		} else if (e.getSource() == mainLogoutButton) {
			EventBus.publish(new Event(Event.Tag.LOGOUT, 1));
		}
	}

	private void init() {
		// JPanel mainPanel = new JPanel();
		// frame.getContentPane().add(mainPanel);
		this.setLayout(null);

		mainLogoutButton = new JButton("Log out");
		mainLogoutButton.setBounds(10, 683, 108, 36);
		mainLogoutButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		mainLogoutButton.addActionListener(this);
		this.add(mainLogoutButton);

		mainJoinTableButton = new JButton("Join table");
		mainJoinTableButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		mainJoinTableButton.setBounds(420, 292, 167, 146);
		mainJoinTableButton.addActionListener(this);
		this.add(mainJoinTableButton);

		mainCreateTableButton = new JButton("Create table");
		mainCreateTableButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		mainCreateTableButton.setBounds(128, 292, 167, 146);
		mainCreateTableButton.addActionListener(this);
		this.add(mainCreateTableButton);

		mainStatisticsButton = new JButton("View statistics");
		mainStatisticsButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		mainStatisticsButton.setBounds(704, 292, 167, 146);
		mainStatisticsButton.addActionListener(this);
		this.add(mainStatisticsButton);
	}

}
