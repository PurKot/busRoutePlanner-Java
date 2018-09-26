package edu.tamu.routePlanner.domain;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 * Neo4j relationship class "NEXT_STOP between 2 Bus stops
 * 
 * @author purni
 *
 */
@RelationshipEntity(type = "NEXT_STOP")
public class NextStop {

	@Id
	@GeneratedValue
	private @GraphId Long id;
	private @StartNode BusStop startBusStop;
	private @EndNode BusStop endBusStop;

	public NextStop(BusStop startBusStop, BusStop nextBusStop) {
		this.startBusStop = startBusStop;
		this.endBusStop = nextBusStop;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BusStop getStartBusStop() {
		return startBusStop;
	}

	public BusStop getEndBusStop() {
		return endBusStop;
	}

}
