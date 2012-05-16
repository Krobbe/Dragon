package client.gui.table;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.player.hand.IHand;

public class PlayerThreePanel extends JPanel implements IPlayerPanel {
	
	private JLabel p3c1Label;
	private JLabel p3c2Label;
	private JLabel p3NameLabel;
	private JLabel p3CreditsLabel;
	private JLabel p3AvailableCreditsLabel;
	
	public PlayerThreePanel() {
		init();
	}

	private void init() {
		this.setLayout(null);
		this.setBounds(853, 202, 135, 144);

		p3c1Label = new JLabel("Card 1");
		p3c1Label.setBounds(10, 5, 53, 80);
		this.add(p3c1Label);

		p3c2Label = new JLabel("Card 2\r\n");
		p3c2Label.setBounds(73, 5, 53, 80);
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
		p3c1Label.setText(null);
		p3c2Label.setText(null);
		return true;
	}

	@Override
	public void setBalance(String s) {
		p3AvailableCreditsLabel.setText(s);
	}

	@Override
	public void showCards(IHand h) {
		p3c1Label.setText(h.getCards().get(0).toString());
		p3c2Label.setText(h.getCards().get(0).toString());
	}
	
	@Override
	public void setBackground(Color c) {
		this.setBackground(c);
	}

}
