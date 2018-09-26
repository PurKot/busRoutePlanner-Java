package edu.tamu.routePlanner.data;

import java.io.IOException;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.exceptions.ServiceUnavailableException;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import edu.tamu.routePlanner.Configfile;

// Class to handle Neo4j connections
public class Neo4jDBOperation implements AutoCloseable {
	public Session ogmsession;

	// this is the connector to the graphDB
	public final Driver driver;
	Configfile dbfile;
	private String dburl;
	private String dbuserid;
	private String dbpasswrd;

	// constructor for bolt driver session
	public Neo4jDBOperation() throws ServiceUnavailableException {
		getconfigparams();
		driver = GraphDatabase.driver(dburl, AuthTokens.basic(dbuserid, dbpasswrd));
		try (org.neo4j.driver.v1.Session dsession = driver.session()) {
			System.out.println("DB connected successfully. Can open a session");
		} catch (Exception e) {
			System.out.println("Error starting a session. Check DB connection. Exception details:  " + e.getMessage());
		}
	}

	private void getconfigparams() {
		Configfile dbfile = new Configfile();
		try {
			this.dburl = dbfile.GetConfigvalue("Neo4jDBurl");
			this.dbuserid = dbfile.GetConfigvalue("dbuser");
			this.dbpasswrd = dbfile.GetConfigvalue("dbpaswrd");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// override the constructor for OGM session- Will use this for bulk load.
	public Neo4jDBOperation(Neo4jDBconfiguration config) {
		SessionFactory factory = config.sessionFactory();
		ogmsession = factory.openSession();
		driver = null;
	}

	// will close the connection if the program throws any exception
	public void close() throws Exception {
		driver.close();
		System.out.println("Successfully Closed DB connection and session");
	}

	StatementResult Getresults(String query) {

		try (org.neo4j.driver.v1.Session bsession = driver.session()) {
			System.out.println("Session opened successfully to read");
			return bsession.run(query);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}