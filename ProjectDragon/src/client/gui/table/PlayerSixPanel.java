package client.gui.table;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import common.model.card.ICard;
import common.model.player.IPlayer;

import model.player.hand.IHand;

/**
 * The panel for player 6
 * @author forssenm
 *
 */
public class PlayerSixPanel extends JPanel implements IPlayerPanel {
	
	private JLabel p6c1Label;
	private JLabel p6c2Label;
	private JLabel p6NameLabel;
	private JLabel p6CreditsLabel;
	private JLabel p6AvailableCreditsLabel;
	private String path = "lib/deckimages/";
	
	/**
	 * Creates the panel
	 */
	public PlayerSixPanel() {
		init();
	}

	private void init() {
		this.setLayout(null);
		this.setBounds(297, 405, 135, 144);

		p6c1Label = new JLabel("Card 1");
		p6c1Label.setBounds(10, 5, 53, 80);
		p6c1Label.setIcon(new ImageIcon(path + "NORANKNOSUIT.gif"));
		this.add(p6c1Label);

		p6c2Label = new JLabel("Card 2\r\n");
		p6c2Label.setBounds(73, 5, 53, 80);
		p6c2Label.setIcon(new ImageIcon(path + "NORANKNOSUIT.gif"));
		this.add(p6c2Label);

		p6NameLabel = new JLabel("Player1\r\n");
		p6NameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		p6NameLabel.setBounds(10, 96, 115, 14);
		this.add(p6NameLabel);

		p6CreditsLabel = new JLabel("Credits:");
		p6CreditsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		p6CreditsLabel.setBounds(10, 119, 53, 14);
		this.add(p6CreditsLabel);

		p6AvailableCreditsLabel = new JLabel("xxx");
		p6AvailableCreditsLabel.setBounds(72, 119, 53, 14);
		this.add(p6AvailableCreditsLabel);
		
	}
	
	@Override
	public boolean discard() {
		p6c1Label.setIcon(new ImageIcon(path + "NORANKNOSUIT.gif"));
		p6c2Label.setIcon(new ImageIcon(path + "NORANKNOSUIT.gif"));
		return true;
	}

	@Override
	public void setBalance(String s) {
		p6AvailableCreditsLabel.setText(s);
	}

	@Override
	public void showCards(IHand h) {
		ICard card1 = h.getCards().get(0);
		ICard card2 = h.getCards().get(1);
		p6c1Label.setIcon(new ImageIcon(path + 
				card1.getRank() + card1.getSuit() + ".gif"));
		p6c2Label.setIcon(new ImageIcon(path + 
				card2.getRank() + card2.getSuit() + ".gif"));
	}
	
	@Override
	public void setTheBackground(Color c) {
		this.setBackground(c);
	}
	
	@Override
	public void setName(IPlayer p) {
		p6NameLabel.setText(p.getName());
	}

}
