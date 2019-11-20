package br.ufrn.imd.testeobama.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ObamaPagePublicarPlanoPage {
	private WebDriver driver;
	
	@FindBy(id = "btn-publicar")
	private List<WebElement> publicar;
	
	public ObamaPagePublicarPlanoPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void publicar() {
		publicar.get(0).click();
	}
}
