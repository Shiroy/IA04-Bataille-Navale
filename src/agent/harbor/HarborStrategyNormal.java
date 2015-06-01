package agent.harbor;

import sim.engine.SimState;
import sim.util.Int2D;
import agent.ship.Ship;
import agent.ship.ShipMessage.ShootReceived;
import application.state.BattleShip;

public class HarborStrategyNormal implements HarborStrategy {

	/**
	 * The strategy for harbor when it is safe
	 */
	
	@Override
	public void init(Harbor harbor, SimState state) {
		BattleShip bs = (BattleShip)state;
		harbor.setPosition(bs.map.getObjectLocation(this));
	}

	@Override
	public void action(Harbor harbor, SimState state) {
		//TODO add strategy for different type of ship
		BattleShip bs = (BattleShip)state;
		String shipName = "Bark";
		Ship newShip = harbor.createShip(shipName);
		if (newShip != null) {
			Int2D position = harbor.getPosition();
			int x = position.x + 1; 
			int y = position.y + 1;
			bs.map.setObjectLocation(newShip, x, y);
			bs.schedule.scheduleRepeating(newShip);
		}
		
		//TODO Handle the messages. add alart message
	}

	@Override
	public void attacked(Harbor harbor, ShootReceived shoot) {
		
	}

}
