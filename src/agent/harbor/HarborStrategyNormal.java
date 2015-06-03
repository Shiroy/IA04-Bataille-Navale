package agent.harbor;

import sim.engine.SimState;
import sim.util.Int2D;
import agent.ship.Ship;
import agent.ship.ShipMessage.EnvironmentDamage;
import agent.ship.ShipMessage.ShootReceived;
import application.state.BattleShip;

public class HarborStrategyNormal implements HarborStrategy {

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
		String shipName = harbor.getNextShip();
		Ship newShip = null;
		if(shipName != null){
			newShip = harbor.createShip(shipName);
		}
		if (newShip != null) {
			Int2D position = harbor.getPosition();
			int x = position.x + 1;
			int y = position.y + 1;
			bs.map.setObjectLocation(newShip, x, y);
			bs.schedule.scheduleRepeating(newShip);
			harbor.count(shipName);
		}
		
		harbor.setNextShip("Frigate");
		if(harbor.getWoodStock() > 100){
			int n = harbor.getFrigateNum();
			if (n - (n/3)*3 == 0){
				harbor.setNextShip("Bark");
			}
		}
		else if(harbor.getFrigateNum()>harbor.getBarkNum()*3+9)
			if (harbor.getWoodStock() > 200)
				harbor.setNextShip("Bark");
			else
				harbor.setNextShip(null);
			
		
		harbor.setNextShip("Frigate");
		int n = harbor.getFrigateNum();
		if (n - (n/3)*3 == 0){
			harbor.setNextShip("Bark");
		}
		if (newShip != null) {
			Int2D position = harbor.getPosition();
			int x = position.x + 1;
			int y = position.y + 1;
			bs.map.setObjectLocation(newShip, x, y);
			bs.schedule.scheduleRepeating(newShip);
		} else {
			harbor.setNextShip("Frigate");
		}
		
		harbor.addWoodStock(10);
		
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
