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

public class ProjectAndBoardDashBoardTestScript extends BaseTestSuite {

	@Test(enabled = true)
	public void verifyCreateNewProject() {
		String projectName = null;
		ProjectAndBoardDashboard dlp = new ProjectAndBoardDashboard(das);
		try {

			dlp.clickOnCreateNewProject();

			projectName = dlp
					.inputProjectName(CreateProjectAndBoard.getJSONObject("createProject").getString("Project Name"));

			dlp.selectProjectType(CreateProjectAndBoard.getJSONObject("createProject").getString("Project Type"));

			dlp.inputDescription(CreateProjectAndBoard.getJSONObject("createProject").getString("Description"));

			dlp.clickOnCreateButton();

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (dlp.projectAlreadyExists()) {

				projectName = CreateProjectAndBoard.getJSONObject("createProject").getString("Project Name");

				projectName = dlp.inputProjectName(projectName + das.getRandomNo());
				dlp.clickOnCreateButton();

			}

			WebElement createdProjectName = dlp.getAllProject(projectName);
			das.uiText_validation(createdProjectName.getText(), projectName);
			Assert.assertEquals(createdProjectName.getText(), projectName, "");

		} catch (Exception e) {
			dlp.popupClose();
			System.out.println(e.getMessage());
			das.etest.log(Status.FAIL, "Project is not Created");
			Assert.assertEquals(true, false);

		}
	}

	@Test(enabled = true)
	public void verifyCreateTeamBoard() {

		String boardName = null;
		ProjectAndBoardDashboard projectBoard = new ProjectAndBoardDashboard(das);
		try {

			projectBoard.openFirstProject();

			projectBoard.clickOnCreateTeamBoard();
			boardName = projectBoard
					.inputBoardTitle(CreateProjectAndBoard.getJSONObject("createBoard").getString("Board Name"));
			projectBoard
					.inputBoardDescription(CreateProjectAndBoard.getJSONObject("createBoard").getString("Description"));

			projectBoard.clickOnCreateButton();
			

			if (projectBoard.boardAlreadyExists()) {

				boardName = CreateProjectAndBoard.getJSONObject("createBoard").getString("Board Name");

				boardName = projectBoard.inputProjectName(boardName + das.getRandomNo());
				projectBoard.clickOnCreateButton();

				// das.waitForElementPresent(projectBoard.cancelBuuton);
			}
			try {
				if (das.isDisplayed(projectBoard.cancelButon)) {
					Thread.sleep(4000);
				}
			} catch (Exception e) {
			}
			WebElement createdboardName = projectBoard.getAllBoard(boardName);
			das.uiText_validation(createdboardName.getText(), boardName);
			Assert.assertEquals(createdboardName.getText(), boardName);

		} catch (Exception e) {
			projectBoard.popupClose();
			System.out.println(e.getMessage());
			das.etest.log(Status.FAIL, "Board is not created");
			Assert.assertEquals(true, false);
		}

	}

	@Test(enabled = true)
	public void verifyAddMemberFromProjectSetting() {

		String memberName = "rohit c";

		ProjectAndBoardDashboard projectBoard = new ProjectAndBoardDashboard(das);

		try {

			WebElement newCreatedProject = projectBoard.createNewProject();
			das.clickElement(newCreatedProject, newCreatedProject.getText());
			projectBoard.createNewBoard();
			Thread.sleep(3000);
			projectBoard.clickOnProjectSetting();
			Thread.sleep(3000);

			int size = projectBoard.ProjectSettingMemberSize();

			projectBoard.clickOnAddMemberButton();
			BacklogModule backlog = new BacklogModule(das);
			backlog.inputMemberOnProjectSetting(memberName);
			projectBoard.selectMemberRole("Admin");
			WebElement we = backlog.clickOnSubmitMemberButton();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			boolean display = false;
			try {
				display = das.isDisplayed(we);
				System.out.println("Display");
				System.out.println(display);
				if (display) {

//					das.etest.log(Status.FAIL, projectBoard.getTextMemberAlreadyAddedMessage());
					das.etest.log(Status.FAIL, "Member is not added");

					backlog.closePopUp();
					System.out.println("Popup close");
					Assert.assertEquals(true, false);
				}
			} catch (Exception e) {
			}

			int newSize = projectBoard.ProjectSettingMemberSize();

			// String addedMemberName=projectBoard.extractMemberFromList(memberName);

			Assert.assertEquals(size + 1, newSize);
			das.uiText_validation("Member Size ", "Member Size ");
		} catch (Exception e) {

			System.out.println(e.getMessage());
			das.etest.log(Status.FAIL, "Member not Added");
			Assert.assertEquals(true, false);
		}
	}

}