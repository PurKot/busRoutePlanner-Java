package edu.tamu.routePlanner.domain;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Node Class for TimedStop details. This will have BusStop Name and the Time at
 * which a bus stops at it. It will be later used to create a relationship
 * between BusStop and StopTime
 */

@NodeEntity
public class StopTime {
	@Id
	@GeneratedValue
	@GraphId
	Long iD;

	private String time;

	private String busStopName;

	private Integer Sequence;

	public Integer getSequence() {
		return Sequence;
	}

	public void setSequence(Integer sequence) {
		Sequence = sequence;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String sttime) {
		this.time = sttime;
	}

	public String getBusStopName() {
		return busStopName;
	}

	public void setBusStopName(String busStopName) {
		this.busStopName = busStopName;
	}

}
