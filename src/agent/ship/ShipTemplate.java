/**
 * 
 */
package agent.ship;

/**
 * @author antoine
 *
 * This class describe a ship.
 * 
 * It describe all the features common to a model of a ship such as
 * construction cost, attack power, etc
 */
public class ShipTemplate {
	private int constructionCost;
	private int attackPower;
	private int attackSpeed;
	private int maxSpeed;
	private int shootRange;
	private int viewRange;
	private int initialLifePoint;
	private String templateName;
	
	public ShipTemplate(int constructionCost, int attackPower, int attackSpeed,
			int maxSpeed, int shootRange, int viewRange, int initialLifePoint,
			String templateName) {
		super();
		this.constructionCost = constructionCost;
		this.attackPower = attackPower;
		this.attackSpeed = attackSpeed;
		this.maxSpeed = maxSpeed;
		this.shootRange = shootRange;
		this.viewRange = viewRange;
		this.initialLifePoint = initialLifePoint;
		this.templateName = templateName;
	}

	public int getConstructionCost() {
		return constructionCost;
	}
	public int getAttackPower() {
		return attackPower;
	}
	public int getAttackSpeed() {
		return attackSpeed;
	}
	public int getMaxSpeed() {
		return maxSpeed;
	}
	public int getShootRange() {
		return shootRange;
	}
	public int getViewRange() {
		return viewRange;
	}
	public int getInitialLifePoint() {
		return initialLifePoint;
	}
	public String getTemplateName() {
		return templateName;
	}

}
