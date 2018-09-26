package edu.tamu.routePlanner.domain;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 * Relationship Class for Trip-Route relation details. This will have define
 * relationship Trip "uses" BusRoute pattern.
 */

@RelationshipEntity(type = "USES")
public class TripUses {
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public BusRoute getBusRoute() {
		return BusRoute;
	}

	public void setBusRoute(BusRoute busRoute) {
		BusRoute = busRoute;
	}

	@Id
	@GeneratedValue
	private @GraphId Long id;
	private @StartNode Trip trip;
	private @EndNode BusRoute BusRoute;

	public TripUses(Trip trip, BusRoute route) {
		this.BusRoute = route;
		this.trip = trip;
	}
}
