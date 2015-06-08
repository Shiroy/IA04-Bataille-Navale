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

public class ShipStrategyFollow implements ShipStrategy {

	@Override
	public void init(Ship ship) {
		// TODO Auto-generated method stub

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

		for (Object obj : result)
			if (obj instanceof Ship) {
				Ship s = (Ship) obj;
				if (s.getFaction() != ship.getFaction()) {
					// TODO
					; // Attack the ship
					action = true; // If attack succeed
				}
			}
		range = ship.getTemplate().getViewRange();
		result = new Bag();
		xPos = new IntBag();
		yPos = new IntBag();
		bs.map.getRadialNeighborsAndLocations(location.x, location.y, range,
				Grid2D.TOROIDAL, false, Grid2D.ALL, false, result, xPos, yPos);
		for (Object obj : result) {
			if (obj instanceof Ship && !action) {
				Ship s = (Ship) obj;
				if (s.getFaction() == ship.getFaction()
						&& s.getTemplate().getTemplateName().equals("Bark")) {

					// Absolute position
					Int2D positionLeader = bs.map.getObjectLocation(s);

					int xm;
					int ym;
					Int2D bestPosition = location;

					for (int i = -1; i <= 1; ++i)
						for (int j = -1; j <= 1; ++j) {
							xm = location.x + i;
							ym = location.y + j;
							if (xm < 0)
								xm = bs.map.getWidth() + xm;
							else if (xm >= bs.map.getWidth())
								xm -= bs.map.getWidth();
							if (ym < 0)
								ym = bs.map.getHeight() + ym;
							else if (ym >= bs.map.getHeight())
								ym -= bs.map.getHeight();

							if (bs.map.getObjectsAtLocation(xm, ym) == null
									&& positionLeader
											.distance(new Int2D(xm, ym)) < positionLeader
											.distance(bestPosition))
								bestPosition = new Int2D(xm, ym);
						}

					bs.map.setObjectLocation(ship, bestPosition);
					action = true;

				}

			}
		}
	}

	@Override
	public void shootReceived(Ship ship, ShootReceived damage) {
		// TODO Auto-generated method stub

	}

	@Override
	public void ennemyKilled(Ship killer, EnnemyKilled killInfo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void environmentDamage(Ship victim, EnvironmentDamage damage) {
		// TODO Auto-generated method stub

	}

}
