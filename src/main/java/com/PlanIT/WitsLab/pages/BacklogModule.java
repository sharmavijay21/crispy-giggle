package com.PlanIT.WitsLab.pages;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import org.apache.bcel.generic.DASTORE;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.server.handler.GetTagName;
import org.openqa.selenium.support.PageFactory;
//import org.testng.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.PlanIT.WitsLab.elementrepository.BacklogModuleOR;
import com.PlanIT.WitsLab.ui.selenium.DriverActions;
import com.aventstack.extentreports.Status;
import java.text.SimpleDateFormat;

public class BacklogModule extends BacklogModuleOR {

	private DriverActions da;
	private Actions ac;

	public void clickOnCreateIssueBtn() {
		da.webDriverWait(createIssueFromBacklog);
		da.clickElement(createIssueFromBacklog, "Create ISSUE");

	}

	public String inputIssueTitle(String inputIssueTitle) {
		da.webDriverWait(issueTitle);
		da.sendKeys(issueTitle, inputIssueTitle, "Issue Title");

		return inputIssueTitle;
	}

	public void clickOnBugType() {

		da.clickElement(issueType, "Bug Type");

	}

	public void clickOnPriorityType() {

		da.clickElement(priorityType, "Priority Field");

	}

	public void selectIssueType(String type) {

		clickOnBugType();
		if (type.equalsIgnoreCase("Epic")) {
			da.clickElement(epic, "Epic");
		} else if (type.equalsIgnoreCase("Bug")) {
			da.clickElement(bug, "Bug");
		} else if (type.equalsIgnoreCase("Task")) {
			da.clickElement(task, "Task");
		} else if (type.equalsIgnoreCase("Subtask")) {
			da.clickElement(subTask, "SubTask");
		} else if (type.equalsIgnoreCase("UserStory")) {
			da.clickElement(userStory, "UserStory");
		}

	}

	public void selectPriority(String priority) {

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		clickOnPriorityType();

		if (priority.equalsIgnoreCase("Critical")) {
			da.clickElement(critical, "Critical");
		} else if (priority.equalsIgnoreCase("High")) {
			da.clickElement(high, "High");
		} else if (priority.equalsIgnoreCase("Medium")) {
			da.clickElement(medium, "Medium");
		} else if (priority.equalsIgnoreCase("Low")) {
			da.clickElement(low, "Low");
		}

	}

	public void inputDueDate(String inputStartDate) {

		da.sendKeysWithoutClearingExistingData(dueDate, inputStartDate, "Start Date");
		da.sendKeys(dueDate, Keys.ENTER);

	}

//	public void inputEndDate(String inputEndDate) {
//
//		da.clickElement(endDate, "End Date");
//		da.sendKeysWithoutClearingExistingData(endDate, inputEndDate,"End Date");
//		da.sendKeys(endDate, Keys.ENTER);
	
//	}
	public void inputStoryPoint(String inputStoryPoint) {

		da.sendKeys(storyPoint, inputStoryPoint, "Story Point");

	}

	public void inputDescription(String inputDescription) {

		da.sendKeys(addDescription, inputDescription, "Description");

	}

	public void clickOnCreateButton() {

		da.clickElement(createButton, "Create");

	}
	
	public void clickOnIssues() {
		da.webDriverWait(Issues);
		da.clickElement(Issues, "Issues");

	}
	public void clickOnSelectIssuesType() {
		da.webDriverWait(selectIssueType);
		da.clickElement(selectIssueType, "select Issue Type");

	}
	
	public void clickOnSelectBug() {
		da.webDriverWait(Bug);
		da.clickElement(Bug, "Bug");

	}
	
	public void clickOnResetFilter() {
		da.webDriverWait(resetFilter);
		da.clickElement(resetFilter, "resetFilter");

	}
	
	public void clickOnIssuescreated() {
		da.webDriverWait(Issuescreated);
		da.clickElement(Issuescreated, "Issues created");

	}
	
	
	public void clickOnCompleteSprint() {
		da.webDriverWait(completeSprint);
		da.clickElement(completeSprint, "complete Sprint");
		da.webDriverWait(OkButton);
		da.clickElement(OkButton, "Ok Button");
	}
	
     public void clickOnSkip() {
		
		da.webDriverWait(Skip);
		da.clickElement(Skip, "Skip");
     }
	
     public void clickOnPastSprints() {
 		
 		da.webDriverWait(pastSprint);
 		da.clickElement(pastSprint, "past Sprint");
      }
     public void clickOnExpandicon() {
  		
  		da.webDriverWait(expandicon);
  		da.clickElement(expandicon, "expandicon");
       }
     
