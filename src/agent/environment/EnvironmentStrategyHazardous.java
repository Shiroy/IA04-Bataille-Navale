package agent.environment;

import sim.engine.SimState;
import application.state.BattleShip;

public class EnvironmentStrategyHazardous implements EnvironmentStrategy{
	private int freqTornado;

	public EnvironmentStrategyHazardous() {
		freqTornado = 2500;
	}

	@Override
	public void action(Environment env, SimState state) {
		BattleShip bs=(BattleShip) state;
		int random = (int) (Math.random()*(4000-freqTornado));
		if(random==1){
			Tornado tor=env.createTornado(state);
			bs.schedule.scheduleRepeating(tor);
		}		
	}

	@Override
	public void init(Environment env) {
		
		
	}

}
