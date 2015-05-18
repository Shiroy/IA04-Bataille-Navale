package agent.environment;

import application.state.BattleShip;
import sim.engine.SimState;
import sim.engine.Steppable;

@SuppressWarnings("serial")
public class Environment implements Steppable {

	public Environment() {
	}

	@Override
	public void step(SimState state) {
		@SuppressWarnings("unused")
		BattleShip bs = (BattleShip) state;		
	}

}
