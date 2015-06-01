package agent.environment;

import application.state.BattleShip;
import sim.engine.SimState;
import sim.util.Int2D;

public class TornadoStrategy {

	public TornadoStrategy() {
	}
	public void action(Tornado tor, SimState state) {
		BattleShip bs=(BattleShip) state;
		Int2D location = bs.map.getObjectLocation(tor);
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
		bs.map.setObjectLocation(tor, new Int2D(max_x, max_y));	
		tor.decrementLifetime();
	}

	public void init(Tornado tor) {		
	}

}
