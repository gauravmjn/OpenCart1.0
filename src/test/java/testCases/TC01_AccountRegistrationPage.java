package testCases;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.*;

import pageObject.AccountRegistrationPage;
import pageObject.HomePage;
import testBase.BaseClass;

public class TC01_AccountRegistrationPage extends BaseClass
{
	
	@Test(groups= {"regression","master"})
	public void verify_account_registration() 
	{
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickRegister();
		
		AccountRegistrationPage ar=new AccountRegistrationPage(driver);
		
		String generatedPassword = randomAlphaNumeric();
		
		ar.firstName(randomString());
		
		ar.lastName(randomString());
		
		ar.email(randomString()+"@gmail.com");
		
		ar.telephone(randomNumeric());
		
		ar.password(generatedPassword);
		
		ar.confirmPassword(generatedPassword);
		
		ar.agree();
		
		ar.done();
		
		
		Assert.assertEquals(ar.confirmation(), "Your Account Has Been Created!");
		
	}
	
	
}
