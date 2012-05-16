package client.gui.table;

import java.awt.Color;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.card.ICard;

public class TableInfoPanel extends JPanel {
	
	private JLabel flopc1Label;
	private JLabel flopc2Label;
	private JLabel flopc3Label;
	private JLabel turnc1Label;
	private JLabel riverc1Label;
	private JLabel potSizeInfoLabel;
	private JLabel potSize;
	private JLabel currentBetInfoLabel;
	private JLabel currentBet;
	
	public TableInfoPanel() {
		init();
	}

	private void init() {
		this.setBackground(Color.green);
		this.setBounds(297, 202, 418, 144);
		this.setLayout(null);

		flopc1Label = new JLabel("Card 1");
		flopc1Label.setBounds(10, 11, 53, 80);
		this.add(flopc1Label);

		flopc2Label = new JLabel("Card 1");
		flopc2Label.setBounds(89, 11, 53, 80);
		this.add(flopc2Label);

		flopc3Label = new JLabel("Card 1");
		flopc3Label.setBounds(182, 11, 53, 80);
		this.add(flopc3Label);

		turnc1Label = new JLabel("Card 1");
		turnc1Label.setBounds(260, 11, 53, 80);
		this.add(turnc1Label);

		riverc1Label = new JLabel("Card 1");
		riverc1Label.setBounds(355, 11, 53, 80);
		this.add(riverc1Label);

		potSizeInfoLabel= new JLabel("Pot size:");
		potSizeInfoLabel.setBounds(10, 120, 53, 15);
		this.add(potSizeInfoLabel);
		
		potSize = new JLabel("xxx");
		potSize.setBounds(89, 120, 53, 15);
		this.add(potSize);
		
		currentBetInfoLabel = new JLabel("Current bet:");
		currentBetInfoLabel.setBounds(260, 120, 70, 15);
		this.add(currentBetInfoLabel);
		
		currentBet = new JLabel("xxx");
		currentBet.setBounds(355, 120, 53, 15);
		this.add(currentBet);
	}
	
	//TODO think these setters should return a boolean
	public void setBet(String s) {
		currentBet.setText(s);
	}
	public void setPotSize(String s) {
		potSize.setText(s);
	}
	
	public void showCards(List<ICard> list) {
		if(list.size() == 3) {
			flopc1Label.setText(list.get(0).toString());
			flopc2Label.setText(list.get(1).toString());
			flopc3Label.setText(list.get(2).toString());
		}
		if(list.size() == 4) {
			flopc1Label.setText(list.get(0).toString());
			flopc2Label.setText(list.get(1).toString());
			flopc3Label.setText(list.get(2).toString());
			turnc1Label.setText(list.get(3).toString());
		}
		if(list.size() == 5) {
			flopc1Label.setText(list.get(0).toString());
			flopc2Label.setText(list.get(1).toString());
			flopc3Label.setText(list.get(2).toString());
			turnc1Label.setText(list.get(3).toString());
			riverc1Label.setText(list.get(4).toString());
		}
	}
	
	public void setFlopC1(String s) {
		flopc1Label.setText(s);
	}
	
	public void setFlopC2(String s) {
		flopc2Label.setText(s);
	}
	
	public void setFlopC3(String s) {
		flopc3Label.setText(s);
	}
	
	public void setTurnC1(String s) {
		turnc1Label.setText(s);
	}
	public void setRiverC1(String s) {
		riverc1Label.setText(s);
	}

}
