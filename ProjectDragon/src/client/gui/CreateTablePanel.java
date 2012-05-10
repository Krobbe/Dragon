package client.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class CreateTablePanel extends JPanel implements ActionListener,
		client.event.EventHandler {

	// TODO Implement these when there's time for it
	// private JTextField createTableNameField;
	// private JPasswordField createTablePasswordField;
	private JComboBox<Integer> createTablePlayersSpinner;
	private JButton createTableBackButton;
	private JButton createTableCreateButton;

	public CreateTablePanel() {
		init();
		client.event.EventBus.register(this);
	}

	@Override
	public void onEvent(client.event.Event evt) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Skicka med antal spelare?
		if (e.getSource() == createTableCreateButton) {
			client.event.EventBus.publish(new client.event.Event(client.event.Event.Tag.CREATE_TABLE, 1));
		} else if (e.getSource() == createTableBackButton) {
			client.event.EventBus.publish(new client.event.Event(client.event.Event.Tag.GO_TO_MAIN, 1));
		}
	}

	private void init() {
		this.setLayout(null);

		JLabel createTableNameLabel = new JLabel("Choose a table name");
		createTableNameLabel.setBounds(416, 262, 176, 20);
		createTableNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(createTableNameLabel);

		// createTableNameField = new JTextField();
		// createTableNameField.setBounds(416, 293, 176, 20);
		// this.add(createTableNameField);
		// createTableNameField.setColumns(10);

		JLabel createTablePasswordLabel = new JLabel("Choose a table password");
		createTablePasswordLabel.setBounds(416, 324, 176, 20);
		createTablePasswordLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(createTablePasswordLabel);

		// createTablePasswordField = new JPasswordField();
		// createTablePasswordField.setBounds(416, 355, 176, 20);
		// this.add(createTablePasswordField);

		JLabel createTablePlayersLabel = new JLabel("Number of players");
		createTablePlayersLabel.setBounds(416, 386, 176, 20);
		createTablePlayersLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(createTablePlayersLabel);

		createTablePlayersSpinner = new JComboBox<Integer>();
		createTablePlayersSpinner.setModel(new DefaultComboBoxModel<Integer>(
				new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }));
		createTablePlayersSpinner.setFont(new Font("Tahoma", Font.PLAIN, 16));
		createTablePlayersSpinner.setMaximumRowCount(10);
		createTablePlayersSpinner.setBounds(416, 417, 49, 26);
		this.add(createTablePlayersSpinner);

		createTableBackButton = new JButton("Back");
		createTableBackButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		createTableBackButton.setBounds(10, 683, 108, 36);
		createTableBackButton.addActionListener(this);
		this.add(createTableBackButton);

		createTableCreateButton = new JButton("Create table");
		createTableCreateButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		createTableCreateButton.setBounds(881, 683, 117, 36);
		createTableCreateButton.addActionListener(this);
		this.add(createTableCreateButton);
	}
}
