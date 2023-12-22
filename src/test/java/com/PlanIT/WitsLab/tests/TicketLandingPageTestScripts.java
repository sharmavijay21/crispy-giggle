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
// working fine
	@Test(enabled = true)
	public void verifyUpdateDetailsOfTickets() {

		BacklogModule backlog = new BacklogModule(das);

		ProjectAndBoardDashboard projectBoard = new ProjectAndBoardDashboard(das);
		try {

			
		//	projectBoard.openFirstProject();
			WebElement newCreatedProject = projectBoard.createNewProject();
			das.clickElement(newCreatedProject, newCreatedProject.getText());

			WebElement createdBoard = projectBoard.createNewBoard();
			das.clickElement(createdBoard, createdBoard.getText());

			Thread.sleep(3000);

		backlog.addMemberFromBoard("Sunil");
			WebElement createdIssue = backlog.createNewIssueFromBacklog();
			das.clickElement(createdIssue, "CreatedIssue");

			TicketLandingPage ticketLandingPage = new TicketLandingPage(das);

		
			ticketLandingPage.selectUpdatePriority("Medium");
			String priorityText = ticketLandingPage.getTextUpdatedPriority();
			das.uiText_validation(priorityText, "Medium");
			Assert.assertEquals(priorityText, "Medium");

			ticketLandingPage.updateAssignee("Sunil");
			String updatedAssignee = ticketLandingPage.getTextUpdatedAssignee("Sunil");
			das.uiText_validation(updatedAssignee, "Sunil");
			Assert.assertEquals(updatedAssignee, "Sunil");		
             
			//ticketLandingPage.clickOnEstimatedTime();
			
			ticketLandingPage.inputUpdatedStoryPoint("9");

			
			String storyPointText = ticketLandingPage.getTextUpdatedStoryPoint();
			das.uiText_validation(storyPointText, "StoryPoint Updated Successfully");
			Assert.assertEquals(storyPointText, "StoryPoint Updated Successfully");
		
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
//Working fine
	@Test(enabled = true)
	public void verifyChangeTicketStageFromDropdown() {

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
			backlog.clickOnCrossSprintButton();

			Thread.sleep(3000);
			backlog.clickOnBoardModule();

			backlog.clickOnBoardCreateIssueButton();
			String issueName = backlog
					.inputIssueTitle("Issue is created successfully");

			backlog.selectIssueType("Bug");

			backlog.inputDescription("User should able to create new >> account");
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
	
//not fine
	@Test(enabled = true)
	public void verifyAddNewLane() {

		ProjectAndBoardDashboard projectBoard = new ProjectAndBoardDashboard(das);

		try {

			WebElement newCreatedProject = projectBoard.createNewProject();
			das.clickElement(newCreatedProject, newCreatedProject.getText());
		
			WebElement createdBoard = projectBoard.createNewBoard();
			Thread.sleep(3000);
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
			backlog.clickOnCrossSprintButton();

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
//working fine 
	@Test(enabled = true)
	public void verifyTicketDeletionFromTicket() {

		ProjectAndBoardDashboard projectBoard = new ProjectAndBoardDashboard(das);
		try {

			TicketLandingPage tlp = new TicketLandingPage(das);

			//projectBoard.openFirstProject();
			WebElement newCreatedProject = projectBoard.createNewProject();
			das.clickElement(newCreatedProject, newCreatedProject.getText());
			
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
//working fine
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
// Now working
	@Test(enabled = true)
	public void verifyTicketDescriptionAndComment() {

		ProjectAndBoardDashboard projectBoard = new ProjectAndBoardDashboard(das);

		try {

		//	projectBoard.openFirstProject();
			WebElement newCreatedProject = projectBoard.createNewProject();
			das.clickElement(newCreatedProject, newCreatedProject.getText());
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
	
	@Test(enabled = true)
	public void verifySubTaskFromCreatedTicket() {

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
			backlog.clickOnCrossSprintButton();

			Thread.sleep(3000);
			backlog.clickOnBoardModule();

			backlog.clickOnBoardCreateIssueButton();
			String issueName = backlog
					.inputIssueTitle("Issue is created successfully");

			backlog.selectIssueType("Bug");

			backlog.inputDescription("User should able to create new >> account");
			backlog.clickOnCreateButton();

			WebElement createdIssueName = backlog.getTODOlistFromBoad(issueName);
			das.clickElement(createdIssueName, createdIssueName.getText());
			Thread.sleep(5000);
			backlog.clickOnCreateSubtask();
			backlog.inputSubtaskDescription("Testing Task");
		    String taskcreatedsuccessfully= backlog.getTextSubTaskConfirmationMessage();
		    das.uiText_validation(taskcreatedsuccessfully, "Subtask Deleted Successfully");
			Assert.assertEquals(taskcreatedsuccessfully, "Subtask Deleted Successfully");
		
		}catch(Exception e) {
			projectBoard.popupClose();
			System.out.println(e.getMessage());
			das.etest.log(Status.FAIL, "SubTask not created Successfully");
			Assert.assertEquals(true, false, "SubTask not created Successfully");
		}
		}

	@Test(enabled = true)
	public void verifyDeleteBoardfromActivatedSprint() {

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
			backlog.clickOnCrossSprintButton();

			Thread.sleep(3000);
			
			backlog.clickOnCreatedProject();
			ProjectAndBoardDashboard projectBoardDelete = new ProjectAndBoardDashboard(das);
			projectBoardDelete.clickOnThreedotSetting();
			projectBoardDelete.clickOnDeleteIcon();

		    String BoardDelete= backlog.getTextBoardDeleteMessage();
		    das.uiText_validation(BoardDelete, "Project with only Completed Spints can be Deleted");
			Assert.assertEquals(BoardDelete, "Project with only Completed Spints can be Deleted");
		
		}catch(Exception e) {
			projectBoard.popupClose();
			System.out.println(e.getMessage());
			das.etest.log(Status.FAIL, "Project with only Completed Spints can not be Deleted");
			Assert.assertEquals(true, false, "Project with only Completed Spints can not be Deleted");
		}
		}
	
	@Test(enabled = true)
	public void verifyReportsSection() {

		BacklogModule backlog = new BacklogModule(das);

		ProjectAndBoardDashboard projectBoard = new ProjectAndBoardDashboard(das);

		try {

			WebElement newCreatedProject = projectBoard.createNewProject();
			das.clickElement(newCreatedProject, newCreatedProject.getText());

			WebElement createdBoard = projectBoard.createNewBoard();
			das.clickElement(createdBoard, createdBoard.getText());

			Thread.sleep(3000);
			backlog.clickOnReportSection();
			backlog.clickOnVelocityChart();
			String Velocitychart= backlog.getTextVelocityChart();
			das.uiText_validation(Velocitychart, "Velocity Chart");
			Assert.assertEquals(Velocitychart, "Velocity Chart");
		
		}catch(Exception e) {
			projectBoard.popupClose();
			System.out.println(e.getMessage());
			das.etest.log(Status.FAIL, "Velocit Chart unable to open");
			Assert.assertEquals(true, false, "Velocit Chart unable to open");
		}
	}
	
	@Test(enabled = true)
	public void verifySprintCompletedConfirmation() {

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
			backlog.clickOnCrossSprintButton();
			backlog.clickOnEditSprintButton();
			backlog.clickOnCompleteRadioButton();
			backlog.clickOnCompleteButton();
			String Sprintcompleted= backlog.getTextSprintCompleted();
			das.uiText_validation(Sprintcompleted, "Sprint Completed!");
			Assert.assertEquals(Sprintcompleted, "Sprint Completed!");
					

			Thread.sleep(5000);
		}catch(Exception e) {
			projectBoard.popupClose();
			System.out.println(e.getMessage());
			das.etest.log(Status.FAIL, "Sprint not completed");
			Assert.assertEquals(true, false, "Sprint not completed");
		}
	}
	
		@Test(enabled = true)
		public void verifyWorkloadSettingsUpdate() {

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
				backlog.clickOnToWorkloadSettings();
				backlog.clickOnAction();
				backlog.clickOnSave();
				String workloadSettingsSuccessfull= backlog.getTextSuccessfulConfirmationMessage();
				das.uiText_validation(workloadSettingsSuccessfull, "Workload Seetings Successful!");
				Assert.assertEquals(workloadSettingsSuccessfull, "Workload Seetings Successful!");
			
		
			}catch(Exception e) {
				projectBoard.popupClose();
				System.out.println(e.getMessage());
				das.etest.log(Status.FAIL, "Sprint not completed");
				Assert.assertEquals(true, false, "Sprint not completed");
			}
	}
	
		@Test(enabled = true)
		public void verifySkipSpSuccessfullySprintComplete() {

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
                backlog.clickOnCompleteSprint();
                String Sprintcompleted= backlog.getTextSprintCompleted();
    			das.uiText_validation(Sprintcompleted, "Sprint Completed!");
    			Assert.assertEquals(Sprintcompleted, "Sprint Completed!");
    					

    			Thread.sleep(5000);
    		}catch(Exception e) {
    			projectBoard.popupClose();
    			System.out.println(e.getMessage());
    			das.etest.log(Status.FAIL, "Sprint not completed");
    			Assert.assertEquals(true, false, "Sprint not completed");
			}
	}
		
		@Test(enabled = true)
		public void verifyPastSprintCompletedData() {

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
                backlog.clickOnCompleteSprint();
                backlog.clickOnSkip();
                backlog.clickOnPastSprints();
                backlog.clickOnExpandicon();
                String NoSprintData= backlog.getTextNoSprintData();
                
                das.uiText_validation(NoSprintData, "No Sprint Data");
    			Assert.assertEquals(NoSprintData, "No Sprint Data");
               
		
			}catch(Exception e) {
    			projectBoard.popupClose();
    			System.out.println(e.getMessage());
    			das.etest.log(Status.FAIL, "Sprint Data Found");
    			Assert.assertEquals(true, false, "Sprint Data Found");
			}
		}
		
		@Test(enabled = true)
		public void verifyAddAnotherList() {

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
		        backlog.clickOnHideLanes();
		        backlog.clickOnAddAnotherList();
		        backlog.inputEnterList("Add lane");
   
                String LaneHasBeenCreated= backlog.getTextLaneHasBeenCreated();                
                das.uiText_validation(LaneHasBeenCreated, "ADD LANE");
    			Assert.assertEquals(LaneHasBeenCreated, "ADD LANE");
		       
			}catch(Exception e) {
    			projectBoard.popupClose();
    			System.out.println(e.getMessage());
    			das.etest.log(Status.FAIL, "New Lane has not been created");
    			Assert.assertEquals(true, false, "New Lane has not been created");
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
				backlog.clickOnIssuescreated();
				

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
