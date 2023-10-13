package com.PlanIT.WitsLab.elementrepository;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProjectAndBoardDashboardOR {



	@FindBy(xpath="//h2[text()='Your Project ']")
	public WebElement projectHeader;


	@FindBy(xpath="//button[text()='Create New Project']")
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


	@FindBy(xpath = "//div[text()=' Create Team Board']")
	protected WebElement createTeamBoard;

	@FindBy(xpath = "(//div[contains(@class,'Modal__Box1')])[6]")
	protected WebElement selectBackground;

	@FindBy(xpath = "//input[@placeholder='Enter Title']")
	protected WebElement boardTitle;

	@FindBy(xpath = "//textarea[@placeholder='Enter the Job Description']")
	protected WebElement boardDescription;

	@FindBy(xpath = "(//div[contains(@class,'Create__RecentBox-sc-ytizb')])[1]")
	protected WebElement openFirstProject;


	@FindBy(xpath = "(//div[contains(@class,'Unity__MainContainer')])[1]")
	protected WebElement openFirstBoard;

	@FindBy(xpath = "//div[contains(@class,'ant-notification')]//div[text()='Project created successflly']")
	public WebElement projectConfirmationmessage;

	@FindBy(xpath = "//div[text()='Board created successflly']")
	public WebElement boardConfirmationMessage;


	@FindBy(xpath = "//div[text()='Project Settings']")
	protected WebElement projectSettingButton;

	@FindBy(xpath = "//button[text()='Add Members']")
	protected WebElement AddMemberButtonprojectSetting;

	@FindBy(xpath = "//span[text()='Select Role']")
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
	

	


}
