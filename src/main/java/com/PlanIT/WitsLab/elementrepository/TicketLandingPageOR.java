package com.PlanIT.WitsLab.elementrepository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TicketLandingPageOR {

	@FindBy(xpath = "//span[text()='Update fields']")
	protected WebElement updateFields;

	//	@FindBy(xpath = "//span[text()='Priority']")
	//	protected WebElement updatePriorityType;


	@FindBy(xpath = "//span[text()='Priority']")
	protected WebElement updatePriority;

	@FindBy(xpath = "(//div[contains(@class,'TicketModal__UserDetails')]//h2[contains(@class,'TicketModal__Heading')])[5]")
	protected WebElement priorityText;


	@FindBy(xpath = "//input[@role='spinbutton']")
	protected WebElement updateStoryPoint;

	@FindBy(xpath = "(//div[contains(@class,'TicketModal__DetailCont')]//h2)[3]")
	protected WebElement updatedStoryPointText;

	@FindBy(xpath = "//span[text()='Member']")
	protected WebElement updateAssignee;

	@FindBy(xpath = "(//div[contains(@class,'TicketModal__UserDetails')]//h2[contains(@class,'TicketModal__Heading')])[1]")
	protected WebElement assigneeText;

	@FindBy(xpath = "//span[text()='Type']")
	protected WebElement updateType;

	@FindBy(xpath = "//div[contains(@class,'TicketModal__RoleManagement')]//div")
	protected WebElement updatedissueTypeText;

	@FindBy(xpath = "//div[@role='alert']//div[text()='type Updated Successfully']")
	protected WebElement updatedissueTypeAlertMessage;

	@FindBy(xpath = "//div[@class='quill ']//div[2]")
	protected WebElement ticketDescriptionField;

	@FindBy(xpath = "//button[text()='Save']")
	protected WebElement SaveButton;

	@FindBy(xpath = "//div[text()='description updated.']")
	protected WebElement descriptionUpdateMessage;

	@FindBy(xpath = "//h2[text()='Update Story Points']")
	protected WebElement updateStoryPointText;



	@FindBy(xpath = "//span[text()='Member']")
	protected WebElement updateMember;


	@FindBy(xpath = "//span[@class='ant-select-selection-item']")
	public WebElement ticketStage;


	@FindBy(xpath = "//div[contains(@class,'ant-select-dropdown')]//div[@title='TODO']")
	protected WebElement todoStage;

	@FindBy(xpath = "//div[contains(@class,'ant-select-dropdown')]//div[@title='IN PROGRESS']")
	protected WebElement inProgressStage;

	@FindBy(xpath = "//div[contains(@class,'ant-select-dropdown')]//div[@title='DONE']")
	protected WebElement doneStage;

	@FindBy(xpath = "//a[text()='Delete']")
	protected WebElement ticketDeleteButton;

	@FindBy(xpath = "//span[text()='OK']")
	protected WebElement okButtonOnDeletePopup;

	@FindBy(xpath = "//button[text()='Link Issue']")
	public WebElement linkIssueButton;

	@FindBy(xpath = "//div[@class=' css-13cymwt-control']")
	public WebElement linkIssueTextField;

	@FindBy(xpath = "//div[@class=' css-qr46ko']")
	public WebElement ticketForLinkIssue;

	@FindBy(xpath = "//button[text()='Link']")
    public WebElement linkIssueSubmitButton;
	
	@FindBy(xpath = "//div[text()='link Updated Successfully']")
    public WebElement linkIssueConfirmationMessage;

}
