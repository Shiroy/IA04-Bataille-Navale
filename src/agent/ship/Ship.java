/**
 * 
 */
package agent.ship;

import sim.engine.SimState;
import sim.engine.Steppable;

/**
 * @author antoine
 *
 */
public class Ship implements Steppable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * Called at each tick of the simulation to update the agent 
	 */
	@Override
	public void step(SimState state) {
		behaviourStrategy.action(this, state);

	}
	
	/**
	 * Set the ship behavior strategy and init it.
	 * 
	 * The {@link agent.ship.ShipStrategy#init(Ship) init} is called to init the strategy.
	 * @param behavior
	 */
	public void setStrategy(ShipStrategy behavior){
		behaviourStrategy = behavior;
		behaviourStrategy.init(this);
	}	
	
	public ShipTemplate getTemplate() {
		return template;
	}

	private ShipStrategy behaviourStrategy;	
	private ShipTemplate template;
}
