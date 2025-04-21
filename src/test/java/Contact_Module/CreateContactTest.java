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
import Liseners.Utility.LisenersClass;
import PomPages.ContactDetailsPomPage;
import PomPages.ContactPomPage;
import PomPages.CreateNewContactPomPage;
import PomPages.HomePage;

import junit.framework.Assert;

@Listeners(Liseners.Utility.LisenersClass.class)
public class CreateContactTest extends Baseclass {
	@Test(groups = "smoke", retryAnalyzer = Liseners.Utility.RetryAnalyser_Utility.class)
	public void CreateContact_Test() throws IOException {
		WebDriver_Utility w_util = new WebDriver_Utility();

		// FETCH data from Excel
		ClassObject_Utility.getTest().log(Status.INFO, "Fetch data from Excel");
		Excel_Utility ex_util = new Excel_Utility();
		Java_Utility j_util = new Java_Utility();
		int random = j_util.getRandomNumber();
		String Lastname = ex_util.FetchDataFromExcelFile("Contact", 1, 2) + random;

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
		newcon.getLastname_TF(Lastname);
		newcon.getSaveBtn();

		// verify actual contact name with expected org name
		ClassObject_Utility.getTest().log(Status.INFO, "verify the contact ");
		ContactDetailsPomPage condetail = new ContactDetailsPomPage(driver);
		boolean exp_res1 = condetail.getHeader().contains(Lastname);
		Assert.assertEquals(exp_res1, true);

		// click on contact tab and delete the created org
		ClassObject_Utility.getTest().log(Status.INFO, "Navigate to contact tab and delete ");
		home.getCont_tab();
		driver.findElement(By.xpath(
				"//a[text()='" + Lastname + "']/ancestor::tr[@bgcolor='white']/descendant::a[contains(text(),'del')]"))
				.click();
		// Handle the popup
		ClassObject_Utility.getTest().log(Status.INFO, "handling delete popup ");
		w_util.HandleAlertAndAccept(driver);
		soft.assertAll();
	}
}