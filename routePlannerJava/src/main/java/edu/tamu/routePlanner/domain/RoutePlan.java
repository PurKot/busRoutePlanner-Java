package edu.tamu.routePlanner.domain;

/**
 * RoutePlan Repository class.
 */
import java.util.List;

import org.neo4j.driver.v1.Record;

public interface RoutePlan {
	/**
	 * Function to get route plans between the From and To bus Stops
	 * 
	 * @param fromBusStop - From bus stop Name
	 * @param toBusStop   - To bus stop Name
	 * @return List of Route plan records
	 */
	public List<Record> getRoutes(String fromBusStop, String toBusStop);

}
