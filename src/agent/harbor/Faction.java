package agent.harbor;

import java.awt.Color;

public enum Faction {
	RED, BLUE;
	
	Color toColor() {
		switch(this) {
		default:
		case RED:
			return Color.RED;
		case BLUE:
			return Color.BLUE;
		}
	}
}
