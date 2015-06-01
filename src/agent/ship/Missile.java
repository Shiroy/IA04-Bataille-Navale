package agent.ship;

import agent.ship.ShipMessage.ShootReceived;
import application.state.BattleShip;
import sim.engine.SimState;
import sim.engine.Steppable;
import sim.util.Bag;
import sim.util.Double2D;
import sim.util.Int2D;

public class Missile implements Steppable {

	private static final long serialVersionUID = 1L;
	Double2D direction;
	Double2D pos;
	private final int missileSpeed = 20; //En case / seconde 
	private long lastTime = System.currentTimeMillis();
	Ship launcher;
	
	public Missile(Double2D direction, Double2D origine, Ship launcher) {
		super();
		this.direction = direction;
		this.direction.normalize();
		this.launcher = launcher;
		pos = origine;
	}


	@Override
	public void step(SimState state) {
		long deltaT = System.currentTimeMillis() - lastTime;
		
		BattleShip bs = (BattleShip)state;
		
		Double2D deplacement = new Double2D(direction.getX() * missileSpeed * deltaT, direction.getY() * missileSpeed * deltaT);
		pos.add(deplacement);
		
		//TODO Gérer les sortie de map;
		
		Int2D gridPos = new Int2D((int)Math.floor(pos.x), (int)Math.floor(pos.y)); 
		
		bs.map.setObjectLocation(this, gridPos);
		Bag allObjectInCell = bs.map.getObjectsAtLocation(gridPos.x, gridPos.y);
		for(int i = 0 ; i < allObjectInCell.size() ; i++) {
			Object obj = allObjectInCell.get(i);
			if(obj instanceof Ship && obj != launcher) {
				ShootReceived shootMsg = new ShootReceived(20, launcher);
				((Ship)obj).shipShot(shootMsg);
				bs.map.remove(this);
			}
		}
		
		lastTime = System.currentTimeMillis();	
		
		
	}
}
