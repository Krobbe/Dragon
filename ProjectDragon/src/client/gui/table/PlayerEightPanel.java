package client.gui.table;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.player.IPlayer;
import model.player.Player;
import model.player.hand.IHand;

public class PlayerEightPanel extends JPanel implements IPlayerPanel {
	
	private JLabel p8c1Label;
	private JLabel p8c2Label;
	private JLabel p8NameLabel;
	private JLabel p8CreditsLabel;
	private JLabel p8AvailableCreditsLabel;
	
	public PlayerEightPanel() {
		init();
	}

	private void init() {
		this.setLayout(null);
		this.setBounds(0, 202, 135, 144);

		p8c1Label = new JLabel("Card 1");
		p8c1Label.setBounds(10, 5, 53, 80);
		this.add(p8c1Label);

		p8c2Label = new JLabel("Card 2\r\n");
		p8c2Label.setBounds(73, 5, 53, 80);
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
		p8c1Label.setText(null);
		p8c2Label.setText(null);
		return true;
	}

	@Override
	public void setBalance(String s) {
		p8AvailableCreditsLabel.setText(s);
	}

	@Override
	public void showCards(IHand h) {
		p8c1Label.setText(h.getCards().get(0).toString());
		p8c2Label.setText(h.getCards().get(0).toString());
	}

	@Override
	public void setBackground(Color c) {
		this.setBackground(c);
	}

	@Override
	public void setName(IPlayer p) {
		p8NameLabel.setText(p.getName());
	}

}
