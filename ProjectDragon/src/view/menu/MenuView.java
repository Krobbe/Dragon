package view.menu;

import javax.swing.JFrame;

import java.awt.CardLayout;

import event.Event;
import event.EventBus;
import event.EventHandler;


public class MenuView implements EventHandler {

	private JFrame frame;
	private CardLayout layout;
	private LoginPanel loginPanel = new LoginPanel();
	private RegisterPanel registerPanel = new RegisterPanel();
	private MainMenuPanel mainMenuPanel = new MainMenuPanel();
	private JoinTablePanel joinTablePanel = new JoinTablePanel();
	private CreateTablePanel createTablePanel = new CreateTablePanel();
	//TODO Statistics panel

	/**
	 * Create the application.
	 */
	public MenuView() {
		init();
		EventBus.register(this);
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
		frame.getContentPane().add(loginPanel, "loginPanel");
		frame.getContentPane().add(registerPanel, "registerPanel");
		frame.getContentPane().add(mainMenuPanel, "mainMenuPanel");
		frame.getContentPane().add(joinTablePanel, "joinTablePanel");
		frame.getContentPane().add(createTablePanel, "createTablePanel");
		//TODO Statistics panel
	}

	@Override
	public void onEvent(Event evt) {

	}

}