package com.PlanIT.WitsLab.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.PlanIT.WitsLab.pages.BacklogModule;
import com.PlanIT.WitsLab.pages.ProjectAndBoardDashboard;
import com.PlanIT.WitsLab.pages.TicketLandingPage;
import com.aventstack.extentreports.Status;

public class TicketLandingPageTestScripts extends BaseTestSuite {

	@Test(enabled = true)
	public void verifyUpdateDetailsOfTickets() {

		BacklogModule backlog = new BacklogModule(das);

		ProjectAndBoardDashboard projectBoard = new ProjectAndBoardDashboard(das);
		try {

			projectBoard.openFirstProject();
			// projectBoard.openFirstBoard();

			WebElement createdBoard = projectBoard.createNewBoard();
			das.clickElement(createdBoard, createdBoard.getText());

			Thread.sleep(3000);

//			backlog.addMemberFromBoard("Sunil");
			WebElement createdIssue = backlog.createNewIssueFromBacklog();
			das.clickElement(createdIssue, "CreatedIssue");

//			backlog.openFirstTicket();
			// backlog.addMemberFromBoard(createIssue.getJSONObject("update
			// Issue").getString("Assignee"));

			TicketLandingPage ticketLandingPage = new TicketLandingPage(das);

//			ticketLandingPage.clickOnUpdateField();
			ticketLandingPage.selectUpdatePriority("Medium");
			String priorityText = ticketLandingPage.getTextUpdatedPriority();
			das.uiText_validation(priorityText, "Medium");
			Assert.assertEquals(priorityText, "Medium");

			// handleNoSuchElement Exception after entering wrong name

			ticketLandingPage.updateAssignee("Sunil");
			String updatedAssignee = ticketLandingPage.getTextUpdatedAssignee("Sunil");
			das.uiText_validation(updatedAssignee, "Sunil");
			Assert.assertEquals(updatedAssignee, "Sunil");

			ticketLandingPage.selectUpdateIssueType("Bug");

			// System.out.println(das.getAttribute(das.getWebDriver().findElement(By.xpath("//div[contains(@class,'TicketModal__SvgWrapper')]")),
			// "title"));
			;
			String updatedType = ticketLandingPage.getTextUpdatedIssueType();
			das.uiText_validation(updatedType, "bug");
			Assert.assertEquals(updatedType, "bug");

			ticketLandingPage.inputUpdatedStoryPoint("9");
//			ticketLandingPage.clickOnStoryPointText();
//			ticketLandingPage.clickOnStoryPointText();
			Thread.sleep(2000);
			String storyPointText = ticketLandingPage.getTextUpdatedStoryPoint("9");
			das.uiText_validation(storyPointText, "9");
			Assert.assertEquals(storyPointText, "9");
			Thread.sleep(8000);
			backlog.closePopUp();
		} catch (Exception e) {
			e.printStackTrace();
			if (das.isDisplayed(projectBoard.closePopup)) {
				projectBoard.popupClose();
			}

			System.out.println(e.getMessage());
			// backlog.closePopUp();
			das.etest.log(Status.FAIL, "Detail is not updated");
			Assert.assertEquals(true, false);
		}
	}

	@Test(enabled = true)
	public void verifyChangeTicketStageFromDropdown() {

		BacklogModule backlog = new BacklogModule(das);

		ProjectAndBoardDashboard projectBoard = new ProjectAndBoardDashboard(das);

		try {

			projectBoard.openFirstProject();

			WebElement createdBoard = projectBoard.createNewBoard();
			das.clickElement(createdBoard, createdBoard.getText());

			Thread.sleep(3000);

			backlog.createNewSprint();

			backlog.clickOnActivateSprintButton();

			Thread.sleep(3000);
			backlog.clickOnBoardModule();

			backlog.clickOnBoardCreateIssueButton();
			String issueName = backlog
					.inputIssueTitle(createIssue.getJSONObject("createIssue").getString("issueTitle"));

			backlog.selectIssueType(createIssue.getJSONObject("createIssue").getString("issueType"));

			backlog.inputDescription(createIssue.getJSONObject("createIssue").getString("Description"));
			backlog.clickOnCreateButton();

			WebElement createdIssueName = backlog.getTODOlistFromBoad(issueName);
			das.clickElement(createdIssueName, createdIssueName.getText());
			TicketLandingPage ticketLandingPage = new TicketLandingPage(das);
			// ticketLandingPage.clickOnTicketStage();
			ticketLandingPage.selectTicketStage("in Progress");

			Thread.sleep(10000);
			WebElement createdIssueName1 = backlog.getTODOlistFromBoad(issueName);
			das.clickElement(createdIssueName1, createdIssueName1.getText());

			String ticketStageText = das.getText(ticketLandingPage.ticketStage);

			das.uiText_validation(ticketStageText, "IN PROGRESS");
			Assert.assertEquals(ticketStageText, "IN PROGRESS");

			backlog.closePopUp();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			// backlog.closePopUp();
			projectBoard.popupClose();

			das.etest.log(Status.FAIL, "TicketStage Not Updated");

			Assert.assertEquals(true, false);

		}

	}

