package client.gui.table;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import common.model.card.ICard;


/**
 * This is the panel that holds all the information about what cards are on the table, how much is in the pot
 * and what the current bet is
 * @author forssenm
 * @author lisastenberg
 *
 */
@SuppressWarnings("serial")
public class TableInfoPanel extends JPanel {
	
	private JPanel northPanel;
	private JPanel southPanel;
	private JLabel card1Label;
	private JLabel card2Label;
	private JLabel card3Label;
	private JLabel card4Label;
	private JLabel card5Label;
	private JLabel potSizeInfoLabel;
	private JLabel potSize;
	private JLabel currentBetInfoLabel;
	private JLabel currentBet;
	private String path = "lib/deckimages/";
	
	/**
	 * Creates the panel
	 */
	public TableInfoPanel() {
		init();
	}

	private void init() {
		this.setPreferredSize(new Dimension(418, 144));
		this.setLayout(new BorderLayout());
		
		northPanel = new JPanel();
		southPanel = new JPanel();
		northPanel.setLayout(new FlowLayout());
		southPanel.setLayout(new FlowLayout());
		
		northPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		southPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		card1Label = new JLabel();
		card1Label.setIcon(new ImageIcon(path + "NORANKNOSUIT.gif"));
		this.add(card1Label);

		card2Label = new JLabel();
		card2Label.setIcon(new ImageIcon(path + "NORANKNOSUIT.gif"));
		this.add(card2Label);

		card3Label = new JLabel();
		card3Label.setIcon(new ImageIcon(path + "NORANKNOSUIT.gif"));
		this.add(card3Label);

		card4Label = new JLabel();
		card4Label.setIcon(new ImageIcon(path + "NORANKNOSUIT.gif"));
		this.add(card4Label);

		card5Label = new JLabel();
		card5Label.setIcon(new ImageIcon(path + "NORANKNOSUIT.gif"));
		this.add(card5Label);

		potSizeInfoLabel= new JLabel("Pot size:");
		potSizeInfoLabel.setPreferredSize(new Dimension(53, 15));
		this.add(potSizeInfoLabel);
		
		potSize = new JLabel("0");
		potSize.setPreferredSize(new Dimension(53, 15));
		this.add(potSize);
		
		currentBetInfoLabel = new JLabel("Current bet:");
		currentBetInfoLabel.setPreferredSize(new Dimension(90, 15));
		this.add(currentBetInfoLabel);
		
		currentBet = new JLabel("0");
		potSizeInfoLabel.setPreferredSize(new Dimension(53, 15));
		this.add(currentBet);
		
		northPanel.add(card1Label);
		northPanel.add(card2Label);
		northPanel.add(card3Label);
		northPanel.add(card4Label);
		northPanel.add(card5Label);
		
		southPanel.add(potSizeInfoLabel);
		southPanel.add(potSize);
		southPanel.add(currentBetInfoLabel);
		southPanel.add(currentBet);
		
		this.add(northPanel, BorderLayout.NORTH);
		this.add(southPanel, BorderLayout.SOUTH);
	}
	
	/**
	 * Sets the current bet
	 * @param s The string that the bet should be set to
	 */
	public void setBet(String s) {
		currentBet.setText(s);
	}
	
	/**
	 * Sets the pot size
	 * @param s The string that the pot should be set to
	 */
	public void setPotSize(String s) {
		potSize.setText(s);
	}
	
	/**
	 * Shows the cards that the dealer has placed on the table.
	 * 
	 * @param list The list of the cards that should be shown
	 */
	public void showCards(List<ICard> list) {
		if(list.size() == 0) {
			card1Label.setIcon(new ImageIcon(path + "NORANKNOSUIT.gif"));
			card2Label.setIcon(new ImageIcon(path + "NORANKNOSUIT.gif"));
			card3Label.setIcon(new ImageIcon(path + "NORANKNOSUIT.gif"));
			card4Label.setIcon(new ImageIcon(path + "NORANKNOSUIT.gif"));
			card5Label.setIcon(new ImageIcon(path + "NORANKNOSUIT.gif"));
		} else if(list.size() == 3) {
			card1Label.setIcon(new ImageIcon(path + list.get(0).getRank() + list.get(0).getSuit() + ".gif"));
			card2Label.setIcon(new ImageIcon(path + list.get(1).getRank() + list.get(1).getSuit() + ".gif"));
			card3Label.setIcon(new ImageIcon(path + list.get(2).getRank() + list.get(2).getSuit() + ".gif"));
		} else if(list.size() == 4) {
			card1Label.setIcon(new ImageIcon(path + list.get(0).getRank() + list.get(0).getSuit() + ".gif"));
			card2Label.setIcon(new ImageIcon(path + list.get(1).getRank() + list.get(1).getSuit() + ".gif"));
			card3Label.setIcon(new ImageIcon(path + list.get(2).getRank() + list.get(2).getSuit() + ".gif"));
			card4Label.setIcon(new ImageIcon(path + list.get(3).getRank() + list.get(3).getSuit() + ".gif"));
		} else if(list.size() == 5) {
			card1Label.setIcon(new ImageIcon(path + list.get(0).getRank() + list.get(0).getSuit() + ".gif"));
			card2Label.setIcon(new ImageIcon(path + list.get(1).getRank() + list.get(1).getSuit() + ".gif"));
			card3Label.setIcon(new ImageIcon(path + list.get(2).getRank() + list.get(2).getSuit() + ".gif"));
			card4Label.setIcon(new ImageIcon(path + list.get(3).getRank() + list.get(3).getSuit() + ".gif"));
			card5Label.setIcon(new ImageIcon(path + list.get(4).getRank() + list.get(4).getSuit() + ".gif"));
		}
	}
}
