/**
 * 
 */
package agent.ship;

import java.util.Queue;

import agent.harbor.Faction;
import agent.ship.ShipMessage.EnnemyKilled;
import agent.ship.ShipMessage.EnvironmentDamage;
import agent.ship.ShipMessage.Message;
import agent.ship.ShipMessage.ShootReceived;
import sim.engine.SimState;
import sim.engine.Steppable;
import sim.util.Int2D;

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
		
		handleAllMessage();
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
	
	/**
	 * Return the strategy currently used.
	 * @return
	 */
	public ShipTemplate getTemplate() {
		return template;
	}
	
	private void handleAllMessage() {
		Message msg;
		
		synchronized (messageQueue)
		{
			while ((msg = messageQueue.poll()) != null) {
				switch (msg.getType()) {
				case "ShootReceived":
					behaviourStrategy.shootReceived(this, (ShootReceived) msg);
					break;
				case "EnnemyKilled":
					behaviourStrategy.ennemyKilled(this, (EnnemyKilled) msg);
					break;
				case "EnvironmentDamage":
					behaviourStrategy.environmentDamage(this,
							(EnvironmentDamage) msg);
					break;
				default:
					break;
				}
			}
		}
	}
	
	public void shoot(Int2D direction)
	{
		if(shootCooldownEnd <= System.currentTimeMillis()){
			
		}
	}
	
	/**
	 * Notify the ship he has been strike by a missile
	 * @param msg
	 */
	private void shipShot(ShootReceived msg)
	{
		synchronized (messageQueue) {
			messageQueue.add(msg);
		}
	}

	private ShipStrategy behaviourStrategy;	
	private ShipTemplate template;
	
	private Queue<Message> messageQueue;
	
	private int shootCooldownEnd = 0;
	private Faction faction;

	public Faction getFaction() {
		return faction;
	}
}
