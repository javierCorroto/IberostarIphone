package com.mo2o.Iberostar.utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

public class CommonUtils {

	private static final String DEVICE_NAME = null;
	private static Properties prop = new Properties();
	public static int EXPLICIT_WAIT_TIME;
	public static int IMPLICIT_WAIT_TIME;
	public static int DEFAULT_WAIT_TIME;
	public static String APPLICATION_NAME;
	public static String BASE_PKG;
	public static String APP_ACTIVITY;
	public static String APP_PASSWORD;
	private static String APPIUM_PORT;
	public static String AUTOMATION_INSTRUMENTATION;
	public static String BROWSER_NAME;
	public static String PLATFORM_NAME;
	public static String NEW_COMMAND_TIMEOUT;
	public static String PLATFORM_VERSION;
	public static String DEVICE_READY_TIMEOUT;
	public static String NEXUS_5; 
	public static String AREA_PERSONAL;
	private static DesiredCapabilities capabilities = new DesiredCapabilities();
	private static URL serverUrl;
	private static AndroidDriver driver;

	public static void loadConfigProp(String propertyFileName) throws IOException{

		FileInputStream fis = new FileInputStream (System.getProperty("user.dir") 
				+ "/src/test/resources/properties/carrefour.properties");
		EXPLICIT_WAIT_TIME = Integer.parseInt(prop.getProperty("explicit.wait"));
		IMPLICIT_WAIT_TIME = Integer.parseInt(prop.getProperty("implicit.wait"));
		DEFAULT_WAIT_TIME = Integer.parseInt(prop.getProperty("default.wait"));
		APPLICATION_NAME = prop.getProperty("application.path");
		BASE_PKG = prop.getProperty("base.pkg");
		APP_ACTIVITY = prop.getProperty("application.activity");
		APPIUM_PORT = prop.getProperty("appium.server.port");
		AUTOMATION_INSTRUMENTATION = prop.getProperty("automation.instrumentation");
		BROWSER_NAME = prop.getProperty("browser.name");
		PLATFORM_NAME = prop.getProperty("platform.name");
		NEW_COMMAND_TIMEOUT = prop.getProperty("new.command.timeout");
		PLATFORM_VERSION = prop.getProperty("platform.version");
		DEVICE_READY_TIMEOUT = prop.getProperty("device.ready.timeout");	
	}

	public static void setCapabilities(){
		capabilities.setCapability(MobileCapabilityType.BROWSER_NAME,
				CommonUtils.BROWSER_NAME);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,
				CommonUtils.PLATFORM_VERSION);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,
				CommonUtils.PLATFORM_NAME);
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,
				CommonUtils.DEVICE_NAME);
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,
				CommonUtils.AUTOMATION_INSTRUMENTATION);
		capabilities.setCapability(MobileCapabilityType.APP,
				CommonUtils.APPLICATION_NAME);
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,
				CommonUtils.NEW_COMMAND_TIMEOUT);
		capabilities.setCapability(MobileCapabilityType.APP_ACTIVITY,
				CommonUtils.APP_ACTIVITY);
		capabilities.setCapability(MobileCapabilityType.APP_PACKAGE,
				CommonUtils.BASE_PKG);
	}

	public static  AndroidDriver GetDriver () throws MalformedURLException{
		serverUrl = new URL("http://localhost:" + APPIUM_PORT + "wd/hub");
		driver = new AndroidDriver(serverUrl,capabilities);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		return driver;
	}

	public static void setcapabilities(String appackage, String appActivity,String device) {
		capabilities.setCapability("deviceName", device);
		capabilities.setCapability("automationName", "Appium");
		capabilities.setCapability("platformName", "ANDROID");
		capabilities.setCapability("androidPackage", appackage);
		capabilities.setCapability("appActivity", appActivity);
		System.out.println("Ha entrado en capabalities");
		capabilities.setCapability("udid", device);
	}


}
