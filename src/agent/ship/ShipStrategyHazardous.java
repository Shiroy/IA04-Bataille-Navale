package agent.ship;

import sim.engine.SimState;
import sim.util.Int2D;
import application.state.BattleShip;

public class ShipStrategyHazardous implements ShipStrategy {

	@Override
	public void init(Ship ship) {
		
	}

	@Override
	public void action(Ship ship, SimState state) {
		BattleShip bs = (BattleShip)state;
        
        Int2D location = bs.map.getObjectLocation(this);
        int x = location.x;
        int y = location.y;
        
        
	}

}
