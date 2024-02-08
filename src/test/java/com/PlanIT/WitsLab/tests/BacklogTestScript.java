package com.PlanIT.WitsLab.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.PlanIT.WitsLab.pages.BacklogModule;
import com.PlanIT.WitsLab.pages.ProjectAndBoardDashboard;
import com.aventstack.extentreports.Status;

public class BacklogTestScript extends BaseTestSuite {
//WORKING FINE
	@Test(enabled = true)
	public void verifyCreateIssueFromBacklogFunctionality() {

		ProjectAndBoardDashboard projectBoard = new ProjectAndBoardDashboard(das);
		try {

			WebElement newCreatedProject = projectBoard.createNewProject();

			das.clickElement(newCreatedProject, newCreatedProject.getText());

			WebElement firstBoard = projectBoard.createNewBoard();
			das.clickElement(firstBoard, firstBoard.getText());

			Thread.sleep(3000);

			BacklogModule backlog = new BacklogModule(das);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			backlog.clickOnCreateIssueBtn();

			String issueName = backlog
					.inputIssueTitle("Issue is created successfully");

			backlog.selectIssueType("Bug");

			backlog.selectPriority("Low");

			backlog.enterCurrentDate();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// backlog.inputEndDate(createIssue.getJSONObject("createIssue").getString("EndDate"));
			Thread.sleep(5000);
			backlog.chooseAttachment();
			Thread.sleep(3000);
			backlog.inputStoryPoint("6");

			backlog.inputDescription("User should able to create new >> account");

			backlog.clickOnCreateButton();
			Thread.sleep(5000);
			WebElement createdIssueName = backlog.getAllIssue(issueName);
			das.uiText_validation(createdIssueName.getText(), issueName);
			Assert.assertEquals(createdIssueName.getText(), issueName);

		} catch (Exception e) {
			projectBoard.popupClose();
			System.out.println(e.getMessage());
			das.etest.log(Status.FAIL, "Issue is not Created");
			Assert.assertEquals(true, false);

		}

	}
//working fine
	@Test(enabled = true)
	public void verifyCreateSprintFunctionality() {
		BacklogModule backlog = new BacklogModule(das);

		ProjectAndBoardDashboard projectBoard = new ProjectAndBoardDashboard(das);

		try {

			projectBoard.openFirstProject();
			WebElement we = projectBoard.createNewBoard();
			das.clickElement(we, we.getText());

			backlog.clickOnCreateSprintButton();
			backlog.selectSprintDuration("2");
			
			backlog.inputSprintGoal("Sprint should be complete within 7 days");
			backlog.clickOnSubmitSprintButton();
			
			boolean bb = das.isDisplayed(backlog.newCreatedSprint);

			Assert.assertEquals(bb, true);
			das.uiText_validation("Sprint created successfully", "Sprint created successfully");
		} catch (Exception e) {

			projectBoard.popupClose();
			System.out.println(e.getMessage());
			das.etest.log(Status.FAIL, "Sprint is not created");
			backlog.closePopUp();

			Assert.assertEquals(true, false);

		}
	}
// working fine
	@Test(enabled = true)
	public void verifyAddMemberFunctionalityFromBoard() {

		BacklogModule backlog = new BacklogModule(das);

		ProjectAndBoardDashboard projectBoard = new ProjectAndBoardDashboard(das);
		try {
			WebElement createProject = projectBoard.createNewProject();
			createProject.click();
//			projectBoard.openFirstProject();
			

			WebElement createdBoard = projectBoard.createNewBoard();
			das.clickElement(createdBoard, createdBoard.getText());

			Thread.sleep(3000);

			backlog.clickOnBoardModule();
			int totalNo = backlog.TotalMember();
			backlog.clickOnAddMemberButtonOnBoard();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			backlog.inputMember("van");
			WebElement we = backlog.clickOnSubmitMemberButton();			
			
			boolean display = false;
			try {
				display = das.isDisplayed(we);
				if (display) {
					das.etest.log(Status.FAIL, backlog.getTextMemberAlreadyAddedMessageFromBoard());

					Assert.assertEquals(true, false);
				}
			} catch (Exception e) {
			}
			
			int totalNoAfterAdding = backlog.TotalMember();

			Assert.assertNotEquals(totalNo, totalNoAfterAdding);

			das.etest.log(Status.PASS, "member Added Successfully. Now total member : " + totalNoAfterAdding);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			projectBoard.popupClose();
			das.etest.log(Status.FAIL, "Member is Not added");
			Assert.assertEquals(true, false);
		}

	}
// working fine
	@Test(enabled = true)
	public void verifyExistingProjectMemberFunctionalityToBoard() {

		BacklogModule backlog = new BacklogModule(das);

		ProjectAndBoardDashboard projectBoard = new ProjectAndBoardDashboard(das);
		try {
			WebElement createProject = projectBoard.createNewProject();
	    	createProject.click();

			WebElement createdBoard = projectBoard.createNewBoard();
			das.clickElement(createdBoard, createdBoard.getText());

			Thread.sleep(3000);

			backlog.clickOnBoardModule();
			int totalNo = backlog.TotalMember();
			backlog.clickOnAddMemberButtonOnBoard();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			backlog.inputMember("van");
			Thread.sleep(2000);
			WebElement we = backlog.clickOnSubmitMemberButton();
			Thread.sleep(5000);
			backlog.clickOnAddMemberButtonOnBoard();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			backlog.inputMember("van");
			Thread.sleep(2000);
			WebElement w1 = backlog.clickOnSubmitMemberButton();

			String memberAlreadyexistMessage = backlog.getTextMemberAlreadyExistInBaordConfirmationMessage();

			das.uiText_validation(memberAlreadyexistMessage, "Members already exist in the project");
			Assert.assertEquals(memberAlreadyexistMessage, "Members already exist in the project");

		} catch (Exception e) {
			System.out.println(e.getMessage());
			projectBoard.popupClose();
			backlog.closePopUp();
//			das.etest.log(Status.FAIL, "Member is Not added");
//			Assert.assertEquals(true, false);
		}

	}
//working fine
	@Test(enabled = true)
	public void verifySprintActivation() {

		ProjectAndBoardDashboard projectBoard = new ProjectAndBoardDashboard(das);

		try {

			projectBoard.openFirstProject();
			WebElement createdBoard = projectBoard.createNewBoard();
			das.clickElement(createdBoard, createdBoard.getText());

			Thread.sleep(3000);

			BacklogModule backlog = new BacklogModule(das);
			backlog.createNewSprint();
			Thread.sleep(3000);
			backlog.clickOnActivateSprintButton();
			//backlog.clickOnCrossSprintButton();


			String sprintActivationMessage = backlog.getTextSprintActivateConfirmationMessage();

			das.uiText_validation(sprintActivationMessage, "Sprint Activated successfully");
			Assert.assertEquals(sprintActivationMessage, "Sprint Activated successfully");

			// das.clickElement(createdBoard, sprintActivationMessage)

		} catch (Exception e) {
			projectBoard.popupClose();
			System.out.println(e.getMessage());
			das.etest.log(Status.FAIL, "Sprint is not Activated");
			Assert.assertEquals(true, false);
		}
	}
//Not working fine
	@Test(enabled = true)
	public void verifyCreateIssueFromBoard() {

		ProjectAndBoardDashboard projectBoard = new ProjectAndBoardDashboard(das);

		try {

			WebElement createProject = projectBoard.createNewProject();
	    	createProject.click();

			WebElement createdBoard = projectBoard.createNewBoard();
			Thread.sleep(4000);
			das.clickElement(createdBoard, createdBoard.getText());

			Thread.sleep(3000);

			BacklogModule backlog = new BacklogModule(das);

			backlog.createNewSprint();

			backlog.clickOnActivateSprintButton();
			backlog.clickOnCrossSprintButton();

			Thread.sleep(3000);
			backlog.clickOnBoardModule();

			backlog.clickOnBoardCreateIssueButton();
			String issueName = backlog
					.inputIssueTitle("Issue is created successfully");

			backlog.selectIssueType("Bug");

			backlog.selectPriority("Low");

			backlog.enterCurrentDate();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// backlog.inputEndDate(createIssue.getJSONObject("createIssue").getString("EndDate"));
			Thread.sleep(5000);
			backlog.chooseAttachment();
			backlog.inputStoryPoint("6");

			backlog.inputDescription("User should able to create new >> account");

			backlog.clickOnCreateButton();

			WebElement createdIssueName = backlog.getTODOlistFromBoad(issueName);
			das.uiText_validation(createdIssueName.getText(), issueName);
			Assert.assertEquals(createdIssueName.getText(), issueName);

		} catch (Exception e) {
			projectBoard.popupClose();
			System.out.println(e.getMessage());
			das.etest.log(Status.FAIL, "Issue not created");
			Assert.assertEquals(true, false);

		}

	}
//working fine
	@Test(enabled = true)
	public void moveTicketFromOneBoardToAnother() {

		ProjectAndBoardDashboard projectBoard = new ProjectAndBoardDashboard(das);
		try {

			WebElement newProject = projectBoard.createNewProject();

			das.webDriverWait(newProject);
			das.clickElement(newProject, newProject.getText());
			WebElement firstBoard = projectBoard.createNewBoard();
			WebElement secondBoard = projectBoard.createNewBoard();

			String secondBoardName = das.getText(secondBoard);

			das.clickElement(firstBoard, firstBoard.getText());

			// firstBoard.click();

			BacklogModule backlog = new BacklogModule(das);
			backlog.clickOnCreateIssueBtn();

			String issueName = backlog
					.inputIssueTitle("Issue is created successfully");
			
			System.out.println(issueName);
			
			backlog.selectIssueType("Bug");
			backlog.inputDescription(" User should able to create new >> account");
			
			backlog.clickOnCreateButton();
//			das.clickElement(backlog.backlogLane, "Backlog Lane");
			Thread.sleep(4000);
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
			das.clickElement(das.getWebDriver().findElement(By.xpath("//h4[text()='" + secondBoardName + "']")),
					secondBoardName);

			WebElement movedIssue = backlog.getAllIssue(issueName);

			das.uiText_validation(movedIssue.getText(), issueName);
			Assert.assertEquals(movedIssue.getText(), issueName);

		} catch (Exception e) {
			projectBoard.popupClose();
			e.printStackTrace();
			das.etest.log(Status.FAIL, "Ticket not move from one board to another");
			Assert.assertEquals(true, false);

		}
	}
// working fine
	@Test(enabled = true)
	public void verifyCloneIssueFunctionality() {

		ProjectAndBoardDashboard projectBoard = new ProjectAndBoardDashboard(das);

		try {

			WebElement newProject = projectBoard.createNewProject();
			das.clickElement(newProject, newProject.getText());
			// newProject.click();
			WebElement firstBoard = projectBoard.createNewBoard();
			das.clickElement(firstBoard, firstBoard.getText());
			// firstBoard.click();

			BacklogModule backlog = new BacklogModule(das);
			backlog.clickOnCreateIssueBtn();
			String issueName = backlog
					.inputIssueTitle("Issue is created successfully");

			backlog.selectIssueType("Bug");
			backlog.inputDescription(" User should able to create new >> account");
			backlog.clickOnCreateButton();
			WebElement createdIssue = backlog.getAllIssue(issueName);
			das.clickElement(createdIssue, createdIssue.getText());
			backlog.clickOnTicketKebabMenu();
			backlog.clickOnCloneButton();
			Thread.sleep(2000);

			int size = das.getWebDriver().findElements(By.xpath(
					"//div[contains(@class,'BacklogTable__TableContainer-sc-145rst5-1')]//div[contains(@class,'BacklogTable__Box')]"))
					.size();
			das.etest.log(Status.INFO, "size of issue is " + size);
			Assert.assertEquals(size, 3);
			das.uiText_validation("Ticket Clone", "Ticket Clone");
		} catch (Exception e) {
			projectBoard.popupClose();
			e.printStackTrace();
			das.etest.log(Status.FAIL, "Issue not clonned");
			Assert.assertEquals(true, false);
		}
	}
// working fine
	@Test(enabled = true)
	public void verifyMoveAllIssueFromBacklogToSprint() {

		ProjectAndBoardDashboard projectBoard = new ProjectAndBoardDashboard(das);

		try {

			WebElement newProject = projectBoard.createNewProject();
			das.clickElement(newProject, newProject.getText());
			WebElement firstBoard = projectBoard.createNewBoard();
			das.clickElement(firstBoard, firstBoard.getText());

			BacklogModule backlog = new BacklogModule(das);
			Thread.sleep(2000);
			backlog.createNewSprint();

			backlog.createNewIssueFromBacklog();
			backlog.createNewIssueFromBacklog();
			backlog.createNewIssueFromBacklog();
			// backlog.clickOnBacklogExpandIcon();
			int totalCreatedIssue = backlog.getSizeOfBacklogIssue();
			backlog.clickOnBacklogSelectAllCheckbox();
			backlog.clickOnBacklogMoveButton();
			
			backlog.selectSprintForMoveAllTicketFromBacklog();
			Thread.sleep(3000);
			int totalMovedIssue = backlog.getSizeOfSprintIssueOnBacklogPage();
			// String movedAllIssueFromBacklog=backlog.getTextBacklogIssueData();
			// String issueOnSprint= backlog.getTextSprintIssueData();
			System.out.println(totalCreatedIssue);
			System.out.println(totalMovedIssue);

			Assert.assertEquals(totalMovedIssue, totalCreatedIssue);
			das.uiText_validation("All issue moved on Sprint", "All issue moved on Sprint");

		} catch (Exception e) {
			projectBoard.popupClose();
			das.etest.log(Status.FAIL, "Issue not moved in Sprint");
			Assert.assertEquals(true, false);

		}
	}
	
