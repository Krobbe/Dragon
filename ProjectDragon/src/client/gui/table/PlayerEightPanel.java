package client.gui.table;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PlayerEightPanel extends JPanel {
	
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

}
