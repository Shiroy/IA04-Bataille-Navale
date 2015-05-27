package agent.ship.ShipMessage;

import agent.ship.Ship;

public class EnnemyKilled implements Message {

	private Ship who;
	
	public EnnemyKilled(Ship who) {
		this.who = who;
	}
	
	@Override
	public String getType() {
		return "EnnemyKilled";
	}

	/**
	 * Return the victim
	 * @return
	 */
	public Ship getWho() {
		return who;
	}	
}
