package agent.harbor;

import sim.engine.SimState;
import sim.engine.Steppable;
import sim.util.Int2D;
import agent.ship.Ship;
import agent.ship.ShipFactory;
import agent.ship.ShipTemplate;

public class Harbor implements Steppable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public void step(SimState arg0) {
		behaviourStrategy.action(this, arg0);
	}
	
	/* 
	 * Creat new ship with the given template at 
	 * a position near the Harbor.
	 */
	public Ship createShip(String name){
		ShipTemplate template = this.shipFactory.getShipTemplate(name);
		if(template.getConstructionCost() <= this.woodStock){
			
		}
		Ship newShip = new Ship(template);
		this.woodStock = this.woodStock - template.getConstructionCost();
		return newShip;
	}
	/*
	 *  Add some wood if necessary 
	 */
	public void addWoodStock(int num){
		woodStock = woodStock + num;
	}
	/*
	 * the harbor is damaged by enemy ship
	 */
	
	public void damage(int damagePoint){
		if(this.life > damagePoint)
			this.life = this.life - damagePoint;
		else
			this.life = 0;
	}
	
	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public Faction getFaction() {
		return faction;
	}

	public void setFaction(Faction faction) {
		this.faction = faction;
	}

	public Int2D getPosition() {
		return position;
	}

	public void setPosition(Int2D position) {
		this.position = position;
	}

	public int getWoodStock() {
		return woodStock;
	}

	public void setWoodStock(int woodStock) {
		this.woodStock = woodStock;
	}

	public HarborStrategy getBehaviourStrategy() {
		return behaviourStrategy;
	}

	public void setBehaviourStrategy(HarborStrategy behaviour,SimState state) {
		this.behaviourStrategy = behaviour;
		behaviourStrategy.init(this,state);
	}

	private int life;
	private Faction faction;
	private Int2D position;
	private int woodStock;
	private HarborStrategy behaviourStrategy;
	private ShipFactory shipFactory = ShipFactory.getInstance();
	
}
