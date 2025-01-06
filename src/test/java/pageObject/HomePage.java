package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage
{
	//constructor
		public HomePage(WebDriver driver)
		{
			super(driver);
		}
	
	
	
	
	//Locator
	@FindBy(xpath="//span[@class='caret']") WebElement MyAccount;
	@FindBy(xpath="//a[normalize-space()='Register']") WebElement Register;
	@FindBy(xpath="//a[normalize-space()='Login']") WebElement Login;
	

	//Actions
	
	public void clickMyAccount()
	{
		MyAccount.click();
	}
	
	public void clickRegister() 
	{
		Register.click();
	}
	
	public void clickLogin() {
		Login.click();
	}
	
}
