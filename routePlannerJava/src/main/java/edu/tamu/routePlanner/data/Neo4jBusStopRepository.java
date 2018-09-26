package edu.tamu.routePlanner.data;

import java.util.List;
import java.util.Optional;

import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.StatementResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.annotation.QueryResult;

import edu.tamu.routePlanner.domain.BusStop;
import edu.tamu.routePlanner.domain.BusStopLatLong;
import edu.tamu.routePlanner.domain.BusStopRepository;

/**
 * Neo4j BusStop repository class
 * <p>
 * Implements BusStopRepository interface
 * 
 * @author purni
 *
 */
public class Neo4jBusStopRepository implements BusStopRepository {

	static List<Record> busStopLatLongList;

	@Override
	public <S extends BusStop> S save(S s, int depth) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends BusStop> Iterable<S> save(Iterable<S> entities, int depth) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<BusStop> findById(String id, int depth) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<BusStop> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<BusStop> findAll(int depth) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<BusStop> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<BusStop> findAll(Sort sort, int depth) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<BusStop> findAllById(Iterable<String> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<BusStop> findAllById(Iterable<String> ids, int depth) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<BusStop> findAllById(Iterable<String> ids, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<BusStop> findAllById(Iterable<String> ids, Sort sort, int depth) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<BusStop> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<BusStop> findAll(Pageable pageable, int depth) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends BusStop> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends BusStop> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<BusStop> findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(BusStop entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(Iterable<? extends BusStop> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	//
	/**
	 * Method to retrieve names of Bus Stops
	 * 
	 * @return array of BusStop names
	 */
	public String[] getBusStopNames() {
		String query = String.format("match(p:BusStop) return p.Name;");
		String bsNames[] = null;
		try {
			Neo4jDBOperation neo4jdb = new Neo4jDBOperation();
			StatementResult result = neo4jdb.Getresults(query);
			List<Record> list1 = result.list();
			bsNames = new String[list1.size()];
			for (int i = 0; i < list1.size(); i++) {

				bsNames[i] = list1.get(i).get("p.Name").toString();
				bsNames[i] = bsNames[i].replace("\"", "");
			}
			neo4jdb.close();
		} catch (Exception e) {
			System.out.println("Unable to Connect to Database. Check DB connection and retry");
		}
		return bsNames;

	}

	/**
	 * Method to get the Bus stop Names, Latitudes and Longitudes
	 * 
	 * @return List of Record objects with Name, Latitude, Longitude keys
	 */
	public List<Record> busStopLatLong() {

		try {
			Neo4jDBOperation neo4jdb = new Neo4jDBOperation();
			StatementResult result = neo4jdb.Getresults(
					"match(p:BusStop) return p.Latitude as Latitude,p.Longtitude as Longitude,p.Name as Name");
			busStopLatLongList = result.list();
			neo4jdb.close();

		} catch (Exception e) {
			System.out.println("Unable to Connect to Database. Check DB connection and retry");
		}

		return busStopLatLongList;
	}

	@Override
	public List<BusStopLatLong> findBusStopLatLong() {

		return null;
	}
}
