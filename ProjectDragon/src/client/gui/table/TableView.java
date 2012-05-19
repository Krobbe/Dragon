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
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import common.model.card.ICard;
import common.model.player.Bet;
import common.model.player.IPlayer;
import common.model.player.Player;


import client.event.*;

import client.model.game.*;

/**
 * This is the main table panel. It is in this panel that all other panels are created and added.
 * @author forssenm
 *
 */
public class TableView implements EventHandler, ActionListener{
	
	private Table table;
	private JFrame frame;
	private JPanel backPanel;
	
	private JPanel northPanel, southPanel, westPanel, eastPanel, centerPanel;
	private JPanel northPanel1, northPanel2;
	/*
	private PlayerOnePanel playerOnePanel;
	private PlayerTwoPanel playerTwoPanel;
	private PlayerThreePanel playerThreePanel;
	private PlayerFourPanel playerFourPanel;
	private PlayerFivePanel playerFivePanel;
	private PlayerSixPanel playerSixPanel;
	private PlayerSevenPanel playerSevenPanel;
	private PlayerEightPanel playerEightPanel;
	private PlayerNinePanel playerNinePanel;
	private PlayerTenPanel playerTenPanel;
	*/
	private PlayerPanel player1Panel, player2Panel, player3Panel, player4Panel, 
	player5Panel, player6Panel, player7Panel, player8Panel, player9Panel
	, player10Panel;

	private TableInfoPanel tableInfoPanel;
	private UserBetPanel userBetPanel;
	private List<PlayerPanel> playerPanelList;
	
	private JButton leaveTableButton;
	
	private int frameHeight = P.INSTANCE.getFrameHeight();
	private int frameWidth = P.INSTANCE.getFrameWidth();
	
	/**
	 * Creates the panel
	 * @param table It takes a table as parameter to be able to set names of player etc
	 */
	public TableView(Table table) {
		this.table = table;
		init();
		EventBus.register(this);
	}
	
	private void init() {
		frame = new JFrame();
		frame.setSize(frameWidth, frameHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FlowLayout());	
		
		backPanel = new JPanel();
		backPanel.setSize(frameWidth, frameHeight);
		backPanel.setLayout(new BorderLayout());
		
		northPanel = new JPanel();
		southPanel = new JPanel();
		westPanel = new JPanel();
		eastPanel = new JPanel();
		centerPanel = new JPanel();
		northPanel1 = new JPanel();
		northPanel2 = new JPanel();
		
		northPanel.setLayout(new BorderLayout());
		southPanel.setLayout(new FlowLayout());
		centerPanel.setLayout(new BorderLayout());
		westPanel.setLayout(new FlowLayout());
		eastPanel.setLayout(new FlowLayout());
		northPanel1.setLayout(new FlowLayout());
		northPanel2.setLayout(new FlowLayout());
		
		southPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		northPanel2.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		leaveTableButton = new JButton("Leave table");
		leaveTableButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		leaveTableButton.setPreferredSize(new Dimension(111,40));
		//leaveTableButton.setBounds(100, 683, 111, 40);
		
		/*
		playerOnePanel = new PlayerOnePanel();
		playerTwoPanel = new PlayerTwoPanel();
		playerThreePanel = new PlayerThreePanel();
		playerFourPanel = new PlayerFourPanel();
		playerFivePanel = new PlayerFivePanel();
		playerSixPanel = new PlayerSixPanel();
		playerSevenPanel = new PlayerSevenPanel();
		playerEightPanel = new PlayerEightPanel();
		playerNinePanel = new PlayerNinePanel();
		playerTenPanel = new PlayerTenPanel();
		
		playerOnePanel.setName(table.getPlayers().get(0));
		
		playerPanelList = new ArrayList<IPlayerPanel>();
		playerPanelList.add(playerOnePanel);
		playerPanelList.add(playerTwoPanel);
		playerPanelList.add(playerThreePanel);
		playerPanelList.add(playerFourPanel);
		playerPanelList.add(playerFivePanel);
		playerPanelList.add(playerSixPanel);
		playerPanelList.add(playerSevenPanel);
		playerPanelList.add(playerEightPanel);
		playerPanelList.add(playerNinePanel);
		playerPanelList.add(playerTenPanel);
		*/
		
		player1Panel = new PlayerPanel();
		player2Panel = new PlayerPanel();
		player3Panel = new PlayerPanel();
		player4Panel = new PlayerPanel();
		player5Panel = new PlayerPanel();
		player6Panel = new PlayerPanel();
		player7Panel = new PlayerPanel();
		player8Panel = new PlayerPanel();
		player9Panel = new PlayerPanel();
		player10Panel = new PlayerPanel();
		
		
		playerPanelList = new ArrayList<PlayerPanel>();
		playerPanelList.add(player1Panel);
		playerPanelList.add(player2Panel);
		playerPanelList.add(player3Panel);
		playerPanelList.add(player4Panel);
		playerPanelList.add(player5Panel);
		playerPanelList.add(player6Panel);
		playerPanelList.add(player7Panel);
		playerPanelList.add(player8Panel);
		playerPanelList.add(player9Panel);
		playerPanelList.add(player10Panel);
		
		System.out.println("panel: " + playerPanelList.get(0));
		
		tableInfoPanel = new TableInfoPanel();
		userBetPanel = new UserBetPanel();
		/*
		for(IPlayerPanel p : playerPanelList) {
			//TODO Will this work?
			backPanel.add((JPanel)p);
		}
		*/
		
		westPanel.add(player8Panel);
		southPanel.add(player9Panel);
		southPanel.add(player10Panel);
		southPanel.add(player1Panel);
		southPanel.add(player2Panel);
		eastPanel.add(player3Panel);
		northPanel2.add(player7Panel);
		northPanel2.add(player6Panel);
		northPanel2.add(player5Panel);
		northPanel2.add(player4Panel);
		
		northPanel1.add(leaveTableButton);
		
		centerPanel.add(tableInfoPanel, BorderLayout.WEST);
		centerPanel.add(userBetPanel, BorderLayout.EAST);
		northPanel.add(northPanel1, BorderLayout.NORTH);
		northPanel.add(northPanel2, BorderLayout.SOUTH);
		
		backPanel.add(centerPanel, BorderLayout.CENTER);
		backPanel.add(northPanel, BorderLayout.NORTH);
		backPanel.add(southPanel, BorderLayout.SOUTH);
		backPanel.add(westPanel, BorderLayout.WEST);
		backPanel.add(eastPanel, BorderLayout.EAST);
		
		frame.add(backPanel);
		
		frame.setVisible(true);
		frame.setResizable(true);
	}

