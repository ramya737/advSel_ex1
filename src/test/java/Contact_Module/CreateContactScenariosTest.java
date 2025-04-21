package Contact_Module;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import BaseclassUtility.Baseclass;
import GenericUtilities.ClassObject_Utility;
import GenericUtilities.Excel_Utility;
import GenericUtilities.Java_Utility;
import GenericUtilities.WebDriver_Utility;
import PomPages.ContactDetailsPomPage;
import PomPages.ContactPomPage;
import PomPages.CreateNewContactPomPage;
import PomPages.CreateNewOrganization;
import PomPages.HomePage;
import PomPages.OrgDetailPomPage;
import PomPages.OrganizationPomPage;

//retryAnalyzer = Liseners.Utility.RetryAnalyser_Utility.class
@Listeners(Liseners.Utility.LisenersClass.class)
public class CreateContactScenariosTest extends Baseclass {
	@Test(groups = "smoke")
	public void CreateContactTest() throws IOException, InterruptedException {
		WebDriver_Utility w_util = new WebDriver_Utility();

		// FETCH data from Excel
		ClassObject_Utility.getTest().log(Status.INFO, "Fetch data from Excel");
		Excel_Utility ex_util = new Excel_Utility();
		Java_Utility j_util = new Java_Utility();
		int random = j_util.getRandomNumber();
		String lastname = ex_util.FetchDataFromExcelFile("Contact", 1, 2) + random;

		// Identigy contacts tab in home page and click on it
		ClassObject_Utility.getTest().log(Status.INFO, "Home page verification");
		HomePage home = new HomePage(driver);
		boolean exp_res = home.getHeader().contains("Home");
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(exp_res, true);
		// identify contact tab in home page and click
		ClassObject_Utility.getTest().log(Status.INFO, "Navigate to Contact tab ");
		home.getCont_tab();

		// Identify plus button and click on it
		ClassObject_Utility.getTest().log(Status.INFO, "Navigate to Create new contact page");
		ContactPomPage con = new ContactPomPage(driver);
		con.getPlusicon();

		// enter contact name in create new org page and save
		ClassObject_Utility.getTest().log(Status.INFO, "Creating new contact ");
		CreateNewContactPomPage newcon = new CreateNewContactPomPage(driver);
		newcon.getLastname_TF(lastname);
		newcon.getSaveBtn();

		// verify actual contact name with expected org name
		ClassObject_Utility.getTest().log(Status.INFO, "verify the contact ");
		ContactDetailsPomPage condetail = new ContactDetailsPomPage(driver);
		boolean exp_res1 = condetail.getHeader().contains(lastname);
		Assert.assertEquals(exp_res1, true);

		// click on contact tab and delete the created org
		ClassObject_Utility.getTest().log(Status.INFO, "Navigate to contact tab and delete ");
		home.getCont_tab();
		driver.findElement(By.xpath(
				"//a[text()='" + lastname + "']/ancestor::tr[@bgcolor='white']/descendant::a[contains(text(),'del')]"))
				.click();
		// Handle the popup
		Thread.sleep(3000);

		ClassObject_Utility.getTest().log(Status.INFO, "handling delete popup ");
		w_util.HandleAlertAndAccept(driver);
		soft.assertAll();
	}

