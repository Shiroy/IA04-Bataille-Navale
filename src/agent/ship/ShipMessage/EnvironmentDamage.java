package agent.ship.ShipMessage;

public class EnvironmentDamage implements Message {

	private int damage;
	
	public EnvironmentDamage(int damage) {
		this.damage = damage;
	}
	
	/**
	 * Get the amount of damage caused by the environment.
	 * @return
	 */
	public int getDamage() {
		return damage;
	}
	
	@Override
	public String getType() {
		return "EnvironmentDamage";
	}

}
