package GenericUtilities;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class WebDriver_Utility {
	/**
	 * 
	 * @param driver
	 */
public void maximizeTheWindow(WebDriver driver)
{
	driver.manage().window().maximize();
}

public void waitTillElementFound(String timeouts,WebDriver driver) {
	long time=Long.parseLong(timeouts);
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
}
//navigate to an application
public void navigateToAnAppln(WebDriver driver,String url) {
	driver.get(url);
}
public void HandleAlertAndAccept(WebDriver driver) {
	driver.switchTo().alert().accept();
}
public void HandleAlertAndCancel(WebDriver driver) {
	driver.switchTo().alert().dismiss();
}
public String HandleAlertAndFetchText(WebDriver driver) {
	String text=driver.switchTo().alert().getText();
	return text;
}
public void HandleAlertAndPassText(WebDriver driver,String text) {
	
	driver.switchTo().alert().sendKeys(text);
}
public void Action_MouseHovering(WebDriver driver,WebElement element)
{
	Actions act=new Actions(driver);
	act.moveToElement(element).perform();
	
}
public void QuitTheBrowser(WebDriver driver) {
	driver.quit();
	
}
public void closeTheBrowser(WebDriver driver) {
	driver.close();
}
public void HandleDropdownUsingIndex(WebElement dropdown,int index) {
	Select s=new Select(dropdown);
	s.selectByIndex(index);}
public void HandleDropdownUsingValue(WebElement dropdown,String value) {
	Select s=new Select(dropdown);
	s.selectByValue(value);}

public void HandleDropdownUsingVisibleText(WebElement dropdown,String text) {
	Select s=new Select(dropdown);
	s.selectByVisibleText(text);}
public void switchToTabUsingUrl(WebDriver driver,String url) {
	Set<String> wids=driver.getWindowHandles();
	for(String s :wids) {
		driver.switchTo().window(s);
		if(driver.getCurrentUrl().contains(url)) {
			break;
		}
	}
}
public void switchToTabUsingTitle(WebDriver driver,String title) {
	Set<String> wids=driver.getWindowHandles();
	for(String s :wids) {
		driver.switchTo().window(s);
		if(driver.getTitle().contains(title)) {
			break;
		}
	}
	}
public void switchBackToParentWindow(WebDriver driver,String pwid) {
	driver.switchTo().window(pwid);
}
	
	
	
	
	
	
	
	
	
	
	
}

