package br.ufrn.imd.testeobama.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.ufrn.imd.testeobama.pageobject.utils.Utils;

public class ObamaPlanosPendentesPage {
	
	private WebDriver driver;
	
	@FindBy(className="form-control input-sm")
	private List<WebElement> campoBusca;
	
	@FindBy(className="tabela-planoDeAula")
	private List<WebElement> planosTable;
	
	@FindBy(className="modal-content")
	private List<WebElement> modalAtribuirRevisor;
	
	private WebElement tbody;
	
	private static final String urlBase = Utils.URL_BASE + "/planoDeAula/planosPendentes";

	public ObamaPlanosPendentesPage(WebDriver driver) {
		this.driver = driver;
		this.driver.get(urlBase);
		PageFactory.initElements(this.driver, this);
		tbody.findElement(By.tagName("body"));
	}
	
	public void setCampoBuscar(String nome){
		campoBusca.get(0).clear();
		campoBusca.get(0).sendKeys(nome);
		planosTable = driver.findElements(By.className("planosTable"));
	}
	
	public WebElement buscarPlanoDeAula(String nome) {
		for(WebElement tr: tbody.findElements(By.tagName("tr"))) {
			List<WebElement> tds = tr.findElements(By.tagName("td"));
	    	if(tds.get(0).getText().contains(nome)) {
	    		return tr;
	    	}
		}
		return null;
	}
	
	public void abrirAtribuirRevisores(WebElement tr) {
		List<WebElement> tds = tr.findElements(By.tagName("td"));
		WebElement botao = tds.get(2).findElement(By.tagName("a"));
		botao.click();
		modalAtribuirRevisor = driver.findElements(By.className("modal-content"));
	}
	
	public void atribuirRevisor(String email) {
		WebElement campoEmailRevisor = modalAtribuirRevisor.get(0).findElement(By.id("inputEmail"));
		WebElement botaoSalvar =  modalAtribuirRevisor.get(0).findElement(By.id("btn-salvar-add-revisor"));
		campoEmailRevisor.clear();
		campoEmailRevisor.sendKeys("email");
		botaoSalvar.click();
	}
	
	public boolean verificarAtribuicaoDeRevisor(WebElement tr) {
		List<WebElement> tds = tr.findElements(By.tagName("td"));
		WebElement botao = tds.get(2).findElement(By.tagName("a"));
		if(botao.getText() == Utils.ALTERAR_REVISOR) {
			return true;
		}
		return false;
	}
	

}
