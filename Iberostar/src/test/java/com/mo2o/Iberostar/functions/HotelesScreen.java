package com.mo2o.Iberostar.functions;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import com.mo2o.Iberostar.testcases.TestBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;

public class HotelesScreen extends CommonFunctions{

	public HotelesScreen(AppiumDriver <MobileElement> driver) {
		super(driver);
		this.driver = driver;
	}

	static By expandableTextViewPaisDroid = By.id("com.mo2o.iberostar:id/ParentLevel");
	static By textViewPaisDroid = By.id("com.mo2o.iberostar:id/txtTitlePais");
	static By expandableListViewtProvinciaDroid = By.className("android.widget.ExpandableListView");
	static By textViewProvinciaDroid = By.id("com.mo2o.iberostar:id/txtTitleZona");
	static By relativeLayoutProvinciaDroid = By.className("android.widget.RelativeLayout");
	static By textViewHotelDroid = By.id("com.mo2o.iberostar:id/txtTitleHotel");
	static By textPaisDroid = By.id("com.mo2o.iberostar:id/txtTitlePais");
	static By textPaisOS = By.id("");
	static By textHotelDroid = By.id("com.mo2o.iberostar:id/txtTitleHotel");
	static By waitReservarAhoraDroid = By.id("com.mo2o.iberostar:id/placeHolder");
	static By waitReservarAhoraiOS = By.id("");


	@AndroidFindBy(id="com.mo2o.iberostar:id/placeHolder")
	@iOSFindBy(id="")
	public static WebElement buttonReservarAhora; 
	
	//Seleccionar hoteles desde menu lateral Hoteles
	public HotelesScreen  seleccionHotel(AppiumDriver<MobileElement>  driver, String pais, String provincia, String hotel ){
		try{
			CommonFunctions.waitForElements(driver, textPaisDroid, textPaisOS);
			
			//Select country
			WebElement listaPais = driver.findElement(expandableTextViewPaisDroid);
			List<WebElement> itemsPaisLista = listaPais.findElements(textViewPaisDroid);
			//Se verifica si es el pais que se desea
			outerlopp:
				for (WebElement elementPais : itemsPaisLista){
					if (elementPais.getText().contains(pais)){
						elementPais.click();
						//Se verifica si es la provincia que se desea
						WebElement listaProvincia = driver.findElement(expandableListViewtProvinciaDroid);
						List<WebElement> itemsProvinciLista = listaProvincia.findElements(textViewProvinciaDroid);
						for (WebElement elementProvincia : itemsProvinciLista){
							System.out.println("Provincia es " + elementProvincia.getText());
							if (elementProvincia.getText().contains(provincia)){
								elementProvincia.click();
								//Se verifica si es el hotel que se desea
								int totalLayOuts= driver.findElements(By.className("android.widget.RelativeLayout")).size();
								System.out.println("Total de layouts es " + totalLayOuts );
								for (int i= 0; i<totalLayOuts; i++){
									WebElement indexLayOut = driver.findElements(By.className("android.widget.RelativeLayout")).get(i);
									List<WebElement> listaHotel= indexLayOut.findElements(textHotelDroid);
									for (WebElement hotelTexto : listaHotel){
										System.out.println("Hotel es " + hotelTexto.getText());
										if (hotelTexto.getText().contains(hotel)){
											hotelTexto.click();
											//Se espera que se muestre el botón reservar para pulsar
											CommonFunctions.waitForElements(driver, waitReservarAhoraDroid, waitReservarAhoraiOS);
											buttonReservarAhora.click();
											break outerlopp;
										}
									}
								}
							}
						}
					}
				}	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Ha habido errror en selección seleccionHotel "+ e);
		}
		return this;
	}
}