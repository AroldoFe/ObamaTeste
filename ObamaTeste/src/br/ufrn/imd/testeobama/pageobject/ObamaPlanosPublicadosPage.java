package br.ufrn.imd.testeobama.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import br.ufrn.imd.testeobama.pageobject.utils.Utils;

public class ObamaPlanosPublicadosPage {
	private WebDriver driver;

	@FindBy(id = "planos")
	private WebElement planos;
	
	private static final String ulrBase = Utils.URL_BASE + "/planoDeAula/publicados";
	
	public ObamaPlanosPublicadosPage(WebDriver driver) {
		this.driver = driver;
		this.driver.get(ulrBase);
	}
	
	public boolean estaPublicado(String nomePlano) {
		WebElement tbody = planos.findElements(By.tagName("tbody")).get(0);
		List<WebElement> trs = tbody.findElements(By.tagName("tr"));
		for(WebElement tr: trs) {
			WebElement td = tr.findElements(By.tagName("td")).get(0);
			if(td.getText().contains(nomePlano)) {
				return true;
			} else {
				continue;
			}
		}
		return false;
	}
	
	
}
