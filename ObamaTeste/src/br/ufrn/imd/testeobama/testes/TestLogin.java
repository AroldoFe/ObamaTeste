package br.ufrn.imd.testeobama.testes;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestLogin {
	private WebDriver driver;
	private String driverPath = "/home/aroldo-felix/Área de Trabalho/ObamaTeste/chromedriver";
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	
	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
  
	@Test
	public void testLogin() throws Exception {
		driver.get("https://hobama.imd.ufrn.br/login/form");
		driver.findElement(By.id("email")).clear();
	    driver.findElement(By.id("email")).sendKeys("testeobama@gmail.com");
	    driver.findElement(By.id("senha")).clear();
	    driver.findElement(By.id("senha")).sendKeys("t35t30b4m4");
	    driver.findElement(By.id("btn-entrar")).click();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
}
