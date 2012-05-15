package client.gui.table;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PlayerSevenPanel extends JPanel {
	
	private JLabel p7c1Label;
	private JLabel p7c2Label;
	private JLabel p7NameLabel;
	private JLabel p7CreditsLabel;
	private JLabel p7AvailableCreditsLabel;
	
	public PlayerSevenPanel() {
		init();
	}

	private void init() {
		this.setLayout(null);
		this.setBounds(0, 405, 135, 144);

		p7c1Label = new JLabel("Card 1");
		p7c1Label.setBounds(10, 5, 53, 80);
		this.add(p7c1Label);

		p7c2Label = new JLabel("Card 2\r\n");
		p7c2Label.setBounds(73, 5, 53, 80);
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

}
