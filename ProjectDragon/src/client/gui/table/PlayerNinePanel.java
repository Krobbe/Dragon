package client.gui.table;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.card.ICard;
import model.player.IPlayer;
import model.player.hand.IHand;

public class PlayerNinePanel extends JPanel implements IPlayerPanel {
	
	private JLabel p9c1Label;
	private JLabel p9c2Label;
	private JLabel p9NameLabel;
	private JLabel p9CreditsLabel;
	private JLabel p9AvailableCreditsLabel;
	private String path = "lib/deckimages/";
	
	public PlayerNinePanel() {
		init();
	}

	private void init() {
		this.setBounds(0, 0, 135, 144);
		this.setLayout(null);

		p9c1Label = new JLabel("Card 1");
		p9c1Label.setBounds(10, 5, 53, 80);
		p9c1Label.setIcon(new ImageIcon(getClass().getResource(path + "NORANKNOSUIT.gif")));
		this.add(p9c1Label);

		p9c2Label = new JLabel("Card 2\r\n");
		p9c2Label.setBounds(73, 5, 53, 80);
		p9c2Label.setIcon(new ImageIcon(getClass().getResource(path + "NORANKNOSUIT.gif")));
		this.add(p9c2Label);

		p9NameLabel = new JLabel("Player1\r\n");
		p9NameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		p9NameLabel.setBounds(10, 96, 115, 14);
		this.add(p9NameLabel);

		p9CreditsLabel = new JLabel("Credits:");
		p9CreditsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		p9CreditsLabel.setBounds(10, 119, 53, 14);
		this.add(p9CreditsLabel);

		p9AvailableCreditsLabel = new JLabel("xxx");
		p9AvailableCreditsLabel.setBounds(72, 119, 53, 14);
		this.add(p9AvailableCreditsLabel);
		
	}
	
	@Override
	public boolean discard() {
		p9c1Label.setIcon(new ImageIcon(getClass().getResource(
				path + "NORANKNOSUIT.gif")));
		p9c2Label.setIcon(new ImageIcon(getClass().getResource(
				path + "NORANKNOSUIT.gif")));
		return true;
	}

	@Override
	public void setBalance(String s) {
		p9AvailableCreditsLabel.setText(s);
	}

	@Override
	public void showCards(IHand h) {
		ICard card1 = h.getCards().get(0);
		ICard card2 = h.getCards().get(1);
		p9c1Label.setIcon(new ImageIcon(getClass().getResource(path + 
				card1.getRank() + card1.getSuit() + ".gif")));
		p9c2Label.setIcon(new ImageIcon(getClass().getResource(path + 
				card2.getRank() + card2.getSuit() + ".gif")));
	}
	
	@Override
	public void setBackground(Color c) {
		this.setBackground(c);
	}
	
	@Override
	public void setName(IPlayer p) {
		p9NameLabel.setText(p.getName());
	}
}