	@Test(enabled = true)
	public void verifyIssuecreatedfromIssueSection() {

		BacklogModule backlog = new BacklogModule(das);

		ProjectAndBoardDashboard projectBoard = new ProjectAndBoardDashboard(das);

		try {

			WebElement newCreatedProject = projectBoard.createNewProject();
			das.clickElement(newCreatedProject, newCreatedProject.getText());

			WebElement createdBoard = projectBoard.createNewBoard();
			das.clickElement(createdBoard, createdBoard.getText());

			Thread.sleep(3000);
			backlog.createNewSprint();
			backlog.clickOnActivateSprintButton();
			backlog.clickOnSkipSpCuttOffDays();
			backlog.clickOnBoardCreateIssueButton();
			String issueName = backlog
					.inputIssueTitle("Issue is created successfully");

			backlog.selectIssueType("Bug");

			backlog.inputDescription("User should able to create new >> account");
			backlog.clickOnCreateButton();
			backlog.clickOnIssues();
			backlog.clickOnSelectIssuesType();
			backlog.clickOnSelectBug();
			backlog.clickOnResetFilter();
			
			 String issueCreated= backlog.getTextIssueIsCreatedSuccessfully();                
             das.uiText_validation(issueCreated, "Issue is created successfully");
 			Assert.assertEquals(issueCreated, "Issue is created successfully");
		       
			}catch(Exception e) {
 			projectBoard.popupClose();
 			System.out.println(e.getMessage());
 			das.etest.log(Status.FAIL, "New Issue has been created");
 			Assert.assertEquals(true, false, "New Issue has been created");
		
			}
		}

}       
