package com.mo2o.Iberostar.testcases;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.TestNG;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.mo2o.Iberostar.functions.HabitacionesScreen;
import com.mo2o.Iberostar.functions.HotelesScreen;
import com.mo2o.Iberostar.functions.RegimenHabitacionScreen;
import com.mo2o.Iberostar.utils.Constants;


public class TestBase {
	public static String iOS = "iOS";
	public static String android =  "Android";
	public static AppiumDriver <MobileElement> driver;
	public static WebDriverWait waitVar = null;
	public HotelesScreen screenSelectHotel;
	public HabitacionesScreen selectHabitacionesFechas;
	public RegimenHabitacionScreen regimenHabitacion;
	protected static String OSExecuting = null;
	private boolean numEjecuciones = false;
	private String OS = null;

	DesiredCapabilities capabilities = new DesiredCapabilities();

	@BeforeSuite
	public String setDriver(){
		if (numEjecuciones == false){
			//			String input = scanner.nextLine();
			//			System.out.println( "input = " + input );
			OS = "Android";
		}
		return OS;
	}

	@BeforeTest
	@Parameters("Device_ID")
	public void createDriver(String Device_ID) throws Exception {
		if(OS.equalsIgnoreCase("iOS")){
			returnIOSDriver();
			OSExecuting = iOS;
		}
		if(OS.equalsIgnoreCase("android")){
			returnAndroidDriver(Device_ID);
			OSExecuting = android;
		}
	}

	public void returnIOSDriver() throws MalformedURLException{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities
		.setCapability(
				"app",
				"/Users/Apple/Library/Developer/Xcode/DerivedData/PayCloud-ffksktfojvhzadaztzhtikjpdhdv/Build/Products/Debug-iphonesimulator/PayCloud.app");
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "iOS");
		capabilities.setCapability("platformVersion", "7.1");
		capabilities.setCapability("platformName", "iOS");
		capabilities.setCapability("deviceName", "iPhone Simulator");
		//		driver = new IOSDriver <MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"),
		//				capabilities);

		waitVar = new WebDriverWait(driver, 90);
		System.out.println("iOS started");
	}

	public void returnAndroidDriver(String Device_ID) throws MalformedURLException, InterruptedException{
		try {
			Thread.sleep(5000);
			if (Device_ID.equalsIgnoreCase(Constants.SAMSUNG_S5)){
				setcapabilitiesAndroid(Constants.APP_ACTIVITY, Constants.BASE_PKG,Device_ID);
			}
			if (Device_ID.equalsIgnoreCase(Constants.LG_BLANCO)){
				setcapabilitiesAndroid(Constants.APP_ACTIVITY, Constants.BASE_PKG,Device_ID);
			}
			if (Device_ID.equalsIgnoreCase(Constants.SAMSUNG_GALAXY6)){
				setcapabilitiesAndroid("com.mo2o.iberostar", "com.mo2o.iberostar.activities.HomeActivity",Device_ID);
			}
			if (Device_ID.equalsIgnoreCase(Constants.HUAWEIP8)){
				setcapabilitiesAndroid("com.mo2o.iberostar", "com.mo2o.iberostar.activities.HomeActivity",Device_ID);
			}
			if (Device_ID.equalsIgnoreCase(Constants.SAMSUNG_S3)){
				setcapabilitiesAndroid("com.mo2o.iberostar", "com.mo2o.iberostar.activities.HomeActivity",Device_ID);
			}
			//driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4444/wd/hub"), capabilities);
			driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());	
		}

	}

	public void setcapabilitiesAndroid(String apppackage,
			String appActivity,String device) {
		capabilities.setCapability("deviceName", device);
		capabilities.setCapability("automationName", "Appium");
		capabilities.setCapability("platformName", "ANDROID");
		capabilities.setCapability("androidPackage", apppackage);
		capabilities.setCapability("appActivity", appActivity);
		capabilities.setCapability("udid", device);
		//Actions builder = new Actions(driver);
	}


	@AfterTest
	public void tearDown() throws Exception {
		//Close the app and simulator
		System.out.println("Inside quit");
		driver.quit();
	}
	public String[][] getTableArray(String xlFilePath, String sheetName,
			String tableName, Method m) throws Exception {

		String[][] tabArray = null;
		Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
		Sheet sheet = workbook.getSheet(sheetName);
		int startRow, startCol, endRow, endCol, ci, cj;
		Cell tableStart = sheet.findCell(tableName);
		startRow = tableStart.getRow();
		startCol = tableStart.getColumn();

		Cell tableEnd = sheet.findCell(tableName, startCol + 1, startRow + 1,
				100, 64000, false);

		endRow = tableEnd.getRow();
		endCol = tableEnd.getColumn();
		System.out.println("startRow=" + startRow + ", endRow=" + endRow + ", "
				+ "startCol=" + startCol + ", endCol=" + endCol);
		tabArray = new String[endRow - startRow - 1][endCol - startCol - 1];
		ci = 0;
		for (int i = startRow + 1; i < endRow; i++, ci++) {
			cj = 0;
			boolean continuarLoop = false;
			for (int j = startCol + 1; j < endCol; j++, cj++) {			
				tabArray[ci][cj] = sheet.getCell(j, i).getContents();
				if (continuarLoop == true) continue;
				String contenidoCelda = sheet.getCell(j, i).getContents();
				String metodo = m.toString();  
				boolean tcID= metodo.contains(contenidoCelda);
				if (tcID==true){
					continuarLoop =true;
				}else{
					break;
				}
			}
		}
		return (tabArray);
	}

	public static  boolean executeTestCase(String testCase, Method m){
		boolean execute = false;
		String testcase = testCase.toLowerCase();
		String metodo = m.toString().toLowerCase(); 
		boolean testCaseID= metodo.contains(testcase);

		if(testCaseID ==  true){
			execute = true;
		}
		return execute;
	}
}

