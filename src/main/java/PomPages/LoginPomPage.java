package PomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPomPage {

	// declaration
	@FindBy(linkText = "vtiger")
	private WebElement header;

	@FindBy(name = "user_name")
	private WebElement un;

	@FindBy(name = "user_password")
	private WebElement pswd;

	@FindBy(id = "submitButton")
	private WebElement loginbtn;

	// initialization
	public LoginPomPage(WebDriver driver) {

		PageFactory.initElements(driver, this);
	}

	// Utilization
	public String getHeader() {
		return header.getText();
	}

	public void getUn(String user) {
		un.sendKeys(user);
	}

	public void getPwd(String password) {
		pswd.sendKeys(password);
	}

	public void getLoginbtn() {
		loginbtn.click();
	}
//Business logic
	public void login(String user,String pass) {
		un.sendKeys(user);
		pswd.sendKeys(pass);
		loginbtn.click();
	}
}