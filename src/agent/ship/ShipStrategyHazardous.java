package agent.ship;

import sim.engine.SimState;
import sim.field.grid.Grid2D;
import sim.util.Bag;
import sim.util.Int2D;
import sim.util.IntBag;
import agent.ship.ShipMessage.EnnemyKilled;
import agent.ship.ShipMessage.EnvironmentDamage;
import agent.ship.ShipMessage.ShootReceived;
import application.state.BattleShip;

public class ShipStrategyHazardous implements ShipStrategy {

	@Override
	public void init(Ship ship) {

	}

	@Override
	public void action(Ship ship, SimState state) {
		BattleShip bs = (BattleShip) state;

		Int2D location = bs.map.getObjectLocation(ship);
		int range = ship.getTemplate().getShootRange();
		Bag result = new Bag();
		IntBag xPos = new IntBag();
		IntBag yPos = new IntBag();
		bs.map.getRadialNeighborsAndLocations(location.getX(), location.getY(),
				range, Grid2D.TOROIDAL, false, Grid2D.ALL, false, result, xPos,
				yPos);

		boolean action = false;

		if (result.size() != 0) {
			for (Object obj : result)
				if (obj instanceof Ship) {
					Ship s = (Ship) obj;
					if (s.getFaction() != ship.getFaction()) {
						// TODO
						; // Attack the ship
						action = true; // If attack succeed
					}
				}
		} else {
			range = ship.getTemplate().getViewRange();
			result = new Bag();
			xPos = new IntBag();
			yPos = new IntBag();
			bs.map.getRadialNeighborsAndLocations(location.x, location.y,
					range, Grid2D.TOROIDAL, false, Grid2D.ALL, false, result,
					xPos, yPos);
			for (Object obj : result)
				if (obj instanceof Ship && !action) {
					Ship s = (Ship) obj;
					if (s.getFaction() != ship.getFaction()) {
						// TODO
						; // Move to the ship
						action = true;
					}
				}
		}

		if (!action) {
			int xd = (state.random.nextInt(3) - 1);
            int yd = (state.random.nextInt(3) - 1);
            int xm = location.x + xd;
            int ym = location.y + yd;
            int max_x = location.x;
            int max_y = location.y;
            if (!(xd == 0 && yd == 0) && xm >= 0 && xm < bs.GRID_WIDTH && ym >= 0 && ym < bs.GRID_HEIGHT)
                { max_x = xm; max_y = ym; }

            bs.map.setObjectLocation(ship, new Int2D(max_x, max_y));

		}

	}

	@Override
	public void shootReceived(Ship ship, ShootReceived damage) {
		// TODO
	}

	@Override
	public void ennemyKilled(Ship killer, EnnemyKilled killInfo) {
	}

	@Override
	public void environmentDamage(Ship victim, EnvironmentDamage damage) {
	}
}
