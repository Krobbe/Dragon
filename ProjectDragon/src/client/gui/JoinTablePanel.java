package client.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;


@SuppressWarnings("serial")
public class JoinTablePanel extends JPanel implements ActionListener,
		client.event.EventHandler {

	private JButton joinTableBackButton;
	private JButton joinTableJoinButton;
	private JList<Object> joinTableList;

	public JoinTablePanel() {
		init();
		client.event.EventBus.register(this);
	}

	@Override
	public void onEvent(client.event.Event evt) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Have to send what table to join
		if (e.getSource() == joinTableJoinButton) {
			client.event.EventBus.publish(new client.event.Event(client.event.Event.Tag.JOIN_TABLE, 1));
		} else if (e.getSource() == joinTableBackButton) {
			client.event.EventBus.publish(new client.event.Event(client.event.Event.Tag.GO_TO_MAIN, 1));
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void init() {
		this.setLayout(null);

		joinTableBackButton = new JButton("Back");
		joinTableBackButton.setBounds(10, 683, 108, 36);
		joinTableBackButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		joinTableBackButton.addActionListener(this);
		this.add(joinTableBackButton);

		joinTableJoinButton = new JButton("Join table");
		joinTableJoinButton.setBounds(890, 683, 108, 36);
		joinTableJoinButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		joinTableJoinButton.addActionListener(this);
		this.add(joinTableJoinButton);

		joinTableList = new JList<Object>();
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
		this.add(joinTableList);
	}
}
