package agent.environment;

import sim.engine.SimState;
import application.state.BattleShip;

public class EnvironmentStrategyHazardous implements EnvironmentStrategy{

	public EnvironmentStrategyHazardous() {
	}

	@Override
	public void action(Environment env, SimState state) {
		BattleShip bs=(BattleShip) state;
		int random = (int) (Math.random()*3000);
		if(random==10){
			Tornado tor=env.createTornado(state);
			bs.schedule.scheduleRepeating(tor);
		}		
	}

	@Override
	public void init(Environment env) {
		
		
	}

}
