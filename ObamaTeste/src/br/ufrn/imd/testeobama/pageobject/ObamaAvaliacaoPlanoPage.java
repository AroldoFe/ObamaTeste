package br.ufrn.imd.testeobama.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ObamaAvaliacaoPlanoPage {
	private WebDriver driver;
	
	public ObamaAvaliacaoPlanoPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void avaliarPositivamente() {
		String id = "pergunta";
		for(int i=1; i <= 9; i++) {
			driver.findElement(By.id(id + i)).findElement(By.tagName("input")).click();
		}
		driver.findElement(By.id("parecer")).sendKeys("Avaliado.");
		
		driver.findElement(By.id("btn-submissao")).click();
	}
}
