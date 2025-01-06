package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC03_LoginDDT extends BaseClass 
{
	
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups= {"master"})//getting data provider from different class
	public void verify_loginDDT(String email, String psd, String exp ) {
		try {
		HomePage hp= new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin(); 
		
		LoginPage lp = new LoginPage(driver);
		lp.loginEmail(email);
		lp.loginPassword(psd);
		lp.login();
		
		//MyAccount 
		MyAccountPage macc= new MyAccountPage(driver);
		boolean targetPage=macc.isMyAcccountPageExists();
		
		/*
		 * Data is valid 	| ---login success --test pass---logout
		 *               	| ---login failed ---test fail
		 *               
		 * Data is Invalid  | ---login success--test fail--logout
		 *                 	| ---login fail --	test pass	        		
		 */
	
		
		if(exp.equalsIgnoreCase("valid")) 
		{
			if(targetPage==true)
			{
				macc.clickLogout();
				Assert.assertTrue(true);
				
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		if(exp.equalsIgnoreCase("invalid")) {
			if(targetPage==true)
			{
				macc.clickLogout();
				Assert.assertTrue(false);
			}
			else 
			{
				Assert.assertTrue(true);
			}
			
		
		}
		}
		catch(Exception e) 
		{
			Assert.fail();
		}
		
	}
}

		
	
	

