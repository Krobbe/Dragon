package view.menu;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.CardLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;

import event.Event;
import event.EventBus;
import event.EventHandler;

public class MenuView implements EventHandler, ActionListener {

	private JFrame frame;
	private JTextField loginNameField;
	private JPasswordField loginPasswordField;
	private JTextField registerNameField;
	private JPasswordField registerPassword;
	private JPasswordField registerPasswordAgainField;
	private JTextField createTableNameField;
	private JPasswordField createTablePasswordField;
	private JComboBox createTablePlayersSpinner;
	private JButton loginButton;
	private JButton registerRegisterButton;
	private JButton registerBackButton;
	private JButton mainLogoutButton;
	private JButton mainJoinTableButton;
	private JButton mainCreateTableButton;
	private JButton mainViewStatisticsButton;
	private JButton createTableBackButton;
	private JButton createTableCreateButton;
	private JPanel loginPanel;

	/**
	 * Create the application.
	 */
	public MenuView() {
		initialize();
		EventBus.register(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1024, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));

		
		//Code for the login panel
		JPanel loginPanel = new JPanel();
		frame.getContentPane().add(loginPanel);
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

		JButton loginButton = new JButton("Login");
		loginButton.setBounds(890, 683, 108, 36);
		loginButton.addActionListener(this);
		loginPanel.add(loginButton);
		loginButton.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel noAccountLabel = new JLabel("Don't have an account?" + "\n" + "");
		noAccountLabel.setBounds(447, 386, 113, 14);
		loginPanel.add(noAccountLabel);

		JButton loginRegisterButton = new JButton("Register");
		loginRegisterButton.setBounds(447, 401, 113, 23);
		loginRegisterButton.addActionListener(this);
		loginPanel.add(loginRegisterButton);
		
		
		//Code for the register panel
		JPanel registerPanel = new JPanel();
		frame.getContentPane().add(registerPanel);
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

		JButton registerRegisterButton = new JButton("Register");
		registerRegisterButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		registerRegisterButton.setBounds(890, 683, 108, 36);
		registerRegisterButton.addActionListener(this);
		registerPanel.add(registerRegisterButton);

		JButton registerBackButton = new JButton("Back");
		registerBackButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		registerBackButton.setBounds(10, 683, 108, 36);
		registerBackButton.addActionListener(this);
		registerPanel.add(registerBackButton);
		
		
		//Code for the main panel
		JPanel mainPanel = new JPanel();
		frame.getContentPane().add(mainPanel);
		mainPanel.setLayout(null);

		JButton mainLogoutButton = new JButton("Log out");
		mainLogoutButton.setBounds(10, 683, 108, 36);
		mainLogoutButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		mainLogoutButton.addActionListener(this);
		mainPanel.add(mainLogoutButton);

		JButton mainJoinTableButton = new JButton("Join table");
		mainJoinTableButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		mainJoinTableButton.setBounds(420, 292, 167, 146);
		mainJoinTableButton.addActionListener(this);
		mainPanel.add(mainJoinTableButton);

		JButton mainCreateTableButton = new JButton("Create table");
		mainCreateTableButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		mainCreateTableButton.setBounds(128, 292, 167, 146);
		mainCreateTableButton.addActionListener(this);
		mainPanel.add(mainCreateTableButton);

		JButton mainViewStatisticsButton = new JButton("View statistics");
		mainViewStatisticsButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		mainViewStatisticsButton.setBounds(704, 292, 167, 146);
		mainViewStatisticsButton.addActionListener(this);
		mainPanel.add(mainViewStatisticsButton);
		
		
		//Code for the create table panel
		JPanel createTablePanel = new JPanel();
		frame.getContentPane().add(createTablePanel);
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

		createTablePlayersSpinner = new JComboBox();
		createTablePlayersSpinner.setModel(new DefaultComboBoxModel(
				new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9",
						"10" }));
		createTablePlayersSpinner.setFont(new Font("Tahoma", Font.PLAIN, 16));
		createTablePlayersSpinner.setMaximumRowCount(10);
		createTablePlayersSpinner.setBounds(416, 417, 49, 26);
		createTablePanel.add(createTablePlayersSpinner);

		JButton createTableBackButton = new JButton("Back");
		createTableBackButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		createTableBackButton.setBounds(10, 683, 108, 36);
		createTableBackButton.addActionListener(this);
		createTablePanel.add(createTableBackButton);

		JButton createTableCreateButton = new JButton("Create table");
		createTableCreateButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		createTableCreateButton.setBounds(881, 683, 117, 36);
		createTableCreateButton.addActionListener(this);
		createTablePanel.add(createTableCreateButton);
		
		
		//Code for the join table panel
		JPanel joinTablePanel = new JPanel();
		frame.getContentPane().add(joinTablePanel);
		joinTablePanel.setLayout(null);

		JButton joinTableBackButton = new JButton("Back");
		joinTableBackButton.setBounds(10, 683, 108, 36);
		joinTableBackButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		joinTableBackButton.addActionListener(this);
		joinTablePanel.add(joinTableBackButton);

		JButton joinTableJoinButton = new JButton("Join table");
		joinTableJoinButton.setBounds(890, 683, 108, 36);
		joinTableJoinButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		joinTableJoinButton.addActionListener(this);
		joinTablePanel.add(joinTableJoinButton);

		JList joinTableList = new JList();
		joinTableList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		joinTableList.setFont(new Font("Tahoma", Font.PLAIN, 16));
		joinTableList.setModel(new AbstractListModel() {
			String[] values = new String[] {};

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		joinTableList.setBounds(192, 111, 623, 507);
		joinTablePanel.add(joinTableList);
	}

	@Override
	public void onEvent(Event evt) {
        if (evt.getTag().equals(Event.Tag.LOGIN)) {
            //TODO Change to the mainMenuPanel
        }
        else if(evt.getTag().equals(Event.Tag.GO_TO_CREATETABLE)) {
        	//TODO change to the createTablePanel
        }
        	//etc...
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == loginButton) {
			EventBus.publish(new Event(Event.Tag.LOGIN, 1));
		}
		else if(e.getSource() == registerRegisterButton) {
			EventBus.publish(new Event(Event.Tag.REGISTER_ACCOUNT, 1));
		}
		else if(e.getSource() == registerBackButton) {
			EventBus.publish(new Event(Event.Tag.REGISTER_BACK, 1));
		}
		else if(e.getSource() == mainLogoutButton) {
			EventBus.publish(new Event(Event.Tag.LOGOUT, 1));
		}
		else if(e.getSource() == mainJoinTableButton) {
			EventBus.publish(new Event(Event.Tag.GO_TO_JOINTABLE, 1));
		}
		else if(e.getSource() == mainCreateTableButton) {
			EventBus.publish(new Event(Event.Tag.GO_TO_CREATETABLE, 1));
		}
		else if(e.getSource() == mainViewStatisticsButton) {
			EventBus.publish(new Event(Event.Tag.GO_TO_STATISTICS, 1));
		}
		else if(e.getSource() == createTableBackButton) {
			EventBus.publish(new Event(Event.Tag.REGISTER_BACK, 1));
		}
		else if(e.getSource() == createTableCreateButton) {
			EventBus.publish(new Event(Event.Tag.CREATE_TABLE, 1));
		}
	}
}
