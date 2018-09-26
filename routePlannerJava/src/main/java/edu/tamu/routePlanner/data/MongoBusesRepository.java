package edu.tamu.routePlanner.data;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

import edu.tamu.routePlanner.domain.Buses;

public class MongoBusesRepository {

	public Document getRealTimeBusObject(Buses bus) {

		BasicDBObject dbObj = new BasicDBObject();
		Document docBuilder = new Document();
		docBuilder.append("test", dbObj);
		docBuilder.append("Name", bus.CurrentWork.Route.getName());
		docBuilder.append("Name", bus.CurrentWork.Route.getRouteNumber());
		docBuilder.append("Key", bus.CurrentWork.Route.getKey());
		Document docgps = new Document();
		docgps.append("Date", bus.GPS.getDate());
		docgps.append("Lat", bus.GPS.getLat());
		docgps.append("Long", bus.GPS.getLong());
		docgps.append("Lat", bus.GPS.getLat());
		docBuilder.append("GPS", docgps);
		Document docapc = new Document();
		docapc.append("PassengerCapacity", bus.APC.getPassengerCapacity());
		docapc.append("TotalPassenger", bus.APC.getTotalPassenger());
		docBuilder.append("APC", docapc);
		Document docNextStop = new Document();
		docNextStop.append("Key", bus.NextStops[0].getKey());
		docNextStop.append("Name", bus.NextStops[0].getName());
		docNextStop.append("guys ", bus.NextStops[0].getScheduledDepartTime());
		docNextStop.append("Lat", bus.NextStops[0].getEstimatedDepartTime());
		docBuilder.append("GPS", docgps);
		docBuilder.append("NextStops", bus.NextStops);
		return docBuilder;

	}
}
