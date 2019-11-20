package br.ufrn.imd.testeobama.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import br.ufrn.imd.testeobama.pageobject.utils.Utils;

public class ObamaAvaliacaoPrimeiroRevisorPage {
	private WebDriver driver;
	private String baseUrl = Utils.URL_BASE + "/planoDeAula/planosPendentes";
	
	@FindBy(id = "input-sm")
	private WebElement barraPesquisa;
	
	private WebElement tabelaPlanos;
	
	public ObamaAvaliacaoPrimeiroRevisorPage(WebDriver driver) {
		this.driver = driver;
		this.driver.get(baseUrl);
	}
	
	public void avalia(String plano) {
		barraPesquisa.clear();
		barraPesquisa.sendKeys(plano);
		
		tabelaPlanos = driver.findElements(By.className("tabela-planoDeAula")).get(0);
		
		tabelaPlanos.findElement(By.id("btnAvaliar")).click();
	}
}
