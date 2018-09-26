package edu.tamu.routePlanner.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class for getting converted Latitude and Longitude from GIS REST API
 * <p>
 * Still under construction
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LatLong {

	@JsonProperty("strings")
	private String[] strings;

	public String[] getStrings() {
		return strings;
	}

	public void setStrings(String[] strings) {
		this.strings = strings;
	}

}
