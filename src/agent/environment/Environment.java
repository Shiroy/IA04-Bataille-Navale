package agent.environment;

import sim.display.GUIState;
import sim.engine.SimState;
import sim.engine.Steppable;
import sim.portrayal.grid.SparseGridPortrayal2D;
import application.state.BattleShip;
import application.state.BattleShipWithUI;

@SuppressWarnings("serial")
public class Environment implements Steppable {
	private EnvironmentStrategy behaviourStrategy;
	private static Environment m_instance = null;
	private SparseGridPortrayal2D mapPortrayal;
	
	public static Environment getInstance() {
		if(m_instance == null)
			m_instance = new Environment();
		return m_instance;
	}
	
	public void setMap(GUIState gui){
		BattleShipWithUI bswu = (BattleShipWithUI) gui;
		//this.mapPortrayal=bswu.getMap;
	}

	public Environment() {
		
	}

	@Override
	public void step(SimState state) {
		BattleShip bs = (BattleShip) state;
		behaviourStrategy.action(this, bs);
	}
	
	public void setStrategy(EnvironmentStrategy behaviour){
		behaviourStrategy = behaviour;
		behaviourStrategy.init(this);
	}
	
	public void createTornado(){
		Tornado tornado = new Tornado();
		mapPortrayal.setObjectPosition(tornado, null, null);
	}
	

}
