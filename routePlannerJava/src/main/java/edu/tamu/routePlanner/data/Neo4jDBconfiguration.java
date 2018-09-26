package edu.tamu.routePlanner.data;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.neo4j.ogm.config.*;
import org.neo4j.ogm.session.SessionFactory;

@Configuration
@EnableNeo4jRepositories("edu.tamu.routePlanner.domain")
@EnableTransactionManagement
@ComponentScan("edu.tamu.routePlanner.domain")
public class Neo4jDBconfiguration {
	@Bean
	public SessionFactory sessionFactory() {
		return new SessionFactory(configuration(), "edu.tamu.routePlanner.domain");

	}

	@Bean
	public org.neo4j.ogm.config.Configuration configuration() {
		ConfigurationSource properties = new ClasspathConfigurationSource("dbconfig.properties");
		org.neo4j.ogm.config.Configuration configuration = new org.neo4j.ogm.config.Configuration.Builder(properties)
				.build();
		return configuration;
	}

	@Bean
	public Neo4jTransactionManager transactionManager() {
		return new Neo4jTransactionManager(sessionFactory());
	}

	public SessionFactory getSessionFactory() {
		return new SessionFactory("edu.tamu.routePlanner.domain");
	}

}
