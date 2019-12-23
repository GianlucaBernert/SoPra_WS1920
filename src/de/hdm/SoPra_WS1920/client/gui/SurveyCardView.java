package de.hdm.SoPra_WS1920.client.gui;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

import de.hdm.SoPra_WS1920.shared.bo.Survey;

public class SurveyCardView extends FlowPanel {
	
	Label group;
	Label movies;
	Label startDate;
	Label endDate;
	
	Survey surveyToShow;
	SurveyCard parentCard;
	
	Image editIcon;
	
	public SurveyCardView(SurveyCard parentCard, Survey surveyToShow) {
		this.parentCard = parentCard;
		this.surveyToShow = surveyToShow;
	}
	
	public void onLoad() {
		super.onLoad();
		
		
		
	
	
	
	}
	
	

}
