package com.automation.web.components.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.automation.ui.selenium.DriverActions;
import com.automation.web.elementrepository.LoginAndSignupPageOR;

public class LoginPage extends LoginAndSignupPageOR{

	private DriverActions da;

	public void clickOnLoginButton(String elementName) {

		da.clickElement(googleLoginBtn,elementName);

	}
	public void inputEmail_signInPage(String inputemail) {
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

	public void clickOnLogoutBtn(String elementName) {
		da.clickElement(logoutBtn,elementName);
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
