package edu.tamu.routePlanner.domain;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.neo4j.driver.v1.Record;

/**
 * BusStop entity repository interface. It extends Neo4jRepository interface
 * 
 * @author Purnima
 *
 */
@Repository
public interface BusStopRepository extends Neo4jRepository<BusStop, String>, BusStopRepositoryCustom {

	@Query("match(p:BusStop) return p.Name;")
	public String[] getBusStopNames();

	public List<Record> busStopLatLong();

	@Query("match(p:BusStop) return p.Name as Name,p.Latitude as Latitude,p.Longtitude as Longitude")
	List<BusStopLatLong> findBusStopLatLong();

}
