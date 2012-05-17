package client.gui.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.RenderingHints.Key;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import client.event.*;

/**
 * LoginPanel is the panel in which users can login to the application
 * @author forssenm
 *
 */
@SuppressWarnings("serial")
public class LoginPanel extends JPanel implements KeyListener, ActionListener, client.event.EventHandler {

	private JTextField loginNameField;
	private JPasswordField loginPasswordField;
	private JButton loginButton;
	private JButton loginRegisterButton;
	private JLabel errorLabel;

	
	/**
	 * Creates the panel
	 */
	public LoginPanel() {
		init();
		client.event.EventBus.register(this);
	}

	@Override
	public void onEvent(Event evt) {
		if (evt.getTag().equals(Event.Tag.LOGIN_FAILED)) {
			errorLabel.setText("Incorrect username or password");
		} else if(evt.getTag().equals(Event.Tag.LOGIN_SUCCESS)) {
			errorLabel.setText("");
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginButton) {
			ArrayList<char[]> loginInfo = new ArrayList<char[]>();
			loginInfo.add(loginNameField.getText().toCharArray());
			loginInfo.add(loginPasswordField.getPassword());
			EventBus.publish(new Event(Event.Tag.TRY_LOGIN, loginInfo));
		} else if (e.getSource() == loginRegisterButton) {
			EventBus.publish(new Event(Event.Tag.GO_TO_REGISTER, 1));
		}
	}

	private void init() {
		this.setLayout(null);
		this.setBackground(P.INSTANCE.getBackground());

		JLabel loginNameLabel = new JLabel("User name");
		loginNameLabel.setBounds(447, 274, 108, 14);
		this.add(loginNameLabel);
		loginNameLabel.setFont(P.INSTANCE.getLabelFont());

		loginNameField = new JTextField();
		loginNameField.setBounds(447, 299, 113, 20);
		this.add(loginNameField);
		loginNameField.setToolTipText("Type in your account name here\r\n");
		loginNameField.setColumns(10);
		loginNameLabel.setLabelFor(loginNameField);

		JLabel loginPasswordLabel = new JLabel("Password\r\n");
		loginPasswordLabel.setBounds(447, 330, 108, 14);
		this.add(loginPasswordLabel);
		loginPasswordLabel.setFont(P.INSTANCE.getLabelFont());

		loginPasswordField = new JPasswordField();
		loginPasswordField.setBounds(447, 355, 113, 20);
		loginPasswordField.addKeyListener(this);
		this.add(loginPasswordField);

		loginButton = new JButton("Login");
		loginButton.setBounds(890, 683, 108, 36);
		loginButton.addActionListener(this);
		this.add(loginButton);
		loginButton.setFont(P.INSTANCE.getLabelFont());

		JLabel noAccountLabel = new JLabel("Don't have an account?" + "\n" + "");
		noAccountLabel.setBounds(447, 386, 113, 14);
		noAccountLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		this.add(noAccountLabel);

		loginRegisterButton = new JButton("Register");
		loginRegisterButton.setBounds(447, 401, 113, 23);
		loginRegisterButton.addActionListener(this);
		this.add(loginRegisterButton);
		
		errorLabel = new JLabel();
		errorLabel.setBounds(447, 249, 208, 14);
		errorLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		errorLabel.setForeground(Color.red);
		this.add(errorLabel);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyChar() == KeyEvent.VK_ENTER) {
			ArrayList<char[]> loginInfo = new ArrayList<char[]>();
			loginInfo.add(loginNameField.getText().toCharArray());
			loginInfo.add(loginPasswordField.getPassword());
			EventBus.publish(new Event(Event.Tag.TRY_LOGIN, loginInfo));
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
