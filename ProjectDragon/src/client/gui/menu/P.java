package client.gui.menu;

import java.awt.Color;
import java.awt.Font;

/**
 * This class holds parametrization data for the menu GUI.
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
		return new Font("Tahoma", Font.BOLD, 16);
	}
	
	public int getFrameHeight() {
		return 700;
	}
	
	public int getFrameWidth() {
		return 1000;
	}
	
	public int getButtonHeight() {
		return 36;
	}
	
	public int getButtonWidth() {
		return 108;
	}
	
	public int getMarginSize() {
		return 20;
	}
	
	public String getBackgroundImage() {
		return "lib/dragon_background.jpg";
	}
	
	public float getTransparency() {
		return (float)0.2;
	}
}
