package view.menu;

import javax.swing.JPanel;

import event.Event;
import event.EventBus;
import event.EventHandler;

@SuppressWarnings("serial")
public class StatisticsPanel extends JPanel implements EventHandler {
	
	
	
	public StatisticsPanel() {
		init();
		EventBus.register(this);
	}

	@Override
	public void onEvent(Event evt) {
		// TODO Auto-generated method stub
		
	}
	
	private void init() {
		// TODO Auto-generated method stub
		
	}
}
