package agent.environment;

import application.state.BattleShip;
import sim.engine.SimState;
import sim.util.Int2D;

public class TornadoStrategy {

	public TornadoStrategy() {
	}
	public void action(Tornado tor, SimState state) {
		BattleShip bs=(BattleShip) state;
		if (tor.getLifetime() > 0){
			Int2D location = bs.map.getObjectLocation(tor);
			int randomNumber = (int) (Math.random()*15);
			if (randomNumber==1){
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
				bs.map.setObjectLocation(tor, new Int2D(xm, ym));
			}
			tor.decrementLifetime();
		} 
		else
			bs.map.remove(tor);

	}

	public void init(Tornado tor) {		
	}

}
