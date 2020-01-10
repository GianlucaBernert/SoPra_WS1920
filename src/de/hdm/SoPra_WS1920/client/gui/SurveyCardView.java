package de.hdm.SoPra_WS1920.client.gui;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;

import de.hdm.SoPra_WS1920.server.SurveyManagementImpl;
import de.hdm.SoPra_WS1920.shared.bo.Group;
import de.hdm.SoPra_WS1920.shared.bo.Movie;
import de.hdm.SoPra_WS1920.shared.bo.Screening;
import de.hdm.SoPra_WS1920.shared.bo.Survey;
import de.hdm.SoPra_WS1920.shared.bo.SurveyEntry;

public class SurveyCardView extends FlowPanel {
	
	Label group;
	Label movie;
	Label status;
	Label participations;
	Label voted;
	Button vote;
	Button edit;
	
	Survey surveyToShow;
	Movie movieOfSurvey;
	Group groupOfSurvey;
	Screening screeningOfSurvey;
	SurveyEntry surveyEntryOfSurvey;
	
	
	SurveyCard parentCard;
	
	Image editIcon;
	
	public SurveyCardView(SurveyCard parentCard, Survey surveyToShow) {
		this.parentCard = parentCard;
		this.surveyToShow = surveyToShow;
	}
	
	public void onLoad() {
		super.onLoad();
		
		
		//Vector surveyEntrysOfSurvey = SurveyManagementImpl.getSurveyEntryBySurveyFK(surveyToShow.getId());
		
		//surveyEntrysOfSurvey = SurveyManagementImpl.getSurveyEntryBySurveyFK(surveyToShow.getId)
		//screeningOfSurvey = CinemaAdminImpl.getScreeningById(surveyentry.getScreeningFK)
		//movieOfSurvey = cinemaAdminImpl.getMovieById(screening.getMovieFK)
		movie = new Label("Joker");
		movie.setStyleName("CardViewTitle");
		
		//groupofSurvey = SurveyManagementImpl.getGroupbyId(survey.getGroupFK)
		group = new Label("Friends");
		group.setStyleName("CardViewSubtitle");
		
		status = new Label("Status: Active");
		status.setStyleName("CardViewParagraph");
		
		voted = new Label("Voted:");
		voted.setStyleName("CardViewParagraph");
		
		
		
		
		// Anzahl der Votes holen + groupmembers = SurveyManagementImpl.countGroupMembers(groupOfSurvey.getId)
		participations = new Label("4/6 participations");
		participations.setStyleName("CardViewParagraph");
		
		edit = new Button("");
		edit.setStyleName("InvisibleButton");
		editIcon = new Image("/Images/png/006-pen.png");
		editIcon.setStyleName("EditIcon");
		//editIcon.addClickHandler(new EditClickHandler());
		
		vote = new Button("vote");
		vote.setStyleName("InvisibleButton");
		//vote.addClickHandler(new VoteClickHandler());
	
		this.add(movie);
		this.add(group);
		this.add(status);
		this.add(voted);
		this.add(participations);
		this.add(edit);
		this.add(editIcon);
		this.add(vote);
	
	}
//	class EditClickHandler implements ClickHandler{
//
//		@Override
//		public void onClick(ClickEvent event) {
//			// TODO Auto-generated method stub
//			parentCard.showSurveyCardEdit(surveyToShow);
//		}
//	}
//	class VoteClickHandler implements ClickHandler{
//
//		@Override
//		public void onClick(ClickEvent event) {
//			// TODO Auto-generated method stub
//			parentCard.showSurveyCardVote(surveyToShow);
//		}
//	}
	

}
