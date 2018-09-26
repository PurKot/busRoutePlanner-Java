package edu.tamu.routePlanner.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BusCWRoute {
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

	public String getRouteNumber() {
		return RouteNumber;
	}

	public void setRouteNumber(String routeNumber) {
		RouteNumber = routeNumber;
	}

	@JsonProperty
	private String Key;
	@JsonProperty
	private String Name;
	@JsonProperty
	private String RouteNumber;

}
