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
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.AbstractAction;
import javax.swing.Action;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;

public class MenuView extends Observable {

	private JFrame frame;
	private JTextField loginNameField;
	private JPasswordField loginPasswordField;
	private JTextField registerNameField;
	private JPasswordField registerPassword;
	private JPasswordField registerPasswordAgainField;
	private JTextField createTableNameField;
	private JPasswordField createTablePasswordField;
	private JComboBox createTablePlayersSpinner;

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
		loginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				notifyAll();
			}
		});
		loginButton.setBounds(890, 683, 108, 36);
		loginPanel.add(loginButton);
		loginButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel noAccountLabel = new JLabel("Don't have an account?" + "\n" + "");
		noAccountLabel.setBounds(447, 386, 113, 14);
		loginPanel.add(noAccountLabel);
		
		JButton loginRegisterButton = new JButton("Register");
		loginRegisterButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				notifyAll();
			}
		});
		loginRegisterButton.setBounds(447, 401, 113, 23);
		loginPanel.add(loginRegisterButton);
		
		JPanel registerPanel = new JPanel();
		frame.getContentPane().add(registerPanel, "name_176451557173287");
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
		registerRegisterButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				notifyAll();
			}
		});
		registerRegisterButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		registerRegisterButton.setBounds(890, 683, 108, 36);
		registerPanel.add(registerRegisterButton);
		
		JButton registerBackButton = new JButton("Back");
		registerBackButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				notifyAll();
			}
		});
		registerBackButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		registerBackButton.setBounds(10, 683, 108, 36);
		registerPanel.add(registerBackButton);
		
		JPanel mainPanel = new JPanel();
		frame.getContentPane().add(mainPanel, "name_183956866615909");
		mainPanel.setLayout(null);
		
		JButton mainLogoutButton = new JButton("Log out");
		mainLogoutButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				notifyAll();
			}
		});
		mainLogoutButton.setBounds(10, 683, 108, 36);
		mainLogoutButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		mainPanel.add(mainLogoutButton);
		
		JButton mainJoinTableButton = new JButton("Join table");
		mainJoinTableButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				notifyAll();
			}
		});
		mainJoinTableButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		mainJoinTableButton.setBounds(420, 292, 167, 146);
		mainPanel.add(mainJoinTableButton);
		
		JButton mainCreateTableButton = new JButton("Create table");
		mainCreateTableButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				notifyAll();
			}
		});
		mainCreateTableButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		mainCreateTableButton.setBounds(128, 292, 167, 146);
		mainPanel.add(mainCreateTableButton);
		
		JButton mainViewStatisticsButton = new JButton("View statistics");
		mainViewStatisticsButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				notifyAll();
			}
		});
		mainViewStatisticsButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		mainViewStatisticsButton.setBounds(704, 292, 167, 146);
		mainPanel.add(mainViewStatisticsButton);
		
		JPanel createTablePanel = new JPanel();
		frame.getContentPane().add(createTablePanel, "name_197255274162820");
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
		createTablePlayersSpinner.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		createTablePlayersSpinner.setFont(new Font("Tahoma", Font.PLAIN, 16));
		createTablePlayersSpinner.setMaximumRowCount(10);
		createTablePlayersSpinner.setBounds(416, 417, 49, 26);
		createTablePanel.add(createTablePlayersSpinner);
		
		JButton createTableBackButton = new JButton("Back");
		createTableBackButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				notifyAll();
			}
		});
		createTableBackButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		createTableBackButton.setBounds(10, 683, 108, 36);
		createTablePanel.add(createTableBackButton);
		
		JButton createTableCreateButton = new JButton("Create table");
		createTableCreateButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				notifyAll();
			}
		});
		createTableCreateButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		createTableCreateButton.setBounds(881, 683, 117, 36);
		createTablePanel.add(createTableCreateButton);
		
		JPanel joinTablePanel = new JPanel();
		frame.getContentPane().add(joinTablePanel, "name_200411312977830");
		joinTablePanel.setLayout(null);
		
		JButton joinTableBackButton = new JButton("Back");
		joinTableBackButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				notifyAll();
			}
		});
		joinTableBackButton.setBounds(10, 683, 108, 36);
		joinTableBackButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		joinTablePanel.add(joinTableBackButton);
		
		JButton joinTableJoinButton = new JButton("Join table");
		joinTableJoinButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				notifyAll();
			}
		});
		joinTableJoinButton.setBounds(890, 683, 108, 36);
		joinTableJoinButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
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
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
