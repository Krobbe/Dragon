package client.gui.menu;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import client.event.Event;
import client.event.EventBus;
import client.event.EventHandler;

import common.model.player.Account;
/**
 * The panel that shows information about the account. It's possible to add
 * balance to the account in this panel.
 * 
 * @author lisastenberg
 *
 */
@SuppressWarnings("serial")
public class AccountPanel extends JPanel implements ActionListener, EventHandler {

	private JButton backButton, saveButton;
	private JPanel infoPanel;
	
	private JLabel userNameLabel;
	private JLabel firstNameLabel;
	private JLabel lastNameLabel;
	private JLabel balanceLabel;
	private JLabel addToBalance;
	private JTextField balanceField, passwordField;
	private JLabel passwordLabel;
	private JLabel error2Label, error1Label;
	
	private String background = P.INSTANCE.getBackgroundImage();
	private float transparency = P.INSTANCE.getTransparency();
	private int frameHeight = P.INSTANCE.getFrameHeight();
	private int frameWidth = P.INSTANCE.getFrameWidth();
	private int margin = P.INSTANCE.getMarginSize();
	private int buttonHeight = P.INSTANCE.getButtonHeight();
	private int buttonWidth = P.INSTANCE.getButtonWidth() + 30;
	
	private String password, username;
	
	public AccountPanel() {
		init();
		EventBus.register(this);
	}
	
	private void init() {
		this.setSize(frameWidth, frameHeight);
		this.setLayout(null);
		this.setOpaque(false);
		
		infoPanel = new JPanel();
		infoPanel.setLayout(new BorderLayout());
		
		userNameLabel = new JLabel();
		userNameLabel.setBounds(200, 127, 200, 20);
		userNameLabel.setFont(P.INSTANCE.getLabelFont());
		this.add(userNameLabel);
		
		firstNameLabel = new JLabel();
		firstNameLabel.setBounds(200, 177, 200, 20);
		firstNameLabel.setFont(P.INSTANCE.getLabelFont());
		this.add(firstNameLabel);
		
		lastNameLabel = new JLabel();
		lastNameLabel.setBounds(200, 227, 200, 20);
		lastNameLabel.setFont(P.INSTANCE.getLabelFont());
		this.add(lastNameLabel);
		
		balanceLabel = new JLabel();
		balanceLabel.setBounds(200, 277, 200, 20);
		balanceLabel.setFont(P.INSTANCE.getLabelFont());
		this.add(balanceLabel);
		
		addToBalance = new JLabel(("Add to balance:"));
		addToBalance.setBounds(200, 327, 130, 20);
		addToBalance.setFont(P.INSTANCE.getBoldLabelFont());
		this.add(addToBalance);
		
		balanceField = new JTextField();
		balanceField.setBounds(370, 327, 100, 20);
		balanceField.setFont(P.INSTANCE.getLabelFont());
		this.add(balanceField);
		
		passwordLabel = new JLabel("Input password:");
		passwordLabel.setBounds(200, 377, 150, 20);
		passwordLabel.setFont(P.INSTANCE.getBoldLabelFont());
		this.add(passwordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(370, 377, 100, 20);
		passwordField.setFont(P.INSTANCE.getLabelFont());
		this.add(passwordField);
		
		error2Label = new JLabel();
		error2Label.setBounds(480, 377, 150, 20);
		error2Label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		error2Label.setForeground(Color.red);
		this.add(error2Label);
		
		error1Label = new JLabel();
		error1Label.setBounds(480, 327, 200, 20);
		error1Label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		error1Label.setForeground(Color.red);
		this.add(error1Label);
		
		backButton = new JButton("Back");
		backButton.setFont(P.INSTANCE.getLabelFont());
		backButton.setBounds(margin, frameHeight - buttonHeight 
				- 2*margin, buttonWidth, buttonHeight);
		backButton.addActionListener(this);
		this.add(backButton);
		
		saveButton = new JButton("Save information");
		saveButton.setBounds(frameWidth - buttonWidth - margin, 
				frameHeight-buttonHeight - 2*margin, buttonWidth, buttonHeight);
		saveButton.setFont(P.INSTANCE.getLabelFont());
		saveButton.addActionListener(this);
		this.add(saveButton);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == backButton) {
			EventBus.publish(new Event(Event.Tag.GO_TO_MAIN, 1));
		} else if(e.getSource() == saveButton) {
			if(balanceField.getText().equals("")) {
				error1Label.setText("You have no information to save");
				error2Label.setText("");
			} else if(!(passwordField.getText().equals(password))) {
				error1Label.setText("");
				error2Label.setText("Incorrect password");
			} else {
				error2Label.setText("");
				error1Label.setText("");
				Account a = new Account("", "", username, password);
				a.getBalance().addToBalance(Integer.parseInt(balanceField.getText()));
				EventBus.publish(new Event(Event.Tag.ACCOUNT_CHANGED, a));
			}
		}
	}

	@Override
	public void onEvent(Event evt) {
		if(evt.getTag().equals(Event.Tag.PUBLISH_ACCOUNT_INFORMATION)) {
			Account acc = (Account)evt.getValue();
			username = acc.getUserName();
			userNameLabel.setText("<html><b>Username:</b> \t" + username + "</html>");
			firstNameLabel.setText("<html><b>Firstname:</b> \t" + acc.getFirstName() + "</html>");
			lastNameLabel.setText("<html><b>Lastname:</b> \t" + acc.getLastName() + "</html>");
			balanceLabel.setText("<html><b>Balance:</b> \t" + acc.getBalance() + "</html>");
			password = acc.getPassWord();
		}
		
	}
	
	public void paintComponent(Graphics g) {
		Image im = loadTranslucentImage(background, transparency);
		g.drawImage(im, 0, 0, frameWidth, frameHeight, null);
	}

	/**
	 * Return a translucent bufferedImage with transparency "transperancy"
	 * 
	 * @param url
	 * @param transperancy
	 * @return
	 * @author lisastenberg
	 */
	public static BufferedImage loadTranslucentImage(String url,
			float transparency) {
		BufferedImage loaded;
		try {
			loaded = ImageIO.read(new File(url));
			BufferedImage aimg = new BufferedImage(loaded.getWidth(),
					loaded.getHeight(), BufferedImage.TRANSLUCENT);
			Graphics2D g = aimg.createGraphics();
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
					transparency));
			g.drawImage(loaded, null, 0, 0);
			g.dispose();
			return aimg;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
