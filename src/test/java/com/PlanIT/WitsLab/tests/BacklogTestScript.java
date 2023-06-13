package com.PlanIT.WitsLab.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.PlanIT.WitsLab.pages.BacklogModule;
import com.PlanIT.WitsLab.pages.ProjectAndBoardDashboard;
import com.aventstack.extentreports.Status;

public class BacklogTestScript extends BaseTestSuite{

	@Test(enabled  = true)
	public void verifyCreateIssueFromBacklogFunctionality() {

		ProjectAndBoardDashboard projectBoard=new ProjectAndBoardDashboard(das);
		try {

			projectBoard.openFirstProject();
			projectBoard.openFirstBoard();

			Thread.sleep(3000);

			BacklogModule backlog=new BacklogModule(das);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
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
			projectBoard.popupClose();
			System.out.println(e.getMessage());
			das.etest.log(Status.FAIL, "Issue is not Created");
			Assert.assertEquals(true, false);

		}

	}

	@Test(enabled = true)
	public void verifyCreateSprintFunctionality() {
		BacklogModule backlog=new BacklogModule(das);

		ProjectAndBoardDashboard projectBoard=new ProjectAndBoardDashboard(das);

		try {

			projectBoard.openFirstProject();
			WebElement we=projectBoard.createNewBoard();
			das.clickElement(we, we.getText());
			Thread.sleep(3000);
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

			Assert.assertEquals(bb, true);
			das.uiText_validation("Sprint created successfully", "Sprint created successfully");
		}catch (Exception e) {

			projectBoard.popupClose();
			System.out.println(e.getMessage());
			das.etest.log(Status.FAIL, "Sprint is not created");
			backlog.closePopUp();

			Assert.assertEquals(true, false);

		}
	}

	@Test(enabled = true)
	public void verifyAddMemberFunctionalityFromBoard() {

		BacklogModule backlog=new BacklogModule(das);

		ProjectAndBoardDashboard projectBoard=new ProjectAndBoardDashboard(das);

		try {

			projectBoard.openFirstProject();
			WebElement createdBoard=projectBoard.createNewBoard();
			das.clickElement(createdBoard,createdBoard.getText() );

			Thread.sleep(3000);

			backlog.clickOnBoardModule();
			int totalNo=backlog.TotalMember();
			backlog.clickOnAddMemberButtonOnBoard();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			backlog.inputMember("van");
			WebElement we=backlog.clickOnSubmitMemberButton();

			boolean display=false;
			try {
				display=das.isDisplayed(we);
				if(display) {
					das.etest.log(Status.FAIL, backlog.getTextMemberAlreadyAddedMessageFromBoard());

					Assert.assertEquals(true, false);
				}
			}catch (Exception e) {
			}
			//			if(display) {
			//				das.etest.log(Status.FAIL, backlog.getTextMemberAlreadyAddedMessageFromBoard());
			//
			//				Assert.assertEquals(true, false);
			//			}
			int totalNoAfterAdding=backlog.TotalMember();

			Assert.assertNotEquals(totalNo, totalNoAfterAdding);

			das.etest.log(Status.PASS, "member Added Successfully. Now total member : "+totalNoAfterAdding);

		}catch (Exception e) {
			System.out.println(e.getMessage());
			projectBoard.popupClose();
			//			backlog.closePopUp();
			das.etest.log(Status.FAIL, "Member is Not added");
			Assert.assertEquals(true, false);
		}

	}

	@Test(enabled = true)
	public void verifySprintActivation() {

		ProjectAndBoardDashboard projectBoard=new ProjectAndBoardDashboard(das);

		try {

			projectBoard.openFirstProject();
			WebElement createdBoard=projectBoard.createNewBoard();
			das.clickElement(createdBoard, createdBoard.getText());

			Thread.sleep(3000);

			BacklogModule backlog=new BacklogModule(das);
			backlog.createNewSprint();

			backlog.clickOnActivateSprintButton();

			String sprintActivationMessage=backlog.getTextSprintActivateConfirmationMessage();

			das.uiText_validation(sprintActivationMessage, "Sprint Activated successfully");
			Assert.assertEquals(sprintActivationMessage, "Sprint Activated successfully");

			//			das.clickElement(createdBoard, sprintActivationMessage)

		}catch (Exception e) {
			projectBoard.popupClose();
			System.out.println(e.getMessage());
			das.etest.log(Status.FAIL, "Sprint is not Activated");
			Assert.assertEquals(true, false);
		}
	}

