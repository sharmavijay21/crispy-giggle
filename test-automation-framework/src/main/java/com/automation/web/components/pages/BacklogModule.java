package com.automation.web.components.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.GetTagName;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.automation.ui.selenium.DriverActions;
import com.automation.web.elementrepository.BacklogModuleOR;
import com.aventstack.extentreports.Status;

public class BacklogModule extends BacklogModuleOR{

	private DriverActions da;
	private Actions ac;

	public void clickOnCreateIssueBtn() {

		da.clickElement(createIssueFromBacklog,"Create ISSUE");

	}

	public String inputIssueTitle(String inputIssueTitle) {
		da.sendKeys(issueTitle, inputIssueTitle,"Issue Title");

		return inputIssueTitle;
	}

	public void clickOnBugType() {

		da.clickElement(issueType,"Bug Type");

	}
	public void clickOnPriorityType() {

		da.clickElement(priorityType,"Priority Field");

	}

	public void selectIssueType(String type) {

		clickOnBugType();
		if(type.equalsIgnoreCase("Epic")) {
			da.clickElement(epic,"Epic");
		}else if(type.equalsIgnoreCase("Bug")) {
			da.clickElement(bug,"Bug");
		}else if(type.equalsIgnoreCase("Task")) {
			da.clickElement(task,"Task");
		}else if(type.equalsIgnoreCase("Subtask")) {
			da.clickElement(subTask,"SubTask");
		}else if(type.equalsIgnoreCase("UserStory")) {
			da.clickElement(userStory,"UserStory");
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

		if(priority.equalsIgnoreCase("Critical")) {
			da.clickElement(critical,"Critical");
		}else if(priority.equalsIgnoreCase("High")) {
			da.clickElement(high,"High");
		}else if(priority.equalsIgnoreCase("Medium")) {
			da.clickElement(medium,"Medium");
		}else if(priority.equalsIgnoreCase("Low")) {
			da.clickElement(low,"Low");
		}

	}

	public void inputStartDate(String inputStartDate) {

		da.sendKeysWithoutClearingExistingData(startDate, inputStartDate,"Start Date");
		da.sendKeys(startDate, Keys.ENTER);

	}

	public void inputEndDate(String inputEndDate) {

		da.clickElement(endDate, "End Date");
		da.sendKeysWithoutClearingExistingData(endDate, inputEndDate,"End Date");
		da.sendKeys(endDate, Keys.ENTER);
	}
	public void inputStoryPoint(String inputStoryPoint) {

		da.sendKeys(storyPoint, inputStoryPoint,"Story Point");

	}
	public void inputDescription(String inputDescription) {

		da.sendKeys(addDescription, inputDescription,"Description");

	}

	public void clickOnCreateButton() {

		da.clickElement(createButton,"Create");

	}
	public void clickOnCreateSprintButton() {

		da.clickElement(createSprintBtn,"Create Sprint");

	}

	public void clickOnSubmitSprintButton() {

		da.clickElement(submitSprintBtn,"Submit");

	}


	public void inputSprintGoal(String inputSprintGoals) {

		da.sendKeys(sprintGoal, inputSprintGoals,"Sprint Goal");

	}

	public String getTextSprintConfirmationMessage() {

		return da.getText(sprintConfirmationmessage);
	}

	public void clickOnAddMemberButton() {

		//		da.clickElement(addMembersBtn,"Add Member");
		da.clickJavaScript(addMembersBtn, "Add Member");

	}

	public void inputMember(String MemberName) {
		da.clickElement(inputMemberName, "Member Text Field");
		//		da.sendKeys(inputMemberName, MemberName,"Member Name");

		da.inputData(inputMemberName, MemberName, "Member Field");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		da.moveToElement(selectMember);


		//div[@class='rc-virtual-list']

	}
	public WebElement clickOnSubmitMemberButton() {

		da.clickElement(submitMemberBtn,"Add");

		return submitMemberBtn;

	}
	public void clickOnActivateSprintButton() {

		da.clickElement(activateSprintBtn,"Activate Sprint");

	}

	public void verifySwimLane() {

	}

	public String getTextSprintActivateConfirmationMessage() {

		da.webDriverWait(sprintActivateConfirmationmessage);

		String text=da.getText(sprintActivateConfirmationmessage);

		da.clickElement(closeActivationAlertMessage, "Activativation Alert");
		//		da.moveToElement(activateSprintBtn);	
		return text;
	}




	public void clickOnBoardModule() {

		da.clickElement(boardModule,"Board Module");

	}

	public String getTextCreateIssueConfirmationMessage() {

		da.moveToElement(createIssueConfirmationmessage);	
		return da.getText(createIssueConfirmationmessage);
	}

	public void clickOnBoardCreateIssueButton() {

		da.clickElement(createIssueFromBoard,"Create Button");

	}

	public WebElement getAllIssue(String createdBoardName) {

		List<WebElement> ll=da.getWebDriver().findElements(By.xpath("(//div[contains(@class,'BacklogTable__T')])"));
		String issueName=null;
		WebElement issue=null;
		for (int i = 3; i < ll.size(); i=i+6) {
			WebElement we=da.getWebDriver().findElement(By.xpath("(//div[contains(@class,'BacklogTable__T')])["+i+"]"));

			issueName=we.getText();
			if(createdBoardName.equalsIgnoreCase(issueName)) {
				//				da.uiText_validation(createdProjectName, projectName);
				issue=we;
				return issue;
			}

		}
		return issue;
	}
	public String getTODOlistFromBoad(String createdIssueName) {
		//		
		WebElement we=da.getWebDriver().findElement(By.xpath("//div[contains(@class,'text-md my-2 text-base') and text()='"+createdIssueName+"']"));
		da.scrollToElement(we, createdIssueName);
		String issueName=we.getText();

		//		List<WebElement> ll=da.getWebDriver().findElements(By.xpath("//div[contains(@class,'text-md my-2 text-base leading')]"));
		//		String issueName=null;
		//		WebElement issue=null;
		//		for (int i = 1; i < ll.size(); i++) {
		//			WebElement we=ll.get(i);
		//			da.scrollToElement(we, createdIssueName);
		//
		//			issueName=we.getText();
		//			if(createdIssueName.equalsIgnoreCase(issueName)) {
		//
		//				//				da.uiText_validation(createdProjectName, projectName);
		//				issue=we;
		//				//return issue.getText();
		//				break;
		//			}
		//
		//		}
		//		return issue.getText();
		return issueName;
	}


	public void openFirstTicket() {

		da.clickElement(openFirstTicket, "First Ticket");

	}

	public void updatePriority(String priority) {
		selectPriority(priority);
	}


	public void closePopUp() {
		da.clickElement(closePopup, "Close Popup");
	}

	public void selectSprintDuration(String inputWeek) {
		da.clickElement(sprintDuration, "Duration");

		WebElement we=da.getWebDriver().findElement(By.xpath("//div[text()='"+inputWeek+" Week']"));
		da.clickElement(we, inputWeek);
	}

	//	public void checkActivatedSprint() {
	//		
	//		WebElement symbol=da.getWebDriver().findElement(By.xpath("//div[@class='ant-collapse-extra']//span[1]"));
	//String attributeValue=da.getAttribute(symbol, "aria-label");
	//if(attributeValue.equalsIgnoreCase("interaction")){
	//	
	//}else {
	//	
	//}
	//
	//	}
	//	
	//	public void compliteSprint() {
	//		da.clickElement(sprintEditButton, "Sprint Edit button");
	//		
	//		
	//	}

	public String getTextActiveSprint() {


		return da.getText(activeSprintText);
	}

	public int TotalMember() {
		return da.getWebDriver().findElements(By.xpath("//div[@class='ant-avatar-group css-yp8pcc']//span[contains(@class,'ant-avatar ant-avatar-circle ant-avatar-image w-')]")).size();
	}

	public String getTextMemberAlreadyAddedMessageFromBoard() {
		ProjectAndBoardDashboard projectBoard=new ProjectAndBoardDashboard(da);
		String text= da.getText(boardAddMemberErrorMessage);
		da.clickElement(projectBoard.closeAddMemberPopup, "Add Member PopUp Closed");
		return text;
	}

	public void createNewSprint() {

		BacklogModule backlog=new BacklogModule(da);
		backlog.clickOnCreateSprintButton();
		backlog.selectSprintDuration("2");
		//		backlog.inputStartDate("2023-05-31");
		//		backlog.inputEndDate("2023-06-20");
		backlog.inputSprintGoal("completed");
		backlog.clickOnSubmitSprintButton();

	}

	public void moveToKebabMenuInBacklog() {
		da.mouseOver(kebabMenu);
	}
	public void clickOnMoveToSprintButton() {
		da.clickElement(moveToSprint,"Move To Sprint");
	}
	public void clickOnAddButton() {
		da.clickElement(addButton,"Add Button");
	}

	public void clickOnNewCreatedSprint() {
		da.clickElement(newCreatedSprint,"Sprint");
	}
	public String textOfTicketStage() {
		return da.getText(stageOfTicket);
	}

	public WebElement createNewIssueFromBacklog() {

		clickOnCreateIssueBtn();

		String issueName=	inputIssueTitle("Issue should be visible");

		selectIssueType("UserStory");

		selectPriority("Critical");

		//Add Sprint
		inputStartDate("2023-06-12");

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		inputEndDate("2023-06-12");

		inputStoryPoint("0.8");

		inputDescription("Discription should be visible");

		clickOnCreateButton();

		WebElement createdIssueName=getAllIssue(issueName);
		
		return createdIssueName;

	}
  public void addMemberFromBoard(String memberName) {
	clickOnAddMemberButton();
	try {
		Thread.sleep(2000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	//add Validation Part



	inputMember(memberName);
	WebElement we=clickOnSubmitMemberButton();

}

	public BacklogModule(DriverActions das) {
		this.da=das;
		PageFactory.initElements(da.getWebDriver(), this);
	}
}
