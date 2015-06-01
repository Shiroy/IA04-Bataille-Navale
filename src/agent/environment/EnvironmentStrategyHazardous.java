package agent.environment;

import sim.engine.SimState;

public class EnvironmentStrategyHazardous implements EnvironmentStrategy{

	public EnvironmentStrategyHazardous() {
	}

	@Override
	public void action(Environment env, SimState state) {
		//BattleShip bs=(BattleShip) state;
		int random = (int) (Math.random()*100);
		if(random==50){
			env.createTornado();
		}		
	}

	@Override
	public void init(Environment env) {
		
		
	}

}
