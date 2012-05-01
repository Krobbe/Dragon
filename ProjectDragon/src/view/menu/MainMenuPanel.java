package view.menu;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import event.Event;
import event.EventBus;
import event.EventHandler;

@SuppressWarnings("serial")
public class MainMenuPanel extends JPanel implements ActionListener, EventHandler{
	
	private JButton mainLogoutButton;
	private JButton mainJoinTableButton;
	private JButton mainCreateTableButton;
	private JButton mainViewStatisticsButton;
	
	
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
		// TODO Auto-generated method stub
		
	}
	
	private void init() {
		JPanel mainPanel = new JPanel();
//		frame.getContentPane().add(mainPanel);
		mainPanel.setLayout(null);

		mainLogoutButton = new JButton("Log out");
		mainLogoutButton.setBounds(10, 683, 108, 36);
		mainLogoutButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		mainLogoutButton.addActionListener(this);
		mainPanel.add(mainLogoutButton);

		mainJoinTableButton = new JButton("Join table");
		mainJoinTableButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		mainJoinTableButton.setBounds(420, 292, 167, 146);
		mainJoinTableButton.addActionListener(this);
		mainPanel.add(mainJoinTableButton);

		mainCreateTableButton = new JButton("Create table");
		mainCreateTableButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		mainCreateTableButton.setBounds(128, 292, 167, 146);
		mainCreateTableButton.addActionListener(this);
		mainPanel.add(mainCreateTableButton);

		mainViewStatisticsButton = new JButton("View statistics");
		mainViewStatisticsButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		mainViewStatisticsButton.setBounds(704, 292, 167, 146);
		mainViewStatisticsButton.addActionListener(this);
		mainPanel.add(mainViewStatisticsButton);
	}

}
