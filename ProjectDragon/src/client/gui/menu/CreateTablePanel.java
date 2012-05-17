package client.gui.menu;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.event.Event;
import client.event.EventBus;
@SuppressWarnings("serial")
public class CreateTablePanel extends JPanel implements ActionListener,
		client.event.EventHandler {

	// TODO Implement these when there's time for it
	private JTextField costToEnterField;
	private JTextField numberOfChipsField;
	private JComboBox createTablePlayersSpinner;
	private JButton createTableBackButton;
	private JButton createTableCreateButton;

	public CreateTablePanel() {
		init();
		EventBus.register(this);
	}

	@Override
	public void onEvent(Event evt) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == createTableCreateButton) {
			ArrayList<String> list = new ArrayList<String>();
			list.add(costToEnterField.getText());
			list.add(numberOfChipsField.getText());
			list.add(createTablePlayersSpinner.getSelectedItem().toString());
			client.event.EventBus.publish(new client.event.Event(client.event.Event.Tag.CREATE_TABLE, list));

		} else if (e.getSource() == createTableBackButton) {
			EventBus.publish(new Event(Event.Tag.GO_TO_MAIN, 1));
		}
	}

	private void init() {
		this.setLayout(null);
		this.setBackground(P.INSTANCE.getBackground());
		
		JLabel costToEnterLabel = new JLabel("Cost to enter?");
		costToEnterLabel.setBounds(416, 262, 176, 20);
		costToEnterLabel.setFont(P.INSTANCE.getLabelFont());
		this.add(costToEnterLabel);

		costToEnterField = new JTextField();
		costToEnterField.setBounds(416, 293, 176, 20);
		this.add(costToEnterField);
		costToEnterField.setColumns(10);

		JLabel numberOfChipsLabel = new JLabel("Number of chips?");
		numberOfChipsLabel.setBounds(416, 324, 176, 20);
		numberOfChipsLabel.setFont(P.INSTANCE.getLabelFont());
		this.add(numberOfChipsLabel);

		numberOfChipsField = new JTextField();
		numberOfChipsField.setBounds(416, 355, 176, 20);
		this.add(numberOfChipsField);

		JLabel createTablePlayersLabel = new JLabel("Number of players");
		createTablePlayersLabel.setBounds(416, 386, 176, 20);
		createTablePlayersLabel.setFont(P.INSTANCE.getLabelFont());
		this.add(createTablePlayersLabel);

		createTablePlayersSpinner = new JComboBox();
		createTablePlayersSpinner.setModel(new DefaultComboBoxModel(
				new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }));
		createTablePlayersSpinner.setFont(P.INSTANCE.getLabelFont());
		createTablePlayersSpinner.setMaximumRowCount(10);
		createTablePlayersSpinner.setLocation(416, 417);
		createTablePlayersSpinner.setSize(new Dimension(80, 30));
		this.add(createTablePlayersSpinner);

		createTableBackButton = new JButton("Back");
		createTableBackButton.setFont(P.INSTANCE.getLabelFont());
		createTableBackButton.setBounds(10, 683, 108, 36);
		createTableBackButton.addActionListener(this);
		this.add(createTableBackButton);

		createTableCreateButton = new JButton("Create table");
		createTableCreateButton.setFont(P.INSTANCE.getLabelFont());
		createTableCreateButton.setBounds(881, 683, 117, 36);
		createTableCreateButton.addActionListener(this);
		this.add(createTableCreateButton);
	}
}
