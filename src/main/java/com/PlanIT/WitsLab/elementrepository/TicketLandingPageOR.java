package com.PlanIT.WitsLab.elementrepository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TicketLandingPageOR {

	@FindBy(xpath = "//span[text()='Update fields']")
	protected WebElement updateFields;
	
	@FindBy(xpath = "(//div[@class='ant-picker-input'])[2]")
	protected WebElement EstimatedTime;
	
	@FindBy(xpath = "(//div[text()='06' or class='ant-picker-time-panel-cell-inner'])[1]")
	protected WebElement selectEstimatedTime;
	
	@FindBy(xpath = "//span[text()='OK']")
    protected WebElement OkButton;

	@FindBy(xpath = "(//div[contains(@class,'TicketModal__DetailWrapper')]/h2[text()='Priority']/following-sibling::div/div)[2]/span")
	protected WebElement updatePriority;

	@FindBy(xpath = "//span[text()='Priority']")
	protected WebElement selectPriortyfield;

	@FindBy(xpath = "//div[contains(@class,'ant-ribbon ant-ribbon-placement')]//span")
	protected WebElement priorityText;

	@FindBy(xpath = "(*//*[@data-icon='edit'])[3]")
	protected WebElement clickOnStoryPointsEditbutton;

	@FindBy(xpath = "//input[@role='spinbutton']")
	protected WebElement selectStoryPointInputBox;

	@FindBy(xpath = "//div[contains(@class,'TicketM')]/div/span/span")
	protected WebElement storyPointsSelectTickButton;

//	@FindBy(xpath = "//input[@role='spinbutton']")
//	protected WebElement updateStoryPoint;

	@FindBy(xpath = "(//div[contains(@class,'TicketModal__DetailCont')]//h2)[3]")
	protected WebElement updatedStoryPointText;

	@FindBy(xpath = "//span[text()='Member']")
	protected WebElement updateAssignee;

	@FindBy(xpath = "(//div[contains(@class,'TicketModal__UserDetails')]//h2[contains(@class,'TicketModal__Heading')])[1]")
	protected WebElement assigneeText;

	@FindBy(xpath = "(//div[contains(@class,'TicketModal__DetailWrapper')]/h2[text()='Update Type']/following-sibling::div/div)/span")
	protected WebElement updateIssueTypeEditButton;

	@FindBy(xpath = "//span[text()='Type']")
	protected WebElement selectUpdateIssueTypeDropDown;

	@FindBy(xpath = "//div[contains(@class,'TicketModal__RoleManagement')]//div")
	protected WebElement updatedissueTypeText;

	@FindBy(xpath = "//div[@role='alert']//div[text()='Type Updated Successfully']")
	protected WebElement updatedissueTypeAlertMessage;

	@FindBy(xpath = "//h2[text()='bug']")
	protected WebElement updateTypeAfterChange;

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
	protected WebElement linkIssueButton;

	@FindBy(xpath = "//div[@class=' css-13cymwt-control']")
	protected WebElement linkIssueTextField;

	@FindBy(xpath = "//div[@class=' css-qr46ko']")
	protected WebElement ticketForLinkIssue;

	@FindBy(xpath = "//button[text()='Link']")
	protected WebElement linkIssueSubmitButton;

	@FindBy(xpath = "//div[text()='Link Updated Successfully']")
	public WebElement linkIssueConfirmationMessage;

	@FindBy(xpath = "//div[contains(@class,'TicketModal__Desc-sc')]")
	protected WebElement descriptionBox;

	@FindBy(xpath = "//div[@class='editor-class rdw-editor-main']/div[@class='DraftEditor-root']")
	protected WebElement inputFieldDescriptionAndComment;

	@FindBy(css = ".DraftEditor-root > .DraftEditor-editorContainer")
	protected WebElement inputBox;

	@FindBy(xpath = "//div[contains(@class,'public-DraftSt')]/span/span")
	protected WebElement descriptionClear;

	@FindBy(xpath = "//button[text()='Comments']")
	protected WebElement commentbutton;

	@FindBy(xpath = "//input[@placeholder='Add a Comment...']")
	protected WebElement commentInputBox;

}
