package br.ufrn.imd.testeobama.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.ufrn.imd.testeobama.pageobject.utils.Utils;

public class ObamaListagemPlanoAulaPage {
	private WebDriver driver;
	private static final String PATH_PLANOS = "/planoDeAula/meusPlanosDeAula";
	
	@FindBy(className = "tabela-planoDeAula")
	private List<WebElement> table;
	
	private WebElement tbody;
	
	/**
	 * Constructor
	 * @param driver
	 */
	public ObamaListagemPlanoAulaPage (WebDriver driver) {
		this.driver = driver;
		driver.get(Utils.URL_BASE + PATH_PLANOS);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PageFactory.initElements(this.driver, this);
		
		this.tbody = table.get(0).findElements(By.tagName("tbody")).get(0);
	}
	
	/**
	 * 
	 * @param	planName - The name of the plan
	 * @return 	The row of yhat 
	 */
	public WebElement findPlanoDeAula (String planName) {
		for(WebElement tr: tbody.findElements(By.tagName("tr"))) {
	    	List<WebElement> tds = tr.findElements(By.tagName("td"));
	    	if(tds.get(0).getText().contains(planName)) {
	    		return tr;
	    	}
    	}
		return null;
	}
	
	public boolean inReview (String planName) {
		for(WebElement tr: tbody.findElements(By.tagName("tr"))) {
	    	List<WebElement> tds = tr.findElements(By.tagName("td"));
	    	if(tds.get(0).getText().contains(planName) && 
	    		tds.get(2).findElement(By.tagName("i")).getAttribute("title").contains("Aguardando")) {
	    		return true;
	    	}
    	}
		return false;
	}
}