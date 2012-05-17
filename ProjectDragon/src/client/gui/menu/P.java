package client.gui.menu;

import java.awt.Color;
import java.awt.Font;

/**
 * This class holds data for the GUI.
 * @author lisastenberg
 *
 */
public enum P {
	INSTANCE;
	
	public Color getBackground() {
		return new Color(188, 219, 204);
	}
	
	public Font getLabelFont() {
		return new Font("Tahoma", Font.PLAIN, 16);
	}
	
	public Font getBoldLabelFont() {
		return new Font("Tahoma", Font.PLAIN, 16);
	}
}