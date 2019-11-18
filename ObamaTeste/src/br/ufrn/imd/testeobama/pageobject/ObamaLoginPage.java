package br.ufrn.imd.testeobama.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ObamaLoginPage {
	private WebDriver driver;
	
	@FindBy(id="email")
	private WebElement campoLogin;
	
	@FindBy(id="senha")
	private WebElement campoSenha;
	
	@FindBy(id="btn-entrar")
	private List<WebElement> btnEntrar;
	
	private static final String urlBase = "https://www.hobama.imd.ufrn.br";
	
	public ObamaLoginPage(WebDriver driver) {
		this.driver = driver;
		this.driver.get(urlBase);
		PageFactory.initElements(this.driver, this);
	}
	
	public void login(String email, String senha) {
		campoLogin.clear();
		campoLogin.sendKeys(email);
		
		campoSenha.clear();
		campoSenha.sendKeys(senha);
		
		btnEntrar.get(1).click();
	}
}
