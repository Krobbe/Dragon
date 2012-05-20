package client.gui.menu;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import common.database.DatabaseCommunicator;
import common.database.IDBLoadGame;
import common.model.player.Account;

import client.event.Event;

/**
 * The StatisticsPanel has information about the logged in user. The information is retrieved from the DB.
 * @author forssenm
 *
 */
@SuppressWarnings("serial")
public class StatisticsPanel extends JPanel implements ActionListener,
		ListSelectionListener, client.event.EventHandler, IDBLoadGame {
	DatabaseCommunicator dbc = DatabaseCommunicator.getInstance();

	private JLabel userName;
	private JLabel setThisPlayedGames;
	private JLabel setThisWonGames;
	
	private JList listWithGames;
	private DefaultListModel listWithGamesModel;
	
	private JList selectedGame;
	private DefaultListModel selectedGameModel;
	
	private String background = P.INSTANCE.getBackgroundImage();
	private float transparency = P.INSTANCE.getTransparency();
	private int frameWidth = P.INSTANCE.getFrameWidth();
	private int frameHeight = P.INSTANCE.getFrameHeight();
	private int margin = P.INSTANCE.getMarginSize();
	private int buttonHeight = P.INSTANCE.getButtonHeight();
	private int buttonWidth = P.INSTANCE.getButtonWidth();

	private JButton statisticsBackButton;
	
	/**
	 * Creates the application.
	 */
	public StatisticsPanel() {
		init();
		client.event.EventBus.register(this);
	}

	@Override
	public void onEvent(client.event.Event evt) {
		if(evt.getTag().equals(Event.Tag.PUBLISH_ACCOUNT_INFORMATION)) {
			Account acc = (Account)evt.getValue();
			userName.setText("<html><b>Username: </b>" + acc.getUserName() + "</html>");
			setThisWonGames.setText(Integer.toString(loadNbrOfWonGames(acc.getUserName())));
			setThisPlayedGames.setText(Integer.toString(loadNbrOfPlayedGames(acc.getUserName())));
			List<Integer> tmp = loadPlayedGames(acc.getUserName());
			listWithGamesModel.clear();
			for(Integer i : tmp) {
				listWithGamesModel.addElement(i);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == statisticsBackButton) {
			client.event.EventBus.publish(new client.event.Event(client.event.Event.Tag.GO_TO_MAIN, 1));
		}
	}

	private void init() {
		this.setLayout(null);
		this.setOpaque(false);

		userName = new JLabel("User name");
		userName.setBounds(200, 102, 200, 20);
		userName.setFont(P.INSTANCE.getLabelFont());
		this.add(userName);
		
		JLabel playedGames = new JLabel("Number of played games");
		playedGames.setBounds(200, 252, 230, 20);
		playedGames.setFont(P.INSTANCE.getBoldLabelFont());
		this.add(playedGames);
		
		setThisPlayedGames = new JLabel();
		setThisPlayedGames.setBounds(200, 277, 200, 20);
		setThisPlayedGames.setFont(P.INSTANCE.getLabelFont());
		this.add(setThisPlayedGames);
		
		JLabel wonGames = new JLabel("Number of won games");
		wonGames.setBounds(200, 302, 200, 20);
		wonGames.setFont(P.INSTANCE.getBoldLabelFont());
		this.add(wonGames);
		
		setThisWonGames = new JLabel();
		setThisWonGames.setBounds(200, 327, 200, 20);
		setThisWonGames.setFont(P.INSTANCE.getLabelFont());
		this.add(setThisWonGames);
		
		listWithGamesModel = new DefaultListModel();
		listWithGames = new JList(listWithGamesModel);
		listWithGames.setBounds(500, 102, 400, 245);
		listWithGames.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listWithGames.addListSelectionListener(this);
		this.add(listWithGames);
		
		selectedGameModel = new DefaultListModel();
		selectedGame = new JList(selectedGameModel);
		selectedGame.setBounds(500, 372, 400, 245);
		this.add(selectedGame);
		
		statisticsBackButton = new JButton("Back");
		statisticsBackButton.setFont(P.INSTANCE.getLabelFont());
		statisticsBackButton.setBounds(margin, frameHeight - buttonHeight 
				- 2*margin, buttonWidth, buttonHeight);
		statisticsBackButton.addActionListener(this);
		this.add(statisticsBackButton);
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
		return 0;
	}

	@Override
	public int loadNbrOfPlayedGames(String userName) {
		Connection conn = dbc.getConnection();
		Statement myStmt;
		try {
			myStmt = conn.createStatement();
			ResultSet rs = myStmt
					.executeQuery("SELECT player, COUNT(*) AS " +
							"nbrOfPlayedGames FROM PlayedGames WHERE player = '"
							+ userName + "' GROUP BY player");
			if (rs.next()) {
				String nbrOfPlayedGames = rs.getString(2);
				int tmp = Integer.parseInt(nbrOfPlayedGames);
				return tmp;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Integer> loadPlayedGames(String userName) {
		Connection conn = dbc.getConnection();
		Statement myStmt;
		try {
			myStmt = conn.createStatement();
			ResultSet rs = myStmt
					.executeQuery("SELECT gameID FROM PlayedGames WHERE player = '"
							+ userName + "'");
			List<Integer> playedGames = new LinkedList<Integer>();
			while (rs.next()) {
				String gameID = rs.getString(1);
				int tmp = Integer.parseInt(gameID);
				playedGames.add(tmp);
			}
			return playedGames;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		String tmp = listWithGames.getSelectedValue().toString();
		Map<Integer, String> placements = loadGamePlacements(tmp);
		selectedGameModel.clear();
		for(Integer i : placements.keySet()) {
			selectedGameModel.addElement(i + ". " + placements.get(i));
		}
	}
	
	public void paintComponent(Graphics g) {
		Image im = loadTranslucentImage(background, transparency);
		g.drawImage(im, 0, 0, frameWidth, frameHeight, null);
	}

	/**
	 * Return a translucent bufferedImage with transparency "transperancy"
	 * 
	 * @param url
	 * @param transperancy
	 * @return
	 * @author lisastenberg
	 */
	public static BufferedImage loadTranslucentImage(String url,
			float transparency) {
		BufferedImage loaded;
		try {
			loaded = ImageIO.read(new File(url));
			BufferedImage aimg = new BufferedImage(loaded.getWidth(),
					loaded.getHeight(), BufferedImage.TRANSLUCENT);
			Graphics2D g = aimg.createGraphics();
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
					transparency));
			g.drawImage(loaded, null, 0, 0);
			g.dispose();
			return aimg;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
