package view.menu;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import event.Event;
import event.EventBus;
import event.EventHandler;

@SuppressWarnings("serial")
public class RegisterPanel extends JPanel implements ActionListener,
		EventHandler {

	private JButton registerRegisterButton;
	private JButton registerBackButton;
	private JTextField registerNameField;
	private JPasswordField registerPassword;
	private JPasswordField registerPasswordAgainField;

	public RegisterPanel() {
		init();
		EventBus.register(this);
	}

	@Override
	public void onEvent(Event evt) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == registerRegisterButton) {
			EventBus.publish(new Event(Event.Tag.REGISTER_ACCOUNT, 1));
		} else if (e.getSource() == registerBackButton) {
			EventBus.publish(new Event(Event.Tag.REGISTER_BACK, 1));
		}
	}

	private void init() {
		JPanel registerPanel = new JPanel();
		// frame.getContentPane().add(registerPanel);
		registerPanel.setLayout(null);

		JLabel registerNameLabel = new JLabel("User name\r\n");
		registerNameLabel.setBounds(461, 284, 110, 14);
		registerNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		registerPanel.add(registerNameLabel);

		registerNameField = new JTextField();
		registerNameField.setBounds(461, 309, 110, 20);
		registerPanel.add(registerNameField);
		registerNameField.setColumns(10);

		JLabel registerPasswordLabel = new JLabel("Password");
		registerPasswordLabel.setBounds(461, 340, 110, 14);
		registerPasswordLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		registerPanel.add(registerPasswordLabel);

		registerPassword = new JPasswordField();
		registerPassword.setBounds(461, 365, 110, 20);
		registerPanel.add(registerPassword);

		JLabel registerPasswordAgainLabel = new JLabel("Password again");
		registerPasswordAgainLabel.setBounds(461, 396, 110, 20);
		registerPasswordAgainLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		registerPanel.add(registerPasswordAgainLabel);

		registerPasswordAgainField = new JPasswordField();
		registerPasswordAgainField.setBounds(461, 427, 110, 20);
		registerPanel.add(registerPasswordAgainField);

		registerRegisterButton = new JButton("Register");
		registerRegisterButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		registerRegisterButton.setBounds(890, 683, 108, 36);
		registerRegisterButton.addActionListener(this);
		registerPanel.add(registerRegisterButton);

		registerBackButton = new JButton("Back");
		registerBackButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		registerBackButton.setBounds(10, 683, 108, 36);
		registerBackButton.addActionListener(this);
		registerPanel.add(registerBackButton);
	}

}
