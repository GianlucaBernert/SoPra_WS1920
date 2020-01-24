package de.hdm.SoPra_WS1920.client.gui;

import com.google.gwt.user.client.ui.FlowPanel;

import de.hdm.SoPra_WS1920.shared.bo.Movie;
import de.hdm.SoPra_WS1920.shared.bo.Survey;

public class SurveyCard extends FlowPanel {
	
	Survey surveyToShow;
	SurveyContent surveyContent;
	SurveyCardView surveyCardView;
	Movie movieOfSurvey;
	
	public void onLoad() {
		super.onLoad();
		this.setStylePrimaryName("SurveyCard");
		this.showSurveyCardView(surveyToShow);
//		surveyCardView = new SurveyCardView(this, surveyToShow);
	}
	
	public SurveyCard(Survey surveyToShow) {
		this.surveyToShow = surveyToShow;
	}
	
	public SurveyCard(SurveyContent surveyContent, Survey surveyToShow) {
		this.surveyContent = surveyContent;
		this.surveyToShow = surveyToShow;
	}
	
	public void showSurveyCardView(Survey surveyToShow) {
		this.surveyToShow = surveyToShow;
		this.clear();
		this.add(new SurveyCardView(this, surveyToShow));
	}
	
	public void remove() {
		surveyContent.remove(this);
	}
	
	public Movie getMovie() {
		return movieOfSurvey;
	}

	public void setMovie(Movie movie) {
		this.movieOfSurvey = movie;
	}

}
