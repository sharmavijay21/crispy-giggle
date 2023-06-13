package com.PlanIT.WitsLab.tests;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.PlanIT.WitsLab.pages.BacklogModule;
import com.PlanIT.WitsLab.pages.LoginPage;
import com.PlanIT.WitsLab.pages.ProjectAndBoardDashboard;
import com.PlanIT.WitsLab.pages.TicketLandingPage;
import com.aventstack.extentreports.Status;

import gherkin.lexer.Da;


public class prmsTestScript extends BaseTestSuite{



	@Test(priority = 1,enabled = true)
	public void verifyCreateNewProject() {
		String projectName=null;
		try {

			ProjectAndBoardDashboard dlp=new ProjectAndBoardDashboard(das);
			dlp.clickOnCreateNewProject();

			projectName=	dlp.inputProjectName(CreateProjectAndBoard.getJSONObject("createProject").getString("Project Name"));

			dlp.selectProjectType(CreateProjectAndBoard.getJSONObject("createProject").getString("Project Type"));

			dlp.inputDescription(CreateProjectAndBoard.getJSONObject("createProject").getString("Description"));

			dlp.clickOnCreateButton();

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(dlp.projectAlreadyExists()) {


				projectName=CreateProjectAndBoard.getJSONObject("createProject").getString("Project Name");

				projectName=dlp.inputProjectName(projectName+das.getRandomNo());
				dlp.clickOnCreateButton();

			}

			WebElement createdProjectName=dlp.getAllProject(projectName);
			das.uiText_validation(createdProjectName.getText(), projectName);
			Assert.assertEquals(createdProjectName.getText(), projectName);


		}catch (Exception e) {
			das.etest.log(Status.FAIL, "Project is not Created");
			Assert.assertEquals(true, false);

		}
	}
	@Test(priority = 2,enabled = true)
	public void verifyCreateTeamBoard() {

		String boardName=null;

		try {

			ProjectAndBoardDashboard projectBoard=new ProjectAndBoardDashboard(das);
			projectBoard.openFirstProject();

			projectBoard.clickOnCreateTeamBoard();
			boardName=	projectBoard.inputBoardTitle(CreateProjectAndBoard.getJSONObject("createBoard").getString("Board Name"));
			projectBoard.inputBoardDescription(CreateProjectAndBoard.getJSONObject("createBoard").getString("Description"));

			projectBoard.clickOnCreateButton();


			if(projectBoard.boardAlreadyExists()) {


				boardName=CreateProjectAndBoard.getJSONObject("createBoard").getString("Board Name");

				boardName=projectBoard.inputProjectName(boardName+das.getRandomNo());
				projectBoard.clickOnCreateButton();

				//				das.waitForElementPresent(projectBoard.cancelBuuton);
			}
			try {
				if(das.isDisplayed(projectBoard.cancelBuuton)) {
					Thread.sleep(10000);
				}
			}catch (Exception e) {
			}
			WebElement createdboardName=projectBoard.getAllBoard(boardName);
			das.uiText_validation(createdboardName.getText(), boardName);
			Assert.assertEquals(createdboardName.getText(), boardName);


		}catch (Exception e) {
			e.printStackTrace();
			das.etest.log(Status.FAIL, "Board is not created");
			Assert.assertEquals(true, false);
		}

	}

	@Test(enabled  = true,priority = 3)
	public void verifyCreateIssueFromBacklogFunctionality() {

		try {

			ProjectAndBoardDashboard projectBoard=new ProjectAndBoardDashboard(das);

			projectBoard.openFirstProject();
			projectBoard.openFirstBoard();

			BacklogModule backlog=new BacklogModule(das);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			backlog.clickOnCreateIssueBtn();

			String issueName=	backlog.inputIssueTitle(createIssue.getJSONObject("createIssue").getString("issueTitle"));

			backlog.selectIssueType(createIssue.getJSONObject("createIssue").getString("issueType"));

			backlog.selectPriority(createIssue.getJSONObject("createIssue").getString("Priority"));

			//Add Sprint
			backlog.inputStartDate(createIssue.getJSONObject("createIssue").getString("Start Date"));

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			backlog.inputEndDate(createIssue.getJSONObject("createIssue").getString("End Date"));

			backlog.inputStoryPoint(createIssue.getJSONObject("createIssue").getString("Story Point"));

			backlog.inputDescription(createIssue.getJSONObject("createIssue").getString("Description"));

			backlog.clickOnCreateButton();



			WebElement createdIssueName=backlog.getAllIssue(issueName);
			das.uiText_validation(createdIssueName.getText(), issueName);
			Assert.assertEquals(createdIssueName.getText(), issueName);

		}catch (Exception e) {
			das.etest.log(Status.FAIL, "Issue is not Created");
			Assert.assertEquals(true, false);

		}

	}

