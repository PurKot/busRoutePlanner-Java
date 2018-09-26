package edu.tamu.routePlanner.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Data service class to access data repositories. Plan to implement it in
 * further release.
 * 
 * @author purni
 *
 */
@Service
public class DataService {

	@Autowired
	private BusStopRepository busStopRepository;

	public BusStopRepository getBusStopRepository() {
		return busStopRepository;
	}

	public void setBusStopRepository(BusStopRepository busStopRepository) {
		this.busStopRepository = busStopRepository;
	}

}