	// , retryAnalyzer = Liseners.Utility.RetryAnalyser_Utility.class
	@Test(groups = "regression")
	public void CreateConwithorgTest() throws IOException, InterruptedException {

		WebDriver_Utility w_util = new WebDriver_Utility();
		ClassObject_Utility.getTest().log(Status.INFO, "Fetch data from Excel");
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
		// soft assert -nonstatic
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(exp_res, true);

		ClassObject_Utility.getTest().log(Status.INFO, "Navigata to org tab ");
		home.getOrg_tab();

		// Identify plus button and click on it
		ClassObject_Utility.getTest().log(Status.INFO, "Navigate to Create new org ");
		OrganizationPomPage org = new OrganizationPomPage(driver);
		org.getPlusicon();

		// enter org name in create new org page and save
		ClassObject_Utility.getTest().log(Status.INFO, "create new org name");
		CreateNewOrganization neworg = new CreateNewOrganization(driver);
		neworg.getOrgname_TF(orgname);
		neworg.getSaveBtn();

		// verify actual org name with expected org name
		ClassObject_Utility.getTest().log(Status.INFO, "verifying org name");
		OrgDetailPomPage orgdetail = new OrgDetailPomPage(driver);
		boolean Ex_res3 = orgdetail.getHeader().contains(orgname);
		// hard assert-static
		Assert.assertEquals(Ex_res3, true);

		ClassObject_Utility.getTest().log(Status.INFO, "navigate to contact tab");
		home.getCont_tab();

		// Identify plus button and click on it
		ClassObject_Utility.getTest().log(Status.INFO, "navigate to create new contact page");
		ContactPomPage con = new ContactPomPage(driver);
		con.getPlusicon();

		// Enter org name in create org page and save
		ClassObject_Utility.getTest().log(Status.INFO, "Create new contact with org name");
		CreateNewContactPomPage newcon = new CreateNewContactPomPage(driver);
		newcon.getLastname_TF(lastname);

		// Select org name
		ClassObject_Utility.getTest().log(Status.INFO, "Select org name from child window");
		String pwid = driver.getWindowHandle();
		newcon.getOrgplusicon();
		w_util.switchToTabUsingUrl(driver, "module=Accounts&actions");

		newcon.getOrgsearchTF(orgname);
		newcon.getOrgsearchBtn();
		driver.findElement(By.xpath("//a[text()='" + orgname + "']")).click();

		w_util.switchBackToParentWindow(driver, pwid);
		newcon.getSaveBtn();

		// verify actuat org name with expected org name
		ClassObject_Utility.getTest().log(Status.INFO, "Verify contact name with org name");
		ContactDetailsPomPage condetail = new ContactDetailsPomPage(driver);

		boolean exp_res5 = condetail.getHeader().contains(lastname);
		Assert.assertEquals(exp_res5, true);

		// click on contacts tab and delete the created the contact
		ClassObject_Utility.getTest().log(Status.INFO, "NAVIGATE TO CONTACT TAB AND DELETE IT");
		home.getCont_tab();

		driver.findElement(
				By.xpath("//a[text()='" + lastname + "']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
				.click();

		Thread.sleep(3000);
		// Handle the popup
		ClassObject_Utility.getTest().log(Status.INFO, "handling delete popup ");
		w_util.HandleAlertAndAccept(driver);

		// click on org tab and delete the created org
		ClassObject_Utility.getTest().log(Status.INFO, "NAVIGATE TO ORG TAB AND DELETE IT ");
		home.getOrg_tab();

		// NONSTATIC ELEMENT
		driver.findElement(By.xpath(
				"//a[text()='" + orgname + "']/ancestor::tr[@bgcolor='white']/descendant::a[contains(text(),'del')]"))
				.click();
		Thread.sleep(3000);

		ClassObject_Utility.getTest().log(Status.INFO, "handling delete popup ");
		w_util.HandleAlertAndAccept(driver);
		soft.assertAll();
	}

	@Test

	public void CreatewithSuppDateT() throws IOException, InterruptedException {
		WebDriver_Utility w_util = new WebDriver_Utility();
		ClassObject_Utility.getTest().log(Status.INFO, "Fetch data from Excel");
		// Fetch data from excel file

		Excel_Utility ex_util = new Excel_Utility();
		Java_Utility j_util = new Java_Utility();
		int random = j_util.getRandomNumber();
		String contname = ex_util.FetchDataFromExcelFile("Contact", 4, 2) + random;

		// Identigy contacts tab in home page and click on it
		ClassObject_Utility.getTest().log(Status.INFO, "Home page verification");
		HomePage home = new HomePage(driver);
		boolean exp_res = home.getHeader().contains("Home");
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(exp_res, true);

		ClassObject_Utility.getTest().log(Status.INFO, "navigate to contact tab");
		home.getCont_tab();

		// Identify plus button and click on it
		ClassObject_Utility.getTest().log(Status.INFO, "Identify create new contact plus icon");
		ContactPomPage con = new ContactPomPage(driver);
		con.getPlusicon();

		// driver.findElement(By.name("lastname")).sendKeys(lastname);
		ClassObject_Utility.getTest().log(Status.INFO, "Create new conatct");
		CreateNewContactPomPage newcon = new CreateNewContactPomPage(driver);
		newcon.getLastname_TF(contname);

		// specify start and end support date
		ClassObject_Utility.getTest().log(Status.INFO, "Identify date");
		WebElement start_dateTF = newcon.getConStartDate_TF();
		start_dateTF.clear();
		String startdate = j_util.getCurrentDate();
		start_dateTF.sendKeys(startdate);

		WebElement end_dateTF = newcon.getConEndDate_TF();
		end_dateTF.clear();
		String enddate = j_util.getDateAftergivenDays(30);
		end_dateTF.sendKeys(enddate);

		newcon.getSaveBtn();

		// verify actual org name with expected org name
		ClassObject_Utility.getTest().log(Status.INFO, "Verifying the org");
		ContactDetailsPomPage condetail = new ContactDetailsPomPage(driver);

		boolean exp_res_con = condetail.getHeader().contains(contname);
		Assert.assertEquals(exp_res_con, true);

		// Verify start sup date and end support date
		ClassObject_Utility.getTest().log(Status.INFO, "Verifying the date");
		boolean exp_actstartdate = condetail.getVerifyStatedate().contains(startdate);
		Assert.assertEquals(exp_actstartdate, true);

		boolean exp_actenddate = condetail.getVerifyEnddate().contains(enddate);
		Assert.assertEquals(exp_actenddate, true);

		// click on org tab and delete the created org
		ClassObject_Utility.getTest().log(Status.INFO, "Navigate to org and delete the org");
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(
				By.xpath("//a[text()='" + contname + "']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
				.click();

		// Handle the popup
		// driver.switchTo().alert().accept();
		Thread.sleep(2000);
		ClassObject_Utility.getTest().log(Status.INFO, "Handle the popup");
		w_util.HandleAlertAndAccept(driver);
		soft.assertAll();
	}
}
