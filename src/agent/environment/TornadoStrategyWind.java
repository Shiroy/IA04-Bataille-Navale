package agent.environment;

import sim.engine.SimState;
import sim.util.Int2D;
import application.state.BattleShip;

public class TornadoStrategyWind {
	private int ventx;
	private int venty;
	private int force;

	public TornadoStrategyWind() {
		ventx=-1;
		venty=1;
		force=10;
	}

	public void action(Tornado tor, SimState state) {
		BattleShip bs=(BattleShip) state;
		if (tor.getLifetime() > 0){
			Int2D location = bs.map.getObjectLocation(tor);
			int randomNumber = (int) (Math.random()*(25-force));
			if (randomNumber==1){
				int randomVent = (int) (Math.random()*5);
				int xm = location.x;
				int ym = location.y;
				if (randomVent>1) xm = location.x + ventx;
				if (randomVent<=3) ym = location.y + venty;
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
