package de.hdm.SoPra_WS1920.client.gui;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;

import de.hdm.SoPra_WS1920.shared.bo.Group;
import de.hdm.SoPra_WS1920.shared.bo.Movie;
import de.hdm.SoPra_WS1920.shared.bo.Screening;
import de.hdm.SoPra_WS1920.shared.bo.Survey;

public class SurveyCardResult extends FlowPanel {
	
	Label result;
	Label group;
	Label movie;
	Label status;
	Button newSurvey;
	
	Survey surveyToShow;
	Movie movieOfSurvey;
	Group groupOfSurvey;
	Screening screeningOfSurvey;
	
	
	SurveyCard parentCard;
	
	
	public SurveyCardResult(SurveyCard parentCard, Survey surveyToShow) {
		this.parentCard = parentCard;
		this.surveyToShow = surveyToShow;
	}
	
	public void onLoad() {
		super.onLoad();
		
		
		result = new Label("Results");
		result.setStyleName("CardViewParagraph");
		//surveyEntrysOfSurvey = SurveyManagementImpl.getSurveyEntryBySurveyFK(survey.getId)
		//screeningOfSurvey = CinemaAdminImpl.getScreeningById(surveyentry.getScreeningFK)
		//movieOfSurvey = cinemaAdminImpl.getMovieById(screening.getMovieFK)
		movie = new Label("Joker");
		movie.setStyleName("CardViewTitle");
		
		//groupofSurvey = SurveyManagementImpl.getGroupbyId(survey.getGroupFK)
		group = new Label("Friends");
		group.setStyleName("CardViewSubtitle");
		
		status = new Label("Status: Active");
		status.setStyleName("CardViewParagraph");
		
		newSurvey = new Button("Results");
		newSurvey.setStyleName("InvisibleButton");
		//newSurvey.addClickHandler(new NewSurveyClickHandler());
	
		this.add(movie);
		this.add(group);
		this.add(status);
		this.add(newSurvey);
		this.add(result);
	
	}
////	class NewSurveyClickHandler implements ClickHandler{
////
////		@Override
////		public void onClick(ClickEvent event) {
////			 TODO Auto-generated method stub
////			parentCard.showSurveyCardCreate(surveyToShow);
////		}
//	}
	
	

}
