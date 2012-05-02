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
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginButton) {
			EventBus.publish(new Event(Event.Tag.LOGIN, 1));
		} else if (e.getSource() == loginRegisterButton) {
			EventBus.publish(new Event(Event.Tag.GO_TO_REGISTER, 1));
		}
	}

	private void init() {
		JPanel loginPanel = new JPanel();
		loginPanel.setLayout(null);

		JLabel loginNameLabel = new JLabel("User name");
		loginNameLabel.setBounds(447, 274, 108, 14);
		loginPanel.add(loginNameLabel);
		loginNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));

		loginNameField = new JTextField();
		loginNameField.setBounds(447, 299, 113, 20);
		loginPanel.add(loginNameField);
		loginNameField.setToolTipText("Type in your account name here\r\n");
		loginNameField.setColumns(10);
		loginNameLabel.setLabelFor(loginNameField);

		JLabel loginPasswordLabel = new JLabel("Password\r\n");
		loginPasswordLabel.setBounds(447, 330, 108, 14);
		loginPanel.add(loginPasswordLabel);
		loginPasswordLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));

		loginPasswordField = new JPasswordField();
		loginPasswordField.setBounds(447, 355, 113, 20);
		loginPanel.add(loginPasswordField);

		loginButton = new JButton("Login");
		loginButton.setBounds(890, 683, 108, 36);
		loginButton.addActionListener(this);
		loginPanel.add(loginButton);
		loginButton.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel noAccountLabel = new JLabel("Don't have an account?" + "\n" + "");
		noAccountLabel.setBounds(447, 386, 113, 14);
		loginPanel.add(noAccountLabel);

		loginRegisterButton = new JButton("Register");
		loginRegisterButton.setBounds(447, 401, 113, 23);
		loginRegisterButton.addActionListener(this);
		loginPanel.add(loginRegisterButton);
	}
}
