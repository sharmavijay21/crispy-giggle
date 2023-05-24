package com.automation.web.components.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.automation.ui.selenium.DriverActions;
import com.automation.web.elementrepository.TicketLandingPageOR;

public class TicketLandingPage extends TicketLandingPageOR{

	private DriverActions da;

	public void clickOnUpdateField() {
		da.clickElement(updateFields, "UpdateFields");
	}

	public void inputUpdatedStoryPoint(String inputUpdatedstoryPoint) {

		da.sendKeys(updateStoryPoint, inputUpdatedstoryPoint,"Updated Story Point");
		da.getWebDriver().findElement(By.xpath("//h2[text()='Update Story Points']")).click();

	}
	public void clickOnStoryPointText() {
		da.clickElement(updateStoryPointText, "UpdateStoryPointText");
	}
	public String getTextUpdatedStoryPoint() {

		return da.getText(updatedStoryPointText);
	}
	public String getTextUpdatedPriority() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String Text=da.getText(priorityText);

		return Text;
	}

	public void updateAssignee(String text) {
		da.clickElement(updateMember, "Update Member");

		da.getWebDriver().findElement(By.xpath("//div[@title='"+text+"']")).click();
	}
	public String getTextUpdatedAssignee(String text) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String Text=da.getText(da.getWebDriver().findElement(By.xpath("(//h2[text()='"+text+"'])[1]")));

		return Text;
	}

	public void clickOnUpdatePriorityField() {

		da.clickElement(updatePriority, "UpdatePriorityField");

	}

	public void clickOnUpdateIssueTypeField() {

		da.clickElement(updateType, "Issue Type");

	}
	public String getTextUpdatedIssueType() {

		//		return da.getAttribute(updatedissueTypeText, "title");
		return da.getText(updatedissueTypeAlertMessage);

	}

	public void selectUpdatePriority(String priority) {

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BacklogModule backlog=new BacklogModule(da);
		clickOnUpdatePriorityField();

		if(priority.equalsIgnoreCase("Critical")) {
			da.clickElement(backlog.critical,"Critical");
		}else if(priority.equalsIgnoreCase("High")) {
			da.clickElement(backlog.high,"High");
		}else if(priority.equalsIgnoreCase("Medium")) {
			da.clickElement(backlog.medium,"Medium");
		}else if(priority.equalsIgnoreCase("Low")) {
			da.clickElement(backlog.low,"Low");
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
		BacklogModule backlog=new BacklogModule(da);

		if(issueType.equalsIgnoreCase("Epic")) {
			da.clickElement(backlog.epic,"Epic");
		}else if(issueType.equalsIgnoreCase("Bug")) {
			da.clickElement(backlog.bug,"Bug");
		}else if(issueType.equalsIgnoreCase("Task")) {
			da.clickElement(backlog.task,"Task");
		}else if(issueType.equalsIgnoreCase("Subtask")) {
			da.clickElement(backlog.subTask,"SubTask");
		}else if(issueType.equalsIgnoreCase("UserStory")) {
			da.clickElement(backlog.userStory,"UserStory");
		}


	}public String updateTicketDescription(String inputDescription) {
		da.clickElement(ticketDescriptionField, "Ticket Description Field");
		da.sendKeys(ticketDescriptionField,inputDescription,"Description Field" );
		da.clickElement(SaveButton, "SaveButton");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return da.getText(descriptionUpdateMessage);
	}

	public TicketLandingPage(DriverActions das) {
		this.da=das;
		PageFactory.initElements(da.getWebDriver(), this);
	}

}
