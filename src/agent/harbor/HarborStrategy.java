package agent.harbor;

import agent.ship.Ship;
import agent.ship.ShipMessage.ShootReceived;
import sim.engine.SimState;

public interface HarborStrategy {
	
	/**
	 * Called when a Strategy first bound to a Harbor
	 * 
	 * @param harbor The harbor the Strategy was bounded to
	 */
	public void init(Harbor harbor, SimState state);
	
	/**
	 * Called every simulation cycle to update the strategy 
	 * 
	 * @param harbor The harbor the strategy was bounded to
	 */
	public void action(Harbor harbor, SimState state);
	
	/**
	 * Called when the harbor is under attack
	 * 
	 * @param harbor The harbor that is under attack
	 * @param damage 
	 */
	public void attacked(Harbor harbor, ShootReceived shoot);
	
}
