package edu.tamu.routePlanner.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class to get real time bus data from the REST web api
 * 
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Buses {
	@JsonProperty
	public String Key;
	@JsonProperty
	public String Name;
	// @JsonProperty
	// public BusStatic Static;
	// @JsonProperty
	// public BusDriver Driver;
	@JsonProperty
	public BusGPS GPS;
	@JsonProperty
	public BusAPC APC;
	@JsonProperty
	public BusCurrentWork CurrentWork;
	@JsonProperty
	public BusNextStop[] NextStops;

}
