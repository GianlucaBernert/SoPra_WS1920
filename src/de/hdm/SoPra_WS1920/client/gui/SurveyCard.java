package de.hdm.SoPra_WS1920.client.gui;

import com.google.gwt.user.client.ui.FlowPanel;

import de.hdm.SoPra_WS1920.shared.bo.Survey;

public class SurveyCard extends FlowPanel {
	
	Survey surveyToShow;
	FlowPanel main;
	SurveyCardView surveyCardView;
	
	public void onLoad() {
		super.onLoad();
		this.setStylePrimaryName("moviecard");
		this.showSurveyCardView(surveyToShow);
		surveyCardView = new SurveyCardView(this, surveyToShow);
		this.showSurveyCardView(surveyToShow);
	}
	
	public SurveyCard(Survey surveyToShow) {
		this.surveyToShow = surveyToShow;
	}
	
	public SurveyCard(FlowPanel main, Survey surveyToShow) {
		this.main = main;
		this.surveyToShow = surveyToShow;
	}
	
	public void showSurveyCardView(Survey surveyToShow) {
		this.surveyToShow = surveyToShow;
		this.clear();
		this.add(new SurveyCardView(this, surveyToShow));
	}
	
	public void remove() {
		main.remove(this);
	}

}
