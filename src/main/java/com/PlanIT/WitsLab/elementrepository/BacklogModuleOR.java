package com.PlanIT.WitsLab.elementrepository;

import javax.xml.xpath.XPath;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

public class BacklogModuleOR {

	@FindBy(xpath = "//p[text()='Create']")
	protected WebElement createIssueFromBacklog;


	@FindBy(xpath = "//input[@placeholder='Enter Title']")
	public WebElement issueTitle;

	@FindBy(xpath = "//span[text()='Bug']")
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

	@FindBy(xpath = "//div[text()='User Story']")
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
	protected WebElement dueDate;

//	@FindBy(xpath = "(//input[@placeholder='Select date'])[2]")
//	protected WebElement endDate;

	@FindBy(xpath = "//div[text()='End Date']")
	protected WebElement endDateText;

	@FindBy(xpath = "//input[@role='spinbutton']")
	protected WebElement storyPoint;

	@FindBy(xpath = "//button[text()='Create']")
	protected WebElement createButton;
	
	@FindBy(xpath = "//span[@class='anticon anticon-check-circle']")
	protected WebElement completeSprint;
	
	@FindBy(xpath = "//div[text()='Issues']")
	protected WebElement Issues;
	
	
	@FindBy(xpath = "//span[text()='Select Issue Type']")
	protected WebElement selectIssueType;
	
	@FindBy(xpath = "//div[text()='Bug']")
	protected WebElement Bug;
	
	@FindBy(xpath = "//button[text()='Reset Filter']")
	protected WebElement resetFilter;
	
	@FindBy(xpath = "//td[text()='Issue is created successfully']")
	protected WebElement Issuescreated;
	
	@FindBy(xpath = "//span[text()='OK']")
	protected WebElement OkButton;
	
	@FindBy(xpath = "//button[text()='Skip']")
	protected WebElement Skip;
	
	@FindBy(xpath = "//div[text()='Past Sprints']")
	protected WebElement pastSprint;
	
	@FindBy(xpath = "*//*[@data-icon='right']")
	protected WebElement expandicon;
	
	@FindBy(xpath = "//button[@placeholder='Create Sub Task']")
	protected WebElement createSubtask;
	
	@FindBy(xpath = "//input[@placeholder='Enter Name of SubTask']")
	protected WebElement EnterSubtask;

	@FindBy(xpath = "//button[text()='Save']")
	protected WebElement EnterSave;
	
	
	@FindBy(xpath = "*//*[@data-icon='close']")
	protected WebElement closeicon; 
	
	
	@FindBy(xpath = "//div[text()='Subtask Deleted Successfully']")	
	protected WebElement TaskCreatedSuccessfully;
	
	@FindBy(xpath = "//h3[text()='Workload Seetings Successful!']")	
	protected WebElement workloadSettingsSuccessfully;
	
	@FindBy(xpath = "//div[text()='Project with only Completed Spints can be Deleted ']")	
	protected WebElement BoardDelete;
	
	@FindBy(xpath = "//div[@id='close']")
	protected WebElement close;
	
	@FindBy(xpath = "//div[text()='Create Sprint']")
	protected WebElement createSprintBtn;

	@FindBy(xpath = "//textarea[@name='sprintGoal']")
	protected WebElement sprintGoal;

	@FindBy(xpath = "//button[text()='Submit']")
	protected WebElement submitSprintBtn;

	@FindBy(xpath = "//div[text()='Sprint created successfully']")
	protected WebElement sprintConfirmationmessage;

	@FindBy(xpath = "(//div[text()='Add Members'])[1]")
	protected WebElement addMembersBtn;

	
	
	@FindBy(xpath = "(//div[text()='Add Members'])[last()]")
	protected WebElement addMembersBtnOnBoard;



