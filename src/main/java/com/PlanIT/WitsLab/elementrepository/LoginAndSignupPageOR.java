package com.PlanIT.WitsLab.elementrepository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginAndSignupPageOR {
	
	@FindBy(xpath="//button[text()='Google']")
	protected WebElement googleLoginBtn;

	@FindBy(xpath = "//input[@name='email']")
	protected WebElement email_signInPage;

	@FindBy(xpath = "//input[@name='password']")
	protected WebElement pass_signInPage;

	@FindBy(xpath="//button[text()='Sign In']")
	protected WebElement loginBtn_signInPage;

////
	
	@FindBy(xpath = "//input[@aria-label='Email or phone']")
	protected WebElement email_googleBtn;

	@FindBy(xpath = "//input[@aria-label='Enter your password']")
	protected WebElement pass_googleBtn;
	
	@FindBy(xpath="//span[text()='Next']")
	protected WebElement loginBtn_googleBtn;

	@FindBy(xpath="//span[@aria-label='logout']")
	protected WebElement logoutBtn;

	@FindBy(xpath="//small[text()='Create new account']")
	protected WebElement createNewAccount;
	
	@FindBy(xpath="//input[@placeholder='Name']")
	protected WebElement nameSignUp;

	@FindBy(xpath="//input[@placeholder='Email']")
	protected WebElement emailSignUp;

	@FindBy(xpath="//input[@placeholder='Password']")
	protected WebElement passwordSignUp;

	@FindBy(xpath="//button[text()='Create Account']")
	protected WebElement createAccount;

	@FindBy(xpath="//h6[text()='Sign in with Google']")
	public WebElement loginPageHeader;

	
	
}
