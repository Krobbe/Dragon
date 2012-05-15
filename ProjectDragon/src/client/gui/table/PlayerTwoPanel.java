package client.gui.table;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PlayerTwoPanel extends JPanel {
	
	private JLabel p2c1Label;
	private JLabel p2c2Label;
	private JLabel p2NameLabel;
	private JLabel p2CreditsLabel;
	private JLabel p2AvailableCreditsLabel;
	
	public PlayerTwoPanel() {
		init();
	}

	private void init() {
		this.setLayout(null);
		this.setBounds(853, 0, 135, 144);

		p2c1Label = new JLabel("Card 1");
		p2c1Label.setBounds(10, 5, 53, 80);
		this.add(p2c1Label);

		p2c2Label = new JLabel("Card 2\r\n");
		p2c2Label.setBounds(73, 5, 53, 80);
		this.add(p2c2Label);

		p2NameLabel = new JLabel("Player1\r\n");
		p2NameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		p2NameLabel.setBounds(10, 96, 115, 14);
		this.add(p2NameLabel);

		p2CreditsLabel = new JLabel("Credits:");
		p2CreditsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		p2CreditsLabel.setBounds(10, 119, 53, 14);
		this.add(p2CreditsLabel);

		p2AvailableCreditsLabel = new JLabel("xxx");
		p2AvailableCreditsLabel.setBounds(72, 119, 53, 14);
		this.add(p2AvailableCreditsLabel);
	}

}