	public void clickOnCreateSubtask() {
		
		da.webDriverWait(createSubtask);
		da.clickElement(createSubtask, "create Subtask");

	}
	
	public void inputSubtaskDescription(String inputDescription) {
		da.webDriverWait(EnterSubtask);
		da.sendKeys(EnterSubtask, inputDescription, "Enter Subtask");
		
		da.webDriverWait(EnterSave);
		da.clickElement(EnterSave, "Enter Save");
		da.webDriverWait(closeicon);
		da.clickElement(closeicon, "Enter Save");
		
	
	}
	
	public String getTextSubTaskConfirmationMessage() {
		da.webDriverWait(TaskCreatedSuccessfully);

		String text = da.getText(TaskCreatedSuccessfully);

		
		return text;
	}


	public String getTextBoardDeleteMessage() {
		da.webDriverWait(BoardDelete);

		String text = da.getText(BoardDelete);

		
		return text;
	}
	public String getTextVelocityChart() {
		da.webDriverWait(VelocityChart);

		String text = da.getText(VelocityChart);

		
		return text;
	}
	
	public String getTextSprintCompleted() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		da.webDriverWait(SprintCompleted);

		String text = da.getText(SprintCompleted);

		//da.clickElement(SkipButton, "Skip Button");
		return text;
	}
	
	public String getTextNoSprintData() {


		da.webDriverWait(noSprintData);

		String text = da.getText(noSprintData);

	
		return text;
	}
	
	public String getTextLaneHasBeenCreated() {


	//	da.webDriverWait(newStatusLanehasbeenCreated);

		String text = da.getText(newStatusLanehasbeenCreated);

	
		return text;
	}
	

	public String getTextIssueIsCreatedSuccessfully() {


	//	da.webDriverWait(newStatusLanehasbeenCreated);

		String text = da.getText(issueisCreatedSuccessfully);

	
		return text;
	}
	
	
	
	public void clickOnCreateSprintButton() {

		da.webDriverWait(createSprintBtn);

		da.clickElement(createSprintBtn, "Create Sprint");

	}

	public void clickOnSubmitSprintButton() {

		da.clickElement(submitSprintBtn, "Submit");

	}

	public void inputSprintGoal(String inputSprintGoals) {

		da.sendKeys(sprintGoal, inputSprintGoals, "Sprint Goal");

	}

	public String getTextSprintConfirmationMessage() {

		return da.getText(sprintConfirmationmessage);
	}

	public void clickOnAddMemberButton() {
		da.webDriverWait(addMembersBtn);
		// da.clickElement(addMembersBtn,"Add Member");
		da.clickJavaScript(addMembersBtn, "Add Member");

	}

	public void clickOnAddMemberButtonOnBoard() {
		da.webDriverWait(addMembersBtnOnBoard);
		// da.clickElement(addMembersBtn,"Add Member");
		da.clickJavaScript(addMembersBtnOnBoard, "Add Member");

	}

	public void inputMember(String memberName) {
		da.clickElement(inputMemberName, "Member Text Field");
		// da.sendKeys(inputMemberName, MemberName,"Member Name");

		da.inputData(inputMemberName, memberName, "Member Field");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		da.moveToElementAndClick(selectMember);

		da.clickElement(addMemberText, selectMember.getText());
		// div[@class='rc-virtual-list']

	}

	public void inputMemberOnProjectSetting(String memberName) {
		da.clickElement(inputMemberNameProjectSetting, "Member Text Field");
		// da.sendKeys(inputMemberName, MemberName,"Member Name");

		da.inputData(inputMemberNameProjectSetting, memberName, "Member Field");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		da.moveToElementAndClick(selectMember);
		//da.webDriverWait(addMemberText);
		//da.clickElement(addMemberText, selectMember.getText());
		// div[@class='rc-virtual-list']

		
	}

	public WebElement clickOnSubmitMemberButton() {

		da.clickElement(submitMemberBtn, "Add");

		return submitMemberBtn;

	}

	public void clickOnActivateSprintButton() {

		da.clickElement(activateSprintBtn, "Activate Sprint");

	}
	
	public void clickOnToWorkloadSettings() {
 
		da.webDriverWait(takemeToWorkloadSeetings);
		da.clickElement(takemeToWorkloadSeetings, "take me To Workload Seetings");

	}
	
	public void clickOnSkipSpCuttOffDays() {
		 
		da.webDriverWait(skipspcutoff);
		da.clickElement(skipspcutoff, "skip sp cut off");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void clickOnAddAnotherList() {
		 
		da.webDriverWait(addAnotherList);
		da.clickElement(addAnotherList, "add Another List");

	}
	
	public void inputEnterList(String EnterListTitle1) {

		da.sendKeys(EnterListTitle, EnterListTitle1, "Enter List  Title");
		da.clickElement(Add, "add");

	}
	
	public void clickOnDeleteLane() {
		 
		da.webDriverWait(threeDoticon);
		da.clickElement(threeDoticon, "three Dot icon");
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		da.webDriverWait(DeleteIcon);
		da.clickElement(DeleteIcon, "DeleteIcon");
		da.webDriverWait(DeleteIcon);
		da.clickElement(DeleteIcon, "DeleteIcon");
		da.webDriverWait(okButton1);
		da.clickElement(okButton1, "okButton");
		
	}
	
	public void clickOnHideLanes() {
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		da.webDriverWait(hidelanes);
		da.clickElement(hidelanes, "hide lanes");
		da.webDriverWait(hide);
		da.clickElement(hide, "hide");
	}
	
	
	
	public void clickOnAction() {
		 
		da.webDriverWait(actionWorkloadSettings);
		da.clickElement(actionWorkloadSettings, "action Workload Settings");

	}
	
	public void clickOnSave() {
		 
		da.webDriverWait(clickOnSave);
		da.clickElement(clickOnSave, "click On Save");

	}
	
	public void clickOnCrossSprintButton() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		da.webDriverWait(crossBtn);
		da.clickElement(crossBtn, "crossBtn");

	}
	
	public void clickOnEditSprintButton() {
		da.webDriverWait(editBtn);
		da.clickElement(editBtn, "editBtn");

	}
	
	public String getTextSuccessfulConfirmationMessage() {
		da.webDriverWait(workloadSettingsSuccessfully);

		String text = da.getText(workloadSettingsSuccessfully);

		
		return text;
	}

	
	
	public void clickOnCompleteRadioButton() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		da.webDriverWait(CompleteRadioButton);
		da.clickElement(CompleteRadioButton, "CompleteRadio Button");

	}
	public void clickOnReportSection() {
		da.webDriverWait(Reports);
		da.clickElement(Reports, "crossBtn crossBtn");

	}
	public void clickOnCompleteButton() {
		da.webDriverWait(CompleteBtn);
		da.clickElement(CompleteBtn, "Complete Btn");

	}
	public void clickOnVelocityChart() {
		da.webDriverWait(VelocityChart);
		da.clickElement(VelocityChart, "Velocity Chart");

	}
	
	public void clickOnCreatedProject() {
		da.webDriverWait(createdProject);
		da.clickElement(createdProject, "created Project");

	}
	

	public void verifySwimLane() {

	}

	public String getTextSprintActivateConfirmationMessage() {

		da.webDriverWait(sprintActivateConfirmationmessage);

		String text = da.getText(sprintActivateConfirmationmessage);

		da.clickElement(closeActivationAlertMessage, "Activativation Alert");
		// da.moveToElement(activateSprintBtn);
		return text;
	}

	public void clickOnBoardModule() {

		da.webDriverWait(boardModule);

		da.clickElement(boardModule, "Board Module");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getTextCreateIssueConfirmationMessage() {

		da.moveToElementAndClick(createIssueConfirmationmessage);
		return da.getText(createIssueConfirmationmessage);
	}

	public void clickOnBoardCreateIssueButton() {

		da.clickElement(createIssueFromBoard, "Create Button");

	}

	public WebElement getAllIssue(String createdIssueName) {
		da.webDriverWait(backlogLane);
		da.clickElement(backlogLane, "BacklogLane");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List<WebElement> ll = da.getWebDriver().findElements(By.xpath("(//div[contains(@class,'BacklogTable__T')])"));
		// String issueName=null;
		WebElement issue = null;
		for (int i = 3; i < ll.size(); i++) {
			WebElement we = da.getWebDriver()
					.findElement(By.xpath("(//div[contains(@class,'BacklogTable__T')])[" + i + "]"));

			String issueName = we.getText();

			if (createdIssueName.equalsIgnoreCase(issueName)) {
				// da.uiText_validation(createdProjectName, projectName);
				issue = we;
				break;
			}

		}
		return issue;
	}

	public WebElement getTODOlistFromBoad(String createdIssueName) {
		//
		WebElement we = da.getWebDriver().findElement(
				By.xpath("//div[contains(@class,'text-md my-2 text-base') and text()='" + createdIssueName + "']"));
		da.scrollToElement(we, createdIssueName);
		// String issueName=we.getText();

		// List<WebElement>
		// ll=da.getWebDriver().findElements(By.xpath("//div[contains(@class,'text-md
		// my-2 text-base leading')]"));
		// String issueName=null;
		// WebElement issue=null;
		// for (int i = 1; i < ll.size(); i++) {
		// WebElement we=ll.get(i);
		// da.scrollToElement(we, createdIssueName);
		//
		// issueName=we.getText();
		// if(createdIssueName.equalsIgnoreCase(issueName)) {
		//
		// // da.uiText_validation(createdProjectName, projectName);
		// issue=we;
		// //return issue.getText();
		// break;
		// }
		//
		// }
		// return issue.getText();
		return we;
	}

	public WebElement getTODOlistFromBoadModule(String createdIssueName) {
		//
		WebElement we = da.getWebDriver().findElement(
				By.xpath("//div[contains(@class,'text-md my-2 text-base') and text()='" + createdIssueName + "']"));
		da.scrollToElement(we, createdIssueName);
		WebElement issueName = we;

		return issueName;
	}

	public void openFirstTicket() {
//		if(openFirstTickets.isDisplayed()) {
//			da.clickElement(openFirstTickets, "First Ticket");
//		}
//		else {
		da.clickElement(backlogLane, "Backlog Lane");
		da.webDriverWait(openFirstTickets);
		da.clickJavaScript(openFirstTickets, "First Ticket");
//		}
	}

	public void updatePriority(String priority) {
		selectPriority(priority);
	}

	public void closePopUp() {
		da.webDriverWait(closePopup);
		da.clickElement(closePopup, "Close Popup");
	}

	public void selectSprintDuration(String inputWeek) {
		da.webDriverWait(sprintDuration);
		da.clickElement(sprintDuration, "Duration");

		WebElement we = da.getWebDriver().findElement(By.xpath("//div[text()='" + inputWeek + " Week']"));
		da.clickElement(we, inputWeek);
	}

	public void clickOnTicketKebabMenu() {

		da.clickElement(ticketKebabMenu, "Kebab Menu");

	}

	public void clickOnCloneButton() {

		da.clickElement(cloneButton, "Clone Button");
		da.clickElement(okButton, "OK Button");

	}

	// public void checkActivatedSprint() {
	//
	// WebElement
	// symbol=da.getWebDriver().findElement(By.xpath("//div[@class='ant-collapse-extra']//span[1]"));
	// String attributeValue=da.getAttribute(symbol, "aria-label");
	// if(attributeValue.equalsIgnoreCase("interaction")){
	//
	// }else {
	//
	// }
	//
	// }
	//
	// public void compliteSprint() {
	// da.clickElement(sprintEditButton, "Sprint Edit button");
	//
	//
	// }

	public String getTextActiveSprint() {

		return da.getText(activeSprintText);
	}

	public int TotalMember() {
		return da.getWebDriver().findElements(By.xpath(
				"//div[contains(@class,'ant-avatar-group css')]/span"))
				.size();
	}

	public String getTextMemberAlreadyAddedMessageFromBoard() {
		ProjectAndBoardDashboard projectBoard = new ProjectAndBoardDashboard(da);
		String text = da.getText(boardAddMemberErrorMessage);
		da.clickElement(projectBoard.closePopup, "Add Member PopUp Closed");
		return text;
	}

	public String getTextLastAddedStageFromBoard() {
		String text = da.getText(lastStageName);

		return text;
	}

	public void createNewSprint() {

		BacklogModule backlog = new BacklogModule(da);
		backlog.clickOnCreateSprintButton();

		backlog.selectSprintDuration("2");
		// backlog.inputStartDate("2023-05-31");
		// backlog.inputEndDate("2023-06-20");
		backlog.inputSprintGoal("completed");
		backlog.clickOnSubmitSprintButton();

	}

	public void moveToKebabMenuInBacklog() {
		da.clickElement(backlogKebabMenu, "Click on Kebab Menu");
	}

	public void clickOnMoveToSprintButton() {
		da.clickElement(moveToSprint, "Move To Sprint");
	}

	public void clickOnAddButton() {
		da.clickElement(addButton, "Add Button");
	}

	public void clickOnNewCreatedSprint() {
		da.clickElement(newCreatedSprint, "Sprint");
	}

	public String textOfTicketStage() {
		return da.getText(stageOfTicket);
	}

	public boolean isTicketStageVisible() {
		return stageOfTicket.isDisplayed();
	}

	public WebElement createNewIssueFromBacklog() {

		clickOnCreateIssueBtn();

		String issueName = inputIssueTitle("Issue should be visible");

		selectIssueType("UserStory");

		// selectPriority("Critical");
		//
		// //Add Sprint
		// inputStartDate("2023-06-12");
		//
		// try {
		// Thread.sleep(2000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// inputEndDate("2023-06-12");
		//
		// // inputStoryPoint("7");
		//
		inputDescription("Discription should be visible");

		clickOnCreateButton();

		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement createdIssueName = getAllIssue(issueName);

		return createdIssueName;

	}

	public void addMemberFromBoard(String memberName) {
		clickOnAddMemberButtonOnBoard();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		inputMember(memberName);
		WebElement we = clickOnSubmitMemberButton();

	}

	public void clickOnMoveToBoard(String boardName) {

		da.clickElement(ticketMoveButton, " Board inputField");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		da.clickElement(SelectTeamBoard, " Move Button");

		WebElement board = da.getWebDriver().findElement(By.xpath("//div[text()='" + boardName + "']"));
		da.clickElement(board, boardName);
		clickOnAddButton();

	}

	public void clickOnBackToProject() {

		da.clickElement(clickOnProjectLink, " Project Link");
	}

	public String getTextNoBacklogData() {
		da.webDriverWait(totalNoOfIssueInBacklog);
		return da.getText(totalNoOfIssueInBacklog);
	}

	public void clickOnBacklogSelectAllCheckbox() {

		da.clickElement(backlogSelectAllCheckbox, "Backlog select All Checkbox");
	}

	public void clickOnBacklogMoveButton() {

		da.clickElement(backlogMoveButton, "Backlog Move Button");
	}

	public void selectSprintForMoveAllTicketFromBacklog() {

		da.clickElement(sprintFieldForMoveAllTicketFromBacklog, "Click on Sprint field for move all ticket");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		da.clickElement(sprintForMoveAllTicketFromBacklog, "Click To Sprint for move all ticket from backlog");
		clickOnSubmitMemberButton();
	}

	public int getSizeOfBacklogIssue() {
		return da.getWebDriver()
				.findElements(By.xpath(
						"//div[contains(@class,'BacklogTable__TableContainer-sc')]//div//input[contains(@name,'d')]"))
				.size();

	}

	public int getSizeOfSprintIssueOnBacklogPage() {
		da.clickElement(sprintExpandIcon, "Sprint Expand Button");
		return da.getWebDriver().findElements(By.xpath(
				"//span[text()='Issue should be visible']")).size();
			//	"//div[contains(@class,'BacklogTable__TableContainer-sc')]//div[contains(@class,'BacklogTable__Box-sc-145rst5-2 g')]
	}

	public BacklogModule(DriverActions das) {
		this.da = das;
		PageFactory.initElements(da.getWebDriver(), this);
	}

	public void chooseAttachment() {

		da.sendKeys(choosefile, System.getProperty("user.dir") + "/src/main/resources/Attachments/wilImage.png",
				"choosefile");
	}

	public void enterCurrentDate() {
		String monthValue = null;
		String dayValue= null;
		WebElement click = da.getWebDriver().findElement(By.xpath("//input[@placeholder=\"Select date\"]"));
		click.click();
		LocalDate currentdate = LocalDate.now();
		int year = currentdate.getYear();
		int month = currentdate.getMonthValue();
		if (month < 10) {
			monthValue = "0" + month;}
		else {
			monthValue = "" + month;
		}
		int day = currentdate.getDayOfMonth();
		if (day<10) {
			dayValue= "0"+ day;
		}
		else 
		{	dayValue= ""+ day;
		}
	
		System.out.println("//td[@title='" + year + "-" + month + "-" + day + "']");
		WebElement date = da.getWebDriver()
				.findElement(By.xpath("//td[@title='" + year + "-" + monthValue + "-" + dayValue + "']"));
		date.click();

	}

	public String getTextMemberAlreadyExistInBaordConfirmationMessage() {

		da.webDriverWait(MemberAlreadyExistConfirmationmessage);

		String text = da.getText(MemberAlreadyExistConfirmationmessage);

//		da.clickElement(closeActivationAlertMessage, "Activativation Alert");
		// da.moveToElement(activateSprintBtn);
		return text;
	

}}
