package edu.tamu.routePlanner.domain;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 * Relationship STOPS_AT class
 *
 */
@RelationshipEntity(type = "STOPS_AT")
public class StopsAt {
	@Id
	@GeneratedValue
	private @GraphId Long id;
	private @StartNode BusRoute startBusRoute;
	private @EndNode BusStop stopsatBusStop;

	public StopsAt(BusRoute busroute, BusStop stopsatBS) {
		this.startBusRoute = busroute;
		this.stopsatBusStop = stopsatBS;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BusRoute getStartBusRoute() {
		return startBusRoute;
	}

	public void setStartBusRoute(BusRoute startBusRoute) {
		this.startBusRoute = startBusRoute;
	}

	public BusStop getStopsatBusStop() {
		return stopsatBusStop;
	}

	public void setStopsatBusStop(BusStop stopsatBusStop) {
		this.stopsatBusStop = stopsatBusStop;
	}

}
