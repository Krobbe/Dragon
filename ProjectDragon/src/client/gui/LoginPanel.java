package client.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.player.Account;

import database.IDBAccount;

import event.Event;
import event.EventBus;
import event.EventHandler;

@SuppressWarnings("serial")
public class LoginPanel extends JPanel implements ActionListener, EventHandler {

	private JTextField loginNameField;
	private JPasswordField loginPasswordField;
	private JButton loginButton;
	private JButton loginRegisterButton;

	public LoginPanel() {
		init();
		EventBus.register(this);
	}

	@Override
	public void onEvent(Event evt) {
		if (evt.getTag().equals(Event.Tag.LOGIN_FAILED)) {
			JOptionPane.showMessageDialog(null,
					"Incorrect username or password");
			// TODO A better solution to inform about a failed login, pop-ups
			// are baaaaaad!
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginButton) {
			EventBus.publish(new Event(Event.Tag.TRY_LOGIN, loginNameField
					.getText() + " " + loginPasswordField.getPassword()));
			// TODO Security with passwords?
		} else if (e.getSource() == loginRegisterButton) {
			EventBus.publish(new Event(Event.Tag.GO_TO_REGISTER, 1));
		}
	}

	private void init() {
		this.setLayout(null);

		JLabel loginNameLabel = new JLabel("User name");
		loginNameLabel.setBounds(447, 274, 108, 14);
		this.add(loginNameLabel);
		loginNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));

		loginNameField = new JTextField();
		loginNameField.setBounds(447, 299, 113, 20);
		this.add(loginNameField);
		loginNameField.setToolTipText("Type in your account name here\r\n");
		loginNameField.setColumns(10);
		loginNameLabel.setLabelFor(loginNameField);

		JLabel loginPasswordLabel = new JLabel("Password\r\n");
		loginPasswordLabel.setBounds(447, 330, 108, 14);
		this.add(loginPasswordLabel);
		loginPasswordLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));

		loginPasswordField = new JPasswordField();
		loginPasswordField.setBounds(447, 355, 113, 20);
		this.add(loginPasswordField);

		loginButton = new JButton("Login");
		loginButton.setBounds(890, 683, 108, 36);
		loginButton.addActionListener(this);
		this.add(loginButton);
		loginButton.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel noAccountLabel = new JLabel("Don't have an account?" + "\n" + "");
		noAccountLabel.setBounds(447, 386, 113, 14);
		noAccountLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		this.add(noAccountLabel);

		loginRegisterButton = new JButton("Register");
		loginRegisterButton.setBounds(447, 401, 113, 23);
		loginRegisterButton.addActionListener(this);
		this.add(loginRegisterButton);
	}
}
