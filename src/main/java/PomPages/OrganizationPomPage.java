package PomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationPomPage {
//Declare
	@FindBy(linkText= "Organizations")
	private WebElement header;

@FindBy(xpath="//img[@title='Create Organization...']")
private WebElement plusicon;
//Initialize
public OrganizationPomPage(WebDriver driver) {
	PageFactory.initElements(driver,this);
}
//Utilize
public String getHeader() {
	return header.getText();
}
public void getPlusicon() {
	plusicon.click();
}
}
