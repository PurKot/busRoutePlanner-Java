package edu.tamu.routePlanner.data;

import java.io.IOException;
import java.util.List;

import org.bson.Document;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.Transaction;
import org.neo4j.driver.v1.TransactionWork;
import org.neo4j.driver.v1.exceptions.ServiceUnavailableException;
import org.neo4j.ogm.session.*;

import com.mongodb.DBObject;

import edu.tamu.routePlanner.domain.BusRoute;
import edu.tamu.routePlanner.domain.BusStop;
import edu.tamu.routePlanner.domain.Buses;
import edu.tamu.routePlanner.domain.Trip;
import edu.tamu.routePlanner.data.ConsumeRestAPI;

/**
 * <li>This class has the functionalities for loading the data to Neo4j Server
 * <li>This class will Add Routes, BusStops, Trips for each route and TimedStops
 * for each trip
 * <li>Relationships are also loaded through this class.
 * <li>This is run by gradle task before starting the GUI.
 * 
 */

public class Neo4jBulkDataLoader {

	public static void main(String[] args) {

		// Will Open the DB connection and thrown an error if Connection is not made.
		try {
			Neo4jDBOperation neo4jdb = new Neo4jDBOperation();
			deleteAllNodes(neo4jdb.driver.session()); // clear all previous nodes on neo4j
			System.out.println("Deleted all previous nodes on Neo4j");
			Neo4jDBconfiguration neodbconfig = new Neo4jDBconfiguration();
			SessionFactory DbSessionFactory = neodbconfig.sessionFactory();
			org.neo4j.ogm.session.Session ogmsession = DbSessionFactory.openSession();
			loadRouteBaseData(ogmsession); // Load Data
			ogmsession.clear();
			deleteduplicatenodes(neo4jdb.driver.session());
			addTimedStopRelation(neo4jdb.driver.session());
			addPrecedesRelation(neo4jdb.driver.session());
			neo4jdb.close();
		} catch (ServiceUnavailableException e) {
			System.out.println("Check Neo4j Database connection. Start your DB service and rerun.");
		} catch (IOException e) {
			System.out.println("Ensure the Config file is present and accessible. ");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Method to delete all previous data on Neo4j Server
	 * 
	 * @param bsession - session for the Neo4j database.
	 */
	private static void deleteAllNodes(Session bsession) {
		bsession.run("MATCH (n) DETACH DELETE n;");
	}

	/**
	 * Method to add TIMED_STOP relationship between StopTime nodes and Trip nodes
	 * 
	 * @param bsession - session for the Neo4j database.
	 */
	private static void addTimedStopRelation(Session bsession) {

		try {
			System.out.println("Adding StopTime BusStop relationship");

			bsession.writeTransaction(new TransactionWork<Integer>() {
				@Override
				public Integer execute(org.neo4j.driver.v1.Transaction tx) {
					return addLocatedAtRel(tx);
				}
			});
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Error loading base data. Check internet and DB connections");
		}
	}

	/**
	 * Method to add LOCATED_AT relationship between StopTime nodes and BusStop
	 * nodes
	 * 
	 * @param tx - Transaction id for the work
	 * @return 1 for a successful transaction
	 */
	protected static Integer addLocatedAtRel(Transaction tx) {

		String query = "MATCH(n:BusStop),(p:StopTime) where n.Name=p.busStopName create (p)-[r:LOCATED_AT]->(n) return r";
		tx.run(query);
		return 1;
	}

	/**
	 * Method to add PRECEDES relationship between StopTimes based on a sequence
	 * 
	 * @param bsession - session for the Neo4j database.
	 */
	private static void addPrecedesRelation(Session bsession) {

		try {
			System.out.println("Adding StopTime precedes relationship");

			bsession.writeTransaction(new TransactionWork<Integer>() {
				@Override
				public Integer execute(org.neo4j.driver.v1.Transaction tx) {
					return addPrecedesRel(tx);
				}
			});
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Error loading base data. Check internet and DB connections");
		}
	}

	/**
	 * Method to add Precedes relationship using a transaction
	 * 
	 * @param tx - Transaction unit of work.
	 * @return 1 for a successful transaction
	 */
	protected static Integer addPrecedesRel(Transaction tx) {

		String query = "MATCH (s1:StopTime)<-[:TIMED_STOP]-(t:Trip), (s2:StopTime)<-[:TIMED_STOP]-(t) where s2.Sequence=s1.Sequence+1  CREATE (s1)-[:PRECEDES]->(s2);";
		tx.run(query);
		return 1;
	}

	/**
	 * Custom Method to delete duplicate nodes inserted by the Neo4j- OGM- spring
	 * transactions
	 * 
	 * @param bsession - session for the Neo4j database.
	 */
	private static void deleteduplicatenodes(Session bsession) {

		try {
			System.out.println("Deleting BusRoute and BusStop duplicate Nodes and merging relationships...");

			bsession.writeTransaction(new TransactionWork<Integer>() {
				@Override
				public Integer execute(org.neo4j.driver.v1.Transaction tx) {
					String m = "1";
					return mergerel(tx, m);
				}
			});
			bsession.writeTransaction(new TransactionWork<Integer>() {
				@Override
				public Integer execute(org.neo4j.driver.v1.Transaction tx) {
					String m = "2";
					return mergerel(tx, m);
				}
			});
			bsession.writeTransaction(new TransactionWork<Integer>() {
				@Override
				public Integer execute(org.neo4j.driver.v1.Transaction tx) {
					String m = "3";
					return mergerel(tx, m);
				}
			});
			bsession.writeTransaction(new TransactionWork<Integer>() {
				@Override
				public Integer execute(org.neo4j.driver.v1.Transaction tx) {
					String m = "4";
					return mergerel(tx, m);
				}
			});
			System.out.println("Bulk Data load complete.");

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Error loading base data. Check internet and DB connections");
		}
	}

	/**
	 * Custom Method to merge relationships
	 * 
	 * @param tx - Transaction unit of work
	 * @param m  - case for selecting the query to run.
	 * @return 1 for a successful transaction
	 */
	protected static Integer mergerel(org.neo4j.driver.v1.Transaction tx, String m) {

		switch (m) {
		case "1": {
			String query = "MATCH (s:BusStop) WITH s.Name as Name, collect(s) as Stops, count(*) as cnt WHERE cnt > 1 WITH head(Stops) as first, tail(Stops) as rest UNWIND rest AS to_delete MATCH (to_delete)<-[r:NEXT_STOP]-(bs:BusStop) MERGE (first)<-[:NEXT_STOP]-(bs) DELETE r RETURN count(*)";
			tx.run(query);
			return 1;
		}
		case "2": {
			String query = "MATCH (s:BusStop) WITH s.Name as Name, collect(s) as Stops, count(*) as cnt WHERE cnt > 1 WITH head(Stops) as first, tail(Stops) as rest UNWIND rest AS to_delete MATCH (bs:BusStop)<-[r:NEXT_STOP]-(to_delete) MERGE (bs)<-[:NEXT_STOP]-(first) DELETE r RETURN count(*)";
			tx.run(query);
			return 1;
		}
		case "3": {
			String query = "MATCH (s:BusStop) WITH s.Name as Name, collect(s) as Stops, count(*) as cnt WHERE cnt > 1 WITH head(Stops) as first, tail(Stops) as rest UNWIND rest AS to_delete MATCH (to_delete)<-[r:STOPS_AT]-(br:BusRoute) MERGE (first)<-[:STOPS_AT]-(br) DELETE r RETURN count(*)";
			tx.run(query);
			return 1;
		}
		case "4":
			String query = "MATCH (s:BusStop) WITH s.Name as Name, collect(s) as Stops, count(*) as cnt WHERE cnt > 1 WITH head(Stops) as first, tail(Stops) as rest UNWIND rest AS to_delete DELETE to_delete RETURN count(*)";
			tx.run(query);
			return 1;
		}
		return null;

	}

	/**
	 * <li>Basic method to load all Bus related data to the Neo4j database
	 * <li>It will get all data from REST API
	 * 
	 * @param session - session for the Neo4j database.
	 */
	private static void loadRouteBaseData(org.neo4j.ogm.session.Session session) {

		ConsumeRestAPI rest = new ConsumeRestAPI();
		// get available bus routes from REST API
		System.out.println("Loading BusRoute and BusStop Nodes...");
		List<BusRoute> busroutes = rest.GetBusRoutesfromAPI();
		System.out.println("Fetched BusRoutes from REST API");

		for (BusRoute r : busroutes) {
			String shortName = r.getShortName();

			// For every bus route get the bus stops
			List<BusStop> busstops = rest.getRouteBusStops(shortName);
			if (!busstops.isEmpty()) {
				Trip[] trips = rest.getTripsforRoute(shortName);

				r.addStopsAtRelationship(busstops); // Add STOPS_AT relationship between route and stops
				r.addUsesRelationship(trips); // Add USES relationship between trip and route
				int j = 0;
				int i = 0;
				while (i < busstops.size() - 1) {
					j = i;
					if (!(i == busstops.size() - 2)) {
						busstops.get(j).addNextRelationship(busstops.get(j + 1)); // Add NEXT_STOP relationship between
																					// stops
					} else {
						busstops.get(i).addNextRelationship(busstops.get(0));
					}
					i++;
				}
				System.out.printf("Fetched BusStops, Trips and StopTimes from REST API for Route %s %n", shortName);
				session.save(r, 5);// will load the BusRoute, BusStop, Trip, StopTime nodes and Relationships to DB
			}

		}
		System.out.println("Loaded Nodes and Relationships to Neo4j DB...");

	}

}
