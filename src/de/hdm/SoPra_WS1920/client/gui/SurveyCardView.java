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
	Label voteLabel;
	Button edit;
	Label resultLabel;
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
		
		surveyManagement.getGroupById(surveyToShow.getGroupFK(), new GetGroupCallback());
		surveyManagement.getVotedPersonsOfSurvey(surveyToShow.getId(), new GetParticipationsCallback());
		surveyManagement.getGroupMembersOfGroup(surveyToShow.getGroupFK(), new GetGroupMemberAmountCallback());
		
		movie = new Label();
		movie.setStyleName("CardViewTitle");
//		if(movieOfSurvey!=null) {
//			movie.setText(movieOfSurvey.getName());
//		}else {
//			surveyManagement.getMovieBySurveyFK(surveyToShow.getId(), new GetMovieCallback());
//		}
		movie.setText(surveyToShow.getMovieName());
		
		group = new Label();
		group.setStyleName("CardViewSubTitle");
		
		surveyStatus = new Label();
		surveyStatus.setStyleName("VoteLabel");
		
		voteStatus = new Label();
		voteStatus.setStyleName("CardViewParagraph");
		
		// Anzahl der Votes holen + groupmembers = SurveyManagementImpl.countGroupMembers(groupOfSurvey.getId)
		participations = new Label();
		participations.setStyleName("Participations");
		
		groupMemberAmount = new Label();
		groupMemberAmount.setStyleName("Participations");
		
		edit = new Button();
		edit.setStyleName("InvisibleButton");
		editIcon = new Image("/Images/png/006-pen.png");
		editIcon.setStyleName("EditIcon");
		
		voteLabel = new Label("Vote");
		voteLabel.setStyleName("VoteLabel");
		voteLabel.addClickHandler(new VoteClickHandler(this));
		
		resultLabel = new Label("Results");
		resultLabel.setStyleName("ResultsButton");
		resultLabel.addClickHandler(new ResultsClickHandler());

		this.add(movie);
		this.add(group);
		this.add(surveyStatus);
//		this.add(voteStatus);
		this.add(participations);
		this.add(groupMemberAmount);
		
		if(surveyToShow.getStatus()==1) {
			this.showEditVoteView();
		}else {
			this.showResultsView();
		}
		if(surveyToShow.getPersonFK()==1) {
			editIcon.addClickHandler(new EditClickHandler());
			this.add(edit);
			this.add(editIcon);
		}
	
	}
	
	public void showEditVoteView() {
		surveyStatus.setText("Active");
		surveyStatus.setStyleName("ActiveSurveyLabel");
		this.add(voteLabel);
	}
	
	public void showResultsView() {
		surveyStatus.setText("Closed");
		surveyStatus.setStyleName("ClosedSurveyLabel");
		this.add(resultLabel);
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
//			SurveyCardEdit surveyCardEdit = new SurveyCardEdit(parentCard,surveyToShow);
			VotingCard votingCard = new VotingCard(parentCard, surveyToShow);
			votingCard.center();
			votingCard.show();
		}
		
	}
	
	class EditClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			SurveyCardEdit surveyCardEdit = new SurveyCardEdit(parentCard,surveyToShow);
//			Window.alert("In editClickHandler: "+parentCard.toString()+ " "+surveyToShow.toString());
			surveyCardEdit.center();
			surveyCardEdit.show();
			surveyCardEdit.showSurveyCardEdit();
			
		}
	}
	
	class ResultsClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			VotingCard votingCard = new VotingCard(parentCard,surveyToShow);
			//votingCard.showResultsOnly ---> Remove ClickHandler from SurveyEntryRows, Remove SaveButton
			votingCard.center();
			votingCard.show();
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
			groupMemberAmount.setText(Integer.toString(result.size())+ " Participations");
		}
		
	}

}
