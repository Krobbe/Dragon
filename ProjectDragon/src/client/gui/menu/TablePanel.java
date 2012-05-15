package client.gui.menu;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import model.card.ICard;
import model.player.Bet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import client.event.*;
import model.player.*;

@SuppressWarnings("serial")
public class TablePanel extends JPanel implements client.event.EventHandler,
		ActionListener {

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

	private JPanel tableInfo;
	private JLabel flopc1Label;
	private JLabel flopc2Label;
	private JLabel flopc3Label;
	private JLabel turnc1Label;
	private JLabel riverc1Label;
	private JLabel potSize;
	private JLabel potSizeInfoLabel;
	private JLabel currentBet;
	private JLabel currentBetInfoLabel;
	
	private client.model.game.Table table;

	/**
	 * Create the application.
	 */
	public TablePanel() {
		initialize();
		client.event.EventBus.register(this);
	}
	
	public TablePanel(client.model.game.Table table) {
		initialize();
		this.table = table;
		client.event.EventBus.register(this);
	}

	@Override
	public void onEvent(Event evt) {
		
		List<IPlayer> allPlayers = table.getPlayers();
    	
		switch (evt.getTag()) {
		
		case CURRENT_BET_CHANGED:
			int bet = table.getRound().getBettingRound().getCurrentBet().getValue();
			currentBet.setText("" + bet);
			break;
			
		case POT_CHANGED:
			int potValue = table.getRound().getPot().getValue();
			potSize.setText("" + potValue);
			break;
			
		case HAND_DISCARDED:
			IPlayer handDiscardedPlayer;
			if (!(evt.getValue() instanceof IPlayer)) {
				System.out.println("Wrong evt.getValue() for evt.getTag(): "
						+ evt.getTag());
			} else {
				handDiscardedPlayer = (IPlayer) evt.getValue();
				int handDiscardedPlayerIndex = -1;

				// TODO: om man fick in ett index fr�n b�rjan ist hade inte
				// f�ljand varit n�dv�ndigt:
				/* get the index of the player whos hand was discarded */
				for (int i = 0; i < allPlayers.size(); i++) {
					if (handDiscardedPlayer.equals(allPlayers.get(i))) {
						handDiscardedPlayerIndex = i;
						break;
					}
				}

				/* handle the correct panels */
				switch (handDiscardedPlayerIndex) {
				case 0:
					// hantera panel nr 0.
					break;

				}
			
			/* handle the correct panels */
			switch (handDiscardedPlayerIndex) {
			case 0:
				
				p1c1Label.setText(null);
				p1c2Label.setText(null);
				playerOnePanel.setBackground(Color.red);
				
				break;
				
			case 1:
				
				p2c1Label.setText(null);
				p2c2Label.setText(null);
				playerTwoPanel.setBackground(Color.red);
				
				break;
				
			case 2:
				
				p3c1Label.setText(null);
				p3c2Label.setText(null);
				playerThreePanel.setBackground(Color.red);
				
				break;
				
			case 3:
				
				p4c1Label.setText(null);
				p4c2Label.setText(null);
				playerFourPanel.setBackground(Color.red);
				
				break;
				
			case 4:
				
				p5c1Label.setText(null);
				p5c2Label.setText(null);
				playerFivePanel.setBackground(Color.red);
				
				break;
				
			case 5:
				
				p6c1Label.setText(null);
				p6c2Label.setText(null);
				playerSixPanel.setBackground(Color.red);
				
				break;
				
			case 6:
				
				p7c1Label.setText(null);
				p7c2Label.setText(null);
				playerSevenPanel.setBackground(Color.red);
				
				break;
				
			case 7:
				
				p8c1Label.setText(null);
				p8c2Label.setText(null);
				playerEightPanel.setBackground(Color.red);
				
				break;
				
			case 8:
				
				p9c1Label.setText(null);
				p9c2Label.setText(null);
				playerNinePanel.setBackground(Color.red);
				
				break;
				
			case 9:
				
				p10c1Label.setText(null);
				p10c2Label.setText(null);
				playerTenPanel.setBackground(Color.red);
				
				break;
				
			default:
				break;

			}
			
			
			}
			
		
		case BALANCE_CHANGED:
			
			IPlayer balanceChangedPlayer = (Player) evt.getValue();
			int balanceChangedPlayerIndex = -1;
			
			//TODO: om man fick in ett index fr�n b�rjan ist hade inte f�ljand varit n�dv�ndigt:
			/* get the index of the player whos balance was changed */
			for(int i = 0; i < allPlayers.size(); i++) {
				if(balanceChangedPlayer.equals(allPlayers.get(i))) {
					balanceChangedPlayerIndex = i;
					break;
				}
			}
			
			switch (balanceChangedPlayerIndex) {
			case 0:
				
				p1CreditsLabel.setText(balanceChangedPlayer.getBalance().toString());
				
				break;
				
			case 1:
				
				p2CreditsLabel.setText((balanceChangedPlayer.getBalance().toString()));
				
				break;
				
			case 2:
				
				p3CreditsLabel.setText((balanceChangedPlayer.getBalance().toString()));
				
				break;
				
			case 3:
				
				p4CreditsLabel.setText((balanceChangedPlayer.getBalance().toString()));
				
				break;
				
			case 4:
				
				p5CreditsLabel.setText((balanceChangedPlayer.getBalance().toString()));
				
				break;
				
			case 5:
				
				p6CreditsLabel.setText((balanceChangedPlayer.getBalance().toString()));
				
				break;
				
			case 6:
				
				p7CreditsLabel.setText((balanceChangedPlayer.getBalance().toString()));
				
				break;
				
			case 7:
				
				p8CreditsLabel.setText((balanceChangedPlayer.getBalance().toString()));
				
				break;
				
			case 8:
				
				p9CreditsLabel.setText((balanceChangedPlayer.getBalance().toString()));
				
				break;
				
			case 9:
				
				p10CreditsLabel.setText((balanceChangedPlayer.getBalance().toString()));
				
				break;
			default:
				break;
			}
			
			break;
			
		case OWN_CURRENT_BET_CHANGED:
			Bet ownCurrentBet;
			if (!(evt.getValue() instanceof Bet)) {
				System.out.println("Wrong evt.getValue() for evt.getTag(): "
						+ evt.getTag());
			} else {
				ownCurrentBet = (Bet)evt.getValue();
				IPlayer betOwner = ownCurrentBet.getOwner();
				int tmp = 0;
				for(IPlayer player : allPlayers) {
					tmp++;
					if(player.equals(betOwner)) {
						//TODO change bet
						break;
					}
				}
			}
			break;
			
		case TURN_CHANGED:
			
			int turnIndex = (Integer) evt.getValue();
			
			switch (turnIndex) {
			case 0:
				
				//hantera att det �r 0's tur
				
				break;
				
			case 1:
				
				//hantera att det �r 1's tur

			default:
				break;
			}
			
			break;
			
		case HANDS_CHANGED:
			
			if (!(evt.getValue() instanceof Player)) {
				System.out.println("Wrong evt.getValue() for evt.getTag(): "
						+ evt.getTag());
			} else {

				for (IPlayer acp : table.getActivePlayers()) {
					int index = allPlayers.indexOf(acp);

					switch (index) {
					case 0:

						// s�tt panelerna p� plats 0's kort. h�mta fr�n
						// allPlayers.get(0).getHand()

						break;

					case 1:

						// s�tt panelerna p� plats 0's kort. h�mta fr�n
						// allPlayers.get(1).getHand()

					default:
						break;
					}

				}

			}
			
			break;
			
		//TODO: snygga till denna?	
		case COMMUNITY_CARDS_CHANGED:
			List<ICard> communityCards = table.getCommunityCards();
				switch (communityCards.size()) {

				case 0:
					// S�tt bakgrund till gr�n eller liknanade...
					break;

				case 3:
					// sett paneler till korttext genom: cards.get(0).toString()
					break;

				case 4:
					// sett paneler till korttext genom: cards.get(0).toString()
					break;

				case 5:
					// sett paneler till korttext genom: cards.get(0).toString()
					break;

				default:
					break;
				}
				break;
		default:
			break;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Should the result be saved here somehow?
		
		if (e.getSource().equals(leaveTableButton)) {
			
			EventBus.publish(new Event(Event.Tag.GO_TO_MAIN, 1));
			
		} else if (e.getSource().equals(userCheckButton)) {
			
			EventBus.publish(new Event(Event.Tag.REQUEST_CHECK, 1));
			
		} else if (e.getSource().equals(userFoldButton)) {
			
			EventBus.publish(new Event(Event.Tag.REQUEST_FOLD, 1));
			
		} else if (e.getSource().equals(userRaiseButton)) {
			
			EventBus.publish(new Event(Event.Tag.REQUEST_RAISE, Integer
					.parseInt(userBetField.getText())));
			
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

		tableInfo = new JPanel();
		tableInfo.setBackground(Color.green);
		tableInfo.setBounds(297, 202, 418, 144);
		gamePanel.add(tableInfo);
		tableInfo.setLayout(null);

		flopc1Label = new JLabel("Card 1");
		flopc1Label.setBounds(10, 11, 53, 80);
		tableInfo.add(flopc1Label);

		flopc2Label = new JLabel("Card 1");
		flopc2Label.setBounds(89, 11, 53, 80);
		tableInfo.add(flopc2Label);

		flopc3Label = new JLabel("Card 1");
		flopc3Label.setBounds(182, 11, 53, 80);
		tableInfo.add(flopc3Label);

		turnc1Label = new JLabel("Card 1");
		turnc1Label.setBounds(260, 11, 53, 80);
		tableInfo.add(turnc1Label);

		riverc1Label = new JLabel("Card 1");
		riverc1Label.setBounds(355, 11, 53, 80);
		tableInfo.add(riverc1Label);

		potSizeInfoLabel= new JLabel("Pot size:");
		potSizeInfoLabel.setBounds(10, 120, 53, 15);
		tableInfo.add(potSizeInfoLabel);
		
		potSize = new JLabel("xxx");
		potSize.setBounds(89, 120, 53, 15);
		tableInfo.add(potSize);
		
		currentBetInfoLabel = new JLabel("Current bet:");
		currentBetInfoLabel.setBounds(260, 120, 70, 15);
		tableInfo.add(currentBetInfoLabel);
		
		currentBet = new JLabel("xxx");
		currentBet.setBounds(355, 120, 53, 15);
		tableInfo.add(currentBet);
		
	}
}