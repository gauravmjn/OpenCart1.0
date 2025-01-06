package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import testBase.BaseClass;

public class TC02_LoginTest extends BaseClass {
	
	@Test(groups= {"sanity","regression","master"})
	public void verify_login() {
		//HomePage
		HomePage hp= new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//LoginPage
		LoginPage lp = new LoginPage(driver);
		lp.loginEmail(p.getProperty("email"));
		lp.loginPassword(p.getProperty("password"));
		lp.login();
		
		
		//MyAccount
		MyAccountPage acc= new MyAccountPage(driver);
		boolean status=acc.isMyAcccountPageExists();
		Assert.assertEquals(status, true,"Login Failed");
		//or
		//Assert.assertTrue(status);
		
	}
	
	

}
