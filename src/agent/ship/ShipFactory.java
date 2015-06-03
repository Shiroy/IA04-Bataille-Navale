package agent.ship;

import java.util.HashMap;

public class ShipFactory {

	public static ShipFactory getInstance() {
		if (m_instance == null)
			m_instance = new ShipFactory();

		return m_instance;
	}

	public ShipTemplate getShipTemplate(String name) {
		if (m_allShipTemplates.containsKey(name))
			return m_allShipTemplates.get(name);
		else
			return null;
	}

	private ShipFactory() {
		m_allShipTemplates = new HashMap<String, ShipTemplate>();

		ShipTemplate barkTemplate = new ShipTemplate(100, 10, 3, 2, 3, 5, 50,
				"Bark");
		
		ShipTemplate frigateTemplate = new ShipTemplate(20, 2, 8, 8, 2, 4, 10, 
				"Frigate");

		m_allShipTemplates.put("Bark", barkTemplate);
		m_allShipTemplates.put("Frigate", barkTemplate);
		// TODO Add other ship templates
	}

	private HashMap<String, ShipTemplate> m_allShipTemplates;

	private static ShipFactory m_instance = null;
}
