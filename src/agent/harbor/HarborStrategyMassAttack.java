package agent.harbor;

import sim.engine.SimState;
import sim.util.Int2D;
import agent.ship.Ship;
import agent.ship.ShipFactory;
import agent.ship.ShipStrategyFollow;
import agent.ship.ShipStrategyHazardous;
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

		if (harbor.getWoodStock() >= ShipFactory.getInstance()
				.getShipTemplate("Bark").getConstructionCost() * 3) {
			String shipName = "Bark";
			Ship newShip = harbor.createShip(shipName);
			boolean leader = false;
			while (newShip != null) {
				Int2D position = harbor.getPosition();
				int x = position.x - 1 + state.random.nextInt(3);
				int y = position.y - 1 + state.random.nextInt(3);
				while (x == position.x && y == position.y) {
					x = position.x - 1 + state.random.nextInt(3);
					y = position.y - 1 + state.random.nextInt(3);
				}
				bs.map.setObjectLocation(newShip, x, y);
				bs.schedule.scheduleRepeating(newShip);
				if (!leader) {
					newShip.setStrategy(new ShipStrategyHazardous());
					shipName = "Frigate";
					leader = true;
				} else
					newShip.setStrategy(new ShipStrategyFollow());
				newShip = harbor.createShip(shipName);
			}
		}
		
		//harbor.addWoodStock(10);

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
