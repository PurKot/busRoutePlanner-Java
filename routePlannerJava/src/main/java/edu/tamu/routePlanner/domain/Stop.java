package edu.tamu.routePlanner.domain;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Stop Class to get data from REST Web API
 * 
 * @author Purnima
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Transient
public class Stop {
	@Id
	@GeneratedValue
	Long iD;
	@JsonProperty
	private String Key;
	@JsonProperty
	private Integer Rank;
	@JsonProperty
	private String Name;
	@JsonProperty
	private String StopCode;
	@JsonProperty
	private Boolean IsTimePoint;

	public Stop() {

	}

	public String getKey() {
		return Key;
	}

	public void setKey(String key) {
		Key = key;
	}

	public Integer getRank() {
		return Rank;
	}

	public void setRank(Integer rank) {
		Rank = rank;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getStopCode() {
		return StopCode;
	}

	public void setStopCode(String stopCode) {
		StopCode = stopCode;
	}

	public Boolean getIsTimePoint() {
		return IsTimePoint;
	}

	public void setIsTimePoint(Boolean isTimePoint) {
		IsTimePoint = isTimePoint;
	}

}
