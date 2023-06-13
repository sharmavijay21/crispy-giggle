package com.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.web.components.pages.LoginPage;

public class PlanITLoginAndSignUpTestScript extends BaseTestSuite{



	@Test(priority = 1,enabled=false)
	public void verifyLoginButton() {
		LoginPage lp=new LoginPage(das)	;
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		lp.clickOnLoginButton("Sign In");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		das.switchWindow(driver);
		lp.inputEmail_googleBtn("gaurav.dubey@thewitslab.com","Email Field");
		lp.clickOnLogin_googleBtn("Next");
		lp.inputPassword_googleBtn("Gaurav@1998","Password field");
		lp.clickOnLogin_googleBtn("Next");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		das.switchWindow(driver);
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url=das.getBrowserURL();
		System.out.println(url);
		Assert.assertEquals(url,"https://projectmanagement-uat.thewitslab.com/");
	}
	@Test(priority = 2,enabled = false)
	public void verifyLoginThroughSignInPage() {
		//ThreadContext.put(THREADCONTEXT_ROUTINGKEY, browser.toLowerCase());
		LoginPage lp=new LoginPage(das)	;
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		lp.inputEmail_signInPage(loginAndSignUp.getJSONObject("login").getString("Email"));
		lp.inputPassword_signInPage(loginAndSignUp.getJSONObject("login").getString("Password"));


		//				lp.inputEmail_signInPage("sunil.kumar@thewitslab.com","Email Field");
		//				lp.inputPassword_signInPage("SunilSunil","Password Field");
		lp.clickOnLogin_signInPage();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String url=das.getBrowserURL();
		System.out.println(url);
		Assert.assertEquals(url,"https://projectmanagement-uat.thewitslab.com/");

	}
	@Test
	public void verifySignUpFunctionality() {

		LoginPage lp=new LoginPage(das);

		lp.clickOnCreateNewAccountButton();

		lp.inputNameForSignUp(loginAndSignUp.getJSONObject("SignUp").getString("Name"));
		lp.inputEmailForSignUp(loginAndSignUp.getJSONObject("SignUp").getString("Email"));
		lp.inputPasswordForSignUp(loginAndSignUp.getJSONObject("SignUp").getString("Password"));
		lp.clickOnCreateAccountButton();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String url=das.getBrowserURL();
		das.uiText_validation(url,"https://projectmanagement-uat.thewitslab.com/login");
		Assert.assertEquals(url, "https://projectmanagement-uat.thewitslab.com/login");

	}


}
