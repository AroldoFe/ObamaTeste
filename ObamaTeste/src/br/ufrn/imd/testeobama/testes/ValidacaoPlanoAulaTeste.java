package br.ufrn.imd.testeobama.testes;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import br.ufrn.imd.testeobama.pageobject.ObamaLoginPage;

public class ValidacaoPlanoAulaTeste {
	private static WebDriver driver;
	private static String driverPath = "/home/aroldo.felix/Área de Trabalho/ObamaTeste/chromedriver_71";
	
	@BeforeClass
	public static void setUp() {
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	public void testValidacaoPlanoAula() {
		ObamaLoginPage loginPage = new ObamaLoginPage(driver);
		loginPage.login("testeobama@gmail.com", "t35t30b4m4");
		
		
	}
	
	
	@AfterClass
	public static void tearDown() {
		driver.close();
	}

}
