package Liseners.Utility;

import java.io.File;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import BaseclassUtility.Baseclass;
import GenericUtilities.ClassObject_Utility;

public class LisenersClass implements ITestListener, ISuiteListener {

	public ExtentReports report = null;
	public static ExtentTest test=null ;

	@Override
	public void onStart(ISuite suite) {
		Reporter.log("Report configuration", true);
//		 test.log(Status.INFO, "On suit Excution Startde");

		String time = new Date().toString().replace(" ", "_").replace(":", "_");
		// Report configuration(folder name,file name)
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvancedReport/demo" + time + ".html");
		spark.config().setDocumentTitle("VTiger Suite Execution Report");
		spark.config().setReportName("VTiger Report");
		spark.config().setTheme(Theme.DARK);
		// set the environment configuration or details
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS version", "Window-11");
		report.setSystemInfo("Browser version", "Chrome-135");
		test=report.createTest("VTiger Runtime Events");
		ClassObject_Utility.setTest(test);
	}

	@Override
	public void onFinish(ISuite suite) {
		Reporter.log("Report Backup", true);
		report.flush();
		test.log(Status.INFO, "Suit Excution Finished");
	}

	@Override
	public void onTestStart(ITestResult result) {
		Reporter.log(result.getMethod().getMethodName() + "----started--", true);
		test = report.createTest(result.getMethod().getMethodName());
		test.log(Status.INFO, result.getMethod().getMethodName() + "----started--");
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		Reporter.log(result.getMethod().getMethodName() + "--succes--", true);

		test.log(Status.INFO, result.getMethod().getMethodName() + "--success--");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testname = result.getMethod().getMethodName();
		Reporter.log(testname + "--failed--", true);
		test.log(Status.FAIL, testname + "--failed--");
		
		String time = new Date().toString().replace("", "_").replace(":", "_");
		TakesScreenshot Ts = (TakesScreenshot) Baseclass.sdriver;
		String filepath = Ts.getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String(filepath, testname + "_" + time);

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		Reporter.log(result.getMethod().getMethodName() + "--skipped--", true);
		test.log(Status.INFO, result.getMethod().getMethodName() + "--skipped--");
	}

}
