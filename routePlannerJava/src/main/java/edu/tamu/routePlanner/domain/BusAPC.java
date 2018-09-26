package edu.tamu.routePlanner.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BusAPC {

	public Integer getPassengerCapacity() {
		return PassengerCapacity;
	}

	public void setPassengerCapacity(Integer passengerCapacity) {
		PassengerCapacity = passengerCapacity;
	}

	public Integer getTotalPassenger() {
		return TotalPassenger;
	}

	public void setTotalPassenger(Integer totalPassenger) {
		TotalPassenger = totalPassenger;
	}

	@JsonProperty
	private Integer PassengerCapacity;
	@JsonProperty
	private Integer TotalPassenger;

}
