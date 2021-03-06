package client.gui.menu;

import java.awt.CardLayout;

import javax.swing.JFrame;

import client.event.Event;

/**
 * The MainView class uses a cardlayout to store all the other panels. It is in this class
 * that all the other panels are created.
 * @author forssenm
 *
 */
public class MainView implements client.event.EventHandler {

	private JFrame frame;
	private CardLayout layout;
	private LoginPanel loginPanel = new LoginPanel();
	private RegisterPanel registerPanel = new RegisterPanel();
	private MainMenuPanel mainMenuPanel = new MainMenuPanel();
	private JoinTablePanel joinTablePanel = new JoinTablePanel();
	private CreateTablePanel createTablePanel = new CreateTablePanel();
	private StatisticsPanel statisticsPanel = new StatisticsPanel();
	private AccountPanel accountPanel = new AccountPanel();

	/**
	 * Create the application.
	 */
	public MainView() {
		init();
		client.event.EventBus.register(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void init() {
		frame = new JFrame();
		layout = new CardLayout();
		frame.setBounds(0, 0, P.INSTANCE.getFrameWidth(), P.INSTANCE.getFrameHeight());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(layout);
		frame.getContentPane().add(loginPanel, "loginPanel");
		frame.getContentPane().add(registerPanel, "registerPanel");
		frame.getContentPane().add(mainMenuPanel, "mainMenuPanel");
		frame.getContentPane().add(joinTablePanel, "joinTablePanel");
		frame.getContentPane().add(createTablePanel, "createTablePanel");
		frame.getContentPane().add(statisticsPanel, "statisticsPanel");
		frame.getContentPane().add(accountPanel, "accountPanel");
		
		frame.setVisible(true);
		frame.setResizable(false);
		
	}

	@Override
	public void onEvent(client.event.Event evt) {
		if (evt.getTag().equals(client.event.Event.Tag.LOGIN_SUCCESS)) {
			layout.show(frame.getContentPane(), "mainMenuPanel");
		} else if(evt.getTag().equals(client.event.Event.Tag.REGISTER_SUCCESS)) {
			layout.show(frame.getContentPane(), "loginPanel");
		} else if(evt.getTag().equals(Event.Tag.GO_TO_ACCOUNTINFO)) {
			layout.show(frame.getContentPane(), "accountPanel");
		} else if (evt.getTag().equals(client.event.Event.Tag.GO_TO_REGISTER)) {
			layout.show(frame.getContentPane(), "registerPanel");
		} else if (evt.getTag().equals(client.event.Event.Tag.GO_TO_JOINTABLE)) {
			layout.show(frame.getContentPane(), "joinTablePanel");
		} else if (evt.getTag().equals(client.event.Event.Tag.GO_TO_CREATETABLE)) {
			layout.show(frame.getContentPane(), "createTablePanel");
		} else if (evt.getTag().equals(client.event.Event.Tag.GO_TO_STATISTICS)) {
			layout.show(frame.getContentPane(), "statisticsPanel");
		} else if (evt.getTag().equals(client.event.Event.Tag.GO_TO_MAIN)) {
			layout.show(frame.getContentPane(), "mainMenuPanel");
		} else if (evt.getTag().equals(client.event.Event.Tag.GO_TO_LOGIN)) {
			layout.show(frame.getContentPane(), "loginPanel");
		} else if (evt.getTag().equals(client.event.Event.Tag.LOGOUT_SUCCESS)) {
			layout.show(frame.getContentPane(), "loginPanel");
		}
	}
}