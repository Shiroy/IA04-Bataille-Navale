package agent.environment;

import sim.engine.SimState;
import sim.engine.Steppable;
import sim.field.grid.SparseGrid2D;
import application.state.BattleShip;

@SuppressWarnings("serial")
public class Environment implements Steppable {
	private EnvironmentStrategy behaviourStrategy;
	private static Environment m_instance = null;
	private SparseGrid2D map;

	public static Environment getInstance(BattleShip bs) {
		if (m_instance == null)
			m_instance = new Environment(bs);
		return m_instance;
	}

	public Environment(BattleShip bs) {
		map = bs.map;
	}

	@Override
	public void step(SimState state) {
		BattleShip bs = (BattleShip) state;
		this.map=bs.map;
		behaviourStrategy.action(this, bs);
	}

	public void setStrategy(EnvironmentStrategyHazardous behaviour) {
		behaviourStrategy = behaviour;
		behaviourStrategy.init(this);
	}

	public Tornado createTornado() {
		Tornado tornado = new Tornado();
		map.setObjectLocation(tornado, (int)(Math.random()*99), (int)(Math.random()*99));
		return tornado;
	}

}
