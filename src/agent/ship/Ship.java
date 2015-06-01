/**
 * 
 */
package agent.ship;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.Queue;

import sim.engine.SimState;
import sim.engine.Steppable;
import sim.portrayal.DrawInfo2D;
import sim.portrayal.simple.OvalPortrayal2D;
import sim.util.Double2D;
import sim.util.Int2D;
import agent.harbor.Faction;
import agent.ship.ShipMessage.EnnemyKilled;
import agent.ship.ShipMessage.EnvironmentDamage;
import agent.ship.ShipMessage.Message;
import agent.ship.ShipMessage.ShootReceived;

/**
 * @author antoine
 *
 */
public class Ship extends OvalPortrayal2D implements Steppable {

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
	
	/**
	 * Setter faction
	 * @param faction the new faction
	 */
	public void setFaction(Faction faction) {
		this.faction = faction;
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
	
	public void shoot(Double2D direction)
	{
		if(shootCooldownEnd <= System.currentTimeMillis()){
			
		}
	}
	
	/**
	 * Notify the ship he has been strike by a missile
	 * @param msg
	 */
	public void shipShot(ShootReceived msg)
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

	public Ship(ShipTemplate template) {
		this.template = template;
		messageQueue = new LinkedList<Message>();
		behaviourStrategy = new ShipStrategyHazardous();
	}

	public final void draw(Object object, Graphics2D graphics, DrawInfo2D info) {
		graphics.setColor(new Color(96, 48, 0)); //Brown
		int x = (int) (info.draw.x - info.draw.width / 2.0);
		int y = (int) (info.draw.y - info.draw.height / 2.0);
		int width = (int) (info.draw.width);
		int height = (int) (info.draw.height);
		graphics.fillOval(x, y, width, height);
		
		graphics.setColor(faction.toColor());
		x = (int) (info.draw.x - info.draw.width / 4.0);
		y = (int) (info.draw.y - info.draw.height / 4.0);
		width = (int) (info.draw.width / 2.0);
		height = (int) (info.draw.height / 2.0);
		graphics.fillOval(x, y, width, height);
	}
	
}
