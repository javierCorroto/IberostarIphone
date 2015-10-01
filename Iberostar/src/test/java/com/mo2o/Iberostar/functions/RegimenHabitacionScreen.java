package com.mo2o.Iberostar.functions;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.mo2o.Iberostar.testcases.TestBase;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import io.appium.java_client.FindsByAndroidUIAutomator;

import org.testng.Assert;

public class RegimenHabitacionScreen extends CommonFunctions{

	public RegimenHabitacionScreen(AppiumDriver<MobileElement> driver) {
		super(driver);
		this.driver = driver;
	}

	static By linearLayoutTipoHabitacionDroid = By.id("com.mo2o.iberostar:id/linearListTiposHabs");
	static By linearLayoutTipoTarfiaDroid = By.id("com.mo2o.iberostar:id/linearTipoTarifa");
	static By linearLayoutTipoHabitacionIOS = By.id("");
	static By waitForTxtRegimenReservaDroid = By.id("com.mo2o.iberostar:id/txtHotel");
	static By waitForTxtRegimenReservaIOS = By.id("");
	static By checksTipoHabitacionDroid = By.id("com.mo2o.iberostar:id/checkTipoHab");
	static By checksTipoTarifaDroid = By.id("com.mo2o.iberostar:id/checkTipoHab");
	static By textTipoHabitacionDroid = By.id("com.mo2o.iberostar:id/textEleccionHabitacion");
	static By linearLayoutTipoTarifaDroid = By.id("com.mo2o.iberostar:id/linearHolderTarifas");
	static By textEurosDroid = By.id("com.mo2o.iberostar:id/textViewPrecio");
	static By textTipoTarifaDroid = By.id("com.mo2o.iberostar:id/textViewNameTipoTarifa");
	static By relativaLayOutTipoTarifaDroid = By.id("com.mo2o.iberostar:id/relativeTipoTarifa");
	static By checkTipoTarifaDroid = By.id("com.mo2o.iberostar:id/checkBoxTipoTarifa");

	@AndroidFindBy(id="com.mo2o.iberostar:id/txtHotel")
	@iOSFindBy(id="")
	private static MobileElement txtRegimenHabitacion; 
	@AndroidFindBy(id="com.mo2o.iberostar:id/scrollViewReservaData")
	@iOSFindBy(id="")
	private static MobileElement scrollView; 
	@AndroidFindBy(id="com.mo2o.iberostar:id/linearTextEleccionTipoHab")
	@iOSFindBy(id="")
	private static MobileElement listTipoHabitacion; 
	@AndroidFindBy(id="com.mo2o.iberostar:id/linearListTiposHabs")
	@iOSFindBy(id="")
	private static MobileElement listaChecksTipoHabitacion; 
	@AndroidFindBy(id="com.mo2o.iberostar:id/checkTipoHab")
	@iOSFindBy(id="")
	private static MobileElement checksTipoHabitacion; 
	@AndroidFindBy(id="com.mo2o.iberostar:id/textEleccionHabitacion")
	@iOSFindBy(id="")
	private static MobileElement textTipoHabitacion; 
	@AndroidFindBy(id="com.mo2o.iberostar:id/textEleccionTarifa")
	@iOSFindBy(id="")
	private static MobileElement textTipoTarifa; 

	private double eurosTipoHabitacion;
	private double eurosTipoTarifa;
	private String monedaEuros = "EUR";

