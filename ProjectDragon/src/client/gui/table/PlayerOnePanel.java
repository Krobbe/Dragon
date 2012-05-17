package client.gui.table;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.card.ICard;
import model.player.IPlayer;
import model.player.hand.IHand;

public class PlayerOnePanel extends JPanel implements IPlayerPanel {
	
	private JLabel p1c1Label;
	private JLabel p1c2Label;
	private JLabel p1NameLabel;
	private JLabel p1CreditsLabel;
	private JLabel p1AvailableCreditsLabel;
	private String path = "lib/deckimages/";
	
	public PlayerOnePanel() {
		init();
	}

	private void init() {
		this.setLayout(null);
		this.setBounds(580, 0, 135, 144);

		p1c1Label = new JLabel("Card 1");
		p1c1Label.setBounds(10, 5, 53, 80);
		p1c1Label.setIcon(new ImageIcon(getClass().getResource(path + "NORANKNOSUIT.gif")));
		this.add(p1c1Label);

		p1c2Label = new JLabel("Card 2\r\n");
		p1c2Label.setBounds(73, 5, 53, 80);
		p1c2Label.setIcon(new ImageIcon(getClass().getResource(path + "NORANKNOSUIT.gif")));
		this.add(p1c2Label);

		p1NameLabel = new JLabel("Player1\r\n");
		p1NameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		p1NameLabel.setBounds(10, 96, 115, 14);
		this.add(p1NameLabel);

		p1CreditsLabel = new JLabel("Credits:");
		p1CreditsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		p1CreditsLabel.setBounds(10, 119, 53, 14);
		this.add(p1CreditsLabel);

		p1AvailableCreditsLabel = new JLabel("1000");
		p1AvailableCreditsLabel.setBounds(72, 119, 53, 14);
		this.add(p1AvailableCreditsLabel);
	}
	
	@Override
	public boolean discard() {
		p1c1Label.setIcon(new ImageIcon(getClass().getResource(
				path + "NORANKNOSUIT.gif")));
		p1c2Label.setIcon(new ImageIcon(getClass().getResource(
				path + "NORANKNOSUIT.gif")));
		return true;
	}

	@Override
	public void setBalance(String s) {
		p1AvailableCreditsLabel.setText(s);
	}

	@Override
	public void showCards(IHand h) {
		ICard card1 = h.getCards().get(0);
		ICard card2 = h.getCards().get(1);
		p1c1Label.setIcon(new ImageIcon(getClass().getResource(path + 
				card1.getRank() + card1.getSuit() + ".gif")));
		p1c2Label.setIcon(new ImageIcon(getClass().getResource(path + 
				card2.getRank() + card2.getSuit() + ".gif")));
	}
	
	@Override
	public void setTheBackground(Color c) {
		this.setBackground(c);
	}
	
	@Override
	public void setName(IPlayer p) {
		p1NameLabel.setText(p.getName());
	}

}
