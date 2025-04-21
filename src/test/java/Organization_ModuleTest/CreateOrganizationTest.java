package Organization_ModuleTest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import BaseclassUtility.Baseclass;
import GenericUtilities.ClassObject_Utility;
import GenericUtilities.Excel_Utility;
import GenericUtilities.Java_Utility;
import GenericUtilities.WebDriver_Utility;
import PomPages.CreateNewOrganization;
import PomPages.HomePage;
import PomPages.OrgDetailPomPage;
import PomPages.OrganizationPomPage;

public class CreateOrganizationTest extends Baseclass {
	@Test(groups = "smoke", retryAnalyzer = Liseners.Utility.RetryAnalyser_Utility.class)
	public void createOrgTest() throws IOException, InterruptedException {
		ClassObject_Utility.getTest().log(Status.INFO,"Fetch data from Excel");
		// Fetch data from excel file
		Excel_Utility ex_util = new Excel_Utility();
		Java_Utility j_util = new Java_Utility();
		int random = j_util.getRandomNumber();
		String orgname = ex_util.FetchDataFromExcelFile("Organisation", 1, 2) + random;

		WebDriver_Utility w_util = new WebDriver_Utility();

		// Identify org tab in home page and click
		ClassObject_Utility.getTest().log(Status.INFO,"Verifying home page");
		HomePage home = new HomePage(driver);
		boolean exp_res_home = home.getHeader().contains("Home");
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(exp_res_home, true);
		ClassObject_Utility.getTest().log(Status.INFO,"Navigate to an org page");
		home.getOrg_tab();

		// Identify plus button and click
		ClassObject_Utility.getTest().log(Status.INFO,"Navigate to create new org page");
		OrganizationPomPage org = new OrganizationPomPage(driver);
		org.getPlusicon();

		// Enter org name in create new org page and save
		ClassObject_Utility.getTest().log(Status.INFO,"create new org");
		CreateNewOrganization newcon = new CreateNewOrganization(driver);
		newcon.getOrgname_TF(orgname);
		newcon.getSaveBtn();

		// Verify actual org name with expected org name
		ClassObject_Utility.getTest().log(Status.INFO,"Verify org name");
		OrgDetailPomPage orgdetail = new OrgDetailPomPage(driver);
		boolean exp_res_org = orgdetail.getVerifyOrganme().contains(orgname);
		Assert.assertEquals(exp_res_org, true);

		// Click on org tab and delete the created org
		ClassObject_Utility.getTest().log(Status.INFO,"Navigate to org tab and delete");
		home.getOrg_tab();

		driver.findElement(
				By.xpath("//a[text()='" + orgname + "']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
				.click();

		// Handle the popup
		Thread.sleep(3000);
		ClassObject_Utility.getTest().log(Status.INFO,"handling delete popup ");
		w_util.HandleAlertAndAccept(driver);
		soft.assertAll();

	}

}
