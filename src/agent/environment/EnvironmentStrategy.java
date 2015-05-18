package agent.environment;

import sim.engine.SimState;

public interface EnvironmentStrategy {
	public void action(Environment env, SimState state);
	public void init(Environment env);

}
