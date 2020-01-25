package de.hdm.SoPra_WS1920.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.SoPra_WS1920.client.gui.SurveyNavigationBar;
import de.hdm.SoPra_WS1920.client.gui.SurveyContent;
import de.hdm.SoPra_WS1920.client.gui.SurveyManagementHeader;

public class SurveyManagementEntry implements EntryPoint {
	
	SurveyNavigationBar navBar;
	SurveyManagementHeader header;
	SurveyContent content;
	
	//Image menuIcon;
	//Label movies;
	//Label groups;
	//Label surveys;
	
	//Group g;
	
	//private int menuOpen = 0;
	
	
	@Override
	public void onModuleLoad() {
		
		//Group g = new Group();
		//g.setName("Yesin");
		
		
		content = new SurveyContent();
		header = new SurveyManagementHeader(content);
//		navBar = new SurveyNavigationBar(this,header, content);
		navBar = new SurveyNavigationBar(header, content);
		
		RootPanel.get().add(navBar);
		RootPanel.get().add(header);
		RootPanel.get().add(content);
		
	}
		
		/*navbar.setStylePrimaryName("navbar");
		
		menuIcon = new Image("/Images/menu.png");
		menuIcon.setStyleName("menuIcon");
		//menuIcon.addClickHandler(new MenuClickHandler());
		
		movies = new Label("Movies"); //Menu Item 1
		movies.setStyleName("navbar-element");
		movies.addClickHandler(new ShowMoviesClickHandler(header, content));
		
		groups = new Label("Groups"); //Menu Item 2
		groups.setStyleName("navbar-element");
		groups.addClickHandler(new ShowGroupsClickHandler(header, content));
		
	
		surveys = new Label("Surveys"); //Menu Item 2
		surveys.setStyleName("navbar-element");
		surveys.addClickHandler(new ShowSurveysClickHandler(header, content));
		
		navbar.add(menuIcon);
		navbar.add(movies);
		navbar.add(groups);
		navbar.add(surveys);
		
		RootPanel.get().add(navbar);
		RootPanel.get().add(header);
		RootPanel.get().add(content);
		
	}
	
	class MenuClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			if(menuOpen==0) {
				navbar.setStyleName("navbar-show");
				menuIcon.setStyleName("menuIcon-show");
				movies.setStyleName("navbar-element-show");
				groups.setStyleName("navbar-element-show");
				surveys.setStyleName("navbar-element-show");
				menuOpen=1;
			}else {
				navbar.setStyleName("navbar");
				menuIcon.setStyleName("menuIcon");
				movies.setStyleName("navbar-element");
				groups.setStyleName("navbar-element");
				surveys.setStyleName("navbar-element");
				menuOpen=0;
			}
		}
		
	}
	
	class ShowGroupsClickHandler implements ClickHandler{
		
		public ShowGroupsClickHandler(SurveyManagementHeader header, SurveyContent content) {
			
		}
		

		@Override
		public void onClick(ClickEvent event) {
			header.showGroupHeader();
		
		}
		
	}
	
	class ShowSurveysClickHandler implements ClickHandler{
		
		public ShowSurveysClickHandler(SurveyManagementHeader header, SurveyContent content) {
			
		}

		@Override
		public void onClick(ClickEvent event) {
			header.showSurveyHeader();
			
		}
		
	}
		
	class ShowMoviesClickHandler implements ClickHandler{
		
		public ShowMoviesClickHandler(SurveyManagementHeader header, SurveyContent content) {
			
		}

		@Override
		public void onClick(ClickEvent event) {
			header.showMoviesHeader();
			
		}*/
	
		
	}		
		

		
		
	
	

	


