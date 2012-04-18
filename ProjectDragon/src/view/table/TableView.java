package view.table;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TableView {

	private JFrame frame;
	private JTextField userBetField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TableView window = new TableView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TableView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1024, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel tablePanel = new JPanel();
		tablePanel.setBounds(0, 0, 1008, 730);
		frame.getContentPane().add(tablePanel);
		tablePanel.setLayout(null);
		
		JButton leaveTableButton = new JButton("Leave table");
		leaveTableButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				notifyAll();
			}
		});
		leaveTableButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		leaveTableButton.setBounds(10, 683, 111, 36);
		tablePanel.add(leaveTableButton);
		
		JPanel callPanel = new JPanel();
		callPanel.setBounds(666, 571, 332, 148);
		tablePanel.add(callPanel);
		callPanel.setLayout(null);
		
		JLabel userAvailableCreditsLabel = new JLabel("xxx");
		userAvailableCreditsLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		userAvailableCreditsLabel.setBounds(168, 33, 60, 19);
		callPanel.add(userAvailableCreditsLabel);
		
		JButton userFoldButton = new JButton("Fold");
		userFoldButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				notifyAll();
			}
		});
		userFoldButton.setBounds(232, 108, 100, 40);
		callPanel.add(userFoldButton);
		userFoldButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton userCheckButton = new JButton("Check");
		userCheckButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				notifyAll();
			}
		});
		userCheckButton.setBounds(0, 108, 100, 40);
		callPanel.add(userCheckButton);
		userCheckButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton userRaiseButton = new JButton("Raise");
		userRaiseButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				notifyAll();
			}
		});
		userRaiseButton.setBounds(116, 108, 100, 40);
		callPanel.add(userRaiseButton);
		userRaiseButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		userBetField = new JTextField();
		userBetField.setBounds(116, 63, 100, 40);
		callPanel.add(userBetField);
		userBetField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		userBetField.setColumns(10);
		
		JLabel userCreditsLabel = new JLabel("Credits:");
		userCreditsLabel.setBounds(103, 32, 55, 20);
		callPanel.add(userCreditsLabel);
		userCreditsLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JPanel gamePanel = new JPanel();
		gamePanel.setBounds(10, 11, 988, 549);
		tablePanel.add(gamePanel);
		gamePanel.setLayout(null);
		
		JPanel playerOnePanel = new JPanel();
		playerOnePanel.setLayout(null);
		playerOnePanel.setBounds(580, 0, 135, 144);
		gamePanel.add(playerOnePanel);
		
		JLabel p1c1Label = new JLabel("Card 1");
		p1c1Label.setBounds(10, 5, 53, 80);
		playerOnePanel.add(p1c1Label);
		
		JLabel p1c2Label = new JLabel("Card 2\r\n");
		p1c2Label.setBounds(73, 5, 53, 80);
		playerOnePanel.add(p1c2Label);
		
		JLabel p1NameLabel = new JLabel("Player1\r\n");
		p1NameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		p1NameLabel.setBounds(10, 96, 115, 14);
		playerOnePanel.add(p1NameLabel);
		
		JLabel p1CreditsLabel = new JLabel("Credits:");
		p1CreditsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		p1CreditsLabel.setBounds(10, 119, 53, 14);
		playerOnePanel.add(p1CreditsLabel);
		
		JLabel p1AvailableCreditsLabel = new JLabel("xxx");
		p1AvailableCreditsLabel.setBounds(72, 119, 53, 14);
		playerOnePanel.add(p1AvailableCreditsLabel);
		
		JPanel playerTwoPanel = new JPanel();
		playerTwoPanel.setLayout(null);
		playerTwoPanel.setBounds(853, 0, 135, 144);
		gamePanel.add(playerTwoPanel);
		
		JLabel p2c1Label = new JLabel("Card 1");
		p2c1Label.setBounds(10, 5, 53, 80);
		playerTwoPanel.add(p2c1Label);
		
		JLabel p2c2Label = new JLabel("Card 2\r\n");
		p2c2Label.setBounds(73, 5, 53, 80);
		playerTwoPanel.add(p2c2Label);
		
		JLabel p2NameLabel = new JLabel("Player1\r\n");
		p2NameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		p2NameLabel.setBounds(10, 96, 115, 14);
		playerTwoPanel.add(p2NameLabel);
		
		JLabel p2CreditsLabel = new JLabel("Credits:");
		p2CreditsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		p2CreditsLabel.setBounds(10, 119, 53, 14);
		playerTwoPanel.add(p2CreditsLabel);
		
		JLabel p2AvailableCreditsLabel = new JLabel("xxx");
		p2AvailableCreditsLabel.setBounds(72, 119, 53, 14);
		playerTwoPanel.add(p2AvailableCreditsLabel);
		
		JPanel playerThreePanel = new JPanel();
		playerThreePanel.setLayout(null);
		playerThreePanel.setBounds(853, 202, 135, 144);
		gamePanel.add(playerThreePanel);
		
		JLabel p3c1Label = new JLabel("Card 1");
		p3c1Label.setBounds(10, 5, 53, 80);
		playerThreePanel.add(p3c1Label);
		
		JLabel p3c2Label = new JLabel("Card 2\r\n");
		p3c2Label.setBounds(73, 5, 53, 80);
		playerThreePanel.add(p3c2Label);
		
		JLabel p3NameLabel = new JLabel("Player1\r\n");
		p3NameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		p3NameLabel.setBounds(10, 96, 115, 14);
		playerThreePanel.add(p3NameLabel);
		
		JLabel p3CreditsLabel = new JLabel("Credits:");
		p3CreditsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		p3CreditsLabel.setBounds(10, 119, 53, 14);
		playerThreePanel.add(p3CreditsLabel);
		
		JLabel p3AvailableCredits = new JLabel("xxx");
		p3AvailableCredits.setBounds(72, 119, 53, 14);
		playerThreePanel.add(p3AvailableCredits);
		
		JPanel playerFourPanel = new JPanel();
		playerFourPanel.setLayout(null);
		playerFourPanel.setBounds(853, 405, 135, 144);
		gamePanel.add(playerFourPanel);
		
		JLabel p4c1Label = new JLabel("Card 1");
		p4c1Label.setBounds(10, 5, 53, 80);
		playerFourPanel.add(p4c1Label);
		
		JLabel p4c2Label = new JLabel("Card 2\r\n");
		p4c2Label.setBounds(73, 5, 53, 80);
		playerFourPanel.add(p4c2Label);
		
		JLabel p4NameLabel = new JLabel("Player1\r\n");
		p4NameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		p4NameLabel.setBounds(10, 96, 115, 14);
		playerFourPanel.add(p4NameLabel);
		
		JLabel p4CreditsLabel = new JLabel("Credits:");
		p4CreditsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		p4CreditsLabel.setBounds(10, 119, 53, 14);
		playerFourPanel.add(p4CreditsLabel);
		
		JLabel p4AvailableCreditsLabel = new JLabel("xxx");
		p4AvailableCreditsLabel.setBounds(72, 119, 53, 14);
		playerFourPanel.add(p4AvailableCreditsLabel);
		
		JPanel playerFivePanel = new JPanel();
		playerFivePanel.setLayout(null);
		playerFivePanel.setBounds(580, 405, 135, 144);
		gamePanel.add(playerFivePanel);
		
		JLabel p5c1Label = new JLabel("Card 1");
		p5c1Label.setBounds(10, 5, 53, 80);
		playerFivePanel.add(p5c1Label);
		
		JLabel p5c2Label = new JLabel("Card 2\r\n");
		p5c2Label.setBounds(73, 5, 53, 80);
		playerFivePanel.add(p5c2Label);
		
		JLabel p5NameLabel = new JLabel("Player1\r\n");
		p5NameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		p5NameLabel.setBounds(10, 96, 115, 14);
		playerFivePanel.add(p5NameLabel);
		
		JLabel p5CreditsLabel = new JLabel("Credits:");
		p5CreditsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		p5CreditsLabel.setBounds(10, 119, 53, 14);
		playerFivePanel.add(p5CreditsLabel);
		
		JLabel p5AvailableCreditsLabel = new JLabel("xxx");
		p5AvailableCreditsLabel.setBounds(72, 119, 53, 14);
		playerFivePanel.add(p5AvailableCreditsLabel);
		
		JPanel playerSixPanel = new JPanel();
		playerSixPanel.setLayout(null);
		playerSixPanel.setBounds(297, 405, 135, 144);
		gamePanel.add(playerSixPanel);
		
		JLabel p6c1Label = new JLabel("Card 1");
		p6c1Label.setBounds(10, 5, 53, 80);
		playerSixPanel.add(p6c1Label);
		
		JLabel p6c2Label = new JLabel("Card 2\r\n");
		p6c2Label.setBounds(73, 5, 53, 80);
		playerSixPanel.add(p6c2Label);
		
		JLabel p6NameLabel = new JLabel("Player1\r\n");
		p6NameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		p6NameLabel.setBounds(10, 96, 115, 14);
		playerSixPanel.add(p6NameLabel);
		
		JLabel p6CreditsLabel = new JLabel("Credits:");
		p6CreditsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		p6CreditsLabel.setBounds(10, 119, 53, 14);
		playerSixPanel.add(p6CreditsLabel);
		
		JLabel p6AvailableCreditsLabel = new JLabel("xxx");
		p6AvailableCreditsLabel.setBounds(72, 119, 53, 14);
		playerSixPanel.add(p6AvailableCreditsLabel);
		
		JPanel playerSevenPanel = new JPanel();
		playerSevenPanel.setLayout(null);
		playerSevenPanel.setBounds(0, 405, 135, 144);
		gamePanel.add(playerSevenPanel);
		
		JLabel p7c1Label = new JLabel("Card 1");
		p7c1Label.setBounds(10, 5, 53, 80);
		playerSevenPanel.add(p7c1Label);
		
		JLabel p7c2Label = new JLabel("Card 2\r\n");
		p7c2Label.setBounds(73, 5, 53, 80);
		playerSevenPanel.add(p7c2Label);
		
		JLabel p7NameLabel = new JLabel("Player1\r\n");
		p7NameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		p7NameLabel.setBounds(10, 96, 115, 14);
		playerSevenPanel.add(p7NameLabel);
		
		JLabel p7CreditsLabel = new JLabel("Credits:");
		p7CreditsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		p7CreditsLabel.setBounds(10, 119, 53, 14);
		playerSevenPanel.add(p7CreditsLabel);
		
		JLabel p7AvailableCreditsLabel = new JLabel("xxx");
		p7AvailableCreditsLabel.setBounds(72, 119, 53, 14);
		playerSevenPanel.add(p7AvailableCreditsLabel);
		
		JPanel playerEightPanel = new JPanel();
		playerEightPanel.setLayout(null);
		playerEightPanel.setBounds(0, 202, 135, 144);
		gamePanel.add(playerEightPanel);
		
		JLabel p8c1Label = new JLabel("Card 1");
		p8c1Label.setBounds(10, 5, 53, 80);
		playerEightPanel.add(p8c1Label);
		
		JLabel p8c2Label = new JLabel("Card 2\r\n");
		p8c2Label.setBounds(73, 5, 53, 80);
		playerEightPanel.add(p8c2Label);
		
		JLabel p8NameLabel = new JLabel("Player1\r\n");
		p8NameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		p8NameLabel.setBounds(10, 96, 115, 14);
		playerEightPanel.add(p8NameLabel);
		
		JLabel p8CreditsLabel = new JLabel("Credits:");
		p8CreditsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		p8CreditsLabel.setBounds(10, 119, 53, 14);
		playerEightPanel.add(p8CreditsLabel);
		
		JLabel p8AvailableCreditsLabel = new JLabel("xxx");
		p8AvailableCreditsLabel.setBounds(72, 119, 53, 14);
		playerEightPanel.add(p8AvailableCreditsLabel);
		
		JPanel playerNinePanel = new JPanel();
		playerNinePanel.setBounds(0, 0, 135, 144);
		gamePanel.add(playerNinePanel);
		playerNinePanel.setLayout(null);
		
		JLabel p9c1Label = new JLabel("Card 1");
		p9c1Label.setBounds(10, 5, 53, 80);
		playerNinePanel.add(p9c1Label);
		
		JLabel p9c2Label = new JLabel("Card 2\r\n");
		p9c2Label.setBounds(73, 5, 53, 80);
		playerNinePanel.add(p9c2Label);
		
		JLabel p9NameLabel = new JLabel("Player1\r\n");
		p9NameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		p9NameLabel.setBounds(10, 96, 115, 14);
		playerNinePanel.add(p9NameLabel);
		
		JLabel p9CreditsLabel = new JLabel("Credits:");
		p9CreditsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		p9CreditsLabel.setBounds(10, 119, 53, 14);
		playerNinePanel.add(p9CreditsLabel);
		
		JLabel p9AvailableCreditsLabel = new JLabel("xxx");
		p9AvailableCreditsLabel.setBounds(72, 119, 53, 14);
		playerNinePanel.add(p9AvailableCreditsLabel);
		
		JPanel playerTenPanel = new JPanel();
		playerTenPanel.setLayout(null);
		playerTenPanel.setBounds(297, 0, 135, 144);
		gamePanel.add(playerTenPanel);
		
		JLabel p10c1Label = new JLabel("Card 1");
		p10c1Label.setBounds(10, 5, 53, 80);
		playerTenPanel.add(p10c1Label);
		
		JLabel p10c2Label = new JLabel("Card 2\r\n");
		p10c2Label.setBounds(73, 5, 53, 80);
		playerTenPanel.add(p10c2Label);
		
		JLabel p10NameLabel = new JLabel("Player1\r\n");
		p10NameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		p10NameLabel.setBounds(10, 96, 115, 14);
		playerTenPanel.add(p10NameLabel);
		
		JLabel p10CreditsLabel = new JLabel("Credits:");
		p10CreditsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		p10CreditsLabel.setBounds(10, 119, 53, 14);
		playerTenPanel.add(p10CreditsLabel);
		
		JLabel p10AvailableCreditsLabel = new JLabel("xxx");
		p10AvailableCreditsLabel.setBounds(72, 119, 53, 14);
		playerTenPanel.add(p10AvailableCreditsLabel);
		
		JPanel shownCardsPanel = new JPanel();
		shownCardsPanel.setBounds(297, 202, 418, 144);
		gamePanel.add(shownCardsPanel);
		shownCardsPanel.setLayout(null);
		
		JLabel flopc1Label = new JLabel("Card 1");
		flopc1Label.setBounds(10, 11, 53, 80);
		shownCardsPanel.add(flopc1Label);
		
		JLabel flopc2Label = new JLabel("Card 1");
		flopc2Label.setBounds(89, 11, 53, 80);
		shownCardsPanel.add(flopc2Label);
		
		JLabel flopc3Label = new JLabel("Card 1");
		flopc3Label.setBounds(182, 11, 53, 80);
		shownCardsPanel.add(flopc3Label);
		
		JLabel turnc1Label = new JLabel("Card 1");
		turnc1Label.setBounds(260, 11, 53, 80);
		shownCardsPanel.add(turnc1Label);
		
		JLabel riverc1Label = new JLabel("Card 1");
		riverc1Label.setBounds(355, 11, 53, 80);
		shownCardsPanel.add(riverc1Label);
	}
}
