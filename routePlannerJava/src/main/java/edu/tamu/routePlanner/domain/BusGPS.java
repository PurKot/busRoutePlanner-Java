package edu.tamu.routePlanner.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BusGPS {
	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public Integer getDir() {
		return Dir;
	}

	public void setDir(Integer dir) {
		Dir = dir;
	}

	public Double getLat() {
		return Lat;
	}

	public void setLat(Double lat) {
		Lat = lat;
	}

	public Double getLong() {
		return Long;
	}

	public void setLong(Double l) {
		Long = l;
	}

	public Integer getSpd() {
		return Spd;
	}

	public void setSpd(Integer spd) {
		Spd = spd;
	}

	@JsonProperty
	private String Date;
	@JsonProperty
	private Integer Dir;
	@JsonProperty
	private Double Lat;
	@JsonProperty
	private Double Long;
	@JsonProperty
	private Integer Spd;

}
