package com.mo2o.Iberostar.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadPropertyFile {

	protected Properties prop = null;
	protected InputStream input = new FileInputStream (System.getProperty("user.dir") 
			+ "/src/test/resources/properties/data.txt");;

			public ReadPropertyFile() throws IOException {
				prop = new Properties();
				prop.load(input);
			}
			public String txtAreaPersonal(){
				return prop.getProperty("areal.personal");
			}
			public String nexus5Rojo(){
				return prop.getProperty("nexus5Rojo");
			}
			public String nexus5Blanco(){
				return prop.getProperty("nexus5Blanco");
			}
			public String samsungS5(){
				return prop.getProperty("samsungS5");
			}
			public String samsungNote4(){
				return prop.getProperty("samsungNote4");
			}
			public String appActivity(){
				return prop.getProperty("application.activity");
			}
			public String basePkg(){
				return prop.getProperty("base.pkg");
			}



}
