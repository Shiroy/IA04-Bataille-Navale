package agent.harbor;

public interface HarborStrategy {
	
	public void init(Harbor harbor);
	
	public void action(Harbor harbor);
	
}
