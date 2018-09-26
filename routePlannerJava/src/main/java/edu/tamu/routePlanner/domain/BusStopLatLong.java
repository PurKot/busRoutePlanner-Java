package edu.tamu.routePlanner.domain;

import org.springframework.data.neo4j.annotation.QueryResult;

@QueryResult
public class BusStopLatLong {
	public String Name;
	public Double Latitude;
	public Double Longitude;
}
