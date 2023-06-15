package com.PlanIT.WitsLab.pages;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
//import org.testng.Assert;

import com.PlanIT.WitsLab.elementrepository.ProjectAndBoardDashboardOR;
import com.PlanIT.WitsLab.ui.selenium.DriverActions;
import com.aventstack.extentreports.Status;
import com.github.javafaker.Cat;

public class ProjectAndBoardDashboard extends ProjectAndBoardDashboardOR{

	private DriverActions da;

	public void clickOnCreateNewProject() {
		da.clickElement(createNewProject,"Create New Project");
	}
	public String inputProjectName(String projectName1) {
		da.webDriverWait(projectName);
		da.sendKeys(projectName, projectName1,"Project Name");;
		return projectName1;
	}

	//	public void selectType() {
	//		da.clickElement(type,"Type");
	//		da.clickElement(engineering,"Engineering-IT");
	//	}

	public void selectProjectType(String projectType) {

		da.clickElement(type,"Type");

		if(projectType.equalsIgnoreCase("Marketing")) {
			da.clickElement(marketing,"Marketing");
		}else if(projectType.equalsIgnoreCase("Engineering-IT")) {
			da.clickElement(engineering,"Engineering-IT");
		}else if(projectType.equalsIgnoreCase("Human Resource")) {
			da.clickElement(humanResource,"Human Resource");
		}else if(projectType.equalsIgnoreCase("Education")) {
			da.clickElement(education,"Education");
		}else if(projectType.equalsIgnoreCase("Operations")) {
			da.clickElement(operations,"Operations");
		}else if(projectType.equalsIgnoreCase("Sales CRM")) {
			da.clickElement(salesCRM,"Sales CRM");
		}else if(projectType.equalsIgnoreCase("Small Business")) {
			da.clickElement(smallBusiness,"Small Business");
		}else if(projectType.equalsIgnoreCase("Other")) {
			da.clickElement(other,"Other");
		}

	}

	public void inputDescription(String inputDescription) {
		da.sendKeys(description, inputDescription,"Description Field");;
	}
	public void clickOnCreateButton() {
		da.clickElement(create,"Create");



	}

	public boolean projectAlreadyExists() {

		try {

			String str=da.getText(projectExists);
			if(str.equalsIgnoreCase("Workspace already exists with this name")) {
				da.etest.log(Status.INFO,str);
				//				da.clickElement(projectCancelButton, "Cancel Button");
				return true;
			}

		}catch (Exception e) {
			return false;
		}
		return false;
	}

	public boolean popupClose() {
		boolean visible=false;
		try {
			visible=closePopup.isDisplayed();
			if(visible) {
			da.clickElement(closePopup, "Close Button");
			}
//			da.webDriverWait(closePopup);
			
		}catch (Exception e) {
			return false;
		}
		return visible;
	}

	public boolean boardAlreadyExists() {
		try {
			//			da.webDriverWait(boardExists);
			Thread.sleep(3000);
			String str=da.getText(boardExists);
			if(str.equalsIgnoreCase("Workspace already created with this name.")) {
				da.etest.log(Status.INFO, str);
				//				da.clickElement(projectCancelButton, "Cancel Button");
				return true;
			}

		}catch (Exception e) {

			return false;
		}
		return false;
	}


	//	public String createNewProjectheaderGetText() {
	//		String text=da.getTest(createNewProjectheader);
	//		return text;
	//	}
	public WebElement getAllProject(String createdProjectName) {
		
		WebElement project=da.getWebDriver().findElement(By.xpath("//div[contains(@class,'Create__RecentView')]//div[text()='"+createdProjectName+"']"));
		
//		String projectName=null;
//		WebElement project=null;
//
//		try {
//			List<WebElement> ll=da.getWebDriver().findElements(By.xpath("//div[contains(@class,'Create__RecentView-sc-ytizbg-33')]//div[@style]"));
//
//
//			for (int i = 0; i < ll.size(); i++) {
//
//				WebElement we=ll.get(i);
//				projectName=we.getText();
//				if(createdProjectName.equalsIgnoreCase(projectName)) {
//					//				da.uiText_validation(createdProjectName, projectName);
//					project=we;
//					return project;
//				}
//
//			}
//		}catch (NoSuchElementException e) {
//
//		}
		return project;
	}

