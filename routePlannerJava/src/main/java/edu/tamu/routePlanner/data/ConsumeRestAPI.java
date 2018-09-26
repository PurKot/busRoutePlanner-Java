package edu.tamu.routePlanner.data;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;

import edu.tamu.routePlanner.domain.BusRoute;
import edu.tamu.routePlanner.domain.BusStop;
import edu.tamu.routePlanner.domain.Buses;
import edu.tamu.routePlanner.domain.StopTime;
import edu.tamu.routePlanner.domain.Trip;

/**
 * Class with methods to access the RESTful web api and get Bus transport data
 * 
 * @author purni
 *
 */

public class ConsumeRestAPI {

	public ConsumeRestAPI() {

	}

	/**
	 * To get Bus Stops data from the REST API
	 * 
	 * @param url - url to access to get Data
	 * @return List of Bus Stop objects.
	 */
	public List<BusStop> GetBusStopsfromAPI(String url) {

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<BusStop[]> response = restTemplate.getForEntity(url, BusStop[].class);
		return Arrays.asList(response.getBody());
	}

	/**
	 * Method to get Bus Routes data from the REST API
	 * 
	 * @return list of BusRoutes objects
	 */
	public List<BusRoute> GetBusRoutesfromAPI() {
		String url = "http://transport.tamu.edu:80/BusRoutesFeed/api/Routes";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<BusRoute[]> response = restTemplate.getForEntity(url, BusRoute[].class);
		System.out.println("Fetched BusRoutes from REST API");
		return Arrays.asList(response.getBody());

	}

	/**
	 * Method to get Bus stops for the Route defined by the Short Name from the REST
	 * api
	 * 
	 * @param ShortName Short Name for the route
	 * @return List of BusStop objects for the route
	 */
	public List<BusStop> getRouteBusStops(String ShortName) {
		ConsumeRestAPI rest = new ConsumeRestAPI();
		String url = String.format("http://transport.tamu.edu:80/BusRoutesFeed/api/route/%s/stops", ShortName);
		List<BusStop> busstops = rest.GetBusStopsfromAPI(url);
		for (BusStop x : busstops)
			x.setName();
		return busstops;
	}

	/**
	 * Method to get Trips for a bus Route defined by the Short name from the REST
	 * api
	 * 
	 * @param ShortName - short name for the route
	 * @return List of Trip objects for the route
	 */
	public Trip[] getTripsforRoute(String ShortName) {
		String url = String.format("http://transport.tamu.edu/BusRoutesFeed/api/Route/%s/TimeTable/2017-11-27",
				ShortName);
		RestTemplate restTemplate = new RestTemplate();
		JsonNode nodes = restTemplate.getForObject(url, JsonNode.class);
		Trip[] trips = new Trip[nodes.size()];
		for (int i = 0; i < nodes.size(); i++) {
			trips[i] = new Trip();
			Iterator<Entry<String, JsonNode>> it = nodes.get(i).fields();
			int seq = 0;
			while (it.hasNext()) {

				StopTime st = new StopTime();
				Map.Entry<String, JsonNode> entry = it.next();
				String key = entry.getKey();
				try {
					String Stop = key.substring(36);
					st.setBusStopName(Stop);
					st.setTime(entry.getValue().asText());
					st.setSequence(seq);
					trips[i].addStopTime(st);
					seq++;
				} catch (Exception e) {
					System.out.println(key);
				}

			}
		}
		return trips;

	}

	/**
	 * Method to get real time Data for the bus route from REST API
	 * 
	 * @param ShortName - short name for the route
	 * @return List of Buses objects
	 */
	public List<Buses> getRealTimeBusData(String ShortName) {
		String url = String.format("http://transport.tamu.edu/BusRoutesFeed/api/route/%s/buses/mentor", ShortName);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Buses[]> response = restTemplate.getForEntity(url, Buses[].class);
		return Arrays.asList(response.getBody());
	}

	/**
	 * Method to get Converted Latitude and Longitude from GIS RESt API
	 * <p>
	 * Method still under testing-
	 * 
	 * @param url- URL for the GIS API
	 * @return LatLong object
	 */
	public LatLong convertLatLong(String url) {
		// TODO Auto-generated method stub
		RestTemplate restTemplate = new RestTemplate();
		JSONObject response = restTemplate.getForObject(url, JSONObject.class);
		// JSONObject map = response.getBody();
		String temp = response.getJSONArray("strings").get(0).toString();
		LatLong templ = new LatLong();

		// temp = response.getBody();
		System.out.println(temp);
		return templ;
	}

}
