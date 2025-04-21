package Contact_Module;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import BaseclassUtility.Baseclass;
import GenericUtilities.ClassObject_Utility;
import GenericUtilities.Excel_Utility;
import GenericUtilities.Java_Utility;
import GenericUtilities.Property_Utility;
import GenericUtilities.WebDriver_Utility;
import PomPages.ContactDetailsPomPage;
import PomPages.ContactPomPage;
import PomPages.CreateNewContactPomPage;
import PomPages.CreateNewOrganization;
import PomPages.HomePage;
import PomPages.LoginPomPage;
import PomPages.OrgDetailPomPage;
import PomPages.OrganizationPomPage;
import junit.framework.Assert;
@Listeners(Liseners.Utility.LisenersClass.class)
	public class CreateContactWithOrgTest extends Baseclass {

		@Test(groups = "Regression",retryAnalyzer = Liseners.Utility.RetryAnalyser_Utility.class)
	
		public void CreateConwithorgTest() throws IOException, InterruptedException {

			WebDriver_Utility w_util = new WebDriver_Utility();
			ClassObject_Utility.getTest().log(Status.INFO,"Fetch data from Excel");
			// FETCH data from Excel
			Excel_Utility ex_util = new Excel_Utility();
			Java_Utility j_util = new Java_Utility();
			int random = j_util.getRandomNumber();
			String lastname = ex_util.FetchDataFromExcelFile("Contact", 7, 2) + random;
			String orgname = ex_util.FetchDataFromExcelFile("Contact", 7, 3) + random;

			 
			  // Identigy organization tab in home page and click on it
			  ClassObject_Utility.getTest().log(Status.INFO, "Home page verification");
			  HomePage home = new HomePage(driver);
			  boolean exp_res = home.getHeader().contains("Home");
			  SoftAssert soft = new SoftAssert();
			  soft.assertEquals(exp_res, true);
			  
			  //Identify org tab and click on it
			  ClassObject_Utility.getTest().log(Status.INFO, "navigate to organization tab");
			  home.getOrg_tab();

			  // Identify plus button and click on it
			  ClassObject_Utility.getTest().log(Status.INFO, "Navigate to create new org");
			  OrganizationPomPage org = new OrganizationPomPage(driver);
			  org.getPlusicon();

			  // Enter org name in create new org name page and save
			  ClassObject_Utility.getTest().log(Status.INFO, "Create new org");
			  CreateNewOrganization neworg = new CreateNewOrganization(driver);
			  neworg.getOrgname_TF(orgname);
			  neworg.getSaveBtn();

			  // verify actual org name with expected org name
			  ClassObject_Utility.getTest().log(Status.INFO, "Verifying the org");
			  OrgDetailPomPage orgdetail = new OrgDetailPomPage(driver);
			  boolean exp_res1 = orgdetail.getHeader().contains(orgname);
			  Assert.assertEquals(exp_res1, true);

			        //Click on Contact tab
			  ClassObject_Utility.getTest().log(Status.INFO, "navigate to contact tab");
			  home.getCont_tab();

			  // Identify plus button and click on it
			  ClassObject_Utility.getTest().log(Status.INFO, "navigate to create new contat plus icon");
			  ContactPomPage con = new ContactPomPage(driver);
			  con.getPlusicon();

			  // driver.findElement(By.name("lastname")).sendKeys(lastname);
			  ClassObject_Utility.getTest().log(Status.INFO, "Create new contact with org name");
			  CreateNewContactPomPage newcon = new CreateNewContactPomPage(driver);
			  newcon.getLastname_TF(lastname);

			  // select org name
			  ClassObject_Utility.getTest().log(Status.INFO, "Select org name from child window");
			  String pwid = driver.getWindowHandle();
			  newcon.getOrgplusicon();

			  w_util.switchToTabUsingUrl(driver, "module=Accounts&action");

			  newcon.getOrgsearchTF(orgname);
			  newcon.getOrgsearchBtn();
			  driver.findElement(By.xpath("//a[text()='" + orgname + "']")).click();

			  w_util.switchBackToParentWindow(driver, pwid);

			  newcon.getSaveBtn();

			  ClassObject_Utility.getTest().log(Status.INFO, "Verifying contact name with org name");
			  ContactDetailsPomPage condetail = new ContactDetailsPomPage(driver);
			        boolean exp_res_con = condetail.getHeader().contains(lastname);
			  Assert.assertEquals(exp_res_con, true);

			  ClassObject_Utility.getTest().log(Status.INFO, "navigate to contact tab and delete the contact");
			  home.getCont_tab();

			  driver.findElement(
			    By.xpath("//a[text()='" + lastname + "']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
			    .click();

			  Thread.sleep(2000);
			  ClassObject_Utility.getTest().log(Status.INFO, "Handling delete popup");
			  w_util.HandleAlertAndAccept(driver);
			 
			  ClassObject_Utility.getTest().log(Status.INFO, "navigate to org tab and delete org");
			  home.getOrg_tab();
			  soft.assertAll();
			 }
}