	public WebElement getAllBoard(String createdBoardName) {

		//		List<WebElement> ll=da.getWebDriver().findElements(By.xpath("//div[contains(@class,'ProjectSection__UnitySection')]//div[contains(@class,'Unity__Main')]//h4"));
		//		String boardName=null;
		//		WebElement project=null;
		//		for (int i = 0; i < ll.size(); i++) {
		//
		//			WebElement we=ll.get(i);
		//			
		//			boardName=we.getText();
		//			da.scrollToElement(we, boardName);
		//			if(createdBoardName.equalsIgnoreCase(boardName)) {
		//				
		//				//				da.uiText_validation(createdProjectName, projectName);
		//				project=we;
		//				return project;
		//			}
		//
		//		}
		WebElement project=da.getWebDriver().findElement(By.xpath("//div[contains(@class,'ProjectSection__UnitySection')]//div[contains(@class,'Unity__Main')]//h4[text()='"+createdBoardName+"']"));
		return project;
	}


	public void clickOnCreateTeamBoard() {
		da.clickElement(createTeamBoard,"Create Team Board");
	}
	public String inputBoardTitle(String BoardName) {
		da.webDriverWait(boardTitle);
		da.sendKeys(boardTitle, BoardName,"Board Title");
		return BoardName;
	}
	public void inputBoardDescription(String boardDescriptions) {
		da.sendKeys(boardDescription, boardDescriptions,"Board Description");;
	}
	public void selectBackground() {
		da.clickElement(selectBackground,"Blue");
	}

	public void openFirstProject() {
		da.clickElement(openFirstProject,"First Project");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void openFirstBoard() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		da.clickElement(openFirstBoard,"First Board");
	}

	public String getTextProjectConfirmationMessage() {

		return da.getText(projectConfirmationmessage);
	}

	public String getTextBoardConfirmationMessage() {

		return da.getText(boardConfirmationMessage);
	}

	public void clickOnProjectSetting() {
		da.webDriverWait(projectSettingButton);
		da.clickElement(projectSettingButton, "ProjectSetting");
	}


	public void clickOnAddMemberButton() {
		da.webDriverWait(AddMemberButtonprojectSetting);
		da.clickElement(AddMemberButtonprojectSetting, "Add Member");
	}



	public void clickOnTeamBoardSetting() {
		da.webDriverWait(teamBoardSetting);
		da.clickElement(teamBoardSetting, "ProjectSetting");
	}

	public void clickOnAddNewList() {
		da.clickElement(addNewList, "Add New List");
	}

	public void selectMemberRole(String projectType) {

		da.clickElement(memberRole,"Type");

		if(projectType.equalsIgnoreCase("Admin")) {
			da.clickElement(adminRole,"Admin");
		}else if(projectType.equalsIgnoreCase("Maintainer")) {
			da.clickElement(maintainerRole,"Maintainer");
		}else if(projectType.equalsIgnoreCase("User")) {
			da.clickElement(userRole,"User");
		}

	}

	public String extractMemberFromList(String memberName) {

		return da.getWebDriver().findElement(By.xpath("//tbody//tr//td[text()='"+memberName+"']")).getText();

	}
	public int ProjectSettingMemberSize() {

		return da.getWebDriver().findElements(By.xpath("//tbody//tr")).size();

	}

	public void clickOnBoardModule() {
		da.clickElement(boardModule, "Board Module");
	}
	public String getTextMemberAlreadyAddedMessage() {

		String text= da.getText(memberAlreadyAddedMessage);
		da.clickElement(closePopup, "Add Member PopUp Closed");
		return text;
	}

	public void AddNewStageFromTeamBoardSetting(String inputNewStageName) {
		clickOnAddNewList();
		da.clickElement(inputNewStage, "Input Stage Text");
		da.sendKeys(inputNewStage,inputNewStageName , "New Stage");;
		da.clickElement(addNewStageButton, "Add New Stage Button");
	}

	public WebElement createNewProject() {

		String projectName=null;
		WebElement createdProjectName=null;
		try {

			clickOnCreateNewProject();

			projectName=inputProjectName("NIT"+da.getRandomNo());

			selectProjectType("Marketing");

			inputDescription("Description Added");

			clickOnCreateButton();

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(projectAlreadyExists()) {


				projectName=inputProjectName("LTM"+da.getRandomNo());

				clickOnCreateButton();

			}

			createdProjectName=getAllProject(projectName);

			return createdProjectName;

		}catch (Exception e) {

		}

		return createdProjectName;
	}

	public WebElement createNewBoard() {


		WebElement createdboardName=null;
		try {

			clickOnCreateTeamBoard();

			String boardName=inputProjectName("JPR"+da.getRandomNo());

			inputBoardDescription("Board Description added.");

			clickOnCreateButton();


			createdboardName=getAllBoard(boardName);
			//			return createdboardName;

		}catch (Exception e) {

		}
		return createdboardName;

	}


	public ProjectAndBoardDashboard(DriverActions das) {
		this.da=das;
		PageFactory.initElements(da.getWebDriver(), this);
	}

}
