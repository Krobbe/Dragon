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
import java.rmi.RemoteException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import client.event.Event;

import common.remote.IServerGame;


/**
 * JoinTablePanel is the panel in which the user can select a table to join
 * @author forssenm
 *
 */
@SuppressWarnings("serial")
public class JoinTablePanel extends JScrollPane implements ActionListener,
		client.event.EventHandler {

	private JButton joinTableBackButton;
	private JButton joinTableJoinButton;
	private JList joinTableList;
	private DefaultListModel model;
	private List<IServerGame> availableGamesLists;
	
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
	public JoinTablePanel() {
		init();
		client.event.EventBus.register(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onEvent(client.event.Event evt) {
		if(evt.getTag().equals(Event.Tag.PUBLISH_ACTIVE_GAMES)) {
			availableGamesLists = (List<IServerGame>)evt.getValue();
			model.clear();
			int i = 1;
			for(IServerGame isg : availableGamesLists) {
				try {
					if (isg.getPlayers().size() < isg.getMaxPlayers()) {
						model.addElement("Table " + i + "          Players: "
								+ isg.getPlayers().size() + "/"
								+ isg.getMaxPlayers());
						i++;
					}
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Have to send what table to join
		if (e.getSource() == joinTableJoinButton) {
			String s = (String)joinTableList.getSelectedValue();
			//TODO Ugly solution but works for the scope of this application
			int i = Integer.parseInt(s.substring(6, 10).trim());
			client.event.EventBus.publish(new Event(Event.Tag.JOIN_TABLE, i));
		} else if (e.getSource() == joinTableBackButton) {
			client.event.EventBus.publish(new Event(Event.Tag.GO_TO_MAIN, 1));
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void init() {
		this.setLayout(null);
		this.setOpaque(false);

		joinTableBackButton = new JButton("Back");
		joinTableBackButton.setBounds(margin, frameHeight-buttonHeight - 2*margin, 
				buttonWidth, buttonHeight);
		joinTableBackButton.setFont(P.INSTANCE.getLabelFont());
		joinTableBackButton.addActionListener(this);
		this.add(joinTableBackButton);

		joinTableJoinButton = new JButton("Join table");
		joinTableJoinButton.setBounds(frameWidth - buttonWidth - margin, 
				frameHeight-buttonHeight - 2*margin, buttonWidth, buttonHeight);
		joinTableJoinButton.setFont(P.INSTANCE.getLabelFont());
		joinTableJoinButton.addActionListener(this);
		this.add(joinTableJoinButton);
		
		joinTableList = new JList();
		model = new DefaultListModel();
		joinTableList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		joinTableList.setFont(P.INSTANCE.getLabelFont());
		joinTableList.setModel(model);
		joinTableList.setBounds(192, 111, 623, 507);
		this.add(joinTableList);
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
