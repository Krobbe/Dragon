package client.gui.table;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import common.model.card.ICard;

import model.player.IPlayer;
import model.player.hand.IHand;

/**
 * The panel for player 4
 * @author forssenm
 *
 */
public class PlayerFourPanel extends JPanel implements IPlayerPanel {
	
	private JLabel p4c1Label;
	private JLabel p4c2Label;
	private JLabel p4NameLabel;
	private JLabel p4CreditsLabel;
	private JLabel p4AvailableCreditsLabel;
	private String path = "lib/deckimages/";
	
	/**
	 * Creates the panel
	 */
	public PlayerFourPanel() {
		init();
	}

	private void init() {
		this.setLayout(null);
		this.setBounds(853, 405, 135, 144);

		p4c1Label = new JLabel("Card 1");
		p4c1Label.setBounds(10, 5, 53, 80);
		p4c1Label.setIcon(new ImageIcon(path + "NORANKNOSUIT.gif"));
		this.add(p4c1Label);

		p4c2Label = new JLabel("Card 2\r\n");
		p4c2Label.setBounds(73, 5, 53, 80);
		p4c2Label.setIcon(new ImageIcon(path + "NORANKNOSUIT.gif"));
		this.add(p4c2Label);

		p4NameLabel = new JLabel("Player1\r\n");
		p4NameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		p4NameLabel.setBounds(10, 96, 115, 14);
		this.add(p4NameLabel);

		p4CreditsLabel = new JLabel("Credits:");
		p4CreditsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		p4CreditsLabel.setBounds(10, 119, 53, 14);
		this.add(p4CreditsLabel);

		p4AvailableCreditsLabel = new JLabel("xxx");
		p4AvailableCreditsLabel.setBounds(72, 119, 53, 14);
		this.add(p4AvailableCreditsLabel);
		
	}
	
	@Override
	public boolean discard() {
		p4c1Label.setIcon(new ImageIcon(
				path + "NORANKNOSUIT.gif"));
		p4c2Label.setIcon(new ImageIcon(
				path + "NORANKNOSUIT.gif"));
		return true;
	}

	@Override
	public void setBalance(String s) {
		p4AvailableCreditsLabel.setText(s);
	}

	@Override
	public void showCards(IHand h) {
		ICard card1 = h.getCards().get(0);
		ICard card2 = h.getCards().get(1);
		p4c1Label.setIcon(new ImageIcon(path + 
				card1.getRank() + card1.getSuit() + ".gif"));
		p4c2Label.setIcon(new ImageIcon(path + 
				card2.getRank() + card2.getSuit() + ".gif"));
	}
	
	@Override
	public void setTheBackground(Color c) {
		this.setBackground(c);
	}
	
	@Override
	public void setName(IPlayer p) {
		p4NameLabel.setText(p.getName());
	}
}
