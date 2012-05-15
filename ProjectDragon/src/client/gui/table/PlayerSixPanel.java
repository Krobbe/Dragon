package client.gui.table;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PlayerSixPanel extends JPanel {
	
	private JLabel p6c1Label;
	private JLabel p6c2Label;
	private JLabel p6NameLabel;
	private JLabel p6CreditsLabel;
	private JLabel p6AvailableCreditsLabel;
	
	public PlayerSixPanel() {
		init();
	}

	private void init() {
		this.setLayout(null);
		this.setBounds(297, 405, 135, 144);

		p6c1Label = new JLabel("Card 1");
		p6c1Label.setBounds(10, 5, 53, 80);
		this.add(p6c1Label);

		p6c2Label = new JLabel("Card 2\r\n");
		p6c2Label.setBounds(73, 5, 53, 80);
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

}