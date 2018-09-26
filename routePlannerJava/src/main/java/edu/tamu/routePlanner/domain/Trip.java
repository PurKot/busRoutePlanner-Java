package edu.tamu.routePlanner.domain;

import java.util.ArrayList;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 * Node Class for Trip details. This will have details for Timed stops for each
 * route
 */

@NodeEntity
public class Trip {

	@Id
	@GeneratedValue
	@GraphId
	Long iD;
	@Relationship(type = "TIMED_STOP", direction = Relationship.OUTGOING)
	private ArrayList<StopTime> stoptimes;

	public Trip() {
		this.stoptimes = new ArrayList<StopTime>();
	}

	public void addStopTime(StopTime st) {
		this.stoptimes.add(st);
	}

	public ArrayList<StopTime> getStoptimes() {
		return this.stoptimes;
	}
}
