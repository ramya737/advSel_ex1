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
import PomPages.HomePage;
import PomPages.LoginPomPage;
import junit.framework.Assert;
@Listeners(Liseners.Utility.LisenersClass.class)
public class CreatewithSuppDateTest extends Baseclass {
	@Test

	public void CreatewithSuppDateT() throws IOException, InterruptedException {
		WebDriver_Utility w_util = new WebDriver_Utility();
		ClassObject_Utility.getTest().log(Status.INFO,"Fetch data from Excel");
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

		 //driver.findElement(By.name("lastname")).sendKeys(lastname);
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
		 Assert.assertEquals(exp_res_con,true);
		 
		 
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

		