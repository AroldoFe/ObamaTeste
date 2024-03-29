package br.ufrn.imd.testeobama.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.ufrn.imd.testeobama.pageobject.utils.Utils;

public class ObamaCadastroPlanoPage {
	private WebDriver driver;
	
	@FindBy(name="planoDeAula.escola")
	private WebElement campoEscola;
	
	@FindBy(name="planoDeAula.titulo")
	private WebElement campoTitulo;
	
	@FindBy(name="planoDeAula.duracaoEmMinutos")
	private WebElement campoDuracaoMinutos;
	
	@FindBy(className="link-avancar")
	private List<WebElement> linkAvancar;
	
	@FindBy(className="CodeMirror-code")
	private WebElement campoPlano;
	
	@FindBy(id="btnAdicionar")
	private WebElement btnAdicionar;
	
	@FindBy(id="btn-salvarRascunho")
	private WebElement btnSalvarRascunho;
	
	private static final String urlBase = Utils.URL_BASE + "/planoDeAula/formNovo";

	public ObamaCadastroPlanoPage(WebDriver driver) {
		this.driver = driver;
		this.driver.get(urlBase);
		PageFactory.initElements(this.driver, this);
	}
	
	public void cadastrar(String nome_plano) {
		campoEscola.clear();
		campoEscola.sendKeys("UFRN");
		
		campoTitulo.clear();
		campoTitulo.sendKeys(nome_plano);
		
		campoDuracaoMinutos.clear();
		campoDuracaoMinutos.sendKeys("50");
		
		linkAvancar.get(0).click();
		
//		campoPlano.click();
		
		linkAvancar.get(1).click();
		
		btnAdicionar.click();
		
		btnSalvarRascunho.click();
	}
}
