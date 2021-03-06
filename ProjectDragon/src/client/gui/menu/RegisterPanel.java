package client.gui.menu;

import java.awt.AlphaComposite;
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
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import client.event.Event;

/**
 * The RegisterPanel is the panel where users can type in their details and register for an account.
 * @author forssenm
 *
 */
@SuppressWarnings("serial")
public class RegisterPanel extends JPanel implements ActionListener,
		client.event.EventHandler {

	private JButton registerRegisterButton;
	private JButton registerBackButton;
	private JTextField registerUserNameField;
	private JTextField registerFirstNameField;
	private JTextField registerLastNameField;
	private JPasswordField registerPassword;
	private JPasswordField registerPasswordAgainField;
	private JLabel errorLabel;
	
	private String background = P.INSTANCE.getBackgroundImage();
	private float transparency = P.INSTANCE.getTransparency();
	private int frameHeight = P.INSTANCE.getFrameHeight();
	private int frameWidth = P.INSTANCE.getFrameWidth();
	private int margin = P.INSTANCE.getMarginSize();
	private int buttonHeight = P.INSTANCE.getButtonHeight();
	private int buttonWidth = P.INSTANCE.getButtonWidth();

	/**
	 * Creates the application
	 */
	public RegisterPanel() {
		init();
		client.event.EventBus.register(this);
	}

	@Override
	public void onEvent(client.event.Event evt) {
		if (evt.getTag().equals(Event.Tag.REGISTER_FAILED)) {
			errorLabel.setText("That username already exist. " +
					"Choose another one.");
		} else if(evt.getTag().equals(Event.Tag.REGISTER_SUCCESS)
				|| evt.getTag().equals(Event.Tag.LOGIN_SUCCESS)) {
			errorLabel.setText("");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == registerRegisterButton) {
			ArrayList<char[]> registerInfo = new ArrayList<char[]>();
			registerInfo.add(registerUserNameField.getText().toCharArray());
			registerInfo.add(registerFirstNameField.getText().toCharArray());
			registerInfo.add(registerLastNameField.getText().toCharArray());
			registerInfo.add(registerPassword.getPassword());
			registerInfo.add(registerPasswordAgainField.getPassword());
			client.event.EventBus.publish(new client.event.Event(client.event.Event.Tag.TRY_REGISTER, registerInfo));
		} else if (e.getSource() == registerBackButton) {
			client.event.EventBus.publish(new client.event.Event(client.event.Event.Tag.GO_TO_LOGIN, 1));
		}
	}

	private void init() {
		// JPanel registerPanel = new JPanel();
		// frame.getContentPane().add(registerPanel);
		this.setOpaque(false);
		this.setLayout(null);
		this.setBackground(P.INSTANCE.getBackground());

		JLabel registerFirstNameLabel = new JLabel("First name\r\n");
		registerFirstNameLabel.setBounds(447, 172, 110, 14);
		registerFirstNameLabel.setFont(P.INSTANCE.getLabelFont());
		this.add(registerFirstNameLabel);

		registerFirstNameField = new JTextField();
		registerFirstNameField.setBounds(447, 197, 110, 20);
		this.add(registerFirstNameField);
		registerFirstNameField.setColumns(10);

		JLabel registerLastNameLabel = new JLabel("Last name\r\n");
		registerLastNameLabel.setBounds(447, 228, 110, 14);
		registerLastNameLabel.setFont(P.INSTANCE.getLabelFont());
		this.add(registerLastNameLabel);

		registerLastNameField = new JTextField();
		registerLastNameField.setBounds(447, 253, 110, 20);
		this.add(registerLastNameField);
		registerLastNameField.setColumns(10);

		JLabel registerUserNameLabel = new JLabel("User name\r\n");
		registerUserNameLabel.setBounds(447, 284, 110, 14);
		registerUserNameLabel.setFont(P.INSTANCE.getLabelFont());
		this.add(registerUserNameLabel);

		registerUserNameField = new JTextField();
		registerUserNameField.setBounds(447, 309, 110, 20);
		this.add(registerUserNameField);
		registerUserNameField.setColumns(10);

		JLabel registerPasswordLabel = new JLabel("Password");
		registerPasswordLabel.setBounds(447, 340, 110, 14);
		registerPasswordLabel.setFont(P.INSTANCE.getLabelFont());
		this.add(registerPasswordLabel);

		registerPassword = new JPasswordField();
		registerPassword.setBounds(447, 365, 110, 20);
		this.add(registerPassword);

		JLabel registerPasswordAgainLabel = new JLabel("Password again");
		registerPasswordAgainLabel.setBounds(447, 396, 110, 20);
		registerPasswordAgainLabel.setFont(P.INSTANCE.getLabelFont());
		this.add(registerPasswordAgainLabel);

		registerPasswordAgainField = new JPasswordField();
		registerPasswordAgainField.setBounds(447, 427, 110, 20);
		this.add(registerPasswordAgainField);

		registerRegisterButton = new JButton("Register");
		registerRegisterButton.setFont(P.INSTANCE.getLabelFont());
		registerRegisterButton.setBounds(frameWidth - buttonWidth - margin, 
				frameHeight - buttonHeight - 2*margin, buttonWidth, buttonHeight);
		registerRegisterButton.addActionListener(this);
		this.add(registerRegisterButton);

		registerBackButton = new JButton("Back");
		registerBackButton.setFont(P.INSTANCE.getLabelFont());
		registerBackButton.setBounds(margin, frameHeight - buttonHeight 
				- 2*margin, buttonWidth, buttonHeight);
		registerBackButton.addActionListener(this);
		this.add(registerBackButton);
		
		errorLabel = new JLabel();
		errorLabel.setBounds(447, 152, 300, 14);
		errorLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		errorLabel.setForeground(Color.red);
		this.add(errorLabel);
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
