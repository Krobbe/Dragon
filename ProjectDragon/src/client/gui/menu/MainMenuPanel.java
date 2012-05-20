package client.gui.menu;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import client.event.*;

/**
 * The MainMenuPanel is the panel that the user is sent to after logging in. Has buttons for
 * accessing other important panels.
 * @author forssenm
 *
 */
@SuppressWarnings("serial")
public class MainMenuPanel extends JPanel implements ActionListener,
		client.event.EventHandler {

	private JButton mainLogoutButton;
	private JButton mainJoinTableButton;
	private JButton mainCreateTableButton;
	private JButton mainStatisticsButton;
	private JButton mainAccountButton;
	
	private String background = P.INSTANCE.getBackgroundImage();
	private float transparency = P.INSTANCE.getTransparency();
	private int frameHeight = P.INSTANCE.getFrameHeight();
	private int frameWidth = P.INSTANCE.getFrameWidth();
	private int margin = P.INSTANCE.getMarginSize();
	private int buttonHeight = P.INSTANCE.getButtonHeight();
	private int buttonWidth = P.INSTANCE.getButtonWidth();
	
	/**
	 * Creates the panel
	 */
	public MainMenuPanel() {
		init();
		client.event.EventBus.register(this);
	}

	@Override
	public void onEvent(client.event.Event evt) {
		// TODO Auto-generated method stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mainJoinTableButton) {
			EventBus.publish(new Event(Event.Tag.GO_TO_JOINTABLE, 1));
			EventBus.publish(new Event(Event.Tag.GET_ACTIVE_GAMES, 1));
		} else if (e.getSource() == mainCreateTableButton) {
			EventBus.publish(new Event(Event.Tag.GO_TO_CREATETABLE, 1));
		} else if (e.getSource() == mainStatisticsButton) {
			EventBus.publish(new Event(Event.Tag.GO_TO_STATISTICS, 1));
			EventBus.publish(new Event(Event.Tag.GET_ACCOUNT_INFORMATION, 1));
		} else if (e.getSource() == mainLogoutButton) {
			EventBus.publish(new Event(Event.Tag.TRY_LOGOUT, 1));
		} else if (e.getSource() == mainAccountButton) {
			EventBus.publish(new Event(Event.Tag.GO_TO_ACCOUNTINFO, 1));
			EventBus.publish(new Event(Event.Tag.GET_ACCOUNT_INFORMATION, 1));
		}
	}

	private void init() {
		this.setLayout(null);
		this.setOpaque(false);

		mainLogoutButton = new JButton("Log out");
		mainLogoutButton.setBounds(margin, frameHeight-buttonHeight - 2*margin, 
				buttonWidth, buttonHeight);
		mainLogoutButton.setFont(P.INSTANCE.getLabelFont());
		mainLogoutButton.addActionListener(this);
		this.add(mainLogoutButton);

		mainJoinTableButton = new JButton("Join table");
		mainJoinTableButton.setFont(P.INSTANCE.getLabelFont());
		mainJoinTableButton.setBounds(280, 292, 200, 146);
		mainJoinTableButton.addActionListener(this);
		this.add(mainJoinTableButton);

		mainCreateTableButton = new JButton("Create table");
		mainCreateTableButton.setFont(P.INSTANCE.getLabelFont());
		mainCreateTableButton.setBounds(40, 292, 200, 146);
		mainCreateTableButton.addActionListener(this);
		this.add(mainCreateTableButton);

		mainStatisticsButton = new JButton("View statistics");
		mainStatisticsButton.setFont(P.INSTANCE.getLabelFont());
		mainStatisticsButton.setBounds(520, 292, 200, 146);
		mainStatisticsButton.addActionListener(this);
		this.add(mainStatisticsButton);
		
		mainAccountButton = new JButton("Account information");
		mainAccountButton.setFont(P.INSTANCE.getLabelFont());
		mainAccountButton.setBounds(760, 292, 200, 146);
		mainAccountButton.addActionListener(this);
		this.add(mainAccountButton);
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
