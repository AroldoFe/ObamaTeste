package br.ufrn.imd.testeobama.testes;

import static org.junit.Assert.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestCadastroPlanoAula {
	private WebDriver driver;
	private String driverPath = "/home/aroldo-felix/Área de Trabalho/ObamaTeste/chromedriver";
	
	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	@Test
	public void test() throws InterruptedException {
		// login
		driver.get("https://hobama.imd.ufrn.br/login/form");
		driver.findElement(By.id("email")).clear();
	    driver.findElement(By.id("email")).sendKeys("testeobama@gmail.com");
	    driver.findElement(By.id("senha")).clear();
	    driver.findElement(By.id("senha")).sendKeys("t35t30b4m4");
	    driver.findElements(By.id("btn-entrar")).get(1).click();
	    // Acessando formulário
	    driver.get("https://hobama.imd.ufrn.br/planoDeAula/formNovo");
	    driver.findElement(By.name("planoDeAula.escola")).sendKeys("UFRN");
	    driver.findElement(By.name("planoDeAula.titulo")).sendKeys("Funções");
	    driver.findElement(By.name("planoDeAula.duracaoEmMinutos")).sendKeys("50");
	    driver.findElements(By.className("link-avancar")).get(0).click();
	    driver.findElement(By.className("CodeMirror-code")).click();
	    driver.findElements(By.className("link-avancar")).get(1).click();
	    driver.findElement(By.id("btnAdicionar")).click();
	    driver.findElement(By.id("btn-salvarRascunho")).click();
	    // Verificando se foi cadastrado
	    driver.get("https://hobama.imd.ufrn.br/planoDeAula/meusPlanosDeAula");
	    List<WebElement> as = driver.findElements(By.tagName("a"));
	    for(WebElement a: as) {
	    	if(a.getText().contains("Funções")) {
	    		assertTrue(true);
	    		return;
	    	}
	    }
	    fail();
	    
	}
	
	@After
	public void tearDown() throws Exception {
		/*driver.get("https://hobama.imd.ufrn.br/planoDeAula/meusPlanosDeAula");
		boolean achou = false;
	    List<WebElement> trs = driver.findElements(By.tagName("tr"));
	    for(WebElement tr: trs) {
	    	for(WebElement td: tr.findElements(By.tagName("td"))) {	
	    		for(WebElement a: td.findElements(By.tagName("a"))) {
	    			if(achou) {
	    				if(a.)
	    			} else {
	    				if(a.getText().contains("Funções")) {
		    				achou = true;
		    			}
	    			}
	    		}
	    	}
	    }*/
		
		driver.quit();
	}
}
