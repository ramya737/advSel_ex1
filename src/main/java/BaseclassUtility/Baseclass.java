package BaseclassUtility;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.Status;

import GenericUtilities.ClassObject_Utility;
import GenericUtilities.DataBase_Utility;
import GenericUtilities.Property_Utility;
import GenericUtilities.WebDriver_Utility;
import PomPages.HomePage;
import PomPages.LoginPomPage;

public class Baseclass {
	Property_Utility pro = new Property_Utility();
	DataBase_Utility db = new DataBase_Utility();
	WebDriver_Utility w_util = new WebDriver_Utility();
	public WebDriver driver = null;
	public static WebDriver sdriver = null;

	@BeforeSuite(groups= {"smoke","Regression"})
	public void beforeSuit() {
		Reporter.log("Configure the DB:Connect", true);
		ClassObject_Utility.getTest().log(Status.INFO, "Configure the DB:Connect");
		db.getDatabaseConnection();
	}


	@BeforeTest(groups= {"smoke","Regression"})
	public void beforeTest() {
		Reporter.log("BT:Parallel Exe", true);
		ClassObject_Utility.getTest().log(Status.INFO, "BT:Parallel Exe");
	}
	//@Parameters("browser")
	@BeforeClass(groups= {"smoke","Regression"})
	public void beforeClass() throws IOException {
		Reporter.log("Launch the browser", true);
		ClassObject_Utility.getTest().log(Status.INFO, "Launch the browser");
		//String Browser = pro.FetchDataFromPropFile("browser");
		String Browser=System.getProperty("browser",pro.FetchDataFromPropFile("browser"));
		if (Browser.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (Browser.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}
		sdriver = driver;
		ClassObject_Utility.setDriver(driver);
	}

	@BeforeMethod(groups= {"smoke","Regression"})
	public void beforeMethod() throws IOException {
		Reporter.log("Login to the appln", true);
		ClassObject_Utility.getTest().log(Status.INFO, "Login to the appln");
		//String url = pro.FetchDataFromPropFile("url");
		//String username = pro.FetchDataFromPropFile("username");
		//String pwd = pro.FetchDataFromPropFile("password");
		String Timeouts = pro.FetchDataFromPropFile("timeouts");
		String url=System.getProperty("url",pro.FetchDataFromPropFile("url"));
		String username=System.getProperty("username",pro.FetchDataFromPropFile("username"));
		String pwd=System.getProperty("password",pro.FetchDataFromPropFile("password"));
		LoginPomPage l = new LoginPomPage(driver);
		//exicution lo maven lo username,password,url ivvali
		w_util.navigateToAnAppln(driver, url);
		w_util.maximizeTheWindow(driver);

		l.login(username, pwd);
		
		w_util.waitTillElementFound(Timeouts, driver);
	}

	@AfterMethod(groups= {"smoke","Regression"})
	public void afterMethod() {
		Reporter.log("Logout of the appln", true);
		ClassObject_Utility.getTest().log(Status.INFO, "Logout of the appln");
		HomePage home = new HomePage(driver);
		home.logout(driver);
	}

	@AfterClass(groups= {"smoke","Regression"})
	public void afterClass() {
		Reporter.log("Close the browser", true);
		ClassObject_Utility.getTest().log(Status.INFO, "Close the browser");
		WebDriver_Utility wb=new WebDriver_Utility();
		wb.QuitTheBrowser(driver);
	}

	@AfterTest(groups= {"smoke","Regression"})
	public void afterTest() {
		Reporter.log("AT:Parallel Exe", true);
		ClassObject_Utility.getTest().log(Status.INFO, "AT:Parallel Exe");
	}

	@AfterSuite(groups= {"smoke","Regression"})
	public void afterSuit() {
		Reporter.log("Close DB connection", true);
		ClassObject_Utility.getTest().log(Status.INFO, "Close DB Connection");
		db.closeDatabaseConnection();
	}

}
