package agent.harbor;

import agent.ship.Ship;
import sim.engine.SimState;

public interface HarborStrategy {
	
	/**
	 * Called when a Strategy first bound to a Harbor
	 * 
	 * @param harbor The harbor the Strategy was bounded to
	 */
	public void init(Harbor harbor);
	
	/**
	 * Called every simulation cycle to update the strategy 
	 * 
	 * @param harbor The harbor the strategy was bounded to
	 */
	public void action(Harbor harbor);
	
	/**
	 * Called when the harbor is under attack
	 * 
	 * @param harbor The harbor that is under attack
	 */
	public void attacked(Harbor harbor);
	
	/**
	 * Called when the harbor is occupied by an other faction
	 * 
	 * @param harbor	The harbor that is occupied
	 * @param occupant	The faction that occupy the harbor
	 */
	public void occupied(Harbor harbor, Faction occupant);
	
}
