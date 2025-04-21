package BaseclassUtility;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import BaseclassUtility.Baseclass;
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
public class CreateContactTest extends Baseclass{
	@Test(groups="smoke",retryAnalyzer = Liseners.Utility.RetryAnalyser_Utility.class)
		public void CreateContact() throws IOException {
			
			
			// FETCH data from Excel
			Excel_Utility ex_util=new Excel_Utility();
			Java_Utility j_util=new Java_Utility();
			int random=j_util.getRandomNumber();
			String lastname=ex_util.FetchDataFromExcelFile("Contact",1, 2)+random; 
			WebDriver_Utility w_util=new WebDriver_Utility();
			
			
			// Identigy contacts tab in home page and click on it
			HomePage home=new HomePage(driver);
			 boolean exp_res=home.getHeader().contains("home");
			SoftAssert soft = new SoftAssert();
			soft.assertEquals(exp_res,true);
			
			home.getCont_tab();
			
			// Identify plus button and click on it
			
			
			ContactPomPage con=new ContactPomPage(driver);
			
			
			con.getPlusicon();
			
			//enter contact name in create new org page and save
			CreateNewContactPomPage newcon=new CreateNewContactPomPage(driver);
			newcon.getLastname_TF(lastname);
			newcon.getSaveBtn();
			
			// verify ecpected result with actual result
			ContactDetailsPomPage condetail=new ContactDetailsPomPage(driver);
			
			 boolean exp_res1 = condetail.getHeader().contains(lastname);
			 Assert.assertEquals( exp_res1,true);
			
			home.getCont_tab();
			driver.findElement(By.xpath(
					"//a[text()='" + lastname + "']/ancestor::tr[@bgcolor='white']/descendant::a[contains(text(),'del')]"))
					.click();
			// Handle the popup
			w_util. HandleAlertAndAccept( driver); 
			soft.assertAll();
		}

}
