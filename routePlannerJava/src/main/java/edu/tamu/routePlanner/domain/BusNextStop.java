package edu.tamu.routePlanner.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BusNextStop {
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

	public String getStopCode() {
		return StopCode;
	}

	public void setStopCode(String stopCode) {
		StopCode = stopCode;
	}

	public String getEstimatedDepartTime() {
		return EstimatedDepartTime;
	}

	public void setEstimatedDepartTime(String estimatedDepartTime) {
		EstimatedDepartTime = estimatedDepartTime;
	}

	public String getScheduledDepartTime() {
		return ScheduledDepartTime;
	}

	public void setScheduledDepartTime(String scheduledDepartTime) {
		ScheduledDepartTime = scheduledDepartTime;
	}

	public String getScheduledWorkDate() {
		return ScheduledWorkDate;
	}

	public void setScheduledWorkDate(String scheduledWorkDate) {
		ScheduledWorkDate = scheduledWorkDate;
	}

	public String getWork() {
		return Work;
	}

	public void setWork(String work) {
		Work = work;
	}

	@JsonProperty
	private String Key;
	@JsonProperty
	private String Name;
	@JsonProperty
	private String StopCode;
	@JsonProperty
	private String EstimatedDepartTime;
	@JsonProperty
	private String ScheduledDepartTime;
	@JsonProperty
	private String ScheduledWorkDate;
	@JsonProperty
	private String Work;

}
