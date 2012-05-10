package client.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.player.Player;

import database.*;


@SuppressWarnings("serial")
public class StatisticsPanel extends JPanel implements ActionListener,
		client.event.EventHandler, IDBGame {
	DatabaseCommunicator dbc = DatabaseCommunicator.getInstance();

	private JLabel setThisUserName;
	private JLabel setThisFirstName;
	private JLabel setThisLastName;
	private JLabel setThisPlayedGames;
	private JLabel setThisWonGames;

	private JButton statisticsBackButton;

	public StatisticsPanel() {
		init();
		client.event.EventBus.register(this);
	}

	@Override
	public void onEvent(client.event.Event evt) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == statisticsBackButton) {
			client.event.EventBus.publish(new client.event.Event(client.event.Event.Tag.GO_TO_MAIN, 1));
		}
	}

	private void init() {
		this.setLayout(null);
		
		JLabel userName = new JLabel("User name");
		userName.setBounds(447, 172, 110, 14);
		userName.setFont(new Font("Tahoma", Font.BOLD, 16));
		this.add(userName);
		
		//TODO set the different values, get them from where?
		setThisUserName = new JLabel("Get the name from where?");
		setThisUserName.setBounds(447, 197, 110, 14);
		setThisUserName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(setThisUserName);
		
		JLabel firstName = new JLabel("First name");
		firstName.setBounds(447, 222, 110, 14);
		firstName.setFont(new Font("Tahoma", Font.BOLD, 16));
		this.add(firstName);
		
		setThisFirstName = new JLabel("Get the name from where?");
		setThisFirstName.setBounds(447, 247, 110, 14);
		setThisFirstName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(setThisFirstName);
		
		JLabel lastName = new JLabel("Last name");
		lastName.setBounds(447, 272, 110, 14);
		lastName.setFont(new Font("Tahoma", Font.BOLD, 16));
		this.add(lastName);
		
		setThisLastName = new JLabel("Get the name from where?");
		setThisLastName.setBounds(447, 297, 110, 14);
		setThisLastName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(setThisLastName);
		
		JLabel playedGames = new JLabel("Number of played games");
		playedGames.setBounds(447, 322, 110, 14);
		playedGames.setFont(new Font("Tahoma", Font.BOLD, 14));
		this.add(playedGames);
		
		setThisPlayedGames = new JLabel("Get the number of games from where?");
		setThisPlayedGames.setBounds(447, 347, 110, 14);
		setThisPlayedGames.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(setThisPlayedGames);
		
		JLabel wonGames = new JLabel("Number of won games");
		wonGames.setBounds(447, 372, 110, 14);
		wonGames.setFont(new Font("Tahoma", Font.BOLD, 16));
		this.add(wonGames);
		
		setThisWonGames = new JLabel(("Get number of won games from where?"));
		setThisWonGames.setBounds(447, 397, 110, 14);
		setThisWonGames.setFont(new Font("Tahoma", Font.PLAIN, 16));
		this.add(setThisWonGames);
		
		statisticsBackButton = new JButton("Back");
		statisticsBackButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		statisticsBackButton.setBounds(10, 683, 108, 36);
		statisticsBackButton.addActionListener(this);
		this.add(statisticsBackButton);
		
	}

	@Override
	public void saveGame(String gameID, String date) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void savePlacement(String gameID, Player player, int placement) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Map<Integer, String> loadGamePlacements(String gameID) {
		Connection conn = dbc.getConnection();
		Statement myStmt;
		try {
			myStmt = conn.createStatement();
			ResultSet rs = myStmt
					.executeQuery("SELECT * FROM PlayedGames WHERE gameID = '"
							+ gameID + "'");
			Map<Integer, String> placements = new TreeMap<Integer, String>();
			while (rs.next()) {
				String placement = rs.getString(3);
				int tmp = Integer.parseInt(placement);
				String player = rs.getString(2);

				placements.put(tmp, player);
			}
			return placements;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int loadNbrOfWonGames(String userName) {
		Connection conn = dbc.getConnection();
		Statement myStmt;
		try {
			myStmt = conn.createStatement();
			ResultSet rs = myStmt
					.executeQuery("SELECT * FROM WonGames WHERE player = '"
							+ userName + "'");
			if (rs.next()) {
				String nbrOfWonGames = rs.getString(2);
				int tmp = Integer.parseInt(nbrOfWonGames);
				return tmp;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return -1;
	}
}
