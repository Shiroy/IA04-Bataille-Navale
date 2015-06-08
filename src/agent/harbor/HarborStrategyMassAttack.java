package agent.harbor;

import sim.engine.SimState;
import sim.util.Int2D;
import agent.ship.Ship;
import agent.ship.ShipMessage.EnvironmentDamage;
import agent.ship.ShipMessage.ShootReceived;
import application.state.BattleShip;

public class HarborStrategyMassAttack implements HarborStrategy {

	/**
	 * The strategy for harbor when it is safe
	 */

	@Override
	public void init(Harbor harbor, SimState state) {
		BattleShip bs = (BattleShip) state;
		harbor.setPosition(bs.map.getObjectLocation(this));
	}

	@Override
	public void action(Harbor harbor, SimState state) {
		// TODO add strategy for different type of ship
		BattleShip bs = (BattleShip) state;

		if (harbor.getWoodStock() >= 1000) {
			String shipName = "Bark";			
				
			Ship newShip = harbor.createShip(shipName);
			while (newShip != null) {				
				newShip = harbor.createShip(shipName);
			}
		}

		// TODO Handle the messages. add alert message
	}

	@Override
	public void attacked(Harbor harbor, ShootReceived shoot) {
		harbor.damage(shoot.getDamage());
		// Change strategy to Alarm
		// Send alert message with shooter

	}

	@Override
	public void environmentDamage(Harbor harbor, EnvironmentDamage damage) {
		harbor.damage(damage.getDamage());
	}

}
