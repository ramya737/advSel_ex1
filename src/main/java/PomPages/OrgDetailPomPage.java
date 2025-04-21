package PomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrgDetailPomPage {
	//Declare 
@FindBy(xpath= "//span[contains(text(),'Organization Information')]")
private WebElement header;

@FindBy(id = "dtlview_Organization Name")
private WebElement verifyOrganame;


@FindBy(id = "mouseArea_Phone")
private WebElement verifyOrgPhno;


@FindBy(id = "dtlview_Industry")
private WebElement verifyIndustry;


@FindBy(id="dtlview_Type")
private WebElement verifyType;

//initialization
public OrgDetailPomPage(WebDriver driver) {
PageFactory.initElements(driver, this);
}
//UtilizTION

public String getHeader() {
	return header.getText();
}

public String getVerifyOrganme() {
	return verifyOrganame.getText();
}

public String getVerifyOrgPhno() {
	return verifyOrgPhno.getText();
}

public String getVerifyIndustry() {
	return verifyIndustry.getText();
}

public String getVerifyType() {
	return verifyType.getText();
}






}
