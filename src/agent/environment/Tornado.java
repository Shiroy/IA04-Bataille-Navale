package agent.environment;

import application.state.BattleShip;
import sim.engine.SimState;
import sim.engine.Steppable;
import sim.util.Int2D;

public class Tornado implements Steppable{

	private static final long serialVersionUID = 1L;

	public Tornado() {
	}

	@Override
	public void step(SimState state) {
		
	}
	
	public void action(SimState state){
		BattleShip bs = (BattleShip)state;
        Int2D location = bs.map.getObjectLocation(this);
        @SuppressWarnings("unused")
		int x = location.x;
        @SuppressWarnings("unused")
        int y = location.y;
	}

}
