package de.hdm.SoPra_WS1920.client.gui;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;

import de.hdm.SoPra_WS1920.shared.SurveyManagementAsync;
import de.hdm.SoPra_WS1920.shared.bo.Group;
import de.hdm.SoPra_WS1920.shared.bo.Membership;
import de.hdm.SoPra_WS1920.shared.bo.Movie;
import de.hdm.SoPra_WS1920.shared.bo.Person;
import de.hdm.SoPra_WS1920.shared.bo.Screening;
import de.hdm.SoPra_WS1920.shared.bo.Survey;
import de.hdm.SoPra_WS1920.shared.bo.SurveyEntry;

public class SurveyCardView extends FlowPanel {
	
	Label group;
	Label movie;
	Label status;
	Label participations1;
	Label participations2;
	Label voted;
	Button vote;
	Button edit;
	Button close;
	
	Survey surveyToShow;
	Movie movieOfSurvey;
	Group groupOfSurvey;
	Screening screeningOfSurvey;
	SurveyEntry surveyEntryOfSurvey;
	int amountOfVoters;
	int amountOfGroupMembers;
	
	
	SurveyManagementAsync surveymanagement;
	
	SurveyCard parentCard;
	
	Image editIcon;
	
	public SurveyCardView(SurveyCard parentCard, Survey surveyToShow) {
		this.parentCard = parentCard;
		this.surveyToShow = surveyToShow;
	}
	
	public void onLoad() {
		super.onLoad();
		surveymanagement.getMovieBySurveyFK(surveyToShow.getId(), new GetMovieCallback());
		surveymanagement.getGroupById(surveyToShow.getGroupFK(), new GetGroupCallback());
		surveymanagement.getGroupMembersOfGroup(surveyToShow.getId(), new GetParticipations2Callback());
		surveymanagement.getVotedPersonsOfSurvey(surveyToShow.getId(), new GetParticipations1Callback());
		
		//surveyEntrysOfSurvey = SurveyManagementImpl.getSurveyEntryBySurveyFK(surveyToShow.getId());
		//screeningOfSurvey = CinemaAdminImpl.getScreeningById(surveyEntryOfSurvey.getScreeningFK)
		//movieOfSurvey = cinemaAdminImpl.getMovieById(screeningOfSurvey.getMovieFK)
		movie = new Label();
		movie.setStyleName("CardViewTitle");
		//groupofSurvey = SurveyManagementImpl.getGroupbyId(survey.getGroupFK)
		group = new Label();
		group.setStyleName("CardViewSubtitle");
		status = new Label("Status: Active");
		status.setStyleName("CardViewParagraph");
		voted = new Label("Voted:");
		voted.setStyleName("CardViewParagraph");
		
		// Anzahl der Voters holen + groupmembers = SurveyManagementImpl.countGroupMembers(groupOfSurvey.getId)
		participations1 = new Label();
		participations1.setStyleName("CardViewParagraph");
		
		participations2 = new Label();
		participations2.setStyleName("CardViewParagraph");
		
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
		this.add(participations1);
		this.add(participations2);
		this.add(edit);
		this.add(editIcon);
		this.add(vote);
	
	}
	class EditClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			//parentCard.showSurveyCardEdit(surveyToShow);
		}
	}
//	class VoteClickHandler implements ClickHandler{
//
//		@Override
//		public void onClick(ClickEvent event) {
//			// TODO Auto-generated method stub
//			parentCard.showSurveyCardVote(surveyToShow);
//		}
//	}
	
	class GetGroupCallback implements AsyncCallback<Group>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Group result) {
			// TODO Auto-generated method stub
//			Window.alert(result.getName());
			groupOfSurvey = result;
			group.setText(result.getName());
		}
		
	}
	
	class GetMovieCallback implements AsyncCallback<Movie>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Movie result) {
			// TODO Auto-generated method stub
//			Window.alert(result.getName());
			movieOfSurvey = result;
			movie.setText(result.getName());
		}
		
	}
	
	class GetParticipations2Callback implements AsyncCallback<Vector<Membership>>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Vector<Membership> result) {
			// TODO Auto-generated method stub
//			Window.alert(result.getName());
			amountOfGroupMembers = result.size();
			String p2 = amountOfGroupMembers + " participations";
			participations1.setText(p2);
		}
		
	}
	
	class GetParticipations1Callback implements AsyncCallback<Vector<Person>>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Vector<Person> result) {
			// TODO Auto-generated method stub
//			Window.alert(result.getName());
			amountOfVoters = result.size();
			String p1 = amountOfVoters + " /";
			participations1.setText(p1);
		}
		
	}
}
