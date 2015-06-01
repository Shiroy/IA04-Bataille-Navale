/**
 * 
 */
package agent.ship;

import sim.engine.SimState;
import agent.ship.ShipMessage.EnnemyKilled;
import agent.ship.ShipMessage.EnvironmentDamage;
import agent.ship.ShipMessage.ShootReceived;

/**
 * @author antoine
 *
 * This interface implement a behavior strategy to control a ship.
 */
public interface ShipStrategy {
	
	/**
	 * This method is called once when the behavior is bound to a ship.
	 * 
	 * @param ship The ship the behavior was bound to.
	 */
	public void init(Ship ship);
	
	/**
	 * Called to update the strategy.
	 * 
	 * This method is called once per simulation tick to update
	 * the ship behavior. Use ship method to make the ship do something.
	 *  
	 * @param ship The ship which must be updated
	 * @param state The actual simulation's state
	 */
	public void action(Ship ship, SimState state);
	
	/**	 
	 * Called when a shoot is received
	 * 
	 * This method is called when the ship received an ennemy shoot.
	 * 
	 * @param ship The ship which received the shoot
	 */
	public void shootReceived(Ship ship, ShootReceived damage);
	
	/**
	 * Called when the ship kill an ennemy.
	 * 
	 * @param killer The killer ship
	 * @param killInfo The informations about the last kill
	 */
	public void ennemyKilled(Ship killer, EnnemyKilled killInfo);
	
	public void environmentDamage(Ship victim, EnvironmentDamage damage);

}
