package agent.harbor;

import java.awt.Color;
import java.awt.Graphics2D;

import sim.engine.SimState;
import sim.engine.Steppable;
import sim.portrayal.DrawInfo2D;
import sim.portrayal.simple.OvalPortrayal2D;
import sim.util.Int2D;
import agent.ship.Ship;
import agent.ship.ShipFactory;
import agent.ship.ShipTemplate;

public class Harbor extends OvalPortrayal2D implements Steppable {

	private int life;
	private Faction faction;
	private Int2D position;
	private int woodStock;
	private HarborStrategy behaviourStrategy;
	private ShipFactory shipFactory = ShipFactory.getInstance();
	
	public Harbor(int life, Faction faction, Int2D position, int woodStock,
			HarborStrategy behaviourStrategy) {
		super(Color.BLACK, true);
		this.life = life;
		this.faction = faction;
		this.position = position;
		this.woodStock = woodStock;
		this.behaviourStrategy = behaviourStrategy;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override

	public void step(SimState state) {
		behaviourStrategy.action(this,state);
	}

	/*
	 * Create new ship with the given template at a position near the Harbor.
	 */
	public Ship createShip(String name){
		ShipTemplate template = this.shipFactory.getShipTemplate(name);
		if(template.getConstructionCost() <= this.woodStock){
			Ship newShip = new Ship(template);
			this.woodStock = this.woodStock - template.getConstructionCost();
			return newShip;
		}
		else 
			return null;
	}

	/*
	 * Add some wood if necessary
	 */
	public void addWoodStock(int num) {
		woodStock = woodStock + num;
	}

	/*
	 * the harbor is damaged by enemy ship or environment
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

	public final void draw(Object object, Graphics2D graphics, DrawInfo2D info) {
		graphics.setColor(Color.BLACK);

		int x = (int) (info.draw.x - info.draw.width);
		int y = (int) (info.draw.y - info.draw.height);
		int width = (int) (info.draw.width * 2);
		int height = (int) (info.draw.height * 2);
		graphics.fillOval(x, y, width, height);
		
		graphics.setColor(faction.toColor());
		x = (int) (info.draw.x - info.draw.width / 2.0);
		y = (int) (info.draw.y - info.draw.height / 2.0);
		width = (int) (info.draw.width);
		height = (int) (info.draw.height);

		graphics.fillOval(x, y, width, height);
	}

}
