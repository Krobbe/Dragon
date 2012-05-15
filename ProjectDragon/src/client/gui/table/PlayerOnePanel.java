package client.gui.table;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PlayerOnePanel extends JPanel {
	
	private JLabel p1c1Label;
	private JLabel p1c2Label;
	private JLabel p1NameLabel;
	private JLabel p1CreditsLabel;
	private JLabel p1AvailableCreditsLabel;
	
	public PlayerOnePanel() {
		init();
	}

	private void init() {
		this.setLayout(null);
		this.setBounds(580, 0, 135, 144);

		p1c1Label = new JLabel("Card 1");
		p1c1Label.setBounds(10, 5, 53, 80);
		this.add(p1c1Label);

		p1c2Label = new JLabel("Card 2\r\n");
		p1c2Label.setBounds(73, 5, 53, 80);
		this.add(p1c2Label);

		p1NameLabel = new JLabel("Player1\r\n");
		p1NameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		p1NameLabel.setBounds(10, 96, 115, 14);
		this.add(p1NameLabel);

		p1CreditsLabel = new JLabel("Credits:");
		p1CreditsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		p1CreditsLabel.setBounds(10, 119, 53, 14);
		this.add(p1CreditsLabel);

		p1AvailableCreditsLabel = new JLabel("xxx");
		p1AvailableCreditsLabel.setBounds(72, 119, 53, 14);
		this.add(p1AvailableCreditsLabel);
	}

}
