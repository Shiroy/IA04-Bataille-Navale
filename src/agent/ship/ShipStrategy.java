/**
 * 
 */
package agent.ship;

/**
 * @author antoine
 *
 * This interface implement a behavior strategy to control a ship.
 */
public interface ShipStrategy {
	
	/**
	 * @author antoine
	 * 
	 * This method is called once when the behavior is bound to a ship.
	 * 
	 * @param ship Ship The ship the behavior was bound to.
	 */
	public void init(Ship ship);
	
	/**
	 * @author antoine
	 * Called to update the strategy.
	 * 
	 * This method is called once per simulation tick to update
	 * the ship behavior. Use ship method to make the ship do something.
	 *  
	 * @param ship Ship The ship which must be updated
	 */
	public void action(Ship ship);
}
