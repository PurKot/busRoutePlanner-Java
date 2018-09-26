package edu.tamu.routePlanner.ui;

import java.net.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.json.*;
import org.neo4j.driver.v1.Record;
import edu.tamu.routePlanner.data.Neo4jBusStopRepository;
import edu.tamu.routePlanner.domain.BusStopRepository;

/**
 * Class which is responsible to display the BusStops on the UI. It provides
 * functions to populate nearest bus stops using GIS and Geocoder APIs.
 */
public class PopulateBusStops {
	static double[] dlatitude = new double[200];
	static double[] dlongitude = new double[200];
	public final double latlong[][];
	static double[] userLatLong = new double[2];

	static int r = 0;
	static int p = 0;
	static int n = 0;
	public final List<Record> busStopLatLongList;
	public final BusStopRepository rep;

	// Initializes the class and populates the list with converted Latitudes and
	// Longitudes to the DD formal.
	PopulateBusStops() {
		rep = new Neo4jBusStopRepository();
		busStopLatLongList = rep.busStopLatLong();// Get BusStops names, latitudes and Longitudes.
		latlong = getConvLatLong(busStopLatLongList); // Convert BusStop Latitudes and Longitudes from SPC to DD format.

	}

	/**
	 * Function to get top 5 nearest BusStops from the user entered address
	 * 
	 * @param address - string with user entered address
	 * @return array of 5 nearest bus stops.
	 */
	public String[] getNearestBusStops(String address) {

		address = address.replaceAll(" ", "%%20");
		double userloc[] = getUserAddLatLong(address);
		Map<Double, String> distBusStopMap = new HashMap<Double, String>();
		Double tempDist;
		String tempBS;
		for (int i = 0; i < busStopLatLongList.size(); i++) {

			tempDist = calculateDistanceInKilometer(userloc[0], userloc[1], latlong[0][i], latlong[1][i]);
			tempBS = busStopLatLongList.get(i).get("Name").toString();
			distBusStopMap.put(tempDist, tempBS);
		}
		Map<Double, String> sortedDistBusStopMap = new HashMap<Double, String>();
		sortedDistBusStopMap = new TreeMap<Double, String>(distBusStopMap);
		// return top 5 nearest stops
		String[] nearestStopsList = new String[5];
		Set<Entry<Double, String>> entry = sortedDistBusStopMap.entrySet();
		Iterator<Entry<Double, String>> it = entry.iterator();

		for (int i = 0; i < 5; i++) {
			nearestStopsList[i] = it.next().getValue();
		}

		return (nearestStopsList);
	}

	/**
	 * Function responsible to convert Latitudes and Longitudes from SPS to DD
	 * format uses GIS TAMU REST service
	 * 
	 * @param busStoplatlang- list of Records with Name, Latitude and Longitude of
	 *        Bus stops
	 * @return double array of Latitudes and Longitudes
	 */
	public static double[][] getConvLatLong(List<Record> busStoplatlang) {

		try {
			int i = 0;
			for (Record obj : busStoplatlang) {
				Double lat = obj.get("Latitude").asDouble();
				Double longt = obj.get("Longitude").asDouble();
				String url = String.format(
						"http://gis.tamu.edu/arcgis/rest/services/Utilities/Geometry/GeometryServer/toGeoCoordinateString?sr=3857&coordinates=%%5B%%5B%s%%2C%s%%5D%%5D&conversionType=DD&conversionMode=mgrsDefault&numOfDigits=&rounding=false&addSpaces=false&f=json",
						longt.toString(), lat.toString());

				URL httpurl = new URL(url);
				URLConnection urlConnection = httpurl.openConnection();
				BufferedReader inputBR = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
				String inputLine;
				String out = null;
				while ((inputLine = inputBR.readLine()) != null) {
					out = inputLine;
				}
				inputBR.close();
				JSONObject de = new JSONObject(out);
				String m = de.getJSONArray("strings").get(0).toString();
				int j = m.indexOf(" ");
				int y = m.length();
				String longitude = m.substring(j + 2, y - 1);
				String latitude = m.substring(0, j - 1);
				dlatitude[i] = Double.parseDouble(latitude);
				dlongitude[i] = Double.parseDouble(longitude) * -1;
				i++;
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		double latlong[][] = { dlatitude, dlongitude };
		return latlong;
	}

	/**
	 * Function to get Array of Latitude and Longitude of User entered address Uses
	 * Google Geocode API.
	 * 
	 * @param Address - string
	 * @return array of latitude (index-0 of the array) and longitude (index-1 of
	 *         the array) in decimals
	 */
	public static double[] getUserAddLatLong(String Address) {
		String response[] = new String[200];
		try {
			String Mainaddress = String.format(
					"https://maps.googleapis.com/maps/api/geocode/json?address=%s&key=AIzaSyAhcJpVafbjhO70dBvkFQ34MFvUFUob_8g",
					Address);
			URL mainURL = new URL(Mainaddress);
			URLConnection urlConn = mainURL.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
			String inputLine;
			int w = 0;
			int b = 0;
			int z = 0;
			String g = "location";
			while ((inputLine = in.readLine()) != null) {
				response[b] = inputLine;
				if (z != 1) {
					if (response[b].contains(g)) {
						w = b;
						z++;
					}
				}
				b++;
			}
			response[w + 1] = response[w + 1].replaceFirst("\"lat\"", "");
			response[w + 1] = response[w + 1].replaceFirst(":", "");
			response[w + 1] = response[w + 1].replaceFirst(",", "").trim();
			response[w + 2] = response[w + 2].replaceFirst("\"lng\"", "");
			response[w + 2] = response[w + 2].replaceFirst(":", "");
			response[w + 2] = response[w + 2].replaceFirst(",", "").trim();
			double userlat = Double.parseDouble(response[w + 1]);
			double userlong = Double.parseDouble(response[w + 2]);
			in.close();
			userLatLong[0] = userlat;
			userLatLong[1] = userlong;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return userLatLong;
	}

	/**
	 * Function to calculate distance in Kilometers between the User provided
	 * co-ordinates and Venue co-ordinates
	 * 
	 * @param userLat  - from Latitude in decimal
	 * @param userLng  - from Longitude in decimal
	 * @param venueLat - to Latitude in decimal
	 * @param venueLng - to Longitude in decimal
	 * @return the distance between user co-ordinates and Venue co-ordinates in
	 *         decimal value
	 */

	public static double calculateDistanceInKilometer(double userLat, double userLng, double venueLat,
			double venueLng) {
		final double AVERAGE_RADIUS_OF_EARTH_KM = 6371;
		double latDistance = Math.toRadians(userLat - venueLat);
		double lngDistance = Math.toRadians(userLng - venueLng);

		double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(userLat))
				* Math.cos(Math.toRadians(venueLat)) * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);

		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		return ((AVERAGE_RADIUS_OF_EARTH_KM) * c);
	}

}