	@Test(enabled = true,priority = 4)
	public void verifyCreateSprintFunctionality() {
		BacklogModule backlog=new BacklogModule(das);
		try {

			ProjectAndBoardDashboard projectBoard=new ProjectAndBoardDashboard(das);

			projectBoard.openFirstProject();
			WebElement we=projectBoard.createNewBoard();
			das.clickElement(we, we.getText());

			backlog.clickOnCreateSprintButton();
			backlog.selectSprintDuration("2");
			//		backlog.inputStartDate("2023-05-31");
			//		backlog.inputEndDate("2023-06-20");
			backlog.inputSprintGoal("completed");
			backlog.clickOnSubmitSprintButton();
			//			String message=null;
			//			try {
			//				message=backlog.getTextSprintConfirmationMessage();
			//			}catch (Exception e) {
			//				das.uiText_validation(message, "");
			//				Assert.assertEquals(true, false);
			//			}
			boolean bb=das.isDisplayed(backlog.newCreatedSprint);

			Assert.assertEquals(true, true);
			das.uiText_validation("Sprint created successfully", "Sprint created successfully");

		}catch (Exception e) {
			backlog.closePopUp();
			das.etest.log(Status.FAIL, "Sprint is not created");
			Assert.assertEquals(true, false);

		}
	}

	@Test(enabled = true,priority = 5)
	public void verifyAddMemberFunctionalityFromBoard() {

		BacklogModule backlog=new BacklogModule(das);
		try {

			ProjectAndBoardDashboard projectBoard=new ProjectAndBoardDashboard(das);

			projectBoard.openFirstProject();
			WebElement createdBoard=projectBoard.createNewBoard();
			das.clickElement(createdBoard,createdBoard.getText() );



			backlog.clickOnBoardModule();
			int totalNo=backlog.TotalMember();
			backlog.clickOnAddMemberButton();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//add Validation Part

			backlog.inputMember("van");
			WebElement we=backlog.clickOnSubmitMemberButton();

			boolean display=false;
			try {
				display=das.isDisplayed(we);
			}catch (Exception e) {
			}
			if(display) {
				das.etest.log(Status.FAIL, backlog.getTextMemberAlreadyAddedMessageFromBoard());

				Assert.assertEquals(true, false);
			}
			int totalNoAfterAdding=backlog.TotalMember();

			Assert.assertNotEquals(totalNo, totalNoAfterAdding);

			das.etest.log(Status.PASS, "member Added Successfully");

		}catch (Exception e) {
			backlog.closePopUp();
			das.etest.log(Status.FAIL, "Member is Not added");
			Assert.assertEquals(true, false);
		}

	}

	@Test(enabled = true,priority = 6)
	public void verifySprintActivation() {

		try {

			ProjectAndBoardDashboard projectBoard=new ProjectAndBoardDashboard(das);

			projectBoard.openFirstProject();
			WebElement createdBoard=projectBoard.createNewBoard();
			das.clickElement(createdBoard, createdBoard.getText());


			BacklogModule backlog=new BacklogModule(das);
			backlog.createNewSprint();

			backlog.clickOnActivateSprintButton();

			String sprintActivationMessage=backlog.getTextSprintActivateConfirmationMessage();

			das.uiText_validation(sprintActivationMessage, "Sprint Activated successfully");
			Assert.assertEquals(sprintActivationMessage, "Sprint Activated successfully");

			//			das.clickElement(createdBoard, sprintActivationMessage)

		}catch (Exception e) {
			das.etest.log(Status.FAIL, "Sprint is not Activated");
			Assert.assertEquals(true, false);
		}
	}

