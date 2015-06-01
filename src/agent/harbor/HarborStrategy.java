package agent.harbor;

import sim.engine.SimState;
import agent.ship.ShipMessage.EnvironmentDamage;
import agent.ship.ShipMessage.ShootReceived;

public interface HarborStrategy {

	/**
	 * Called when a Strategy first bound to a Harbor
	 * 
	 * @param harbor
	 *            The harbor the Strategy was bounded to
	 */
	public void init(Harbor harbor, SimState state);

	/**
	 * Called every simulation cycle to update the strategy
	 * 
	 * @param harbor
	 *            The harbor the strategy was bounded to
	 */
	public void action(Harbor harbor, SimState state);

	/**
	 * Called when the harbor is under attack
	 * 
	 * @param harbor
	 *            The harbor that is under attack
	 * @param damage
	 */
	public void attacked(Harbor harbor, ShootReceived shoot);

	/**
	 * Called when the harbor is damaged by environment
	 * 
	 * @param harbor
	 *            The harbor that is damaged
	 * @param damage
	 *            The damage environment cost
	 */
	public void environmentDamage(Harbor harbor, EnvironmentDamage damage);

}