	//	@FindBy(xpath = "(//input[@type='search'])[1]")
	//	@FindBy(xpath = "(//div[@class='ant-select-selector']//div)[1]")
	//    @FindBy(xpath = "((//input[@type='search'])[1]/ancestor::div[contains(@class,'ant-select-selection-overflow')])[last()]")
	@FindBy(xpath = "//div[@style='opacity: 1;']")
	protected WebElement inputMemberName;

	@FindBy(xpath = "(//input[@type='search'])[1]")
	protected WebElement inputMemberNameProjectSetting;


	@FindBy(xpath = "(//div[contains(@class,'ant-select-item-option')])[2]")
	protected WebElement selectMember;

	@FindBy(xpath = "//h1[contains(text(),'Add')]")
	protected WebElement addMemberText;




	@FindBy(xpath = "//button[text()='Add']")
	protected WebElement submitMemberBtn;

	@FindBy(xpath = "//span[@aria-label='interaction']")
	protected WebElement activateSprintBtn;
	
	@FindBy(xpath = "//div[text()='Yes, Take me to Workload settings']")
	protected WebElement takemeToWorkloadSeetings;
	
	@FindBy(xpath = "//div[text()='Skip, SP Cut-off days will remain same!']")
	protected WebElement skipspcutoff;
	
	@FindBy(xpath = "//span[text()='ADD ANOTHER LIST']")
	protected WebElement addAnotherList;
	
	@FindBy(xpath = "*//*[@data-icon='more']")
	protected WebElement threeDoticon;
	
	@FindBy(xpath = "//a[text()='Delete']")
	protected WebElement DeleteIcon;
	
	
	
	@FindBy(xpath = "//button[text()='Hide Lanes']")
	protected WebElement hidelanes;
	
	@FindBy(xpath = "//div[text()='Hide']")
	protected WebElement hide;
	
	@FindBy(xpath = "//input[@placeholder='Enter list title...']")
	protected WebElement EnterListTitle;
	
	@FindBy(xpath = "//button[text()='Add']")
	protected WebElement Add;
	
	@FindBy(xpath = "//a[@class='ant-typography css-3mqfnx']")
	protected WebElement actionWorkloadSettings;
	
	@FindBy(xpath = "//span[text()='Save']")
	protected WebElement clickOnSave;
	
	@FindBy(xpath = "//div[text()='X']")
	protected WebElement crossBtn;
	
	@FindBy(xpath = "*//*[@data-icon='edit']")
	protected WebElement editBtn;
	
	@FindBy(xpath = "//div[text()='Complete']")
	protected WebElement CompleteBtn;
	
	@FindBy(xpath = "//div[text()='Sprint Completed!']")
	protected WebElement SprintCompleted;
	
	@FindBy(xpath = "//p[text()='No Sprint Data']")
	protected WebElement noSprintData;
	
	@FindBy(xpath = "//div[text()='ADD LANE']")
	protected WebElement newStatusLanehasbeenCreated;
	
	@FindBy(xpath = "//span[text()='Issue is created successfully']")
	protected WebElement issueisCreatedSuccessfully;
	
	@FindBy(xpath = "//button[text()='Skip']")
	protected WebElement SkipButton;
	
	@FindBy(xpath = "//span[@class='ant-radio ant-wave-target']")
	protected WebElement CompleteRadioButton;
	
	@FindBy(xpath = "//div[text()='Reports']")
	protected WebElement Reports;
	
	@FindBy(xpath = "(//div[text()='Velocity Chart'])[2]")
	protected WebElement VelocityChart;
	
	@FindBy(xpath = "//a[@href='/project']")
	protected WebElement createdProject;

	@FindBy(xpath = "//div[text()='Sprint Activated successfully']")
	protected WebElement sprintActivateConfirmationmessage;


	@FindBy(xpath ="//div[text()='Board']") // "(//div[contains(@class,'ant-tabs-tab-btn')])[2]//p[text()=' Board']")
	protected WebElement boardModule;


	@FindBy(xpath = "//h3[text()='Backlog']")
	public WebElement backlogLane;



