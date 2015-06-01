package agent.environment;

import java.awt.Color;
import java.awt.Graphics2D;

import sim.engine.SimState;
import sim.engine.Steppable;
import sim.portrayal.DrawInfo2D;
import sim.portrayal.simple.OvalPortrayal2D;
import agent.ship.Ship;
import agent.ship.ShipMessage.EnvironmentDamage;

public class Tornado extends OvalPortrayal2D implements Steppable {

	private static final long serialVersionUID = 1L;
	private int lifetime;
	private TornadoStrategy behaviour;

	public Tornado(SimState state) {
		lifetime=3000;
		behaviour=new TornadoStrategy();
	}

	@Override
	public void step(SimState state) {
		//BattleShip bs=(BattleShip) state;
		behaviour.action(this,state);
		//if (lifetime<0) bs.map.remove(this);
	
	}

	public void decrementLifetime(){
		lifetime--;
	}
	

	public EnvironmentDamage createDamage(Ship target) {
		EnvironmentDamage ed = new EnvironmentDamage(20);
		return ed;	
	}
	public final void draw(Object object, Graphics2D graphics, DrawInfo2D info) {
		graphics.setColor(Color.white);
		int x = (int) (info.draw.x - info.draw.width / 2.0);
		int y = (int) (info.draw.y - info.draw.height / 2.0);
		int width = (int) (info.draw.width);
		int height = (int) (info.draw.height);
		graphics.fillOval(x, y, width, height);

	}

}
