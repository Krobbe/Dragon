package client.gui.table;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


import model.card.ICard;
import model.player.IPlayer;
import model.player.hand.IHand;

/**
 * The Panel for player 8
 * @author forssenm
 *
 */
public class PlayerEightPanel extends JPanel implements IPlayerPanel {

	private JLabel p8c1Label;
	private JLabel p8c2Label;
	private JLabel p8NameLabel;
	private JLabel p8CreditsLabel;
	private JLabel p8AvailableCreditsLabel;
	private String path = "lib/deckimages/";
	
	/**
	 * Creates the panel
	 */
	public PlayerEightPanel() {
		init();
	}

	private void init() {
		this.setLayout(null);
		this.setBounds(0, 202, 135, 144);

		p8c1Label = new JLabel("Card 1");
		p8c1Label.setBounds(10, 5, 53, 80);
		p8c1Label.setIcon(new ImageIcon(getClass().getResource(
				path + "NORANKNOSUIT.gif")));
		this.add(p8c1Label);

		p8c2Label = new JLabel("Card 2\r\n");
		p8c2Label.setBounds(73, 5, 53, 80);
		p8c2Label.setIcon(new ImageIcon(getClass().getResource(
				path + "NORANKNOSUIT.gif")));
		this.add(p8c2Label);

		p8NameLabel = new JLabel("Player1\r\n");
		p8NameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		p8NameLabel.setBounds(10, 96, 115, 14);
		this.add(p8NameLabel);

		p8CreditsLabel = new JLabel("Credits:");
		p8CreditsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		p8CreditsLabel.setBounds(10, 119, 53, 14);
		this.add(p8CreditsLabel);

		p8AvailableCreditsLabel = new JLabel("xxx");
		p8AvailableCreditsLabel.setBounds(72, 119, 53, 14);
		this.add(p8AvailableCreditsLabel);

	}

	@Override
	public boolean discard() {
		p8c1Label.setIcon(new ImageIcon(getClass().getResource(
				path + "NORANKNOSUIT.gif")));
		p8c2Label.setIcon(new ImageIcon(getClass().getResource(
				path + "NORANKNOSUIT.gif")));
		return true;
	}

	@Override
	public void setBalance(String s) {
		p8AvailableCreditsLabel.setText(s);
	}

	@Override
	public void showCards(IHand h) {
		ICard card1 = h.getCards().get(0);
		ICard card2 = h.getCards().get(1);
		p8c1Label.setIcon(new ImageIcon(getClass().getResource(
				path + card1.getRank() + card1.getSuit() + ".gif")));
		p8c2Label.setIcon(new ImageIcon(getClass().getResource(
				path + card2.getRank() + card2.getSuit() + ".gif")));
	}

	@Override
	public void setTheBackground(Color c) {
		this.setBackground(c);
	}

	@Override
	public void setName(IPlayer p) {
		p8NameLabel.setText(p.getName());
	}

}
