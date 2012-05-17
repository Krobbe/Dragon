package client.gui.table;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.card.ICard;
import model.player.IPlayer;
import model.player.hand.IHand;

public class PlayerSevenPanel extends JPanel implements IPlayerPanel {
	
	private JLabel p7c1Label;
	private JLabel p7c2Label;
	private JLabel p7NameLabel;
	private JLabel p7CreditsLabel;
	private JLabel p7AvailableCreditsLabel;
	private String path = "lib/deckimages/";
	
	public PlayerSevenPanel() {
		init();
	}

	private void init() {
		this.setLayout(null);
		this.setBounds(0, 405, 135, 144);

		p7c1Label = new JLabel("Card 1");
		p7c1Label.setBounds(10, 5, 53, 80);
		p7c1Label.setIcon(new ImageIcon(getClass().getResource(path + "NORANKNOSUIT.gif")));
		this.add(p7c1Label);

		p7c2Label = new JLabel("Card 2\r\n");
		p7c2Label.setBounds(73, 5, 53, 80);
		p7c2Label.setIcon(new ImageIcon(getClass().getResource(path + "NORANKNOSUIT.gif")));
		this.add(p7c2Label);

		p7NameLabel = new JLabel("Player1\r\n");
		p7NameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		p7NameLabel.setBounds(10, 96, 115, 14);
		this.add(p7NameLabel);

		p7CreditsLabel = new JLabel("Credits:");
		p7CreditsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		p7CreditsLabel.setBounds(10, 119, 53, 14);
		this.add(p7CreditsLabel);

		p7AvailableCreditsLabel = new JLabel("xxx");
		p7AvailableCreditsLabel.setBounds(72, 119, 53, 14);
		this.add(p7AvailableCreditsLabel);
	}
	
	@Override
	public boolean discard() {
		p7c1Label.setIcon(new ImageIcon(getClass().getResource(path + "NORANKNOSUIT.gif")));
		p7c2Label.setIcon(new ImageIcon(getClass().getResource(path + "NORANKNOSUIT.gif")));
		return true;
	}

	@Override
	public void setBalance(String s) {
		p7AvailableCreditsLabel.setText(s);
	}

	@Override
	public void showCards(IHand h) {
		ICard card1 = h.getCards().get(0);
		ICard card2 = h.getCards().get(1);
		p7c1Label.setIcon(new ImageIcon(getClass().getResource(path + 
				card1.getRank() + card1.getSuit() + ".gif")));
		p7c2Label.setIcon(new ImageIcon(getClass().getResource(path + 
				card2.getRank() + card2.getSuit() + ".gif")));
	}
	
	@Override
	public void setTheBackground(Color c) {
		this.setBackground(c);
	}
	
	@Override
	public void setName(IPlayer p) {
		p7NameLabel.setText(p.getName());
	}

}
