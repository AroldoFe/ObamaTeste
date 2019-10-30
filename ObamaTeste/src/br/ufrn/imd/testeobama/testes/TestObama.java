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
	private String driverPath = "assets/chromedriver.exe";
	
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
	public void testEditarPlanoDeAula() throws InterruptedException {
		testCadastroPlanoAula();
	    // Acessando formulário
	    // Verificando se foi cadastrado
	    driver.get("https://hobama.imd.ufrn.br/planoDeAula/meusPlanosDeAula");
	    
	    WebElement table = driver.findElement(By.className("tabela-planoDeAula"));
	    WebElement tbody = table.findElement(By.tagName("tbody"));
	    
	    for(WebElement tr: tbody.findElements(By.tagName("tr"))) {
	    	List<WebElement> tds = tr.findElements(By.tagName("td"));
	    	if(tds.get(0).getText().contains("Funções")) {
	    		WebElement lastTd = tds.get(tds.size() - 1);
	    		lastTd.findElement(By.id("btnEditar")).click();

	    		break;
	    	}
	    }
	    
	    Thread.sleep(2000);

	    driver.findElement(By.name("planoDeAula.titulo")).clear();
		driver.findElement(By.name("planoDeAula.titulo")).sendKeys("Funções3");
		driver.findElement(By.id("btn-salvarRascunho")).click();
		
		Thread.sleep(2000);
		
		driver.get("https://hobama.imd.ufrn.br/planoDeAula/meusPlanosDeAula");
	    
		table = driver.findElement(By.className("tabela-planoDeAula"));
	    tbody = table.findElement(By.tagName("tbody"));
	    
		for(WebElement tr: tbody.findElements(By.tagName("tr"))) {
	    	List<WebElement> tds = tr.findElements(By.tagName("td"));
	    	if(tds.get(0).getText().contains("Funções3")) {
	    		assertTrue(true);
	    		return ;
	    	}
	    }
		
	    fail();
	}
	
	@Test
	public void testDeletarPlanosDeAula() throws InterruptedException{
		testCadastroPlanoAula();
		
	    // Verificando se foi cadastrado
	    driver.get("https://hobama.imd.ufrn.br/planoDeAula/meusPlanosDeAula");
	    
	    
	    for (;;) {
	    	List<WebElement> btns = driver.findElements(By.id("btnRemover"));
	    	
	    	if (btns != null && !btns.isEmpty()) {
		    	WebElement btn = btns.get(0);
		    	btn.click();
		    	driver.switchTo().alert().accept();
		    	driver.get("https://hobama.imd.ufrn.br/planoDeAula/meusPlanosDeAula");
	    	} else break;
	    }
	    
	    List<WebElement> btns = driver.findElements(By.id("btnRemover"));
	    if(!(btns == null || btns.isEmpty())) {
	    	fail();
	    }
	    assertTrue(true);
	    
	}
	
	@After
	public void tearDown() throws Exception {	
		driver.quit();
	}
}
