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
 * The panel for player 3
 * @author forssenm
 *
 */
public class PlayerThreePanel extends JPanel implements IPlayerPanel {
	
	private JLabel p3c1Label;
	private JLabel p3c2Label;
	private JLabel p3NameLabel;
	private JLabel p3CreditsLabel;
	private JLabel p3AvailableCreditsLabel;
	private String path = "lib/deckimages/";
	
	/**
	 * Creates the panel
	 */
	public PlayerThreePanel() {
		init();
	}

	private void init() {
		this.setLayout(null);
		this.setBounds(853, 202, 135, 144);

		p3c1Label = new JLabel("Card 1");
		p3c1Label.setBounds(10, 5, 53, 80);
		p3c1Label.setIcon(new ImageIcon(path + "NORANKNOSUIT.gif"));
		this.add(p3c1Label);

		p3c2Label = new JLabel("Card 2\r\n");
		p3c2Label.setBounds(73, 5, 53, 80);
		p3c2Label.setIcon(new ImageIcon(path + "NORANKNOSUIT.gif"));
		this.add(p3c2Label);

		p3NameLabel = new JLabel("Player1\r\n");
		p3NameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		p3NameLabel.setBounds(10, 96, 115, 14);
		this.add(p3NameLabel);

		p3CreditsLabel = new JLabel("Credits:");
		p3CreditsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		p3CreditsLabel.setBounds(10, 119, 53, 14);
		this.add(p3CreditsLabel);

		p3AvailableCreditsLabel = new JLabel("xxx");
		p3AvailableCreditsLabel.setBounds(72, 119, 53, 14);
		this.add(p3AvailableCreditsLabel);
		
	}
	
	@Override
	public boolean discard() {
		p3c1Label.setIcon(new ImageIcon(path + "NORANKNOSUIT.gif"));
		p3c2Label.setIcon(new ImageIcon(path + "NORANKNOSUIT.gif"));
		return true;
	}

	@Override
	public void setBalance(String s) {
		p3AvailableCreditsLabel.setText(s);
	}

	@Override
	public void showCards(IHand h) {
		ICard card1 = h.getCards().get(0);
		ICard card2 = h.getCards().get(1);
		p3c1Label.setIcon(new ImageIcon(path + 
				card1.getRank() + card1.getSuit() + ".gif"));
		p3c2Label.setIcon(new ImageIcon(path + 
				card2.getRank() + card2.getSuit() + ".gif"));
	}
	
	@Override
	public void setTheBackground(Color c) {
		this.setBackground(c);
	}
	
	@Override
	public void setName(IPlayer p) {
		p3NameLabel.setText(p.getName());
	}

}
