package com.mo2o.Iberostar.testcases;


import io.appium.java_client.MobileElement;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;

public class DummyCode {



	public static void main(String[] args) throws MalformedURLException {


		DesiredCapabilities capabilites = new DesiredCapabilities();
		capabilites.setCapability("device", "Android");
		capabilites.setCapability("deviceName", "10.0.0.6:5555");
		capabilites.setCapability("platformVersion", "4.4.2");
		capabilites.setCapability("platformName", "Android");
		capabilites.setCapability("appPackage", "com.android.contacts");
		capabilites.setCapability("appActivity", "com.android.contacts.activities.PeopleActivity");

		
		}
}










