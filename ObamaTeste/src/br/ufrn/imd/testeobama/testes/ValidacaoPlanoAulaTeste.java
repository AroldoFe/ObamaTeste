package br.ufrn.imd.testeobama.testes;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import br.ufrn.imd.testeobama.pageobject.ObamaAvaliacaoPlanoPage;
import br.ufrn.imd.testeobama.pageobject.ObamaAvaliacaoPrimeiroRevisorPage;
import br.ufrn.imd.testeobama.pageobject.ObamaCadastroPlanoPage;
import br.ufrn.imd.testeobama.pageobject.ObamaEnviarPlanoRevisaoPage;
import br.ufrn.imd.testeobama.pageobject.ObamaListagemPlanoAulaPage;
import br.ufrn.imd.testeobama.pageobject.ObamaLoginPage;
import br.ufrn.imd.testeobama.pageobject.ObamaPlanosPendentesPage;
import br.ufrn.imd.testeobama.pageobject.ObamaPlanosPublicadosPage;

public class ValidacaoPlanoAulaTeste {
	private static WebDriver driver;
	private static String driverPath = "/home/aroldo-felix/Área de Trabalho/ObamaTeste/chromedriver_77";
	
	@BeforeClass
	public static void setUp() {
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	private void cadastrarPlano(String plano) {
		ObamaLoginPage loginPage = new ObamaLoginPage(driver);
		loginPage.login("testeobama@gmail.com", "t35t30b4m4");
		
		ObamaCadastroPlanoPage cadastro = new ObamaCadastroPlanoPage(driver);
		cadastro.cadastrar(plano);
		
		ObamaListagemPlanoAulaPage planosAula = new ObamaListagemPlanoAulaPage(driver);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		WebElement row = planosAula.findPlanoDeAula(plano);
		
		ObamaEnviarPlanoRevisaoPage enviarParaRevisao = new ObamaEnviarPlanoRevisaoPage(driver);
		enviarParaRevisao.enviarRevisao(row);
		// Terminando o cadastro e envio do plano de aula para revisor
		loginPage.logout();
	}
	
	private void atribuirPlano(String plano) {
		ObamaLoginPage loginPage = new ObamaLoginPage(driver);
		loginPage.login("amanda.marry@hotmail.com", "123");
		ObamaPlanosPendentesPage planosPendentes = new ObamaPlanosPendentesPage(driver);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		planosPendentes.setCampoBuscar(plano);
		
		WebElement tr = planosPendentes.buscarPlanoDeAula(plano);
		planosPendentes.abrirAtribuirRevisores(tr);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		planosPendentes.atribuirRevisor("vitorhenrique908@gmail.com");
		
		loginPage.logout();
	}
	
	private void avaliarPlano(String plano) {
		ObamaLoginPage loginPage = new ObamaLoginPage(driver);
		loginPage.login("vhcb.vitor@hotmail.com", "123");
		
		ObamaAvaliacaoPrimeiroRevisorPage revisor = new ObamaAvaliacaoPrimeiroRevisorPage(driver);
		revisor.avalia(plano);
		
		ObamaAvaliacaoPlanoPage avaliacao = new ObamaAvaliacaoPlanoPage(driver);
		avaliacao.avaliarPositivamente();
		
		loginPage.logout();
	}
	
	public boolean verificarPublicacao(String plano) {
		ObamaPlanosPublicadosPage publicados = new ObamaPlanosPublicadosPage(driver);
		return publicados.estaPublicado(plano);
	}
	
	@Test
	public void testValidacaoPlanoAula() {
		String nomePlano = "Teste Hobama Funções";
		// Cadastro de um plano e envio para revisor
		cadastrarPlano(nomePlano);
		
		// Entrar como administrador e atribuir plano a um revisor
		this.atribuirPlano(nomePlano);
		
		// Entrar como revisor e avaliar o plano
		this.avaliarPlano(nomePlano);
		
		assertTrue(this.verificarPublicacao(nomePlano));
	}
	
	
	@AfterClass
	public static void tearDown() {
		driver.close();
	}

}