	public double calcularImporteTotal(){
		double importeTotal = eurosTipoHabitacion + eurosTipoTarifa;
		return importeTotal;
	}
	public RegimenHabitacionScreen selectTipoHabitacion(AppiumDriver<MobileElement> driver) 
			throws InterruptedException {
		try {
			waitForElements(driver, waitForTxtRegimenReservaDroid, waitForTxtRegimenReservaIOS);
			swipe(textTipoHabitacion, scrollView);
			if (isElementPresent(textTipoHabitacion) == true){
				int checkBoxSelected = 0;
				int textEurosTipoHabitacion = 0;
				MobileElement listaHabitaciones = driver.findElement(linearLayoutTipoHabitacionDroid);
				List <MobileElement> checksBoxTipoHabitacion = listaHabitaciones.findElements(checksTipoHabitacionDroid);
				for (WebElement checkBox : checksBoxTipoHabitacion ){
					checkBoxSelected ++;
					String checkEnabled = checkBox.getAttribute("checked");
					if (checkEnabled.equalsIgnoreCase("false")){
						checkBox.click();
						break;
					}
				}

				List <MobileElement> getTextEurosTipoHabitacion = listaHabitaciones.findElements(textEurosDroid);
				for (MobileElement textEuros : getTextEurosTipoHabitacion){
					textEurosTipoHabitacion ++;
					if (textEurosTipoHabitacion == checkBoxSelected){
						System.out.println(textEuros.getText().toString());
						if(textEuros.getText().toLowerCase().contains(monedaEuros.toLowerCase())){
							String stringEuros = textEuros.getText().replaceAll(monedaEuros, "");
							double converStringEurosToDouble = Double.parseDouble(stringEuros);
							eurosTipoHabitacion = converStringEurosToDouble;
							break;
						}
					}
				}
			}
			else{ 
				eurosTipoHabitacion = 0;
			}
		} catch
		(Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Ha habido errror en selección de Entrada "+ e);
		}
		return this;
	}
//
//	public RegimenHabitacionScreen selectTipoTarifa(AppiumDriver<MobileElement> driver) 
//			throws InterruptedException {
//		try {
//			swipeCalendario(scrollView);
//			if (isElementPresent(textTipoTarifaDroid) == true){
//				int i = 0;
//				int j = 0;
//				String tarifa = "Flexible - SA";
//				String lowerCaseTarifa = tarifa.toLowerCase();
//				MobileElement listaLinearLayoutsTipoTarifa = driver.findElement(linearLayoutTipoTarifaDroid);
//				int sizelistaLinearLayoutsTipoTarifa = listaLinearLayoutsTipoTarifa.findElements(relativaLayOutTipoTarifaDroid).size();
//				outerlopp:
//					for (; i < sizelistaLinearLayoutsTipoTarifa;i++){
//						MobileElement getIndex = listaLinearLayoutsTipoTarifa.findElements(relativaLayOutTipoTarifaDroid).get(j);
//						List <MobileElement> textTipoTarifa = getIndex.findElements(textTipoTarifaDroid);
//						j++;
//						for (MobileElement tipoTarifa : textTipoTarifa){
//							System.out.println("El texto de la tarifa " + tipoTarifa.getText());
//							if (tipoTarifa.getText().toLowerCase().equals(lowerCaseTarifa)){
//								List <MobileElement> checksTipoTarifa = getIndex.findElements(checkTipoTarifaDroid);
//								for (MobileElement checkTipoTarifa : checksTipoTarifa){
//									String estadoChecked = checkTipoTarifa.getAttribute("checked");
//									System.out.println("El estado del check es " + estadoChecked);
//									if (checkTipoTarifa.getAttribute("checked").equals("false")){
//										checkTipoTarifa.click();
//										List <MobileElement> precioTipoTarifa = getIndex.findElements(textEurosDroid);
//										for (MobileElement precioTarifa : precioTipoTarifa){
//											System.out.println("El precio es " + precioTarifa.getText());
//											precioTarifa.getText();
//											String stringEuros = precioTarifa.getText().replaceAll(monedaEuros, "");
//											double converStringEurosToDouble = Double.parseDouble(stringEuros);
//											eurosTipoTarifa = converStringEurosToDouble;
//											break outerlopp; 
//										}
//									}else if (checkTipoTarifa.getAttribute("checked").equals("true")){ 
//										List <MobileElement> precioTipoTarifa = getIndex.findElements(textEurosDroid);
//										for (MobileElement precioTarifa : precioTipoTarifa){
//											System.out.println("El precio es " + precioTarifa.getText());
//											precioTarifa.getText();
//											String stringEuros = precioTarifa.getText().replaceAll(monedaEuros, "");
//											double converStringEurosToDouble = Double.parseDouble(stringEuros);
//											eurosTipoTarifa = converStringEurosToDouble;
//											break outerlopp;
//										}
//									}
//								}
//							}
//						}
//					}
//			}
//
//			else{ 
//				eurosTipoTarifa = 0;
//			}
//		} catch
//		(Exception e) {
//			// TODO Auto-generated catch block
//			System.out.println("Ha habido errror en selección de Entrada "+ e);
//		}
//		return this;
//	}



	
	
	public RegimenHabitacionScreen selectTipo(AppiumDriver<MobileElement> driver) 
			throws InterruptedException {
		try {
			swipe(textTipoHabitacion, scrollView);
			
		} catch
		(Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Ha habido errror en selección de Entrada "+ e);
		}
		return this;
}
	
	
	
	
	
	
	public RegimenHabitacionScreen selectDiaSalida(AppiumDriver<MobileElement> driver, String mesSalida, String diaSalida) 
			throws InterruptedException {
		try {

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Ha habido errror en selección de Entrada "+ e);




		}
		return this;
	}

	public boolean verificarPresenciaRegimenHabitacionTxt(){
		waitForElements(driver, waitForTxtRegimenReservaDroid, waitForTxtRegimenReservaIOS);
		txtRegimenHabitacion.isDisplayed();
		return true;	
	}


}

