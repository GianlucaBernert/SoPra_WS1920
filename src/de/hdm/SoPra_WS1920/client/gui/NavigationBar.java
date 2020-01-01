package de.hdm.SoPra_WS1920.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;




public class NavigationBar extends FlowPanel {
	
	SurveyManagementHeader header;
	SurveyContent content;
	Image menuIcon;
//	Image createIcon;
//	Label headerLabel;
	Label movies;
	Label groups;
	Label surveys;
	Label settings;
	Label switchToEditor;
	Label logOut;
	Button b;
//	private int menuOpen = 0;
	
	public NavigationBar(SurveyManagementHeader header,SurveyContent content) {
		this.header=header;
		this.content=content;
	}
	
	public void onLoad() {
		super.onLoad();
		this.setStyleName("navbar");
		b = new Button();
		b.setStyleName("InvisibleButton");

		
		movies = new Label("Movies");	//Menu Item 1
		movies.setStyleName("navbar-element");
		movies.addClickHandler(new ShowMoviesClickHandler(header, content, this));
		
		groups = new Label("Groups"); //Menu Item 2
		groups.setStyleName("navbar-element");
		groups.addClickHandler(new ShowGroupsClickHandler(header, content, this));
		
		surveys = new Label("Surveys"); //Menu Item 3
		surveys.setStyleName("navbar-element");
		surveys.addClickHandler(new ShowSurveysClickHandler(header, content, this));
		
		settings = new Label("Settings");
		settings.setStyleName("navbar-element bottom");
		settings.addClickHandler(new SettingsClickHandler());
		
		logOut = new Label("Logout");
		logOut.setStyleName("navbar-element logout");
		logOut.addClickHandler(new LogOutClickHandler());
		
//		createIcon = new Image("/Images/png/001-add-button.png");
//		createIcon.setStyleName("createIcon");
//		createIcon.addClickHandler(new CreateBoClickHandler(header, content));

		this.add(b);
//		this.add(menuIcon);
//		this.add(headerLabel);
//		this.add(createIcon);
		this.add(movies); //Add Item 1 to Menu
		this.add(groups);	 //Add Item 2 to Menu
		this.add(surveys); //Add Item 3 to Menu
		this.add(settings);
		this.add(logOut);
	}

	
	class ShowSurveysClickHandler implements ClickHandler{
		NavigationBar navigationBar;
		public ShowSurveysClickHandler(SurveyManagementHeader header, SurveyContent content, NavigationBar navigationBar) {
			this.navigationBar=navigationBar;
		}

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
//			headerLabel.setText("Movies");
			header.showMoviesHeader();
//			content.showCinemas();
//			navigationBar.add(createIcon);
			navigationBar.setStyleName("navbar");
//			menuIcon.setStyleName("menuIcon");
			movies.setStyleName("navbar-element");
			groups.setStyleName("navbar-element");
			surveys.setStyleName("navbar-element");
//			menuOpen=0;
		}
		
	}
	
	class ShowMoviesClickHandler implements ClickHandler{
		NavigationBar navigationBar;
		public ShowMoviesClickHandler(SurveyManagementHeader header, SurveyContent content, NavigationBar navigationBar) {
			this.navigationBar=navigationBar;
		}

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
//			headerLabel.setText("Movies");
			header.showMoviesHeader();
//			content.showMovies();
//			navigationBar.add(createIcon);
			navigationBar.setStyleName("navbar");
//			menuIcon.setStyleName("menuIcon");
			movies.setStyleName("navbar-element");
			groups.setStyleName("navbar-element");
			surveys.setStyleName("navbar-element");
//			menuOpen=0;
		}
		
	}
	class ShowGroupsClickHandler implements ClickHandler{
		NavigationBar navigationBar;
		public ShowGroupsClickHandler(SurveyManagementHeader header, SurveyContent content, NavigationBar navigationBar) {
			this.navigationBar=navigationBar;
		}

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
//			headerLabel.setText("Screenings");
			header.showGroupHeader();
//			content.showSurveys();
//			navigationBar.add(createIcon);
			navigationBar.setStyleName("navbar");
//			menuIcon.setStyleName("menuIcon");
			movies.setStyleName("navbar-element");
			groups.setStyleName("navbar-element");
			surveys.setStyleName("navbar-element");
//			menuOpen=0;
		}
		
	}
	class SettingsClickHandler implements ClickHandler{

		public SettingsClickHandler() {
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			//UserSettingsForm userSettingsForm = new UserSettingsForm(header, content);
			//userSettingsForm.center();
			//userSettingsForm.show();
		}
		
	}
	
	class LogOutClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			Window.alert("Successfully logged out!");
			
		}
		
	}



}
