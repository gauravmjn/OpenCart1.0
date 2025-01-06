package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage
{

	//constructor
		public AccountRegistrationPage(WebDriver driver) {
			super(driver);
		}
	
	
	//Locator
	@FindBy(xpath="//input[@id='input-firstname']") WebElement txt_fname;
	
	@FindBy(xpath="//input[@id='input-lastname']") WebElement txt_lname;
	
	@FindBy(xpath="//input[@id='input-email']") WebElement txt_email;
	
	@FindBy(xpath="//input[@id='input-telephone']") WebElement txt_telephone;
	
	
	@FindBy(xpath="//input[@id='input-password']") WebElement txt_password;
	
	@FindBy(xpath="//input[@id='input-confirm']") WebElement con_password;
	
	@FindBy(xpath="//input[@name='agree']") WebElement btn_agree;
	
	@FindBy(xpath="//input[@value='Continue']") WebElement btn_continue;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']") WebElement confirmation_msg;
	
	
	
	//Actions
	
	public void firstName(String fname) {
		txt_fname.sendKeys(fname);
	}
	
	public void lastName(String lname) {
		txt_lname.sendKeys(lname);
	}
	
	public void email(String email) {
		txt_email.sendKeys(email);
	}
	
	public void telephone(String phno) {
		txt_telephone.sendKeys(phno);
	}
	
	public void password(String pswd) {
		txt_password.sendKeys(pswd);
	}
	
	public void confirmPassword(String psd) {
		con_password.sendKeys(psd);
	}
	
	public void agree(){
		btn_agree.click();
	}
	
	public void done(){
		btn_continue.click();
	}
	
	
	public String confirmation() {
		try {
		String msg=confirmation_msg.getText();
		return msg;
		}
		catch(Exception e) {
			return e.getMessage()
;		}
	}
}
