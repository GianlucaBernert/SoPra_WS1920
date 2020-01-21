package de.hdm.SoPra_WS1920.client.gui;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import de.hdm.SoPra_WS1920.client.ClientsideSettings;

import de.hdm.SoPra_WS1920.shared.SurveyManagementAsync;
import de.hdm.SoPra_WS1920.shared.bo.Cinema;
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
	Label surveyStatus;
	Label participations;
	Label groupMemberAmount;
	Label voteStatus;
	Button voteButton;
	Button edit;
	Button resultButton;
	Image editIcon;
	Survey surveyToShow;
	Movie movieOfSurvey;
	Group groupOfSurvey;
	Screening screeningOfSurvey;
	SurveyEntry surveyEntryOfSurvey;
	
	
	SurveyManagementAsync surveyManagement;
	SurveyCard parentCard;
	
	
	
	public SurveyCardView(SurveyCard parentCard, Survey surveyToShow) {
		this.parentCard = parentCard;
		this.surveyToShow = surveyToShow;
	}
	
	public void onLoad() {
		super.onLoad();		
		surveyManagement = ClientsideSettings.getSurveyManagement();
		surveyManagement.getMovieBySurveyFK(surveyToShow.getId(), new GetMovieCallback());
		surveyManagement.getGroupById(surveyToShow.getGroupFK(), new GetGroupCallback());
		surveyManagement.getVotedPersonsOfSurvey(surveyToShow.getId(), new GetParticipationsCallback());
		surveyManagement.getGroupMembersOfGroup(surveyToShow.getGroupFK(), new GetGroupMemberAmountCallback());
		
		movie = new Label();
		movie.setStyleName("CardViewTitle");
		
		group = new Label();
		group.setStyleName("CardViewSubTitle");
		
		surveyStatus = new Label();
		surveyStatus.setStyleName("CardViewParagraph");
		
		voteStatus = new Label();
		voteStatus.setStyleName("CardViewParagraph");
		
		// Anzahl der Votes holen + groupmembers = SurveyManagementImpl.countGroupMembers(groupOfSurvey.getId)
		participations = new Label();
		participations.setStyleName("CardViewParagraph");
		
		groupMemberAmount = new Label();
		groupMemberAmount.setStyleName("CardViewParagraph");
		
		edit = new Button();
		edit.setStyleName("InvisibleButton");
		editIcon = new Image("/Images/png/006-pen.png");
		editIcon.setStyleName("EditIcon");
//		editIcon.addClickHandler(new EditClickHandler(this));
		
		voteButton = new Button("Vote");
		voteButton.setStyleName("CardViewParagraph");
		voteButton.addClickHandler(new VoteClickHandler(this));
		
		resultButton = new Button("Results");
		resultButton.setStyleName("CardViewParagraph");
//		resultButton.addClickHandler(new ResultsClickHandler());

		this.add(movie);
		this.add(group);
		this.add(surveyStatus);
		this.add(voteStatus);
		this.add(participations);
		this.add(groupMemberAmount);
		
		if(surveyToShow.getStatus()==1) {
			this.showEditVoteView();
		}else {
			this.showResultsView();
		}
	
	}
	
	public void showEditVoteView() {
		surveyStatus.setText("Survey Status: Active");
		surveyStatus.setStyleName("ActiveSurveyLabel");
		if(surveyToShow.getPersonFK()==1) {
			editIcon.addClickHandler(new EditClickHandler());
			this.add(edit);
			this.add(editIcon);
		}
		
		this.add(voteButton);
	}
	
	public void showResultsView() {
		surveyStatus.setText("Survey Status: Closed");
		surveyStatus.setStyleName("ClosedSurveyLabel");
		this.add(resultButton);
	}
	
	class VoteClickHandler implements ClickHandler{
		SurveyCardView surveyCardView;
		public VoteClickHandler(SurveyCardView surveyCardView) {
			// TODO Auto-generated constructor stub
			this.surveyCardView = surveyCardView;
		}

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			SurveyCardEdit surveyCardEdit = new SurveyCardEdit(parentCard,surveyToShow);

		}
		
	}
	
	class EditClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			SurveyCardEdit surveyCardEdit = new SurveyCardEdit(parentCard,surveyToShow);
			surveyCardEdit.showSurveyCardEdit();
			surveyCardEdit.center();
			surveyCardEdit.show();
		}
	}

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
	
	class GetParticipationsCallback implements AsyncCallback<Vector<Person>>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Vector<Person> result) {
			// TODO Auto-generated method stub
			participations.setText(Integer.toString(result.size())+ "/");
		}

		
		
	}
	
	class GetGroupMemberAmountCallback implements AsyncCallback<Vector<Membership>>{

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(Vector<Membership> result) {
			// TODO Auto-generated method stub
			groupMemberAmount.setText(Integer.toString(result.size()));
		}
		
	}

}
