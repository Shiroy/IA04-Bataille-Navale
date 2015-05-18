package agent.environment;

import application.state.BattleShip;
import sim.engine.SimState;
import sim.engine.Steppable;

@SuppressWarnings("serial")
public class Environment implements Steppable {
	private EnvironmentStrategy behaviourStrategy;

	public Environment() {
		
	}

	@Override
	public void step(SimState state) {
		@SuppressWarnings("unused")
		BattleShip bs = (BattleShip) state;
		behaviourStrategy.action(this, state);
	}
	
	public void setStrategy(EnvironmentStrategy behaviour){
		behaviourStrategy = behaviour;
		behaviourStrategy.init(this);
	}
	

}
