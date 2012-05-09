package client.gui;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import event.Event;
import event.EventBus;
import event.EventHandler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class TablePanel extends JPanel implements EventHandler, ActionListener {

	private JButton leaveTableButton;

	private JTextField userBetField;
	private JLabel userAvailableCreditsLabel;
	private JButton userFoldButton;
	private JButton userCheckButton;
	private JButton userRaiseButton;
	private JLabel userCreditsLabel;

	private JPanel playerOnePanel;
	private JLabel p1c1Label;
	private JLabel p1c2Label;
	private JLabel p1NameLabel;
	private JLabel p1CreditsLabel;
	private JLabel p1AvailableCreditsLabel;

	private JPanel playerTwoPanel;
	private JLabel p2c1Label;
	private JLabel p2c2Label;
	private JLabel p2NameLabel;
	private JLabel p2CreditsLabel;
	private JLabel p2AvailableCreditsLabel;

	private JPanel playerThreePanel;
	private JLabel p3c1Label;
	private JLabel p3c2Label;
	private JLabel p3NameLabel;
	private JLabel p3CreditsLabel;
	private JLabel p3AvailableCreditsLabel;

	private JPanel playerFourPanel;
	private JLabel p4c1Label;
	private JLabel p4c2Label;
	private JLabel p4NameLabel;
	private JLabel p4CreditsLabel;
	private JLabel p4AvailableCreditsLabel;

	private JPanel playerFivePanel;
	private JLabel p5c1Label;
	private JLabel p5c2Label;
	private JLabel p5NameLabel;
	private JLabel p5CreditsLabel;
	private JLabel p5AvailableCreditsLabel;

	private JPanel playerSixPanel;
	private JLabel p6c1Label;
	private JLabel p6c2Label;
	private JLabel p6NameLabel;
	private JLabel p6CreditsLabel;
	private JLabel p6AvailableCreditsLabel;

	private JPanel playerSevenPanel;
	private JLabel p7c1Label;
	private JLabel p7c2Label;
	private JLabel p7NameLabel;
	private JLabel p7CreditsLabel;
	private JLabel p7AvailableCreditsLabel;

	private JPanel playerEightPanel;
	private JLabel p8c1Label;
	private JLabel p8c2Label;
	private JLabel p8NameLabel;
	private JLabel p8CreditsLabel;
	private JLabel p8AvailableCreditsLabel;

	private JPanel playerNinePanel;
	private JLabel p9c1Label;
	private JLabel p9c2Label;
	private JLabel p9NameLabel;
	private JLabel p9CreditsLabel;
	private JLabel p9AvailableCreditsLabel;

	private JPanel playerTenPanel;
	private JLabel p10c1Label;
	private JLabel p10c2Label;
	private JLabel p10NameLabel;
	private JLabel p10CreditsLabel;
	private JLabel p10AvailableCreditsLabel;

	private JPanel shownCardsPanel;
	private JLabel flopc1Label;
	private JLabel flopc2Label;
	private JLabel flopc3Label;
	private JLabel turnc1Label;
	private JLabel riverc1Label;

	/**
	 * Create the application.
	 */
	public TablePanel() {
		initialize();
		EventBus.register(this);
	}
	
	@Override
	public void onEvent(Event evt) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//TODO Should the result be saved here somehow?
		if(e.getSource().equals(leaveTableButton)) {
			EventBus.publish(new Event(Event.Tag.GO_TO_MAIN,1));
		}
		else if(e.getSource().equals(userCheckButton)) {
			EventBus.publish(new Event(Event.Tag.))
		}
			

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// Code for the table panel
		this.setLayout(null);

		leaveTableButton = new JButton("Leave table");
		leaveTableButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		leaveTableButton.setBounds(10, 683, 111, 40);
		this.add(leaveTableButton);

		JPanel callPanel = new JPanel();
		callPanel.setBounds(666, 571, 332, 148);
		this.add(callPanel);
		callPanel.setLayout(null);

		userAvailableCreditsLabel = new JLabel("xxx");
		userAvailableCreditsLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		userAvailableCreditsLabel.setBounds(168, 33, 60, 19);
		callPanel.add(userAvailableCreditsLabel);

		userFoldButton = new JButton("Fold");
		userFoldButton.setBounds(232, 108, 100, 40);
		callPanel.add(userFoldButton);
		userFoldButton.setFont(new Font("Tahoma", Font.PLAIN, 16));

		userCheckButton = new JButton("Check");
		userCheckButton.setBounds(0, 108, 100, 40);
		callPanel.add(userCheckButton);
		userCheckButton.setFont(new Font("Tahoma", Font.PLAIN, 16));

		userRaiseButton = new JButton("Raise");
		userRaiseButton.setBounds(116, 108, 100, 40);
		callPanel.add(userRaiseButton);
		userRaiseButton.setFont(new Font("Tahoma", Font.PLAIN, 16));

		userBetField = new JTextField();
		userBetField.setBounds(116, 63, 100, 40);
		callPanel.add(userBetField);
		userBetField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		userBetField.setColumns(10);

		userCreditsLabel = new JLabel("Credits:");
		userCreditsLabel.setBounds(103, 32, 55, 20);
		callPanel.add(userCreditsLabel);
		userCreditsLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JPanel gamePanel = new JPanel();
		gamePanel.setBounds(10, 11, 988, 549);
		this.add(gamePanel);
		gamePanel.setLayout(null);

		// Player one
		playerOnePanel = new JPanel();
		playerOnePanel.setLayout(null);
		playerOnePanel.setBounds(580, 0, 135, 144);
		gamePanel.add(playerOnePanel);

		p1c1Label = new JLabel("Card 1");
		p1c1Label.setBounds(10, 5, 53, 80);
		playerOnePanel.add(p1c1Label);

		p1c2Label = new JLabel("Card 2\r\n");
		p1c2Label.setBounds(73, 5, 53, 80);
		playerOnePanel.add(p1c2Label);

		p1NameLabel = new JLabel("Player1\r\n");
		p1NameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		p1NameLabel.setBounds(10, 96, 115, 14);
		playerOnePanel.add(p1NameLabel);

		p1CreditsLabel = new JLabel("Credits:");
		p1CreditsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		p1CreditsLabel.setBounds(10, 119, 53, 14);
		playerOnePanel.add(p1CreditsLabel);

		p1AvailableCreditsLabel = new JLabel("xxx");
		p1AvailableCreditsLabel.setBounds(72, 119, 53, 14);
		playerOnePanel.add(p1AvailableCreditsLabel);

		// Player two
		playerTwoPanel = new JPanel();
		playerTwoPanel.setLayout(null);
		playerTwoPanel.setBounds(853, 0, 135, 144);
		gamePanel.add(playerTwoPanel);

		p2c1Label = new JLabel("Card 1");
		p2c1Label.setBounds(10, 5, 53, 80);
		playerTwoPanel.add(p2c1Label);

		p2c2Label = new JLabel("Card 2\r\n");
		p2c2Label.setBounds(73, 5, 53, 80);
		playerTwoPanel.add(p2c2Label);

		p2NameLabel = new JLabel("Player1\r\n");
		p2NameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		p2NameLabel.setBounds(10, 96, 115, 14);
		playerTwoPanel.add(p2NameLabel);

		p2CreditsLabel = new JLabel("Credits:");
		p2CreditsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		p2CreditsLabel.setBounds(10, 119, 53, 14);
		playerTwoPanel.add(p2CreditsLabel);

		p2AvailableCreditsLabel = new JLabel("xxx");
		p2AvailableCreditsLabel.setBounds(72, 119, 53, 14);
		playerTwoPanel.add(p2AvailableCreditsLabel);

		// Player three
		playerThreePanel = new JPanel();
		playerThreePanel.setLayout(null);
		playerThreePanel.setBounds(853, 202, 135, 144);
		gamePanel.add(playerThreePanel);

		p3c1Label = new JLabel("Card 1");
		p3c1Label.setBounds(10, 5, 53, 80);
		playerThreePanel.add(p3c1Label);

		p3c2Label = new JLabel("Card 2\r\n");
		p3c2Label.setBounds(73, 5, 53, 80);
		playerThreePanel.add(p3c2Label);

		p3NameLabel = new JLabel("Player1\r\n");
		p3NameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		p3NameLabel.setBounds(10, 96, 115, 14);
		playerThreePanel.add(p3NameLabel);

		p3CreditsLabel = new JLabel("Credits:");
		p3CreditsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		p3CreditsLabel.setBounds(10, 119, 53, 14);
		playerThreePanel.add(p3CreditsLabel);

		p3AvailableCreditsLabel = new JLabel("xxx");
		p3AvailableCreditsLabel.setBounds(72, 119, 53, 14);
		playerThreePanel.add(p3AvailableCreditsLabel);

		// Player four
		playerFourPanel = new JPanel();
		playerFourPanel.setLayout(null);
		playerFourPanel.setBounds(853, 405, 135, 144);
		gamePanel.add(playerFourPanel);

		p4c1Label = new JLabel("Card 1");
		p4c1Label.setBounds(10, 5, 53, 80);
		playerFourPanel.add(p4c1Label);

		p4c2Label = new JLabel("Card 2\r\n");
		p4c2Label.setBounds(73, 5, 53, 80);
		playerFourPanel.add(p4c2Label);

		p4NameLabel = new JLabel("Player1\r\n");
		p4NameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		p4NameLabel.setBounds(10, 96, 115, 14);
		playerFourPanel.add(p4NameLabel);

		p4CreditsLabel = new JLabel("Credits:");
		p4CreditsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		p4CreditsLabel.setBounds(10, 119, 53, 14);
		playerFourPanel.add(p4CreditsLabel);

		p4AvailableCreditsLabel = new JLabel("xxx");
		p4AvailableCreditsLabel.setBounds(72, 119, 53, 14);
		playerFourPanel.add(p4AvailableCreditsLabel);

		// Player five
		playerFivePanel = new JPanel();
		playerFivePanel.setLayout(null);
		playerFivePanel.setBounds(580, 405, 135, 144);
		gamePanel.add(playerFivePanel);

		p5c1Label = new JLabel("Card 1");
		p5c1Label.setBounds(10, 5, 53, 80);
		playerFivePanel.add(p5c1Label);

		p5c2Label = new JLabel("Card 2\r\n");
		p5c2Label.setBounds(73, 5, 53, 80);
		playerFivePanel.add(p5c2Label);

		p5NameLabel = new JLabel("Player1\r\n");
		p5NameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		p5NameLabel.setBounds(10, 96, 115, 14);
		playerFivePanel.add(p5NameLabel);

		p5CreditsLabel = new JLabel("Credits:");
		p5CreditsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		p5CreditsLabel.setBounds(10, 119, 53, 14);
		playerFivePanel.add(p5CreditsLabel);

		p5AvailableCreditsLabel = new JLabel("xxx");
		p5AvailableCreditsLabel.setBounds(72, 119, 53, 14);
		playerFivePanel.add(p5AvailableCreditsLabel);

		// Player six
		playerSixPanel = new JPanel();
		playerSixPanel.setLayout(null);
		playerSixPanel.setBounds(297, 405, 135, 144);
		gamePanel.add(playerSixPanel);

		p6c1Label = new JLabel("Card 1");
		p6c1Label.setBounds(10, 5, 53, 80);
		playerSixPanel.add(p6c1Label);

		p6c2Label = new JLabel("Card 2\r\n");
		p6c2Label.setBounds(73, 5, 53, 80);
		playerSixPanel.add(p6c2Label);

		p6NameLabel = new JLabel("Player1\r\n");
		p6NameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		p6NameLabel.setBounds(10, 96, 115, 14);
		playerSixPanel.add(p6NameLabel);

		p6CreditsLabel = new JLabel("Credits:");
		p6CreditsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		p6CreditsLabel.setBounds(10, 119, 53, 14);
		playerSixPanel.add(p6CreditsLabel);

		p6AvailableCreditsLabel = new JLabel("xxx");
		p6AvailableCreditsLabel.setBounds(72, 119, 53, 14);
		playerSixPanel.add(p6AvailableCreditsLabel);

		// Player seven
		playerSevenPanel = new JPanel();
		playerSevenPanel.setLayout(null);
		playerSevenPanel.setBounds(0, 405, 135, 144);
		gamePanel.add(playerSevenPanel);

		p7c1Label = new JLabel("Card 1");
		p7c1Label.setBounds(10, 5, 53, 80);
		playerSevenPanel.add(p7c1Label);

		p7c2Label = new JLabel("Card 2\r\n");
		p7c2Label.setBounds(73, 5, 53, 80);
		playerSevenPanel.add(p7c2Label);

		p7NameLabel = new JLabel("Player1\r\n");
		p7NameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		p7NameLabel.setBounds(10, 96, 115, 14);
		playerSevenPanel.add(p7NameLabel);

		p7CreditsLabel = new JLabel("Credits:");
		p7CreditsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		p7CreditsLabel.setBounds(10, 119, 53, 14);
		playerSevenPanel.add(p7CreditsLabel);

		p7AvailableCreditsLabel = new JLabel("xxx");
		p7AvailableCreditsLabel.setBounds(72, 119, 53, 14);
		playerSevenPanel.add(p7AvailableCreditsLabel);

		// Player eight
		playerEightPanel = new JPanel();
		playerEightPanel.setLayout(null);
		playerEightPanel.setBounds(0, 202, 135, 144);
		gamePanel.add(playerEightPanel);

		p8c1Label = new JLabel("Card 1");
		p8c1Label.setBounds(10, 5, 53, 80);
		playerEightPanel.add(p8c1Label);

		p8c2Label = new JLabel("Card 2\r\n");
		p8c2Label.setBounds(73, 5, 53, 80);
		playerEightPanel.add(p8c2Label);

		p8NameLabel = new JLabel("Player1\r\n");
		p8NameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		p8NameLabel.setBounds(10, 96, 115, 14);
		playerEightPanel.add(p8NameLabel);

		p8CreditsLabel = new JLabel("Credits:");
		p8CreditsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		p8CreditsLabel.setBounds(10, 119, 53, 14);
		playerEightPanel.add(p8CreditsLabel);

		p8AvailableCreditsLabel = new JLabel("xxx");
		p8AvailableCreditsLabel.setBounds(72, 119, 53, 14);
		playerEightPanel.add(p8AvailableCreditsLabel);

		// Player nine
		playerNinePanel = new JPanel();
		playerNinePanel.setBounds(0, 0, 135, 144);
		gamePanel.add(playerNinePanel);
		playerNinePanel.setLayout(null);

		p9c1Label = new JLabel("Card 1");
		p9c1Label.setBounds(10, 5, 53, 80);
		playerNinePanel.add(p9c1Label);

		p9c2Label = new JLabel("Card 2\r\n");
		p9c2Label.setBounds(73, 5, 53, 80);
		playerNinePanel.add(p9c2Label);

		p9NameLabel = new JLabel("Player1\r\n");
		p9NameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		p9NameLabel.setBounds(10, 96, 115, 14);
		playerNinePanel.add(p9NameLabel);

		p9CreditsLabel = new JLabel("Credits:");
		p9CreditsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		p9CreditsLabel.setBounds(10, 119, 53, 14);
		playerNinePanel.add(p9CreditsLabel);

		p9AvailableCreditsLabel = new JLabel("xxx");
		p9AvailableCreditsLabel.setBounds(72, 119, 53, 14);
		playerNinePanel.add(p9AvailableCreditsLabel);

		// Player ten
		playerTenPanel = new JPanel();
		playerTenPanel.setLayout(null);
		playerTenPanel.setBounds(297, 0, 135, 144);
		gamePanel.add(playerTenPanel);

		p10c1Label = new JLabel("Card 1");
		p10c1Label.setBounds(10, 5, 53, 80);
		playerTenPanel.add(p10c1Label);

		p10c2Label = new JLabel("Card 2\r\n");
		p10c2Label.setBounds(73, 5, 53, 80);
		playerTenPanel.add(p10c2Label);

		p10NameLabel = new JLabel("Player1\r\n");
		p10NameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		p10NameLabel.setBounds(10, 96, 115, 14);
		playerTenPanel.add(p10NameLabel);

		p10CreditsLabel = new JLabel("Credits:");
		p10CreditsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		p10CreditsLabel.setBounds(10, 119, 53, 14);
		playerTenPanel.add(p10CreditsLabel);

		p10AvailableCreditsLabel = new JLabel("xxx");
		p10AvailableCreditsLabel.setBounds(72, 119, 53, 14);
		playerTenPanel.add(p10AvailableCreditsLabel);

		shownCardsPanel = new JPanel();
		shownCardsPanel.setBounds(297, 202, 418, 144);
		gamePanel.add(shownCardsPanel);
		shownCardsPanel.setLayout(null);

		flopc1Label = new JLabel("Card 1");
		flopc1Label.setBounds(10, 11, 53, 80);
		shownCardsPanel.add(flopc1Label);

		flopc2Label = new JLabel("Card 1");
		flopc2Label.setBounds(89, 11, 53, 80);
		shownCardsPanel.add(flopc2Label);

		flopc3Label = new JLabel("Card 1");
		flopc3Label.setBounds(182, 11, 53, 80);
		shownCardsPanel.add(flopc3Label);

		turnc1Label = new JLabel("Card 1");
		turnc1Label.setBounds(260, 11, 53, 80);
		shownCardsPanel.add(turnc1Label);

		riverc1Label = new JLabel("Card 1");
		riverc1Label.setBounds(355, 11, 53, 80);
		shownCardsPanel.add(riverc1Label);
	}
}