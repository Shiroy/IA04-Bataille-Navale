package agent.ship;

import sim.engine.SimState;
import sim.field.grid.Grid2D;
import sim.util.Bag;
import sim.util.Int2D;
import sim.util.IntBag;
import agent.harbor.Harbor;
import agent.ship.ShipMessage.EnnemyKilled;
import agent.ship.ShipMessage.EnvironmentDamage;
import agent.ship.ShipMessage.ShootReceived;
import application.state.BattleShip;

public class ShipStrategyHazardous implements ShipStrategy {

	private int waitForMove;

	@Override
	public void init(Ship ship) {
		waitForMove = 0;
	}

	@Override
	public void action(Ship ship, SimState state) {
		waitForMove--;
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
				else if(obj instanceof Harbor){
					Harbor h = (Harbor)obj;
					if (h.getFaction() != ship.getFaction()) {
						ShootReceived shoot = new ShootReceived(ship.getTemplate().getAttackPower(),ship);
						h.receiveMessage(shoot);
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
			if (waitForMove <= 0) {
				int xd = (state.random.nextInt(3) - 1);
				int yd = (state.random.nextInt(3) - 1);
				int xm = location.x + xd;
				int ym = location.y + yd;
				if (xm < 0)
					xm = bs.map.getWidth() + xm;
				else if (xm >= bs.map.getWidth())
					xm -= bs.map.getWidth();
				if (ym < 0)
					ym = bs.map.getHeight() + ym;
				else if (ym >= bs.map.getHeight())
					ym -= bs.map.getHeight();
				System.out.println(bs.map.getHeight());
				System.out.println(xm + " " + ym);
				bs.map.setObjectLocation(ship, new Int2D(xm, ym));
				waitForMove = 5;
			}

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
