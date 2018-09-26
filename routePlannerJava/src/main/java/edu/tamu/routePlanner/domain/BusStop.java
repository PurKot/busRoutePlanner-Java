package edu.tamu.routePlanner.domain;

import java.util.HashSet;

import java.util.Set;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Bus stop class to get JSON objects from REST api and put data to Neo4j
 * database
 * <p>
 * Node entity class for Neo4j with Label "BusStop"
 * <p>
 * 
 * @author purni
 *
 */
@NodeEntity(label = "BusStop")

@JsonIgnoreProperties(ignoreUnknown = true)
public class BusStop {
	@Id
	@GeneratedValue
	@GraphId
	Long iD;
	@JsonProperty
	private String Key;
	@JsonProperty
	private String Name;
	@JsonProperty
	private String Description;
	@JsonProperty
	private Integer Rank;
	@JsonProperty
	private Double Longtitude;
	@JsonProperty
	private Double Latitude;
	@JsonProperty
	private Integer PointTypeCode;
	@JsonProperty
	@Transient
	private Stop Stop;
	@JsonProperty
	private Integer RouteHeaderRank;
	@Relationship(type = "NEXT_STOP")
	private Set<NextStop> nextStops = new HashSet<>();

	/**
	 * Method to Add NEXT_STOP relationship
	 * 
	 * @param nextBusStop
	 */
	public void addNextRelationship(BusStop nextBusStop) {
		NextStop nextStop = new NextStop(this, nextBusStop);
		this.nextStops.add(nextStop);
	}

	public BusStop() {

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

	public void setName() {
		this.Name = Stop.getName();
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public Integer getRank() {
		return Rank;
	}

	public void setRank(Integer rank) {
		Rank = rank;
	}

	public Double getLongtitude() {
		return Longtitude;
	}

	public void setLongtitude(Double longtitude) {
		Longtitude = longtitude;
	}

	public Double getLatitude() {
		return Latitude;
	}

	public void setLatitude(Double latitude) {
		Latitude = latitude;
	}

	public Integer getPointTypeCode() {
		return PointTypeCode;
	}

	public void setPointTypeCode(Integer pointTypeCode) {
		PointTypeCode = pointTypeCode;
	}

	public Stop getStop() {
		return Stop;
	}

	public void setStop(Stop stop) {
		Stop = stop;
	}

	public Integer getRouteHeaderRank() {
		return RouteHeaderRank;
	}

	public void setRouteHeaderRank(Integer routeHeaderRank) {
		RouteHeaderRank = routeHeaderRank;
	}

}
