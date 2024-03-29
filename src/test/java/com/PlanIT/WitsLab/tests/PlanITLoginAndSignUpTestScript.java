package com.PlanIT.WitsLab.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.PlanIT.WitsLab.pages.BacklogModule;
import com.PlanIT.WitsLab.pages.LoginPage;
import com.PlanIT.WitsLab.pages.ProjectAndBoardDashboard;
import com.aventstack.extentreports.Status;

public class PlanITLoginAndSignUpTestScript extends BaseTestSuite{



	@Test(priority = 1,enabled=true)
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
		das.switchWindow(das.getWebDriver());
		lp.inputEmail_googleBtn("gaurav.dubey@thewitslab.com","Email Field");
		lp.clickOnLogin_googleBtn("Next");
		lp.inputPassword_googleBtn("Gaurav@1998","Password field");
		lp.clickOnLogin_googleBtn("Next");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		das.switchWindow(das.getWebDriver());
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

	//	lp.inputEmail_signInPage(loginAndSignUp.getJSONObject("login").getString("Email"));
		//lp.inputPassword_signInPage(loginAndSignUp.getJSONObject("login").getString("Password"));


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

	//	lp.inputNameForSignUp(loginAndSignUp.getJSONObject("SignUp").getString("Name"));
		//lp.inputEmailForSignUp(loginAndSignUp.getJSONObject("SignUp").getString("Email"));
//		lp.inputPasswordForSignUp(loginAndSignUp.getJSONObject("SignUp").getString("Password"));
//		lp.clickOnCreateAccountButton();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String url=das.getBrowserURL();
		das.uiText_validation(url,"https://projectmanagement-uat.thewitslab.com/login");
		Assert.assertEquals(url, "https://projectmanagement-uat.thewitslab.com/login");

	}


	@Test(priority=10,enabled = false)
	public void verifyMoveTicketFunctionalityFromBacklogToSprint() {

		try {

			ProjectAndBoardDashboard projectBoard=new ProjectAndBoardDashboard(das);

			projectBoard.openFirstProject();

			WebElement createdBoard=projectBoard.createNewBoard();
			das.clickElement(createdBoard, createdBoard.getText());

			BacklogModule backlog=new BacklogModule(das);


			backlog.createNewSprint();
			backlog.clickOnActivateSprintButton();

			backlog.createNewIssueFromBacklog();
			Thread.sleep(3000);
			backlog.moveToKebabMenuInBacklog();
			backlog.clickOnMoveToSprintButton();
			Thread.sleep(4000);
			backlog.clickOnAddButton();
			Thread.sleep(4000);
//			backlog.clickOnNewCreatedSprint();

			backlog.openFirstTicket();
			Thread.sleep(3000);
			String ticketStage=backlog.textOfTicketStage();
//			das.uiText_validation(ticketStage, "TODO");
//			String todo= "TODO";
//			
			System.out.println(ticketStage);
			Assert.assertTrue(backlog.isTicketStageVisible());
//			Assert.assertEquals(ticketStage, "TODO");


		}catch (Exception e) {
			das.etest.log(Status.FAIL, "TicketNotMoved");
			Assert.assertEquals(true, false);

		}}

	@Test(priority=11,enabled = false)
	public void verifySwimLane() {
		try {

			ProjectAndBoardDashboard projectBoard=new ProjectAndBoardDashboard(das);

			projectBoard.openFirstProject();

			WebElement createdBoard=projectBoard.createNewBoard();
			das.clickElement(createdBoard, createdBoard.getText());

			BacklogModule backlog=new BacklogModule(das);
			//			backlog.createNewIssueFromBacklog();

			backlog.createNewSprint();
			backlog.clickOnActivateSprintButton();

			backlog.clickOnBoardModule();

			backlog.clickOnBoardCreateIssueButton();
//			String issueName=	backlog.inputIssueTitle(createIssue.getJSONObject("createIssue").getString("issueTitle"));
//
//			backlog.selectIssueType(createIssue.getJSONObject("createIssue").getString("issueType"));

			backlog.clickOnCreateButton();
		//	WebElement createdIssueName=backlog.getTODOlistFromBoadModule(issueName);
			//WebElement des=das.getWebDriver().findElement(By.xpath("(//div[contains(@class,'flex flex-col')])[9]//button"));
//			das.dragAndDrop(createdIssueName, des);
			//das.dragAndDropByHold(createdIssueName, des);
			//			String ticketStage=backlog.textOfTicketStage();
			//			das.uiText_validation(ticketStage, "TODO");
			//			Assert.assertEquals(ticketStage, "TODO");

		}catch (Exception e) {
			das.etest.log(Status.FAIL, "TicketNotMoved");
			Assert.assertEquals(true, false);

		}
	}
	public void verifyTheUpdatedStageOfTheTicket() {
		try {

			ProjectAndBoardDashboard projectBoard=new ProjectAndBoardDashboard(das);

			projectBoard.openFirstProject();

			WebElement createdBoard=projectBoard.createNewBoard();
			das.clickElement(createdBoard, createdBoard.getText());

			Thread.sleep(2000);

			BacklogModule backlog=new BacklogModule(das);

			backlog.createNewSprint();

			backlog.clickOnActivateSprintButton();

			Thread.sleep(3000);
			backlog.clickOnBoardModule();


			backlog.clickOnBoardCreateIssueButton();
		//	String issueName=	backlog.inputIssueTitle(createIssue.getJSONObject("createIssue").getString("issueTitle"));

				backlog.clickOnCreateButton();

//			WebElement  createdIssueName=backlog.getTODOlistFromBoad(issueName);
//			das.clickElement(createdIssueName, "created issue");
			
//			das.uiText_validation(createdIssueName, issueName);
//			Assert.assertEquals(createdIssueName, issueName);

		}catch (Exception e) {
			das.etest.log(Status.FAIL, "Issue not created");
			Assert.assertEquals(true, false);

		}

}
	
}
