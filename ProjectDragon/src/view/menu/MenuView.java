package view.menu;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JInternalFrame;
import java.awt.Insets;
import javax.swing.JPasswordField;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextPane;
import java.awt.CardLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class MenuView {

	private JFrame frame;
	private JTextField userNameField;
	private JPasswordField passwordField;
	private JTextField userNameTextField;
	private JPasswordField regPassword;
	private JPasswordField regPasswordAgain;
	private JTextField tableNameField;
	private JPasswordField createTablePassword;
	private JComboBox numberOfPlayers;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuView window = new MenuView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MenuView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1024, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		JPanel loginPanel = new JPanel();
		frame.getContentPane().add(loginPanel);
		loginPanel.setLayout(null);
		
		JLabel userNameLabel_1 = new JLabel("User name");
		userNameLabel_1.setBounds(447, 274, 108, 14);
		loginPanel.add(userNameLabel_1);
		userNameLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		userNameField = new JTextField();
		userNameField.setBounds(447, 299, 113, 20);
		loginPanel.add(userNameField);
		userNameField.setToolTipText("Type in your account name here\r\n");
		userNameField.setColumns(10);
		userNameLabel_1.setLabelFor(userNameField);
		
		JLabel passwordLabel = new JLabel("Password\r\n");
		passwordLabel.setBounds(447, 330, 108, 14);
		loginPanel.add(passwordLabel);
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		passwordField = new JPasswordField();
		passwordField.setBounds(447, 355, 113, 20);
		loginPanel.add(passwordField);
		
		JButton loginButton = new JButton("Login");
		loginButton.setBounds(890, 683, 108, 36);
		loginPanel.add(loginButton);
		loginButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblDontHaveAn = new JLabel("Don't have an account?" + "\n" + "");
		lblDontHaveAn.setBounds(447, 386, 113, 20);
		loginPanel.add(lblDontHaveAn);
		
		JButton newUserButton = new JButton("Register");
		newUserButton.setBounds(447, 401, 113, 23);
		loginPanel.add(newUserButton);
		
		JPanel registerPanel = new JPanel();
		frame.getContentPane().add(registerPanel, "name_176451557173287");
		registerPanel.setLayout(null);
		
		JLabel userNameLabel = new JLabel("User name\r\n");
		userNameLabel.setBounds(461, 284, 110, 14);
		userNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		registerPanel.add(userNameLabel);
		
		userNameTextField = new JTextField();
		userNameTextField.setBounds(461, 309, 110, 20);
		registerPanel.add(userNameTextField);
		userNameTextField.setColumns(10);
		
		JLabel regPasswordLabel = new JLabel("Password");
		regPasswordLabel.setBounds(461, 340, 110, 14);
		regPasswordLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		registerPanel.add(regPasswordLabel);
		
		regPassword = new JPasswordField();
		regPassword.setBounds(461, 365, 110, 20);
		registerPanel.add(regPassword);
		
		JLabel lblPasswordAgain = new JLabel("Password again");
		lblPasswordAgain.setBounds(461, 396, 110, 20);
		lblPasswordAgain.setFont(new Font("Tahoma", Font.PLAIN, 16));
		registerPanel.add(lblPasswordAgain);
		
		regPasswordAgain = new JPasswordField();
		regPasswordAgain.setBounds(461, 427, 110, 20);
		registerPanel.add(regPasswordAgain);
		
		JButton registerButton = new JButton("Register");
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		registerButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		registerButton.setBounds(890, 683, 108, 36);
		registerPanel.add(registerButton);
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		backButton.setBounds(10, 683, 108, 36);
		registerPanel.add(backButton);
		
		JPanel mainPanel = new JPanel();
		frame.getContentPane().add(mainPanel, "name_183956866615909");
		mainPanel.setLayout(null);
		
		JButton logOutButton = new JButton("Log out");
		logOutButton.setBounds(10, 683, 108, 36);
		logOutButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		mainPanel.add(logOutButton);
		
		JButton btnNewButton = new JButton("Join table");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(420, 292, 167, 146);
		mainPanel.add(btnNewButton);
		
		JButton btnCreateTable = new JButton("Create table");
		btnCreateTable.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCreateTable.setBounds(128, 292, 167, 146);
		mainPanel.add(btnCreateTable);
		
		JButton btnViewStatistics = new JButton("View statistics");
		btnViewStatistics.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnViewStatistics.setBounds(704, 292, 167, 146);
		mainPanel.add(btnViewStatistics);
		
		JPanel createTablePanel = new JPanel();
		frame.getContentPane().add(createTablePanel, "name_197255274162820");
		createTablePanel.setLayout(null);
		
		JLabel lblChoseATable = new JLabel("Choose a table name");
		lblChoseATable.setBounds(416, 262, 176, 20);
		lblChoseATable.setFont(new Font("Tahoma", Font.PLAIN, 16));
		createTablePanel.add(lblChoseATable);
		
		tableNameField = new JTextField();
		tableNameField.setBounds(416, 293, 176, 20);
		createTablePanel.add(tableNameField);
		tableNameField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Choose a table password");
		lblNewLabel.setBounds(416, 324, 176, 20);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		createTablePanel.add(lblNewLabel);
		
		createTablePassword = new JPasswordField();
		createTablePassword.setBounds(416, 355, 176, 20);
		createTablePanel.add(createTablePassword);
		
		JLabel lblNewLabel_1 = new JLabel("Number of players");
		lblNewLabel_1.setBounds(416, 386, 176, 20);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		createTablePanel.add(lblNewLabel_1);
		
		numberOfPlayers = new JComboBox();
		numberOfPlayers.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		numberOfPlayers.setFont(new Font("Tahoma", Font.PLAIN, 16));
		numberOfPlayers.setMaximumRowCount(10);
		numberOfPlayers.setBounds(416, 417, 49, 26);
		createTablePanel.add(numberOfPlayers);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBack.setBounds(10, 683, 108, 36);
		createTablePanel.add(btnBack);
		
		JButton createTableButton = new JButton("Create table");
		createTableButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		createTableButton.setBounds(881, 683, 117, 36);
		createTablePanel.add(createTableButton);
		
		JPanel joinTablePanel = new JPanel();
		frame.getContentPane().add(joinTablePanel, "name_200411312977830");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
	}
}
