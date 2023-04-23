package testcases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Recordset;

import pages.LoginPage;

public class LoginTest extends BaseClass {
	
	@Test
	public void LoginPositive(Method method) throws FilloException, InterruptedException {
		
		Recordset recordset = connection.executeQuery("select * from data where TestName='" + method.getName() + "'");
		recordset.next();
			
		String UserName = recordset.getField("UserName");
		String Password = recordset.getField("Password");
		
		LoginPage login = new LoginPage(driver);
		login.LoginFunction(UserName, Password);
		String currentPageURL = driver.getCurrentUrl();
		String expectedPageURL = "https://www.saucedemo.com/inventory.html";
		Assert.assertEquals(currentPageURL, expectedPageURL);
		Thread.sleep(1000);

		
	}
	
	@Test
	public void LoginNegative(Method method) throws FilloException, InterruptedException {
		
		Recordset recordset = connection.executeQuery("select * from data where TestName='" + method.getName() + "'");
		recordset.next();
			
		String UserName = recordset.getField("UserName");
		String Password = recordset.getField("Password");
		
		LoginPage login = new LoginPage(driver);
		login.LoginFunction(UserName, Password);
		
		String actualErrorMsg = driver.findElement(By.xpath("//div[@class='error-message-container error']//h3")).getText();
		String expectedErrorMsg = "Epic sadface: Username and password do not match any user in this service";
		
		Assert.assertEquals(actualErrorMsg, expectedErrorMsg);
		Thread.sleep(1000);
		
	}

}
