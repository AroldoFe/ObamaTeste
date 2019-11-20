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
	
	@FindBy(className="input-sm")
	private List<WebElement> campoBusca;
	
	@FindBy(className="tabela-planoDeAula")
	private List<WebElement> planosTable;
	
	
	private WebElement tbody;
	
	private static final String urlBase = Utils.URL_BASE + "/planoDeAula/planosPendentes";

	public ObamaPlanosPendentesPage(WebDriver driver) {
		this.driver = driver;
		this.driver.get(urlBase);
		PageFactory.initElements(this.driver, this);
		tbody = driver.findElements(By.tagName("tbody")).get(0);
	}
	
	public void setCampoBuscar(String nome){
		campoBusca = driver.findElements(By.className("input-sm"));
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
	}
	
	public void atribuirRevisor(String email) {
		WebElement campoEmailRevisor = driver.findElements(By.name("emailRevisor")).get(0);
		WebElement botaoSalvar =  driver.findElement(By.id("btn-salvar-add-revisor"));
		campoEmailRevisor.clear();
		campoEmailRevisor.sendKeys(email);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		botaoSalvar.click();
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean verificarAtribuicaoDeRevisor(WebElement tr) {
		List<WebElement> tds = tr.findElements(By.tagName("td"));
		WebElement botao = tds.get(2).findElement(By.tagName("a"));
		if(botao.getText() == Utils.ALTERAR_REVISOR) {
			return true;
		}
		return false;
	}
	
	public void validar(String plano) {
		driver.get(urlBase);
		
		this.setCampoBuscar(plano);
		WebElement tr = this.buscarPlanoDeAula(plano);
		tr.findElement(By.id("btnValidar")).click();
	}
	

}
