package edu.tamu.routePlanner.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Route Pattern class to get data from RESTful api
 * 
 * @author purni
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class RoutePattern {
	@JsonProperty
	private String Key;
	@JsonProperty
	private String Name;
	@JsonProperty
	private String ShortName;

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
}
