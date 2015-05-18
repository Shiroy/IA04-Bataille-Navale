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
	public void step(SimState arg0) {
		behaviourStrategy.action(this);

	}
	
	public void setStrategy(ShipStrategy behavior){
		behaviourStrategy = behavior;
		behaviourStrategy.init(this);
	}
	
	private ShipStrategy behaviourStrategy;

}
