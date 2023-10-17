package com.PlanIT.WitsLab.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.PlanIT.WitsLab.elementrepository.TicketLandingPageOR;
import com.PlanIT.WitsLab.ui.selenium.DriverActions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class TicketLandingPage extends TicketLandingPageOR {

	private DriverActions da;
	protected static DriverActions das;

	public void clickOnUpdateField() {
		da.webDriverWait(updateFields);
		da.clickElement(updateFields, "UpdateFields");
	}

	public void inputUpdatedStoryPoint(String inputUpdatedstoryPoint) {

//		updateStoryPoint.clear();
		da.clickElement(clickOnStoryPointsEditbutton, "Click on Edit button");

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		da.clickElement(selectStoryPointInputBox, " Select story point drop down");
//		selectStoryPointDropDown.clear();
		da.sendKeys(selectStoryPointInputBox, inputUpdatedstoryPoint, "Updated Story Point");

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		da.clickElement(storyPointsSelectTickButton, "Click on TickButton");

//		da.sendKeys(updateStoryPoint, inputUpdatedstoryPoint,"Updated Story Point");
//		da.getWebDriver().findElement(By.xpath("//h2[text()='Update Story Points']")).click();

	}

	public void clickOnStoryPointText() {
		da.clickElement(updateStoryPointText, "UpdateStoryPointText");
	}

	public String getTextUpdatedStoryPoint(String inputUpdatedstoryPoint) {

		String storyPoints = da.getText(da.getWebDriver().findElement(
				By.xpath("//div[contains(@class,'TicketModal__S')]/h2[text()='" + inputUpdatedstoryPoint + "']")));

		return storyPoints;
	}

	public String getTextUpdatedPriority() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String Text = da.getText(priorityText);

		return Text;
	}

	public void updateAssignee(String text) {
		da.clickElement(updateMember, "Update Member");
		da.inputData(updateMember, "Sunil", "Update Member");

		da.getWebDriver().findElement(By.xpath("//div[@title='" + text + "']")).click();
	}

	public String getTextUpdatedAssignee(String text) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String Text = da.getText(da.getWebDriver().findElement(
				By.xpath("//h2[contains(@class,'TicketModal__Heading')]/div//div[text()='" + text + "']")));

		return Text;
	}

	public void clickOnUpdatePriorityField() throws InterruptedException {
		Thread.sleep(2000);
		da.clickElement(updatePriority, "UpdatePriorityField");
		da.webDriverWait(selectPriortyfield);
		da.clickElement(selectPriortyfield, "Select update property");

	}

	public void clickOnUpdateIssueTypeField() {

		da.clickElement(updateIssueTypeEditButton, "updateIssueType editButton");
		da.webDriverWait(selectUpdateIssueTypeDropDown);
		da.clickElement(selectUpdateIssueTypeDropDown, "selectUpdateIssueTypeDropDown()");

	}

	public String getTextUpdatedIssueType() {
		da.webDriverWait(updateTypeAfterChange);
		// return da.getAttribute(updatedissueTypeText, "title");
		return da.getText(updateTypeAfterChange);
//		return da.getText(updatedissueTypeAlertMessage);

	}

	public void selectUpdatePriority(String priority) throws InterruptedException {

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BacklogModule backlog = new BacklogModule(da);
		clickOnUpdatePriorityField();

		if (priority.equalsIgnoreCase("Critical")) {
			da.clickElement(backlog.critical, "Critical");
		} else if (priority.equalsIgnoreCase("High")) {
			da.clickElement(backlog.high, "High");
		} else if (priority.equalsIgnoreCase("Medium")) {
			da.clickElement(backlog.medium, "Medium");
		} else if (priority.equalsIgnoreCase("Low")) {
			da.clickElement(backlog.low, "Low");
		}

	}

	public void selectUpdateIssueType(String issueType) {

		clickOnUpdateIssueTypeField();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BacklogModule backlog = new BacklogModule(da);

		if (issueType.equalsIgnoreCase("Epic")) {
			da.clickElement(backlog.epic, "Epic");
		} else if (issueType.equalsIgnoreCase("Bug")) {
			da.clickElement(backlog.bug, "Bug");
		} else if (issueType.equalsIgnoreCase("Task")) {
			da.clickElement(backlog.task, "Task");
		} else if (issueType.equalsIgnoreCase("Subtask")) {
			da.clickElement(backlog.subTask, "SubTask");
		} else if (issueType.equalsIgnoreCase("UserStory")) {
			da.clickElement(backlog.userStory, "UserStory");
		}

	}

	public void clickOnTicketStage() {
		da.clickElement(ticketStage, "TicketStage");
	}

	public void selectTicketStage(String inputTicketStage) {

		clickOnTicketStage();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		BacklogModule backlog = new BacklogModule(da);

		if (inputTicketStage.equalsIgnoreCase("todo")) {
			da.clickElement(todoStage, "TODO Stage");
		} else if (inputTicketStage.equalsIgnoreCase("in progress")) {
			da.clickElement(inProgressStage, "IN PROGRESS");

		} else if (inputTicketStage.equalsIgnoreCase("done")) {
			da.clickElement(doneStage, "DONE");
		}
	}

	public String updateTicketDescription(String inputDescription) {
		da.clickElement(ticketDescriptionField, "Ticket Description Field");
		da.sendKeys(ticketDescriptionField, inputDescription, "Description Field");
		da.clickElement(SaveButton, "SaveButton");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return da.getText(descriptionUpdateMessage);
	}

	public void ticketDelete() {
		da.clickElement(ticketDeleteButton, " Delete Button");
		da.webDriverWait(okButtonOnDeletePopup);
		da.clickElement(okButtonOnDeletePopup, "OK Button");
	}

	public void clickOnLinkIssueButton() {

		da.clickElement(linkIssueButton, "Link Issue");

	}

	public String selectIssueForLink() {
		da.clickElement(linkIssueTextField, "LinkIssueField");
		da.clickElement(ticketForLinkIssue, "ticket");
		da.webDriverWait(linkIssueSubmitButton);
		da.clickElement(linkIssueSubmitButton, "Link");
		da.webDriverWait(linkIssueConfirmationMessage);
		return da.getText(linkIssueConfirmationMessage);
	}

	public String addDescription(String descriptionData) throws InterruptedException {

		da.actionClick(descriptionBox, "Description Box");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		da.actionClick(inputFieldDescriptionAndComment, "Description box");
		// Actions a1= new Actions((WebDriver) das);
		Thread.sleep(3000);
		descriptionClear.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		descriptionClear.sendKeys(Keys.chord(Keys.CONTROL, " "));
//		descriptionClear.clear();
		// da.clearDataFromInputBox(descriptionClear);// js executer
		// da.actionSendKey(descriptionBox1, descriptionData, "Description Field");
//		da.actionClick(SaveButton, "Save Button");
//		da.actionClick(descriptionBox, "Description Box");
//		da.actionClick(descriptionClear, "Description box");
		da.actionSendKey(descriptionClear, descriptionData, "Description Field");
		Thread.sleep(3000);

		da.actionClick(SaveButton, "Save Button");

		return da.getWebDriver()
				.findElement(
						By.xpath("//div[contains(@class,'diXKuF')]/div/p[contains(text(),'" + descriptionData + "')]"))
				.getText();
	}

	public String addComment(String commentData) {

		da.actionClick(commentbutton, "comment Box");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		da.actionClick(commentInputBox, "comment Box");

		da.scrollToElement(inputFieldDescriptionAndComment, " Comment Field");
		da.actionSendKey(inputFieldDescriptionAndComment, commentData, "comment Field");
		da.scrollToElement(SaveButton, "Save Button");

		da.actionClick(SaveButton, "Save Button");

		return da.getWebDriver().findElement(By.xpath("//p[text()='" + commentData + "']")).getText();

	}

	public TicketLandingPage(DriverActions das) {
		this.da = das;
		PageFactory.initElements(da.getWebDriver(), this);
	}

}
