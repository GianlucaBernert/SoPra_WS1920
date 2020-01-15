package de.hdm.SoPra_WS1920.client.gui;

import java.util.Vector;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlowPanel;

import de.hdm.SoPra_WS1920.client.ClientsideSettings;
import de.hdm.SoPra_WS1920.client.gui.Admin.Content;
import de.hdm.SoPra_WS1920.client.gui.Admin.MovieCard;
import de.hdm.SoPra_WS1920.shared.CinemaAdministrationAsync;
import de.hdm.SoPra_WS1920.shared.SurveyManagementAsync;
import de.hdm.SoPra_WS1920.shared.bo.Group;
import de.hdm.SoPra_WS1920.shared.bo.Movie;
import de.hdm.SoPra_WS1920.shared.bo.Survey;

public class SurveyContent extends FlowPanel {
	
	Group g;
	Movie m;
	Survey s;
	
	SurveyManagementAsync surveyManagementAdministration;
	CinemaAdministrationAsync cinemaAdministration;
	
	public SurveyContent() {
		
	}
	
	public void onLoad() {
		super.onLoad();
		this.setStylePrimaryName("content");
		surveyManagementAdministration = ClientsideSettings.getSurveyManagement();
	}
	
	public void ShowGroups() {
		this.clear();
		surveyManagementAdministration.getGroupByPersonFK(1, new GetGroupByPersonCallback(this));
	}
	
	class GetGroupByPersonCallback implements AsyncCallback<Vector<Group>>{
		SurveyContent content;
		
		public GetGroupByPersonCallback(SurveyContent content) {
			this.content = content;
		}

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			Window.alert("Problem with the Callback");
			
		}

		@Override
		public void onSuccess(Vector<Group> result) {
			// TODO Auto-generated method stub
			for(Group g : result) {
				GroupCard groupCard = new GroupCard(content, g);
				content.add(groupCard);
			}
			
		}
		
	}
	
	public void showMovies() {
		this.clear();
		cinemaAdministration.getAllMovies(new GetAllMoviesCallback(this));
		
		
	}
	
	class GetAllMoviesCallback implements AsyncCallback<Vector<Movie>>{
		
		SurveyContent content;
		
		public GetAllMoviesCallback(SurveyContent content) {
			this.content = content;
		}

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			Window.alert("Problem with the Callback");
			
		}

		@Override
		public void onSuccess(Vector<Movie> result) {
			// TODO Auto-generated method stub
				for(Movie m : result) {
				
			MovieBoard movieBoard = new MovieBoard(content, m);
			content.add(movieBoard);
			}
			
		}
		
	}
	
	public void showSurveys() {
		this.clear();
		surveyManagementAdministration.getSurveyByPersonFK(1, new GetSurveyByPersonCallback(this));
		
		
	}
	class GetSurveyByPersonCallback implements AsyncCallback<Vector<Survey>> {
		SurveyContent content;
		
	
		public GetSurveyByPersonCallback(SurveyContent content) {
			this.content = content;
		}
	

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			Window.alert("Problem with the Callback");
			
			
		}


		@Override
		public void onSuccess(Vector<Survey> result) {
			// TODO Auto-generated method stub
			for(Survey s : result) {
			SurveyCard surveyCard = new SurveyCard(content, s);
			surveyCard.showSurveyCardView(s);
			content.add(surveyCard);
				
			}
			
			
			
		}
		
	}
	
	/*Movie m;
	Content content;
	
	
	
	public SurveyContent() {
		
	}
	
	public void onLoad() {
		super.onLoad();
		this.setStylePrimaryName("content");
		
		m = new Movie();
		m.setName("Joker");
		m.setGenre("Action");
		m.setDescription("A movie about a clown");
		
		m.setId(1);
	
	}
	
	public void showMovies() {
		this.clear();
		MovieCard movieCard = new MovieCard(content, m);
		this.add(movieCard);*/
	}
	


