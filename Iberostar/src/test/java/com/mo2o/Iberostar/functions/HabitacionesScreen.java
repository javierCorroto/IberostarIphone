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

public class HabitacionesScreen extends CommonFunctions{

	public HabitacionesScreen(AppiumDriver<MobileElement> driver) {
		super(driver);
		this.driver = driver;
	}
	static By contenedorListasDroid = By.id("com.mo2o.iberostar:id/layoutContainer");
	static By contenedorListasIOS= By.id("");
	static By itemsContenedorListasDroid = By.className("android.widget.Button");
	static By itemsContenedorListasIOS= By.id("");
	static By waitHabitacionesDroid = By.className("com.mo2o.iberostar:id/layoutContainer");
	static By waitHabitacionesiOS= By.className("");
	static By frameLayOutDroid = By.className("android.widget.FrameLayout");
	static By frameLayOutIOS= By.className("");
	static By linearLayOutDroid = By.className("android.widget.LinearLayout");
	static By listViewentradaSalidaDroid = By.className("android.widget.ListView");
	static By textEntradaSalidaMes = By.id("com.mo2o.iberostar:id/title");
	static By calendarGridDroid = By.id("com.mo2o.iberostar:id/calendar_grid");
	static By calendarText = By.className("android.widget.TextView");
	static By waitForButtonReservarDroid = By.id("com.mo2o.iberostar:id/btnReservar");
	static By waitForButtonReservarIOS = By.id("");

	@AndroidFindBy(id="com.mo2o.iberostar:id/layoutContainer")
	@iOSFindBy(id="")
	public static WebElement contenedorListas; 

	@AndroidFindBy(id="com.mo2o.iberostar:id/buttonMas")
	@iOSFindBy(id="")
	public static WebElement buttonMasNumHabitaciones; 

	@AndroidFindBy(id="com.mo2o.iberostar:id/buttonMenos")
	@iOSFindBy(id="")
	public static WebElement buttonMenosNumHabitaciones; 

	@AndroidFindBy(className="android.widget.ListView")
	@iOSFindBy(id="")
	public static MobileElement listView; 

	@AndroidFindBy(id="com.mo2o.iberostar:id/btnReservar")
	@iOSFindBy(id="")
	public static MobileElement buttonRervar; 

