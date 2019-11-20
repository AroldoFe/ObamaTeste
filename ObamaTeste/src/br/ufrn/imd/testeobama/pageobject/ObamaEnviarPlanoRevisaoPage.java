package br.ufrn.imd.testeobama.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ObamaEnviarPlanoRevisaoPage {
	
	private WebDriver driver;
	
	public ObamaEnviarPlanoRevisaoPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void enviarRevisao(WebElement row) {
		List<WebElement> tds = row.findElements(By.tagName("td"));
		WebElement lastTd = tds.get(tds.size() - 1);
		lastTd.findElement(By.id("btnEditar")).click();
		
		List<WebElement> avancar = driver.findElements(By.className("link-avancar"));
	    avancar.get(0).click();
	    avancar.get(1).click();
	    avancar.get(2).click();
	    
	    // TODO Terminar
	}

}
