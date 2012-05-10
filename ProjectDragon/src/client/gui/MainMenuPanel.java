package client.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class MainMenuPanel extends JPanel implements ActionListener,
		client.event.EventHandler {

	private JButton mainLogoutButton;
	private JButton mainJoinTableButton;
	private JButton mainCreateTableButton;
	private JButton mainStatisticsButton;

	public MainMenuPanel() {
		init();
		client.event.EventBus.register(this);
	}

	@Override
	public void onEvent(client.event.Event evt) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mainJoinTableButton) {
			client.event.EventBus.publish(new client.event.Event(client.event.Event.Tag.GO_TO_JOINTABLE, 1));
		} else if (e.getSource() == mainCreateTableButton) {
			client.event.EventBus.publish(new client.event.Event(client.event.Event.Tag.GO_TO_CREATETABLE, 1));
		} else if (e.getSource() == mainStatisticsButton) {
			client.event.EventBus.publish(new client.event.Event(client.event.Event.Tag.GO_TO_STATISTICS, 1));
		} else if (e.getSource() == mainLogoutButton) {
			client.event.EventBus.publish(new client.event.Event(client.event.Event.Tag.LOGOUT, 1));
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
