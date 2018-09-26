package edu.tamu.routePlanner.domain;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * BusRoute Repository interface
 * <p>
 * Extends Neo4jRepository interface
 * 
 * @author purni
 *
 */
@Repository
public interface BusRouteRepository extends Neo4jRepository<BusRoute, String> {

}
