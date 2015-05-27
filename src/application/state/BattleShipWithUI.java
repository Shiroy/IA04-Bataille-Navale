package application.state;

import java.awt.Color;

import javax.swing.JFrame;

import sim.display.Controller;
import sim.display.Display2D;
import sim.display.GUIState;
import sim.engine.SimState;
import sim.portrayal.grid.SparseGridPortrayal2D;

public class BattleShipWithUI extends GUIState {

	// / The swing frame
	public JFrame displayFrame;

	// / The display inside the frame
	public Display2D display;

	// / The portrayal of the map
	private SparseGridPortrayal2D mapPortrayal = new SparseGridPortrayal2D();

	/**
	 * Default constructor Set the seed to the current time in milliseconds
	 */
	public BattleShipWithUI() {
		super(new BattleShip(System.currentTimeMillis()));
	}

	/**
	 * The constructor with an already initialized Simstate
	 * 
	 * @param state
	 *            The predefined simstate
	 */
	public BattleShipWithUI(SimState state) {
		super(state);
	}

	@Override
	public Object getSimulationInspectedObject() {
		return state;
	}

	/**
	 * Reset all portrayals
	 */
	public void setupPortrayals() {

		BattleShip bs = (BattleShip) state;

		mapPortrayal = new SparseGridPortrayal2D();
		mapPortrayal.setField(bs.map);

		// reschedule the displayer
		display.reset();

		// redraw the display
		display.repaint();
	}

	@Override
	public void start() {
		super.start();

		// New state new portrayals
		setupPortrayals();
	}

	@Override
	public void load(SimState state) {
		super.load(state);

		// After loading the state set up portrayals
		setupPortrayals();
	}

	@Override
	public void init(Controller c) {
		super.init(c);

		BattleShip bs = (BattleShip) state;

		display = new Display2D(BattleShip.GRID_WIDTH * 4,
				BattleShip.GRID_HEIGHT * 4, this);
		displayFrame = display.createFrame();
		c.registerFrame(displayFrame);

		displayFrame.setVisible(true);

		mapPortrayal.setField(bs.map);
		display.attach(mapPortrayal, "Map");

		display.setBackdrop(new Color(100, 150, 150));
	}

	@Override
	public void quit() {
		super.quit();

		if (displayFrame != null) {
			displayFrame.dispose();
			displayFrame = null;
		}
		display = null;
	}

	/**
	 * Create the BattleShip Simulation with an User Interface
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new BattleShipWithUI().createController();
	}

}
