package agent.harbor;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.Queue;

import sim.engine.SimState;
import sim.engine.Steppable;
import sim.portrayal.DrawInfo2D;
import sim.portrayal.simple.OvalPortrayal2D;
import sim.util.Int2D;
import agent.ship.Ship;
import agent.ship.ShipFactory;
import agent.ship.ShipTemplate;
import agent.ship.ShipMessage.EnvironmentDamage;
import agent.ship.ShipMessage.Message;
import agent.ship.ShipMessage.ShootReceived;
import application.state.BattleShip;

/**
 * @author xianyu
 *
 */
public class Harbor extends OvalPortrayal2D implements Steppable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int life;
	private Faction faction;
	private Int2D position;
	private int woodStock;
	private HarborStrategy behaviourStrategy;
	private ShipFactory shipFactory = ShipFactory.getInstance();
	private Queue<Message> messageQueue;
	private BattleShip state;
	private String nextShip = "Bark";
	private int frigateNum = 0;
	private int barkNum = 0;

	public Harbor(int life, Faction faction, Int2D position, int woodStock,
			HarborStrategy behaviourStrategy, SimState state) {
		super(Color.BLACK, true);
		this.life = life;
		this.faction = faction;
		this.position = position;
		this.woodStock = woodStock;
		this.behaviourStrategy = behaviourStrategy;
		this.messageQueue = new LinkedList<Message>();
		this.state = (BattleShip)state;
	}

	@Override
	public void step(SimState state) {
		
		behaviourStrategy.action(this, state);
		handleAllMessage();
	}

	/*
	 * Create new ship with the given name at a position near the Harbor.
	 */
	public Ship createShip(String name) {
		Int2D position = getPosition();
		int x = position.x - 1 + state.random.nextInt(3);
		int y = position.y - 1 + state.random.nextInt(3);
		
		while (x == position.x && y == position.y) {
			x = position.x - 1 + state.random.nextInt(3);
			y = position.y - 1 + state.random.nextInt(3);
		}
		
		ShipTemplate template = this.shipFactory.getShipTemplate(name);
		Ship newShip = null;
		if (template.getConstructionCost() <= this.woodStock) {

			newShip = new Ship(template, position, state);

			newShip.setFaction(faction);
			this.woodStock = this.woodStock - template.getConstructionCost();
		}
		return newShip;
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

	public void damage(int damagePoint) {
		if (this.life > damagePoint)
			this.life = this.life - damagePoint;
		else
			this.life = 0;
	}
	
	public void count(String shipName){
		switch(shipName){
		case "Frigate":
			this.frigateNum++;
			break;
		case "Bark":
			this.barkNum++;
			break;
		default:
			break;
		}
	}


	private void handleAllMessage() {
		Message msg;
		synchronized (messageQueue) {
			while (messageQueue.peek() != null) {
				msg = messageQueue.poll();
				switch (msg.getType()) {
				case "ShootReceived":
					behaviourStrategy.attacked(this, (ShootReceived) msg);
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

//	public int getLife() {
//		return life;
//	}

//	public void setLife(int life) {
//		this.life = life;
//	}

//	public Faction getFaction() {
//		return faction;
//	}

//	public void setFaction(Faction faction) {
//		this.faction = faction;
//	}

	public Int2D getPosition() {
		return position;
	}

	public void setPosition(Int2D position) {
		this.position = position;
	}

	public int getWoodStock() {
		return woodStock;
	}

//	public void setWoodStock(int woodStock) {
//		this.woodStock = woodStock;
//	}

//	public HarborStrategy getBehaviourStrategy() {
//		return behaviourStrategy;
//	}

	public void setBehaviourStrategy(HarborStrategy behaviour, SimState state) {
		this.behaviourStrategy = behaviour;
		behaviourStrategy.init(this, state);
	}

	public String getNextShip() {
		return nextShip;
	}

	public void setNextShip(String nextShip) {
		this.nextShip = nextShip;
	}

	public int getFrigateNum() {
		return frigateNum;
	}
	
	public int getBarkNum() {
		return barkNum;
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
