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
import de.hdm.SoPra_WS1920.client.gui.Admin.Header;
import de.hdm.SoPra_WS1920.shared.CinemaAdministrationAsync;
import de.hdm.SoPra_WS1920.shared.SurveyManagementAsync;
import de.hdm.SoPra_WS1920.shared.bo.Group;
import de.hdm.SoPra_WS1920.shared.bo.Movie;
import de.hdm.SoPra_WS1920.shared.bo.Survey;




public class SurveyManagementHeader extends FlowPanel {
	
	Label headline;
	Button createBo;
	SearchBox searchBox;
	
	SurveyContent content;
	SurveyManagementAsync surveyManagementAdministration;
	CinemaAdministrationAsync cinemaAdministration;
	
	
	public SurveyManagementHeader(SurveyContent content) {
		this.content = content;
	}
	
	
	public void onLoad() {
		super.onLoad();
		this.setStylePrimaryName("Header");
		surveyManagementAdministration = ClientsideSettings.getSurveyManagement();
		cinemaAdministration = ClientsideSettings.getCinemaAdministration();
	}
	
	class CreateGroupClickHandler implements ClickHandler{
		SurveyManagementHeader header;
		SurveyContent content;
		
		public CreateGroupClickHandler(SurveyManagementHeader header, SurveyContent content) {
			this.header = header;
			this.content = content;
	
		}
		
		@Override
		public void onClick(ClickEvent event) {
			GroupForm gf = new GroupForm(header, content);
			gf.center();
			gf.show();
			
		}
	
	}
		
	class CreateSurveyClickHandler implements ClickHandler{
		SurveyManagementHeader header;
		SurveyContent content;
		
		public CreateSurveyClickHandler(SurveyManagementHeader header, SurveyContent content) {
		this.header = header;
		this.content = content;

		}

		@Override
		public void onClick(ClickEvent event) {
			SurveyCardEdit se = new SurveyCardEdit(content, header);
			
			se.center();
			se.show();
			se.showCreateCard();
			
		}
		
	}
		
	
	class SearchBox extends FlowPanel{
		SurveyManagementHeader header;
		TextBox searchText;
		Button submitSearch;
		Image searchIcon;
		Image searchSubmitIcon;
		
		
		public SearchBox(SurveyManagementHeader header) {
			// TODO Auto-generated constructor stub
			this.header = header;
		}

		public void onLoad(){
			super.onLoad();
			this.setStyleName("SearchBox");
			
			searchText = new TextBox();
			searchText.setStyleName("SearchText");
			searchText.getElement().setPropertyString("placeholder", "Search...");
			searchText.addKeyPressHandler(new SearchKeyPressHandler(this,header));
			
			submitSearch = new Button();

			searchSubmitIcon = new Image("/Images/png/009-arrow-pointing-to-right-1.png");
			searchSubmitIcon.setStyleName("SearchSubmitIcon");
			
			searchIcon = new Image("/Images/png/003-search.png");
			searchIcon.setStyleName("SearchIcon");

			this.add(searchIcon);
			this.add(searchText);
			this.add(searchSubmitIcon);
			
			searchIcon.addClickHandler(new SearchClickHandler(this,header));
			searchSubmitIcon.addClickHandler(new SearchClickHandler(this,header));
			 	
			
		}	 	 
			
		public void searchWord(SearchBox searchBox, SurveyManagementHeader header) {
			if(header.headline.getText().equals("Movies")){
				cinemaAdministration.searchMovie(searchBox.searchText.getText(), new SearchMovieCallback());
			}
//				}else if(header.headline.getText().equals("Groups")){
//					cinemaAdministration.searchCinema(1,searchBox.searchText.getText(), new SearchCinemaCallback());
//				}else if(header.headline.getText().equals("Surveys")){
//			  		cinemaAdministration.searchCinemaChain(1,searchBox.searchText.getText(), new SearchCinemaChainCallback());
//			  	}
		}
			
	}
		
		class SearchClickHandler implements ClickHandler{
	 		SurveyManagementHeader header;
	 		SearchBox searchBox;
			public SearchClickHandler(SearchBox searchBox, SurveyManagementHeader header) {
				// TODO Auto-generated constructor stub
				this.searchBox = searchBox;
				this.header = header;
				
			}

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub

				searchBox.searchWord(searchBox, header);
			}
		}
		

		class SearchKeyPressHandler implements KeyPressHandler{
			
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
				if(enterPressed){
					searchBox.searchWord(searchBox, header);
				}
			}	
		}
		
		

		public void showGroupHeader() {
			this.clear();
			headline = new Label("Groups");
			headline.setStylePrimaryName("Headline");
			
			searchBox = new SearchBox(this);
			
			createBo = new Button("+Add Group");
			createBo.setStylePrimaryName("CreateBoButton");
			createBo.addClickHandler(new CreateGroupClickHandler(this, content));
			
			this.add(headline);
			this.add(createBo);
			this.add(searchBox);
		}
		

		

		public void showSurveyHeader() {
			this.clear();
			headline = new Label("Surveys");
			headline.setStylePrimaryName("Headline");
			
			searchBox = new SearchBox(this);
			
			createBo = new Button("+Add Survey");
			createBo.setStylePrimaryName("CreateBoButton");
			createBo.addClickHandler(new CreateSurveyClickHandler(this, content));
			
			this.add(headline);
			this.add(createBo);
			this.add(searchBox);
		}
		
		
		public void showMoviesHeader(){
			this.clear();
			headline = new Label("Movies");
			headline.setStylePrimaryName("Headline");
			
			searchBox = new SearchBox(this);
			
			this.add(headline);
			this.add(searchBox);
			
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
		
//		class searchGroupCallback implements AsyncCallback<Vector<Group>>{
//
//			@Override
//			public void onFailure(Throwable caught) {
//				// TODO Auto-generated method stub
//				Window.alert("Problem with the callback");
//				
//			}
//
//			@Override
//			public void onSuccess(Vector<Group> result) {
//				// TODO Auto-generated method stub
//				if(result.size()==0) {
//					Window.alert("No Search Results");
//					content.clear();
//					content.ShowGroups();
//					searchBox.searchText.selectAll();
//				}else {
//					content.clear();
//					for(Group g : result) {
//						GroupCard groupCard = new GroupCard(content, g);
//						content.add(groupCard);
//						
//					}
//				
//					}
//				}
//				
//		}
			
//		class searchSurveyCallback implements AsyncCallback<Vector<Survey>>{
//
//			@Override
//			public void onFailure(Throwable caught) {
//				Window.alert("Problem with the Callback");
//				
//			}
//
//			@Override
//			public void onSuccess(Vector<Survey> result) {
//				// TODO Auto-generated method stub
//				if(result.size() == 0) {
//					Window.alert("No Search Results");
//					content.clear();
//					content.showSurveys();
//					searchBox.searchText.selectAll();
//					
//				}else {
//					content.clear();
//					for(Survey s : result) {
//						SurveyCard surveyCard = new SurveyCard(content, s);
//						content.add(surveyCard);
//						
//					}
//				}
//				
//			}
//			
//		}
		
}
		
		
		

			
		
	


