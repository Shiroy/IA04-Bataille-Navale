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
	private int templateName;
	
	
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
	public int getTemplateName() {
		return templateName;
	}
	
	

}
