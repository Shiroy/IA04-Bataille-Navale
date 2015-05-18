/**
 * 
 */
package agent.ship;

import sim.engine.SimState;

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
	 * We assume that ships are excellent shooters and never shoot 
	 * a friend.
	 * 
	 * @param ship The ship whicj received the shoot
	 */
	public void shootReceived(Ship ship);
	
	/**
	 * Called when the ship kill an ennemy.
	 * 
	 * @param killer The killer ship
	 * @param killed The killed ship
	 */
	public void ennemyKilled(Ship killer, Ship killed);

}
