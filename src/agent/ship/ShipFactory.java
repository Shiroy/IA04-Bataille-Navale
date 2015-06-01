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

		ShipTemplate barkTemplate = new ShipTemplate(100, 10, 3, 2, 3, 5, 30,
				"Bark");

		m_allShipTemplates.put("Bark", barkTemplate);
		// TODO Add other ship templates
	}

	private HashMap<String, ShipTemplate> m_allShipTemplates;

	private static ShipFactory m_instance = null;
}
