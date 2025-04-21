package PomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericUtilities.WebDriver_Utility;

public class HomePage {
	//Declare
	@FindBy(partialLinkText="Home")
	private WebElement header;
	
	@FindBy(linkText="Organizations")
	private WebElement org_tab;
	
	@FindBy(linkText="Contacts")
	private WebElement cont_tab;
	
	@FindBy(xpath="//span[text()=' Administrator']/../following-sibling::td/img")
	private WebElement admin_icon;
	
	
	@FindBy(xpath="//a[text()='Sign Out']")
	private WebElement signout;
	
	//Initialize
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
//Utilize
	public String getHeader() {
		 return header.getText();
	}

	public void getOrg_tab() {
		 org_tab.click();;
	}

	public void getCont_tab() {
	 cont_tab.click();
	}

	public WebElement getAdmin_icon() {
	 return admin_icon;
	}

	public void getSignout() {
		 signout.click();
	}

		
	public void logout(WebDriver driver) {
		WebDriver_Utility w_util=new WebDriver_Utility();
		w_util.Action_MouseHovering(driver, admin_icon);
		signout.click();
	}
	
	
	
	
	
}
