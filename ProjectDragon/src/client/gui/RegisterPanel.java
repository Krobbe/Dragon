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

import event.Event;
import event.EventBus;
import event.EventHandler;

@SuppressWarnings("serial")
public class RegisterPanel extends JPanel implements ActionListener,
		EventHandler {

	private JButton registerRegisterButton;
	private JButton registerBackButton;
	private JTextField registerUserNameField;
	private JTextField registerFirstNameField;
	private JTextField registerLastNameField;
	private JPasswordField registerPassword;
	private JPasswordField registerPasswordAgainField;

	public RegisterPanel() {
		init();
		EventBus.register(this);
	}

	@Override
	public void onEvent(Event evt) {
		if (evt.getTag().equals(Event.Tag.REGISTER_FAILED)) {
			JOptionPane.showMessageDialog(null, "Incorrect values");
			// TODO Better solution is required to show what value was "bad"
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == registerRegisterButton) {
			EventBus.publish(new Event(Event.Tag.TRY_REGISTER,
					registerFirstNameField.getText() + " "
							+ registerLastNameField.getText() + " "
							+ registerUserNameField.getText() + " "
							+ registerPassword.getPassword() + " "
							+ registerPasswordAgainField.getPassword()));
			// TODO Security with passwords?
		} else if (e.getSource() == registerBackButton) {
			EventBus.publish(new Event(Event.Tag.REGISTER_BACK, 1));
		}
	}

	private void init() {
		// JPanel registerPanel = new JPanel();
		// frame.getContentPane().add(registerPanel);
		this.setLayout(null);

		JLabel registerFirstNameLabel = new JLabel("First name\r\n");
		registerFirstNameLabel.setBounds(447, 172, 110, 14);
		registerFirstNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(registerFirstNameLabel);

		registerFirstNameField = new JTextField();
		registerFirstNameField.setBounds(447, 197, 110, 20);
		this.add(registerFirstNameField);
		registerFirstNameField.setColumns(10);

		JLabel registerLastNameLabel = new JLabel("Last name\r\n");
		registerLastNameLabel.setBounds(447, 228, 110, 14);
		registerLastNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(registerLastNameLabel);

		registerLastNameField = new JTextField();
		registerLastNameField.setBounds(447, 253, 110, 20);
		this.add(registerLastNameField);
		registerLastNameField.setColumns(10);

		JLabel registerUserNameLabel = new JLabel("User name\r\n");
		registerUserNameLabel.setBounds(447, 284, 110, 14);
		registerUserNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(registerUserNameLabel);

		registerUserNameField = new JTextField();
		registerUserNameField.setBounds(447, 309, 110, 20);
		this.add(registerUserNameField);
		registerUserNameField.setColumns(10);

		JLabel registerPasswordLabel = new JLabel("Password");
		registerPasswordLabel.setBounds(447, 340, 110, 14);
		registerPasswordLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(registerPasswordLabel);

		registerPassword = new JPasswordField();
		registerPassword.setBounds(447, 365, 110, 20);
		this.add(registerPassword);

		JLabel registerPasswordAgainLabel = new JLabel("Password again");
		registerPasswordAgainLabel.setBounds(447, 396, 110, 20);
		registerPasswordAgainLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(registerPasswordAgainLabel);

		registerPasswordAgainField = new JPasswordField();
		registerPasswordAgainField.setBounds(447, 427, 110, 20);
		this.add(registerPasswordAgainField);

		registerRegisterButton = new JButton("Register");
		registerRegisterButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		registerRegisterButton.setBounds(890, 683, 108, 36);
		registerRegisterButton.addActionListener(this);
		this.add(registerRegisterButton);

		registerBackButton = new JButton("Back");
		registerBackButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		registerBackButton.setBounds(10, 683, 108, 36);
		registerBackButton.addActionListener(this);
		this.add(registerBackButton);
	}

}