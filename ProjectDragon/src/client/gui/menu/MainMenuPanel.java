package client.gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import client.event.*;

/**
 * The MainMenuPanel is the panel that the user is sent to after logging in. Has buttons for
 * accessing other important panels.
 * @author forssenm
 *
 */
@SuppressWarnings("serial")
public class MainMenuPanel extends JPanel implements ActionListener,
		client.event.EventHandler {

	private JButton mainLogoutButton;
	private JButton mainJoinTableButton;
	private JButton mainCreateTableButton;
	private JButton mainStatisticsButton;
	
	/**
	 * Creates the panel
	 */
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
			EventBus.publish(new Event(Event.Tag.GO_TO_JOINTABLE, 1));
			EventBus.publish(new Event(Event.Tag.GET_ACTIVE_GAMES, 1));
		} else if (e.getSource() == mainCreateTableButton) {
			EventBus.publish(new Event(Event.Tag.GO_TO_CREATETABLE, 1));
		} else if (e.getSource() == mainStatisticsButton) {
			EventBus.publish(new Event(Event.Tag.GO_TO_STATISTICS, 1));
			EventBus.publish(new Event(Event.Tag.GET_ACCOUNT_INFORMATION, 1));
		} else if (e.getSource() == mainLogoutButton) {
			EventBus.publish(new Event(Event.Tag.LOGOUT, 1));
		}
	}

	private void init() {
		// JPanel mainPanel = new JPanel();
		// frame.getContentPane().add(mainPanel);
		this.setLayout(null);
		this.setBackground(P.INSTANCE.getBackground());

		mainLogoutButton = new JButton("Log out");
		mainLogoutButton.setBounds(10, 683, 108, 36);
		mainLogoutButton.setFont(P.INSTANCE.getLabelFont());
		mainLogoutButton.addActionListener(this);
		this.add(mainLogoutButton);

		mainJoinTableButton = new JButton("Join table");
		mainJoinTableButton.setFont(P.INSTANCE.getLabelFont());
		mainJoinTableButton.setBounds(420, 292, 167, 146);
		mainJoinTableButton.addActionListener(this);
		this.add(mainJoinTableButton);

		mainCreateTableButton = new JButton("Create table");
		mainCreateTableButton.setFont(P.INSTANCE.getLabelFont());
		mainCreateTableButton.setBounds(128, 292, 167, 146);
		mainCreateTableButton.addActionListener(this);
		this.add(mainCreateTableButton);

		mainStatisticsButton = new JButton("View statistics");
		mainStatisticsButton.setFont(P.INSTANCE.getLabelFont());
		mainStatisticsButton.setBounds(704, 292, 167, 146);
		mainStatisticsButton.addActionListener(this);
		this.add(mainStatisticsButton);
	}

}
