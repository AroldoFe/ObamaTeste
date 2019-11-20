package br.ufrn.imd.testeobama.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import br.ufrn.imd.testeobama.pageobject.utils.Utils;

public class ObamaLoginPage {
	private WebDriver driver;
	
	@FindBy(id="email")
	private WebElement campoLogin;
	
	@FindBy(id="senha")
	private WebElement campoSenha;
	
	@FindBy(id="btn-entrar")
	private List<WebElement> btnEntrar;
	
	private static final String urlBase = Utils.URL_BASE + "/login/form";
	
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
	
	public void logout() {
		driver.get(Utils.URL_BASE + "/logout");
	}
}
