package application.state;

import sim.engine.SimState;
import sim.field.grid.SparseGrid2D;
import sim.util.Double2D;
import sim.util.Int2D;
import agent.environment.Environment;
import agent.environment.EnvironmentStrategyHazardous;
import agent.harbor.Faction;
import agent.harbor.Harbor;
import agent.harbor.HarborStrategyMassAttack;
import agent.harbor.HarborStrategyNormal;
import agent.ship.Missile;
import agent.ship.Ship;

/**
 * This class represents the state of the simulation. At the beginning it
 * creates the map and two harbor and let them working.
 * 
 * @author cedric
 */
@SuppressWarnings("serial")
public class BattleShip extends SimState {

	public static final int GRID_WIDTH = 100;
	public static final int GRID_HEIGHT = 100;
	public final SparseGrid2D map = new SparseGrid2D(GRID_WIDTH, GRID_HEIGHT);

	/**
	 * Default constructor
	 * 
	 * @param seed
	 *            Seed for random initialization
	 */
	public BattleShip(long seed) {
		super(seed);
	}

	@Override
	public void start() {
		super.start();
		map.clear();

		Harbor h = new Harbor(100, Faction.RED, new Int2D(GRID_HEIGHT / 4,
				GRID_WIDTH / 2), 500, new HarborStrategyNormal(), this);
		map.setObjectLocation(h, GRID_HEIGHT / 4, GRID_WIDTH / 2);
		schedule.scheduleRepeating(h);

		Harbor h2 = new Harbor(100, Faction.BLUE, new Int2D(
				GRID_HEIGHT * 3 / 4, GRID_WIDTH / 2), 500,
				new HarborStrategyMassAttack(), this);
		map.setObjectLocation(h2, GRID_HEIGHT * 3 / 4, GRID_WIDTH / 2);
		schedule.scheduleRepeating(h2);

		Environment env = new Environment(this,
				new EnvironmentStrategyHazardous());
		schedule.scheduleRepeating(env);

	}

	/**
	 * Create the BattleShip Simulation
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		doLoop(BattleShip.class, args);
		System.exit(0);
	}

}
