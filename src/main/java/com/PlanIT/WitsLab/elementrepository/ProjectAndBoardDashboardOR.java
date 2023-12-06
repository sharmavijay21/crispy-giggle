package com.PlanIT.WitsLab.elementrepository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProjectAndBoardDashboardOR {



	@FindBy(xpath="//h2[text()='Your Project ']")
	public WebElement projectHeader;


	@FindBy(xpath="//div[text()=' Create New Project']")
	protected WebElement createNewProject;

	@FindBy(xpath = "//input[@placeholder='Enter Title']")
	protected WebElement projectName;

	@FindBy(xpath = "//textarea[@name='description']")
	protected WebElement description;

	@FindBy(xpath = "//div[text()='Workspace name has been taken by someone else']")
	protected WebElement projectExists;

	//	@FindBy(xpath = "//div[text()='Workspace already created with this name.']")
	//	protected WebElement boardExists;

	@FindBy(xpath = "//div[@role='alert']")
	protected WebElement boardExists;



	@FindBy(xpath = "//button[text()='Cancel']")
	protected WebElement projectCancelButton;


	@FindBy(xpath = "//span[text()='Choose..']")
	protected WebElement type;

	@FindBy(xpath = "(//div[text()='Engineering-IT'])[2]")
	protected WebElement engineering;

	@FindBy(xpath = "(//div[text()='Marketing'])[last()]")
	protected WebElement marketing;

	@FindBy(xpath = "//div[text()='Human Resources']")
	protected WebElement humanResource;

	@FindBy(xpath = "//div[text()='Education']")
	protected WebElement education;

	@FindBy(xpath = "//div[text()='Operations']")
	protected WebElement operations;

	@FindBy(xpath = "//div[text()='Sales CRM']")
	protected WebElement salesCRM;

	@FindBy(xpath = "//div[text()='Small Business']")
	protected WebElement smallBusiness;

	@FindBy(xpath = "//div[text()='Other']")
	protected WebElement other;


	@FindBy(xpath = "//button[text()='Create']")
	public WebElement create;

	@FindBy(xpath = "//h3[text()='Create Project']")
	protected WebElement createNewProjectheader;


	//@FindBy(xpath = "//div[text()=' Create Team Board']")//1st
	@FindBy(xpath = "//div[text()='Create Team Board']")
	//@FindBy(xpath = "//span[@class='anticon anticon-plus-circle']")
	//@FindBy(xpath = "(//div[@class='ProjectSidebar__LeftList-sc-1wdsd6s-26 kzfRSo'])[3]")
	protected WebElement createTeamBoard;

	@FindBy(xpath = "(//div[contains(@class,'Modal__Box1')])[6]")
	protected WebElement selectBackground;

	@FindBy(xpath = "//input[@placeholder='Enter Title']")
	protected WebElement boardTitle;

	@FindBy(xpath = "//textarea[@placeholder='Enter the Job Description']")
	protected WebElement boardDescription;

	@FindBy(xpath = "(//div[@class='Unity__MenuIconSection-sc-1i9vwno-4 iDXERb'])[6]")
	protected WebElement openFirstProject;

   
	
	
	@FindBy(xpath = "(//div[contains(@class,'Create__RecentBox-sc-ytizb')])[1]")
	protected WebElement openFirstProject1;
	@FindBy(xpath = "(//div[contains(@class,'Unity__MainContainer')])[1]")
	protected WebElement openFirstBoard;

	@FindBy(xpath = "//div[contains(@class,'ant-notification')]//div[text()='Project created successflly']")
	public WebElement projectConfirmationmessage;

	@FindBy(xpath = "//div[text()='Board created successflly']")
	public WebElement boardConfirmationMessage;


	@FindBy(xpath = "//div[text()='Project Settings']")
	  //@FindBy(xpath = "//div[text()='Porject Settings']")

	protected WebElement projectSettingButton;

	@FindBy(xpath = "//button[text()='Add Members']")
	protected WebElement AddMemberButtonprojectSetting;

	@FindBy(xpath = "(//span[@class='ant-select-arrow'])[2]//*[name()='svg']")
	protected WebElement memberRole;

	@FindBy(xpath = "//div[text()='Admin']")
	protected WebElement adminRole;

	@FindBy(xpath = "//div[text()='Maintainer']")
	protected WebElement maintainerRole;

	@FindBy(xpath = "//div[text()='User']")
	protected WebElement userRole;

	@FindBy(xpath = "//div[text()='Member already added in workspace']")
	protected WebElement memberAlreadyAddedMessage;

	@FindBy(xpath = "//div[text()='X']")
	public WebElement closePopup;

	@FindBy(xpath = "//a[text()='Project']")
	public WebElement boardHeader;

	@FindBy(xpath = "//button[text()='Cancel']")
	public WebElement cancelButon;

	@FindBy(xpath = "//div[text()='Team Boards']")
	protected WebElement teamBoard;

	@FindBy(xpath = "//div[text()='+ Add New List']")
	protected WebElement addNewList;

	@FindBy(xpath = "(//input[@type='text'])[last()]")
	protected WebElement inputNewStage;

	@FindBy(xpath = "//span[@role='img' and @aria-label='check-circle']")
	protected WebElement addNewStageButton;

	@FindBy(xpath = "//div[text()='Boards']")
	protected WebElement boardModule;
	
	@FindBy(xpath="//button[@class='ReportBackButton']")
	protected WebElement backToProjectButton;
	
	@FindBy(xpath = "//a[@href='/projectsettings/usermanagement']")
	protected WebElement projectSettingsButton;
	
	@FindBy(xpath = "(//a[@href='/projectsettings/fieldconfigurations/customfields'])[1]")
	protected WebElement fieldconfiguration;
    
	@FindBy(xpath = "//button[text()='Create']")
	protected WebElement Createbutton;
	
	@FindBy(xpath = "//div[text()='Checkbox']")
	protected WebElement Checkbox;
	
	@FindBy(xpath = "//input[@placeholder='Enter a title']")
	protected WebElement Title;
	
	@FindBy(xpath = "//span[text()='Issue Type']")
	protected WebElement Issuetype;
	
	@FindBy(xpath = "(//span[@class='ant-checkbox-inner'])[2]")
	protected WebElement IssuetypeCheckbox;
	
	@FindBy(xpath = "//div[@class='CustomModal__MainSection-sc-s43hw4-12 ddctFm']")
	protected WebElement BeforeAddMoreOption;
	
	@FindBy(xpath = "//div[@class='AddDynamicField__AddOption-sc-159gqoa-33 hKipO']")
	protected WebElement AddMoreOption;
	
	@FindBy(xpath = "//button[text()='Add Field']")
	protected WebElement Addfield;
	
	@FindBy(xpath = "//input[@placeholder='Enter options']")
	protected WebElement EnterOption;
	
	@FindBy(xpath = "*//*[@data-icon='delete']")
	protected WebElement Deleteicon;
	
	//@FindBy(xpath = "//span[contains(text(), 'OK')]")
	//@FindBy(xpath = "//button[@class='ant-btn css-3mqfnx ant-btn-primary']/span")
	@FindBy(xpath = "//span[text()='OK']")
	protected WebElement OKButton;
	
	@FindBy(xpath = "//div[text()='No board is deleted']")
	protected WebElement BoardisDeleted;
	
	@FindBy(xpath = "//div[text()='Custom field deleted successfully' or class='ant-notification-notice-message']")
	protected WebElement CustomfieldSuccessfully;
	
	@FindBy(xpath = "*//*[@data-icon='more']")
	protected WebElement Addmore;
	
	@FindBy(xpath = "//a[text()='Delete']")
	protected WebElement DeleteiconOption;
	
	@FindBy(xpath = "//div[text()='Archived']")
	protected WebElement Archived;
	
	@FindBy(xpath = "//a[text()='Revert Back']")
	protected WebElement RevertBack;
	
	@FindBy(xpath = "//div[text()='Notifications' and @class='SprintSidebar__ItemText-sc-sykxpl-4 cJwZCe']")
	protected WebElement notification;
	
	@FindBy(xpath = "(//span[@class='ant-checkbox-inner'])[6]")
	protected WebElement WatcherAdmin;
	
	@FindBy(xpath = "//button[text()='Submit']")
	protected WebElement Submit;
	
	@FindBy(xpath = "//div[text()='Notification data saved successfully']")
	protected WebElement NotificationSavedMessage;
	
	@FindBy(xpath = "//div[@class='Create__MenuIconWrap-sc-ytizbg-8 iAeDf']")
	protected WebElement NotificationButton;
	
	@FindBy(xpath = "*//*[@data-icon='ellipsis']")
	protected WebElement notificationThreeDot;
	
	@FindBy(xpath = "(//li[@role='menuitem'])[2]")
	protected WebElement MarkAllRead;
	
	@FindBy(xpath = "//div[text()='Unread']")
	protected WebElement Unread;
	
	@FindBy(xpath = "//div[text()='No unread notifications']")
	protected WebElement NoUnreadnotification;
	
	@FindBy(xpath = "*//*[@data-icon='delete']")
	protected WebElement deleteIcon;
	
	//@FindBy(xpath = "(//th[text()='Action'])[2]")
	@FindBy(xpath = "(//table)[2]//th[2]")
	protected WebElement Scroll;
	
	@FindBy(xpath = "//div[text()='Member has been deleted successfully']")
	protected WebElement DeleteSuccessfully;
	
	@FindBy(xpath = "*//*[@data-icon='close-circle']")
	protected WebElement Admincheckbox;
	
	
}
