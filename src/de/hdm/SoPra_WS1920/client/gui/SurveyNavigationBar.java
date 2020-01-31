package de.hdm.SoPra_WS1920.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

import de.hdm.SoPra_WS1920.client.CinemaAdminEntry;
import de.hdm.SoPra_WS1920.client.ClientsideSettings;
import de.hdm.SoPra_WS1920.client.SurveyManagementEntry;
import de.hdm.SoPra_WS1920.shared.SurveyManagementAsync;
import de.hdm.SoPra_WS1920.shared.bo.Person;




public class SurveyNavigationBar extends FlowPanel {
	
	SurveyManagementHeader header;
	SurveyContent surveyContent;
	Person p;
	
	Image menuIcon;
	Image logoIcon;
	
	Label logoText;
	Label movies;
	Label groups;
	Label surveys;
	Label settings;
	Label switchToAdmin;
	Label logOut;
	Button b;
	CinemaAdminEntry cinemaAdminEntry;
	
	SurveyManagementAsync surveyManagement;
	
	public SurveyNavigationBar(SurveyManagementHeader header,SurveyContent surveyContent) {
		this.header=header;
		this.surveyContent=surveyContent;

	}
	
	public void onLoad() {
		super.onLoad();
		this.setStyleName("navbar");
		
		surveyManagement = ClientsideSettings.getSurveyManagement();
		
		b = new Button();
		b.setStyleName("InvisibleButton");
		
		logoIcon = new Image("/Images/png/clapperboard.png");
		logoIcon.setStylePrimaryName("LogoIcon");
		logoText = new Label("Popcorns");
		logoText.setStylePrimaryName("LogoText");

		
		movies = new Label("Movies");	//Menu Item 1
		movies.setStyleName("navbar-element");
		movies.addClickHandler(new ShowMoviesClickHandler(header, surveyContent, this));
		
		groups = new Label("Groups"); //Menu Item 2
		groups.setStyleName("navbar-element");
		groups.addClickHandler(new ShowGroupsClickHandler(header, surveyContent, this));
		
		surveys = new Label("Surveys"); //Menu Item 3
		surveys.setStyleName("navbar-element");
		surveys.addClickHandler(new ShowSurveysClickHandler(header, surveyContent, this));
		
		switchToAdmin = new Label("Switch to Admin");
		switchToAdmin.setStyleName("navbar-element switchClient");
		switchToAdmin.addClickHandler(new SwitchToAdminClickHandler());
		
		settings = new Label("Settings");
		settings.setStyleName("navbar-element bottom");
		settings.addClickHandler(new SettingsClickHandler());
		
		logOut = new Label("Logout");
		logOut.setStyleName("navbar-element logout");
		logOut.addClickHandler(new LogOutClickHandler());
		

		this.add(b);
		this.add(logoIcon);
		this.add(logoText);
		this.add(movies); //Add Item 1 to Menu
		this.add(groups);	 //Add Item 2 to Menu
		this.add(surveys); //Add Item 3 to Menu
		this.add(switchToAdmin);
		this.add(settings);
		this.add(logOut);
	}
	
	class SwitchToAdminClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
//			Window.Location.assign(GWT.getHostPageBaseURL() + "SoPra_WS1920a.html");
			Window.Location.replace("/SoPra_WS1920a.html");
//			cinemaAdminEntry.showCinemaAdminEntry();
		}
		
	}
	
	class ShowMoviesClickHandler implements ClickHandler{
		SurveyNavigationBar surveyNavigationBar;
		public ShowMoviesClickHandler(SurveyManagementHeader header, SurveyContent content, SurveyNavigationBar surveyNavigationBar) {
			this.surveyNavigationBar=surveyNavigationBar;
		}

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub

			header.showMoviesHeader();
			surveyContent.showMovies();
			
			surveyNavigationBar.setStyleName("navbar");
			movies.setStyleName("navbar-element");
			groups.setStyleName("navbar-element");
			surveys.setStyleName("navbar-element");
		}
		
	}
	
	class ShowGroupsClickHandler implements ClickHandler{
		SurveyNavigationBar surveyNavigationBar;
		public ShowGroupsClickHandler(SurveyManagementHeader header, SurveyContent content, SurveyNavigationBar surveyNavigationBar) {
			this.surveyNavigationBar=surveyNavigationBar;
		}

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			header.showGroupHeader();
			surveyContent.showGroups();
			
			surveyNavigationBar.setStyleName("navbar");
			movies.setStyleName("navbar-element");
			groups.setStyleName("navbar-element");
			surveys.setStyleName("navbar-element");
		}
		
	}
	
	class ShowSurveysClickHandler implements ClickHandler{
		SurveyNavigationBar surveyNavigationBar;
		public ShowSurveysClickHandler(SurveyManagementHeader header, SurveyContent surveyContent, SurveyNavigationBar surveyNavigationBar) {
			this.surveyNavigationBar=surveyNavigationBar;
		}

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			header.showSurveyHeader();
			surveyContent.showSurveys();
			
			surveyNavigationBar.setStyleName("navbar");
			movies.setStyleName("navbar-element");
			groups.setStyleName("navbar-element");
			surveys.setStyleName("navbar-element");
		}
		
	}
	
	class SettingsClickHandler implements ClickHandler{

		public SettingsClickHandler() {
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			UserSettingsForm userSettingsForm = new UserSettingsForm(header, surveyContent);
			userSettingsForm.center();
			userSettingsForm.show();
		}
		
	}
	
	class LogOutClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			Window.alert("Successfully logged out!");
			
		}
		
	}

	public void setPerson(Person person) {
		this.p = person;
	}
	
	public Person getPerson() {
		return p;
	}
	

}
