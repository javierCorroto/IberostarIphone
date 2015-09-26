package com.mo2o.Iberostar.testcases;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import java.lang.reflect.Method;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import test.assertion.AssertionTest;
import test.tmp.AssertEqualsTest;

import com.mo2o.Iberostar.functions.CommonFunctions;
import com.mo2o.Iberostar.functions.HabitacionesScreen;
import com.mo2o.Iberostar.functions.HotelesScreen;
import com.mo2o.Iberostar.functions.RegimenHabitacionScreen;



public class SmokeTest extends TestBase {
	//TC001: Verificar imposibilidad de hacer log in con email no existente o erroneo
	@BeforeTest
	public void iniciliador(){
		screenSelectHotel = new HotelesScreen(driver);
		selectHabitacionesFechas =  new HabitacionesScreen(driver);
		regimenHabitacion =  new RegimenHabitacionScreen(driver);
	}

	@Test (dataProvider ="Reservas", description= "Verificar que no se puede crear cuentas con informacion erronea")
	public void reservaHotelEMEATarjeta(String testCase, String seleccionMenuLateral, String pais, String provincia, String hotel,
			String numHabitaciones, String mesEntrada,String diaEntrada, String mesSalida, String diaSalida, Method m)
					throws Exception {
		while(executeTestCase(testCase, m)){
			CommonFunctions.seleccionMenuLateral(driver, seleccionMenuLateral);
			screenSelectHotel.seleccionHotel(driver, pais, provincia, hotel);
			selectHabitacionesFechas.selectDiaEntrada(driver, mesEntrada,diaEntrada);
			selectHabitacionesFechas.selectDiaSalida(driver, mesSalida, diaSalida);
			Assert.assertTrue(selectHabitacionesFechas.verificarPresenciaBtnReservar(), "Hay disponibilidad de habitaciones");
			selectHabitacionesFechas.tapReservar();
			regimenHabitacion.selectTipoHabitacion(driver);
			//regimenHabitacion.getEurosHabitacion();
			Assert.assertTrue(selectHabitacionesFechas.verificarPresenciaBtnReservar(), "Hay disponibilidad de habitaciones");
		}
	}
	@DataProvider(name = "Reservas")
	public Object[][] logIn(Method m) throws Exception {
		Object[][] retObjArr = getTableArray(
				"/Users/javiercorroto/Desktop/Iberostar.xls","Reservas",
				"Iberostar",m);
		return (retObjArr);
	}
}