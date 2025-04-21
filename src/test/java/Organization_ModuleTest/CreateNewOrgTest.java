package Organization_ModuleTest;

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
import PomPages.CreateNewOrganization;
import PomPages.HomePage;
import PomPages.OrgDetailPomPage;
import PomPages.OrganizationPomPage;

@Listeners(Liseners.Utility.LisenersClass.class)
public class CreateNewOrgTest extends Baseclass {
	@Test(groups = "smoke", retryAnalyzer = Liseners.Utility.RetryAnalyser_Utility.class)
	public void createOrgTest() throws IOException, InterruptedException {
		ClassObject_Utility.getTest().log(Status.INFO, "Fetch data from Excel");
		// Fetch data from excel file
		Excel_Utility ex_util = new Excel_Utility();
		Java_Utility j_util = new Java_Utility();
		int random = j_util.getRandomNumber();
		String Orgname = ex_util.FetchDataFromExcelFile("Organisation", 1, 2) + random;

		WebDriver_Utility w_util = new WebDriver_Utility();

		// Identify org tab in home page and click
		ClassObject_Utility.getTest().log(Status.INFO, "Verifying home page");
		HomePage home = new HomePage(driver);
		boolean exp_res_home = home.getHeader().contains("Home");
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(exp_res_home, true);
		ClassObject_Utility.getTest().log(Status.INFO, "Navigate to an org page");
		home.getOrg_tab();

		// Identify plus button and click
		ClassObject_Utility.getTest().log(Status.INFO, "Navigate to create new org page");
		OrganizationPomPage org = new OrganizationPomPage(driver);
		org.getPlusicon();

		// Enter org name in create new org page and save
		ClassObject_Utility.getTest().log(Status.INFO, "create new org");
		CreateNewOrganization newcon = new CreateNewOrganization(driver);
		newcon.getOrgname_TF(Orgname);
		newcon.getSaveBtn();

		// Verify actual org name with expected org name
		ClassObject_Utility.getTest().log(Status.INFO, "Verify org name");
		OrgDetailPomPage orgdetail = new OrgDetailPomPage(driver);
		boolean exp_res_org = orgdetail.getVerifyOrganme().contains(Orgname);
		Assert.assertEquals(exp_res_org, true);

		// Click on org tab and delete the created org
		ClassObject_Utility.getTest().log(Status.INFO, "Navigate to org tab and delete");
		home.getOrg_tab();

		driver.findElement(
				By.xpath("//a[text()='" + Orgname + "']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
				.click();

		// Handle the popup
		Thread.sleep(3000);
		ClassObject_Utility.getTest().log(Status.INFO, "handling delete popup ");
		w_util.HandleAlertAndAccept(driver);
		soft.assertAll();

	}

	@Test(groups = "regression", retryAnalyzer = Liseners.Utility.RetryAnalyser_Utility.class)
	public void createOrgWithPhnNoTest() throws Exception {
		ClassObject_Utility.getTest().log(Status.INFO, "Fetch data from Excel");
		// Fetch data from excel file
		Excel_Utility ex_util = new Excel_Utility();
		Java_Utility j_util = new Java_Utility();
		int random = j_util.getRandomNumber();
		String orgname = ex_util.FetchDataFromExcelFile("Organisation", 4, 2) + random;
		String phno = ex_util.FetchDataFromExcelFile("Organisation", 4, 3);

		WebDriver_Utility w_util = new WebDriver_Utility();

		// Identify org tab in home page and click
		ClassObject_Utility.getTest().log(Status.INFO, "Verifying home page");
		HomePage home = new HomePage(driver);
		boolean exp_res_home = home.getHeader().contains("Home");
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(exp_res_home, true);

		ClassObject_Utility.getTest().log(Status.INFO, "navigate to org tab");
		home.getOrg_tab();

		// Identify plus button andd click
		ClassObject_Utility.getTest().log(Status.INFO, "navigate to create new org page");
		OrganizationPomPage org = new OrganizationPomPage(driver);
		org.getPlusicon();

		// Enter org name in create new org page and save
		ClassObject_Utility.getTest().log(Status.INFO, "Creating new org with phno");
		CreateNewOrganization neworg = new CreateNewOrganization(driver);
		neworg.getOrgname_TF(orgname);
		neworg.getOrgname_TF(phno);

		neworg.getSaveBtn();

		// Verify actual org name with expected org name
		ClassObject_Utility.getTest().log(Status.INFO, "verify org name and phno");
		OrgDetailPomPage orgdetail = new OrgDetailPomPage(driver);
		boolean exp_res_org = orgdetail.getHeader().contains(orgname);
		Assert.assertEquals(exp_res_org, true);

		boolean exp_res_phno = orgdetail.getVerifyOrgPhno().contains(phno);
		Assert.assertEquals(exp_res_phno, true);

		// Click on org tab and delete the created org
		ClassObject_Utility.getTest().log(Status.INFO, "navigate to org tab and delete");
		home.getOrg_tab();

		driver.findElement(
				By.xpath("//a[text()='" + orgname + "']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
				.click();
		Thread.sleep(3000);

		// Handle the popup
		ClassObject_Utility.getTest().log(Status.INFO, "handling delete popup ");
		w_util.HandleAlertAndAccept(driver);
		soft.assertAll();

	}

	@Test(groups = "regression", retryAnalyzer = Liseners.Utility.RetryAnalyser_Utility.class)
	public void createOrgWithIndAndType() throws Exception {
		ClassObject_Utility.getTest().log(Status.INFO, "Fetch data from Excel");
		// Fetch data from excel file
		Excel_Utility ex_util = new Excel_Utility();
		Java_Utility j_util = new Java_Utility();
		int random = j_util.getRandomNumber();
		String orgname = ex_util.FetchDataFromExcelFile("Organisation", 7, 2) + random;
		String industry = ex_util.FetchDataFromExcelFile("Organisation", 7, 3) + random;
		String type = ex_util.FetchDataFromExcelFile("Organisation", 7, 4) + random;

		WebDriver_Utility w_util = new WebDriver_Utility();

		// Identify org tab in home page and click
		ClassObject_Utility.getTest().log(Status.INFO, "Verifying home page");
		HomePage home = new HomePage(driver);
		boolean exp_res_home = home.getHeader().contains("Home");
		SoftAssert soft = new SoftAssert();
		soft.assertEquals(exp_res_home, true);
		ClassObject_Utility.getTest().log(Status.INFO, "navigate to org tab");
		home.getOrg_tab();

		// Identify plus button andd click
		ClassObject_Utility.getTest().log(Status.INFO, "navigate to create new org page");
		OrganizationPomPage org = new OrganizationPomPage(driver);
		org.getPlusicon();

		// Enter org name in create new org page and save
		ClassObject_Utility.getTest().log(Status.INFO, "Creating new org with industry and type");
		CreateNewOrganization neworg = new CreateNewOrganization(driver);
		neworg.getOrgname_TF(orgname);
		WebElement ind_dd = neworg.getOrgIndustryDD();
		WebElement type_dd = neworg.getOrgTypeDD();

		w_util.HandleDropdownUsingValue(ind_dd, industry);
		w_util.HandleDropdownUsingValue(type_dd, type);

		neworg.getSaveBtn();

		// Verify actual org name with expected org name
		ClassObject_Utility.getTest().log(Status.INFO, "verify org name");
		OrgDetailPomPage orgdetail = new OrgDetailPomPage(driver);
		boolean exp_res_org = orgdetail.getHeader().contains(orgname);
		Assert.assertEquals(exp_res_org, true);

		// Verify actual industry with expected industry
		ClassObject_Utility.getTest().log(Status.INFO, "verify industry and type");
		boolean exp_ind = orgdetail.getVerifyIndustry().contains(industry);
		Assert.assertEquals(exp_ind, true);

		// Verify actual type with expected type
		boolean exp_type = orgdetail.getVerifyType().contains(type);
		Assert.assertEquals(exp_type, true);

		// Click on org tab and delete the created org
		ClassObject_Utility.getTest().log(Status.INFO, "Navigate to org tab and delete");
		home.getOrg_tab();

		driver.findElement(
				By.xpath("//a[text()='" + orgname + "']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
				.click();
		Thread.sleep(3000);

		// Handle the popup
		ClassObject_Utility.getTest().log(Status.INFO, "handling delete popup ");
		w_util.HandleAlertAndAccept(driver);
		soft.assertAll();

	}

}
