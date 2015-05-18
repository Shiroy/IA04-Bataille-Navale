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
	 * @author antoine
	 * 
	 * Called at each tick of the simulation to update the agent 
	 */
	@Override
	public void step(SimState state) {
		behaviourStrategy.action(this, state);

	}
	
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
