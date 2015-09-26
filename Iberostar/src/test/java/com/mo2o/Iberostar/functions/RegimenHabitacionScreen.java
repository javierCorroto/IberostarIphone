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
	static By linearLayoutTipoHabitacionIOS = By.id("");
	static By waitForTxtRegimenReservaDroid = By.id("com.mo2o.iberostar:id/txtHotel");
	static By waitForTxtRegimenReservaIOS = By.id("");
	static By checksTipoHabitacionDroid = By.id("com.mo2o.iberostar:id/checkTipoHab");
	static By textTipoHabitacionDroid = By.id("com.mo2o.iberostar:id/textEleccionHabitacion");
	static By textTipoTarifaDroid = By.id("com.mo2o.iberostar:id/textEleccionTarifa");
	static By linearLayoutTipoTarifaDroid = By.id("com.mo2o.iberostar:id/linearHolderTarifas");
	static By textEurosTipoHabitacionDroid = By.id("com.mo2o.iberostar:id/textViewPrecio");

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

	public double calcularImporteTotal(){
		double importeTotal = eurosTipoHabitacion;
		return importeTotal;
	}
	public RegimenHabitacionScreen selectTipoHabitacion(AppiumDriver<MobileElement> driver) 
			throws InterruptedException {
		try {
			swipeCalendario(scrollView);
			if (isElementPresent(textTipoHabitacionDroid) == true){
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

				List <MobileElement> getTextEurosTipoHabitacion = listaHabitaciones.findElements(textEurosTipoHabitacionDroid);
				for (MobileElement textEuros : getTextEurosTipoHabitacion){
					String monedaEuros = "EUR";
					textEurosTipoHabitacion ++;
					if (textEurosTipoHabitacion == checkBoxSelected){
						System.out.println(textEuros.getText().toString());
						if(textEuros.getText().toLowerCase().contains(monedaEuros.toLowerCase())){
							String stringEuros = textEuros.getText().replaceAll(monedaEuros, "");
							double converStringEurosToDouble = Double.parseDouble(stringEuros);
							this.eurosTipoHabitacion = converStringEurosToDouble;
							break;
						}
					}
				}
			}
			else{ 
				this.eurosTipoHabitacion = 0;
			}
		} catch
		(Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Ha habido errror en selección de Entrada "+ e);
		}
		return this;
	}

	public RegimenHabitacionScreen selectTipoTarifaTarjeta(AppiumDriver<MobileElement> driver) 
			throws InterruptedException {
		try {
			swipeCalendario(scrollView);
			if(isElementPresent(textTipoHabitacionDroid) == true){
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
			}
			else{ 
				this.eurosTipoHabitacion = 0;
			}
		} catch
		(Exception e) {
			// TODO Auto-generated catch blockude
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

