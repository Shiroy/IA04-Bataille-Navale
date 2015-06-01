package agent.environment;

import java.awt.Color;
import java.awt.Graphics2D;

import sim.engine.SimState;
import sim.engine.Steppable;
import sim.portrayal.DrawInfo2D;
import sim.portrayal.simple.OvalPortrayal2D;
import sim.util.Int2D;
import agent.ship.Ship;
import agent.ship.ShipMessage.EnvironmentDamage;
import application.state.BattleShip;

public class Tornado extends OvalPortrayal2D implements Steppable {

	private static final long serialVersionUID = 1L;

	public Tornado() {
	}

	@Override
	public void step(SimState state) {

	}

	public void action(SimState state) {
		BattleShip bs = (BattleShip) state;
		Int2D location = bs.map.getObjectLocation(this);
		int xd = (state.random.nextInt(3) - 1);
		int yd = (state.random.nextInt(3) - 1);
		int xm = location.x + xd;
		int ym = location.y + yd;
		int max_x = location.x;
		int max_y = location.y;
		if (!(xd == 0 && yd == 0) && xm >= 0 && xm < BattleShip.GRID_WIDTH
				&& ym >= 0 && ym < BattleShip.GRID_HEIGHT) {
			max_x = xm;
			max_y = ym;
		}
		bs.map.setObjectLocation(this, new Int2D(max_x, max_y));
	}

	public EnvironmentDamage createDamage(Ship target) {
		EnvironmentDamage ed = new EnvironmentDamage(20);
		return ed;	
	}
	public final void draw(Object object, Graphics2D graphics, DrawInfo2D info) {
		graphics.setColor(Color.BLACK); //Brown
		int x = (int) (info.draw.x - info.draw.width / 2.0);
		int y = (int) (info.draw.y - info.draw.height / 2.0);
		int width = (int) (info.draw.width);
		int height = (int) (info.draw.height);
		graphics.fillOval(x, y, width, height);
		
		graphics.setColor(Color.white);
		x = (int) (info.draw.x - info.draw.width / 4.0);
		y = (int) (info.draw.y - info.draw.height / 4.0);
		width = (int) (info.draw.width / 2.0);
		height = (int) (info.draw.height / 2.0);
		graphics.fillOval(x, y, width, height);
	}

}