	@Test(enabled = true)
	public void verifyAddNewLane() {

		ProjectAndBoardDashboard projectBoard = new ProjectAndBoardDashboard(das);

		try {

			WebElement newCreatedProject = projectBoard.createNewProject();
			das.clickElement(newCreatedProject, newCreatedProject.getText());

			WebElement createdBoard = projectBoard.createNewBoard();

			projectBoard.clickOnProjectSetting();
			Thread.sleep(3000);
			projectBoard.clickOnTeamBoard();

			projectBoard.AddNewStageFromTeamBoardSetting("ABC");
//			projectBoard.clickOnBoardModule();
			projectBoard.clickOnBackToProjectButton();

			das.clickElement(das.getWebDriver().findElement(By.xpath("//div[contains(@class,'Unity__MainContainer')]")),
					"Board");

			Thread.sleep(2000);

			BacklogModule backlog = new BacklogModule(das);

			backlog.createNewSprint();

			backlog.clickOnActivateSprintButton();

			Thread.sleep(3000);
			backlog.clickOnBoardModule();
			String lastAddedStage = backlog.getTextLastAddedStageFromBoard();

			das.uiText_validation(lastAddedStage, "ABC");

			Assert.assertEquals(lastAddedStage, "ABC");
			//
		} catch (Exception e) {
			System.out.println(e.getMessage());
			projectBoard.popupClose();
			das.etest.log(Status.FAIL, "Lane is not added");
			Assert.assertEquals(true, false);
		}
	}

	@Test(enabled = true)
	public void verifyTicketDeletionFromTicket() {

		ProjectAndBoardDashboard projectBoard = new ProjectAndBoardDashboard(das);
		try {

			TicketLandingPage tlp = new TicketLandingPage(das);

			projectBoard.openFirstProject();

			WebElement teamBoard = projectBoard.createNewBoard();
			das.clickElement(teamBoard, teamBoard.getText());

			BacklogModule backlog = new BacklogModule(das);

			WebElement createdIssue = backlog.createNewIssueFromBacklog();

			das.clickElement(createdIssue, createdIssue.getText());

			// backlog.openFirstTicket();

			backlog.clickOnTicketKebabMenu();

			tlp.ticketDelete();

			String nobacklogDataMessage = backlog.getTextNoBacklogData();

			das.uiText_validation(nobacklogDataMessage, "(0 Issue)");

			Assert.assertEquals(nobacklogDataMessage, "(0 Issue)");

		} catch (Exception e) {
			projectBoard.popupClose();
			System.out.println(e.getMessage());
			das.etest.log(Status.FAIL, "Ticket not Deleted");
			Assert.assertEquals(true, false);

		}

	}

	@Test(enabled = true)
	public void verifyLinkIssueFunctionalityFromTicket() {

		ProjectAndBoardDashboard projectBoard = new ProjectAndBoardDashboard(das);

		try {

			projectBoard.openFirstProject();
			WebElement firstBoard = projectBoard.createNewBoard();
			das.clickElement(firstBoard, firstBoard.getText());

			BacklogModule backlog = new BacklogModule(das);
			WebElement firstIssue = backlog.createNewIssueFromBacklog();
			backlog.createNewIssueFromBacklog();

			// das.clickElement(firstIssue, firstIssue.getText());
			backlog.openFirstTicket();

			TicketLandingPage tlp = new TicketLandingPage(das);
			tlp.clickOnLinkIssueButton();
			String linkedIssueConfirmationMessage = tlp.selectIssueForLink();

			das.uiText_validation(linkedIssueConfirmationMessage, "Link Updated Successfully");
			Assert.assertEquals(linkedIssueConfirmationMessage, "Link Updated Successfully");

		} catch (Exception e) {
			projectBoard.popupClose();
			System.out.println(e.getMessage());
			das.etest.log(Status.FAIL, "Link is not Updated Successfully");
			Assert.assertEquals(true, false);
		}
	}

	@Test
	public void verifyTicketDescriptionAndComment() {

		ProjectAndBoardDashboard projectBoard = new ProjectAndBoardDashboard(das);

		try {

			projectBoard.openFirstProject();

			WebElement firstBoard = projectBoard.createNewBoard();
			das.clickElement(firstBoard, firstBoard.getText());

			BacklogModule backlog = new BacklogModule(das);

			backlog.createNewIssueFromBacklog();

			backlog.openFirstTicket();

			TicketLandingPage tlp = new TicketLandingPage(das);

			String actualDescription = tlp.addDescription("Dashboard should be verified");

			das.uiText_validation(actualDescription, "Dashboard should be verified ");
			Assert.assertEquals(actualDescription, "Dashboard should be verified ");

			String actualComment = tlp.addComment("fixed");

			projectBoard.popupClose();

			das.uiText_validation(actualComment, "fixed");
			Assert.assertEquals(actualComment, "fixed");

		} catch (Exception e) {
			e.printStackTrace();
			projectBoard.popupClose();
			System.out.println(e.getMessage());
			das.etest.log(Status.FAIL, "Discription and comment not added");
			Assert.assertEquals(true, false, "Discription and comment not added");
		}
	}

}