	@Override
	public void onEvent(Event evt) {
		List<IPlayer> allPlayers = table.getPlayers();
		
		switch (evt.getTag()) {
		
		case CURRENT_BET_CHANGED:
			int bet = table.getRound().getBettingRound().getCurrentBet().getValue();
			tableInfoPanel.setBet(Integer.toString(bet));
			break;
			
		case POT_CHANGED:
			int potValue = table.getRound().getPot().getValue();
			tableInfoPanel.setPotSize(Integer.toString(potValue));
			break;
		
		case HAND_DISCARDED:
			IPlayer handDiscardedPlayer = (Player) evt.getValue();
			for(int i = 0; i < allPlayers.size(); i++) {
				if(handDiscardedPlayer.equals(allPlayers.get(i))) {
					playerPanelList.get(i).discard();
					playerPanelList.get(i).setBackground(Color.red);
					break;
				}
			}
			break;
			
		case BALANCE_CHANGED:
			
			IPlayer balanceChangedPlayer = (Player) evt.getValue();
			
			//TODO: om man fick in ett index fr�n b�rjan ist hade inte f�ljand varit n�dv�ndigt:
			/* get the index of the player whos balance was changed */
			for(int i = 0; i < allPlayers.size(); i++) {
				if(balanceChangedPlayer.equals(allPlayers.get(i))) {
					playerPanelList.get(i).setBalance(balanceChangedPlayer.getBalance().toString());
					break;
				}
			}
			break;
			
			//TODO What is this case supposed to do? Change player.owncurrentbet
//		case OWN_CURRENT_BET_CHANGED:
//			Bet ownCurrentBet;
//			if (!(evt.getValue() instanceof Bet)) {
//				System.out.println("Wrong evt.getValue() for evt.getTag(): "
//						+ evt.getTag());
//			} else {
//				ownCurrentBet = (Bet)evt.getValue();
//				IPlayer betOwner = ownCurrentBet.getOwner();
//				for(int i = 0; i < allPlayers.size(); i++) {
//					if(allPlayers.get(i).equals(betOwner)) {
//						playerPanelList.get(i).setBalance()
//						break;
//					}
//				}
//			}
//			break;
			
		case TURN_CHANGED:
			int turnIndex = (Integer) evt.getValue();
			playerPanelList.get(turnIndex).setBackground(Color.green);
			if(turnIndex == 1) {
				playerPanelList.get(10).setBackground(Color.gray);
			}
			else {
				playerPanelList.get(turnIndex-1).setBackground(Color.gray);
			}
			
			List<String> legalButtons = table.getLegalButtons();
			//TODO: hantera call, check, fold och raise knappar. listan inneh�ller
			// str�ngar med vilka knappar som man ska kunna trycka p�.
			break;
			
		case HANDS_CHANGED:
			
			if (!(evt.getValue() instanceof Player)) {
				System.out.println("Wrong evt.getValue() for evt.getTag(): "
						+ evt.getTag());
			} else {
				for (IPlayer acp : table.getActivePlayers()) {
					int index = allPlayers.indexOf(acp);
					playerPanelList.get(index).showCards(allPlayers.get(index).getHand());
				}
			}
			break;
			
		case COMMUNITY_CARDS_CHANGED:
			List<ICard> communityCards = table.getCommunityCards();
			tableInfoPanel.showCards(communityCards);
			break;

		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(leaveTableButton)) {
			//TODO this is the wrong event
			EventBus.publish(new Event(Event.Tag.LEAVE_TABLE, 1));
		}
	}

}
