package view.menu;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import event.Event;
import event.EventBus;
import event.EventHandler;

@SuppressWarnings("serial")
public class CreateTablePanel extends JPanel implements ActionListener, EventHandler {

	private JTextField createTableNameField;
	private JPasswordField createTablePasswordField;
	private JComboBox<Integer> createTablePlayersSpinner;
	private JButton createTableBackButton;
	private JButton createTableCreateButton;

	public CreateTablePanel() {
		init();
		EventBus.register(this);
	}

	@Override
	public void onEvent(Event evt) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	private void init() {
		JPanel createTablePanel = new JPanel();
		// frame.getContentPane().add(createTablePanel);
		createTablePanel.setLayout(null);

		JLabel createTableNameLabel = new JLabel("Choose a table name");
		createTableNameLabel.setBounds(416, 262, 176, 20);
		createTableNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		createTablePanel.add(createTableNameLabel);

		createTableNameField = new JTextField();
		createTableNameField.setBounds(416, 293, 176, 20);
		createTablePanel.add(createTableNameField);
		createTableNameField.setColumns(10);

		JLabel createTablePasswordLabel = new JLabel("Choose a table password");
		createTablePasswordLabel.setBounds(416, 324, 176, 20);
		createTablePasswordLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		createTablePanel.add(createTablePasswordLabel);

		createTablePasswordField = new JPasswordField();
		createTablePasswordField.setBounds(416, 355, 176, 20);
		createTablePanel.add(createTablePasswordField);

		JLabel createTablePlayersLabel = new JLabel("Number of players");
		createTablePlayersLabel.setBounds(416, 386, 176, 20);
		createTablePlayersLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		createTablePanel.add(createTablePlayersLabel);

		createTablePlayersSpinner = new JComboBox<Integer>();
		createTablePlayersSpinner.setModel(new DefaultComboBoxModel<Integer>(
				new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }));
		createTablePlayersSpinner.setFont(new Font("Tahoma", Font.PLAIN, 16));
		createTablePlayersSpinner.setMaximumRowCount(10);
		createTablePlayersSpinner.setBounds(416, 417, 49, 26);
		createTablePanel.add(createTablePlayersSpinner);

		createTableBackButton = new JButton("Back");
		createTableBackButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		createTableBackButton.setBounds(10, 683, 108, 36);
		createTableBackButton.addActionListener(this);
		createTablePanel.add(createTableBackButton);

		createTableCreateButton = new JButton("Create table");
		createTableCreateButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		createTableCreateButton.setBounds(881, 683, 117, 36);
		createTableCreateButton.addActionListener(this);
		createTablePanel.add(createTableCreateButton);
	}
}
