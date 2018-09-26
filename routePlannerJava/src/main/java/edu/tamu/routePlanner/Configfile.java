package edu.tamu.routePlanner;

import java.io.*;
import java.util.Properties;

//Class for getting config properties from properties file in resources folder
public class Configfile {
	InputStream is = null;
	String propvalue = "";

	public String GetConfigvalue(String parameter) throws IOException {
		Properties configprop = new Properties();
		String filename = "dbconfig.properties";
		try {
			is = getClass().getClassLoader().getResourceAsStream(filename);
			if (is != null) {
				configprop.load(is);
			} else {
				throw new FileNotFoundException("file not found in resources folder");

			}
			propvalue = configprop.getProperty(parameter);
			System.out.println("Retreived " + parameter + " value from config file");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		is.close();
		return propvalue;

	}

}
