package de.hdm.SoPra_WS1920.client.gui;


import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;



import de.hdm.SoPra_WS1920.shared.SurveyManagementAsync;
import de.hdm.SoPra_WS1920.shared.bo.Group;
import de.hdm.SoPra_WS1920.shared.bo.Movie;
import de.hdm.SoPra_WS1920.shared.bo.Screening;
import de.hdm.SoPra_WS1920.shared.bo.Survey;

public class SurveyCardResult extends DialogBox {
	
	Label result;
	Label group;
	Label movie;
	Button newSurvey;
	Image closeIcon;
	
	Survey surveyToShow;
	Movie movieOfSurvey;
	Group groupOfSurvey;
	Screening screeningOfSurvey;
	
	SurveyManagementAsync surveymanagement;
	
	SurveyCard parentCard;
	
	
	public SurveyCardResult(SurveyCard parentCard, Survey surveyToShow) {
		this.parentCard = parentCard;
		this.surveyToShow = surveyToShow;
	}
	
	public void onLoad() {
		super.onLoad();
		surveymanagement.getMovieBySurveyFK(surveyToShow.getId(), new GetMovieCallback());
		surveymanagement.getGroupById(surveyToShow.getGroupFK(), new GetGroupCallback());
		
		result = new Label("Results");
		result.setStyleName("CardViewTitle");
		//surveyEntrysOfSurvey = SurveyManagementImpl.getSurveyEntryBySurveyFK(survey.getId)
		//screeningOfSurvey = CinemaAdminImpl.getScreeningById(surveyentry.getScreeningFK)
		//movieOfSurvey = cinemaAdminImpl.getMovieById(screening.getMovieFK)
		movie = new Label();
		movie.setStyleName("CardViewParagraph");
		
		//groupofSurvey = SurveyManagementImpl.getGroupbyId(survey.getGroupFK)
		group = new Label();
		group.setStyleName("CardViewParagraph");
		
		closeIcon = new Image("/Images/png/007-close.png");
		closeIcon.setStyleName("CancelIcon");
		closeIcon.addClickHandler(new CancelClickHandler(this));
		
		//Platzhalter - Liste mit Ergebnissen
		
		newSurvey = new Button("New Survey with Votes only");
		newSurvey.setStyleName("InvisibleButton");
		//newSurvey.addClickHandler(new NewSurveyClickHandler());
	
		this.add(result);
		this.add(movie);
		this.add(group);
		//Liste mit Ergebnissen
		this.add(newSurvey);
		
	
	}
//	class NewSurveyClickHandler implements ClickHandler{
//
//		@Override
//		public void onClick(ClickEvent event) {
//			 //TODO Auto-generated method stub
//			parentCard.showSurveyCardEdit(surveyToShow);
//		}
//	}
	
	class CancelClickHandler implements ClickHandler{
		SurveyCardResult surveyCardResult;
		public CancelClickHandler(SurveyCardResult surveyCardResult) {
			// TODO Auto-generated constructor stub
			this.surveyCardResult = surveyCardResult;
		}

		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			
			if(parentCard==null) {
				surveyCardResult.hide();
			}else {
				parentCard.showSurveyCardView(surveyToShow);
				surveyCardResult.hide();
			}
			
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

}
