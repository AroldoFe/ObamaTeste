package br.ufrn.imd.testeobama.testes;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestObama {
	private WebDriver driver;
	private String driverPath = "/home/aroldo-felix/Área de Trabalho/ObamaTeste/chromedriver";
	
	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		// login
		driver.get("https://hobama.imd.ufrn.br/login/form");
		driver.findElement(By.id("email")).clear();
	    driver.findElement(By.id("email")).sendKeys("testeobama@gmail.com");
	    driver.findElement(By.id("senha")).clear();
	    driver.findElement(By.id("senha")).sendKeys("t35t30b4m4");
	    driver.findElements(By.id("btn-entrar")).get(1).click();
	}
	
	@Test
	public void testCadastroPlanoAula() throws InterruptedException {
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
	    Thread.sleep(2000);
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
	
	@Test
	public void testEditarPlanoDeAula() {
	    // Acessando formulário
	    // Verificando se foi cadastrado
	    driver.get("https://hobama.imd.ufrn.br/planoDeAula/meusPlanosDeAula");
	    List<WebElement> trs = driver.findElements(By.tagName("tr"));
	    boolean achou = false;
	    for(WebElement tr: trs) {
	    	for(WebElement td: tr.findElements(By.tagName("td"))) {	
	    		for(WebElement a: td.findElements(By.tagName("a"))) {
	    			if(achou) {
	    				if(a.getAttribute("id").contains("btnEditar")) {
	    					a.click();
	    					driver.findElement(By.name("planoDeAula.titulo")).clear();
	    					driver.findElement(By.name("planoDeAula.titulo")).sendKeys("Funções3");
	    					driver.findElement(By.id("btn-salvarRascunho")).click();
	    					driver.get("https://hobama.imd.ufrn.br/planoDeAula/meusPlanosDeAula");
	    				    List<WebElement> as = driver.findElements(By.tagName("a"));
	    				    for(WebElement b: as) {
	    				    	if(b.getText().contains("Funções3")) {
	    				    		assertTrue(true);
	    				    		return;
	    				    	}
	    				    }
	    				}
	    			} else {
	    				if(a.getText().contains("Funções")) {
		    				achou = true;
		    			}
	    			}
	    		}
	    	}
	    }
	    fail();
	}
	
	@Test
	public void testDeletarPlanosDeAula(){
	    
	    // Verificando se foi cadastrado
	    driver.get("https://hobama.imd.ufrn.br/planoDeAula/meusPlanosDeAula");
	    List<WebElement> as = driver.findElements(By.tagName("a"));
	    for(WebElement a: as) {
	    	if(a.getAttribute("id").contains("btnRemover")) {
	    		a.click();
	    		driver.switchTo().alert().accept();
	    	}
	    }
	    
	    for(WebElement a: as) {
	    	if(a.getAttribute("id").contains("btnRemover")) {
	    		fail();
	    	}
	    }
	    assertTrue(true);
	    
	}
	
	@After
	public void tearDown() throws Exception {	
		driver.quit();
	}
}
