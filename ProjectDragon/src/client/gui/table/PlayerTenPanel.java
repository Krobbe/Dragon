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
 * The panel for player 10
 * @author forssenm
 *
 */
public class PlayerTenPanel extends JPanel implements IPlayerPanel {
	
	private JLabel p10c1Label;
	private JLabel p10c2Label;
	private JLabel p10NameLabel;
	private JLabel p10CreditsLabel;
	private JLabel p10AvailableCreditsLabel;
	private String path = "lib/deckimages/";
	
	/**
	 * Creates the panel
	 */
	public PlayerTenPanel() {
		init();
	}

	private void init() {
		this.setLayout(null);
		this.setBounds(297, 0, 135, 144);

		p10c1Label = new JLabel("Card 1");
		p10c1Label.setBounds(10, 5, 53, 80);
		p10c1Label.setIcon(new ImageIcon(getClass().getResource(path + "NORANKNOSUIT.gif")));
		this.add(p10c1Label);

		p10c2Label = new JLabel("Card 2\r\n");
		p10c2Label.setBounds(73, 5, 53, 80);
		p10c2Label.setIcon(new ImageIcon(getClass().getResource(path + "NORANKNOSUIT.gif")));
		this.add(p10c2Label);

		p10NameLabel = new JLabel("Player1\r\n");
		p10NameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		p10NameLabel.setBounds(10, 96, 115, 14);
		this.add(p10NameLabel);

		p10CreditsLabel = new JLabel("Credits:");
		p10CreditsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		p10CreditsLabel.setBounds(10, 119, 53, 14);
		this.add(p10CreditsLabel);

		p10AvailableCreditsLabel = new JLabel("xxx");
		p10AvailableCreditsLabel.setBounds(72, 119, 53, 14);
		this.add(p10AvailableCreditsLabel);
		
	}
	
	@Override
	public boolean discard() {
		p10c1Label.setIcon(new ImageIcon(getClass().getResource(
				path + "NORANKNOSUIT.gif")));
		p10c2Label.setIcon(new ImageIcon(getClass().getResource(
				path + "NORANKNOSUIT.gif")));
		return true;
	}

	@Override
	public void setBalance(String s) {
		p10AvailableCreditsLabel.setText(s);
	}

	@Override
	public void showCards(IHand h) {
		ICard card1 = h.getCards().get(0);
		ICard card2 = h.getCards().get(1);
		p10c1Label.setIcon(new ImageIcon(getClass().getResource(path + 
				card1.getRank() + card1.getSuit() + ".gif")));
		p10c2Label.setIcon(new ImageIcon(getClass().getResource(path + 
				card2.getRank() + card2.getSuit() + ".gif")));
	}
	
	@Override
	public void setTheBackground(Color c) {
		this.setBackground(c);
	}
	
	@Override
	public void setName(IPlayer p) {
		p10NameLabel.setText(p.getName());
	}
}
