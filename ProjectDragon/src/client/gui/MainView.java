package client.gui;

import javax.swing.JFrame;

import java.awt.CardLayout;

public class MainView implements client.event.EventHandler {

	private JFrame frame;
	private CardLayout layout;
	private LoginPanel loginPanel = new LoginPanel();
	private RegisterPanel registerPanel = new RegisterPanel();
	private MainMenuPanel mainMenuPanel = new MainMenuPanel();
	private JoinTablePanel joinTablePanel = new JoinTablePanel();
	private CreateTablePanel createTablePanel = new CreateTablePanel();
	private StatisticsPanel statisticsPanel = new StatisticsPanel();
	private TablePanel tablePanel = new TablePanel();

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
		frame.setBounds(100, 100, 1024, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(layout);
		frame.getContentPane().add(tablePanel, "tablePanel");
		frame.getContentPane().add(loginPanel, "loginPanel");
		frame.getContentPane().add(registerPanel, "registerPanel");
		frame.getContentPane().add(mainMenuPanel, "mainMenuPanel");
		frame.getContentPane().add(joinTablePanel, "joinTablePanel");
		frame.getContentPane().add(createTablePanel, "createTablePanel");
		frame.getContentPane().add(statisticsPanel, "statisticsPanel");
		
		frame.setVisible(true);
		frame.setResizable(false);

		// TODO Statistics panel
	}

	@Override
	public void onEvent(client.event.Event evt) {
		if (evt.getTag().equals(client.event.Event.Tag.LOGIN_SUCCESS)
				|| evt.getTag().equals(client.event.Event.Tag.REGISTER_SUCCESS)) {
			layout.show(frame.getContentPane(), "mainMenuPanel");
		} else if (evt.getTag().equals(client.event.Event.Tag.GO_TO_REGISTER)) {
			layout.show(frame.getContentPane(), "registerPanel");
		} else if (evt.getTag().equals(client.event.Event.Tag.GO_TO_JOINTABLE)) {
			layout.show(frame.getContentPane(), "joinTablePanel");
		} else if (evt.getTag().equals(client.event.Event.Tag.GO_TO_CREATETABLE)) {
			layout.show(frame.getContentPane(), "createTablePanel");
		} else if (evt.getTag().equals(client.event.Event.Tag.GO_TO_MAIN)) {
			layout.show(frame.getContentPane(), "mainMenuPanel");
		} else if (evt.getTag().equals(client.event.Event.Tag.REGISTER_BACK)) {
			layout.show(frame.getContentPane(), "loginPanel");
		} else if (evt.getTag().equals(client.event.Event.Tag.LOGOUT)) {
			layout.show(frame.getContentPane(), "loginPanel");
		} else if (evt.getTag().equals(client.event.Event.Tag.CREATE_TABLE)) {
			layout.show(frame.getContentPane(), "tablePanel");
		} else if (evt.getTag().equals(client.event.Event.Tag.JOIN_TABLE)) {
			layout.show(frame.getContentPane(), "tablePanel");
		}
	}
}