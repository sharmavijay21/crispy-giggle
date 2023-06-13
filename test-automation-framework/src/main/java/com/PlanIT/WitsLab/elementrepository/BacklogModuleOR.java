package com.PlanIT.WitsLab.elementrepository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BacklogModuleOR {

	@FindBy(xpath = "//p[text()='Create']")
	protected WebElement createIssueFromBacklog;


	@FindBy(xpath = "//input[@placeholder='Enter Title']")
	public WebElement issueTitle;

	@FindBy(xpath = "//span[text()='Task']")
	protected WebElement issueType;


	@FindBy(xpath = "//span[text()='Select Priority']")
	protected WebElement priorityType;



	@FindBy(xpath = "//span[text()='Type a name or email address']")
	protected WebElement assignee;

	@FindBy(xpath = "//div[@data-placeholder='Add a description...']")
	protected WebElement addDescription;

	@FindBy(xpath = "//div[text()='Epic']")
	public WebElement epic;

	@FindBy(xpath = "//div[text()='Bug']")
	public WebElement bug;

	@FindBy(xpath = "//div[text()='Task']")
	public WebElement task;

	@FindBy(xpath = "//div[text()='SubTask']")
	public WebElement subTask;

	@FindBy(xpath = "//div[text()='UserStory']")
	public WebElement userStory;

	@FindBy(xpath = "(//div[text()='Critical'])[last()]")
	public WebElement critical;

	@FindBy(xpath ="(//div[text()='High'])[last()]")
	public WebElement high;

	@FindBy(xpath = "(//div[text()='Medium'])[last()]")
	public WebElement medium;

	@FindBy(xpath = "(//div[text()='Low'])[last()]")
	public WebElement low;

	@FindBy(xpath = "(//input[@placeholder='Select date'])[1]")
	protected WebElement startDate;

	@FindBy(xpath = "(//input[@placeholder='Select date'])[2]")
	protected WebElement endDate;

	@FindBy(xpath = "//div[text()='End Date']")
	protected WebElement endDateText;

	@FindBy(xpath = "//input[@role='spinbutton']")
	protected WebElement storyPoint;

	@FindBy(xpath = "//button[text()='Create']")
	protected WebElement createButton;

	@FindBy(xpath = "//p[text()='Create Sprint']")
	protected WebElement createSprintBtn;

	@FindBy(xpath = "//textarea[@name='sprintGoal']")
	protected WebElement sprintGoal;

	@FindBy(xpath = "//button[text()='Submit']")
	protected WebElement submitSprintBtn;

	@FindBy(xpath = "//div[text()='Sprint created successfully']")
	protected WebElement sprintConfirmationmessage;

	@FindBy(xpath = "//p[text()='Add Members']")
	protected WebElement addMembersBtn;

	@FindBy(xpath = "(//input[@type='search'])[1]")
	protected WebElement inputMemberName;

	@FindBy(xpath = "(//div[contains(@class,'ant-select-item-option')])[2]")
	protected WebElement selectMember;

	@FindBy(xpath = "//h1[contains(text(),'Add')]")
	protected WebElement addMemberText;

	
	

	@FindBy(xpath = "//button[text()='Add']")
	protected WebElement submitMemberBtn;

	@FindBy(xpath = "//span[@aria-label='interaction']")
	protected WebElement activateSprintBtn;

	@FindBy(xpath = "//div[text()='Sprint Activated successfully']")
	protected WebElement sprintActivateConfirmationmessage;


	@FindBy(xpath = "(//div[contains(@class,'ant-tabs-tab-btn')])[2]//p[text()=' Board']")
	protected WebElement boardModule;

	@FindBy(xpath = "//li[@class='mt-3']//p[text()='Create']")
	protected WebElement createIssueFromBoard;

	@FindBy(xpath = "//div[text()='Issue Created Successfully']")
	protected WebElement createIssueConfirmationmessage;


	@FindBy(xpath = "//div[contains(@class,'BacklogTable__')]//div//div")
	protected WebElement openFirstTicket;

	@FindBy(xpath = "//span[contains(@class,'ant-select-selection')]")
	protected WebElement sprintDuration;

	@FindBy(xpath = "(//span[@aria-label='edit'])[1]")
	protected WebElement sprintEditButton;

	@FindBy(xpath = "//h3[contains(text(),'( active')]")
	protected WebElement activeSprintText;

	@FindBy(xpath = "//div[text()='Member is already added']")
	protected WebElement boardAddMemberErrorMessage;

	@FindBy(xpath = "(//div[@class='ant-collapse-header'])[last()]")
	public WebElement newCreatedSprint;


	@FindBy(xpath = "//a//span[contains(@class,'ant-notification')]")
	public WebElement closeActivationAlertMessage;


	@FindBy(xpath = "//div[@id='close']")
	protected WebElement closePopup;


	@FindBy(xpath = "//span[@aria-label='more']")
	protected WebElement kebabMenu;

	@FindBy(xpath = "(//a[text()='Move to sprint'])[1]")
	protected WebElement moveToSprint;

	@FindBy(xpath = "//button[text()='Add']")
	protected WebElement addButton;

	@FindBy(xpath = "(//button[contains(@class,'Button__StyledButton')])[1]")
	protected WebElement stageOfTicket;

	
	//	@FindBy(xpath = "(//div[contains(@class,'TicketModal__UserDetails')]//h2[contains(@class,'TicketModal__Heading')])[1]")
	//	public WebElement typeText;



}
