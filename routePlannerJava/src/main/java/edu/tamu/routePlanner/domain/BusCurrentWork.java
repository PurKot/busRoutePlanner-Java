package edu.tamu.routePlanner.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BusCurrentWork {
	@JsonProperty
	public BusCWRoute Route;

}
