package edu.tamu.routePlanner.data;

import java.util.List;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.StatementResult;
import edu.tamu.routePlanner.domain.RoutePlan;

public class Neo4jRoutePlanRepository implements RoutePlan {

	@Override

	public List<Record> getRoutes(String fromBusStop, String toBusStop) {
		String shortestpathquery = String.format(
				"MATCH (a:BusStop {Name:'%s'}), (d:BusStop {Name:'%s'}) MATCH p = allShortestPaths((a)-[:STOPS_AT*]-(d)) RETURN EXTRACT(x IN NODES(p) | CASE WHEN x:BusStop THEN 'Bus Stop - ' + x.Name WHEN x:BusRoute THEN 'Bus Number - ' + x.ShortName ELSE '' END) AS itinerary;",
				fromBusStop, toBusStop);
		Neo4jDBOperation neo4jdb = new Neo4jDBOperation();
		StatementResult result = neo4jdb.Getresults(shortestpathquery);
		List<Record> list = result.list();
		System.out.println("Retreived Itinerary details");
		try {
			neo4jdb.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}