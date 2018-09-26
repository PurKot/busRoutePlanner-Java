package edu.tamu.routePlanner.data;

import java.util.Arrays;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDBOperation {

	public final MongoClient mongoClient;
	public final MongoDatabase realTimeDB;

	MongoDBOperation() {
		MongoCredential auth = MongoCredential.createCredential("istm622", "admin", "istm622".toCharArray());
		mongoClient = new MongoClient(new ServerAddress("localhost", 27017), Arrays.asList(auth));
		realTimeDB = mongoClient.getDatabase("admin");

	}

	/*
	 * public MongoCollection<?> createRouteCollection(String ShortName) {
	 * MongoCollection routeCollection = realTimeDB.getCollection(ShortName); return
	 * routeCollection; }
	 */

}
