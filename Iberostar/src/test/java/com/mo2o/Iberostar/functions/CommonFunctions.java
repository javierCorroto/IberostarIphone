package com.mo2o.Iberostar.functions;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.apache.log4j.Priority;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mo2o.Iberostar.testcases.TestBase;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

public class CommonFunctions extends TestBase{

	public AppiumDriver <MobileElement> driver;
	private String p;
	private int intP;
	private int intTrans;

	public CommonFunctions(AppiumDriver <MobileElement> driver){
		this.driver = driver;
		loadElements();
	}

	public void loadElements(){
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	static By sideMenuListViewDroid = By.id("com.mo2o.iberostar:id/drawer_list");
	static By sideMenuListItemDroid = By.className("android.widget.TextView");
	static By iberostarImagenDroid = By.id("com.mo2o.iberostar:id/imageViewBanner");
	static By iberostarImageniOS = By.id("com.mo2o.iberostar:id/imageViewBanner");

	@AndroidFindBy(id="com.mo2o.iberostar:id/imageViewMenu")
	@iOSFindBy(className="")
	public static WebElement buttonSideMenu; 

	//Inform keypad in Android devices
	public static void insertKeyPad(AppiumDriver<MobileElement> driver, String excelInput){
		int  numberExcelInput = Integer.parseInt(excelInput);
		String insertNumber = String.valueOf(numberExcelInput);
		for(int i = 0; i < insertNumber.length(); i++) {
			int j = Character.digit(insertNumber.charAt(i), 10);
			switch (j) {
			case 0:  j = 7;
			break;
			case 1:  j = 8;
			break;
			case 2:  j = 9;
			break;
			case 3:  j = 10;
			break;
			case 4:  j = 11;
			break;
			case 5:  j = 12;
			break;
			case 6:  j = 13;
			break;
			case 7:  j = 14;
			break;
			case 8:  j = 15;
			break;
			case 9:  j = 16;
			break;
			default: j = 0;
			}
			((AndroidDriver <MobileElement>) driver).sendKeyEvent(j);
		}
	}

	public static void hideKeyboardAndroid(AppiumDriver <MobileElement> driver){
		try{
			driver.hideKeyboard();
		}catch (Exception e){
			System.out.println(e);
		}
	}

	public static void waitForElements(AppiumDriver <MobileElement> driver, By droid, By iOS){
		WebDriverWait wait = new WebDriverWait(driver, 35);

		if (OSExecuting.toLowerCase().contains("android")){
			wait.until(ExpectedConditions.presenceOfElementLocated(droid));
		}
		if (OSExecuting.toLowerCase().contains("ios")){
			wait.until(ExpectedConditions.presenceOfElementLocated(iOS));
		}
	}


	public static void sideMenuButtonClick(AppiumDriver <MobileElement> driver){
		buttonSideMenu.click();
	}

	public static void seleccionMenuLateral(AppiumDriver <MobileElement> driver, String menuLateralSeleccion ){
		waitForElements(driver, iberostarImagenDroid, iberostarImageniOS);
		buttonSideMenu.click();
		WebElement list = driver.findElement(sideMenuListViewDroid);
		List<WebElement> itemsList = list.findElements(sideMenuListItemDroid);

		for (WebElement element : itemsList){
			if (element.getText().toLowerCase().contains(menuLateralSeleccion)){
				element.click();
				break;
			}
		}
	}

	public void swipeCalendario (MobileElement element) {
		//get middle x
		int leftX = element.getLocation().getX();
		int rightX = leftX + element.getSize().getWidth();
		int middleX = (rightX-leftX) / 2;
		// get the Y coordinate of the upper left corner of the element, then subtract the height to get the lowest Y value of the element
		int upperY = element.getLocation().getY();
		int lowery = (element.getSize().height - 20) + upperY;
		driver.swipe(middleX , lowery, middleX, upperY, 4000);
	}

	public void swipe (MobileElement textTipoHabitacion, MobileElement lay) {
		//get middle x
		try {
			int leftX = lay.getLocation().getX();
			int rightX = leftX + lay.getSize().getWidth();
			int middleX = (rightX-leftX)/2;
			// get the Y coordinate of the upper left corner of the element, then subtract the height to get the lowest Y value of the element
			int upperY = lay.getLocation().getY();
			int lowery = (lay.getSize().height - 20) + upperY;
			int middleY = lowery - upperY;
			int swipeToY = (int) (middleY *1.5);

			while (isElementPresent(textTipoHabitacion) == false){
				driver.swipe(middleX , lowery, middleX, swipeToY, 4000);
			}
			intP = ((Locatable)textTipoHabitacion).getCoordinates().onPage().getY();
			while ( intP > middleY ){
				intP = ((Locatable)textTipoHabitacion).getCoordinates().onPage().getY();
				driver.swipe(middleX , lowery, middleX, swipeToY, 4000);
			}
		} catch
		(Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Ha habido errror en selecciOn de Entrada "+ e);
		}
	}

	public boolean isElementPresent(MobileElement textTipoHabitacion ){
		try {
			//driver.findElement(textTipoHabitacion);
			if (textTipoHabitacion.isDisplayed()){
				return true;
			}
		}
		catch (Exception e){

		}
		return false;
	}
}














