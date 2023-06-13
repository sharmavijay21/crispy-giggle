package com.PlanIT.WitsLab.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.PlanIT.WitsLab.elementrepository.LoginAndSignupPageOR;
import com.PlanIT.WitsLab.ui.selenium.DriverActions;

public class LoginPage extends LoginAndSignupPageOR{

	private DriverActions da;

	public void clickOnLoginButton(String elementName) {

		da.clickElement(googleLoginBtn,elementName);

	}
	public void inputEmail_signInPage(String inputemail) {
		da.webDriverWait(email_signInPage);
		da.sendKeys(email_signInPage, inputemail,"Email Field");;
	}
	public void inputPassword_signInPage(String inputPassword) {
		da.sendKeys(pass_signInPage, inputPassword,"Password Field");
	}
	public void clickOnLogin_signInPage() {
		da.clickElement(loginBtn_signInPage,"SignIn Button");
	}

	public void inputEmail_googleBtn(String inputemail,String elementName) {
		da.sendKeys(email_googleBtn, inputemail,elementName);;
	}
	public void inputPassword_googleBtn(String inputPassword,String elementName) {
		da.sendKeys(pass_googleBtn, inputPassword,elementName);
	}
	public void clickOnLogin_googleBtn(String elementName) {
		da.clickElement(loginBtn_googleBtn,elementName);
	}

	public void clickOnLogoutBtn() {
		try {
			da.scrollDown(logoutBtn);
			da.webDriverWait(logoutBtn);
			da.ClickElementJavaScript(logoutBtn,"LogOut");
		}catch (Exception e) {
			new BacklogModule(da).closePopUp();
			da.clickElement(logoutBtn,"LogOut");
		}
	}

	public void inputNameForSignUp(String name) {
		da.sendKeys(nameSignUp,name ,"Name Field");

	}
	public void inputEmailForSignUp(String email) {
		da.sendKeys(emailSignUp, email,"Email Field");

	}
	public void inputPasswordForSignUp(String password) {
		da.sendKeys(passwordSignUp,password,"Password Field");

	}
	public void clickOnCreateAccountButton() {
		da.clickElement(createAccount,"Create Account");

	}
	public void clickOnCreateNewAccountButton() {
		da.scrollToElement(createNewAccount, "Create New Account");
		da.clickElement(createNewAccount,"Create New Account");

	}

	public LoginPage(DriverActions das) {
		this.da=das;
		PageFactory.initElements(da.getWebDriver(), this);
	}

}