	//Seleccionar hoteles desde menu lateral Hoteles
	public static void selecionarHabitacion(AndroidDriver <MobileElement> driver, String numHabitaciones) throws InterruptedException{
		try {
			//Seleccionar botón habitación
			CommonFunctions.waitForElements(driver, waitHabitacionesDroid, waitHabitacionesiOS);
			//driver.tap(1, listHabitacionDroid, 1);
			String habitaciones = "habi"; 
			WebElement listaContenedor = driver.findElement(contenedorListasDroid);
			List<WebElement> itemslistaContenedor = listaContenedor.findElements(itemsContenedorListasDroid);
			for (WebElement elementContenedor : itemslistaContenedor){
				System.out.println("Boton es " + elementContenedor.getText());
				if (elementContenedor.getText().contains(habitaciones)){
					elementContenedor.click();
					break;
				}
			}

			//TODO - Implementar seleccionar número de habitaciones y adultos niños
			String numeHabitacionesActual = buttonMasNumHabitaciones.getText();
			int intHabitacionesActual = Integer.parseInt(numeHabitacionesActual);
			String numeHabitacionesDeseadas = numHabitaciones;
			int intHabitacionesDeseadas =  Integer.parseInt(numeHabitacionesDeseadas);

			while (intHabitacionesActual > intHabitacionesDeseadas) {
				buttonMenosNumHabitaciones.click();
				System.out.println("Habitaciones deseadas es " + intHabitacionesDeseadas);
				System.out.println("Habitaciones actual es " + intHabitacionesActual);
				if (intHabitacionesActual == intHabitacionesDeseadas){
					break;
				}

			} 

			while (intHabitacionesActual < intHabitacionesDeseadas) {
				buttonMenosNumHabitaciones.click();
				System.out.println("Habitaciones deseadas es " + intHabitacionesDeseadas);
				System.out.println("Habitaciones actual es " + intHabitacionesActual);
				if (intHabitacionesActual == intHabitacionesDeseadas){
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("El error es " + e);
		}
	}

	public HabitacionesScreen selectDiaEntrada(AppiumDriver<MobileElement> driver, String mesEntrada, String diaEntrada)  throws InterruptedException {
		try {
			//Buscar entrada para desplegar el calendario de dia de entrada
			String Entrada = "Entrada"; 
			MobileElement listaContenedorEntrada = driver.findElement(contenedorListasDroid);
			List<MobileElement> itemslistaContenedorEntrada = listaContenedorEntrada.findElements(itemsContenedorListasDroid);
			outerloop:
				for (MobileElement elementContenedor : itemslistaContenedorEntrada){
					//System.out.println("Boton es " + elementContenedor.getText());
					if (elementContenedor.getText().contains(Entrada)){
						elementContenedor.click();
						//Hacer swipe hasta encontrar el mes seleccionado
						MobileElement listViewEntrada = driver.findElement(listViewentradaSalidaDroid);
						List<MobileElement> entradaSalidaMestext = listViewEntrada.findElements(textEntradaSalidaMes);
						for (WebElement mes : entradaSalidaMestext ){
							String mesLowerCase = mesEntrada.toLowerCase(); 
							//System.out.println(mes.getText());
							//Se hace swipe hasta que se encuentre el mes seleccionado
							while (!mes.getText().toLowerCase().contains(mesLowerCase)){
								swipeCalendario(listView);
							}
							//Buscar el dia seleccionado y seleccionarlo
							MobileElement calendarioDiasLista = driver.findElement(calendarGridDroid);
							List<MobileElement> calendarioDiaEntrada = calendarioDiasLista.findElements(calendarText);
							for (WebElement diaEntradaCalendario : calendarioDiaEntrada){
								//System.out.println("El día es " + diaEntradaCalendario.getText());
								//System.out.println("El día es enabled? " + diaEntradaCalendario.getAttribute("enabled"));
								String diaEnabled =  diaEntradaCalendario.getAttribute("enabled");
								if (diaEnabled.equals("true") && diaEntradaCalendario.getText().equals(diaEntrada)){
									diaEntradaCalendario.click();
									elementContenedor.click();
									break outerloop;
								}
							}
						}
					}
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Ha habido errror en selección de Entrada "+ e);
		}
		return this;
	}

	public HabitacionesScreen selectDiaSalida(AppiumDriver<MobileElement> driver, String mesSalida, String diaSalida)  throws InterruptedException {
		try {
			String Salida = "Salida"; 
			MobileElement listaContenedorSalida = driver.findElement(contenedorListasDroid);
			List<MobileElement> itemslistaContenedorSalida = listaContenedorSalida.findElements(itemsContenedorListasDroid);
			outerloop:
				for (MobileElement elementContenedor : itemslistaContenedorSalida){
					//System.out.println("Boton es " + elementContenedor.getText());
					if (elementContenedor.getText().contains(Salida)){
						elementContenedor.click();
						elementContenedor.click();
						//Se busca el mes de salida
						MobileElement listViewEntrada = driver.findElement(listViewentradaSalidaDroid);
						List<MobileElement> entradaSalidaMestext = listViewEntrada.findElements(textEntradaSalidaMes);

						for (WebElement mes : entradaSalidaMestext ){
							String mesSalidaLowerCase = mesSalida.toLowerCase();
							//System.out.println(mes.getText());
							//Se hace swipe hasta que se encuentre el mes seleccionado
							while (!mes.getText().toLowerCase().contains(mesSalidaLowerCase)){
								swipeCalendario(listView);
							}
							MobileElement calendarioDiasLista = driver.findElement(calendarGridDroid);
							List<MobileElement> calendarioDiaSalida = calendarioDiasLista.findElements(calendarText);
							for (WebElement diaSalidaCalendario : calendarioDiaSalida){
								//System.out.println("El día es " + diaSalidaCalendario.getText());
								//System.out.println("El día es enabled? " + diaSalidaCalendario.getAttribute("enabled"));
								String diaEnabled =  diaSalidaCalendario.getAttribute("enabled");
								if (diaEnabled.equals("true") && diaSalidaCalendario.getText().equals(diaSalida)){
									diaSalidaCalendario.click();
									break outerloop;
								}
							}
						}
					}
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Ha habido errror en selección de Entrada "+ e);
		}
		return this;
	}


	public HabitacionesScreen tapReservar(){
		try{
			//waitForElements(driver, waitForButtonReservarDroid, waitForButtonReservarIOS);
			buttonRervar.click();
		} catch (Exception e) {
			System.out.println("Ha habido errror al tratar de pulsar er "+ e);
		}
		return this;
	}

	public boolean verificarPresenciaBtnReservar(){
		waitForElements(driver, waitForButtonReservarDroid, waitForButtonReservarIOS);
		buttonRervar.isDisplayed();
		return true;	
	}

}