	@FindBy(xpath = "//li[@class='mt-3']//p[text()='Create']")
	protected WebElement createIssueFromBoard;

	@FindBy(xpath = "//div[text()='Issue Created Successfully']")
	protected WebElement createIssueConfirmationmessage;

	
	@FindBy(xpath = "//button[contains(@class,'BacklogTable__TableDataScrollSection')]//div[contains(@class,'BacklogTable__Box')]")
    // @FindBy(xpath ="//div[text()='TODO']")
	protected WebElement openFirstTickets;

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


	//	@FindBy(xpath = "//div[@id='close']")
	//	protected WebElement closePopup;

	@FindBy(xpath = "//div[text()='X']")
	protected WebElement closePopup;



	@FindBy(xpath = "//span[@aria-label='more']")
	protected WebElement backlogKebabMenu;

	@FindBy(xpath = "//a[text()='Move']")
	protected WebElement ticketMoveButton;

	@FindBy(xpath = "//div[contains(@class,'TicketModal__MenuIconWrap')]")
	protected WebElement ticketKebabMenu;

	@FindBy(xpath = "//span[text()='Select TeamBoard']")
	protected WebElement SelectTeamBoard;


	@FindBy(xpath = "(//a[text()='Move to sprint'])[1]")
	protected WebElement moveToSprint;

	@FindBy(xpath = "//button[text()='Add']")
	public WebElement addButton;

	@FindBy(xpath = "(//span[@class='ant-breadcrumb-link'])[2]")
	public WebElement clickOnProjectLink;

	@FindBy(xpath = "//*[text()='TODO']")
	protected WebElement stageOfTicket;

	//	@FindBy(xpath = "(//h4[contains(@class,' p-3 flex justify-between')])[last()]")
	@FindBy(xpath = "(//h4[contains(@class,' p-3 flex justify-between')])[last()]//div//div")
	protected WebElement lastStageName;

	@FindBy(xpath = "//a[text()='Clone']")
	protected WebElement cloneButton;

	@FindBy(xpath = "//span[text()='OK']")
	protected WebElement okButton;
	
	@FindBy(xpath = "//button//span[contains(text(), 'OK')]")
	protected WebElement okButton1;
	//	@FindBy(xpath = "(//div[contains(@class,'TicketModal__UserDetails')]//h2[contains(@class,'TicketModal__Heading')])[1]")
	//	public WebElement typeText;
	@FindBy(xpath = "//div[text()='(0 Issue)']")
	protected WebElement totalNoOfIssueInBacklog;

	@FindBy(xpath = "//input[@name='All']")
	public WebElement backlogSelectAllCheckbox;

	@FindBy(xpath = "//button[text()='Move']")
	public WebElement backlogMoveButton;

	@FindBy(xpath = "(//span[@class=\"ant-select-selection-item\"])[2]")//(//div[@class='ant-select css-yp8pcc ant-select-single ant-select-allow-clear ant-select-show-arrow'])[2]")
	public WebElement sprintFieldForMoveAllTicketFromBacklog;

	@FindBy(xpath = "//div[@class='ant-select-item-option-content']")
	public WebElement sprintForMoveAllTicketFromBacklog;

	@FindBy(xpath = "//div[contains(@class,'BacklogTable__TableContainer-sc-135rst5')]//div//input[contains(@name,'d')]")
	public WebElement totalIssueCountOfBacklog;

	@FindBy(xpath = "//div[contains(@class,'BacklogTable__TableContainer-sc')]//div[contains(@class,'BacklogTable__Box-sc-145rst5-2 g')]")
	public WebElement totalIssueCountOfSprint;

	@FindBy(xpath = "(//div[@class='ant-collapse-expand-icon'])[2]")
	public WebElement sprintExpandIcon;
	
	@FindBy (xpath="//input[@type='file']")
	public WebElement choosefile;
	
	@FindBy(xpath = "//div[text()='Members already exist in the project']")
	public WebElement MemberAlreadyExistConfirmationmessage;


}
