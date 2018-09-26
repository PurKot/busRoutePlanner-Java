package edu.tamu.routePlanner.data;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.tamu.routePlanner.domain.BusRoute;
import edu.tamu.routePlanner.domain.BusRouteRepository;

public class Neo4jBusRouteRepository implements BusRouteRepository {

	@Override
	public <S extends BusRoute> S save(S s, int depth) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends BusRoute> Iterable<S> save(Iterable<S> entities, int depth) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<BusRoute> findById(String id, int depth) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<BusRoute> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<BusRoute> findAll(int depth) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<BusRoute> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<BusRoute> findAll(Sort sort, int depth) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<BusRoute> findAllById(Iterable<String> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<BusRoute> findAllById(Iterable<String> ids, int depth) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<BusRoute> findAllById(Iterable<String> ids, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<BusRoute> findAllById(Iterable<String> ids, Sort sort, int depth) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<BusRoute> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<BusRoute> findAll(Pageable pageable, int depth) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends BusRoute> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends BusRoute> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<BusRoute> findById(String id) {
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
	public void delete(BusRoute entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(Iterable<? extends BusRoute> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