	@Test(enabled = true)
	public void verifyCreateIssueFromBoard() {

		ProjectAndBoardDashboard projectBoard=new ProjectAndBoardDashboard(das);

		try {

			projectBoard.openFirstProject();

			WebElement createdBoard=projectBoard.createNewBoard();
			das.clickElement(createdBoard, createdBoard.getText());

			Thread.sleep(3000);

			BacklogModule backlog=new BacklogModule(das);

			backlog.createNewSprint();

			backlog.clickOnActivateSprintButton();

			Thread.sleep(3000);
			backlog.clickOnBoardModule();


			backlog.clickOnBoardCreateIssueButton();
			String issueName=	backlog.inputIssueTitle(createIssue.getJSONObject("createIssue").getString("issueTitle"));

			backlog.selectIssueType(createIssue.getJSONObject("createIssue").getString("issueType"));

			backlog.selectPriority(createIssue.getJSONObject("createIssue").getString("Priority"));


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


			WebElement createdIssueName=backlog.getTODOlistFromBoad(issueName);
			das.uiText_validation(createdIssueName.getText(), issueName);
			Assert.assertEquals(createdIssueName.getText(), issueName);

		}catch (Exception e) {
			projectBoard.popupClose();
			System.out.println(e.getMessage());
			das.etest.log(Status.FAIL, "Issue not created");
			Assert.assertEquals(true, false);

		}

	}

	@Test(enabled = true)
	public void moveTicketFromOneBoardToAnother() {

		String projectName=null;
		String boardName=null;
		try {

			ProjectAndBoardDashboard projectBoard=new ProjectAndBoardDashboard(das);
			//    

			WebElement newProject=projectBoard.createNewProject();
			
			das.webDriverWait(newProject);
			das.clickElement(newProject, newProject.getText());
			WebElement firstBoard=projectBoard.createNewBoard();
			WebElement secondBoard=projectBoard.createNewBoard();

			String secondBoardName=das.getText(secondBoard);

			das.clickElement(firstBoard, firstBoard.getText());

			//			firstBoard.click();


			BacklogModule backlog=new BacklogModule(das);
			backlog.clickOnCreateIssueBtn();

			String issueName=    backlog.inputIssueTitle(createIssue.getJSONObject("createIssue").getString("issueTitle"));
			backlog.clickOnCreateButton();
			backlog.openFirstTicket();
			backlog.clickOnTicketKebabMenu();
			backlog.clickOnMoveToBoard(secondBoardName);
			projectBoard.popupClose();
			backlog.clickOnBackToProject();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			das.clickElement(das.getWebDriver().findElement(By.xpath("//h4[text()='"+secondBoardName+"']")), secondBoardName);

			WebElement movedIssue=backlog.getAllIssue(issueName);

			das.uiText_validation(movedIssue.getText(), issueName);
			Assert.assertEquals(movedIssue.getText(), issueName);

		}catch (Exception e) {
			e.printStackTrace();
			das.etest.log(Status.FAIL, "Ticket not move from one board to another");
			Assert.assertEquals(true, false);

		}
	}    


	@Test(enabled =true )
	public void verifyCloneIssueFunctionality() {


		try {

			ProjectAndBoardDashboard projectBoard=new ProjectAndBoardDashboard(das);

			WebElement newProject=projectBoard.createNewProject();
			das.clickElement(newProject, newProject.getText());
			//			newProject.click();
			WebElement firstBoard=projectBoard.createNewBoard();
			das.clickElement(firstBoard, firstBoard.getText());
			//			firstBoard.click();

			BacklogModule backlog=new BacklogModule(das);
			backlog.clickOnCreateIssueBtn();
			String issueName=    backlog.inputIssueTitle(createIssue.getJSONObject("createIssue").getString("issueTitle"));
			backlog.clickOnCreateButton();
			WebElement createdIssue= backlog.getAllIssue(issueName);
			das.clickElement(createdIssue, createdIssue.getText());
			backlog.clickOnTicketKebabMenu();
			backlog.clickOnCloneButton();
			Thread.sleep(2000);

			int size= das.getWebDriver().findElements(By.xpath("//div[contains(@class,'BacklogTable__TableContainer-sc-145rst5-1')]//div[contains(@class,'BacklogTable__Box')]")).size();
			das.etest.log(Status.INFO, "size of issue is "+size);
			Assert.assertEquals(size, 2);
			das.uiText_validation("Ticket Clone", "Ticket Clone");
		}catch (Exception e) {
			e.printStackTrace();
			das.etest.log(Status.FAIL, "Issue not clonned");
			Assert.assertEquals(true, false);
		}
	}


}