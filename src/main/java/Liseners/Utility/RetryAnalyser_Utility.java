package Liseners.Utility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyser_Utility implements IRetryAnalyzer {
	int count = 0;
	int max_retry = 5;

	public boolean retry(ITestResult result) {
		if (count < max_retry) {
			count++;
			return true;
		}
		return false;
	}
}
