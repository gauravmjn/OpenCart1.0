package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage 
{
	
		public  LoginPage(WebDriver driver) {
			super(driver);
		}
		
		
@FindBy(xpath="//input[@id='input-email']") WebElement emailAddress;	
@FindBy (xpath="//input[@id='input-password']") WebElement password;
@FindBy (xpath="//input[@value='Login']") WebElement btn_login;


public void loginEmail(String email) {
	emailAddress.sendKeys(email);
}
 	public void loginPassword(String psd) {
 		password.sendKeys(psd);
 	
 	}
 	
 	public void login() {
 		btn_login.click();
 	}

}
