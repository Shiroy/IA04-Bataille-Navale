package agent.ship.ShipMessage;

import agent.ship.Ship;

public class ShootReceived implements Message {

	private int damage;
	private Ship shooter;
	
	public ShootReceived(int damage, Ship shooter) {
		this.damage = damage;
		this.shooter = shooter;
	}
	
	@Override
	public String getType() {
		return "ShootReceived";
	}

	/**
	 * Return the amount of damage received
	 * @return
	 */
	public int getDamage() {
		return damage;
	}
	
	/**
	 * Return the shooter ship
	 * @return
	 */
	public Ship getShooter() {
		return shooter;
	}
}
