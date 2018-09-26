package edu.tamu.routePlanner.domain;

import java.util.ArrayList;
import java.util.List;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * represents the Bus Route class
 * <p>
 * Node entity class for Neo4j with Label BusRoute
 * 
 * @author purni
 *
 */
@NodeEntity(label = "BusRoute")
@JsonIgnoreProperties(ignoreUnknown = true)
public class BusRoute {
	@Id
	@GeneratedValue
	@GraphId
	Long iD;
	@JsonProperty
	@Transient
	private RoutePattern Pattern;
	@JsonProperty
	private String Key;
	@JsonProperty
	private String Name;
	@JsonProperty
	private String ShortName;
	@Relationship(type = "STOPS_AT")
	private ArrayList<StopsAt> stopsAt;
	@Relationship(type = "USES", direction = Relationship.INCOMING)
	private ArrayList<TripUses> tripuses;

	public BusRoute() {
		stopsAt = new ArrayList<StopsAt>();
		tripuses = new ArrayList<TripUses>();
	}

	public void addStopsAtRelationship(List<BusStop> busstops) {
		for (BusStop x : busstops) {
			StopsAt stopsat = new StopsAt(this, x);
			this.stopsAt.add(stopsat);
		}
	}

	public void addUsesRelationship(Trip[] trips) {
		for (int i = 0; i < trips.length; i++) {
			TripUses uses = new TripUses(trips[i], this);
			this.tripuses.add(uses);
		}
	}

	public Long getiD() {
		return iD;
	}

	public void setiD(Long iD) {
		this.iD = iD;
	}

	public String getKey() {
		return Key;
	}

	public void setKey(String key) {
		Key = key;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getShortName() {
		return ShortName;
	}

	public void setShortName(String shortName) {
		ShortName = shortName;
	}

	public ArrayList<StopsAt> getStopsAt() {
		return stopsAt;
	}

	public void setStopsAt(ArrayList<StopsAt> stopsAt) {
		this.stopsAt = stopsAt;
	}

}
