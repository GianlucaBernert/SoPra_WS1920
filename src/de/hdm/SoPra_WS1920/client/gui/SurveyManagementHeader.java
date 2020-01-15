package de.hdm.SoPra_WS1920.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

import de.hdm.SoPra_WS1920.client.ClientsideSettings;
import de.hdm.SoPra_WS1920.shared.CinemaAdministrationAsync;
import de.hdm.SoPra_WS1920.shared.SurveyManagementAsync;
import de.hdm.SoPra_WS1920.shared.bo.Group;
import de.hdm.SoPra_WS1920.shared.bo.Movie;
import de.hdm.SoPra_WS1920.shared.bo.Survey;




public class SurveyManagementHeader extends FlowPanel {
	
	Label headline;
	Button createBo;
	Button createSurvey;
	SearchBox searchBox;
	
	GroupForm gf;
	SurveyCardEdit se;
	
	GroupCard groupCard;
	Group groupToShow;
	
	SurveyContent content;
	SurveyManagementAsync surveyManagementAdministration;
	
	
	public SurveyManagementHeader(SurveyContent content) {
		this.content = content;
	}
	
	
	public void onLoad() {
		super.onLoad();
		this.setStylePrimaryName("header");
		surveyManagementAdministration = ClientsideSettings.getSurveyManagement();
		//createBo.addClickHandler(new CreateBoClickHandler(this, gf));
		//createSurvey.addClickHandler(new CreateSurveyClickHandler(this, sf));
		
	}
		class CreateGroupClickHandler implements ClickHandler{
			SurveyManagementHeader header;
			SurveyContent content;
			GroupForm gf;
			
			public CreateGroupClickHandler(SurveyManagementHeader header, SurveyContent content) {
				this.header = header;
				this.content = content;
				
						
			}
			
		

			@Override
			public void onClick(ClickEvent event) {
				GroupForm gf = new GroupForm(header, content);
				gf.show();
				gf.center();
				
			}
		
		}
		
		class CreateSurveyClickHandler implements ClickHandler{
			SurveyManagementHeader header;
//			SurveyContent content;
//			SurveyCardEdit se;
			
//			public CreateSurveyClickHandler(SurveyManagementHeader header, SurveyCardEdit se) {
			public CreateSurveyClickHandler(SurveyManagementHeader header) {
			this.header = header;
//			this.content = content;
//			this.se = se;
			}

			@Override
			public void onClick(ClickEvent event) {
				SurveyCardEdit se = new SurveyCardEdit(content, header);
				se.center();
				se.show();
				
				
				
			}
			
		}
			
		
		class SearchBox extends FlowPanel {
			TextBox searchText;
			Button submitSearch;
			Image searchIcon;

		
		public void onLoad() {
			super.onLoad();
			this.setStylePrimaryName("searchBox");
			
			searchText = new TextBox();
			searchText.setStylePrimaryName("searchText");
			searchText.getElement().setPropertyString("placeholder", "Search...");
			
			
			submitSearch = new Button();

			searchIcon = new Image("/Images/search.png");
			searchIcon.setStyleName("searchIcon");

			this.add(searchIcon);
			this.add(searchText);
		}
		
		
		
		public void searchWord(SearchBox searchBox, SurveyManagementHeader header){
			if(header.headline.getText().equals("Movies")){
				surveyManagementAdministration.searchMovie(searchBox.searchText.getText(), new SearchMovieCallback());
				}else if(header.headline.getText().equals("Groups")){
//					surveyManagementAdministration.searchGroup(searchBox.searchText.getText(), new SearchGroupCallback());
				}else if(header.headline.getText().equals("Survey")) {
//						surveyManagementAdministration.searchSurvey(searchBox.searchText.getText(), new SearchSurveyCallback());
					
				
					}
		}
		}
		

		class SearchKeyPressHandler implements KeyPressHandler {
			
			SearchBox searchBox;
			SurveyManagementHeader header;
			
			public SearchKeyPressHandler(SearchBox searchBox, SurveyManagementHeader header) {
				this.searchBox = searchBox;
				this.header = header;
			}

			@Override
			public void onKeyPress(KeyPressEvent event) {
				// TODO Auto-generated method stub
				boolean enterPressed = KeyCodes.KEY_ENTER == event.getNativeEvent().getKeyCode();
				if(enterPressed)
				
				{
					searchBox.searchWord(searchBox, header);
				
			}
		
		}
			
			
		}
		
		

		public void showGroupHeader() {
			this.clear();
			headline = new Label("Groups");
			headline.setStylePrimaryName("headline");
			
			searchBox = new SearchBox();
			
			createBo = new Button("+Add Group");
			createBo.setStylePrimaryName("CreateBoButton");
			createBo.addClickHandler(new CreateGroupClickHandler(this, content));
			
			this.add(headline);
			this.add(createBo);
			this.add(searchBox);
		}
		

		

		public void showSurveyHeader() {
			this.clear();
			headline = new Label("Survey");
			headline.setStylePrimaryName("headline");
			
			searchBox = new SearchBox();
			
			createBo = new Button("+Add Survey");
			createBo.setStylePrimaryName("CreateBoButton");
//			createBo.addClickHandler(new CreateSurveyClickHandler(this, se));
			createBo.addClickHandler(new CreateSurveyClickHandler(this));
			
			this.add(headline);
			this.add(createBo);
			this.add(searchBox);
		}
		
		
		public void showMoviesHeader(){
			this.clear();
			headline = new Label("Movies");
			headline.setStylePrimaryName("headline");
			
			searchBox = new SearchBox();
			
			this.add(headline);
			
		}
		
		class SearchMovieCallback implements AsyncCallback<Vector <Movie>>{

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				Window.alert("Problem with the Callback");
				
			}

			@Override
			public void onSuccess(Vector<Movie> result) {
				// TODO Auto-generated method stub
				if(result.size()==0) {
					Window.alert("No Search Results");
					content.clear();
					content.showMovies();
					searchBox.searchText.selectAll();
				}else {
					content.clear();
					for(Movie m : result) {
						MovieBoard movieBoard = new MovieBoard(content, m);
						content.add(movieBoard);
					}
				}
				
			}
		}
		
		class searchGroupCallback implements AsyncCallback<Vector<Group>>{

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				Window.alert("Problem with the callback");
				
			}

			@Override
			public void onSuccess(Vector<Group> result) {
				// TODO Auto-generated method stub
				if(result.size()==0) {
					Window.alert("No Search Results");
					content.clear();
					content.ShowGroups();
					searchBox.searchText.selectAll();
				}else {
					content.clear();
					for(Group g : result) {
						GroupCard groupCard = new GroupCard(content, g);
						content.add(groupCard);
						
					}
				
					}
				}
				
		}
			
		class searchSurveyCallback implements AsyncCallback<Vector<Survey>>{

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Problem with the Callback");
				
			}

			@Override
			public void onSuccess(Vector<Survey> result) {
				// TODO Auto-generated method stub
				if(result.size() == 0) {
					Window.alert("No Search Results");
					content.clear();
					content.showSurveys();
					searchBox.searchText.selectAll();
					
				}else {
					content.clear();
					for(Survey s : result) {
						SurveyCard surveyCard = new SurveyCard(content, s);
						content.add(surveyCard);
						
					}
				}
				
			}
			
		}
		
}
		
		
		

			
		
	


