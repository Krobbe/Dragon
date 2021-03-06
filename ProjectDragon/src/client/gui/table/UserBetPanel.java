package client.gui.table;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import client.event.Event;
import client.event.EventBus;

import common.model.player.IPlayer;

/**
 * The panel that has all the options that the player can do such as make a call or a bet
 * @author forssenm
 * @author lisastenberg
 *
 */
@SuppressWarnings("serial")
public class UserBetPanel extends JPanel implements ActionListener {
		
	private JLabel availableCreditsLabel;
	private JLabel creditsLabel;
	private JButton foldButton;
	private JButton checkButton;
	private JButton raiseButton;
	private JButton leaveTableButton;
	private JSpinner betSpinner;
	private JLabel ownCurrentBetLabel;
	private JButton callButton;
	private JLabel nameLabel;
	
	private IPlayer user;
	private int bigblind = common.model.game.P.INSTANCE.getBigBlindValue();
	
	private int buttonHeight = P.INSTANCE.getButtonHeight();
	private int buttonWidth = P.INSTANCE.getButtonWidth();
	
	/**
	 * Creates the panel
	 */
	public UserBetPanel(IPlayer user) {
		this.user = user;
		init();
	}

	private void init() {
		this.setPreferredSize(new Dimension(buttonHeight + 10, 
				P.INSTANCE.getFrameWidth()));
		this.setLayout(new FlowLayout());
		
		leaveTableButton = new JButton("Leave table");
		leaveTableButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		leaveTableButton.setSize(buttonWidth, buttonHeight);
		leaveTableButton.addActionListener(this);
		
		availableCreditsLabel = new JLabel("" + user.getBalance().getValue());
		availableCreditsLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		availableCreditsLabel.setPreferredSize(new Dimension(60,19));

		foldButton = new JButton("Fold");
		foldButton.setEnabled(false);
		foldButton.setSize(buttonWidth, buttonHeight);
		foldButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		foldButton.setBackground(Color.red);
		foldButton.setOpaque(true);
		foldButton.addActionListener(this);
		
		callButton = new JButton("Call");
		callButton.setEnabled(false);
		callButton.setSize(buttonWidth, buttonHeight);
		callButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		callButton.setOpaque(true);
		callButton.addActionListener(this);

		checkButton = new JButton("Check");
		checkButton.setEnabled(false);
		checkButton.setSize(buttonWidth, buttonHeight);
		checkButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		checkButton.setBackground(Color.green);
		checkButton.setOpaque(true);
		checkButton.addActionListener(this);

		raiseButton = new JButton("Raise");
		raiseButton.setEnabled(false);
		raiseButton.setSize(buttonWidth, buttonHeight);
		raiseButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		raiseButton.addActionListener(this);

		creditsLabel = new JLabel("Credits:");
		creditsLabel.setSize(buttonWidth, buttonHeight);
		creditsLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		ownCurrentBetLabel = new JLabel("Own Current Bet: " + user.getOwnCurrentBet());
		ownCurrentBetLabel.setSize(buttonWidth + 50, buttonHeight);
		ownCurrentBetLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		nameLabel = new JLabel("<html><b>Player: </b>" + user.getName() + "</html>");
		nameLabel.setSize(buttonWidth, buttonHeight);
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		updateSpinner(0);
		
		this.add(leaveTableButton);
		this.add(foldButton);
		this.add(checkButton);
		this.add(callButton);
		this.add(raiseButton);
		this.add(betSpinner);
		this.add(creditsLabel);
		this.add(availableCreditsLabel);
		this.add(ownCurrentBetLabel);
		this.add(nameLabel);
	}
	
	/**
	 * Update availableCreditsLabel.
	 * @author lisastenberg
	 */
	public void updateAvailableCredits() {
		availableCreditsLabel.setText("" + user.getBalance().getValue());
		updateSpinner(user.getOwnCurrentBet());
	}
	
	/**
	 * Update betSpinner.
	 * @param currentBet the current bet on the table.
	 * @author lisastenberg
	 */
	public void updateSpinner(int currentBet) {
		int minRaise = bigblind + (currentBet - user.getOwnCurrentBet());
		if(minRaise <= user.getBalance().getValue()) {
			SpinnerModel model = new SpinnerNumberModel(minRaise, minRaise, user.getBalance().getValue(), 10);
			betSpinner = new JSpinner(model);
		} else {
			setRaiseEnabled(false);
			betSpinner = new JSpinner();
			betSpinner.setEnabled(false);
		}
		
	}
	
	/**
	 * Update ownCurrentBetLabel.
	 * @author lisastenberg
	 */
	public void updateOwnCurrentBet() {
		ownCurrentBetLabel.setText("Own Current Bet: " + user.getOwnCurrentBet());
		updateSpinner(user.getOwnCurrentBet());
	}
	
	/**
	 * Set Foldbutton enabled
	 * @param b true if the button should be enabled.
	 * @author lisastenberg
	 */
	public void setFoldEnabled(boolean b) {
		foldButton.setEnabled(b);
	}
	
	/**
	 * Set Checkbutton enabled.
	 * @param b true if the button should be enabled.
	 * @author lisastenberg
	 */
	public void setCheckEnabled(boolean b) {
		checkButton.setEnabled(b);
	}
	
	/**
	 * Set Raisebutton enabled.
	 * @param b true if the button should be enabled.
	 * @author lisastenberg
	 */
	public void setRaiseEnabled(boolean b) {
		raiseButton.setEnabled(b);
	}
	
	/**
	 * Set Callbutton enabled.
	 * @param b true if the button should be enabled.
	 * @author mattiashenriksson
	 */
	public void setCallEnabled(boolean b) {
		callButton.setEnabled(b);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(checkButton)) {
			
			EventBus.publish(new Event(Event.Tag.REQUEST_CHECK, 1));
			
		} else if (e.getSource().equals(foldButton)) {
			
			EventBus.publish(new Event(Event.Tag.REQUEST_FOLD, 1));
			
		} else if (e.getSource().equals(callButton)) {
			
			EventBus.publish(new Event(Event.Tag.REQUEST_CALL,1));
		
		} else if (e.getSource().equals(raiseButton)) {
			SpinnerNumberModel model = (SpinnerNumberModel)betSpinner.getModel();
			int value = model.getNumber().intValue();
			EventBus.publish(new Event(Event.Tag.REQUEST_RAISE,
					value));
		} else if (e.getSource().equals(leaveTableButton)) {
			EventBus.publish(new Event(Event.Tag.LEAVE_TABLE, 1));
		}
	}

}
