package br.ufrn.imd.testeobama.testes;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import br.ufrn.imd.testeobama.pageobject.ObamaCadastroPlanoPage;
import br.ufrn.imd.testeobama.pageobject.ObamaEnviarPlanoRevisaoPage;
import br.ufrn.imd.testeobama.pageobject.ObamaListagemPlanoAulaPage;
import br.ufrn.imd.testeobama.pageobject.ObamaLoginPage;
import br.ufrn.imd.testeobama.pageobject.ObamaPlanosPublicadosPage;

public class ValidacaoPlanoAulaTeste {
	private static WebDriver driver;
	private static String driverPath = "/home/aroldo.felix/Área de Trabalho/ObamaTeste/chromedriver_77";
	
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
		
		String nomePlano = "Teste Hobama Funções";
		
		ObamaCadastroPlanoPage cadastro = new ObamaCadastroPlanoPage(driver);
		cadastro.cadastrar(nomePlano);
		
		ObamaListagemPlanoAulaPage planosAula = new ObamaListagemPlanoAulaPage(driver);
		WebElement row = planosAula.findPlanoDeAula(nomePlano);
		
		ObamaEnviarPlanoRevisaoPage enviarParaRevisao = new ObamaEnviarPlanoRevisaoPage(driver);
		enviarParaRevisao.enviarRevisao(row);
		// Terminando o cadastro e envio do plano de aula para revisor
		loginPage.logout();
		// Admin entrando para atribuir um revisor para o plano
		loginPage.login("", "");
		
		
		// Escritor do plano de aula voltando para o sistema e saber se foi publicado
		ObamaPlanosPublicadosPage publicados = new ObamaPlanosPublicadosPage(driver);
		
		assertTrue(publicados.estaPublicado(nomePlano));
	}
	
	
	@AfterClass
	public static void tearDown() {
		driver.close();
	}

}