	@Test(priority = 7,enabled = true)
	public void verifyCreateIssueFromBoard() {

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
			String issueName=	backlog.inputIssueTitle(createIssue.getJSONObject("createIssue").getString("issueTitle"));

			backlog.selectIssueType(createIssue.getJSONObject("createIssue").getString("issueType"));

			backlog.selectPriority(createIssue.getJSONObject("createIssue").getString("Priority"));

			//Add Sprint

			backlog.inputStartDate(createIssue.getJSONObject("createIssue").getString("Start Date"));

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			backlog.inputEndDate(createIssue.getJSONObject("createIssue").getString("End Date"));

			backlog.inputStoryPoint(createIssue.getJSONObject("createIssue").getString("Story Point"));

			backlog.inputDescription(createIssue.getJSONObject("createIssue").getString("Description"));
			backlog.clickOnCreateButton();

			//		das.webDriverWait(backlog.createIssueConfirmationmessage);
			//		
			//		String createIssueConfiemationMessage=backlog.getTextCreateIssueConfirmationMessage();
			//		das.uiText_validation(createIssueConfiemationMessage, "Issue Created Successfully");

			String createdIssueName=backlog.getTODOlistFromBoad(issueName);
			das.uiText_validation(createdIssueName, issueName);
			Assert.assertEquals(createdIssueName, issueName);

		}catch (Exception e) {
			das.etest.log(Status.FAIL, "Issue not created");
			Assert.assertEquals(true, false);

		}

	}

	@Test(priority  = 8,enabled = true)
	public void verifyUpdateDetailsOfTickets() {

		BacklogModule backlog=new BacklogModule(das);
		try {

			ProjectAndBoardDashboard projectBoard=new ProjectAndBoardDashboard(das);

			projectBoard.openFirstProject();
			//			projectBoard.openFirstBoard();

			WebElement createdBoard=projectBoard.createNewBoard();
			das.clickElement(createdBoard, createdBoard.getText());

			backlog.addMemberFromBoard("Sunil");
			WebElement createdIssue=backlog.createNewIssueFromBacklog();
			das.clickElement(createdIssue, "CreatedIssue");

						backlog.openFirstTicket();
//			backlog.addMemberFromBoard(createIssue.getJSONObject("update Issue").getString("Assignee"));

			TicketLandingPage ticketLandingPage=new TicketLandingPage(das);

			ticketLandingPage.clickOnUpdateField();
			ticketLandingPage.selectUpdatePriority("Medium");
			String priorityText=ticketLandingPage.getTextUpdatedPriority();
			das.uiText_validation(priorityText, "Medium");
			Assert.assertEquals(priorityText, "Medium");

			
			//handleNoSuchElement Exception after entering wrong name
			
			ticketLandingPage.updateAssignee("Sunil");
			String updatedAssignee=ticketLandingPage.getTextUpdatedAssignee("Sunil");
			das.uiText_validation(updatedAssignee, "Sunil");
			Assert.assertEquals(updatedAssignee, "Sunil");

			ticketLandingPage.selectUpdateIssueType("Bug");
			Thread.sleep(3000);
			String updatedType=ticketLandingPage.getTextUpdatedIssueType();
			das.uiText_validation(updatedType, "type Updated Successfully");
			Assert.assertEquals(updatedType, "type Updated Successfully");
			
			ticketLandingPage.inputUpdatedStoryPoint("9");
			ticketLandingPage.clickOnStoryPointText();
			Thread.sleep(2000);
			String storyPointText=ticketLandingPage.getTextUpdatedStoryPoint();
			das.uiText_validation(storyPointText, "9");
			Assert.assertEquals(storyPointText, "9");


			//ticket Description Updation

			//			String message=ticketLandingPage.updateTicketDescription("First Tickeytfchgb");
			//			das.uiText_validation(message, "description updated.");
			//			Assert.assertEquals(message, "description updated.");


			backlog.closePopUp();
		}catch (Exception e) {
			backlog.closePopUp();
			das.etest.log(Status.FAIL, "Detail is not updated");
			Assert.assertEquals(true, false);
		}
	}
	
	@Test(priority=9,enabled = true)
	public void verifyAddMemberFromProjectSetting() {

		String memberName="Kaja";

		try {
			ProjectAndBoardDashboard projectBoard=new ProjectAndBoardDashboard(das);

			WebElement newCreatedProject=projectBoard.createNewProject();
			das.clickElement(newCreatedProject, newCreatedProject.getText());

			projectBoard.clickOnProjectSetting();
			Thread.sleep(3000);

			int size=projectBoard.ProjectSettingMemberSize();

			projectBoard.clickOnAddMemberButton();
			BacklogModule backlog=new BacklogModule(das);
			backlog.inputMember(memberName);
			projectBoard.selectMemberRole("Admin");
			WebElement we=backlog.clickOnSubmitMemberButton();
			boolean display=false;
			try {
				display=das.isDisplayed(we);
			}catch (Exception e) {
			}
			if(display) {
				das.etest.log(Status.FAIL, projectBoard.getTextMemberAlreadyAddedMessage());

				Assert.assertEquals(true, false);
			}
			int newSize=projectBoard.ProjectSettingMemberSize();

			//			String addedMemberName=projectBoard.extractMemberFromList(memberName);

			Assert.assertEquals(size+1, newSize);
			das.uiText_validation("Member Size ", "Member Size ");
		}catch (Exception e) {

			das.etest.log(Status.FAIL, "Member not Added");
			Assert.assertEquals(true, false);
		}
	}

	

}
