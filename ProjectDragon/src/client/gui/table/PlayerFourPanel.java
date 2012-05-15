package client.gui.table;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PlayerFourPanel extends JPanel {
	
	private JLabel p4c1Label;
	private JLabel p4c2Label;
	private JLabel p4NameLabel;
	private JLabel p4CreditsLabel;
	private JLabel p4AvailableCreditsLabel;
	
	public PlayerFourPanel() {
		init();
	}

	private void init() {
		this.setLayout(null);
		this.setBounds(853, 405, 135, 144);

		p4c1Label = new JLabel("Card 1");
		p4c1Label.setBounds(10, 5, 53, 80);
		this.add(p4c1Label);

		p4c2Label = new JLabel("Card 2\r\n");
		p4c2Label.setBounds(73, 5, 53, 80);
		this.add(p4c2Label);

		p4NameLabel = new JLabel("Player1\r\n");
		p4NameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		p4NameLabel.setBounds(10, 96, 115, 14);
		this.add(p4NameLabel);

		p4CreditsLabel = new JLabel("Credits:");
		p4CreditsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		p4CreditsLabel.setBounds(10, 119, 53, 14);
		this.add(p4CreditsLabel);

		p4AvailableCreditsLabel = new JLabel("xxx");
		p4AvailableCreditsLabel.setBounds(72, 119, 53, 14);
		this.add(p4AvailableCreditsLabel);
		
	}

}